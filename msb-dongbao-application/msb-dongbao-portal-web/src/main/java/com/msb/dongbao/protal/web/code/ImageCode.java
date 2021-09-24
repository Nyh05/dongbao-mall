package com.msb.dongbao.protal.web.code;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Random;

@Data
public class ImageCode {
    private String code;
    private ByteArrayInputStream image;
    private int width =400;
    private int height =100;
    public static ImageCode getInstance(){
        return new ImageCode();
    }
    private ImageCode(){
        //图形缓冲区,给一个黑板
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //需要一个画笔
        Graphics graphics = image.getGraphics();
        //用笔画图形，涂色
        graphics.setColor(new Color(255,255,37));
        //画出一个矩形
        graphics.fillRect(0,0,width,height);
        //设置字体
        graphics.setFont(new Font("宋体",Font.PLAIN,30));
        //设置随机数
        Random random=new Random();
        code="";
        for (int i = 0; i < 6; i++) {
            String s = String.valueOf(random.nextInt(10));
            this.code+=s;
            //设置字体的颜色
            graphics.setColor(new Color(36,210,37));
            //将字体画在画布上
            graphics.drawString(s,(width/6)*i,50);

            //划线
            graphics.setColor(new Color(62,78,192));
            graphics.drawLine((width/6)*i,50,(width/6)*i+20,50-30);
        }

        //画很多线
        graphics.setColor(new Color(245,11,37));
        for (int i = 0; i < 100; i++) {
            int x=random.nextInt(width);
            int y=random.nextInt(height);
            int x1=random.nextInt(25);
            int y1=random.nextInt(35);
            graphics.drawLine(x,y,x+x1,y+y1);
        }
        //收笔
        graphics.dispose();

        ByteArrayInputStream inputStream=null;
        ByteOutputStream outputStream=  new ByteOutputStream();

        try {
            //创建一个图片的输出,赋值给ByteArrayInputStream
            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
            ImageIO.write(image,"jpeg",imageOutputStream);
            inputStream =new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            System.out.println("生成图片验证码失败");
        }
        this.image=inputStream;
    }
}
