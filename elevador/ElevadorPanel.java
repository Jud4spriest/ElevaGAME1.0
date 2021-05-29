package elevador;

import static elevador.Elevador.DESCER;
import static elevador.Elevador.SUBIR;
import static elevador.Elevador.DEFAULT_POSITION;
import static elevador.Const.YOFFSET;
import static elevador.Const.XOFFSET;
import static elevador.Const.X;
import static elevador.Const.Y;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;


public class ElevadorPanel extends JPanel implements Runnable {

	private static int Ciclo_Atual;
	private Thread exe;
	private List<Elevadores> andares = new LinkedList<>();
	private List<Botao> botoes = new LinkedList<>();
	private int[] p = DEFAULT_POSITION;
	private Elevador e;
	
	
	public ElevadorPanel() {		//SETUP PANEL
		setBackground(Color.ORANGE);
        setFocusable(true);
        Ciclo_Atual = 0;
		e = new Elevador();
				
		for (int i = 0; i <= 10; i++) {
            andares.add(new Elevadores(i,p[0],p[1] - (i*YOFFSET.getVal())));
            botoes.add(new Botao(i, p[0] - XOFFSET.getVal(), p[1] - (i*YOFFSET.getVal())));
            this.add(botoes.get(i));  //COM PROBLEMAS AINDA NO CONTAINER BOTAO
        }
		
		startApp(); //Start Thread do app
	}

	@Override
    public void run() {
        while (true) {       	
   
        	if(isCicloCompleto()) updateInterface();
        	repaint();
        	Ciclo_Atual++;
//        	System.out.println(e);
        	
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
	
    
    private void startApp() {
        if (exe == null) {
        	exe = new Thread(this);
        	exe.start();
        }
    }
    
	
	
	public Elevador getElevador() {
		return e;
	}
	public void setElevador(int e) {
		this.e = new Elevador(e);
	}
	public static int getCiclo_Atual() {
		return Ciclo_Atual;
	}
	
	//Check ciclo completo
	private boolean isCicloCompleto() {
		if(Ciclo_Atual>=e.getQtdCiclos()) {
			Ciclo_Atual = 0;
			return true;
		}
		else return false;	
	}
	
//	AtualizaStatus
	public synchronized void updateStatus() {

		if(e.getListaRequest().isEmpty()) return;	
		e.isArrived();
		if(e.getActionPortas()) {
			botoes.get(e.getAndar()).setAtivo(false);
			botoes.get(e.getAndar()).getResources();
			return;		
		}
		if((Integer)e.getListaRequest().get(0) > e.getAndar()) e.setStatus(SUBIR);
		else if((Integer)e.getListaRequest().get(0) < e.getAndar()) e.setStatus(DESCER);
		e.setAtivo(true);
	}
		
//	Atualiza Interface
	public synchronized void updateInterface() {
		updateStatus();
		e.move();
		this.repaint();
		
	}	

    // Print componentes na janela
    protected synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Botao b : botoes) {
            b.draw(g);
        }
        
        for (Elevadores a : andares) {
            a.draw(g);
        }
        
        e.draw(g);
        g.drawRect(X.getVal()-30, Y.getVal()-530, 90, 580);
        g.drawString(e.toString(), 5, getHeight() - 10);		// Print elevador status
    }


}
