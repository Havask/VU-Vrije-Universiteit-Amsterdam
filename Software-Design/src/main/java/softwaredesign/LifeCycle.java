package softwaredesign;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import softwaredesign.Activities.Playground;
import softwaredesign.Factory.TamagotchiFactory;

public final class LifeCycle {

    private static LifeCycle single_instance = null;

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private LifeCycle() {
    }
    public static LifeCycle getInstance()
    {
        // To ensure only one instance is created
        if (single_instance == null) {
            single_instance = new LifeCycle();
        }
        return single_instance;
    }

    public static void Start() {

        String name;
        String race;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your tamagotchi name: ");
        name = scanner.next();
        System.out.print("Enter your tamagotchi type (cat/human/bird/bunny/dog): ");
        race = scanner.next();

        //Factory method to create new instances of Tamagotchi instead of directly instantiating them.
        Tamagotchi tamagotchi = TamagotchiFactory.createTamagotchi(name, race);

        //Had to initialize the playground instance here
        Playground playground = new Playground();

        System.out.println("What do you want to do with " + tamagotchi.getName() + "?");

        tamagotchi.needs[2].Satisfy();
        tamagotchi.needs[3].Satisfy();

        int deactiveTurnCount = 0;

        while(tamagotchi.getStatus()!=Status.Dead) {

            for(int i=0;i<tamagotchi.needs.length;i++){
                tamagotchi.needs[i].PrintStatus();
            }
            System.out.println("--------------------------------");

            if(deactiveTurnCount > 0){
                deactiveTurnCount--;
                tamagotchi.Update();

                try {
                    TimeUnit.SECONDS.sleep(1);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            System.out.println("1. Feed");
            System.out.println("2. Play");
            System.out.println("3. Clean");
            System.out.println("4. Sleep");
            System.out.println("5. Skip");
            System.out.println("6. Quit");
            System.out.println("-----------------------------------");
            System.out.print("> ");

            int option = scanner.nextInt();

            switch (option) {

                case 1:
                    System.out.print("What would you like to eat? Cake (1) Pizza (2) Candy (3) Salad(4)\n");
                    int optionsFood = scanner.nextInt();
                    FoodItem selectedFood = new FoodItem(optionsFood);
                    tamagotchi.needs[1].Satisfy();
                    System.out.println("You selected "+ selectedFood.getOption() + ". Tamagotchi has eaten a " + selectedFood.getName() );
                    break;

                case 2:
                    System.out.println("Play guess the number (1) or guess the character (2)?");

                    int optionsPlay = scanner.nextInt();

                    switch (optionsPlay) {
                        case 1:
                            //Play the game then satisfy
                            playground.GuessTheNumber();
                            tamagotchi.needs[2].Satisfy();
                            break;
                        case 2:
                            playground.GuessTheCharacter();
                            tamagotchi.needs[2].Satisfy();
                            break;
                        default:
                            System.out.println("Invalid option. Try again.");
                    }
                    break;
                case 3:
                    System.out.println("Take a bath (1) or poop (2)?");
                    int optionsClean = scanner.nextInt();

                    switch (optionsClean) {
                        case 1:
                            System.out.println("Your tamagotchi is clean!");
                            break;
                        case 2:
                            System.out.println("Your tamagotchi has pooped!");
                            break;
                    }
                    tamagotchi.needs[3].Satisfy();
                    break;
                case 4:
                    System.out.println(tamagotchi.getName() + " is now sleeping... zzzz...");
                    tamagotchi.needs[0].Satisfy();
                    break;
                case 5:
                    System.out.println("How many turns do you want to skip: ");
                    deactiveTurnCount = scanner.nextInt();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }


            tamagotchi.Update();
            if(deactiveTurnCount == 0)
                System.out.println("What else do you want to do with " + tamagotchi.getName() + "?\n");

            try {
                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

