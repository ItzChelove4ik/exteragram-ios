package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRegex.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Regex.kt\nkotlin/text/RegexKt$fromInt$1$1\n*L\n1#1,420:1\n*E\n"})
/* loaded from: classes5.dex */
public final class Regex$special$$inlined$fromInt$1 implements Function1<RegexOption, Boolean> {
    final /* synthetic */ int $value;

    public Regex$special$$inlined$fromInt$1(int r1) {
        this.$value = r1;
    }

    public final Boolean invoke(RegexOption r2) {
        RegexOption r22 = r2;
        if ((this.$value & r22.getMask()) != r22.getValue()) goto L5;
        boolean r1 = true;
    L7:
        return Boolean.valueOf(r1);
    L5:
        r1 = false;
        goto L7
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Boolean invoke(RegexOption r1) {
        return invoke(r1);
    }
}
