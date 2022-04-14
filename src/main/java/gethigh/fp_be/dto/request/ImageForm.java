package gethigh.fp_be.dto.request;

import gethigh.fp_be.model.Product;

import java.util.List;

public class ImageForm {
    List<String> imageList;
    Product product;

    public ImageForm(List<String> imageList, Product product) {
        this.imageList = imageList;
        this.product = product;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
