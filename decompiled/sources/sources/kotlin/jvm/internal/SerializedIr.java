package kotlin.jvm.internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.jvm.JvmName;
import okhttp3.internal.url._UrlKt;

@Target({ElementType.TYPE})
@SinceKotlin(version = "1.6")
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0016\bF\u0012\u0012\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003B\u0002\b\fR\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0007X\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/jvm/internal/SerializedIr;", _UrlKt.FRAGMENT_ENCODE_SET, "bytes", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "b", "()[Ljava/lang/String;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS})
@Retention(RetentionPolicy.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
/* loaded from: classes5.dex */
public @interface SerializedIr {
    @JvmName(name = "b")
    String[] b() default {};
}
