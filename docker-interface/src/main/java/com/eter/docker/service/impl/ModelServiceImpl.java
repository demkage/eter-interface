package com.eter.docker.service.impl;

import com.eter.docker.domain.Model;
import com.eter.docker.repository.ModelRepository;
import com.eter.docker.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class ModelServiceImpl implements ModelService {

    private ModelRepository modelRepository;

    @Autowired
    public void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public List<Model> getAllModels() {
        return createModelListFromIterable(modelRepository.findAll());
    }

    @Override
    public List<Model> getCompleteModels() {
        return null;
    }

    @Override
    public Model findModel(String name) {
        Iterator<Model> iterator = modelRepository.findAll().iterator();

        Optional<Model> model = Optional.empty();

        while (iterator.hasNext()) {
            Model current = iterator.next();

            if(current.getName().equals(name)) {
                model = Optional.of(current);
                break;
            }
        }

        return model.orElse(new Model());
    }

    @Override
    public String getModelPath(String name) {
        return null;
    }

    @Override
    public boolean isModelComplete(String name) {
        return false;
    }

    @Override
    public boolean isModelInProcessing(String name) {
        return false;
    }


    private List<Model> createModelListFromIterable(Iterable<Model> models) {
        List<Model> result = new ArrayList<>();
        models.forEach(result::add);
        return result;
    }
}
