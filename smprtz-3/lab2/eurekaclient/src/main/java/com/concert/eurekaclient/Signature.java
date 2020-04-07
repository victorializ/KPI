package com.concert.eurekaclient;

public class Signature<T> {
    private final T data;
    private final String instanceId;

    public Signature(T data, String instanceId) {
        this.data = data;
        this.instanceId = instanceId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public T getData() {
        return data;
    }
}