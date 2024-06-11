package com.devstack.pos.util;

import java.util.Random;

public class KeyGenerator { // generation random number (to use in id)
    private static final Random RANDOM = new Random();

    public static long generateId(){
        /*long maxValue = Long.MAX_VALUE;
        long minValue = 1;
        return RANDOM.nextLong(maxValue-minValue+1)+minValue; // ihina nextLong() ikku java la (parameter
        // keattilla) option kuduththilla*/

        long maxValue = Long.MAX_VALUE;
        return (long) (RANDOM.nextDouble()*maxValue); // izu la sellappadum long da maxValue varakkim veachchiko indu
        // RANDOM.nextDouble()*minValue izula double type la value varum but long range in value thaan varum
    }
}