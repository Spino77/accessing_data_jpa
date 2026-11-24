package com.example.accessingdatajpa.repository;

import com.example.accessingdatajpa.model.Incident;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends CrudRepository<Incident, Long> {

}
