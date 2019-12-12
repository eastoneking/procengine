package net.wangds.procengine.flow.define;

import net.wangds.procengine.flow.define.actor.ActorDef;
import net.wangds.procengine.flow.define.node.FlowNode;

import java.io.Serializable;
import java.util.List;

/**
 * 流程定义.
 * @param <A> 流程所属参与者类型.
 */
public interface FlowDef<A extends ActorDef> extends Serializable {

    /**
     * 流程Id.
     * @return 流程Id.
     */
    String getFlowId();

    /**
     * 流程名称.
     * @return 名称.
     */
    String getFlowName();

    /**
     * 获得所属机构.
     * @return 所属机构.
     */
    A getOwner();

    List<ActorDef> getActorDefs();

    /**
     * 获得第index个泳道的参与者.
     * @param index 顺序.
     * @param <AC> 泳道参与者类型.
     * @return 泳道参与者定义.
     */
    <AC extends ActorDef> AC fetchActor(int index);

    /**
     * 获得流程节点列表.
     * @return 流程节点列表.
     */
    List<FlowNode> getFlowNodes();


}
