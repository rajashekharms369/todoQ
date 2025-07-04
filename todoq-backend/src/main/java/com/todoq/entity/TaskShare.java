package com.todoq.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "task_share")
@IdClass(TaskShareId.class)
public class TaskShare {
    /*
    * composite key class.
    */
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

/*
The ProjectShareId class must implement Serializable because:

JPA Specification Requirement

The JPA spec (JSR 338) mandates that composite key classes must be serializable.

This ensures the key can be safely stored in HTTP sessions, caches, or passed over RMI.

Hibernate / JPA Provider Needs

Hibernate (or other JPA providers) may serialize the key for:

Second-level caching

Detached entity state

Clustering support

Consistency in ORM Operations

Helps in equals() and hashCode() comparisons (required for entity identity).
*/
class TaskShareId implements java.io.Serializable {
    private Integer task;
    private Integer user;
}