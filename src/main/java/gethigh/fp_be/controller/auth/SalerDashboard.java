package gethigh.fp_be.controller.auth;

import gethigh.fp_be.dto.response.MessageResponse;
import gethigh.fp_be.model.Product;
import gethigh.fp_be.service.IProductImageService;
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
@RequestMapping("/saler/dashboard")
public class SalerDashboard {
//khu vực thực hiện các tác vụ quản lý với gian hàng - huydu

    // test phân quyền
    @GetMapping("/show")
    private String showDashboard(){
        return " Saler Dashboard";
    }

    @Autowired
    IProductService productService;

    @Autowired
    private IStoreService iStoreService;

    @Autowired
    private IProductImageService iProductImageService;

    //hiển thị tất cả product của 1 cửa hàng
    @GetMapping("/{id}")
    public ResponseEntity<Iterable<Product>> findAllProductsByStore(@PathVariable("id") Long id) {
        Iterable<Product> products = productService.findAllByStore_Id(id);
        if (products.iterator().hasNext()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id_store}/create-product")
    public ResponseEntity<?> createProduct(@PathVariable("id_store") Long id_store,
                                                 @RequestParam("product") Product product,
                                                 @RequestParam("file") MultipartFile file) {
        String message = "";
        product.setSoldQuantity(0);
        product.setStore(iStoreService.findById(id_store).get());
        try {
            iProductImageService.saveFile(file);
            productService.save(product);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return new ResponseEntity<>(new MessageResponse(message), HttpStatus.CREATED);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return new ResponseEntity<>(new MessageResponse(message), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iStoreService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
