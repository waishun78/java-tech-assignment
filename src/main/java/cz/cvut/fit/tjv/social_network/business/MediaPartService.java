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

package cz.cvut.fit.tjv.social_network.business;

import cz.cvut.fit.tjv.social_network.dao.MediaPartInMemRepository;
import cz.cvut.fit.tjv.social_network.dao.MediaPartRepository;
import cz.cvut.fit.tjv.social_network.domain.MediaPart;
import cz.cvut.fit.tjv.social_network.domain.MediaPartKey;

public class MediaPartService extends AbstractCrudService<MediaPart, MediaPartKey> {
    public MediaPartService(MediaPartRepository mediaPartRepository) {
        super(mediaPartRepository);
    }
}
