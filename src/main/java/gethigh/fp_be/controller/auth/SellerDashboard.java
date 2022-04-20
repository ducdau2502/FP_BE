package gethigh.fp_be.controller.auth;

import gethigh.fp_be.dto.response.RevenueTime;
import gethigh.fp_be.model.*;
import gethigh.fp_be.service.*;
import gethigh.fp_be.service.impl.StoreCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("seller/dashboard")
public class SellerDashboard {
// Khu vực thực hiện các tác vụ quản lý với gian hàng - huydu

    //test phân quyền
    @GetMapping("/show")
    private String showDashboard(){
        return " Seller Dashboard";
    }

    @Autowired
    IProductService productService;

    @Autowired
    private IStoreService storeService;

    @Autowired
    private IVoucherService voucherService;

    @Autowired
    private IBillService billService;

    @Autowired
    private IProductImageService productImageService;

    @Autowired
    StoreCategoriesService storeCategoriesService;


    @PostMapping("/updateStore/{id}")
    public ResponseEntity<?> saveInformationStore(@PathVariable("id") Long id,@RequestBody Store store){
        Store newStore = storeService.findById(id).get();
        newStore.setName(store.getName());
        newStore.setDescription(store.getDescription());
        newStore.setAvatar(store.getAvatar());
        storeService.save(newStore);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //hiển thị tất cả product của 1 cửa hàng
    @GetMapping("/{id}")
    public ResponseEntity<?> findAllProducts(@PathVariable("id") Long id) {
        Iterable<Product> products = productService.findAllByStore_Id(id);
        if (products.iterator().hasNext()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //hiển thị tất cả danh mục đang có sẵn
    @GetMapping("/find-category")
    public ResponseEntity<Iterable<StoreCategories>> findAllStoreCategories() {
        Iterable<StoreCategories> storeCategories = storeCategoriesService.findAll();
        if (storeCategories.iterator().hasNext()) {
            return new ResponseEntity<>(storeCategories, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //tìm sản phẩm theo tên và cửa hàng
    @GetMapping("/find-by-name/{id_store}")
    public ResponseEntity<Iterable<Product>> findByNameAndStore(@PathVariable("id_store") Long id_store,
                                                                @RequestParam("search_product") String search) {
        Iterable<Product> products = productService.findAllByStore_IdAndNameContaining(id_store, search);
        if (products.iterator().hasNext()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //tìm tất cả ảnh của một sản phẩm
    @GetMapping("/search-product-image/{id}")
    public ResponseEntity<Iterable<ProductImage>> findProductImagesByProductId(@PathVariable("id") Long id) {
        Iterable<ProductImage> productImages = productImageService.findAllByProductId(id);
        if (productImages.iterator().hasNext()) {
            return new ResponseEntity<>(productImages, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //sửa sản phẩm
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id,
                                           @RequestBody Product productUpdate) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productUpdate.setId(product.get().getId());
        productUpdate.setSoldQuantity(product.get().getSoldQuantity());
        productUpdate.setStore(product.get().getStore());
        if (productUpdate.getCoverImage() == null) {
            productUpdate.setCoverImage(product.get().getCoverImage());
        }
        productUpdate = productService.save(productUpdate);
        return new ResponseEntity<>(productUpdate, HttpStatus.OK);
    }

    //thêm ảnh cho sản phẩm
    @PostMapping("/create-image")
    public ResponseEntity<ProductImage> saveProductImage(@RequestBody ProductImage productImage) {
        ProductImage productImageCreate = productImageService.save(productImage);
        return new ResponseEntity<>(productImageCreate, HttpStatus.OK);
    }

    //sửa ảnh sản phẩm
    @PutMapping("/{id}/update-image")
    public ResponseEntity<ProductImage> updateProductImage(@PathVariable("id") Long id,
                                                           @RequestBody ProductImage productImageUpdate) {
        Optional<ProductImage> productImage = productImageService.findById(id);
        if (!productImage.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productImageUpdate.setId(productImage.get().getId());
        productImageUpdate = productImageService.save(productImageUpdate);
        return new ResponseEntity<>(productImageUpdate, HttpStatus.OK);
    }

    //-----------------------------------------------------------------------------------

    //Làm việc với image-product
    @PostMapping("/save-image")
    public ResponseEntity<?> saveImage(@RequestBody ProductImage image) {
//        for (int i = 0; i < imageForm.getImageList().size(); i++) {
//            iProductImageService.save(new ProductImage(imageForm.getImageList().get(i), imageForm.getProduct()));
//        }

        return new ResponseEntity<>(productImageService.save(image), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-image/{id}")
    public void deleteImage(@PathVariable("id") Long id) {
        productImageService.remove(id);
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

    //Làm việc với product
    @PostMapping("/create-product/{id_store}")
    public ResponseEntity<?> createProduct(@PathVariable("id_store") Long id_store,
                                           @RequestBody Product product) {
        product.setSoldQuantity(0);
        Store store = storeService.findById(id_store).get();
        product.setStore(store);
        Product productCreate = productService.save(product);
        return new ResponseEntity<>(productCreate, HttpStatus.CREATED);
    }

    //xóa 1 sản phẩm
    //xử lý git
    @DeleteMapping("/delete-product/{id_product}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id_product") Long id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/product/{id_product}")
    public ResponseEntity<Product> getProduct(@PathVariable("id_product") Long id) {
        Optional<Product> productOptional = productService.findById(id);
        return productOptional.map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Gán category cho store
    @GetMapping("/store/{id_store}/{id_category}")
    public ResponseEntity<Store> saveCategory(@PathVariable("id_store") Long id_store, @PathVariable("id_category") Long id_category) {
        Optional<Store> store = storeService.findById(id_store);
        Optional<StoreCategories> category = storeCategoriesService.findById(id_category);
        if (!store.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        store.get().setCategoriesList(category.get());
        return new ResponseEntity<>(storeService.save(store.get()), HttpStatus.OK);
    }

    //tạo category cho store
    @PostMapping("/create-category")
    public ResponseEntity<StoreCategories> create(@RequestBody StoreCategories category) {
        return new ResponseEntity<>(storeCategoriesService.save(category), HttpStatus.OK);
    }

    // doanh thu theo thời gian
    @PostMapping("/revenue/{id_store}")
    public ResponseEntity<Map<String, Object>> revenue(@PathVariable("id_store") Long id_store, @RequestBody RevenueTime revenue) {
        Iterable<Bill> bills = billService.findAllByDateCreateBetweenAndStore_Id(revenue.getStart(), revenue.getEnd(), id_store);
        double totalRevenue = 0;
        for(Bill bill : bills) {
            totalRevenue += bill.getTotalPrice();
        }
        Map<String, Object> response = new HashMap<>();
        response.put("bills", bills);
        response.put("totalRevenue", totalRevenue);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/total-revenue/{id_store}")
    public ResponseEntity<Map<String, Object>> totalRevenue(@PathVariable("id_store") Long id_store) {
        Iterable<Bill> bills = billService.findAllByStore_Id(id_store);
        double totalRevenue = 0;
        for(Bill bill : bills) {
            totalRevenue += bill.getTotalPrice();
        }
        Map<String, Object> response = new HashMap<>();
        response.put("bills", bills);
        response.put("totalRevenue", totalRevenue);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/find-voucher/{id_store}")
    public ResponseEntity<?> findAllByStore_Id(@PathVariable("id_store") Long id_store) {
        Iterable<Voucher> vouchers = voucherService.findAllByStore_Id(id_store);
        return new ResponseEntity<>(vouchers, HttpStatus.OK);
    }

    @PostMapping("/create-voucher")
    public ResponseEntity<?> createVoucher(@RequestBody Voucher voucher) {
        return new ResponseEntity<>(voucherService.save(voucher), HttpStatus.OK);
    }

    @DeleteMapping("/delete-voucher/{id}")
    public ResponseEntity<?> createVoucher(@PathVariable("id") Long id) {
        Optional<Voucher> voucherOptional = voucherService.findById(id);
        if (!voucherOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            voucherService.remove(id);
            return new ResponseEntity<>(voucherOptional.get(), HttpStatus.OK);
        }
    }
}