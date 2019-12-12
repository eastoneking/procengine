package net.wangds.procengine.flow.instance;

/**
 * 可执行对象.
 */
public interface Executeable {

    long getExecuteTm();
    void setExecuteTm(long tm);
    String getExecuteTime();
    void setExecuteTime(String time);
    long getExecuteFinishTm();
    void setExecuteFinishTm(long tm);
    String getExecuteFinishTime();
    void setExecuteFinishTime(String time);

}
