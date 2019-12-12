package net.wangds.procengine.flow.instance.step.groovy;

import groovy.lang.GroovyClassLoader;
import net.wangds.procengine.ProcResEnum;
import net.wangds.procengine.flow.FlowContext;
import net.wangds.procengine.flow.define.node.groovy.GroovyNode;
import net.wangds.procengine.flow.instance.step.spring.SpringStep;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import javax.script.*;

import static net.wangds.procengine.flow.instance.step.groovy.GroovyStepConstants.*;

/**
 * Groovy语言自动节点.
 * @param <C> 上下文类型.
 */
public class GroovyStep<C extends FlowContext> extends SpringStep<C> {

    private GroovyNode def;

    /**
     * 根据节点定义初始化执行步骤.
     * @param node 节点定义.
     */
    public void init(GroovyNode node){
        def = node;
    }

    @Override
    public ProcResEnum proc(C ctx) {

        if(def==null){
            this.setRes(ProcResEnum.ERROR);
            return this.getRes();
        }


        //脚本引擎
        ScriptEngine scriptEngine = ctx.getBean(CTX_KEY_GROOVY_SCRIPT_ENGINE);
        if(scriptEngine==null){
            synchronized (SCRIPT_ENGINE_MANAGER) {
                scriptEngine = SCRIPT_ENGINE_MANAGER.getEngineByName(LANGUAGE_GROOVY_NAME);
                ctx.putBean(CTX_KEY_GROOVY_SCRIPT_ENGINE, scriptEngine);
            }
        }

        ApplicationContext appCtx = getSpringApplicationContext();

        String script = def.getScript();
        if(StringUtils.isNotBlank(script)){
            ScriptContext c = scriptEngine.getContext();

            c.setBindings(new SimpleBindings(){

                @Override
                public boolean containsKey(Object key) {
                    String strKey = key.toString();
                    boolean res = StringUtils.equals(strKey, CTX_KEY_CONTEXT);
                    if(!res){
                        res=super.containsKey(key);
                    }
                    if(!res){
                        res = null!=ctx.getBean(strKey);
                    }
                    if((!res)&&appCtx!=null){
                        res = appCtx.containsBean(strKey);
                    }
                    return res;
                }

                @Override
                public Object get(Object key) {
                    String strKey = key.toString();
                    if(StringUtils.equals(strKey, CTX_KEY_CONTEXT)){
                        return ctx;
                    }
                    Object res = super.get(key);
                    if(res==null){
                        res = ctx.getBean(strKey);
                        if(res==null&&appCtx!=null){
                            res = appCtx.getBean(strKey);
                        }
                    }
                    return res;
                }
            }, ScriptContext.ENGINE_SCOPE);

            try {
                scriptEngine.eval(script, c);

                this.setRes(ProcResEnum.CONTINUE);
                this.setErrCode(0);
                this.setErrMsg("");

            } catch (ScriptException e) {
                this.setRes(ProcResEnum.ERROR);
                this.setErrCode(ERR_CODE_SCRIPT_WRONG);
                this.setErrMsg("");
            }

        }

        this.setRes(ProcResEnum.CONTINUE);
        return this.getRes();
    }
}
