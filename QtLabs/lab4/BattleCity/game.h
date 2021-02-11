#ifndef GAME_H
#define GAME_H

#include <QGraphicsView>
#include <QWidget>
#include <QGraphicsScene>
#include <Player.h>

class Game: public QGraphicsView{

public:
    Game(QWidget* parent);

    QGraphicsScene* scene;
    Player* player;

private:
    void paintEvent(QPaintEvent *event);
};

#endif // GAME_H
