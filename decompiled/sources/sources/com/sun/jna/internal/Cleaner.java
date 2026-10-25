package com.sun.jna.internal;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes5.dex */
public class Cleaner {
    private static final Cleaner INSTANCE = null;
    private Thread cleanerThread;
    private CleanerRef firstCleanable;
    private final ReferenceQueue<Object> referenceQueue;

    public interface Cleanable {
        void clean();
    }

    public static class CleanerRef extends PhantomReference<Object> implements Cleanable {
        private final Cleaner cleaner;
        private final Runnable cleanupTask;
        private CleanerRef next;
        private CleanerRef previous;

        public CleanerRef(Cleaner r1, Object r2, ReferenceQueue<? super Object> r3, Runnable r4) {
            super(r2, r3);
            this.cleaner = r1;
            this.cleanupTask = r4;
        }

        public static /* synthetic */ CleanerRef access$400(CleanerRef r0) {
            return r0.next;
        }

        public static /* synthetic */ Runnable access$500(CleanerRef r0) {
            return r0.cleanupTask;
        }

        @Override // com.sun.jna.internal.Cleaner.Cleanable
        public void clean() {
            if (Cleaner.access$000(this.cleaner, this) == false) goto L6;
            this.cleanupTask.run();
            return;
        }

        public CleanerRef getNext() {
            return this.next;
        }

        public CleanerRef getPrevious() {
            return this.previous;
        }

        public void setNext(CleanerRef r1) {
            this.next = r1;
        }

        public void setPrevious(CleanerRef r1) {
            this.previous = r1;
        }
    }

    public class CleanerThread extends Thread {
        final /* synthetic */ Cleaner this$0;

        public CleanerThread(Cleaner r1) {
            this.this$0 = r1;
            super("JNA Cleaner");
            setDaemon(true);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
        L36:
            Reference r1 = Cleaner.access$100(this.this$0).remove(NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);     // Catch: Exception -> L6 InterruptedException -> L33
            if ((r1 instanceof CleanerRef) == true) goto L5;
            if (r1 != null) goto L36;
            ReferenceQueue r12 = Cleaner.access$100(this.this$0);     // Catch: Exception -> L6 InterruptedException -> L33
            monitor-enter(r12);     // Catch: Exception -> L6 InterruptedException -> L33
            Logger r2 = Logger.getLogger(Cleaner.class.getName());     // Catch: Throwable -> L16
            if (Cleaner.access$200(this.this$0) != null) goto L19;
            Cleaner.access$302(this.this$0, null);     // Catch: Throwable -> L16
            r2.log(Level.FINE, "Shutting down CleanerThread");     // Catch: Throwable -> L16
            monitor-exit(r12);     // Catch: Throwable -> L16
            return;
        L19:
            if (r2.isLoggable(Level.FINER) == false) goto L27;
            StringBuilder r3 = new StringBuilder();     // Catch: Throwable -> L16
            CleanerRef r4 = Cleaner.access$200(this.this$0);     // Catch: Throwable -> L16
        L21:
            if (r4 == null) goto L26;
            if (r3.length() == 0) goto L25;
            r3.append(", ");     // Catch: Throwable -> L16
        L25:
            r3.append(CleanerRef.access$500(r4).toString());     // Catch: Throwable -> L16
            r4 = CleanerRef.access$400(r4);     // Catch: Throwable -> L16
            goto L21
        L26:
            r2.log(Level.FINER, "Registered Cleaners: {0}", r3.toString());     // Catch: Throwable -> L16
        L27:
            monitor-exit(r12);     // Catch: Throwable -> L16
        L16:
            th = move-exception;
            throw th;     // Catch: Exception -> L6 InterruptedException -> L33
            goto L53
        L5:
            ((CleanerRef) r1).clean();     // Catch: Exception -> L6 InterruptedException -> L33
        L6:
            e = move-exception;
            Logger.getLogger(Cleaner.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    static {
        INSTANCE = new Cleaner();
    }

    private Cleaner() {
        this.referenceQueue = new ReferenceQueue();
    }

    public static /* synthetic */ boolean access$000(Cleaner r0, CleanerRef r1) {
        return r0.remove(r1);
    }

    public static /* synthetic */ ReferenceQueue access$100(Cleaner r0) {
        return r0.referenceQueue;
    }

    public static /* synthetic */ CleanerRef access$200(Cleaner r0) {
        return r0.firstCleanable;
    }

    public static /* synthetic */ Thread access$302(Cleaner r0, Thread r1) {
        r0.cleanerThread = r1;
        return r1;
    }

    private synchronized CleanerRef add(CleanerRef r5) {
        monitor-enter(this);
        ReferenceQueue<Object> r0 = this.referenceQueue;     // Catch: Throwable -> L19
        monitor-enter(r0);     // Catch: Throwable -> L19
        CleanerRef r1 = this.firstCleanable;     // Catch: Throwable -> L8
        if (r1 != null) goto L10;
        this.firstCleanable = r5;     // Catch: Throwable -> L8
    L12:
        if (this.cleanerThread != null) goto L14;
        Logger.getLogger(Cleaner.class.getName()).log(Level.FINE, "Starting CleanerThread");     // Catch: Throwable -> L8
        CleanerThread r12 = new CleanerThread(this);     // Catch: Throwable -> L8
        this.cleanerThread = r12;     // Catch: Throwable -> L8
        r12.start();     // Catch: Throwable -> L8
    L14:
        monitor-exit(r0);     // Catch: Throwable -> L8
        monitor-exit(this);
        return r5;
    L10:
        r5.setNext(r1);     // Catch: Throwable -> L8
        this.firstCleanable.setPrevious(r5);     // Catch: Throwable -> L8
        this.firstCleanable = r5;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;     // Catch: Throwable -> L19
    L19:
        th = move-exception;
        throw th;
    }

    public static Cleaner getCleaner() {
        return INSTANCE;
    }

    private synchronized boolean remove(CleanerRef r6) {
        monitor-enter(this);
        ReferenceQueue<Object> r0 = this.referenceQueue;     // Catch: Throwable -> L29
        monitor-enter(r0);     // Catch: Throwable -> L29
        boolean r2 = true;
        if (r6 != this.firstCleanable) goto L10;
        this.firstCleanable = r6.getNext();     // Catch: Throwable -> L8
        boolean r1 = true;
    L12:
        if (r6.getPrevious() == null) goto L15;
        r6.getPrevious().setNext(r6.getNext());     // Catch: Throwable -> L8
    L15:
        if (r6.getNext() == null) goto L18;
        r6.getNext().setPrevious(r6.getPrevious());     // Catch: Throwable -> L8
    L18:
        if (r6.getPrevious() == null) goto L20;
    L23:
        r6.setNext(null);     // Catch: Throwable -> L8
        r6.setPrevious(null);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        monitor-exit(this);
        return r2;
    L20:
        if (r6.getNext() != null) goto L23;
        r2 = r1;
        goto L23
    L10:
        r1 = false;
    L8:
        th = move-exception;
        throw th;     // Catch: Throwable -> L29
    L29:
        th = move-exception;
        throw th;
    }

    public synchronized Cleanable register(Object r3, Runnable r4) {
        monitor-enter(this);
        CleanerRef r32 = add(new CleanerRef(this, r3, this.referenceQueue, r4));     // Catch: Throwable -> L6
        monitor-exit(this);
        return r32;
    L6:
        th = move-exception;
        throw th;
    }
}
