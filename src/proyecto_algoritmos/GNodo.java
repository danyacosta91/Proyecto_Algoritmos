/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_algoritmos;

/**
 *
 * @author dany
 */
public class GNodo {
    private int _id;
    private String _nombre;
    private GNodo _siguiente;
    
    public GNodo(){
    }
    
    public GNodo( int id ){
        _id = id;
    }
    
    public GNodo( int id, String nombre ){
        _id = id;
        _nombre = nombre;
    }

    /**
     * @return the _id
     */
    public int getId() {
        return _id;
    }

    /**
     * @return the _nombre
     */
    public String getNombre() {
        return _nombre;
    }

    /**
     * @return the _siguiente
     */
    public GNodo getSiguiente() {
        return _siguiente;
    }
    
    public String imprimirNodo(){
        return "ID: " + _id + " Nombre: " + _nombre;
    }
    
    public String imprimirArista(){
        return "ID: " + _id;
    }

    /**
     * @param siguiente the _siguiente to set
     */
    public void setSiguiente(GNodo siguiente) {
        this._siguiente = siguiente;
    }
}
