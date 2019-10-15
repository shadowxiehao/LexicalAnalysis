import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Output {
    Data data=null;
    HashMap<Integer,String[]> output=null;
    public Output(Data data){
        this.data=data;
        output=data.result;
    }
    public void print(){
        for(int i=1;i<=output.size();i++){
            if(output.get(i)[1]=="字符串开始") System.out.println(String.format("%-22s\t", (output.get(i))[0]) + String.format("%-18s\t", "1" )+String.format("%-22s\t", output.get(i)[1]));
            else if(output.get(i)[1]=="字符串结束") System.out.println(String.format("%-22s\t", (output.get(i))[0]) + String.format("%-18s\t", "2" )+String.format("%-22s\t", output.get(i)[1]));
            else if(output.get(i)[1]=="字符开始") System.out.println(String.format("%-22s\t", (output.get(i))[0]) + String.format("%-18s\t", "3" )+String.format("%-22s\t", output.get(i)[1]));
            else if(output.get(i)[1]=="字符结束") System.out.println(String.format("%-22s\t", (output.get(i))[0]) + String.format("%-18s\t", "4" )+String.format("%-22s\t", output.get(i)[1]));
            else if(output.get(i)[1]=="标识符") System.out.println(String.format("%-22s\t", (output.get(i))[0]) + String.format("%-18s\t", "5" )+String.format("%-22s\t", output.get(i)[1]));
            else if(output.get(i)[1]=="常数") System.out.println(String.format("%-22s\t", (output.get(i))[0]) + String.format("%-18s\t", "6" )+String.format("%-22s\t", output.get(i)[1]));
            else if(output.get(i)[1]=="浮点数") System.out.println(String.format("%-22s\t", (output.get(i))[0]) + String.format("%-18s\t", "7" )+String.format("%-22s\t", output.get(i)[1]));
            else System.out.println(String.format("%-22s\t", (output.get(i))[0])+String.format("%-18s\t", data.mapSet.get(output.get(i)[0]))+String.format("%-22s\t", output.get(i)[1]));
        }
    }

    public void save(FileOperator fileOperator){
        for(int i=1;i<=output.size();i++){
            if(output.get(i)[1]=="字符串开始") fileOperator.saveAll(String.format("%-22s\t", (output.get(i))[0]) + String.format("%-18s\t", "1" )+String.format("%-22s\t", output.get(i)[1])+"\n");
            else if(output.get(i)[1]=="字符串结束") fileOperator.saveAll(String.format("%-22s\t", (output.get(i))[0]) + String.format("%-18s\t", "2" )+String.format("%-22s\t", output.get(i)[1])+"\n");
            else if(output.get(i)[1]=="字符开始") fileOperator.saveAll(String.format("%-22s\t", (output.get(i))[0]) + String.format("%-18s\t", "3" )+String.format("%-22s\t", output.get(i)[1])+"\n");
            else if(output.get(i)[1]=="字符结束") fileOperator.saveAll(String.format("%-22s\t", (output.get(i))[0]) + String.format("%-18s\t", "4" )+String.format("%-22s\t", output.get(i)[1])+"\n");
            else if(output.get(i)[1]=="标识符") fileOperator.saveAll(String.format("%-22s\t", (output.get(i))[0]) + String.format("%-18s\t", "5" )+String.format("%-22s\t", output.get(i)[1])+"\n");
            else if(output.get(i)[1]=="常数") fileOperator.saveAll(String.format("%-22s\t", (output.get(i))[0]) + String.format("%-18s\t", "6" )+String.format("%-22s\t", output.get(i)[1])+"\n");
            else if(output.get(i)[1]=="浮点数") fileOperator.saveAll(String.format("%-22s\t", (output.get(i))[0]) + String.format("%-18s\t", "7" )+String.format("%-22s\t", output.get(i)[1])+"\n");
            else fileOperator.saveAll(String.format("%-22s\t", (output.get(i))[0])+String.format("%-18s\t", data.mapSet.get(output.get(i)[0]))+String.format("%-22s\t", output.get(i)[1])+"\n");
        }
    }
}
