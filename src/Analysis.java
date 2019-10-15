import java.io.File;
import java.io.FileReader;

/**
 * 此程序是通过将文件的字符读取到字符数组中去，然后遍历数组，将读取的字符进行分类并输出
 * @author XieHao
 *
 */
public class Analysis {
    private Data data=null;
    private char ch;
    //判断是否是关键字
    boolean isKey(String str)
    {
        return data.mapSet.containsKey(str);
    }
    //判断是否是字母
    boolean isLetter(char letter)
    {
        return (letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z');
    }
    //判断是否是数字
    boolean isDigit(char digit)
    {
        return digit >= '0' && digit <= '9';
    }
    //判断是否是$与下划线
    boolean is$_(char letter)
    {
        return (letter == '$') || (letter == '_');
    }
    boolean isComment1_s(char letter)
    {
        return (letter == '$') || (letter == '_');
    }
    boolean isComment1_e(char letter)
    {
        return (letter == '$') || (letter == '_');
    }
    boolean isComment2_s(char letter)
    {
        return (letter == '$') || (letter == '_');
    }
    boolean isComment2_e(char letter)
    {
        return (letter == '$') || (letter == '_');
    }
    //词法分析
    void analyze(char[] chars,Data data)
    {
        int order=0;//存放词的顺序
        this.data=data;//数据存放
        String arr = "";

        for(int i = 0;i< chars.length;i++) {
            ch = chars[i];
            arr = "";
            if(ch == ' '||ch == '\t'||ch == '\n'||ch == '\r'){}
            else if(isLetter(ch)||is$_(ch)){
                while(isLetter(ch)||isDigit(ch)||is$_(ch)){
                    arr += ch;
                    ch = chars[++i];
                }
                //回退一个字符
                i--;
                if(isKey(arr)){
                    //关键字
                    data.result.put(++order,new String[]{arr,"关键字"});
                }
                else{
                    //标识符
                    data.result.put(++order,new String[]{arr,"标识符"});
                }
            }
            else if(isDigit(ch))
            {
                arr=arr+ch;
                while (isDigit(ch=chars[++i])){
                    arr=arr+ch;
                }
                if((ch=chars[i])=='.'|chars[i]=='f'){
                    arr=arr+ch;
                    while (isDigit(ch=chars[++i])|ch=='f'){
                        if(ch=='f'){arr=arr+ch;++i;break;}
                        arr=arr+ch;
                    }
                    data.result.put(++order,new String[]{arr,"浮点数"});
                }
                //属于无符号常数
                else data.result.put(++order,new String[]{arr,"常数"});
                --i;
            }
            else switch(ch){
                    case '#':
                        while (chars[++i]!='\n') ;
                        break;
                    //运算符
                    case '*':data.result.put(++order,new String[]{""+ch,"运算符"});break;
                    //分界符
                    case '(':data.result.put(++order,new String[]{""+ch,"分界符"});break;
                    case ')':data.result.put(++order,new String[]{""+ch,"分界符"});break;
                    case '[':data.result.put(++order,new String[]{""+ch,"分界符"});break;
                    case ']':data.result.put(++order,new String[]{""+ch,"分界符"});break;
                    case ';':data.result.put(++order,new String[]{""+ch,"分界符"});break;
                    case '{':data.result.put(++order,new String[]{""+ch,"分界符"});break;
                    case '}':data.result.put(++order,new String[]{""+ch,"分界符"});break;
                    case ',':data.result.put(++order,new String[]{""+ch,"分界符"});break;
                    //运算符
                    case '/': {
                        ch = chars[++i];
                        if (ch == '*') {
                            while (!(((chars[++i])=='*')&&((chars[i+1])=='/'))){
                            }
                            ++i;
                            data.result.put(++order,new String[]{"/*","注释开始"});

                            data.result.put(++order,new String[]{"*/","注释结束"});
                        }
                        else if (ch == '/') {
                            while (chars[++i]!='\n'){
                            }

                            data.result.put(++order,new String[]{"//","单行注释"});
                        }
                        else {
                            data.result.put(++order,new String[]{"/","运算符"});
                            i--;
                        }
                    }break;
                    case '\"': {//字符串
                        if(i>0&&chars[i-1]!='\\') {
                            data.result.put(++order, new String[]{"\"", "字符串开始"});
                            while (!(chars[++i] == '\"' && chars[i - 1] != '\\')) {}

                            data.result.put(++order, new String[]{"\"", "字符串结束"});
                        }
                    }break;
                    case '\'': {//字符
                        if(i>0&&chars[i-1]!='\\') {
                            data.result.put(++order, new String[]{"\'", "字符开始"});
                            while (!(chars[++i] == '\'' && chars[i - 1] != '\\')) {
                            }
                            data.result.put(++order, new String[]{"\'", "字符结束"});
                        }
                    }break;

                    case '&': {
                        ch = chars[++i];
                        if (ch == '&') data.result.put(++order,new String[]{"&&","运算符"});
                        else {
                            data.result.put(++order,new String[]{"&","运算符"});
                            i--;
                        }
                    }break;
                    case '+': {
                        ch = chars[++i];
                        if (ch == '+') data.result.put(++order,new String[]{"++","运算符"});
                        else {
                            data.result.put(++order,new String[]{"+","运算符"});
                            i--;
                        }
                    }break;
                    case '-': {
                        ch = chars[++i];
                        if (ch == '-') data.result.put(++order,new String[]{"--","运算符"});
                        else {
                            data.result.put(++order,new String[]{"-","运算符"});
                            i--;
                        }
                    }break;
                    case '!':{
                        ch = chars[++i];
                        if(ch == '=')data.result.put(++order,new String[]{"!=","运算符"});
                        else {
                            data.result.put(++order,new String[]{"!","运算符"});
                            i--;
                        }
                    }break;
                    case '=':{
                        ch = chars[++i];
                        if(ch == '=')data.result.put(++order,new String[]{"==","运算符"});
                        else {
                            data.result.put(++order,new String[]{"=","运算符"});
                            i--;
                        }
                    }break;
                    case ':':{
                        ch = chars[++i];
                        if(ch == ':')data.result.put(++order,new String[]{"::","运算符"});
                        else {
                            data.result.put(++order,new String[]{":","运算符"});
                            i--;
                        }
                    }break;
                    case '>':{
                        ch = chars[++i];
                        if(ch == '=')data.result.put(++order,new String[]{">=","运算符"});
                        else {
                            data.result.put(++order,new String[]{">","运算符"});
                            i--;
                        }
                    }break;
                    case '<':{
                        ch = chars[++i];
                        if(ch == '=')data.result.put(++order,new String[]{"<=","运算符"});
                        else {
                            data.result.put(++order,new String[]{"<","运算符"});
                            i--;
                        }
                    }break;
                    //无识别
                    default:
                        //System.out.println(ch+"\t6"+"\t无识别符");
                }
        }
    }

}