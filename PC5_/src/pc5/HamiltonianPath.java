/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pc5;

import java.util.*;

public class HamiltonianPath {
    private final int[][] graph;
    private final int numVertices;
    private final boolean[] visited;
    private final List<List<Integer>> allPaths;

    public HamiltonianPath(int[][] graph) {
        this.graph = graph;
        this.numVertices = graph.length;
        this.visited = new boolean[numVertices];
        this.allPaths = new ArrayList<>();
    }

    public List<List<Integer>> findHamiltonianPaths() {
        allPaths.clear();
        Arrays.fill(visited, false);

        for (int v = 0; v < numVertices; v++) {
            visited[v] = true;
            List<Integer> path = new ArrayList<>();
//Se crea una nueva lista path que se utilizará para almacenar el camino hamiltoniano en construcción.
            path.add(v);

            hamiltonianPathUtil(v, 1, path);

            visited[v] = false;
        }

        return allPaths;
    }

    private void hamiltonianPathUtil(int vertex, int count, List<Integer> path) {
        if (count == numVertices) {
            allPaths.add(new ArrayList<>(path));
            return;
        }

        for (int v = 0; v < numVertices; v++) {
            if (isSafe(vertex, v)) {
                visited[v] = true;
                path.add(v);
                hamiltonianPathUtil(v, count + 1, path);
                visited[v] = false;
                path.remove(path.size() - 1);
//Se elimina el último vértice agregado al camino para retroceder y explorar otras opciones
            }
        }
    }

    private boolean isSafe(int currentVertex, int nextVertex) {
        if (!visited[nextVertex] && graph[currentVertex][nextVertex] == 1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 0, 1, 1, 1},
            {0, 0, 1, 1, 0},
            {1, 1, 0, 1, 1},
            {1, 1, 1, 0, 1},
            {1, 0, 1, 1, 0}
        };

        HamiltonianPath hamiltonianPath = new HamiltonianPath(graph);
        List<List<Integer>> paths = hamiltonianPath.findHamiltonianPaths();

        if (!paths.isEmpty()) {
            System.out.println("Caminos Hamiltonianos:");
            for (List<Integer> path : paths) {
                System.out.println(path);
            }
        } else {
            System.out.println("Ningun camino Hamiltoniano encontrado");
        }
    }
}
