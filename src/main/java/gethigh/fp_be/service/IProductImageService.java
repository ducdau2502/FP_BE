package gethigh.fp_be.service;

import gethigh.fp_be.model.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

public interface IProductImageService  {
    ProductImage saveFile(MultipartFile file) throws IOException;
    Optional<ProductImage> getFile(Long id);
    Stream<ProductImage> getALLFile();
}
