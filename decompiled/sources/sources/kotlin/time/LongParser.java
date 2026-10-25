package kotlin.time;

import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.MediaTrack;
import com.sun.jna.Callback;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0019\bB\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007Jw\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2K\u0010\u000f\u001aG\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00160\u0010H\u0086\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\b\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\t\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0018"}, d2 = {"Lkotlin/time/LongParser;", _UrlKt.FRAGMENT_ENCODE_SET, "overflowLimit", _UrlKt.FRAGMENT_ENCODE_SET, "allowSign", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(JZ)V", "overflowThreshold", "lastDigitMax", "parse", "value", _UrlKt.FRAGMENT_ENCODE_SET, "startIndex", _UrlKt.FRAGMENT_ENCODE_SET, Callback.METHOD_NAME, "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "endIndex", MediaTrack.ROLE_SIGN, "hasOverflow", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDuration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Duration.kt\nkotlin/time/LongParser\n+ 2 Strings.kt\nkotlin/text/StringsKt__StringsKt\n*L\n1#1,1613:1\n1656#2,3:1614\n1656#2,3:1617\n*S KotlinDebug\n*F\n+ 1 Duration.kt\nkotlin/time/LongParser\n*L\n1295#1:1614,3\n1302#1:1617,3\n*E\n"})
/* loaded from: classes5.dex */
public final class LongParser {
    public static final Companion Companion = null;

    /* renamed from: default, reason: not valid java name */
    private static final LongParser f5default = null;
    private static final LongParser iso = null;
    private final boolean allowSign;
    private final long lastDigitMax;
    private final long overflowLimit;
    private final long overflowThreshold;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0015\u0010\b\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lkotlin/time/LongParser$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "iso", "Lkotlin/time/LongParser;", "getIso", "()Lkotlin/time/LongParser;", PluginsConstants.Settings.DEFAULT, "getDefault", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final LongParser getDefault() {
            return LongParser.access$getDefault$cp();
        }

        public final LongParser getIso() {
            return LongParser.access$getIso$cp();
        }
    }

    static {
        Companion = new Companion(null);
        iso = new LongParser(DurationKt.MAX_MILLIS, true);
        f5default = new LongParser(LongCompanionObject.MAX_VALUE, false);
    }

    private LongParser(long r5, boolean r7) {
        this.overflowLimit = r5;
        this.allowSign = r7;
        this.overflowThreshold = r5 / 10;
        this.lastDigitMax = r5 % 10;
    }

    public static final /* synthetic */ boolean access$getAllowSign$p(LongParser r0) {
        return r0.allowSign;
    }

    public static final /* synthetic */ LongParser access$getDefault$cp() {
        return f5default;
    }

    public static final /* synthetic */ LongParser access$getIso$cp() {
        return iso;
    }

    public static final /* synthetic */ long access$getLastDigitMax$p(LongParser r2) {
        return r2.lastDigitMax;
    }

    public static final /* synthetic */ long access$getOverflowLimit$p(LongParser r2) {
        return r2.overflowLimit;
    }

    public static final /* synthetic */ long access$getOverflowThreshold$p(LongParser r2) {
        return r2.overflowThreshold;
    }

    public final long parse(String r12, int r13, Function3<? super Integer, ? super Integer, ? super Boolean, Unit> r14) {
        if (access$getAllowSign$p(this) == false) goto L11;
        char r0 = r12.charAt(r13);
        if (r0 != '+') goto L7;
        r13 = r13 + 1;
        goto L11
    L7:
        if (r0 != '-') goto L11;
        r13 = r13 + 1;
        int r02 = -1;
    L13:
        if (r13 >= r12.length()) goto L17;
        if (r12.charAt(r13) != '0') goto L17;
        r13 = r13 + 1;
    L17:
        long r4 = 0;
    L19:
        if (r13 >= r12.length()) goto L40;
        char r2 = r12.charAt(r13);
        if ('0' > r2) goto L40;
        if (r2 >= ':') goto L40;
        int r22 = r2 - '0';
        if (r4 > access$getOverflowThreshold$p(this)) goto L33;
        if (r4 != access$getOverflowThreshold$p(this)) goto L31;
        if (r22 > access$getLastDigitMax$p(this)) goto L33;
    L31:
        r4 = r22 + ((r4 << 3) + (r4 << 1));
        r13 = r13 + 1;
    L33:
        if (r13 >= r12.length()) goto L38;
        char r1 = r12.charAt(r13);
        if ('0' > r1) goto L38;
        if (r1 >= ':') goto L38;
        r13 = r13 + 1;
    L38:
        r14.invoke(Integer.valueOf(r13), Integer.valueOf(r02), Boolean.TRUE);
        return access$getOverflowLimit$p(this);
    L40:
        r14.invoke(Integer.valueOf(r13), Integer.valueOf(r02), Boolean.FALSE);
        return r4;
    L11:
        r02 = 1;
        goto L13
    }
}
