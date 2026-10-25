package kotlinx.coroutines.internal;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\b\b\u0081@\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0013\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0006\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b\u0007\u0010\b\u0088\u0001\u0003\u0092\u0001\u0004\u0018\u00010\u0002¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/internal/InlineList;", "E", _UrlKt.FRAGMENT_ENCODE_SET, "holder", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "element", "plus-FjFbRPM", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "plus", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
@SourceDebugExtension({"SMAP\nInlineList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InlineList.kt\nkotlinx/coroutines/internal/InlineList\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,45:1\n1#2:46\n*E\n"})
/* loaded from: classes5.dex */
public abstract class InlineList<E> {
    /* renamed from: constructor-impl, reason: not valid java name */
    public static <E> Object m3554constructorimpl(Object r0) {
        return r0;
    }

    /* renamed from: constructor-impl$default, reason: not valid java name */
    public static /* synthetic */ Object m3555constructorimpl$default(Object r0, int r1, DefaultConstructorMarker r2) {
        if ((r1 & 1) == 0) goto L6;
        r0 = null;
    L6:
        return m3554constructorimpl(r0);
    }

    /* renamed from: plus-FjFbRPM, reason: not valid java name */
    public static final Object m3556plusFjFbRPM(Object r2, E r3) {
        if (r2 != null) goto L6;
        return m3554constructorimpl(r3);
    L6:
        if ((r2 instanceof ArrayList) == false) goto L9;
        ((ArrayList) r2).add(r3);
        return m3554constructorimpl(r2);
    L9:
        ArrayList r0 = new ArrayList(4);
        r0.add(r2);
        r0.add(r3);
        return m3554constructorimpl(r0);
    }
}
