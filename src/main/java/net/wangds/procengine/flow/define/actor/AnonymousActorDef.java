package net.wangds.procengine.flow.define.actor;

import net.wangds.procengine.flow.instance.actor.Actor;

public class AnonymousActorDef implements ActorDef {
    @Override
    public boolean validate(Actor actor) {
        return true;
    }
}
