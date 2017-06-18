package org.tushar.test.alloc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tushar on 6/18/2017.
 */
public class Order implements Comparable<Order>{
    public int quantity;
    public int id;
    public MatchStatus matchStatus = MatchStatus.UNMATCHED;
    public int matchedQuantity=0;
    public List<Alloc> allocations=new ArrayList<Alloc>();

    public int getAvailableQty() {
        return quantity - matchedQuantity;
    }

    public void allocate(Alloc alloc) {
        if (alloc.isUsed) {
//            System.out.println("Allocation "+alloc+" is already matched.");
            return;
        }
        if (this.matchStatus == MatchStatus.MATCHED) {
//            System.out.println("Order O_"+id+" is already matched.");
            return;
        }
        if (alloc.quantity > getAvailableQty()) {
            String str = String.format("Order O_%s has only %s Quantity available, which is less than %s ", id, getAvailableQty(), alloc );
//            System.out.println(str);
            return;
        }
        alloc.isUsed=true;
        allocations.add(alloc);
        matchedQuantity=matchedQuantity+alloc.quantity;
        if (getAvailableQty() == 0) {
            this.matchStatus= MatchStatus.MATCHED;
        } else {
            this.matchStatus=MatchStatus.PARTIAL;
        }
//        System.out.println(this);

    }

    public String toString() {
        String str = String.format("O_%s(Total:%s, Matched:%s, Unmatched:%s) %s\n"+
                getAllocString()
                , id, quantity, matchedQuantity, (quantity-matchedQuantity), matchStatus);
        return str;
                //"O_"+id+"("+quantity+")";
    }
    private String getAllocString() {
        if (allocations == null || allocations.size()==0) {
            return "\n";
        }
        StringBuilder sb = new StringBuilder();
        for (Alloc alloc: allocations) {
            String str = String.format("\t A_%s(%s)\n",alloc.id, alloc.quantity);
            sb.append(str);
        }
        return sb.toString();
    }

    public Order (int id, int qty) {
        this.id=id;
        this.quantity=qty;
    }

    public int compareTo(Order other) {
        return other.quantity - this.quantity ;
    }

}
