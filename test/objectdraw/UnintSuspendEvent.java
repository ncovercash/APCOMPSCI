/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import objectdraw.SuspendEvent;

final class UnintSuspendEvent
extends SuspendEvent {
    UnintSuspendEvent() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void execute() {
        UnintSuspendEvent unintSuspendEvent = this;
        synchronized (unintSuspendEvent) {
            do {
                if (this.isExpired()) {
                    return;
                }
                try {
                    this.wait();
                }
                catch (InterruptedException interruptedException) {
                    // empty catch block
                }
            } while (true);
        }
    }
}

