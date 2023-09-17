package printTreeNodes;

import java.util.*;

public class PrintTreeNodes {

    public static class Node {
        int id;
        String value;
        int parentId;

        public Node(int id, String value, int pId) {
            this.id = id;
            this.value = value;
            this.parentId = pId;
        }
    }

    public static void main(String[] args) {
        // Example input
        Node[] input = new Node[] {
                new Node(10, "var", 5),
                new Node(5, "/", 0), // pid 0 doesn't exist --> Root node
                new Node(23, "usr", 5),
                new Node(18, "logs", 10),
                new Node(13, "bin", 5),
                new Node(2, "root", 23)
        };

        /* expected output
         - /
         -- var
         --- logs
         -- usr
         --- root
         -- bin
        */

        System.out.println(printTree(input));
    }

    public static String printTree(Node[] nodes) {
        Map<Integer, List<Node>> parentToChildMap = new HashMap<>();
        for (Node node : nodes) {
            List<Node> children = parentToChildMap.computeIfAbsent(node.parentId, x -> new ArrayList<>());
            children.add(node);
            parentToChildMap.put(node.parentId, children);
        }

        StringBuilder res = new StringBuilder();
        printTreeR(parentToChildMap, parentToChildMap.get(0), 1, res);
        return res.toString();
    }

    private static void printTreeR(Map<Integer, List<Node>> parentToChildMap,
                                            List<Node> children,
                                            int depth, StringBuilder res) {
        for (Node n : children) {
            for (int i = 0; i < depth; i++) {
                res.append("-");
            }
            res.append(" ");
            res.append(n.value);
            res.append("\n");

            printTreeR(parentToChildMap, parentToChildMap.getOrDefault(n.id, new ArrayList<>()), depth+1, res);
        }
    }

}

