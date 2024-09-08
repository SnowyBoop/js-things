package de.uniba.wiai.dsg.ajp.assignment2;
import java.util.*;
import java.util.Scanner;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.logic.ProjectServiceImpl;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.logic.IssueTrackerImpl;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.IssueTrackingException;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.ProjectService;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model.Issue;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model.Milestone;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model.Severity;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model.Type;

public class Main {
    public static String[] firstMenu = {"Exit", "Validate and Load Project", "Create New Project" };
    public static String[] secondMenu = {   "Exit",
                                            "Add Milestone",
                                            "Remove Milestone and Cleanup",
                                            "List Milestones",
                                            "Add Issue",
                                            "Close Issue",
                                            "Remove Issue and Cleanup",
                                            "List Issues",
                                            "Print JSON on Console",
                                            "Save JSON to File",
                                            "Add Milestone"};
    public static String[] displayArray = firstMenu;
    public static ProjectServiceImpl PService = new ProjectServiceImpl();
    public static IssueTrackerImpl ITracker = new IssueTrackerImpl();

	public static void main(String[] args) {
        int menuDepth = 0;
        selectionMenu(menuDepth);
	}

    public static void selectionMenu(int menuDepth) {
        Boolean switchMenu = false;
        printOptions();
        String toFunction = null;
        int index = Integer.parseInt(getValidUserInput("int","Select an ID"));
        //String newString = getValidUserInput(scanner,"string");

        if (index >= 0 && index < displayArray.length) {

            if(menuDepth == 1) {
                switch(index) {
                    case 0: System.out.println("Returning back to main menu...");
                            drawBar();
                            displayArray = firstMenu;
                            switchMenu = true;
                            break;
                    case 1:
                        String IDtoFunction = getValidUserInput("int","Please enter the new Milestone ID:");
                        String StringToFunction = getValidUserInput("string","Please enter the new Milestone Name:");
                        String StringToDecoder = getValidUserInput("string","Please enter the Issue(s) ID(s) [can be multiple IDs comma seperated]:");
                        java.util.Set<String> issueIds = csvToSet(StringToDecoder);
                        try{PService.createMilestone(IDtoFunction, StringToFunction, issueIds);} catch(IssueTrackingException e) {System.out.println("Failed!");}
                        break;
                    case 2:
                        String IDtoRemove = getValidUserInput("int","Please enter the Milestone ID to be deleted:");
                        try{PService.removeMilestoneById(IDtoRemove);} catch(IssueTrackingException e) {System.out.println("Failed!");}
                        break;
                    case 3:
                        System.out.println("Listing Milestones:");
                        System.out.println(PService.getMilestones());
                        break;
                    case 4:
                        String IssueID = getValidUserInput("string","Please enter the new Issue ID:");
                        String IssueName = getValidUserInput("string","Please enter the new Issue Name:");
                        String IssueDescription = getValidUserInput("string","Please enter the new Issue Description:");
                        Severity IssueSeverity = Severity.valueOf(getValidUserInput("string","Please enter the new Issue Severity:"));
                        Type IssueType = Type.valueOf(getValidUserInput("string","Please enter the new Issue Type:"));
                        String IssueMilestoneID = getValidUserInput("int","Please enter the new Milestone ID:");
                        String DependencyStringToDecoder = getValidUserInput("string","Please enter the Dependencies [can be multiple Dependencies comma seperated]:");
                        java.util.Set<String> Dependencies = csvToSet(DependencyStringToDecoder);
                        try{PService.createIssue(IssueID, IssueName, IssueDescription, IssueSeverity, IssueType, IssueMilestoneID, Dependencies);} catch(IssueTrackingException e) {System.out.println("Failed!");}
                        break;
                    case 5:
                        String CloseIssueID = getValidUserInput("string","Please enter the Issue ID to be closed:");
                        try{PService.closeIssueById(CloseIssueID);} catch(IssueTrackingException e) {System.out.println("Failed!");}
                        break;
                    case 6:
                        String RemoveIssueID = getValidUserInput("string","Please enter the Issue ID to be removed:");
                        try{PService.closeIssueById(RemoveIssueID);} catch(IssueTrackingException e) {System.out.println("Failed!");}
                        break;
                    case 7:
                        System.out.println("Listing Issues:");
                        System.out.println(PService.getIssues());
                        break;
                    case 8:
                        System.out.println("Printing JSON of current Project to Console:");
                        try{PService.printJsonToConsole();} catch(IssueTrackingException e) {System.out.println("Failed!");}
                        break;
                    case 9:
                        String SaveToJsonPath = getValidUserInput("string","Please enter the the File path to save:");
                        try{PService.saveJsonToFile(SaveToJsonPath);} catch(IssueTrackingException e) {System.out.println("Failed!");}
                        break;

                }
            }
            if(menuDepth == 0) {
                switch(index) {
                    case 0: System.out.println("Exiting!"); return;
                    case 1: drawBar();
                        displayArray = secondMenu;
                        switchMenu = true;
                        break;
                    case 2: System.out.println("Creating new Project...");
                        ITracker.create();
                        break;
                }
            }
        } else {
            System.out.println("Invalid Selection. Please try again.");
        }

        if(switchMenu) {
            if (menuDepth == 0) {
                selectionMenu(1);
            }
            if (menuDepth == 1) {
                selectionMenu(0);
            }
        }
        else {
            selectionMenu(menuDepth);
        }
    }

    public static void printOptions() {
        System.out.println("Select an option by entering the index (0-" + (displayArray.length - 1) + "):");
        for (int i = 1; i < displayArray.length; i++) {
            System.out.println("(" + i + ")" + " " + displayArray[i]);
        }
        System.out.println("(" + 0 + ")" + " " + displayArray[0]);
    }

    public static String getValidUserInput(String type, String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        if (type == "int") {
            if (scanner.hasNextInt()) {
                return Integer.toString(scanner.nextInt());
            } else {
                System.out.println("Invalid input. Please enter a valid String.");
                scanner.nextLine();
            }
        }

        if (type == "string") {
            if (scanner.hasNextLine()) {
                return scanner.nextLine();
            } else {
                System.out.println("Invalid input. Please enter a valid String.");
                scanner.nextLine();
            }
        }

        return "err";
    }

    public static void drawBar() {
        System.out.println("----------------------------------");
    }

    public static Set<String> csvToSet(String input) {
        Set<String> s = new HashSet<>(Arrays.asList(input.split(",")));
        return s;

    }

}
