package com.alma.commontest.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author maximk
 */
public abstract class Tools
{
	private static final Logger	LOG				= LogManager.getLogger( Tools.class );
	
	private static final int	BUFFER_SIZE		= 1024;
	
	public static void CopyFile( String sourceFile_, String destinationFile_ ) throws FileNotFoundException, IOException
	{
		LOG.entry( sourceFile_, destinationFile_);
			
		byte[]		buffer	= new byte[ BUFFER_SIZE ];
		InputStream	source	= new FileInputStream( sourceFile_ );
		
		try
		{
			OutputStream	destination	= new FileOutputStream( destinationFile_ );
			
			try
			{
				while( true )
				{
					int	count	= source.read( buffer );

					if( count < 0 )
					{
						break;
						
					}
					else
					{
						destination.write( buffer, 0, count );
						
					}
					
				}
				
			}
			finally
			{
				destination.close();

			}

		}
		finally
		{
			source.close();
			
		}
		
		LOG.exit();

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
	
	public static float TestFloat( float source_ ) throws IOException
	{
		LOG.entry( source_ );
		
		ByteArrayOutputStream	buffer	= new ByteArrayOutputStream( Float.SIZE );
		DataOutputStream		writer	= new DataOutputStream( buffer );
		
		writer.writeFloat( source_ );
		writer.close();
		
		byte[]					floatData	= buffer.toByteArray();
		ByteArrayInputStream	converter	= new ByteArrayInputStream( floatData );
		DataInputStream			reader		= new DataInputStream( converter );
		float					result		= reader.readFloat();
		
		LOG.exit( result );
		
		return result;
		
	}
	
	private static final String	DATE_FORMAT_ND	= "EEE d'nd' MMMM";
	private static final String	DATE_FORMAT_RD	= "EEE d'rd' MMMM";
	private static final String	DATE_FORMAT_ST	= "EEE d'st' MMMM";
	private static final String	DATE_FORMAT_TH	= "EEE d'th' MMMM";
	private static final String	DATE_DAY_NUMBER	= "d";
	
	public static String FormatDate( Date date_ )
	{
		String				format			= null;
		SimpleDateFormat	dayNumberFormat	= new SimpleDateFormat( DATE_DAY_NUMBER );
		String				daySource		= dayNumberFormat.format( date_ );
		int					day				= Integer.parseInt( daySource );
		
		switch( day )
		{
		case 1:
		case 21:
		case 31:
			format	= DATE_FORMAT_ST;
			break;
			
		case 2:
		case 22:
			format	= DATE_FORMAT_ND;
			break;
			
		case 3:
		case 23:
			format	= DATE_FORMAT_RD;
			break;
			
		case 4:
		case 24:
		case 5:
		case 25:
		case 6:
		case 26:
		case 7:
		case 27:
		case 8:
		case 28:
		case 9:
		case 29:
		case 10:
		case 30:
			format	= DATE_FORMAT_TH;
			break;
			
		}
		
		SimpleDateFormat	dateFormat	= new SimpleDateFormat( format );
		String				result		= dateFormat.format( date_ );
		
		return result;
		
	}
	
	private Tools()
	{
		
	}
    
}
