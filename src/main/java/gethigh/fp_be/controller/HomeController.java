package gethigh.fp_be.controller;

import gethigh.fp_be.dto.response.TopStoreSale;
import gethigh.fp_be.model.*;
import gethigh.fp_be.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gethigh.fp_be.dto.response.MessageResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private IStoreService storeService;
// khu vực thực hiện các logic với trang chủ - huydu

    @Autowired
    IStoreService iStoreService;

    @Autowired
    IAccountDetailService accountDetailService;

    @Autowired
    IProductService productService;

    @Autowired
    IProductImageService productImageService;

    @Autowired
    IStoreRatingService ratingService;

    @Autowired
    IProductFeedbackService productFeedbackService;

    @Autowired
    IStoreCategoriesService categoriesService;

    // test phân quyền
    @GetMapping("/show")
    private String showPage(){
        return " Home page";
    }

    // xem thông tin cửa hàng
    @GetMapping("/find-store/{id}")
    public ResponseEntity<?> findStoreById(@PathVariable("id") long id) {
        Optional<Store> storeOptional = storeService.findById(id);
        if (storeOptional.isPresent()) {
            return new ResponseEntity<>(storeOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("store not found"), HttpStatus.NOT_FOUND);
        }
    }

    // tìm kiếm cửa hàng theo category
    @GetMapping("/find-store-by-category/{id}")
    public ResponseEntity<?> findAllByCategoriesList_Id(@PathVariable("id") long id) {
        Iterable<Store> stores = storeService.findAllByCategoriesList_Id(id);
        if (stores.iterator().hasNext()) {
            return new ResponseEntity<>(stores, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("store not found"), HttpStatus.NOT_FOUND);
        }
    }

    // get all category
    @GetMapping("/list-category")
    public ResponseEntity<?> getAllCategories() {
        Iterable<StoreCategories> categories = categoriesService.findAll();
        if (categories.iterator().hasNext()) {
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("store not found"), HttpStatus.NOT_FOUND);
        }
    }

    //Tìm ảnh của sản phẩm
    @GetMapping("/get-image/{id_product}")
    public ResponseEntity<?> getImage(@PathVariable("id_product") Long id_product) {
        Optional<ProductImage> productImage = productImageService.findByProduct_Id(id_product);
        if (!productImage.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productImage, HttpStatus.OK);
    }

    //tìm tất cả sản phẩm của cửa hàng
    @GetMapping("/{id}")
    public ResponseEntity<?> findAllProductByStoreId(@PathVariable("id") Long id) {
        Iterable<Product> products = productService.findAllByStore_Id(id);
        if (products.iterator().hasNext()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //tìm kiếm 1 sán phẩm
    @GetMapping("/find-product/{id}")
    public ResponseEntity<?> findProductById(@PathVariable("id") long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("product not found"), HttpStatus.NOT_FOUND);
        }
    }

    // tìm kiếm cửa hàng theo tên gần đúng
    @GetMapping("/search-stores")
    private ResponseEntity<?> findAllStoreByNameContaining(@RequestParam("search-store") String searchStore) {
        Iterable<Store> storeIterable = storeService.findAllByNameContaining(searchStore);
        if (storeIterable.iterator().hasNext()) {
            return new ResponseEntity<>(storeIterable, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("stores not found"), HttpStatus.NOT_FOUND);
        }
    }

    // tìm kiếm sản phẩm theo khoảng giá
    @GetMapping("/search-products/{lower}/{upper}")
    private ResponseEntity<?> findAllProductByPriceIsGreaterThanEqualAndPriceIsLessThanEqual(@PathVariable("lower") Double lower, @PathVariable("upper") Double upper) {
        Iterable<Product> products = productService.findAllByPriceIsGreaterThanEqualAndPriceIsLessThanEqual(lower, upper);
        if (products.iterator().hasNext()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("products not found"), HttpStatus.NOT_FOUND);
        }
    }

    // feedback 1 sản phẩm
    @PostMapping("/feed-back")
    private ResponseEntity<ProductFeedback> createFeedback(@RequestBody ProductFeedback productFeedback) {
        return new ResponseEntity<>(productFeedbackService.save(productFeedback), HttpStatus.CREATED);
    }

    @GetMapping("/feed-back/count-fb/{id}")
    private ResponseEntity<?> countFeedback(@PathVariable("id") long id) {
        return new ResponseEntity<>(productFeedbackService.countFeedbackByIdProduct(id), HttpStatus.CREATED);
    }

    // show feedback theo sản phẩm
    @GetMapping("/feed-back/{id}")
    private ResponseEntity<?> showFeedbackByProduct(@PathVariable("id") Long id) {
        Iterable<ProductFeedback> productFeedbacks = productFeedbackService.findAllByProductFeedback_Id(id);
        return new ResponseEntity<>(productFeedbacks, HttpStatus.OK);
    }

    //show all store
    @GetMapping("/showAllStore")
    private ResponseEntity<Iterable<Store>> showAllStore(){
        Iterable<Store> stores = iStoreService.findAll();
        if (!stores.iterator().hasNext()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    //show all product
    @GetMapping("/showAllProduct")
    private ResponseEntity<?> showALLProduct(){
        Iterable<Product> products = productService.findAll();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    //search with product name
    @GetMapping("/search")
    private ResponseEntity<?> searchProduct(@RequestParam("name") String name){
        Iterable<Product> products = productService.findByName(name);
        if (!products.iterator().hasNext()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    //Top 3 sản phẩm được bán nhiều nhất
    @GetMapping("/top-product-sale")
    private ResponseEntity<?> topProductSale() {
        Iterable<Product> products = productService.topProductSale();
        if (!products.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    //Hiển thị 2 cửa hàng bán được nhiều sản phẩm nhất
    @GetMapping("/top-store-sale")
    private ResponseEntity<?> topStoreSale() {
        Iterable<Store> stores = iStoreService.topStoreSale();
        if (!stores.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @GetMapping("/detail-account/{id}")
    public ResponseEntity<AccountDetail> findAccountById(@PathVariable("id") Long id) {
        Optional<AccountDetail> accountDetail = accountDetailService.findById(id);
        if (accountDetail.isPresent()) {
            return new ResponseEntity<>(accountDetail.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/detail-store/{id}")
    public ResponseEntity<Store> findStoreById(@PathVariable("id") Long id) {
        Optional<Store> store = storeService.findStoreByStoreOwner_Id(id);
        if (store.isPresent()) {
            return new ResponseEntity<>(store.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //rating
    @GetMapping("/find-rating/{store_id}")
    public ResponseEntity<Iterable<StoreRating>> findByStore_Id(@PathVariable("store_id") Long store_id) {
        Iterable<StoreRating> storeRatings = ratingService.findAllByStore_Id(store_id);
        return new ResponseEntity<>(storeRatings, HttpStatus.OK);
    }
}
