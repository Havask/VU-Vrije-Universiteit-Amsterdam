package softwaredesign;

public class FoodItem {
    private String name;
    private int hungerDecr;
    private int option;

    public FoodItem(int option) {
        this.option=option;
        switch (option){
            case 1:
                this.name="Cake";
//                this.hungerDecr=15;
                break;
            case 2:
                this.name="Pizza";
//                this.hungerDecr=10;
                break;
            case 3:
                this.name="Candy";
//                this.hungerDecr=5;
                break;
            case 4:
                this.name="Salad";
//                this.hungerDecr=10;
                break;
        }
    }
    public String getName(){
        return this.name;
    }
    public int getOption(){
        return this.option;
    }
    public int getHungerDecr(){
        return this.hungerDecr;
    }
}
