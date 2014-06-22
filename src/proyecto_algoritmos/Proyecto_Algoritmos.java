/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_algoritmos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author dany
 */
public class Proyecto_Algoritmos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Test leer = new Test("//home//dany//Documents//Algoritmos//Proyecto//prueba2.txt");
    }

    private static final class Test {

        private ArrayList<String> nodos = new ArrayList<String>();
        private ArrayList<String> aristas = new ArrayList<String>();
        GGrafo grafo = new GGrafo();

        public Test(String ruta) {
            leer(ruta);
            correr();
        }

        public void leer(String nombre) {
            String linea = "";
            File archivo = null;
            FileReader fr = null;
            BufferedReader br = null;
            try {
                archivo = new File(nombre);
                fr = new FileReader(archivo);
                br = new BufferedReader(fr);
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                    nodos.add(linea.split(";")[0]);
                    aristas.add(linea.split(";")[1]);
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error:" + e.getMessage());
            }
        }
        
        public void correr(){
            
            for (int i = 0; i < nodos.size(); i++)
                grafo.agregarNodo(Integer.parseInt(nodos.get(i).split(",")[0]), nodos.get(i).split(",")[1]);
            
            String []tmp = null;
            for (int i = 0; i < aristas.size(); i++) {
                tmp = aristas.get(i).split(",");
                for (int j = 0; j < tmp.length; j++) {
                    grafo.agregarArista(Integer.parseInt(nodos.get(i).split(",")[0]), Integer.parseInt(tmp[j]));
                }
            }
            
            int opc = 0;
            
            grafo.setCover();
            grafo.clique();
            
            Scanner entrada = new Scanner( System.in);
            do{
                System.out.println("******************** MENU ***************************");
                System.out.println("1) Grupo mas grande\n2) Grupo mas pequeño\n3) Imprimir grafo\n4) Salir\n\nIngrese opcion:");
                opc = entrada.nextInt();
                switch( opc ){
                    case 1:
                        grafo.imprimirClique();
                        break;
                    case 2:
                        grafo.imprimirSetCover();
                        break;
                    case 3:
                        grafo.imprimirGrafo();
                        break;
                    case 4:
                        System.exit(0);
                        break;
                }
            }while( opc != 4 );
        }
    }
}
