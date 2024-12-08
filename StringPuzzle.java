import java.util.*;

public class StringPuzzle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt(); // Number of edges
        scanner.nextLine(); // Consume the newline
        
        Map<String, List<String>> graph = new HashMap<>();
        Set<String> allWords = new HashSet<>();
        
        // Read the edges
        for (int i = 0; i < N; i++) {
            String[] edge = scanner.nextLine().split(" ");
            String from = edge[0];
            String to = edge[1];
            
            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(to);
            allWords.add(from);
            allWords.add(to);
        }
        
        // Read the input string
        String[] inputWords = scanner.nextLine().split(" ");
        
        // Calculate levels of each word
        Map<String, Integer> levels = new HashMap<>();
        calculateLevels(graph, levels);
        
        // Calculate the total value of the input string
        int totalValue = 0;
        for (String word : inputWords) {
            totalValue += levels.getOrDefault(word, -1);
        }
        
        // Output the result
        System.out.print(totalValue);
    }
    
    private static void calculateLevels(Map<String, List<String>> graph, Map<String, Integer> levels) {
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> inDegree = new HashMap<>();
        
        // Initialize in-degree for each word
        for (String node : graph.keySet()) {
            inDegree.put(node, 0);
        }
        for (List<String> edges : graph.values()) {
            for (String neighbor : edges) {
                inDegree.put(neighbor, inDegree.getOrDefault(neighbor, 0) + 1);
            }
        }
        
        // Start with nodes that have no incoming edges (roots)
        for (String node : inDegree.keySet()) {
            if (inDegree.get(node) == 0) {
                queue.add(node);
                levels.put(node, 1); // Level 1 for root nodes
            }
        }
        
        // BFS to calculate levels
        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentLevel = levels.get(current);
            
            for (String neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                levels.put(neighbor, currentLevel + 1); // Level of neighbor is current level + 1
                queue.add(neighbor);
            }
        }
    }
}