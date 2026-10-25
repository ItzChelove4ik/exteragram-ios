package okhttp3.internal.publicsuffix;

import java.net.IDN;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal.url._UrlKt;
import okio.ByteString;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0007J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n2\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;", _UrlKt.FRAGMENT_ENCODE_SET, "publicSuffixList", "Lokhttp3/internal/publicsuffix/PublicSuffixList;", "<init>", "(Lokhttp3/internal/publicsuffix/PublicSuffixList;)V", "getEffectiveTldPlusOne", _UrlKt.FRAGMENT_ENCODE_SET, "domain", "splitDomain", _UrlKt.FRAGMENT_ENCODE_SET, "findMatchingRule", "domainLabels", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class PublicSuffixDatabase {
    public static final Companion Companion = null;
    private static final char EXCEPTION_MARKER = '!';
    private static final List<String> PREVAILING_RULE = null;
    private static final ByteString WILDCARD_LABEL = null;
    private static PublicSuffixDatabase instance;
    private final PublicSuffixList publicSuffixList;

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\r\u001a\u00020\fJ)\u0010\u000e\u001a\u0004\u0018\u00010\b*\u00020\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002¢\u0006\u0002\u0010\u0013J\r\u0010\u0014\u001a\u00020\u0015H\u0000¢\u0006\u0002\b\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lokhttp3/internal/publicsuffix/PublicSuffixDatabase$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "WILDCARD_LABEL", "Lokio/ByteString;", "PREVAILING_RULE", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "EXCEPTION_MARKER", _UrlKt.FRAGMENT_ENCODE_SET, "instance", "Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;", "get", "binarySearch", "labels", _UrlKt.FRAGMENT_ENCODE_SET, "labelIndex", _UrlKt.FRAGMENT_ENCODE_SET, "(Lokio/ByteString;[Lokio/ByteString;I)Ljava/lang/String;", "resetForTests", _UrlKt.FRAGMENT_ENCODE_SET, "resetForTests$okhttp", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ String access$binarySearch(Companion r0, ByteString r1, ByteString[] r2, int r3) {
            return r0.binarySearch(r1, r2, r3);
        }

        private final String binarySearch(ByteString r18, ByteString[] r19, int r20) {
            int r2 = r18.size();
            int r4 = 0;
        L3:
            if (r4 >= r2) goto L43;
            int r5 = (r4 + r2) / 2;
        L6:
            if (r5 <= (-1)) goto L10;
            if (r18.getByte(r5) == 10) goto L10;
            r5 = r5 - 1;
        L10:
            int r8 = r5 + 1;
            int r10 = 1;
        L11:
            int r11 = r8 + r10;
            if (r18.getByte(r11) == 10) goto L14;
            r10 = r10 + 1;
            goto L11
        L14:
            int r6 = r11 - r8;
            int r12 = r20;
            boolean r102 = false;
            int r13 = 0;
            int r14 = 0;
        L16:
            if (r102 == false) goto L18;
            int r103 = 46;
            boolean r3 = false;
        L19:
            int r104 = r103 - _UtilCommonKt.and(r18.getByte(r8 + r14), 255);
            if (r104 != 0) goto L30;
            r14 = r14 + 1;
            r13 = r13 + 1;
            if (r14 == r6) goto L30;
            if (r19[r12].size() == r13) goto L26;
            r102 = r3;
            goto L16
        L26:
            if (r12 == (r19.length - 1)) goto L30;
            r12 = r12 + 1;
            r102 = true;
            r13 = -1;
        L30:
            if (r104 < 0) goto L31;
            if (r104 > 0) goto L33;
            int r32 = r6 - r14;
            int r7 = r19[r12].size() - r13;
            int r122 = r12 + 1;
            int r9 = r19.length;
        L35:
            if (r122 >= r9) goto L37;
            r7 = r7 + r19[r122].size();
            r122 = r122 + 1;
            goto L35
        L37:
            if (r7 < r32) goto L31;
            if (r7 > r32) goto L33;
            return r18.substring(r8, r6 + r8).string(Charsets.UTF_8);
        L33:
            r4 = r11 + 1;
        L31:
            r2 = r5;
            goto L3
        L18:
            boolean r16 = r102;
            r103 = _UtilCommonKt.and(r19[r12].getByte(r13), 255);
            r3 = r16;
            goto L19
        L43:
            return null;
        }

        public final PublicSuffixDatabase get() {
            return PublicSuffixDatabase.access$getInstance$cp();
        }

        public final void resetForTests$okhttp() {
            PublicSuffixDatabase.access$setInstance$cp(new PublicSuffixDatabase(PublicSuffixList_androidKt.getDefault(PublicSuffixList.Companion)));
        }
    }

    static {
        Companion = new Companion(null);
        WILDCARD_LABEL = ByteString.Companion.of(new byte[]{42});
        PREVAILING_RULE = CollectionsKt.listOf("*");
        instance = new PublicSuffixDatabase(PublicSuffixList_androidKt.getDefault(PublicSuffixList.Companion));
    }

    public PublicSuffixDatabase(PublicSuffixList r1) {
        this.publicSuffixList = r1;
    }

    public static final /* synthetic */ PublicSuffixDatabase access$getInstance$cp() {
        return instance;
    }

    public static final /* synthetic */ void access$setInstance$cp(PublicSuffixDatabase r0) {
        instance = r0;
    }

    private final List<String> findMatchingRule(List<String> r15) {
        this.publicSuffixList.ensureLoaded();
        int r0 = r15.size();
        ByteString[] r1 = new ByteString[r0];
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L5;
        r1[r3] = ByteString.Companion.encodeUtf8(r15.get(r3));
        r3 = r3 + 1;
        goto L3
    L5:
        int r152 = 0;
    L6:
        String r32 = null;
        if (r152 >= r0) goto L12;
        String r4 = Companion.access$binarySearch(Companion, this.publicSuffixList.getBytes(), r1, r152);
        if (r4 != null) goto L14;
        r152 = r152 + 1;
    L14:
        if (r0 <= 1) goto L21;
        ByteString[] r5 = (ByteString[]) r1.clone();
        int r6 = r5.length - 1;
        int r7 = 0;
    L16:
        if (r7 >= r6) goto L21;
        r5[r7] = WILDCARD_LABEL;
        String r8 = Companion.access$binarySearch(Companion, this.publicSuffixList.getBytes(), r5, r7);
        if (r8 != null) goto L19;
        r7 = r7 + 1;
        goto L16
    L19:
        String r9 = r8;
    L22:
        if (r9 == null) goto L30;
        int r02 = r0 - 1;
        int r52 = 0;
    L24:
        if (r52 >= r02) goto L30;
        String r62 = Companion.access$binarySearch(Companion, this.publicSuffixList.getExceptionBytes(), r1, r52);
        if (r62 != null) goto L27;
        r52 = r52 + 1;
        goto L24
    L27:
        r32 = r62;
    L30:
        if (r32 != null) goto L32;
        if (r4 != null) goto L37;
        if (r9 != null) goto L37;
        return PREVAILING_RULE;
    L37:
        if (r4 == null) goto L40;
        List<String> r03 = StringsKt.split$default(r4, new char[]{'.'}, false, 0, 6, null);
        if (r03 == null) goto L40;
    L41:
        if (r9 == null) goto L44;
        List<String> r14 = StringsKt.split$default(r9, new char[]{'.'}, false, 0, 6, null);
        if (r14 == null) goto L44;
    L46:
        if (r03.size() <= r14.size()) goto L48;
        return r03;
    L48:
        return r14;
    L44:
        r14 = CollectionsKt.emptyList();
    L40:
        r03 = CollectionsKt.emptyList();
        goto L41
    L32:
        return StringsKt.split$default("!".concat(r32), new char[]{'.'}, false, 0, 6, null);
    L21:
        r9 = null;
        goto L22
    L12:
        r4 = null;
        goto L14
    }

    private final List<String> splitDomain(String r7) {
        List<String> r72 = StringsKt.split$default(r7, new char[]{'.'}, false, 0, 6, null);
        if (Intrinsics.areEqual(CollectionsKt.last(r72), _UrlKt.FRAGMENT_ENCODE_SET) == true) goto L5;
        return r72;
    L5:
        return CollectionsKt.dropLast(r72, 1);
    }

    public final String getEffectiveTldPlusOne(String r11) {
        List<String> r0 = splitDomain(IDN.toUnicode(r11));
        List<String> r1 = findMatchingRule(r0);
        if (r0.size() != r1.size()) goto L9;
        if (r1.get(0).charAt(0) == '!') goto L9;
        return null;
    L9:
        if (r1.get(0).charAt(0) != '!') goto L12;
        int r02 = r0.size();
        int r12 = r1.size();
    L11:
        int r03 = r02 - r12;
        return SequencesKt.joinToString$default(SequencesKt.drop(CollectionsKt.asSequence(splitDomain(r11)), r03), ".", null, null, 0, null, null, 62, null);
    L12:
        r02 = r0.size();
        r12 = r1.size() + 1;
        goto L11
    }
}
