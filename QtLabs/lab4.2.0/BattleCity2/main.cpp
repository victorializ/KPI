#include "Game.h"
#include <QApplication>

//static Game game;

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    static Game game;

    game.show();

    return a.exec();
}

static Game game;
