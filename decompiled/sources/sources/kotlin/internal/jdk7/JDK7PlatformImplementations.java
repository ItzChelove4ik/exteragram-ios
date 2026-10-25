package kotlin.internal.jdk7;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.internal.PlatformImplementations;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0001\u000fB\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0082\u0080\u0004J\u001a\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0096\u0080\u0004J\u0018\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000e2\u0006\u0010\f\u001a\u00020\u000bH\u0096\u0080\u0004¨\u0006\u0010"}, d2 = {"Lkotlin/internal/jdk7/JDK7PlatformImplementations;", "Lkotlin/internal/PlatformImplementations;", "<init>", "()V", "sdkIsNullOrAtLeast", _UrlKt.FRAGMENT_ENCODE_SET, "version", _UrlKt.FRAGMENT_ENCODE_SET, "addSuppressed", _UrlKt.FRAGMENT_ENCODE_SET, "cause", _UrlKt.FRAGMENT_ENCODE_SET, "exception", "getSuppressed", _UrlKt.FRAGMENT_ENCODE_SET, "ReflectSdkVersion", "kotlin-stdlib-jdk7"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public class JDK7PlatformImplementations extends PlatformImplementations {

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0084\b¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/internal/jdk7/JDK7PlatformImplementations$ReflectSdkVersion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "sdkVersion", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/Integer;", "kotlin-stdlib-jdk7"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nJDK7PlatformImplementations.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JDK7PlatformImplementations.kt\nkotlin/internal/jdk7/JDK7PlatformImplementations$ReflectSdkVersion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,38:1\n1#2:39\n*E\n"})
    /* loaded from: classes5.dex */
    public static final class ReflectSdkVersion {
        public static final ReflectSdkVersion INSTANCE = null;

        @JvmField
        public static final Integer sdkVersion = null;

        static {
            INSTANCE = new ReflectSdkVersion();
            Integer r0 = null;
            Object r1 = Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);     // Catch: Throwable -> L14
            if ((r1 instanceof Integer) == false) goto L7;
            Integer r12 = (Integer) r1;     // Catch: Throwable -> L14
        L8:
            if (r12 != null) goto L10;
        L12:
            sdkVersion = r0;
            return;
        L10:
            if (r12.intValue() <= 0) goto L12;
            r0 = r12;
        L7:
            r12 = null;
            goto L8
        }

        private ReflectSdkVersion() {
        }
    }

    public JDK7PlatformImplementations() {
    }

    private final boolean sdkIsNullOrAtLeast(int r1) {
        Integer r0 = ReflectSdkVersion.sdkVersion;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.intValue() >= r1) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // kotlin.internal.PlatformImplementations
    public void addSuppressed(Throwable r2, Throwable r3) {
        if (sdkIsNullOrAtLeast(19) == false) goto L6;
        r2.addSuppressed(r3);
        return;
    L6:
        super.addSuppressed(r2, r3);
    }

    @Override // kotlin.internal.PlatformImplementations
    public List<Throwable> getSuppressed(Throwable r2) {
        if (sdkIsNullOrAtLeast(19) == false) goto L7;
        return ArraysKt.asList(r2.getSuppressed());
    L7:
        return super.getSuppressed(r2);
    }
}
