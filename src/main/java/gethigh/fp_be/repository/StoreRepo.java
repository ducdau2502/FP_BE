package gethigh.fp_be.repository;

import gethigh.fp_be.dto.response.TopStoreSale;
import gethigh.fp_be.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepo extends JpaRepository<Store,Long> {
    //Hiển thị 2 cửa hàng bán được nhiều sản phẩm nhất
    @Query(nativeQuery = true, value = "select stores.id from shopbi.products\n" +
            "join shopbi.stores on products.store_id = stores.id\n" +
            "group by store_id order by sum(products.sold_quantity) desc limit 2;")
    List<Long> topStoreSale();

    Iterable<Store> findAllByNameContaining(String name);

    Iterable<Store> findAllByCategoriesList_Id(Long id);

    Optional<Store> findStoreByStoreOwner_Id(Long id);
}
