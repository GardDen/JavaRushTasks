package regex;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 1 on 01.08.2017.
 */
public class ParseHTML {
    public static void main(String[] args) {
        String regex = "(<span)|(</span[^>]*>)";
        String text = "<span>string1 <span>string2</span> string11</span>";
        List<String> list = new ArrayList<>();
        Deque<Integer> queue = new LinkedList<>();
        Matcher matcher = Pattern.compile(regex).matcher(text);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                queue.add(matcher.start());
            } else {
                list.add(text.substring(queue.removeLast(), matcher.end()));
            }
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
