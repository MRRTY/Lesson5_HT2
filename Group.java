import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by Greg on 24.01.2017.
 */
public class Group implements Voenkom{
   private final ArrayList<Student> students = new ArrayList<>();
   private int count = 0;

    public Group() {
    }
    public Group(File file) {
        readGroupFromFile(file);
    }
    public void addStudent(Student s){
        try {
            if(count<10) {
                this.students.add(s);
                count++;
            }else
                throw new OutOfListException();
        }catch (OutOfListException e){
            System.out.println("Sorry, but group is full...");
        }
    }
    public void addStudent(){
        try {
            if(count<10) {
                try{
                    this.students.add(readStudent());
                    count++;
                }catch(Exception ex){
                    System.out.println("Something goes wrong...");
                }
            }else
                throw new OutOfListException();
        }catch (OutOfListException e){
            System.out.println("Sorry, but group is full...");
        }
    }
    private void readGroupFromFile(File file){
        try(Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                String firstName = sc.next();
                String lastName = sc.next();
                int age = sc.nextInt();
                double weight = sc.nextDouble();
                boolean sex = sc.nextBoolean();
                int index = sc.nextInt();
                String faculty = sc.next();
                String university = sc.next();
                double averMark = sc.nextDouble();
                addStudent(new Student(firstName,lastName,age,weight,sex,index,faculty,university,averMark));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void saveGroup(File file){
        try(PrintWriter pr = new PrintWriter(file)) {
            for(Student s : students){
                pr.println(s.toString());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public Student readStudent(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter First Name (String)");
        String firstName = sc.next();
        System.out.println("Enter Last Name (String)");
        String lastName = sc.next();
        System.out.println("Enter age (Int)");
        int age = sc.nextInt();
        System.out.println("Enter weight (Double)");
        double weight = sc.nextDouble();
        System.out.println("Enter sex (Boolean)");
        boolean sex = sc.nextBoolean();
        System.out.println("Enter id (Int)");
        int index = sc.nextInt();
        System.out.println("Enter faculty (String)");
        String faculty = sc.next();
        System.out.println("Enter university (String)");
        String university = sc.next();
        System.out.println("Enter average mark  (Double)");
        double averMark = sc.nextDouble();
        return new Student(firstName,lastName,age,weight,sex,index,faculty,university,averMark);
    }
    public Student search(String lastName){
        for (Student s : students) {
            if(s.getLastName().equals(lastName))
                return s;
        }
        return null;
    }
    public void sort(){
        Collections.sort(students, (s1,s2) -> s1.getLastName().compareTo(s2.getLastName()) );

    }
    public void sort(int parametr,boolean direction){
        switch (parametr){
            case 1 : Collections.sort(students, (s1,s2) -> direction?s1.getLastName().compareTo(s2.getLastName()):s2.getLastName().compareTo(s1.getLastName()) );
            break;
            case 2 : Collections.sort(students, (s1,s2) -> direction?s1.getFirstName().compareTo(s2.getFirstName()):s2.getFirstName().compareTo(s1.getFirstName()) );
            break;
            case 3 : Collections.sort(students, (s1,s2) -> direction?s1.getFaculty().compareTo(s2.getFaculty()):s2.getFaculty().compareTo(s1.getFaculty()) );
            break;
            case 4 : Collections.sort(students, (s1,s2) -> direction?s1.getUniversity().compareTo(s2.getUniversity()):s2.getUniversity().compareTo(s1.getUniversity()) );
            break;
            case 5 : Collections.sort(students, (s1,s2) -> direction? Integer.valueOf(s1.getIndex()).compareTo(s2.getIndex()): Integer.valueOf(s2.getIndex()).compareTo(s1.getIndex()) );
            break;
            case 6 : Collections.sort(students, (s1,s2) -> direction? Integer.valueOf(s1.getAge()).compareTo(s2.getAge()): Integer.valueOf(s2.getAge()).compareTo(s1.getAge()) );
            break;
            case 7 : Collections.sort(students, (s1,s2) -> direction? Integer.valueOf(s1.getAge()).compareTo(s2.getAge()): Integer.valueOf(s2.getAge()).compareTo(s1.getAge()) );
            break;
            case 8 : Collections.sort(students, (s1,s2) -> direction? Double.valueOf(s1.getWeight()).compareTo(s2.getWeight()): Double.valueOf(s2.getWeight()).compareTo(s1.getWeight()));
            break;
            case 9 : Collections.sort(students, (s1,s2) -> direction?  Double.valueOf(s1.getAverMark()).compareTo(s2.getAverMark()): Double.valueOf(s2.getAverMark()).compareTo(s1.getAverMark()) );
            break;
            case 10 : Collections.sort(students, (s1,s2) -> direction? Boolean.valueOf(s1.isSex()).compareTo(s2.isSex()): Boolean.valueOf(s2.isSex()).compareTo(s1.isSex()) );
            break;
        }
    }

    @Override
    public ArrayList<Student> getStudents() {
        ArrayList<Student> st = new ArrayList<>();
        for (Student s:students) {
            if(s.getAge() > 18 && s.isSex())
                st.add(s);
        }
        return st;
    }

    @Override
    public void printStudents() {
        ArrayList<Student> st =getStudents();
        StringBuilder sb = new StringBuilder();
        sb.append("Ready for duty{");
        sb.append(System.lineSeparator());
        for (Student s: st) {
            sb.append(s.toString());
            sb.append(System.lineSeparator());
        }
        sb.append("}");
        System.out.println(sb.toString());
    }

    @Override
    public String toString() {
        sort();
        StringBuilder sb = new StringBuilder();
        sb.append("Group{");
        sb.append(System.lineSeparator());
        for (Student s: students) {

            sb.append(s.toString());
            sb.append(System.lineSeparator());
        }
        sb.append("}");
        return sb.toString();

    }

    public void delete(String lastName){
        Student s = search(lastName);
        if(s!=null)
            students.remove(s);
    }
}
