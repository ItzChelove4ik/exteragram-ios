package kotlin.text;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ResultKt;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import okhttp3.internal.http.HttpStatusCodesKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 52\u00060\u0001j\u0002`\u0002:\u000245B\u0011\bA\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\bV\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\tB\u0019\bV\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\u0005\u0010\fB\u001f\bV\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000e¢\u0006\u0004\b\u0005\u0010\u000fJ\u0012\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0086\u0084\u0004J\u0012\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0086\u0080\u0004J\u001e\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u001c\u001a\u00020\u001dH\u0086\u0080\u0004J\"\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001f2\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u001c\u001a\u00020\u001dH\u0086\u0080\u0004J\u0014\u0010 \u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0086\u0080\u0004J\u001c\u0010!\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u001dH\u0087\u0080\u0004J\u001a\u0010#\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u001dH\u0087\u0080\u0004J\u001a\u0010$\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\bH\u0086\u0080\u0004J&\u0010$\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00180'H\u0086\u0080\u0004J\u001a\u0010(\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\bH\u0086\u0080\u0004J\"\u0010)\u001a\b\u0012\u0004\u0012\u00020\b0*2\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010+\u001a\u00020\u001dH\u0086\u0080\u0004J\"\u0010,\u001a\b\u0012\u0004\u0012\u00020\b0\u001f2\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010+\u001a\u00020\u001dH\u0087\u0080\u0004J\n\u0010-\u001a\u00020\bH\u0096\u0080\u0004J\n\u0010.\u001a\u00020\u0004H\u0086\u0080\u0004J\n\u0010/\u001a\u000200H\u0082\u0080\u0004J\u0012\u00101\u001a\u0002022\u0006\u0010\u0017\u001a\u000203H\u0082\u0080\u0004R\u000f\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u0007\u001a\u00020\b8FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u000eX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000e8FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u00066"}, d2 = {"Lkotlin/text/Regex;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "nativePattern", "Ljava/util/regex/Pattern;", "<init>", "(Ljava/util/regex/Pattern;)V", "pattern", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/String;)V", "option", "Lkotlin/text/RegexOption;", "(Ljava/lang/String;Lkotlin/text/RegexOption;)V", "options", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/String;Ljava/util/Set;)V", "getPattern", "()Ljava/lang/String;", "_options", "getOptions", "()Ljava/util/Set;", "matches", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.TYPE_INPUT, _UrlKt.FRAGMENT_ENCODE_SET, "containsMatchIn", "find", "Lkotlin/text/MatchResult;", "startIndex", _UrlKt.FRAGMENT_ENCODE_SET, "findAll", "Lkotlin/sequences/Sequence;", "matchEntire", "matchAt", "index", "matchesAt", "replace", "replacement", "transform", "Lkotlin/Function1;", "replaceFirst", "split", _UrlKt.FRAGMENT_ENCODE_SET, "limit", "splitToSequence", "toString", "toPattern", "writeReplace", _UrlKt.FRAGMENT_ENCODE_SET, "readObject", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/io/ObjectInputStream;", "Serialized", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRegex.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Regex.kt\nkotlin/text/Regex\n+ 2 Regex.kt\nkotlin/text/RegexKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,420:1\n24#2,3:421\n1#3:424\n*S KotlinDebug\n*F\n+ 1 Regex.kt\nkotlin/text/Regex\n*L\n105#1:421,3\n*E\n"})
/* loaded from: classes.dex */
public final class Regex implements Serializable {
    public static final Companion Companion = null;
    private Set<? extends RegexOption> _options;
    private final Pattern nativePattern;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0086\u0080\u0004J\u0012\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007H\u0086\u0080\u0004J\u0012\u0010\t\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007H\u0086\u0080\u0004J\u0012\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0082\u0080\u0004¨\u0006\r"}, d2 = {"Lkotlin/text/Regex$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "fromLiteral", "Lkotlin/text/Regex;", "literal", _UrlKt.FRAGMENT_ENCODE_SET, "escape", "escapeReplacement", "ensureUnicodeCase", _UrlKt.FRAGMENT_ENCODE_SET, "flags", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ int access$ensureUnicodeCase(Companion r0, int r1) {
            return r0.ensureUnicodeCase(r1);
        }

        private final int ensureUnicodeCase(int r1) {
            if ((r1 & 2) != 0) goto L5;
            return r1;
        L5:
            return r1 | 64;
        }

        public final String escape(String r1) {
            return Pattern.quote(r1);
        }

        public final String escapeReplacement(String r1) {
            return Matcher.quoteReplacement(r1);
        }

        public final Regex fromLiteral(String r2) {
            return new Regex(r2, RegexOption.LITERAL);
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u0000 \u000f2\u00060\u0001j\u0002`\u0002:\u0001\u000fB\u0019\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\r\u001a\u00020\u000eH\u0082\u0080\u0004R\u0015\u0010\u0003\u001a\u00020\u0004X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0005\u001a\u00020\u0006X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lkotlin/text/Regex$Serialized;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "pattern", _UrlKt.FRAGMENT_ENCODE_SET, "flags", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "getPattern", "()Ljava/lang/String;", "getFlags", "()I", "readResolve", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class Serialized implements Serializable {
        public static final Companion Companion = null;
        private static final long serialVersionUID = 0;
        private final int flags;
        private final String pattern;

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lkotlin/text/Regex$Serialized$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "serialVersionUID", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }
        }

        static {
            Companion = new Companion(null);
        }

        public Serialized(String r1, int r2) {
            this.pattern = r1;
            this.flags = r2;
        }

        private final Object readResolve() {
            return new Regex(Pattern.compile(this.pattern, this.flags));
        }

        public final int getFlags() {
            return this.flags;
        }

        public final String getPattern() {
            return this.pattern;
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    /* renamed from: kotlin.text.Regex$findAll$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<MatchResult, MatchResult> {
        public static final AnonymousClass2 INSTANCE = null;

        static {
            INSTANCE = new AnonymousClass2();
        }

        public AnonymousClass2() {
            super(1, MatchResult.class, "next", "next()Lkotlin/text/MatchResult;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ MatchResult invoke(MatchResult r1) {
            return invoke2(r1);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final MatchResult invoke2(MatchResult r1) {
            return r1.next();
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/sequences/SequenceScope;", _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "kotlin.text.Regex$splitToSequence$1", f = "Regex.kt", i = {0, 0, 1, 1, 1, 1, 2, 2, 2, 2}, l = {296, 304, HttpStatusCodesKt.HTTP_PERM_REDIRECT}, m = "invokeSuspend", n = {"$this$sequence", "matcher", "$this$sequence", "matcher", "nextStart", "splitCount", "$this$sequence", "matcher", "nextStart", "splitCount"}, nl = {297, 305, 309}, s = {"L$0", "L$1", "L$0", "L$1", "I$0", "I$1", "L$0", "L$1", "I$0", "I$1"}, v = 2)
    /* renamed from: kotlin.text.Regex$splitToSequence$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super String>, Continuation<? super Unit>, Object> {
        final /* synthetic */ CharSequence $input;
        final /* synthetic */ int $limit;
        int I$0;
        int I$1;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ Regex this$0;

        public AnonymousClass1(Regex r1, CharSequence r2, int r3, Continuation<? super AnonymousClass1> r4) {
            this.this$0 = r1;
            this.$input = r2;
            this.$limit = r3;
            super(2, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r4, Continuation<?> r5) {
            AnonymousClass1 r0 = new AnonymousClass1(this.this$0, this.$input, this.$limit, r5);
            r0.L$0 = r4;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(SequenceScope<? super String> r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(SequenceScope<? super String> r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0074 -> B:22:0x0075). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r11) {
            SequenceScope r0 = (SequenceScope) this.L$0;
            Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = this.label;
            if (r2 == 0) goto L12;
            if (r2 == 1) goto L11;
            if (r2 == 2) goto L10;
            if (r2 != 3) goto L8;
            Matcher r10 = (Matcher) this.L$1;
            ResultKt.throwOnFailure(r11);
        L32:
            return Unit.INSTANCE;
        L8:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L10:
            int r22 = this.I$1;
            Matcher r6 = (Matcher) this.L$1;
            ResultKt.throwOnFailure(r11);
        L22:
            int r112 = r6.end();
            int r23 = r22 + 1;
            if (r23 != (this.$limit - 1)) goto L25;
        L28:
            CharSequence r4 = this.$input;
            String r42 = r4.subSequence(r112, r4.length()).toString();
            this.L$0 = SpillingKt.nullOutSpilledVariable(r0);
            this.L$1 = SpillingKt.nullOutSpilledVariable(r6);
            this.I$0 = r112;
            this.I$1 = r23;
            this.label = 3;
            if (r0.yield(r42, this) != r1) goto L32;
        L35:
            return r1;
        L25:
            if (r6.find() == false) goto L28;
            int r24 = r112;
            int r113 = r23;
        L18:
            String r7 = this.$input.subSequence(r24, r6.start()).toString();
            this.L$0 = r0;
            this.L$1 = r6;
            this.I$0 = r24;
            this.I$1 = r113;
            this.label = 2;
            if (r0.yield(r7, this) == r1) goto L35;
            r22 = r113;
            goto L22
        L11:
            Matcher r102 = (Matcher) this.L$1;
            ResultKt.throwOnFailure(r11);
        L37:
            return Unit.INSTANCE;
        L12:
            ResultKt.throwOnFailure(r11);
            Matcher r114 = Regex.access$getNativePattern$p(this.this$0).matcher(this.$input);
            if (this.$limit != 1) goto L15;
        L33:
            String r25 = this.$input.toString();
            this.L$0 = SpillingKt.nullOutSpilledVariable(r0);
            this.L$1 = SpillingKt.nullOutSpilledVariable(r114);
            this.label = 1;
            if (r0.yield(r25, this) != r1) goto L37;
        L15:
            if (r114.find() == false) goto L33;
            r24 = 0;
            r6 = r114;
            r113 = 0;
            goto L18
        }
    }

    /* renamed from: $r8$lambda$e-xakchuIqKiaOGSDowV8aNl-y8, reason: not valid java name */
    public static MatchResult m3335$r8$lambda$exakchuIqKiaOGSDowV8aNly8(Regex r0, CharSequence r1, int r2) {
        return r0.find(r1, r2);
    }

    static {
        Companion = new Companion(null);
    }

    public Regex(String r1) {
        this(Pattern.compile(r1));
    }

    public Regex(String r2, Set<? extends RegexOption> r3) {
        this(Pattern.compile(r2, Companion.access$ensureUnicodeCase(Companion, RegexKt.access$toInt(r3))));
    }

    public Regex(String r2, RegexOption r3) {
        this(Pattern.compile(r2, Companion.access$ensureUnicodeCase(Companion, r3.getValue())));
    }

    @PublishedApi
    public Regex(Pattern r1) {
        this.nativePattern = r1;
    }

    public static final /* synthetic */ Pattern access$getNativePattern$p(Regex r0) {
        return r0.nativePattern;
    }

    public static /* synthetic */ MatchResult find$default(Regex r0, CharSequence r1, int r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = 0;
    L6:
        return r0.find(r1, r2);
    }

    public static /* synthetic */ Sequence findAll$default(Regex r0, CharSequence r1, int r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = 0;
    L6:
        return r0.findAll(r1, r2);
    }

    private final void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization is supported via proxy only");
    }

    public static /* synthetic */ List split$default(Regex r0, CharSequence r1, int r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = 0;
    L6:
        return r0.split(r1, r2);
    }

    public static /* synthetic */ Sequence splitToSequence$default(Regex r0, CharSequence r1, int r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = 0;
    L6:
        return r0.splitToSequence(r1, r2);
    }

    private final Object writeReplace() {
        return new Serialized(this.nativePattern.pattern(), this.nativePattern.flags());
    }

    public final boolean containsMatchIn(CharSequence r1) {
        return this.nativePattern.matcher(r1).find();
    }

    public final MatchResult find(CharSequence r1, int r2) {
        return RegexKt.access$findNext(this.nativePattern.matcher(r1), r2, r1);
    }

    public final Sequence<MatchResult> findAll(CharSequence r2, int r3) {
        if (r3 >= 0) goto L4;
    L7:
        Regex$$ExternalSyntheticBUOutline0.m("Start index out of bounds: ", r3, ", input length: ", r2.length());
        return null;
    L4:
        if (r3 > r2.length()) goto L7;
        return SequencesKt.generateSequence(new Regex$$ExternalSyntheticLambda1(this, r2, r3), AnonymousClass2.INSTANCE);
    }

    public final Set<RegexOption> getOptions() {
        Set r0 = this._options;
        if (r0 != null) goto L6;
        int r02 = this.nativePattern.flags();
        EnumSet r1 = EnumSet.allOf(RegexOption.class);
        CollectionsKt.retainAll(r1, new Regex$special$$inlined$fromInt$1(r02));
        Set<RegexOption> r03 = Collections.unmodifiableSet(r1);
        this._options = r03;
        return r03;
    L6:
        return r0;
    }

    public final String getPattern() {
        return this.nativePattern.pattern();
    }

    @SinceKotlin(version = "1.7")
    public final MatchResult matchAt(CharSequence r2, int r3) {
        Matcher r1 = this.nativePattern.matcher(r2).useAnchoringBounds(false).useTransparentBounds(true).region(r3, r2.length());
        if (r1.lookingAt() == true) goto L5;
        return null;
    L5:
        return new MatcherMatchResult(r1, r2);
    }

    public final MatchResult matchEntire(CharSequence r1) {
        return RegexKt.access$matchEntire(this.nativePattern.matcher(r1), r1);
    }

    public final boolean matches(CharSequence r1) {
        return this.nativePattern.matcher(r1).matches();
    }

    @SinceKotlin(version = "1.7")
    public final boolean matchesAt(CharSequence r2, int r3) {
        return this.nativePattern.matcher(r2).useAnchoringBounds(false).useTransparentBounds(true).region(r3, r2.length()).lookingAt();
    }

    public final String replace(CharSequence r1, String r2) {
        return this.nativePattern.matcher(r1).replaceAll(r2);
    }

    public final String replace(CharSequence r5, Function1<? super MatchResult, ? extends CharSequence> r6) {
        int r2 = 0;
        MatchResult r4 = find$default(this, r5, 0, 2, null);
        if (r4 == null) goto L5;
        int r0 = r5.length();
        StringBuilder r1 = new StringBuilder(r0);
    L7:
        r1.append(r5, r2, r4.getRange().getStart().intValue());
        r1.append(r6.invoke(r4));
        r2 = r4.getRange().getEndInclusive().intValue() + 1;
        r4 = r4.next();
        if (r2 >= r0) goto L10;
        if (r4 != null) goto L7;
    L10:
        if (r2 >= r0) goto L13;
        r1.append(r5, r2, r0);
    L13:
        return r1.toString();
    L5:
        return r5.toString();
    }

    public final String replaceFirst(CharSequence r1, String r2) {
        return this.nativePattern.matcher(r1).replaceFirst(r2);
    }

    public final List<String> split(CharSequence r4, int r5) {
        StringsKt__StringsKt.requireNonNegativeLimit(r5);
        Matcher r3 = this.nativePattern.matcher(r4);
        if (r5 == 1) goto L20;
        if (r3.find() == false) goto L20;
        int r2 = 10;
        if (r5 <= 0) goto L10;
        r2 = RangesKt.coerceAtMost(r5, 10);
    L10:
        ArrayList r1 = new ArrayList(r2);
        int r52 = r5 - 1;
        int r0 = 0;
    L11:
        r1.add(r4.subSequence(r0, r3.start()).toString());
        r0 = r3.end();
        if (r52 < 0) goto L16;
        if (r1.size() != r52) goto L16;
    L17:
        r1.add(r4.subSequence(r0, r4.length()).toString());
        return r1;
    L16:
        if (r3.find() == true) goto L11;
    L20:
        return CollectionsKt.listOf(r4.toString());
    }

    @SinceKotlin(version = "1.6")
    public final Sequence<String> splitToSequence(CharSequence r3, int r4) {
        StringsKt__StringsKt.requireNonNegativeLimit(r4);
        return SequencesKt.sequence(new AnonymousClass1(this, r3, r4, null));
    }

    public final Pattern toPattern() {
        return this.nativePattern;
    }

    public String toString() {
        return this.nativePattern.toString();
    }
}
