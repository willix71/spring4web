package w.rest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import w.model.Dbable;
import w.rest.service.IDbableService;

public abstract class SimpleDbableService<T extends Dbable> implements IDbableService<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleDbableService.class);
	
	private final Map<Long,T> storage = new HashMap<Long,T>();
	
	private long counter = 0;
		
    public SimpleDbableService() {
		LOGGER.info("SimpleDbableService initialized");
    }
   
   	@Override
	public List<T> findAll() {
		return new ArrayList<T>(storage.values());
	}

	@Override
	public T findOne(final long id) {
		return storage.get(id);
	}

	@Override
	public T create(final T t) {
		if (t.getId()<=0) t.setId(++counter);
		if (t.getVersion()<=0) t.setVersion(1l);
		
		storage.put(t.getId(), t);
		return t;
	}

	@Override
	public T update(T newT) {
		T oldT = storage.put(newT.getId(), newT);
		if (oldT != null) {
			newT.setVersion(Math.max(oldT.getVersion(),newT.getVersion())+1);	
		}
		return newT;
	}

	@Override
	public void deleteById(long id) {
		storage.remove(id);
	}
  
}
