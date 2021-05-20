package com.casestudy.repository;

import com.casestudy.model.Category;
import com.casestudy.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;




@Repository
public interface ITopicRepository extends PagingAndSortingRepository<Topic,Long> {
    @Query(nativeQuery = true,value = "select * from topics t order by topic_like desc limit 5")
    Iterable<Topic> findTopByTopicLike();
    Iterable<Topic> findAllByCategory(Category category);
    Page<Topic> findAllByTitleContaining(String firstname, Pageable pageable);
    Page<Topic> findByCategoryCateId(Long cateId,Pageable pageable);
}
