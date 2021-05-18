package com.casestudy.service.reply;


import com.casestudy.model.Reply;
import com.casestudy.model.Topic;
import com.casestudy.service.IGeneralService;


public interface IReplyService extends IGeneralService<Reply> {
    Iterable<Reply> findAllByTopic(Topic topic);
}
