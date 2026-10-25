package androidx.camera.camera2.pipe;

import android.hardware.camera2.CameraAccessException;
import android.os.Build;
import androidx.camera.camera2.pipe.core.Log;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.voip.Instance;

@kotlin.Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u000e\u0010\u0005J\u001a\u0010\u0013\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u0018"}, d2 = {"Landroidx/camera/camera2/pipe/CameraError;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(I)I", _UrlKt.FRAGMENT_ENCODE_SET, "isDisconnected-impl", "(I)Z", "isDisconnected", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(I)Ljava/lang/String;", "toString", "hashCode-impl", "hashCode", "other", "equals-impl", "(ILjava/lang/Object;)Z", "equals", "I", "getValue", "()I", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public final class CameraError {
    public static final Companion Companion = null;
    private static final int ERROR_CAMERA_DEVICE = 0;
    private static final int ERROR_CAMERA_DISABLED = 0;
    private static final int ERROR_CAMERA_DISCONNECTED = 0;
    private static final int ERROR_CAMERA_IN_USE = 0;
    private static final int ERROR_CAMERA_LIMIT_EXCEEDED = 0;
    private static final int ERROR_CAMERA_OPENER = 0;
    private static final int ERROR_CAMERA_OPEN_TIMEOUT = 0;
    private static final int ERROR_CAMERA_SERVICE = 0;
    private static final int ERROR_DO_NOT_DISTURB_ENABLED = 0;
    private static final int ERROR_GRAPH_CONFIG = 0;
    private static final int ERROR_ILLEGAL_ARGUMENT_EXCEPTION = 0;
    private static final int ERROR_SECURITY_EXCEPTION = 0;
    private static final int ERROR_UNDETERMINED = 0;
    private static final int ERROR_UNKNOWN_EXCEPTION = 0;
    private final int value;

    @kotlin.Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020%H\u0000¢\u0006\u0004\b&\u0010'J\u0017\u0010#\u001a\u00020\u00052\u0006\u0010(\u001a\u00020)H\u0000¢\u0006\u0004\b&\u0010*J\u0017\u0010#\u001a\u00020\u00052\u0006\u0010+\u001a\u00020,H\u0000¢\u0006\u0004\b&\u0010-J\u0015\u0010.\u001a\u00020/2\u0006\u0010$\u001a\u00020%H\u0000¢\u0006\u0002\b0J\u0010\u00101\u001a\u00020/2\u0006\u0010$\u001a\u00020%H\u0002R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0013\u0010\r\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0013\u0010\u000f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0010\u0010\u0007R\u0013\u0010\u0011\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0012\u0010\u0007R\u0013\u0010\u0013\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0014\u0010\u0007R\u0013\u0010\u0015\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0016\u0010\u0007R\u0013\u0010\u0017\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0018\u0010\u0007R\u0013\u0010\u0019\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001a\u0010\u0007R\u0013\u0010\u001b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001c\u0010\u0007R\u0013\u0010\u001d\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001e\u0010\u0007R\u0013\u0010\u001f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b \u0010\u0007R\u0013\u0010!\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\"\u0010\u0007¨\u00062"}, d2 = {"Landroidx/camera/camera2/pipe/CameraError$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "ERROR_UNDETERMINED", "Landroidx/camera/camera2/pipe/CameraError;", "getERROR_UNDETERMINED-v7Vf74A", "()I", "I", "ERROR_CAMERA_IN_USE", "getERROR_CAMERA_IN_USE-v7Vf74A", "ERROR_CAMERA_LIMIT_EXCEEDED", "getERROR_CAMERA_LIMIT_EXCEEDED-v7Vf74A", "ERROR_CAMERA_DISABLED", "getERROR_CAMERA_DISABLED-v7Vf74A", "ERROR_CAMERA_DEVICE", "getERROR_CAMERA_DEVICE-v7Vf74A", "ERROR_CAMERA_SERVICE", "getERROR_CAMERA_SERVICE-v7Vf74A", "ERROR_CAMERA_DISCONNECTED", "getERROR_CAMERA_DISCONNECTED-v7Vf74A", "ERROR_ILLEGAL_ARGUMENT_EXCEPTION", "getERROR_ILLEGAL_ARGUMENT_EXCEPTION-v7Vf74A", "ERROR_SECURITY_EXCEPTION", "getERROR_SECURITY_EXCEPTION-v7Vf74A", "ERROR_GRAPH_CONFIG", "getERROR_GRAPH_CONFIG-v7Vf74A", "ERROR_DO_NOT_DISTURB_ENABLED", "getERROR_DO_NOT_DISTURB_ENABLED-v7Vf74A", "ERROR_UNKNOWN_EXCEPTION", "getERROR_UNKNOWN_EXCEPTION-v7Vf74A", "ERROR_CAMERA_OPENER", "getERROR_CAMERA_OPENER-v7Vf74A", "ERROR_CAMERA_OPEN_TIMEOUT", "getERROR_CAMERA_OPEN_TIMEOUT-v7Vf74A", "from", "throwable", _UrlKt.FRAGMENT_ENCODE_SET, "from-PVuDhNw$camera_camera2_pipe", "(Ljava/lang/Throwable;)I", "exception", "Landroid/hardware/camera2/CameraAccessException;", "(Landroid/hardware/camera2/CameraAccessException;)I", "stateCallbackError", _UrlKt.FRAGMENT_ENCODE_SET, "(I)I", "shouldHandleDoNotDisturbException", _UrlKt.FRAGMENT_ENCODE_SET, "shouldHandleDoNotDisturbException$camera_camera2_pipe", "isDoNotDisturbException", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCameraError.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraError.kt\nandroidx/camera/camera2/pipe/CameraError$Companion\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,228:1\n71#2,2:229\n71#2,2:231\n1#3:233\n*S KotlinDebug\n*F\n+ 1 CameraError.kt\nandroidx/camera/camera2/pipe/CameraError$Companion\n*L\n140#1:229,2\n154#1:231,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private final boolean isDoNotDisturbException(Throwable r2) {
            if ((r2 instanceof RuntimeException) == true) goto L5;
            return false;
        L5:
            StackTraceElement[] r1 = ((RuntimeException) r2).getStackTrace();
            if (r1.length != 0) goto L8;
            boolean r22 = true;
        L9:
            if (r22 == true) goto L11;
            String r12 = r1[0].getMethodName();
        L13:
            return Intrinsics.areEqual(r12, "_enableShutterSound");
        L11:
            r12 = null;
            goto L13
        L8:
            r22 = false;
            goto L9
        }

        /* renamed from: from-PVuDhNw$camera_camera2_pipe, reason: not valid java name */
        public final int m183fromPVuDhNw$camera_camera2_pipe(int r2) {
            if (r2 == 1) goto L23;
            if (r2 == 2) goto L21;
            if (r2 == 3) goto L19;
            if (r2 == 4) goto L17;
            if (r2 == 5) goto L13;
            CharCodeKt$$ExternalSyntheticBUOutline0.m("Unexpected StateCallback error code: ", r2);
            return 0;
        L13:
            return m193getERROR_CAMERA_SERVICEv7Vf74A();
        L17:
            return m186getERROR_CAMERA_DEVICEv7Vf74A();
        L19:
            return m187getERROR_CAMERA_DISABLEDv7Vf74A();
        L21:
            return m190getERROR_CAMERA_LIMIT_EXCEEDEDv7Vf74A();
        L23:
            return m189getERROR_CAMERA_IN_USEv7Vf74A();
        }

        /* renamed from: from-PVuDhNw$camera_camera2_pipe, reason: not valid java name */
        public final int m184fromPVuDhNw$camera_camera2_pipe(CameraAccessException r3) {
            int r0 = r3.getReason();
            if (r0 == 1) goto L26;
            if (r0 == 2) goto L24;
            if (r0 == 3) goto L22;
            if (r0 == 4) goto L20;
            if (r0 == 5) goto L18;
            if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L16;
            android.util.Log.w("CXCP", "Unexpected CameraAccessException: " + r3);
        L16:
            return m199getERROR_UNKNOWN_EXCEPTIONv7Vf74A();
        L18:
            return m190getERROR_CAMERA_LIMIT_EXCEEDEDv7Vf74A();
        L20:
            return m189getERROR_CAMERA_IN_USEv7Vf74A();
        L22:
            return m198getERROR_UNDETERMINEDv7Vf74A();
        L24:
            return m188getERROR_CAMERA_DISCONNECTEDv7Vf74A();
        L26:
            return m187getERROR_CAMERA_DISABLEDv7Vf74A();
        }

        /* renamed from: from-PVuDhNw$camera_camera2_pipe, reason: not valid java name */
        public final int m185fromPVuDhNw$camera_camera2_pipe(Throwable r3) {
            if ((r3 instanceof CameraAccessException) == false) goto L7;
            return m184fromPVuDhNw$camera_camera2_pipe((CameraAccessException) r3);
        L7:
            if ((r3 instanceof IllegalArgumentException) == false) goto L11;
            return m196getERROR_ILLEGAL_ARGUMENT_EXCEPTIONv7Vf74A();
        L11:
            if ((r3 instanceof SecurityException) == false) goto L15;
            return m197getERROR_SECURITY_EXCEPTIONv7Vf74A();
        L15:
            if (shouldHandleDoNotDisturbException$camera_camera2_pipe(r3) == false) goto L19;
            return m194getERROR_DO_NOT_DISTURB_ENABLEDv7Vf74A();
        L19:
            if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L22;
            android.util.Log.w("CXCP", "Unexpected throwable: " + r3);
        L22:
            return m199getERROR_UNKNOWN_EXCEPTIONv7Vf74A();
        }

        /* renamed from: getERROR_CAMERA_DEVICE-v7Vf74A, reason: not valid java name */
        public final int m186getERROR_CAMERA_DEVICEv7Vf74A() {
            return CameraError.access$getERROR_CAMERA_DEVICE$cp();
        }

        /* renamed from: getERROR_CAMERA_DISABLED-v7Vf74A, reason: not valid java name */
        public final int m187getERROR_CAMERA_DISABLEDv7Vf74A() {
            return CameraError.access$getERROR_CAMERA_DISABLED$cp();
        }

        /* renamed from: getERROR_CAMERA_DISCONNECTED-v7Vf74A, reason: not valid java name */
        public final int m188getERROR_CAMERA_DISCONNECTEDv7Vf74A() {
            return CameraError.access$getERROR_CAMERA_DISCONNECTED$cp();
        }

        /* renamed from: getERROR_CAMERA_IN_USE-v7Vf74A, reason: not valid java name */
        public final int m189getERROR_CAMERA_IN_USEv7Vf74A() {
            return CameraError.access$getERROR_CAMERA_IN_USE$cp();
        }

        /* renamed from: getERROR_CAMERA_LIMIT_EXCEEDED-v7Vf74A, reason: not valid java name */
        public final int m190getERROR_CAMERA_LIMIT_EXCEEDEDv7Vf74A() {
            return CameraError.access$getERROR_CAMERA_LIMIT_EXCEEDED$cp();
        }

        /* renamed from: getERROR_CAMERA_OPENER-v7Vf74A, reason: not valid java name */
        public final int m191getERROR_CAMERA_OPENERv7Vf74A() {
            return CameraError.access$getERROR_CAMERA_OPENER$cp();
        }

        /* renamed from: getERROR_CAMERA_OPEN_TIMEOUT-v7Vf74A, reason: not valid java name */
        public final int m192getERROR_CAMERA_OPEN_TIMEOUTv7Vf74A() {
            return CameraError.access$getERROR_CAMERA_OPEN_TIMEOUT$cp();
        }

        /* renamed from: getERROR_CAMERA_SERVICE-v7Vf74A, reason: not valid java name */
        public final int m193getERROR_CAMERA_SERVICEv7Vf74A() {
            return CameraError.access$getERROR_CAMERA_SERVICE$cp();
        }

        /* renamed from: getERROR_DO_NOT_DISTURB_ENABLED-v7Vf74A, reason: not valid java name */
        public final int m194getERROR_DO_NOT_DISTURB_ENABLEDv7Vf74A() {
            return CameraError.access$getERROR_DO_NOT_DISTURB_ENABLED$cp();
        }

        /* renamed from: getERROR_GRAPH_CONFIG-v7Vf74A, reason: not valid java name */
        public final int m195getERROR_GRAPH_CONFIGv7Vf74A() {
            return CameraError.access$getERROR_GRAPH_CONFIG$cp();
        }

        /* renamed from: getERROR_ILLEGAL_ARGUMENT_EXCEPTION-v7Vf74A, reason: not valid java name */
        public final int m196getERROR_ILLEGAL_ARGUMENT_EXCEPTIONv7Vf74A() {
            return CameraError.access$getERROR_ILLEGAL_ARGUMENT_EXCEPTION$cp();
        }

        /* renamed from: getERROR_SECURITY_EXCEPTION-v7Vf74A, reason: not valid java name */
        public final int m197getERROR_SECURITY_EXCEPTIONv7Vf74A() {
            return CameraError.access$getERROR_SECURITY_EXCEPTION$cp();
        }

        /* renamed from: getERROR_UNDETERMINED-v7Vf74A, reason: not valid java name */
        public final int m198getERROR_UNDETERMINEDv7Vf74A() {
            return CameraError.access$getERROR_UNDETERMINED$cp();
        }

        /* renamed from: getERROR_UNKNOWN_EXCEPTION-v7Vf74A, reason: not valid java name */
        public final int m199getERROR_UNKNOWN_EXCEPTIONv7Vf74A() {
            return CameraError.access$getERROR_UNKNOWN_EXCEPTION$cp();
        }

        public final boolean shouldHandleDoNotDisturbException$camera_camera2_pipe(Throwable r3) {
            if (Build.VERSION.SDK_INT == 28) goto L5;
            return false;
        L5:
            if (isDoNotDisturbException(r3) == false) goto L10;
            return true;
        L10:
            return false;
        }
    }

    static {
        Companion = new Companion(null);
        ERROR_UNDETERMINED = m176constructorimpl(0);
        ERROR_CAMERA_IN_USE = m176constructorimpl(1);
        ERROR_CAMERA_LIMIT_EXCEEDED = m176constructorimpl(2);
        ERROR_CAMERA_DISABLED = m176constructorimpl(3);
        ERROR_CAMERA_DEVICE = m176constructorimpl(4);
        ERROR_CAMERA_SERVICE = m176constructorimpl(5);
        ERROR_CAMERA_DISCONNECTED = m176constructorimpl(6);
        ERROR_ILLEGAL_ARGUMENT_EXCEPTION = m176constructorimpl(7);
        ERROR_SECURITY_EXCEPTION = m176constructorimpl(8);
        ERROR_GRAPH_CONFIG = m176constructorimpl(9);
        ERROR_DO_NOT_DISTURB_ENABLED = m176constructorimpl(10);
        ERROR_UNKNOWN_EXCEPTION = m176constructorimpl(11);
        ERROR_CAMERA_OPENER = m176constructorimpl(12);
        ERROR_CAMERA_OPEN_TIMEOUT = m176constructorimpl(13);
    }

    private /* synthetic */ CameraError(int r1) {
        this.value = r1;
    }

    public static final /* synthetic */ int access$getERROR_CAMERA_DEVICE$cp() {
        return ERROR_CAMERA_DEVICE;
    }

    public static final /* synthetic */ int access$getERROR_CAMERA_DISABLED$cp() {
        return ERROR_CAMERA_DISABLED;
    }

    public static final /* synthetic */ int access$getERROR_CAMERA_DISCONNECTED$cp() {
        return ERROR_CAMERA_DISCONNECTED;
    }

    public static final /* synthetic */ int access$getERROR_CAMERA_IN_USE$cp() {
        return ERROR_CAMERA_IN_USE;
    }

    public static final /* synthetic */ int access$getERROR_CAMERA_LIMIT_EXCEEDED$cp() {
        return ERROR_CAMERA_LIMIT_EXCEEDED;
    }

    public static final /* synthetic */ int access$getERROR_CAMERA_OPENER$cp() {
        return ERROR_CAMERA_OPENER;
    }

    public static final /* synthetic */ int access$getERROR_CAMERA_OPEN_TIMEOUT$cp() {
        return ERROR_CAMERA_OPEN_TIMEOUT;
    }

    public static final /* synthetic */ int access$getERROR_CAMERA_SERVICE$cp() {
        return ERROR_CAMERA_SERVICE;
    }

    public static final /* synthetic */ int access$getERROR_DO_NOT_DISTURB_ENABLED$cp() {
        return ERROR_DO_NOT_DISTURB_ENABLED;
    }

    public static final /* synthetic */ int access$getERROR_GRAPH_CONFIG$cp() {
        return ERROR_GRAPH_CONFIG;
    }

    public static final /* synthetic */ int access$getERROR_ILLEGAL_ARGUMENT_EXCEPTION$cp() {
        return ERROR_ILLEGAL_ARGUMENT_EXCEPTION;
    }

    public static final /* synthetic */ int access$getERROR_SECURITY_EXCEPTION$cp() {
        return ERROR_SECURITY_EXCEPTION;
    }

    public static final /* synthetic */ int access$getERROR_UNDETERMINED$cp() {
        return ERROR_UNDETERMINED;
    }

    public static final /* synthetic */ int access$getERROR_UNKNOWN_EXCEPTION$cp() {
        return ERROR_UNKNOWN_EXCEPTION;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ CameraError m175boximpl(int r1) {
        return new CameraError(r1);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    private static int m176constructorimpl(int r0) {
        return r0;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m177equalsimpl(int r2, Object r3) {
        if ((r3 instanceof CameraError) == true) goto L6;
        return false;
    L6:
        if (r2 == ((CameraError) r3).m182unboximpl()) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m178equalsimpl0(int r0, int r1) {
        if (r0 != r1) goto L5;
        return true;
    L5:
        return false;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m179hashCodeimpl(int r0) {
        return Integer.hashCode(r0);
    }

    /* renamed from: isDisconnected-impl, reason: not valid java name */
    public static final boolean m180isDisconnectedimpl(int r1) {
        if (m178equalsimpl0(r1, ERROR_CAMERA_DISCONNECTED) == false) goto L5;
        return true;
    L5:
        if (m178equalsimpl0(r1, ERROR_CAMERA_IN_USE) == false) goto L7;
        return true;
    L7:
        if (m178equalsimpl0(r1, ERROR_CAMERA_LIMIT_EXCEEDED) == true) goto L14;
        return false;
    L14:
        return true;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m181toStringimpl(int r2) {
        StringBuilder r0 = new StringBuilder("CameraError(");
        if (m178equalsimpl0(r2, ERROR_UNDETERMINED) == false) goto L6;
        String r22 = "ERROR_UNDETERMINED";
    L45:
        r0.append(r22);
        r0.append(')');
        return r0.toString();
    L6:
        if (m178equalsimpl0(r2, ERROR_CAMERA_IN_USE) == false) goto L9;
        r22 = "ERROR_CAMERA_IN_USE";
        goto L45
    L9:
        if (m178equalsimpl0(r2, ERROR_CAMERA_LIMIT_EXCEEDED) == false) goto L12;
        r22 = "ERROR_CAMERA_LIMIT_EXCEEDED";
        goto L45
    L12:
        if (m178equalsimpl0(r2, ERROR_CAMERA_DISABLED) == false) goto L15;
        r22 = "ERROR_CAMERA_DISABLED";
        goto L45
    L15:
        if (m178equalsimpl0(r2, ERROR_CAMERA_DEVICE) == false) goto L18;
        r22 = "ERROR_CAMERA_DEVICE";
        goto L45
    L18:
        if (m178equalsimpl0(r2, ERROR_CAMERA_SERVICE) == false) goto L21;
        r22 = "ERROR_CAMERA_SERVICE";
        goto L45
    L21:
        if (m178equalsimpl0(r2, ERROR_CAMERA_DISCONNECTED) == false) goto L24;
        r22 = "ERROR_CAMERA_DISCONNECTED";
        goto L45
    L24:
        if (m178equalsimpl0(r2, ERROR_ILLEGAL_ARGUMENT_EXCEPTION) == false) goto L27;
        r22 = "ERROR_ILLEGAL_ARGUMENT_EXCEPTION";
        goto L45
    L27:
        if (m178equalsimpl0(r2, ERROR_SECURITY_EXCEPTION) == false) goto L30;
        r22 = "ERROR_SECURITY_EXCEPTION";
        goto L45
    L30:
        if (m178equalsimpl0(r2, ERROR_GRAPH_CONFIG) == false) goto L33;
        r22 = "ERROR_GRAPH_CONFIG";
        goto L45
    L33:
        if (m178equalsimpl0(r2, ERROR_DO_NOT_DISTURB_ENABLED) == false) goto L36;
        r22 = "ERROR_DO_NOT_DISTURB_ENABLED";
        goto L45
    L36:
        if (m178equalsimpl0(r2, ERROR_UNKNOWN_EXCEPTION) == false) goto L39;
        r22 = "ERROR_UNKNOWN_EXCEPTION";
        goto L45
    L39:
        if (m178equalsimpl0(r2, ERROR_CAMERA_OPENER) == false) goto L42;
        r22 = "ERROR_CAMERA_OPENER";
        goto L45
    L42:
        if (m178equalsimpl0(r2, ERROR_CAMERA_OPEN_TIMEOUT) == false) goto L44;
        r22 = "ERROR_CAMERA_OPEN_TIMEOUT";
        goto L45
    L44:
        r22 = Instance.ERROR_UNKNOWN;
        goto L45
    }

    public boolean equals(Object r1) {
        return m177equalsimpl(this.value, r1);
    }

    public int hashCode() {
        return m179hashCodeimpl(this.value);
    }

    public String toString() {
        return m181toStringimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m182unboximpl() {
        return this.value;
    }
}
