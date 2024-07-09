package Microsoft.OTS;
import java.util.*;
public class NotificationFormatter { //good
    public static void main(String[] args) {
        System.out.println(prepareNotification("And now here is my secret", 15));//And now ...
        System.out.println(prepareNotification("There is an animal with four legs", 15)); //There is an ...
        System.out.println(prepareNotification("super dog", 4)); //...
        System.out.println(prepareNotification("how are you", 20)); //how are you
    }

    public static String prepareNotification(String msg, int K) {
        if (msg.length() <= K || msg.length() < 3) {
            return msg; // Message fits without any cropping
        }

        int totalLen= 3;
        Stack<String> stack= new Stack<>();
        String[] words= msg.split(" ");
        StringBuilder notification= new StringBuilder();

        for(String word: words){
            int curLen= word.length();
            totalLen += curLen+ 1;
            if(totalLen <= K)   stack.push(word);
            else{
                totalLen = totalLen - curLen - 1;
                break;
            }
        }
        if(!stack.isEmpty()){
            if(msg.length() < K)    return String.join(" ",stack);
            notification.append(String.join(" ",stack)).append(" ...");
            return notification.toString();
        }else   return "...";
    }
}
