package com.example.accessingdatajpa.controller.rest;

import com.example.accessingdatajpa.dto.IncidentDto;
import com.example.accessingdatajpa.service.CustomerService;
import com.example.accessingdatajpa.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(IncidentResource.INCIDENTS)
public class IncidentResource {
    public static final String INCIDENTS = "/incidents";
    @Autowired
    IncidentService incidentService;

    @GetMapping
    public ResponseEntity<List<IncidentDto>> getIncidents() {
        return ResponseEntity.ok(incidentService.getAllIncidents());
    }
}