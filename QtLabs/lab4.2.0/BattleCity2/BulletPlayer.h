#ifndef PLAYERBULLET_H
#define PLAYERBULLET_H

#include <Bullet.h>

class BulletPlayer: public Bullet {
     Q_OBJECT
public:
    BulletPlayer(Direction direction, int width, int height);
public slots:
    void moveBulletPlayer();
};

#endif // PLAYERBULLET_H
