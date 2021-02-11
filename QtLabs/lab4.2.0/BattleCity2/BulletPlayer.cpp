#include "BulletPlayer.h"
#include <Barrier.h>
#include <Game.h>

extern Game game;

BulletPlayer::BulletPlayer(Direction direction, int width, int height) : Bullet(direction, width, height) {
    timer = new QTimer;
    connect(timer, SIGNAL(timeout()), this, SLOT(moveBulletPlayer()));
    timer->start(50);
}

void BulletPlayer::moveBulletPlayer() {
    if(!game.enemys.empty()) {
        for (Enemy* enemy : game.enemys) {
            if(std::abs(enemy->x - this->x) < 64 && std::abs(enemy->y - this->y) < 64){
                 game.bullets.remove(this);
                 game.enemys.remove(enemy);
                 delete enemy;
                 timer->stop();
                 delete this;
                 return;
            }
       }
    }
    moveBullet();
}
