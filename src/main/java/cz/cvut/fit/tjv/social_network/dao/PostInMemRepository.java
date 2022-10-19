package cz.cvut.fit.tjv.social_network.dao;

import cz.cvut.fit.tjv.social_network.domain.Post;

import java.util.Collection;

public class PostInMemRepository extends InMemoryRepository<Post, Long> implements PostRepository {
    @Override
    public Collection<Post> findByLikedGreaterThan(int likes) {
        return findAll().stream().filter(p -> p.getLiked().size() > likes).toList();
    }

    @Override
    public Collection<Post> findAllByAuthorUsername(String userId) {
        return findAll().stream().filter(p -> p.getAuthor().getUsername().equals(userId)).toList();
    }
}
