package com.fulgay.bilenyum.converter;

import com.fulgay.bilenyum.populators.Populator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Converter<S,T> {
    private List<T> targetList;

    Populator<S,T> populator;

    public Converter(Populator<S, T> populator) {
        this.populator = populator;
    }

    public List<T> convertToTargetList(List<S> sourceList){

        if (sourceList != null && sourceList.size() > 0) {
            targetList = new ArrayList<>();

            for (S source : sourceList) {
                T targetDto = populator.populate(source);
                targetList.add(targetDto);
            }
        }
        return targetList;
    }

}