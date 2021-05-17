package com.casestudy.service;

import com.casestudy.model.Category;
import com.casestudy.model.Topic;
import com.casestudy.repository.ITopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicService implements ITopicService{
@Autowired
    ITopicRepository topicRepository;
    @Override
    public Iterable<Topic> findAllByCategory(Category category) {
        return topicRepository.findAllByCategory(category);
    }

    @Override
    public Page<Topic> findAll(Pageable pageable) {
        return topicRepository.findAll(pageable);
    }

    @Override
    public Page<Topic> findAllByTitleContaining(String title, Pageable pageable) {
        return topicRepository.findAllByTitleContaining(title,pageable);
    }

    @Override
    public Iterable<Topic> findAll() {
        return null;
    }

    @Override
    public Optional<Topic> findById(Long id) {
        return topicRepository.findById(id);
    }

    @Override
    public void save(Topic topic) {
        topicRepository.save(topic);
    }

    @Override
    public void remove(Long id) {
        topicRepository.deleteById(id);
    }
}
