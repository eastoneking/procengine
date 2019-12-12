package net.wangds.procengine.flow.define.actor;

import net.wangds.procengine.flow.instance.actor.Actor;

import java.io.Serializable;

/**
 * 参与者定义.
 */
public interface ActorDef extends Serializable {
    /**
     * 判断Actor实例是否符合定义标准.
     * @param actor actor实例.
     * @return 是否符合定义.
     */
    boolean validate(Actor actor);
}
