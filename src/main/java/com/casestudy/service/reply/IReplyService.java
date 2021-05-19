package com.casestudy.service.reply;


import com.casestudy.model.Reply;
import com.casestudy.model.Topic;
import com.casestudy.service.IGeneralService;

import java.util.Optional;


public interface IReplyService  {
    Iterable<Reply> findAll();

    Optional<Reply> findById(Long id);

    Reply save(Reply smartPhone);

    void remove(Long id);
    Iterable<Reply> findAllByTopic(Topic topic);
}
