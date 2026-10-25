package kotlin.coroutines.jvm.internal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a\u0015\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0081\u0080\u0004¢\u0006\u0002\b\u0003\u001a\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u0002H\u0082\u0080\u0004\u001a\u000e\u0010\u0006\u001a\u00020\u0007*\u00020\u0002H\u0082\u0080\u0004\u001a\u001b\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t*\u00020\u0002H\u0081\u0080\u0004¢\u0006\u0002\u0010\u000b\u001a\u000e\u0010\u000e\u001a\u00020\u0007*\u00020\u0002H\u0081\u0080\u0004\"\u000f\u0010\f\u001a\u00020\u0007X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\r\u001a\u00020\u0007X\u0082Ô\b¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"getStackTraceElementImpl", "Ljava/lang/StackTraceElement;", "Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "getStackTraceElement", "getDebugMetadataAnnotation", "Lkotlin/coroutines/jvm/internal/DebugMetadata;", "getLabel", _UrlKt.FRAGMENT_ENCODE_SET, "getSpilledVariableFieldMapping", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;)[Ljava/lang/String;", "COROUTINES_DEBUG_METADATA_VERSION_1_3", "COROUTINES_DEBUG_METADATA_VERSION_2_2", "getNextLineNumber", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDebugMetadata.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DebugMetadata.kt\nkotlin/coroutines/jvm/internal/DebugMetadataKt\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,161:1\n37#2,2:162\n*S KotlinDebug\n*F\n+ 1 DebugMetadata.kt\nkotlin/coroutines/jvm/internal/DebugMetadataKt\n*L\n134#1:162,2\n*E\n"})
/* loaded from: classes5.dex */
public final class DebugMetadataKt {
    private static final int COROUTINES_DEBUG_METADATA_VERSION_1_3 = 1;
    private static final int COROUTINES_DEBUG_METADATA_VERSION_2_2 = 2;

    private static final DebugMetadata getDebugMetadataAnnotation(BaseContinuationImpl r1) {
        return (DebugMetadata) r1.getClass().getAnnotation(DebugMetadata.class);
    }

    private static final int getLabel(BaseContinuationImpl r3) {
        int r1 = 0;
        if ((r3 instanceof TailCallBaseContinuationImpl) == false) goto L15;
        return 0;
    L15:
        Field r0 = r3.getClass().getDeclaredField("label");     // Catch: Exception -> L13
        r0.setAccessible(true);     // Catch: Exception -> L13
        Object r32 = r0.get(r3);     // Catch: Exception -> L13
        if ((r32 instanceof Integer) == false) goto L8;
        Integer r33 = (Integer) r32;     // Catch: Exception -> L13
    L9:
        if (r33 == null) goto L12;
        r1 = r33.intValue();     // Catch: Exception -> L13
    L12:
        return r1 - 1;
    L8:
        r33 = null;
    L13:
        return -1;
    }

    @SinceKotlin(version = "2.2")
    @PublishedApi
    public static final int getNextLineNumber(BaseContinuationImpl r4) {
        DebugMetadata r0 = getDebugMetadataAnnotation(r4);
        if (r0 != null) goto L6;
        return -1;
    L6:
        if (r0.v() >= 2) goto L8;
        return -1;
    L8:
        int r42 = getLabel(r4);
        if (r42 >= 0) goto L12;
        return -1;
    L12:
        if (r42 < r0.nl().length) goto L15;
        return -1;
    L15:
        return r0.nl()[r42];
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @JvmName(name = "getSpilledVariableFieldMapping")
    public static final String[] getSpilledVariableFieldMapping(BaseContinuationImpl r7) {
        DebugMetadata r0 = getDebugMetadataAnnotation(r7);
        if (r0 != null) goto L6;
        return null;
    L6:
        if (r0.v() >= 1) goto L8;
        return null;
    L8:
        ArrayList r1 = new ArrayList();
        int r72 = getLabel(r7);
        int[] r2 = r0.i();
        int r3 = r2.length;
        int r5 = 0;
    L9:
        if (r5 >= r3) goto L15;
        if (r2[r5] != r72) goto L13;
        r1.add(r0.s()[r5]);
        r1.add(r0.n()[r5]);
    L13:
        r5 = r5 + 1;
        goto L9
    L15:
        return (String[]) r1.toArray(new String[0]);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @JvmName(name = "getStackTraceElement")
    public static final StackTraceElement getStackTraceElement(BaseContinuationImpl r4) {
        DebugMetadata r0 = getDebugMetadataAnnotation(r4);
        if (r0 != null) goto L6;
        return null;
    L6:
        if (r0.v() >= 1) goto L8;
        return null;
    L8:
        int r1 = getLabel(r4);
        if (r1 >= 0) goto L11;
        int r12 = -1;
    L12:
        String r42 = ModuleNameRetriever.INSTANCE.getModuleName(r4);
        if (r42 != null) goto L15;
        String r43 = r0.c();
    L17:
        return new StackTraceElement(r43, r0.m(), r0.f(), r12);
    L15:
        r43 = r42 + '/' + r0.c();
        goto L17
    L11:
        r12 = r0.l()[r1];
        goto L12
    }
}
