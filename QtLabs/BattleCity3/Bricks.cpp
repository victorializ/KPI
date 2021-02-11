#include "Bricks.h"
#include <widget.h>
#include <QDebug>

Bricks::Bricks(int x, int y): PhysicalObject (x, y)
{
    this->width = WIDTH/26;
    this->height = HEIGHT/28;
}

void Bricks::draw(QPainter *painter)
{
    this->img = ":/images/bricks.png";
    QPixmap pixmap(img);
    painter->drawPixmap(x, y, width, height, pixmap);
}
