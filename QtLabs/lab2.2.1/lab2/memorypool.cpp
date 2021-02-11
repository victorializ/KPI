#include <memorypool.h>
#include <iostream>

using namespace std;

MemoryPool::MemoryPool(){
    memoryList->ptrPrev = memoryList;
    for(unsigned int i = 0; i<numberOfPicturs; i++){
            Pointer* point = reinterpret_cast<Pointer*>(arr + i*blockLength);
            point->ptrPrev = memoryList;
            point->isFree = 1;
            memoryList = point;
    }
}

image* pool_new(MemoryPool* pool){

    Pointer* point = pool->memoryList;

    for(unsigned int i = 0; i<pool->numberOfPicturs; i++)
    {
            if(point->isFree == 1)
            {
               image* colorPicture = reinterpret_cast<image*>(reinterpret_cast<char*>(point) + sizeof(Pointer));
               colorPicture->pixels = reinterpret_cast<pixelRGB*>(reinterpret_cast<char*>(colorPicture) + sizeof(image));
               point->isFree = 0;
               cout<<"Memory allocated successfully"<<endl;
               return colorPicture;
            }
            else
            {
                point = point->ptrPrev;
            }
    }

    cout<<"Error! Memory Pool filled"<<endl;
    return nullptr;
}

void pool_delete(MemoryPool* pool, image** colorPicture) {
    Pointer* point = pool->memoryList;

    if(*colorPicture == nullptr){
        cout<<"Nothing to delete"<<endl;
        return;
    }

    for(unsigned int i = 0; i<=pool->numberOfPicturs; i++){
            if(*colorPicture == reinterpret_cast<image*>(reinterpret_cast<char*>(point)+sizeof(Pointer))
                    && point->isFree == 0){
                point->isFree = 1;
                *colorPicture = nullptr;
                cout<<"Memory successfully freed"<<endl;
                return;
            }
            else{
                point = point->ptrPrev;
            }
    }
}

