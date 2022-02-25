// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        String [] split_lines = markdown.split("\n"); 
        for(String line : split_lines) {
            System.out.println(line);
            if(!line.contains("[") || !line.contains("]") || !line.contains("(") || !line.contains(")")){
                continue;
            }
            currentIndex = 0;
            int nextOpenBracket = line.indexOf("[", currentIndex);
            int nextCloseBracket = line.indexOf("]", nextOpenBracket);
            int openParen = line.indexOf("(", nextCloseBracket);
            int closeParen = line.indexOf(")", openParen);
            toReturn.add(line.substring(openParen + 1, closeParen));
            // currentIndex = closeParen + 1;
            System.out.println(currentIndex);
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}
