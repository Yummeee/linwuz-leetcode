/*
 * Find Duplicate File in System (Medium)
 * 
 * Given a list of directory info including directory path, and all the files with contents in this directory, 
 * you need to find out all the groups of duplicate files in the file system in terms of their paths.
 * A group of duplicate files consists of at least two files that have exactly the same content.
 * A single directory info string in the input list has the following format:
 * "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
 * It means there are n files (f1.txt, f2.txt ... fn.txt with content f1_content, f2_content ... fn_content, 
 * respectively) in directory root/d1/d2/.../dm. Note that n >= 1 and m >= 0. If m = 0, it means the directory 
 * is just the root directory.
 * The output is a list of group of duplicate file paths. For each group, it contains all the file paths of the
 * files that have the same content. A file path is a string that has the following format:
 * "directory_path/file_name.txt"
 * 
 * Example 1:
 * Input:
   ["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]
   Output:  
   [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
 *
 * Note:
 * No order is required for the final output.
   You may assume the directory name, file name and file content only has letters and digits, and the length of file content is in the range of [1,50].
   The number of files given is in the range of [1,20000].
   You may assume no files or directories share the same name in a same directory.
   You may assume each given directory info represents a unique directory. Directory path and file infos are separated by a single blank space.
 *
 * Follow up beyond contest:
   Imagine you are given a real file system, how will you search files? DFS or BFS ?
   If the file content is very large (GB level), how will you modify your solution?
   If you can only read the file by 1kb each time, how will you modify your solution?
   What is the time complexity of your modified solution? What is the most time consuming part and memory consuming part of it? How to optimize?
   How to make sure the duplicated files you find are not false positive?
 */
/*
 * 思路：
 * 使用hashmap保存content与对应List<String>，即文件路径集合，的映射。之所以这样是因为可以节省时间，不然用string保存文件路径集合会超时。
 * 第二次遍历map时只需将size超过1的集合加入最后返回的结果集合中即可。
 * 
 * 利用java8的流特性：
 * public static List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for(String path : paths) {
            String[] tokens = path.split(" ");
            for(int i = 1; i < tokens.length; i++) {
                String file = tokens[i].substring(0, tokens[i].indexOf('('));
                String content = tokens[i].substring(tokens[i].indexOf('(') + 1, tokens[i].indexOf(')'));
                map.putIfAbsent(content, new ArrayList<>());
                map.get(content).add(tokens[0] + "/" + file);
            }
        }
        return map.values().stream().filter(e -> e.size() > 1).collect(Collectors.toList());
    }
 */
import java.util.*;
public class question609 {
	public List<List<String>> findDuplicate(String[] paths) {
        HashMap<String, List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        for(int i = 0; i < paths.length; i++) {
            String[] files = paths[i].split(" ");
            for(int j = 1; j < files.length; j++) {
                String[] temp = files[j].split("\\(");
                String file_name = temp[0];
                String content = temp[1].substring(0, temp[1].length() - 1);
                String path = files[0] + "/" + file_name;
                List<String> old_files = map.getOrDefault(content, new ArrayList<>());
                old_files.add(path);
                map.put(content, old_files);
            }
        }
        for(Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> files = entry.getValue();
            if(files.size() != 1) {
                res.add(files);
            }
        }
        return res;
    }
}
