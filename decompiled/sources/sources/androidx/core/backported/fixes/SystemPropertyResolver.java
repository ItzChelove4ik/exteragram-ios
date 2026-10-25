package androidx.core.backported.fixes;

import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002R!\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0015"}, d2 = {"Landroidx/core/backported/fixes/SystemPropertyResolver;", "Landroidx/core/backported/fixes/StatusResolver;", "<init>", "()V", "aliases", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "getAliases", "()Ljava/util/Set;", "aliases$delegate", "Lkotlin/Lazy;", "getStatus", "Landroidx/core/backported/fixes/Status;", "ki", "Landroidx/core/backported/fixes/KnownIssue;", "initAliases", "parseLongListString", _UrlKt.FRAGMENT_ENCODE_SET, "s", _UrlKt.FRAGMENT_ENCODE_SET, "getAliasBitsetString", "core-backported-fixes"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SystemPropertyResolver implements StatusResolver {
    private final Lazy aliases$delegate;

    public static Set $r8$lambda$WUGZ1J69Ga10Y7fYyHq8WrTbPFQ(SystemPropertyResolver r0) {
        return r0.initAliases();
    }

    public SystemPropertyResolver() {
        this.aliases$delegate = LazyKt.lazy(new SystemPropertyResolver$$ExternalSyntheticLambda0(this));
    }

    private final String getAliasBitsetString() {
        Class<?> r1 = Class.forName("android.os.SystemProperties");     // Catch: Exception -> L5
        return (String) r1.getMethod("get", new Class[]{String.class, String.class}).invoke(r1, new Object[]{"ro.build.backported_fixes.alias_bitset.long_list", _UrlKt.FRAGMENT_ENCODE_SET});
    L5:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private final Set<Integer> initAliases() {
        BitSet r3 = BitSet.valueOf(parseLongListString(getAliasBitsetString()));
        int r0 = r3.size();
        if (r0 == 0) goto L5;
        Set r02 = SetsKt.createSetBuilder(r0);
        int r1 = 0;
    L7:
        if (r1 < 0) goto L16;
        if (r3.get(r1) == false) goto L12;
        r02.add(Integer.valueOf(r1));
    L12:
        if (r1 == Integer.MAX_VALUE) goto L16;
        r1 = r3.nextSetBit(r1 + 1);
    L16:
        return SetsKt.build(r02);
    L5:
        return SetsKt.emptySet();
    }

    private final long[] parseLongListString(String r8) {
        List r7 = CollectionsKt.createListBuilder();
        Iterator r82 = StringsKt.split$default(r8, new char[]{','}, false, 0, 6, null).iterator();
    L4:
        if (r82.hasNext() == false) goto L9;
        r7.add(Long.valueOf(Long.parseLong((String) r82.next())));     // Catch: NumberFormatException -> L10
    L9:
        return CollectionsKt.toLongArray(CollectionsKt.build(r7));
    }

    public final Set<Integer> getAliases() {
        return (Set) this.aliases$delegate.getValue();
    }

    @Override // androidx.core.backported.fixes.StatusResolver
    public Status getStatus(KnownIssue r2) {
        if (r2.getAlias$core_backported_fixes() != null) goto L7;
        return Status.Unknown;
    L7:
        if (getAliases().contains(r2.getAlias$core_backported_fixes()) == false) goto L11;
        return Status.Fixed;
    L11:
        return Status.NotFixed;
    }
}
