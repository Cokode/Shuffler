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
        travelList.add("New York");
        travelList.add("Berlin");
        travelList.add("Abuja");

        System.out.println("Some countries already added to your list\n");
        menu();
    }

    private static boolean putInOrder(LinkedList<String> newLinked, String newCity){
        ListIterator<String> newList = newLinked.listIterator();

        while(newList.hasNext()) {
            int compareList = newList.next().compareTo(newCity);
            if(compareList == 0) {
                System.out.println(newCity + " is already existing in file\n");
                return false;
            } else if(compareList > 0) {
                newList.previous();
                newList.add(newCity);
                return true;
            }
        }
        newList.add(newCity);
        return true;
    }
    private static void shuffler(LinkedList<String> newLinked, String newCity){
        ListIterator<String> newList = newLinked.listIterator();

        while(newList.hasNext()) {
            int compareList = newList.next().compareTo(newCity);
            if(compareList == 0) {
                return;
            } else if(compareList > 0) {
                newList.previous();
                newList.add(newCity);
                return;
            }
        }
        newList.add(newCity);
    }
    public static void shuffleList(LinkedList<String> newOne) {
        LinkedList<String> toDisplay  = new LinkedList<>();

        for(int i = 0; i < newOne.size(); i ++) {
            String name = newOne.get(i);
            shuffler(toDisplay, name);
        }
        showList(toDisplay);
        travelList = toDisplay;
        System.out.println("Note: Names appearing twice is removed.\n");
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

    /**
     * This method() was moved into Menu() and accessed from the switch statement
     */
    public static void placesToVisited(LinkedList<String> visitedPlace) {

        boolean flag = false;
        ListIterator<String> parseStrings = visitedPlace.listIterator();
        boolean goingForward = true;

        if(parseStrings.next().isEmpty()){
            System.out.println("There is nothing in the list");
            return;
        } else {
            parseStrings.previous();
            System.out.println("Now visiting --- > " + parseStrings.next());
        }

        while(!flag) {
            System.out.println("""
            \nPress
            (1) to go next city.
            (2) to go back to previous city.
            (3) to go back to Main Menu options.
            """);

            Scanner myScanner = new Scanner(in);
            int input = myScanner.nextInt();
            myScanner.nextLine();

            switch (input) {
                case 1 -> {
                    if(goingForward) {
                        if(parseStrings.hasNext()) {
                            System.out.println("\nNow visiting --> " + parseStrings.next());
                            if(!parseStrings.hasNext()){
                                parseStrings.previous();
                                goingForward = false;
                            }
                        }
                    } else {
                        System.out.println("\nYou've reached the end of the list.");
                    }
                }
                case 2 -> {
                    if(!goingForward){
                        if (parseStrings.hasPrevious()) {
                            System.out.println("\nNow visiting --> " + parseStrings.previous());
                            if(!parseStrings.hasPrevious()){
                                parseStrings.next();
                                goingForward = true;
                            }
                        }
                    } else {
                        System.out.println("\nYou've reached the beginning of the list.");
                    }
                }
                case 3 -> flag = true;
            }
        }
    }

    public static void addMachine(String toAdd){
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
        System.out.println("================================\n");
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
                    //flag = true;
                    System.out.println("Goodbye...");
                    return;
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
                    System.out.println("SHUFFLED LIST:");
                    shuffleList(travelList);
                }
                case 9 -> placesToVisited(travelList);
                default -> System.out.println("Invalid selection ");
            }

            System.out.println("""
                    Press
                    (5) to show Menu options
                    (6) to quit this application
                    """);
        } while (true);
    }

    public static void menuOptions() {
        System.out.println(
                """
                Press
                (1) remove name from list.
                (2) add name to list.
                (3) to display names on list.
                (4) to search names on list.
                (5) to show menu options.
                (6) to quit application.
                (7) to change position of names.
                (8) to shuffle list alphabetically.
                (9) to move through places to visit.
                """);
    }
}
