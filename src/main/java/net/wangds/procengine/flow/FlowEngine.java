package net.wangds.procengine.flow;

import net.wangds.log.helper.LogHelper;
import net.wangds.procengine.ProcResEnum;
import net.wangds.procengine.flow.define.FlowDef;
import net.wangds.procengine.flow.define.actor.ActorDef;
import net.wangds.procengine.flow.define.node.FlowNode;
import net.wangds.procengine.flow.instance.FlowConstants;
import net.wangds.procengine.flow.instance.FlowInstance;
import net.wangds.procengine.flow.instance.SimpleFlowInstance;
import net.wangds.procengine.flow.instance.actor.Actor;
import net.wangds.procengine.flow.instance.context.HashTableContext;
import net.wangds.procengine.flow.instance.step.FlowStep;
import net.wangds.procengine.flow.instance.step.SimpleFlowStep;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 流程引擎.
 */
public class FlowEngine {

    private static final List<FlowOperator> FLOW_OPERATORS = new LinkedList<>();

    static class CombineFlowOperator implements FlowOperator {

        @Override
        public <A extends ActorDef> Optional<FlowDef<A>> findFlowDefById(String flowDefId) {
            for(FlowOperator op:FLOW_OPERATORS){
                Optional<FlowDef<A>> res = op.findFlowDefById(flowDefId);
                if(res.isPresent()){
                    return res;
                }
            }
            return Optional.empty();
        }

        @Override
        public <C extends FlowContext, A extends ActorDef> FlowInstance<C, A> createFlowInstance(Actor actor, FlowDef<A> def) {
            for(FlowOperator op:FLOW_OPERATORS){
                FlowInstance<C, A> res = op.createFlowInstance(actor, def);
                if(res!=null){
                    return res;
                }
            }
            return null;
        }

        public <C extends FlowContext, A extends ActorDef>  FlowStep<C> createFlowStepBy(C ctx, Actor actor, FlowInstance<C, A> instance, FlowNode node){
            SimpleFlowStep<C> res = new SimpleFlowStep<>();

            res.setId(UUID.randomUUID().toString());
            res.setFlowInstanceId(instance.getId());
            res.setOrganiger(instance.getOwner());
            res.setStepOwner(actor);

            return res;
        }
    }

    private static final CombineFlowOperator combineFlowOperator = new FlowEngine.CombineFlowOperator();

    /**
     * 注册流程操作组件.
     * @param fo 流程操作组件.
     */
    public static void registorFlowOperator(FlowOperator fo){
        if(fo!=null){
            synchronized (FLOW_OPERATORS){
                if(!FLOW_OPERATORS.contains(fo)) {
                    FLOW_OPERATORS.add(fo);
                }
            }
        }
    }



    public static <C extends FlowContext, A extends ActorDef> ProcResEnum start(C ctx, Actor actor, FlowDef<A> flowDef) {
        FlowInstance<FlowContext, A> instance = combineFlowOperator.createFlowInstance(actor, flowDef);
        if(ctx==null){
            ctx = (C)new HashTableContext();
        }
        instance.setContext(ctx);
        return instance.start();
    }


    public static <C extends FlowContext, A extends ActorDef> ProcResEnum start(C ctx, Actor actor, String flowDefId) {
        Optional<FlowDef<A>> def = combineFlowOperator.findFlowDefById(flowDefId);
        if(def.isPresent()){
            return start(ctx, actor, def.get());
        }else{
            LogHelper.warn("无法找到编号为\""+flowDefId+"\"的流程定义");
        }
        return ProcResEnum.FINISH;
    }

    public static <C extends FlowContext, A extends ActorDef>
    ProcResEnum  run(C ctx, Actor actor, FlowInstance<C, A> instance, FlowNode node){

        ActorDef actorDef = node.getActorDef();
        if(actorDef.validate(actor)){

            FlowStep<C> step = combineFlowOperator.createFlowStepBy(ctx, actor, instance, node);

        }else{
            return ProcResEnum.NO_PRIVILEGE;
        }

        return ProcResEnum.CONTINUE;
    }

    public static <C extends FlowContext> ProcResEnum  run(C ctx, Actor actor, String flowInstanceId){
        return ProcResEnum.CONTINUE;
    }




}
