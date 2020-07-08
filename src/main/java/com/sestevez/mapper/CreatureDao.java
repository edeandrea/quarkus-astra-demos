package com.sestevez.mapper;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;
import io.smallrye.mutiny.Multi;

import java.util.Optional;

@Dao
public interface CreatureDao {

    @Update
    void update(Creature creature);

    @Select
    Optional<Creature> findById(String name);

    @Select
    Multi<Creature> findAll();
}
