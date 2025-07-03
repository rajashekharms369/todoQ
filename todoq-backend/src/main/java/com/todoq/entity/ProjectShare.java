package com.todoq.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "project_share")
@IdClass(ProjectShareId.class)
public class ProjectShare {
    @Id
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "access_type", length = 50, nullable = false)
    private String accessType;
}

class ProjectShareId implements java.io.Serializable {
    private Integer project;
    private Integer user;
}