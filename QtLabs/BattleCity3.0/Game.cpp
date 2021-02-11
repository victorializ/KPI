#include "Game.h"
#include "Bricks.h"
#include "QDebug"
#include "QTimer"
#include "Enemy.h"
#include "QRandomGenerator"
#include "widget.h"
#include "IronBrick.h"
#include "Base.h"

Game::Game()
{
    readGameMap();
    player = new Player(MovingObject:: Up, 400, 700);
    gameMovingObjects.push_back(player);
    EnemyCounter = 0;
    stopGame = false;

    while(EnemyCounter < 3){
        generateEnemys();
    }

    rotateInterval = 0;
    shootInterval = 0;
}

Game::~Game()
{
    if(!gameMovingObjects.empty()){
        for(auto obj: gameMovingObjects) {
            delete obj;
        }
    }

    if(!gameStaticObjects.empty()){
        for(auto obj : gameStaticObjects) {
            delete obj;
        }
    }
}

void Game::generateEnemys()
{
    int x = QRandomGenerator::global()->bounded(800);
    Enemy* enemy = new Enemy(MovingObject:: Down, x, 50);
    gameMovingObjects.push_back(enemy);
    EnemyCounter ++;
}

bool Game::gameOver()
{
    return stopGame;
}

void Game::readGameMap()
{
    QFile file(":/images/map.txt");
    if (file.open(QIODevice::ReadOnly|QIODevice::Text)){
        int i = 0;
        while (!file.atEnd()) {
            QByteArray arr = file.readLine();
            for(int j = 0; j <= arr.size(); j++) {
                 if(arr[j] == '#'){
                    Bricks* brick = new Bricks(j*32, i*32);
                    gameStaticObjects.push_back(brick);
                }
                 else if(arr[j] == '@'){
                     IronBrick* brick = new IronBrick(j*32, i*32);
                     gameStaticObjects.push_back(brick);
                 }
                 else if(arr[j] == '%'){
                     base = new Base(j*32, i*32);
                     gameStaticObjects.push_back(base);
                 }
            }
            i++;
        }
    }
}

void Game::drawGame(QPainter *painter)
{
    if(!gameStaticObjects.empty()) {
        for (auto obj : gameStaticObjects) {       
               obj->draw(painter);
       }
    }

    if(!gameMovingObjects.empty()) {
        for (auto obj : gameMovingObjects) {
               obj->draw(painter);
       }
    }
}

void Game::reactionToKeyPress(QKeyEvent *event)
{
    //player->move(event);
    player->reactionToKeyPress(event);
}

bool Game::checkCollisions(PhysicalObject* obj1, PhysicalObject* obj2)
{
   int x1, x2, y1, y2;
   x1 = obj1->getX(); int x11 = x1 + obj1->getW();
   y1 = obj1->getY(); int y11 = y1 + obj1->getH();
   //left up corner (x1, y1)
   //right up corner (x11, y1)
   //left d corner (x1, y11)
   //right d corner (x11, y11)
   x2 = obj2->getX(); int x21 = x2 + obj2->getW();
   y2 = obj2->getY(); int y21= y2 + obj2->getH();

   bool s1 = (x1 >= x2 && x1 <= x21)||(x11 >= x2 && x11 <= x21);
   bool s2 = (y1 >= y2 && y1 < y21)||(y11 >= y2 && y11 <= y21);
   bool s3 = (x2 >= x1 && x2 <= x11)||(x21 >= x1 && x21 <= x11);
   bool s4 = (y2 >= y1 && y2 <= y11)||(y21 >= y1 && y21 <= y11);

   if((s1 && s2) || (s3 && s4) || (s1 && s4) || (s3 && s2)){
       return true;
   }
   else return false;
}


void Game::gameRules()
{
    //conditions for stopping the game
    if(base->isDestroyed() || player->getLifes() <= 0){
        this->stopGame = true;
    }

    //generate Enemys (if someone was killed)
    if(EnemyCounter < 3) generateEnemys();

    //enemys shoot and rotate
    if(!gameMovingObjects.empty()){
        for (auto obj : gameMovingObjects) {
               if(dynamic_cast<Enemy*>(obj) != nullptr){

                   if(rotateInterval % 1000  == 0) {
                    dynamic_cast<Enemy*>(obj)->setRandomDirection();
                   }

                   if(shootInterval % 3000 == 0){
                       Bullet* bullet = new Bullet(obj->getDirection(), obj->getX(), obj->getY());
                       gameMovingObjects.push_back(bullet);
                   }
               }
         }
    }

   //player shoot
    if(player->readyToShoot() == true){
        Bullet* bullet = new Bullet(player->getDirection(), player->getX(), player->getY());
        gameMovingObjects.push_back(bullet);
        player->notShoot();
    }

    //check collisions MovingObjects with StaticObjects
    if(!gameMovingObjects.empty() && !gameStaticObjects.empty()){
    for(auto obj1: gameMovingObjects) {
        for (auto obj2 : gameStaticObjects) {
                if(checkCollisions(obj1, obj2)) {
                    obj1->collision_react(obj2);
                    break;
                }
            }
        }
    }

    //check collisions MovingObjects
    if(!gameMovingObjects.empty()) {
        for(auto obj1: gameMovingObjects) {
            for (auto obj2 : gameMovingObjects) {
                if(obj1 != obj2 && checkCollisions(obj1, obj2)){
                    obj1->collision_react(obj2);
                    obj2->collision_react(obj1);
                }
            }
        }
    }

    if(!gameMovingObjects.empty()) {
        for (auto obj : gameMovingObjects) {
               obj->move(obj->getDirection());
       }
    }

    //delete gameMovingObjects gameStaticObjects
    if(!gameMovingObjects.empty()) {
        for (auto obj: gameMovingObjects) {
            if(obj->isDestroyed() == true) {
                if(dynamic_cast<Enemy*>(obj) != nullptr) {
                    EnemyCounter --;
                }
               gameMovingObjects.remove(obj);
               delete obj;
            }
       }
    }

    if(!gameStaticObjects.empty()) {
        for (auto obj :gameStaticObjects) {
            if(obj->isDestroyed() == true){
               gameStaticObjects.remove(obj);
               delete obj;
            }
       }
    }

    rotateInterval += 50;
    shootInterval += 50;
}




