/**
 *Read multiple files of baby names to find fun data
 * 
 * @author: Garima Aggarwal,coursera
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class babyNames {

 public void readOneFile(int year) {
  String fname = "data/yob" + year + ".txt";
  FileResource fr = new FileResource(fname);
  CSVParser parser = fr.getCSVParser(false);
  for (CSVRecord rec: parser) {
   String name = rec.get(0);
   String gender = rec.get(1);


  }

 }
 public void printNames() {
     //Prints all names whose total number of births >500 in the selected file
  FileResource fr = new FileResource();
  CSVParser parser = fr.getCSVParser(false);
  for (CSVRecord rec: parser) {
   int numBorn = Integer.parseInt(rec.get(2));
   if (numBorn > 500) {
    String name = rec.get(0);
    String gender = rec.get(1);
    System.out.println("Name : " + name + " Gender : " + gender);
   }



  }

 }
 public void totalBirth(FileResource fr) {
  int totalBirths = 0;
  int totalGirls = 0;
  int totalBoys = 0;
  for (CSVRecord rec: fr.getCSVParser(false)) {
   int numBorn = Integer.parseInt(rec.get(2));

   String gender = rec.get(1);
   //System.out.println("Gender : "+gender);
   if (gender.equals("F")) {
    
    totalGirls = totalGirls + numBorn;
    if (numBorn == 1) {
     System.out.println(" A unique name : " + rec.get(0));

    }

   } else if (gender.equals("M")) {
    totalBoys = totalBoys + numBorn;
    if (numBorn == 1) {
     System.out.println(" A unique name : " + rec.get(0));

    }

   }

   totalBirths = totalBirths + numBorn;




  }
  System.out.println("Total number of births : " + totalBirths);
  System.out.println("Total number of girls : " + totalGirls);

  System.out.println("Total number of boys : " + totalBoys);

 }
 public void test_birth() {
     //prints total number of births 

  FileResource fr = new FileResource();
  totalBirth(fr);
 }
 public void testTotalBirth() {
     //gives total number of births,total number of females and total number of males born in the selected year
  FileResource fr = new FileResource();
  totalBirth(fr);
 }
 public int ret_no_Girls(FileResource f) {
  int girls = 0;
  for (CSVRecord rec: f.getCSVParser(false)) {
   String gender = rec.get(1);
   if (gender.equals("F")) {
    girls = girls + 1;
   }

  }
  
  return girls;
 }
 public int ret_no_Boys(FileResource f) {
  int boys = 0;
  for (CSVRecord rec: f.getCSVParser(false)) {
   String gender = rec.get(1);
   if (gender.equals("M")) {
    boys = boys + 1;
   }

  }
  return boys;
 }
 public int getRank(int year, String name, String gender) {
  //Finds Rank in a files
  String fname = "data/yob" + year + ".txt";
 
  FileResource fr = new FileResource();
  int rank = 0;
  int i = 0;
  int tot_girls = ret_no_Girls(fr);
  
  int tot_boys = ret_no_Boys(fr);
  

  CSVParser parser = fr.getCSVParser(false);
  for (CSVRecord rec: parser) {
   String name1 = rec.get(0);
   String gender1 = rec.get(1);
   rank = rank + 1;
   if (gender1.equals("M")) {
    i = i + 1;
   }
   if (gender.equals("F")) {

    if (rank > tot_girls) {
     rank = -1;
     break;
    }

   } else if (gender.equals("M")) {
    //i=i+1;
    if (i > tot_boys) {
     i = -1;
     break;
    }


   }
   if (name1.equals(name) && gender1.equals(gender)) {
    
    break;
   }



  }
  if (gender.equals("M")) {
   return i;
  } else {
   return rank;
  }





 }
 public int getRank_file(int year, String name, String gender, FileResource f) {
  //Finds Rank in a files
  String fname = "data/yob" + year + ".txt";
 
  int rank = 0;
  int i = 0;
  int tot_girls = ret_no_Girls(f);
 
  int tot_boys = ret_no_Boys(f);
  CSVParser parser = f.getCSVParser(false);
  for (CSVRecord rec: parser) {
   String name1 = rec.get(0);
   String gender1 = rec.get(1);
   rank = rank + 1;
   if (gender1.equals("M")) {
    i = i + 1;
   }
   if (gender.equals("F")) {

    if (rank > tot_girls) {
     rank = -1;
     break;
    }

   } else if (gender.equals("M")) {
    //i=i+1;
    if (i > tot_boys) {
     i = -1;
     break;
    }


   }
   if (name1.equals(name) && gender1.equals(gender)) {
   
    break;
   }



  }
  if (gender.equals("M")) {
   return i;
  } else {
   return rank;
  }





 }
 public String getName(int year, String gender, int rank) {
  //finds name on given rank
  String fname = "data/yob" + year + ".txt";
 
  FileResource fr = new FileResource();
  
  String name = "";
  String boyName = "";
  int tot_girls = ret_no_Girls(fr);
  int tot_boys = ret_no_Boys(fr);
  System.out.println("Number of girls : " + tot_girls + " Number of boys : " + tot_boys);
  int i = 0;
  if (gender.equals("F")) {
   if (rank > tot_girls) {
    name = "No Name Found";
   }

  } else {

   if (rank > tot_boys) {
    name = "No Name Found";

   }

  }
  CSVParser parser = fr.getCSVParser(false);
  for (CSVRecord rec: parser) {
   String name1 = rec.get(0);
   String gender1 = rec.get(1);
   i = i + 1;
   if (gender.equals("F")) {
    if (i == rank) {
     name = rec.get(0);
     break;

    }
   } else if (gender.equals("M") && i == tot_girls + rank) {
    
    System.out.println(" i : " + i);
    boyName = rec.get(0);
    break;
   


   }
  }
  if (gender.equals("M")) {
   return boyName;
  }
  return name;
 }
 public void whatIsNameInYear(String name, int year, int newYear, String gender) {
     //Tells what will be the most likely name if the person was born in newYear instead of year
  int rank = getRank(year, name, gender);
  String name_new = getName(newYear, gender, rank);
  System.out.println("Rank : " + rank);
  System.out.println(name + "born in " + year + " would be " + name_new + "  if she was born in " + newYear);


 }
 private double averageRank;
 private int test_average;
 public int yearOfHighestRank(String name, String gender) {
     //Out of multiple files selected for years,gives the year which has highest rank for the input name
  DirectoryResource dr = new DirectoryResource();
  String file = "";
  int year = 0;
  int rank = 0;
  int an = 0;
  int flag = 0;
  double sum = 0, i = 0;

  int highestRank = 32000000;
  for (File f: dr.selectedFiles()) {
   i = i + 1;
   FileResource f1 = new FileResource(f);
   String name1 = f.getName();
   String year1 = name1.substring(3, 7);
   //System.out.println(year1);
   year = Integer.parseInt(year1);
   rank = getRank_file(year, name, gender, f1);
   sum = sum + rank;
   System.out.println("Year : " + year + "Rank : " + rank);
   if (rank == -1) {
    flag = 1;
    test_average = 1;
    break;
   } else {
    if (rank < highestRank) {
     highestRank = rank;
     an = year;
    }
   }
  }

  if (flag == 1) {

   return -1;
  } else {
   System.out.println("Highest Rank : " + highestRank + " In Year :" + an);
   System.out.println("Sum : " + sum + "  i : " + i);
   averageRank = sum / i;

   System.out.println("Average Rank : " + averageRank);
   return an;
  }

 }
 public void getTotalBirthsRankedHigher(int year, String name, String gender) {

 //Gives the number of girls/boys ranked higher than the input name in the selected years
  FileResource fr = new FileResource();
  int rank = 0;
  int i = 0;
  int tot_girls = ret_no_Girls(fr);
    int tot_boys = ret_no_Boys(fr);
 


  int sum_girls = 0;
  int sum_boys = 0;
  CSVParser parser = fr.getCSVParser(false);
  for (CSVRecord rec: parser) {
   String name1 = rec.get(0);
   String gender1 = rec.get(1);

   int numBorn = Integer.parseInt(rec.get(2));
   rank = rank + 1;
   if (name1.equals(name) && gender1.equals(gender)) {
    
    break;
   }

   if (gender1.equals("F")) {

    sum_girls = sum_girls + numBorn;

   } else if (gender1.equals("M")) {
    //i=i+1;
    sum_boys = sum_boys + numBorn;

   }




  }
  if (gender.equals("M")) {
   System.out.println("Number of Boys Ranked Higher than : " + name + " is : " + sum_boys);
  } else {
   System.out.println("Number of Girls Ranked Higher than : " + name + " is : " + sum_girls);
  }




 }


}
