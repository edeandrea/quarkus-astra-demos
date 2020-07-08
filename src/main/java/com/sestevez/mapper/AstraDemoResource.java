package com.sestevez.mapper;

import io.smallrye.mutiny.Multi;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/creatures")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AstraDemoResource {

    @Inject
    CreatureService creatureService;

    @GET
    @Path("/{name}")
    public Optional<CreatureDto> get(@PathParam("name") String name) {
        return creatureService.get(name).map(CreatureDto::fromEntity);
    }

    @GET
    public Multi<CreatureDto> get() {
        return creatureService.getAll().map(CreatureDto::fromEntity);
    }

    @POST
    public void add(CreatureDto creature) {
        creatureService.save(creature.toEntity());
    }
}
