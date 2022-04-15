package gethigh.fp_be.service;

import gethigh.fp_be.dto.response.TopStoreSale;
import gethigh.fp_be.model.Store;

import java.util.Optional;

public interface IStoreService extends _IGeneralService<Store> {
    Iterable<Store> topStoreSale();

    Iterable<Store> findAllByNameContaining(String name);

    Iterable<Store> findAllByCategoriesList_Id(Long id);

    Optional<Store> findStoreByStoreOwner_Id(Long id);
}
