package androidx.lifecycle.viewmodel.internal;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\" \u0010\u0000\u001a\u0004\u0018\u00010\u0001*\b\u0012\u0002\b\u0003\u0018\u00010\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"canonicalName", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KClass;", "getCanonicalName", "(Lkotlin/reflect/KClass;)Ljava/lang/String;", "lifecycle-viewmodel"}, k = 2, mv = {2, 1, 0}, xi = 48)
@JvmName(name = "CanonicalName_jvmKt")
/* loaded from: classes.dex */
public abstract class CanonicalName_jvmKt {
    public static final String getCanonicalName(KClass<?> r0) {
        if (r0 != null) goto L4;
        return null;
    L4:
        return r0.getQualifiedName();
    }
}
