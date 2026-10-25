package androidx.camera.core.impl.utils;

import androidx.camera.core.Logger;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.stabilization.VideoStabilization;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u00020\u0006*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\t\u001a\u00020\u0006*\u00020\u0005H\u0007¢\u0006\u0004\b\t\u0010\nJ3\u0010\u000f\u001a\u00020\u000e*\b\u0012\u0004\u0012\u00020\u00050\u00042\u0018\b\u0002\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0\u000bH\u0007¢\u0006\u0004\b\u000f\u0010\u0010J1\u0010\u0012\u001a\u00020\u0011*\b\u0012\u0004\u0012\u00020\u00050\u00042\u0016\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0\u000bH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J1\u0010\u0014\u001a\u00020\u0011*\b\u0012\u0004\u0012\u00020\u00050\u00042\u0016\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0\u000bH\u0002¢\u0006\u0004\b\u0014\u0010\u0013¨\u0006\u0015"}, d2 = {"Landroidx/camera/core/impl/utils/UseCaseUtil;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", _UrlKt.FRAGMENT_ENCODE_SET, "containsVideoCapture", "(Ljava/util/Collection;)Z", "isVideoCapture", "(Landroidx/camera/core/UseCase;)Z", "Lkotlin/Function1;", "Landroidx/camera/core/impl/UseCaseConfig;", "configProvider", "Landroidx/camera/core/impl/stabilization/VideoStabilization;", "getVideoStabilization", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Landroidx/camera/core/impl/stabilization/VideoStabilization;", _UrlKt.FRAGMENT_ENCODE_SET, "getPreviewStabilizationMode", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)I", "getVideoStabilizationMode", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUseCaseUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseUtil.kt\nandroidx/camera/core/impl/utils/UseCaseUtil\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,158:1\n1869#2,2:159\n1869#2,2:161\n1869#2,2:163\n295#2,2:165\n295#2,2:167\n295#2,2:169\n295#2,2:171\n*S KotlinDebug\n*F\n+ 1 UseCaseUtil.kt\nandroidx/camera/core/impl/utils/UseCaseUtil\n*L\n35#1:159,2\n68#1:161,2\n96#1:163,2\n127#1:165,2\n138#1:167,2\n147#1:169,2\n156#1:171,2\n*E\n"})
/* loaded from: classes3.dex */
public final class UseCaseUtil {
    public static final UseCaseUtil INSTANCE = null;

    public static UseCaseConfig $r8$lambda$M5Tm0HjWhdy66TpzSL1OyPbufao(UseCase r0) {
        return r0.getCurrentConfig();
    }

    static {
        INSTANCE = new UseCaseUtil();
    }

    private UseCaseUtil() {
    }

    @JvmStatic
    public static final boolean containsVideoCapture(Collection<? extends UseCase> r2) {
        Iterator<T> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L10;
        UseCase r0 = (UseCase) r22.next();
        if (r0 == null) goto L4;
        if (isVideoCapture(r0) != true) goto L4;
        return true;
    L10:
        return false;
    }

    private final int getPreviewStabilizationMode(Collection<? extends UseCase> r4, Function1<? super UseCase, ? extends UseCaseConfig<?>> r5) {
        Iterator<T> r3 = r4.iterator();
        int r42 = 0;
    L4:
        if (r3.hasNext() == false) goto L11;
        int r0 = r5.invoke((UseCase) r3.next()).getPreviewStabilizationMode();
        if (r0 == 0) goto L4;
        if (r42 == r0) goto L10;
        if (r42 == 0) goto L10;
        Logger.w("UseCaseUtil", "Unexpected configurations: Overwriting current previewStabilizationMode(" + r42 + ") with useCasePreviewStabilization(" + r0 + ")!");
    L10:
        r42 = r0;
        goto L4
    L11:
        return r42;
    }

    @JvmStatic
    public static final VideoStabilization getVideoStabilization(Collection<? extends UseCase> r3, Function1<? super UseCase, ? extends UseCaseConfig<?>> r4) {
        VideoStabilization.Companion r0 = VideoStabilization.Companion;
        UseCaseUtil r1 = INSTANCE;
        return r0.from$camera_core(r1.getPreviewStabilizationMode(r3, r4), r1.getVideoStabilizationMode(r3, r4));
    }

    public static /* synthetic */ VideoStabilization getVideoStabilization$default(Collection r0, Function1 r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = new UseCaseUtil$$ExternalSyntheticLambda0();
    L6:
        return getVideoStabilization(r0, r1);
    }

    private final int getVideoStabilizationMode(Collection<? extends UseCase> r4, Function1<? super UseCase, ? extends UseCaseConfig<?>> r5) {
        Iterator<T> r3 = r4.iterator();
        int r42 = 0;
    L4:
        if (r3.hasNext() == false) goto L11;
        int r0 = r5.invoke((UseCase) r3.next()).getVideoStabilizationMode();
        if (r0 == 0) goto L4;
        if (r42 == r0) goto L10;
        if (r42 == 0) goto L10;
        Logger.w("UseCaseUtil", "Unexpected configurations: Overwriting current videoStabilizationMode(" + r42 + ") with useCaseVideoStabilization(" + r0 + ")!");
    L10:
        r42 = r0;
        goto L4
    L11:
        return r42;
    }

    @JvmStatic
    public static final boolean isVideoCapture(UseCase r2) {
        if (r2.getCurrentConfig().containsOption(UseCaseConfig.OPTION_CAPTURE_TYPE) == true) goto L5;
        Logger.e("UseCaseUtil", r2 + " UseCase does not have capture type.");
        return false;
    L5:
        if (r2.getCurrentConfig().getCaptureType() != UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE) goto L8;
        return true;
    L8:
        return false;
    }
}
