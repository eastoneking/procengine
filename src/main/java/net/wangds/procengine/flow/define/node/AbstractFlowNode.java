package net.wangds.procengine.flow.define.node;

import net.wangds.procengine.flow.define.actor.ActorDef;

public abstract class AbstractFlowNode implements  FlowNode {

    private String id;
    private String nextId;
    private FlowNodeTypeEnum type;
    private String title;
    private String desc;
    private ActorDef actorDef;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getNextId() {
        return nextId;
    }

    @Override
    public FlowNodeTypeEnum getType() {
        return type;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public ActorDef getActorDef() {
        return actorDef;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNextId(String nextId) {
        this.nextId = nextId;
    }

    public void setType(FlowNodeTypeEnum type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setActorDef(ActorDef actorDef) {
        this.actorDef = actorDef;
    }
}
