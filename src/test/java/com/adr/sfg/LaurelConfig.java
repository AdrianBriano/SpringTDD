package com.adr.sfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@Profile("base-test")
public class LaurelConfig {

    @Bean
    LaurelWordProducer laurelWordProducer(){
        return new LaurelWordProducer();
    }

}
