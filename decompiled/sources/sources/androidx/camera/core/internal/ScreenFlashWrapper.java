package androidx.camera.core.internal;

import androidx.camera.core.ImageCapture;
import androidx.camera.core.Logger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0000\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0013\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\b\u0010\u0007J\u001f\u0010\r\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u000f\u0010\u0007J\r\u0010\u0010\u001a\u00020\u0005¢\u0006\u0004\b\u0010\u0010\u0007R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0016\u001a\u00020\u00158\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, d2 = {"Landroidx/camera/core/internal/ScreenFlashWrapper;", "Landroidx/camera/core/ImageCapture$ScreenFlash;", "screenFlash", "<init>", "(Landroidx/camera/core/ImageCapture$ScreenFlash;)V", _UrlKt.FRAGMENT_ENCODE_SET, "completePendingScreenFlashListener", "()V", "completePendingScreenFlashClear", _UrlKt.FRAGMENT_ENCODE_SET, "expirationTimeMillis", "Landroidx/camera/core/ImageCapture$ScreenFlashListener;", "screenFlashListener", "apply", "(JLandroidx/camera/core/ImageCapture$ScreenFlashListener;)V", "clear", "completePendingTasks", "Landroidx/camera/core/ImageCapture$ScreenFlash;", _UrlKt.FRAGMENT_ENCODE_SET, "lock", "Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "isClearScreenFlashPending", "Z", "pendingListener", "Landroidx/camera/core/ImageCapture$ScreenFlashListener;", "Companion", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ScreenFlashWrapper implements ImageCapture.ScreenFlash {
    public static final Companion Companion = null;
    private boolean isClearScreenFlashPending;
    private final Object lock;
    private ImageCapture.ScreenFlashListener pendingListener;
    private final ImageCapture.ScreenFlash screenFlash;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/camera/core/internal/ScreenFlashWrapper$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "from", "Landroidx/camera/core/internal/ScreenFlashWrapper;", "screenFlash", "Landroidx/camera/core/ImageCapture$ScreenFlash;", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final ScreenFlashWrapper from(ImageCapture.ScreenFlash r2) {
            return new ScreenFlashWrapper(r2, null);
        }
    }

    /* renamed from: $r8$lambda$3O4w5sgEqhZqXYjda-gTyWXM7Vw, reason: not valid java name */
    public static void m603$r8$lambda$3O4w5sgEqhZqXYjdagTyWXM7Vw(ScreenFlashWrapper r3) {
        Object r0 = r3.lock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (r3.pendingListener != null) goto L9;
        Logger.w("ScreenFlashWrapper", "apply: pendingListener is null!");     // Catch: Throwable -> L7
    L9:
        r3.completePendingScreenFlashListener();     // Catch: Throwable -> L7
        Unit r32 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
    }

    static {
        Companion = new Companion(null);
    }

    private ScreenFlashWrapper(ImageCapture.ScreenFlash r1) {
        this.screenFlash = r1;
        this.lock = new Object();
    }

    public /* synthetic */ ScreenFlashWrapper(ImageCapture.ScreenFlash r1, DefaultConstructorMarker r2) {
        this(r1);
    }

    private final void completePendingScreenFlashClear() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.isClearScreenFlashPending == false) goto L12;
        ImageCapture.ScreenFlash r1 = this.screenFlash;     // Catch: Throwable -> L9
        if (r1 == null) goto L11;
        r1.clear();     // Catch: Throwable -> L9
    L13:
        this.isClearScreenFlashPending = false;     // Catch: Throwable -> L9
        Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r0);
        return;
    L11:
        Logger.e("ScreenFlashWrapper", "completePendingScreenFlashClear: screenFlash is null!");     // Catch: Throwable -> L9
        goto L13
    L12:
        Logger.w("ScreenFlashWrapper", "completePendingScreenFlashClear: none pending!");     // Catch: Throwable -> L9
        goto L13
    }

    private final void completePendingScreenFlashListener() {
        Object r0 = this.lock;
        monitor-enter(r0);
        ImageCapture.ScreenFlashListener r1 = this.pendingListener;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        r1.onCompleted();     // Catch: Throwable -> L7
    L9:
        this.pendingListener = null;     // Catch: Throwable -> L7
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @JvmStatic
    public static final ScreenFlashWrapper from(ImageCapture.ScreenFlash r1) {
        return Companion.from(r1);
    }

    @Override // androidx.camera.core.ImageCapture.ScreenFlash
    public void apply(long r3, ImageCapture.ScreenFlashListener r5) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.isClearScreenFlashPending = true;     // Catch: Throwable -> L13
        this.pendingListener = r5;     // Catch: Throwable -> L13
        Unit r52 = Unit.INSTANCE;     // Catch: Throwable -> L13
        monitor-exit(r0);
        ImageCapture.ScreenFlash r53 = this.screenFlash;
        if (r53 == null) goto L11;
        r53.apply(r3, new ScreenFlashWrapper$$ExternalSyntheticLambda0(this));
        return;
    L11:
        Logger.e("ScreenFlashWrapper", "apply: screenFlash is null!");
        completePendingScreenFlashListener();
        return;
    L13:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.ImageCapture.ScreenFlash
    public void clear() {
        completePendingScreenFlashClear();
    }

    public final void completePendingTasks() {
        completePendingScreenFlashListener();
        completePendingScreenFlashClear();
    }
}
