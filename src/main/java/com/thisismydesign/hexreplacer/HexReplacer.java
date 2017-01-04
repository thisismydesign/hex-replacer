package com.thisismydesign.hexreplacer;

import com.thisismydesign.stringprocessor.Replacer;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class HexReplacer implements Replacer {

    private Charset encoding;

    public HexReplacer(Charset encoding) {
        if (!isCharsetSupported(encoding.name())) throw new IllegalArgumentException(String.format("Charset %s is not " +
                "supported.", encoding));
        this.encoding = encoding;
    }

    public HexReplacer() {
        this(Charset.defaultCharset());
    }

    public static String mergePipes(String string) {
        return string.replaceAll("\\|\\|", " ");
    }

    @Override
    public String getReplacement(String string) {
        try {
            return betweenPipes(toHex(string));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private boolean isCharsetSupported(String name) {
        return Charset.availableCharsets().keySet().contains(name);
    }

    private String toHex(String string) throws UnsupportedEncodingException {
        return Hex.encodeHexString(string.getBytes(encoding));
    }

    private String betweenPipes(String string) {
        return "|" + string + "|";
    }
}
