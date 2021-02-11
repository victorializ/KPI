#include "widget.h"
#include "ui_widget.h"

Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);

    game = new Game();
    setFixedSize(WIDTH, HEIGHT);

    QPalette pall;
    pall.setColor(QPalette::Background, Qt::black);
    setPalette(pall);

    timerUpdate = new QTimer();
    connect(timerUpdate, SIGNAL(timeout()), this, SLOT(updateTime()));
    timerUpdate->start(50);
}

Widget::~Widget()
{
    delete ui;
    timerUpdate->stop();
    delete timerUpdate;
    delete game;
}

void Widget::updateTime()
{
    if(game->gameOver() == true) {
        timerUpdate->stop();

   }
    else {
    game->gameRules();
    update(0, 0, WIDTH, HEIGHT);
    }
}

void Widget::paintEvent(QPaintEvent *event)
{
    Q_UNUSED(event);

    QPainter painter(this);
    game->drawGame(&painter);
}

void Widget::keyPressEvent(QKeyEvent *event)
{
    game->reactionToKeyPress(event);
}
