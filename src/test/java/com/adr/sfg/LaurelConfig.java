package com.adr.sfg;

import org.springframework.context.annotation.Bean;

public class LaurelConfig {

    @Bean
    LaurelWordProducer laurelWordProducer(){
        return new LaurelWordProducer();
    }

}
