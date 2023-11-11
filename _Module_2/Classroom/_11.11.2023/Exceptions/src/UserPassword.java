import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserPassword {
    String password = null;
    int requiredLength = 8;

    public static void handleProblem(String msg) throws IOException {
        throw new IOException(msg);
    }

    public void getUserPasswordFromFile() {
        Scanner scanner = null;
        File file = null;
        String fileName;

        while (true) {
            try {
                scanner = new Scanner(System.in);
                System.out.println("Enter a file name with passwords: ");
                fileName = scanner.nextLine();

                if (isSpaceInString(fileName)) {
                    System.out.println("File cannot contain a space.");
                    continue;
                }
                scanner.close();

                // todo - constant loop when name doesn't exist...
                //  Enter a file name with passwords:
                // java.util.NoSuchElementException: No line found
                
                file = new File(fileName);
                scanner = new Scanner(file);
                break;
            } catch (FileNotFoundException e) {
                System.out.println("File doesn't exist");
            } catch (NoSuchElementException e) {
                System.out.println(e);
            }
        }

//        String line = null;
//        while (true) {
//            if (scanner.hasNextLine()) {
//                line = scanner.nextLine();
//                this.password = line;
//                try {
//                    if (this.isPasswordAllGood()) {
//                        System.out.println("Password is valid: " + this.password);
//                    }
//                } catch (IOException e) {
//                    System.out.println(e);
//                }
//            } else {
//                System.out.println("End of file");
//                break;
//            }
//        }
    }

    public void getUserPassword() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter a password: ");
            this.password = scanner.nextLine();
            try {
                if (isPasswordAllGood()) {
                    break;
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        System.out.println("Password accepted: " + this.password);
    }

    public boolean isPasswordAllGood() throws IOException {
        try {
            if (isSpaceInPassword()) {
                handleProblem("Space is not allowed in the password.");
            } else if (!isCapitalLetterInPassword()) {
                handleProblem("No Capital letter in password");
            } else if (!isDigitInPassword()) {
                handleProblem("No digit in the password");
            } else if (isPasswordLongEnough()) {
                handleProblem("Password needs to be at least " + this.requiredLength);
            } else {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static boolean isSpaceInString(String msg) {
        for (String letter : msg.split("")) {
            if (letter.equals(" ")) {
                return true;
            }
        }
        return false;
    }

    public boolean isSpaceInPassword() {
        return isSpaceInString(this.password);
    }

    public boolean isPasswordLongEnough() {
        return this.password.length() < this.requiredLength;
    }

    public boolean isDigitInPassword() {
        for (Character character : this.password.toCharArray()) {
            if (Character.isDigit(character)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCapitalLetterInPassword() {
        for (Character character : this.password.toCharArray()) {
            if (Character.isUpperCase(character)) {
                return true;
            }
        }
        return false;
    }

}
