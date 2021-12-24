import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TraTuDien {
	Map<String, String> tuDien = new TreeMap<String, String>();

    public TraTuDien(Map<String, String> tuDien) {
        this.tuDien = tuDien;
    }

    public  void read(String url) {
        try {
            FileReader r = new FileReader(url);
            BufferedReader file = new BufferedReader(r);
            String i;
            while ((i = file.readLine()) != null) {
                String[] st = i.split(":");
                tuDien.put(st[0], st[1]);
            }
            r.close();
            file.close();
        } catch (Exception e) {
            System.out.println("file khong ton tai");
        } 
    }

    public void themTu(String key, String value) {
        this.tuDien.put(key, value);
    }

    public void xoaTu(String key) {
        this.tuDien.remove(key);
    }

    public String traTu(String key) {
    	 String result = "";
    	String k = inHoa(key);
        Set<String> set = tuDien.keySet();
        for (String i : set) {
            if (i.equals(k)) {
                result = i + " : " + this.tuDien.get(i);
                break;
            } else {
                result = "Khong tim thay!";
            }
        }
        return result;
    }

    public String toString() {
        String result = "";
        Set<String> set = tuDien.keySet();
        for (String i : set) {
            result += i + "   : " + tuDien.get(i) + "\n";
        }
        return result;
    }

     public String inHoa(String str) {
    	 char[] arr = str.toCharArray();
         String result = "";
         for (int i = 0; i < arr.length; i++) {
             if (i == 0) {
                 result += String.valueOf(arr[0]).toUpperCase();
             } else {
                 result += String.valueOf(arr[i]).toLowerCase();
             }
         }
     return result;
     }

    public static void main(String[] args) {
    	Map<String, String> tudien = new TreeMap<String, String>(); 
    	TraTuDien tra = new TraTuDien(tudien);
    	tra.read("src\\tudien");
        
        tra.themTu("Dog", "con cho");
        tra.xoaTu("Cat");
        System.out.println(tra.toString());
        System.out.println(tra.traTu("Hello"));
        
        
    }
}
