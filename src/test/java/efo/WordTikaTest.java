package efo;


import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.LinkContentHandler;
import org.apache.tika.sax.ToXMLContentHandler;
import org.junit.Test;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WordTikaTest {
    AutoDetectParser autoParser = new AutoDetectParser();
    ContentHandler contentHandler = new ToXMLContentHandler();
    BodyContentHandler bodyContentHandler = new BodyContentHandler();
    LinkContentHandler linkContentHandler=new LinkContentHandler();
    Metadata metadata = new Metadata();

    String resultRir="./result";
    @Test
    public void testTikaParseEmlWithDocAttachment_output_html() throws TikaException, SAXException, IOException {
        String filename="./test-files/这是word的名字.doc";
        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(filename);
        autoParser.parse(stream, contentHandler, metadata);
        System.out.println(contentHandler.toString());
        Files.write(Paths.get("./result/word-result.xhtml"),contentHandler.toString().getBytes());
    }

    @Test
    public void testTikaParseEmlWithDocAttachment__output_txt() throws TikaException, SAXException, IOException {
        String filename="./test-files/这是word的名字.doc";
        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(filename);
        autoParser.parse(stream, bodyContentHandler, metadata);
        System.out.println(bodyContentHandler.toString());
        System.out.println(metadata.toString());
        Files.write(Paths.get("./result/word-result.txt"),bodyContentHandler.toString().getBytes());
    }

}
