# HexReplacer

Implements [StringProcessor](https://github.com/thisismydesign/string-processor)'s Replacer interface to replace
strings with their Hex values.

Format: |A1 B2|

### API

#### public HexReplacer()

Default constructor using default Charset

#### public HexReplacer(Charset encoding)

Constructor using custom Charset

#### public static String mergePipes(String string)

Replaces pipes next to each other with a space to create consecutive Hex format. |A1||B2| => |A1 B2|

#### public String getReplacement(String string)

Returns Hex representation of 'string'. Used by StringProcessor's 'replace' method.
