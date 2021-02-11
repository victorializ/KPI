#ifndef PLAYER_H
#define PLAYER_H

#include <Tank.h>

class Player: public Tank {
     Q_OBJECT
public:
    Player(Direction direction, int width, int height);
    static int Lifes;
    void movePlayer(Direction direction);
};

#endif // PLAYER_H
