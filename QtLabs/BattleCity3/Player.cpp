#include "Player.h"
#include "widget.h"
#include "QDebug"

Player::Player(Direction direction, int x, int y):MovingObject(direction, x, y)
{
    this->width = WIDTH/16;
    this->height = width;
    this->canMove = true;
    this->Lifes = 3;
    this->speed = 5;
}



void Player::collision_react(PhysicalObject *obj)
{
  if(dynamic_cast<MovingObject*>(obj) == nullptr){
       canMove = false;
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
   }
}

void Player::draw(QPainter *painter)
{
    switch(direction) {
       case Up:
            this->img = ":/images/PlayerU.png";
            break;
       case Down:
            this->img = ":/images/PlayerD.png";
            break;
        case Left:
            this->img = ":/images/PlayerL.png";
            break;
        case Right:
            this->img = ":/images/PlayerR.png";
            break;
    }
    QPixmap pixmap(img);
    painter->drawPixmap(x, y, width, height, pixmap);
}

void Player::move(MovingObject::Direction direction) {Q_UNUSED(direction)}

void Player::move(QKeyEvent *event)
{
    if(objectOutOfFrame() == true) {
        canMove = false;
    }
    switch(event->key()) {
       case Qt::Key_Left:
            this->MovingObject::move(MovingObject::Left);
            break;
       case Qt::Key_Right:
            this->MovingObject::move(MovingObject::Right);
            break;
        case Qt::Key_Up:
            this->MovingObject::move(MovingObject::Up);
            break;
        case Qt::Key_Down:
            this->MovingObject::move(MovingObject::Down);
            break;
        case Qt::Key_Space:
            this->shoot = true;
            break;
    }
}

void Player::decreaseLifes()
{
    Lifes--;
}

bool Player::readyToShoot()
{
    return shoot;
}

void Player::notShoot()
{
    shoot = false;
}

int Player::getLifes()
{
    return Lifes;
}
