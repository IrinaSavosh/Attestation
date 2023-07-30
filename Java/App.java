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
                shop.add(new Toy(1,"Кукла Барби", 5));
                shop.add(new Toy(2,"Мишка", 8));
                shop.add(new Toy(3,"Машинка", 12));
                shop.add(new Toy(4,"Конструктор Lego", 7));
                shop.add(new Toy(5,"Динозаврик", 20));
                // shop.conversionWeight(0); что-нить придумать с айди
                shop.saveToFile("toys.csv");
                
            }   else{
                shop.LoadFromFile("toys.csv");
            }
            // shop.setWeight(1, 30); //изменить вес куклы
            shop.add(new Toy(1,"Кукла Барби", 5));
            ArrayList<String> toyList = shop.getToyList(); // получить список игрушек в магазине
            for (String toy : toyList) {
                System.out.println(toy);
            }
            // ArrayList<Toy> winners = shop.playGame(3); //разыграть 3 игрушки 
            // for (Toy t : winners) {
                // System.out.println(t.getName());
            // }

            shop.saveToFile("toys.csv"); //сохранить данные об игрушках в файл
        
        
        }   
        catch(IOException e){
            System.out.println("Ошибка при работе с файлами: " + e.getMessage());
        }
    }
}