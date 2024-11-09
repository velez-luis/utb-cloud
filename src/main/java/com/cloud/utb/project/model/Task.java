package com.cloud.utb.project.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Data
public class Task {

    @Id
    @Column(name = "id_task", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTask;

    @Column(name = "fecha")
    private LocalDateTime fecha= LocalDateTime.now();;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "completado")
    private Boolean completado;

    public Long getIdTask() {
        return idTask;
    }

    public void setIdTask(Long idTask) {
        this.idTask = idTask;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }

    // Constructor sin parámetros (agregado explícitamente para Hibernate)
    public Task() {
        // Constructor vacío necesario para Hibernate
    }

    public Task(Long idTask, LocalDateTime fecha, String descripcion, Boolean completado) {
        this.idTask = idTask;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.completado = completado;
    }
}
