package com.spring.restapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.restapi.model.model;

@Service
public class CrudService {

	static List<model> tutorials = new ArrayList<model>();
	static long id = 0;

	public List<model> findAll() {
		return tutorials;
	}

	public List<model> findByTitleContaining(String title) {
		return tutorials.stream().filter(tutorial -> tutorial.getTitle().contains(title)).toList();
	}

	public model findById(long id) {
		return tutorials.stream().filter(tutorial -> id == tutorial.getId()).findAny().orElse(null);
	}

	public model save(model tutorial) {
		// update Tutorial
		if (tutorial.getId() != 0) {
			long _id = tutorial.getId();

			for (int idx = 0; idx < tutorials.size(); idx++)
				if (_id == tutorials.get(idx).getId()) {
					tutorials.set(idx, tutorial);
					break;
				}

			return tutorial;
		}

		// create new Tutorial
		tutorial.setId(++id);
		tutorials.add(tutorial);
		return tutorial;
	}

	public void deleteById(long id) {
		tutorials.removeIf(tutorial -> id == tutorial.getId());
	}

	public void deleteAll() {
		tutorials.removeAll(tutorials);
	}

	public List<model> findByPublished(boolean isPublished) {
		return tutorials.stream().filter(tutorial -> isPublished == tutorial.isPublished()).toList();
	}
}
