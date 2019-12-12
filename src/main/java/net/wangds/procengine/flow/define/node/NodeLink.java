package net.wangds.procengine.flow.define.node;

import net.wangds.procengine.flow.FlowContext;

import java.io.Serializable;

/**
 * 到下一步的连接.
 */
public interface NodeLink<C extends FlowContext> extends Serializable {

    String getNextNodeId();

    BooleanDecideDef getDecide(C ctx);

}
