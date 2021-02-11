#ifndef BRICKSWALL_H
#define BRICKSWALL_H

#include <QObject>
#include <QGraphicsPixmapItem>
#include <Barrier.h>

class BricksWall: public Barrier{
     Q_OBJECT

public:
    BricksWall(int x, int y);
};


#endif // BRICKSWALL_H
