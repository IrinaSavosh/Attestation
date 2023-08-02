public class Toy {
   private int id;
   private String name;
   private int quantity;
   private double weight;
   
   public Toy(int id, String name, int quantity) {
      this.id = id;
      this.name = name;
      this.quantity = quantity;
      weight = 100;
   }

   public int getID() {
      return id;
   }

   public String getName() {
      return name;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public double getWeight() {
      return weight;
   }

   public void setWeight(double weight) {
      this.weight = weight;
   }
   
}
