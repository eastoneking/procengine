package net.wangds.procengine.flow;

import net.wangds.procengine.ProcResEnum;
import net.wangds.procengine.flow.define.FlowDef;
import net.wangds.procengine.flow.define.actor.ActorDef;
import net.wangds.procengine.flow.instance.FlowConstants;
import net.wangds.procengine.flow.instance.FlowInstance;
import net.wangds.procengine.flow.instance.SimpleFlowInstance;
import net.wangds.procengine.flow.instance.actor.Actor;
import net.wangds.procengine.flow.instance.context.HashTableContext;
import net.wangds.procengine.flow.instance.step.FlowStep;

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

            SimpleFlowInstance<C, A> inst = new SimpleFlowInstance<>();

            HashTableContext ctx = new HashTableContext();
            ctx.put(FlowConstants.CTX_KEY_CONTEXT, ctx);

            inst.setContext((C)ctx);
            inst.setOwner(actor);
            inst.setId(UUID.randomUUID().toString());
            inst.setFlowDefId(def.getFlowId());
            inst.setFlowDef(def);

            return inst;
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



        return ProcResEnum.CONTINUE;
    }


    public static <C extends FlowContext, A extends ActorDef> ProcResEnum start(C ctx, Actor actor, String flowDefId) {
        Optional<FlowDef<A>> def = combineFlowOperator.findFlowDefById(flowDefId);
        if(def.isPresent()){
            return start(ctx, actor, def.get());
        }
        return ProcResEnum.FINISH;
    }

    public static <C extends FlowContext> ProcResEnum  run(C ctx, Actor actor, String flowInstanceId){



        return ProcResEnum.CONTINUE;
    }




}
