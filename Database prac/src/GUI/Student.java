
package GUI;


public class Student implements Runnable{
    String studentName=null;
    public Student(String newName){
        studentName= newName;
    }
    
    public void race(){
        for(int i=0;i<10;i++){
           System.out.println( studentName+ ""+ "is running" + i + "meters"); 
        }
        
    }
    @Override
    public void run(){
        race();
    }
}
