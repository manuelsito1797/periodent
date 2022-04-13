package com.project.domain.security;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public interface SecurityAdapter {

    String encrypt(String text) throws GeneralSecurityException, UnsupportedEncodingException;

    String decrypt(String text) throws GeneralSecurityException;
}
