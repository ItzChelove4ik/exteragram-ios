package androidx.camera.core.impl;

import android.content.Context;
import androidx.camera.core.CameraIdentifier;
import androidx.camera.core.CameraSelector;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u000e2\u00020\u0001:\u0002\r\u000eJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J$\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Landroidx/camera/core/impl/CameraValidator;", _UrlKt.FRAGMENT_ENCODE_SET, "validateOnFirstInit", _UrlKt.FRAGMENT_ENCODE_SET, "cameraRepository", "Landroidx/camera/core/impl/CameraRepository;", "isChangeInvalid", _UrlKt.FRAGMENT_ENCODE_SET, "currentCameras", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/CameraInternal;", "removedCameras", "Landroidx/camera/core/CameraIdentifier;", "CameraIdListIncorrectException", "Companion", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CameraValidator {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\u0018\u00002\u00060\u0001j\u0002`\u0002B#\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/camera/core/impl/CameraValidator$CameraIdListIncorrectException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", _UrlKt.FRAGMENT_ENCODE_SET, "availableCameraCount", _UrlKt.FRAGMENT_ENCODE_SET, "cause", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;ILjava/lang/Throwable;)V", "getAvailableCameraCount", "()I", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class CameraIdListIncorrectException extends Exception {
        private final int availableCameraCount;

        public CameraIdListIncorrectException(String r1, int r2, Throwable r3) {
            super(r1, r3);
            this.availableCameraCount = r2;
        }

        public final int getAvailableCameraCount() {
            return this.availableCameraCount;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¨\u0006\n"}, d2 = {"Landroidx/camera/core/impl/CameraValidator$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "create", "Landroidx/camera/core/impl/CameraValidator;", "context", "Landroid/content/Context;", "availableCamerasSelector", "Landroidx/camera/core/CameraSelector;", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = null;

        static {
            $$INSTANCE = new Companion();
        }

        private Companion() {
        }

        @JvmStatic
        public final CameraValidator create(Context r1, CameraSelector r2) {
            return new CameraValidatorImpl(r1, r2);
        }
    }

    static {
        Companion = Companion.$$INSTANCE;
    }

    @JvmStatic
    static CameraValidator create(Context r1, CameraSelector r2) {
        return Companion.create(r1, r2);
    }

    boolean isChangeInvalid(Set<? extends CameraInternal> r1, Set<CameraIdentifier> r2);

    void validateOnFirstInit(CameraRepository r1);
}
