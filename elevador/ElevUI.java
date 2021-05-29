package elevador;

import javax.swing.JFrame;

public class ElevUI extends JFrame {

	private static ElevadorPanel cpanel;
	
	public ElevUI(String name) {
		super();
		this.setTitle(name);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(500, 50, 600, 500);
		this.setSize(600,720);
		this.setResizable(false);
        cpanel = new ElevadorPanel();
        this.setContentPane(cpanel);
        this.setVisible(true);
	}

	public ElevadorPanel getPanel() {
		return cpanel;
	}
	
}
