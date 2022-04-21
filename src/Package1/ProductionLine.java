package Package1;

import java.util.Random;

public class ProductionLine extends Thread {
    private int id;
    private int usage;
    private int done = 1;
    private boolean resource1 = false;
    private boolean resource2 = false;
    Storage[] magazine;

    ProductionLine(int id, int usage, Storage[] magazine){
        this.id = id;
        this.usage = usage;
        this.magazine = magazine;
    }

    public void run(){
        Random rand = new Random();

        while(true){
            while(!(resource1) || !(resource2)){
                if((!resource1)){ //&& (magazine[0].state() >= usage))
                    magazine[0].collect(usage, id);
                    resource1 = true;
                }
                if((!resource2) ){ //&& (magazine[1].state() >= usage)
                    magazine[1].collect(usage, id);
                    resource2 = true;
                }
            }
            //magazine[0].collect(usage, id);
            //magazine[1].collect(usage, id);

            try {
                Thread.sleep(rand.nextInt(100) + 1);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            resource1 = false;
            resource2 = false;

            System.out.println(id + " has produced ");
            magazine[2].insert(1);
            //System.out.println(id + "wyprodukowal " + done);
            done++;
        }
    }

}
