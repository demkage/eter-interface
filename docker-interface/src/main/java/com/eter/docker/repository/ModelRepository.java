package com.eter.docker.repository;

import com.eter.docker.domain.Model;
import org.springframework.data.repository.CrudRepository;


public interface ModelRepository extends CrudRepository<Model, Long> {
}
