package w.rest.service;

import java.util.List;

import w.model.Dbable;

public interface IDbableService<T extends Dbable> {

	List<T> findAll();
	T findOne(final long id);
	T create(final T foo);
	T update(final T foo);
	void deleteById(final long id);
}
