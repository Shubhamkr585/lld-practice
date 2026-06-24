public class GenericsDemo {
    // <T extends class & interface> used with class
  public <T> GenericsDemo(T value) {
      System.out.println(value);
  }
      public static void main (String[]args){
          GenericsDemo demo = new GenericsDemo("kya kalla");
      }
  }


//  "?" is only used for accessing the data not for modifying
//public static double sum(List <? extends Number> numbers) {
//    double sum = 0;
//    for(Number o: numbers){
//        sum += o.doubleValue();
//
//        return sum;
//
//        no usages
//        public static void printNumbers(List <? super Integer> List) {
//            for (Object obj : list) {
//                System.out.println(obj);
//PECS principle (Producer Extends, Consumer Super)


