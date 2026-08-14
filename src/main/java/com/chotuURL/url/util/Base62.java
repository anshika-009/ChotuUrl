package com.chotuURL.url.util;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class Base62 {
    private static final char[] alphabet=
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final int RADIX=alphabet.length;//here 62
    private static final SecureRandom obj=new SecureRandom();
    private Base62(){}

    public static String randomCode(int length){
        int bits=(int)Math.ceil(length*6.0);
        byte[] arr=new byte[(bits+7)/8];
        //fill the existing positions with randomness(random bytes)
        obj.nextBytes(arr);
        //after filling up
        //arr=[0x1f, 9gb4,8jsh, 453fg]
        return encode(new BigInteger(1,arr)).substring(0,length);
    }

    private static String encode(BigInteger number){
        if(number.signum()==0){return "0";}
        StringBuilder sb=new StringBuilder();
        BigInteger radix=BigInteger.valueOf(RADIX);
        //continuously divide the number by 62 and store the value of alphabet[index] in the string
        while(number.signum()>0){
            BigInteger[] divRem=number.divideAndRemainder(radix);
            sb.append(alphabet[divRem[1].intValue()]);
            number=divRem[0];
        }
        //reverse the code generated so far
        return sb.reverse().toString();
    }
}

