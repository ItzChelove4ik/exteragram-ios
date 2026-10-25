package kotlin.text;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.internal.IntrinsicConstEvaluation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.SequencesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0087\u0080\u0004\u001a\"\u0010\u0003\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u000e\u0010\u0005\u001a\u00020\u0001*\u00020\u0001H\u0087\u0080\u0004\u001a\u0018\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u0018\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u0013\u0010\t\u001a\u00020\n*\u00020\u0001H\u0082\u0080\u0004¢\u0006\u0002\b\u000b\u001a#\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\r2\u0006\u0010\b\u001a\u00020\u0001H\u0082\u0080\u0004¢\u0006\u0002\b\u000e\u001aK\u0010\u000f\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00102\u0006\u0010\u0011\u001a\u00020\n2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\r2\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00010\rH\u0082\u0088\u0004¢\u0006\u0002\b\u0014¨\u0006\u0015"}, d2 = {"trimMargin", _UrlKt.FRAGMENT_ENCODE_SET, "marginPrefix", "replaceIndentByMargin", "newIndent", "trimIndent", "replaceIndent", "prependIndent", "indent", "indentWidth", _UrlKt.FRAGMENT_ENCODE_SET, "indentWidth$StringsKt__IndentKt", "getIndentFunction", "Lkotlin/Function1;", "getIndentFunction$StringsKt__IndentKt", "reindent", _UrlKt.FRAGMENT_ENCODE_SET, "resultSizeEstimate", "indentAddFunction", "indentCutFunction", "reindent$StringsKt__IndentKt", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/text/StringsKt")
@SourceDebugExtension({"SMAP\nIndent.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Indent.kt\nkotlin/text/StringsKt__IndentKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,129:1\n119#1,2:131\n121#1,4:137\n126#1,2:150\n119#1,2:159\n121#1,4:165\n126#1,2:172\n1#2:130\n1#2:147\n1#2:169\n1#2:184\n1606#3:133\n1617#3:134\n1924#3,2:135\n1926#3:148\n1618#3:149\n777#3:152\n873#3,2:153\n1586#3:155\n1661#3,3:156\n1606#3:161\n1617#3:162\n1924#3,2:163\n1926#3:170\n1618#3:171\n1606#3:180\n1617#3:181\n1924#3,2:182\n1926#3:185\n1618#3:186\n161#4,6:141\n161#4,6:174\n*S KotlinDebug\n*F\n+ 1 Indent.kt\nkotlin/text/StringsKt__IndentKt\n*L\n42#1:131,2\n42#1:137,4\n42#1:150,2\n83#1:159,2\n83#1:165,4\n83#1:172,2\n42#1:147\n83#1:169\n120#1:184\n42#1:133\n42#1:134\n42#1:135,2\n42#1:148\n42#1:149\n79#1:152\n79#1:153,2\n80#1:155\n80#1:156,3\n83#1:161\n83#1:162\n83#1:163,2\n83#1:170\n83#1:171\n120#1:180\n120#1:181\n120#1:182,2\n120#1:185\n120#1:186\n43#1:141,6\n107#1:174,6\n*E\n"})
/* loaded from: classes.dex */
class StringsKt__IndentKt extends StringsKt__AppendableKt {
    public static /* synthetic */ String $r8$lambda$3BvvMery65JEhzYBhCrX5rYm0VE(String r0) {
        return getIndentFunction$lambda$0$StringsKt__IndentKt(r0);
    }

    /* renamed from: $r8$lambda$42ieJlZRMv6w-QjCqSPAMJL39OY, reason: not valid java name */
    public static /* synthetic */ String m3336$r8$lambda$42ieJlZRMv6wQjCqSPAMJL39OY(String r0, String r1) {
        return prependIndent$lambda$0$StringsKt__IndentKt(r0, r1);
    }

    public static /* synthetic */ String $r8$lambda$fNhk8osE5fmKswSs2oW3GpnF78I(String r0, String r1) {
        return getIndentFunction$lambda$1$StringsKt__IndentKt(r0, r1);
    }

    public StringsKt__IndentKt() {
    }

    private static final Function1<String, String> getIndentFunction$StringsKt__IndentKt(String r1) {
        if (r1.length() != 0) goto L7;
        return new StringsKt__IndentKt$$ExternalSyntheticLambda1();
    L7:
        return new StringsKt__IndentKt$$ExternalSyntheticLambda2(r1);
    }

    private static final String getIndentFunction$lambda$0$StringsKt__IndentKt(String r0) {
        return r0;
    }

    private static final String getIndentFunction$lambda$1$StringsKt__IndentKt(String r1, String r2) {
        return r1 + r2;
    }

    private static final int indentWidth$StringsKt__IndentKt(String r4) {
        int r0 = r4.length();
        int r1 = 0;
    L4:
        if (r1 >= r0) goto L9;
        if (CharsKt__CharJVMKt.isWhitespace(r4.charAt(r1)) == false) goto L10;
        r1 = r1 + 1;
    L10:
        if (r1 == (-1)) goto L12;
        return r1;
    L12:
        return r4.length();
    L9:
        r1 = -1;
        goto L10
    }

    public static final String prependIndent(String r10, String r11) {
        return SequencesKt.joinToString$default(SequencesKt.map(StringsKt__StringsKt.lineSequence(r10), new StringsKt__IndentKt$$ExternalSyntheticLambda0(r11)), "\n", null, null, 0, null, null, 62, null);
    }

    public static /* synthetic */ String prependIndent$default(String r0, String r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = "    ";
    L6:
        return prependIndent(r0, r1);
    }

    private static final String prependIndent$lambda$0$StringsKt__IndentKt(String r2, String r3) {
        if (StringsKt__StringsKt.isBlank(r3) == false) goto L9;
        if (r3.length() >= r2.length()) goto L7;
        return r2;
    L7:
        return r3;
    L9:
        return r2 + r3;
    }

    private static final String reindent$StringsKt__IndentKt(List<String> r11, int r12, Function1<? super String, String> r13, Function1<? super String, String> r14) {
        int r0 = CollectionsKt.getLastIndex(r11);
        ArrayList r1 = new ArrayList();
        Iterator<T> r112 = r11.iterator();
        int r2 = 0;
    L4:
        if (r112.hasNext() == false) goto L24;
        Object r3 = r112.next();
        int r4 = r2 + 1;
        if (r2 >= 0) goto L8;
        CollectionsKt.throwIndexOverflow();
    L8:
        String r32 = (String) r3;
        if (r2 == 0) goto L12;
        if (r2 == r0) goto L12;
    L14:
        String r22 = r14.invoke(r32);
        if (r22 == null) goto L20;
        String r23 = r13.invoke(r22);
        if (r23 == null) goto L20;
        r32 = r23;
    L20:
        if (r32 == null) goto L22;
        r1.add(r32);
    L22:
        r2 = r4;
    L12:
        if (StringsKt__StringsKt.isBlank(r32) == false) goto L14;
        r32 = null;
        goto L20
    L24:
        return ((StringBuilder) CollectionsKt.joinTo$default(r1, new StringBuilder(r12), "\n", null, null, 0, null, null, 124, null)).toString();
    }

    public static final String replaceIndent(String r16, String r17) {
        List<String> r0 = StringsKt__StringsKt.lines(r16);
        ArrayList r2 = new ArrayList();
        Iterator<T> r1 = r0.iterator();
    L4:
        if (r1.hasNext() == false) goto L8;
        Object r3 = r1.next();
        if (StringsKt__StringsKt.isBlank((String) r3) == true) goto L4;
        r2.add(r3);
        goto L4
    L8:
        ArrayList r12 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r2, 10));
        int r32 = r2.size();
        int r4 = 0;
        int r5 = 0;
    L9:
        if (r5 >= r32) goto L11;
        Object r6 = r2.get(r5);
        r5 = r5 + 1;
        r12.add(Integer.valueOf(indentWidth$StringsKt__IndentKt((String) r6)));
        goto L9
    L11:
        Integer r13 = (Integer) CollectionsKt.minOrNull(r12);
        if (r13 == null) goto L14;
        int r14 = r13.intValue();
    L15:
        int r22 = r16.length() + (r17.length() * r0.size());
        Function1<String, String> r33 = getIndentFunction$StringsKt__IndentKt(r17);
        int r52 = CollectionsKt.getLastIndex(r0);
        ArrayList r62 = new ArrayList();
        Iterator<T> r02 = r0.iterator();
    L17:
        if (r02.hasNext() == false) goto L37;
        Object r7 = r02.next();
        int r8 = r4 + 1;
        if (r4 >= 0) goto L21;
        CollectionsKt.throwIndexOverflow();
    L21:
        String r72 = (String) r7;
        if (r4 == 0) goto L25;
        if (r4 == r52) goto L25;
    L27:
        String r42 = StringsKt___StringsKt.drop(r72, r14);
        if (r42 == null) goto L33;
        String r43 = r33.invoke(r42);
        if (r43 == null) goto L33;
        r72 = r43;
    L33:
        if (r72 == null) goto L35;
        r62.add(r72);
    L35:
        r4 = r8;
    L25:
        if (StringsKt__StringsKt.isBlank(r72) == false) goto L27;
        r72 = null;
        goto L33
    L37:
        return ((StringBuilder) CollectionsKt.joinTo$default(r62, new StringBuilder(r22), "\n", null, null, 0, null, null, 124, null)).toString();
    L14:
        r14 = 0;
        goto L15
    }

    public static /* synthetic */ String replaceIndent$default(String r0, String r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        return replaceIndent(r0, r1);
    }

    public static final String replaceIndentByMargin(String r16, String r17, String r18) {
        if (StringsKt__StringsKt.isBlank(r18) == true) goto L39;
        List<String> r0 = StringsKt__StringsKt.lines(r16);
        int r2 = r16.length() + (r17.length() * r0.size());
        Function1<String, String> r3 = getIndentFunction$StringsKt__IndentKt(r17);
        int r4 = CollectionsKt.getLastIndex(r0);
        ArrayList r5 = new ArrayList();
        Iterator<T> r02 = r0.iterator();
        int r7 = 0;
    L6:
        if (r02.hasNext() == false) goto L38;
        Object r8 = r02.next();
        int r9 = r7 + 1;
        if (r7 >= 0) goto L10;
        CollectionsKt.throwIndexOverflow();
    L10:
        String r10 = (String) r8;
        if (r7 == 0) goto L14;
        if (r7 == r4) goto L14;
    L16:
        int r72 = r10.length();
        int r82 = 0;
    L18:
        if (r82 >= r72) goto L23;
        if (CharsKt__CharJVMKt.isWhitespace(r10.charAt(r82)) == false) goto L21;
        r82 = r82 + 1;
        goto L18
    L21:
        int r12 = r82;
    L24:
        if (r12 != (-1)) goto L27;
    L25:
        String r73 = null;
    L29:
        if (r73 == null) goto L34;
        String r74 = r3.invoke(r73);
        if (r74 == null) goto L34;
        r10 = r74;
    L34:
        if (r10 == null) goto L36;
        r5.add(r10);
    L36:
        r7 = r9;
        goto L6
    L27:
        if (StringsKt__StringsJVMKt.startsWith$default(r10, r18, r12, false, 4, null) == false) goto L25;
        r73 = r10.substring(r12 + r18.length());
        goto L29
    L23:
        r12 = -1;
    L14:
        if (StringsKt__StringsKt.isBlank(r10) == false) goto L16;
        r10 = null;
        goto L34
    L38:
        return ((StringBuilder) CollectionsKt.joinTo$default(r5, new StringBuilder(r2), "\n", null, null, 0, null, null, 124, null)).toString();
    L39:
        f$$ExternalSyntheticBUOutline1.m("marginPrefix must be non-blank string.");
        return null;
    }

    public static /* synthetic */ String replaceIndentByMargin$default(String r0, String r1, String r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        if ((r3 & 2) == 0) goto L9;
        r2 = "|";
    L9:
        return replaceIndentByMargin(r0, r1, r2);
    }

    @IntrinsicConstEvaluation
    public static String trimIndent(String r1) {
        return replaceIndent(r1, _UrlKt.FRAGMENT_ENCODE_SET);
    }

    @IntrinsicConstEvaluation
    public static final String trimMargin(String r1, String r2) {
        return replaceIndentByMargin(r1, _UrlKt.FRAGMENT_ENCODE_SET, r2);
    }

    public static /* synthetic */ String trimMargin$default(String r0, String r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = "|";
    L6:
        return trimMargin(r0, r1);
    }
}
