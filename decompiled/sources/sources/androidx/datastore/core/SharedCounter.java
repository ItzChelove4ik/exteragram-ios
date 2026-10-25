package androidx.datastore.core;

import android.os.ParcelFileDescriptor;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0000\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/datastore/core/SharedCounter;", _UrlKt.FRAGMENT_ENCODE_SET, "mappedAddress", _UrlKt.FRAGMENT_ENCODE_SET, "(J)V", "getValue", _UrlKt.FRAGMENT_ENCODE_SET, "incrementAndGetValue", "Factory", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SharedCounter {
    public static final Factory Factory = null;
    private static final NativeSharedCounter nativeSharedCounter = null;
    private final long mappedAddress;

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001b\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0000¢\u0006\u0002\b\fJ\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0006\u0010\u0010\u001a\u00020\u0011R\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Landroidx/datastore/core/SharedCounter$Factory;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "nativeSharedCounter", "Landroidx/datastore/core/NativeSharedCounter;", "getNativeSharedCounter$datastore_core_release", "()Landroidx/datastore/core/NativeSharedCounter;", "create", "Landroidx/datastore/core/SharedCounter;", "produceFile", "Lkotlin/Function0;", "Ljava/io/File;", "create$datastore_core_release", "createCounterFromFd", "pfd", "Landroid/os/ParcelFileDescriptor;", "loadLib", _UrlKt.FRAGMENT_ENCODE_SET, "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Factory {
        private Factory() {
        }

        public /* synthetic */ Factory(DefaultConstructorMarker r1) {
            this();
        }

        private final SharedCounter createCounterFromFd(ParcelFileDescriptor r3) {
            int r32 = r3.getFd();
            if (getNativeSharedCounter$datastore_core_release().nativeTruncateFile(r32) != 0) goto L11;
            long r2 = getNativeSharedCounter$datastore_core_release().nativeCreateSharedCounter(r32);
            if (r2 >= 0) goto L7;
            Model$$ExternalSyntheticBUOutline0.m("Failed to mmap counter file");
            return null;
        L7:
            return new SharedCounter(r2, null);
        L11:
            Model$$ExternalSyntheticBUOutline0.m("Failed to truncate counter file");
            return null;
        }

        public final SharedCounter create$datastore_core_release(Function0<? extends File> r2) {
            ParcelFileDescriptor r22 = ParcelFileDescriptor.open(r2.invoke(), 939524096);     // Catch: Throwable -> L10
            SharedCounter r1 = createCounterFromFd(r22);     // Catch: Throwable -> L8
            if (r22 == null) goto L7;
            r22.close();
        L7:
            return r1;
        L8:
            th = th;
        L12:
            if (r22 == null) goto L14;
            r22.close();
        L14:
            throw th;
        L10:
            th = th;
            r22 = null;
            goto L12
        }

        public final NativeSharedCounter getNativeSharedCounter$datastore_core_release() {
            return SharedCounter.access$getNativeSharedCounter$cp();
        }

        public final void loadLib() {
            System.loadLibrary("datastore_shared_counter");
        }
    }

    static {
        Factory = new Factory(null);
        nativeSharedCounter = new NativeSharedCounter();
    }

    private SharedCounter(long r1) {
        this.mappedAddress = r1;
    }

    public /* synthetic */ SharedCounter(long r1, DefaultConstructorMarker r3) {
        this(r1);
    }

    public static final /* synthetic */ NativeSharedCounter access$getNativeSharedCounter$cp() {
        return nativeSharedCounter;
    }

    public final int getValue() {
        return nativeSharedCounter.nativeGetCounterValue(this.mappedAddress);
    }

    public final int incrementAndGetValue() {
        return nativeSharedCounter.nativeIncrementAndGetCounterValue(this.mappedAddress);
    }
}
