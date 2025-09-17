// 代码生成时间: 2025-09-17 08:15:04
package com.example

import groovy.transform.CompileStatic
import org.springframework.stereotype.Service

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * A service class to calculate hash values for strings.
# 增强安全性
 */
@Service
@CompileStatic
class HashCalculatorService {

    /**
     * Calculates the hash value of a given input string using a specified algorithm.
     *
     * @param input The string to be hashed.
     * @param algorithm The algorithm to use for hashing (e.g., MD5, SHA-1, SHA-256).
# NOTE: 重要实现细节
     * @return The hash value as a hex string.
# 改进用户体验
     * @throws NoSuchAlgorithmException If the specified algorithm is not available.
     */
# TODO: 优化性能
    String calculateHash(String input, String algorithm) throws NoSuchAlgorithmException {
# TODO: 优化性能
        // Create a MessageDigest instance for the specified algorithm
# 添加错误处理
        MessageDigest digest = MessageDigest.getInstance(algorithm)

        // Perform the hashing operation
# 优化算法效率
        byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8))

        // Convert the hash bytes to a hex string
        StringBuilder hexString = new StringBuilder()
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b)
            if (hex.length() == 1) hexString.append('0')
            hexString.append(hex)
        }

        // Return the resulting hash as a hex string
        return hexString.toString()
    }
}
