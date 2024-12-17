package org.yearup.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface WebSecurityConfigu {
    void configure(HttpSecurity httpSecurity) throws Exception;
}
