package softwaredesign.Factory;
import softwaredesign.Tamagotchi;

//Simple interface for making the tamagotchi objects
public class TamagotchiFactory {
    public static Tamagotchi createTamagotchi(String name, String race) {
        String type= race.toLowerCase();

        switch(type) {
            case "cat":
                System.out.println("   /\\_/\\  ");
                System.out.println("  / o o \\  ");
                System.out.println(" (   \"   )  ");
                System.out.println("  \\~(*)~/  ");
                System.out.println("   // \\\\   ");
                break;

            case "dog":
                System.out.println("  ,-.___,-.");
                System.out.println("  \\_/_ _\\_/");
                System.out.println("    )O_O(");
                System.out.println("   { (_) }");
                System.out.println("    `-^-' ");
                break;

            case "human":
                System.out.println("      ,,, ");
                System.out.println("    /////\\\\");
                System.out.println("    |O O ||");
                System.out.println("    |/   @|");
                System.out.println("     \\-_//");
                System.out.println("     __| |__");
                System.out.println("   .'       `.");
                System.out.println("   |         |");
                System.out.println("   | |     | |");
                System.out.println("   |_|     |_|");
                System.out.println("   \\/|_____|\\/");
                System.out.println("     |  |  |");
                System.out.println("     |  |  |");
                System.out.println("    _|__|__|");
                System.out.println("   (__(____]");
                break;

            case "bunny":
                System.out.println("            ,\\");
                System.out.println("            \\\\\\,_");
                System.out.println("             \\` ,\\");
                System.out.println("        __,.-\" =__)");
                System.out.println("     .\"        )");
                System.out.println("  ,_/   ,    \\/\\_");
                System.out.println("  \\_|    )_-\\ \\_-`");
                System.out.println("      `-----` `--`");
                break;

            case "bird":
                System.out.println("             __");
                System.out.println("            /'{>");
                System.out.println("        ____) (____");
                System.out.println("      //'--;   ;--'\\\\");
                System.out.println("     ///////\\_/\\\\\\\\\\\\\\");
                System.out.println("            m m");
                break;
            default:
                System.out.println("Sorry, this is not possible. Please enter from the creature types mentioned above.");
                break;

        }
        return new Tamagotchi(name, race);
    }


}