package androidx.core.backported.fixes;

import android.os.Build;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\t\b\u0016¢\u0006\u0004\b\u0004\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/core/backported/fixes/BackportedFixManager;", _UrlKt.FRAGMENT_ENCODE_SET, "resolver", "Landroidx/core/backported/fixes/StatusResolver;", "<init>", "(Landroidx/core/backported/fixes/StatusResolver;)V", "()V", "isFixed", _UrlKt.FRAGMENT_ENCODE_SET, "ki", "Landroidx/core/backported/fixes/KnownIssue;", "getStatus", "Landroidx/core/backported/fixes/Status;", "core-backported-fixes"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BackportedFixManager {
    private final StatusResolver resolver;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[Status.values().length];
            r0[Status.Unknown.ordinal()] = 1;     // Catch: NoSuchFieldError -> L9
        L13:
            r0[Status.Fixed.ordinal()] = 2;     // Catch: NoSuchFieldError -> L10
        L19:
            r0[Status.NotApplicable.ordinal()] = 3;     // Catch: NoSuchFieldError -> L11
        L15:
            r0[Status.NotFixed.ordinal()] = 4;     // Catch: NoSuchFieldError -> L12
        L7:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public BackportedFixManager() {
        this(new SystemPropertyResolver());
    }

    public BackportedFixManager(StatusResolver r1) {
        this.resolver = r1;
    }

    public final Status getStatus(KnownIssue r3) {
        if (r3.getPrecondition$core_backported_fixes().invoke().booleanValue() == false) goto L11;
        if (r3.getManuallyTestedFingerprints$core_backported_fixes().contains(Build.FINGERPRINT) == false) goto L9;
        return Status.Fixed;
    L9:
        return this.resolver.getStatus(r3);
    L11:
        return Status.NotApplicable;
    }

    public final boolean isFixed(KnownIssue r3) {
        Status r2 = getStatus(r3);
        int r22 = WhenMappings.$EnumSwitchMapping$0[r2.ordinal()];
        if (r22 != 1) goto L5;
        return false;
    L5:
        if (r22 != 2) goto L7;
    L13:
        return true;
    L7:
        if (r22 == 3) goto L13;
        if (r22 != 4) goto L11;
        return false;
    L11:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return false;
    }
}
