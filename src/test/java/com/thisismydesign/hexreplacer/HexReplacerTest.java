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
        assertEquals("|a1 b2|", HexReplacer.mergePipes("|a1||b2|"));
    }

    @Test
    public void getReplacement_ASCII_ShouldBeReplacedByTwoDigitHexBetweenPipes() throws Exception {
        assertEquals("hi|3b|there", StringProcessor.replace("hi;there", Pattern.compile(";"), new HexReplacer()));
    }

    @Test
    public void getReplacement_MultipleASCII_ShouldAllBeReplacedByTwoDigitHexBetweenPipes() throws Exception {
        Pattern[] patterns = new Pattern[] {Pattern.compile(";"), Pattern.compile("h")};
        assertEquals("|68|i|3b|t|68|ere", StringProcessor.replaceAll("hi;there", patterns, new HexReplacer()));
    }

    @Test
    public void getReplacement_NonASCII_UTF8_ShouldBeReplacedByTwoDigitHexBetweenPipes() throws Exception {
        String str = StringEscapeUtils.unescapeJava("\\u00e1");
        Charset utf8 = Charset.forName("UTF-8");
        assertEquals("|c3a1|", StringProcessor.replace(str, Patterns.nonASCII, new HexReplacer(utf8)));
    }

    @Test
    public void getReplacement_NonASCII_UTF16_ShouldBeReplacedByTwoDigitHexBetweenPipes() throws Exception {
        String str = StringEscapeUtils.unescapeJava("\\u00e1");
        Charset utf16 = Charset.forName("UTF-16");
        assertEquals("|feff00e1|", StringProcessor.replace(str, Patterns.nonASCII, new HexReplacer(utf16)));
    }
}
