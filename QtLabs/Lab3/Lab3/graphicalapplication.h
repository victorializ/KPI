#ifndef GRAPHICALAPPLICATION_H
#define GRAPHICALAPPLICATION_H

#include <QWidget>

namespace Ui {
class GraphicalApplication;
}

class GraphicalApplication : public QWidget
{
    Q_OBJECT

public:
    explicit GraphicalApplication(QWidget *parent = nullptr);
    ~GraphicalApplication();

private:
    Ui::GraphicalApplication *ui;
    QTimer *tmr;
    void paintEvent(QPaintEvent *event);
    void mousePressEvent(QMouseEvent *event);
    void keyPressEvent(QKeyEvent *event);

private slots:
    void updateTime();

};

#endif // GRAPHICALAPPLICATION_H
