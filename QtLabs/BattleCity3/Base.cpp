#include "Base.h"
#include "widget.h"

Base::Base(int x, int y): PhysicalObject (x, y)
{
    this->width = WIDTH/12;
    this->height = HEIGHT/14;
    //this->shotDown = false;
}

void Base::draw(QPainter *painter)
{
    this->img = ":/images/base.png";
    QPixmap pixmap(img);
    painter->drawPixmap(x, y, width, height, pixmap);
}
