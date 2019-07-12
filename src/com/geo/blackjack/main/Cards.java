package com.geo.blackjack.main;

import java.util.*;

public class Cards {
    private static int[] p1Cards = new int[getTheoreticalMaxDrawForBlackjack()];

    public static void main(String[] args) {
        for (int i : p1Cards) {
            p1Cards[i] = 0;
        }

        int[] cardTypesArr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        int min = cardTypesArr[0];
        int max = cardTypesArr[cardTypesArr.length-1];

        boolean isBustOrFold = false;

        Scanner sc = new Scanner(System.in);

        p1Cards[0] = getRandomNumber(min, max);
        p1Cards[1] = getRandomNumber(min, max);

        System.out.println("Player one dealt cards: " + p1Cards[0] + ", "  + p1Cards[1]);

        System.out.println("Hit me or fold?");

        System.out.println("Type hit to get dealt another card, type stick if you do not want any more");

        int c = 2;
        String choice = sc.next();
        while(!isBustOrFold) {
            if (choice.equalsIgnoreCase("hit")) {
                p1Cards[c] = getRandomNumber(min, max);
                System.out.println("You drew the card: " + p1Cards[c]);
                System.out.println("Your total is: " + sumPlayerTotal(p1Cards));
                c++;
                choice = "";
            } else if (choice.equalsIgnoreCase("stick")) {
                System.out.println("You finished with a score of: " + sumPlayerTotal(p1Cards));
                isBustOrFold = true;
            }
            else {
                System.out.println("Pick another card.");
                System.out.println("Type hit to get dealt another card, type stick if you do not want any more");
                choice = sc.next();
            }

            if (isBust(p1Cards)) {
                System.out.println("You went bust with a total of: " + sumPlayerTotal(p1Cards) + ". Oh no!");
                isBustOrFold = true;
            }
        }
    }

    public static boolean isBust(int[] items) {
        return sumPlayerTotal(items) > 21;
    }

    public static int sumPlayerTotal(int[] items) {
        int total = 0;
        for (int i = 0; i < items.length; i++) {
            total = total + getCardValue(items[i]);
        }
        return total;
    }

    public static int getRandomNumber(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static int getTheoreticalMaxDrawForBlackjack() {
        int max = 21;
        int total = 0;
        int cardUse = 0;
        for (int i = 1; total < max; i++) {
            total = total + i * 4;
            cardUse += 4;
        }
        return cardUse;
    }

    public static int getCardValue(int i) {
        if (i >= 1 && i <= 10) {
            return i;
        } else {
            return 10;
        }
    }

    public static boolean containsAce(int[] list) {
        for (int i : list) {
            if (i == 1) {
                return true;
            }
        }
        return false;
    }
}
