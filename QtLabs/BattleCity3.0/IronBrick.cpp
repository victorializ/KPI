#include "IronBrick.h"
#include "widget.h"

IronBrick::IronBrick(int x, int y): PhysicalObject (x, y)
{
    this->width = WIDTH/26;
    this->height = HEIGHT/28;
}


void IronBrick::draw(QPainter *painter)
{
    this->img = ":/images/wall.png";
    QPixmap pixmap(img);
    painter->drawPixmap(x, y, width, height, pixmap);
}
