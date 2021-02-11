#include <Enemy.h>
#include <Player.h>
#include <BulletEnemy.h>
#include <Game.h>

Enemy:: Enemy(Direction direction, int width, int height) : Tank(direction, width, height) {
    x = std::rand() % 800;
    y = 0;
    Rotation(direction, "Down");

    timerMove = new QTimer;
    connect(timerMove, SIGNAL(timeout()), this, SLOT(moveEnemy()));
    timerMove->start(50);

    timerShoot = new QTimer;
    connect(timerShoot, SIGNAL(timeout()), this, SLOT(shootEnemy()));
    timerShoot->start(1000);

    timerDirection = new QTimer;
    connect(timerDirection, SIGNAL(timeout()), this, SLOT(setDirection()));
    timerDirection->start(3000);
}

Enemy::~Enemy() {
    timerMove->stop();
    timerShoot->stop();
    timerDirection->stop();
    delete timerMove;
    delete timerShoot;
    delete timerDirection;
}


void Enemy::moveEnemy() {
     if(!moveTank(5)) {
      setDirection();
     }
}


void Enemy::shootEnemy() {
   if(Player::Lifes <= 0) return;
     BulletEnemy* bullete = new BulletEnemy(direction, 12, 5);
     shoot(bullete);
}


void Enemy::setDirection() {
    if(Player::Lifes <= 0) return;

    int i = std::rand() % 5;

    switch(i) {
       case 0:
            direction = Right;
            break;
       case 1:
            direction = Left;
            break;
        case 2:
            direction = Up;
            break;
        case 3:
            direction = Down;
            break;
        case 4:
            direction = Down;
            break;
    }
    Rotation(direction, "Enemy");
}





