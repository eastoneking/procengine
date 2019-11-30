package net.wangds.procengine;

public interface Proc<C extends ProcContext> {

    ProcResEnum proc(C ctx);

}
