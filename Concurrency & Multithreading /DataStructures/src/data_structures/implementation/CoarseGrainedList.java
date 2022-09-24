package data_structures.implementation;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import data_structures.Sorted;
import java.util.concurrent.locks.ReentrantLock; 

// Replace the exceptions in the code below with the actual code for the assignment.
// Leave the public API the same so it can be tested with the existing evaluation framework.
// Document your design/implementation choices in comments.
public class CoarseGrainedList<T extends Comparable<T>> implements Sorted<T> {
    
    list linkedlist = new list();
    ReentrantLock l = new ReentrantLock();
  
    public void add(T t) {
        
        //System.out.print("Adding"); 
        //tries to aquire the lock 
        l.lock();
        
        try{
            linkedlist.add(t);
            //System.out.print(linkedlist + "\n"); 
            
        }finally{
            //Unlocks the lock 
            l.unlock();
        }
    }
    
    public void remove(T t) {

        //System.out.print("Removing"); 

        //tries to aquire the lock 
        l.lock();
     

        try{
            linkedlist.remove(t);
            //System.out.print(linkedlist + "\n"); 
            
        }finally{
            //Unlocks the lock 
            l.unlock();
        }
    }
    
    //return an ArrayList containing the elements
    public ArrayList<T> toArrayList() {
        
        //Makes an array out of the data structure to check if its emty 
        ArrayList<T> array = linkedlist.ToArray();  
        //System.out.print(array); 

        return array;   
    }
}

class node<T> {
 
    //data and pointer 
    T data;
    node<T> next;

    //Node constructor
    node(T data){
        this.data = data;
        this.next = null;
    }
}
 
class list<T> {
 
    node<T> head;
    private int length = 0;
 
    // Default constructor
    list() { this.head = null; }
   
    void add(T t){

        // Creating new node with given value
        node<T> temp = new node<>(t);
 
        // Checking if list is empty
        if (this.head == null) {
            head = temp;
        }
 
        // If list already exists
        else {
            // Temporary node for traversal
            node<T> Node = head;
 
            // Iterating till end of the List
            while (Node.next != null) {
                Node = Node.next;
            }
 
            // Adding new valued node at the end of the list
            Node.next = temp;
        }
 
        // Increasing length after adding new node
        length++;
    }
 
    // To remove a node from list
    void remove(T t){

        //Creates a node pointed
        node<T> prev = new node<>(null);
 
        prev.next = head;
        node<T> next = head.next;
        node<T> temp = head;
 
        //If head node needs to be deleted, making it null
        if (head.data == t) {
            head = head.next;
        }
 
        //Iterating over LinkedList
        while (temp.next != null) {

            // Comparing value of key and current node
            if (String.valueOf(temp.data).equals(String.valueOf(t))) {
                prev.next = next;
                break;
            }
 
            //Previous node now points to current node
            prev = temp;
            //Current node now points to next node
            temp = temp.next;
            // Next node points the node ahead of current node
            next = temp.next;
        }
            //Reduces the length of the list
            length--;
    }

    public String toString(){
 
        String S = "{ ";
        node<T> node = head;
 
        if (node == null)
            return S + " }";
 
        while (node.next != null) {
            S += String.valueOf(node.data) + " -> ";
            node = node.next;
        }

        S += String.valueOf(node.data);
        return S + " }";
    }

    public ArrayList<T> ToArray()
    {
        node<T> node = head;
        ArrayList<T>  array = new ArrayList<T>();

        if(node == null){
            return array; 
        }

        while (node.next != null) {
    
            array.add(node.data); 
            node = node.next;
        }

        if(array.size() > 0){
            remove(array.get(0));
            array.clear();
        } 

        return array; 
    }
}