package androidx.camera.camera2.impl;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.MeteringRectangle;
import androidx.camera.camera2.pipe.AeMode;
import androidx.camera.camera2.pipe.Lock3ABehavior;
import androidx.camera.camera2.pipe.Result3A;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\bf\u0018\u00002\u00020\u0001:\u0001<J:\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0016\u0010\u0005\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH'J:\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0016\u0010\u0005\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH&J*\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u000f2\b\b\u0002\u0010\b\u001a\u00020\tH&J$\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H'J,\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0017\u001a\u00020\u00182\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00010\u0006H'J\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0003H'J\u001d\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00032\u0006\u0010\u001e\u001a\u00020\u001fH'¢\u0006\u0004\b \u0010!J\u0083\u0001\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00032\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010\u000f2\u0010\b\u0002\u0010%\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010\u000f2\u0010\b\u0002\u0010&\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010\u000f2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010(2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010(2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010(2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010,\u001a\u00020-H'¢\u0006\u0002\b.J\u000e\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0003H'J<\u00100\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001010\u00030\u000f2\f\u00102\u001a\b\u0012\u0004\u0012\u0002030\u000f2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002052\u0006\u00107\u001a\u000205H'JD\u00108\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00032\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010\u000f2\u0010\b\u0002\u0010%\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010\u000f2\u0010\b\u0002\u0010&\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010\u000fH'J\u000e\u00109\u001a\u00020\u0012H¦@¢\u0006\u0002\u0010:J\b\u0010;\u001a\u00020\u0004H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006=À\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", _UrlKt.FRAGMENT_ENCODE_SET, "setParametersAsync", "Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET, "values", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureRequest$Key;", "type", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl$Type;", "optionPriority", "Landroidx/camera/core/impl/Config$OptionPriority;", "submitParameters", "removeParametersAsync", "keys", _UrlKt.FRAGMENT_ENCODE_SET, "updateRepeatingRequestAsync", "isPrimary", _UrlKt.FRAGMENT_ENCODE_SET, "runningUseCases", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "updateCamera2ConfigAsync", "config", "Landroidx/camera/core/impl/Config;", "tags", _UrlKt.FRAGMENT_ENCODE_SET, "setTorchOnAsync", "Landroidx/camera/camera2/pipe/Result3A;", "setTorchOffAsync", "aeMode", "Landroidx/camera/camera2/pipe/AeMode;", "setTorchOffAsync-MtizInI", "(I)Lkotlinx/coroutines/Deferred;", "startFocusAndMeteringAsync", "aeRegions", "Landroid/hardware/camera2/params/MeteringRectangle;", "afRegions", "awbRegions", "aeLockBehavior", "Landroidx/camera/camera2/pipe/Lock3ABehavior;", "afLockBehavior", "awbLockBehavior", "afTriggerStartAeMode", "timeLimitNs", _UrlKt.FRAGMENT_ENCODE_SET, "startFocusAndMeteringAsync-NxRnBj4", "cancelFocusAndMeteringAsync", "issueSingleCaptureAsync", "Ljava/lang/Void;", "captureSequence", "Landroidx/camera/core/impl/CaptureConfig;", "captureMode", _UrlKt.FRAGMENT_ENCODE_SET, "flashType", "flashMode", "update3aRegions", "awaitSurfaceSetup", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "close", "Type", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface UseCaseCameraRequestControl {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/camera/camera2/impl/UseCaseCameraRequestControl$Type;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "SESSION_CONFIG", PluginsConstants.Strategy.DEFAULT, "CAMERA2_CAMERA_CONTROL", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public enum Type extends Enum<Type> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ Type[] $VALUES = null;
        public static final Type CAMERA2_CAMERA_CONTROL = null;
        public static final Type DEFAULT = null;
        public static final Type SESSION_CONFIG = null;

        private static final /* synthetic */ Type[] $values() {
            return new Type[]{SESSION_CONFIG, DEFAULT, CAMERA2_CAMERA_CONTROL};
        }

        static {
            SESSION_CONFIG = new Type("SESSION_CONFIG", 0);
            DEFAULT = new Type(PluginsConstants.Strategy.DEFAULT, 1);
            CAMERA2_CAMERA_CONTROL = new Type("CAMERA2_CAMERA_CONTROL", 2);
            Type[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        Type(String r1, int r2) {
        }

        public static EnumEntries<Type> getEntries() {
            return $ENTRIES;
        }

        public static Type valueOf(String r1) {
            return (Type) Enum.valueOf(Type.class, r1);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }
    }

    static /* synthetic */ Deferred removeParametersAsync$default(UseCaseCameraRequestControl r0, List r1, Type r2, int r3, Object r4) {
        if (r4 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: removeParametersAsync");
        return null;
    L4:
        if ((r3 & 2) == 0) goto L7;
        r2 = Type.DEFAULT;
    L7:
        return r0.removeParametersAsync(r1, r2);
    }

    static /* synthetic */ Deferred setParametersAsync$default(UseCaseCameraRequestControl r0, Map r1, Type r2, Config.OptionPriority r3, int r4, Object r5) {
        if (r5 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: setParametersAsync");
        return null;
    L4:
        if ((r4 & 2) == 0) goto L7;
        r2 = Type.DEFAULT;
    L7:
        if ((r4 & 4) == 0) goto L10;
        r3 = UseCaseCameraKt.getDefaultOptionPriority();
    L10:
        return r0.setParametersAsync(r1, r2, r3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: startFocusAndMeteringAsync-NxRnBj4$default, reason: not valid java name */
    static /* synthetic */ Deferred m96startFocusAndMeteringAsyncNxRnBj4$default(UseCaseCameraRequestControl r1, List r2, List r3, List r4, Lock3ABehavior r5, Lock3ABehavior r6, Lock3ABehavior r7, AeMode r8, long r9, int r11, Object r12) {
        if (r12 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: startFocusAndMeteringAsync-NxRnBj4");
        return null;
    L4:
        if ((r11 & 1) == 0) goto L7;
        r2 = null;
    L7:
        if ((r11 & 2) == 0) goto L10;
        r3 = null;
    L10:
        if ((r11 & 4) == 0) goto L13;
        r4 = null;
    L13:
        if ((r11 & 8) == 0) goto L16;
        r5 = null;
    L16:
        if ((r11 & 16) == 0) goto L19;
        r6 = null;
    L19:
        if ((r11 & 32) == 0) goto L22;
        r7 = null;
    L22:
        if ((r11 & 64) == 0) goto L25;
        r8 = null;
    L25:
        if ((r11 & 128) == 0) goto L28;
        r9 = 3000000000L;
    L28:
        return r1.mo70startFocusAndMeteringAsyncNxRnBj4(r2, r3, r4, r5, r6, r7, r8, r9);
    }

    static /* synthetic */ Deferred submitParameters$default(UseCaseCameraRequestControl r0, Map r1, Type r2, Config.OptionPriority r3, int r4, Object r5) {
        if (r5 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: submitParameters");
        return null;
    L4:
        if ((r4 & 2) == 0) goto L7;
        r2 = Type.DEFAULT;
    L7:
        if ((r4 & 4) == 0) goto L10;
        r3 = UseCaseCameraKt.getDefaultOptionPriority();
    L10:
        return r0.submitParameters(r1, r2, r3);
    }

    Object awaitSurfaceSetup(Continuation<? super Boolean> r1);

    Deferred<Result3A> cancelFocusAndMeteringAsync();

    void close();

    List<Deferred<Void>> issueSingleCaptureAsync(List<CaptureConfig> r1, int r2, int r3, int r4);

    Deferred<Unit> removeParametersAsync(List<? extends CaptureRequest.Key<?>> r1, Type r2);

    Deferred<Unit> setParametersAsync(Map<CaptureRequest.Key<?>, ? extends Object> r1, Type r2, Config.OptionPriority r3);

    /* renamed from: setTorchOffAsync-MtizInI */
    Deferred<Result3A> mo69setTorchOffAsyncMtizInI(int r1);

    Deferred<Result3A> setTorchOnAsync();

    /* renamed from: startFocusAndMeteringAsync-NxRnBj4 */
    Deferred<Result3A> mo70startFocusAndMeteringAsyncNxRnBj4(List<MeteringRectangle> r1, List<MeteringRectangle> r2, List<MeteringRectangle> r3, Lock3ABehavior r4, Lock3ABehavior r5, Lock3ABehavior r6, AeMode r7, long r8);

    Deferred<Unit> submitParameters(Map<CaptureRequest.Key<?>, ? extends Object> r1, Type r2, Config.OptionPriority r3);

    Deferred<Result3A> update3aRegions(List<MeteringRectangle> r1, List<MeteringRectangle> r2, List<MeteringRectangle> r3);

    Deferred<Unit> updateCamera2ConfigAsync(Config r1, Map<String, ? extends Object> r2);

    Deferred<Unit> updateRepeatingRequestAsync(boolean r1, Collection<? extends UseCase> r2);
}
