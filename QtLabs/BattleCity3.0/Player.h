#ifndef PLAYER_H
#define PLAYER_H
#include <MovingPhysicalObject.h>
#include <QKeyEvent>

class Player : public MovingObject
{

public:
    Player(Direction direction, int x, int y);

    void collision_react(PhysicalObject *obj) override;
    void draw(QPainter *painter) override;
    //void move(Direction direction) override;
    //void move(QKeyEvent *event);

    void decreaseLifes();
    bool readyToShoot();
    void notShoot();
    int getLifes();
    void reactionToKeyPress(QKeyEvent *event);

private:
    int Lifes;
    bool shoot;
    void move(Direction direction);

};

#endif // PLAYER_H
