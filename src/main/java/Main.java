import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Integer>[] adj;
//Aquí se guarda el árbol.Cada posición guarda los vecinos de un nodo.
 // ejemplo 1 conectado con 2 y 3, adj[1] = [2,3] 
    static class Pair {//esta es la calse auxiliar y sirve para guardar el nodo y la distancia
        int node;// nodo actual
        int dist;// distancia desde el inicio

        Pair(int node, int dist) {//constructor, crea un objeto
            this.node = node;
            this.dist = dist;
        }
        //ejemplo: new pair(5,3) osea nodo 5 a distancia 3
    }

    static Pair bfs(int start, int n) {//esta funcion recorre el arbol nivel por nivel usando una cola.
       //esta recibe el nodo inicial y la cantidad de nodos y devuelve el nodo mas lejano encontrado y su distancia 

        boolean[] visited = new boolean[n + 1];//este es para no repetir nodos

        Queue<Pair> q = new LinkedList<>();//creo la cola, la cual guarda los nodos pendientes por recorrer
//agregar inicio
        q.add(new Pair(start, 0));//mete el nodo inicial con distancia 0
//marcar visitado
        visited[start] = true;// marca el nodo anterior para no volverlo a recorrer
//variable
        Pair last = new Pair(start, 0);// aqui guardo el ultimo nodo recorrido, terminara siendo el mas lejano

        while (!q.isEmpty()) {// mientras la cola tenga elementos
//El recorrido sigue mientras haya nodos pendientes.
            Pair cur = q.poll();//saca el primer elemento de la cola
//actualizar last
            last = cur;// guarda el nodo actual, al final sera el mas lejano
// este sirve para recorrer vecinos
            for (int next : adj[cur.node]) {

                if (!visited[next]) {//si no fue visitado, este sirve para evitar repetir nodos.

                    visited[next] = true;// lo marca como recorrido

                    q.add(new Pair(next, cur.dist + 1));//agregar a la cola, guarda el vecino y su distancia que aumenta en 1
                }
            }
        }

        return last;//retornar el mas lejano
        // devuelve el ultimo nodo recorrido, este es el mas lejano
    }

    @SuppressWarnings("unchecked")// esto dice a java:No me muestres advertencias de tipo unchecked aquí porque sé lo que estoy haciendo.
    public static void main(String[] args) throws Exception {//empieza la ejecucion

        FastScanner fs = new FastScanner(System.in);//lector rapido
//lee datos rapido, porque el limite es grande
        int n = fs.nextInt();//leer N
        //cantidad de nodos

        adj = new ArrayList[n + 1];//crear listas
//crea espacio para guardar vecinos
        for (int i = 1; i <= n; i++) {//inicializar    
            adj[i] = new ArrayList<>();
        }//Cada nodo tiene su lista

        for (int i = 0; i < n - 1; i++) {//leer conexiones
// recordar que tiene, N-1 conexiones
//leer la arista
            int a = fs.nextInt();
            int b = fs.nextInt();
//lee dos nodos conectados
            adj[a].add(b);// como el arbol no tiene direccion se hace a conecta con b y b conecta con a
            adj[b].add(a);
            
        }
//Primar recorrido
        Pair first = bfs(1, n);// empieza desde 1 y encuentra un nodo lejano.
//Segundo recorrido
        Pair second = bfs(first.node, n);//empieza desde ese nodo lejano
//ahora encuentra la distancia maxima real
        System.out.println(second.dist);//muestra el diametro
    }

    static class FastScanner {// hasta 200000 nodos

        private final InputStream in;//InputStream es el flujo de entrada.
//lee muchos caracteres de una sola vez
        private final byte[] buffer = new byte[1 << 16];//Aquí se crea una memoria temporal llamada buffer

        private int ptr = 0, len = 0;//Variables de control.

        FastScanner(InputStream is) {//constructor,Guarda el flujo de entrada.
            in = is;
        }

        private int read() throws IOException {//Lee UN carácter.

            if (ptr >= len) {//¿ya leí todo el buffer?

                len = in.read(buffer);//Carga más datos desde entrada.

                ptr = 0;

                if (len <= 0) return -1;
            }

            return buffer[ptr++];
        }

        int nextInt() throws IOException {

            int c;

            while ((c = read()) <= ' ') {
                if (c == -1) return -1;
            }

            int sign = 1;

            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }

            return val * sign;
        }
    }
}