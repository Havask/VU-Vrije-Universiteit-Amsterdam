package data_structures.implementation;
import java.util.ArrayList;
import data_structures.Sorted;
import java.util.TreeSet;
import java.util.concurrent.locks.ReentrantLock; 

// Replace the exceptions in the code below with the actual code for the assignment.
// Leave the public API the same so it can be tested with the existing evaluation framework.
// Document your design/implementation choices in comments.
public class CoarseGrainedTree<T extends Comparable<T>> implements Sorted<T> {

    //Creates one tree 
    TreeSet tree = new TreeSet();
    //Creates one lock 
    ReentrantLock l = new ReentrantLock();

    //Method for adding to the tree 
    public void add(T t){

        //tries to aquire the lock 
        l.lock();
        
        //if the lock gets aquired
        try{
            //Adds object to the tree 
            tree.add(t); 

        }finally{
            //Unlocks the lock and gives it to another thread
            l.unlock();
        }
    }

    //Method for removing objects from the tree
    public void remove(T t) {

        //tries to aquire the lock 
        l.lock();
      
        //if the lock gets aquired
        try{
             //Removes object from the tree
            tree.remove(t);

        }finally{
            //Unlocks the lock and gives it to another thread
            l.unlock();
        }
    }

    //Return an ArrayList containing the elements
    public ArrayList<T> toArrayList(){

        //Clears the tree and returns a empty array
        tree.clear();
        ArrayList<T>  array = new ArrayList<T>();
        return array; 
    }
}