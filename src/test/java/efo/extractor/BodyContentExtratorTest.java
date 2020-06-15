package efo.extractor;

import org.apache.tika.exception.TikaException;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BodyContentExtratorTest {
    String inputDir="./test-files";
    String outputDir="./result";

    @Test
    public void testParseEmlWithOneDocAttachment() throws IOException, TikaException, SAXException {
        BodyContentExtrator bodyContentExtrator=new BodyContentExtrator();
        String filename="eml-with-doc-attachment-test.eml";

        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        String result=bodyContentExtrator.extract(stream);
        System.out.println(result);
        Files.write(Paths.get(outputDir,filename.split("\\.")[0]+".xhtml"),result.getBytes());
    }

    @Test
    public void testParseExcel() throws IOException, TikaException, SAXException {
        BodyContentExtrator bodyContentExtrator=new BodyContentExtrator();
        String filename="excel-test.xlsx";

        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        String result=bodyContentExtrator.extract(stream);
        System.out.println(result);
        Files.write(Paths.get(outputDir,filename.split("\\.")[0]+".xhtml"),result.getBytes());
    }

    @Test
    public void testParsePdfWithTable() throws IOException, TikaException, SAXException {
        BodyContentExtrator bodyContentExtrator=new BodyContentExtrator();
        String filename="pdf-text-with-table.pdf";

        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        String result=bodyContentExtrator.extract(stream);
        System.out.println(result);
        Files.write(Paths.get(outputDir,filename.split("\\.")[0]+".xhtml"),result.getBytes());
    }

    @Test
    public void testParsePdfWithImage() throws IOException, TikaException, SAXException {
        BodyContentExtrator bodyContentExtrator=new BodyContentExtrator();
        String filename="pdf-text-with-image.pdf";

        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        String result=bodyContentExtrator.extract(stream);
        System.out.println(result);
        Files.write(Paths.get(outputDir,filename.split("\\.")[0]+".xhtml"),result.getBytes());
    }

    @Test
    public void testParsePptWithImage() throws IOException, TikaException, SAXException {
        BodyContentExtrator bodyContentExtrator=new BodyContentExtrator();
        String filename="ppt-test-with-image.ppt";

        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        String result=bodyContentExtrator.extract(stream);
        System.out.println(result);
        Files.write(Paths.get(outputDir,filename.split("\\.")[0]+".xhtml"),result.getBytes());
    }

    @Test
    public void testParseWordWithImage() throws IOException, TikaException, SAXException {
        BodyContentExtrator bodyContentExtrator=new BodyContentExtrator();
        String filename="word-with-image-test.doc";

        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        String result=bodyContentExtrator.extract(stream);
        System.out.println(result);
        Files.write(Paths.get(outputDir,filename.split("\\.")[0]+".xhtml"),result.getBytes());
    }
}
