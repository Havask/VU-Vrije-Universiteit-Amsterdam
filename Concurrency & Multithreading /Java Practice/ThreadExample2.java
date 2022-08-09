

public class ThreadExample2 {
    
    public static class MyThread extends Thread {

        public void run(){
            System.out.println("Mythread is running"); 
            System.out.println("Mythread is finished"); 
        }
    }
    public static void main(String[] args){
        MyThread myThread = new MyThread(); 
        myThread.start(); 
    }
}
