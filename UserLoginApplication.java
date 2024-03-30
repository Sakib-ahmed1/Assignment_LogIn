package com.coderscampus.login;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class UserLoginApplication {
	public void main(String[] args) {
		User[] users = readUsersFromFile("data.txt");

		try (Scanner inputScanner = new Scanner(System.in)) {
			int attempts = 0;

			while (attempts < 5) {
				System.out.print("Enter username: ");
				String usernameInput = inputScanner.nextLine();

				System.out.print("Enter password: ");
				String passwordInput = inputScanner.nextLine();

				boolean isMatchFound = false;

				for (User user : users) {
					if (user.getUsername().equalsIgnoreCase(usernameInput) && user.getPassword().equals(passwordInput)) {

						System.out.println("Welcome " + user.getUsername());
						isMatchFound = true;
						break;
					}
				}

				if (!isMatchFound) {
					attempts++;
					if (attempts < 5) {
						System.out.println("Invalid login attempt " + attempts + ". Try again.");
					} else {
						System.out.println("Too many failed login attempts. Please try later.");
					}
				} else {
					break;
				}
			}
		}
	}

	private static User[] readUsersFromFile(String filename) {
		try {
			Scanner fileScanner = new Scanner(new File(filename));
			int index = 0;
			User[] users = new User[3];

			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] details = line.split(",");
				users[index] = new User(details[0], details[1]);
				index++;
			}

			return users;
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			return new User[0];
		}
	}
}

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username.trim();
        this.password = password.trim();
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
