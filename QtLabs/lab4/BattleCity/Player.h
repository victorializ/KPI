#ifndef MYRECT_H
#define MYRECT_H
#include <QGraphicsPixmapItem>
#include <QObject>
#include <directions.h>

class Player: public QObject, public QGraphicsPixmapItem{
     Q_OBJECT
public:
    Player();
    Direction directon = Up;
    //move the player using the keys
    void keyPressEvent(QKeyEvent* event);
    int height;
    int width;

public slots:
    void generateEnemies();

};

#endif // MYRECT_H
