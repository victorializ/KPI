#include "graphicalapplication.h"
#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    GraphicalApplication w;
    w.show();

    return a.exec();
}
