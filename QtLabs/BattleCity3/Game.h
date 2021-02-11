#ifndef GAME_H
#define GAME_H

#include <Player.h>
#include <QPainter>
#include <QKeyEvent>
#include <Enemy.h>
#include <Bullet.h>
#include <Base.h>

class Game
{
public:
    Game();
    ~Game();

    void drawGame(QPainter* painter);
    void reactionToKeyPress(QKeyEvent *event);
    void gameRules();
    bool gameOver();

private:
     Player* player;
     Base* base;
     std::list<Enemy*> enemys;
     std::list<Bullet*> bullets;
     std::list<PhysicalObject*> barrier;

     int EnemyCounter;
     bool stopGame;
     int shootInterval, rotateInterval;
     void generateEnemys();
     void readGameMap();
     bool checkCollisions(PhysicalObject* obj1, PhysicalObject* obj2);

     //std::list<MovingObject*> gameMovingObjects;
};

#endif // GAME_H
