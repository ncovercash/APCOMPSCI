/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import objectdraw.ActiveObjectEventInterface;

abstract class SuspendEvent
implements ActiveObjectEventInterface {
    private boolean resumeCalled = false;

    SuspendEvent() {
    }

    public void resume() {
        SuspendEvent suspendEvent = this;
        synchronized (suspendEvent) {
            this.resumeCalled = true;
            this.notifyAll();
        }
    }

    public boolean isExpired() {
        SuspendEvent suspendEvent = this;
        synchronized (suspendEvent) {
            return this.resumeCalled;
        }
    }
}

