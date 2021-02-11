#include <iostream>
#include <cmath>
#include <fstream>

using namespace std;

void medians(double a, double b, double c){
   double m1, m2, m3;
   if(a>0 && b>0 && c>0 && a+b>c && a+c>b && b+c>a) {
        m1 = 0.5 * sqrt(2*pow(a,2)+2*pow(b,2)-pow(c,2)); //median length to side с
        m2 = 0.5 * sqrt(2*pow(b,2)+2*pow(c,2)-pow(a,2)); // median length to side а
        m3 = 0.5 * sqrt(2*pow(a,2)+2*pow(c,2)-pow(b,2)); // median length to side b
   }
   else {
       m1 = m2 = m3 = 0.0;
   }

   cout<<m1<<" "<<m2<<" "<<m3<<endl;

   ofstream fout;
   fout.open("medians.txt", ofstream::app);
   if(fout.is_open()){
       fout<<m1<<" "<<m2<<" "<<m3<<endl;
   }

   fout.close();
}

int main()
{
  /*
  const double a = 3;
  const double b = 4;
  const double c = 5;
  medians(a,b,c);
  */

  /*
  const int n = 4;
  double triangles[n][3] = {{1, 0, 3},{3, 4, 5},{-1, 6, 9},{3, 6, 9}};
  for(int i=0; i<n; i++){
      medians(triangles[i][0],triangles[i][1],triangles[i][2]);
  }
  */

  ifstream fin;
  fin.open("triangles.txt");
  if(fin.is_open()){
      int n;
      fin>>n;
      double triangles[n][3];
      int value;
      for(int i=0;i<n;i++){
          for(int j=0;j<3;j++){
          fin>>value;
          triangles[i][j]=value;
          }
      }
   fin.close();
   for(int i=0; i<n; i++){
       medians(triangles[i][0],triangles[i][1],triangles[i][2]);
      }
  }
}



