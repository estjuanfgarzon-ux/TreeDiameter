El programa hace esto:



Empieza desde cualquier nodo

Busca el nodo más lejano

Desde ese nodo lejano vuelve a buscar el más lejano

Esa distancia final es el diámetro

**Explicación código**

**Lista de adyacencia**

static ArrayList<Integer>\[] adj;



Aquí guardamos las conexiones.

EJEMPLO



Si tenemos:



1 - 2

1 - 3



Entonces:



adj\[1] = \[2,3]

adj\[2] = \[1]

adj\[3] = \[1]



Es decir:



desde 1 puedo ir a 2 y 3



**Clase Pair**

static class Pair {



Esta clase guarda DOS cosas juntas.



Variables

int node;

int dist;

node = nodo actual

dist = distancia recorrida



Constructor

Pair(int node, int dist) {

&#x20;   this.node = node;

&#x20;   this.dist = dist;

}



Cuando hacemos:



new Pair(5, 3)



significa:



estoy en el nodo 5

he recorrido distancia 3



**Función bfs**

static Pair bfs(int start, int n)



Esta función:



empieza desde un nodo

recorre el árbol

encuentra el nodo más lejano



**visited**

boolean\[] visited = new boolean\[n + 1];

Sirve para recordar:



qué nodos ya visitamos

Así evitamos repetir.



**Cola**

Queue<Pair> q = new LinkedList<>();

La cola funciona como una fila.

El primero que entra:

es el primero que sale.



**Nodo inicial**

q.add(new Pair(start, 0));



Metemos:

el nodo inicial

con distancia 0

Porque todavía no hemos caminado.



**Marcar visitado**

visited\[start] = true;



Decimos:

"ya pasé por aquí"



**Variable last**

Pair last = new Pair(start, 0);

Aquí guardaremos:

el nodo más lejano encontrado hasta ahora



**While principal**

while (!q.isEmpty())

Mientras la cola tenga nodos:

seguimos recorriendo.

**Sacar nodo**

Pair cur = q.poll();

Sacamos el primero de la fila.



**Actualizar last**

last = cur;



Guardamos el nodo actual.

Como BFS recorre por niveles:

el último nodo suele ser el más lejano.

**Recorrer vecinos**

for (int next : adj\[cur.node])

Miramos todos los vecinos del nodo actual.

**Si no ha visitado**

if (!visited\[next])



Solo vamos si no hemos pasado antes.

**Marcar**

visited\[next] = true;

Lo marcamos como visitado.

**Meter vecino**

q.add(new Pair(next, cur.dist + 1));



Entramos al vecino.

La distancia aumenta en 1 porque usamos un camino más.

**EJEMPLO PEQUEÑO**



Supongamos:

1 - 2 - 3

Comenzamos en 1.

Cola

\[(1,0)]

Sacamos 1.

}Metemos 2:



\[(2,1)]



Sacamos 2.

Metemos 3:



\[(3,2)]



Sacamos 3.



Fin.

El más lejano fue:



nodo 3

distancia 2

**Retorno**

return last;



Devolvemos:

el nodo más lejano

su distancia

**Leer n**

int n = fs.nextInt();



Cantidad de nodos.

**Crear listas**

adj = new ArrayList\[n + 1];



Creamos espacio para todas las listas.

**Inicializar listas**

for (int i = 1; i <= n; i++) {

&#x20;   adj\[i] = new ArrayList<>();

}



Cada nodo necesita su propia lista.

**Leer conexiones**

for (int i = 0; i < n - 1; i++)



Un árbol tiene:

n−1



conexiones

**Leer arista**

int a = fs.nextInt();

int b = fs.nextInt();



Leemos:



a conectado con b

**Guardar conexión**

adj\[a].add(b);

adj\[b].add(a);



Como el camino sirve en ambos sentidos.

**Primer recorrido**

Pair first = bfs(1, n);



Desde el nodo 1 buscamos el más lejano.

**Segundo recorrido**

Pair second = bfs(first.node, n);



Desde ese nodo lejano buscamos otra vez el más lejano.



La distancia encontrada:



es el diámetro.

**Imprimir respuesta**

System.out.println(second.dist);



Mostramos el diámetro.

