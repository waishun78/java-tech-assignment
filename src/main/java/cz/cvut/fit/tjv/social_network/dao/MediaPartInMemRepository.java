package cz.cvut.fit.tjv.social_network.dao;

import cz.cvut.fit.tjv.social_network.domain.MediaPart;
import cz.cvut.fit.tjv.social_network.domain.MediaPartKey;

public class MediaPartInMemRepository extends InMemoryRepository<MediaPart, MediaPartKey> implements MediaPartRepository {
}
