### VARIABLES ###

JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none

JVM = java
JVMFLAGS = 

### REGLES ESSENTIELLES ###

main.class : mainProgramme2.java menuProgramme2.class
	${JC} ${JCFLAGS} main.java

grille.class : grille.java resolveurGrille.class 
	${JC} ${JCFLAGS} grille.java

menu.class : menuProgramme2.java resolveurGrille.class grille.class
	${JC} ${JCFLAGS} menu.java

resolveurGrille.class : resolveurGrille.java
	${JC} ${JCFLAGS} resolveurGrille.java

### REGLES OPTIONNELLES ###

run : mainProgramme2.class
	${JVM} ${JVMFLAGS} main

clean :
	-rm -f *.class

mrproper : clean mainProgramme2.class

### BUTS FACTICES ###

.PHONY : run clean mrproper

### FIN ###