package encryptdecrypt.algorithms.strategies;

import encryptdecrypt.algorithms.Algorithm;

public class ShiftAlgorithm implements Algorithm {

    public String encrypt(final String plainText, final int key) {
        StringBuilder stringBuilder = new StringBuilder();

        for (char c : plainText.toCharArray()) {
            if ((c > 64 && c < 91) || (c > 96 && c < 123)) {
                stringBuilder.append(shiftCharByKey(c, key));
            } else {
                stringBuilder.append(c);
            }
        }

        return stringBuilder.toString();
    }

    public String decrypt(final String cipherText, final int key) {

        return encrypt(cipherText, -key);
    }

    private char shiftCharByKey(char character, int key) {
        int charOrder;
        boolean isUpperCase = false;

        if (character > 64 && character < 91) {
            charOrder = character - 65;
            isUpperCase = true;
        } else if (character > 96 && character < 123) {
            charOrder = character - 97;
        } else {
            return character;
        }

        charOrder = (charOrder + key) % 26;
        charOrder += charOrder < 0 ? 26 : 0;

        return (char) (charOrder + (isUpperCase ? 65 : 97));
    }
}
