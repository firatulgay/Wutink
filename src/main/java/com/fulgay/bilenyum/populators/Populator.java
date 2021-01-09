package com.fulgay.bilenyum.populators;

public interface Populator <S,T> {

    public T populate(S source);

}
