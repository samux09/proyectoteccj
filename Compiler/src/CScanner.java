
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;



public class CScanner {
	private static String sourceCode;
	private int apuntador_actual, apuntador_busqueda,linea, apuntadorSint=0;
	private char caracter = ' ';
	boolean b;
	private String buffer  = "", token="";
	private ArrayList<Token> lista = new ArrayList<Token>();
	public String [] PalabrasReservadas = {"class","public","static","void","main","String","int","if","while","System","out","println",
			"true","false","extends","return","this","new","private","else","final","boolean"};
	private int iToken = 0;
	Map<String, String> map = new HashMap<String, String>();
	
	public static void main(String [] args) {
//		String sNombreArchivo = "Archivos/Prueba2.java";
//		//Lexer lex = new Lexer("public boolean estaEnArray(int numero){\nArrays.asList(permitidos).contains(numero);\n}"); //---------------------------------------------------------------------- sourceCode
//		Lexer lex = new Lexer(sNombreArchivo);
//		lex.E0();
//
//		System.out.println("Lista de Tokens");
////		System.out.print(lex);
//
////		lex.secuencia();
//		
//		while(lex.nextToken() != null){
//			System.out.println(lex.getToken().toString());
//		}
	}


	CScanner(String sNombreArchivo){
		leerArchivo leer = new leerArchivo();
		this.sourceCode = leer.leerArchivo(sNombreArchivo);
		this.apuntador_actual = 0;
		this.apuntador_busqueda = 0;
		this.linea = 1;
		this.lista = new java.util.ArrayList<>();
		this.buffer = "";
		
		
		map.put("0", "Entero");
		map.put("1", "IDENTIFICADOR");
		map.put("Operador", "IDENTIFICADOR");
		map.put("3", "(");
		map.put("4", ")");
		map.put("5", "{");
		map.put("6", "}");
		map.put("10", ";");
		map.put("12", "=");
		map.put("16", "class");
		map.put("17", "boolean");
		map.put("18", "int");
		map.put("19", "float");
		map.put("20", "do");
		map.put("22", "System");
		map.put("23", ".");
		map.put("24", "in");
		map.put("25", "<");
		map.put("26", "+");
		map.put("27", "-");
		map.put("28", "*");
		map.put("29", "true");
		map.put("30", "false");
	}

	public char getCharacter() {
		if(apuntador_busqueda >= sourceCode.length()) {
			return 0;
		}
		char caracter = sourceCode.charAt(this.apuntador_busqueda) ;
		this.apuntador_busqueda++;
		return caracter;
	}
	public String caracterDoble(){
		return ""+caracter+sourceCode.charAt(this.apuntador_busqueda);
	}
	public class Nodo{}
	public Nodo E0() {
		if(apuntador_busqueda >= sourceCode.length()) return null;

		caracter = this.getCharacter();
		if(caracter == '\n') return this.salto();
		if(caracter == ' ') {apuntador_actual = apuntador_busqueda; return this.E0();}
		if(Character.isDigit(caracter)) return this.E1();
		if(Character.isAlphabetic(caracter) || caracter == '_') return this.E3();
		if(caracter == '(') return this.eCaracter(Tokens.P_ABRE);
		if(caracter == ')') return this.eCaracter(Tokens.P_CIERRA);
		if(caracter == '{') return this.eCaracter(Tokens.LL_ABRE);
		if(caracter == '}') return this.eCaracter(Tokens.LL_CIERRA);
		if(caracter == '[') return this.eCaracter(Tokens.C_ABRE);
		if(caracter == ']') return this.eCaracter(Tokens.C_CIERRA);
		if(caracter == '<') return this.eCaracter(Tokens.MENOR_QUE);
		if(caracter == '+') return this.eCaracter(Tokens.MAS);
		if(caracter == '-') return this.eCaracter(Tokens.MENOS);
		if(caracter == '*') return this.eCaracter(Tokens.ASTERI);
		if(caracter == '=') return this.eCaracter(Tokens.IGUALDAD);
		if(caracter == '!') return this.eCaracter(Tokens.NEGACION);
		if(caracter == ';') return this.eCaracter(Tokens.FIN_COMAN);
		if(caracter == '.') return this.eCaracter(Tokens.PUNTO);
		if(caracter == ',') return this.eCaracter(Tokens.COMA);

		return Error();
	}
//	public String getToken(){
//		String result = lista.get(apuntadorSint).nombreLexema();
//		apuntadorSint++;
//		return result;
//
//	}
	public Nodo E1(){
		do{
			buffer += Character.toString(caracter);
			caracter = this.getCharacter();
		}while(Character.isDigit(caracter));
		return E2();
	}
	public Nodo E2(){
		apuntador_busqueda--;
		Token t = new Token(Tokens.ENTERO, buffer, apuntador_actual , apuntador_busqueda, linea);
		lista.add(t);
		this.apuntador_actual = this.apuntador_busqueda;
		buffer = "";
		return E0();
	}
	public Nodo E3(){
		do{
			buffer += Character.toString(caracter);
			caracter = this.getCharacter();
		}while(Character.isAlphabetic(caracter) || caracter == '_');
		if(Character.isDigit(caracter)) return this.E4();
		return E5();
	}
	public Nodo E4(){
		do{
			buffer += Character.toString(caracter);
			caracter = this.getCharacter();
		}while(Character.isDigit(caracter));
		if(Character.isAlphabetic(caracter) || caracter == '_') this.E3();
		return E5();
	}
	public Nodo E5(){
		int idToken = -1;
		if(buffer.equals("class")){
			idToken = Tokens.CLASS;
		}else if(buffer.equals("boolean")){
			idToken = Tokens.BOOLEAN;
		}else if(buffer.equals("int")){
			idToken = Tokens.INT;
		}else if(buffer.equals("true")){
			idToken = Tokens.TRUE;
		}else if(buffer.equals("false")){
			idToken = Tokens.FALSE;
		}else if(buffer.equals("float")){
			idToken = Tokens.FLOAT;
		}else if(buffer.equals("System")){
			idToken = Tokens.SYSTEM;
		}else if(buffer.equals(".")){
			idToken = Tokens.PUNTO;
		}else if(buffer.equals("in")){
			idToken = Tokens.IN;
		}else if(buffer.equals("readln")){
			idToken = Tokens.READLN;
		}else if(buffer.equals("do")){
			idToken = Tokens.DO;
		}else if(buffer.equals("until")){
			idToken = Tokens.UNTIL;
		}else{
			idToken = Tokens.IDENTIFICADOR;
		}
		Token t = new Token(idToken, buffer, apuntador_actual , apuntador_busqueda-1, linea);
		lista.add(t);
		apuntador_busqueda--;
		this.apuntador_actual = this.apuntador_busqueda;
		buffer = "";
		return E0();
	}
	public Nodo eCaracter(int tok){
		Token t = new Token(tok, ""+caracter, apuntador_actual , apuntador_busqueda, linea);
		lista.add(t);
		this.apuntador_actual = this.apuntador_busqueda;
		return E0();
	}
	public Nodo eDosCaracter(){
		Token t = new Token(Tokens.OP_LOGICO, ""+caracterDoble(), apuntador_actual , apuntador_busqueda+1, linea);
		lista.add(t);
		this.apuntador_busqueda++;
		this.apuntador_actual = this.apuntador_busqueda;
		return E0();
	}
	public Nodo Error(){
		System.out.println("Error en: "+(apuntador_busqueda-1)+ " Linea: "+linea+ " el caracter no forma parte de la gramatica: "+caracter);
		System.exit(0);
		return null;
	}
	public Nodo salto(){
		this.linea++;
		this.apuntador_actual = this.apuntador_busqueda;
		return E0();
	}
	@Override
	public String toString() {
		if(lista == null) {
			return "Sin Tokens";
		}
		String resultado = "";
		System.out.printf("%-20s %-15s %-10s %-10s %-10s \n","Nombre","Lexema","ColInicio","ColFinal","Linea");
		for(Object ite: lista) {
			Token t = (Token)ite;
			resultado += t.toString()+"\n";
		}
		return resultado;
	}
	
	public Token getToken(){
		if(iToken < lista.size()){
			return lista.get(iToken++);
		}else{
			return null;
		}
	}
	public Token nextToken(){
		if(iToken+1 <= lista.size()){
			return lista.get(iToken);
		}else{
			return null;
		}
	}
	
	public Token prevToken() {
		return lista.get(iToken-1);
	}
    
    public String getNombre(int i){
    	return map.get(""+i);
    }
}