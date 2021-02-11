#ifndef BARRIER_H
#define BARRIER_H

#include <QObject>
#include <QGraphicsPixmapItem>

class Barrier: public QObject, public QGraphicsPixmapItem{
     Q_OBJECT
public:
    Barrier(int x, int y);
};

#endif // BARRIER_H
