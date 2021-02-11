#include "BulletEnemy.h"
#include "Game.h"
#include <QDebug>

extern Game game;

BulletEnemy::BulletEnemy(Direction direction, int width, int height): Bullet(direction, width, height) {
    timer = new QTimer;
    connect(timer, SIGNAL(timeout()), this, SLOT(moveEnemyBullet()));
    timer->start(50);
}

void BulletEnemy::moveEnemyBullet() {
    if(std::abs(game.player->x - this->x) < 64 && std::abs(game.player->y - this->y) < 64){
        Player::Lifes--;
        qDebug() << Player::Lifes;
        game.bullets.remove(this);
        timer->stop();
        delete this;
        return;
    }
    moveBullet();
}


