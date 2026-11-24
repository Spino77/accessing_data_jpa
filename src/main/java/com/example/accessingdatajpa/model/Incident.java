package com.example.accessingdatajpa.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "incidents")
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titol;
    @Column()
    private String descripcio;
    @Column(name = "data_obertura", nullable = false)
    private LocalDateTime dataobertura = LocalDateTime.now();
    @Column(nullable = false)
    private String estat; // Obert, tancat o en progres

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Incident() {}

    public Incident(String titol, String descripcio, LocalDateTime dataobertura, String estat) {
        this.titol = titol;
        this.descripcio = descripcio;
        this.dataobertura = dataobertura;
        this.estat = estat;
    }

    public String getTitol() {
        return titol;
    }
    public String getDescripcio() {
        return descripcio;
    }
    public LocalDateTime getDataobertura() {
        return dataobertura;
    }
    public String getEstat() {
        return estat;
    }
    public void setTitol(String titol) {
        this.titol = titol;
    }
    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }
    public void setDataobertura(LocalDateTime dataobertura) {
        this.dataobertura = dataobertura;
    }
    public void setEstat(String estat) {
        this.estat = estat;
    }
}