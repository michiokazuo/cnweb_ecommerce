package com.http.model;

public class Role {

    private Integer id;

    private String name;

    private String content;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id + '\'' +
                "name=" + name + '\'' +
                "content=" + content + '\'' +
                '}';
    }
}
