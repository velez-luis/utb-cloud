package com.cloud.utb.project.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "task")
@Data
public class Task {

    @Id
    @Column(name = "id_task", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTask;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

}
