#include <Game.h>
#include "ui_game.h"
#include <QTimer>
#include <QFile>
#include <QPainter>
#include <Enemy.h>
#include <BulletPlayer.h>
#include <QKeyEvent>

Game:: Game(QWidget *parent):
    QWidget(parent),
    ui(new Ui::Game) {
    ui->setupUi(this);

    //fixed size form
    setFixedSize(832, 896);

    //black background
    QPalette pall;
    pall.setColor(QPalette::Background, Qt::black);
    setPalette(pall);

    //read level map
    readGameMap();

    //create player
    player = new Player(GameObject::Up, 54, 54);
    gameMatrix = new char[26*28];
    //EnemyCounter = 0;

    //generate enemys
    timerEnemys = new QTimer();
    connect(timerEnemys, SIGNAL(timeout()), this, SLOT(generateEnemies()));
    timerEnemys->start(1000); 

    //update
    timer = new QTimer();
    connect(timer, SIGNAL(timeout()), this, SLOT(updateTime()));
    timer->start(50);
}

Game::~Game() {

    delete[] gameMatrix;
    delete player;
    timer->stop();
    timerEnemys->stop();
    delete timer;
    delete timerEnemys;
    delete ui;
}

void Game::readGameMap() {
    QFile file(":/images/map.txt");
    if (file.open(QIODevice::ReadOnly|QIODevice::Text)){
        int i = 0;
        while (!file.atEnd()) {
            QByteArray arr = file.readLine();
            for(int j = 0; j <= arr.size(); j++) {
                    gameMatrix[i*28 + j] = arr[j];
            }
            i++;
        }
    }
}

void Game::updateTime() {
    update(0, 0, 832, 896);
}


void Game::paintEvent(QPaintEvent *event) {
     Q_UNUSED(event);

     QPainter painter(this);

     for(int i = 0; i < 28; i ++) {
         for(int j = 0; j < 26; j++){
             if(gameMatrix[i*28 + j] == '#'){
                 QPixmap pixmap(":/images/bricks.png");
                 painter.drawPixmap(j*32, i*32, 32, 32, pixmap);
             }
             else if(gameMatrix[i*28 + j] == '@'){
                 QPixmap pixmap(":/images/wall.png");
                 painter.drawPixmap(j*32, i*32, 32, 32, pixmap);
             }
             else if(gameMatrix[i*28 + j] == '%'){
                 QPixmap pixmap(":/images/base.png");
                 painter.drawPixmap(j*32, i*32, 64, 64, pixmap);
             }
         }
     }

     QPixmap pixmap(player->img);
     painter.drawPixmap(player->x, player->y, 54, 50, pixmap);

     if(!enemys.empty()) {
         for (Enemy* enemy : enemys) {
                QPixmap pixmap(enemy->img);
                painter.drawPixmap(enemy->x, enemy->y, 54, 50, pixmap);
        }
     }

     if(!bullets.empty()) {
     for (Bullet* bullet : bullets) {
            QPixmap pixmap(bullet->img);
            painter.drawPixmap(bullet->x, bullet->y, pixmap);
        }
     }
}

void Game:: keyPressEvent(QKeyEvent *event) {
    //условия выхода
    /*
    if(Player::Lifes <= 0){
        for(int i = 0; i< scene()->items().size(); i++){
            scene()->items()[i]->setEnabled(false);
        }
        return;
    }
    */

    if(event->key() == Qt::Key_Left) {
        player->movePlayer(GameObject::Left);
    }

    else if(event->key() == Qt::Key_Right) {
        player->movePlayer(GameObject::Right);
    }

    else if(event->key() == Qt::Key_Up) {
        player->movePlayer(GameObject::Up);
    }

    else if(event->key() == Qt::Key_Down) {
        player->movePlayer(GameObject::Down);
    }

    else if(event->key() == Qt::Key_Space) {
        BulletPlayer* bullete = new BulletPlayer(player->direction, 12, 5);
        player->shoot(bullete);
    }
}

void Game::generateEnemies()
{
    if(Game:: enemys.size() < 4 && Player::Lifes > 0) {
        Enemy* enemy = new Enemy(GameObject::Down, 54, 50);
        enemys.push_back(enemy);
    }
}



