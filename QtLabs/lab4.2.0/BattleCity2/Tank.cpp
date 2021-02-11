#include <Tank.h>
#include <Bullet.h>
#include <Game.h>

extern Game game;

void Tank::shoot(Bullet* bullete) {
    switch(direction) {
       case Up:
            bullete->x = x + 32 - 7;
            bullete->y = y;
            break;
       case Down:
            bullete->x = x + 32 - 7;
            bullete->y = y + 60;
            break;
        case Left:
            bullete->x = x;
            bullete->y = y + 32 - 7;
            break;
        case Right:
            bullete->x = x + 64;
            bullete->y = y + 32 - 7;
            break;
    }
}

int Tank:: moveTank(int speed) {
        int j = getJ();
        int i = getI();

        if((direction == Up && game.gameMatrix[(i)*28+j] == '.' && game.gameMatrix[(i)*28+j+1] == '.') ||
           (direction == Down && game.gameMatrix[(i+2)*28+j] == '.' && game.gameMatrix[(i+2)*28+j+1] == '.') ||
           (direction == Left && game.gameMatrix[i*28+j] == '.' && game.gameMatrix[(i+1)*28+j] == '.') ||
           (direction == Right && game.gameMatrix[i*28+j+2] == '.' && game.gameMatrix[(i+1)*28+j+2] == '.')) {
                 move(direction, speed);
                 return 1;
           }
        else return 0;
}






