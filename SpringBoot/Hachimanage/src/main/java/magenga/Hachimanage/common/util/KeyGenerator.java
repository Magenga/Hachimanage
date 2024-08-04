package magenga.Hachimanage.common.util;

import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class KeyGenerator {
    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
        System.out.println(java.util.Base64.getEncoder().encodeToString(key.getEncoded()));
    }
}
