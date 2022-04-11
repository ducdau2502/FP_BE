package gethigh.fp_be.dto.request;

import java.util.Set;

public class AcceptRequest {

    private Set<String> role;

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
