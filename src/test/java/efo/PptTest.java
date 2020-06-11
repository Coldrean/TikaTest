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

public class PptTest {
    AutoDetectParser autoParser = new AutoDetectParser();
    ContentHandler contentHandler = new ToXMLContentHandler();
    BodyContentHandler bodyContentHandler = new BodyContentHandler();
    LinkContentHandler linkContentHandler=new LinkContentHandler();
    Metadata metadata = new Metadata();
    String inputDir="./test-files";
    String outputDir="./result";

    @Test
    public void testTikaParseExcel_output_html() throws TikaException, SAXException, IOException {
        String filename= "ppt-test-with-image.ppt";
        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        autoParser.parse(stream, contentHandler, metadata);
        System.out.println(contentHandler.toString());
        Files.write(Paths.get(outputDir,filename.split("\\.")[0]+".xhtml"),contentHandler.toString().getBytes());
    }

    @Test
    public void testTikaParseExcel_output_txt() throws TikaException, SAXException, IOException {
        String filename= "ppt-test-with-image.ppt";
        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        autoParser.parse(stream, bodyContentHandler, metadata);
        System.out.println(bodyContentHandler.toString());
        System.out.println(metadata.toString());
        Files.write(Paths.get(outputDir,filename.split("\\.")[0]+".txt"),bodyContentHandler.toString().getBytes());
    }
}