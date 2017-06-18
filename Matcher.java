package org.tushar.test.alloc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tushar on 6/18/2017.
 */
public class Matcher {
    public static void main (String[] args) {
        int[] ordArr= {450, 350, 600, 200};
        int[] allocArr= {250,200,200,350,100,400,100};
        List<Order> orderList = getOrders(ordArr);
        List<Alloc> allocList = getAllocs(allocArr);
        Collections.sort(orderList);
        Collections.sort(allocList);

        if (getTotalOrderQuantity(orderList) > getTotalAllocQty(allocList)) {
            System.out.println("Not enough allocs available");
            return;
        }

        match(orderList,allocList);
        boolean allMatched = printOrderStatus(orderList);
        System.out.println(String.format("All orders are %s MATCHED", allMatched?"":"NOT"));



    }

    public static int getTotalOrderQuantity(List<Order> list) {
        int tQty=0;
        for (Order order: list) {
            tQty = tQty+order.quantity;
        }
        return tQty;
    }

    public static int getTotalAllocQty(List<Alloc> list) {
        int tQty=0;
        for (Alloc alloc: list) {
            tQty = tQty+alloc.quantity;
        }
        return tQty;
    }

    public static boolean printOrderStatus(List<Order> orderList) {
        boolean allMatched=true;
        System.out.println("================================");
        System.out.println("====ORDER STATUS================");
        for (Order order:orderList) {
            System.out.println(order);
            if (order.matchStatus != MatchStatus.MATCHED) {
                allMatched=false;
            }
        }
        System.out.println("===/ORDER STATUS================");
        System.out.println("================================");
        return allMatched;
    }

    private static void match(List<Order> orderList, List<Alloc> allocList) {
        for (Order order: orderList) {
            match(order, allocList);
        }
    }

    private static void match(Order order, List<Alloc> allocList) {
        for (Alloc alloc: allocList) {
            order.allocate(alloc);
        }
    }


    private static List<Order> getOrders(int[] list) {
        ArrayList<Order> retList = new ArrayList<Order>();
        for (int i=0; i < list.length; i++) {
            retList.add(new Order(i+1, list[i]));
        }
        return retList;
    }

    private static List<Order> getOrders() {
        ArrayList<Order> list = new ArrayList<Order>();
        list.add(new Order(1, 450));
        list.add(new Order(2, 350));
        list.add(new Order(3, 600));
        list.add(new Order(4, 200));
        return list;
    }

    private static List<Alloc> getAllocs(int[] list) {
        ArrayList<Alloc> retList = new ArrayList<Alloc>();
        for (int i=0; i < list.length; i++) {
            retList.add(new Alloc(i+1, list[i]));
        }
        return retList;
    }

    private static List<Alloc> getAllocs() {
        ArrayList<Alloc> list = new ArrayList<Alloc>();
        list.add(new Alloc(1, 250));
        list.add(new Alloc(2, 200));
        list.add(new Alloc(3, 200));
        list.add(new Alloc(4, 350));
        list.add(new Alloc(5, 100));
        list.add(new Alloc(6, 400));
        list.add(new Alloc(7, 100));

        return list;
    }


}
