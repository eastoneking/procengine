package net.wangds.procengine.flow.instance.step.groovy;

import net.wangds.procengine.flow.instance.FlowConstants;

import javax.script.ScriptEngineManager;

public interface GroovyStepConstants extends FlowConstants {

    ScriptEngineManager SCRIPT_ENGINE_MANAGER = new ScriptEngineManager();

    String LANGUAGE_GROOVY_NAME = "Groovy";

    /**
     * 上下文中GROOVY引擎.
     */
    String CTX_KEY_GROOVY_SCRIPT_ENGINE = "__CTX_KEY_GROOVY_SCRIPT_ENGINE";


    /**
     * 脚本执行错误异常.
     */
    int ERR_CODE_SCRIPT_WRONG = 0x100000;

}
