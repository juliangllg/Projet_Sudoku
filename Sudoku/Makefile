### VARIABLES ###

JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none

JVM = java
JVMFLAGS = 

### REGLES ESSENTIELLES ###

main.class : main.java menu.class
	${JC} ${JCFLAGS} main.java

grille.class : grille.java resolveurGrille.class 
	${JC} ${JCFLAGS} grille.java

menu.class : menu.java resolveurGrille.class grille.class
	${JC} ${JCFLAGS} menu.java

resolveurGrille.class : resolveurGrille.java
	${JC} ${JCFLAGS} resolveurGrille.java

### REGLES OPTIONNELLES ###

run : main.class
	${JVM} ${JVMFLAGS} main

clean :
	-rm -f *.class

mrproper : clean main.class

### BUTS FACTICES ###

.PHONY : run clean mrproper

### FIN ###