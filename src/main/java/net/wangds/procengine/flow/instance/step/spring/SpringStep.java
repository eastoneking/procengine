package net.wangds.procengine.flow.instance.step.spring;

import net.wangds.procengine.flow.FlowContext;
import net.wangds.procengine.flow.instance.step.SimpleFlowStep;
import org.springframework.context.ApplicationContext;

public class SpringStep<C extends FlowContext> extends SimpleFlowStep<C> {

    public ApplicationContext getSpringApplicationContext(){
        return null;
    }

}
