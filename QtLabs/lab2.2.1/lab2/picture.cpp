#include <picture.h>
#include <iostream>
#include <fstream>

using namespace std;

void create_white(image* picture) {
    if(picture!=nullptr){
        for (int i = 0; i < IMAGE_HEIGHT; i++) {
            for (int j = 0; j < IMAGE_WIDTH; j++) {
                picture->pixels[i * IMAGE_HEIGHT + j].red = static_cast<char>(255);
                picture->pixels[i * IMAGE_HEIGHT + j].green = static_cast<char>(255);
                picture->pixels[i * IMAGE_HEIGHT + j].blue = static_cast<char>(255);
            }
        }
    }
}

void create_black(image* picture){
    if(picture!=nullptr){
        for (int i = 0; i < IMAGE_HEIGHT; i++) {
            for (int j = 0; j < IMAGE_WIDTH; j++) {
                picture->pixels[i * IMAGE_HEIGHT + j].red = static_cast<char>(0);
                picture->pixels[i * IMAGE_HEIGHT + j].green = static_cast<char>(0);
                picture->pixels[i * IMAGE_HEIGHT + j].blue = static_cast<char>(0);
            }
        }
    }
}

void create_color(image* picture){
    if(picture!=nullptr){
        for (int i = 0; i < IMAGE_HEIGHT; i++) {
            for (int j = 0; j < IMAGE_WIDTH; j++) {
                picture->pixels[i * IMAGE_HEIGHT + j].red = static_cast<char>((i + j)%256);
                picture->pixels[i * IMAGE_HEIGHT + j].green = static_cast<char>((i + j)%256);
                picture->pixels[i * IMAGE_HEIGHT + j].blue = static_cast<char>((i + j)%256);
            }
        }
    }
}

void save_to_tgaFile(image* picture, string path){
    if(picture != nullptr){
        ofstream fout;
        fout.open(path, ios::binary);

        HEADER h;
        h.idlength = 0;
        h.colourmaptype = 0;
        h.datatypecode = 2;
        h.colourmaporigin = 0;
        h.colourmaplength = 0;
        h.colourmapdepth = 0;
        h.x_origin = 0;
        h.y_origin = 0;
        h.width = 256;
        h.height = 256;
        h.bitsperpixel = 24;
        h.imagedescriptor = 0x20;

        fout.put(static_cast<char>(h.idlength));
        fout.put(static_cast<char>(h.colourmaptype));
        fout.put(static_cast<char>(h.datatypecode));
        fout.put(static_cast<char>(h.colourmaporigin % 256));
        fout.put(static_cast<char>(h.colourmaporigin / 256));
        fout.put(static_cast<char>(h.colourmaplength % 256));
        fout.put(static_cast<char>(h.colourmaplength / 256));
        fout.put(static_cast<char>(h.colourmapdepth));
        fout.put(static_cast<char>(h.x_origin % 256));
        fout.put(static_cast<char>(h.x_origin / 256));
        fout.put(static_cast<char>(h.y_origin % 256));
        fout.put(static_cast<char>(h.y_origin / 256));
        fout.put(static_cast<char>(h.width % 256));
        fout.put(static_cast<char>(h.width / 256));
        fout.put(static_cast<char>(h.height % 256));
        fout.put(static_cast<char>(h.height / 256));
        fout.put(static_cast<char>(h.bitsperpixel));
        fout.put(static_cast<char>(h.imagedescriptor));

        if(fout.is_open()){
            for (int i = IMAGE_HEIGHT - 1; i >= 0; i--){
                for (int j = 0; j < IMAGE_WIDTH; j++){
                    fout << ((picture->pixels[i * IMAGE_HEIGHT + j ].blue));
                    fout << ((picture->pixels[i * IMAGE_HEIGHT + j ].green));
                    fout << ((picture->pixels[i * IMAGE_HEIGHT + j ].red));
                }
            }
        fout.close();
        }
    }
}

void read_from_tgaFile(image* picture, string path){
    if(picture != nullptr){

        ifstream fin;
        HEADER h;
        fin.open(path, ios::binary);

        if(fin.is_open()){
            char* bufferHeader = new char [sizeof(h)];
            fin.read(bufferHeader,sizeof(h));
            delete [] (bufferHeader);

            for (int i = IMAGE_HEIGHT - 1; i >= 0; i--){
                for (int j = 0; j < IMAGE_WIDTH; j++){
                    char* bufferPixel = new char[3];
                    fin.read(bufferPixel, 3);
                    picture->pixels[i * IMAGE_HEIGHT + j].red = bufferPixel[2];
                    picture->pixels[i * IMAGE_HEIGHT + j].green = bufferPixel[1];
                    picture->pixels[i * IMAGE_HEIGHT + j].blue = bufferPixel[0];
                    delete [] (bufferPixel);
                }
            }
        }
    }
}
