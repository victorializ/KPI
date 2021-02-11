#include "MovingPhysicalObject.h"
#include "widget.h"
#include "QKeyEvent"

MovingObject::MovingObject(Direction direction, int x, int y): PhysicalObject (x, y)
{
    this->direction = direction;
}

void MovingObject::draw(QPainter *painter) {Q_UNUSED(painter)}

void MovingObject::move(MovingObject::Direction direction)
{
   //Rotation(direction);
   //if(canMove == false) return;
    if(!objectOutOfFrame()){
        if(direction == Up){
                y = y - speed;
        }
        else if(direction == Down) {
                y =  y + speed;
        }
        else if(direction == Left) {
                x =  x - speed;
        }
        else if(direction == Right){
                x =  x + speed;
        }
    }
}

MovingObject::Direction MovingObject::getDirection()
{
    return direction;
}

int MovingObject::getSpeed()
{
    return speed;
}

void MovingObject::Rotation(MovingObject::Direction direction)
{
    if(this->direction != direction) {
        this->canMove = true;
        this->direction = direction;
    }
}

bool MovingObject::objectOutOfFrame()
{
    if((direction == Up && y <= 0) ||
      (direction == Down && y >= HEIGHT - height)||
      (direction == Left && x <= 0) ||
       (direction == Right && x >= WIDTH - width)) {
         this -> canMove = false;
         return true;
    }
    else {
        return false;
    }
}



