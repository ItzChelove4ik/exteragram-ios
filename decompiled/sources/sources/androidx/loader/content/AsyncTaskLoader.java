package androidx.loader.content;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.text.format.DateUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public abstract class AsyncTaskLoader<D> extends Loader<D> {
    private volatile AsyncTaskLoader<D>.LoadTask mCancellingTask;
    private Executor mExecutor;
    private Handler mHandler;
    private long mLastLoadCompleteTime;
    private volatile AsyncTaskLoader<D>.LoadTask mTask;
    private long mUpdateThrottle;

    public final class LoadTask extends ModernAsyncTask<D> implements Runnable {
        final /* synthetic */ AsyncTaskLoader this$0;
        boolean waiting;

        public LoadTask(AsyncTaskLoader r1) {
            this.this$0 = r1;
        }

        @Override // androidx.loader.content.ModernAsyncTask
        public D doInBackground() {
            return (D) this.this$0.onLoadInBackground();
        }

        @Override // androidx.loader.content.ModernAsyncTask
        public void onCancelled(D r2) {
            this.this$0.dispatchOnCancelled(this, r2);
        }

        @Override // androidx.loader.content.ModernAsyncTask
        public void onPostExecute(D r2) {
            this.this$0.dispatchOnLoadComplete(this, r2);
        }

        @Override // java.lang.Runnable
        public void run() {
            this.waiting = false;
            this.this$0.executePendingTask();
        }
    }

    public AsyncTaskLoader(Context r3) {
        super(r3);
        this.mLastLoadCompleteTime = -10000;
    }

    public void cancelLoadInBackground() {
    }

    public void dispatchOnCancelled(AsyncTaskLoader<D>.LoadTask r1, D r2) {
        onCanceled(r2);
        if (this.mCancellingTask != r1) goto L6;
        rollbackContentChanged();
        this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
        this.mCancellingTask = null;
        deliverCancellation();
        executePendingTask();
        return;
    }

    public void dispatchOnLoadComplete(AsyncTaskLoader<D>.LoadTask r3, D r4) {
        if (this.mTask == r3) goto L7;
        dispatchOnCancelled(r3, r4);
        return;
    L7:
        if (isAbandoned() == false) goto L10;
        onCanceled(r4);
        return;
    L10:
        commitContentChanged();
        this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
        this.mTask = null;
        deliverResult(r4);
    }

    @Override // androidx.loader.content.Loader
    @Deprecated
    public void dump(String r7, FileDescriptor r8, PrintWriter r9, String[] r10) {
        super.dump(r7, r8, r9, r10);
        if (this.mTask == null) goto L6;
        r9.print(r7);
        r9.print("mTask=");
        r9.print(this.mTask);
        r9.print(" waiting=");
        r9.println(this.mTask.waiting);
    L6:
        if (this.mCancellingTask == null) goto L9;
        r9.print(r7);
        r9.print("mCancellingTask=");
        r9.print(this.mCancellingTask);
        r9.print(" waiting=");
        r9.println(this.mCancellingTask.waiting);
    L9:
        if (this.mUpdateThrottle == 0) goto L16;
        r9.print(r7);
        r9.print("mUpdateThrottle=");
        r9.print(DateUtils.formatElapsedTime(this.mUpdateThrottle / 1000));
        r9.print(" mLastLoadCompleteTime=");
        if (this.mLastLoadCompleteTime != (-10000)) goto L13;
        String r6 = "--";
    L14:
        r9.print(r6);
        r9.println();
        return;
    L13:
        r6 = "-" + DateUtils.formatElapsedTime((SystemClock.uptimeMillis() - this.mLastLoadCompleteTime) / 1000);
        goto L14
    }

    public void executePendingTask() {
        if (this.mCancellingTask == null) goto L5;
        return;
    L5:
        if (this.mTask != null) goto L7;
        return;
    L7:
        if (this.mTask.waiting == false) goto L10;
        this.mTask.waiting = false;
        this.mHandler.removeCallbacks(this.mTask);
    L10:
        if (this.mUpdateThrottle <= 0) goto L16;
        if (SystemClock.uptimeMillis() >= (this.mLastLoadCompleteTime + this.mUpdateThrottle)) goto L16;
        this.mTask.waiting = true;
        this.mHandler.postAtTime(this.mTask, this.mLastLoadCompleteTime + this.mUpdateThrottle);
        return;
    L16:
        if (this.mExecutor != null) goto L18;
        this.mExecutor = getExecutor();
    L18:
        this.mTask.executeOnExecutor(this.mExecutor);
    }

    public Executor getExecutor() {
        return AsyncTask.THREAD_POOL_EXECUTOR;
    }

    public abstract D loadInBackground();

    @Override // androidx.loader.content.Loader
    public boolean onCancelLoad() {
        if (this.mTask != null) goto L5;
        return false;
    L5:
        if (isStarted() == true) goto L7;
        onContentChanged();
    L7:
        AsyncTaskLoader<D>.LoadTask r0 = this.mCancellingTask;
        AsyncTaskLoader<D>.LoadTask r2 = this.mTask;
        if (r0 != null) goto L10;
        boolean r02 = r2.waiting;
        AsyncTaskLoader<D>.LoadTask r22 = this.mTask;
        if (r02 == false) goto L18;
        r22.waiting = false;
        this.mHandler.removeCallbacks(this.mTask);
        this.mTask = null;
        return false;
    L18:
        boolean r03 = r22.cancel(false);
        if (r03 == false) goto L21;
        this.mCancellingTask = this.mTask;
        cancelLoadInBackground();
    L21:
        this.mTask = null;
        return r03;
    L10:
        if (r2.waiting == false) goto L12;
        this.mTask.waiting = false;
        this.mHandler.removeCallbacks(this.mTask);
    L12:
        this.mTask = null;
        return false;
    }

    public void onCanceled(D r1) {
    }

    @Override // androidx.loader.content.Loader
    public void onForceLoad() {
        super.onForceLoad();
        cancelLoad();
        this.mTask = new LoadTask(this);
        executePendingTask();
    }

    public D onLoadInBackground() {
        return loadInBackground();
    }
}
