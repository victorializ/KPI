#include <Enemy.h>
#include <QTimer>
#include <QGraphicsScene>
#include <QDebug>
#include <Bullet.h>
#include <directions.h>

Enemy::Enemy(){

    this->width = 64;
    this->height = 60;

    setPos(std::rand() % 700,0);
    setPixmap(QPixmap(":/images/Tank_Player2.png"));
    setTransformOriginPoint(32,30);
    setRotation(270);

    QTimer* timerMove = new QTimer;
    connect(timerMove, SIGNAL(timeout()), this, SLOT(moveEnemy()));
    timerMove->start(50);

    QTimer* timerShoot = new QTimer;
    connect(timerShoot, SIGNAL(timeout()), this, SLOT(shoot()));
    timerShoot->start(1000);

    QTimer* timerDirection = new QTimer;
    connect(timerDirection, SIGNAL(timeout()), this, SLOT(setDirection()));
    timerDirection->start(3000);
}

Enemy::Enemy(Direction direction, int width, int height)
{

}

void Enemy::moveEnemy(){

    if(direction == Up){
        setPos(x(), y() - 5);
    }
    else if(direction == Down){
        setPos(x(), y() + 5);
    }
    if(direction == Left){
        setPos(x() - 5, y());
    }
    else if(direction == Right){
        setPos(x() + 5, y());
    }

    if(pos().y()<0 || pos().y()>600 - height ||
            pos().x()<0 || pos().x()>800 - width){
        scene()->removeItem(this);
        delete this;
        qDebug() << "Enemy deleted";
    }
}


void Enemy::shoot() //!
{
    Bullet* bullete = new Bullet(direction);

    if(direction == Up){
       bullete->setPos(x() + width/2 + bullete->width/2, y() - bullete->width - 10);
    }
    else if(direction== Down){
       bullete->setPos(x() + width/2 + bullete->width/2, y() + height + bullete->width + 10 );
    }
    else if(direction == Left){
       bullete->setPos(x() - bullete->height - 10, y() + height/2 - bullete->width);
    }
    else if(direction == Right){
       bullete->setPos(x() + height + bullete->height + 10, y() + height/2 - bullete->width);
    }
    scene()->addItem(bullete);
}

void Enemy::setDirection()
{
    int i = std::rand() % 4;
    if(i == 0) {
        this->direction = Right;
        setRotation(180);
    }
    else if(i == 1) {
        this->direction = Left;
        setRotation(360);
    }
    else if(i == 2) {
        this->direction = Up;
        setRotation(90);
    }
    else if(i == 3) {
        this->direction = Down;
        setRotation(270);
    }
}


