package androidx.camera.core.internal.compat.quirk;

import android.annotation.SuppressLint;
import androidx.camera.core.impl.Quirk;
import androidx.core.backported.fixes.BackportedFixManager;
import androidx.core.backported.fixes.KnownIssue;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b'\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Landroidx/camera/core/internal/compat/quirk/BackportedFixQuirk;", "Landroidx/camera/core/impl/Quirk;", "<init>", "()V", "getKnownIssue", "Landroidx/core/backported/fixes/KnownIssue;", "hasIssue", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
/* loaded from: classes3.dex */
public abstract class BackportedFixQuirk implements Quirk {
    public static final Companion Companion = null;
    private static final Lazy<BackportedFixManager> backportedFixManager$delegate = null;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/camera/core/internal/compat/quirk/BackportedFixQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "backportedFixManager", "Landroidx/core/backported/fixes/BackportedFixManager;", "getBackportedFixManager", "()Landroidx/core/backported/fixes/BackportedFixManager;", "backportedFixManager$delegate", "Lkotlin/Lazy;", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final BackportedFixManager getBackportedFixManager() {
            return (BackportedFixManager) BackportedFixQuirk.access$getBackportedFixManager$delegate$cp().getValue();
        }
    }

    /* renamed from: $r8$lambda$2eNJcB0ZkXo6McceQrtwYP3-Me0, reason: not valid java name */
    public static BackportedFixManager m605$r8$lambda$2eNJcB0ZkXo6McceQrtwYP3Me0() {
        return new BackportedFixManager();
    }

    static {
        Companion = new Companion(null);
        backportedFixManager$delegate = LazyKt.lazy(new BackportedFixQuirk$$ExternalSyntheticLambda0());
    }

    public BackportedFixQuirk() {
    }

    public static final /* synthetic */ Lazy access$getBackportedFixManager$delegate$cp() {
        return backportedFixManager$delegate;
    }

    public abstract KnownIssue getKnownIssue();

    public final boolean hasIssue() {
        return !Companion.getBackportedFixManager().isFixed(getKnownIssue());
    }
}
