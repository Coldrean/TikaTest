package efo.extractor;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public class MsWordExtractorTest {
    String inputDir="./test-files";
    String outputDir="./result";

    @Test
    public void extractorImagesFromWordTest() throws IOException {
        String filename= "word-with-image-test.doc";
        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        MsWordExtractor msWordExtractor=new MsWordExtractor();
        msWordExtractor.extractorImagesFromWord(stream);
    }
}
