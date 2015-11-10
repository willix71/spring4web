package w.rest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import w.model.Foo;
import w.rest.service.IFooService;

@Service
public class SimpleFooService implements IFooService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleFooService.class);
	
	private static final Map<Long,Foo> storage = new HashMap<Long,Foo>();
	
	private static long counter = 0;
		
    public SimpleFooService() {
		LOGGER.info("SimpleFooService initialized");
    }
   
    @PostConstruct
    public void init() {
    	LOGGER.info("Creating initial Foos");
    	
		String[] names = {"willy","ralph","laurent","franck","olivier","patrick","marc","diana"};
		for (String name : names) create(name);
    }
    
   	@Override
	public List<Foo> findAll() {
		return new ArrayList<Foo>(storage.values());
	}

	@Override
	public Foo findOne(final long id) {
		return storage.get(id);
	}

	@Override
	public Foo create(final String name) {
		Foo foo = new Foo(++counter, name);
		storage.put(foo.getId(), foo);
		return foo;
	}

	@Override
	public Foo create(final Foo foo) {
		if (foo.getId()==0) foo.setId(++counter);
		storage.put(foo.getId(), foo);
		return foo;
	}

	@Override
	public Foo update(Foo foo) {
		Foo f = storage.get(foo.getId());
		if (f!=null) {
			f.setName(foo.getName());
		}
		return f;
	}

	@Override
	public void deleteById(long id) {
		storage.remove(id);
	}

}
