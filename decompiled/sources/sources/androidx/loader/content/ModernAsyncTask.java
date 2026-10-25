package androidx.loader.content;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
abstract class ModernAsyncTask<Result> {
    private static Handler sHandler;
    final AtomicBoolean mCancelled;
    private final FutureTask<Result> mFuture;
    private volatile Status mStatus;
    final AtomicBoolean mTaskInvoked;

    /* renamed from: androidx.loader.content.ModernAsyncTask$1, reason: invalid class name */
    public class AnonymousClass1 implements Callable<Result> {
        final /* synthetic */ ModernAsyncTask this$0;

        public AnonymousClass1(ModernAsyncTask r1) {
            this.this$0 = r1;
        }

        @Override // java.util.concurrent.Callable
        public Result call() {
            this.this$0.mTaskInvoked.set(true);
            Result r2 = null;
            Process.setThreadPriority(10);     // Catch: Throwable -> L6
            r2 = (Result) this.this$0.doInBackground();
            Binder.flushPendingCommands();     // Catch: Throwable -> L6
            this.this$0.postResult(r2);
            return r2;
        L6:
            th = move-exception;
            this.this$0.mCancelled.set(true);     // Catch: Throwable -> L9
            throw th;     // Catch: Throwable -> L9
        L9:
            th = move-exception;
            this.this$0.postResult(r2);
            throw th;
        }
    }

    /* renamed from: androidx.loader.content.ModernAsyncTask$2, reason: invalid class name */
    public class AnonymousClass2 extends FutureTask<Result> {
        final /* synthetic */ ModernAsyncTask this$0;

        public AnonymousClass2(ModernAsyncTask r1, Callable r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // java.util.concurrent.FutureTask
        public void done() {
            Result r1 = get();     // Catch: Throwable -> L5 ExecutionException -> L7 CancellationException -> L11 InterruptedException -> L14
            this.this$0.postResultIfNotInvoked(r1);     // Catch: Throwable -> L5 ExecutionException -> L7 CancellationException -> L11 InterruptedException -> L14
            return;
        L14:
            e = move-exception;
            Log.w("AsyncTask", e);
            return;
        L11:
            this.this$0.postResultIfNotInvoked(null);
            return;
        L7:
            e = move-exception;
            Make$Map$$ExternalSyntheticBUOutline0.m("An error occurred while executing doInBackground()", e.getCause());
            return;
        L5:
            th = move-exception;
            Make$Map$$ExternalSyntheticBUOutline0.m("An error occurred while executing doInBackground()", th);
        }
    }

    /* renamed from: androidx.loader.content.ModernAsyncTask$3, reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ ModernAsyncTask this$0;
        final /* synthetic */ Object val$result;

        public AnonymousClass3(ModernAsyncTask r1, Object r2) {
            this.this$0 = r1;
            this.val$result = r2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            this.this$0.finish(this.val$result);
        }
    }

    /* renamed from: androidx.loader.content.ModernAsyncTask$4, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$androidx$loader$content$ModernAsyncTask$Status = null;

        static {
            int[] r0 = new int[Status.values().length];
            $SwitchMap$androidx$loader$content$ModernAsyncTask$Status = r0;
            r0[Status.RUNNING.ordinal()] = 1;     // Catch: NoSuchFieldError -> L6
        L8:
            $SwitchMap$androidx$loader$content$ModernAsyncTask$Status[Status.FINISHED.ordinal()] = 2;     // Catch: NoSuchFieldError -> L7
            return;
        }
    }

    public enum Status extends Enum<Status> {
        private static final /* synthetic */ Status[] $VALUES = null;
        public static final Status FINISHED = null;
        public static final Status PENDING = null;
        public static final Status RUNNING = null;

        static {
            Status r0 = new Status("PENDING", 0);
            PENDING = r0;
            Status r1 = new Status("RUNNING", 1);
            RUNNING = r1;
            Status r2 = new Status("FINISHED", 2);
            FINISHED = r2;
            $VALUES = new Status[]{r0, r1, r2};
        }

        Status(String r1, int r2) {
        }

        public static Status valueOf(String r1) {
            return (Status) Enum.valueOf(Status.class, r1);
        }

        public static Status[] values() {
            return (Status[]) $VALUES.clone();
        }
    }

    public ModernAsyncTask() {
        this.mStatus = Status.PENDING;
        this.mCancelled = new AtomicBoolean();
        this.mTaskInvoked = new AtomicBoolean();
        this.mFuture = new AnonymousClass2(this, new AnonymousClass1(this));
    }

    private static Handler getHandler() {
        monitor-enter(ModernAsyncTask.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (sHandler != null) goto L9;
        sHandler = new Handler(Looper.getMainLooper());     // Catch: Throwable -> L7
    L9:
        Handler r1 = sHandler;     // Catch: Throwable -> L7
        monitor-exit(ModernAsyncTask.class);     // Catch: Throwable -> L7
        return r1;
    }

    public final boolean cancel(boolean r3) {
        this.mCancelled.set(true);
        return this.mFuture.cancel(r3);
    }

    public abstract Result doInBackground();

    public final void executeOnExecutor(Executor r3) {
        if (this.mStatus == Status.PENDING) goto L14;
        int r2 = AnonymousClass4.$SwitchMap$androidx$loader$content$ModernAsyncTask$Status[this.mStatus.ordinal()];
        if (r2 != 1) goto L7;
        Segment$$ExternalSyntheticBUOutline1.m("Cannot execute task: the task is already running.");
        return;
    L7:
        if (r2 == 2) goto L10;
        Segment$$ExternalSyntheticBUOutline1.m("We should never reach this state");
        return;
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("Cannot execute task: the task has already been executed (a task can be executed only once)");
        return;
    L14:
        this.mStatus = Status.RUNNING;
        r3.execute(this.mFuture);
    }

    public void finish(Result r2) {
        if (isCancelled() == false) goto L5;
        onCancelled(r2);
    L6:
        this.mStatus = Status.FINISHED;
        return;
    L5:
        onPostExecute(r2);
        goto L6
    }

    public final boolean isCancelled() {
        return this.mCancelled.get();
    }

    public abstract void onCancelled(Result r1);

    public abstract void onPostExecute(Result r1);

    public void postResult(Result r3) {
        getHandler().post(new AnonymousClass3(this, r3));
    }

    public void postResultIfNotInvoked(Result r2) {
        if (this.mTaskInvoked.get() == true) goto L6;
        postResult(r2);
        return;
    }
}
