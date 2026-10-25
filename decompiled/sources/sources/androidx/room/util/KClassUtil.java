package androidx.room.util;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a1\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"findAndInstantiateDatabaseImpl", "T", "C", "klass", "Ljava/lang/Class;", "suffix", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;", "room-runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
@JvmName(name = "KClassUtil")
/* loaded from: classes.dex */
public abstract class KClassUtil {
    public static final <T, C> T findAndInstantiateDatabaseImpl(Class<C> r10, String r11) {
        Package r0 = r10.getPackage();
        if (r0 == null) goto L6;
        String r02 = r0.getName();
        if (r02 == null) goto L6;
    L7:
        String r1 = r10.getCanonicalName();
        if (r02.length() == 0) goto L11;
        r1 = r1.substring(r02.length() + 1);
    L11:
        String r7 = StringsKt.replace$default(r1, '.', '_', false, 4, null) + r11;
    L22:
        e = move-exception;
        KClassUtil$$ExternalSyntheticBUOutline1.m("Cannot find implementation for ", r10.getCanonicalName(), ". ", r7, " does not exist. Is Room annotation processor correctly configured?", e);
        return null;
    L20:
        e = move-exception;
        KClassUtil$$ExternalSyntheticBUOutline0.m("Cannot access the constructor ", r10.getCanonicalName(), e);
        return null;
    L18:
        e = move-exception;
        KClassUtil$$ExternalSyntheticBUOutline0.m("Failed to create an instance of ", r10.getCanonicalName(), e);
        return null;
    L13:
        if (r02.length() != 0) goto L15;
        String r03 = r7;
    L16:
        return (T) Class.forName(r03, true, r10.getClassLoader()).getDeclaredConstructor(null).newInstance(null);
    L15:
        r03 = r02 + '.' + r7;     // Catch: InstantiationException -> L18 IllegalAccessException -> L20 ClassNotFoundException -> L22
    L6:
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L7
    }

    public static /* synthetic */ Object findAndInstantiateDatabaseImpl$default(Class r0, String r1, int r2, Object r3) {
        if ((r2 & 2) == 0) goto L6;
        r1 = "_Impl";
    L6:
        return findAndInstantiateDatabaseImpl(r0, r1);
    }
}
