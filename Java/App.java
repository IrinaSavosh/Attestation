// Необходимо написать программу – розыгрыша игрушек в магазине детских товаров.
// Стараемся применять ООП и работу с файлами.
// Если какой-то пункт не изучали и не знаете, как сделать, то можете сделать своим способом. Например, у кого в курсе не было ООП, то применяем списки\массивы\словари
 
// Желательный функционал программы:
// В программе должен быть минимум один класс со следующими свойствами:
// id игрушки,
// текстовое название,
// количество
// частота выпадения игрушки (вес в % от 100)
 
// Метод добавление новых игрушек и возможность изменения веса (частоты выпадения игрушки)
// Возможность организовать розыгрыш игрушек.

// Например, следующим образом:
// С помощью метода выбора призовой игрушки – мы получаем эту призовую игрушку и записываем в список\массив.
// Это список призовых игрушек, которые ожидают выдачи.
// Еще у нас должен быть метод – получения призовой игрушки.
// После его вызова – мы удаляем из списка\массива первую игрушку и сдвигаем массив. А эту игрушку записываем в текстовый файл.
// Не забываем уменьшить количество игрушек


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        try{
            ToyShop shop = new ToyShop();
            File toyFile = new File("toys.csv");

            if(!toyFile.exists()){
                toyFile.createNewFile();
                shop.adding(new Toy(1,"Кукла Барби", 5));
                shop.adding(new Toy(2,"Мишка", 8));
                // shop.add(new Toy(3,"Машинка", 12));
                // shop.add(new Toy(4,"Конструктор Lego", 7));
                // shop.add(new Toy(5,"Динозаврик", 20));
                // shop.conversionWeight(0); что-нить придумать с айди
                shop.saveToFile("toys.csv");
                
            }   else{
                shop.LoadFromFile("toys.csv");
            }
            // shop.setWeight(1, 30); //изменить вес куклы
            // shop.adding(new Toy(1,"Кукла Барби", 5));
            // shop.saveToFile("toys.csv");
            // ArrayList<String> toyList = shop.getToyList(); // получить список игрушек в магазине
            // for (String toy : toyList) {
            //     System.out.println(toy);
            // }
            ArrayList<Toy> winners = shop.playGame(3); //разыграть 3 игрушки 
            for (Toy t : winners) {
                System.out.println(t.getName());
            }

            shop.saveToFile("toys.csv"); //сохранить данные об игрушках в файл
        
        
        }   
        catch(IOException e){
            System.out.println("Ошибка при работе с файлами: " + e.getMessage());
        }
    }
}