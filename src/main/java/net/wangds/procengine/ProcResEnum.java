package net.wangds.procengine;

public enum ProcResEnum {

    /**
     * 继续处理下一步.
     */
    CONTINUE(0x30),
    /**
     * 不再处理后续任务.
     */
    BREAK(0x40),
    /**
     * 等待人工触发下一步.
     */
    WAIT(0x50),
    /**
     * 正在执行, 一般异步执行步骤结果为此类型.
     */
    RUNNING(0x20),
    /**
     * 整个流程结束.
     */
    FINISH(0xA0),
    /**
     * 流程异常.
     */
    ERROR(0xB0),
    /**
     * 流程存档,不在算作活跃流程.
     */
    ARCHIVE(0xC0),
    /**
     * 初始化.
     */
    INIT(0x00),
    /**
     * 尚未运行.
     */
    NOT_START(0x10);

    private int code = 0;

    ProcResEnum(int code){
        this.code = code;
    }

}
