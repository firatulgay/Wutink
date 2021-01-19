package com.fulgay.wutink.populators;

public interface Populator <S,T> {

    public void populate(S source,T target);

}
