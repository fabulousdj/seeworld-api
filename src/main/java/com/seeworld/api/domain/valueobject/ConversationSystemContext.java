package com.seeworld.api.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

@Component
public class ConversationSystemContext {
    @JsonProperty("dialog_stack")
    private List dialogStack;
    @JsonProperty("dialog_turn_counter")
    private int dialogTurnCounter;
    @JsonProperty("dialog_request_counter")
    private int dialogRequestCounter;
    @JsonProperty("_node_output_map")
    private Map nodeOutputMap;

    public List getDialogStack() {
        return dialogStack;
    }

    public void setDialogStack(List dialogStack) {
        this.dialogStack = dialogStack;
    }

    public int getDialogTurnCounter() {
        return dialogTurnCounter;
    }

    public void setDialogTurnCounter(int dialogTurnCounter) {
        this.dialogTurnCounter = dialogTurnCounter;
    }

    public int getDialogRequestCounter() {
        return dialogRequestCounter;
    }

    public void setDialogRequestCounter(int dialogRequestCounter) {
        this.dialogRequestCounter = dialogRequestCounter;
    }

    public Map getNodeOutputMap() {
        return nodeOutputMap;
    }

    public void setNodeOutputMap(Map nodeOutputMap) {
        this.nodeOutputMap = nodeOutputMap;
    }
}
