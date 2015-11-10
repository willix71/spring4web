package w.rest.service;

import java.util.List;

import w.model.Foo;

public interface IFooService {
	
	List<Foo> findAll();
	Foo findOne(final long id);
	Foo create(final String name);
	Foo create(final Foo foo);
	Foo update(final Foo foo);
	void deleteById(final long id);
}
