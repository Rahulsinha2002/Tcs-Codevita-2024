# Tcs-Codevita-2024
Problem : BuzzDaySal in java

import java.util.*;

public class BuzzDaySale {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Read number of items
        int n = sc.nextInt();
        int[] ids = new int[n];
        int[] costs = new int[n]; // Fixed the declaration of costs
        
        // Read IDs of items
        for (int i = 0; i < n; i++) {
            ids[i] = sc.nextInt();
        }
        
        // Read costs of items
        for (int i = 0; i < n; i++) {
            costs[i] = sc.nextInt();
        }
        
        // Read the budget
        int budget = sc.nextInt();
      
        // Prepare a list to store divisors
        List<Integer>[] divisors = new List[1001];
        for (int i = 1; i <= 1000; i++) {
            divisors[i] = new ArrayList<>();
        }
        
        // Fill the divisors for each number
        for (int j = 1; j <= 1000; j++) {
            for (int i = 1; i <= j; i++) {
                if (j % i == 0) {
                    divisors[j].add(i);
                }
            }
        }
        
        // Maps to count free items and their worth
        Map<Integer, Integer> freeItemCount = new HashMap<>();
        Map<Integer, Integer> freeItemWorth = new HashMap<>();
  
        // Calculate free items and their worth
        for (int i = 0; i < n; i++) {
            int itemId = ids[i];
            for (int divisor : divisors[itemId]) {
                for (int j = 0; j < n; j++) {
                    if (ids[j] == divisor && i != j) {
                        freeItemCount.put(itemId, freeItemCount.getOrDefault(itemId, 0) + 1);
                        freeItemWorth.put(itemId, freeItemWorth.getOrDefault(itemId, 0) + costs[j]);
                    }
                }
            }
        }
  
        int maxFreeItems = 0;
        int maxFreeWorth = 0;
  
        // Evaluate each item for purchase
        for (int i = 0; i < n; i++) {
            int itemCost = costs[i];
            int maxQuantity = budget / itemCost;
            
            if (maxQuantity > 0) {
                int freeItems = freeItemCount.getOrDefault(ids[i], 0) * maxQuantity;
                int freeWorth = freeItemWorth.getOrDefault(ids[i], 0) * maxQuantity;
                
                if (freeItems > maxFreeItems || (freeItems == maxFreeItems && freeWorth > maxFreeWorth)) {
                    maxFreeItems = freeItems;
                    maxFreeWorth = freeWorth;
                }
            }
        }
       
        // Output the result
        System.out.print(maxFreeItems + " " + maxFreeWorth);
        sc.close();
    }
}
