package com.simploo.simplooapp.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Todo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("completed")
    @Expose
    private Boolean completed;

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

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The completed
     */
    public Boolean getCompleted() {
        return completed;
    }

    /**
     *
     * @param completed
     * The completed
     */
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

}