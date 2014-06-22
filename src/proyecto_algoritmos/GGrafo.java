/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_algoritmos;

import java.util.ArrayList;

/**
 *
 * @author dany
 */
public class GGrafo {

    ArrayList<GLista> _lista = new ArrayList<GLista>();
    ArrayList<Integer> _visitados = new ArrayList<Integer>();
    ArrayList<Integer> _todos = new ArrayList<Integer>();
    ArrayList<GNodo> _ptos = new ArrayList<GNodo>();
    ArrayList<GClique> _clique = new ArrayList<GClique>();

    private class GClique {

        ArrayList<GNodo> _cliques = new ArrayList<GNodo>();

        public GClique() {
        }

        void agregarNodo(GNodo nodo) {
            _cliques.add(nodo);
        }

        int getSize() {
            return _cliques.size();
        }

        String imprimir() {
            String ret = "";

            for (int i = 0; i < _cliques.size(); i++) {
                ret += _cliques.get(i).imprimirNodo() + "\n";
            }

            return ret;
        }
    }

    private void bfp(GNodo nodo) {
        GLista padre = null;
        _visitados.add(nodo.getId());
        while (nodo.getSiguiente() != null) {
            nodo = nodo.getSiguiente();
            padre = obtenerLista(nodo.getId());
            if (nodo != null && estaGuardado(nodo.getId()) == false && buscarAdyacencia(padre)) {
                bfp(padre.getCabeza());
            }
        }
        padre = obtenerLista(nodo.getId());
        if (estaGuardado(nodo.getId()) == false && buscarAdyacencia(padre)) {
            bfp(padre.getCabeza());
        }
    }

    private GLista obtenerLista(int id) {
        for (int i = 0; i < _lista.size(); i++) {
            if (_lista.get(i).getID() == id) {
                return _lista.get(i);
            }
        }
        return null;
    }

    private Boolean estaGuardado(int id) {
        for (int i = 0; i < _visitados.size(); i++) {
            if (_visitados.get(i) == id) {
                return true;
            }
        }
        return false;
    }

    private Boolean fueIngresado(int id) {
        for (int i = 0; i < _todos.size(); i++) {
            if (_todos.get(i) == id) {
                return true;
            }
        }
        return false;
    }

    private Boolean buscarAdyacencia(GLista lista) {
        int valor = 0;
        for (int i = 0; i < _visitados.size(); i++) {
            valor = _visitados.get(i);
            if (lista.buscar(valor) == false) {
                return false;
            }
        }
        return true;
    }

    void visitar(int id) {
        for (int i = 0; i < _lista.size(); i++) {
            if (_lista.get(i).getID() == id) {
                _lista.get(i).visitar();
                return;
            }
        }
    }

    GNodo getNodoGrafo(int id) {
        for (int i = 0; i < _lista.size(); i++) {
            if (_lista.get(i).getID() == id) {
                return _lista.get(i).getCabeza();
            }
        }
        return null;
    }

    void agregarNodo(int id, String nombre) {
        _lista.add(new GLista(id, nombre));
    }

    void agregarArista(int id, int id_1) {
        for (int i = 0; i < _lista.size(); i++) {
            if (_lista.get(i).getID() == id) {
                _lista.get(i).agregar(id_1);
                return;
            }
        }
    }

    void setCover() {
        int cont = 0;
        GNodo tmp = null;
        int[] arr;
        for (int i = 0; i < _lista.size(); i++) {
            cont = 0;
            arr = new int[_lista.get(i).getSize()];
            tmp = _lista.get(i).getCabeza();
            while (tmp.getSiguiente() != null) {
                if (fueIngresado(tmp.getId()) == false) {
                    arr[cont] = tmp.getId();
                    cont++;
                }
                tmp = tmp.getSiguiente();
            }
            if (fueIngresado(tmp.getId()) == false) {
                arr[cont] = tmp.getId();
                cont++;
            }

            if ((cont / _lista.get(i).getSize()) * 100 >= 50 || cont >= 2) {
                for (int j = 0; j < cont; j++) {
                    if (fueIngresado(arr[j]) == false) {
                        _todos.add(arr[j]);
                    }
                }
                _ptos.add(_lista.get(i).getCabeza());
            }
        }
    }

    void clique() {
        GNodo tmp = null;
        for (int i = 0; i < _lista.size(); i++) {
            for (int j = 0; j < _visitados.size(); j++) {
                _visitados.remove(j);
            }
            tmp = _lista.get(i).getCabeza();
            if (estaGuardado(tmp.getId()) == false) {
                bfp(tmp);
                if (_visitados.size() > 0) {
                    _clique.add(new GClique());
                    for (int j = 0; j < _visitados.size(); j++) {
                        _clique.get(_clique.size() - 1).agregarNodo(getNodoGrafo(_visitados.get(j)));
                    }
                }
            }
        }
    }

    void imprimirSetCover() {
        System.out.println("******************** GRUPO MAS PEQUEÑO *************************");
        for (int i = 0; i < _ptos.size(); i++) {
            System.out.println(_ptos.get(i).imprimirNodo());
        }
        System.out.println();
    }

    void imprimirGrafo() {
        System.out.println("******************** GRAFO *************************************");
        for (int i = 0; i < _lista.size(); i++) {
            _lista.get(i).imprimirLista();
        }
        System.out.println();
    }

    void imprimirClique() {
        System.out.println("******************** GRUPOS MAS GRANDES **************************");
        int mayor = 0;
        GClique tmp = null;
        for (int i = 0; i < _clique.size(); i++) {
            System.out.println(_clique.get(i).imprimir());
            if (mayor < _clique.get(i).getSize()) {
                mayor = _clique.get(i).getSize();
                tmp = _clique.get(i);
            }
        }
        System.out.println("******************** GRUPO MAS GRANDE **************************");
        System.out.println(tmp.imprimir());
    }
}
