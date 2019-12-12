package net.wangds.procengine.flow.instance.actor;

import java.io.Serializable;
import java.util.List;

public interface Actor extends Serializable {

    String getId();

    String getDisplayName();

    List<String> getOrgIds();

    List<String> getPositionIds();

    String getReplacementUserId();

}
