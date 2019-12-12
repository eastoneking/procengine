package net.wangds.procengine.flow.instance.context;

import net.wangds.procengine.flow.FlowContext;

import java.io.Serializable;
import java.util.Hashtable;

public class HashTableContext extends Hashtable<String, Serializable> implements FlowContext {
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(String key) {
        return (T)this.get(key);
    }

    @Override
    public <T extends Serializable> void putBean(String key, T instance) {
        this.put(key, instance);
    }
}
