package net.wangds.procengine.flow;

import net.wangds.procengine.flow.define.FlowDef;
import net.wangds.procengine.flow.define.actor.ActorDef;
import net.wangds.procengine.flow.define.node.FlowNode;
import net.wangds.procengine.flow.instance.FlowInstance;
import net.wangds.procengine.flow.instance.actor.Actor;
import net.wangds.procengine.flow.instance.step.FlowStep;

import java.util.Optional;

/**
 * 流程操作.
 */
public interface FlowOperator {

    /**
     * 根据流程定义id查询流程定义实例.
     * @param flowDefId 流程定义id.
     * @param <A> 流程拥有者角色定义.
     * @return 流程定义.
     */
    <A extends ActorDef> Optional<FlowDef<A>> findFlowDefById(String flowDefId);

    /**
     * 根据流程定义创建流程实例.
     * @param actor 启动流程的参与者.
     * @param def 流程定义.
     * @param <C> 上下文类型.
     * @param <A> 启动流程参与者的定义类型.
     * @return 流程实例.
     */
    <C extends FlowContext, A extends ActorDef> Optional<FlowInstance<C, A>> createFlowInstance(Actor actor, FlowDef<A> def);


    /**
     * 根据流程节点定义生成流程实例中的步骤.
     * @param instance 流程实例.
     * @param node 流程节点定义.
     * @param actor 参与者.
     * @param <C> 上下文类型.
     * @param <A> 参与者定义类型.
     * @return 流程步骤对象.
     */
    <C extends FlowContext, A extends ActorDef> Optional<FlowStep<C>> generateFlowStep(FlowInstance<C,A> instance, FlowNode node, Actor actor);

}
