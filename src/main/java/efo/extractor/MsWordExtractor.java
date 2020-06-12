package efo.extractor;

import java.io.*;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.*;
import org.apache.poi.hwpf.model.*;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;

public class MsWordExtractor {

    public void extractorImagesFromWord(InputStream inputStream) throws IOException {

        HWPFDocument doc = new HWPFDocument(inputStream);


        Range range = doc.getRange();

        long start2 = System.currentTimeMillis();

        int numChar = range.numCharacterRuns();

        PicturesTable pTable =doc.getPicturesTable();

        int count=1;
        for(int j = 0; j < numChar; ++j){
            CharacterRun cRun = range.getCharacterRun(j);

            //看看有没有图片
            boolean has = pTable.hasPicture(cRun);
            System.out.println("hasPicture " + has);
            if(has){
                Picture zhou =  pTable.extractPicture(cRun, true);
                //目录你就自己设了，像保存什么的格式都可以
                zhou.writeImageContent(new FileOutputStream("./image" + count +"."+zhou.suggestFileExtension()));
                count+=1;
                System.out.println("extract Picture successfully! ");
            }
        }

        //时间花费，还是很快的
        long end2 = System.currentTimeMillis();
        System.out.println("time spend " + (end2 - start2));

    }
}
