package net.wangds.procengine.flow.define.node.groovy;

import net.wangds.procengine.flow.define.FlowDef;
import net.wangds.procengine.flow.define.actor.ActorDef;
import net.wangds.procengine.flow.define.node.AbstractFlowNode;
import net.wangds.procengine.flow.define.node.FlowNode;
import net.wangds.procengine.flow.define.node.FlowNodeTypeEnum;
import org.apache.commons.lang3.StringUtils;

public class DefaultGroovyNode extends AbstractFlowNode implements GroovyNode {

    private String script;

    public DefaultGroovyNode(){

    }

    public <A extends ActorDef> DefaultGroovyNode(FlowDef<A> flow, String script, FlowNode previousNode){
        if(flow==null|| StringUtils.isBlank(script)){
            return;
        }
        flow.addFlowNode(this);
        this.script = script;
        this.setType(FlowNodeTypeEnum.AUTO);
        if(previousNode!=null){
            previousNode.setNextId(this.getId());
        }
    }

    @Override
    public String getScript() {
        return script;
    }

    public void setScript(String script){
        this.script = script;
    }
}
