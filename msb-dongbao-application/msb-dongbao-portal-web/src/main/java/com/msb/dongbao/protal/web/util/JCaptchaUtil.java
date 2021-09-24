package com.msb.dongbao.protal.web.util;

import com.octo.captcha.CaptchaFactory;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.word.FileDictionary;
import com.octo.captcha.component.word.wordgenerator.ComposeDictionaryWordGenerator;
import com.octo.captcha.engine.GenericCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;

import java.awt.*;

public class JCaptchaUtil {
    //最好使用单列模式
    //饿汉模式，简单，不怕占用存储空间
    private static final ImageCaptchaService IMAGE_CAPTCHA_SERVICE=imageCaptchaService();

    public static ImageCaptchaService getImageCaptchaService(){
        return JCaptchaUtil.IMAGE_CAPTCHA_SERVICE;
    }


    //使用工具类jcaptcha随机生成验证码
    private static ImageCaptchaService imageCaptchaService(){
        //生成背景
        UniColorBackgroundGenerator backGround = new UniColorBackgroundGenerator(250,150);
        //生成字体
        RandomRangeColorGenerator randomColor = new RandomRangeColorGenerator(new int[]{0,100},new int[]{100,200},new int[]{50,255});
        RandomTextPaster randomTextPaster = new RandomTextPaster(4,6,randomColor);
        RandomFontGenerator randomFontGenerator = new RandomFontGenerator(35, 45,new Font[]{new Font("TimesRoman",Font.PLAIN,30)});
        //组装图象
        ComposedWordToImage composedWordToImage = new ComposedWordToImage(randomFontGenerator, backGround, randomTextPaster);

        ComposeDictionaryWordGenerator cdwg = new ComposeDictionaryWordGenerator(new FileDictionary("toddlist"));
        GimpyFactory gimpyFactory = new GimpyFactory(cdwg, composedWordToImage);
        GenericCaptchaEngine gce = new GenericCaptchaEngine(new CaptchaFactory[]{gimpyFactory});
        //GC是自己的GC并非JVM的
        return new GenericManageableCaptchaService(gce,20,2000,2000);

    }
}
