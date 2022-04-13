package com.project.adapter.security.cipher;

import com.project.adapter.util.PropertyUtil;
import com.project.domain.security.SecurityAdapter;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.Objects;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public class CipherAdapter implements SecurityAdapter {

    private static final String INSTANCE_STRING = "PBEWithMD5AndDES";
    private static final char[] PASSWORD = Objects.requireNonNull(PropertyUtil.load(CipherAdapter.class)).getProperty("secret").toCharArray();
    private static final byte[] SALT = { (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12, (byte) 0xde, (byte) 0x33,
            (byte) 0x10, (byte) 0x12, };

    @Override
    public String encrypt(String text) throws GeneralSecurityException, UnsupportedEncodingException {
        var keyFactory = SecretKeyFactory.getInstance(INSTANCE_STRING);
        var key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
        var pbeCipher = Cipher.getInstance(INSTANCE_STRING);
        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        return base64Encode(pbeCipher.doFinal(text.getBytes("UTF-8")));
    }

    private static String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    @Override
    public String decrypt(String text) throws GeneralSecurityException {
        var keyFactory = SecretKeyFactory.getInstance(INSTANCE_STRING);
        var key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
        var pbeCipher = Cipher.getInstance(INSTANCE_STRING);
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        return new String(pbeCipher.doFinal(base64Decode(text)));
    }

    private static byte[] base64Decode(String property) {
        return Base64.getDecoder().decode(property);
    }
}
