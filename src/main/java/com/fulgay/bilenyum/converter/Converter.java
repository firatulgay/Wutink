package com.fulgay.bilenyum.converter;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class Converter<S,T> {
    private List<T> targetList;

    public List<T> convertToList(List<S> sourceList){

        if (sourceList != null && sourceList.size() > 0) {
            targetList = new ArrayList<>();

            for (S source : sourceList) {
                T targetDto = convert(source);
                targetList.add(targetDto);
            }
        }
        return targetList;
    }

    public abstract T convert(S source);

}
