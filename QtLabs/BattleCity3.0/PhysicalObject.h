#ifndef PHYSICALOBJECT_H
#define PHYSICALOBJECT_H
#include <QString>
#include <QPainter>

class PhysicalObject {

public:
    PhysicalObject(int x, int y);
    virtual ~PhysicalObject() = default;

    int getX();
    int getY();
    int getH();
    int getW();

    virtual void draw(QPainter *painter) = 0;
    bool isDestroyed();
    void destroy();

protected:
    int width, height;
    int x, y;
    QString img;
    bool destroyed;
};



#endif // PHYSICALOBJECT_H
