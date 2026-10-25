package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRegex.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Regex.kt\nkotlin/text/RegexKt$fromInt$1$1\n*L\n1#1,420:1\n*E\n"})
/* loaded from: classes5.dex */
public final class RegexKt$fromInt$1$1<T> implements Function1<T, Boolean> {
    final /* synthetic */ int $value;

    public RegexKt$fromInt$1$1(int r1) {
        this.$value = r1;
    }

    /* JADX WARN: Incorrect types in method signature: (TT;)Ljava/lang/Boolean; */
    /* JADX WARN: Multi-variable type inference failed */
    public final Boolean invoke(Enum r2) {
        FlagEnum r22 = (FlagEnum) r2;
        if ((this.$value & r22.getMask()) != r22.getValue()) goto L5;
        boolean r1 = true;
    L7:
        return Boolean.valueOf(r1);
    L5:
        r1 = false;
        goto L7
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Boolean invoke(Object r1) {
        return invoke((Enum) r1);
    }
}
