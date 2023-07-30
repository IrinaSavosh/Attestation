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

   public void add(Toy toy) {
      boolean foundToy = false;
      for (Toy t : toys) {
         if (t.getID() == toy.getID()) {
            t.setQuantity(t.getQuantity() + toy.getQuantity());
            totalNumber += t.getQuantity(); //мой
            System.out.println(totalNumber);
            t.setWeight(conversionWeight(t.getID())); //мой
            foundToy = true;
            break;
         }
         
      }
      if (!foundToy) {
         toys.add(toy);
         totalNumber += toy.getQuantity(); //мой
         toy.setWeight(conversionWeight(toy.getID())); //мой
      }
   }

   // Метод изменения веса игрушек

   public double conversionWeight(int toyID) {
      double tt = 0;
      for (Toy t : toys) {
         if(t.getID() == toyID){
         // t.setWeight(weight);
         t.setWeight(t.getQuantity()/totalNumber*100); //мой
         tt = t.getWeight();
         break;
         //String.format("%.3f",value); округление с переводом в строку
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
   public ArrayList<Toy> playGame(int count){
      ArrayList<Toy> winners = new ArrayList<>();
      double weightSum = 0;
      for (Toy t : toys) {
         weightSum += t.getWeight();
      }

      Random random = new Random();
      for (int i = 0; i < count; i++) {
         double randomNumber = random.nextDouble() * weightSum;
         double currentSum = 0;
         for (Toy t : toys) {
            currentSum += t.getWeight();
            if (currentSum >= randomNumber){
               if(t.getQuantity()> 0){
                  winners.add(t);
                  t.setQuantity(t.getQuantity()-1);
                  weightSum -= t.getWeight();
               }
               break;
            }
         }
      }
      return winners;
   }

   //Метод сохранения данных об игрушках в файл
   public void saveToFile(String fileName) throws IOException{
      try (FileWriter writer = new FileWriter(new File(fileName))){
         for (Toy t : toys) {
            writer.write(t.getID() + "," + t.getName() + "," + t.getQuantity());
            writer.write("\n");
         }
      }
   }

   //Метод загрузки данных об игрушках из файла
   public void LoadFromFile(String fileName) throws IOException{
      try (BufferedReader reader = new BufferedReader(new FileReader(new  File(fileName)))){
         String line;
         while ((line = reader.readLine()) != null){
            String[] toyData = line.split(",");
            Toy toy = new Toy(Integer.parseInt(toyData[0]), toyData[1], Integer.parseInt(toyData[2]));
            toys.add(toy);
         }
      }
   }
}
