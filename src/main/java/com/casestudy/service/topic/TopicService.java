package com.casestudy.service.topic;

import com.casestudy.model.Topic;
import com.casestudy.repository.ITopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TopicService implements ITopicService{
    @Autowired
    private ITopicRepository iTopicRepository;

    @Override
    public Iterable<Topic> findAll() {
        return iTopicRepository.findAll();
    }

    @Override
    public Optional<Topic> findById(Long id) {
        return iTopicRepository.findById(id);
    }

    @Override
    public void save(Topic topic) {
        iTopicRepository.save(topic);
    }

    @Override
    public void remove(Long id) {
        iTopicRepository.deleteById(id);
    }
}
