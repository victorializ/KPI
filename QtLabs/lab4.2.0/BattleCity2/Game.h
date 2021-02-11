#ifndef GAME_H
#define GAME_H

#include <QWidget>
#include <Player.h>
#include <Enemy.h>
#include <Bullet.h>

namespace Ui {
    class Game;
}

class Game: public QWidget{
    Q_OBJECT

public:
    explicit Game(QWidget *parent = nullptr);
    ~Game();

      Player* player; // using in BulletEnemy
     char* gameMatrix; // using in Bullet, Enemy, Player
     std::list<Enemy*> enemys; // using in BulletPlayer
      std::list<Bullet*> bullets; // using in BulletPlayer, BulletEnemy

private:
     Ui::Game *ui;
     QTimer* timer;
     QTimer* timerEnemys;

     void readGameMap();

     void paintEvent(QPaintEvent *event);
     void keyPressEvent(QKeyEvent* event);

private slots:
     void generateEnemies();
     void updateTime();
};

#endif // GAME_H
