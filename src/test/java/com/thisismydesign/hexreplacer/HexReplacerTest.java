package com.thisismydesign.hexreplacer;

import com.thisismydesign.stringprocessor.StringProcessor;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class HexReplacerTest {

    @Test
    public void mergePipes() throws Exception {
        assertEquals("|a1 b2|", HexReplacer.mergePipes("|a1||b2|"));
    }

    @Test
    public void getReplacement_ASCII_ShouldBeReplacedByTwoDigitHex() throws Exception {
        assertEquals("hi|3b|there", StringProcessor.replace("hi;there", Pattern.compile(";"), new HexReplacer()));
    }

    @Test
    public void getReplacement_MultipleASCII_ShouldAllBeReplacedByTwoDigitHex() throws Exception {
        Pattern[] patterns = new Pattern[] {Pattern.compile(";"), Pattern.compile("h")};
        assertEquals("|68|i|3b|t|68|ere", StringProcessor.replaceAll("hi;there", patterns, new HexReplacer()));
    }

}
