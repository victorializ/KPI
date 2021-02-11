#ifndef BASE_H
#define BASE_H
#include <PhysicalObject.h>

class Base: public PhysicalObject
{
public:
    Base(int x, int y);

    void draw(QPainter *painter) override;
};

#endif // BASE_H
