package com.wallpad.ventilation.repository.common;

public class ConvertNumber {
    private static final int numberOfBitsInAHalfByte = 4;
    private static final int halfByte = 0x0F;
    private static final char[] hexDigits = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    private static final String digits = "0123456789ABCDEF";

    /**
     * dec to hex
     *
     * @param dec                  dec value
     * @param sizeOfIntInHalfBytes size of int in half bytes
     * @return hex string
     */
    static String decToHex(int dec, int sizeOfIntInHalfBytes) {
        StringBuilder hexBuilder = new StringBuilder(sizeOfIntInHalfBytes);
        hexBuilder.setLength(sizeOfIntInHalfBytes);
        for (int i = sizeOfIntInHalfBytes - 1; i >= 0; --i) {
            int j = dec & halfByte;
            hexBuilder.setCharAt(i, hexDigits[j]);
            dec >>= numberOfBitsInAHalfByte;
        }
        return hexBuilder.toString();
    }

    /**
     * hex to dec
     *
     * @param s hex string
     * @return dec value
     */
    public static int hexToDec(String s) {
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16 * val + d;
        }
        return val;
    }
}