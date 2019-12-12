package net.wangds.procengine.flow.instance.step;

import net.wangds.procengine.ProcResEnum;
import net.wangds.procengine.flow.FlowContext;
import net.wangds.procengine.flow.FlowEngine;

import java.util.List;
import java.util.Map;

public class SteamFlowGroupStep<C extends FlowContext> extends AbstractWithCombinFunFlowGroupStep<C> {
    @Override
    protected void executeSubSteps(C ctx, List<FlowStep<C>> subSteps, Map<String, ProcResEnum> subStepResults) {
        subSteps.stream().reduce(subStepResults, (resultMap, curStep)->{
            String key = curStep.getId();
            resultMap.put(key, FlowEngine.run(ctx, curStep));
            return resultMap;
        }, (r1, r2)->{r1.putAll(r2);return r1;});
    }
}
