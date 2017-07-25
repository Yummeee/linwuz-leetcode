/*
 * Encode and Decode TinyURL (Medium)
 * Note: This is a companion problem to the System Design problem: Design TinyURL.
 * 
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it 
 * returns a short URL such as http://tinyurl.com/4e9iAk.
 * 
 * Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm 
 * should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the 
 * original URL.
 */
/*
 * 思路：这题是发散性思维，只要满足decode(encode(string)) == string就行，不管中间是怎么实现的。
 * 1.我首先想到用hash表实现，保存每个已有映射的关系。短地址采用依次增长的方式，当最新分配的短地址的最后一位已经使用过0-9A-Za-z的话，则新添一位。这种方法
 * 能保证新生成的较短url永远不会是重复的，扩展性较强，不会用尽地址，但短url的地址不固定，且必须要在不调用时存储cache的内容，且new的时候改成读入cache。还有
 * 一个缺点，就是当多次调用encode一个相同的地址时，会重新分配一个新的短地址。浪费时间与空间。可以再用一个HashSet来保存已分配的长地址来解决这个问题
 * 
 * 2.仍用hash表，但可以设定短url的范围，且不用自动增长，而是随机分配地址。比如这样：
 * public class Codec {
    Map<Integer, String> map = new HashMap<>();
    Random r=new Random();
    int key=r.nextInt(10000);
    public String encode(String longUrl) {
        while(map.containsKey(key))
            key= r.nextInt(10000);
        map.put(key,longUrl);
        return "http://tinyurl.com/"+key;
    }
    public String decode(String shortUrl) {
        return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
    }
}
 * 
 * 3.另外看到的一个答案，简单的用数字序号来表示短地址。然后使用一个arraylist来存储每个对应的长地址，其在list中的序号默认是短地址。和我的一开始思路比较像，不过只用数字表示端地址，会暴露服务器上存了多少url，存在安全隐患
 * below is the tiny url solution in java, also this is the similar method in industry. In industry, most of shorten url service is by database, one auto increasing long number as primary key. whenever a long url need to be shorten, append to the database, and return the primary key number. (the database is very easy to distribute to multiple machine like HBase, or even you can use the raw file system to store data and improve performance by shard and replica).
   Note, it's meaningless to promise the same long url to be shorten as the same short url. if you do the promise and use something like hash to check existing, the benefit is must less than the cost.
   Note: if you want the shorted url contains '0-9a-zA-Z' instead of '0-9', then you need to use 62 number system, not 10 number system(decimal) to convert the primary key number. like 123->'123' in decimal, 123->'1Z' in 62 number system (or '0001Z' for align).
   public class Codec {
    List<String> urls = new ArrayList<String>();
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        urls.add(longUrl);
        return String.valueOf(urls.size()-1);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int index = Integer.valueOf(shortUrl);
        return (index<urls.size())?urls.get(index):"";
    }
}
 * 4.使用string自带的hashcode，将长地址的hashcode作为短地址的最后几位独一无二的值
 * public class Codec {
    Map<Integer, String> map = new HashMap<>();
    public String encode(String longUrl) {
        map.put(longUrl.hashCode(),longUrl);
        return "http://tinyurl.com/"+longUrl.hashCode();
    }
    public String decode(String shortUrl) {
        return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
    }
}
 * 注：到了一个超贱的答案，面试的时候应该不会给过吧...两个函数都直接return输入，即shortUrl == longUrl
 */
import java.util.*;
public class question535 {
	HashMap<String, String> cache = new HashMap<>();
    StringBuilder latest_url = new StringBuilder("http://tinyurl.com/");
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if(longUrl == null || longUrl.length() == 0) {
            return "";
        }
        
        if(latest_url.length() == 19) {
            latest_url.append('0');
        }
        else {
            char c = (char) (latest_url.charAt(latest_url.length() - 1) + 1);
            if(c > 'z') {
            	c = '0';           
            }
            else {
            	latest_url.deleteCharAt(latest_url.length() - 1);	//I think it is efficient than replace
                if(c == ':') {				//':' is following '9'
                    c = 'A';
                }
                else if(c == '[') {			//'[' is following 'Z'
                    c = 'a';
                }
            }
            latest_url.append(c);
        }
        cache.put(latest_url.toString(), longUrl);
        return latest_url.toString();
    }
	// Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        if(shortUrl == null || shortUrl.length() == 0) {
            return "";
        }
        
        return cache.get(shortUrl);
    }
}

//Your Codec object will be instantiated and called as such:
//Codec codec = new Codec();
//codec.decode(codec.encode(url));