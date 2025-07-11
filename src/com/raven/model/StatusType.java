package com.raven.model;

public enum StatusType {
    RUNNING, FIXING, PAUSING, SUCCESS, ACTIVE;
    public static StatusType fromIndex(int index) {
        StatusType[] values = StatusType.values();
        if (index >= 0 && index < values.length) {
            return values[index];
        }
        return PAUSING;
    }
}
