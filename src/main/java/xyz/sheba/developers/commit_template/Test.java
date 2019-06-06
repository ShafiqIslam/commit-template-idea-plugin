package xyz.sheba.developers.commit_template;

import org.apache.commons.lang3.StringEscapeUtils;
import xyz.sheba.developers.commit_template.dto.CZRC;

public class Test {

    public static void main(String[] args) throws Exception {
        CZRC czrc = CZRC.load("E:\\Works\\Khucra\\commit-template");
        CZRC czrc1 = CZRC.get();
        System.out.println("\u00f7");
        System.out.println(czrc1.getTypes().get(0).getEmoji());
    }
}
