#ifndef CONCRETEWALL_H
#define CONCRETEWALL_H

#include <QObject>
#include <QGraphicsPixmapItem>
#include <Barrier.h>

class ConcreteWall: public Barrier{
     Q_OBJECT

public:
    ConcreteWall(int x, int y);
};


#endif // CONCRETEWALL_H
