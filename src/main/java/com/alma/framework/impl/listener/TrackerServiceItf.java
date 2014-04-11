package com.alma.framework.impl.listener;

/**
 * Apr 11, 2014
 * @author Maxim Kuzovlev
 */
public interface TrackerServiceItf
{
	public void AddListener( ListenerItf listener_ );
	public boolean RemoveListener( ListenerItf listener_ );

}
