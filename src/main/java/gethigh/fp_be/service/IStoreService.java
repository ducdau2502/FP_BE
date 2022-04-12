package gethigh.fp_be.service;

import gethigh.fp_be.model.Store;

import java.util.Optional;

public interface IStoreService extends _IGeneralService<Store> {
    Iterable<Store> findAllByNameContaining(String name);
}
