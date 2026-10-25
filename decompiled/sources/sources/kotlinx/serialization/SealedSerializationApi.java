package kotlinx.serialization;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.RequiresOptIn;
import kotlin.annotation.MustBeDocumented;
import okhttp3.internal.url._UrlKt;

@Target({})
@RequiresOptIn(level = RequiresOptIn.Level.ERROR, message = "This class or interface should not be inherited/implemented outside of kotlinx.serialization library. Note it is still permitted to use it directly. Read its documentation about inheritance for details.")
@kotlin.annotation.Target(allowedTargets = {})
@Retention(RetentionPolicy.RUNTIME)
@MustBeDocumented
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0002\bF¨\u0006\u0002"}, d2 = {"Lkotlinx/serialization/SealedSerializationApi;", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-serialization-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Documented
/* loaded from: classes5.dex */
public @interface SealedSerializationApi {
}
