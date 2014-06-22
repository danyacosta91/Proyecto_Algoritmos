/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_algoritmos;

/**
 *
 * @author dany
 */
public class GLista {
    GNodo _cabeza;
    GNodo _ultimo;
    int _tam;
    Boolean _visitado;
    
    public GLista(){}
    
    public GLista( int id, String nombre ){
        _cabeza = new GNodo(id, nombre);
        _ultimo = _cabeza;
        _tam = 1;
        _visitado = false;
    }
    
    void agregar( int id ){
        _ultimo.setSiguiente( new GNodo(id) );
        _ultimo = _ultimo.getSiguiente();
        _tam++;
    }
    
    int getSize(){
        return _tam;
    }
    
    int getID(){
        return _cabeza.getId();
    }
    
    Boolean visitado(){
        return _visitado;
    }
    
    void visitar(){
        _visitado = true;
    }
    
    Boolean buscar( int id ){
        GNodo tmp = _cabeza;
        while( tmp.getSiguiente() != null ){
            if( tmp.getId() == id )
                return true;
            tmp = tmp.getSiguiente();
        }
        if( tmp.getId() == id )
            return true;
        return false;
    }
    
    GNodo getCabeza(){
        return _cabeza;
    }
    
    void imprimirLista(){
        GNodo tmp = _cabeza;
        System.out.println( tmp.imprimirNodo() );
        if( tmp.getSiguiente() != null ){
            tmp = tmp.getSiguiente();
            while( tmp.getSiguiente() != null ){
                System.out.println( tmp.imprimirArista() );
                tmp = tmp.getSiguiente();
            }
            System.out.println( tmp.imprimirArista() );
        }
    }
}
