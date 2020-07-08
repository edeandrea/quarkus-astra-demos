package com.sestevez.cql;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.quarkus.runtime.api.session.QuarkusCqlSession;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class AstraDemoCQL {

    @Inject
    QuarkusCqlSession cqlSession;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        ResultSet rs = cqlSession
                .execute("SELECT release_version FROM system.local");

        Row row = rs.one();
        String version = row.getString(0);
        return "hello Cassandra version " + version;
    }


}