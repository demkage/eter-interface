package com.eter.docker.repository;

import com.eter.docker.domain.Application;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by abosii on 4/14/2017.
 */
public interface ApplicationRepository extends CrudRepository<Application, Integer> {
}
