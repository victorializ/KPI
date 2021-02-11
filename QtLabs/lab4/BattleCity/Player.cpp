#include<Player.h>
#include<QKeyEvent>
#include<QDebug>
#include<QGraphicsScene>
#include<Bullet.h>
#include<Enemy.h>
#include<Game.h>

Player::Player(){
    this->width = 64;
    this->height = 60;
    setPixmap(QPixmap(":/images/Tank_Player1.png"));
    setTransformOriginPoint(32,30);
    setRotation(270);
    this->setPos(800/2-width/2, 600-height);
    this->setFlag(QGraphicsItem::ItemIsFocusable);
    this->setFocus();

}
void Player::keyPressEvent(QKeyEvent *event)
{
    if(event->key() == Qt::Key_Left){
        this->setRotation(180);
        this->directon = Left;
        if(x()>0){
            setPos(x()-10,y());
        }
    }
    else if(event->key() == Qt::Key_Right){
        this->setRotation(360);
        this->directon = Right;
        if(x()+width<800){
            setPos(x()+10,y());
        }
    }

    else if(event->key() == Qt::Key_Up){
        this->setRotation(270);
        this->directon = Up;
        if(y()>0){
            setPos(x(),y()-10);
        }
    }
    else if(event->key() == Qt::Key_Down){
        this->setRotation(90);
        this->directon = Down;
        if(y()+width<600){
            setPos(x(),y()+10);
        }
    }

    else if(event->key() == Qt::Key_Space){
        Bullet* bullete = new Bullet(directon);
        if(directon == Up){
            bullete->setPos(x() + width/2 - bullete->width/2,y() - bullete->height-10);
        }
        else if(directon == Down){
             bullete->setPos(x() + width/2 - bullete->width/2,y() + height + bullete->height);
        }
        if(directon == Left){
             bullete->setPos(x() -10 - bullete->width, y() + height/2 - bullete->height);
        }
        else if(directon == Right){
             bullete->setPos(x() + width + bullete->width, y() + height/2 - bullete->height);
        }

        scene()->addItem(bullete);
    }
}

void Player::generateEnemies(){
    Enemy* enemy = new Enemy();
    scene()->addItem(enemy);
}
