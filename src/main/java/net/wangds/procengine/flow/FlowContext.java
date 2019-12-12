package net.wangds.procengine.flow;

import net.wangds.procengine.ProcContext;

import java.io.Serializable;

/**
 * 流程上下文
 */
public interface FlowContext extends ProcContext {

    <T> T getBean(String key);

    <T> void putBean(String key, T instance);

}
