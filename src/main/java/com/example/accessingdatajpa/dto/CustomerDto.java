package com.example.accessingdatajpa.dto;

import com.example.accessingdatajpa.model.Incident;

import java.util.List;

public record CustomerDto(
        String first_name,
        String last_name,
        String email,
        List<Incident> incidents
) {}