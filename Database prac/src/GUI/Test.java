
package GUI;


public class Test {
    public static void main(String args[]){
        Student s1= new Student("Luffy");
        Student s2= new Student("Nami");
        Student s3= new Student("Zoro");
        /*s1.race();
        s2.race();
        s3.race();*/
        
        Thread t1= new Thread(s1);
        Thread t2= new Thread(s2);
        Thread t3= new Thread(s3);
        
        t1.start();
        t2.start();
        t3.start();
        
    }
}
