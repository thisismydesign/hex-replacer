package com.thisismydesign.hexreplacer;

import com.thisismydesign.stringprocessor.Patterns;
import com.thisismydesign.stringprocessor.StringProcessor;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class HexReplacerTest {

    @Test
    public void mergePipes() throws Exception {
        assertEquals("|A1 B2|", HexReplacer.mergePipes("|A1||B2|"));
    }

    @Test
    public void getReplacement_ASCII_ShouldBeReplacedByTwoDigitHexBetweenPipes() throws Exception {
        assertEquals("hi|3B|there", StringProcessor.replace("hi;there", Pattern.compile(";"), new HexReplacer()));
    }

    @Test
    public void getReplacement_MultipleASCII_ShouldAllBeReplacedByTwoDigitHexBetweenPipes() throws Exception {
        Pattern[] patterns = new Pattern[] {Pattern.compile(";"), Pattern.compile("h")};
        assertEquals("|68|i|3B|t|68|ere", StringProcessor.replaceAll("hi;there", patterns, new HexReplacer()));
    }

    @Test
    public void getReplacement_NonASCII_UTF8_ShouldBeReplacedByTwoDigitHexBetweenPipes() throws Exception {
        String str = StringEscapeUtils.unescapeJava("\\u00e1");
        Charset utf8 = Charset.forName("UTF-8");
        assertEquals("|C3 A1|", StringProcessor.replace(str, Patterns.nonASCII, new HexReplacer(utf8)));
    }

    @Test
    public void getReplacement_NonASCII_UTF16_ShouldBeReplacedByTwoDigitHexBetweenPipes() throws Exception {
        String str = StringEscapeUtils.unescapeJava("\\u00e1");
        Charset utf16 = Charset.forName("UTF-16");
        assertEquals("|FE FF 00 E1|", StringProcessor.replace(str, Patterns.nonASCII, new HexReplacer(utf16)));
    }
}
