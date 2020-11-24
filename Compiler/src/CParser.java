import java.util.ArrayList;

public class CParser {
	private Token TToken;
	private CScanner lex;
	private ArrayList<CVarDec> varDec = new ArrayList<CVarDec>();
	private ArrayList<CStmnt> statmnts = new ArrayList<CStmnt>();
	public static void main(String[] args) {
		new CParser();
	}
	
	public CParser (){
		String sNombreArchivo = "Archivos/Prueba3.java";
		lex = new CScanner(sNombreArchivo);
		lex.E0();
		TToken = lex.getToken();
		CProgram p = Program();
		new CSemantico(p);
		System.out.println("Compiló correctamente");
	}
	
	public CProgram Program(){
		ArrayList<CVarDec> vd = null;
		ArrayList<CStmnt> stmnt = null;
		CId id = null;
		varDec.clear();
		switch(TToken.getId()){
			case Tokens.CLASS:
				comer(Tokens.CLASS);
				id = new CId(TToken.getTexto());
				comer(Tokens.IDENTIFICADOR);
				vd=VarDec();
				comer(Tokens.LL_ABRE);
				stmnt= Statement(false);
				comer(Tokens.LL_CIERRA);
			break;
			default: error(TToken);
		}
		return new CProgram(id, vd, stmnt);
	}
	
	public ArrayList<CVarDec> VarDec(){
		CType type = null;
		CId id = null;
		try{
			type = Type();
			if(type.tipoDato.equals(" "))
				return varDec;
			id = new CId(TToken.getTexto());
			comer(Tokens.IDENTIFICADOR);
			comer(Tokens.FIN_COMAN);
			varDec.add(new CVarDec(type, id));
			VarDec();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return varDec;
	}
	public CType Type(){
		String sType = " ";
		switch(TToken.getId()){
			case Tokens.BOOLEAN:
				comer(Tokens.BOOLEAN);
				sType = "boolean";
				break;
			case Tokens.INT:
				comer(Tokens.INT);
				sType = "int";
				break;
			case Tokens.FLOAT:
				comer(Tokens.FLOAT);
				sType = "float";
				break;
			default:
				break;
		}
		return new CType(sType);
	}
	public ArrayList<CStmnt> Statement(boolean isCiclo){
		ArrayList<CStmnt> arrStmnt = null;
		CStatements Statements = null;
		CExp exp = null;
		CId id = null;
		try {
			switch(TToken.getId()){
			case Tokens.DO:
				isCiclo = true;
				comer(Tokens.DO);
				arrStmnt = Statement(true);
				Statements = new CStatements(arrStmnt);
				comer(Tokens.UNTIL);
				comer(Tokens.P_ABRE);
				exp = Expresion(); 
				comer(Tokens.P_CIERRA);
				this.statmnts.add(new CDoWhile(exp, Statements, isCiclo));
				Statement(false);
				break;
			case Tokens.SYSTEM:
				comer(Tokens.SYSTEM); 
				comer(Tokens.PUNTO);
				comer(Tokens.IN); 
				comer(Tokens.PUNTO); 
				comer(Tokens.READLN);
				comer(Tokens.P_ABRE); 
				exp = Expresion();
				comer(Tokens.P_CIERRA); 
				comer(Tokens.FIN_COMAN);
				this.statmnts.add(new CSysr(exp,isCiclo));
				Statement(false);
				break;
			case Tokens.IDENTIFICADOR:
				id = new CId(TToken.getTexto());
				comer(Tokens.IDENTIFICADOR);
				comer(Tokens.IGUALDAD);
				exp = Expresion();
				comer(Tokens.FIN_COMAN);
				this.statmnts.add(new CAsign(id,exp, isCiclo));
				Statement(false);
				break;
			default:
				break;
			}
		}catch(Exception e) {
			
		}
		
		return this.statmnts;
	}
	
	public CExp Expresion(){
		CId id1 = null;
		CId id2 = null;
		String sOperacion = "";
		CValor value = null;
		switch(TToken.getId()) {
			case Tokens.IDENTIFICADOR:
				id1 = new CId(TToken.getTexto());
				comer(Tokens.IDENTIFICADOR);
				switch(TToken.getId()) {
					case Tokens.MENOR_QUE:
						comer(Tokens.MENOR_QUE);
						sOperacion = "<";
						break;
					case Tokens.MAS:
						comer(Tokens.MAS);
						sOperacion = "+";
						break;
					case Tokens.MENOS:
						comer(Tokens.MENOS);
						sOperacion = "-";
						break;
					case Tokens.ASTERI:
						comer(Tokens.ASTERI);
						sOperacion = "*";
						break;
					default:
						return new CExpId(id1);
				}
				id2 = new CId(TToken.getTexto());
				comer(Tokens.IDENTIFICADOR);
				return new COperacion(id1, id2,sOperacion);
			case Tokens.TRUE:
				value = new CValor("true");
				comer(Tokens.TRUE);
				return value;
			case Tokens.FALSE:
				value = new CValor("false");
				comer(Tokens.FALSE);
				return value;
			case Tokens.ENTERO:
				value = new CValor(TToken.getTexto());
				comer(Tokens.ENTERO);
				return value;
			default: 
				error(TToken);
				break;
		}
		return null;
	}
	public void avanzar(){
		TToken = lex.getToken();
	}
	public void comer(int tok){
		try{
			if(TToken.getId() == tok){
				avanzar();
			}else{
				error(TToken, tok);
			}
		}catch(Exception e){
			System.out.println("ERROR, se esperaba: '"+lex.getNombre(tok)+"'");
			System.exit(1);
		}
	}
	
	public void error(Token tActual){
		System.out.println("Token inesperado: "+tActual.getTexto()+ " Se obtuvo: "+/*tEsperado.getTexto()+*/ " En la linea: "+tActual.linea);
		System.exit(1);
	}
	public void error(Token tActual, int tok){
		System.out.println(tok);
		System.out.println("Token inesperado: "+tActual.getTexto()+ " Se se esperaba: "+lex.getNombre(tok)+ " En la linea: "+tActual.linea);
		System.exit(1);
	}
	public void error(){
		System.out.println("ERROR");
	}
	
	public class Nodo{
		private Nodo sigNodo;
		public Nodo(){
			
		}
	}	
	
	public class CProgram{
		CId id;
		ArrayList<CVarDec> varDec = new ArrayList<CVarDec>();
		//ArrayList<CStmnt> stmnt = new ArrayList<CStmnt>();
		CStatements st;
		CProgram(CId i,ArrayList<CVarDec> varDec, ArrayList<CStmnt> stmnt){
			this.id = i;
			this.varDec = varDec;
			//this.stmnt = stmnt;
			st = new CStatements(stmnt); 
		}
		public CStatements getSt() {
			return st;
		}
		public void setSt(CStatements st) {
			this.st = st;
		}
		public CId getId() {
			return id;
		}
		public void setId(CId id) {
			this.id = id;
		}
		public ArrayList<CVarDec> getVarDec() {
			return varDec;
		}
		public void setVarDec(ArrayList<CVarDec> varDec) {
			this.varDec = varDec;
		}
		
	}

	public class CId{
		String id;
		
		CId(String id){
			this.id = id;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
		
	}
	
	public class CVarDec{
		CType type;
		CId id;
		
		CVarDec(CType type,CId id){
			this.type = type;
			this.id = id;
		}

		public CType getType() {
			return type;
		}

		public void setType(CType type) {
			this.type = type;
		}

		public CId getId() {
			return id;
		}

		public void setId(CId id) {
			this.id = id;
		}
		
	}
	
	public class CType{
		String tipoDato;
		
		CType(String tipoDato){
			this.tipoDato = tipoDato;
		}

		public String getTipoDato() {
			return tipoDato;
		}

		public void setTipoDato(String tipoDato) {
			this.tipoDato = tipoDato;
		}
		
	}

	
	public abstract class CExp {
		
	}
	
	public class COperacion extends CExp {
		CId id1, id2;
		String operacion;
		
		COperacion(CId i1, CId i2, String op){
			this.id1 = i1;
			this.id2 = i2;
			this.operacion = op;
		}

		public CId getId1() {
			return id1;
		}

		public void setId1(CId id1) {
			this.id1 = id1;
		}

		public CId getId2() {
			return id2;
		}

		public void setId2(CId id2) {
			this.id2 = id2;
		}

		public String getOperacion() {
			return operacion;
		}

		public void setOperacion(String operacion) {
			this.operacion = operacion;
		}
		
	}
	
	public class CValor extends CExp {
		String valor;
		
		CValor(String val){
			this.valor = val;
		}

		public String getValor() {
			return valor;
		}

		public void setValor(String valor) {
			this.valor = valor;
		}
		
	}
	
	public class CExpId extends CExp {
		CId id;
		
		CExpId(CId i){
			this.id = i;
		}

		public CId getId() {
			return id;
		}

		public void setId(CId id) {
			this.id = id;
		}
		
	}
	
	public abstract class CStmnt{
		boolean inicioCiclo;
		public boolean isInicioCiclo() {
			return inicioCiclo;
		}
	}
	
	public class CStatements extends CStmnt {
		ArrayList<CStmnt> statemnts = new ArrayList<CStmnt>();
		
		CStatements(ArrayList<CStmnt> statemnts){
			this.statemnts = statemnts;
		}

		public boolean isInicioCiclo() {
			return inicioCiclo;
		}

		public void setInicioCiclo(boolean inicioCiclo) {
			this.inicioCiclo = inicioCiclo;
		}

		public ArrayList<CStmnt> getStatemnts() {
			return statemnts;
		}

		public void setStatemnts(ArrayList<CStmnt> statemnts) {
			this.statemnts = statemnts;
		}
		
	}
	public class CDoWhile extends CStmnt{
		CExp exp;
		CStatements stmnt;
		
		public CDoWhile(CExp xp, CStatements stmt, boolean ini) {
			this.exp = xp;
			this.stmnt = stmt;
			this.inicioCiclo=ini;
		}

		public CExp getExp() {
			return exp;
		}

		public void setExp(CExp exp) {
			this.exp = exp;
		}

		public CStatements getStmnt() {
			return stmnt;
		}

		public void setStmnt(CStatements stmnt) {
			this.stmnt = stmnt;
		}
		
	}
	public class CSysr extends CStmnt{
		CExp exp;
		
		public CSysr(CExp c, boolean ini) {
			this.exp = c;
			this.inicioCiclo = ini;
		}

		public CExp getExp() {
			return exp;
		}

		public void setExp(CExp exp) {
			this.exp = exp;
		}
		
	}
	
	public class CAsign extends CStmnt{
		CId id;
		CExp exp;
		
		public CAsign(CId  i, CExp e, boolean ini) {
			this.id = i;
			this.exp = e;
			this.inicioCiclo = ini;
		}

		public CId getId() {
			return id;
		}

		public void setId(CId id) {
			this.id = id;
		}

		public CExp getExp() {
			return exp;
		}

		public void setExp(CExp exp) {
			this.exp = exp;
		}
		
	}
	
}


