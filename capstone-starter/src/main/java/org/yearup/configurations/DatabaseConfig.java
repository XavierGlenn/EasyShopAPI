package org.yearup.configurations;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    private BasicDataSource basicDataSource;

    @Bean
    public BasicDataSource dataSource() {
        return basicDataSource;
    }

    @Autowired
    public DatabaseConfig(@Value("${spring.datasource.url}") String url,
                          @Value("${spring.datasource.username}") String username,
                          @Value("${spring.datasource.password}") String password) {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }
}

//admin "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ4Z2FkbWluIiwiYXV0aCI6IlJPTEVfQURNSU4iLCJleHAiOjE3MzQwNDUxMzN9.A4IJ_uVa75OACtFE0WHzBEiZj2a5d-fQV0MSlGADD2qRwenLMJEyK6Iz2vluvugFOHDR-6iJ-3qsmUtfMnI9oQ",
//"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE3MzQwNDY1NjR9.BMUHMPMncGTuLi-v-Htp1OHYU0iIu1V4qxYzugCziz1HG8E8o_CULaO0LPCK8UGCYZtB2ONUZ1ufeOrvnukoiQ