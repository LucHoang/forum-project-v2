package com.casestudy.service.topic;

import com.casestudy.model.Category;
import com.casestudy.model.Topic;
import com.casestudy.repository.ITopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicService implements ITopicService {

    @Autowired
    private ITopicRepository topicRepository;

    @Override
    public Iterable<Topic> findAll() {
        return topicRepository.findAll();
    }

    @Override
    public Optional<Topic> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Topic topic) {
        topicRepository.save(topic);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Iterable<Topic> findAllByCategory(Category category) {
        return null;
    }
}
