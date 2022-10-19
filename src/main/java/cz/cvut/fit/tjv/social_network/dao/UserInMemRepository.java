package cz.cvut.fit.tjv.social_network.dao;

import cz.cvut.fit.tjv.social_network.domain.User;

public class UserInMemRepository extends InMemoryRepository<User, String> implements UserRepository {
}
