package gethigh.fp_be.service;

import gethigh.fp_be.model.ProductFeedback;

public interface IProductFeedbackService extends _IGeneralService<ProductFeedback> {
    Iterable<ProductFeedback> findAllByProductFeedback_Id(long id);

    String countFeedbackByIdProduct(Long id);
}
