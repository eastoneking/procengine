package net.wangds.procengine.org;

import java.io.Serializable;

public interface Privilege extends Serializable {

    String getId();

    String getPrivilegeName();

    String getPrivilegeUri();

    String getAction();

}
