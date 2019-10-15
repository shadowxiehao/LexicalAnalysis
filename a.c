/*
   累加法
   author:谢昊
*/
#include <stdio.h>

int main { //演示
    int c=0, a=1.1f ,b=2.33;
    c=a+b;
        for(int i=0;i<2;i++){
            c=i++;
        }
    printf("%d (这里可能还会有\'之类的)",c);
}