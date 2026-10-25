package androidx.camera.camera2.compat.workaround;

import androidx.camera.camera2.compat.quirk.CameraQuirks;
import androidx.camera.camera2.compat.quirk.UseTorchAsFlashQuirk;
import androidx.camera.camera2.internal.IntrinsicZoomCalculator;
import androidx.camera.camera2.pipe.CameraDevices;
import androidx.camera.camera2.pipe.FrameMetadata;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0001\nJ.\u0010\u0002\u001a\u00020\u00032\u001e\u0010\u0004\u001a\u001a\b\u0001\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005H¦@¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/compat/workaround/UseTorchAsFlash;", _UrlKt.FRAGMENT_ENCODE_SET, "shouldUseTorchAsFlash", _UrlKt.FRAGMENT_ENCODE_SET, "frameMetadata", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "Landroidx/camera/camera2/pipe/FrameMetadata;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shouldDisableAePrecapture", "Bindings", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface UseTorchAsFlash {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b'\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/compat/workaround/UseTorchAsFlash$Bindings;", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static abstract class Bindings {
        public static final Companion Companion = null;

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007¨\u0006\f"}, d2 = {"Landroidx/camera/camera2/compat/workaround/UseTorchAsFlash$Bindings$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "provideUseTorchAsFlash", "Landroidx/camera/camera2/compat/workaround/UseTorchAsFlash;", "cameraQuirks", "Landroidx/camera/camera2/compat/quirk/CameraQuirks;", "cameraDevices", "Landroidx/camera/camera2/pipe/CameraDevices;", "intrinsicZoomCalculator", "Landroidx/camera/camera2/internal/IntrinsicZoomCalculator;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            public final UseTorchAsFlash provideUseTorchAsFlash(CameraQuirks r2, CameraDevices r3, IntrinsicZoomCalculator r4) {
                if (r2.getQuirks().contains(UseTorchAsFlashQuirk.class) == false) goto L7;
                return new UseTorchAsFlashImpl(r2, r3, r4);
            L7:
                return NotUseTorchAsFlash.INSTANCE;
            }
        }

        static {
            Companion = new Companion(null);
        }
    }

    boolean shouldDisableAePrecapture();

    Object shouldUseTorchAsFlash(Function1<? super Continuation<? super FrameMetadata>, ? extends Object> r1, Continuation<? super Boolean> r2);
}
