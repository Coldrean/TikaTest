package efo;

import efo.extractor.ExtractEmbeddedFiles;
import efo.handlers.MyHandler;
import org.apache.tika.exception.TikaException;
import org.apache.tika.extractor.ParsingEmbeddedDocumentExtractor;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.RecursiveParserWrapper;
import org.apache.tika.sax.*;
import org.apache.tika.sax.xpath.Matcher;
import org.apache.tika.sax.xpath.MatchingContentHandler;
import org.apache.tika.sax.xpath.XPathParser;
import org.junit.Test;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EmlTest {

    AutoDetectParser autoParser = new AutoDetectParser();
    ContentHandler toXMLContentHandler = new ToXMLContentHandler();
    BodyContentHandler bodyContentHandler = new BodyContentHandler();
    MyHandler myHandler=new MyHandler();
    Metadata metadata = new Metadata();
    String RESOURCE_NAME_KEY = "resourceName";
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
        String filename="word-with-image-test.doc";
        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        ExtractEmbeddedFiles extractEmbeddedFiles=new ExtractEmbeddedFiles();
        extractEmbeddedFiles.extract(stream,Paths.get(outputDir));
    }

    @Test
    public void testTikaParseEmlWithTwoDocAttachment_output_html() throws TikaException, SAXException, IOException {
        String filename="eml-with-two-attachments.eml";

        XPathParser xhtmlParser = new XPathParser("http://www.w3.org/1999/xhtml", XHTMLContentHandler.XHTML);
        ////div[@class='email-entry']
        Matcher divContentMatcher = xhtmlParser.parse("html[@xmlns='http://www.w3.org/1999/xhtml']");
        ContentHandler handler = new MatchingContentHandler(
                new ToXMLContentHandler(), divContentMatcher);

        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        try (InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());) {
            parser.parse(stream, handler, metadata);
            System.out.printf(handler.toString());
        }
    }

    @Test
    public void recursiveParserWrapperExample() throws IOException,
            SAXException, TikaException {
        String filename="eml-with-two-attachments.eml";
        Parser p = new AutoDetectParser();
        ContentHandlerFactory factory = new BasicContentHandlerFactory(
                BasicContentHandlerFactory.HANDLER_TYPE.TEXT, -1);

        RecursiveParserWrapper wrapper = new RecursiveParserWrapper(p);
        Metadata metadata = new Metadata();
        metadata.set(RESOURCE_NAME_KEY, "test_recursive_embedded.docx");
        ParseContext context = new ParseContext();
        RecursiveParserWrapperHandler handler = new RecursiveParserWrapperHandler(factory, -1);
        try (InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString())) {
            wrapper.parse(stream, handler, metadata, context);
        }
        System.out.printf(handler.getMetadataList().get(0).get(""));
        //return handler.getMetadataList();
    }


}
