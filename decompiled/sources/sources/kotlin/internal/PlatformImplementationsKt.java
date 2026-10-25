package kotlin.internal;

import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.internal.jdk8.JDK8PlatformImplementations;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a#\u0010\u0002\u001a\u0002H\u0003\"\n\b\u0000\u0010\u0003\u0018\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0083\u0088\u0004¢\u0006\u0002\u0010\u0006\u001a\"\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0081\u0080\u0004\"\u0011\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0084\b¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"IMPLEMENTATIONS", "Lkotlin/internal/PlatformImplementations;", "castToBaseType", "T", _UrlKt.FRAGMENT_ENCODE_SET, "instance", "(Ljava/lang/Object;)Ljava/lang/Object;", "apiVersionIsAtLeast", _UrlKt.FRAGMENT_ENCODE_SET, "major", _UrlKt.FRAGMENT_ENCODE_SET, "minor", "patch", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PlatformImplementationsKt {

    @JvmField
    public static final PlatformImplementations IMPLEMENTATIONS = null;

    static {
        IMPLEMENTATIONS = new JDK8PlatformImplementations();
    }

    @SinceKotlin(version = "1.2")
    @PublishedApi
    public static final boolean apiVersionIsAtLeast(int r1, int r2, int r3) {
        return KotlinVersion.CURRENT.isAtLeast(r1, r2, r3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    private static final /* synthetic */ <T> T castToBaseType(Object r5) {
        Intrinsics.reifiedOperationMarker(1, "T");     // Catch: ClassCastException -> L5
        return r5;
    L5:
        e = move-exception;
        ClassLoader r52 = r5.getClass().getClassLoader();
        Intrinsics.reifiedOperationMarker(4, "T");
        ClassLoader r0 = Object.class.getClassLoader();
        if (Intrinsics.areEqual(r52, r0) == false) goto L9;
        throw e;
    L9:
        throw new ClassNotFoundException("Instance class was loaded from a different classloader: " + r52 + ", base type classloader: " + r0, e);
    }
}
