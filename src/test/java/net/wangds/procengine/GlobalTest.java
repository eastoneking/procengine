package net.wangds.procengine;

import net.wangds.log.helper.LogHelper;
import net.wangds.procengine.flow.FlowContext;
import net.wangds.procengine.flow.FlowEngine;
import net.wangds.procengine.flow.FlowOperator;
import net.wangds.procengine.flow.define.AbstractFlowDef;
import net.wangds.procengine.flow.define.FlowDef;
import net.wangds.procengine.flow.define.actor.ActorDef;
import net.wangds.procengine.flow.instance.FlowInstance;
import net.wangds.procengine.flow.instance.SimpleFlowInstance;
import net.wangds.procengine.flow.instance.actor.Actor;
import net.wangds.procengine.flow.instance.actor.AnonymousActor;
import net.wangds.procengine.flow.instance.context.HashTableContext;
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



                return Optional.of(res);
            }

            @Override
            public <C extends FlowContext, A extends ActorDef> FlowInstance<C, A> createFlowInstance(Actor actor, FlowDef<A> def) {
                LogHelper.info("create flow instance");
                SimpleFlowInstance<C, A> inst = new SimpleFlowInstance<>();

                inst.setOwner(actor);
                inst.setId(UUID.randomUUID().toString());
                inst.setFlowDefId(def.getId());
                inst.setFlowDef(def);

                return inst;
            }
        });

        FlowEngine.start(new HashTableContext(), new AnonymousActor(), "test");
    }
}
