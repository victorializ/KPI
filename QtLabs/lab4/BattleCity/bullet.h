#ifndef BULLET_H
#define BULLET_H
#include <QGraphicsPixmapItem>
#include <QObject>
#include <directions.h>

class Bullet: public QObject, public QGraphicsPixmapItem{
    Q_OBJECT
public:
    Bullet(Direction direction);

    //bullet flight direction
    Direction direction;
    int height;
    int width;

public slots:
    void moveBullet();

};

#endif // BULLET_H
