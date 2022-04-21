package Package1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
    final Lock access = new ReentrantLock();
    private int quantity = 0;
    private int type = 0;
    private int need = 0;
    public Condition ResLack = access.newCondition();

    Storage(int start_quantity, int type, int need){
        this.quantity = start_quantity;
        this.type = type;
        this.need = need;
    }

    public void collect(int usage, int id){
        access.lock();

        try {
            while(isLacking(usage)){
                System.out.println(id + " waits for " + type);
                ResLack.await();
                //access.lock();
            }
            quantity -= usage;
            if(id != 99)
                System.out.println(id + " has collected the resource: " + type + " |state: " + quantity);
            else {
                System.out.println("Collected " + usage + " products");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            access.unlock();
        }
    }

    public void insert(int quantity){
        access.lock();

        try {
            this.quantity += quantity;
            if(type != 99)
                System.out.println("Delievered to " + type + " > " + quantity);
            else
                System.out.println("Delievered " + quantity + " to magazine |state: " + this.quantity);
            ResLack.signalAll();
        } finally {
            access.unlock();
        }
    }

    public int state() {
        return this.quantity;
    }

    public int getType() {
        return this.type;
    }

    public int getNeed() {
        return this.need;
    }

    public boolean isLacking(int usage){
        if(quantity < usage)
            return true;
        return false;
    }

}
