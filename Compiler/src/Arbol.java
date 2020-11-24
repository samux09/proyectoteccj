public class Arbol {

    /* Atributos */
    private NodoArbol raiz;

    /* Contructories */    

    public <T> Arbol( NodoArbol<T> raiz ) {
        this.raiz = raiz;
    }
    public Arbol() {
    }

    /* Setters y Getters */
    public NodoArbol getRaiz() {
        return raiz;
    }

    public <T> void setRaiz(NodoArbol<T> raiz) {
        this.raiz = raiz;
    }

}