package efo.extractor;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.ToXMLContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

public class ToXMLContentExtrator {

    AutoDetectParser autoParser = new AutoDetectParser();
    ToXMLContentHandler toXMLContentHandler = new ToXMLContentHandler();
    Metadata metadata = new Metadata();

    public String extract(InputStream inputStream) throws TikaException, SAXException, IOException {
        autoParser.parse(inputStream, toXMLContentHandler, metadata);
        return toXMLContentHandler.toString();
    }
}
