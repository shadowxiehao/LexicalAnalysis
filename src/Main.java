import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            System.out.println("--(配置类号可在目录的Set.txt中设置)\n请输入源程序路径(路径包含程序名,回车则默认为目录下a.c):");
            String name_set = "./Set.txt";
            String name_input = "./a.c";
            Scanner scanner=new Scanner(System.in);
            if((name_input=scanner.nextLine())!=null){
                if(name_input.trim().length()!=0){
                }
                else name_input="./a.c";
            }
            else name_input="./a.c";
            FileOperator fileOperator = new FileOperator(name_input, name_set);
            Data data = new Data(fileOperator);

            FileReader reader = new FileReader(fileOperator.getF_inputC());//定义一个fileReader对象，用来初始化BufferedReader
            int length = (int) fileOperator.getF_inputC().length();
            //这里定义字符数组的时候需要多定义一个,因为词法分析器会遇到超前读取一个字符的时候，如果是最后一个
            //字符被读取，如果在读取下一个字符就会出现越界的异常
            char[] buf = new char[length + 1];

            reader.read(buf);
            reader.close();
            new Analysis().analyze(buf, data);
            Output output = new Output(data);
            output.print();

            output.save(fileOperator);//保存成文件
            fileOperator.closeSave();
        }catch (Exception e){
            System.out.println("确保文件名与路径正确,且c源程序没有语法错误");
        }
    }
}
