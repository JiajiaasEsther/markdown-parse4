import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.*;

public class MarkdownParseTest {
    @Test
    public void testSnippet1() throws IOException {
        String contents= Files.readString(Path.of("./snippet1.md"));
        List<String> expect = List.of("[a link](url.com)", "another link`", "cod[e", "code]");
        assertEquals( expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void testSnippet2() throws IOException {
        String contents= Files.readString(Path.of("./snippet2.md"));
        List<String> expect = List.of("[a nested link](b.com)", "a nested parenthesized url", 
        "some escaped [ brackets ]");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void testSnippet3() throws IOException {
        String contents= Files.readString(Path.of("./snippet3.md"));
        List<String> expect = List.of("this title text is really long and takes up more than one line and has some line breaks]( https://www.twitter.com )", 
        "this title text is really long and takes up more than one line",
        "[this link doesn't have a closing parenthesis](github.com And there's still some more text after that.", 
        "[this link doesn't have a closing parenthesis for a while](https://cse.ucsd.edu/)",
        "And then there's more text");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
}

