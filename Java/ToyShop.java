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

   public ArrayList<Toy> getToys() {
      return toys;
   }

   public double getTotalNumber() {
      return totalNumber;
   }

   public void setToys(ArrayList<Toy> toys) {
      this.toys = toys;
   }

   public void setTotalNumber(double totalNumber) {
      this.totalNumber = totalNumber;
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
         // System.out.println(totalNumber);

      }
      for (Toy t : toys) {
         t.setWeight(conversionWeight(t.getID())); // мой
         // System.out.println(
         // "Общее количество - " + totalNumber + ". Количество этой игрушки - " +
         // t.getQuantity() + ". ID - "
         // + t.getID() + ". Вес - " + t.getWeight());
      }
   }

   // Метод изменения веса игрушек

   public double conversionWeight(int toyID) {
      double tt = 0;
      for (Toy t : toys) {
         if (t.getID() == toyID) {
            // t.setWeight(weight);
            t.setWeight((double) (t.getQuantity() / totalNumber * 100)); // мой
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

   // Сделать через @!!!!!!

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
   public ArrayList<Toy> playGame(int count) {// возвращаемый масив метод, на входе кот число розыгрышей 3
      
      ArrayList<Toy> winners = new ArrayList<>();// массив с типом Тоу []
      if(totalNumber>0){
      ArrayList<Toy> raffleToys = new ArrayList<>();// массив с типом Тоу []
      System.out.println("Начальный массив Toys:");
      for (Toy t : toys) {
         System.out.print(t.getName() + " " + t.getQuantity() + " шт.");
      }
      System.out.println();
      if (count > totalNumber) { // 3<13 -> false
         count = (int) totalNumber;
         System.out
               .println("Для розыгрыша запрошено больше игрушек, чем есть в наличии. Количество уменьшено до " + count);
      }
      while (raffleToys.size() < count) { // true
         int raffleCount = 1;// null
         for (Toy t : toys) {
            System.out.println("До округления: " + t.getQuantity() + "/" + totalNumber + "... = "
                  + Math.round(t.getQuantity() / totalNumber * t.getQuantity()));
            if (t.getQuantity() > 1) {
               raffleCount = (int) Math.round(t.getQuantity() / totalNumber * t.getQuantity()); // 5/13*5=1.92 == 2
               System.out.println("Количество добавляемых игрушек в массив raffleToys: " + raffleCount);
            } else if (t.getQuantity() < 1) {
               continue;
            }

            for (int i = 0; i < raffleCount; i++) { //// 0<2 ->true
               raffleToys.add(t); // barbie +1 to raffle
               t.setQuantity(t.getQuantity() - 1); // barbie -1 из toys
               t.setWeight(conversionWeight(t.getID())); // пересчет уд.веса !!!НУЖНО?
            }
         }
         // double weightSum = 0; // сумма весов, т.е. всех %%
         // for (Toy t : toys) { // перебор общего (главноного/верхнего) массива
         // weightSum += t.getWeight(); // сложение всех %весов
         // System.out.println(weightSum);

         // }
      }

      for (Toy toy : raffleToys) {
         System.out.print(toy.getName() + ", ");
      }
      System.out.println();

      System.out.println("закончили заплнять массив raffleToys");

      Random random = new Random();// создание рандома
      int i = 0;
      while (i < count) {// перебор до количества розыгрышей 3
         if (raffleToys.size() < 1) { // = 7
            break;
         }
         int randomNumber = random.nextInt(raffleToys.size()); // 3
         System.out.println("Рандом = " + randomNumber);
         // double currentSum = 0;// текущая сумма
         // for (Toy t : toys) {// перебор значений верхнего массива
         // currentSum += t.getWeight();
         // if (currentSum >= randomNumber) {
         // if (t.getQuantity() > 0) {
         winners.add(winners.size(), raffleToys.get(randomNumber));//
         // System.out.println("Добавили в winners " + raffleToys.get(randomNumber));
         raffleToys.remove(randomNumber);
         // t.setQuantity(t.getQuantity() - 1);
         // weightSum -= t.getWeight();
         // t.setWeight(conversionWeight(t.getID()));
         // i++;

         // break;
         i++;
      }
      totalNumber -= count;
      if (raffleToys.size() > 0) {
         for (Toy toy : raffleToys) {
            for (Toy t : toys) {
               if (toy.getID() == t.getID()) {
                  t.setQuantity(t.getQuantity() + 1);

               }

            }
            // toys.adding(toy); // - просто добавить объект
            /*
             * возможно понадобится добавить метод пересчета веса
             */
         }
      }

      System.out.println("Конечный массив Toys:");
      for (Toy t : toys) {
         System.out.print(t.getName() + " " + t.getQuantity() + " шт.");
         t.setWeight(conversionWeight(t.getID()));
         System.out.println("Вес " + t.getName() + " = " + t.getWeight() + ". Общее количество: " + totalNumber);
      }
      System.out.println();}
      else{
         System.out.println("Призовые игрушки закончилисью Добавьте для дальнейших розыгрышей.");
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
