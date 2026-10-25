package androidx.camera.core.internal;

import android.media.MediaCodec;
import android.util.Range;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.SessionConfig;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nJ\u0012\u0010\u000b\u001a\u00020\f*\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r*\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J\u0012\u0010\u0010\u001a\u00020\f*\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J\f\u0010\u0010\u001a\u00020\f*\u00020\nH\u0002J\f\u0010\u0011\u001a\u00020\f*\u00020\u0012H\u0002¨\u0006\u0014"}, d2 = {"Landroidx/camera/core/internal/HighSpeedFpsModifier;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "modifyFpsForPreviewOnlyRepeating", _UrlKt.FRAGMENT_ENCODE_SET, "outputConfigs", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/SessionConfig$OutputConfig;", "repeatingConfigBuilder", "Landroidx/camera/core/impl/CaptureConfig$Builder;", "isHighSpeedFixedFps", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/util/Range;", _UrlKt.FRAGMENT_ENCODE_SET, "toPreviewOnlyRange", "hasVideoSurface", "isVideoSurface", "Landroidx/camera/core/impl/DeferrableSurface;", "Companion", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nHighSpeedFpsModifier.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HighSpeedFpsModifier.kt\nandroidx/camera/core/internal/HighSpeedFpsModifier\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,90:1\n1#2:91\n1761#3,3:92\n1761#3,3:95\n*S KotlinDebug\n*F\n+ 1 HighSpeedFpsModifier.kt\nandroidx/camera/core/internal/HighSpeedFpsModifier\n*L\n80#1:92,3\n85#1:95,3\n*E\n"})
/* loaded from: classes3.dex */
public final class HighSpeedFpsModifier {
    private static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Landroidx/camera/core/internal/HighSpeedFpsModifier$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "PREVIEW_ONLY_FPS_LOWER", _UrlKt.FRAGMENT_ENCODE_SET, "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
    }

    public HighSpeedFpsModifier() {
    }

    private final boolean hasVideoSurface(CaptureConfig.Builder r3) {
        Set<DeferrableSurface> r32 = r3.getSurfaces();
        if (r32 != null) goto L5;
    L7:
        Iterator<T> r33 = r32.iterator();
    L9:
        if (r33.hasNext() == false) goto L14;
        if (isVideoSurface((DeferrableSurface) r33.next()) == false) goto L9;
        return true;
    L14:
        return false;
    L5:
        if (r32.isEmpty() == false) goto L7;
        return false;
    }

    private final boolean hasVideoSurface(Collection<? extends SessionConfig.OutputConfig> r3) {
        Collection<? extends SessionConfig.OutputConfig> r32 = r3;
        if ((r32 instanceof Collection) == true) goto L5;
    L7:
        Iterator<T> r33 = r32.iterator();
    L9:
        if (r33.hasNext() == false) goto L14;
        if (isVideoSurface(((SessionConfig.OutputConfig) r33.next()).getSurface()) == false) goto L9;
        return true;
    L14:
        return false;
    L5:
        if (r32.isEmpty() == false) goto L7;
        return false;
    }

    private final boolean isHighSpeedFixedFps(Range<Integer> r2) {
        if (((Number) r2.getUpper()).intValue() >= 120) goto L5;
        return false;
    L5:
        if (Intrinsics.areEqual(r2.getLower(), r2.getUpper()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private final boolean isVideoSurface(DeferrableSurface r1) {
        return Intrinsics.areEqual(r1.getContainerClass(), MediaCodec.class);
    }

    private final Range<Integer> toPreviewOnlyRange(Range<Integer> r3) {
        Range<Integer> r2 = new Range(30, r3.getUpper());
        Logger.d("HighSpeedFpsModifier", "Modified high-speed FPS range from " + r3 + " to " + r2);
        return r2;
    }

    public final void modifyFpsForPreviewOnlyRepeating(Collection<? extends SessionConfig.OutputConfig> r3, CaptureConfig.Builder r4) {
        if (r3.size() == 2) goto L5;
        return;
    L5:
        if (hasVideoSurface(r3) == true) goto L7;
        return;
    L7:
        if (hasVideoSurface(r4) == true) goto L19;
        Range<Integer> r32 = r4.getExpectedFrameRateRange();
        if (r32 != null) goto L11;
        return;
    L11:
        if (isHighSpeedFixedFps(r32) == true) goto L14;
        r32 = null;
    L14:
        if (r32 == null) goto L21;
        r4.setExpectedFrameRateRange(toPreviewOnlyRange(r32));
        return;
    L21:
        return;
    }
}
