package net.wangds.procengine.flow;

import net.wangds.log.helper.LogHelper;
import net.wangds.procengine.ProcResEnum;
import net.wangds.procengine.flow.define.FlowDef;
import net.wangds.procengine.flow.define.actor.ActorDef;
import net.wangds.procengine.flow.define.actor.AnonymousActorDef;
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
        public <C extends FlowContext, A extends ActorDef> Optional<FlowInstance<C, A>> createFlowInstance(Actor actor, FlowDef<A> def) {
            for(FlowOperator op:FLOW_OPERATORS){
                Optional<FlowInstance<C, A>> res = op.createFlowInstance(actor, def);
                if(res.isPresent()){
                    return res;
                }
            }
            return Optional.empty();
        }

        @Override
        public <C extends FlowContext, A extends ActorDef> Optional<FlowStep<C>> generateFlowStep(FlowInstance<C, A> instance, FlowNode node, Actor actor) {
            for(FlowOperator op:FLOW_OPERATORS){
                Optional<FlowStep<C>> res = op.generateFlowStep(instance, node, actor);
                if(res.isPresent()){
                    return res;
                }
            }
            return Optional.empty();
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
        Optional<FlowInstance<FlowContext, A>> op = combineFlowOperator.createFlowInstance(actor, flowDef);
        if(ctx==null){
            ctx = (C)new HashTableContext();
        }
        if(op.isPresent()){
            FlowInstance<FlowContext, A> instance;
            instance = op.get();
            instance.setContext(ctx);
            return instance.start();
        }else {
            LogHelper.debug("创建流程实例为null，返回流程结束.");
            return ProcResEnum.FINISH;
        }

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
        if(actorDef==null){
            actorDef = new AnonymousActorDef();
        }
        if(actorDef.validate(actor)){

            Optional<FlowStep<C>> stepOp = combineFlowOperator.generateFlowStep(instance, node, actor);

            if(stepOp.isPresent()){
                FlowStep<C> step = stepOp.get();
                instance.setCurrentStep(step);
                step.proc(ctx);
                ProcResEnum res = step.getRes();
                if(res==null){
                    res = ProcResEnum.CONTINUE;
                }
                step.setRes(res);
                return res;
            }else{
                return ProcResEnum.FINISH;
            }



        }else{
            return ProcResEnum.NO_PRIVILEGE;
        }
    }

    public static <C extends FlowContext> ProcResEnum  run(C ctx, Actor actor, String flowInstanceId){
        return ProcResEnum.CONTINUE;
    }




}
