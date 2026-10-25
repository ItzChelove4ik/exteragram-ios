package androidx.camera.core.impl.utils.futures;

import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes3.dex */
class ListFuture<V> implements ListenableFuture<List<V>> {
    private final boolean mAllMustSucceed;
    List<? extends ListenableFuture<? extends V>> mFutures;
    private final AtomicInteger mRemaining;
    private final ListenableFuture<List<V>> mResult;
    CallbackToFutureAdapter.Completer<List<V>> mResultNotifier;
    List<V> mValues;

    /* renamed from: androidx.camera.core.impl.utils.futures.ListFuture$1, reason: invalid class name */
    public class AnonymousClass1 implements CallbackToFutureAdapter.Resolver<List<V>> {
        final /* synthetic */ ListFuture this$0;

        public AnonymousClass1(ListFuture r1) {
            this.this$0 = r1;
        }

        @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
        public Object attachCompleter(CallbackToFutureAdapter.Completer<List<V>> r3) {
            if (this.this$0.mResultNotifier != null) goto L5;
            boolean r0 = true;
        L6:
            Preconditions.checkState(r0, "The result can only set once!");
            this.this$0.mResultNotifier = r3;
            return "ListFuture[" + this + "]";
        L5:
            r0 = false;
            goto L6
        }
    }

    /* renamed from: androidx.camera.core.impl.utils.futures.ListFuture$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ ListFuture this$0;

        public AnonymousClass2(ListFuture r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            ListFuture r1 = this.this$0;
            r1.mValues = null;
            r1.mFutures = null;
        }
    }

    /* renamed from: androidx.camera.core.impl.utils.futures.ListFuture$3, reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ ListFuture this$0;
        final /* synthetic */ int val$index;
        final /* synthetic */ ListenableFuture val$listenable;

        public AnonymousClass3(ListFuture r1, int r2, ListenableFuture r3) {
            this.this$0 = r1;
            this.val$index = r2;
            this.val$listenable = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.setOneValue(this.val$index, this.val$listenable);
        }
    }

    public ListFuture(List<? extends ListenableFuture<? extends V>> r3, boolean r4, Executor r5) {
        this.mFutures = (List) Preconditions.checkNotNull(r3);
        this.mValues = new ArrayList(r3.size());
        this.mAllMustSucceed = r4;
        this.mRemaining = new AtomicInteger(r3.size());
        this.mResult = CallbackToFutureAdapter.getFuture(new AnonymousClass1(this));
        init(r5);
    }

    private void callAllGets() {
        List<? extends ListenableFuture<? extends V>> r0 = this.mFutures;
        if (r0 != null) goto L5;
        return;
    L5:
        if (isDone() == true) goto L30;
        Iterator<? extends ListenableFuture<? extends V>> r02 = r0.iterator();
    L8:
        if (r02.hasNext() == false) goto L31;
        ListenableFuture<? extends V> r1 = r02.next();
    L11:
        if (r1.isDone() == true) goto L8;
        r1.get();     // Catch: Throwable -> L14 InterruptedException -> L17 Error -> L19
    L19:
        e = move-exception;
        throw e;
    L17:
        e = move-exception;
        throw e;
    L15:
        if (this.mAllMustSucceed == false) goto L11;
        return;
    L31:
        return;
    }

    private void init(Executor r5) {
        addListener(new AnonymousClass2(this), CameraXExecutors.directExecutor());
        if (this.mFutures.isEmpty() == false) goto L6;
        this.mResultNotifier.set(new ArrayList(this.mValues));
        return;
    L6:
        int r0 = 0;
        int r1 = 0;
    L8:
        if (r1 >= this.mFutures.size()) goto L10;
        this.mValues.add(null);
        r1 = r1 + 1;
        goto L8
    L10:
        List<? extends ListenableFuture<? extends V>> r12 = this.mFutures;
    L12:
        if (r0 >= r12.size()) goto L14;
        ListenableFuture<? extends V> r2 = r12.get(r0);
        r2.addListener(new AnonymousClass3(this, r0, r2), r5);
        r0 = r0 + 1;
        goto L12
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable r1, Executor r2) {
        this.mResult.addListener(r1, r2);
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean r3) {
        List<? extends ListenableFuture<? extends V>> r0 = this.mFutures;
        if (r0 == null) goto L9;
        Iterator<? extends ListenableFuture<? extends V>> r02 = r0.iterator();
    L6:
        if (r02.hasNext() == false) goto L9;
        r02.next().cancel(r3);
    L9:
        return this.mResult.cancel(r3);
    }

    @Override // java.util.concurrent.Future
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }

    @Override // java.util.concurrent.Future
    public /* bridge */ /* synthetic */ Object get(long r1, TimeUnit r3) {
        return get(r1, r3);
    }

    @Override // java.util.concurrent.Future
    public List<V> get() {
        callAllGets();
        return this.mResult.get();
    }

    @Override // java.util.concurrent.Future
    public List<V> get(long r1, TimeUnit r3) {
        return this.mResult.get(r1, r3);
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.mResult.isCancelled();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.mResult.isDone();
    }

    public void setOneValue(int r7, Future<? extends V> r8) {
        List<V> r1 = this.mValues;
        if (isDone() == true) goto L88;
        if (r1 == null) goto L88;
        boolean r2 = true;
        Preconditions.checkState(r8.isDone(), "Tried to set value from future which is not done");     // Catch: Throwable -> L20 Error -> L22 RuntimeException -> L24 ExecutionException -> L26 CancellationException -> L64
        r1.set(r7, Futures.getUninterruptibly(r8));     // Catch: Throwable -> L20 Error -> L22 RuntimeException -> L24 ExecutionException -> L26 CancellationException -> L64
        int r72 = this.mRemaining.decrementAndGet();
        if (r72 >= 0) goto L12;
        r2 = false;
    L12:
        Preconditions.checkState(r2, "Less than 0 remaining futures");
        if (r72 != 0) goto L76;
        List<V> r73 = this.mValues;
        if (r73 == null) goto L18;
        this.mResultNotifier.set(new ArrayList(r73));
        return;
    L18:
        Preconditions.checkState(isDone());
        return;
    L76:
        return;
    L20:
        th = move-exception;
        int r82 = this.mRemaining.decrementAndGet();
        if (r82 >= 0) goto L81;
        r2 = false;
    L81:
        Preconditions.checkState(r2, "Less than 0 remaining futures");
        if (r82 != 0) goto L87;
        List<V> r83 = this.mValues;
        if (r83 == null) goto L86;
        this.mResultNotifier.set(new ArrayList(r83));
        goto L87
    L86:
        Preconditions.checkState(isDone());
    L87:
        throw th;
    L26:
        e = move-exception;
        if (this.mAllMustSucceed == false) goto L55;
        this.mResultNotifier.setException(e.getCause());     // Catch: Throwable -> L20
    L55:
        int r74 = this.mRemaining.decrementAndGet();
        if (r74 >= 0) goto L59;
        r2 = false;
    L59:
        Preconditions.checkState(r2, "Less than 0 remaining futures");
        if (r74 != 0) goto L96;
        List<V> r75 = this.mValues;
        if (r75 != null) goto L63;
    L39:
        Preconditions.checkState(isDone());
        return;
    L63:
        CallbackToFutureAdapter.Completer<List<V>> r6 = this.mResultNotifier;
        ArrayList r84 = new ArrayList(r75);
    L38:
        r6.set(r84);
        return;
    L96:
        return;
    L22:
        e = move-exception;
        this.mResultNotifier.setException(e);     // Catch: Throwable -> L20
        int r76 = this.mRemaining.decrementAndGet();
        if (r76 >= 0) goto L33;
        r2 = false;
    L33:
        Preconditions.checkState(r2, "Less than 0 remaining futures");
        if (r76 != 0) goto L92;
        List<V> r77 = this.mValues;
        if (r77 == null) goto L39;
        r6 = this.mResultNotifier;
        r84 = new ArrayList(r77);
        goto L38
    L92:
        return;
    L24:
        e = move-exception;
        if (this.mAllMustSucceed == false) goto L43;
        this.mResultNotifier.setException(e);     // Catch: Throwable -> L20
    L43:
        int r78 = this.mRemaining.decrementAndGet();
        if (r78 >= 0) goto L47;
        r2 = false;
    L47:
        Preconditions.checkState(r2, "Less than 0 remaining futures");
        if (r78 != 0) goto L95;
        List<V> r79 = this.mValues;
        if (r79 == null) goto L39;
        r6 = this.mResultNotifier;
        r84 = new ArrayList(r79);
        goto L38
    L95:
        return;
    L65:
        if (this.mAllMustSucceed == false) goto L67;
        cancel(false);     // Catch: Throwable -> L20
    L67:
        int r710 = this.mRemaining.decrementAndGet();
        if (r710 >= 0) goto L71;
        r2 = false;
    L71:
        Preconditions.checkState(r2, "Less than 0 remaining futures");
        if (r710 != 0) goto L97;
        List<V> r711 = this.mValues;
        if (r711 == null) goto L39;
        r6 = this.mResultNotifier;
        r84 = new ArrayList(r711);
        goto L38
    L97:
        return;
    L88:
        Preconditions.checkState(this.mAllMustSucceed, "Future was done before all dependencies completed");
    }
}
