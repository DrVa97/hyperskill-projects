package encryptdecrypt.algorithms.strategies;

import encryptdecrypt.algorithms.Algorithm;

public class UnicodeAlgorithm implements Algorithm {

    public String encrypt(final String plainText, final int key) {
        StringBuilder stringBuilder = new StringBuilder();

        for (char c : plainText.toCharArray()) {
            stringBuilder.append((char) (c + key));
        }

        return stringBuilder.toString();
    }

    public String decrypt(final String cipherText, final int key) {
        return encrypt(cipherText, -key);
    }
}
