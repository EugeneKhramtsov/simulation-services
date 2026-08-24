package uu.app.repositoryservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uu.app.repositoryservice.entity.DataEntity;

@Repository
public interface DataRepository extends CrudRepository<DataEntity, Long> {
}
