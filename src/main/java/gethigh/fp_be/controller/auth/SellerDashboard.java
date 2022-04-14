package gethigh.fp_be.controller.auth;

import gethigh.fp_be.dto.request.ImageForm;
import gethigh.fp_be.model.Product;
import gethigh.fp_be.model.ProductImage;
import gethigh.fp_be.model.Store;
import gethigh.fp_be.model.StoreCategories;
import gethigh.fp_be.service.IProductImageService;
import gethigh.fp_be.service.IProductService;
import gethigh.fp_be.service.IStoreService;
import gethigh.fp_be.service.impl.StoreCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private IStoreService iStoreService;

    @Autowired
    private IProductImageService iProductImageService;

    @Autowired
    StoreCategoriesService storeCategoriesService;

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

    //tìm sản phẩm theo tên
    @GetMapping("/find/{name}")
    public ResponseEntity<Iterable<Product>> findByName(@RequestParam("name") String name) {
        Iterable<Product> products = productService.findByName(name);
        if (products.iterator().hasNext()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //tìm tất cả ảnh của một sản phẩm
    @GetMapping("/search-product-image/{id}")
    public ResponseEntity<Iterable<ProductImage>> findProductImagesByProductId(@PathVariable("id") Long id) {
        Iterable<ProductImage> productImages = iProductImageService.findAllByProductId(id);
        if (productImages.iterator().hasNext()) {
            return new ResponseEntity<>(productImages, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //sửa sản phẩm
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id,
                                           @RequestBody Product productUpdate) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productUpdate.setId(product.get().getId());
        productUpdate.setSoldQuantity(product.get().getSoldQuantity());
        productUpdate.setStore(product.get().getStore());
        productUpdate = productService.save(productUpdate);
        return new ResponseEntity<>(productUpdate, HttpStatus.OK);
    }

    //thêm ảnh cho sản phẩm
    @PostMapping("/create-image")
    public ResponseEntity<ProductImage> saveProductImage(@RequestBody ProductImage productImage) {
        ProductImage productImageCreate = iProductImageService.save(productImage);
        return new ResponseEntity<>(productImageCreate, HttpStatus.OK);
    }

    //sửa ảnh sản phẩm
    @PutMapping("/{id}/update-image")
    public ResponseEntity<ProductImage> updateProductImage(@PathVariable("id") Long id,
                                                           @RequestBody ProductImage productImageUpdate) {
        Optional<ProductImage> productImage = iProductImageService.findById(id);
        if (!productImage.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productImageUpdate.setId(productImage.get().getId());
        productImageUpdate = iProductImageService.save(productImageUpdate);
        return new ResponseEntity<>(productImageUpdate, HttpStatus.OK);
    }

    //-----------------------------------------------------------------------------------

    //Làm việc với image-product
    @PostMapping("/save-image")
    public ResponseEntity<?> saveImage(@RequestBody ProductImage image) {
//        for (int i = 0; i < imageForm.getImageList().size(); i++) {
//            iProductImageService.save(new ProductImage(imageForm.getImageList().get(i), imageForm.getProduct()));
//        }

        return new ResponseEntity<>(iProductImageService.save(image), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-image/{id}")
    public void deleteImage(@PathVariable("id") Long id) {
        iProductImageService.remove(id);
    }

    //Tìm ảnh của sản phẩm
    @GetMapping("/get-image/{id_product}")
    public ResponseEntity<?> getImage(@PathVariable("id_product") Long id_product) {
        Optional<ProductImage> productImage = iProductImageService.findByProduct_Id(id_product);
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
        Store store = iStoreService.findById(id_store).get();
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
}