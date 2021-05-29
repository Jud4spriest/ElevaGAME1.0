package elevador;

public enum Const {
	XOFFSET(275), YOFFSET(50), PARADO(0), SUBIR(1), DESCER(2),
	X(400), Y(580), //Configuração disposição dos objetos na janela
	DIR("C:/Users/mazev/eclipse/FinalProjectObjetos/src/resources/"); //Setup resources dir.
	
	private final int valor;
	private final String s;
	Const(int valor) {
		this.valor = valor;
		this.s = null;
	}
	
	Const(String s) {
		this.valor = 0;
		this.s = s;
	}

	public int getVal(){
			return valor;
	}
	public String get(){
		return s;
	}

}