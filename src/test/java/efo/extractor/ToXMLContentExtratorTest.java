package efo.extractor;

import org.apache.tika.exception.TikaException;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ToXMLContentExtratorTest {
    String inputDir="./test-files";
    String outputDir="./result";


    @Test
    public void testExtractEmlWithOneDocAttachment() throws IOException, TikaException, SAXException {
        ToXMLContentExtrator toXMLContentExtrator=new ToXMLContentExtrator();
        String filename="eml-with-doc-attachment-test.eml";

        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        String result=toXMLContentExtrator.extract(stream);
        System.out.println(result);
        Files.write(Paths.get(outputDir,filename.split("\\.")[0]+".xhtml"),result.getBytes());

    }

    @Test
    public void testExtractEmlWithTwoAttachment() throws IOException, TikaException, SAXException {
        ToXMLContentExtrator toXMLContentExtrator=new ToXMLContentExtrator();
        String filename="eml-with-two-attachments.eml";

        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        String result=toXMLContentExtrator.extract(stream);
        System.out.println(result);
        Files.write(Paths.get(outputDir,filename.split("\\.")[0]+".xhtml"),result.getBytes());

    }

    @Test
    public void testExtractExcel() throws IOException, TikaException, SAXException {
        ToXMLContentExtrator toXMLContentExtrator=new ToXMLContentExtrator();
        String filename="excel-test.xlsx";

        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        String result=toXMLContentExtrator.extract(stream);
        System.out.println(result);
        Files.write(Paths.get(outputDir,filename.split("\\.")[0]+".xhtml"),result.getBytes());

    }

    @Test
    public void testExtractPdfWithTable() throws IOException, TikaException, SAXException {
        ToXMLContentExtrator toXMLContentExtrator=new ToXMLContentExtrator();
        String filename="pdf-text-with-table.pdf";

        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        String result=toXMLContentExtrator.extract(stream);
        System.out.println(result);
        Files.write(Paths.get(outputDir,filename.split("\\.")[0]+".xhtml"),result.getBytes());

    }

    @Test
    public void testExtractPdfWithImage() throws IOException, TikaException, SAXException {
        ToXMLContentExtrator toXMLContentExtrator=new ToXMLContentExtrator();
        String filename="pdf-text-with-image.pdf";

        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        String result=toXMLContentExtrator.extract(stream);
        System.out.println(result);
        Files.write(Paths.get(outputDir,filename.split("\\.")[0]+".xhtml"),result.getBytes());

    }

    @Test
    public void testExtractPptWithImage() throws IOException, TikaException, SAXException {
        ToXMLContentExtrator toXMLContentExtrator=new ToXMLContentExtrator();
        String filename="ppt-test-with-image.ppt";

        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        String result=toXMLContentExtrator.extract(stream);
        System.out.println(result);
        Files.write(Paths.get(outputDir,filename.split("\\.")[0]+".xhtml"),result.getBytes());

    }

    @Test
    public void testExtractWordWithImage() throws IOException, TikaException, SAXException {
        ToXMLContentExtrator toXMLContentExtrator=new ToXMLContentExtrator();
        String filename="word-with-image-test.doc";

        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        String result=toXMLContentExtrator.extract(stream);
        System.out.println(result);
        Files.write(Paths.get(outputDir,filename.split("\\.")[0]+".xhtml"),result.getBytes());

    }

    @Test
    public void testExtractZipInZip() throws IOException, TikaException, SAXException {
        ToXMLContentExtrator toXMLContentExtrator=new ToXMLContentExtrator();
        String filename="zip-in-zip.zip";

        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        String result=toXMLContentExtrator.extract(stream);
        System.out.println(result);
        Files.write(Paths.get(outputDir,filename.split("\\.")[0]+".xhtml"),result.getBytes());

    }


    @Test
    public void testExtractZipInRar() throws IOException, TikaException, SAXException {
        ToXMLContentExtrator toXMLContentExtrator=new ToXMLContentExtrator();
        String filename="zip-in-rar.rar";

        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        String result=toXMLContentExtrator.extract(stream);
        System.out.println(result);
        Files.write(Paths.get(outputDir,filename.split("\\.")[0]+".xhtml"),result.getBytes());

    }
}
