package net.wangds.procengine.flow.define.node;

import net.wangds.procengine.flow.FlowContext;

import java.util.function.Function;

/**
 * 判定定义.
 * @param <C> 上下文类型.
 * @param <R> 结果类型.
 */
public interface DecideDef<C extends FlowContext, R> extends FlowNode {

    Function<C, R> getDecideFunc();

}
