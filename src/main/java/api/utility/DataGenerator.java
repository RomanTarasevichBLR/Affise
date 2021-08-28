package api.utility;

import api.model.user.User;
import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class DataGenerator {

    private static final Faker FAKER = new Faker();

    public User generateUserDataOnlyRequiredFieldsWithRoles(List<String> roles) {
        User user = new User();
        user.setEmail(FAKER.internet().emailAddress());
        user.setPassword(FAKER.internet().password(6, 10));
        user.setFirst_name(FAKER.name().firstName());
        user.setLast_name(FAKER.name().lastName());
        user.setRoles(roles);
        return user;
    }

    public User generateUserDataOnlyRequiredFields() {
        User user = new User();
        user.setEmail(FAKER.internet().emailAddress());
        user.setPassword(FAKER.internet().password(6, 20));
        user.setFirst_name(FAKER.name().firstName());
        user.setLast_name(FAKER.name().lastName());
        return user;
    }


}
