package wb3.core.model;

import org.springframework.data.annotation.Id;

/**
 * The base class for all DTOs which contains their most basic behaviours.
 */
public abstract class BaseDTO implements IIdentifiableObject {

    @Id
    private String id;

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }
}
