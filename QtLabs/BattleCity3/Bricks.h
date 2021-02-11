#ifndef BRICKS_H
#define BRICKS_H
#include <PhysicalObject.h>

class Bricks: public PhysicalObject
{
public:
    Bricks(int x, int y);

    void draw(QPainter *painter) override;
};

#endif // BRICKS_H
