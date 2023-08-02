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
            totalNumber += toy.getQuantity(); 
            t.setQuantity(t.getQuantity() + toy.getQuantity());
            foundToy = true;
            break;
         }

      }
      if (!foundToy) {
         toys.add(toy);
         totalNumber += toy.getQuantity();
      }
      for (Toy t : toys) {
         t.setWeight(conversionWeight(t.getID()));
      }
   }

   // Метод изменения веса игрушек

   public double conversionWeight(int toyID) {
      double tt = 0;
      for (Toy t : toys) {
         if (t.getID() == toyID) {
            t.setWeight((double) (t.getQuantity() / totalNumber * 100));
            tt = t.getWeight();
            break;
         }
      }

      return tt;
   }

   // Метод получения списка игрушек с их атрибутами (id, название, количество,
   // вес)
   @Override
   public String toString() {
      System.out.println("Метод вывода списка стартанул");
      String listToys = "";
      for (Toy t : toys) {
         listToys = listToys + "ID: " + t.getID() + " Название игрушки: " + t.getName() + " Количество: "
               + t.getQuantity()
               + " Вес: " + t.getWeight() + "\n";
      }
      System.out.println("Метод вывода списка сработал");
      return listToys;
   }

   // Метод розыгрыша игрушек

   // Этот метод можно преобразовать с ориентацией не на удельный вес, а на цену
   // игрушки.
   // Метод должен будет запонять массив сначала дешевыми игрушкми. По мере
   // уменьшения более дешевых игрушек, в список лотерейных будут попадать игрушки
   // с более высокой ценой. Но это уже
   // другая история игрушек
   public ArrayList<Toy> playGame(int count) {

      ArrayList<Toy> winners = new ArrayList<>();
      if (totalNumber > 0) {
         ArrayList<Toy> raffleToys = new ArrayList<>();
         System.out.println("Начальный массив Toys:");
         for (Toy t : toys) {
            System.out.print(t.getName() + " " + t.getQuantity() + " шт.");
         }
         System.out.println();
         if (count > totalNumber) {
            count = (int) totalNumber;
            System.out
                  .println(
                        "Для розыгрыша запрошено больше игрушек, чем есть в наличии. Количество уменьшено до " + count);
         }
         while (raffleToys.size() < count) {
            int raffleCount;
            for (Toy t : toys) {
               System.out.println("До округления: " + t.getQuantity() + "/" + totalNumber + "... = "
                     + Math.round(t.getQuantity() / totalNumber * t.getQuantity()));
               if (t.getQuantity() < 1) {
                  continue;

               } else if (t.getQuantity() == 1) {
                  raffleToys.add(t);
                  t.setQuantity(t.getQuantity() - 1);

               } else {
                  raffleCount = (int) Math.round(t.getQuantity() / totalNumber * t.getQuantity());
                  System.out.println("Количество добавляемых игрушек в массив raffleToys: " + raffleCount);

                  for (int i = 0; i < raffleCount; i++) {
                     raffleToys.add(t);
                     t.setQuantity(t.getQuantity() - 1);
                  }

               }
            }
         }

         for (Toy toy : raffleToys) {
            System.out.print(toy.getName() + ", ");
         }
         System.out.println();

         System.out.println("закончили заплнять массив raffleToys");

         Random random = new Random();
         int i = 0;
         while (i < count) {
            if (raffleToys.size() < 1) {
               break;
            }
            int randomNumber = random.nextInt(raffleToys.size());
            System.out.println("Рандом = " + randomNumber);
            winners.add(winners.size(), raffleToys.get(randomNumber));
            raffleToys.remove(randomNumber);

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
            }
         }

         System.out.println("Конечный массив Toys:");
         for (Toy t : toys) {
            System.out.print(t.getName() + " " + t.getQuantity() + " шт.");
            t.setWeight(conversionWeight(t.getID()));
            System.out.println("Вес " + t.getName() + " = " + t.getWeight() + ". Общее количество: " + totalNumber);
         }
         System.out.println();
      } else {
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
            totalNumber += toy.getQuantity();
         }
      }
   }
}
