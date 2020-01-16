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
		System.out.println( substringUDF("", 1, 5) );
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
