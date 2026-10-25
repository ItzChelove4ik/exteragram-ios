package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.InputConfiguration;
import android.view.Surface;
import androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper;
import androidx.camera.camera2.pipe.core.Log;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J%\u0010\f\u001a\u00020\u000b2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\rJ-\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000e2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J%\u0010\u0012\u001a\u00020\u000b2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\u0012\u0010\rJ%\u0010\u0015\u001a\u00020\u000b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00062\u0006\u0010\n\u001a\u00020\tH\u0017¢\u0006\u0004\b\u0015\u0010\rJ-\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00162\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00130\u00062\u0006\u0010\n\u001a\u00020\tH\u0017¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u001aH\u0017¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u001eH\u0017¢\u0006\u0004\b\f\u0010\u001fJ\u0019\u0010%\u001a\u0004\u0018\u00010\"2\u0006\u0010!\u001a\u00020 H\u0016¢\u0006\u0004\b#\u0010$J\u0019\u0010(\u001a\u0004\u0018\u00010\"2\u0006\u0010'\u001a\u00020&H\u0016¢\u0006\u0004\b(\u0010)J\u000f\u0010+\u001a\u00020*H\u0016¢\u0006\u0004\b+\u0010,J\u000f\u0010-\u001a\u00020*H\u0016¢\u0006\u0004\b-\u0010,J)\u00102\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010/*\u00020.2\f\u00101\u001a\b\u0012\u0004\u0012\u00028\u000000H\u0016¢\u0006\u0004\b2\u00103J\u000f\u00105\u001a\u00020*H\u0000¢\u0006\u0004\b4\u0010,J\u0017\u0010:\u001a\u00020*2\u0006\u00107\u001a\u000206H\u0017¢\u0006\u0004\b8\u00109R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010;\u001a\u0004\b<\u0010=R\u0014\u0010>\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b>\u0010?R\u0016\u0010@\u001a\u00020\u000b8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b@\u0010AR\u0014\u0010E\u001a\u00020B8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bC\u0010D¨\u0006F"}, d2 = {"Landroidx/camera/camera2/pipe/compat/VirtualAndroidCameraDevice;", "Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "Landroidx/camera/camera2/pipe/compat/AndroidCameraDevice;", "androidCameraDevice", "<init>", "(Landroidx/camera/camera2/pipe/compat/AndroidCameraDevice;)V", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/view/Surface;", "outputs", "Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper$StateCallback;", "stateCallback", _UrlKt.FRAGMENT_ENCODE_SET, "createCaptureSession", "(Ljava/util/List;Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper$StateCallback;)Z", "Landroid/hardware/camera2/params/InputConfiguration;", PluginsConstants.Settings.TYPE_INPUT, "createReprocessableCaptureSession", "(Landroid/hardware/camera2/params/InputConfiguration;Ljava/util/List;Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper$StateCallback;)Z", "createConstrainedHighSpeedCaptureSession", "Landroidx/camera/camera2/pipe/compat/OutputConfigurationWrapper;", "outputConfigurations", "createCaptureSessionByOutputConfigurations", "Landroidx/camera/camera2/pipe/compat/InputConfigData;", "inputConfig", "createReprocessableCaptureSessionByConfigurations", "(Landroidx/camera/camera2/pipe/compat/InputConfigData;Ljava/util/List;Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper$StateCallback;)Z", "Landroidx/camera/camera2/pipe/compat/ExtensionSessionConfigData;", "config", "createExtensionSession", "(Landroidx/camera/camera2/pipe/compat/ExtensionSessionConfigData;)Z", "Landroidx/camera/camera2/pipe/compat/SessionConfigData;", "(Landroidx/camera/camera2/pipe/compat/SessionConfigData;)Z", "Landroidx/camera/camera2/pipe/RequestTemplate;", "template", "Landroid/hardware/camera2/CaptureRequest$Builder;", "createCaptureRequest-2PPcXtw", "(I)Landroid/hardware/camera2/CaptureRequest$Builder;", "createCaptureRequest", "Landroid/hardware/camera2/TotalCaptureResult;", "inputResult", "createReprocessCaptureRequest", "(Landroid/hardware/camera2/TotalCaptureResult;)Landroid/hardware/camera2/CaptureRequest$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "onDeviceClosing", "()V", "onDeviceClosed", _UrlKt.FRAGMENT_ENCODE_SET, "T", "Lkotlin/reflect/KClass;", "type", "unwrapAs", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "disconnect$camera_camera2_pipe", "disconnect", "Landroidx/camera/camera2/pipe/AudioRestrictionMode;", "mode", "onCameraAudioRestrictionUpdated-LwUUkyU", "(I)V", "onCameraAudioRestrictionUpdated", "Landroidx/camera/camera2/pipe/compat/AndroidCameraDevice;", "getAndroidCameraDevice$camera_camera2_pipe", "()Landroidx/camera/camera2/pipe/compat/AndroidCameraDevice;", "lock", "Ljava/lang/Object;", "disconnected", "Z", "Landroidx/camera/camera2/pipe/CameraId;", "getCameraId-Dz_R5H8", "()Ljava/lang/String;", "cameraId", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraDeviceWrapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraDeviceWrapper.kt\nandroidx/camera/camera2/pipe/compat/VirtualAndroidCameraDevice\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,714:1\n71#2,2:715\n71#2,2:717\n71#2,2:719\n71#2,2:721\n71#2,2:723\n71#2,2:725\n71#2,2:727\n71#2,2:729\n71#2,2:731\n1#3:733\n*S KotlinDebug\n*F\n+ 1 CameraDeviceWrapper.kt\nandroidx/camera/camera2/pipe/compat/VirtualAndroidCameraDevice\n*L\n570#1:715,2\n585#1:717,2\n599#1:719,2\n616#1:721,2\n637#1:723,2\n656#1:725,2\n668#1:727,2\n679#1:729,2\n689#1:731,2\n*E\n"})
/* loaded from: classes3.dex */
public final class VirtualAndroidCameraDevice implements CameraDeviceWrapper {
    private final AndroidCameraDevice androidCameraDevice;
    private boolean disconnected;
    private final Object lock;

    public VirtualAndroidCameraDevice(AndroidCameraDevice r1) {
        this.androidCameraDevice = r1;
        this.lock = new Object();
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    /* renamed from: createCaptureRequest-2PPcXtw */
    public CaptureRequest.Builder mo411createCaptureRequest2PPcXtw(int r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.disconnected == true) goto L7;
        CaptureRequest.Builder r2 = this.androidCameraDevice.mo411createCaptureRequest2PPcXtw(r3);     // Catch: Throwable -> L9
    L13:
        monitor-exit(r0);
        return r2;
    L7:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L11;
        android.util.Log.w("CXCP", "createCaptureRequest failed: Virtual device disconnected");     // Catch: Throwable -> L9
    L11:
        r2 = null;
        goto L13
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    public boolean createCaptureSession(SessionConfigData r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.disconnected == true) goto L7;
        boolean r2 = this.androidCameraDevice.createCaptureSession(r3);     // Catch: Throwable -> L9
    L13:
        monitor-exit(r0);
        return r2;
    L7:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L11;
        android.util.Log.w("CXCP", "createCaptureSession failed: Virtual device disconnected");     // Catch: Throwable -> L9
    L11:
        r3.getStateCallback().onSessionFinalized();     // Catch: Throwable -> L9
        r2 = false;
        goto L13
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    public boolean createCaptureSession(List<? extends Surface> r3, CameraCaptureSessionWrapper.StateCallback r4) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.disconnected == true) goto L7;
        boolean r2 = this.androidCameraDevice.createCaptureSession(r3, r4);     // Catch: Throwable -> L9
    L13:
        monitor-exit(r0);
        return r2;
    L7:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L11;
        android.util.Log.w("CXCP", "createCaptureSession failed: Virtual device disconnected");     // Catch: Throwable -> L9
    L11:
        r4.onSessionFinalized();     // Catch: Throwable -> L9
        r2 = false;
        goto L13
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    public boolean createCaptureSessionByOutputConfigurations(List<? extends OutputConfigurationWrapper> r3, CameraCaptureSessionWrapper.StateCallback r4) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.disconnected == true) goto L7;
        boolean r2 = this.androidCameraDevice.createCaptureSessionByOutputConfigurations(r3, r4);     // Catch: Throwable -> L9
    L13:
        monitor-exit(r0);
        return r2;
    L7:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L11;
        android.util.Log.w("CXCP", "createCaptureSessionByOutputConfigurations failed: Virtual device disconnected");     // Catch: Throwable -> L9
    L11:
        r4.onSessionFinalized();     // Catch: Throwable -> L9
        r2 = false;
        goto L13
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    public boolean createConstrainedHighSpeedCaptureSession(List<? extends Surface> r3, CameraCaptureSessionWrapper.StateCallback r4) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.disconnected == true) goto L7;
        boolean r2 = this.androidCameraDevice.createConstrainedHighSpeedCaptureSession(r3, r4);     // Catch: Throwable -> L9
    L13:
        monitor-exit(r0);
        return r2;
    L7:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L11;
        android.util.Log.w("CXCP", "createConstrainedHighSpeedCaptureSession failed: Virtual device disconnected");     // Catch: Throwable -> L9
    L11:
        r4.onSessionFinalized();     // Catch: Throwable -> L9
        r2 = false;
        goto L13
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    public boolean createExtensionSession(ExtensionSessionConfigData r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.disconnected == true) goto L7;
        boolean r2 = this.androidCameraDevice.createExtensionSession(r3);     // Catch: Throwable -> L9
    L13:
        monitor-exit(r0);
        return r2;
    L7:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L11;
        android.util.Log.w("CXCP", "createExtensionSession failed: Virtual device disconnected");     // Catch: Throwable -> L9
    L11:
        r3.getExtensionStateCallback().onSessionFinalized();     // Catch: Throwable -> L9
        r2 = false;
        goto L13
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    public CaptureRequest.Builder createReprocessCaptureRequest(TotalCaptureResult r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.disconnected == true) goto L7;
        CaptureRequest.Builder r2 = this.androidCameraDevice.createReprocessCaptureRequest(r3);     // Catch: Throwable -> L9
    L13:
        monitor-exit(r0);
        return r2;
    L7:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L11;
        android.util.Log.w("CXCP", "createReprocessCaptureRequest failed: Virtual device disconnected");     // Catch: Throwable -> L9
    L11:
        r2 = null;
        goto L13
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    public boolean createReprocessableCaptureSession(InputConfiguration r3, List<? extends Surface> r4, CameraCaptureSessionWrapper.StateCallback r5) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.disconnected == true) goto L7;
        boolean r2 = this.androidCameraDevice.createReprocessableCaptureSession(r3, r4, r5);     // Catch: Throwable -> L9
    L13:
        monitor-exit(r0);
        return r2;
    L7:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L11;
        android.util.Log.w("CXCP", "createReprocessableCaptureSession failed: Virtual device disconnected");     // Catch: Throwable -> L9
    L11:
        r5.onSessionFinalized();     // Catch: Throwable -> L9
        r2 = false;
        goto L13
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    public boolean createReprocessableCaptureSessionByConfigurations(InputConfigData r3, List<? extends OutputConfigurationWrapper> r4, CameraCaptureSessionWrapper.StateCallback r5) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.disconnected == true) goto L7;
        boolean r2 = this.androidCameraDevice.createReprocessableCaptureSessionByConfigurations(r3, r4, r5);     // Catch: Throwable -> L9
    L13:
        monitor-exit(r0);
        return r2;
    L7:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L11;
        android.util.Log.w("CXCP", "createReprocessableCaptureSessionByConfigurations failed: Virtual device disconnected");     // Catch: Throwable -> L9
    L11:
        r5.onSessionFinalized();     // Catch: Throwable -> L9
        r2 = false;
        goto L13
    }

    public final void disconnect$camera_camera2_pipe() {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.disconnected = true;     // Catch: Throwable -> L8
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(r0);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    /* renamed from: getCameraId-Dz_R5H8 */
    public String mo412getCameraIdDz_R5H8() {
        return this.androidCameraDevice.mo412getCameraIdDz_R5H8();
    }

    @Override // androidx.camera.camera2.pipe.compat.AudioRestrictionController.Listener
    /* renamed from: onCameraAudioRestrictionUpdated-LwUUkyU */
    public void mo413onCameraAudioRestrictionUpdatedLwUUkyU(int r1) {
        this.androidCameraDevice.mo413onCameraAudioRestrictionUpdatedLwUUkyU(r1);
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    public void onDeviceClosed() {
        this.androidCameraDevice.onDeviceClosed();
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraDeviceWrapper
    public void onDeviceClosing() {
        this.androidCameraDevice.onDeviceClosing();
    }

    @Override // androidx.camera.camera2.pipe.UnsafeWrapper
    public <T> T unwrapAs(KClass<T> r1) {
        return (T) this.androidCameraDevice.unwrapAs(r1);
    }
}
