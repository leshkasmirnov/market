package ru.asmirnov.market;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.asmirnov.market.common.PayMethod;
import ru.asmirnov.market.config.ApplicationConfig;
import ru.asmirnov.market.db.entity.Item;
import ru.asmirnov.market.db.entity.Person;
import ru.asmirnov.market.db.entity.PersonCart;
import ru.asmirnov.market.db.entity.PersonOrder;
import ru.asmirnov.market.service.ItemService;
import ru.asmirnov.market.service.OrderService;
import ru.asmirnov.market.service.PersonCartService;
import ru.asmirnov.market.service.PersonService;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Entry point.
 *
 * @author Alexey Smirnov at 21/04/2018
 */
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        context.start();

        ItemService itemService = context.getBean(ItemService.class);
        PersonService personService = context.getBean(PersonService.class);
        PersonCartService personCartService = context.getBean(PersonCartService.class);
        OrderService orderService = context.getBean(OrderService.class);

        Scanner scanner = new Scanner(System.in);

        Person person = personService.findById(1L);
        if (person == null) {
            System.out.println("There is no user in db. Check test data!");
            return;
        }
        System.out.println("Hello " + person.getName() + "!");

        Item item = getAvailableItem(itemService, scanner);

        Long count = readCount(scanner, item);
        personCartService.put(Collections.singletonList(new PersonCart(person, item, count)));
        System.out.println("Items in Cart! Press enter to create order.");
        scanner.next();

        List<PersonCart> personCartList = personCartService.checkout(person);
        String address = readAddress(scanner);
        PayMethod payMethod = readPayMethod(scanner);

        PersonOrder order = orderService.createOrder(personCartList, address, payMethod);
        orderService.acceptOrder(order);
        System.out.println("Order successful created");
    }

    private static Item getAvailableItem(ItemService itemService, Scanner scan) {
        List<String> availableCodes = itemService.findAvailable().stream()
                .map(Item::getCode).collect(Collectors.toList());
        System.out.println("Available item codes are " + availableCodes);
        String code = readCode(scan);
        Item available = itemService.findAvailableByCode(code);
        if (available == null) {
            System.out.println("Product is not available.");
            return getAvailableItem(itemService, scan);
        }
        return available;
    }

    private static String readCode(Scanner scan) {
        System.out.println("Enter product code:");
        String next = scan.next();
        if (next == null || next.length() == 0) {
            System.out.println("Wrong product code!");
            return readCode(scan);
        }
        return next;
    }

    private static Long readCount(Scanner scanner, Item item) {
        System.out.println("Enter count to buy:");
        Long result;
        try {
            String next = scanner.next();
            result = Long.valueOf(next);
        } catch (NumberFormatException e) {
            System.out.println("Error input!");
            return readCount(scanner, item);
        }
        if (result <= 0) {
            System.out.println("Count must be greater than zero!");
            return readCount(scanner, item);
        }
        if (item.getAvailableQuantity() < result) {
            System.out.println("You can buy maximum " + item.getAvailableQuantity() + " count.");
            return readCount(scanner, item);
        }
        return result;
    }

    private static String readAddress(Scanner scanner) {
        System.out.println("Enter address:");
        String next = scanner.next();
        if (next == null || next.length() <= 0) {
            return readAddress(scanner);
        }
        return next;
    }

    private static PayMethod readPayMethod(Scanner scanner) {
        System.out.println("Enter pay method - CASH or CARD:");
        String next = scanner.next();
        if (next == null || next.length() <= 0) {
            return readPayMethod(scanner);
        }
        PayMethod payMethod;
        try {
            payMethod = PayMethod.valueOf(next);
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong pay method!");
            return readPayMethod(scanner);
        }
        return payMethod;
    }
}
