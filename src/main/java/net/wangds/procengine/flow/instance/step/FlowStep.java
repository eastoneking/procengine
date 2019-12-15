package net.wangds.procengine.flow.instance.step;

import net.wangds.procengine.Proc;
import net.wangds.procengine.ProcResEnum;
import net.wangds.procengine.flow.FlowContext;
import net.wangds.procengine.flow.define.node.FlowNode;
import net.wangds.procengine.flow.instance.actor.Actor;

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

    /**
     * 流程定义.
     * @return 流程定义.
     */
    FlowNode getFlowNode();

    ProcResEnum getRes();

    void setRes(ProcResEnum res);

}
