package com.alma.commontest;

import com.alma.commontest.tools.Tools;
import com.alma.framework.impl.StarPerTime;
import com.alma.framework.impl.classinit.TestInitialize;
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
	private static final String	COMMAND_START	= "-start";
	private static final String	COMMAND_XINX	= "-xinx";
	private static final String	COMMAND_STOP	= "-stop";
	private static final String	COMMAND_LISTENER= "-listener";
	private static final String	COMMAND_INIT	= "-init";
	private static final String	COMMAND_LOG		= "-log";
	private static final Logger	LOG				= LogManager.getLogger( App.class );

	private static final double	LOG_BASIS		= Math.pow( 99.0, 1.0/10.0 );
	
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

	private static StarPerTime	s_stub	= null;
	
	private static void StopStub()
	{
		if( null != s_stub )
		{
			s_stub.Stop();

			s_stub	= null;

		}
		
	}
	
	private static boolean ParseCommand( String input_ ) throws IOException
	{
		LOG.entry( input_ );
		
		boolean	result	= true;

		if( COMMAND_EXIT.equalsIgnoreCase( input_ ) )
		{
			StopStub();
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
		else if( COMMAND_LOG.equalsIgnoreCase( input_ ) )
		{
			Tools.Log( LOG_BASIS );
			
		}
		else if( COMMAND_START.equalsIgnoreCase( input_ ) )
		{
			s_stub	= new StarPerTime( 500, 60 );
			
			s_stub.Start();

		}
		else if( COMMAND_STOP.equalsIgnoreCase( input_ ) )
		{
			StopStub();

		}
		else if( COMMAND_XINX.equalsIgnoreCase( input_ ) )
		{
			Tools.TestXinX();

		}
		else if( COMMAND_COPY.equalsIgnoreCase( input_ ) )
		{
			Tools.CopyFile( "pom.xml", "pom.bak" );
				
		}
		else if( COMMAND_LISTENER.equalsIgnoreCase( input_ ) )
		{
			Tools.TestListener();

		}
		else if( COMMAND_INIT.equalsIgnoreCase( input_ ) )
		{
			TestInitialize	instance1	= new TestInitialize( 1 );
			TestInitialize	instance2	= new TestInitialize( 2 );

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
