package com.casestudy.repository;

import com.casestudy.model.Category;
import com.casestudy.model.Reply;
import com.casestudy.model.Topic;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReplyRepository extends PagingAndSortingRepository<Reply,Long> {
    Iterable<Reply> findAllByTopic(Topic topic);
    int countReplyByTopic(Topic topic);
    Iterable<Reply> findAllByCommentId(Long id);
}
