package com.stas.tests.core.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:config.properties"
})
public interface ConfigForTests extends Config {
    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("headless")
    @DefaultValue("false")
    boolean headless();

    @Key("base.url")
    @DefaultValue("https://demoqa.com")
    String baseUrl();

    @Key("implicitTimeout")
    @DefaultValue("5")
    int implicitTimeout();

    @Key("explicitTimeout")
    @DefaultValue("10")
    int explicitTimeout();

    @Key("pageLoadTimeout")
    @DefaultValue("15")
    int pageLoadTimeout();

    @Key("username")
    @DefaultValue("admin6")
    String username();

    @Key("password")
    @DefaultValue("4HgD7asjTManyOlpahzT5Q==")
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
