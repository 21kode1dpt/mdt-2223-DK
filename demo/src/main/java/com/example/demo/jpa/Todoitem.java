package com.example.demo.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Todoitem implements Serializable {

    // Id
    @Id
    private String todo;
    private int priority = 2;

    public Todoitem() {
    }

    public Todoitem(String todo) {
        this.todo = todo;
    }

    public Todoitem(String todo, int priority) {
        this.todo = todo;
        this.priority = priority;
    }

    public void setTodo(String todo) {

        this.todo = todo;
    }

    public String getTodo() {
        return todo;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "TodoItem [todo=" + todo + ", priority=" + priority + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((todo == null) ? 0 : todo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null || todo == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Todoitem other = (Todoitem) obj;
        if (todo.equals(other.todo))
            return true;

        return false;
    }

}