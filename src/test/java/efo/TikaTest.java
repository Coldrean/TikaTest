package efo;


import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.ToXMLContentHandler;
import org.junit.Test;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TikaTest {
    AutoDetectParser autoParser = new AutoDetectParser();
    ContentHandler handler = new ToXMLContentHandler();
    Metadata metadata = new Metadata();

    @Test
    public void testTikaParseEmlWithDocAttachment() throws TikaException, SAXException, IOException {
        String filename="./test-files/new-word.docx";
        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(filename);
        autoParser.parse(stream, handler, metadata);

        System.out.println(handler.toString());
        Files.write(Paths.get("./result.xhtml"),handler.toString().getBytes());
    }

}
