package com.nevernote.api.nevernoteapi.repositories;

import com.nevernote.api.nevernoteapi.models.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TagRepository extends CrudRepository<Tag, Long> {

}