#-------------------------------------------------
#
# Project created by QtCreator 2018-11-22T23:24:28
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = BattleCity3
TEMPLATE = app

# The following define makes your compiler emit warnings if you use
# any feature of Qt which has been marked as deprecated (the exact warnings
# depend on your compiler). Please consult the documentation of the
# deprecated API in order to know how to port your code away from it.
DEFINES += QT_DEPRECATED_WARNINGS

# You can also make your code fail to compile if you use deprecated APIs.
# In order to do so, uncomment the following line.
# You can also select to disable deprecated APIs only up to a certain version of Qt.
#DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000    # disables all the APIs deprecated before Qt 6.0.0

CONFIG += c++11

SOURCES += \
        main.cpp \
        widget.cpp \
    PhysicalObject.cpp \
    Player.cpp \
    Bricks.cpp \
    Game.cpp \
    Enemy.cpp \
    Bullet.cpp \
    IronBrick.cpp \
    Base.cpp \
    MovingObject.cpp

HEADERS += \
        widget.h \
    PhysicalObject.h \
    Player.h \
    Bricks.h \
    Game.h \
    Enemy.h \
    Bullet.h \
    IronBrick.h \
    Base.h \
    MovingObject.h

FORMS += \
        widget.ui

# Default rules for deployment.
qnx: target.path = /tmp/$${TARGET}/bin
else: unix:!android: target.path = /opt/$${TARGET}/bin
!isEmpty(target.path): INSTALLS += target

RESOURCES += \
    resource.qrc
