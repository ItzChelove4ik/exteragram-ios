package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.CameraColorSpace;
import androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0015\b\u0080\b\u0018\u00002\u00020\u0001Bc\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0012\u0010\u000f\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000e\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u0014HÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u001b\u001a\u00020\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001d\u001a\u0004\b\u001e\u0010\u0018R\u001f\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b \u0010!R\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00048\u0006¢\u0006\f\n\u0004\b\b\u0010\u001f\u001a\u0004\b\"\u0010!R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010#\u001a\u0004\b$\u0010%R\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010&\u001a\u0004\b'\u0010(R\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\u001d\u001a\u0004\b)\u0010\u0018R#\u0010\u000f\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010*\u001a\u0004\b+\u0010,R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010-\u001a\u0004\b.\u0010\u0016¨\u0006/"}, d2 = {"Landroidx/camera/camera2/pipe/compat/SessionConfigData;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "sessionType", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/compat/InputConfigData;", "inputConfiguration", "Landroidx/camera/camera2/pipe/compat/OutputConfigurationWrapper;", "outputConfigurations", "Ljava/util/concurrent/Executor;", "executor", "Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper$StateCallback;", "stateCallback", "sessionTemplateId", _UrlKt.FRAGMENT_ENCODE_SET, "sessionParameters", "Landroidx/camera/camera2/pipe/CameraColorSpace;", "sessionColorSpace", "<init>", "(ILjava/util/List;Ljava/util/List;Ljava/util/concurrent/Executor;Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper$StateCallback;ILjava/util/Map;Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "I", "getSessionType", "Ljava/util/List;", "getInputConfiguration", "()Ljava/util/List;", "getOutputConfigurations", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper$StateCallback;", "getStateCallback", "()Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper$StateCallback;", "getSessionTemplateId", "Ljava/util/Map;", "getSessionParameters", "()Ljava/util/Map;", "Ljava/lang/String;", "getSessionColorSpace-dxVZaPA", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SessionConfigData {
    private final Executor executor;
    private final List<InputConfigData> inputConfiguration;
    private final List<OutputConfigurationWrapper> outputConfigurations;
    private final String sessionColorSpace;
    private final Map<?, Object> sessionParameters;
    private final int sessionTemplateId;
    private final int sessionType;
    private final CameraCaptureSessionWrapper.StateCallback stateCallback;

    /* JADX WARN: Multi-variable type inference failed */
    private SessionConfigData(int r1, List<InputConfigData> r2, List<? extends OutputConfigurationWrapper> r3, Executor r4, CameraCaptureSessionWrapper.StateCallback r5, int r6, Map<?, ? extends Object> r7, String r8) {
        this.sessionType = r1;
        this.inputConfiguration = r2;
        this.outputConfigurations = r3;
        this.executor = r4;
        this.stateCallback = r5;
        this.sessionTemplateId = r6;
        this.sessionParameters = r7;
        this.sessionColorSpace = r8;
    }

    public /* synthetic */ SessionConfigData(int r1, List r2, List r3, Executor r4, CameraCaptureSessionWrapper.StateCallback r5, int r6, Map r7, String r8, DefaultConstructorMarker r9) {
        this(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof SessionConfigData) == true) goto L8;
        return false;
    L8:
        SessionConfigData r52 = (SessionConfigData) r5;
        if (this.sessionType == r52.sessionType) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.inputConfiguration, r52.inputConfiguration) == true) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(this.outputConfigurations, r52.outputConfigurations) == true) goto L18;
        return false;
    L18:
        if (Intrinsics.areEqual(this.executor, r52.executor) == true) goto L21;
        return false;
    L21:
        if (Intrinsics.areEqual(this.stateCallback, r52.stateCallback) == true) goto L24;
        return false;
    L24:
        if (this.sessionTemplateId == r52.sessionTemplateId) goto L27;
        return false;
    L27:
        if (Intrinsics.areEqual(this.sessionParameters, r52.sessionParameters) == true) goto L29;
        return false;
    L29:
        String r4 = this.sessionColorSpace;
        String r53 = r52.sessionColorSpace;
        if (r4 != null) goto L34;
        if (r53 != null) goto L33;
        boolean r42 = true;
    L37:
        if (r42 == true) goto L39;
        return false;
    L39:
        return true;
    L33:
        r42 = false;
        goto L37
    L34:
        if (r53 == null) goto L33;
        r42 = CameraColorSpace.m160equalsimpl0(r4, r53);
        goto L37
    }

    public final Executor getExecutor() {
        return this.executor;
    }

    public final List<InputConfigData> getInputConfiguration() {
        return this.inputConfiguration;
    }

    public final List<OutputConfigurationWrapper> getOutputConfigurations() {
        return this.outputConfigurations;
    }

    /* renamed from: getSessionColorSpace-dxVZaPA, reason: not valid java name */
    public final String m484getSessionColorSpacedxVZaPA() {
        return this.sessionColorSpace;
    }

    public final Map<?, Object> getSessionParameters() {
        return this.sessionParameters;
    }

    public final int getSessionTemplateId() {
        return this.sessionTemplateId;
    }

    public final int getSessionType() {
        return this.sessionType;
    }

    public final CameraCaptureSessionWrapper.StateCallback getStateCallback() {
        return this.stateCallback;
    }

    public int hashCode() {
        int r0 = Integer.hashCode(this.sessionType) * 31;
        List<InputConfigData> r1 = this.inputConfiguration;
        int r2 = 0;
        if (r1 != null) goto L5;
        int r12 = 0;
    L6:
        int r02 = (((((((((((r0 + r12) * 31) + this.outputConfigurations.hashCode()) * 31) + this.executor.hashCode()) * 31) + this.stateCallback.hashCode()) * 31) + Integer.hashCode(this.sessionTemplateId)) * 31) + this.sessionParameters.hashCode()) * 31;
        String r3 = this.sessionColorSpace;
        if (r3 == null) goto L11;
        r2 = CameraColorSpace.m161hashCodeimpl(r3);
    L11:
        return r02 + r2;
    L5:
        r12 = r1.hashCode();
        goto L6
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("SessionConfigData(sessionType=");
        r0.append(this.sessionType);
        r0.append(", inputConfiguration=");
        r0.append(this.inputConfiguration);
        r0.append(", outputConfigurations=");
        r0.append(this.outputConfigurations);
        r0.append(", executor=");
        r0.append(this.executor);
        r0.append(", stateCallback=");
        r0.append(this.stateCallback);
        r0.append(", sessionTemplateId=");
        r0.append(this.sessionTemplateId);
        r0.append(", sessionParameters=");
        r0.append(this.sessionParameters);
        r0.append(", sessionColorSpace=");
        String r2 = this.sessionColorSpace;
        if (r2 != null) goto L5;
        String r22 = "null";
    L6:
        r0.append(r22);
        r0.append(')');
        return r0.toString();
    L5:
        r22 = CameraColorSpace.m163toStringimpl(r2);
        goto L6
    }
}
