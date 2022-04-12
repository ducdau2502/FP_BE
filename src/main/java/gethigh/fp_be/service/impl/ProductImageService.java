package gethigh.fp_be.service.impl;


import gethigh.fp_be.model.ProductImage;
import gethigh.fp_be.repository.ProductImageRepo;
import gethigh.fp_be.service.IProductImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ProductImageService implements IProductImageService {
    @Autowired
    ProductImageRepo productImageRepo;


    @Override
    public ProductImage saveFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        ProductImage productImage = new ProductImage(fileName,file.getContentType(),file.getBytes());
        return productImageRepo.save(productImage);
    }

    @Override
    public Optional<ProductImage> getFile(Long id) {
        return productImageRepo.findById(id);
    }

    @Override
    public Stream<ProductImage> getALLFile() {
        return productImageRepo.findAll().stream();
    }
}
