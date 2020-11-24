import java.util.ArrayList;
import java.util.Arrays;

public class Token {
    public int tipo,colInicio,colFinal,linea;
    public String lexema;
    public String [] PalabrasReservadas = {"public","static","void","main","String","if","while",
    		                               "extends","return","this","new","private","else","final"};
    public String toString(){
        return String.format("%-20s %-15s %-10d %-10d %-10d", this.nombreLexema(),this.lexema,this.colInicio,this.colFinal,linea);
        //return this.nombreLexema()+" "+this.lexema+" "+this.colInicio+" "+this.colFinal+" "+linea;
    	//return this.lexema;
        
    }


    public String nombreLexema() {
        switch(this.tipo){
            case Tokens.ENTERO:
                return " Entero ";
            case Tokens.BLANCO:
                return " Blanco ";
            case Tokens.FIN_COMAN:
                return " Fin_Coman ";
            case Tokens.LL_ABRE:
                return " Ll_abre ";
            case Tokens.LL_CIERRA:
                return " Ll_cierra ";
            case Tokens.C_ABRE:
                return " C_abre ";
            case Tokens.C_CIERRA:
                return " C_cierra ";
            case Tokens.MENSAJE:
                return " Mensaje ";
            case Tokens.NEGACION:
                return " Negacion ";
            case Tokens.OP_LOGICO:
                return " Op_logico ";
            case Tokens.OPERACION:
                return " Operacion ";
            case Tokens.P_ABRE:
                return " P_abre ";
            case Tokens.P_CIERRA:
                return " P_cierra ";
            case Tokens.IDENTIFICADOR:
            	if(Arrays.asList(PalabrasReservadas).contains(lexema)) return " Palabra reservada ";
                return " Identificador ";
            case Tokens.IGUALDAD:
                return " Igualdad ";
            case Tokens.COMA:
                return " Coma ";
            case Tokens.CLASS:
            case Tokens.BOOLEAN:
            case Tokens.INT:
            case Tokens.FLOAT:
            case Tokens.DO:
            case Tokens.UNTIL:
            case Tokens.SYSTEM:
            case Tokens.PUNTO:
            case Tokens.IN:
            case Tokens.READLN:
            	return " Palabra reservada ";
            case Tokens.MENOR_QUE:
            	return " Menor Que";
            case Tokens.MAS:
            	return " MAS ";
            case Tokens.MENOS:
            	return " MENOS ";
            case Tokens.ASTERI:
            	return " ASTERISCO ";
            default:
                return " Otro ";
        }

    }
    public String getTipo(){
    	return nombreLexema();
    }
    public int getId(){
    	return this.tipo;
    }
    public String getTexto(){
    	return this.lexema;
    }
    Token(int tipo, String lexema, int colInicio, int colFinal, int linea) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.colInicio = colInicio;
        this.colFinal = colFinal;
        this.linea = linea;
    }
}
