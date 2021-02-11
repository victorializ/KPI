#ifndef IRONBRICK_H
#define IRONBRICK_H
#include <PhysicalObject.h>

class IronBrick : public PhysicalObject
{
public:
    IronBrick(int x, int y);

    void draw(QPainter *painter) override;
};

#endif // IRONBRICK_H
