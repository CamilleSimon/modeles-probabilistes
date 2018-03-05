set term postscript eps enhanced color 15
set encoding utf8
set output "tp1-exercice1.eps"
set xrange [0:2.0]
set yrange [0:120]
set xlabel "l" font "Symbol,30"
set ylabel "Des données aléatoires"
set key Left reverse 
plot "courbe1.dat" t "histogrammes" with boxes, "courbe1.dat" t "lignes" with l lt 2
