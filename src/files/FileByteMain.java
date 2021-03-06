package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;

public class FileByteMain {
    public static void main(String[] args) {
        FileByte fileByte = new FileByte();
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean isExit = false;

        while (!isExit) {
            System.out.println("\n--------Menu--------");
            System.out.println("1. Copy File");
            System.out.println("0. Exit Program!");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter path of source File: ");
                    String pathSource = scanner.nextLine();
                    System.out.println("Enter path of destination File: ");
                    String pathDest = scanner.nextLine();
                    try {
                        fileByte.copyFilesIO(pathSource, pathDest);
                    } catch (FileAlreadyExistsException exExist) {
                        System.out.print("Destination File Exist! Overwrite ? (yes/no): ");
                        char confirm = scanner.next().toLowerCase().charAt(0);
                        if (confirm == 'y') {
                            File toDelete = new File(pathDest);
                            if (!toDelete.exists()) {
                                toDelete.delete();
                                try {
                                    fileByte.copyFilesIO(pathSource, pathDest);
                                } catch (IOException exOther) {
                                    System.out.println("There has some error: " + exOther.getMessage());
                                }
                            }
                        }
                    } catch (FileNotFoundException exNotFound) {
                        System.out.println("Source File Not Found!");
                    } catch (IOException exOther) {
                        System.out.println("There has some error: " + exOther.getMessage());
                    } finally {
                        System.out.println("Operation Finished!");
                    }
                    break;
                case 0:
                    System.out.println("Exit Program!");
                    isExit = true;
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
