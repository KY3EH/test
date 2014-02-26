package com.alma.framework.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author maximk
 */
public class AbstractWorker extends Thread
{
	protected static final Logger	LOG	= LogManager.getLogger( AbstractWorker.class );
	
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


}
