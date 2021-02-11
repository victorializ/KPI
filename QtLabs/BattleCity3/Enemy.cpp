#include "Enemy.h"
#include <widget.h>
#include <QRandomGenerator>

Enemy::Enemy(MovingObject::Direction direction, int x, int y) :MovingObject(direction, x, y)
{
    this->width = WIDTH/16;
    this->height = width;
    this->speed = 5;
    this->canMove = true;
    changeDirection();
}

void Enemy::collision_react(PhysicalObject *obj)
{
    canMove = false;
    if(dynamic_cast<MovingObject*>(obj) == nullptr){
        switch(direction) {
           case Up:
                this->y += 1;
                break;
           case Down:
                this->y -= 1;
                break;
            case Left:
                this->x += 1;
                break;
            case Right:
                this->x -= 1;
                break;
         }
        changeDirection();
    }
    else if(dynamic_cast<Enemy*>(obj) != nullptr) {
        reverseDirection();
        dynamic_cast<Enemy*>(obj)->reverseDirection();
    }
    else {
        reverseDirection();
    }
}

void Enemy::draw(QPainter *painter)
{
    switch(direction) {
       case Up:
            this->img = ":/images/EnemyU.png";
            break;
       case Down:
            this->img = ":/images/EnemyD.png";
            break;
        case Left:
            this->img = ":/images/EnemyL.png";
            break;
        case Right:
            this->img = ":/images/EnemyR.png";
            break;
    }
    QPixmap pixmap(img);
    painter->drawPixmap(x, y, width, height, pixmap);
}

void Enemy::changeDirection()
{
    canMove = true;

    switch(direction) {
       case Right:
            Rotation(Up);
            break;
       case Left:
           Rotation(Down);
            break;
        case Down:
            Rotation(Right);
            break;
        case Up:
           Rotation(Left);
           break;
    }
}

void Enemy::setRandomDirection()
{
    int i = QRandomGenerator::global()->bounded(4);
    switch(i) {
       case 0:
            Rotation(Right);
            break;
       case 1:
            Rotation(Left);
            break;
        case 2:
            Rotation(Up);
            break;
        case 3:
            Rotation(Down);
            break;

    }
}

void Enemy::reverseDirection()
{
    switch(direction) {
    case Up:
         Rotation(Down);
         break;
    case Down:
         Rotation(Up);
         break;
     case Left:
         Rotation(Right);
         break;
     case Right:
         Rotation(Left);
         break;
    }
}


void Enemy::move(Direction direction)
{
    if(direction == Up && y > 0){
            y = y - speed;
        }
       else if(direction == Down && y < HEIGHT - height) {
            y =  y + speed;
         }
        else if(direction == Left && x > 0) {
            x =  x - speed;
         }
        else if(direction == Right && x < WIDTH - width){
            x =  x + speed;
    }
    else {
        reverseDirection();
    }
}

