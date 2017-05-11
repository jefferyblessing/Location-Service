package wb3.core.model;

import org.springframework.data.annotation.Id;

/**
 * This DTO contains behaviors which allow the encapsulation of application tenant information
 */
public abstract class MultiTenantDTO extends UserIdentifiedDTO implements IMultiTenantObject {

    private String tenant;

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

}
