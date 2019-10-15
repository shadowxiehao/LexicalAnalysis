import java.io.*;

/*
这个类用于打开文件并返回文件中的字符串
 */
public class FileOperator {
    private String inputC=null;//C文件位置
    //整数、自定义标识符及以下关键字"+ - * / < <= == != > >= & && || = ( ) [ ] { } : ; , void int float char if else while do ! main"等的编号
    private String inputSet="./Set.txt";//定义的关键字文件默认为"Set.txt"
    private String save="./save.txt";//默认保存名save.txt
    private File f_inputC=null ;// C文件
    private File f_inputSet=null ;// 预设文件
    private File f_save=null ;// 保存文件
    BufferedReader readerC = null;
    BufferedReader readerSet=null;
    PrintWriter outSave=null;

    public FileOperator(String inputC,String inputSet)throws Exception{
        this.inputC = inputC;
        this.inputSet = inputSet;
        initFile();
    }
    public FileOperator(String inputC)throws Exception{
        this.inputC = inputC;
        initFile();
    }

    public void setInputC(String inputC) {
        this.inputC = inputC;
    }

    public void setInputSet(String inputSet) {
        this.inputSet = inputSet;
    }

    private void initFile() throws Exception{
        f_inputC=new File(inputC);// 指定要读取的文件
        f_inputSet=new File(inputSet);
        readerC = new BufferedReader(new FileReader(f_inputC));
        readerSet = new BufferedReader(new FileReader(f_inputSet));
        outSave = new PrintWriter(save);
    }

    public BufferedReader getReaderC() {
        return readerC;
    }

    public BufferedReader getReaderSet() {
        return readerSet;
    }

    public File getF_inputC(){
        return this.f_inputC;
    }

    public File getF_inputSet() {
        return f_inputSet;
    }

    public File getF_save() {
        return f_save;
    }

    public String getLineC() throws Exception{
        return readerC.readLine();
    }
    public String getLineSet() throws Exception{
        return readerSet.readLine();
    }
    public boolean saveAll(String string){
        outSave.write(string);
        return true;
    }
    public boolean closeC(){
        try {
            readerC.close();
        }catch (Exception e){return false;}
        return true;
    }
    public boolean closeSet(){
        try {
            readerSet.close();
        }catch (Exception e){return false;}
        return true;
    }
    public boolean closeSave(){
        try {
            outSave.close();
        }catch (Exception e){return false;}
        return true;
    }
}
