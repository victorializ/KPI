#include "PhysicalObject.h"
#include "widget.h"

PhysicalObject::PhysicalObject(int x, int y)
{
    this->x = x;
    this->y = y;
    this->destroyed = false;
}

int PhysicalObject::getX()
{
    return x;
}

int PhysicalObject::getY()
{
    return y;
}

int PhysicalObject::getH()
{
    return height;
}

int PhysicalObject::getW()
{
    return width;
}

bool PhysicalObject::isDestroyed()
{
    return destroyed;
}

void PhysicalObject::destroy()
{
    destroyed = true;
}


