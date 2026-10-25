package androidx.camera.camera2.pipe.graph;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.MeteringRectangle;
import androidx.camera.camera2.pipe.AeMode;
import androidx.camera.camera2.pipe.AfMode;
import androidx.camera.camera2.pipe.AwbMode;
import androidx.camera.camera2.pipe.FlashMode;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0097\u0001\u0010\u0018\u001a\u00020\u00152\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u001b\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a\u0012\u0004\u0012\u00020\u00010\u0019¢\u0006\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R$\u0010&\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u001e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u0006'"}, d2 = {"Landroidx/camera/camera2/pipe/graph/GraphState3A;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/camera2/pipe/AeMode;", "aeMode", "Landroidx/camera/camera2/pipe/AfMode;", "afMode", "Landroidx/camera/camera2/pipe/AwbMode;", "awbMode", "Landroidx/camera/camera2/pipe/FlashMode;", "flashMode", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/params/MeteringRectangle;", "aeRegions", "afRegions", "awbRegions", _UrlKt.FRAGMENT_ENCODE_SET, "aeLock", "afLock", "awbLock", _UrlKt.FRAGMENT_ENCODE_SET, "update-7jOEVJU", "(Landroidx/camera/camera2/pipe/AeMode;Landroidx/camera/camera2/pipe/AfMode;Landroidx/camera/camera2/pipe/AwbMode;Landroidx/camera/camera2/pipe/FlashMode;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", PluginsConstants.UPDATE, _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureRequest$Key;", "toCaptureRequestParametersMap", "()Ljava/util/Map;", "Lkotlinx/atomicfu/AtomicRef;", "Landroidx/camera/camera2/pipe/graph/State3A;", "_state", "Lkotlinx/atomicfu/AtomicRef;", "value", "getCurrent", "()Landroidx/camera/camera2/pipe/graph/State3A;", "setCurrent", "(Landroidx/camera/camera2/pipe/graph/State3A;)V", "current", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGraphState3A.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GraphState3A.kt\nandroidx/camera/camera2/pipe/graph/GraphState3A\n+ 2 AtomicFU.common.kt\nkotlinx/atomicfu/AtomicFU_commonKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,154:1\n164#2,3:155\n167#2:159\n1#3:158\n*S KotlinDebug\n*F\n+ 1 GraphState3A.kt\nandroidx/camera/camera2/pipe/graph/GraphState3A\n*L\n134#1:155,3\n134#1:159\n*E\n"})
/* loaded from: classes3.dex */
public final class GraphState3A {
    private final AtomicRef<State3A> _state;

    public GraphState3A() {
        this._state = AtomicFU.atomic(new State3A(null, null, null, null, null, null, null, null, null, null, 1023, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: update-7jOEVJU$default, reason: not valid java name */
    public static /* synthetic */ void m527update7jOEVJU$default(GraphState3A r1, AeMode r2, AfMode r3, AwbMode r4, FlashMode r5, List r6, List r7, List r8, Boolean r9, Boolean r10, Boolean r11, int r12, Object r13) {
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
        r1.m528update7jOEVJU(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
    }

    public final State3A getCurrent() {
        return this._state.getValue();
    }

    public final Map<CaptureRequest.Key<?>, Object> toCaptureRequestParametersMap() {
        return GraphState3AKt.toCaptureRequestParameterMap(getCurrent());
    }

    /* renamed from: update-7jOEVJU, reason: not valid java name */
    public final void m528update7jOEVJU(AeMode r14, AfMode r15, AwbMode r16, FlashMode r17, List<MeteringRectangle> r18, List<MeteringRectangle> r19, List<MeteringRectangle> r20, Boolean r21, Boolean r22, Boolean r23) {
        AtomicRef<State3A> r13 = this._state;
    L3:
        State3A r0 = r13.getValue();
        State3A r1 = r0;
        if (r14 != null) goto L6;
        AeMode r2 = r1.m535getAeModeO_cDUUs();
    L7:
        if (r15 != null) goto L9;
        AfMode r3 = r1.m536getAfMode32_E3BI();
    L10:
        if (r16 != null) goto L12;
        AwbMode r4 = r1.m537getAwbModeaLFtWSU();
    L13:
        if (r17 != null) goto L15;
        FlashMode r5 = r1.m538getFlashModecL19HE();
    L16:
        List<MeteringRectangle> r6 = null;
        if (r18 == null) goto L23;
        List<MeteringRectangle> r7 = r18;
        if (r7.isEmpty() == false) goto L21;
        r7 = null;
    L21:
        List<MeteringRectangle> r72 = r7;
        if (r72 == null) goto L23;
    L24:
        if (r19 == null) goto L30;
        List<MeteringRectangle> r8 = r19;
        if (r8.isEmpty() == false) goto L28;
        r8 = null;
    L28:
        List<MeteringRectangle> r82 = r8;
        if (r82 == null) goto L30;
    L31:
        if (r20 == null) goto L38;
        List<MeteringRectangle> r9 = r20;
        if (r9.isEmpty() == true) goto L36;
        r6 = r9;
    L36:
        List<MeteringRectangle> r62 = r6;
        if (r62 == null) goto L38;
    L39:
        if (r21 != null) goto L41;
        Boolean r92 = r1.getAeLock();
    L42:
        if (r22 != null) goto L44;
        Boolean r10 = r1.getAfLock();
    L45:
        if (r23 != null) goto L47;
        Boolean r11 = r1.getAwbLock();
        List<MeteringRectangle> r12 = r82;
        List<MeteringRectangle> r83 = r62;
        List<MeteringRectangle> r63 = r72;
        List<MeteringRectangle> r73 = r12;
    L49:
        if (r13.compareAndSet(r0, r1.m534copy7jOEVJU(r2, r3, r4, r5, r63, r73, r83, r92, r10, r11)) == false) goto L3;
        return;
    L47:
        List<MeteringRectangle> r112 = r82;
        r83 = r62;
        r63 = r72;
        r73 = r112;
        r11 = r23;
        goto L49
    L44:
        r10 = r22;
        goto L45
    L41:
        r92 = r21;
    L38:
        r62 = r1.getAwbRegions();
    L30:
        r82 = r1.getAfRegions();
    L23:
        r72 = r1.getAeRegions();
        goto L24
    L15:
        r5 = r17;
        goto L16
    L12:
        r4 = r16;
        goto L13
    L9:
        r3 = r15;
        goto L10
    L6:
        r2 = r14;
        goto L7
    }
}
