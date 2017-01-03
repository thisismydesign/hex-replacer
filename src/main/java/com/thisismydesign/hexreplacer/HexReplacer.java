package com.thisismydesign.hexreplacer;

import com.thisismydesign.stringprocessor.Replacer;
import org.apache.commons.codec.binary.Hex;

public class HexReplacer extends Replacer {

    public static String mergePipes(String string) {
        return string.replaceAll("\\|\\|", " ");
    }

    @Override
    public String getReplacement(String string) {
        return betweenPipes(toHex(string));
    }

    private static String toHex(String string) {
        return Hex.encodeHexString(string.getBytes());
    }

    private static String betweenPipes(String string) {
        return "|" + string + "|";
    }
}
