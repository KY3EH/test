package com.alma.framework.impl.listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Apr 11, 2014
 * @author Maxim Kuzovlev
 */
public class ListenerNotifier implements TrackerServiceItf
{
	private static final Logger	LOG	= LogManager.getLogger( ListenerNotifier.class );
	
	public void AddListener( ListenerItf listener_ )
	{
		LOG.entry();
		
		boolean	rc	= m_listeners.remove( listener_ );
		
		if( true == rc )
		{
			LOG.info( "AddListener old instance of the same listener removed" );
			
		}
		
		m_listeners.add( listener_ );

		LOG.exit();

	}

	public boolean RemoveListener( ListenerItf listener_ )
	{
		LOG.entry();
		
		boolean	result	= m_listeners.remove( listener_ );
		
		LOG.exit( result );
		
		return result;

	}

	private final List<ListenerItf>	m_listeners	= Collections.synchronizedList( new ArrayList<ListenerItf>() );
	
}
