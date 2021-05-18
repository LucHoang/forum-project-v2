package com.casestudy.service.topic;

import com.casestudy.model.Category;
import com.casestudy.model.Topic;
import com.casestudy.service.IGeneralService;

public interface ITopicService extends IGeneralService<Topic> {
    Iterable<Topic> findAllByCategory(Category category);
}
