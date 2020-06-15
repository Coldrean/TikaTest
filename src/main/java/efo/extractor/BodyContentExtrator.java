package efo.extractor;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ToXMLContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

public class BodyContentExtrator {
    AutoDetectParser autoParser = new AutoDetectParser();
    BodyContentHandler bodyContentHandler = new BodyContentHandler();
    Metadata metadata = new Metadata();

    public String extract(InputStream inputStream) throws TikaException, SAXException, IOException {
        autoParser.parse(inputStream, bodyContentHandler, metadata);
        return bodyContentHandler.toString();
    }
}
