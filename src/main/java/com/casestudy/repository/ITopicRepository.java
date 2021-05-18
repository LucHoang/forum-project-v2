package com.casestudy.repository;

import com.casestudy.model.Topic;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ITopicRepository extends PagingAndSortingRepository<Topic,Long> {
}
