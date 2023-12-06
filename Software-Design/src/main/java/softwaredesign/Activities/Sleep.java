package softwaredesign.Activities;

import softwaredesign.Need;


public class Sleep extends Need{

    public Boolean isSleepy;

    public Sleep(int decreaseInterval) {
        super(decreaseInterval);
    }

    public void PrintStatus() {
        System.out.print("Sleep: ");
        super.PrintStatus();
    }

    public void Update() {
        super.Update();
        if(super.statusPoint <= 30) {
            isSleepy = true;
            SendNotification();
        }
        else {
            isSleepy = false;
        }
    }

    public void GetSleep() {
        super.Satisfy();
        isSleepy = false;
    }
    private void SendNotification() {
        System.out.println("Your tamagotchi is sleepy!");
    }

}
