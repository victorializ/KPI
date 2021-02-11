#ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>
#include <QTimer>
#include <Game.h>

const int WIDTH = 832;
const int HEIGHT = 896;

namespace Ui {
class Widget;
}

class Widget : public QWidget
{
    Q_OBJECT

public:
    explicit Widget(QWidget *parent = nullptr);
    ~Widget();

private:
    Ui::Widget *ui;
    QTimer* timerUpdate;
    Game* game;
    void paintEvent(QPaintEvent* event);
    void keyPressEvent(QKeyEvent* event);

private slots:
    void updateTime();

};

#endif // WIDGET_H
