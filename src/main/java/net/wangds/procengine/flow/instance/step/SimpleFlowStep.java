package net.wangds.procengine.flow.instance.step;

import net.wangds.procengine.ProcResEnum;
import net.wangds.procengine.flow.FlowContext;
import net.wangds.procengine.flow.instance.actor.Actor;

/**
 * 最简单的步骤实现.
 * @param <C> 上下文类型.
 */
public class SimpleFlowStep<C extends FlowContext> implements FlowStep<C> {


    private String id;

    private String flowInstanceId;

    private String stepName;

    private ProcResEnum res;

    /**
     * 发起人.
     */
    private Actor organiger;

    /**
     * 执行人.
     */
    private Actor stepOwner;

    private int errCode;

    private String errMsg;

    private Throwable cause;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getFlowInstanceId() {
        return flowInstanceId;
    }

    @Override
    public String getStepName() {
        return stepName;
    }

    @Override
    public Actor getOrganiger() {
        return organiger;
    }

    @Override
    public Actor getStepOwner() {
        return stepOwner;
    }

    @Override
    public ProcResEnum getRes() {
        return res;
    }

    public void setId(String stepId) {
        this.id = stepId;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public void setRes(ProcResEnum res) {
        this.res = res;
    }

    public void setOrganiger(Actor organiger) {
        this.organiger = organiger;
    }

    @Override
    public ProcResEnum proc(C ctx) {
        return ProcResEnum.CONTINUE;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public void setFlowInstanceId(String flowInstanceId) {
        this.flowInstanceId = flowInstanceId;
    }

    public void setStepOwner(Actor stepOwner) {
        this.stepOwner = stepOwner;
    }
}
