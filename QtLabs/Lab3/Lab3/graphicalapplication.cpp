#include "graphicalapplication.h"
#include "ui_graphicalapplication.h"
#include <QPainter>
#include <QTimer>
#include <QColor>
#include <QPaintEvent>
#include <QRandomGenerator>

GraphicalApplication::GraphicalApplication(QWidget *parent):
    QWidget(parent),
    ui(new Ui::GraphicalApplication)
{
    ui->setupUi(this);
    this->setMinimumSize(800,600);
    this->setMaximumSize(800,600);

    QTimer* tmr = new QTimer(this);
    tmr->setInterval(1000);
    connect(tmr, SIGNAL(timeout()), this, SLOT(updateTime()));
    tmr->start();
}

GraphicalApplication::~GraphicalApplication()
{
    delete ui;
}

/*
void GraphicalApplication::paintEvent(QPaintEvent *event)
{
    Q_UNUSED(event);

    QPalette pall;
    pall.setColor(QPalette::Background, Qt::green);
    this->setPalette(pall);
}
*/

void GraphicalApplication::paintEvent(QPaintEvent *)
{
    QPainter painter(this);

    for(int i = 0; i<6;i++){
        for(int j = 0; j<8;j++){
            painter.setBrush(QColor::fromRgb(QRandomGenerator::global()->generate()));
            painter.drawRect(j*100, i*100, 100, 100);
        }
    }
}

void GraphicalApplication::updateTime()
{
    update(0,0,800,300);
}

void GraphicalApplication::mousePressEvent(QMouseEvent *event){
   if(event->button() == Qt::RightButton){
    update(0,300,800,600);
   }
}

void GraphicalApplication::keyPressEvent(QKeyEvent *event){
    if(event->modifiers()==Qt::ControlModifier && event->key()==Qt::Key_C){
         update(0,300,800,600);
    }
}

