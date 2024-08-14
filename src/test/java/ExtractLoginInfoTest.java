import org.example.Client;
import org.example.ExtractLoginInfo;
import org.example.Login;
import org.example.Purchase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ExtractLoginInfoTest {


    @Test
    public void testExtractLoginInfo() {
        List<Purchase> purchaseList = new ArrayList<>();
        List<Login> loginList = new ArrayList<>();

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

        List<String> resualt = ExtractLoginInfo.getLoginWithoutPurchase(loginList, purchaseList);

        Assertions.assertNotNull(resualt);
        Assertions.assertEquals(1, resualt.size());
        Assertions.assertEquals("Dupond Jean 1990-03-12 - 3", resualt.get(0));
    }

    @Test
    public void testExtractLoginInfoWithSameNameDifferentBirthDate() {
        List<Purchase> purchaseList = new ArrayList<>();
        List<Login> loginList = new ArrayList<>();

        Purchase purchase1 = new Purchase(new Client("Dupond", "Jean", LocalDate.of(1990, 3, 12)), "Savon", 3, LocalDate.of(2024, 8, 1));
        Login log1 = new Login(new Client("Dupond", "Jean", LocalDate.of(1990, 3, 12)), LocalDate.of(2024, 6, 4));

        purchaseList.add(purchase1);
        loginList.add(log1);

        Purchase purchase2 = new Purchase(new Client("Dupond", "Jean", LocalDate.of(1990, 3, 11)), "Savon", 3, LocalDate.of(2024, 8, 1));
        Login log2 = new Login(new Client("Dupond", "Jean", LocalDate.of(1990, 3, 11)), LocalDate.of(2024, 6, 4));

        purchaseList.add(purchase2);
        loginList.add(log2);

        List<String> resualt = ExtractLoginInfo.getLoginWithoutPurchase(loginList, purchaseList);

        Assertions.assertNotNull(resualt);
        Assertions.assertEquals(2, resualt.size());
        Assertions.assertEquals("Dupond Jean 1990-03-11 - 1", resualt.get(0));
        Assertions.assertEquals("Dupond Jean 1990-03-12 - 1", resualt.get(1));
    }
}
