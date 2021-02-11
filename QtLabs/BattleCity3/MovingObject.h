#ifndef MOVINGOBJECT_H
#define MOVINGOBJECT_H
#include <PhysicalObject.h>
#include <QPainter>
#include "QKeyEvent"

class MovingObject : public PhysicalObject
{

public:
    enum Direction {Right, Left, Down, Up};

    MovingObject(Direction direction, int x, int y);

    virtual void collision_react(PhysicalObject *obj) = 0;
    virtual void move(Direction direction);
    void draw(QPainter *painter) override;

    Direction getDirection();
    int getSpeed();

protected:
    Direction direction;
    bool canMove;
    int speed;
    void Rotation(Direction direction);
    bool objectOutOfFrame();
};

#endif // MOVINGOBJECT_H
