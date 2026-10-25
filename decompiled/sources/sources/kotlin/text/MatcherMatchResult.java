package kotlin.text;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.List;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.ranges.IntRange;
import kotlin.text.MatchResult;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\f\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\b\u001a\u00020\t8BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\f\u001a\u00020\r8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\u0010\u001a\u00020\u00118VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\u0014\u001a\u00020\u0015X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0019X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u001b\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00110\u00198VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001e"}, d2 = {"Lkotlin/text/MatcherMatchResult;", "Lkotlin/text/MatchResult;", "matcher", "Ljava/util/regex/Matcher;", PluginsConstants.Settings.TYPE_INPUT, _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/util/regex/Matcher;Ljava/lang/CharSequence;)V", "matchResult", "Ljava/util/regex/MatchResult;", "getMatchResult", "()Ljava/util/regex/MatchResult;", "range", "Lkotlin/ranges/IntRange;", "getRange", "()Lkotlin/ranges/IntRange;", "value", _UrlKt.FRAGMENT_ENCODE_SET, "getValue", "()Ljava/lang/String;", "groups", "Lkotlin/text/MatchGroupCollection;", "getGroups", "()Lkotlin/text/MatchGroupCollection;", "groupValues_", _UrlKt.FRAGMENT_ENCODE_SET, "groupValues", "getGroupValues", "()Ljava/util/List;", "next", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
final class MatcherMatchResult implements MatchResult {
    private List<String> groupValues_;
    private final MatchGroupCollection groups;
    private final CharSequence input;
    private final Matcher matcher;

    public MatcherMatchResult(Matcher r1, CharSequence r2) {
        this.matcher = r1;
        this.input = r2;
        this.groups = new MatcherMatchResult$groups$1(this);
    }

    public static final /* synthetic */ java.util.regex.MatchResult access$getMatchResult(MatcherMatchResult r0) {
        return r0.getMatchResult();
    }

    private final java.util.regex.MatchResult getMatchResult() {
        return this.matcher;
    }

    @Override // kotlin.text.MatchResult
    public /* bridge */ MatchResult.Destructured getDestructured() {
        return MatchResult.DefaultImpls.getDestructured(this);
    }

    @Override // kotlin.text.MatchResult
    public List<String> getGroupValues() {
        if (this.groupValues_ != null) goto L6;
        this.groupValues_ = new MatcherMatchResult$groupValues$1(this);
    L6:
        return this.groupValues_;
    }

    @Override // kotlin.text.MatchResult
    public MatchGroupCollection getGroups() {
        return this.groups;
    }

    @Override // kotlin.text.MatchResult
    public IntRange getRange() {
        return RegexKt.access$range(getMatchResult());
    }

    @Override // kotlin.text.MatchResult
    public String getValue() {
        return getMatchResult().group();
    }

    @Override // kotlin.text.MatchResult
    public MatchResult next() {
        int r0 = getMatchResult().end();
        if (getMatchResult().end() != getMatchResult().start()) goto L5;
        int r1 = 1;
    L6:
        int r02 = r0 + r1;
        if (r02 <= this.input.length()) goto L9;
        return null;
    L9:
        return RegexKt.access$findNext(this.matcher.pattern().matcher(this.input), r02, this.input);
    L5:
        r1 = 0;
        goto L6
    }
}
