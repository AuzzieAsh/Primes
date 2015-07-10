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

ifeq (run,$(firstword $(MAKECMDGOALS)))
  RUN_ARGS := $(wordlist 2,$(words $(MAKECMDGOALS)),$(MAKECMDGOALS))
  $(eval $(RUN_ARGS):;@:)
endif

default: classes

classes: $(CLASSES:.java=.class)

run: classes
	$(JVM) $(MAIN) $(RUN_ARGS)

clean:
	$(foreach dir, $(SUBDIRS),$(RM) $(dir)/*.class) 
