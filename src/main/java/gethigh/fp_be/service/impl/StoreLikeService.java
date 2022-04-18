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
    public Optional<StoreLike> findById(Long id) {
        return storeLikeRepo.findById(id);
    }

    @Override
    public StoreLike save(StoreLike storeLike) {
        return storeLikeRepo.save(storeLike);
    }

    @Override
    public void remove(Long id) {
        storeLikeRepo.deleteById(id);
    }

    @Override
    public Optional<StoreLike> findByStore_IdAndAccountLike_Id(Long store_id, Long account_id) {
        return storeLikeRepo.findByStore_IdAndAccountLike_Id(store_id, account_id);
    }

    @Override
    public Long countLikeStoreByStore_Id(Long store_id) {
        return storeLikeRepo.countLikeStoreByStore_Id(store_id);
    }

    @Override
    public Iterable<StoreLike> findAllByAccountLike_Id(Long id) {
        return storeLikeRepo.findAllByAccountLike_Id(id);
    }
}
