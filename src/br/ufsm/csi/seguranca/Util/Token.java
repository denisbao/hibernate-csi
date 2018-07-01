package br.ufsm.csi.seguranca.Util;

import java.security.SecureRandom;

public class Token {

    private static SecureRandom random = new SecureRandom();

    public static synchronized String generateToken() {
        long longToken = Math.abs( random.nextLong() );
        String random = Long.toString( longToken, 16 );
        return random;
    }
}
