package net.wangds.procengine.org;

import java.io.Serializable;
import java.util.List;

public interface Org extends Serializable {

    String getId();
    String getOrgName();
    String getOrgDesc();
    List<Org> getParentOrgs();

}
