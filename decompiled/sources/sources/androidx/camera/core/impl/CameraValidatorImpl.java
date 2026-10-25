package androidx.camera.core.impl;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.camera.core.CameraIdentifier;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraValidator;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u001c2\u00020\u0001:\u0003\u001a\u001b\u001cB\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J$\u0010\u0010\u001a\u00020\t2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0012H\u0016J\u001e\u0010\u0016\u001a\u00020\t2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0018\u001a\u00020\u0005H\u0002J\b\u0010\u0019\u001a\u00020\u000bH\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/camera/core/impl/CameraValidatorImpl;", "Landroidx/camera/core/impl/CameraValidator;", "context", "Landroid/content/Context;", "availableCamerasSelector", "Landroidx/camera/core/CameraSelector;", "<init>", "(Landroid/content/Context;Landroidx/camera/core/CameraSelector;)V", "isVirtualDevice", _UrlKt.FRAGMENT_ENCODE_SET, "validationCriteria", "Landroidx/camera/core/impl/CameraValidatorImpl$ValidationCriteria;", "validateOnFirstInit", _UrlKt.FRAGMENT_ENCODE_SET, "cameraRepository", "Landroidx/camera/core/impl/CameraRepository;", "isChangeInvalid", "currentCameras", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/CameraInternal;", "removedCameras", "Landroidx/camera/core/CameraIdentifier;", "hasCamera", "cameras", PluginsConstants.Settings.TYPE_SELECTOR, "getValidationCriteria", "ValidationCriteria", "Api34Impl", "Companion", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraValidator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraValidator.kt\nandroidx/camera/core/impl/CameraValidatorImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,209:1\n1563#2:210\n1634#2,3:211\n774#2:214\n865#2,2:215\n*S KotlinDebug\n*F\n+ 1 CameraValidator.kt\nandroidx/camera/core/impl/CameraValidatorImpl\n*L\n155#1:210\n155#1:211,3\n157#1:214\n157#1:215,2\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraValidatorImpl implements CameraValidator {
    public static final Companion Companion = null;
    private final CameraSelector availableCamerasSelector;
    private final Context context;
    private final boolean isVirtualDevice;
    private final ValidationCriteria validationCriteria;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/camera/core/impl/CameraValidatorImpl$Api34Impl;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getDeviceId", _UrlKt.FRAGMENT_ENCODE_SET, "context", "Landroid/content/Context;", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Api34Impl {
        public static final Api34Impl INSTANCE = null;

        static {
            INSTANCE = new Api34Impl();
        }

        private Api34Impl() {
        }

        public final int getDeviceId(Context r1) {
            return r1.getDeviceId();
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/camera/core/impl/CameraValidatorImpl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u000e\u001a\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0010\u001a\u0004\b\u0013\u0010\u0012¨\u0006\u0014"}, d2 = {"Landroidx/camera/core/impl/CameraValidatorImpl$ValidationCriteria;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "checkBack", "checkFront", "<init>", "(ZZ)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Z", "getCheckBack", "()Z", "getCheckFront", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class ValidationCriteria {
        private final boolean checkBack;
        private final boolean checkFront;

        public ValidationCriteria(boolean r1, boolean r2) {
            this.checkBack = r1;
            this.checkFront = r2;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof ValidationCriteria) == true) goto L8;
            return false;
        L8:
            ValidationCriteria r52 = (ValidationCriteria) r5;
            if (this.checkBack == r52.checkBack) goto L12;
            return false;
        L12:
            if (this.checkFront == r52.checkFront) goto L14;
            return false;
        L14:
            return true;
        }

        public final boolean getCheckBack() {
            return this.checkBack;
        }

        public final boolean getCheckFront() {
            return this.checkFront;
        }

        public int hashCode() {
            return (Boolean.hashCode(this.checkBack) * 31) + Boolean.hashCode(this.checkFront);
        }

        public String toString() {
            return "ValidationCriteria(checkBack=" + this.checkBack + ", checkFront=" + this.checkFront + ')';
        }
    }

    static {
        Companion = new Companion(null);
    }

    public CameraValidatorImpl(Context r1, CameraSelector r2) {
        this.context = r1;
        this.availableCamerasSelector = r2;
        this.isVirtualDevice = isVirtualDevice(r1);
        this.validationCriteria = getValidationCriteria();
    }

    private final ValidationCriteria getValidationCriteria() {
        PackageManager r0 = this.context.getPackageManager();
        CameraSelector r4 = this.availableCamerasSelector;
        if (r4 == null) goto L5;
        Integer r42 = r4.getLensFacing();
    L6:
        boolean r1 = r0.hasSystemFeature("android.hardware.camera");
        boolean r02 = r0.hasSystemFeature("android.hardware.camera.front");
        boolean r2 = false;
        if (r1 == false) goto L12;
        if (r42 != null) goto L10;
    L11:
        boolean r12 = true;
    L13:
        if (r02 == false) goto L19;
        if (r42 != null) goto L16;
    L17:
        r2 = true;
        goto L19
    L16:
        if (r42.intValue() == 0) goto L17;
    L19:
        return new ValidationCriteria(r12, r2);
    L10:
        if (r42.intValue() == 1) goto L11;
    L12:
        r12 = false;
        goto L13
    L5:
        r42 = null;
        goto L6
    }

    private final boolean hasCamera(Set<? extends CameraInternal> r1, CameraSelector r2) {
        r2.select(new LinkedHashSet(r1));     // Catch: IllegalArgumentException -> L5
        return true;
    L5:
        return false;
    }

    private final boolean isVirtualDevice(Context r2) {
        if (Build.VERSION.SDK_INT >= 34) goto L5;
        return false;
    L5:
        if (Api34Impl.INSTANCE.getDeviceId(r2) == 0) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.camera.core.impl.CameraValidator
    public boolean isChangeInvalid(Set<? extends CameraInternal> r7, Set<CameraIdentifier> r8) {
        if (this.isVirtualDevice == false) goto L5;
    L36:
        return false;
    L5:
        if (this.validationCriteria.getCheckBack() == false) goto L7;
    L9:
        boolean r0 = hasCamera(r7, CameraSelector.DEFAULT_BACK_CAMERA);
        boolean r2 = hasCamera(r7, CameraSelector.DEFAULT_FRONT_CAMERA);
        ArrayList r3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r8, 10));
        Iterator<T> r82 = r8.iterator();
    L11:
        if (r82.hasNext() == false) goto L13;
        r3.add(((CameraIdentifier) r82.next()).getInternalId());
        goto L11
    L13:
        Set r83 = CollectionsKt.toSet(r3);
        ArrayList r32 = new ArrayList();
        Iterator<T> r72 = r7.iterator();
    L15:
        if (r72.hasNext() == false) goto L19;
        Object r4 = r72.next();
        if (r83.contains(((CameraInternal) r4).getCameraInfoInternal().getCameraId()) == true) goto L15;
        r32.add(r4);
        goto L15
    L19:
        Set<? extends CameraInternal> r73 = CollectionsKt.toSet(r32);
        boolean r84 = hasCamera(r73, CameraSelector.DEFAULT_BACK_CAMERA);
        boolean r74 = hasCamera(r73, CameraSelector.DEFAULT_FRONT_CAMERA);
        if (this.validationCriteria.getCheckBack() == false) goto L24;
        if (r0 == false) goto L24;
        if (r84 == true) goto L24;
        boolean r85 = true;
    L26:
        if (this.validationCriteria.getCheckFront() == false) goto L30;
        if (r2 == false) goto L30;
        if (r74 == true) goto L30;
        boolean r6 = true;
    L31:
        if (r85 == true) goto L35;
        if (r6 == true) goto L35;
        return false;
    L35:
        return true;
    L30:
        r6 = false;
    L24:
        r85 = false;
        goto L26
    L7:
        if (this.validationCriteria.getCheckFront() == true) goto L9;
        goto L9
    }

    @Override // androidx.camera.core.impl.CameraValidator
    public void validateOnFirstInit(CameraRepository r4) {
        if (this.isVirtualDevice == false) goto L6;
        Logger.d("CameraValidator", "Virtual device with " + r4.getCameras().size() + " cameras. Skipping validation.");
        return;
    L6:
        Logger.d("CameraValidator", "Verifying camera lens facing on " + Build.DEVICE);
        if (this.validationCriteria.getCheckBack() == true) goto L27;
    L12:
        e = null;
    L14:
        if (this.validationCriteria.getCheckFront() == true) goto L25;
    L21:
        if (e != null) goto L24;
        return;
    L24:
        throw new CameraValidator.CameraIdListIncorrectException("Expected camera missing from device.", r4.getCameras().size(), e);
    L25:
        CameraSelector.DEFAULT_FRONT_CAMERA.select(r4.getCameras());     // Catch: RuntimeException -> L17
    L17:
        e = move-exception;
        Logger.w("CameraValidator", "Camera LENS_FACING_FRONT verification failed", e);
        if (e != null) goto L21;
        e = e;
        goto L21
    L27:
        CameraSelector.DEFAULT_BACK_CAMERA.select(r4.getCameras());     // Catch: RuntimeException -> L10
    L10:
        e = e;
        Logger.w("CameraValidator", "Camera LENS_FACING_BACK verification failed", e);
        goto L14
    }
}
