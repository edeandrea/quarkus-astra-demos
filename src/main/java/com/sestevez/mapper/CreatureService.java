package com.sestevez.mapper;

import io.smallrye.mutiny.Multi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class CreatureService {

    @Inject
    CreatureDao dao;

    public void save(Creature creature) {
        dao.update(creature);
    }

    public Optional<Creature> get(String id) {
        return dao.findById(id);
    }

    public Multi<Creature> getAll() {
        return dao.findAll();
    }
}
