package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Purchase> purchaseList = new ArrayList<>();
        List<Login> loginList = new ArrayList<Login>();

        Purchase purchase1 = new Purchase(new Client("Dupond", "Jean", LocalDate.of(1990, 3, 12)), "Savon", 3, LocalDate.of(2024, 8, 1));
        Purchase purchase2 = new Purchase(new Client("Dupond", "Jean", LocalDate.of(1990, 3, 12)), "Serviette", 1, LocalDate.of(2024, 8, 1));
        Purchase purchase3 = new Purchase(new Client("Dupond", "Jean", LocalDate.of(1990, 3, 12)), "Baguette", 4, LocalDate.of(2024, 8, 1));
        Purchase purchase4 = new Purchase(new Client("Dupond", "Jean", LocalDate.of(1990, 3, 12)), "Baguette", 6, LocalDate.of(2024, 8, 4));
        Purchase purchase5 = new Purchase(new Client("Dupont", "Benoit", LocalDate.of(2001, 8, 31)), "Piles", 1, LocalDate.of(2024, 8, 4));
        Purchase purchase6 = new Purchase(new Client("Dupont", "Benoit", LocalDate.of(2001, 8, 31)), "Baguette", 3, LocalDate.of(2024, 8, 4));

        purchaseList.add(purchase1);
        purchaseList.add(purchase2);
        purchaseList.add(purchase3);
        purchaseList.add(purchase4);
        purchaseList.add(purchase5);
        purchaseList.add(purchase6);

        Login log1 = new Login(new Client("Dupond", "Jean", LocalDate.of(1990, 3, 12)), LocalDate.of(2024, 6, 4));
        Login log2 = new Login(new Client("Dupond", "Jean", LocalDate.of(1990, 3, 12)), LocalDate.of(2024, 7, 4));
        Login log3 = new Login(new Client("Dupond", "Jean", LocalDate.of(1990, 3, 12)), LocalDate.of(2024, 7, 20));
        Login log4 = new Login(new Client("Dupond", "Jean", LocalDate.of(1990, 3, 12)), LocalDate.of(2024, 8, 1));
        Login log5 = new Login(new Client("Dupond", "Jean", LocalDate.of(1990, 3, 12)), LocalDate.of(2024, 8, 2));
        Login log6 = new Login(new Client("Dupont", "Benoit", LocalDate.of(2001, 8, 31)), LocalDate.of(2024, 8, 1));
        Login log7 = new Login(new Client("Dupont", "Benoit", LocalDate.of(2001, 8, 31)), LocalDate.of(2024, 8, 4));

        loginList.add(log1);
        loginList.add(log2);
        loginList.add(log3);
        loginList.add(log4);
        loginList.add(log5);
        loginList.add(log6);
        loginList.add(log7);

        Map<Client, Integer> finalResualt = new HashMap<>();

        loginList.forEach(login -> {
            // find a client that has logged and haven't bought any item, the same day or 7 days after
            boolean clientFound = purchaseList.stream()
                    .anyMatch(purchase -> purchase.getClient().equals(login.getClient()) &&
                            ChronoUnit.DAYS.between(login.getLoginDate(), purchase.getPurchaseDate()) >= 0 &&
                            ChronoUnit.DAYS.between(login.getLoginDate(), purchase.getPurchaseDate()) < 7);
            if (!clientFound) {
                finalResualt.compute(login.getClient(), (client, count) -> count == null ? 1 : count + 1);
            }
        });

        finalResualt.forEach((client, count) -> System.out.println(client.getLastName() + " " + client.getFirstName() + " " + client.getBirthDate() + " - " + count));
    }
}