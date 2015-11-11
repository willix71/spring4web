package w.rest.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import w.model.Foo;
import w.rest.service.IFooService;

@Controller
@RequestMapping(value = "/foos")
public class FooController extends DbableController<Foo>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FooController.class);

    @Autowired
    public FooController(IFooService service) {
    	super(service, Foo.class);
    	
    	LOGGER.info("FooController initialized");
    }
}
