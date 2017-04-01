/*
 * Copyright 2015-2020 uuzu.com All right reserved.
 */
package com.msun.jrest.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author zxc Apr 1, 2017 4:52:10 PM
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends CrudRepository<User, Long> {

}
