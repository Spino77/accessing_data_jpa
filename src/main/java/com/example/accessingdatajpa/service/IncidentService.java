package com.example.accessingdatajpa.service;

import com.example.accessingdatajpa.dto.CustomerDto;
import com.example.accessingdatajpa.dto.IncidentDto;
import com.example.accessingdatajpa.model.Incident;
import com.example.accessingdatajpa.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentService {
    @Autowired
    private IncidentRepository incidentRepository;

    public List<IncidentDto> getAllIncidents() {
        List<Incident> li = (List<Incident>) incidentRepository.findAll();
        List<IncidentDto> lidto = li.stream()
                .map(i -> new IncidentDto(i.getTitol(), i.getDescripcio(), i.getEstat(), i .getDataobertura()))
                .toList();
        return lidto;
    }
}
