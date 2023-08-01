import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ToyShop {
   private ArrayList<Toy> toys;
   private double totalNumber;

   public ToyShop() {
      toys = new ArrayList<>();
   }

   // Метод добавления игрушек

   public void adding(Toy toy) {
      boolean foundToy = false;
      for (Toy t : toys) {
         if (t.getID() == toy.getID()) {
            totalNumber += toy.getQuantity(); // мой
            t.setQuantity(t.getQuantity() + toy.getQuantity());

            // System.out.println(totalNumber);
            // t.setWeight(conversionWeight(t.getID())); // мой
            // System.out.println("ID: " + t.getID() + "... Вес: " + t.getWeight());
            foundToy = true;
            break;
         }

      }
      if (!foundToy) {
         toys.add(toy);
         totalNumber += toy.getQuantity(); // мой
         System.out.println(totalNumber);

      }
      for (Toy t : toys) {
         t.setWeight(conversionWeight(t.getID())); // мой
         System.out.println(
               "Общее количество - " + totalNumber + ". Количество этой игрушки - " + t.getQuantity() + ". ID -  "
                     + t.getID() + ". Вес -  " + t.getWeight());
      }
   }

   // Метод изменения веса игрушек

   public double conversionWeight(int toyID) {
      double tt = 0;
      for (Toy t : toys) {
         if (t.getID() == toyID) {
            // t.setWeight(weight);
            t.setWeight(t.getQuantity() / totalNumber * 100); // мой
            tt = t.getWeight();
            break;
            // String.format("%.3f",value); округление с переводом в строку
            // Math.round(number * count) / count;
         }
      }

      return tt;
   }

   // Метод получения списка игрушек с их атрибутами (id, название, количество,
   // вес)

   public ArrayList<String> getToyList() {
      ArrayList<String> toyList = new ArrayList<>();
      for (Toy t : toys) {
         toyList.add("ID: " + t.getID() + " Название игрушки: " + t.getName() + " Количество: " + t.getQuantity()
               + " Вес: " + t.getWeight());
      }
      return toyList;
   }

   // Метод розыгрыша игрушек
   // Этот метод можно преобразовать с ориентацией не на удельный вес, а на цену
   // игрушки.
   // Метод должен будет запонять массив сначала дешевыми игрушкми. По мере
   // уменьшения более дешевых игрушек, в список лотерейных будут попадать игрушки
   // с более высокой ценой. Но это уже
   // другая история игрушек
   public ArrayList<Toy> playGame(int count) {// возвращаемый масив метод, на входе кот число розыгрышей
      ArrayList<Toy> winners = new ArrayList<>();// массив с типом Тоу
      ArrayList<Toy> raffleToys = new ArrayList<>();// массив с типом Тоу
      if(count>toys.size()){
         count = toys.size();
      }
      while (raffleToys.size() < count) {
         int raffleCount;
         for (Toy t : toys) {
            raffleCount = (int) Math.round(t.getQuantity() / totalNumber * t.getQuantity());
            for (int i = 0; i < raffleCount; i++) {
               raffleToys.add(t);
               t.setQuantity(t.getQuantity() - 1);
            }
         }
         // double weightSum = 0; // сумма весов, т.е. всех %%
         // for (Toy t : toys) { // перебор общего (главноного/верхнего) массива
         // weightSum += t.getWeight(); // сложение всех %весов
         // System.out.println(weightSum);

         // }
      }
      Random random = new Random();// создание рандома
      int i = 0;
      while (i < count) {// перебор до количества розыгрышей
         if (raffleToys.size() < 1) {
            break;
         }
         int randomNumber = random.nextInt(raffleToys.size()); //
         // double currentSum = 0;// текущая сумма
         // for (Toy t : toys) {// перебор значений верхнего массива
         // currentSum += t.getWeight();
         // if (currentSum >= randomNumber) {
         // if (t.getQuantity() > 0) {
         winners.add(raffleToys.get(randomNumber));
         raffleToys.remove(randomNumber);
         // t.setQuantity(t.getQuantity() - 1);
         // weightSum -= t.getWeight();
         // t.setWeight(conversionWeight(t.getID()));
         // i++;

         // break;
      }

      return winners;
   }

   // Метод сохранения данных об игрушках в файл
   public void saveToFile(String fileName) throws IOException {
      try (FileWriter writer = new FileWriter(new File(fileName))) {
         for (Toy t : toys) {
            writer.write(t.getID() + "," + t.getName() + "," + t.getQuantity() + "," + t.getWeight());
            writer.write("\n");
         }
      }
   }

   // Метод загрузки данных об игрушках из файла
   public void LoadFromFile(String fileName) throws IOException {
      try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
         String line;
         while ((line = reader.readLine()) != null) {
            String[] toyData = line.split(",");
            Toy toy = new Toy(Integer.parseInt(toyData[0]), toyData[1], Integer.parseInt(toyData[2]));
            toys.add(toy);
            totalNumber += toy.getQuantity(); // мой
            // System.out.println("ЧТЕНИЕ-----Общее количество - " + totalNumber + ".
            // Количество этой игрушки - " + toy.getQuantity() + ". ID - "
            // + toy.getID() + ". Вес - " + toy.getWeight());
         }
      }
   }
}
