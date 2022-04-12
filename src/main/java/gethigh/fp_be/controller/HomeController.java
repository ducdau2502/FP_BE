package gethigh.fp_be.controller;

import gethigh.fp_be.model.Product;
import gethigh.fp_be.model.Store;
import gethigh.fp_be.service.IProductService;
import gethigh.fp_be.service.IStoreService;
import gethigh.fp_be.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gethigh.fp_be.dto.response.MessageResponse;
import gethigh.fp_be.model.Store;
import gethigh.fp_be.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    IProductService productService;

    // test phân quyền
    @GetMapping("/show")
    private String showPage(){
        return " Home page";
    }

    @GetMapping("/find_store/{id}")
    public ResponseEntity<?> findStoreById(@PathVariable("id") long id) {
        Optional<Store> storeOptional = storeService.findById(id);
        if (storeOptional.isPresent()) {
            return new ResponseEntity<>(storeOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("store not found"), HttpStatus.NOT_FOUND);
        }
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
    @GetMapping("/search/{name}")
    private ResponseEntity<?> searchProduct(@Param("name") String name){
        Iterable<Product> products = productService.findByName(name);
        if (!products.iterator().hasNext()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }


}
