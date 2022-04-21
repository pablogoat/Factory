package Package1;

import java.util.Random;

public class ResourceBus extends Thread{
    private int capacity;
    private int speed;
    Storage[] magazine;

    ResourceBus(int capacity, int speed, Storage[] magazine){
        this.capacity = capacity;
        this.speed = speed;
        this.magazine = magazine;
    }

    public void run(){
        Random rand = new Random();

        while(true){

            try {
                Thread.sleep(rand.nextInt(10) + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(magazine[0].getNeed() > magazine[0].state()){
                try {
                    Thread.sleep(rand.nextInt(speed) + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                magazine[0].insert(capacity);
            }

            if(magazine[1].getNeed() > magazine[1].state()){
                try {
                    Thread.sleep(rand.nextInt(speed) + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                magazine[1].insert(capacity);
            }
        }
    }

}
