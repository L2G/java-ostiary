/** Ostclient implements an Ostiary client in Java. Among other
 *  things, this allows any Java-enabled web browser to act as
 *  an Ostiary client, at least to a web server running Ostiaryd.
 */

import java.applet.Applet;
/*
 import java.awt.Label;
 import java.awt.TextField;
 import java.awt.GridLayout;
 */
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.math.BigInteger;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.security.NoSuchAlgorithmException;

public final class Ostclient extends Applet implements ActionListener {
	Panel ostpanel;
	Label rcvdLabel, sentLabel, statLabel;
	TextField hostField, portField, secretField, statField;

	public Dimension preferredSize() {
		return new Dimension(320, 256);
	}

	public void init() {
		Button goButton;
		GridBagLayout gbag = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		String ostHost, ostPort;

		setLayout(gbag);

		// Default layout
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Title
		gbc.weightx = 1.0;
		gbc.gridwidth = GridBagConstraints.REMAINDER; // Ends a row.
		Label ilabel = new Label("Ostiary Client");
		gbag.setConstraints(ilabel, gbc);
		add(ilabel);

		// Host
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		Label hlabel = new Label("Host:");
		gbag.setConstraints(hlabel, gbc);
		add(hlabel);

		gbc.weightx = 1.0;
		gbc.gridwidth = GridBagConstraints.REMAINDER; // Ends a row.
		try {
			ostHost = getParameter("OSTHOST");
		} catch (NullPointerException n) {
			ostHost = new String();
		}
		if (ostHost != null) {
			hostField = new TextField(ostHost);
		} else {
			hostField = new TextField();
		}
		gbag.setConstraints(hostField, gbc);
		add(hostField);

		// Port
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		Label plabel = new Label("Port:");
		gbag.setConstraints(plabel, gbc);
		add(plabel);

		gbc.weightx = 1.0;
		gbc.gridwidth = GridBagConstraints.REMAINDER; // Ends a row.
		try {
			ostPort = getParameter("OSTPORT");
		} catch (NullPointerException n) {
			ostPort = new String();
		}
		if (ostPort != null) {
			portField = new TextField(ostPort);
		} else {
			portField = new TextField();
		}
		gbag.setConstraints(portField, gbc);
		add(portField);

		// Passphrase
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		Label slabel = new Label("Passphrase:");
		gbag.setConstraints(slabel, gbc);
		add(slabel);

		gbc.weightx = 1.0;
		gbc.gridwidth = GridBagConstraints.REMAINDER; // Ends a row.
		secretField = new TextField(OstiaryHash.HASH_SIZE * 2);
		secretField.setEchoChar('*');
		gbag.setConstraints(secretField, gbc);
		add(secretField);

		// Button
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		Label blabel = new Label("");
		gbag.setConstraints(blabel, gbc);
		add(blabel);

		gbc.weightx = 1.0;
		gbc.gridwidth = GridBagConstraints.REMAINDER; // Ends a row.
		goButton = new Button("Go");
		gbag.setConstraints(goButton, gbc);
		add(goButton);
		goButton.addActionListener(this);

		// Received
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		Label rlabel = new Label("Rcvd:");
		gbag.setConstraints(rlabel, gbc);
		add(rlabel);

		gbc.weightx = 1.0;
		gbc.gridwidth = GridBagConstraints.REMAINDER; // Ends a row.
		rcvdLabel = new Label();
		gbag.setConstraints(rcvdLabel, gbc);
		add(rcvdLabel);

		// Sent
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		Label nlabel = new Label("Sent:");
		gbag.setConstraints(nlabel, gbc);
		add(nlabel);

		gbc.weightx = 1.0;
		gbc.gridwidth = GridBagConstraints.REMAINDER; // Ends a row.
		sentLabel = new Label();
		gbag.setConstraints(sentLabel, gbc);
		add(sentLabel);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridheight = 3;

		// Status
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		Label tlabel = new Label("Status:");
		gbag.setConstraints(tlabel, gbc);
		add(tlabel);

		gbc.weightx = 1.0;
		gbc.gridwidth = GridBagConstraints.REMAINDER; // Ends a row.
		statField = new TextField();
		gbag.setConstraints(statField, gbc);
		add(statField);

		// add("Center", wp);
	}

	public static void main(String[] args) {
		Frame f = new Frame("Ostiary Client");

		Ostclient ost = new Ostclient();

		ost.init();

		f.add("Center", ost);

		f.pack();
		f.setVisible(true);
	}

	public void actionPerformed(ActionEvent evt) {
		int nRead;
		byte[] response = null;

		// Here's where the magic happens...
		rcvdLabel.setText("");
		sentLabel.setText("");
		statField.setText("");

		String host = hostField.getText();
		String port = portField.getText();
		if (host.isEmpty() || port.isEmpty()) {
			statField.setText("Please fill in both host and port fields");
			return;
		}
		
		Socket MyClient = null;
		try {
			statField.setText("Connecting...");
			MyClient = new Socket(host, Integer.parseInt(port));
		} catch (IOException e) {
			statField.setText("Socket error: " + e.getMessage());
			return;
		} catch (SecurityException secEvt) {
			statField.setText("Security violation: " + secEvt.getMessage());
			return;
		} catch (NumberFormatException e) {
			statField.setText("Format error (did you enter a port number?)");
			return;
		}

		DataInputStream input;
		try {
			input = new DataInputStream(MyClient.getInputStream());
		} catch (IOException e) {
			statField.setText("Stream error: " + e.getMessage());
			return;
		}

		DataOutputStream output;
		try {
			output = new DataOutputStream(MyClient.getOutputStream());
		} catch (IOException e) {
			statField.setText("Stream error: " + e.getMessage());
			return;
		}

		// Actually do the work.

		// Read the challenge.
		byte[] challenge;
		challenge = new byte[OstiaryHash.HASH_SIZE];
		try {
			nRead = input.read(challenge, 0, challenge.length);
		} catch (IOException e) {
			statField.setText(e.getMessage());
			return;
		}

		if (nRead < challenge.length) {
			rcvdLabel.setText("Only got " + nRead + " bytes of challenge!");
			statField.setText("Locked out?");
			return;
		} else {
			rcvdLabel.setText(new BigInteger(1, challenge).toString(16));
		}

		// Calculate the new hash.
		try {
			response = new OstiaryHash(challenge, challenge.length, secretField
					.getText().getBytes(), secretField.getText().length()).getResponseHash();
		} catch (NoSuchAlgorithmException e) {
			statField.setText(e.getMessage());
			return;
		}

		// Send it.
		try {
			output.write(response, 0, response.length);
		} catch (IOException e) {
			statField.setText(e.getMessage());
			return;
		}

		sentLabel.setText(new BigInteger(1, response).toString(16));
		statField.setText("Operation completed.");

		try {
			output.close();
			input.close();
			MyClient.close();
		} catch (IOException e) {
			statField.setText(e.getMessage());
		}
	}
}
