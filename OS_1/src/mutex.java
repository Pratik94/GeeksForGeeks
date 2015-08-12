
public class mutex {

		static boolean lock = false ;
		
		

		public boolean mutex_in_read()
		{
			if(lock==false)
			{
				lock = true;
				return true;
			}
			return false;
		}
		
		
		public void mutex_out_read()
		{
			lock = false;
			
		}
		
		public boolean mutex_in_write()
		{
			if(lock==false)
			{
				lock = true;
				return true;
			}
			return false;
		}
		
		
		public void mutex_out_write()
		{
			lock = false;
			
		}
		
		
		
}
