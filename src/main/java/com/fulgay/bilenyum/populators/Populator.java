package com.fulgay.bilenyum.populators;

import org.springframework.stereotype.Component;

@Component
public interface Populator <S,T> {

    public T populate(S source);

}
