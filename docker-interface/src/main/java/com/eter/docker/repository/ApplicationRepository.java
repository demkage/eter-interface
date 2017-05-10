package com.eter.docker.repository;

import com.eter.docker.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by abosii on 4/14/2017.
 */
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Application findOneByName(String name);

    boolean existsByName(String name);
}
