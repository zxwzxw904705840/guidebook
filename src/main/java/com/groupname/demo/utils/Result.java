package com.groupname.demo.utils;

public class Result<T> {
    private boolean success;
    private String message;
    private T object;

    public Result(boolean success,String message,T object){
        this.success=success;
        this.message=message;
        this.object=object;
    }
    public boolean isSuccess(){
        return this.success;
    }
    public String getMessage(){
        return this.message;
    }
    public T getObject(){
        return this.object;
    }
    public boolean hasObject(){
        if(object==null){
            return false;
        }
        return success;
    }

    @Override
    public String toString(){
        return this.success + " " + this.message;
    }
}
