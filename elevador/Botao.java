package elevador;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Botao extends JButton implements Componentes{

	private int andar;
	private int x;
	private int y;
	private boolean isAtivo;
	private static String[] nome_img = {"Botao.png","Botaopress3.png"};
	Image icon;
	
	
 Botao(int andar, int x, int y) {
		
	 
//	 this.setAlignmentX(x);
//	 this.setIcon(icon);
//	 this.setIgnoreRepaint(false);
//	 this.setLayout(new BoxLayout(this,	BoxLayout.Y_AXIS));
//	 this.setVisible(true);
//	 this.setContentAreaFilled(false);
//	 System.out.println(this.isShowing());
//		System.out.println(this.getLayout());
		this.andar = andar;
		this.x = x;
		this.y = y;
		isAtivo = false;
		this.icon = this.getResources();
		this.getAcionamento();
//		setBounds(new Rectangle(x, y, 50, 80));
//		addActionListener();
		this.setToolTipText(andar+"º Andar");
//		setActionCommand("Go");
	}

	
	
	public void getAcionamento() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Elevador.ListaRequest.add(getAndar());
				System.out.println("Chamando 1.. 2.. 3!");
				isAtivo = true;
				getResources();
			}
			
		});
		
	}
	
	public Image getResources() {
		if(!isAtivo) icon = ImgFinder.getImage(nome_img[0]);
		else icon = ImgFinder.getImage(nome_img[1]);
		if(Elevador.ListaRequest.contains(andar)) icon = ImgFinder.getImage(nome_img[1]);
		return icon;
	}
	
	public void draw(Graphics g) {
		g.drawImage(icon, x, y, null);
	}
	
	
	
	
/*			
 * 			# Getters & Setters #
 */
	
	
	@Override
	public int getAndar() {
		return andar;
	}
	public boolean isAtivo() {
		return isAtivo;
	}
	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}
	public String[] getNome_img() {
		return nome_img;
	}
	@Override
	public int getX() {
		return x;
	}
	@Override
	public int getY() {
		return y;
	}
	@Override
	public void setIcon(String src) {
		icon = getResources();
		
	}
	@Override
	public void setNome_img(String nome_img) {
		// TODO Auto-generated method stub
		
	}

	

	
}
