package net.wangds.procengine.flow.instance.step;

import net.wangds.procengine.ProcResEnum;
import net.wangds.procengine.flow.FlowContext;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public abstract class AbstractWithCombinFunFlowGroupStep<C extends FlowContext> extends FlowGroupStep<C> {

    private BiFunction<List<FlowStep<C>>, Map<String, ProcResEnum>, ProcResEnum> combineResultFunction;

    public BiFunction<List<FlowStep<C>>, Map<String, ProcResEnum>, ProcResEnum> getCombineResultFunction() {
        return combineResultFunction;
    }

    public void setCombineResultFunction(BiFunction<List<FlowStep<C>>, Map<String, ProcResEnum>, ProcResEnum> combineResultFunction) {
        this.combineResultFunction = combineResultFunction;
    }

    @Override
    protected ProcResEnum combineResults(List<FlowStep<C>> subSteps, Map<String, ProcResEnum> subStepResults) {
        return combineResultFunction==null?ProcResEnum.CONTINUE:combineResultFunction.apply(subSteps, subStepResults);
    }
}
