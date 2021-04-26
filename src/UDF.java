import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class UDF
{
	public static void main(String args[])
	{
		//System.out.println( formatPRECIO("0.50") );
		//System.out.println( formatPRECIO("0.50") );
		//System.out.println( calculatePRECIO("50", "100") );
		//System.out.println( formatCODART("1234") );
		//System.out.println( formatCODART("12345678") );
		//System.out.println( formatCODART("123456789ABCDEF") );
		//System.out.println( setFileName("0000000246367027") );
		//System.out.println( substringFromRight("0000000246367027", 3) );
		//System.out.println( removeDecimals("55.123") );
		//System.out.println( substringUDF("", 1, 5) );
		//System.out.println( getASMA("http://sap.com/xi/XI/System/REST", "Authorization","BS_XXCLNTXX" , 5) );
		//System.out.println( formatDate("09/04/2021 00:00:00") );
		//System.out.println( formatTime("09/04/2021 00:00:00") );
		//System.out.println( removeLeadingZero("000000000000177483") );
		//System.out.println( formatIdtnrArticulo("9233") );
		//System.out.println( formatIdtnrArticulo("000000000000199233") );
		//System.out.println( replaceDots("0.12") );
		//System.out.println( getSAPDate("20210427") );
		System.out.println( getSAPDateAndTime("20210426 135157") );
	}
	
	private static String getSAPDateAndTime(String date)
	{
		String year = date.substring(0, 4);
		String month = date.substring(4, 6);
		String day = date.substring(6, 8);
		String hour = date.substring(9, 11);
		String minute = date.substring(11, 13);
		String second = date.substring(13, 15);
		
		return day + "/" + month + "/" + year + " " + hour + ":" + minute + ":" + second;
	}
	
	private static String getSAPDate(String date)
	{
		String year = date.substring(0, 4);
		String month = date.substring(4, 6);
		String day = date.substring(6, 8);
		
		return day + "/" + month + "/" + year;
	}
	
	private static String replaceDots(String value)
	{
		return value.replaceFirst("\\.", ",");
	}
	
	private static String formatIdtnrArticulo(String idtnr)
	{
		// Si tiene menos de 6 caracteres, se le añaden 0 a la izquierda
		if( idtnr.length() < 6 )
		{
			idtnr = String.format("%06d", Integer.parseInt(idtnr));
		}
		// Si tiene mas de 6 caracteres, se le quitan caracteres a la izquierda
		else if( idtnr.length() > 6 )
		{
			idtnr = idtnr.substring( ( idtnr.length()-6 ), idtnr.length() );
		}
		
		return idtnr;
	}
	
	// Elimina los ceros a la izquierda
	private static String removeLeadingZero(String value) //Copied from github
	{
		String valueWithoutZeros = "";
		try
		{
			valueWithoutZeros = value.replaceFirst("^0+", "");
		}
		catch(Exception ee)
		{
			valueWithoutZeros = value;
		}
		
		return valueWithoutZeros;
	}
	
	private static String formatDate(String date) //20210409
	{
		String year = date.substring(6, 10);
		String month = date.substring(3, 5);
		String day = date.substring(0, 2);
		
		return year+month+day;
	}
	
	private static String formatTime(String date)
	{
		return date.substring(11, 19);
	}
	
	private static String getASMA(String namespace, String attribute, String receiver, int tokenTimeout)
	{
		String ret = null; 
		String AuthorizationHeader = "Bearer ijafoaifoajfklfjdlf_161359";

		// Calcula fecha actual
		Calendar ahoraCal = Calendar.getInstance();
		int diaHoy = ahoraCal.get(Calendar.DAY_OF_MONTH);
		int horaHoy = ahoraCal.get(Calendar.HOUR_OF_DAY);
		int minHoy = ahoraCal.get(Calendar.MINUTE);
		
		// Calcula fecha del token
		int diaToken = Integer.parseInt(AuthorizationHeader.substring(AuthorizationHeader.length()-6, AuthorizationHeader.length()-4));
		int horaToken = Integer.parseInt(AuthorizationHeader.substring(AuthorizationHeader.length()-4, AuthorizationHeader.length()-2));
		int minToken = Integer.parseInt(AuthorizationHeader.substring(AuthorizationHeader.length()-2));
		
		// Validar "Bearer ijafoaifoajfklfjdlf071921"
		// Si diaHoy == AuthorizationHeader(size-6, size-4)
		// Si horaHoy == AuthorizationHeader(size-4, size-2)
		// Si minHoy == AuthorizationHeader(size-2, size)
		// SI minHoy == franja
		   // ret = receiver;

		System.out.println(diaToken);
		System.out.println(horaToken);
		System.out.println(minToken);
		
		// Si minHoy < tokenTimeout
			// minHoy = minHoy + tokenTimeout
		
		return ret;
	}
	
	
	private static String substringUDF(String textIn, int beginIndex, int endIndex)
	{
		String aux = "";
		
		try
		{
			if(endIndex == -1)
				aux = textIn.substring(beginIndex);
			else
				aux = textIn.substring(beginIndex, endIndex);
		}
		catch (Exception e)
		{
			return "";
		}
		
		return aux;
	}
	
	private static String removeDecimals(String textIn)
	{
		if( textIn.indexOf(".") != -1 )
			return (textIn.substring(0, textIn.indexOf(".") ) );
		else
			return textIn;
	}
	
	// Elimina el punto decimal y pone ceros a la izquierda hasta que el total sea de 9 caracteres
	private static String formatPRECIO(String var1)
	{
		String temp = var1.replace(".", "");
		
		return( String.format("%09d", Integer.parseInt(temp)) );
	}
	
	private static String formatPRECIO2(String var1)
	{
		var1 = var1.replace(".", "");
		
		while (var1.length() < 9)
		{
			var1 = "0" + var1;
		}
		return var1;
	}
	
	private static String calculatePRECIO(String var1, String var2)
	{
		var1 = String.format("%.2f", Float.parseFloat(var1) / Float.parseFloat(var2));
		
		var1 = var1.replace(".", "");
		var1 = var1.replace(",", "");
		
		while (var1.length() < 9)
		{
			var1 = "0" + var1;
		}
		return var1;
	}
	
	private static String formatCODART(String var1)
	{
		if(var1.length() > 8)
		{
			return( var1.substring( ( var1.length()-8 ), var1.length() ) );
		}
		else if(var1.length() < 8)
		{
			while (var1.length() < 8)
			{
				var1 = "0" + var1;
			}
			return var1;
		}
		else
		{
			return var1;
		}
	}
	
	private static String setFileName(String numIdoc)
	{
		String filename = "";
		String year, month, day, hour, minute, second = "";
		Calendar calendar = Calendar.getInstance();
		
		//DynamicConfiguration conf1 = (DynamicConfiguration) container.getTransformationParameters().get(StreamTransformationConstants.DYNAMIC_CONFIGURATION);
		//DynamicConfigurationKey key1 = DynamicConfigurationKey.create("http://sap.com/xi/XI/System/File","FileName");
		
		year = String.valueOf( calendar.get(Calendar.YEAR) );
		
		month = String.valueOf( calendar.get(Calendar.MONTH)+1 );
		if( month.length() < 2 )
			month = "0" + month;
		
		day = String.valueOf( calendar.get(Calendar.DATE) );
		if( day.length() < 2 )
			day = "0" + day;
		
		hour = String.valueOf( calendar.get(Calendar.HOUR_OF_DAY) );
		if( hour.length() < 2 )
			hour = "0" + hour;
		
		minute = String.valueOf( calendar.get(Calendar.MINUTE));
		if( minute.length() < 2 )
			minute = "0" + minute;
		
		second = String.valueOf( calendar.get(Calendar.SECOND));
		if( second.length() < 2 )
			second = "0" + second;
		
		
		filename = "Precios_" + year+month+day+"-"+hour+minute+second + "_" + numIdoc + ".txt";
		
		//conf1.put(key1, filename);

		return filename;
	}
	
	private static String substringFromRight(String varIn1, int places)
	{
		return( varIn1.substring( ( varIn1.length()-places ), varIn1.length() ) );
	}
	
	private static String fillWithSpaces(String text, int length)
	{
		if( text.length() < length )
		{
			while( text.length() < length )
			{
				text = " " + text;
			}
			return text;
		}
		else
		{
			return text;
		}
	}
}
