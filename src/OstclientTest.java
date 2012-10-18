import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import junit.framework.TestCase;


public class OstclientTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testInit() {
		fail("Not yet implemented"); // TODO
	}

	public void testPreferredSize() {
		fail("Not yet implemented"); // TODO
	}

	public void testMain() {
		fail("Not yet implemented"); // TODO
	}

	public void testActionPerformed() {
		fail("Not yet implemented"); // TODO
	}

	public void testOstiaryHash() throws NoSuchAlgorithmException {
		String challengeString = "-d5423863033e4ef6143716059a9fa16285c0165d41d01ee6ca957ff30be73e1";
		String responseString = "72f5556a4e4c61cdfd198426718c4ba72db2a169c619cfa453f5410ea2821129";
		
		byte[] secret = "Sample".getBytes();
		assertEquals(6, secret.length);
		
		byte[] challenge = (new BigInteger(challengeString, 16)).toByteArray();
		assertEquals(32, challenge.length);
		
		byte[] hash = Ostclient.OstiaryHash(challenge, 32, secret, secret.length);
		assertEquals(32, hash.length);
		assertEquals(responseString, new BigInteger(1,hash).toString(16));
	}

}
