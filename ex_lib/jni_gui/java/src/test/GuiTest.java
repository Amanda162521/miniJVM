package test;

import java.util.Random;
import org.mini.apploader.AppManager;
import org.mini.gl.warp.GLFrameBuffer;
import org.mini.gl.warp.GLFrameBufferPainter;
import org.mini.gui.GApplication;
import static org.mini.nanovg.Gutil.toUtf8;
import org.mini.gui.GButton;
import org.mini.gui.GCanvas;
import org.mini.gui.GCheckBox;
import org.mini.gui.GColorSelector;
import org.mini.gui.GContainer;
import org.mini.gui.GTextBox;
import org.mini.gui.GFrame;
import org.mini.gui.GForm;
import org.mini.gui.GGraphics;
import org.mini.gui.GImage;
import org.mini.gui.GTextField;
import org.mini.gui.GLabel;
import org.mini.gui.GList;
import org.mini.gui.GObject;
import org.mini.gui.GScrollBar;
import org.mini.gui.GViewPort;
import org.mini.gui.impl.GuiCallBack;
import org.mini.gui.event.GActionListener;
import static org.mini.nanovg.Nanovg.nvgCreateImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gust
 */
public class GuiTest extends GApplication {

    public static void main(String[] args) {
        GuiCallBack ccb = GuiCallBack.getInstance();
        ccb.init(800, 600);//window size

//        GuiTest app = new GuiTest();
//        app.setSaveRoot("./");
//        GForm form = app.getForm(app);
//        form.setTitle("窗口");
//        ccb.setForm(form);
        AppManager.getInstance().active();

        ccb.mainLoop();
        ccb.destory();
    }

    GForm win;

    @Override
    public GForm getForm(GApplication app) {
        if (win == null) {
            win = new GForm(GuiCallBack.getInstance());
            win.init();
            long vg = win.getNvContext();
            GFrame gframe = new GFrame("Github"/*"demo"*/, 50, 50, 300, 500);
            init(gframe.getView(), vg);
            win.add(gframe);
        }
        return win;
    }

    Light light;

    public void init(GViewPort parent, long vg) {
        light = new Light();

        int x = 8, y = 10;
        GTextField gif = new GTextField("", "search", x, y, 280, 25);
        parent.add(gif);
        y += 30;
        GLabel lb1 = new GLabel("Login", x, y, 280, 20);
        parent.add(lb1);
        y += 25;
        GTextField mail = new GTextField("", "Email", x, y, 280, 28);
        parent.add(mail);
        y += 35;
        GTextField pwd = new GTextField("", "Password", x, y, 280, 28);
        parent.add(pwd);
        y += 35;
        String conttxt = "This is longer chunk of text.\n  \n  Would have used lorem ipsum but she    was busy jumping over the lazy dog with the fox and all the men who came to the aid of the party.";
        conttxt += "I test the program ,there are two window , one window left a button that open the other window, the other left a button for close self.\n"
                + "\n"
                + "the issue maybe related with font , if i use nuklear defult font , the bug nerver show , but i am using chinese font (google android system default font), the bug frequently occure. the app memory using about 180M with default font in macos, use chinese font it would be 460M, is that nuklear load all glyph? but it's not the cause of bug .\n"
                + "\n"
                + "i have a reference that using stb_truetype, follow code is a stbtt test case , the code using chinese font ,that var byteOffset is -64 , out of the allocated bitmap memory . but i 'm not sure there is a same issue, only a note.";
        GTextBox cont = new GTextBox(conttxt, "Contents", x, y, 280, 188);
        parent.add(cont);
        y += 195;

        GCheckBox cbox = new GCheckBox("Remember me", true, x, y, 140, 28);
        parent.add(cbox);
        GButton sig = new GButton("Sign in", x + 138, y, 140, 28);
        sig.setBgColor(0, 96, 128, 255);
        sig.setIcon(GObject.ICON_LOGIN);
        parent.add(sig);
        sig.setActionListener(new GActionListener() {
            @Override
            public void action(GObject go) {
                Random ran = new Random();
                GFrame sub1 = new GFrame(/*"子窗口"*/"颜色选择", 400 + ran.nextInt(100), 50 + ran.nextInt(100), 300, 400);
                GViewPort view = sub1.getView();
                init1(view, vg);
                sub1.setClosable(true);
                win.add(sub1);
            }
        });
        y += 35;
        GLabel lb2 = new GLabel("Diameter", x, y, 280, 20);
        parent.add(lb2);
        y += 25;
        //drawEditBoxNum(vg, "123.00", "px", x + 180, y, 100, 28);
        GScrollBar sli = new GScrollBar(0.4f, GScrollBar.HORIZONTAL, x, y, 170, 28);
        parent.add(sli);
        y += 35;
        GButton bt1 = new GButton("Delete删除", x, y, 160, 28);
        bt1.setBgColor(128, 16, 8, 255);
        bt1.setIcon(GObject.ICON_TRASH);
        parent.add(bt1);
        GButton bt2 = new GButton("Cancel", x + 170, y, 110, 28);
        bt2.setBgColor(0, 0, 0, 0);
        parent.add(bt2);
        y += 35;
        GButton bt3 = new GButton("Test", x + 170, y, 110, 28);
        bt3.setBgColor(0, 0, 0, 0);
        parent.add(bt3);

        GButton bt4 = new GButton("Test", x + 170, -10, 110, 28);
        bt4.setBgColor(0, 0, 0, 0);
        parent.add(bt4);

        y += 35;
        GButton bt5 = new GButton("Test", x + 170, y, 110, 28);
        bt5.setBgColor(0, 0, 0, 0);
        parent.add(bt5);

        bt1.setActionListener(new GActionListener() {
            @Override
            public void action(GObject go) {
                System.out.println("delete something");
            }
        });
    }

    GImage img;
    GList list;

    public void init1(GViewPort parent, long vg) {
        img = GImage.createImage("./image4.png");

        int x = 10, y = 10;
        list = new GList(x, y, 280, 30);
        parent.add(list);
        int i = nvgCreateImage(vg, toUtf8("./image4.png"), 0);
        list.setItems(new GImage[]{img, img, img, img, img, img, img, img, img, img},
                new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",});
        y += 40;
        GCanvas cvs = new TestCanvas(x, y, i, i);
        cvs.setLocation(x, y);
        cvs.setSize(280, 150);
        parent.add(cvs);
        y += 160;
        GColorSelector cs = new GColorSelector(0, x, y, 130, 130);
        parent.add(cs);

    }

    class TestCanvas extends GCanvas {

        GLFrameBuffer glfb;
        GLFrameBufferPainter glfbRender;
        GImage img3D;

        public TestCanvas() {
        }

        public TestCanvas(int x, int y, int w, int h) {
            super(x, y, w, h);
//            glfb = new GLFrameBuffer(300, 300);
//            glfbRender = new GLFrameBufferPainter() {
//                @Override
//                public void paint() {
//                    light.setCamera();
//                    light.draw();
//                }
//            };
//            img3D = new GImage(glfb.getTexture(), glfb.getWidth(), glfb.getHeight());
        }

        int pos = 0, delta = 1;

        public void paint(GGraphics g) {
            g.setColor(0xff000000);
            g.fillRect(0, 0, (int) getW(), (int) getH());
            g.setColor(0xff0000ff);
            g.drawLine(0, 100, 100, 100);
            pos += delta;
            if (pos > 50) {
                delta = -1;
            }
            if (pos < 0) {
                delta = 1;
            }
            g.drawString("this is a canvas", pos, 50, GGraphics.TOP | GGraphics.LEFT);
//            glfb.render(glfbRender);
//            g.drawImage(img3D, 0, 0, 100, 100, GGraphics.TOP | GGraphics.LEFT);
        }
    }

}
