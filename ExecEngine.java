import sicassem.*;
import java.util.*;

public class ExecEngine{
//Execution engine that will have values for memory and registers
//Register fields
private int reg_A,reg_L,reg_PC,reg_X,reg_SW,strt,end,lastAddr;
private Hashtable<Integer,Integer> memory; //Memory hash map for easy access
private ObjectProg myObj;
public String final_output =new String();
//Constructor
public ExecEngine()
{
	memory=new Hashtable<Integer,Integer>();
}

public ExecEngine(ObjectProg ob)
{
	memory=new Hashtable<Integer,Integer>();
	myObj=ob;
}

//Helper method to initialise the memory
private void init()
{
	strt=myObj.getHeader().getStartAddr();
	end=strt+myObj.getHeader().getLength();
	for(int i=strt;i<=end;i++)
		memory.put(i,0xFF);
	reg_PC=strt;
	reg_SW=0;
}

//Getter and setter functions for memory
public void setMem(int addr,int val)
{
	memory.put(addr,val);
}

public int getMem(int addr)
{
	int val;
	val=memory.get(addr);
	return val;	
}

//Gettert methods for registers. Setting of register valuesis not allowed
int getA()
{	return reg_A;}

int getX()
{	return reg_X;}

int getL()
{	return reg_L;}

int getPC()
{	return reg_PC;}
int getSW()
{	return reg_SW;}


//Setting the object program
void setProg(ObjectProg p)
{	myObj=p;	}


//Method to begin the execution of the engine
//To be called after the object program is passed and begins the execution
//Version 1.0 - works only for load and store and basic arithmetic operations with direct addressing only
public void exec()
{
if(myObj==null)
{
	System.out.println("No  object program to execute !");
	return;
}
init();
ArrayList<TextRecord> txtList=myObj.getTextRecords();
for(TextRecord txt:txtList)
{	
	int strt=txt.getStart();
	reg_PC=strt;
	ArrayList<String> recs=txt.getRecord();
	for(String s:recs)
	{
		if(s.length()==6){
			//System.out.println("PC:"+reg_PC);
			reg_PC+=3;
			int val=Integer.parseInt(s,16);
			//System.out.println("loading instruction : "+s+" or "+val);
			int mask=0x00FF;
			int i=1;
			while(i<=3)
			{
				memory.put(reg_PC-i,(val&mask)>>(8*(i-1)));
				mask<<=8;
			
				///System.out.println((reg_PC-4+i)+" "+memory.get(reg_PC-4+i));
				i++;
			}
		}

		else if(s.length()!=6)
		{

			for (int i=0;i<s.length();i+=2 ) {

				int val=Integer.parseInt(s.substring(i,i+2),16);
				//System.out.println("loading instruction : "+s+" or "+val);
				memory.put(reg_PC++,val);
				//reg_PC=reg_PC+1;
			}
		}
	}
}
System.out.println("\n---------------------------------DEBUGGER----------------------------------------\n");
	lastAddr=reg_PC;
	reg_PC=strt;
	int currAddr;
	System.out.println("reg_PC   >====>"+reg_PC+"lastAddr >====>"+lastAddr);

	while(reg_PC<=lastAddr)
	{
		try{
		currAddr =reg_PC;
		reg_PC+=3;
		int opc,val1,val2,operand;
		opc =memory.get(currAddr);
		if (memory.get(currAddr+1)!=null&&memory.get(currAddr+1)!=null) {
			val1= memory.get(currAddr+1);
			val2=memory.get(currAddr+2);

			operand=(val1<<8)+val2;
			if (operand>0x8000) { //checking if INDEXED addressing mode..
				operand=(operand-0x8000)+reg_X;
			}
			//System.out.println("val1 => "+val1+"val2 => "+val2);
			System.out.println("Executing instruction : opcode ="+ opc+" opearnd = "+operand);
			if(operand!=getVal(operand)){
			perform(opc,operand);
			System.out.println("\n-----------------------------------------------\n|reg_A= "+reg_A+"|  reg_L= "+reg_L+"|  reg_X= "+reg_X+"| reg_SW= "+reg_SW+"\n-----------------------------------------------\n");
		}
	}
	   
	}catch(Exception e)
				{

					//System.out.println("AT PC  "+reg_PC+"  Execption occured--->>"+e);	
				}
	}
	/*memory.put(4162,72);
	
	for (int i =0;i<5 ;i++ ) {
		int a= memory.get(4158+i);	
		final_output +=(char)a;
	}
	System.out.println(final_output);*/
}


//Helper function to perform operation directed by the opcode
private void perform(int opc,int addr)
{
//System.out.println("Received in perform : opc:"+opc+"addr:"+addr); 
if(opc==0x00){ //LDA instruction code
	Integer value=memory.get(addr);
	if(value!=null)
		reg_A=getVal(addr);
	else
		System.out.println("Invalid address LDA instruction code!");
}
else if(opc==0x0C){//STA instruction code
	Integer value=memory.get(addr);
	if(value!=null)
		putMem(addr,reg_A);
	else
		System.out.println("Invalid address STA instruction code!");
}
else if(opc==0x04){//LDX instruction code
	Integer value=memory.get(addr);
	if(value!=null)
		reg_X=getVal(addr);
	else
		System.out.println("Invalid address LDX instruction code!");
}
else if(opc==0x10){//STX instruction code
	Integer value=memory.get(addr);
	if(value!=null)
		putMem(addr,reg_X);
	else
		System.out.println("Invalid address STX instruction code!");
}
else if(opc==0x08){//LDL instruction code
	Integer value=memory.get(addr);
	if(value!=null)
		reg_L=getVal(addr);
	if (value==0xFF) {
		reg_L=addr;
	}
	else
		System.out.println("Invalid address LDL instruction code!");
}
else if(opc==0x14){//STL instruction code
	Integer value=memory.get(addr);
	if(value!=null)
		putMem(addr,reg_L);
	else
		System.out.println("Invalid address STL instruction code!");
}
else if(opc==0x18){//ADD instruction code
	Integer value=memory.get(addr);
	if(value!=null)
		reg_A+=getVal(addr);
	else
		System.out.println("Invalid address /ADD instruction code !");
}
else if(opc==0x1C){//SUB instruction code
	Integer value=memory.get(addr);
	if(value!=null)
		reg_A-=getVal(addr);
	else
		System.out.println("Invalid address !SUB instruction code");
}
else if(opc==0x20){//MUL instruction code
	Integer value=memory.get(addr);
	if(value!=null)
		reg_A*=getVal(addr);
	else
		System.out.println("Invalid address !");
}
else if(opc==0x24){//DIV instruction code
	Integer value=memory.get(addr);
	if(value!=null)
		reg_A/=getVal(addr);
	else
		System.out.println("Invalid address !");
}
if(opc==0x50){ //LDCH instruction code
	Integer value=memory.get(addr);
	if(value!=null)
		reg_A=memory.get(addr);
	else
		System.out.println("Invalid address LDCH instruction code!");
}
else if(opc==0x54){//STCH instruction code
	Integer value=memory.get(addr);
	if(value!=null)
		memory.put(addr,reg_A);
	else
		System.out.println("Invalid address!STCH instruction code");
}
else if (opc==0x28) {//COMP instruction code
	Integer value = memory.get(addr);
	if (value!=null) {
		if (reg_A==getVal(addr)) {//if equal then
				reg_SW=0;	
		}
		else if (reg_A<getVal(addr)) {//if less than then set 7th bit
			reg_SW= 1<<7;
		}
		else //if greater than set 6 th bit
			reg_SW=1<<6;
	}
	else
		System.out.println("Invalid address!! in COMP");
}
else if (opc==0x3C){//j instruction
	Integer value = memory.get(addr);
	if (value!=null) {
		reg_PC = addr;
	}
	else
	System.out.println("Invalid address!! in J");
	
}
else if (opc==0x30) {//JEQ instruction
	Integer value = memory.get(addr);
	if (value!=null) {
		if (getSW()==0) {
			reg_PC = addr;
		}
	}
	else
	System.out.println("Invalid address!! in JEQ");
	
}
else if (opc==0x34) {//JGT instruction
	Integer value = memory.get(addr);
	if (value!=null) {
		if(reg_SW==1<<6){
			reg_PC=addr;

		}
	}
	else
	System.out.println("Invalid address!! in JGT");
	
}
else if (opc==0x38) {//JLT instruction
	Integer value = memory.get(addr);
	if (value!=null) {
		if(reg_SW==1<<7){
			reg_PC=addr;
		}
	}
	else
	System.out.println("Invalid address!! in JLT");
	
}
else if (opc==0x48) {//JSUB instruction
	Integer value = memory.get(addr);
	if (value!=null) {
		reg_L=reg_PC;
		reg_PC=addr;
	}
	else
	System.out.println("Invalid address!! in JSUB");
	
}
else if (opc==0x44) {//OR instruction
	Integer value = memory.get(addr);
	if (value!=null) {
		reg_A =(reg_A)| getVal(addr);
	}
	else
	System.out.println("Invalid address!! in OR");
	
}
else if (opc==0x40) {//AND instruction
	Integer value = memory.get(addr);
	if (value!=null) {
		reg_A =(reg_A)& getVal(addr);
	}
	else
	System.out.println("Invalid address!! in AND");
	
}
else if (opc==0x38) {//RSUB instruction
	
	reg_PC = reg_L;
}
else if (opc==0x2C) {//TIX instruction
	Integer value = memory.get(addr);
	if (value!=null) {
		reg_X =reg_X +1;
		if (reg_X==getVal(addr)) {
			reg_SW=0;
		}
		else if (reg_X<getVal(addr)) {
			reg_SW=1<<7;
		}
		else
			reg_SW =1<<6;
	}
	else
	System.out.println("Invalid address!! in TIX");
	
}

}

//Helper function for easy memory access
private void putMem(int addr,int val)
{
	memory.put(addr,(val&0xFF0000)>>16);
	memory.put(addr+1,(val&0x00FF00)>>8);
	memory.put(addr+2,(val&0x0000FF));
}

private int getVal(int addr)
{
int value=memory.get(addr);
value=value<<8;
value+=memory.get(addr+1);
value=value<<8;
value+=memory.get(addr+2);
//System.out.println("returning value for "+addr+"= "+value);
return value;
}

//Debugging method to print all data
public String debug()
{	
	StringBuilder string=new StringBuilder();
	/*System.out.println("Register values : ");
	System.out.println("A: "+reg_A);
	System.out.println("L: "+reg_L);
	System.out.println("X: "+reg_X);
	System.out.println("PC: "+reg_PC);
	System.out.println("Memory trace :");*/

	for(int i=strt;i<=end;i++)
		string.append(i+"  "+String.format("%02x",memory.get(i)).toUpperCase()+"\n");
	return string.toString();
}

}//End of ExecEngine class