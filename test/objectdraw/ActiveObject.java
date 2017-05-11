/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import objectdraw.ActiveObject;
import objectdraw.ActiveObjectEventInterface;

public class ActiveObject
extends Thread {
    private final EventDeque EVENT_DEQUE = new EventDeque();
    private final EventIterator CLASS_EVENT_ITERATOR = CLASS_EVENT_LIST.getEventIterator();
    private static EventList CLASS_EVENT_LIST;

    public ActiveObject() {
        ActiveObject.introspect();
    }

    public ActiveObject(String name) {
        super(name);
        ActiveObject.introspect();
    }

    public ActiveObject(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        ActiveObject.introspect();
    }

    void scheduleEvent(ActiveObjectEventInterface event) {
        this.EVENT_DEQUE.put(event);
    }

    static void introspect() {
        Thread CURRENT_THREAD = Thread.currentThread();
        if (CURRENT_THREAD instanceof ActiveObject) {
            ActiveObject CURRENT_ACTIVE_OBJECT = (ActiveObject)CURRENT_THREAD;
            ActiveObjectEventInterface event = CURRENT_ACTIVE_OBJECT.CLASS_EVENT_ITERATOR.getNext();
            while (event != null) {
                event.execute();
                event = CURRENT_ACTIVE_OBJECT.CLASS_EVENT_ITERATOR.getNext();
            }
            event = CURRENT_ACTIVE_OBJECT.EVENT_DEQUE.poll();
            while (event != null) {
                event.execute();
                event = CURRENT_ACTIVE_OBJECT.EVENT_DEQUE.poll();
            }
        }
    }

    public static void defer() {
        ActiveObject.introspect();
        Thread.yield();
    }

    public static void yield() {
        ActiveObject.introspect();
        Thread.yield();
    }

    public static void pause(long millis) {
        ActiveObject.pause(millis, 0);
    }

    public static void pause(double millis) {
        ActiveObject.pause(Math.round(millis), 0);
    }

    public static void pause(long millis, int nano) {
        ActiveObject.introspect();
        try {
            Thread.sleep(millis, nano);
        }
        catch (InterruptedException interruptedException) {
            // empty catch block
        }
    }

    public static double getTime() {
        throw new UnsupportedOperationException("getTime() is no longer supported - use System.currentTimeMillis() instead");
    }

    static void scheduleClassEvent(ActiveObjectEventInterface event) {
        CLASS_EVENT_LIST.add(event);
    }

    static void initializeEventList() {
        CLASS_EVENT_LIST = new EventList();
    }

    private static class EventDeque {
        private EventNode head;
        private EventNode tail;
        private final Object PUT_LOCK;
        private final Object PUSH_LOCK;

        EventDeque() {
            this.tail = this.head = new EventNode(null);
            this.PUT_LOCK = new Object();
            this.PUSH_LOCK = new Object();
        }

        void put(ActiveObjectEventInterface event) {
            if (event != null) {
                EventNode NEW_TAIL = new EventNode(event);
                Object object = this.PUT_LOCK;
                synchronized (object) {
                    this.tail.setNext(NEW_TAIL);
                    this.tail = NEW_TAIL;
                }
            } else {
                throw new NullPointerException();
            }
        }

        void push(ActiveObjectEventInterface event) {
            if (event != null) {
                EventNode NEW_HEAD = new EventNode(null);
                Object object = this.PUSH_LOCK;
                synchronized (object) {
                    NEW_HEAD.setNext(this.head);
                    this.head.setEvent(event);
                    this.head = NEW_HEAD;
                }
            } else {
                throw new NullPointerException();
            }
        }

        ActiveObjectEventInterface poll() {
            Object object = this.PUSH_LOCK;
            synchronized (object) {
                ActiveObjectEventInterface event = null;
                EventNode NEW_HEAD = this.head.getNext();
                if (NEW_HEAD != null) {
                    event = NEW_HEAD.getEvent();
                    NEW_HEAD.setEvent(null);
                    this.head = NEW_HEAD;
                }
                return event;
            }
        }
    }

    private static class EventList {
        private final EventNode HEAD;
        private EventNode tail;
        private final Object ADD_LOCK;

        public EventList() {
            this.tail = this.HEAD = new EventNode(null);
            this.ADD_LOCK = new Object();
        }

        void add(ActiveObjectEventInterface event) {
            if (event != null) {
                EventNode NEW_TAIL = new EventNode(event);
                Object object = this.ADD_LOCK;
                synchronized (object) {
                    this.tail.setNext(NEW_TAIL);
                    this.tail = NEW_TAIL;
                }
            } else {
                throw new NullPointerException();
            }
            if (Math.random() < 0.1) {
                this.trim();
            }
        }

        EventIterator getEventIterator() {
            return new EventIterator(this){
                private EventNode currentNode;
                final /* synthetic */ EventList this$1;

                public ActiveObjectEventInterface getNext() {
                    ActiveObjectEventInterface activeObjectEventInterface;
                    EventNode nextNode = this.currentNode.getNext();
                    if (nextNode == null) {
                        activeObjectEventInterface = null;
                    } else {
                        this.currentNode = nextNode;
                        activeObjectEventInterface = this.currentNode.getEvent();
                    }
                    return activeObjectEventInterface;
                }
            };
        }

        private void trim() {
            EventNode currentNode = this.HEAD;
            EventNode nextNode = null;
            EventNode nextUnexpiredNode = null;
            while (currentNode != null) {
                nextUnexpiredNode = nextNode = currentNode.getNext();
                while (nextUnexpiredNode != null && nextUnexpiredNode.getEvent().isExpired()) {
                    nextUnexpiredNode = nextUnexpiredNode.getNext();
                }
                if (nextUnexpiredNode != nextNode && nextUnexpiredNode != null) {
                    currentNode.setNext(nextUnexpiredNode);
                }
                currentNode = nextUnexpiredNode;
            }
        }

        static /* synthetic */ EventNode access$0(EventList eventList) {
            return eventList.HEAD;
        }
    }

    private static interface EventIterator {
        public ActiveObjectEventInterface getNext();
    }

    private static class EventNode {
        private ActiveObjectEventInterface event = null;
        private EventNode next = null;
        private final Object NEXT_LOCK = new Object();

        public EventNode(ActiveObjectEventInterface event) {
            this.event = event;
        }

        public ActiveObjectEventInterface getEvent() {
            return this.event;
        }

        public void setEvent(ActiveObjectEventInterface event) {
            this.event = event;
        }

        public EventNode getNext() {
            Object object = this.NEXT_LOCK;
            synchronized (object) {
                return this.next;
            }
        }

        public void setNext(EventNode next) {
            Object object = this.NEXT_LOCK;
            synchronized (object) {
                this.next = next;
            }
        }
    }

}

