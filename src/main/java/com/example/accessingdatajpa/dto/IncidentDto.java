package com.example.accessingdatajpa.dto;

import java.time.LocalDateTime;

public record IncidentDto(
        String titol,
        String descripcio,
        String estat,
        LocalDateTime dataObertura
) { }