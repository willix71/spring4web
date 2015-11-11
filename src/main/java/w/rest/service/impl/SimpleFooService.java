package w.rest.service.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import w.model.Dbable;
import w.model.Foo;
import w.rest.service.IFooService;

@Service
public class SimpleFooService extends SimpleDbableService<Foo> implements IFooService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleFooService.class);
	
    public SimpleFooService() {
		super();
		
		LOGGER.info("SimpleFooService initialized");
    }
   
    @PostConstruct
    public void init() {
    	LOGGER.info("Creating initial Foos");
    	
		String[] names = {"willy","ralph","laurent","franck","olivier","patrick","marc","diana","sandra"};
		for (String name : names) create(name);
    }

	public Dbable create(final String name) {
		return create(new Foo(name));
	}

}
