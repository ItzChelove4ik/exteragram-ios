package androidx.camera.camera2.impl;

import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Size;
import android.view.Display;
import androidx.camera.camera2.compat.workaround.DisplaySizeCorrector;
import androidx.camera.camera2.compat.workaround.MaxPreviewSize;
import androidx.camera.core.impl.utils.ContextUtil;
import androidx.camera.core.internal.utils.SizeUtil;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0014J\u0013\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002¢\u0006\u0002\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\r2\b\b\u0002\u0010\u001b\u001a\u00020\u001cJ\b\u0010\u001d\u001a\u00020\u0014H\u0002J\b\u0010\u001e\u001a\u00020\u0014H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Landroidx/camera/camera2/impl/DisplayInfoManager;", _UrlKt.FRAGMENT_ENCODE_SET, "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "maxPreviewSize", "Landroidx/camera/camera2/compat/workaround/MaxPreviewSize;", "displaySizeCorrector", "Landroidx/camera/camera2/compat/workaround/DisplaySizeCorrector;", "lock", "displays", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/view/Display;", "[Landroid/view/Display;", "displayListener", "Landroid/hardware/display/DisplayManager$DisplayListener;", "displayManager", "Landroid/hardware/display/DisplayManager;", "previewSize", "Landroid/util/Size;", "refreshPreviewSize", _UrlKt.FRAGMENT_ENCODE_SET, "getPreviewSize", "getDisplays", "()[Landroid/view/Display;", "getMaxSizeDisplay", "skipStateOffDisplay", _UrlKt.FRAGMENT_ENCODE_SET, "calculatePreviewSize", "getCorrectedDisplaySize", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDisplayInfoManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DisplayInfoManager.kt\nandroidx/camera/camera2/impl/DisplayInfoManager\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,240:1\n1#2:241\n*E\n"})
/* loaded from: classes3.dex */
public final class DisplayInfoManager {
    private static final Size ABNORMAL_DISPLAY_SIZE_THRESHOLD = null;
    public static final Companion Companion = null;
    private static final Size FALLBACK_DISPLAY_SIZE = null;
    private static final Size MAX_PREVIEW_SIZE = null;
    private static volatile DisplayInfoManager instance;
    private final DisplayManager.DisplayListener displayListener;
    private final DisplayManager displayManager;
    private final DisplaySizeCorrector displaySizeCorrector;
    private volatile Display[] displays;
    private final Object lock;
    private final MaxPreviewSize maxPreviewSize;
    private volatile Size previewSize;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\u000bR\u0014\u0010\r\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000bR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/camera/camera2/impl/DisplayInfoManager$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroid/content/Context;", "context", "Landroidx/camera/camera2/impl/DisplayInfoManager;", "getInstance", "(Landroid/content/Context;)Landroidx/camera/camera2/impl/DisplayInfoManager;", "Landroid/util/Size;", "MAX_PREVIEW_SIZE", "Landroid/util/Size;", "ABNORMAL_DISPLAY_SIZE_THRESHOLD", "FALLBACK_DISPLAY_SIZE", "instance", "Landroidx/camera/camera2/impl/DisplayInfoManager;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nDisplayInfoManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DisplayInfoManager.kt\nandroidx/camera/camera2/impl/DisplayInfoManager$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,240:1\n1#2:241\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final DisplayInfoManager getInstance(Context r3) {
            DisplayInfoManager r0 = DisplayInfoManager.access$getInstance$cp();
            if (r0 != null) goto L15;
            monitor-enter(this);
            DisplayInfoManager r02 = DisplayInfoManager.access$getInstance$cp();     // Catch: Throwable -> L9
            if (r02 != null) goto L11;
            r02 = new DisplayInfoManager(ContextUtil.getPersistentApplicationContext(r3), null);     // Catch: Throwable -> L9
            DisplayInfoManager.access$setInstance$cp(r02);     // Catch: Throwable -> L9
        L11:
            monitor-exit(this);
            return r02;
        L9:
            th = move-exception;
            throw th;
        L15:
            return r0;
        }
    }

    static {
        Companion = new Companion(null);
        MAX_PREVIEW_SIZE = new Size(1920, 1080);
        ABNORMAL_DISPLAY_SIZE_THRESHOLD = new Size(320, 240);
        FALLBACK_DISPLAY_SIZE = new Size(640, 480);
    }

    private DisplayInfoManager(Context r4) {
        this.maxPreviewSize = new MaxPreviewSize(null, 1, null);
        this.displaySizeCorrector = new DisplaySizeCorrector();
        this.lock = new Object();
        DisplayInfoManager$displayListener$1 r0 = new DisplayInfoManager$displayListener$1(this);
        this.displayListener = r0;
        DisplayManager r42 = (DisplayManager) r4.getSystemService("display");
        r42.registerDisplayListener(r0, new Handler(Looper.getMainLooper()));
        this.displayManager = r42;
    }

    public /* synthetic */ DisplayInfoManager(Context r1, DefaultConstructorMarker r2) {
        this(r1);
    }

    public static final /* synthetic */ DisplayInfoManager access$getInstance$cp() {
        return instance;
    }

    public static final /* synthetic */ Object access$getLock$p(DisplayInfoManager r0) {
        return r0.lock;
    }

    public static final /* synthetic */ void access$setDisplays$p(DisplayInfoManager r0, Display[] r1) {
        r0.displays = r1;
    }

    public static final /* synthetic */ void access$setInstance$cp(DisplayInfoManager r0) {
        instance = r0;
    }

    public static final /* synthetic */ void access$setPreviewSize$p(DisplayInfoManager r0, Size r1) {
        r0.previewSize = r1;
    }

    private final Size calculatePreviewSize() {
        Size r0 = getCorrectedDisplaySize();
        Size r1 = MAX_PREVIEW_SIZE;
        if (SizeUtil.isSmallerByArea(r1, r0) == false) goto L6;
        r0 = r1;
    L6:
        return this.maxPreviewSize.getMaxPreviewResolution(r0);
    }

    private final Size getCorrectedDisplaySize() {
        Point r0 = new Point();
        getMaxSizeDisplay(false).getRealSize(r0);
        Size r1 = new Size(r0.x, r0.y);
        if (SizeUtil.isSmallerByArea(r1, ABNORMAL_DISPLAY_SIZE_THRESHOLD) == false) goto L9;
        Size r3 = this.displaySizeCorrector.getDisplaySize();
        if (r3 != null) goto L7;
        r3 = FALLBACK_DISPLAY_SIZE;
    L7:
        r1 = r3;
    L9:
        if (r1.getHeight() > r1.getWidth()) goto L11;
        return r1;
    L11:
        return new Size(r1.getHeight(), r1.getWidth());
    }

    private final Display[] getDisplays() {
        Object r0 = this.lock;
        monitor-enter(r0);
        Display[] r1 = this.displays;     // Catch: Throwable -> L11
        if (r1 == null) goto L8;
        monitor-exit(r0);
        return r1;
    L8:
        Display[] r12 = this.displayManager.getDisplays();     // Catch: Throwable -> L11
        this.displays = r12;     // Catch: Throwable -> L11
        monitor-exit(r0);
        return r12;
    L11:
        th = move-exception;
        throw th;
    }

    public static /* synthetic */ Display getMaxSizeDisplay$default(DisplayInfoManager r0, boolean r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = true;
    L6:
        return r0.getMaxSizeDisplay(r1);
    }

    public final Display getMaxSizeDisplay(boolean r14) {
        Display[] r13 = getDisplays();
        int r1 = 0;
        if (r13.length == 1) goto L5;
        int r0 = r13.length;
        int r3 = -1;
        int r5 = -1;
        Display r6 = null;
        Display r7 = null;
    L7:
        if (r1 >= r0) goto L17;
        Display r8 = r13[r1];
        Point r9 = new Point();
        r8.getRealSize(r9);
        int r10 = r9.x;
        int r11 = r9.y;
        if ((r10 * r11) <= r3) goto L12;
        r6 = r8;
        r3 = r10 * r11;
    L12:
        if (r8.getState() == 1) goto L16;
        int r102 = r9.x;
        int r92 = r9.y;
        if ((r102 * r92) <= r5) goto L16;
        r7 = r8;
        r5 = r102 * r92;
    L16:
        r1 = r1 + 1;
        goto L7
    L17:
        if (r14 == false) goto L21;
        if (r7 == null) goto L21;
        r6 = r7;
    L21:
        if (r6 == null) goto L23;
        return r6;
    L23:
        DisplayInfoManager$$ExternalSyntheticBUOutline0.m("No displays found from ", Arrays.toString(r13));
        return null;
    L5:
        return r13[0];
    }

    public final Size getPreviewSize() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.previewSize == null) goto L11;
        Size r2 = this.previewSize;     // Catch: Throwable -> L9
        monitor-exit(r0);
        return r2;
    L11:
        this.previewSize = calculatePreviewSize();     // Catch: Throwable -> L9
        Size r22 = this.previewSize;     // Catch: Throwable -> L9
        monitor-exit(r0);
        return r22;
    }

    public final void refreshPreviewSize() {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.previewSize = calculatePreviewSize();     // Catch: Throwable -> L7
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
