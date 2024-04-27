### VARIABLES ###

JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none

JVM = java
JVMFLAGS = 

### REGLES ESSENTIELLES ###

mainProgramme2.class : mainProgramme2.java menuProgramme2.class
	${JC} ${JCFLAGS} main.java

grille.class : grille.java resolveurGrille.class 
	${JC} ${JCFLAGS} grille.java

menuProgramme2.class : menuProgramme2.java resolveurGrille.class grille.class
	${JC} ${JCFLAGS} menu.java

resolveurGrille.class : resolveurGrille.java
	${JC} ${JCFLAGS} resolveurGrille.java

### REGLES OPTIONNELLES ###

run : mainProgramme2.class
	${JVM} ${JVMFLAGS} mainProgramme2

clean :
	-rm -f *.class

mrproper : clean mainProgramme2.class

### BUTS FACTICES ###

.PHONY : run clean mrproper

### FIN ###