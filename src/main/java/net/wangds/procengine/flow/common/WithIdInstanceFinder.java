package net.wangds.procengine.flow.common;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Optional;
import java.util.function.BiFunction;

public class WithIdInstanceFinder<T, E extends WithId<T>> implements BiFunction<Collection<E>, T, Optional<E>> {


    @Override
    public Optional<E> apply(Collection<E> collection, T id) {
        if(CollectionUtils.isEmpty(collection)){
            return Optional.empty();
        }

        for(E ele:collection){
            if(ele!=null){
                T elementId = ele.getId();
                if(elementId!=null){
                    if(elementId.equals(id)){
                        return Optional.of(ele);
                    }
                }
            }
        }

        return null;
    }

}
