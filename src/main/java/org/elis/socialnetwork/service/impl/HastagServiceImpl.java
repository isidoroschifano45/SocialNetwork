package org.elis.socialnetwork.service.impl;

import lombok.RequiredArgsConstructor;
import org.elis.socialnetwork.model.Hastag;
import org.elis.socialnetwork.repository.HastagRepository;
import org.elis.socialnetwork.service.HastagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HastagServiceImpl implements HastagService {

    private final HastagRepository hastagRepo;

    @Override
    public List<Hastag> findAll() {
        return hastagRepo.findAll();
    }
}
