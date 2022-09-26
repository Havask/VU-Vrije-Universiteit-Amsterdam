package data_structures.implementation;
import java.util.ArrayList;
import data_structures.Sorted;
import java.util.TreeSet;
import java.util.concurrent.locks.ReentrantLock; 


// Replace the exceptions in the code below with the actual code for the assignment.
// Leave the public API the same so it can be tested with the existing evaluation framework.
// Document your design/implementation choices in comments.
public class CoarseGrainedTree<T extends Comparable<T>> implements Sorted<T> {


    TreeSet tree = new TreeSet();
    ReentrantLock l = new ReentrantLock();

    public void add(T t) {

        //tries to aquire the lock 
        l.lock();
        
        try{
            tree.add(t); 
            System.out.print(tree); 
        }finally{
            //Unlocks the lock 
            l.unlock();
        }
    }

    public void remove(T t) {

        //tries to aquire the lock 
        l.lock();
      
        try{
            tree.remove(t);
            System.out.print(tree); 
        }finally{
            //Unlocks the lock 
            l.unlock();
        }
    }

    public ArrayList<T> toArrayList(){
        tree.clear();
        ArrayList<T>  array = new ArrayList<T>();
        return array; 
    }
}