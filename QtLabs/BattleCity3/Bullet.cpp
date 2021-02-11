#include "Bullet.h"
#include "widget.h"
#include "Bricks.h"
#include "IronBrick.h"
#include "Base.h"

Bullet::Bullet(MovingObject::Direction direction, int x, int y):MovingObject(direction, x, y)
{
    this->width = WIDTH/167;
    this->height = HEIGHT/75;
    this->speed = 10;
    this->canMove = true;
    switch(direction) {
       case Up:
            this->x = x + 32 - 7;
            this->y = y - 15;
            break;
       case Down:
            this->x = x + 32 - 7;
            this->y = y + 60;
            break;
        case Left:
            this->x = x - 15;
            this->y = y + 32 - 10;
            break;
        case Right:
            this->x = x + 64;
            this->y = y + 32 - 7;
            break;
    }
}


void Bullet::collision_react(PhysicalObject *obj)
{
    this->destroyed = true;
    if(dynamic_cast<IronBrick*>(obj) == nullptr &&
            dynamic_cast<Player*>(obj) == nullptr){
        obj->destroy();
    }
}

void Bullet::draw(QPainter *painter)
{
    switch(direction) {
       case Up:
            this->img = ":/images/BulletU.png";
            break;
       case Down:
            this->img = ":/images/BulletD.png";
            break;
        case Left:
            this->img = ":/images/BulletL.png";
            break;
        case Right:
            this->img = ":/images/BulletR.png";
            break;
    }
    QPixmap pixmap(img);
    painter->drawPixmap(x, y, pixmap);
}

void Bullet::move(Direction direction)
{
    if(objectOutOfFrame() == true){
        destroyed = true;
    }
    else {
        MovingObject::move(direction);
    }
}


