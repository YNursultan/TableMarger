import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

@WebServlet(name = "Servlet", value = "/Servlet")
public class Servlet extends HttpServlet {

    Table table = new Table();
    ArrayList<File> list = new ArrayList<>();
    ArrayList<ArrayList<String>> ListOfTeacher = new ArrayList<>();
    ArrayList<Integer> timeMax = new ArrayList<>();
    ArrayList<String> newTime = null;
    private void timeCount(ArrayList<String> list,ArrayList<String> time){
        if (list.size() != 0){
            for (String line : list){
                if (line.contains(":")){
                    time.add(line.substring(0,11));
                }
            }
        }
    }
    private static void searchFile(File rootFile, List<File> fileList, String name){
        if (rootFile.isDirectory()){
            File[] directoryFiles = rootFile.listFiles();
            if (directoryFiles != null){
                for (File file : directoryFiles){
                    //System.out.println("Search at: " + file.getAbsolutePath());
                    if (file.isDirectory()){
                        searchFile(file,fileList,name);
                    }else{
                        if (file.getName().toString().equals(name) && file.getName().endsWith(".pdf")){
                            //System.out.println(file.getName());
                            //System.out.println(file.getAbsolutePath());
                            fileList.add(file);
                            break;
                        }
                    }
                }
            }
        }
    }
    private boolean same(String time[],String line){
        boolean c = true;
        for (String a : time){
            if (a.equals(line)){
                c = false;
            }
        }
        return c;
    }
    private void lineMerge(ArrayList<String> list){
        if (list.size() != 0){
            for (int i = 0; i < list.size()-1; i++){
                String line = list.get(i);
                String line2 = list.get(i+1);
                if(line2 != null){
                    if(!line2.substring(0,4).contains(":")) {
                        list.set(i,line + line2);
                    }
                }
            }
        }
    }
    private void lineDelete(ArrayList<String> list){
        if (list.size() != 0){
            list.removeIf(element -> !element.substring(0, 4).contains(":"));
        }
    }
    private void removeLeft(ArrayList<String> list){
        if (list.size() != 0){
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).contains(":") && list.get(i).contains("Day of the week Time Discipline Classroom Type Lecturer")){
                    String newLine = list.get(i).replace("Day of the week Time Discipline Classroom Type Lecturer","");
                    list.set(i,newLine);
                }
            }
        }
    }
    private void removeLesson(ArrayList<String> list){
        if (list.size() != 0){
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).contains("practice")){
                    String newLine = list.get(i).replace("practice ","");
                    list.set(i,newLine);
                }
                if (list.get(i).contains("laboratory")){
                    String newLine = list.get(i).replace("laboratory ","");
                    list.set(i,newLine);
                }
            }
        }
    }
    private void wordDelete(String line){
        if (line != null){
            if (line.contains("practice")){
                String target= String.copyValueOf("practice ".toCharArray());
                line = line.replace(target,"");
            }
            if (line.contains("online lecture")){
                String target= String.copyValueOf("online lecture ".toCharArray());
                line = line.replace(target,"");
            }
        }
    }
    private void arrCorrector(String arr[], int index){
        for (int i = index; i < arr.length; i++){
            arr[i] = arr[i+1];
        }
    }
    private void addTeacher(ArrayList<String> list,String teacher){
        if(list.size() != 0){
            for (int i = 0; i < list.size(); i++){
                list.set(i,list.get(i).substring(0,11) + " " + teacher + " " + list.get(i).substring(12));
            }
        }
    }
    public static ArrayList<String> removeDuplicates(ArrayList<String> list) {
        // Create a new ArrayList
        ArrayList<String> newList = new ArrayList<>();
        // Traverse through the first list
        for (String element : list) {
            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        // return the new list
        return filter(newList);
    }
    public static ArrayList<String> filter(ArrayList<String> time){
        ArrayList<String> newList = new ArrayList<>();
        ArrayList<Integer> num = new ArrayList<>();
        for (String element : time){
            String arr[] = element.toString().substring(0,5).split(":");
            int n = ((((Integer.parseInt(arr[0].substring(0,1))*10) + (Integer.parseInt(arr[0].substring(1))))*60)+ ((Integer.parseInt(arr[1].substring(0,1))*10) + (Integer.parseInt(arr[1].substring(1)))));
            num.add(n);
        }
        Collections.sort(num);
        for (Integer element : num){
            String n;
            if (element%60 == 0){
                if (element < 600){
                    n = "0" + String.valueOf(element/60) + ":";
                }else {
                   n = String.valueOf(element/60) + ":";
                }
                n = n + "00";
            }else {
                if (element < 600){
                    n = "0" + String.valueOf(element/60) + ":";
                }else {
                    n = String.valueOf(element/60) + ":";
                }
                n = n + String.valueOf(element%60);
            }
            newList.add(n);
        }
        return newList;
    }
    private void findMax(ArrayList<String> list, ArrayList<String> time){
        for (int i = 0; i < time.size(); i++){
            int n = 0;
            for (int j = 0; j < list.size(); j++){
                if (list.get(j).contains(time.get(i))){
                    n++;
                }
            }
            if (timeMax.get(i) < n){
                timeMax.set(i,n);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("select") != null){
            String files = request.getParameter("file");
            //System.out.println(files);
            File root = new File("C:\\Users\\Админ\\IdeaProjects");
            searchFile(root,list,files);
            try (PDDocument document = PDDocument.load(new File(list.get(0).getAbsolutePath()))) {
                document.getClass();
                if (!document.isEncrypted()) {
                    PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                    stripper.setSortByPosition(true);
                    PDFTextStripper tStripper = new PDFTextStripper();
                    String pdfFileInText = tStripper.getText(document);
                    //System.out.println("Text:" + st);
                    // split by whitespace
                    String[] lines = pdfFileInText.split("\\r?\\n");
                    String teacher = null;
                    ArrayList<String> MondayList = new ArrayList<>();
                    ArrayList<String> TuesdayList = new ArrayList<>();
                    ArrayList<String> WednesdayList = new ArrayList<>();
                    ArrayList<String> ThursdayList = new ArrayList<>();
                    ArrayList<String> FridayList = new ArrayList<>();
                    ArrayList<String> SaturdayList = new ArrayList<>();

                    ArrayList<String> MondayTime = new ArrayList<>();
                    ArrayList<String> TuesdayTime= new ArrayList<>();
                    ArrayList<String> WednesdayTime = new ArrayList<>();
                    ArrayList<String> ThursdayTime = new ArrayList<>();
                    ArrayList<String> FridayTime= new ArrayList<>();
                    ArrayList<String> SaturdayTime = new ArrayList<>();
                    ArrayList<String> time = new ArrayList<>();

                    int choose = 0;
                    int repeat = 0;
                    for (String line : lines) {
                        if (line.equals("\"Approved by\"") || line.equals("Vice-Rector of " + "\"Astana IT University\"" + "LLP") ||
                                line.equals("______________S.M.Omirbayev") || line.equals("\"__\"__________________2022.") ||
                                line.equals("Dean                                    ___________") || line.contains("Director Of Academic Affairs Department ___________") ||
                                line.equals(" ")) {
                            continue;
                        }
                        if (line.equals("Day of the week Time Discipline Classroom Type Lecturer")) {
                            if (repeat == 1) {
                                //System.out.println("----------------------New Teacher Table-------------------");
                                repeat = 0;
                            }
                            repeat = 1;
                        }
                        if (line.contains("Teacher")) {
                            teacher = line.substring(8);
                            continue;
                        }
                        if (line.equals("Monday")) {
                            choose = 1;
                            continue;
                        }
                        if (line.equals("Tuesday")) {
                            choose = 2;
                            continue;
                        }
                        if (line.equals("Wednesday")) {
                                choose = 3;
                                continue;
                            }
                        if (line.equals("Thursday")) {
                                choose = 4;
                                continue;
                            }
                        if (line.equals("Friday")) {
                                choose = 5;
                                continue;
                            }
                        if (line.equals("Saturday")) {
                                choose = 6;
                                continue;
                            }
                        //wordDelete(line);
                        String newLine = line;
                        String week = "Monday Tuesday Wednesday Thursday Friday Saturday";
                        int change = choose;
                        if (line.contains(":")){
                            newLine = line.substring(0,11) + " " + teacher + " " + line.substring(12);
                        }
                        if (line.contains(":")){
                            if (line.contains("Monday")){
                                newLine = line.substring(7,18) + " " + teacher + " " + line.substring(19);
                                change = 1;
                            }
                            if (line.contains("Tuesday")){
                                newLine = line.substring(8,19) + " " + teacher + " " + line.substring(20);
                                change = 2;
                            }
                            if (line.contains("Wednesday")){
                                newLine = line.substring(10,21) + " " + teacher + " " + line.substring(22);
                                change = 3;
                            }
                            if (line.contains("Thursday")){
                                newLine = line.substring(9,20) + " " + teacher + " " + line.substring(21);
                                change = 4;
                            }
                            if (line.contains("Friday")){
                                newLine = line.substring(7,18) + " " + teacher + " " + line.substring(19);
                                change = 5;
                            }
                            if (line.contains("Saturday")){
                                newLine = line.substring(9,20) + " " + teacher + " " + line.substring(21);
                                change = 6;
                            }
                        }
                            switch (change) {
                                case (1):
                                    MondayList.add(newLine);
                                    break;
                                case (2):
                                    TuesdayList.add(newLine);
                                    break;
                                case (3):
                                    WednesdayList.add(newLine);
                                    break;
                                case (4):
                                    ThursdayList.add(newLine);
                                    break;
                                case (5):
                                    FridayList.add(newLine);
                                    break;
                                case (6):
                                    SaturdayList.add(newLine);
                                    break;
                            }
                        }
                        lineMerge(MondayList);
                        lineMerge(TuesdayList);
                        lineMerge(WednesdayList);
                        lineMerge(ThursdayList);
                        lineMerge(FridayList);
                        lineMerge(SaturdayList);

                        lineDelete(MondayList);
                        lineDelete(TuesdayList);
                        lineDelete(WednesdayList);
                        lineDelete(ThursdayList);
                        lineDelete(FridayList);
                        lineDelete(SaturdayList);

                        removeLeft(MondayList);
                        removeLeft(TuesdayList);
                        removeLeft(WednesdayList);
                        removeLeft(ThursdayList);
                        removeLeft(FridayList);
                        removeLeft(SaturdayList);

                        timeCount(MondayList,MondayTime);
                        timeCount(TuesdayList,TuesdayTime);
                        timeCount(WednesdayList,WednesdayTime);
                        timeCount(ThursdayList,ThursdayTime);
                        timeCount(FridayList,FridayTime);
                        timeCount(SaturdayList,SaturdayTime);

                        time.addAll(MondayTime);
                        time.addAll(TuesdayTime);
                        time.addAll(WednesdayTime);
                        time.addAll(ThursdayTime);
                        time.addAll(FridayTime);
                        time.addAll(SaturdayTime);

                        newTime = removeDuplicates(time);
                        for (String times : newTime){
                            timeMax.add(0);
                        }

                        findMax(MondayTime,newTime);
                        findMax(TuesdayTime,newTime);
                        findMax(WednesdayTime,newTime);
                        findMax(ThursdayTime,newTime);
                        findMax(FridayTime,newTime);
                        findMax(SaturdayTime,newTime);

                        ListOfTeacher.add(MondayList);
                        ListOfTeacher.add(TuesdayList);
                        ListOfTeacher.add(WednesdayList);
                        ListOfTeacher.add(ThursdayList);
                        ListOfTeacher.add(FridayList);
                        ListOfTeacher.add(SaturdayList);
                        request.setAttribute("timeName",newTime);
                        request.setAttribute("timeMax",timeMax);
                    }
                } catch (IOException exception) {exception.printStackTrace();}
            request.setAttribute("table",ListOfTeacher);
            request.getRequestDispatcher("table.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("Add") != null){
            String day = request.getParameter("week");
            String time = request.getParameter("time");
            String teacher = request.getParameter("teacher");
            String room = request.getParameter("room");

            ArrayList<String> Monday = ListOfTeacher.get(0);
            ArrayList<String> Tuesday = ListOfTeacher.get(1);
            ArrayList<String> Wednesday = ListOfTeacher.get(2);
            ArrayList<String> Thursday = ListOfTeacher.get(3);
            ArrayList<String> Friday = ListOfTeacher.get(4);
            ArrayList<String> Saturday = ListOfTeacher.get(5);
            ArrayList<String> newT = null;

            int n = newTime.indexOf(time);
            if (n != -1){
                timeMax.set(n,timeMax.get(n)+1);
                request.setAttribute("timeName",newTime);
            }else{
                newTime.add(time);
                newT = removeDuplicates(newTime);
                timeMax.add(newTime.indexOf(time),1);
                request.setAttribute("timeName",newT);
            }
            String newLine = time + "-14:00 " + teacher + " " + room;
            switch (day) {
                case ("Monday"):
                    Monday.add(newLine);
                    break;
                case ("Tuesday"):
                    Tuesday.add(newLine);
                    break;
                case ("Wednesday"):
                    Wednesday.add(newLine);
                    break;
                case ("Thursday"):
                    Thursday.add(newLine);
                    break;
                case ("Friday"):
                    Friday.add(newLine);
                    break;
                case ("Saturday"):
                    Saturday.add(newLine);
                    break;
            }
            ArrayList<ArrayList<String>> List = new ArrayList<>();

            List.add(Monday);
            List.add(Tuesday);
            List.add(Wednesday);
            List.add(Thursday);
            List.add(Friday);
            List.add(Saturday);

            request.setAttribute("timeMax",timeMax);
            request.setAttribute("table",List);
            request.getRequestDispatcher("table.jsp").forward(request,response);
        }
    }
}
