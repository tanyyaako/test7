package org.example.Repository;

import org.example.Domain.ServiceModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepositoryImpl extends GeneralRepository<ServiceModel,String> {
    @Query("SELECT s FROM ServiceModel s WHERE s.isActive = :isActive")
    List<ServiceModel> findByActive(@Param("isActive") Boolean isActive);
}
