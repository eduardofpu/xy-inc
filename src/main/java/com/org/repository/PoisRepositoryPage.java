package com.org.repository;

import com.org.model.Pois;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PoisRepositoryPage extends PagingAndSortingRepository<Pois, Long>{
}
