package net.wangds.procengine.flow.define.node;

import net.wangds.procengine.flow.common.WithId;
import net.wangds.procengine.flow.define.actor.ActorDef;

import java.io.Serializable;

/**
 * 流程节点.
 */
public interface FlowNode extends Serializable, WithId<String> {

    String getId();

    String getNextId();

    void setNextId(String nextId);

    FlowNodeTypeEnum getType();

    String getTitle();

    String getDesc();

    ActorDef getActorDef();


}
