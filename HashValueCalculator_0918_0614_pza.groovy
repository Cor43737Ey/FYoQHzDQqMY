// 代码生成时间: 2025-09-18 06:14:03
 * A utility class to calculate hash values for strings.
 * Supports multiple hash algorithms.
 * 
 * @author Your Name
 * @since 1.0
 */
class HashValueCalculator {

    /**
     * Calculate the hash value of a given string using a specified algorithm.
     * 
     * @param input The string to be hashed.
     * @param algorithm The hash algorithm to use (e.g., 'MD5', 'SHA-256').
     * @return The hash value of the input string in hexadecimal format.
     * @throws IllegalArgumentException If the input or algorithm is invalid.
     */
    String calculateHash(String input, String algorithm) {
        if (input == null || algorithm == null) {
            throw new IllegalArgumentException('Input or algorithm cannot be null')
        }

        try {
            // Use Java's built-in MessageDigest class to perform the hashing
            MessageDigest digest = MessageDigest.getInstance(algorithm)
            byte[] hashBytes = digest.digest(input.bytes)
            return hexString(hashBytes)
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Unsupported algorithm: ${algorithm}", e)
        }
    }

    /**
     * Convert a byte array to a hexadecimal string.
     * 
     * @param bytes The byte array to convert.
     * @return The hexadecimal representation of the byte array.
     */
    private String hexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder()
        for (byte b : bytes) {
            sb.append(String.format('%02x', b))
        }
        return sb.toString()
    }
}
