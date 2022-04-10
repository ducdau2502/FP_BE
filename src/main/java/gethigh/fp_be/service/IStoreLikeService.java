package gethigh.fp_be.service;

import gethigh.fp_be.model.StoreLike;
import gethigh.fp_be.model.StoreRating;
import java.util.Optional;

public interface IStoreLikeService {
    Iterable<StoreLike> findAll();
    void save(StoreLike storeLike);
    void delete(Long id);
    Optional<StoreLike> findById(Long id);
}
