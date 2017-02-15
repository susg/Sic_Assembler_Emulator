# Sic_Assembler_Emulator
# SIC - ASSEMBLER - SIMULATOR

### Contribution
- Contribution of Shubham Ranu : Designing OPTAB.
- Sushant Gupta : Assembler and their helper classes like ObjectCode,TextRecordClaass,EndRecord,HeaderRecord,Pass1,Pass2.
- Vineet Kumar and Hari Bhushan : ExecEngine Class (loader part and objectcode executer)  & GUI for Simulator.


### Prerequisites

You need to have [JAVA](https://www.java.com/en/) installed in your machine. This is the easiest version. First update the package index.
```sh
$ sudo apt-get update
```
Then check if java is intalled or not.
```sh
$ java -version
```
If it returns "The program java can be found in the following packages", Java hasn't been installed yet, so execute the following command:
```sh
$ sudo apt-get install default-jre
$ sudo apt-get install default-jdk
```
That is everything you need to install Java.

### Run the simulator
First get into the folder SIC-Assembler-Simulator :
```sh
cd SIC-Assembler-Simulator/
```
Now compile the program :
```sh
javac UI.java
```
Run the compiled program :
```sh
java UI
```
You will notice the following dialog box come up :

[![email.jpg](https://s21.postimg.org/g3fl4rc4n/email.jpg)](https://postimg.org/image/53udt5lpf/)

Enter your SIC code in the ***Source SIC Program*** box.
Then click on `Click to Assemble` button.
After that click on `Click to Run`.
You will get something like below as output if yout SIC program is correct :

[![0.png](https://s11.postimg.org/90vvy2mab/image.png)](https://postimg.org/image/nk30zhff3/)
 - `Register Values` gives us the value stored in the different registers.
 - `Symbol Table` defines the symbol used.
 - `Object Program` gives us the object code for the SIC-code.
 - `Memory Trace` shows the value occupied in different memory locations.
 
### File Architecture

```
.
├── deck.pdf
├── ExecEngine.class
├── ExecEngine.java
├── logo.jpg
├── MyMenuBar.class
├── panel$1.class
├── panel$2.class
├── panel$3.class
├── panel$4.class
├── panel$5.class
├── panel$actlist.class
├── panel.class
├── panel$savelist.class
├── Readme.pdf
├── sicassem
│   ├── Assembler.class
│   ├── Assembler.java
│   ├── EndRecord.class
│   ├── EndRecord.java
│   ├── HeaderRecord.class
│   ├── HeaderRecord.java
│   ├── ObjectProg.class
│   ├── ObjectProg.java
│   ├── Optable.class
│   ├── Optable.java
│   ├── SymTab.class
│   ├── SymTab.java
│   ├── TextRecord.class
│   └── TextRecord.java
├── STRING_REVERSE.png
├── Test.class
├── Test.java
├── UI.class
└── UI.java

```

We can see that the `sicassem` folder contains the codes for the assembler while the root folder has the codes for the graphics user interface.

___

 
