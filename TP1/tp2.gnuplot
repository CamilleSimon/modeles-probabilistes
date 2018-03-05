set term postscript eps enhanced color 15
set encoding utf8
set output "tp1-exercice2.eps"
set xrange [0:11]
set yrange [55:65]
set xlabel "l" font "Symbol,30"
set ylabel "Des données aléatoires"
set key Left reverse 
plot "Courbe3.dat" using 1:2 t "lignes" with l lt 2, "Courbe3.dat" using 1:3 t "lignes" with l lt 3
