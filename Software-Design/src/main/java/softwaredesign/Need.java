package softwaredesign;

public class Need {
    protected int statusPoint;
    protected int decreaseInterval;
    protected int lifetime;

    //Generates a bar of hashtags for each need
    protected void PrintStatus() {
        String bar = "";
        int hashtagCount = statusPoint/10;
        for (int i = 0; i < hashtagCount; i++) {
            bar += "#";
        }
        System.out.println(bar);
    }

    //Defines the interval of decreasing the need
    public Need(int decreaseInterval) {
        this.statusPoint = 100;
        this.decreaseInterval = decreaseInterval;
        this.lifetime = 0;
    }

    //updates the points for the given need
    protected void Satisfy() {
        statusPoint += 10;
    }

    //Updates the lifetime and statuspoint from the tamagotchi class
    public void Update() {
        this.lifetime += 1;
        if(this.lifetime % this.decreaseInterval == 0 && this.lifetime != 0){
            this.statusPoint -= 10;
        }
    }

    //Checks the status on the given need
    public boolean checkStatus() {
        return this.statusPoint <= 0;
    }

}

