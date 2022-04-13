package gethigh.fp_be.service;

import gethigh.fp_be.dto.response.TopStoreSale;
import gethigh.fp_be.model.Store;

import java.util.List;

public interface IStoreService extends _IGeneralService<Store> {
    Iterable<TopStoreSale> topStoreSale();

    Iterable<Store> findAllByNameContaining(String name);

    List<Store> findStoreByName (String name);// tim kiem user
}
