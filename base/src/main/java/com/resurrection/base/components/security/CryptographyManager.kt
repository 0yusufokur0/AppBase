package com.resurrection.base.components.security

import android.util.Base64
import java.io.UnsupportedEncodingException
import java.nio.charset.StandardCharsets
import java.security.GeneralSecurityException
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class CryptographyManager {

    companion object {
        private const val AES_MODE = "AES/CBC/PKCS7Padding"
        private const val CHARSET = "UTF-8"
        private const val HASH_ALGORITHM = "SHA-256"
        private val ivBytes = byteArrayOf(
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00
        )
    }

    private fun generateKey(password: String): SecretKeySpec {
        val digest = MessageDigest.getInstance(HASH_ALGORITHM)
        val bytes = password.toByteArray(StandardCharsets.UTF_8)
        digest.update(bytes, 0, bytes.size)
        val key = digest.digest()
        return SecretKeySpec(key, "AES")
    }

    @Throws(GeneralSecurityException::class)
    fun encrypt(password: String, message: String): String {
        return try {
            val key = generateKey(password)
            val cipherText = encrypt(key, ivBytes, message.toByteArray(charset(CHARSET)))
            Base64.encodeToString(cipherText, Base64.NO_WRAP)
        } catch (e: UnsupportedEncodingException) {
            throw GeneralSecurityException(e)
        }
    }

    fun encrypt(key: SecretKeySpec?, iv: ByteArray?, message: ByteArray?): ByteArray {
        val cipher = Cipher.getInstance(AES_MODE)
        val ivSpec = IvParameterSpec(iv)
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec)
        return cipher.doFinal(message)
    }

    @Throws(GeneralSecurityException::class)
    fun decrypt(password: String, base64EncodedCipherText: String?): String {
        return try {
            val key = generateKey(password)
            val decodedCipherText = Base64.decode(base64EncodedCipherText, Base64.NO_WRAP)
            val decryptedBytes = decrypt(key, ivBytes, decodedCipherText)
            String(decryptedBytes, charset(CHARSET))
        } catch (e: UnsupportedEncodingException) {
            throw GeneralSecurityException(e)
        }
    }

    fun decrypt(key: SecretKeySpec?, iv: ByteArray?, decodedCipherText: ByteArray?): ByteArray {
        val cipher = Cipher.getInstance(AES_MODE)
        val ivSpec = IvParameterSpec(iv)
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec)
        return cipher.doFinal(decodedCipherText)
    }
}