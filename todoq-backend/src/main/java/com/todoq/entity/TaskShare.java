package com.todoq.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "task_share")
@IdClass(TaskShareId.class)
public class TaskShare {
    @Id
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "access_type", length = 50, nullable = false)
    private String accessType;
}

class TaskShareId implements java.io.Serializable {
    private Integer task;
    private Integer user;
}