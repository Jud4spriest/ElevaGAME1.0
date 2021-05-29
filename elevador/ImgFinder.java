package elevador;

import static elevador.Const.DIR;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public abstract class ImgFinder {
	
	private static final String path = DIR.get();
	private static BufferedImage image;
	private static File img_src;
	
	
	public static BufferedImage getImage(String nome_img) {
		String dir = path+nome_img;
		
		try {
			img_src = new File(dir);
			image = ImageIO.read(img_src);
			return image;
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		
		return image;
	}

}
