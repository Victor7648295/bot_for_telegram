package com.victor.back.converter;

import java.util.Collection;

import java.util.List;

public interface TypeConverter<S,T> {

    Class<S> getSourceClass();

    Class<T> getTargetClass();

    T convert(S source);

    List<T> convertList(Collection<S> sourceList);
}
