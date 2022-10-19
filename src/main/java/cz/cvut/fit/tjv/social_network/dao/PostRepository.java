package cz.cvut.fit.tjv.social_network.dao;

import cz.cvut.fit.tjv.social_network.domain.Post;

import java.util.Collection;

public interface PostRepository extends CrudRepository<Post, Long> {
    Collection<Post> findByLikedGreaterThan(int likes);

    Collection<Post> findAllByAuthorUsername(String userId);
}
