#include <iostream>
#include <GameObject.h>
#include <Game.h>

GameObject::GameObject(Direction direction, int width, int height) {
    this->direction = direction;
    this->width = width;
    this->height = height;
}

void GameObject::Rotation(Direction direction, QString type) {
    this->direction = direction;
    switch(direction) {
       case Up:
            this->img = ":/images/" + type + "U.png";
            break;
       case Down:
            this->img = ":/images/" + type + "D.png";
            break;
        case Left:
            this->img = ":/images/" + type + "L.png";
            break;
        case Right:
            this->img = ":/images/" + type + "R.png";
            break;
    }
}

void GameObject::move(Direction direction, int speed) {
    switch(direction) {
       case Up:
            y = y - speed;
            break;
       case Down:
            y =  y + speed;
            break;
        case Left:
            x =  x - speed;
            break;
        case Right:
            x =  x + speed;
            break;
    }
}

int GameObject::getI() {
    int i = int(y / 32);
    if(y / 32 - i > 0.5) {
        i = i + 1;
    }
    return i;
}

int GameObject::getJ() {
    int j = int(x / 32);
    if(x / 32 - j > 0.5) {
        j = j + 1;
    }
    return j;
}

