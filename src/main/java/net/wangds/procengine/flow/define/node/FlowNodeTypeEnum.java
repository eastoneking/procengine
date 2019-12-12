package net.wangds.procengine.flow.define.node;

public enum FlowNodeTypeEnum {

    START(0x00), END(0xFF), DECIDE(0x10), AUTO(0x20), MANUAL(0x30), ASYNC(0x40), WAIT(0x50);

    private int code;

    FlowNodeTypeEnum(int code){
        this.code = code;
    }

}
