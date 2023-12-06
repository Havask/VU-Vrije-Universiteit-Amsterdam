package softwaredesign.Activities;

import softwaredesign.Need;

public class Play extends Need{
    public Boolean isSad;

    public Play(int decreaseInterval) {
        super(decreaseInterval);
    }

    public void PrintStatus() {
        System.out.print("Happiness: ");
        super.PrintStatus();
    }

    public void Update() {
        super.Update();
        if(super.statusPoint <= 30) {
            isSad = true;
            SendNotification();
        }
        else {
            isSad = false;
        }
    }

    public void GetHappy() {
        super.Satisfy();
        isSad = false;
    }
    private void SendNotification() {
        System.out.println("Your tamagotchi is sad!");
    }

}
