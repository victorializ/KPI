#include <ConcreteWall.h>

ConcreteWall::ConcreteWall(int x, int y): Barrier(x, y) {
    setPixmap(QPixmap(":/images/wall.png"));
}
