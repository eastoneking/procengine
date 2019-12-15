package net.wangds.procengine.flow.instance.actor;

import java.util.Collections;
import java.util.List;

public class AnonymousActor implements Actor {
    @Override
    public String getId() {
        return "anonymous";
    }

    @Override
    public String getDisplayName() {
        return "匿名用户";
    }

    @Override
    public List<String> getOrgIds() {
        return Collections.emptyList();
    }

    @Override
    public List<String> getPositionIds() {
        return Collections.emptyList();
    }

    @Override
    public String getReplacementUserId() {
        return "";
    }
}
