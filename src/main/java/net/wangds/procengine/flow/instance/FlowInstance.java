package net.wangds.procengine.flow.instance;

import net.wangds.procengine.ProcResEnum;
import net.wangds.procengine.flow.FlowContext;
import net.wangds.procengine.flow.define.FlowDef;
import net.wangds.procengine.flow.define.actor.ActorDef;
import net.wangds.procengine.flow.instance.actor.Actor;
import net.wangds.procengine.flow.instance.step.FlowStep;

public interface FlowInstance<C extends FlowContext, A extends ActorDef> {

    String getId();

    String getFlowDefId();

    FlowDef<A> getFlowDef();

    Actor getOwner();

    FlowStep<C> getCurrentStep();

    C getContext();

    void setContext(C ctx);

    ProcResEnum start();
}
