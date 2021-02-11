#include <Game.h>
#include <Player.h>
#include <QTimer>
#include <enemy.h>
#include <QPalette>
#include <QPixmap>

Game::Game(QWidget *parent)
{
     Q_UNUSED(parent);

    //scene
    scene = new QGraphicsScene;
    scene->setSceneRect(0,0,800,600);
    //setBackgroundBrush(QBrush(QImage(":/back.jpg")));//???
    setScene(scene);

    //fixed size form
    setHorizontalScrollBarPolicy(Qt::ScrollBarAlwaysOff);
    setVerticalScrollBarPolicy(Qt::ScrollBarAlwaysOff);
    setFixedSize(800,600);

    //player
    player = new Player();
    scene->addItem(player);

    //generate enemys
    QTimer* timer = new QTimer();
    QObject:: connect(timer,SIGNAL(timeout()), player, SLOT(generateEnemies()));
    timer->start(5000);
}

void Game::paintEvent(QPaintEvent *event)
{
    Q_UNUSED(event);

    QPalette pall;
    pall.setColor(QPalette::Background, Qt::black);
    this->setPalette(pall);
}

