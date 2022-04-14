package gethigh.fp_be.controller;

import gethigh.fp_be.model.Product;
import gethigh.fp_be.repository.ProductRepo;
import gethigh.fp_be.repository.StoreRepo;
import gethigh.fp_be.service.IProductService;
import gethigh.fp_be.service.IStoreCategoriesService;
import gethigh.fp_be.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/store")
public class StoreController {

    // khu vực thực hiện các tác vụ với store - huydu

    @Autowired
    IStoreCategoriesService iStoreCategoriesService;

    @Autowired
    IStoreService iStoreService;

    @Autowired
    StoreRepo storeRepo;

    @Autowired
    IProductService productService;

    // test phân quyền
    @Autowired
    ProductRepo productRepo;
    @GetMapping("/show")
    private String showPage(){
        return "  store page";
    }



    // show all product with store
    @GetMapping("{id}/showProduct")
    private ResponseEntity<?> showAllProductInStore(@PathVariable("id") Long id){
        Iterable<Product> products = productService.findAllByStore_Id(id);
        if (!products.iterator().hasNext()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    //search product in store
    @GetMapping("{id}/search/{name}")
    private ResponseEntity<?> searchProduct(@PathVariable("id") Long id, @Param("name") String name){
     Iterable<Product> products = productRepo.findAllByNameWithStore(id, name);
     if (!products.iterator().hasNext()){
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
     return new ResponseEntity<>(products,HttpStatus.OK);
    }
}
