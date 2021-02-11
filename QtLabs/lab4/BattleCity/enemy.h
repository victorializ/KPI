#ifndef ENEMY_H
#define ENEMY_H
#include <QGraphicsPixmapItem>
#include <QObject>
#include <directions.h>

class Enemy: public QObject, public QGraphicsPixmapItem{
    Q_OBJECT
public:
    Enemy();
    Direction direction = Down;
    int height;
    int width;

public slots:
    void moveEnemy();
    void shoot();
    void setDirection();

};

#endif // ENEMY_H
