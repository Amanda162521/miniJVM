/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.sun.cldc.i18n.StreamReader;
import com.sun.cldc.i18n.StreamWriter;
import com.sun.cldc.i18n.mini.UTF_8_Reader;
import com.sun.cldc.i18n.mini.UTF_8_Writer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.mini.io.File;
import javax.mini.io.RandomAccessFile;

/**
 *
 * @author Gust
 */
public class TestFile {
   
    void printString(String s) {
        for (int i = 0; i < s.length(); i++) {
            System.out.print(" " + Integer.toHexString((int) (s.charAt(i) & 0xffff)));
        }
        System.out.println();
    }

    void printBytes(String s) {
        try {
            byte[] barr = s.getBytes("utf-8");
            for (int i = 0; i < barr.length; i++) {
                System.out.print(" " + Integer.toHexString((int) (barr[i] & 0xff)));
            }
            System.out.println();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    void t15() {

        try {
            String s = "这是一个测试";
            printBytes(s);
            printString(s);
            File test = new File("./a.txt");
            StreamWriter writer = new UTF_8_Writer();
            writer.open(test.getOutputStream(false), "utf-8");
            writer.write(s);
            writer.close();

            StreamReader reader = new UTF_8_Reader();
            reader.open(test.getInputStream(), "utf-8");
            char[] buf = new char[100];
            int len = reader.read(buf, 0, 100);
            reader.close();
            String r = new String(buf, 0, len);
            printString(r);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        t15_1();
    }

    void t15_1() {
        File file = new File(".");
        System.out.println("isDir:" + file.isDirectory());
        String[] files = file.list();
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i]);
        }
    }

    void t16() {
        try {
            File b = new File("./b.txt");
            String r = "这是一个测试";
            printBytes(r);
            printString(r);
            DataOutputStream dos = new DataOutputStream(b.getOutputStream(true));
            dos.writeUTF(r);
            dos.close();
            DataInputStream dis = new DataInputStream(b.getInputStream());
            String s = dis.readUTF();
            printBytes(s);
            printString(s);
            dis.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void t17() {
        try {
            RandomAccessFile c = new RandomAccessFile("./c.txt", "rw");
            c.seek(0);
            String r = "这是一个测试";
            printBytes(r);
            printString(r);
            byte[] carr = r.getBytes("utf-8");
            c.write(carr, 0, carr.length);
            c.close();
            RandomAccessFile c1 = new RandomAccessFile("./c.txt", "r");
            c1.seek(0);
            byte[] barr = new byte[256];
            int len;
            len = c1.read(barr, 0, 256);
            System.out.println("len=" + len);
            c1.close();
            String s = new String(barr, 0, len, "utf-8");
            printBytes(s);
            printString(s);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public static void main(String[] args) {
        try {
            TestFile tf=new TestFile();
            tf.t15();
            tf.t16();
            tf.t17();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}