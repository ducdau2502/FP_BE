package gethigh.fp_be.repository;

import gethigh.fp_be.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Iterable<Product> findAllByNameContaining(String name);

    @Query(nativeQuery = true,value = "SELECT * FROM stores_product_list \n" +
            "join products on stores_product_list.store_id = products.id \n" +
            "where products.name like concat('%',:name,'%') \n" +
            "and stores_product_list.store_id = :id")
    Iterable<Product> findAllByNameWithStore(Long id, String name);

    Iterable<Product> findAllByStore_Id(Long id);

    Iterable<Product> findAllByPriceIsGreaterThanEqualAndPriceIsLessThanEqual(Double lowerPrice, Double upperPrice);

    //Hiển thị 3 sản phẩm được bán nhiều nhất
    @Query(nativeQuery = true, value = "select * from products order by products.sold_quantity desc limit 3;")
    Iterable<Product> topProductSale();

    Iterable<Product> findAllByStore_IdAndNameContaining(Long id, String name);
}
