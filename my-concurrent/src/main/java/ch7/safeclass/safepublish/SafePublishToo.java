package ch7.safeclass.safepublish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Rocky
 * @Date: 2020/4/9
 * @Description:
 */
public class SafePublishToo {

    //  包装为线程安全的
    private static List<Integer> list = Collections.synchronizedList(new ArrayList<>(3));

    public SafePublishToo() {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    public List<Integer> getList() {
        return list;
    }

    public static void main(String[] args) {
        SafePublishToo too = new SafePublishToo();
        List<Integer> list = too.getList();
        System.out.println("取出时：" + list);
        list.add(4);
        System.out.println("添加后：" + list);
        System.out.println("原来的：" + too.getList());
    }
}
