package gethigh.fp_be.controller;

import gethigh.fp_be.model.Product;
import gethigh.fp_be.model.Store;
import gethigh.fp_be.service.IProductService;
import gethigh.fp_be.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/auth/product")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @Autowired
    private IStoreService iStoreService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll() {
        Iterable<Product> products = iProductService.findAll();
        if (!products.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/{idStore}")
    public ResponseEntity<Product> createProduct(@PathVariable("idStore") Long idStore,
                                                 @RequestParam("product") Product product,
                                                 @RequestParam("file") MultipartFile file) {
        Optional<Store> store = iStoreService.findById(idStore);
        product.setSoldQuantity(0);
        product.setStore(store);

    }
}
