package efo.extractor;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.RecursiveParserWrapper;
import org.apache.tika.sax.BasicContentHandlerFactory;
import org.apache.tika.sax.ContentHandlerFactory;
import org.apache.tika.sax.RecursiveParserWrapperHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

public class MetadataListExtractor {
    Parser p = new AutoDetectParser();
    String RESOURCE_NAME_KEY = "resourceName";

    public List<Metadata> extract(InputStream inputStream) throws TikaException, SAXException, IOException {

        ContentHandlerFactory factory = new BasicContentHandlerFactory(
                BasicContentHandlerFactory.HANDLER_TYPE.TEXT, -1);

        RecursiveParserWrapper wrapper = new RecursiveParserWrapper(p);
        Metadata metadata = new Metadata();
        metadata.set(RESOURCE_NAME_KEY, "test_recursive_embedded.docx");
        ParseContext context = new ParseContext();
        RecursiveParserWrapperHandler handler = new RecursiveParserWrapperHandler(factory, -1);

        wrapper.parse(inputStream, handler, metadata, context);
        return handler.getMetadataList();

    }
}
