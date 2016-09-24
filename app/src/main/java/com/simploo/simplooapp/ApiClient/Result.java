package com.simploo.simplooapp.ApiClient;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.simploo.simplooapp.DataModel.Todo;

public class Result {

    @SerializedName("todos")
    @Expose
    private List<Todo> todos = new ArrayList<Todo>();

    /**
     *
     * @return
     * The todos
     */
    public List<Todo> getTodos() {
        return todos;
    }

    /**
     *
     * @param todos
     * The todos
     */
    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }



}