/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import objectdraw.ActiveObjectEventInterface;

final class TerminateEvent
implements ActiveObjectEventInterface {
    TerminateEvent() {
    }

    public void execute() {
        throw new ThreadDeath();
    }

    public boolean isExpired() {
        return false;
    }
}

