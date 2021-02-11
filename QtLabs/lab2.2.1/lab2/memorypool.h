#include<picture.h>
#ifndef MEMORYPOOL_H
#define MEMORYPOOL_H

struct Pointer{
    int isFree = 1;
    Pointer* ptrPrev = nullptr;
};

struct MemoryPool{

    unsigned int numberOfPicturs = 3;
    unsigned int blockLength = sizeof(image) + (IMAGE_HEIGHT * IMAGE_WIDTH) * sizeof(pixelRGB) + sizeof (Pointer);
    char* arr = new char[blockLength * numberOfPicturs];
    Pointer* memoryList = reinterpret_cast<Pointer*>(arr);

    MemoryPool();
};

image* pool_new(MemoryPool* pool);
void pool_delete(MemoryPool* pool, image** colorPicture);

#endif // MEMORYPOOL_H
