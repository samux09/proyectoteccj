import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import


public class CSemantico {
	private Map<String, String> tablaSimbolos = new HashMap<String, String>();
	private ArrayList<CLineCodigo> codigoIntermedio = new ArrayList<CLineCodigo>();
	public CSemantico (CProgram p){
		checkDeclarations(p.getVarDec());
		checkEstatements(p.getSt().getStatemnts());
		generarCodigoIntermedio(p.getSt().getStatemnts());
	}	

	public void checkDeclarations(ArrayList<CVarDec> varDec){
		for (int i = 0; i < varDec.size(); i++) {
			if(tablaSimbolos .get(varDec.get(i).id.getId()) != null){
				System.out.println("Variable duplicada (" +varDec.get(i).id.getId()+ "). Ya se declaró anteriormente.");
				System.exit(1);
			}
			tablaSimbolos.put(varDec.get(i).id.getId(), varDec.get(i).getType().getTipoDato());
		}
		verDeclaraciones();
	}

	public void checkEstatements(ArrayList<CStmnt> arrStats){
		CExp Exp;
		CId ID;
		for (int i = 0; i < arrStats.size(); i++) {
			if(arrStats.get(i).getClass().getSimpleName().equals("CAsign")){
				//Identificador
				ID = ((CAsign)arrStats.get(i)).getId();
				checkVarDeclarada(ID);

				//Expresión
				Exp = ((CAsign)arrStats.get(i)).getExp();

				if(Exp.getClass().getSimpleName().equals("CValor")){
					checkValorCompatible(ID, ((CValor)Exp));
				}
				if(Exp.getClass().getSimpleName().equals("COperacion")){
					checkVarDeclarada(((COperacion)Exp).getId1());
					checkVarDeclarada(((COperacion)Exp).getId2());
					checkOperacionCompatible(((COperacion)Exp).getOperacion(), ((COperacion)Exp).getId1(), ((COperacion)Exp).getId2());
					checkResultadoCompatible(ID,((COperacion)Exp).getOperacion(), ((COperacion)Exp).getId1(), ((COperacion)Exp).getId2());
				}
				if(Exp.getClass().getSimpleName().equals("CExpId")){
					checkVarDeclarada(((CExpId)Exp).getId());
					checkAsignacionCompatible(ID, ((CExpId)Exp).getId());
				}
			}
			if(arrStats.get(i).getClass().getSimpleName().equals("CSysr")){
				Exp = ((CSysr)arrStats.get(i)).getExp();
				if(Exp.getClass().getSimpleName().equals("COperacion")){
					checkVarDeclarada(((COperacion)Exp).getId1());
					checkVarDeclarada(((COperacion)Exp).getId2());
					checkOperacionCompatible(((COperacion)Exp).getOperacion(), ((COperacion)Exp).getId1(), ((COperacion)Exp).getId2());
				}
				if(Exp.getClass().getSimpleName().equals("CExpId")){
					checkVarDeclarada(((CExpId)Exp).getId());
				}
			}
			if(arrStats.get(i).getClass().getSimpleName().equals("CDoWhile")){
				Exp = ((CDoWhile)arrStats.get(i)).getExp();
				if(Exp.getClass().getSimpleName().equals("COperacion")){
					checkVarDeclarada(((COperacion)Exp).getId1());
					checkVarDeclarada(((COperacion)Exp).getId2());
					checkOperacionCompatible(((COperacion)Exp).getOperacion(), ((COperacion)Exp).getId1(), ((COperacion)Exp).getId2());
				}
				if(Exp.getClass().getSimpleName().equals("CExpId")){
					checkVarDeclarada(((CExpId)Exp).getId());
					String tipoVar = tablaSimbolos.get(((CExpId)Exp).getId().getId());
					if(!tipoVar.equals("boolean")) {
						System.out.println("Se esperaba expresión booleana en 'until'");
						System.exit(1);
					}
				}
			}
		}
	}

	public void checkVarDeclarada(CId identificador){
		if(tablaSimbolos.get(identificador.getId()) == null){
			System.out.println("Variable no declarada (" + identificador.getId()+")");
			System.exit(1);
		}
	}
	public void checkOperacionCompatible(String operador,CId ident1, CId ident2){
		String sTipoDato1 = tablaSimbolos.get(ident1.getId());
		String sTipoDato2 = tablaSimbolos.get(ident2.getId());
		if(!(sTipoDato1.equals("int") && sTipoDato2.equals("int") ) &&
				!(sTipoDato1.equals("int") && sTipoDato2.equals("float")) &&
				!(sTipoDato1.equals("float") && sTipoDato2.equals("int")) &&
				(sTipoDato1.equals("boolean") || sTipoDato2.equals("boolean"))){
			System.out.println("Operación no válida, variables incompatibles.("+ident1.getId()+" "+operador +" "+ident2.getId()+")");
			System.exit(1);
		}
	}
	public void checkAsignacionCompatible(CId ident1, CId ident2){
		String sTipoDato1 = tablaSimbolos.get(ident1.getId());
		String sTipoDato2 = tablaSimbolos.get(ident2.getId());
		if(!(sTipoDato1.equals(sTipoDato2))){
			System.out.println("Asignacion no válida, variable incompatible.("+ident2.getId()+")");
			System.exit(1);
		}
	}
	public void checkValorCompatible(CId ident1, CValor val){
		String sTipoDato1 = tablaSimbolos.get(ident1.getId());
		if(sTipoDato1.equals("int")){
			if(!val.getValor().matches("-?\\d+")){
				System.out.println("Valor: ("+val.getValor()+") no válido para tipo int.");
				System.exit(1);
			}
		}
		if(sTipoDato1.equals("boolean")){
			if(!val.getValor().equals("true") && !val.getValor().equals("false")){
				System.out.println("Valor: ("+val.getValor()+") no válido para tipo boolean.");
				System.exit(1);
			}
		}
	}
	public void checkResultadoCompatible (CId identPrin,String operador,CId ident1, CId ident2){
		String sTipoDatoPrinc = tablaSimbolos.get(identPrin.getId());
		String sTipoDato1 = tablaSimbolos.get(ident1.getId());
		String sTipoDato2 = tablaSimbolos.get(ident2.getId());

		if(sTipoDatoPrinc.equals("int") || sTipoDatoPrinc.equals("float")){
			if(!(sTipoDato1.equals("int") && sTipoDato2.equals("int")) && !(sTipoDato1.equals("int") && sTipoDato2.equals("float")) && !(sTipoDato2.equals("int") && sTipoDato1.equals("float"))){
				System.out.println("Asignación no válida, operación no compatible.("+ident1.getId()+" "+operador +" "+ident2.getId()+") con identificador: "+identPrin.getId());
				System.exit(1);
			}
		}
		if(sTipoDatoPrinc.equals("boolean")){
			if(!operador.equals("<")){
				System.out.println("Asignación no válida, operación no compatible.("+ident1.getId()+" "+operador +" "+ident2.getId()+") con identificador: "+identPrin.getId());
				System.exit(1);
			}
		}

	}

	public void verDeclaraciones(){
		System.out.printf("%-15s %-15s %n", "Tipo", "Identificador");
		tablaSimbolos.forEach((k,v) -> 
		System.out.printf("%-15s %-15s %n", v, k));
	}

	public void generarCodigoIntermedio(ArrayList<CStmnt> arrStats){
		CExp Exp;
		CId ID;
		String valor;
		COperacion op;
		String varTemp = "t";
		CExpId expId;
		int iContadorTemp = 1, iContadorLineas = 1, iLineaInicioCiclo=0;
		for (int i = 0; i < arrStats.size(); i++) {
			if(arrStats.get(i).getClass().getSimpleName().equals("CAsign")){
				//Identificador
				ID = ((CAsign)arrStats.get(i)).getId();
				//Expresión
				Exp = ((CAsign)arrStats.get(i)).getExp();
				if(arrStats.get(i).inicioCiclo) {
					iLineaInicioCiclo = iContadorLineas;
				}
				if(Exp.getClass().getSimpleName().equals("CValor")){
					valor = ((CValor)Exp).getValor();
					codigoIntermedio.add(new CLineCodigo(iContadorLineas, "=", valor, "",ID.getId()));
					iContadorLineas ++;
				}
				if(Exp.getClass().getSimpleName().equals("COperacion")){
					op = ((COperacion)Exp);
					codigoIntermedio.add(new CLineCodigo(iContadorLineas, op.getOperacion(), op.getId1().getId(), op.getId2().getId(),varTemp+iContadorTemp));
					iContadorLineas++;
					codigoIntermedio.add(new CLineCodigo(iContadorLineas, "=", varTemp+iContadorTemp, "",ID.getId()));
					iContadorLineas++;
					iContadorTemp++;
				}
				if(Exp.getClass().getSimpleName().equals("CExpId")){
					expId =((CExpId) Exp);
					codigoIntermedio.add(new CLineCodigo(iContadorLineas,"=", expId.getId().getId(), "",ID.getId()));
					iContadorLineas++;
				}
			}
			if(arrStats.get(i).getClass().getSimpleName().equals("CSysr")){
				if(arrStats.get(i).inicioCiclo) {
					iLineaInicioCiclo = iContadorLineas;
				}
				Exp = ((CSysr)arrStats.get(i)).getExp();
				if(Exp.getClass().getSimpleName().equals("CExpId")){
					expId = ((CExpId) Exp);
					codigoIntermedio.add(new CLineCodigo(iContadorLineas,"read", "", "",expId.getId().getId()));
					iContadorLineas++;
				}
			}
			if(arrStats.get(i).getClass().getSimpleName().equals("CDoWhile")){
				Exp = ((CDoWhile)arrStats.get(i)).getExp();
				if(Exp.getClass().getSimpleName().equals("COperacion")){
					op = ((COperacion)Exp);
					codigoIntermedio.add(new CLineCodigo(iContadorLineas,"-", op.getId1().getId(), op.getId2().getId(),varTemp+iContadorTemp));
					iContadorLineas++;
					if(op.getOperacion().equals("<")) {
						codigoIntermedio.add(new CLineCodigo(iContadorLineas,"JBTZ", varTemp+iContadorTemp, "","("+iLineaInicioCiclo+")"));
						iContadorLineas++;					
					}
					iContadorTemp++;
				}
				if(Exp.getClass().getSimpleName().equals("CExpId")){
					expId = ((CExpId)Exp);
					codigoIntermedio.add(new CLineCodigo(iContadorLineas,"-", expId.getId().getId(), "1",varTemp+iContadorTemp));
					iContadorLineas++;
					codigoIntermedio.add(new CLineCodigo(iContadorLineas,"JNZ", varTemp+iContadorTemp, "","("+iLineaInicioCiclo+")"));
					iContadorLineas++;	
					iContadorTemp++;
				}
			}
		}
		codigoIntermedio.add(new CLineCodigo(iContadorLineas,"", "", "",""));
		escribirTexto(codigoIntermedio);
	}
	public void escribirTexto(ArrayList<CLineCodigo> arr) {
		// jdk 7
		String line = "";
		try (FileWriter writer = new FileWriter("Archivos/salida.txt");
		 BufferedWriter bw = new BufferedWriter(writer)) {
			
			for (int i = 0; i < arr.size(); i++) {
				line = String.format("|%-5d|%-5s|%-5s|%-5s|%-5s|\n", arr.get(i).getiNumeroLinea(), arr.get(i).getsInstruccion(), arr.get(i).getsVar1(), arr.get(i).getsVar2(), arr.get(i).getsVar3());
				bw.write(line);
			}

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		
	}
}
