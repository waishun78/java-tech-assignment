/*
 * Project Social Network. Created for Java Technology course at Czech Technical University in Prague,
 * Faculty of Information Technology.
 *
 * Author: Ondřej Guth (ondrej.guth@fit.cvut.cz)
 *
 * This code is intended for educational purposes only.
 */

package cz.cvut.fit.tjv.social_network.business;

import cz.cvut.fit.tjv.social_network.dao.UserInMemRepository;
import cz.cvut.fit.tjv.social_network.dao.UserRepository;
import cz.cvut.fit.tjv.social_network.domain.User;

/**
 * Business logic operations related to User domain type.
 */
public class UserService extends AbstractCrudService<User, String> {
    public UserService(UserRepository userRepository) {
        super(userRepository);
    }
}
