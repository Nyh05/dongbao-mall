package com.msb.dongbao.protal.web.util;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSeedValueMDP;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author
 */
@Slf4j
public class SliderUtil {

	public static VerificationVO  cut(){
		// 读取图库目录
		File imgCatalog = new File("D:\\imgs");
		ArrayList<File> files = CollectionUtil.newArrayList(imgCatalog.listFiles());
		if (CollectionUtil.isNotEmpty(files)) {
			// 随机选择需要切的图
			int randNum = new Random().nextInt(files.size());
			File targetFile = files.get(randNum);

			// 随机选择剪切模版
			File tempImgFile = new File("E:\\pic\\templates\\" + (new Random().nextInt(6) + 1) + "-w.png");
			try {
				VerificationVO verificationVO = VerifyImageUtils.pictureTemplatesCut(tempImgFile, targetFile);
				System.out.println("宽："+verificationVO.getXWidth());
//				redisUtils.set(AuthConstant.PREFIX_AUTH_VC_CODE_CACHE + account, verificationVO.getXWidth(), 1800);

//				// 移除横坐标送前端
//				verificationVO.setXWidth(null);

				return verificationVO;
			}catch (Exception e) {
				System.out.println("切图失败");
			}
		}

		return null;

	}

	public static void main(String[] args) {
		File imgs=new File("D:\\imgs");
		ArrayList<File> files = CollectionUtil.newArrayList(imgs.listFiles());
		Map<String,Integer> map=new HashMap<>();
		for (File file : files) {
			System.out.println(file.getName());
			try {
				int height = ImageIO.read(file).getHeight();
				//System.out.println(height);
				map.put(file.getName()+"height:",height);
				int width = ImageIO.read(file).getWidth();
				map.put(file.getName()+"width:",width);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Set<String> strings = map.keySet();
		for (String string : strings) {
			System.out.println(string+map.get(string));
		}

		//log.info("图像1的大小为{} x {},随机生成的坐标 X,Y 为（{}，{}）",map.get("1.jpgwidth:"),map.get("1.jpgheight:"),20,30);
	}


}