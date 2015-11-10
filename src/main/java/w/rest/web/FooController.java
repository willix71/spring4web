package w.rest.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.base.Preconditions;

import w.exception.MyResourceNotFoundException;
import w.model.Foo;
import w.rest.events.PaginatedResultsRetrievedEvent;
import w.rest.events.ResourceCreatedEvent;
import w.rest.events.SingleResourceRetrievedEvent;
import w.rest.service.IFooService;
import w.util.RestPreconditions;

@Controller
@RequestMapping(value = "/foos")
public class FooController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FooController.class);
	
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private IFooService service;

    public FooController() {
    	LOGGER.info("FooController initialized");
    }

    // API

    /**
     * curl -i http://localhost:8880/spring/rest/foos/1
     * @param id
     * @param response
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Foo findById(@PathVariable("id") final Long id, final HttpServletResponse response) {
        final Foo resourceById = RestPreconditions.checkFound(service.findOne(id));

        eventPublisher.publishEvent(new SingleResourceRetrievedEvent(this, response));
        return resourceById;
    }

    /**
     * curl -i http://localhost:8880/spring/rest/foos
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Foo> findAll() {
        return service.findAll();
    }

    @RequestMapping(params = { "page"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Foo> findPaginated(@RequestParam("page") final int page, @RequestParam(value="size", defaultValue="10") final int size, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
    	List<Foo> all = service.findAll();
		int fromIndex = page*size;
		if (fromIndex >= all.size()) {
			throw new MyResourceNotFoundException();			
		}
		int toIndex = fromIndex + size;
		if (toIndex > all.size()) toIndex = all.size();
		
		int totalPages = all.size() / size;
		if (all.size() % size > 0) totalPages++;
		
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent(this, uriBuilder.path("/foos"), response, size, page, totalPages));

        return all.subList(fromIndex, toIndex);
    }

    // write

    /**
     * curl -H "Content-Type: application/json" -X POST -d '{"name":"willy123"}' http://localhost:8880/spring/rest/foos
     *
     * or save the json to a file and replace it by @filename
     * 
     * or -d param=value&param2=value2
     * 
     * @param resource
     * @param response
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final Foo resource, final HttpServletResponse response) {
        Preconditions.checkNotNull(resource);
        final Long idOfCreatedResource = service.create(resource).getId();

        eventPublisher.publishEvent(new ResourceCreatedEvent(this, response, idOfCreatedResource));
    }

    /**
     * curl -H "Content-Type: application/json" -X PUT -d '{"id":"1","name":"willy123"}' http://localhost:8880/spring/rest/foos/1
     * 
     * @param id
     * @param resource
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") final Long id, @RequestBody final Foo resource) {
        Preconditions.checkNotNull(resource);
        RestPreconditions.checkFound(service.findOne(resource.getId()));
        service.update(resource);
    }

    /**
     * curl -i -X DELETE http://localhost:8880/spring/rest/foos/1
     * 
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") final Long id) {
        service.deleteById(id);
    }

}
