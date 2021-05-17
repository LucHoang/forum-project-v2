package com.casestudy.repository;

import com.casestudy.model.Topic;
import com.casestudy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITopicRepository extends JpaRepository<Topic, Long> {

}
