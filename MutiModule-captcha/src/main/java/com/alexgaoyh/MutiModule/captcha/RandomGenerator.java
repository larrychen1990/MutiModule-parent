package com.alexgaoyh.MutiModule.captcha;

import java.util.Random;

public class RandomGenerator
{
    private static String range = "0123456789";

    public static synchronized StringBuffer getRandomString()
    {
        Random random = new Random();

        StringBuffer result = new StringBuffer();

        for ( int i = 0; i < 4; i++ )
        {
            result.append( range.charAt( random.nextInt( range.length() ) ) );
        }

        return result;
    }
}
