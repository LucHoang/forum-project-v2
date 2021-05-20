package com.casestudy.service.hastag;

import com.casestudy.model.Hastag;
import com.casestudy.repository.IHastagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class HastagService implements IHastagService{
    @Autowired
    private IHastagRepository hastagRepository;

    @Override
    public Iterable<Hastag> findAll() {
        return hastagRepository.findAll();
    }

    @Override
    public Optional<Hastag> findById(Long id) {
        return hastagRepository.findById(id);
    }

    @Override
    public void save(Hastag hastag) {
        hastagRepository.save(hastag);
    }

    @Override
    public void remove(Long id) {
        hastagRepository.deleteById(id);
    }

    @Override
    public Iterable<Hastag> getTheMostUsedHashtags() {
       return hastagRepository.getTheMostUsedHashtags();
    }

    public Hastag saveAndReturn(Hastag hastag){
        return hastagRepository.save(hastag);
    }

    public static void main(String[] args) {
        HastagService hastagService = new HastagService();
        Hastag hastag = hastagService.saveAndReturn(new Hastag("testhastag"));
        System.out.println(hastag.getHastagId());
    }
}
