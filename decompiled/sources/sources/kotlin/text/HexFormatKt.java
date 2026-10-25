package kotlin.text;

import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.HexFormat;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0000\u001a&\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0087\u0088\u0004ø\u0001\u0000\u001a\u000e\u0010\u0007\u001a\u00020\b*\u00020\tH\u0082\u0080\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\n"}, d2 = {"HexFormat", "Lkotlin/text/HexFormat;", "builderAction", "Lkotlin/Function1;", "Lkotlin/text/HexFormat$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "isCaseSensitive", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nHexFormat.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HexFormat.kt\nkotlin/text/HexFormatKt\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,845:1\n1107#2,2:846\n*S KotlinDebug\n*F\n+ 1 HexFormat.kt\nkotlin/text/HexFormatKt\n*L\n843#1:846,2\n*E\n"})
/* loaded from: classes5.dex */
public final class HexFormatKt {
    @SinceKotlin(version = "2.2")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final HexFormat HexFormat(Function1<? super HexFormat.Builder, Unit> r1) {
        HexFormat.Builder r0 = new HexFormat.Builder();
        r1.invoke(r0);
        return r0.build();
    }

    public static final /* synthetic */ boolean access$isCaseSensitive(String r0) {
        return isCaseSensitive(r0);
    }

    private static final boolean isCaseSensitive(String r4) {
        int r1 = 0;
    L4:
        if (r1 >= r4.length()) goto L13;
        char r2 = r4.charAt(r1);
        if (Intrinsics.compare(r2, 128) >= 0) goto L11;
        if (Character.isLetter(r2) == true) goto L17;
        r1 = r1 + 1;
        goto L4
    L17:
        return true;
    L11:
        return true;
    L13:
        return false;
    }
}
