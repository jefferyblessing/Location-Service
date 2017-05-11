package wb3.core.model;

/**
 * This DTO contains behaviors which allow the encapsulation of user information
 */
public abstract class UserIdentifiedDTO extends BaseDTO {

    private String userId;

    public String getUserId() { return userId; }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
