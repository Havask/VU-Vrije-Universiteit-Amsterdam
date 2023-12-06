package softwaredesign.Activities;

import softwaredesign.Need;

public class Feed extends Need{
    public Boolean isHungry;

    public Feed(int decreaseInterval) {
        super(decreaseInterval);
    }

    public void PrintStatus() {
        System.out.print("Hunger: ");
        super.PrintStatus();
    }

    public void Update() {
        super.Update();
        if(super.statusPoint <= 30) {
            isHungry = true;
            SendNotification();
        }
        else {
            isHungry = false;
        }
    }

    public void GetFed() {
        super.Satisfy();
        isHungry = false;
    }
    private void SendNotification() {
        System.out.println("Your tamagotchi is hungry!");
    }
}
