import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Rick Ingles
 * @author Larry Gilbert <http://L2G.github.com/>
 *
 */
public class OstiaryHash {

	public static final String HASH_ALGO = "SHA-256";
	public static final int HASH_SIZE = 32;  // 32 bytes = 256 bits
	private byte[] response;
	
	/**
	 * Calculate an Ostiary Version 4 hash from the challenge and the secret.
	 * Basically HMAC with a CHAP challenge, using SHA-256 as the the hash.
	 */
	
	public OstiaryHash(byte[] in_hash, int in_size,
			byte[] secret, int secret_size) throws NoSuchAlgorithmException {
		final int HASH_BIN_SIZE = HASH_SIZE;
		final int HASH_BLOCK_SIZE = 64;
		int i;
		byte[] out_hash;
		byte[] hash_input;
	
		assert in_size == HASH_SIZE;
	
		hash_input = new byte[HASH_BLOCK_SIZE + HASH_BIN_SIZE];
		MessageDigest md1 = MessageDigest.getInstance(HASH_ALGO);
	
		for (i = 0; i < hash_input.length; i++) {
			hash_input[i] = 0;
		}
	
		if (secret_size < HASH_BLOCK_SIZE) {
			for (i = 0; i < secret_size; i++) {
				hash_input[i] = secret[i];
			}
		} else {
			/* TODO: fix this! */
		}
	
		/* XOR the first block with the inner pad value. */
		for (i = 0; i < HASH_BLOCK_SIZE; i++) {
			hash_input[i] ^= 0x36;
		}
	
		/* Append the salt hash from the server. */
		for (i = 0; i < in_size; i++) {
			hash_input[i + HASH_BLOCK_SIZE] = in_hash[i];
		}
	
		/* Do the inner hash. */
		md1.reset();
		md1.update(hash_input, 0, HASH_BLOCK_SIZE + in_size);
		out_hash = md1.digest();
	
		/* Clear the input again. */
		for (i = 0; i < hash_input.length; i++) {
			hash_input[i] = 0;
		}
	
		if (secret_size < HASH_BLOCK_SIZE) {
			for (i = 0; i < secret_size; i++) {
				hash_input[i] = secret[i];
			}
		} else {
			/* TODO: fix this! */
		}
	
		/* The outer pad XOR value. */
		for (i = 0; i < HASH_BLOCK_SIZE; i++) {
			hash_input[i] ^= 0x5c;
		}
	
		/* Append the inner hash. */
		for (i = 0; i < out_hash.length; i++) {
			hash_input[i + HASH_BLOCK_SIZE] = out_hash[i];
		}
	
		/* Do the outer hash. */
		md1.reset();
		md1.update(hash_input, 0, HASH_BLOCK_SIZE + out_hash.length);
		out_hash = md1.digest();
		
		response = out_hash;
	}

	public byte[] getResponseHash() {
		return response;
	}
}
