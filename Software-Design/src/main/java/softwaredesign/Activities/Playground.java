package softwaredesign.Activities;
import java.util.Random;
import java.util.Scanner;

//This is the playground class
public class Playground {
    Scanner scanner = new Scanner(System.in);

    public void GuessTheNumber(){

        boolean isPlaying = true;

        System.out.println("Guess a number between 0-100");
        Random rand = new Random(); 
        int Number = rand.nextInt(100); 

        while (isPlaying){

            //System.out.println(Number);

            int guess = scanner.nextInt();

            if(guess == Number){

                System.out.println("Correct! You guess it right");
                return; 
            }
            
            if(guess < Number){
                System.out.println("Go higher");
            }

            if(guess > Number){
                System.out.println("Go lower");
            }
        }

        return; 
    }

    public void GuessTheCharacter(){

        boolean isPlaying = true;

        Random r = new Random();
        char chars = (char) (97 + r.nextInt(3));


        while (isPlaying){

            //System.out.println(chars);

            char guess = scanner.next().charAt(0);

            if(guess == chars){
                System.out.println("Correct! You guess it right");              
                return; 
            }
            
            if(guess != chars){
                System.out.println("Try again");
            }
        }
    }
}
