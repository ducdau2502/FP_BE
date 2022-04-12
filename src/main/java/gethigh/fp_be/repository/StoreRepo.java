package gethigh.fp_be.repository;

import gethigh.fp_be.dto.response.TopStoreSale;
import gethigh.fp_be.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepo extends JpaRepository<Store,Long> {
    //Hiển thị 2 cửa hàng bán được nhiều sản phẩm nhất
    @Query(nativeQuery = true, value = "select stores.id, stores.name, stores.avatar, sum(sold_quantity) sumQ from shopbi.products \n" +
            "join stores on products.store_id = stores.store_owner_id\n" +
            "group by store_id order by sumQ desc limit 2;")
    Iterable<TopStoreSale> topStoreSale();

    Iterable<Store> findAllByNameContaining(String name);
}
