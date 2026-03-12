package com.stas.tests.config;

import org.aeonbits.owner.Config;
import com.stas.tests.config.DecryptConverter;

@Config.Sources({
        "classpath:config.properties"
})

public interface ConfigForTests extends Config{
    @Key("browser")
    String browser();

    @Key("base.url")
    String baseUrl();

    @Key("implicitTimeout")
    int implicitTimeout();

    @Key("explicitTimeout")
    int explicitTimeout();

    @Key("pageLoadTimeout")
    int pageLoadTimeout();

    @Key("username")
    String username();

    @Key("password")
    @ConverterClass(DecryptConverter.class)
    String password();

    @Key("db.url")
    String dbUrl();

    @Key("db.user")
    String dbUser();

    @Key("db.password")
    @ConverterClass(DecryptConverter.class)
    String dbPassword();
}
