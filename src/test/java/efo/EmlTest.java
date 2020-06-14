package efo;

import efo.handlers.MyHandler;
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

public class EmlTest {
    AutoDetectParser autoParser = new AutoDetectParser();
    ContentHandler toXMLContentHandler = new ToXMLContentHandler();
    BodyContentHandler bodyContentHandler = new BodyContentHandler();
    MyHandler myHandler=new MyHandler();
    Metadata metadata = new Metadata();

    String inputDir="./test-files";
    String outputDir="./result";
    @Test
    public void testTikaParseEmlWithDocAttachment_output_html() throws TikaException, SAXException, IOException {
        String filename="eml-with-doc-attachment-test.eml";

        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        autoParser.parse(stream, toXMLContentHandler, metadata);
        System.out.println(metadata.toString());
        System.out.println("------------------------------");
        System.out.println(toXMLContentHandler.toString());

        Files.write(Paths.get(outputDir,filename.split("\\.")[0]+".xhtml"), toXMLContentHandler.toString().getBytes());
    }

    @Test
    public void testTikaParseEmlWithDocAttachment__output_txt() throws TikaException, SAXException, IOException {
        String filename="eml-with-doc-attachment-test.eml";
        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        autoParser.parse(stream, bodyContentHandler, metadata);
        System.out.println(bodyContentHandler.toString());
        System.out.println(metadata.toString());
        Files.write(Paths.get(outputDir,filename.split("\\.")[0]+".txt"),bodyContentHandler.toString().getBytes());
    }

    @Test
    public void testMyHandlerParseEmlWithDocAttachment__output_txt() throws TikaException, SAXException, IOException {
        String filename="eml-with-doc-attachment-test.eml";
        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        autoParser.parse(stream, myHandler, metadata);
        System.out.println(myHandler.toString());
        System.out.println(metadata.toString());
        Files.write(Paths.get(outputDir,filename.split("\\.")[0]+".txt"),myHandler.toString().getBytes());
    }

}
