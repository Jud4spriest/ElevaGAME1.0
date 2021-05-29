package elevador;

import static elevador.Const.YOFFSET;
import static elevador.Const.X;
import static elevador.Const.Y;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Elevador implements Componentes {
	
	private int x, y;
	private int QtdCiclos;
	private int k;
	private boolean isAtivo;
	private Image icon;	
	
	private static int status;		// 0 - PARADO :: 1 - DESCENDO :: 2 - SUBINDO
	private static int Andar_Atual;
	public static ArrayList<Integer> ListaRequest;
	
	
	public final static int CHEGOU = 3;
	public final static int ABRE_PORTAS = 4;
	public final static int FECHA_PORTAS = 5;
	public final static int PARADO = Const.PARADO.getVal();
	public final static int SUBIR = Const.SUBIR.getVal();
	public final static int DESCER = Const.DESCER.getVal();
	private final static int TERREO = 0;
	private final static int CICLODEFAULT = 4;
	private final String[] statustext = {"Parado", "Subindo", "Descendo","Chegou","Abre Portas","Fecha Portas"};

	public static final int[] DEFAULT_POSITION = {X.getVal(),Y.getVal()};
	private static String[] nome_img = {"elevador_dourado.png","Elevador_chegou.png"};
	
//	private static final int OFFSET = 50;
	
	public Elevador() {
		k = -1;
		ListaRequest = new ArrayList<Integer>();
		Andar_Atual = TERREO;
		x = DEFAULT_POSITION[0];
		y = DEFAULT_POSITION[1];
		QtdCiclos = CICLODEFAULT;
		status = 0;
		isAtivo = false;
		icon = ImgFinder.getImage(nome_img[0]);
	}

	
	public Elevador(int andar) {
		k = -1;
		ListaRequest = new ArrayList<Integer>();
		Andar_Atual = andar;
		x = DEFAULT_POSITION[0];
		y = DEFAULT_POSITION[1] - andar*YOFFSET.getVal();
		QtdCiclos = CICLODEFAULT;
		status = 0;
		isAtivo = false;
		icon = ImgFinder.getImage(nome_img[0]);
	}


	public void move() {
		if(status == PARADO) return;
		isAtivo = true;
		if(status == SUBIR) {
			Andar_Atual++;
			y = y - YOFFSET.getVal();
		}
		else if(status == DESCER) {
			Andar_Atual--;
			y = y + YOFFSET.getVal();
		}
		
	}
	
	public void isArrived() {
		if(!isAtivo) return;
		if(Andar_Atual == ListaRequest.get(0)) {
			ListaRequest.remove(0);
			status = PARADO;
			setactionPortas(0);
			setIcon(nome_img[1]);
			isAtivo = false;
		}
	}
	
	public boolean getActionPortas() {
		switch(k) {
		case 0:
			status = CHEGOU;
			k++;
			return true;
		case 1:
			k++;
			status = ABRE_PORTAS;
			return true;
		case 2:
			k++;
			status = FECHA_PORTAS;
			return true;
		default:
			setIcon(nome_img[0]);
			return false;
		}
	}
	
	public void setactionPortas(int k) {
		this.k = k;	
	}
	
    public void draw(Graphics g) {
        g.drawImage(icon, x, y, null);
    }
    
    
	@Override
	public void setNome_img(String nome_img) {
		// TODO Auto-generated method stub
		
	}
    @Override
	public int getAndar() {
		return Andar_Atual;
	}
	@Override
	public void setIcon(String src) {
		this.icon = ImgFinder.getImage(src);
	}
	public Image getIcon() {
		return icon;
	}
	public int getQtdCiclos() {
		return QtdCiclos;
	}
	public void setQtdCiclos(int qtdCiclos) {
		QtdCiclos = qtdCiclos;
	}
	public boolean isAtivo() {
		return isAtivo;
	}
	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
	public void setAndarAtual(int a) {
		Andar_Atual = a;
		this.x = y = y - YOFFSET.getVal();
	}
	@Override
	public int getX() {
		return x;
	}
	@Override
	public int getY() {
		return y;
	}	
	public String[] getNome_img() {
		return nome_img;
	}
	public ArrayList getListaRequest() {
		return ListaRequest;
	}	
	
	@Override
	public String toString() {
		if(Andar_Atual==TERREO) return "Elevador :: Térreo  | Status: "+statustext[status]+" | Ciclo: "+ElevadorPanel.getCiclo_Atual()+" | Requisições: "+ListaRequest.toString();
		else return "Elevador :: "+Andar_Atual+"º Andar | Status: "+statustext[status]+" | Ciclo: "+ElevadorPanel.getCiclo_Atual()+" | Requisições: "+ListaRequest.toString();
	}
	
	public void addDestino(int d) {
		if(d<=0) d=0;
		ListaRequest.add(d);
		
	}


	



}

