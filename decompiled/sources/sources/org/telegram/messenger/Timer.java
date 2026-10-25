package org.telegram.messenger;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class Timer {
    final String name;
    int pad;
    final long startTime;
    public ArrayList<Task> tasks;

    /* loaded from: classes5.dex */
    public class Log extends Task {
        final /* synthetic */ Timer this$0;

        public Log(Timer r1, String r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // org.telegram.messenger.Timer.Task
        public String toString() {
            return this.task;
        }
    }

    public class Task {
        long endTime;
        int pad;
        final long startTime;
        final String task;
        final /* synthetic */ Timer this$0;

        /* renamed from: -$$Nest$mdone, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5007$$Nest$mdone(Task r0) {
            r0.done();
        }

        public Task(Timer r3, String r4) {
            this.this$0 = r3;
            this.endTime = -1;
            this.startTime = System.currentTimeMillis();
            this.task = r4;
            r3.pad++;
        }

        private void done() {
            if (this.endTime >= 0) goto L5;
            Timer r0 = this.this$0;
            int r1 = r0.pad;
            r0.pad = r1 - 1;
            this.pad = r1;
        L5:
            this.endTime = System.currentTimeMillis();
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder();
            r0.append(this.task);
            r0.append(": ");
            if (this.endTime >= 0) goto L5;
            String r6 = "not done";
        L6:
            r0.append(r6);
            return r0.toString();
        L5:
            r6 = (this.endTime - this.startTime) + "ms";
            goto L6
        }
    }

    public Timer(String r3) {
        this.pad = 0;
        this.tasks = new ArrayList();
        this.name = r3;
        this.startTime = System.currentTimeMillis();
    }

    public static Timer create(String r1) {
        if (BuildVars.LOGS_ENABLED == true) goto L7;
        return null;
    L7:
        return new Timer(r1);
    }

    public static void done(Task r0) {
        if (r0 == null) goto L5;
        Task.m5007$$Nest$mdone(r0);
        return;
    }

    private void finish() {
        long r0 = System.currentTimeMillis() - this.startTime;
        StringBuilder r2 = new StringBuilder();
        r2.append(this.name);
        r2.append(" total=");
        r2.append(r0);
        r2.append("ms\n");
        int r1 = 0;
    L4:
        if (r1 >= this.tasks.size()) goto L14;
        if (this.tasks.get(r1) == null) goto L13;
        r2.append("#");
        r2.append(r1);
        int r3 = this.tasks.get(r1).pad;
        int r4 = 0;
    L10:
        if (r4 >= r3) goto L12;
        r2.append(" ");
        r4 = r4 + 1;
        goto L10
    L12:
        r2.append(" ");
        r2.append(this.tasks.get(r1));
        r2.append("\n");
    L13:
        r1 = r1 + 1;
        goto L4
    L14:
        FileLog.d(r2.toString());
    }

    public static void finish(Timer r0) {
        if (r0 == null) goto L5;
        r0.finish();
        return;
    }

    private void log(String r3) {
        this.tasks.add(new Log(this, r3));
    }

    public static void log(Timer r0, String r1) {
        if (r0 == null) goto L5;
        r0.log(r1);
        return;
    }

    private Task start(String r2) {
        Task r0 = new Task(this, r2);
        this.tasks.add(r0);
        return r0;
    }

    public static Task start(Timer r0, String r1) {
        if (r0 != null) goto L4;
        return null;
    L4:
        return r0.start(r1);
    }
}
