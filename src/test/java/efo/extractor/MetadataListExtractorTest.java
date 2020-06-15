package efo.extractor;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

public class MetadataListExtractorTest {
    String inputDir="./test-files";
    String outputDir="./result";

    @Test
    public void testExtractEmlWithOneDocAttachment() throws IOException, TikaException, SAXException {
        MetadataListExtractor metadataListExtractor=new  MetadataListExtractor();
        String filename="eml-with-doc-attachment-test.eml";

        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        List<Metadata> metadataList=metadataListExtractor.extract(stream);
        System.out.println("");
    }
}
