public class CLineCodigo {
	private int iNumeroLinea;
	private String sInstruccion;
	private String sVar1, sVar2, sVar3;
	
	public CLineCodigo(int iNumeroLinea, String sInstruccion, String sVar1, String sVar2, String sVar3) {
		super();
		this.iNumeroLinea = iNumeroLinea;
		this.sInstruccion = sInstruccion;
		this.sVar1 = sVar1;
		this.sVar2 = sVar2;
		this.sVar3 = sVar3;
	}

	public String getsVar3() {
		return sVar3;
	}

	public void setsVar3(String sVar3) {
		this.sVar3 = sVar3;
	}

	public int getiNumeroLinea() {
		return iNumeroLinea;
	}

	public void setiNumeroLinea(int iNumeroLinea) {
		this.iNumeroLinea = iNumeroLinea;
	}

	public String getsInstruccion() {
		return sInstruccion;
	}

	public void setsInstruccion(String sInstruccion) {
		this.sInstruccion = sInstruccion;
	}

	public String getsVar1() {
		return sVar1;
	}

	public void setsVar1(String sVar1) {
		this.sVar1 = sVar1;
	}

	public String getsVar2() {
		return sVar2;
	}

	public void setsVar2(String sVar2) {
		this.sVar2 = sVar2;
	}
}
