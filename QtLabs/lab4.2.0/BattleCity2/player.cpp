#include <Player.h>
#include <QKeyEvent>
#include <Bullet.h>
#include <Enemy.h>
#include <QGraphicsScene>
#include <Game.h>
#include <QDebug>

int Player:: Lifes = 3;

Player::Player(Direction direction, int width, int height) : Tank(direction, width, height) {
    Rotation(direction, "Player");
    x = 400;
    y = 400;
}

void Player::movePlayer(Direction direction) {
    Rotation(direction, "Player");
    moveTank(5);
}


