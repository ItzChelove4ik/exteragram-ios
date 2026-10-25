package androidx.camera.camera2.adapter;

import android.os.Looper;
import android.util.Log;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.pipe.CameraError;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.GraphState;
import androidx.camera.core.CameraState;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.LiveDataObservable;
import androidx.core.util.Consumer;
import androidx.lifecycle.MutableLiveData;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Buffer$$ExternalSyntheticBUOutline4;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 52\u00020\u0001:\u000265B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0003¢\u0006\u0004\b\t\u0010\nJ#\u0010\u000f\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\b¢\u0006\u0004\b\u0011\u0010\u0003J\u0015\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0014\u0010\nJ!\u0010\u0019\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR \u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001c8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R \u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\u0018\u0010'\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010)\u001a\u00020\u000b8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u0018\u0010+\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b+\u0010,R\u0016\u0010.\u001a\u00020-8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b.\u0010/R&\u00103\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"01\u0012\u0004\u0012\u000202008\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b3\u00104¨\u00067"}, d2 = {"Landroidx/camera/camera2/adapter/CameraStateAdapter;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/camera2/pipe/CameraGraph;", "cameraGraph", "Landroidx/camera/camera2/pipe/GraphState;", "graphState", _UrlKt.FRAGMENT_ENCODE_SET, "handleStateTransition", "(Landroidx/camera/camera2/pipe/CameraGraph;Landroidx/camera/camera2/pipe/GraphState;)V", "Landroidx/camera/core/impl/CameraInternal$State;", "internalState", "Landroidx/camera/core/CameraState$StateError;", "stateError", "postCameraState", "(Landroidx/camera/core/impl/CameraInternal$State;Landroidx/camera/core/CameraState$StateError;)V", "onRemoved", "onGraphUpdated", "(Landroidx/camera/camera2/pipe/CameraGraph;)V", "onGraphStateUpdated", "currentState", "Landroidx/camera/camera2/adapter/CameraStateAdapter$CombinedCameraState;", "calculateNextState$camera_camera2", "(Landroidx/camera/core/impl/CameraInternal$State;Landroidx/camera/camera2/pipe/GraphState;)Landroidx/camera/camera2/adapter/CameraStateAdapter$CombinedCameraState;", "calculateNextState", "lock", "Ljava/lang/Object;", "Landroidx/camera/core/impl/LiveDataObservable;", "cameraInternalState", "Landroidx/camera/core/impl/LiveDataObservable;", "getCameraInternalState$camera_camera2", "()Landroidx/camera/core/impl/LiveDataObservable;", "Landroidx/lifecycle/MutableLiveData;", "Landroidx/camera/core/CameraState;", "cameraState", "Landroidx/lifecycle/MutableLiveData;", "getCameraState$camera_camera2", "()Landroidx/lifecycle/MutableLiveData;", "currentGraph", "Landroidx/camera/camera2/pipe/CameraGraph;", "currentCameraInternalState", "Landroidx/camera/core/impl/CameraInternal$State;", "currentCameraStateError", "Landroidx/camera/core/CameraState$StateError;", _UrlKt.FRAGMENT_ENCODE_SET, "isRemoved", "Z", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/core/util/Consumer;", "Ljava/util/concurrent/Executor;", "cameraStateListeners", "Ljava/util/Map;", "Companion", "CombinedCameraState", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraStateAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraStateAdapter.kt\nandroidx/camera/camera2/adapter/CameraStateAdapter\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,301:1\n85#2,4:302\n85#2,4:306\n119#2,4:310\n85#2,4:314\n85#2,4:318\n119#2,4:322\n85#2,4:326\n1#3:330\n1869#4,2:331\n*S KotlinDebug\n*F\n+ 1 CameraStateAdapter.kt\nandroidx/camera/camera2/adapter/CameraStateAdapter\n*L\n72#1:302,4\n85#1:306,4\n98#1:310,4\n102#1:314,4\n110#1:318,4\n116#1:322,4\n127#1:326,4\n142#1:331,2\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraStateAdapter {
    public static final Companion Companion = null;
    private final LiveDataObservable<CameraInternal.State> cameraInternalState;
    private final MutableLiveData<CameraState> cameraState;
    private final Map<Consumer<CameraState>, Executor> cameraStateListeners;
    private CameraInternal.State currentCameraInternalState;
    private CameraState.StateError currentCameraStateError;
    private CameraGraph currentGraph;
    private boolean isRemoved;
    private final Object lock;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0080\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Landroidx/camera/camera2/adapter/CameraStateAdapter$CombinedCameraState;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/CameraInternal$State;", "state", "Landroidx/camera/core/CameraState$StateError;", "error", "<init>", "(Landroidx/camera/core/impl/CameraInternal$State;Landroidx/camera/core/CameraState$StateError;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Landroidx/camera/core/impl/CameraInternal$State;", "getState", "()Landroidx/camera/core/impl/CameraInternal$State;", "Landroidx/camera/core/CameraState$StateError;", "getError", "()Landroidx/camera/core/CameraState$StateError;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class CombinedCameraState {
        private final CameraState.StateError error;
        private final CameraInternal.State state;

        public CombinedCameraState(CameraInternal.State r1, CameraState.StateError r2) {
            this.state = r1;
            this.error = r2;
        }

        public /* synthetic */ CombinedCameraState(CameraInternal.State r1, CameraState.StateError r2, int r3, DefaultConstructorMarker r4) {
            if ((r3 & 2) == 0) goto L5;
            r2 = null;
        L5:
            this(r1, r2);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof CombinedCameraState) == true) goto L8;
            return false;
        L8:
            CombinedCameraState r52 = (CombinedCameraState) r5;
            if (this.state == r52.state) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.error, r52.error) == true) goto L14;
            return false;
        L14:
            return true;
        }

        public final CameraState.StateError getError() {
            return this.error;
        }

        public final CameraInternal.State getState() {
            return this.state;
        }

        public int hashCode() {
            int r0 = this.state.hashCode() * 31;
            CameraState.StateError r1 = this.error;
            if (r1 != null) goto L5;
            int r12 = 0;
        L7:
            return r0 + r12;
        L5:
            r12 = r1.hashCode();
            goto L7
        }

        public String toString() {
            return "CombinedCameraState(state=" + this.state + ", error=" + this.error + ')';
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\t\u001a\u00020\n*\u00020\u000bH\u0000¢\u0006\u0002\b\fJ\u0017\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0012\u001a\u00020\u0013*\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u0015H\u0000¢\u0006\u0002\b\u0017¨\u0006\u0018"}, d2 = {"Landroidx/camera/camera2/adapter/CameraStateAdapter$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "toCameraStateError", "Landroidx/camera/core/CameraState$StateError;", "Landroidx/camera/camera2/pipe/CameraError;", "toCameraStateError-90vkdD0$camera_camera2", "(I)Landroidx/camera/core/CameraState$StateError;", "toCameraState", "Landroidx/camera/core/CameraState$Type;", "Landroidx/camera/core/impl/CameraInternal$State;", "toCameraState$camera_camera2", "isRecoverableError", _UrlKt.FRAGMENT_ENCODE_SET, "cameraError", "isRecoverableError-90vkdD0$camera_camera2", "(I)Z", "setOrPostValue", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/lifecycle/MutableLiveData;", "Landroidx/camera/core/CameraState;", "cameraState", "setOrPostValue$camera_camera2", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {

        @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

            static {
                int[] r0 = new int[CameraInternal.State.values().length];
                r0[CameraInternal.State.CLOSED.ordinal()] = 1;     // Catch: NoSuchFieldError -> L10
            L15:
                r0[CameraInternal.State.OPENING.ordinal()] = 2;     // Catch: NoSuchFieldError -> L11
            L23:
                r0[CameraInternal.State.OPEN.ordinal()] = 3;     // Catch: NoSuchFieldError -> L12
            L17:
                r0[CameraInternal.State.CLOSING.ordinal()] = 4;     // Catch: NoSuchFieldError -> L13
            L19:
                r0[CameraInternal.State.PENDING_OPEN.ordinal()] = 5;     // Catch: NoSuchFieldError -> L14
            L8:
                $EnumSwitchMapping$0 = r0;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        /* renamed from: isRecoverableError-90vkdD0$camera_camera2, reason: not valid java name */
        public final boolean m9isRecoverableError90vkdD0$camera_camera2(int r2) {
            CameraError.Companion r1 = CameraError.Companion;
            if (CameraError.m178equalsimpl0(r2, r1.m188getERROR_CAMERA_DISCONNECTEDv7Vf74A()) == false) goto L5;
            return true;
        L5:
            if (CameraError.m178equalsimpl0(r2, r1.m189getERROR_CAMERA_IN_USEv7Vf74A()) == false) goto L7;
            return true;
        L7:
            if (CameraError.m178equalsimpl0(r2, r1.m190getERROR_CAMERA_LIMIT_EXCEEDEDv7Vf74A()) == false) goto L9;
            return true;
        L9:
            if (CameraError.m178equalsimpl0(r2, r1.m186getERROR_CAMERA_DEVICEv7Vf74A()) == true) goto L17;
            return false;
        L17:
            return true;
        }

        public final void setOrPostValue$camera_camera2(MutableLiveData<CameraState> r2, CameraState r3) {
            if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper()) == false) goto L6;
            r2.setValue(r3);
            return;
        L6:
            r2.postValue(r3);
        }

        public final CameraState.Type toCameraState$camera_camera2(CameraInternal.State r2) {
            int r1 = WhenMappings.$EnumSwitchMapping$0[r2.ordinal()];
            if (r1 == 1) goto L23;
            if (r1 == 2) goto L21;
            if (r1 == 3) goto L19;
            if (r1 == 4) goto L17;
            if (r1 == 5) goto L13;
            Native$$ExternalSyntheticBUOutline5.m("Unexpected CameraInternal state: ", r2);
            return null;
        L13:
            return CameraState.Type.PENDING_OPEN;
        L17:
            return CameraState.Type.CLOSING;
        L19:
            return CameraState.Type.OPEN;
        L21:
            return CameraState.Type.OPENING;
        L23:
            return CameraState.Type.CLOSED;
        }

        /* renamed from: toCameraStateError-90vkdD0$camera_camera2, reason: not valid java name */
        public final CameraState.StateError m10toCameraStateError90vkdD0$camera_camera2(int r4) {
            CameraError.Companion r3 = CameraError.Companion;
            int r1 = 6;
            if (CameraError.m178equalsimpl0(r4, r3.m198getERROR_UNDETERMINEDv7Vf74A()) == true) goto L44;
            if (CameraError.m178equalsimpl0(r4, r3.m189getERROR_CAMERA_IN_USEv7Vf74A()) == false) goto L9;
        L7:
            r1 = 2;
            goto L44
        L9:
            if (CameraError.m178equalsimpl0(r4, r3.m190getERROR_CAMERA_LIMIT_EXCEEDEDv7Vf74A()) == false) goto L12;
            r1 = 1;
            goto L44
        L12:
            if (CameraError.m178equalsimpl0(r4, r3.m187getERROR_CAMERA_DISABLEDv7Vf74A()) == false) goto L15;
            r1 = 5;
            goto L44
        L15:
            if (CameraError.m178equalsimpl0(r4, r3.m186getERROR_CAMERA_DEVICEv7Vf74A()) == false) goto L18;
            r1 = 3;
            goto L44
        L18:
            if (CameraError.m178equalsimpl0(r4, r3.m193getERROR_CAMERA_SERVICEv7Vf74A()) == true) goto L44;
            if (CameraError.m178equalsimpl0(r4, r3.m188getERROR_CAMERA_DISCONNECTEDv7Vf74A()) == true) goto L7;
            if (CameraError.m178equalsimpl0(r4, r3.m196getERROR_ILLEGAL_ARGUMENT_EXCEPTIONv7Vf74A()) == true) goto L44;
            if (CameraError.m178equalsimpl0(r4, r3.m197getERROR_SECURITY_EXCEPTIONv7Vf74A()) == true) goto L44;
            if (CameraError.m178equalsimpl0(r4, r3.m195getERROR_GRAPH_CONFIGv7Vf74A()) == false) goto L33;
            r1 = 4;
            goto L44
        L33:
            if (CameraError.m178equalsimpl0(r4, r3.m194getERROR_DO_NOT_DISTURB_ENABLEDv7Vf74A()) == false) goto L36;
            r1 = 7;
            goto L44
        L36:
            if (CameraError.m178equalsimpl0(r4, r3.m199getERROR_UNKNOWN_EXCEPTIONv7Vf74A()) == true) goto L44;
            if (CameraError.m178equalsimpl0(r4, r3.m191getERROR_CAMERA_OPENERv7Vf74A()) == true) goto L44;
            if (CameraError.m178equalsimpl0(r4, r3.m192getERROR_CAMERA_OPEN_TIMEOUTv7Vf74A()) == true) goto L44;
            Buffer$$ExternalSyntheticBUOutline4.m("Unexpected CameraError: ", CameraError.m181toStringimpl(r4));
            return null;
        L44:
            return CameraState.StateError.create(r1);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[CameraInternal.State.values().length];
            r0[CameraInternal.State.CLOSED.ordinal()] = 1;     // Catch: NoSuchFieldError -> L10
        L15:
            r0[CameraInternal.State.OPENING.ordinal()] = 2;     // Catch: NoSuchFieldError -> L11
        L23:
            r0[CameraInternal.State.OPEN.ordinal()] = 3;     // Catch: NoSuchFieldError -> L12
        L17:
            r0[CameraInternal.State.CLOSING.ordinal()] = 4;     // Catch: NoSuchFieldError -> L13
        L19:
            r0[CameraInternal.State.PENDING_OPEN.ordinal()] = 5;     // Catch: NoSuchFieldError -> L14
        L8:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public static /* synthetic */ void $r8$lambda$6FOKxeEC7IWChTAzIfXreZ8PTVY(Consumer r0, CameraState r1) {
        postCameraState$lambda$1$0(r0, r1);
    }

    static {
        Companion = new Companion(null);
    }

    public CameraStateAdapter() {
        this.lock = new Object();
        this.cameraInternalState = new LiveDataObservable();
        this.cameraState = new MutableLiveData();
        CameraInternal.State r0 = CameraInternal.State.CLOSED;
        this.currentCameraInternalState = r0;
        this.cameraStateListeners = new LinkedHashMap();
        postCameraState$default(this, r0, null, 2, null);
    }

    public static final /* synthetic */ CameraInternal.State access$getCurrentCameraInternalState$p(CameraStateAdapter r0) {
        return r0.currentCameraInternalState;
    }

    public static final /* synthetic */ CameraGraph access$getCurrentGraph$p(CameraStateAdapter r0) {
        return r0.currentGraph;
    }

    private final void handleStateTransition(CameraGraph r3, GraphState r4) {
        if (Intrinsics.areEqual(r3, this.currentGraph) == true) goto L8;
        Camera2Logger r2 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L19;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Ignored stale transition " + r4 + " for " + r3);
        return;
    L19:
        return;
    L8:
        CombinedCameraState r32 = calculateNextState$camera_camera2(this.currentCameraInternalState, r4);
        if (r32 != null) goto L14;
        Camera2Logger r33 = Camera2Logger.INSTANCE;
        if (Logger.isWarnEnabled("CXCP") == false) goto L20;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Impermissible state transition: current camera internal state: " + access$getCurrentCameraInternalState$p(this) + ", received graph state: " + r4);
        return;
    L20:
        return;
    L14:
        this.currentCameraInternalState = r32.getState();
        this.currentCameraStateError = r32.getError();
        Camera2Logger r42 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L17;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Updated current camera internal state to " + r32);
    L17:
        postCameraState(this.currentCameraInternalState, this.currentCameraStateError);
    }

    private final void postCameraState(CameraInternal.State r3, CameraState.StateError r4) {
        this.cameraInternalState.postValue(r3);
        Companion r0 = Companion;
        CameraState r32 = CameraState.create(r0.toCameraState$camera_camera2(r3), r4);
        r0.setOrPostValue$camera_camera2(this.cameraState, r32);
        Object r42 = this.lock;
        monitor-enter(r42);
        List r2 = CollectionsKt.toList(this.cameraStateListeners.entrySet());     // Catch: Throwable -> L11
        monitor-exit(r42);
        Iterator r22 = r2.iterator();
    L8:
        if (r22.hasNext() == false) goto L10;
        Map.Entry r43 = (Map.Entry) r22.next();
        Consumer r02 = (Consumer) r43.getKey();
        ((Executor) r43.getValue()).execute(new CameraStateAdapter$$ExternalSyntheticLambda0(r02, r32));
        goto L8
    L10:
        return;
    L11:
        th = move-exception;
        throw th;
    }

    public static /* synthetic */ void postCameraState$default(CameraStateAdapter r0, CameraInternal.State r1, CameraState.StateError r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = null;
    L5:
        r0.postCameraState(r1, r2);
    }

    private static final void postCameraState$lambda$1$0(Consumer r0, CameraState r1) {
        r0.accept(r1);
    }

    public final CombinedCameraState calculateNextState$camera_camera2(CameraInternal.State r3, GraphState r4) {
        int r2 = WhenMappings.$EnumSwitchMapping$0[r3.ordinal()];
        if (r2 == 1) goto L85;
        if (r2 == 2) goto L60;
        if (r2 == 3) goto L43;
        if (r2 == 4) goto L30;
        if (r2 == 5) goto L13;
        return null;
    L13:
        if (Intrinsics.areEqual(r4, GraphState.GraphStateStarting.INSTANCE) == false) goto L17;
        return new CombinedCameraState(CameraInternal.State.OPENING, null, 2, null);
    L17:
        if (Intrinsics.areEqual(r4, GraphState.GraphStateStarted.INSTANCE) == false) goto L21;
        return new CombinedCameraState(CameraInternal.State.OPEN, null, 2, null);
    L21:
        if ((r4 instanceof GraphState.GraphStateError) == false) goto L28;
        Companion r22 = Companion;
        GraphState.GraphStateError r42 = (GraphState.GraphStateError) r4;
        if (r22.m9isRecoverableError90vkdD0$camera_camera2(r42.m274getCameraErrorv7Vf74A()) == false) goto L27;
        return new CombinedCameraState(CameraInternal.State.PENDING_OPEN, r22.m10toCameraStateError90vkdD0$camera_camera2(r42.m274getCameraErrorv7Vf74A()));
    L27:
        return new CombinedCameraState(CameraInternal.State.CLOSED, r22.m10toCameraStateError90vkdD0$camera_camera2(r42.m274getCameraErrorv7Vf74A()));
    L28:
        return null;
    L30:
        if (Intrinsics.areEqual(r4, GraphState.GraphStateStopped.INSTANCE) == false) goto L34;
        return new CombinedCameraState(CameraInternal.State.CLOSED, null, 2, null);
    L34:
        if (Intrinsics.areEqual(r4, GraphState.GraphStateStarting.INSTANCE) == false) goto L38;
        return new CombinedCameraState(CameraInternal.State.OPENING, null, 2, null);
    L38:
        if ((r4 instanceof GraphState.GraphStateError) == true) goto L40;
        return null;
    L40:
        return new CombinedCameraState(CameraInternal.State.CLOSING, Companion.m10toCameraStateError90vkdD0$camera_camera2(((GraphState.GraphStateError) r4).m274getCameraErrorv7Vf74A()));
    L43:
        if (Intrinsics.areEqual(r4, GraphState.GraphStateStopping.INSTANCE) == false) goto L47;
        return new CombinedCameraState(CameraInternal.State.CLOSING, null, 2, null);
    L47:
        if (Intrinsics.areEqual(r4, GraphState.GraphStateStopped.INSTANCE) == false) goto L51;
        return new CombinedCameraState(CameraInternal.State.CLOSED, null, 2, null);
    L51:
        if ((r4 instanceof GraphState.GraphStateError) == false) goto L58;
        Companion r23 = Companion;
        GraphState.GraphStateError r43 = (GraphState.GraphStateError) r4;
        if (r23.m9isRecoverableError90vkdD0$camera_camera2(r43.m274getCameraErrorv7Vf74A()) == false) goto L57;
        return new CombinedCameraState(CameraInternal.State.PENDING_OPEN, r23.m10toCameraStateError90vkdD0$camera_camera2(r43.m274getCameraErrorv7Vf74A()));
    L57:
        return new CombinedCameraState(CameraInternal.State.CLOSED, r23.m10toCameraStateError90vkdD0$camera_camera2(r43.m274getCameraErrorv7Vf74A()));
    L58:
        return null;
    L60:
        if (Intrinsics.areEqual(r4, GraphState.GraphStateStarted.INSTANCE) == false) goto L64;
        return new CombinedCameraState(CameraInternal.State.OPEN, null, 2, null);
    L64:
        if ((r4 instanceof GraphState.GraphStateError) == false) goto L76;
        GraphState.GraphStateError r44 = (GraphState.GraphStateError) r4;
        if (r44.getWillAttemptRetry() == true) goto L68;
        Companion r24 = Companion;
        if (r24.m9isRecoverableError90vkdD0$camera_camera2(r44.m274getCameraErrorv7Vf74A()) == false) goto L74;
        return new CombinedCameraState(CameraInternal.State.PENDING_OPEN, r24.m10toCameraStateError90vkdD0$camera_camera2(r44.m274getCameraErrorv7Vf74A()));
    L74:
        return new CombinedCameraState(CameraInternal.State.CLOSING, r24.m10toCameraStateError90vkdD0$camera_camera2(r44.m274getCameraErrorv7Vf74A()));
    L68:
        return new CombinedCameraState(CameraInternal.State.OPENING, Companion.m10toCameraStateError90vkdD0$camera_camera2(r44.m274getCameraErrorv7Vf74A()));
    L76:
        if (Intrinsics.areEqual(r4, GraphState.GraphStateStopping.INSTANCE) == false) goto L80;
        return new CombinedCameraState(CameraInternal.State.CLOSING, null, 2, null);
    L80:
        if (Intrinsics.areEqual(r4, GraphState.GraphStateStopped.INSTANCE) == true) goto L82;
        return null;
    L82:
        return new CombinedCameraState(CameraInternal.State.CLOSED, null, 2, null);
    L85:
        if (Intrinsics.areEqual(r4, GraphState.GraphStateStarting.INSTANCE) == false) goto L89;
        return new CombinedCameraState(CameraInternal.State.OPENING, null, 2, null);
    L89:
        if (Intrinsics.areEqual(r4, GraphState.GraphStateStarted.INSTANCE) == true) goto L91;
        return null;
    L91:
        return new CombinedCameraState(CameraInternal.State.OPEN, null, 2, null);
    }

    public final MutableLiveData<CameraState> getCameraState$camera_camera2() {
        return this.cameraState;
    }

    public final void onGraphStateUpdated(CameraGraph r5, GraphState r6) {
        Object r1 = this.lock;
        monitor-enter(r1);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (this.isRemoved == false) goto L14;
        Camera2Logger r4 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L10
        if (Logger.isWarnEnabled("CXCP") == false) goto L12;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Ignoring graph state update " + r6 + " on removed camera.");     // Catch: Throwable -> L10
    L12:
        monitor-exit(r1);
        return;
    L14:
        Camera2Logger r0 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L10
        if (Logger.isDebugEnabled("CXCP") == false) goto L17;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), r5 + " state updated to " + r6);     // Catch: Throwable -> L10
    L17:
        handleStateTransition(r5, r6);     // Catch: Throwable -> L10
        Unit r42 = Unit.INSTANCE;     // Catch: Throwable -> L10
        monitor-exit(r1);
    }

    public final void onGraphUpdated(CameraGraph r6) {
        Object r1 = this.lock;
        monitor-enter(r1);
        Camera2Logger r2 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L7
        if (Logger.isDebugEnabled("CXCP") == false) goto L9;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Camera graph updated from " + access$getCurrentGraph$p(this) + " to " + r6);     // Catch: Throwable -> L7
    L9:
        CameraInternal.State r0 = this.currentCameraInternalState;     // Catch: Throwable -> L7
        CameraInternal.State r22 = CameraInternal.State.CLOSED;     // Catch: Throwable -> L7
        if (r0 == r22) goto L12;
        postCameraState$default(this, CameraInternal.State.CLOSING, null, 2, null);     // Catch: Throwable -> L7
        postCameraState$default(this, r22, null, 2, null);     // Catch: Throwable -> L7
    L12:
        this.currentGraph = r6;     // Catch: Throwable -> L7
        this.currentCameraInternalState = r22;     // Catch: Throwable -> L7
        Unit r5 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r1);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final void onRemoved() {
        CameraState.StateError r0 = CameraState.StateError.create(8);
        Object r1 = this.lock;
        monitor-enter(r1);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (this.isRemoved == false) goto L8;
        monitor-exit(r1);
        return;
    L8:
        Camera2Logger r2 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L11
        if (Logger.isDebugEnabled("CXCP") == false) goto L13;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Camera is removed, forcing state to CLOSED.");     // Catch: Throwable -> L11
    L13:
        this.isRemoved = true;     // Catch: Throwable -> L11
        CameraInternal.State r22 = CameraInternal.State.CLOSED;     // Catch: Throwable -> L11
        this.currentCameraInternalState = r22;     // Catch: Throwable -> L11
        this.currentCameraStateError = r0;     // Catch: Throwable -> L11
        postCameraState(r22, r0);     // Catch: Throwable -> L11
        this.currentGraph = null;     // Catch: Throwable -> L11
        Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L11
        monitor-exit(r1);
    }
}
