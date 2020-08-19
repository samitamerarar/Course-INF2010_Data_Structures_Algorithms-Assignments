package probleme2;

public class HashMapTest 
{

   public static void main(String[] args) 
   {
       MyHashMap<String, String> mhmap = new MyHashMap<String, String>();
       
       mhmap.put("patate", "3$");
       mhmap.put("chou-rave", "1.25$");
       mhmap.put("concombre", "1.75$");
       mhmap.put("carotte", "2.5$");
       
       System.out.println( mhmap.get("concombre") );
       System.out.println( mhmap.get("carotte") );
       
   }

}
