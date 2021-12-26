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
    // đọc file từ điển
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
    // tra từ
    public String traTu(String tu) {
    	String result ="";
    	String h = "Khong tim thay!";
    	if(!(traTuAnhViet(tu)).equals(h)) {
    		result = traTuAnhViet(tu);
    	}else if(!(traTuVietAnh(tu)).equals(h)) {
    		result = traTuVietAnh(tu);
    	}else if(!(traTuTuongDong(tu)).equals(h)) {
    		result = traTuTuongDong(tu);
    	}else {
    		return h;
    	}
    	return result;
    }
    // tra từ Anh - Việt
    public String traTuAnhViet(String key) {
    	 String result = "";
    	String k = inHoa(key);
        Set<String> set = tuDien.keySet();
        for (String i : set) {
            if (i.equals(k)) {
                result = key + " : " + this.tuDien.get(i);
                break;
            } else {
                result = "Khong tim thay!";
            }
        }
        return result;
    }
    // Tra từ Việt - Anh
    public String traTuVietAnh(String value) {
    	String k = value.toLowerCase();
    	String result ="";
    	Set<String> set = tuDien.keySet();
    	for(String i: set) {
    		if(tuDien.get(i).equals(k)) {
    			 result = value + " : " + i;
                 break;
    		}else {
    			result = "Khong tim thay!";
    		}
    	}
    	return result;
    }
    // tra những tù có chữ cái bắt đầu do người dùng nhập vào
    public String traTuTuongDong(String key) {
    	String result ="";
    	String k = inHoa(key);
    	Set<String> set = tuDien.keySet();
    	for(String i: set) {
    		String gt ="";
    		char[] s = i.toCharArray();
    		for(int j=0; j < key.length();j++) {
    			gt +=String.valueOf(s[j]);
    		}
    		if(gt.equals(k)) {
    			result += i +" : " + tuDien.get(i) + "\n";
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
        System.out.println(tra.traTu("c"));
        
    }
}
