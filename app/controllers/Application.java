package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    static int a = 0;
    static test tt = new test(true, 1000);
    static boolean flag = false;

    public static void index() {

        if (!flag) {
            tt.start();
            flag = !flag;

        }
        render();
    }

    public static void suspend() {
        tt.doSuspend();
    }

    public static void resume() {
        tt.doResume();
    }

    public static void getA() {
        String argA = a + "";
        renderJSON(argA);

    }

    public static class test extends ExecThread {

        public test(boolean b, long t) {
            super(b, t);
        }

        @Override
        public void run() {
            while (true) {
                a++;
                syncronizeAndDoTheRest();
            }

        }

    }

}
