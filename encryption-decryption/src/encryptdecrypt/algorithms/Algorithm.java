package encryptdecrypt.algorithms;

public interface Algorithm {
    String encrypt(final String plainText, final int key);

    String decrypt(final String cipherText, final int key);
}
