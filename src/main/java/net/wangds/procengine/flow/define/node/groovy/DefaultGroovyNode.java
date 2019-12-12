package net.wangds.procengine.flow.define.node.groovy;

import net.wangds.procengine.flow.define.node.AbstractFlowNode;

public class DefaultGroovyNode extends AbstractFlowNode implements GroovyNode {

    private String script;

    @Override
    public String getScript() {
        return script;
    }

    public void setScript(String script){
        this.script = script;
    }
}
