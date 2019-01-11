package com.company;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
	static final Logger log = Logger.getLogger(com.company.Main.Imagereader.class.getName());

	interface image{
		void Read();
	}   //C://Games//test_1024x768.bmp   C://Games//File.log

	public static void main(String[] args) {
		com.company.Main.image image = new com.company.Main.ProxyImagereader("C://Games//File.log");
		image.Read();


	}


	public static class Imagereader implements com.company.Main.image {
		String file1;

		public Imagereader(String file) {
			file1 = file;
		}

		@Override
		public void Read() {

			File File = new File(file1);

			String NameFile = File.getName();
			Pattern p = Pattern.compile(".+_(\\d+x\\d+).bmp");
			Matcher n = p.matcher(NameFile);
			if (n.matches() == true) {
				System.out.print("Разрешение="+n.group(1));
			} else {
				Get(File);
			}
		}


		protected void Get(File File) {
			try {
				BufferedImage bufImage;
				//log.addHandler(new FileHandler(file1));
				bufImage = ImageIO.read(File);
				int w, h;
				h = bufImage.getHeight();
				w = bufImage.getWidth();
				System.out.print("Разрешение=" + w + "x" + h);
			}catch(Exception e) { log.info("Ошибка при загрузке изображения");}
		}

	}

	public static class ProxyImagereader  implements com.company.Main.image {
		String file;
		com.company.Main.Imagereader image;

		public ProxyImagereader(String s) {
			file = s;
		}

		public void Read() {

			if(image == null){
				image = new com.company.Main.Imagereader(file);
			}
			image.Read();
		}

	}
}
