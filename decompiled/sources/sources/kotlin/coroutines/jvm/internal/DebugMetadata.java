package kotlin.coroutines.jvm.internal;

import com.android.dx.rop.code.RegisterSpec;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.annotation.AnnotationTarget;
import kotlin.jvm.JvmName;
import okhttp3.internal.url._UrlKt;

@Target({ElementType.TYPE})
@SinceKotlin(version = "1.3")
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0015\b\u0081\u0002\u0018\u00002\u00020\u0001B\u0094\u0001\bF\u0012\u000e\b\u0002\u0010\u0002\u001a\u00020\u0003B\u0004\b\u0003\u0010\u0004\u0012\u000e\b\u0002\u0010\u0004\u001a\u00020\u0005B\u0004\b\b(\u0006\u0012\f\b\u0002\u0010\u0007\u001a\u00020\bB\u0002\b\f\u0012\u0012\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nB\u0002\b\f\u0012\u0012\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\nB\u0002\b\f\u0012\f\b\u0002\u0010\f\u001a\u00020\bB\u0002\b\f\u0012\u000e\b\u0002\u0010\r\u001a\u00020\u0005B\u0004\b\b(\u0006\u0012\u000e\b\u0002\u0010\u000e\u001a\u00020\u0005B\u0004\b\b(\u0006\u0012\f\b\u0002\u0010\u000f\u001a\u00020\bB\u0002\b\fR\u0015\u0010\u0002\u001a\u00020\u00038\u0007X\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\u0004\u001a\u00020\u00058\u0007X\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\u0007\u001a\u00020\b8\u0007X\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u001b\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n8\u0007X\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\n8\u0007X\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0017R\u0015\u0010\f\u001a\u00020\b8\u0007X\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0015R\u0015\u0010\r\u001a\u00020\u00058\u0007X\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0013R\u0015\u0010\u000e\u001a\u00020\u00058\u0007X\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0013R\u0019\u0010\u000f\u001a\u00020\bX\u0087\u0084\b¢\u0006\f\u0012\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u0015¨\u0006\u001f"}, d2 = {"Lkotlin/coroutines/jvm/internal/DebugMetadata;", _UrlKt.FRAGMENT_ENCODE_SET, "version", _UrlKt.FRAGMENT_ENCODE_SET, "sourceFile", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "lineNumbers", _UrlKt.FRAGMENT_ENCODE_SET, "localNames", _UrlKt.FRAGMENT_ENCODE_SET, "spilled", "indexToLabel", "methodName", "className", "nextLineNumbers", RegisterSpec.PREFIX, "()I", "f", "()Ljava/lang/String;", "l", "()[I", "n", "()[Ljava/lang/String;", "s", "i", "m", "c", "nl$annotations", "()V", "nl", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS})
@Retention(RetentionPolicy.RUNTIME)
@PublishedApi
/* loaded from: classes5.dex */
public @interface DebugMetadata {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @SinceKotlin(version = "2.2")
        public static /* synthetic */ void nl$annotations() {
        }
    }

    @JvmName(name = "c")
    String c() default "";

    @JvmName(name = "f")
    String f() default "";

    @JvmName(name = "i")
    int[] i() default {};

    @JvmName(name = "l")
    int[] l() default {};

    @JvmName(name = "m")
    String m() default "";

    @JvmName(name = "n")
    String[] n() default {};

    @JvmName(name = "nl")
    int[] nl() default {};

    @JvmName(name = "s")
    String[] s() default {};

    @JvmName(name = RegisterSpec.PREFIX)
    int v() default 2;
}
