import java.util.*;
import java.io.*;
public class PCB{
	
	public class Inst{
		
		char ch;
		float time; 
		public Inst(char c,float f){
			ch = c;
			time = f;
		}
	}

	public static int counter = 1000;		//Added int and Q. why 1000?ANs:for pid

	int pid;
	String name;
	boolean pause = false;
	
	Scanner file_scan;
	ArrayList<Inst> file_data = new ArrayList<Inst>();
	
	
	String instruction;
	int instruction_pointer=0;
	
	
	boolean hadResponded = false;
	
	float response_time;
	float in_time;
	float wait_time;
	float exit_time;
	float turn_around_time;
	float process_time;

	
	float new_to_ready_time;
	float remaining_time;
	float time_in_CPU;
	
	
	
	
	
	public PCB(String prog_name)throws FileNotFoundException
	{
		process_time=0;
		wait_time=0;
		turn_around_time=0;
		
		pid=++counter;
		name = prog_name;
		file_scan = new Scanner(new File("C:\\Users\\admin\\Desktop\\"+prog_name+".txt"));		//Changed File to FileReader	
		instruction_pointer=0;
		String readLine = file_scan.nextLine();
		
		this.new_to_ready_time=Float.parseFloat(readLine);
		
		read_file();
		this.remaining_time = file_data.get(instruction_pointer).time;
		time_in_CPU =0;
	}
	
	public int loadNextInstruction(){
		for(Inst i:file_data)
		{
			System.out.println(i.ch+"asdfg"+i.time);
		}
		instruction_pointer++;
		if(instruction_pointer < file_data.size())
		{
			this.remaining_time = file_data.get(instruction_pointer).time;
			switch(file_data.get(instruction_pointer).ch){
			case 'c' :
			{
				return 1;
				
			}
			case 'i' :
			{
				return -1;
				
			}
			case 'r' :
			{
				return 2;
				
			}
			case 'w' :
				{
					return 3;
					
				}
			default:
				return -2;
			}
			
		}
		
		return -2;
	}
	
	private  void read_file()
	{
		while(file_scan.hasNextLine())
		{
			String input = file_scan.nextLine();
			if(input.startsWith("CPU"))
			{
				Float time=Float.parseFloat(input.substring(4));
				file_data.add(new Inst('c',time));
				process_time+=time;
				
			}
			else
			{
				if(input.startsWith("IOI"))
				{
					Float time = Float.parseFloat(input.substring(4));
					file_data.add(new Inst('i',time));
				}
				
			}
		}
		
	/**	for(Inst i: file_data)
		{
			System.out.println(i.ch+"\t"+i.time);
		}
		**/
	}

	public boolean present_line_processed()
	{
		return remaining_time == 0;
	}

//returns 1 for CPU instruction
	//returns 0 for IO instruction
	//return -1 for present instruction not processed completely
	/*public int setProcessNext()
	{
		if(this.present_line_processed())
		{

			instruction=scan.nextLine();
			instruction_pointer++;
			time_rem_for_present_instr = get_time();
			if(instruction.startsWith("I"))
				return 0;
			else
				return 1;
		}
		return -1;

	}*/

	private float get_time()		//replaced int with float 
	{
		return Float.parseFloat(instruction.substring(4));
	}

	
	public void setRemainingTime()
	{
		char c = file_data.get(instruction_pointer).ch;
		int i=instruction_pointer;
		float remTime=0;
		while(i<file_data.size() && file_data.get(i).ch == c)
		{
			
			remTime+=file_data.get(i).time;
		}
		
		this.remaining_time = remTime;
	}
	public void get_ready_queue_length()
	{
		
	}


}
