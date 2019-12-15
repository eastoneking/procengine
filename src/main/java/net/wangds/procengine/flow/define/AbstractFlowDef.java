package net.wangds.procengine.flow.define;

import net.wangds.log.helper.LogHelper;
import net.wangds.procengine.flow.common.WithIdInstanceFinder;
import net.wangds.procengine.flow.define.actor.ActorDef;
import net.wangds.procengine.flow.define.node.FlowNode;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 抽象流程定义.
 * @param <A> 流程所有角色的类型.
 */
public abstract class AbstractFlowDef<A extends ActorDef> implements FlowDef<A> {

    /**
     * 流程id.
     */
    private String id;

    /**
     * 流程名称.
     */
    private String flowName;

    /**
     * 能够发起流程的角色定义.
     */
    private A owner;

    /**
     * 流程角色定义.
     */
    private List<ActorDef> actorDefs = new ArrayList<>();

    /**
     * 流程节点定义.
     */
    private List<FlowNode> flowNodes = new ArrayList<>();

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getFlowName() {
        return flowName;
    }

    @Override
    public A getOwner() {
        return owner;
    }

    @Override
    public List<ActorDef> getActorDefs() {
        return actorDefs;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <AC extends ActorDef> AC fetchActor(int index) {
        if (actorDefs == null) {
            return null;
        } else if (actorDefs.size() > index) {
            return (AC) actorDefs.get(index);
        } else {
            return null;
        }
    }

    @Override
    public void addFlowNode(FlowNode node){
        Optional.ofNullable(this.flowNodes).ifPresent(list->{
            if(!list.contains(node)) {
                if(!new WithIdInstanceFinder<String, FlowNode>().apply(list, node.getId()).isPresent()){
                    list.add(node);
                }else{
                    LogHelper.debug("添加节点到流程定义时发现标识相同的节点已经添加到流程定义中，忽略添加操作.");
                }
            }
        });
    }

    @Override
    public void removeFlowNode(FlowNode node){
        if(node==null){
            return;
        }
        Optional.ofNullable(this.flowNodes).ifPresent(list->{
            if(list.contains(node)){
                String nextId = node.getNextId();
                String nodeId = node.getId();
                for(FlowNode cur:list){
                    if(StringUtils.equals(cur.getNextId(), nodeId)){
                        cur.setNextId(nextId);
                    }
                }
            }else{
                LogHelper.debug(()->"流程定义中不包含要删除的节点\""+node.getId()+"\".");
            }
        });
    }

    @Override
    public Optional<FlowNode> findNodeById(String nodeId){
        return new WithIdInstanceFinder<String, FlowNode>().apply(this.flowNodes, nodeId);
    }


    @Override
    public List<FlowNode> getFlowNodes() {
        return flowNodes;
    }

    public void setId(String flowId) {
        this.id = flowId;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public void setOwner(A owner) {
        this.owner = owner;
    }

    public void setActorDefs(List<ActorDef> actorDefs) {
        this.actorDefs = actorDefs;
    }

    public void setFlowNodes(List<FlowNode> flowNodes) {
        this.flowNodes = flowNodes;
    }
}
