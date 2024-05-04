### VARIABLES ###

JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none

JVM = java
JVMFLAGS = 

### REGLES ESSENTIELLES ###

mainProgramme2.class : mainProgramme2.java menuProgramme2.class
	${JC} ${JCFLAGS} mainProgramme2.java

mainProgramme1.class : mainProgramme1.java menuProgramme1.class
	${JC} ${JCFLAGS} mainProgramme1.java

Grille.class : Grille.java resolveurGrille.class JTextFieldCharLimit.class JeuFini.class
	${JC} ${JCFLAGS} Grille.java

menuProgramme2.class : menuProgramme2.java resolveurGrille.class Grille.class
	${JC} ${JCFLAGS} menuProgramme2.java

menuProgramme1.class : menuProgramme1.java resolveurGrille.class Grille.class
	${JC} ${JCFLAGS} menuProgramme1.java

resolveurGrille.class : resolveurGrille.java
	${JC} ${JCFLAGS} resolveurGrille.java

JTextFieldCharLimit.class : JTextFieldCharLimit.java
	${JC} ${JCFLAGS} JTextFieldCharLimit.java

JeuFini.class : JeuFini.java
	${JC} ${JCFLAGS} JeuFini.java

### REGLES OPTIONNELLES ###

editeur : mainProgramme1.class
	${JVM} ${JVMFLAGS} mainProgramme1

resolveur : mainProgramme2.class
	${JVM} ${JVMFLAGS} mainProgramme2
	
clean :
	-rm -f *.class

mrproper : clean mainProgramme2.class

### BUTS FACTICES ###

.PHONY : run clean mrproper

### FIN ###