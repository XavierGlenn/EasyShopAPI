package org.yearup.configurations;

import org.springframework.stereotype.Component;
import org.yearup.data.UserDao;

import java.security.Principal;

@Component
public class UserHelper {

    public int getUserId(Principal principal) {
        if (principal == null) {
            throw new IllegalArgumentException("Principal cannot be null");
        }

        @Component
        class User {

            UserDao userDao;

            public void UserHelper(UserDao userDao) {
                this.userDao = userDao;
            }

            public int getUserId(Principal principal) {
                if (principal == null) {
                    throw new IllegalArgumentException("Principal cannot be null");
                }
                // get the currently logged-in username
                String userName = principal.getName();
                // find database user by userId
                org.yearup.models.User user = userDao.getByUserName(userName);

                if (user == null) {
                    throw new RuntimeException("No user found with username: " + userName);
                }

                return user.getId();
            }
        }
        return 0;
    }
}
