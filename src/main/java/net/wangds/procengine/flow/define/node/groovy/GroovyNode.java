package net.wangds.procengine.flow.define.node.groovy;

import net.wangds.procengine.flow.define.node.FlowNode;

public interface GroovyNode extends FlowNode {

    default String getScriptLanguage(){
        return "groovy";
    }

    String getScript();

}
