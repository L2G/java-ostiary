import java.awt.Dimension;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import junit.framework.TestCase;

/**
 * JUnit tests for the Ostclient applet.
 * @author Larry Gilbert <https://github.com/L2G>
 */
public class OstclientTest extends TestCase {
	Ostclient ost;

	protected void setUp() throws Exception {
		ost = new Ostclient();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		ost.destroy();
		super.tearDown();
	}

	public void testInit() {
		fail("Not yet implemented"); // TODO
	}

	public void testPreferredSize() {
		/* The value 256 is arbitrary and was chosen because it's the smaller
		 * of the two dimensions traditionally used by the Ostclient applet.
		 */
		Dimension dim = ost.preferredSize();
		assertTrue("Dimensions are positive and > 256 each way",
				   (dim.width >= 256) && (dim.height >= 256));
	}

	public void testMain() {
		fail("Not yet implemented"); // TODO
	}

	public void testActionPerformed() {
		fail("Not yet implemented"); // TODO
	}

	public void testOstiaryHash() throws NoSuchAlgorithmException {
		/* If I leave this as an unsigned, 64-digit hexadecimal number, then
		 * Java will convert it to a 17-byte, signed, two's-complement
		 * BigInteger, and I'll have to test for and hack off the extra 0xFF
		 * byte.  Or I could leave this as a negative 63-digit number and not
		 * think about it anymore... 
		 */
		String challengeString = "-d5423863033e4ef6143716059a9fa16285c0165d41d01ee6ca957ff30be73e1";
		String responseString = "72f5556a4e4c61cdfd198426718c4ba72db2a169c619cfa453f5410ea2821129";
		
		byte[] secret = "Sample".getBytes();
		assertEquals(6, secret.length);
		
		byte[] challenge = (new BigInteger(challengeString, 16)).toByteArray();
		assertEquals(Ostclient.HASH_SIZE, challenge.length);
		
		byte[] hash = Ostclient.OstiaryHash(challenge, challenge.length, secret, secret.length);
		assertEquals(Ostclient.HASH_SIZE, hash.length);
		assertEquals(responseString, new BigInteger(1,hash).toString(16));
	}

}
