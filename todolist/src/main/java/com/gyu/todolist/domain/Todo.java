package com.gyu.todolist.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table
public class Todo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -947585423656694361L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Column
    private LocalDateTime createdDateTime;

    @Column
    private Boolean isComplete;


    public Todo() {
        super();
    }



    @Builder
    public Todo(Long id, String content, LocalDateTime createdDateTime, Boolean isComplete) {
        this.id = id;
        this.content = content;
        this.createdDateTime = createdDateTime;
        this.isComplete = isComplete;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Boolean complete) {
        isComplete = complete;
    }

    @Override
    public String toString() {
        return "Todo [id=" + id +
                ", content=" + content +
                ", createdDateTime=" + createdDateTime +
                ", isComplete=" + isComplete + "]";
    }
}