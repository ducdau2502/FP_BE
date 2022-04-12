package gethigh.fp_be.dto.request;

import gethigh.fp_be.model.AccountDetail;
import gethigh.fp_be.model.StoreCategories;

import java.util.List;
import java.util.Set;

public class AcceptRequest {

    private Set<String> role;
    private String nameStore;
    String description;

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
