
/**
 * Program reads data files of popular data files of baby names and extract meaningful data
 * 
 * @author Garima Aggarwal
 * 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
 
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
       
        int rank=0;
        int i=0;
          int tot_girls=ret_no_Girls(f);
         
        int tot_boys=ret_no_Boys(f);
        
      
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
