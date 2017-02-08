
/**
 * Write a description of csvRead here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class csvRead {

    public void listexporters(CSVParser parser,String exportOfInterest)
    {
        
        for(CSVRecord record:parser)
        {
            String export=record.get("Exports");
            if(export.contains(exportOfInterest))
            {
        
             String country=record.get("Country");
             System.out.println(country);    
             
        
        
            }
        }
    }  
    public int numberOfExporters(CSVParser parser,String exportOfInterest)
    {
        int sum=0;
        for(CSVRecord record:parser)
        {
            String export=record.get("Exports");
            if(export.contains(exportOfInterest))
            {
        
             //String country=record.get("Country");
             // System.out.println(country);    
             sum=sum+1;
             
        
        
            }
        }
        return sum;
    }  
    public void bigExporters(CSVParser parser,String value)
    {
        int sum=0;
        //double v=Double.parseDouble(value);
        for(CSVRecord record:parser)
        {
            String value1=record.get("Value (dollars)");
            
            if(value1.contains(value))
            {
        
             String country=record.get("Country");
             System.out.println(country+value1);    
             sum=sum+1;
             System.out.println("Total Countries with big exports : "+sum);
             
        
        
            }
        }
        //return sum;
    }  
   /* public void bigExporters_number(CSVParser parser,String value)
    {
        int sum=0;
        String an="";
        for(int i1=0;i1<value.length();i1++)
            {
                if(value[i1]!="$"||value[i1]!=",")
                {
                    an=an+value[i1];
                }
            }
        double v=Double.parseDouble(an);
       String v2="";
        for(CSVRecord record:parser)
        {
            String value1=record.get("Value (dollars)");
            for(int i=0;i<value1.length();i++)
            {
                if(value1[i]!="$"||value1[i]!=",")
                {
                    v2=v2+value1[i];
                }
            }
            double v1=Double.parseDouble(v2);
            if(v1>=v)
            {
        
             String country=record.get("Country");
             System.out.println(country+value1);    
             sum=sum+1;
             System.out.println("Total Countries with big exports : "+sum);
             
        
        
            }
        }
        //return sum;
    }  
    public void tester_big_exporter()
    {
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        bigExporters_number(parser,"$999,999,999,999");
        
        
    }*/
    public void listExportersTwoProducts (CSVParser parser,String exportOfInterest1,String exportOfInterest2){
        for(CSVRecord record:parser)
        {
            String export=record.get("Exports");
            if(export.contains(exportOfInterest1)&&export.contains(exportOfInterest2) )
            {
        
             String country=record.get("Country");
             System.out.println(country);    
             
        
        
            }
        }
        
        
    }
    public String countryInfo(CSVParser parser,String country)
    {
        int flag=0;
        String exports="",value="";
        for(CSVRecord record:parser)
        {
            String country1=record.get("Country");
            if (record.get("Country").equals(country))
            {
        
              exports=record.get("Exports");
              //value=record.get("Value (Dollars)");
             //System.out.println(country); 
             flag=1;
             break;
             // return country+":"+exports+":"+value;
             //System.out.println(country+":"+exports+":"+value);
        
        
            }
            
        }
        if(flag==1)
        {
            //return country+":"+exports+":"+value;
            return country+":"+exports+":";
        }
            else
            {
                return "Not Found";
            }
        
        
    }
    public void WhoExports()
    {
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        listexporters(parser,"gold");
        
    }
    public void tester()
    {
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        listExportersTwoProducts(parser,"cotton","flowers");
        
    }
     public void tester_number()
    {
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        int sum=numberOfExporters(parser,"cocoa");
        System.out.println(sum);
        
    }
    public void tester_country()
    {
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        String info=countryInfo(parser,"Nauru");
        System.out.println(info);
        
    }
    public CSVRecord hottesthour(CSVParser parser)
    {
        CSVRecord largestsofar=null;
        for(CSVRecord currentRow :parser)
        {
            if (largestsofar==null)
            {
                largestsofar=currentRow;
            }
            else{
                double currentTemp=Double.parseDouble(currentRow.get("TemperatureF"));
                 double largetsTemp=Double.parseDouble(largestsofar.get("TemperatureF"));
                 if(currentTemp>largetsTemp)
                 {
                     largestsofar=currentRow;
                    }
                
                
            }
        }
        return largestsofar;
    }
    public void tester_hottest()
    {
        FileResource fr=new FileResource();
        CSVRecord largest=hottesthour(fr.getCSVParser());
        System.out.println("Hottest Temperature was : "+largest.get("TemperatureF")+ " at : " +largest.get("TimeEST"));
        
    }
     public CSVRecord hottest_in_multiple()
     {
         CSVRecord largestSoFar=null;
         DirectoryResource dr=new DirectoryResource();
         for(File f:dr.selectedFiles())
         {
             FileResource fr=new FileResource(f);
             CSVRecord currentRow= hottesthour(fr.getCSVParser());
             
             if (largestSoFar==null)
            {
                largestSoFar=currentRow;
            }
            else{
                double currentTemp=Double.parseDouble(currentRow.get("TemperatureF"));
                 double largetsTemp=Double.parseDouble(largestSoFar.get("TemperatureF"));
                 if(currentTemp>largetsTemp)
                 {
                     largestSoFar=currentRow;
                    }
                
                
            }
            
         
         
         
        }
        return largestSoFar;   
    
}
public void tester_hottest_many()
    {
        
        CSVRecord largest=hottest_in_multiple();
        System.out.println("Hottest Temperature was : "+largest.get("TemperatureF")+ " at : " +largest.get("DateUST"));
        
    }
    public CSVRecord coldesthour(CSVParser parser)
    {
        CSVRecord smallestsofar=null;
        for(CSVRecord currentRow :parser)
        {
            if (smallestsofar==null)
            {
                smallestsofar=currentRow;
            }
            else{
                double currentTemp=Double.parseDouble(currentRow.get("TemperatureF"));
                 double smallestTemp=Double.parseDouble(smallestsofar.get("TemperatureF"));
                 if(currentTemp<smallestTemp)
                 {
                     smallestsofar=currentRow;
                    }
                
                
            }
        }
        return smallestsofar;
    }
     public void tester_coldest()
    {
        FileResource fr=new FileResource();
        CSVRecord smallest=coldesthour(fr.getCSVParser());
        System.out.println("Coldest Temperature was : "+smallest.get("TemperatureF"));
       
        
    }

    public CSVRecord lowestHumidityInFile (CSVParser parser)
    {
        CSVRecord lowestHumidity=null;
        int i=0;
        
        for(CSVRecord currentRow :parser)
        {
            
            
            if (lowestHumidity==null)
            {
                lowestHumidity=currentRow;
            }
            else{
                 String currentH=currentRow.get("Humidity");
                 
                 if(currentH=="N/A")
                 {
                      i=i+1;
                    }
                    else
                    {
                        double currentH1=Double.parseDouble(currentRow.get("Humidity"));
                        double largetsH=Double.parseDouble(lowestHumidity.get("Humidity"));
                 if(currentH1<largetsH)
                 {
                    lowestHumidity=currentRow;
                    }
                }
                
                
                
        }
       
        }
        return lowestHumidity;
    }
    public void tester_humidity()
    {
        FileResource fr=new FileResource();
        CSVRecord largest=lowestHumidityInFile (fr.getCSVParser());
        System.out.println("Lowest Humidity was : "+largest.get("Humidity")+ " at : " +largest.get("DateUTC")+" at Temperature : " +largest.get("TemperatureF"));
        
    }
     public CSVRecord lowest_humidity_in_multiple()
     {
         CSVRecord lowestsofar=null;
         DirectoryResource dr=new DirectoryResource();
         for(File f:dr.selectedFiles())
         {
             FileResource fr=new FileResource(f);
             CSVRecord currentRow= lowestHumidityInFile(fr.getCSVParser());
             
             if (lowestsofar==null)
            {
                lowestsofar=currentRow;
            }
            else{
                double currentH=Double.parseDouble(currentRow.get("Humidity"));
                 double lowestHH=Double.parseDouble(lowestsofar.get("Humidity"));
                 if(currentH<lowestHH)
                 {
                     lowestsofar=currentRow;
                    }
                
                
            }
            
         
         
         
        }
        return lowestsofar;   
    
}
public void tester_humidity_many()
    {
        
        CSVRecord largest=lowest_humidity_in_multiple();
        System.out.println("Lowest Humidity was : "+largest.get("Humidity")+" at : "+largest.get("DateUTC") );
        
    }
    public double  averageTemperatureInFile(CSVParser parser)
    {
        double sum=0,count=0;
        for(CSVRecord currentRow :parser)
        {
            
            
                double currentTemp=Double.parseDouble(currentRow.get("TemperatureF"));
                 sum=sum+currentTemp;
                 count++;             
                
            
        }
        double average=sum/count;
        return average;
    }
    public void tester_average_temperature()
    {
        FileResource fr=new FileResource();
        double averageTemp=averageTemperatureInFile (fr.getCSVParser());
        System.out.println("Average Temperature : "+averageTemp);
        
    }
    public double  averageTemperatureWithHighHumidityInFile(CSVParser parser,int value)
    {
        double sum=0,count=0;
        for(CSVRecord currentRow :parser)
        {
            
            
                double currentTemp=Double.parseDouble(currentRow.get("TemperatureF"));
                double currentHumidity=Double.parseDouble(currentRow.get("Humidity"));
                if(currentHumidity>=value)
                {
                 sum=sum+currentTemp;
                 count++;             
                }
            
        }
        double average=sum/count;
        return average;
    }
    public void tester_average_temperature_highHumidity()
    {
        FileResource fr=new FileResource();
        double averageTemp=averageTemperatureWithHighHumidityInFile (fr.getCSVParser(),80);
        System.out.println("Average Humidity : "+averageTemp);
        
    }
    
    public CSVRecord coldest_in_multiple()
     {
         CSVRecord coldestSoFar=null;
         DirectoryResource dr=new DirectoryResource();
         String file="";
         for(File f:dr.selectedFiles())
         {
             FileResource fr=new FileResource(f);
             CSVRecord currentRow= coldesthour(fr.getCSVParser());
             
             
             if (coldestSoFar==null)
            {
               coldestSoFar=currentRow;
            }
            else{
                double currentTemp=Double.parseDouble(currentRow.get("TemperatureF"));
                 double coldestTemp=Double.parseDouble(coldestSoFar.get("TemperatureF"));
                 if(currentTemp==-9999)
                 {
                     currentTemp=coldestTemp;
                    }
                 if(currentTemp<coldestTemp)
                 {
                     coldestSoFar=currentRow;
                     file=f.getName();
                     
                    }
                
                
            }
            
         
         
         
        }
        System.out.println(file);
        return  coldestSoFar; 
    
}


 public void tester_coldest_many()
    {
        CSVRecord coldest=coldest_in_multiple();
        System.out.println("coldest Temperature was : "+coldest.get("TemperatureF"));
        
        String v1="25,000";
       
    }
    public void readOneFile(int year)
    {
        String fname="data/yob"+year+".txt";
        FileResource fr=new FileResource(fname);
        CSVParser parser=fr.getCSVParser(false);
        for(CSVRecord rec:parser)
        {
            String name=rec.get(0);
            String gender=rec.get(1);
            
            
        }
        
    }
    public void printNames()
    {
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser(false);
        for(CSVRecord rec:parser)
        {
            int numBorn=Integer.parseInt(rec.get(2));
            if(numBorn<100)
            {
            String name=rec.get(0);
            String gender=rec.get(1);
            System.out.println("Name : " +name+ " Gender : " +gender);
        }
            
            
            
        }
        
    }
    public void totalBirth(FileResource fr)
    {
        int totalBirths=0;
        int totalGirls=0;
        int totalBoys=0;
        for(CSVRecord rec:fr.getCSVParser(false))
        {
             int numBorn=Integer.parseInt(rec.get(2));
             
             String gender=rec.get(1);
             //System.out.println("Gender : "+gender);
             if(gender.equals("F"))
             {
                 //System.out.println("Girl");
                 totalGirls=totalGirls+numBorn;
                 if(numBorn==1)
                 {
                     System.out.println(" A unique name : "+rec.get(0));
                     
                    }
                 
                }
                else if(gender.equals("M"))
                {
                    totalBoys=totalBoys+numBorn;
                    if(numBorn==1)
                 {
                     System.out.println(" A unique name : "+rec.get(0));
                     
                    }
                    
                }
            
             totalBirths=totalBirths+numBorn;
             
            
            
            
        }
        System.out.println("Total number of births : "+totalBirths);
        System.out.println("Total number of girls : " +totalGirls);
        
        System.out.println("Total number of boys : " +totalBoys);
        
    }
    public void test_birth()
    {
        
        FileResource fr=new FileResource();
        totalBirth(fr);
    }
    public void testTotalBirth()
    {
        FileResource fr=new FileResource();
        totalBirth(fr);
    }
    public int ret_no_Girls(FileResource f)
    {
        int girls=0;
        for(CSVRecord rec:f.getCSVParser(false))
        {
             String gender=rec.get(1);
             if(gender.equals("F"))
             {
                 girls=girls+1;
                }
        
    }
    //System.out.println("Number of girls according to get girls no methdod : "+girls);
    return girls;
}
public int ret_no_Boys(FileResource f)
    {
        int boys=0;
        for(CSVRecord rec:f.getCSVParser(false))
        {
             String gender=rec.get(1);
             if(gender.equals("M"))
             {
                 boys=boys+1;
                }
        
    }
    return boys;
}
    public int getRank(int year,String name,String gender)
    {
        //Finds Rank in a files
         String fname="data/yob"+year+".txt";
        //FileResource fr=new FileResource(fname);
        FileResource fr=new FileResource();
        int rank=0;
        int i=0;
          int tot_girls=ret_no_Girls(fr);
          //System.out.println("Total number of girls : "+tot_girls);
        int tot_boys=ret_no_Boys(fr);
        //System.out.println("Total number of boys : "+tot_boys);
        
      
        CSVParser parser=fr.getCSVParser(false);
        for(CSVRecord rec:parser)
        {
            String name1=rec.get(0);
            String gender1=rec.get(1);
            rank=rank+1;
            if(gender1.equals("M"))
            {
                i=i+1;
            }
            if(gender.equals("F"))
            {
                
                if(rank>tot_girls)
                {
                    rank=-1;
                    break;
                }
                
            }
            else if(gender.equals("M"))
            {
                //i=i+1;
                if(i>tot_boys)
                {
                    i=-1;
                    break;
                }
                
                
            }
            if(name1.equals(name)&& gender1.equals(gender))
            {
                //System.out.println("Name searched= " +name1);
                break;
            }
            
            
            
        }
        if(gender.equals("M"))
        {
            return i;
        }
         else
        {
        return rank;
       }
         
    
    
        
        
    }
    public int getRank_file(int year,String name,String gender,FileResource f)
    {
        //Finds Rank in a files
         String fname="data/yob"+year+".txt";
        //FileResource fr=new FileResource(fname);
       // FileResource fr=new FileResource();
        int rank=0;
        int i=0;
          int tot_girls=ret_no_Girls(f);
          //System.out.println("Total number of girls : "+tot_girls);
        int tot_boys=ret_no_Boys(f);
        //System.out.println("Total number of boys : "+tot_boys);
        
      
        CSVParser parser=f.getCSVParser(false);
        for(CSVRecord rec:parser)
        {
            String name1=rec.get(0);
            String gender1=rec.get(1);
            rank=rank+1;
            if(gender1.equals("M"))
            {
                i=i+1;
            }
            if(gender.equals("F"))
            {
                
                if(rank>tot_girls)
                {
                    rank=-1;
                    break;
                }
                
            }
            else if(gender.equals("M"))
            {
                //i=i+1;
                if(i>tot_boys)
                {
                    i=-1;
                    break;
                }
                
                
            }
            if(name1.equals(name)&& gender1.equals(gender))
            {
                //System.out.println("Name searched= " +name1);
                break;
            }
            
            
            
        }
        if(gender.equals("M"))
        {
            return i;
        }
         else
        {
        return rank;
       }
         
    
    
        
        
    }
    public String getName(int year,String gender,int rank)
    {
        //finds name on given rank
        String fname="data/yob"+year+".txt";
        //FileResource fr=new FileResource(fname);
        FileResource fr=new FileResource();
       // int rank=0;
        String name="";
        String boyName="";
          int tot_girls=ret_no_Girls(fr);
        int tot_boys=ret_no_Boys(fr);
        System.out.println("Number of girls : "+tot_girls+" Number of boys : "+tot_boys);
        int i=0;
        if(gender.equals("F"))
        {
            if(rank>tot_girls)
            {
                name="No Name Found";
            }
            
        }
        else 
        {
            
            if(rank>tot_boys)
            {
                name="No Name Found";
               
            }
            
        }
         CSVParser parser=fr.getCSVParser(false);
        for(CSVRecord rec:parser)
        {
            String name1=rec.get(0);
            String gender1=rec.get(1);
            i=i+1;
            if(gender.equals("F"))
            {
              if(i==rank)
                {
                name=rec.get(0);
                break;
                
                   }
             }
                    
            else if(gender.equals("M")&& i==tot_girls+rank)
              {
                       //int num=tot_girls+rank;
                       //System.out.println("Expected Rank : " +num+" i = " +i);
                       //if(i==num)
                       //{
                           System.out.println(" i : "+i);
                          boyName=rec.get(0);
                           break;
                        //}
                       
                       
             }
        }
        if(gender.equals("M"))
        {
            return boyName;
        }
        return name;
    }
    public void whatIsNameInYear(String name,int year,int newYear,String gender )
    {
        int rank=getRank(year,name,gender);
        String name_new=getName(newYear,gender,rank);
        System.out.println("Rank : " +rank);
        System.out.println(name +"born in "+year+" would be " +name_new+"  if she was born in "+ newYear);
        
        
    }
    private double averageRank;
    private int test_average;
    public int  yearOfHighestRank(String name,String gender)
    {
        //dosn't work properly
        DirectoryResource dr=new DirectoryResource();
         String file="";
         int year=0;
         int rank=0;
         int an=0;
         int flag=0;
         double sum=0,i=0;
         //int tot_girls=ret_no_Girls(fr);
        //int tot_boys=ret_no_Boys(fr);
         int highestRank=32000000;
         for(File f:dr.selectedFiles())
         {
             i=i+1;
             FileResource f1=new FileResource(f);
             String name1=f.getName();
             String year1=name1.substring(3,7);
             //System.out.println(year1);
             year=Integer.parseInt(year1);
             rank=getRank_file(year,name,gender,f1);
             sum=sum+rank;
             System.out.println("Year : "+year +"Rank : "+rank);
             if(rank==-1)
             {
                 flag=1;
                 test_average=1;
                 break;
                }
             else{
             if(rank<highestRank)
             {
                 highestRank=rank;
                 an=year;
                }
            }
            }
        
        if(flag==1)
        {
            
            return -1;
        }
        else
        {
            System.out.println("Highest Rank : " +highestRank+" In Year :"+an);
        System.out.println("Sum : " +sum + "  i : " +i);
        averageRank=sum/i;
        
            System.out.println("Average Rank : "+averageRank);
            return an;
        }
        
    }
    public void getTotalBirthsRankedHigher(int year,String name,String gender)
    {
        
        
        FileResource fr=new FileResource();
        int rank=0;
        int i=0;
          int tot_girls=ret_no_Girls(fr);
          //System.out.println("Total number of girls : "+tot_girls);
        int tot_boys=ret_no_Boys(fr);
        //System.out.println("Total number of boys : "+tot_boys);
        
      
        int sum_girls=0;
        int sum_boys=0;
        CSVParser parser=fr.getCSVParser(false);
        for(CSVRecord rec:parser)
        {
            String name1=rec.get(0);
            String gender1=rec.get(1);
            
            int numBorn=Integer.parseInt(rec.get(2));
            rank=rank+1;
            if(name1.equals(name)&& gender1.equals(gender))
            {
                //System.out.println("Name searched= " +name1);
                break;
            }
            
            if(gender1.equals("F"))
            {
                
               sum_girls=sum_girls+numBorn;
                
            }
            else if(gender1.equals("M"))
            {
                //i=i+1;
                sum_boys=sum_boys+numBorn;
                
            }
            
            
            
            
        }
        if(gender.equals("M"))
        {
            System.out.println("Number of Boys Ranked Higher than : "+name+" is : "+sum_boys);
        }
         else
        {
        System.out.println("Number of Girls Ranked Higher than : "+name+" is : "+sum_girls);
       }
         
        
        
        
    }
}
