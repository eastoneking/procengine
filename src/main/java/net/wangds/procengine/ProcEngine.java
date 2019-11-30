package net.wangds.procengine;

import net.wangds.log.helper.LogHelper;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 过程执行引擎.
 * @param <C> 上下文类型.
 */
public class ProcEngine<C extends ProcContext>  {

    @SafeVarargs
    public static <C extends ProcContext> void procWhenThrowsBreak(C ctx, Proc<C>... steps) {
        new ProcEngine<C>().execute(ctx, (t)->{
            LogHelper.error(LoggerFactory.getLogger(ProcEngine.class), new Exception(t));
            return ProcResEnum.BREAK;
        }, steps);
    }

    @SafeVarargs
    @SuppressWarnings("unused")
    public static <C extends ProcContext> void procWhenThrowsContinue(C ctx, Proc<C> ... steps) {
        new ProcEngine<C>().execute(ctx, (t)->{
            LogHelper.error(LoggerFactory.getLogger(ProcEngine.class), new Exception(t));
            return ProcResEnum.CONTINUE;
        }, steps);
    }

    /**
     * 执行流程.
     * @param ctx 上下文.
     * @param onStepThrows 单个步骤抛出异常时的处理函数.
     * @param steps 步骤列表.
     */
    @SafeVarargs
    public final void execute(C ctx, Function<Throwable, ProcResEnum> onStepThrows, Proc<C>... steps){

        if(ctx==null){
            LogHelper.info("启动流程时，上下文为\"null\"，流程继续处理！");
        }

        Optional.ofNullable(steps).ifPresent(sts->  {

            ProcResEnum res = Arrays.stream(sts).reduce(ProcResEnum.CONTINUE, (preRes, c) -> {
                if (preRes == ProcResEnum.CONTINUE) {
                    try {
                        return c == null ? ProcResEnum.CONTINUE : c.proc(ctx);
                    } catch (Exception e) {
                        return onStepThrows == null ? ((Supplier<ProcResEnum>) (() -> {
                            LogHelper.info(ProcEngine.class, e);
                            return ProcResEnum.BREAK;
                        })).get() : onStepThrows.apply(e);
                    }
                }
                return preRes;
            }, (p1, p2) -> p1 == ProcResEnum.BREAK || p2 == ProcResEnum.BREAK ? ProcResEnum.BREAK : ProcResEnum.CONTINUE);

            LogHelper.trace(ProcEngine.class, ()->"执行结果:"+res);

        });

    }

}
