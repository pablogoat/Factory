package Package1;

import java.util.Random;

public class ProductBus extends Thread{
    private int capacity;
    private int speed;
    Storage[] magazine;

    ProductBus(int capacity, int speed, Storage[] magazine){
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

            if(magazine[2].state() >= capacity){
                try {
                    Thread.sleep(rand.nextInt(speed) + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                magazine[2].collect(capacity, 99);
            }


        }
    }

}
