import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import java.util.Locale;


public class OstclientApplet extends JApplet {
	private JTextField txtHost;
	private JTextField txtPort;
	private JTextField txtStatus;
	private JPasswordField txtSecret;
	private JTextField txtChallenge;
	private JTextField txtResponse;

	/**
	 * Create the applet.
	 */
	public OstclientApplet() {
		getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setLocale(Locale.ENGLISH);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setPreferredSize(new Dimension(475, 245));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{67, 122, 0, 72, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 17, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblHost = new JLabel("Host");
		GridBagConstraints gbc_lblHost = new GridBagConstraints();
		gbc_lblHost.anchor = GridBagConstraints.EAST;
		gbc_lblHost.insets = new Insets(5, 0, 5, 5);
		gbc_lblHost.gridx = 0;
		gbc_lblHost.gridy = 0;
		getContentPane().add(lblHost, gbc_lblHost);
		
		txtHost = new JTextField();
		GridBagConstraints gbc_txtHost = new GridBagConstraints();
		gbc_txtHost.insets = new Insets(5, 0, 5, 5);
		gbc_txtHost.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtHost.gridx = 1;
		gbc_txtHost.gridy = 0;
		getContentPane().add(txtHost, gbc_txtHost);
		txtHost.setColumns(64);
		
		JLabel lblPort = new JLabel("Port");
		GridBagConstraints gbc_lblPort = new GridBagConstraints();
		gbc_lblPort.anchor = GridBagConstraints.EAST;
		gbc_lblPort.insets = new Insets(5, 0, 5, 5);
		gbc_lblPort.gridx = 2;
		gbc_lblPort.gridy = 0;
		getContentPane().add(lblPort, gbc_lblPort);
		
		txtPort = new JTextField();
		GridBagConstraints gbc_txtPort = new GridBagConstraints();
		gbc_txtPort.insets = new Insets(5, 0, 5, 5);
		gbc_txtPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPort.gridx = 3;
		gbc_txtPort.gridy = 0;
		getContentPane().add(txtPort, gbc_txtPort);
		txtPort.setColumns(5);
		
		JLabel lblSecret = new JLabel("Secret");
		GridBagConstraints gbc_lblSecret = new GridBagConstraints();
		gbc_lblSecret.anchor = GridBagConstraints.EAST;
		gbc_lblSecret.insets = new Insets(0, 0, 5, 5);
		gbc_lblSecret.gridx = 0;
		gbc_lblSecret.gridy = 1;
		getContentPane().add(lblSecret, gbc_lblSecret);
		
		txtSecret = new JPasswordField();
		txtSecret.setColumns(64);
		GridBagConstraints gbc_txtSecret = new GridBagConstraints();
		gbc_txtSecret.gridwidth = 3;
		gbc_txtSecret.insets = new Insets(0, 0, 5, 5);
		gbc_txtSecret.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSecret.gridx = 1;
		gbc_txtSecret.gridy = 1;
		getContentPane().add(txtSecret, gbc_txtSecret);
		
		JButton btnSend = new JButton("Send");
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.gridwidth = 3;
		gbc_btnSend.insets = new Insets(0, 0, 5, 0);
		gbc_btnSend.gridx = 1;
		gbc_btnSend.gridy = 2;
		getContentPane().add(btnSend, gbc_btnSend);
		
		JLabel lblStatus = new JLabel("Status");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.anchor = GridBagConstraints.EAST;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 4;
		getContentPane().add(lblStatus, gbc_lblStatus);
		
		txtStatus = new JTextField();
		txtStatus.setAutoscrolls(false);
		txtStatus.setHorizontalAlignment(SwingConstants.LEFT);
		txtStatus.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a diam lectus. Sed sit amet ipsum mauris. Maecenas congue ligula ac quam viverra nec consectetur ante hendrerit. Donec et mollis dolor. Praesent et diam eget libero egestas mattis sit amet vitae augue. Nam tincidunt congue enim, ut porta lorem lacinia consectetur. Donec ut libero sed arcu vehicula ultricies a non tortor. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ut gravida lorem. Ut turpis felis, pulvinar a semper sed, adipiscing id dolor. Pellentesque auctor nisi id magna consequat sagittis. Curabitur dapibus enim sit amet elit pharetra tincidunt feugiat nisl imperdiet. Ut convallis libero in urna ultrices accumsan. Donec sed odio eros. Donec viverra mi quis quam pulvinar at malesuada arcu rhoncus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. In rutrum accumsan ultricies. Mauris vitae nisi at sem facilisis semper ac in est.");
		txtStatus.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtStatus.setEditable(false);
		GridBagConstraints gbc_txtStatus = new GridBagConstraints();
		gbc_txtStatus.gridwidth = 3;
		gbc_txtStatus.insets = new Insets(0, 0, 5, 5);
		gbc_txtStatus.fill = GridBagConstraints.BOTH;
		gbc_txtStatus.gridx = 1;
		gbc_txtStatus.gridy = 4;
		getContentPane().add(txtStatus, gbc_txtStatus);
		txtStatus.setColumns(999);
		
		JLabel lblChallenge = new JLabel("Challenge");
		GridBagConstraints gbc_lblChallenge = new GridBagConstraints();
		gbc_lblChallenge.anchor = GridBagConstraints.EAST;
		gbc_lblChallenge.insets = new Insets(0, 0, 5, 5);
		gbc_lblChallenge.gridx = 0;
		gbc_lblChallenge.gridy = 5;
		getContentPane().add(lblChallenge, gbc_lblChallenge);
		
		txtChallenge = new JTextField();
		txtChallenge.setText("0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef");
		txtChallenge.setHorizontalAlignment(SwingConstants.LEFT);
		txtChallenge.setEditable(false);
		txtChallenge.setFont(new Font("Monospaced", Font.PLAIN, 11));
		GridBagConstraints gbc_txtChallenge = new GridBagConstraints();
		gbc_txtChallenge.gridwidth = 3;
		gbc_txtChallenge.insets = new Insets(0, 0, 5, 5);
		gbc_txtChallenge.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtChallenge.gridx = 1;
		gbc_txtChallenge.gridy = 5;
		getContentPane().add(txtChallenge, gbc_txtChallenge);
		txtChallenge.setColumns(10);
		
		JLabel lblResponse = new JLabel("Response");
		GridBagConstraints gbc_lblResponse = new GridBagConstraints();
		gbc_lblResponse.anchor = GridBagConstraints.EAST;
		gbc_lblResponse.insets = new Insets(0, 0, 0, 5);
		gbc_lblResponse.gridx = 0;
		gbc_lblResponse.gridy = 6;
		getContentPane().add(lblResponse, gbc_lblResponse);
		
		txtResponse = new JTextField();
		txtResponse.setText("0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef");
		txtResponse.setHorizontalAlignment(SwingConstants.LEFT);
		txtResponse.setEditable(false);
		txtResponse.setFont(new Font("Monospaced", Font.PLAIN, 11));
		GridBagConstraints gbc_txtResponse = new GridBagConstraints();
		gbc_txtResponse.insets = new Insets(0, 0, 0, 5);
		gbc_txtResponse.gridwidth = 3;
		gbc_txtResponse.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtResponse.gridx = 1;
		gbc_txtResponse.gridy = 6;
		getContentPane().add(txtResponse, gbc_txtResponse);
		txtResponse.setColumns(10);

	}

}
