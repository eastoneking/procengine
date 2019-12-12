package net.wangds.procengine.org;

import java.io.Serializable;
import java.util.List;

public interface Role extends Serializable {

    String getId();
    String getRoleName();
    String getDesc();

    List<Privilege> getPrivileges();


}
