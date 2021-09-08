package by.itransition;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;

public class UtilHMAC{
    private final SecureRandom random = new SecureRandom();
    private final Mac mac = Mac.getInstance("HmacSHA256");
    private byte[] keyBytes=new byte[16];

    public UtilHMAC() throws NoSuchAlgorithmException, InvalidKeyException {
        random.nextBytes(keyBytes);
        SecretKeySpec key = new SecretKeySpec(keyBytes,"HmacSHA256");
        mac.init(key);
    }

    public String getHMAC(String data){
        return Hex.encodeHexString(mac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }

    public String getKey(){
        return Hex.encodeHexString(keyBytes);
    }
}
