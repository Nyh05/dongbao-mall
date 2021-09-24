package com.msb.verifyCode;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class TesseractTest {
    public static void main(String[] args) throws TesseractException {
        ITesseract iTesseract=new Tesseract();
        // 语言包 加进来
        iTesseract.setDatapath("D:\\tessData");
        iTesseract.setLanguage("chi_sim");
//		iTesseract.setLanguage("eng");

        File fileDir = new File("D:\\Data");
        for (File file : fileDir.listFiles()) {
            String s = iTesseract.doOCR(file);
            System.out.println(file.getName()+"识别后的数字是："+s);
        }
    }
}
