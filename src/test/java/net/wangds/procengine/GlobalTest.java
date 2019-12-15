package net.wangds.procengine;

import net.wangds.log.helper.LogHelper;
import net.wangds.procengine.flow.FlowContext;
import net.wangds.procengine.flow.FlowEngine;
import net.wangds.procengine.flow.FlowOperator;
import net.wangds.procengine.flow.define.AbstractFlowDef;
import net.wangds.procengine.flow.define.FlowDef;
import net.wangds.procengine.flow.define.actor.ActorDef;
import net.wangds.procengine.flow.define.node.FlowNode;
import net.wangds.procengine.flow.define.node.FlowNodeTypeEnum;
import net.wangds.procengine.flow.define.node.StartFlowNode;
import net.wangds.procengine.flow.instance.FlowInstance;
import net.wangds.procengine.flow.instance.SimpleFlowInstance;
import net.wangds.procengine.flow.instance.actor.Actor;
import net.wangds.procengine.flow.instance.actor.AnonymousActor;
import net.wangds.procengine.flow.instance.context.HashTableContext;
import net.wangds.procengine.flow.instance.step.FlowStep;
import net.wangds.procengine.flow.instance.step.SimpleFlowStep;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

public class GlobalTest {

    @Test
    public void test(){

        FlowEngine.registorFlowOperator(new FlowOperator() {
            @Override
            public <A extends ActorDef> Optional<FlowDef<A>> findFlowDefById(String flowDefId) {
                AbstractFlowDef<A> res = new AbstractFlowDef<A>() {};

                FlowNode start = new StartFlowNode();
                res.addFlowNode(start);

                return Optional.of(res);
            }

            @Override
            public <C extends FlowContext, A extends ActorDef> Optional<FlowInstance<C, A>> createFlowInstance(Actor actor, FlowDef<A> def) {
                LogHelper.info("create flow instance");
                SimpleFlowInstance<C, A> inst = new SimpleFlowInstance<>();

                inst.setOwner(actor);
                inst.setId(UUID.randomUUID().toString());
                inst.setFlowDefId(def.getId());
                inst.setFlowDef(def);

                return Optional.of(inst);
            }

            @Override
            public <C extends FlowContext, A extends ActorDef> Optional<FlowStep<C>> generateFlowStep(FlowInstance<C,A> instance, FlowNode node, Actor actor) {

                Optional<FlowStep<C>> res = Optional.empty();

                if(node==null){
                    return res;
                }

                FlowNodeTypeEnum type = node.getType();

                FlowStep<C> step = null;
                switch(type){
                    case START:
                        SimpleFlowStep<C> tmp = new SimpleFlowStep<C>(){
                            @Override
                            public ProcResEnum proc(C ctx) {
                                LogHelper.debug("执行开始节点");
                                return super.proc(ctx);
                            }
                        };
                        tmp.initialize(instance, node, actor);
                        step = tmp;
                        break;
                    default:
                }

                if(step!=null){
                    res = Optional.of(step);
                }

                return res;
            }
        });

        FlowEngine.start(new HashTableContext(), new AnonymousActor(), "test");
    }
}
