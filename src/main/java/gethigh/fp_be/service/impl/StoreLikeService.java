package gethigh.fp_be.service.impl;

import gethigh.fp_be.model.StoreLike;
import gethigh.fp_be.repository.StoreLikeRepo;
import gethigh.fp_be.service.IStoreLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreLikeService implements IStoreLikeService {
    @Autowired
    StoreLikeRepo storeLikeRepo;

    @Override
    public Iterable<StoreLike> findAll() {
        return storeLikeRepo.findAll();
    }

    @Override
    public void save(StoreLike storeLike) {
        storeLikeRepo.save(storeLike);
    }

    @Override
    public void delete(Long id) {
        storeLikeRepo.deleteById(id);
    }

    @Override
    public Optional<StoreLike> findById(Long id) {
        return storeLikeRepo.findById(id);
    }
}
