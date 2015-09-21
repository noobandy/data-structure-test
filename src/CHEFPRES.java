import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author anandm
 * @date Sep 21, 2015 10:20:34 AM
 */
public class CHEFPRES {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        // cities
        int N = scanner.nextInt();
        // products
        int K = scanner.nextInt();
        // capital city
        int B = scanner.nextInt();

        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node();
            nodes[i].N = i + 1;
        }

        for (int i = 0; i < N - 1; i++) {
            int j = scanner.nextInt();
            int k = scanner.nextInt();
            nodes[j - 1].next.add(nodes[k - 1]);
            nodes[k - 1].next.add(nodes[j - 1]);
        }

        for (int i = 0; i < N; i++) {
            nodes[i].P = scanner.nextInt();
        }

        int Q = scanner.nextInt();

        for (int i = 0; i < Q; i++) {
            int A = scanner.nextInt();
            int P = scanner.nextInt();
            int city = city(A, P, B, nodes);
            System.out.println(city);
        }
    }

    private int city(int a, int p, int b, Node[] nodes) {
        int city = -1;
        Node[] route = null;
        for (Node node : nodes) {
            if (node.P == p) {

                Node[] foundRoute = route(nodes[a - 1], node);
                if (foundRoute != null) {
                    if (route == null) {
                        route = foundRoute;
                        city = node.N;
                    }
                    else {
                        if (route.length < foundRoute.length) {
                            route = foundRoute;
                            city = node.N;
                        }
                    }

                }

                System.out.println(Arrays.toString(foundRoute));

            }
        }

        return city;
    }

    private Node[] route(Node source, Node destination) {
        Queue<Node> queue = new LinkedList<Node>();
        List<Node> route = new ArrayList<Node>();

        queue.add(source);

        while (!queue.isEmpty()) {
            Node node = queue.remove();

            route.add(node);

            if (node.N == destination.N) {
                Node[] temp = new Node[route.size()];
                return (Node[]) route.toArray(temp);
            }
            else {
                for (Node adjecent : node.next) {
                    if (!route.contains(adjecent)) {
                        queue.add(adjecent);
                    }
                }
            }
        }

        return null;
    }

    private class Node {

        int N;

        private List<Node> next = new ArrayList<CHEFPRES.Node>();

        int P;

        @Override
        public String toString() {
            return "Node [N=" + N + ", P=" + P + "]";
        }

    }

    public static void main(String[] args) {
        CHEFPRES main = new CHEFPRES();
        main.run();
    }
}
