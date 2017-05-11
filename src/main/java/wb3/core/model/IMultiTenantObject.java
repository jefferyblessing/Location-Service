package wb3.core.model;

public interface IMultiTenantObject {

    public void setTenant(String tenant);
    public String getTenant();
}
