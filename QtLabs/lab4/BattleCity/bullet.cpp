#include <Bullet.h>
#include <QTimer>
#include <QGraphicsScene>
#include <QDebug>
#include <QList>
#include <Enemy.h>
#include <directions.h>

Bullet::Bullet(Direction direction){
    this->direction = direction;
    this->width = 5;
    this->height = 12;

    //bullet size
    setPixmap(QPixmap(":/images/bullet.gif"));

    //change bullet position every 50 milliseconds
    QTimer* timer = new QTimer;
    connect(timer, SIGNAL(timeout()), this, SLOT(moveBullet()));
    timer->start(50);
}

void Bullet::moveBullet(){

    QList<QGraphicsItem *> colliding_items = collidingItems();
    for(int i = 0; i <colliding_items.size(); ++i){
        if(typeid(*colliding_items[i]) == typeid(Enemy) ||
                typeid(*colliding_items[i]) == typeid(Bullet)) {
            scene()->removeItem(colliding_items[i]);
            scene()->removeItem(this);
            delete(this);
            delete(colliding_items[i]);
            qDebug() << "Enemy and bullet deleted";
            return;
        }
    }
    if(direction == Up){
        setRotation(270);
        setPos(x(), y() - 10);
    }
    else if(direction == Down){
        setRotation(90);
        setPos(x(), y() + 10);
    }
    if(direction == Left){
        setRotation(180);
        setPos(x() - 10, y());
    }
    else if(direction == Right){
        setRotation(360);
        setPos(x() + 10, y());
    }
    if(pos().y()<0 || pos().y()>600 - height ||
            pos().x()<0 || pos().x()>800 - width){
        scene()->removeItem(this);
        delete this;
        qDebug() << "Bullet deleted";
    }
}
