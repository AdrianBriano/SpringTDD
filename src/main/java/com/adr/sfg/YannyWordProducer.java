package com.adr.sfg;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class YannyWordProducer implements WordProducer {

    @Override
    public String getWord() {
        return "Yanny";
    }
}
