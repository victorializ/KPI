#include <iostream>
#ifndef PICTURE_H
#define PICTURE_H

const int IMAGE_HEIGHT = 256;
const int IMAGE_WIDTH = 256;

struct pixelRGB {
    char red;
    char green;
    char blue;
};

struct image {
    pixelRGB *pixels;
};

struct HEADER {
   char  idlength;
   char  colourmaptype;
   char  datatypecode;
   char  colourmapdepth;
   short int colourmaporigin;
   short int colourmaplength;
   short int x_origin;
   short int y_origin;
   short width;
   short height;
   char  bitsperpixel;
   char  imagedescriptor;
};

void create_white(image* picture);
void create_black(image* picture);
void create_color(image* picture);
void save_to_tgaFile(image* picture, std::string path);
void read_from_tgaFile(image* picture, std::string path);

#endif // PICTURE_H


