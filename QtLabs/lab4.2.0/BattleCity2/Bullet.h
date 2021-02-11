#ifndef BULLET_H
#define BULLET_H

#include <GameObject.h>
#include <QTimer>
#include <Game.h>


class Bullet: public GameObject {
     Q_OBJECT

public:
    Bullet(Direction direction, int width, int height);
    void moveBullet();


protected:
    QTimer* timer;

private:
    void hitWall(int currElem, int nextElem);

};

#endif // BULLET_H
