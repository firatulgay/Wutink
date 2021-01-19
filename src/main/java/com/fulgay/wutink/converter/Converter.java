package com.fulgay.wutink.converter;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class Converter<S,T> {

    public List<T> convertToList(List<S> sourceList){
        List<T> targetList = new ArrayList<>();

        if (sourceList != null && sourceList.size() > 0) {

            for (S source : sourceList) {
                T targetDto = convert(source);
                targetList.add(targetDto);
            }
        }
        return targetList;
    }

    public abstract T convert(S source);

}
