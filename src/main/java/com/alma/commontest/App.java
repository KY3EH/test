package com.alma.commontest;

import com.alma.commontest.tools.Tools;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App extends Thread
{
	private static final char	COMMAND_PREFIX	= '-';
	private static final String	COMMAND_EXIT	= "-exit";
	private static final String	COMMAND_BASE64	= "-base64";
	private static final String	COMMAND_DATE	= "-date";
	private static final String	COMMAND_FLOAT	= "-float";
	private static final String	COMMAND_COPY	= "-copy";
	private static final Logger	LOG				= LogManager.getLogger( App.class );

	public static void main( String[] args_ ) throws Exception
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

	private static boolean ParseCommand( String input_ ) throws IOException
	{
		LOG.entry( input_ );
		
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
		else if( COMMAND_DATE.equalsIgnoreCase( input_ ) )
		{
			String	date	= Tools.FormatDate( new Date() );
			
			System.err.println( date );
				
		}
		else if( COMMAND_FLOAT.equalsIgnoreCase( input_ ) )
		{
			float	source	= (float)Math.random();
			float	rc		= Tools.TestFloat( source );
			
			System.out.println( "Test float: source is " + Float.toString( source ) + ", result is " + Float.toString( rc ) );
				
		}
		else if( COMMAND_COPY.equalsIgnoreCase( input_ ) )
		{
			Tools.CopyFile( "pom.xml", "pom.bak" );
				
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
