package com.casestudy.service.topic;

import com.casestudy.model.Category;
import com.casestudy.model.Topic;
import com.casestudy.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ITopicService extends IGeneralService<Topic> {
    Iterable<Topic> findAllByCategory(Category category);
    Page<Topic> findAll(Pageable pageable);
    Page<Topic> findAllByTitleContaining(String title, Pageable pageable);
    Page<Topic> findByCategoryCateId(Long cateId, Pageable pageable);
}
