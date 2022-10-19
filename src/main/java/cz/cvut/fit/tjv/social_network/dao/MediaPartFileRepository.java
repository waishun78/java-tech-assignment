/*
 * Copyright (c) 2022. Czech Technical University in Prague, Faculty of Information Technology.
 *
 * Project Social Network. Created for Java Technology course.
 *
 *  Authors:
 *  Ondřej Guth (ondrej.guth@fit.cvut.cz)
 *  Jan Blizničenko (jan.bliznicenko@fit.cvut.cz)
 *  Filip Glazar (glazafil@fit.cvut.cz)
 *
 *  This code is intended for educational purposes only.
 *
 */

package cz.cvut.fit.tjv.social_network.dao;

import cz.cvut.fit.tjv.social_network.domain.MediaPart;
import cz.cvut.fit.tjv.social_network.domain.MediaPartKey;

import java.nio.file.Path;

public class MediaPartFileRepository extends FileRepository<MediaPart, MediaPartKey> implements MediaPartRepository {
    public MediaPartFileRepository(Path storage) {
        super(MediaPart.class, storage);
    }
}
