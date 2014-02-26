package com.alma.commontest;

import com.alma.commontest.tools.Tools;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App extends Thread
{
	private static final char	COMMAND_PREFIX	= '-';
	private static final String	COMMAND_EXIT	= "-exit";
	private static final String	COMMAND_BASE64	= "-base64";
	private static final Logger	LOG				= LogManager.getLogger( App.class );

	public static void main( String[] args_ ) throws InterruptedException
	{
		LOG.entry();
		
		App		application	= new App( args_ );
		Scanner	scanner		= new Scanner( System.in );
		boolean	isActive	= true;

		application.start();

		while( isActive )
		{
			String	input	= scanner.nextLine();

			if( input.length() > 0 )
			{
				if( COMMAND_PREFIX == input.charAt( 0 ) )
				{
					isActive	= ParseCommand( input );
					
				}

			}

		}

		application.Exit();
		application.join();
		
		LOG.exit();

	}

	@Override
	public void run()
	{
		LOG.entry();
		
		while( true )
		{
			synchronized( this )
			{
				try
				{
					this.wait();
					break;

				}
				catch( InterruptedException ex_ )
				{
					LOG.error( "", ex_ );

				}

			}

		}
		
		LOG.exit();

	}

	private void Exit()
	{
		LOG.entry();
		
		synchronized( this )
		{
			this.notifyAll();

		}
		
		LOG.exit();

	}

	private static boolean ParseCommand( String input_ )
	{
		LOG.entry();
		
		boolean	result	= true;

		if( COMMAND_EXIT.equalsIgnoreCase( input_ ) )
		{
			result = false;

		}
		else if( COMMAND_BASE64.equalsIgnoreCase( input_ ) )
		{
			try
			{
				Tools.TestBase64();
				
			}
			catch( NoSuchAlgorithmException ex_ )
			{
				LOG.fatal( "", ex_ );
				
			}

		}
		
		LOG.exit( result );

		return result;

	}

	private App( String[] args_ )
	{
		LOG.entry();
		
		m_args	= args_;
		
		LOG.exit();

	}

	private String[]	m_args;

}
