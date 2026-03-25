package com.stas.tests.core.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config.properties"
})
public interface ConfigForTests extends Config {
    @Key("browser")
    String browser();

    @Key("headless")
    @DefaultValue("false")
    boolean headless();

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
