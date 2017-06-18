package org.tushar.test.alloc;

/**
 * Created by Tushar on 6/18/2017.
 */
public class Alloc implements Comparable<Alloc>{
    public int quantity;
    public int id;
    public boolean isUsed=false;

    public String toString() {
        return "A_"+id+"("+quantity+")";
    }

    public Alloc (int id, int qty) {
        this.id=id;
        this.quantity=qty;
    }

    public int compareTo(Alloc other) {
        return other.quantity - this.quantity;
    }

}
