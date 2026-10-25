package kotlin.text;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0082\u0080\u0004\u001a.\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0014\b\u0000\u0010\u0006\u0018\u0001*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00060\u00072\u0006\u0010\b\u001a\u00020\u0001H\u0082\u0088\u0004\u001a \u0010\t\u001a\u0004\u0018\u00010\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0082\u0080\u0004\u001a\u0018\u0010\u000f\u001a\u0004\u0018\u00010\n*\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0082\u0080\u0004\u001a\u000e\u0010\u0010\u001a\u00020\u0011*\u00020\u0012H\u0082\u0080\u0004\u001a\u0016\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0001H\u0082\u0080\u0004¨\u0006\u0014"}, d2 = {"toInt", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/text/FlagEnum;", "fromInt", _UrlKt.FRAGMENT_ENCODE_SET, "T", _UrlKt.FRAGMENT_ENCODE_SET, "value", "findNext", "Lkotlin/text/MatchResult;", "Ljava/util/regex/Matcher;", "from", PluginsConstants.Settings.TYPE_INPUT, _UrlKt.FRAGMENT_ENCODE_SET, "matchEntire", "range", "Lkotlin/ranges/IntRange;", "Ljava/util/regex/MatchResult;", "groupIndex", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRegex.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Regex.kt\nkotlin/text/RegexKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,420:1\n1849#2,3:421\n*S KotlinDebug\n*F\n+ 1 Regex.kt\nkotlin/text/RegexKt\n*L\n21#1:421,3\n*E\n"})
/* loaded from: classes.dex */
public final class RegexKt {
    public static final /* synthetic */ MatchResult access$findNext(Matcher r0, int r1, CharSequence r2) {
        return findNext(r0, r1, r2);
    }

    public static final /* synthetic */ MatchResult access$matchEntire(Matcher r0, CharSequence r1) {
        return matchEntire(r0, r1);
    }

    public static final /* synthetic */ IntRange access$range(java.util.regex.MatchResult r0) {
        return range(r0);
    }

    public static final /* synthetic */ IntRange access$range(java.util.regex.MatchResult r0, int r1) {
        return range(r0, r1);
    }

    public static final /* synthetic */ int access$toInt(Iterable r0) {
        return toInt(r0);
    }

    private static final MatchResult findNext(Matcher r0, int r1, CharSequence r2) {
        if (r0.find(r1) == true) goto L7;
        return null;
    L7:
        return new MatcherMatchResult(r0, r2);
    }

    private static final /* synthetic */ <T extends Enum<T> & FlagEnum> Set<T> fromInt(int r2) {
        Intrinsics.reifiedOperationMarker(4, "T");
        EnumSet r0 = EnumSet.allOf(Enum.class);
        Intrinsics.needClassReification();
        CollectionsKt.retainAll(r0, new RegexKt$fromInt$1$1(r2));
        return Collections.unmodifiableSet(r0);
    }

    private static final MatchResult matchEntire(Matcher r1, CharSequence r2) {
        if (r1.matches() == true) goto L7;
        return null;
    L7:
        return new MatcherMatchResult(r1, r2);
    }

    private static final IntRange range(java.util.regex.MatchResult r1) {
        return RangesKt.until(r1.start(), r1.end());
    }

    private static final IntRange range(java.util.regex.MatchResult r1, int r2) {
        return RangesKt.until(r1.start(r2), r1.end(r2));
    }

    private static final int toInt(Iterable<? extends FlagEnum> r2) {
        Iterator<? extends FlagEnum> r22 = r2.iterator();
        int r0 = 0;
    L4:
        if (r22.hasNext() == false) goto L6;
        r0 = r0 | r22.next().getValue();
        goto L4
    L6:
        return r0;
    }
}
