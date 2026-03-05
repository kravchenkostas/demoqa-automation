package com.stas.tests.config;
import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config.properties"
})

public interface ConfigForTests extends Config{
    @Key("base.url")
    String baseUrl();

    @Key("username")
    String username();

    @Key("password")
    String password();

    @Key("db.url")
    String dbUrl();

    @Key("db.user")
    String dbUser();

    @Key("db.password")
    String dbPassword();
}
