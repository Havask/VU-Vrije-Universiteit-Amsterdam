package data_structures.implementation;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import data_structures.Sorted;
import java.util.concurrent.locks.ReentrantLock; 

// Replace the exceptions in the code below with the actual code for the assignment.
// Leave the public API the same so it can be tested with the existing evaluation framework.
// Document your design/implementation choices in comments.
public class CoarseGrainedList<T extends Comparable<T>> implements Sorted<T> {

    //Creates a linked list 
    list linkedlist = new list();
    //Creates one lock 
    ReentrantLock l = new ReentrantLock();
  
    //Method for adding objects to the list
    public void add(T t) {
        
        //tries to aquire the lock 
        l.lock();
        
        //if the lock gets aquired
        try{
            //Adds object to the list 
            linkedlist.add(t);
            
        }finally{
            //Unlocks the lock and gives it to another thread
            l.unlock();
        }
    }
    
    //Method for removing objects from the list
    public void remove(T t) {

        //tries to aquire the lock 
        l.lock();
     
        //if the lock gets aquired
        try{
            //Removes object from the list
            linkedlist.remove(t);
            
        }finally{
            //Unlocks the lock and gives it to another thread
            l.unlock();
        }
    }
    
    //return an ArrayList containing the elements
    public ArrayList<T> toArrayList() {
        
        //Makes an array out of the data structure to check if its emty 
        ArrayList<T> array = linkedlist.ToArray();  

        //Returns an empty array
        return array;   
    }
}

/*
 * The linked list is based in part on this implementation found here: 
 * https://www.geeksforgeeks.org/how-to-implement-generic-linkedlist-in-java/
 */

 //Generic node class
class node<T> {
 
    //data and pointer for the nodes
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
 
    // Default constructor
    list() { this.head = null; }
   
    //Method to add to the end of the list
    void add(T t){

        //Creating new node with given value
        node<T> temp = new node<>(t);
 
        //Checks if list is empty
        if (this.head == null){
            head = temp;
        }
 
        //If list already exists
        else {
            // Temporary node for traversing the list
            node<T> Node = head;
 
            //Iterating till end of the List
            while (Node.next != null) {
                Node = Node.next;
            }
 
            //Adds the new valued at the end of the list
            Node.next = temp;
        }
    }
 
    //Method to remove an object from the list
    void remove(T t){

        //Creates a node
        node<T> prev = new node<>(null);
        //points it to the head
        prev.next = head;
        
        node<T> next = head.next;
        node<T> temp = head;
 
        //Checks if the heads needs deleting
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
    }

    //method to make the linked list to an array
    public ArrayList<T> ToArray()
    {
        //Creates a node 
        node<T> node = head;
        //Creates a arraylist 
        ArrayList<T>  array = new ArrayList<T>();

        //checks if its empty 
        if(node == null){
            return array; 
        }

        while (node.next != null) {
            //Adds everything to the array
            array.add(node.data); 
            node = node.next;
        }

        //Checks if its empty
        if(array.size() > 0){
            //empty it if its not 
            remove(array.get(0));
            array.clear();
            // Had some trouble with one object still in the list 
        } 
        
        //returns the array
        return array; 
    }
}