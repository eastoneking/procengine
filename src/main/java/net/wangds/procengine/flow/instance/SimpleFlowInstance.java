package net.wangds.procengine.flow.instance;

import net.wangds.procengine.ProcResEnum;
import net.wangds.procengine.flow.FlowContext;
import net.wangds.procengine.flow.FlowEngine;
import net.wangds.procengine.flow.define.FlowDef;
import net.wangds.procengine.flow.define.actor.ActorDef;
import net.wangds.procengine.flow.define.node.FlowNode;
import net.wangds.procengine.flow.define.node.FlowNodeTypeEnum;
import net.wangds.procengine.flow.instance.actor.Actor;
import net.wangds.procengine.flow.instance.step.FlowStep;

import java.util.List;
import java.util.Optional;

/**
 * 简单流程实例模型.
 * @param <C> 上下文.
 */
public class SimpleFlowInstance<C extends FlowContext, A extends ActorDef> implements FlowInstance<C, A> {

    private String id;

    private String flowDefId;

    private Actor owner;

    private FlowStep<C> currentStep;

    private C context;

    private FlowDef<A> flowDef;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getFlowDefId() {
        return flowDefId;
    }

    @Override
    public FlowDef<A> getFlowDef() {
        return flowDef;
    }

    @Override
    public Actor getOwner() {
        return owner;
    }

    @Override
    public FlowStep<C> getCurrentStep() {
        return currentStep;
    }

    @Override
    public C getContext() {
        return context;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFlowDefId(String flowDefId) {
        this.flowDefId = flowDefId;
    }

    public void setOwner(Actor owner) {
        this.owner = owner;
    }

    public void setCurrentStep(FlowStep<C> currentStep) {
        this.currentStep = currentStep;
    }

    public void setContext(C context) {
        this.context = context;
    }

    @Override
    public ProcResEnum start() {

        Optional<FlowNode> startNodeOp = findStartNode();
        if(startNodeOp.isPresent()){
            FlowNode startNode = startNodeOp.get();
            FlowEngine.run(this.getContext(), this.getOwner(), this, startNode);
            return this.currentStep.getRes();
        }

        return ProcResEnum.FINISH;
    }

    public void setFlowDef(FlowDef<A> flowDef) {
        this.flowDef = flowDef;
    }

    protected Optional<FlowNode> findStartNode(){
        if(this.flowDef==null){
            return Optional.empty();
        }

        List<FlowNode> nodes = this.flowDef.getFlowNodes();
        if(nodes==null){
            return Optional.empty();
        }

        for(FlowNode node: nodes){
            if(FlowNodeTypeEnum.START==node.getType()){
                return Optional.of(node);
            }
        }

        return Optional.empty();
    }
}
