package gethigh.fp_be.service;

import gethigh.fp_be.model.StoreLike;

import java.util.Optional;

public interface IStoreLikeService extends _IGeneralService<StoreLike> {
    Optional<StoreLike> findByStore_IdAndAccountLike_Id(Long store_id, Long account_id);

    Long countLikeStoreByStore_Id(Long store_id);

    Iterable<StoreLike> findAllByAccountLike_Id(Long id);
}
