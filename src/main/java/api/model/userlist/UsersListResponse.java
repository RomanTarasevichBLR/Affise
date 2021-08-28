package api.model.userlist;

import api.model.Pagination;
import api.model.user.User;
import lombok.Data;

@Data
public class UsersListResponse {
    private int status;
    private User[] users;
    private Pagination pagination;
}
