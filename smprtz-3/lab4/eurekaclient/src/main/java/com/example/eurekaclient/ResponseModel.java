package com.example.eurekaclient;

public class ResponseModel<T> {

    private T _model;
    private String _instance;

    public T get_model() {
        return _model;
    }

    public String get_instance() {
        return _instance;
    }
    ResponseModel(){}
    ResponseModel(T model, String instance){
        _model = model;
        _instance = instance;
    }

}
