#ifndef ENEMY_H
#define ENEMY_H

#include <Tank.h>
#include <QTimer>

class Enemy: public Tank {
     Q_OBJECT

public:
    Enemy(Direction direction, int width, int height);
    ~Enemy();

private:
    QTimer* timerMove;
    QTimer* timerShoot;
    QTimer* timerDirection;

private slots:
    void moveEnemy();
    void shootEnemy();
    void setDirection();

};

#endif // ENEMY_H
