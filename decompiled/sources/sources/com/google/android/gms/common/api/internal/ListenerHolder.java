package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public final class ListenerHolder<L> {
    private final Executor zaa;
    private volatile ListenerKey zab;

    public static final class ListenerKey<L> {
        private final Object zaa;
        private final String zab;

        public ListenerKey(L r1, String r2) {
            this.zaa = r1;
            this.zab = r2;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof ListenerKey) == true) goto L8;
            return false;
        L8:
            ListenerKey r52 = (ListenerKey) r5;
            if (this.zaa == r52.zaa) goto L11;
        L13:
            return false;
        L11:
            if (this.zab.equals(r52.zab) == false) goto L13;
            return true;
        }

        public int hashCode() {
            return (System.identityHashCode(this.zaa) * 31) + this.zab.hashCode();
        }

        public String toIdString() {
            int r0 = System.identityHashCode(this.zaa);
            String r4 = this.zab;
            int r1 = String.valueOf(r4).length();
            int r12 = r1 + 1;
            StringBuilder r3 = new StringBuilder(r12 + String.valueOf(r0).length());
            r3.append(r4);
            r3.append("@");
            r3.append(r0);
            return r3.toString();
        }

        public final /* synthetic */ Object zaa() {
            return this.zaa;
        }
    }

    public interface Notifier<L> {
        void notifyListener(L r1);

        void onNotifyListenerFailed();
    }

    public ListenerHolder(Looper r2, L r3, String r4) {
        this.zaa = new HandlerExecutor(r2);
        this.zab = new ListenerKey(Preconditions.checkNotNull(r3, "Listener must not be null"), Preconditions.checkNotEmpty(r4));
    }

    public void clear() {
        this.zab = null;
    }

    public ListenerKey<L> getListenerKey() {
        return this.zab;
    }

    public void notifyListener(Notifier<? super L> r2) {
        Preconditions.checkNotNull(r2, "Notifier must not be null");
        zabw r0 = new zabw(this, r2);
        this.zaa.execute(r0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ void zaa(Notifier r1) {
        ListenerKey r0 = this.zab;
        if (r0 != null) goto L11;
        r1.onNotifyListenerFailed();
        return;
    L11:
        r1.notifyListener(r0.zaa());     // Catch: RuntimeException -> L8
        return;
    L8:
        e = move-exception;
        r1.onNotifyListenerFailed();
        throw e;
    }
}
