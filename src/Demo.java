import java.util.*;

import static java.lang.System.in;

public class Demo {
    static LinkedList<String> travelList = new LinkedList<>();

    public static void main(String[] args) {
        travelList.add("Spain");
        travelList.add("London");
        travelList.add("New York");
        travelList.add("Abuja");
        travelList.add("Berlin");
        travelList.add("Cyprus");

        menu();
    }

    public static void shuffleList(LinkedList<String> newOne) {
        LinkedList<String> toDisplay  = new LinkedList<>();
        for(int i = 0; i < newOne.size(); i ++) {
            String name = newOne.get(i);
            putInOrder(toDisplay,name);
            travelList = toDisplay;
        }
        showList(toDisplay);
    }

    public static void removeMachine(String toRemove){
        if(searchMachine(toRemove) == null){
            System.out.println(toRemove + " is not on file.");
            return;
        }

        travelList.remove(toRemove);
        System.out.println(toRemove + " is successfully deleted.");
    }

    public static String searchMachine(String searchName){
        for(int s = 0; s < travelList.size(); s++){
            if(Objects.equals(travelList.get(s), searchName)) {
                return searchName;
            }
        }
        return null;
    }

    public static void findName(String nameToSearch){
        if(searchMachine(nameToSearch) == null) {
            System.out.println(nameToSearch + " not on file.");
        }
        else {
            System.out.println(nameToSearch + " is on File.");
        }
    }

    public static void changeIndex(String nameToChange, int indexPosition){
        if(searchMachine(nameToChange) == null) {
            System.out.println(nameToChange + " is not on the list.");
        }
        else {
            travelList.remove(nameToChange);
            travelList.add(indexPosition, nameToChange);
            System.out.println("Index position of " + nameToChange + " changed." );
        }
    }

    public static void addMachine(String toAdd){
        int count = 1;

        for(int s = 0; s < travelList.size(); s++){
            if(Objects.equals(travelList.get(s), toAdd)) {
                System.out.println("The name already exist in file");
                return;
            }
        }
        travelList.add(toAdd);
        System.out.println(toAdd + " was added to list");
    }

    private static void showList(LinkedList<String> myList) {
        Iterator<String> i= myList.iterator();
        int count = 0;
        while(i.hasNext()){
            count += 1;
            System.out.println("My travel List (" + count + ") -----> " + i.next());
        }
        System.out.println("============================");
    }

    public static void menu(){
        menuOptions();
        int number;
        boolean flag = false;

        do {
            Scanner myScanner = new Scanner(in);
            number = myScanner.nextInt();
            myScanner.nextLine();

            switch (number) {
                case 1 -> {
                    System.out.println("Type name to remove from file ");
                    String name = myScanner.nextLine();
                    removeMachine(name);
                }
                case 2 -> {
                    System.out.println("Type name to add to file ");
                    String name = myScanner.nextLine();
                    addMachine(name);
                }
                case 3 -> showList(travelList);
                case 4 -> {
                    System.out.println("Type name to find in file ");
                    String name = myScanner.nextLine();
                    findName(name);
                }
                case 5 -> menuOptions();
                case 6 -> {
                    flag = true;
                    System.out.println("Goodbye...");
                }
                case 7 -> {
                    System.out.println("input index number");
                    int indexOf = myScanner.nextInt();
                    myScanner.nextLine();
                    System.out.println("input name");
                    String nameOf = myScanner.nextLine();
                    myScanner.nextLine();
                    changeIndex(nameOf, indexOf);
                }
                case 8 -> {
                    System.out.println("SHUFFLED LIST: \n");
                    shuffleList(travelList);
                }

                default -> System.out.println("Invalid selection ");
            }

            System.out.println("""

                    Press
                    (5) to show Menu options\s
                    (6) to quit this application""");
        } while (!flag);
    }

    public static void sortList(LinkedList<String> myLinkedList){

    }

    public static void menuOptions() {
        System.out.println(
                """
                Press
                (1) remove name from list.
                (2) add name to list.
                (3) to show name on list.
                (4) to search names on list.
                (5) to show menu options.
                (6) to quit application.
                (7) to change position of names.
                (8) to shuffle list alphabetically.
                """);
    }

    private static boolean putInOrder(LinkedList<String> newLinked, String newCity){
        ListIterator<String> newList = newLinked.listIterator();

        while(newList.hasNext()) {
            int compareList = newList.next().compareTo(newCity);
            if(compareList == 0) {
                System.out.println(newCity + " is already existing in file");
                return false;
            } else if( compareList > 0) {
                newList.previous();
                newList.add(newCity);
                return true;
            } else if (compareList < 0 ){
                // continue;
            }
        }
        newList.add(newCity);
        return true;
    }
}
