package net.wangds.procengine.flow.instance.step;

import net.wangds.procengine.Proc;
import net.wangds.procengine.ProcResEnum;
import net.wangds.procengine.flow.define.actor.ActorDef;
import net.wangds.procengine.flow.define.node.FlowNode;
import net.wangds.procengine.flow.instance.FlowInstance;
import net.wangds.procengine.flow.instance.actor.Actor;
import net.wangds.procengine.flow.FlowContext;

/**
 * 流程步骤.
 */
public interface FlowStep<C extends FlowContext> extends Proc<C> {


    String getId();

    String getFlowInstanceId();

    String getStepName();

    /**
     * 流程的发起人.
     * @return 发起人.
     */
    Actor getOrganiger();

    /**
     * 步骤流程执行人.
     * @return 执行人.
     */
    Actor getStepOwner();

    ProcResEnum getRes();

}
