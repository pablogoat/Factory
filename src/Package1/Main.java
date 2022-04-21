package Package1;

public class Main {
    public static void main (String[] args){
        int start_quantity1 = 50;
        int start_quantity2 = 40;
        int need1 = 5;
        int need2 = 5;
        int busCapacity = 20;
        int busDelay = 50;
        int Lines = 3;

        Storage[] magazine = new Storage[3];

        magazine[0] = new Storage(start_quantity1, 1, need1);
        magazine[1] = new Storage(start_quantity2, 2, need2);
        magazine[2] = new Storage(0, 99, need2);
        ResourceBus supply = new ResourceBus(busCapacity, busDelay, magazine);
        ProductBus collector = new ProductBus(10, 100, magazine);
        ProductionLine[] Line = new ProductionLine[Lines];

        for(int i = 0;i < Lines; i++){
            Line[i] = new ProductionLine(i, 3, magazine);
        }

        supply.start();
        collector.start();

        for(int i = 0;i < Lines; i++){
            Line[i].start();
        }

        for(int i = 0;i < Lines; i++){
            try{
                Line[i].join();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        try{
            supply.join();
            collector.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
