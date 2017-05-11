/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import objectdraw.AWTWindowController;

class AWTInitializer
extends Thread {
    AWTWindowController windowController;

    public AWTInitializer(AWTWindowController windowController) {
        this.windowController = windowController;
        this.start();
    }

    public void run() {
        while (!this.windowController.callBegin()) {
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
        }
    }
}

