package com.casestudy.service.reply;

import com.casestudy.model.Reply;
import com.casestudy.model.Topic;
import com.casestudy.repository.IReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReplyService implements IReplyService {
    @Autowired
    IReplyRepository replyRepository;

    @Override
    public Iterable<Reply> findAll() {
        return replyRepository.findAll();
    }

    @Override
    public Optional<Reply> findById(Long id) {
        return replyRepository.findById(id);
    }

    @Override
    public void save(Reply reply) {
        replyRepository.save(reply);
    }

    @Override
    public void remove(Long id) {
        replyRepository.deleteById(id);
    }

    @Override
    public Iterable<Reply> findAllByTopic(Topic topic) {
        return replyRepository.findAllByTopic(topic);
    }
}
