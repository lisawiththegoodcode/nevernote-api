package com.nevernote.api.nevernoteapi.repositories;

import com.nevernote.api.nevernoteapi.models.Notebook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface NotebookRepository extends CrudRepository<Notebook, Long> {
}
