package net.wangds.procengine.flow.instance.step;

import net.wangds.procengine.Proc;
import net.wangds.procengine.ProcResEnum;
import net.wangds.procengine.flow.instance.actor.Actor;
import net.wangds.procengine.flow.FlowContext;

/**
 * 流程步骤.
 */
public interface FlowStep<C extends FlowContext> extends Proc<C> {

    String getId();

    String getFlowInstanceId();

    String getStepName();

    Actor getOrganiger();

    ProcResEnum getRes();

}
