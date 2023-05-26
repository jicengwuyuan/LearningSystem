import UIComponent.demodata;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Demo4 {

    public static void main(String[] args) {
        JFrame frame = new JFrame();//窗口类
        JTable table = new JTable(new demodata());//table表格类只是显示数据但不存储数据 ,存储是tablemodel 接口是:数据表数据
        JScrollPane pane = new JScrollPane(table);//通过这个类显示表头
        frame.add(pane);
        frame.pack();//自己的大小计算
        frame.setVisible(true);//空窗口
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭
    }

}
