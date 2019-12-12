package net.wangds.procengine;

import java.io.Serializable;

public interface Proc<C extends ProcContext> extends Serializable {

    ProcResEnum proc(C ctx);

}
