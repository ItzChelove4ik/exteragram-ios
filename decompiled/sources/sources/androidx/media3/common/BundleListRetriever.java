package androidx.media3.common;

import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.media3.common.util.Assertions;
import com.google.common.collect.ImmutableList;
import java.util.List;

/* loaded from: classes3.dex */
public final class BundleListRetriever extends Binder {
    private static final int SUGGESTED_MAX_IPC_SIZE = 0;
    private final ImmutableList<Bundle> list;

    static {
        if (Build.VERSION.SDK_INT < 30) goto L5;
        int r0 = IBinder.getSuggestedMaxIpcSizeBytes();
    L6:
        SUGGESTED_MAX_IPC_SIZE = r0;
        return;
    L5:
        r0 = 65536;
        goto L6
    }

    public BundleListRetriever(List<Bundle> r1) {
        this.list = ImmutableList.copyOf(r1);
    }

    public static ImmutableList<Bundle> getList(IBinder r1) {
        if ((r1 instanceof BundleListRetriever) == false) goto L7;
        return ((BundleListRetriever) r1).list;
    L7:
        return getListFromRemoteBinder(r1);
    }

    public static ImmutableList<Bundle> getListFromRemoteBinder(IBinder r7) {
        ImmutableList.Builder r0 = ImmutableList.builder();
        int r4 = 0;
        int r3 = 1;
    L3:
        if (r3 == 0) goto L20;
        Parcel r32 = Parcel.obtain();
        Parcel r5 = Parcel.obtain();
        r32.writeInt(r4);     // Catch: Throwable -> L11
        r7.transact(1, r32, r5, 0);     // Catch: Throwable -> L11 RemoteException -> L14
    L7:
        int r6 = r5.readInt();     // Catch: Throwable -> L11
        if (r6 != 1) goto L13;
        r0.add((Bundle) Assertions.checkNotNull(r5.readBundle()));     // Catch: Throwable -> L11
        r4 = r4 + 1;
        goto L7
    L13:
        r5.recycle();
        r32.recycle();
        r3 = r6;
        goto L3
    L14:
        e = move-exception;
        throw new RuntimeException(e);     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        r5.recycle();
        r32.recycle();
        throw th;
    L20:
        return r0.build();
    }

    @Override // android.os.Binder
    public boolean onTransact(int r4, Parcel r5, Parcel r6, int r7) {
        if (r4 != 1) goto L5;
        int r42 = 0;
        if (r6 != null) goto L9;
        return false;
    L9:
        int r72 = this.list.size();
        int r52 = r5.readInt();
    L10:
        if (r52 >= r72) goto L14;
        if (r6.dataSize() >= SUGGESTED_MAX_IPC_SIZE) goto L14;
        r6.writeInt(1);
        r6.writeBundle(this.list.get(r52));
        r52 = r52 + 1;
    L14:
        if (r52 >= r72) goto L16;
        r42 = 2;
    L16:
        r6.writeInt(r42);
        return true;
    L5:
        return super.onTransact(r4, r5, r6, r7);
    }
}
