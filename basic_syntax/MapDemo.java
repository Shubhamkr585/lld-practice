import java.util.*;
public class MapDemo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("shubham",1);
        map.put("rahul",2);
        System.out.println(map);
        for(Map.Entry<String,Integer> entry:map.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
    }
}

//Key Characteristics
//
//Unordered: Does not maintain any order of its elements.
//Allows null Keys and Values: Can have one null key and multiple null
//values.
//Not Synchronized: Not thread-safe; requires external
//synchronization if used in a multi-threaded context.
//Performance: Offers constant-time performance (O(1)) for basic
//operations like get and put, assuming the hash function disperses
//elements properly.

//Deterministic: The same input will always produce the same output.
//Fixed Output Size: Regardless of the input size, the hash code has a consistent size (32-bit, 64-bit).
//Efficient Computation: The hash function should compute the hash quickly.

// internal structure of linkedlist used in hashmap
//class Node<K, V> {
//    final int hash;
//    final K key;
//    V value;
//    Node<K, V> next;
//}

// hash code of the key
// the key itself
// the value associated with the key
// pointer to the next node in case of a collision (linked list)
// however storing in linked list also has some drawbacks like when the size of the linked list
// increases to much size due to frequent has collision then it takes to much time time
// to traverse the entire linked list
// in that case we resort to some more flexible data structure called red-black tree - self balancing binary search tree
// the red-black tree is used instead of