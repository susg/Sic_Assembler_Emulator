# Sic_Assembler_Emulator
SIC	-	ASSEMBLER	-	SIMULATOR
Prerequisites
You	need	to	have	JAVA	installed	in	your	machine.	This	is	the	easiest
version.	First	update	the	package	index.
$	sudo	apt-get	update
Then	check	if	java	is	intalled	or	not.
$	java	-version
If	it	returns	“The	program	java	can	be	found	in	the	following
packages”,	Java	hasn’t	been	installed	yet,	so	execute	the	following
command:
$	sudo	apt-get	install	default-jre
$	sudo	apt-get	install	default-jdk
That	is	everything	you	need	to	install	Java.
Run	the	simulator
First	get	into	the	folder	SIC-Assembler-Simulator	:cd	SIC-Assembler-Simulator/
Now	compile	the	program	:
javac	UI.java
Run	the	compiled	program	:
java	UI
You	will	notice	the	following	dialog	box	come	up	:
Enter	your	SIC	code	in	the	 Source	SIC	Program 	box.
Then	click	on	 Click	to	Assemble 	button.	After	the	program	gets
assembled	you	can	see	the	Object	Program	as	shown	below	:Now	click	on	 Click	to	Run 	button.
You	will	get	something	like	below	as	output	if	your	SIC	program	is
correct	:
Register	Values 	gives	us	the	value	stored	in	the	different
registers.
Symbol	Table 	gives	us	the	addresses	assigned	to	labels.
Object	Program 	gives	us	the	object	program	for	the	SIC-code.Memory	Trace 	shows	the	value	occupied	in	different	memory
locations.
File	Architecture
├──	ExecEngine.class
├──	ExecEngine.java
├──	logo.jpg
├──	MyMenuBar.class
├──	panel$1.class
├──	panel$2.class
├──	panel$3.class
├──	panel$4.class
├──	panel$5.class
├──	panel$actlist.class
├──	panel.class
├──	panel$savelist.class
├──	Readme.pdf
├──	Read_problem.pdf
├──	sicassem
│			├──	Assembler.class
│			├──	Assembler.java
│			├──	EndRecord.class
│			├──	EndRecord.java
│			├──	HeaderRecord.class
│			├──	HeaderRecord.java
│			├──	ObjectProg.class
│			├──	ObjectProg.java│			├──	Optable.class
│			├──	Optable.java
│			├──	SymTab.class
│			├──	SymTab.java
│			├──	TextRecord.class
│			└──	TextRecord.java
├──	Test.class
├──	Test.java
├──	UI.class
└──	UI.java
We	can	see	that	the	 sicassem 	folder	contains	the	codes	for	the
assembler	while	the	root	folder	has	the	codes	for	the	graphics	user
interface.
Feel	free	to	contribute.
