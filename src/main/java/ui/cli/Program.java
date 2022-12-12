package ui.cli;

import util.Crypto;

public class Program {
    public static void main(String[] args) {
        new Program().run();
    }

    private void run() {
        System.out.println(Crypto.getInstance().encrypt("moviebase-user"));
        System.out.println(Crypto.getInstance().encrypt("moviebase-pwd"));
    }
}
