#include <Bullet.h>
#include <QPixmap>
#include <QGraphicsScene>
#include <QDebug>
#include <Enemy.h>
#include <Player.h>
#include <Game.h>

extern Game game;

Bullet:: Bullet(Direction direction, int width, int height) : GameObject(direction, width, height) {

    Rotation(direction, "Bullet");
    game.bullets.push_back(this);
    //Game:: bullets.push_back(this);
}


void Bullet::moveBullet() {

   int j = getJ();
   int i = getI();
   int currElem = i*28+j;

   if(direction == Up) {
       int nextElem = (i-1)*28+j;

       if(game.gameMatrix[nextElem] == '.' && game.gameMatrix[currElem] == '.') {
            move(direction, 10);
        }
       else {
           hitWall(currElem, nextElem);
       }
    }

    else if(direction == Down) {
       int nextElem = (i+1)*28+j;

       if(game.gameMatrix[nextElem] == '.' && game.gameMatrix[currElem] == '.') {
             move(direction, 10);
       }
       else {
           hitWall(currElem, nextElem);
       }
    }

    else if(direction == Left) {
       int nextElem = i * 28 + j - 1;

       if(game.gameMatrix[nextElem] == '.'&& game.gameMatrix[currElem] == '.') {
           move(direction, 10);
        }
        else {
           hitWall(currElem, nextElem);
       }
    }

    else if(direction == Right) {
       int nextElem = i * 28 + j +1;

       if(game.gameMatrix[nextElem] == '.'&& game.gameMatrix[currElem] == '.') {
          move(direction, 10);
        }
       else {
           hitWall(currElem, nextElem);
       }
    }
}

void Bullet::hitWall(int currElem, int nextElem)
{
    if(game.gameMatrix[nextElem] == '#'){
                game.gameMatrix[nextElem] = '.';
                game.gameMatrix[currElem] = '.';
     }
    game.bullets.remove(this);
    timer->stop();
    delete this;
}


