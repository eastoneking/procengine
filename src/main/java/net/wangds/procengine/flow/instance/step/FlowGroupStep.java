package net.wangds.procengine.flow.instance.step;

import net.wangds.procengine.ProcResEnum;
import net.wangds.procengine.flow.FlowContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class FlowGroupStep<C extends FlowContext> extends SimpleFlowStep<C> {

    private final List<FlowStep<C>> subs = new ArrayList<>();

    private final Map<String, ProcResEnum> subStepResults = new ConcurrentHashMap<>();

    @Override
    public ProcResEnum proc(C ctx) {

        executeSubSteps(ctx, subs, subStepResults);

        return combineResults(subs, subStepResults);
    }

    protected abstract void executeSubSteps(C ctx, List<FlowStep<C>> subSteps, Map<String, ProcResEnum> subStepResults);

    @SuppressWarnings("WeakerAccess")
    protected abstract ProcResEnum combineResults(List<FlowStep<C>> subSteps, Map<String, ProcResEnum> subStepResults);
}
