package com.adr;

public class Bank {

    Money reduce(Expression source, String toCurrency) {
        return source.reduce(toCurrency);
    }

}
