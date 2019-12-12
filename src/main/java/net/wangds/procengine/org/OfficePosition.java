package net.wangds.procengine.org;

import java.io.Serializable;
import java.util.List;

public interface OfficePosition extends Serializable {

    String getId();
    String getPositionName();
    String getPositionDesc();

    List<OfficePosition> getParentsPosition();

}
