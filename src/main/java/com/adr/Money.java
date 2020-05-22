package com.adr;

import java.util.Objects;

public abstract class Money {

    protected int amount;

    public static Money dollar(int amount){
        return new Dollar(amount);
    }

    public static Money franc(int amount){
        return new Franc(amount);
    }

    public Money times(int multiplier){
        return new Dollar(amount * multiplier);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
