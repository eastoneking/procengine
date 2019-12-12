package net.wangds.procengine.flow.instance;

public class AbstractExecuteable implements Executeable{

    private long executeTm = System.currentTimeMillis();
    private long executeFinishTm = -1;


    @Override
    public long getExecuteTm() {
        return executeTm;
    }

    @Override
    public void setExecuteTm(long tm) {
        this.executeTm = tm;
    }

    @Override
    public String getExecuteTime() {
        return null;
    }

    @Override
    public void setExecuteTime(String time) {

    }

    @Override
    public long getExecuteFinishTm() {
        return executeFinishTm;
    }

    @Override
    public void setExecuteFinishTm(long tm) {
        this.executeFinishTm = tm;
    }

    @Override
    public String getExecuteFinishTime() {
        return null;
    }

    @Override
    public void setExecuteFinishTime(String time) {

    }
}
