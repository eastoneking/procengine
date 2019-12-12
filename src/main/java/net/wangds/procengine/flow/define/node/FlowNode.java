package net.wangds.procengine.flow.define.node;

import net.wangds.procengine.flow.define.actor.ActorDef;

import java.io.Serializable;

/**
 * 流程节点.
 */
public interface FlowNode extends Serializable {

    String getId();

    String getNextId();

    FlowNodeTypeEnum getType();

    String getTitle();

    String getDesc();

    ActorDef getActorDef();


}
