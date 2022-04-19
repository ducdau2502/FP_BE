package gethigh.fp_be.service;

import gethigh.fp_be.model.StoreRating;

import java.util.Optional;

public interface IStoreRatingService extends _IGeneralService<StoreRating> {

    Optional<StoreRating> findByAccount_IdAndStore_Id(Long account_id, Long store_id);
    Iterable<StoreRating> findAllByStore_Id(Long id);

}
