package org.example;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtractLoginInfo {

    public static List<String> getLoginWithoutPurchase(List<Login> loginList,List<Purchase> purchaseList) {

        Map<Client, Integer> finalResualt = new HashMap<>();
        List<String> resualt = new ArrayList<>();

        loginList.stream().forEach(login -> {
            // find a client that has logged and haven't bought any item, the same day or 7 days after
            boolean clientFound = purchaseList.stream()
                    .anyMatch(purchase -> purchase.getClient().equals(login.getClient()) &&
                            ChronoUnit.DAYS.between(login.getLoginDate(), purchase.getPurchaseDate()) >= 0 &&
                            ChronoUnit.DAYS.between(login.getLoginDate(), purchase.getPurchaseDate()) < 7);
            if (!clientFound) {
                finalResualt.compute(login.getClient(), (client, count) -> count == null ? 1 : count + 1);
            }
        });

        finalResualt.entrySet().stream().forEach(entry -> {
            Client client = entry.getKey();

            // try to find a way to use collectors API insted, .collect.toList
            resualt.add(client.getLastName() + " " + client.getFirstName() + " " + client.getBirthDate() + " - " + entry.getValue());
        });
        return resualt;
    }
}
