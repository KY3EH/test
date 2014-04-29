package com.alma.framework.impl.classinit;

/**
 * Apr 29, 2014
 * @author Maxim Kuzovlev
 */
public class TestInitialize
{
	static
	{
		System.out.println( "Static inititialize" );
		
	}

	{
		System.out.println( "Dynamic inititialize" );
		
	}

	public TestInitialize( int instance_ )
	{
		System.out.println( "C-tor inititialize of instance " + Integer.toString( instance_ ) );
		
	}

}
