package com.stas.tests.core.config;

import org.aeonbits.owner.Converter;
import com.stas.tests.utils.CryptoUtils;

import java.lang.reflect.Method;

public class DecryptConverter implements Converter<String>{
    @Override
    public String convert(Method method, String input){
        return CryptoUtils.decrypt(input);
    }
}
