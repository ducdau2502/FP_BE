package gethigh.fp_be.dto.request;

import gethigh.fp_be.model.AccountDetail;
import gethigh.fp_be.model.StoreCategories;

import java.util.List;
import java.util.Set;

public class AcceptRequest {
    private Long idAcc;
    private Set<String> role;


    public Long getIdAcc() {
        return idAcc;
    }

    public void setIdAcc(Long idAcc) {
        this.idAcc = idAcc;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
