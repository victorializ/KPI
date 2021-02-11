#include <QApplication>
#include <QGraphicsScene>
#include <Player.h>
#include <QGraphicsView>
#include <QTimer>
#include <Game.h>

//static Game* game;

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);

    Game game;
    game.show();

    return a.exec();
}
