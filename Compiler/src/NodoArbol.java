public class NodoArbol <T>{

    /* Declaraciones de variables */
    private NodoArbol<T> yo;

    private NodoArbol<T> padre;
    private NodoArbol<T> hojaIzquierda;
    private NodoArbol<T> hojaDerecha;

    /* Constructor */
    public NodoArbol(NodoArbol<T> valor) {
        this.yo = valor;
    }

    /* Setters y Getters */
    public void setValor(NodoArbol<T> valor) {
        this.yo = valor;
    }

    public NodoArbol<T> getValor() {
        return yo;
    }

    public NodoArbol getPadre() {
        return padre;
    }

    public void setPadre(NodoArbol padre) {
        this.padre = padre;
    }

    public NodoArbol getHojaIzquierda() {
        return hojaIzquierda;
    }

    public void setHojaIzquierda(NodoArbol hojaIzquierda) {
        this.hojaIzquierda = hojaIzquierda;
    }

    public NodoArbol getHojaDerecha() {
        return hojaDerecha;
    }

    public void setHojaDerecha(NodoArbol hojaDerecha) {
        this.hojaDerecha = hojaDerecha;
    }

}