package com.casestudy.service;

import com.casestudy.model.Category;
import com.casestudy.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ITopicService extends IGeneralService<Topic>{
    Iterable<Topic> findAllByCategory(Category category);
    Page<Topic> findAll(Pageable pageable);
    Page<Topic> findAllByTitleContaining(String title, Pageable pageable);
}
