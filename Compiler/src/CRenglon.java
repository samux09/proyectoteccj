public class CRenglon {
	private String tipo;
	private String identificador;
	
	public CRenglon(String t, String i){
		this.tipo = t;
		this.identificador = i;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
}
