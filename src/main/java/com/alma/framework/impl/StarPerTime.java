package com.alma.framework.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Mar 21, 2014
 * @author Maxim Kuzovlev
 */
public class StarPerTime implements Runnable
{
	public void run()
	{
		m_executor.schedule( this, m_timeMils, TimeUnit.MILLISECONDS );
		
		long	starCount	= m_starCount.incrementAndGet();
		
		System.out.print( "*" );
		System.out.flush();
		
		if( 0 == starCount % m_startPerLine )
		{
			System.out.println();
			System.out.flush();
			
		}
		
	}
	
	public void Start()
	{
		m_starCount.set( 0L );
		m_executor.schedule( this, m_timeMils, TimeUnit.MILLISECONDS );
		
	}
	
	public void Stop()
	{
		m_executor.shutdown();
		
	}
	
	public StarPerTime( int timeMils_, int startPerLine_ )
	{
		m_timeMils		= timeMils_;
		m_startPerLine	= startPerLine_;
		m_executor		= Executors.newSingleThreadScheduledExecutor();
		m_starCount		= new AtomicLong();
		
	}
	
	private	final int						m_timeMils;
	private	final int						m_startPerLine;
	private final ScheduledExecutorService	m_executor;
	private final AtomicLong				m_starCount;

}
