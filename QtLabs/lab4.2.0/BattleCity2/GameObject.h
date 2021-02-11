#ifndef GAMEOBJECT_H
#define GAMEOBJECT_H

#include <QObject>
#include <QPainter>


class GameObject: public QObject {
     Q_OBJECT

public:
    enum Direction {Right, Left, Down, Up};

    Direction direction;
    int width, height;
    int x, y;
    QString img;

    GameObject(Direction direction, int width, int height);

protected:
    void Rotation(Direction direction, QString type);
    void move(Direction direction, int speed);
    int getI();
    int getJ();
};

#endif // GAMEOBJECT_H
