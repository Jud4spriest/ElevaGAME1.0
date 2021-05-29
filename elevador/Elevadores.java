package elevador;

import static elevador.Const.YOFFSET;
import static elevador.Const.XOFFSET;
import java.awt.Graphics;
import java.awt.Image;

public class Elevadores implements Componentes {
	
	private int andar, x, y;
	private Image icon;
	private static String nome_img = "Elevador_Andar.png";
	
	public Elevadores(int andar, int x, int y){
		this.andar = andar;
		this.x = x;
		this.y = y;
		icon = ImgFinder.getImage(nome_img);
	}
	
	
    public void draw(Graphics g) {
        g.drawImage(icon, x, y, null);
    }

    @Override
	public int getAndar() {
		return andar;
	}
    @Override
	public int getX() {
		return x;
	}
    @Override
	public int getY() {
		return y;
	}
	public String getNome_img() {
		return nome_img;
	}
	@Override
	public void setNome_img(String nome_img) {
		this.nome_img = nome_img;
	}
	@Override
	public void setIcon(String src) {
		icon = ImgFinder.getImage(src);
		
	}
		
}

