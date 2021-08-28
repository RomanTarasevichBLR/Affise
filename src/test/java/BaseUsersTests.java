import api.model.user.User;
import api.model.userlist.UsersListResponse;
import api.request.UserRequest;
import ui.page.users.usersmanagement.ProfilePage;
import ui.page.users.usersmanagement.UserManagementPage;

import java.util.*;

public class BaseUsersTests extends BaseTests {

    protected UserRequest userRequest = new UserRequest();
    protected UserManagementPage userManagementPage;
    protected ProfilePage profilePage;

    protected Map<String, Object> prepareParamsForNewUserWithRequired(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", user.getEmail());
        params.put("password", user.getPassword());
        params.put("first_name", user.getFirst_name());
        params.put("last_name", user.getLast_name());
        params.put("roles[]", user.getRoles());
        return params;
    }

    private Map<String, Object> prepareParamsForPaginationUsersList(int page) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        return params;
    }

    // documentation was checked and as a possible case we can use here parameter "limit=500" to get users on one page
    // but if we have more than 500 users anyway we will run through all the pages to store all the user entries
    public List<User> getAllListUsers() {
        int perPage = response.as(UsersListResponse.class).getPagination().getPer_page();
        int totalCount = response.as(UsersListResponse.class).getPagination().getTotal_count();
        int totalNumberOfPages = totalCount % perPage == 0 ? totalCount / perPage : totalCount / perPage + 1;
        List<User> allUsers = new ArrayList<>();
        for(int i = 1; i <= totalNumberOfPages; i++) {
            response = userRequest.returnListOfAllUsersFromPage(prepareParamsForPaginationUsersList(i));
            List<User> users = Arrays.asList(response.as(UsersListResponse.class).getUsers());
            allUsers.addAll(users);
        }
        return allUsers;
    }

}
