package net.wangds.procengine.flow;

import net.wangds.procengine.flow.common.WithIdInstanceFinder;
import net.wangds.procengine.flow.define.FlowDef;
import net.wangds.procengine.flow.define.actor.ActorDef;
import net.wangds.procengine.flow.define.node.FlowNode;
import net.wangds.procengine.flow.instance.FlowInstance;
import net.wangds.procengine.flow.instance.actor.Actor;
import net.wangds.procengine.flow.instance.step.FlowStep;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
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

    /**
     * 从流程实例中查询id对应的节点定义.
     * @param instance 流程实例.
     * @param id 节点定义id.
     * @param <C> 上下文类型.
     * @param <A> 参与者定义类型.
     * @return 节点定义.
     */
    default <C extends FlowContext, A extends ActorDef> Optional<FlowNode> findFlowNode(FlowInstance<C, A> instance, String id){
        if(instance!=null){
            FlowDef<A> def = instance.getFlowDef();
            if(def!=null) {
                return new WithIdInstanceFinder<String, FlowNode>().apply(def.getFlowNodes(), id);
            }
        }
        return Optional.empty();
    }

}
