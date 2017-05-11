/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import objectdraw.SuspendEvent;

final class InterruptableSuspendEvent
extends SuspendEvent {
    InterruptableSuspendEvent() {
    }

    public void execute() {
        InterruptableSuspendEvent interruptableSuspendEvent = this;
        synchronized (interruptableSuspendEvent) {
            try {
                if (!this.isExpired()) {
                    this.wait();
                }
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
        }
    }
}

