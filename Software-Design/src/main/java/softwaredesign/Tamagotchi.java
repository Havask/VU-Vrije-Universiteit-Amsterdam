package softwaredesign;

import java.util.Scanner;

import softwaredesign.Activities.Clean;
import softwaredesign.Activities.Feed;
import softwaredesign.Activities.Play;
import softwaredesign.Activities.Sleep;

enum Status{
    Alive,
    Dead
}

public class Tamagotchi {

    private String name;
    public String creature;
    private Status status;
    public Need needs[] = new Need[4];

    //constructor 
    public Tamagotchi(String name, String creature){

        this.name = name;
        this.creature = creature;

        //Creates instences for each need. 
        this.needs[0] = new Sleep(7);
        this.needs[1] = new Feed(2);
        this.needs[2] = new Play(5);
        this.needs[3] = new Clean(3);
    }

    //send in a parameter for which need needs updating. 
    //But we need to do an acticity before this satisfy function gets called
    public void Update(){

        for(int i=0;i<this.needs.length;i++){
            this.needs[i].Update();

            if(this.needs[i].checkStatus()) {
                System.out.println("Your Tamagotchi is dead!");
                System.out.println("GAME OVER !!!!!!");
                this.status = Status.Dead;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void printStatus() {
        for(int i=0;i<this.needs.length;i++){
            this.needs[i].PrintStatus();
        }

        System.out.println("Status: " + status.toString());
    }

}
