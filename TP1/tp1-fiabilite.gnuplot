set term postscript eps enhanced color 15
set encoding utf8
set output "tp1-exercice3.eps"
set xrange [0:11]
set yrange [55:65]
set xlabel "l" font "Symbol,30"
set ylabel "Des données aléatoires"
set key Left reverse 
plot "Courbe3-100.dat" using 1:2 t "tirage aléatoire avec une fiabilité à 100" with l lt 2,"Courbe3-1000.dat" using 1:2 t "tirage aléatoire avec une fiabilité à 1000" with l lt 3,"Courbe3-10000.dat" using 1:2 t "tirage aléatoire avec une fiabilité à 10000" with l lt 4



