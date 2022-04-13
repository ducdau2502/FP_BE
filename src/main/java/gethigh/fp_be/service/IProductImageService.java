package gethigh.fp_be.service;

import gethigh.fp_be.model.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface IProductImageService extends _IGeneralService<ProductImage>{
    Iterable<ProductImage> getProductImageByProductId(Long id);
}
