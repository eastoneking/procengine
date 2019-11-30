package net.wangds.procengine;

public enum ProcResEnum {

    /**
     * 继续处理下一步.
     */
    CONTINUE,
    /**
     * 不再处理后续任务.
     */
    BREAK,
    /**
     * 等待人工触发下一步.
     */
    WAIT,
    /**
     * 正在执行, 一般异步执行步骤结果为此类型.
     */
    RUNNING,
    /**
     * 整个流程结束.
     */
    FINISH,
    /**
     * 流程异常.
     */
    ERROR,
    /**
     * 流程存档,不在算作活跃流程.
     */
    ARCHIVE

}
