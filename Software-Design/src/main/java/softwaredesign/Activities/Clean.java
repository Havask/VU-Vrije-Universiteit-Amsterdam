package softwaredesign.Activities;
import java.util.Objects;

import softwaredesign.Need;

public class Clean extends Need{
    
    public Boolean isDirty;

    public Clean(int decreaseInterval) {
        super(decreaseInterval);
    }

    public void PrintStatus() {
        System.out.print("Cleanliness: ");
        super.PrintStatus();
    }

    public void Update() {

        super.Update();

        if(super.statusPoint <= 30) {
            isDirty = true;
            SendNotification();
        }
        else {
            isDirty = false;
        }
    }

    public void GetClean() {
        super.Satisfy();
        isDirty = false;
    }
    private void SendNotification() {
        System.out.println("Your tamagotchi is dirty!");
    }

}
