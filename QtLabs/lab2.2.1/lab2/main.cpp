#include <iostream>
#include <memorypool.h>
#include <picture.h>
using namespace std;

int main(){
    MemoryPool* pool = new MemoryPool{};

    image* colorPicture1 = pool_new(pool);
    image* colorPicture2 = pool_new(pool);
    image* colorPicture3 = pool_new(pool);
    image* colorPicture4 = pool_new(pool);
    image* colorPicture5 = pool_new(pool);
    cout<<"\n\n";

    create_color(colorPicture1);
    create_color(colorPicture2);
    create_color(colorPicture3);
    create_color(colorPicture5);
    save_to_tgaFile(colorPicture1, "1.tga");
    read_from_tgaFile(colorPicture1, "1.tga");
    save_to_tgaFile(colorPicture2, "2.tga");
    read_from_tgaFile(colorPicture2, "2.tga");
    save_to_tgaFile(colorPicture3, "3.tga");
    read_from_tgaFile(colorPicture3, "3.tga");
    save_to_tgaFile(colorPicture5, "5.tga");
    read_from_tgaFile(colorPicture5, "5.tga");

    pool_delete(pool, &colorPicture1);
    pool_delete(pool, &colorPicture4);
    pool_delete(pool, &colorPicture3);
    pool_delete(pool, &colorPicture3);
    pool_delete(pool, &colorPicture1);
    cout<<"\n\n";

    image* colorPicture7 = pool_new(pool);
    image* colorPicture8 = pool_new(pool);
    image* colorPicture9 = pool_new(pool);
    image* colorPicture10 = pool_new(pool);
    cout<<"\n\n";

    create_color(colorPicture7);
    create_color(colorPicture8);
    create_color(colorPicture9);
    create_color(colorPicture10);
    save_to_tgaFile(colorPicture7, "7.tga");
    save_to_tgaFile(colorPicture8, "8.tga");
    save_to_tgaFile(colorPicture9, "9.tga");
    save_to_tgaFile(colorPicture10, "10.tga");

    pool_delete(pool, &colorPicture1);
    pool_delete(pool, &colorPicture4);
    pool_delete(pool, &colorPicture3);
    pool_delete(pool, &colorPicture3);
    pool_delete(pool, &colorPicture1);
    cout<<"\n\n";


    pool_delete(pool, &colorPicture7);
    pool_delete(pool, &colorPicture8);
    pool_delete(pool, &colorPicture8);
    pool_delete(pool, &colorPicture9);
    pool_delete(pool, &colorPicture10);

    delete pool;
    return 0;
}
