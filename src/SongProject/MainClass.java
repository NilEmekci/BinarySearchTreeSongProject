package SongProject;



import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class MainClass {


    public static SongSystem songSystem;

    static {
        try {
            songSystem = new SongSystem();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void menuDisplay() throws Exception {
        Scanner input = new Scanner(System.in);

        System.out.println("\n\nPress 1) To display all the Songs in the system\n"
                + "Press 2) To display song by Name\n"
                + "Press 3) To display song by Artist\n"
                + "Press 4) To display song by ID\n"
                + "Press 5) To display songs with same genre\n"
                + "Press 0) Quit back to main menu...");
        String option = input.nextLine();

        while (!option.equals("0")) {
            switch (option) {

                case "1":
                    System.out.println(songSystem.displayAllTheSongs());
                    break;
                case "2":

                    System.out.println("Enter the song Name:");
                    String songName = input.nextLine().toLowerCase(Locale.ROOT);
                    System.out.println(songSystem.findByName(songName));

                    break;
                case "3":
                    System.out.println("\n\nPress 1) Display only one artist\nPress 2) Display all the songs of the artist");
                    String optionArtist = input.nextLine();
                    if (optionArtist.equals("1")) {
                        System.out.println("Enter the name of the Artist:");
                        String songArtist = input.nextLine();
                        System.out.println(songSystem.findByArtist(songArtist));
                    } else {
                        System.out.println("Enter of name of the Artist:");
                        String songArtist = input.nextLine();
                        System.out.println(songSystem.findByArtistAll(songArtist));
                    }
                    break;
                case "4":
                    System.out.println("Press 1) To Display by only ID\nPress 2) To Display all the songs in range eg.1001-1010");
                    String optionID = input.nextLine();
                    if (optionID.equals("1")) {
                        System.out.println("Enter the ID:");
                        String songID = input.nextLine();
                        System.out.println(songSystem.findByIDTree(songID));
                    } else {
                        System.out.println("Enter the ID's like 1001-1005:");
                        String songIDRange = input.nextLine();
                        songSystem.findByIDInRange(songIDRange);
                    }
                    break;
                case "5":

                    System.out.println("Enter the the genre");
                    String songGenre = input.nextLine();
                    System.out.println(songSystem.findByGenreAll(songGenre));

                    break;

                default:
                    throw new Exception("YouEnteredWrongNumberException " + option);
            }

            System.out.println("\n\nPlease continue with following options");
            System.out.println("\n\nPress 1) To display all the Songs in the system\n"
                    + "Press 2) To display song by Name\n"
                    + "Press 3) To display song by Artist\n"
                    + "Press 4) To display song by ID\n"
                    + "Press 5) To display songs with same genre\n"
                    + "Press 0) Quit back to main menu...");
            System.out.println();
            option = input.nextLine();
        }


    }

    public static void menuAddDelete() throws Exception {
        Scanner input = new Scanner(System.in);

        System.out.println("\n\nPress 1) To Add new song\n" +
                "Press 2) To Delete a song\n" +
                "Press 0) Quit back to main menu...\n");
        String option = input.nextLine();

        while (!option.equals("0")) {
            switch (option) {


                case "1":

                    System.out.println("Enter song Name (eg. Desert Rose)");
                    String name = input.nextLine();
                    System.out.println("Enter song Artist (eg. Sting)");
                    String artist = input.nextLine();
                    System.out.println("Enter song ID (eg. 1)");
                    String ID = input.nextLine();
                    System.out.println("Enter song Genre (eg. pop)");
                    String genre = input.nextLine();
                    System.out.println("Enter song Year (eg. 1999)");
                    String year = input.nextLine();

                    songSystem.insertSong(new Song(name.toLowerCase(Locale.ROOT), artist.toLowerCase(Locale.ROOT), ID, genre.toLowerCase(Locale.ROOT), year.toLowerCase(Locale.ROOT)));
                    System.out.println("\n\nSong is added!!\n\nNew list of the songs \n" + songSystem.displayAllTheSongs() + " continue with following options");
                    break;
                case "2":
                    System.out.println("Enter the song ID\n eg. 1005");

                    String deleteID = input.nextLine();
                    int del = Integer.parseInt(deleteID);
                    System.out.println("Song is deleted -> " + songSystem.deleteFromSystem(del));

                    break;
                default:
                    throw new Exception("YouEnteredWrongNumberException " + option);
            }


            System.out.println("\n\nPress 1) To add new song\n" +
                    "Press 2) To Delete a song\n" +
                    "Press 0) Quit back to main menu...\n");
            System.out.println();
            option = input.nextLine();
        }
    }


    public static void main(String[] args) throws Exception {


        Scanner input = new Scanner(System.in);

        songSystem.reader();

        System.out.println("\nHello Welcome AziNilify\n\n" +
                " Press 1 to See all the displaying options\n" +
                " Press 2 to Add New or Delete Song from the system\n" +
                " Press 0 to Exit from system");
        String option = input.nextLine();


        while (!option.equals("0")) {


            switch (option) {


                case "1":
                    menuDisplay();
                    break;
                case "2":
                    menuAddDelete();
                    break;
                default:
                    throw new Exception("YouEnteredWrongNumberException " + option);
            }
            System.out.println("\n\nPlease continue with following options");
            System.out.println("\n\nPress 1 to See all the displaying options\n" +
                    "Press 2 to Add New or Delete Song from the system\n" +
                    "Press 0 to Exit from system");
            System.out.println();
            option = input.nextLine();

        }


    }
}
