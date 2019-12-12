package net.wangds.procengine.flow.define;

import net.wangds.procengine.flow.define.actor.ActorDef;
import net.wangds.procengine.flow.define.node.FlowNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象流程定义.
 * @param <A> 流程所有角色的类型.
 */
public abstract class AbstractFlowDef<A extends ActorDef> implements FlowDef<A> {

    /**
     * 流程id.
     */
    private String flowId;

    /**
     * 流程名称.
     */
    private String flowName;

    /**
     * 能够发起流程的角色定义.
     */
    private A owner;

    /**
     * 流程角色定义.
     */
    private List<ActorDef> actorDefs = new ArrayList<>();

    /**
     * 流程节点定义.
     */
    private List<FlowNode> flowNodes = new ArrayList<>();

    @Override
    public String getFlowId() {
        return flowId;
    }

    @Override
    public String getFlowName() {
        return flowName;
    }

    @Override
    public A getOwner() {
        return owner;
    }

    @Override
    public List<ActorDef> getActorDefs() {
        return actorDefs;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <AC extends ActorDef> AC fetchActor(int index) {
        if (actorDefs == null) {
            return null;
        } else if (actorDefs.size() > index) {
            return (AC) actorDefs.get(index);
        } else {
            return null;
        }
    }

    @Override
    public List<FlowNode> getFlowNodes() {
        return flowNodes;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public void setOwner(A owner) {
        this.owner = owner;
    }

    public void setActorDefs(List<ActorDef> actorDefs) {
        this.actorDefs = actorDefs;
    }

    public void setFlowNodes(List<FlowNode> flowNodes) {
        this.flowNodes = flowNodes;
    }
}
