
public class Main {

  public static void main(String args[]) {

    //lamda expression 
    Runnable thread1 = () -> {

      String threadName = Thread.currentThread().getName(); 
      System.out.println("Process 1 Running");

      try{
        Thread.sleep(2000);
        
      }catch(InterruptedException e){
        e.printStackTrace();
      }

      System.out.println("Process 1 finished");
    }; 

    //lamda expression 
    Runnable thread2 = () -> {

      String threadName = Thread.currentThread().getName(); 
      System.out.println("Process 2 Running");

      try{
        
        Thread.sleep(2000);
        
      }catch(InterruptedException e){
        e.printStackTrace();
      }

      System.out.println("Process 2 finished");
    }; 
    
    //constructs and starts a thread 
    Thread thread_one= new Thread(thread1); 
    //constructs and starts a thread 
    Thread thread_two = new Thread(thread2); 

    thread_one.setPriority(2);
    thread_two.setPriority(5);
    
    thread_one.start();
    thread_two.start();

  }
}
