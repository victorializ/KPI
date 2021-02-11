#include <BricksWall.h>

BricksWall::BricksWall(int x, int y): Barrier(x, y) {
   setPixmap(QPixmap(":/images/bricks.png"));
}
