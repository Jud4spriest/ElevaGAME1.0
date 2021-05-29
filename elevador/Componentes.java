package elevador;

import java.awt.Graphics;

public interface Componentes {
	public int getX();
	public int getY();
	public int getAndar();
	public void setIcon(String src);
	public void setNome_img(String nome_img);
	public void draw(Graphics g);
	
}
