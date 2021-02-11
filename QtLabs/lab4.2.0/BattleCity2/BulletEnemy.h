#ifndef BULLETENEMY_H
#define BULLETENEMY_H

#include <Bullet.h>

class BulletEnemy: public Bullet {
    Q_OBJECT
public:
    BulletEnemy(Direction direction, int width, int height);
public slots:
    void moveEnemyBullet();
};

#endif // BULLETENEMY_H
