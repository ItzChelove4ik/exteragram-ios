package androidx.camera.camera2.pipe.graph;

import android.hardware.camera2.params.MeteringRectangle;
import androidx.camera.camera2.pipe.AeMode;
import androidx.camera.camera2.pipe.AfMode;
import androidx.camera.camera2.pipe.AwbMode;
import androidx.camera.camera2.pipe.FlashMode;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001c\b\u0080\b\u0018\u00002\u00020\u0001B\u0091\u0001\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0013\u0010\u0014J\u009a\u0001\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000fHÆ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0019\u001a\u00020\u0018HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001c\u001a\u00020\u001bHÖ\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u001a\u0010\u001f\u001a\u00020\u000f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001f\u0010 R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010!\u001a\u0004\b\"\u0010#R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010$\u001a\u0004\b%\u0010&R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010'\u001a\u0004\b(\u0010)R\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\t\u0010*\u001a\u0004\b+\u0010,R\u001f\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\f\u0010-\u001a\u0004\b.\u0010/R\u001f\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\r\u0010-\u001a\u0004\b0\u0010/R\u001f\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000e\u0010-\u001a\u0004\b1\u0010/R\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u00102\u001a\u0004\b3\u00104R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0011\u00102\u001a\u0004\b5\u00104R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0012\u00102\u001a\u0004\b6\u00104¨\u00067"}, d2 = {"Landroidx/camera/camera2/pipe/graph/State3A;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/AeMode;", "aeMode", "Landroidx/camera/camera2/pipe/AfMode;", "afMode", "Landroidx/camera/camera2/pipe/AwbMode;", "awbMode", "Landroidx/camera/camera2/pipe/FlashMode;", "flashMode", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/params/MeteringRectangle;", "aeRegions", "afRegions", "awbRegions", _UrlKt.FRAGMENT_ENCODE_SET, "aeLock", "afLock", "awbLock", "<init>", "(Landroidx/camera/camera2/pipe/AeMode;Landroidx/camera/camera2/pipe/AfMode;Landroidx/camera/camera2/pipe/AwbMode;Landroidx/camera/camera2/pipe/FlashMode;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "copy-7jOEVJU", "(Landroidx/camera/camera2/pipe/AeMode;Landroidx/camera/camera2/pipe/AfMode;Landroidx/camera/camera2/pipe/AwbMode;Landroidx/camera/camera2/pipe/FlashMode;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)Landroidx/camera/camera2/pipe/graph/State3A;", "copy", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Landroidx/camera/camera2/pipe/AeMode;", "getAeMode-O_cDUUs", "()Landroidx/camera/camera2/pipe/AeMode;", "Landroidx/camera/camera2/pipe/AfMode;", "getAfMode-32_E3BI", "()Landroidx/camera/camera2/pipe/AfMode;", "Landroidx/camera/camera2/pipe/AwbMode;", "getAwbMode-aLFtWSU", "()Landroidx/camera/camera2/pipe/AwbMode;", "Landroidx/camera/camera2/pipe/FlashMode;", "getFlashMode-cL-19HE", "()Landroidx/camera/camera2/pipe/FlashMode;", "Ljava/util/List;", "getAeRegions", "()Ljava/util/List;", "getAfRegions", "getAwbRegions", "Ljava/lang/Boolean;", "getAeLock", "()Ljava/lang/Boolean;", "getAfLock", "getAwbLock", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class State3A {
    private final Boolean aeLock;
    private final AeMode aeMode;
    private final List<MeteringRectangle> aeRegions;
    private final Boolean afLock;
    private final AfMode afMode;
    private final List<MeteringRectangle> afRegions;
    private final Boolean awbLock;
    private final AwbMode awbMode;
    private final List<MeteringRectangle> awbRegions;
    private final FlashMode flashMode;

    private State3A(AeMode r1, AfMode r2, AwbMode r3, FlashMode r4, List<MeteringRectangle> r5, List<MeteringRectangle> r6, List<MeteringRectangle> r7, Boolean r8, Boolean r9, Boolean r10) {
        this.aeMode = r1;
        this.afMode = r2;
        this.awbMode = r3;
        this.flashMode = r4;
        this.aeRegions = r5;
        this.afRegions = r6;
        this.awbRegions = r7;
        this.aeLock = r8;
        this.afLock = r9;
        this.awbLock = r10;
    }

    public /* synthetic */ State3A(AeMode r2, AfMode r3, AwbMode r4, FlashMode r5, List r6, List r7, List r8, Boolean r9, Boolean r10, Boolean r11, int r12, DefaultConstructorMarker r13) {
        if ((r12 & 1) == 0) goto L6;
        r2 = null;
    L6:
        if ((r12 & 2) == 0) goto L9;
        r3 = null;
    L9:
        if ((r12 & 4) == 0) goto L12;
        r4 = null;
    L12:
        if ((r12 & 8) == 0) goto L15;
        r5 = null;
    L15:
        if ((r12 & 16) == 0) goto L18;
        r6 = null;
    L18:
        if ((r12 & 32) == 0) goto L21;
        r7 = null;
    L21:
        if ((r12 & 64) == 0) goto L24;
        r8 = null;
    L24:
        if ((r12 & 128) == 0) goto L27;
        r9 = null;
    L27:
        if ((r12 & 256) == 0) goto L30;
        r10 = null;
    L30:
        if ((r12 & 512) == 0) goto L32;
        r11 = null;
    L32:
        this(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, null);
    }

    public /* synthetic */ State3A(AeMode r1, AfMode r2, AwbMode r3, FlashMode r4, List r5, List r6, List r7, Boolean r8, Boolean r9, Boolean r10, DefaultConstructorMarker r11) {
        this(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    /* renamed from: copy-7jOEVJU, reason: not valid java name */
    public final State3A m534copy7jOEVJU(AeMode r13, AfMode r14, AwbMode r15, FlashMode r16, List<MeteringRectangle> r17, List<MeteringRectangle> r18, List<MeteringRectangle> r19, Boolean r20, Boolean r21, Boolean r22) {
        return new State3A(r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, null);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof State3A) == true) goto L8;
        return false;
    L8:
        State3A r52 = (State3A) r5;
        if (Intrinsics.areEqual(this.aeMode, r52.aeMode) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.afMode, r52.afMode) == true) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(this.awbMode, r52.awbMode) == true) goto L18;
        return false;
    L18:
        if (Intrinsics.areEqual(this.flashMode, r52.flashMode) == true) goto L21;
        return false;
    L21:
        if (Intrinsics.areEqual(this.aeRegions, r52.aeRegions) == true) goto L24;
        return false;
    L24:
        if (Intrinsics.areEqual(this.afRegions, r52.afRegions) == true) goto L27;
        return false;
    L27:
        if (Intrinsics.areEqual(this.awbRegions, r52.awbRegions) == true) goto L30;
        return false;
    L30:
        if (Intrinsics.areEqual(this.aeLock, r52.aeLock) == true) goto L33;
        return false;
    L33:
        if (Intrinsics.areEqual(this.afLock, r52.afLock) == true) goto L36;
        return false;
    L36:
        if (Intrinsics.areEqual(this.awbLock, r52.awbLock) == true) goto L38;
        return false;
    L38:
        return true;
    }

    public final Boolean getAeLock() {
        return this.aeLock;
    }

    /* renamed from: getAeMode-O_cDUUs, reason: not valid java name */
    public final AeMode m535getAeModeO_cDUUs() {
        return this.aeMode;
    }

    public final List<MeteringRectangle> getAeRegions() {
        return this.aeRegions;
    }

    public final Boolean getAfLock() {
        return this.afLock;
    }

    /* renamed from: getAfMode-32_E3BI, reason: not valid java name */
    public final AfMode m536getAfMode32_E3BI() {
        return this.afMode;
    }

    public final List<MeteringRectangle> getAfRegions() {
        return this.afRegions;
    }

    public final Boolean getAwbLock() {
        return this.awbLock;
    }

    /* renamed from: getAwbMode-aLFtWSU, reason: not valid java name */
    public final AwbMode m537getAwbModeaLFtWSU() {
        return this.awbMode;
    }

    public final List<MeteringRectangle> getAwbRegions() {
        return this.awbRegions;
    }

    /* renamed from: getFlashMode-cL-19HE, reason: not valid java name */
    public final FlashMode m538getFlashModecL19HE() {
        return this.flashMode;
    }

    public int hashCode() {
        AeMode r0 = this.aeMode;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        int r03 = r02 * 31;
        AfMode r2 = this.afMode;
        if (r2 != null) goto L9;
        int r22 = 0;
    L10:
        int r04 = (r03 + r22) * 31;
        AwbMode r23 = this.awbMode;
        if (r23 != null) goto L13;
        int r24 = 0;
    L14:
        int r05 = (r04 + r24) * 31;
        FlashMode r25 = this.flashMode;
        if (r25 != null) goto L17;
        int r26 = 0;
    L18:
        int r06 = (r05 + r26) * 31;
        List<MeteringRectangle> r27 = this.aeRegions;
        if (r27 != null) goto L21;
        int r28 = 0;
    L22:
        int r07 = (r06 + r28) * 31;
        List<MeteringRectangle> r29 = this.afRegions;
        if (r29 != null) goto L25;
        int r210 = 0;
    L26:
        int r08 = (r07 + r210) * 31;
        List<MeteringRectangle> r211 = this.awbRegions;
        if (r211 != null) goto L29;
        int r212 = 0;
    L30:
        int r09 = (r08 + r212) * 31;
        Boolean r213 = this.aeLock;
        if (r213 != null) goto L33;
        int r214 = 0;
    L34:
        int r010 = (r09 + r214) * 31;
        Boolean r215 = this.afLock;
        if (r215 != null) goto L37;
        int r216 = 0;
    L38:
        int r011 = (r010 + r216) * 31;
        Boolean r3 = this.awbLock;
        if (r3 == null) goto L43;
        r1 = r3.hashCode();
    L43:
        return r011 + r1;
    L37:
        r216 = r215.hashCode();
        goto L38
    L33:
        r214 = r213.hashCode();
        goto L34
    L29:
        r212 = r211.hashCode();
        goto L30
    L25:
        r210 = r29.hashCode();
        goto L26
    L21:
        r28 = r27.hashCode();
        goto L22
    L17:
        r26 = FlashMode.m258hashCodeimpl(r25.m260unboximpl());
        goto L18
    L13:
        r24 = AwbMode.m143hashCodeimpl(r23.m146unboximpl());
        goto L14
    L9:
        r22 = AfMode.m123hashCodeimpl(r2.m127unboximpl());
        goto L10
    L5:
        r02 = AeMode.m113hashCodeimpl(r0.m116unboximpl());
        goto L6
    }

    public String toString() {
        return "State3A(aeMode=" + this.aeMode + ", afMode=" + this.afMode + ", awbMode=" + this.awbMode + ", flashMode=" + this.flashMode + ", aeRegions=" + this.aeRegions + ", afRegions=" + this.afRegions + ", awbRegions=" + this.awbRegions + ", aeLock=" + this.aeLock + ", afLock=" + this.afLock + ", awbLock=" + this.awbLock + ')';
    }
}
