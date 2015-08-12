import java.util.*;
import java.io.*;



public class temp{
	
static LinkedList<PCB> ready_queue = new LinkedList<PCB>();
static LinkedList<PCB> new_processes = new LinkedList<PCB>();
static LinkedList<PCB> waiting_queue = new LinkedList<PCB>();
static LinkedList<PCB> buffered_queue = new LinkedList<PCB>();
static mutex m = new mutex();
static int globalTime=0;
final static float cycle_time = (float) 0.5;
final static float SLOT_TIME = 5*cycle_time;
static PCB current_pcb1;
static PCB current_pcb2;
static PCB current_pcb3;
static PCB current_pcb4;
static boolean check = false;
static int index = 0;
static BufferedWriter writer = null;
static BufferedWriter writer_response=null;
static BufferedWriter writer_turn_around=null;
static BufferedWriter writer_processing_time=null;

public static void main(String[] argvs)throws FileNotFoundException //Added
{
	Scanner scan=new Scanner(System.in);
	
	
	File log_file = new File("logs.txt");
	File response_file = new File("resume.txt");
	File turn_around_file = new File("turn_around.txt");

	try {
		writer = new BufferedWriter(new FileWriter(log_file));
		writer_response = new BufferedWriter(new FileWriter(response_file));
		writer_turn_around = new BufferedWriter(new FileWriter(turn_around_file));

	
	
	
	while(true)
	{
		System.out.println("Type Command:");
		System.out.println("n for next iteration");
		System.out.println("create for creating a process");
		System.out.println("stop for stopping current process");
		System.out.println("pause for pausing execution of current process");
		System.out.println("resume for resuming current process");
		
		String input=scan.nextLine();	
		System.out.println(input);//Added
		if(input.equalsIgnoreCase("n"))		//changed to .equals Ignore case because strings can not be equated using ==
		{
			globalTime+=cycle_time;
			run();
			
		}
		else if(input.equalsIgnoreCase("create"))
		{
			globalTime+=cycle_time;
			System.out.println("P1 or P2? : ");
			String program = scan.nextLine();
			
			PCB new_pcb = new PCB(program);
			//2D array list will be better here
			new_processes.add(new_pcb);   
			// send to my thrread class which will handle new queues and asigning to any cpu  
			//System.out.println(program+"process created");
			writer.write(new_pcb.pid + " process added to new queue \n");
			writer.newLine();
			writer.flush();
			for(PCB p:new_processes)
			{
				System.out.println("outside"+p.new_to_ready_time);
				for(PCB.Inst i :p.file_data)
				{
					System.out.println(i.ch +"\t"+i.time);
				}
			}
			
			run();
			writer.write("------------------------------------------------- \n");
			writer.newLine();
			writer.flush();
			System.out.println("ready_queue");
			
			writer.write("Processes in Ready Queue");
			writer.newLine();
			writer.flush();
			for(PCB p :ready_queue)
			{
				System.out.println("have reached ");
				System.out.println(p.pid);
				writer.write(p.pid+"");
				writer.newLine();
				writer.flush();
			}
			System.out.println("waiting _queue");
			writer.write("Processes in waiting queue");
			writer.newLine();
			writer.flush();
			for(PCB p:waiting_queue)
			{
				System.out.println(p.pid);
				writer.write(p.pid+"");
				writer.newLine();
				writer.flush();
			}
			System.out.println("new_processes");
			writer.write("Processes in New Queue");
			writer.newLine();
			writer.flush();
			for(PCB p:new_processes)
			{
				System.out.println(p.pid);
				writer.write(p.pid+"");
				writer.newLine();
				writer.flush();
			}
			
			// Check this one  in all case          ////////////////////////////////////////////////// 
			if(current_pcb1 !=null)
			{	
				System.out.println("Process pid:"+current_pcb1.pid + "type: running");
				writer.write("CPU 1 : Process pid: "+current_pcb1.pid + "type: running");
				writer.newLine();
				writer.flush();
			}
			if(current_pcb2 !=null)
			{	System.out.println("Process in running state is:");
				System.out.println("Process pid:"+current_pcb2.pid + "type: running");
				writer.write("CPU 2 : Process pid: "+current_pcb2.pid + "type: running");
				writer.newLine();
				writer.flush();
				
			}
			if(current_pcb3 !=null)
			{	System.out.println("Process in running state is:");
				System.out.println("Process pid:"+current_pcb3.pid + "type: running");
				writer.write("CPU 3 : Process pid: "+current_pcb3.pid + "type: running");
				writer.newLine();
				writer.flush();
			}
			if(current_pcb4 !=null)
			{	System.out.println("Process in running state is:");
				System.out.println("Process pid:"+current_pcb4.pid + "type: running");
				writer.write("CPU 4 : Process pid: "+current_pcb4.pid + "type: running");
				writer.newLine();
				writer.flush();
			}
			
		}
		else if(input.equalsIgnoreCase("stop"))
		{
			globalTime+=cycle_time;
			writer.write("------------------------------------------------- \n");
			writer.newLine();
			writer.flush();
			System.out.println("1. for stopping_by PID");
			System.out.println("2. for stopping the running process");
			int choice=scan.nextInt();
			scan.nextLine();
			
			switch(choice){
				case 1 :
				{
					
					if(current_pcb1 !=null)
					{	System.out.println("Process in running state is:");
						System.out.println("Process pid:"+current_pcb1.pid + "type: running");
					}
					if(current_pcb2 !=null)
					{	System.out.println("Process in running state is:");
						System.out.println("Process pid:"+current_pcb2.pid + "type: running");
					}
					if(current_pcb3 !=null)
					{	System.out.println("Process in running state is:");
						System.out.println("Process pid:"+current_pcb3.pid + "type: running");
					}
					if(current_pcb4 !=null)
					{	System.out.println("Process in running state is:");
						System.out.println("Process pid:"+current_pcb4.pid + "type: running");
					}
					System.out.println("Processes in ready state are");
					List<PCB> l = new ArrayList(ready_queue);
					for(PCB p:l)
					{
						System.out.println("Process pid:"+p.pid+"type: ready");
					}
					System.out.println("Processes in waiting state are");
					for(PCB p:waiting_queue)
					{
						System.out.println("Process pid:"+p.pid+"type: waiting");
					}
					System.out.println("Processes in new state are ");
					for(PCB p:new_processes)
					{
						System.out.println("Process pid:"+p.pid+"type: new");
					}
					
					System.out.println("Enter pid");
					int to_stop=scan.nextInt();
					scan.nextLine();
					
					if(current_pcb1 !=null && current_pcb1.pid == to_stop)
					{
						System.out.println("1. running process stopped");
						//print pcb contents
						writer.write(current_pcb1.pid + " was stopped from running and was terminated due to terminate abnormally \n");
						writer.newLine();
						writer.flush();
						current_pcb1=null;
					}
					else
					if(current_pcb2!=null && current_pcb2.pid == to_stop)
						{
							System.out.println("2. running process stopped");
							//print pcb contents
							
							writer.write(current_pcb2.pid + " was stopped from running and was terminated due to terminate abnormally \n");
							writer.newLine();
							writer.flush();
							current_pcb2=null;
						}
					else
					if(current_pcb3 !=null && current_pcb3.pid == to_stop)
							{
								System.out.println("3. running process stopped");
								//print pcb contents
								writer.write(current_pcb3.pid + " was stopped from running and was terminated due to terminate abnormally \n");
								writer.newLine();
								writer.flush();
								current_pcb3=null;
							}
					else
						if(current_pcb4 !=null && current_pcb4.pid == to_stop)
								{
									System.out.println("4. running process stopped");
									//print pcb contents
									writer.write(current_pcb4.pid + " was stopped from running and was terminated due to terminate abnormally \n");
									writer.newLine();
									writer.flush();
									current_pcb4=null;
								}
					
					else 
					{
						for(PCB p:l)
						{
							
							if(p.pid==to_stop)
							{
								check = true;
								//delete from the queue
								l.remove(index);
								System.out.println(p.pid+" process has been stopped from ready_queue");
								writer.write(p.pid + " was stopped from ready queue and was terminated \n");
								writer.newLine();
								writer.flush();
							}
							index ++;
						}
						index =0;
						if(check == false)
						{
							
							for(PCB p:waiting_queue)
							{
								if(p.pid==to_stop)
								{
									check = true;
									waiting_queue.remove(index);
									System.out.println(p.pid+" process has been stopped from waiting queue");
									//delete from the queue
									writer.write(p.pid + " was stopped from waiting queue and was terminated \n");
									writer.newLine();
									writer.flush();
								}
								index++;
							}
						}
						index =0;
						if(check == false)
						{
							for(PCB p:new_processes)
							{
								if(p.pid==to_stop)
								{
									check = true;
									//delete from the queue
									new_processes.remove(index);
									System.out.println(p.pid+" process has been stopped from new processes");
									writer.write(p.pid + " was stopped from new processes and was terminated \n");
									writer.newLine();
									writer.flush();
								}
								index ++;
							}
						}
						index =0;
						if(check == false)
						{
							for(PCB p:ready_queue)
							{
								System.out.println("Came here");
								if(p.pid==to_stop)
								{
									check = true;
									ready_queue.remove();
									System.out.println(p.pid+" process has been stopped from ready queue");
									//delete from the queue
									writer.write(p.pid + " was stopped from ready queue and was terminated \n ");
									writer.newLine();
									writer.flush();
								}
								index++;
							}
						}
						index =0;
						if (check ==false)
						
						{
							System.out.println("Please choose pid from the above list");
						}
						
					}
				
				}
				break;
			
				case 2:
				{
					System.out.println("running process stopped");
					//print pcb contents
					if(current_pcb1 !=null)
					{	
						System.out.println("Process pid:"+current_pcb1.pid + "type: running");
					}
					if(current_pcb2 !=null)
					{	System.out.println("Process in running state is:");
						System.out.println("Process pid:"+current_pcb2.pid + "type: running");
					}
					if(current_pcb3 !=null)
					{	System.out.println("Process in running state is:");
						System.out.println("Process pid:"+current_pcb3.pid + "type: running");
					}
					if(current_pcb4 !=null)
					{	System.out.println("Process in running state is:");
						System.out.println("Process pid:"+current_pcb4.pid + "type: running");
					}
					System.out.println("Enter the pid");
					int in=scan.nextInt();
					scan.nextLine();
					if(current_pcb1!=null && current_pcb1.pid==in)
					{
						current_pcb1=null;
						System.out.println("the process in cpu 1 is termintated");
						writer.write("the process in cpu 1 is termintated");
						writer.newLine();
						writer.flush();
					}else if(current_pcb2!=null && current_pcb2.pid==in)
					{
						current_pcb2=null;
						System.out.println("the process in cpu 2 is terminated");
						writer.write("the process in cpu 2 is termintated");
						writer.newLine();
						writer.flush();
					}
					else if(current_pcb3!=null && current_pcb3.pid==in)
					{
						current_pcb3=null;
						System.out.println("the process in cpu 3 is terminated");
						writer.write("the process in cpu 3 is termintated");
						writer.newLine();
						writer.flush();
					}
					else if(current_pcb4!=null && current_pcb4.pid==in)
					{
						current_pcb4=null;
						System.out.println("the process in cpu 4 is terminated");
						writer.write("the process in cpu 4 is termintated");
						writer.newLine();
						writer.flush();
					}
					else
					{
						System.out.println("the proces pid is not running");
					}
					
				}
			}
			
			run();
			
			writer.write("------------------------------------------------- \n");
			writer.newLine();
			writer.write("After Stopping: ");
			writer.flush();
			System.out.println("ready_queue");
			
			writer.write("Processes in Ready Queue");
			writer.newLine();
			writer.flush();
			for(PCB p :ready_queue)
			{
				System.out.println("have reached ");
				System.out.println(p.pid);
				writer.write(p.pid+"");
				writer.newLine();
				writer.flush();
			}
			System.out.println("waiting _queue");
			writer.write("Processes in waiting queue");
			writer.newLine();
			writer.flush();
			for(PCB p:waiting_queue)
			{
				System.out.println(p.pid);
				writer.write(p.pid+"");
				writer.newLine();
				writer.flush();
			}
			System.out.println("new_processes");
			writer.write("Processes in New Queue");
			writer.newLine();
			writer.flush();
			for(PCB p:new_processes)
			{
				System.out.println(p.pid);
				writer.write(p.pid+"");
				writer.newLine();
				writer.flush();
			}
			
			if(current_pcb1 !=null)
			{	
				System.out.println("Process pid:"+current_pcb1.pid + "type: running");
				writer.write("CPU 1 : Process pid: "+current_pcb1.pid + "type: running");
				writer.newLine();
				writer.flush();
			}
			if(current_pcb2 !=null)
			{	System.out.println("Process in running state is:");
				System.out.println("Process pid:"+current_pcb2.pid + "type: running");
				writer.write("CPU 2 : Process pid: "+current_pcb2.pid + "type: running");
				writer.newLine();
				writer.flush();
				
			}
			if(current_pcb3 !=null)
			{	System.out.println("Process in running state is:");
				System.out.println("Process pid:"+current_pcb3.pid + "type: running");
				writer.write("CPU 3 : Process pid: "+current_pcb3.pid + "type: running");
				writer.newLine();
				writer.flush();
			}
			if(current_pcb4 !=null)
			{	System.out.println("Process in running state is:");
				System.out.println("Process pid:"+current_pcb4.pid + "type: running");
				writer.write("CPU 4 : Process pid: "+current_pcb4.pid + "type: running");
				writer.newLine();
				writer.flush();
			}
			
			
			//We have to take one option 
			//1. Ask User for PID or 
			//2. Ask user for Process type and stop first/last created process of that type
			//3. Stop Currently running Process(Most Appropriate) 
			//new_processes.remove(Object with Decided PID);
			
			//System.out.println("P1 or P2 Terminated");
			//delete the current pcb
		}
		
		else if(input.equalsIgnoreCase("pause"))
		{
			globalTime+=cycle_time;
			writer.write("---------------------------------------------------------------- \n");
			writer.newLine();
			writer.flush();
			System.out.println("1. for pausing_by PID");
			System.out.println("2. for pausing the running process");
			int choice=scan.nextInt();
			scan.nextLine();
			
			switch(choice){
			case 1 :
			{
				System.out.println("Process in running state is:");
				if(current_pcb1 !=null)
				{	
					System.out.println("Process pid:"+current_pcb1.pid + "type: running");
				}
				if(current_pcb2 !=null)
				{	System.out.println("Process in running state is:");
					System.out.println("Process pid:"+current_pcb2.pid + "type: running");
				}
				if(current_pcb3 !=null)
				{	System.out.println("Process in running state is:");
					System.out.println("Process pid:"+current_pcb3.pid + "type: running");
				}
				if(current_pcb4 !=null)
				{	System.out.println("Process in running state is:");
					System.out.println("Process pid:"+current_pcb4.pid + "type: running");
				}
					
				System.out.println("Processes in ready state are");
				List<PCB> l = new ArrayList(ready_queue);
				for(PCB p:l)
				{
					System.out.println("Process pid:"+p.pid+"type: ready");
				}
				System.out.println("Processes in waiting state are");
				for(PCB p:waiting_queue)
				{
					System.out.println("Process pid:"+p.pid+"type: waiting");
				}
				System.out.println("Processes in new state are ");
				for(PCB p:new_processes)
				{
					System.out.println("Process pid:"+p.pid+"type: new");
				}
				
				System.out.println("Enter pid");
				int to_stop=scan.nextInt();
				scan.nextLine();
				System.out.println(to_stop+"+++");
				if(current_pcb1 !=null && current_pcb1.pid == to_stop )
				{
					System.out.println(" running process paused");
					//print pcb contents
					writer.write(current_pcb1.pid+" was paused from running in CPU 1 and added to ready queue in paused state \n");
					writer.newLine();
					writer.flush();
					current_pcb1.pause=true;
					ready_queue.add(current_pcb1);
					current_pcb1 = null;
				}
				else
					if(current_pcb2 !=null && current_pcb2.pid == to_stop)
					{
						System.out.println(" 2 running process paused");
						//print pcb contents
						writer.write(current_pcb2.pid+" was paused from running in CPU 2 and added to ready queue in paused state \n");
						writer.newLine();
						writer.flush();
						current_pcb2.pause=true;
						ready_queue.add(current_pcb2);
						current_pcb2 = null;
					}
				else
				if(current_pcb3 !=null && current_pcb3.pid == to_stop)
				{
					System.out.println(" 3 running process paused");
					//print pcb contents
					writer.write(current_pcb3.pid+" was paused from running in CPU 3 and added to ready queue in paused state \n");
					writer.newLine();
					writer.flush();
					current_pcb3.pause=true;
					ready_queue.add(current_pcb3);
					current_pcb3 = null;
				}
				else
					if(current_pcb4 !=null && current_pcb4.pid == to_stop)
					{
						System.out.println("4 running process paused");
						//print pcb contents
						writer.write(current_pcb4.pid+" was paused from running in CPU 4 and added to ready queue in paused state \n");
						writer.newLine();
						writer.flush();
						current_pcb4.pause=true;
						ready_queue.add(current_pcb4);
						current_pcb4 = null;
					}
				else 
				{
					for(PCB p:l)
					{
						
						if(p.pid==to_stop)
						{
							check = true;
							p.pause = true;
							System.out.println(p.pid+" process has been paused from ready_queue");
							writer.write(p.pid+" was paused in ready state \n");
							writer.newLine();
							writer.flush();
						}
					}
					if(check == false)
					{
						
						for(PCB p:waiting_queue)
						{
							if(p.pid==to_stop)
							{
								check = true;
								p.pause = true;
								System.out.println(p.pid+" process has been paused from waiting queue");
								writer.write(p.pid+" was paused in ready state \n");
								writer.newLine();
								writer.flush();
							}
							
						}
					}
					
					if(check == false)
					{
						for(PCB p:new_processes)
						{
							if(p.pid==to_stop)
							{
								check = true;
								p.pause = true;
								System.out.println(p.pid+" process has been paused from new processes");
								writer.write(p.pid+" was paused in new state \n");
								writer.newLine();
								writer.flush();
							}
							
						}
					}
					
					if(check == false)
					{
						for(PCB p:ready_queue)
						{
							if(p.pid==to_stop)
							{
								check = true;
								p.pause = true;
								System.out.println(p.pid+" process has been paused from ready queue");
								writer.write(p.pid+" was paused in ready state \n");
								writer.newLine();
								writer.flush();
							}
							
						}
					}
				
					if (check ==false)
					
					{
						System.out.println("Please choose pid from the above list");
					}
					check = false;
				}
			}
			break;
			case 2:
			{
				
				System.out.println(" forrunning process paused");
				//print pcb contents
				if(current_pcb1 !=null)
				{	
					System.out.println("Process pid:"+current_pcb1.pid + "type: running");
				}
				if(current_pcb2 !=null)
				{	System.out.println("Process in running state is:");
					System.out.println("Process pid:"+current_pcb2.pid + "type: running");
				}
				if(current_pcb3 !=null)
				{	System.out.println("Process in running state is:");
					System.out.println("Process pid:"+current_pcb3.pid + "type: running");
				}
				if(current_pcb4 !=null)
				{	System.out.println("Process in running state is:");
					System.out.println("Process pid:"+current_pcb4.pid + "type: running");
				}
				System.out.println("Enter the pid");
				int in=scan.nextInt();
				scan.nextLine();
				if(current_pcb1!=null && current_pcb1.pid==in)
				{
					writer.write(current_pcb1.pid+" was paused from running state in CPU 1 and added to ready queue in paused state \n");
					writer.newLine();
					writer.flush();
					current_pcb1.pause = true;
					ready_queue.add(current_pcb1);
					current_pcb1 = null;
					System.out.println("the process in cpu 1 is paused");
				}else if(current_pcb2!=null && current_pcb2.pid==in)
				{
					writer.write(current_pcb2.pid+" was paused from running state in CPU 2 and added to ready queue in paused state \n");
					writer.newLine();
					writer.flush();
					current_pcb2.pause = true;
					ready_queue.add(current_pcb2);
					current_pcb2 = null;
					System.out.println("the process in cpu 2 is paused");
				}
				else if(current_pcb3!=null && current_pcb3.pid==in)
				{
					writer.write(current_pcb3.pid+" was paused from running state in CPU 3 and added to ready queue in paused state \n");
					writer.newLine();
					writer.flush();
					current_pcb3.pause = true;
					ready_queue.add(current_pcb3);
					for(PCB p: ready_queue)
					{
						System.out.println(p.pid+"+");
					}
					
					current_pcb3 = null;
					System.out.println("the process in cpu 3 is paused");
				}
				else if(current_pcb4!=null && current_pcb4.pid==in)
				{
					writer.write(current_pcb4.pid+" was paused from running state in CPU 4 and added to ready queue in paused state \n");
					writer.newLine();
					writer.flush();
					current_pcb4.pause = true;
					ready_queue.add(current_pcb4);
					current_pcb4 = null;
					System.out.println("the process in cpu 4 is paused");
				}
				else
				{
					System.out.println("the proces pid is not running");
				}
			
			}
			
		}
			
			run();
			
			writer.write("------------------------------------------------- \n");
			writer.newLine();
			writer.flush();
			System.out.println("ready_queue");
			writer.write("After pausing : \n");
			writer.newLine();
			writer.write("Processes in Ready Queue: \n");
			writer.newLine();
			writer.flush();
			
			for(PCB p :ready_queue)
			{
				System.out.println("have reached ");
				System.out.println(p.pid);
				writer.write("abcdef");
				writer.write(p.pid+"");
				writer.newLine();
				writer.flush();
			}
			System.out.println("waiting _queue");
			writer.write("Processes in waiting queue");
			writer.newLine();
			writer.flush();
			for(PCB p:waiting_queue)
			{
				System.out.println(p.pid);
				writer.write(p.pid+"");
				writer.newLine();
				writer.flush();
			}
			System.out.println("new_processes");
			writer.write("Processes in New Queue");
			writer.newLine();
			writer.flush();
			for(PCB p:new_processes)
			{
				System.out.println(p.pid);
				writer.write(p.pid+"");
				writer.newLine();
				writer.flush();
			}
			
			if(current_pcb1 !=null)
			{	
				System.out.println("Process pid:"+current_pcb1.pid + "type: running");
				writer.write("CPU 1 : Process pid: "+current_pcb1.pid + "type: running");
				writer.newLine();
				writer.flush();
			}
			if(current_pcb2 !=null)
			{	System.out.println("Process in running state is:");
				System.out.println("Process pid:"+current_pcb2.pid + "type: running");
				writer.write("CPU 2 : Process pid: "+current_pcb2.pid + "type: running");
				writer.newLine();
				writer.flush();
			}
			if(current_pcb3 !=null)
			{	System.out.println("Process in running state is:");
				System.out.println("Process pid:"+current_pcb3.pid + "type: running");
				writer.write("CPU 3 : Process pid: "+current_pcb3.pid + "type: running");
				writer.newLine();
				writer.flush();
			}
			if(current_pcb4 !=null)
			{	System.out.println("Process in running state is:");
				System.out.println("Process pid:"+current_pcb4.pid + "type: running");
				writer.write("CPU 4 : Process pid: "+current_pcb4.pid + "type: running");
				writer.newLine();
				writer.flush();
			}
			//3D array list will be good so the 3rd column will hold whether the process is suspended or not?
			//This will be helpful to us in resuming to and holding any other type of states.e.g Block		
			//System.out.println("P1 or P2 Paused");
			//send the present pcb to paused stack
			
		}
		else if(input.equalsIgnoreCase("resume"))
		{
			globalTime+=cycle_time;
			writer.write("------------------------------------------------- \n");
			writer.newLine();
			writer.flush();
			System.out.println("1. for resuming_by PID");
			//System.out.println("2. for resuming the running process");
			int choice=scan.nextInt();
			scan.nextLine();
			
			switch(choice){
			case 1 :
			{
				System.out.println("Processes in ready state are");
				List<PCB> l = new ArrayList(ready_queue);
				for(PCB p:l)
				{
					System.out.println("tttttt");
					System.out.println("Process pid:"+p.pid+"type: ready");
				}
				System.out.println("Processes in waiting state are");
				for(PCB p:waiting_queue)
				{
					System.out.println("tttttt");
					System.out.println("Process pid:"+p.pid+"type: waiting");
				}
				System.out.println("Processes in new state are ");
				for(PCB p:new_processes)
				{
					System.out.println("tttttt");
					System.out.println("Process pid:"+p.pid+"type: new");
				}
				
				System.out.println("Enter pid");
				int to_stop=scan.nextInt();
				scan.nextLine();
				for(PCB p:l)
				{
						
					if(p.pid==to_stop)
					{
						check = true;
						p.pause = false;
						System.out.println(p.pid+" process has been resumed from ready_queue");
						writer.write(p.pid+"was resumed from pause state in ready queue \n");
						writer.newLine();
						writer.flush();
					}
				}
					if(check == false)
					{
						
						for(PCB p:waiting_queue)
						{
							if(p.pid==to_stop)
							{
								check = true;
								p.pause = false;
								System.out.println(p.pid+" process has been resumed from waiting queue");
								writer.write(p.pid+"was resumed from pause state in waiting queue \n");							
								writer.newLine();
								writer.flush();
							}
							
						}
					}
					if(check == false)
					{
						for(PCB p:new_processes)
						{
							if(p.pid==to_stop)
							{
								check = true;
								p.pause = false;
								System.out.println(p.pid+" process has been resumed new processes");
								writer.write(p.pid+"was resumed from pause state in new queue \n");
								writer.newLine();
								writer.flush();
							}
							
						}
					}
					
					if(check == false)
					{
						for(PCB p:ready_queue)
						{
							if(p.pid==to_stop)
							{
								check = true;
								p.pause = false;
								System.out.println(p.pid+" process has been resumed from ready queue");
								writer.write(p.pid+"was resumed from pause state in ready queue \n");
								writer.newLine();
								writer.flush();
							}
							
						}
					}
				
					if (check ==false)
					
					{
						System.out.println("Please choose pid from the above list");
					}
					
					check = false;
				//}
				run();
				writer.write("------------------------------------------------- \n");
				writer.newLine();
				writer.write("After Resuming\n");
				writer.newLine();
				writer.flush();
				
				writer.write("Processes in Ready queue");
				writer.newLine();
				writer.flush();
				for(PCB p :ready_queue)
				{
					System.out.println("have reached ");
					System.out.println(p.pid);
					writer.write(p.pid+"");
					writer.newLine();
					writer.flush();
				}	
				System.out.println("waiting _queue");
				writer.write("Processes in waiting queue");
				writer.newLine();
				for(PCB p:waiting_queue)
				{
					System.out.println(p.pid);
					writer.write(p.pid+"");
					writer.newLine();
					writer.flush();
				}
				System.out.println("new_processes");
				writer.write("Processes in New Queue");
				writer.newLine();
				writer.flush();
				for(PCB p:new_processes)
				{
					System.out.println(p.pid);
					writer.write(p.pid+"");
					writer.newLine();
					writer.flush();
				}
				
				if(current_pcb1 !=null)
				{	
					System.out.println("Process pid:"+current_pcb1.pid + "type: running");
					writer.write("CPU 1 : Process pid: "+current_pcb1.pid + "type: running");
					writer.newLine();
					writer.flush();
				}
				if(current_pcb2 !=null)
				{	System.out.println("Process in running state is:");
					System.out.println("Process pid:"+current_pcb2.pid + "type: running");
					writer.write("CPU 2 : Process pid: "+current_pcb2.pid + "type: running");
					writer.newLine();
					writer.flush();
				}
				if(current_pcb3 !=null)
				{	System.out.println("Process in running state is:");
					System.out.println("Process pid:"+current_pcb3.pid + "type: running");
					writer.write("CPU 3 : Process pid: "+current_pcb3.pid + "type: running");
					writer.newLine();
					writer.flush();
				}
				if(current_pcb4 !=null)
				{	System.out.println("Process in running state is:");
					System.out.println("Process pid:"+current_pcb4.pid + "type: running");
					writer.write("CPU 4 : Process pid: "+current_pcb4.pid + "type: running");
					writer.newLine();
					writer.flush();
				}
				}//case
			}//switch
		}//elseif
		
		
	}//while
	
}//try
catch(Exception e)
{
	System.out.println("Exception caught while opening a file");
}
finally{
	try{
	writer.close();
	}catch(Exception e)
	{
		System.out.println("File cant close");
	}
}
}//main

public static void run() throws IOException
{
	System.out.println("New iteration started");
	writer.write("------------------------------------------------ \n");
	writer.newLine();
	writer.write("New CPU CYCLE STARTED \n");
	writer.newLine();
	writer.flush();
	longTermScheduling();
	mediumTermScheduling();
	shortTermScheduling();
	

}
public static void longTermScheduling(){
	if(!new_processes.isEmpty())
	{
		List<Integer> l =new ArrayList();
	
		for(int i=0;i<new_processes.size();i++)
		{
	
		System.out.println("long before "+new_processes.get(i).new_to_ready_time);
		PCB p=new_processes.get(i);
		if(p.pause==false)
			p.new_to_ready_time-=cycle_time;
		
		System.out.println("long after"+new_processes.get(i).new_to_ready_time);
		if(p.new_to_ready_time <= 0.0)
		{
			System.out.println("new to ready queue"+p.pid);
			ready_queue.add(p);
			l.add(i);
			System.out.println("ll" + l);
			
			

		}
		
		}
		
		
		
		for(int i=0;i<l.size();i++)
		{
			int index = l.get(i);
			System.out.println(index-i);
			PCB p = new_processes.remove(index-i);
			System.out.println(p.pid+"is removal");
			
			
		}
		System.out.println("after removal in mew");
		for(PCB p:new_processes)
		{
			System.out.println(p.pid);
		}
		
	
	}
			
}

public static void shortTermScheduling() throws IOException
	{
	shortTermScheduling1();
	shortTermScheduling2();
	shortTermScheduling3();
	shortTermScheduling4();
	
	}

public static void shortTermScheduling1() throws IOException
{

	
	System.out.println("ready_queue in short1");
	for(PCB p :ready_queue)
	{
		System.out.println("have reached ");
		System.out.println(p.pid);
	}
		if(current_pcb1 == null)
		{
			if(ready_queue.size()>0)
			{	System.out.println("Queeu size greater than zero");
				int upto=0;
				PCB temp = ready_queue.remove();
				while(upto <= ready_queue.size() && temp.pause == true){
					System.out.println("temp is pause by 1"+temp.pid);
					ready_queue.add(temp);
					temp=ready_queue.remove();
					upto++;
				}
				if(upto <=ready_queue.size())
					{
					
					System.out.println(current_pcb1+"====");
					current_pcb1 =temp;
					if(!current_pcb1.hadResponded)
					{
						current_pcb1.hadResponded = true;
						current_pcb1.response_time=globalTime-current_pcb1.in_time;
						writer_response.write("reponse time of the process is :"+current_pcb1.response_time+" is "+current_pcb1.response_time);
						writer_response.newLine();
						writer_response.flush();
					}
					System.out.println("1.current pcb 1 is set to"+current_pcb1.pid);
					
					if(current_pcb1!=null)
						System.out.println("current_pcb1"+ current_pcb1.pid);
						if(current_pcb2!=null)
							System.out.println("current_pcb2"+ current_pcb2.pid);
							
						if(current_pcb3!=null)
							System.out.println("current_pcb3"+ current_pcb3.pid);
							
						if(current_pcb4!=null)
							System.out.println("current_pcb4"+ current_pcb4.pid);
					
					}
				else
				{
					ready_queue.add(temp);
				}
				
			}
		}
		
		if(current_pcb1!=null)
		{
		System.out.println(current_pcb1.remaining_time+"$$");
		System.out.println(current_pcb1.time_in_CPU+"@@");
		}
		if(current_pcb1!=null)
		{
			System.out.println("pid:"+current_pcb1.pid);
			System.out.println("remaining time:"+current_pcb1.remaining_time);
			System.out.println("time_in_CPU:"+current_pcb1.time_in_CPU);
		}
		if(current_pcb1 != null)
		{
			if(current_pcb1.remaining_time >= cycle_time && current_pcb1.time_in_CPU+cycle_time<=SLOT_TIME)
			{
				System.out.println("short 1 case 1 od sts");
				current_pcb1.remaining_time-=cycle_time;
			//	if(current_pcb1.remaining_time<=0)
				//	current_pcb1.loadNextInstruction();
				current_pcb1.time_in_CPU+=cycle_time;
										
			}
			else
			if(current_pcb1.remaining_time >= cycle_time && current_pcb1.time_in_CPU+cycle_time >SLOT_TIME)
			{
				System.out.println("short 1 case 2 of sts");
				current_pcb1.remaining_time-=(SLOT_TIME-current_pcb1.time_in_CPU);
				current_pcb1.time_in_CPU = 0;
				ready_queue.add(current_pcb1);
				current_pcb1 = null;
			}
			else
			if(current_pcb1.remaining_time < cycle_time && current_pcb1.time_in_CPU +cycle_time <= SLOT_TIME)
			{
				
				System.out.println(current_pcb1.remaining_time+"###");
				System.out.println(current_pcb1.time_in_CPU+"##");
				
				System.out.println("short 1 case 3 of sts");
				float left_time = cycle_time-current_pcb1.remaining_time;
				current_pcb1.time_in_CPU+=current_pcb1.remaining_time;
				
				System.out.println(current_pcb1.remaining_time+"###111");
				System.out.println(current_pcb1.time_in_CPU+"##111");
				
				
				
				do
				{	
					int next_inst_value = current_pcb1.loadNextInstruction();
					if(next_inst_value == 1){
					
					for(PCB.Inst i:current_pcb1.file_data)
					{
						System.out.println(i.ch+"asdfg"+i.time);
					}
					
					System.out.println("short 1 next instruction is cPU");
					float temp=Math.min(current_pcb1.remaining_time,left_time);
					current_pcb1.time_in_CPU+=temp;
					
					current_pcb1.remaining_time-=temp;
					left_time-=temp;
					
					System.out.println(current_pcb1.remaining_time+"***");
					System.out.println(current_pcb1.time_in_CPU+"***");
					
					if(current_pcb1.remaining_time>0 && current_pcb1.time_in_CPU+cycle_time <	SLOT_TIME)
					{
						System.out.println("short 1 Current Pcb remain same");
					}
					else if(current_pcb1.remaining_time>0 && current_pcb1.time_in_CPU+cycle_time >=SLOT_TIME)
					{
						System.out.println("short 1 added to ready queue because of left remaing time in case 3");
						ready_queue.add(current_pcb1);
						current_pcb1 = null;
						break;
					}
					
				}
				else{
					if(next_inst_value == -1)
					{
					System.out.println("short 1 added to wait queue as next instruction is IO");
					//System.out.println("present insrt"+current_pcb1.file_data.get(current_pcb1.instruction_pointer));
					waiting_queue.add(current_pcb1);
					}
					else if(next_inst_value ==2 || next_inst_value == 3)
					{
						
						if(next_inst_value == 2)
						{
							if(!m.mutex_in_read())
							
							{
								buffered_queue.add(current_pcb1);
								current_pcb1=null;
							}
							else
							m.mutex_out_read();
							
						}
						if(next_inst_value == 3)
						{
							if(!m.mutex_in_write())
							{
								buffered_queue.add(current_pcb1);
								current_pcb1=null;
							}
							else
							m.mutex_out_write();
						}
					}
					else
					{
					System.out.println("Current process 1 "+current_pcb1.pid+" terminates execution");
					System.out.println("Current process"+current_pcb1.pid+" terminates execution");
					writer.write(current_pcb1.pid+"terminated execution normally \n");
					current_pcb1.exit_time=globalTime;
					current_pcb1.turn_around_time=current_pcb1.exit_time=current_pcb1.in_time;
					current_pcb1.wait_time=current_pcb1.turn_around_time -current_pcb1.process_time;
					writer_turn_around.write("turn around time of process"+current_pcb1.pid+" is "+current_pcb1.turn_around_time);
					writer_turn_around.newLine();
					writer_turn_around.flush();

					
					}
					current_pcb1.time_in_CPU = 0;
					current_pcb1 = null;
					
					break;

				}
					
				}while(left_time!=0);
				

				
			}
			else
			if(current_pcb1.remaining_time < cycle_time && current_pcb1.time_in_CPU + cycle_time >SLOT_TIME){
				
				//this means there are no cycles left so the process should automatically preempt
				System.out.println("case 4");
				current_pcb1.time_in_CPU=0;
				ready_queue.add(current_pcb1);
				current_pcb1 = null;
			}
							
		}
		
	
}

public static void shortTermScheduling2() throws IOException{
	

	
	System.out.println("ready_queue in short 2 ");
	for(PCB p :ready_queue)
	{
		System.out.println("have reached in 2 ");
		System.out.println(p.pid);
	}
		if(current_pcb2 == null)
		{
			if(ready_queue.size()>0)
			{	System.out.println("Queeu size greater than zero in shorrt 2 ");
				int upto=0;
				PCB temp = ready_queue.remove();
				while(upto <= ready_queue.size() && temp.pause == true){
					System.out.println("temp is pause in short 2 "+temp.pid);
					ready_queue.add(temp);
					temp=ready_queue.remove();
					upto++;
				}
				if(upto <=ready_queue.size())
					{
					
					System.out.println(current_pcb2+"====");
					current_pcb2 =temp;
					if(!current_pcb2.hadResponded)
					{
						current_pcb2.hadResponded = true;
						current_pcb2.response_time=globalTime-current_pcb2.in_time;
						writer_response.write("response time of process :"+current_pcb2.pid+" is "+current_pcb2.response_time);
						writer_response.newLine();
						writer_response.flush();
					}
					System.out.println("1.current pcb 2 is set to"+current_pcb2.pid);
					
					if(current_pcb1!=null)
						System.out.println("current_pcb1"+ current_pcb1.pid);
						if(current_pcb2!=null)
							System.out.println("current_pcb2"+ current_pcb2.pid);
							
						if(current_pcb3!=null)
							System.out.println("current_pcb3"+ current_pcb3.pid);
							
						if(current_pcb4!=null)
							System.out.println("current_pcb4"+ current_pcb4.pid);
					
					}
				else
				{
					ready_queue.add(temp);
				}
				
			}
		}
		
		if(current_pcb2!=null)
		{
		System.out.println(current_pcb2.remaining_time+"$$");
		System.out.println(current_pcb2.time_in_CPU+"@@");
		}
		if(current_pcb2!=null)
		{
			System.out.println("pid_2:"+current_pcb2.pid);
			System.out.println("remianing time_2:"+current_pcb2.remaining_time);
			System.out.println("time_in_CPU_2:"+current_pcb2.time_in_CPU);
		}
		if(current_pcb2 != null)
		{
			if(current_pcb2.remaining_time >= cycle_time && current_pcb2.time_in_CPU+cycle_time<=SLOT_TIME)
			{
				System.out.println("case 1 od sts");
				current_pcb2.remaining_time-=cycle_time;
			//	if(current_pcb.remaining_time<=0)
				//	current_pcb.loadNextInstruction();
				current_pcb2.time_in_CPU+=cycle_time;
										
			}
			else
			if(current_pcb2.remaining_time >= cycle_time && current_pcb2.time_in_CPU+cycle_time >SLOT_TIME)
			{
				System.out.println("case 2 of sts");
				current_pcb2.remaining_time-=(SLOT_TIME-current_pcb2.time_in_CPU);
				current_pcb2.time_in_CPU = 0;
				ready_queue.add(current_pcb2);
				current_pcb2 = null;
			}
			else
			if(current_pcb2.remaining_time < cycle_time && current_pcb2.time_in_CPU +cycle_time <= SLOT_TIME)
			{
				
				System.out.println(current_pcb2.remaining_time+"###");
				System.out.println(current_pcb2.time_in_CPU+"##");
				
				System.out.println("case 3 of sts");
				float left_time = cycle_time-current_pcb2.remaining_time;
				current_pcb2.time_in_CPU+=current_pcb2.remaining_time;
				
				System.out.println(current_pcb2.remaining_time+"###111");
				System.out.println(current_pcb2.time_in_CPU+"##111");
				
				
				
				do
				{	
					int next_inst_value = current_pcb2.loadNextInstruction();
					if(next_inst_value == 1){
					
					for(PCB.Inst i:current_pcb2.file_data)
					{
						System.out.println(i.ch+"asdfg"+i.time);
					}
					
					System.out.println("next instruction is cPU in short 2");
					float temp=Math.min(current_pcb2.remaining_time,left_time);
					current_pcb2.time_in_CPU+=temp;
					
					current_pcb2.remaining_time-=temp;
					left_time-=temp;
					
					System.out.println(current_pcb2.remaining_time+"***");
					System.out.println(current_pcb2.time_in_CPU+"***");
					
					if(current_pcb2.remaining_time>0 && current_pcb2.time_in_CPU+cycle_time <	SLOT_TIME)
					{
						System.out.println("Current Pcb remain same in short 2 ");
					}
					else if(current_pcb2.remaining_time>0 && current_pcb2.time_in_CPU+cycle_time >=SLOT_TIME)
					{
						System.out.println("added to ready queue because of left remaing time in case 3");
						ready_queue.add(current_pcb2);
						current_pcb2 = null;
						break;
					}
					
				}
				else{
					if(next_inst_value == -1)
					{
					System.out.println("added to wait queue as next instruction is IO in short 2 ");
					//System.out.println("present insrt"+current_pcb.file_data.get(current_pcb.instruction_pointer));
					waiting_queue.add(current_pcb2);
					}
					else
					{
					System.out.println("Current process in short 2 "+current_pcb2.pid+" terminates execution");
					System.out.println("Current process"+current_pcb2.pid+" terminates execution");
					writer.write(current_pcb2.pid+"terminated execution normally \n");
					current_pcb2.exit_time=globalTime;
					current_pcb2.turn_around_time=current_pcb2.exit_time-current_pcb2.in_time;
					current_pcb2.wait_time=current_pcb2.turn_around_time -current_pcb2.process_time;
					writer_turn_around.write("turn around time of process"+current_pcb2.pid+" is "+current_pcb2.turn_around_time);
					writer_turn_around.newLine();
					writer_turn_around.flush();
					
					}
					current_pcb2.time_in_CPU = 0;
					current_pcb2 = null;
					
					break;

				}
					
				}while(left_time!=0);
				

				
			}
			else
			if(current_pcb2.remaining_time < cycle_time && current_pcb2.time_in_CPU + cycle_time >SLOT_TIME){
				
				//this means there are no cycles left so the process should automatically preempt
				System.out.println("case 4");
				current_pcb2.time_in_CPU=0;
				ready_queue.add(current_pcb2);
				current_pcb2 = null;
			}
							
		}
		
	
}

public static void shortTermScheduling3() throws IOException{
	

	
	System.out.println("ready_queue in short in short 3");
	for(PCB p :ready_queue)
	{
		System.out.println("have reached ");
		System.out.println(p.pid);
	
	}
		if(current_pcb3 == null)
		{
			if(ready_queue.size()>0)
			{	System.out.println("Queeu size greater than zero in short 3");
				int upto=0;
				PCB temp = ready_queue.remove();
				while(upto <= ready_queue.size() && temp.pause == true){
					System.out.println("temp is pause in short 3"+temp.pid);
					ready_queue.add(temp);
					temp=ready_queue.remove();
					upto++;
				}
				for(PCB p: ready_queue)
				{
					System.out.println(p.pid+"In SHort 3 ready queue");
				}
				if(upto <=ready_queue.size())
					{
					
					System.out.println(current_pcb3+"====");
					current_pcb3 =temp;
					if(!current_pcb3.hadResponded)
					{
						current_pcb3.hadResponded = true;
						current_pcb3.response_time=globalTime-current_pcb3.in_time;
						writer_response.write("response time of process :"+current_pcb3.pid+" is "+current_pcb3.response_time);
						writer_response.newLine();
						writer_response.flush();
					}
					System.out.println("1.current pcb is set to 3"+current_pcb3.pid);
					
					if(current_pcb1!=null)
						System.out.println("current_pcb1"+ current_pcb1.pid);
						if(current_pcb2!=null)
							System.out.println("current_pcb2"+ current_pcb2.pid);
							
						if(current_pcb3!=null)
							System.out.println("current_pcb3"+ current_pcb3.pid);
							
						if(current_pcb4!=null)
							System.out.println("current_pcb4"+ current_pcb4.pid);
					
					}
				else
				{
					ready_queue.add(temp);
				}
			}
		}
		
		if(current_pcb3!=null)
		{
		System.out.println(current_pcb3.remaining_time+"$$");
		System.out.println(current_pcb3.time_in_CPU+"@@");
		}
		if(current_pcb3!=null)
		{
			System.out.println("pid: 3"+current_pcb3.pid);
			System.out.println("remianing time: 3 "+current_pcb3.remaining_time);
			System.out.println("time_in_CPU: 3 "+current_pcb3.time_in_CPU);
		}
		if(current_pcb3 != null)
		{
			if(current_pcb3.remaining_time >= cycle_time && current_pcb3.time_in_CPU+cycle_time<=SLOT_TIME)
			{
				System.out.println("case 1 od sts short 3");
				current_pcb3.remaining_time-=cycle_time;
			//	if(current_pcb.remaining_time<=0)
				//	current_pcb.loadNextInstruction();
				current_pcb3.time_in_CPU+=cycle_time;
										
			}
			else
			if(current_pcb3.remaining_time >= cycle_time && current_pcb3.time_in_CPU+cycle_time >SLOT_TIME)
			{
				System.out.println("case 2 of sts short 3");
				current_pcb3.remaining_time-=(SLOT_TIME-current_pcb3.time_in_CPU);
				current_pcb3.time_in_CPU = 0;
				ready_queue.add(current_pcb3);
				current_pcb3 = null;
			}
			else
			if(current_pcb3.remaining_time < cycle_time && current_pcb3.time_in_CPU +cycle_time <= SLOT_TIME)
			{
				
				System.out.println(current_pcb3.remaining_time+"###");
				System.out.println(current_pcb3.time_in_CPU+"##");
				
				System.out.println("case 3 of sts short 3");
				float left_time = cycle_time-current_pcb3.remaining_time;
				current_pcb3.time_in_CPU+=current_pcb3.remaining_time;
				
				System.out.println(current_pcb3.remaining_time+"###111");
				System.out.println(current_pcb3.time_in_CPU+"##111");
				
				
				
				do
				{	
					int next_inst_value = current_pcb3.loadNextInstruction();
					if(next_inst_value == 1){
					
					for(PCB.Inst i:current_pcb3.file_data)
					{
						System.out.println(i.ch+"asdfg"+i.time);
					}
					
					System.out.println("next instruction is cPU short 3 ");
					float temp=Math.min(current_pcb3.remaining_time,left_time);
					current_pcb3.time_in_CPU+=temp;
					
					current_pcb3.remaining_time-=temp;
					left_time-=temp;
					
					System.out.println(current_pcb3.remaining_time+"***");
					System.out.println(current_pcb3.time_in_CPU+"***");
					
					if(current_pcb3.remaining_time>0 && current_pcb3.time_in_CPU+cycle_time <	SLOT_TIME)
					{
						System.out.println("Current Pcb remain same short 3");
					}
					else if(current_pcb3.remaining_time>0 && current_pcb3.time_in_CPU+cycle_time >=SLOT_TIME)
					{
						System.out.println("added to ready queue because of left remaing time in case 3");
						ready_queue.add(current_pcb3);
						current_pcb3 = null;
						break;
					}
					
				}
				else{
					if(next_inst_value == -1)
					{
					System.out.println("added to wait queue as next instruction is IO");
					//System.out.println("present insrt"+current_pcb.file_data.get(current_pcb.instruction_pointer));
					waiting_queue.add(current_pcb3);
					}
					else
					{
					System.out.println("Current process short 3"+current_pcb3.pid+" terminates execution");
					current_pcb3.exit_time=globalTime;
					current_pcb3.turn_around_time=current_pcb3.exit_time-current_pcb3.in_time;
					current_pcb3.wait_time=current_pcb2.turn_around_time -current_pcb3.process_time;
					writer_turn_around.write("turn around time of process"+current_pcb3.pid+" is "+current_pcb3.turn_around_time);
					writer_turn_around.newLine();
					writer_turn_around.flush();
					
					}
					current_pcb3.time_in_CPU = 0;
					current_pcb3 = null;
					
					break;

				}
					
				}while(left_time!=0);
				

				
			}
			else
			if(current_pcb3.remaining_time < cycle_time && current_pcb3.time_in_CPU + cycle_time >SLOT_TIME){
				
				//this means there are no cycles left so the process should automatically preempt
				System.out.println("case 4 in short 3");
				current_pcb3.time_in_CPU=0;
				ready_queue.add(current_pcb3);
				current_pcb3 = null;
			}
							
		}
		
	
}

public static void shortTermScheduling4() throws IOException{

	
	System.out.println("ready_queue in short in 4");
	for(PCB p :ready_queue)
	{
		System.out.println("have reached in 4");
		System.out.println(p.pid);
	}
		if(current_pcb4 == null)
		{
			if(ready_queue.size()>0)
			{	System.out.println("Queeu size greater than zero in 4 ");
				int upto=0;
				PCB temp = ready_queue.remove();
				while(upto <= ready_queue.size() && temp.pause == true){
					System.out.println("temp is pause"+temp.pid);
					ready_queue.add(temp);
					temp=ready_queue.remove();
					upto++;
				}
				if(upto <=ready_queue.size())
					{
					
					System.out.println(current_pcb4+"====");
					current_pcb4 =temp;
					if(!current_pcb4.hadResponded)
					{
						current_pcb4.hadResponded = true;
						current_pcb4.response_time=globalTime-current_pcb4.in_time;
						writer_response.write("response time of process :"+current_pcb4.pid+" is "+current_pcb4.response_time);
						writer_response.newLine();
						writer_response.flush();
					}
					System.out.println("1.current pcb is set to in 4 "+current_pcb4.pid);
					
					if(current_pcb1!=null)
						System.out.println("current_pcb1"+ current_pcb1.pid);
						if(current_pcb2!=null)
							System.out.println("current_pcb2"+ current_pcb2.pid);
							
						if(current_pcb3!=null)
							System.out.println("current_pcb3"+ current_pcb3.pid);
							
						if(current_pcb4!=null)
							System.out.println("current_pcb4"+ current_pcb4.pid);
					
					}
				else{
					ready_queue.add(temp);
				}
			}
		}
		
		if(current_pcb4!=null)
		{
		System.out.println(current_pcb4.remaining_time+"$$");
		System.out.println(current_pcb4.time_in_CPU+"@@");
		}
		if(current_pcb4!=null)
		{
			System.out.println("pid: in 4"+current_pcb4.pid);
			System.out.println("remianing time: in 4"+current_pcb4.remaining_time);
			System.out.println("time_in_CPU: in 4"+current_pcb4.time_in_CPU);
		}
		if(current_pcb4 != null)
		{
			if(current_pcb4.remaining_time >= cycle_time && current_pcb4.time_in_CPU+cycle_time<=SLOT_TIME)
			{
				System.out.println("case 1 od sts in 4");
				current_pcb4.remaining_time-=cycle_time;
			//	if(current_pcb.remaining_time<=0)
				//	current_pcb.loadNextInstruction();
				current_pcb4.time_in_CPU+=cycle_time;
										
			}
			else
			if(current_pcb4.remaining_time >= cycle_time && current_pcb4.time_in_CPU+cycle_time >SLOT_TIME)
			{
				System.out.println("case 2 of sts in 4");
				current_pcb4.remaining_time-=(SLOT_TIME-current_pcb4.time_in_CPU);
				current_pcb4.time_in_CPU = 0;
				ready_queue.add(current_pcb4);
				current_pcb4 = null;
			}
			else
			if(current_pcb4.remaining_time < cycle_time && current_pcb4.time_in_CPU +cycle_time <= SLOT_TIME)
			{
				
				System.out.println(current_pcb4.remaining_time+"###");
				System.out.println(current_pcb4.time_in_CPU+"##");
				
				System.out.println("case 3 of sts in 4");
				float left_time = cycle_time-current_pcb4.remaining_time;
				current_pcb4.time_in_CPU+=current_pcb4.remaining_time;
				
				System.out.println(current_pcb4.remaining_time+"###111");
				System.out.println(current_pcb4.time_in_CPU+"##111");
				
				
				
				do
				{	
					int next_inst_value = current_pcb4.loadNextInstruction();
					if(next_inst_value == 1){
					
					for(PCB.Inst i:current_pcb4.file_data)
					{
						System.out.println(i.ch+"asdfg"+i.time);
					}
					
					System.out.println("next instruction is cPU in 4");
					float temp=Math.min(current_pcb4.remaining_time,left_time);
					current_pcb4.time_in_CPU+=temp;
					
					current_pcb4.remaining_time-=temp;
					left_time-=temp;
					
					System.out.println(current_pcb4.remaining_time+"***");
					System.out.println(current_pcb4.time_in_CPU+"***");
					
					if(current_pcb4.remaining_time>0 && current_pcb4.time_in_CPU+cycle_time <	SLOT_TIME)
					{
						System.out.println("Current Pcb remain same in 4");
					}
					else if(current_pcb4.remaining_time>0 && current_pcb4.time_in_CPU+cycle_time >=SLOT_TIME)
					{
						System.out.println("added to ready queue because of left remaing time in case 3 in 4");
						ready_queue.add(current_pcb4);
						current_pcb4 = null;
						break;
					}
					
				}
				else{
					if(next_inst_value == -1)
					{
					System.out.println("added to wait queue as next instruction is IO in 4");
					//System.out.println("present insrt"+current_pcb.file_data.get(current_pcb.instruction_pointer));
					waiting_queue.add(current_pcb4);
					}
					else
					{
					System.out.println("Current process in 4"+current_pcb4.pid+" terminates execution");
					current_pcb4.exit_time=globalTime;
					current_pcb4.turn_around_time=current_pcb4.exit_time-current_pcb4.in_time;
					current_pcb4.wait_time=current_pcb4.turn_around_time -current_pcb4.process_time;
					writer_turn_around.write("turn around time of process"+current_pcb4.pid+" is "+current_pcb4.turn_around_time);
					writer_turn_around.newLine();
					writer_turn_around.flush();
					}
					current_pcb4.time_in_CPU = 0;
					current_pcb4 = null;
					
					break;

				}
					
				}while(left_time!=0);
				

				
			}
			else
			if(current_pcb4.remaining_time < cycle_time && current_pcb4.time_in_CPU + cycle_time >SLOT_TIME){
				
				//this means there are no cycles left so the process should automatically preempt
				System.out.println("case 4 in short 4");
				current_pcb4.time_in_CPU=0;
				ready_queue.add(current_pcb4);
				current_pcb4 = null;
			}
							
		}
		
	
}



public static void mediumTermScheduling() throws IOException
{
	for(PCB p :waiting_queue )
	{
		if(p.pause == false)
			p.remaining_time-=cycle_time;
		
	}
	
	
	List<Integer> l = new ArrayList();
	for(int i=0;i<waiting_queue.size();i++)
	{
		System.out.println("Hash Pahochya");
		PCB p = waiting_queue.get(i);
		if(p.remaining_time <=0.0)
		{
			System.out.println("QWYUWERTYUIOqwertyuioytrewqertuiouy");
			float temp=p.remaining_time;
			int next_inst_value1=p.loadNextInstruction();
			if(next_inst_value1 == 1)
			{
			ready_queue.add(p);
			System.out.println(p.file_data.get(p.instruction_pointer).ch);
			//p.instruction_pointer++;
			//p.remaining_time=p.file_data.get(p.instruction_pointer).time;
			System.out.println("12345"+p.file_data.get(p.instruction_pointer).time);
			l.add(i);
			System.out.println("added to ready queue is"+p.pid);
			writer.write(p.pid+"was added from waiting to ready queue because IO got over.\n");
			writer.newLine();
			writer.flush();
			
			}
			else
			if(next_inst_value1 == -1)
			{
				p.remaining_time+=temp;
			}
			else
			{
				System.out.println("process "+p.pid+"completed its execution");
				writer.write(p.pid + " completed execution \n");
				writer.newLine();
				writer.flush();
				p.exit_time=globalTime;
				p.turn_around_time=current_pcb1.exit_time-p.in_time;
				p.wait_time=current_pcb1.turn_around_time -p.process_time;
				writer_turn_around.write("turn around time of process"+p.pid+" is "+p.turn_around_time);
				writer_turn_around.newLine();
				writer_turn_around.flush();
				l.add(i);
			}
			
		}
		
	}
	for(int i=0;i<l.size();i++)
	{
		waiting_queue.remove(l.get(i)-i);
		
	}
	
	
}
	
}
