package io.dropwizard.resources;

import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.api.Saying;
import io.dropwizard.jersey.caching.CacheControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("hello-world")
@Produces(MediaType.APPLICATION_JSON)
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class HelloWorldResource {

    private static final Logger log = LoggerFactory.getLogger(HelloWorldResource.class);

    private final String template;
    private final String defaultName;
    private final AtomicLong id;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.id = new AtomicLong();
    }

    @GET
    @Timed(name = "get-requests-timed")
    @Metered(name = "get-requests-metered")
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String content = String.format(template, name.orElse(defaultName));
        return new Saying(id.incrementAndGet(), content);
    }

    @POST
    @Timed
    public void receiveHello(Saying saying) {
        log.info("Received a saying: {}", saying);
    }

    @GET
    @Path("/date")
    @Produces(MediaType.TEXT_PLAIN)
    public String receiveDate(@QueryParam("date") Optional<String> dateParam) {
        if (dateParam.isPresent()) {
            final String actualDateTime = dateParam.get();
            log.info("Receive a date: {}", actualDateTime);
            return actualDateTime;
        } else {
            log.warn("No received date");
            return null;
        }
    }
}
