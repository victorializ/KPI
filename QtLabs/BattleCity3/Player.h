#ifndef PLAYER_H
#define PLAYER_H
#include <MovingObject.h>
#include <QKeyEvent>

class Player : public MovingObject
{

public:
    Player(Direction direction, int x, int y);

    void collision_react(PhysicalObject *obj) override;
    void draw(QPainter *painter) override;
    void move(Direction direction) override;
    void move(QKeyEvent *event);

    void decreaseLifes();
    bool readyToShoot();
    void notShoot();
    int getLifes();

private:
    int Lifes;
    bool shoot;
};

#endif // PLAYER_H
