JFLAGS = -g
JC = javac
JVM = java
FILE = 
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

SUBDIRS = \
	main \
	primes
	
CLASSES = \
	main/Primes.java \
	primes/PrimeFunctions.java \
	primes/PrimeHandlers.java

MAIN = main/Primes

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(foreach dir, $(SUBDIRS),$(RM) $(dir)/*.class) 
