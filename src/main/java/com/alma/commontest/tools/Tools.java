package com.alma.commontest.tools;

import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author maximk
 */
public abstract class Tools
{
	public static void TestSwitc( int value_ )
	{
		switch( value_ )
		{
		case 1:
			break;
			
		default:
			break;
			
		}
		
	}
	
	public static void TestBase64() throws NoSuchAlgorithmException
	{
		KeyGenerator	generator		= KeyGenerator.getInstance( "AES" );
		SecretKey		key				= generator.generateKey();
		byte[]			symKey			= key.getEncoded();
		String			buffer			= DatatypeConverter.printBase64Binary( symKey );
		byte[]			supposedSymKey	= DatatypeConverter.parseBase64Binary( buffer );
		
		System.err.println( buffer );

	}
	
	private Tools()
	{
		
	}
    
}
