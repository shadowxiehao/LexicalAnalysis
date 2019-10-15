import java.util.HashMap;
import java.util.Map;

/*
用于处理与存储预设关键字(存放关键字与编号)
 */
public class Data {
    public HashMap<String, Integer> mapSet = new HashMap<String,Integer>();//记录预设,key存名字,value存编号
    public HashMap<Integer, String[]> result = new HashMap<Integer,String[]>();//记录结果,key存顺序,value存类型和描述

    public Data(FileOperator fileOperator) throws Exception{
        String str;
        String[] strs;
        while ((str=fileOperator.getLineSet())!=null){
            strs = str.split("\\s");
            mapSet.put(strs[0].trim(),Integer.valueOf(strs[1].trim()));
        }
    }

}
