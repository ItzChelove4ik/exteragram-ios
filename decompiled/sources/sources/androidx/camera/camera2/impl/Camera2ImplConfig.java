package androidx.camera.camera2.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import androidx.camera.camera2.interop.CaptureRequestOptions;
import androidx.camera.core.ExtendableBuilder;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001d\u001cB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u000e\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b\u0017\u0010\u0018R\u0011\u0010\u001b\u001a\u00020\u00018G¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001e"}, d2 = {"Landroidx/camera/camera2/impl/Camera2ImplConfig;", "Landroidx/camera/camera2/interop/CaptureRequestOptions;", "Landroidx/camera/core/impl/Config;", "config", "<init>", "(Landroidx/camera/core/impl/Config;)V", _UrlKt.FRAGMENT_ENCODE_SET, "valueIfMissing", "getCaptureRequestTemplate", "(I)I", _UrlKt.FRAGMENT_ENCODE_SET, "getStreamUseCase", "(Ljava/lang/Long;)Ljava/lang/Long;", "Landroid/hardware/camera2/CameraDevice$StateCallback;", "getDeviceStateCallback", "(Landroid/hardware/camera2/CameraDevice$StateCallback;)Landroid/hardware/camera2/CameraDevice$StateCallback;", "Landroid/hardware/camera2/CameraCaptureSession$StateCallback;", "getSessionStateCallback", "(Landroid/hardware/camera2/CameraCaptureSession$StateCallback;)Landroid/hardware/camera2/CameraCaptureSession$StateCallback;", "Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;", "getSessionCaptureCallback", "(Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;)Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;", _UrlKt.FRAGMENT_ENCODE_SET, "getPhysicalCameraId", "(Ljava/lang/String;)Ljava/lang/String;", "getCaptureRequestOptions", "()Landroidx/camera/camera2/interop/CaptureRequestOptions;", "captureRequestOptions", "Companion", "Builder", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Camera2ImplConfig extends CaptureRequestOptions {

    @JvmField
    public static final Config.Option<Object> CAPTURE_REQUEST_TAG_OPTION = null;
    public static final Companion Companion = null;

    @JvmField
    public static final Config.Option<CameraDevice.StateCallback> DEVICE_STATE_CALLBACK_OPTION = null;

    @JvmField
    public static final Config.Option<CameraCaptureSession.CaptureCallback> SESSION_CAPTURE_CALLBACK_OPTION = null;

    @JvmField
    public static final Config.Option<String> SESSION_PHYSICAL_CAMERA_ID_OPTION = null;

    @JvmField
    public static final Config.Option<CameraCaptureSession.StateCallback> SESSION_STATE_CALLBACK_OPTION = null;

    @JvmField
    public static final Config.Option<Long> STREAM_USE_CASE_OPTION = null;

    @JvmField
    public static final Config.Option<Long> STREAM_USE_HINT_OPTION = null;

    @JvmField
    public static final Config.Option<Integer> TEMPLATE_TYPE_OPTION = null;

    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J)\u0010\f\u001a\u00020\u0000\"\u0004\b\u0000\u0010\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\t2\u0006\u0010\u000b\u001a\u00028\u0000¢\u0006\u0004\b\f\u0010\rJ-\u0010\u0013\u001a\u00020\u00002\u0016\u0010\u0010\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0017\u001a\u00020\u00002\u0010\u0010\u0016\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\u0015¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001d\u0010\u001eR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!¨\u0006\""}, d2 = {"Landroidx/camera/camera2/impl/Camera2ImplConfig$Builder;", "Landroidx/camera/core/ExtendableBuilder;", "Landroidx/camera/camera2/impl/Camera2ImplConfig;", "<init>", "()V", "Landroidx/camera/core/impl/MutableConfig;", "getMutableConfig", "()Landroidx/camera/core/impl/MutableConfig;", "ValueT", "Landroid/hardware/camera2/CaptureRequest$Key;", PluginsConstants.Settings.KEY, "value", "setCaptureRequestOption", "(Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)Landroidx/camera/camera2/impl/Camera2ImplConfig$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "values", "Landroidx/camera/core/impl/Config$OptionPriority;", PluginsConstants.MenuItemProperties.PRIORITY, "addAllCaptureRequestOptionsWithPriority", "(Ljava/util/Map;Landroidx/camera/core/impl/Config$OptionPriority;)Landroidx/camera/camera2/impl/Camera2ImplConfig$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "keys", "removeCaptureRequestOptions", "(Ljava/util/List;)Landroidx/camera/camera2/impl/Camera2ImplConfig$Builder;", "Landroidx/camera/core/impl/Config;", "config", "insertAllOptions", "(Landroidx/camera/core/impl/Config;)Landroidx/camera/camera2/impl/Camera2ImplConfig$Builder;", "build", "()Landroidx/camera/camera2/impl/Camera2ImplConfig;", "Landroidx/camera/core/impl/MutableOptionsBundle;", "mutableOptionsBundle", "Landroidx/camera/core/impl/MutableOptionsBundle;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCamera2ImplConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2ImplConfig.kt\nandroidx/camera/camera2/impl/Camera2ImplConfig$Builder\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,325:1\n216#2,2:326\n1869#3,2:328\n*S KotlinDebug\n*F\n+ 1 Camera2ImplConfig.kt\nandroidx/camera/camera2/impl/Camera2ImplConfig$Builder\n*L\n203#1:326,2\n214#1:328,2\n*E\n"})
    public static final class Builder implements ExtendableBuilder<Camera2ImplConfig> {
        private final MutableOptionsBundle mutableOptionsBundle;

        public Builder() {
            this.mutableOptionsBundle = MutableOptionsBundle.create();
        }

        public final Builder addAllCaptureRequestOptionsWithPriority(Map<CaptureRequest.Key<?>, ? extends Object> r4, Config.OptionPriority r5) {
            Iterator<Map.Entry<CaptureRequest.Key<?>, ? extends Object>> r42 = r4.entrySet().iterator();
        L4:
            if (r42.hasNext() == false) goto L6;
            Map.Entry<CaptureRequest.Key<?>, ? extends Object> r0 = r42.next();
            CaptureRequest.Key<?> r1 = r0.getKey();
            Object r02 = r0.getValue();
            Config.Option<Object> r12 = Camera2ImplConfigKt.createCaptureRequestOption(r1);
            this.mutableOptionsBundle.insertOption(r12, r5, r02);
            goto L4
        L6:
            return this;
        }

        public Camera2ImplConfig build() {
            return new Camera2ImplConfig(OptionsBundle.from(this.mutableOptionsBundle));
        }

        @Override // androidx.camera.core.ExtendableBuilder
        public MutableConfig getMutableConfig() {
            return this.mutableOptionsBundle;
        }

        public final Builder insertAllOptions(Config r6) {
            Iterator<Config.Option<?>> r0 = r6.listOptions().iterator();
        L4:
            if (r0.hasNext() == false) goto L6;
            Config.Option<?> r1 = r0.next();
            this.mutableOptionsBundle.insertOption(r1, r6.getOptionPriority(r1), r6.retrieveOption(r1));
            goto L4
        L6:
            return this;
        }

        public final Builder removeCaptureRequestOptions(List<? extends CaptureRequest.Key<?>> r3) {
            Iterator<T> r32 = r3.iterator();
        L4:
            if (r32.hasNext() == false) goto L6;
            Config.Option<Object> r0 = Camera2ImplConfigKt.createCaptureRequestOption((CaptureRequest.Key) r32.next());
            this.mutableOptionsBundle.removeOption(r0);
            goto L4
        L6:
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final <ValueT> Builder setCaptureRequestOption(CaptureRequest.Key<ValueT> r2, ValueT r3) {
            Config.Option<Object> r22 = Camera2ImplConfigKt.createCaptureRequestOption(r2);
            this.mutableOptionsBundle.insertOption(r22, r3);
            return this;
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/camera/camera2/impl/Camera2ImplConfig$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "CAPTURE_REQUEST_ID_STEM", _UrlKt.FRAGMENT_ENCODE_SET, "TEMPLATE_TYPE_OPTION", "Landroidx/camera/core/impl/Config$Option;", _UrlKt.FRAGMENT_ENCODE_SET, "DEVICE_STATE_CALLBACK_OPTION", "Landroid/hardware/camera2/CameraDevice$StateCallback;", "SESSION_STATE_CALLBACK_OPTION", "Landroid/hardware/camera2/CameraCaptureSession$StateCallback;", "SESSION_CAPTURE_CALLBACK_OPTION", "Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;", "STREAM_USE_CASE_OPTION", _UrlKt.FRAGMENT_ENCODE_SET, "STREAM_USE_HINT_OPTION", "CAPTURE_REQUEST_TAG_OPTION", "SESSION_PHYSICAL_CAMERA_ID_OPTION", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
        TEMPLATE_TYPE_OPTION = Config.Option.create("camera2.captureRequest.templateType", Integer.TYPE);
        DEVICE_STATE_CALLBACK_OPTION = Config.Option.create("camera2.cameraDevice.stateCallback", CameraDevice.StateCallback.class);
        SESSION_STATE_CALLBACK_OPTION = Config.Option.create("camera2.cameraCaptureSession.stateCallback", CameraCaptureSession.StateCallback.class);
        SESSION_CAPTURE_CALLBACK_OPTION = Config.Option.create("camera2.cameraCaptureSession.captureCallback", CameraCaptureSession.CaptureCallback.class);
        Class r1 = Long.TYPE;
        STREAM_USE_CASE_OPTION = Config.Option.create("camera2.cameraCaptureSession.streamUseCase", r1);
        STREAM_USE_HINT_OPTION = Config.Option.create("camera2.cameraCaptureSession.streamUseHint", r1);
        CAPTURE_REQUEST_TAG_OPTION = Config.Option.create("camera2.captureRequest.tag", Object.class);
        SESSION_PHYSICAL_CAMERA_ID_OPTION = Config.Option.create("camera2.cameraCaptureSession.physicalCameraId", String.class);
    }

    public Camera2ImplConfig(Config r1) {
        super(r1);
    }

    public static /* synthetic */ CameraDevice.StateCallback getDeviceStateCallback$default(Camera2ImplConfig r0, CameraDevice.StateCallback r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = null;
    L6:
        return r0.getDeviceStateCallback(r1);
    }

    public static /* synthetic */ String getPhysicalCameraId$default(Camera2ImplConfig r0, String r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = null;
    L6:
        return r0.getPhysicalCameraId(r1);
    }

    public static /* synthetic */ CameraCaptureSession.CaptureCallback getSessionCaptureCallback$default(Camera2ImplConfig r0, CameraCaptureSession.CaptureCallback r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = null;
    L6:
        return r0.getSessionCaptureCallback(r1);
    }

    public static /* synthetic */ CameraCaptureSession.StateCallback getSessionStateCallback$default(Camera2ImplConfig r0, CameraCaptureSession.StateCallback r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = null;
    L6:
        return r0.getSessionStateCallback(r1);
    }

    public static /* synthetic */ Long getStreamUseCase$default(Camera2ImplConfig r0, Long r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = null;
    L6:
        return r0.getStreamUseCase(r1);
    }

    public final CaptureRequestOptions getCaptureRequestOptions() {
        return CaptureRequestOptions.Builder.Companion.from(getConfig()).build();
    }

    public final int getCaptureRequestTemplate(int r2) {
        return ((Number) getConfig().retrieveOption(TEMPLATE_TYPE_OPTION, Integer.valueOf(r2))).intValue();
    }

    public final CameraDevice.StateCallback getDeviceStateCallback(CameraDevice.StateCallback r2) {
        return (CameraDevice.StateCallback) getConfig().retrieveOption(DEVICE_STATE_CALLBACK_OPTION, r2);
    }

    public final String getPhysicalCameraId(String r2) {
        return (String) getConfig().retrieveOption(SESSION_PHYSICAL_CAMERA_ID_OPTION, r2);
    }

    public final CameraCaptureSession.CaptureCallback getSessionCaptureCallback(CameraCaptureSession.CaptureCallback r2) {
        return (CameraCaptureSession.CaptureCallback) getConfig().retrieveOption(SESSION_CAPTURE_CALLBACK_OPTION, r2);
    }

    public final CameraCaptureSession.StateCallback getSessionStateCallback(CameraCaptureSession.StateCallback r2) {
        return (CameraCaptureSession.StateCallback) getConfig().retrieveOption(SESSION_STATE_CALLBACK_OPTION, r2);
    }

    public final Long getStreamUseCase(Long r2) {
        return (Long) getConfig().retrieveOption(STREAM_USE_CASE_OPTION, r2);
    }
}
