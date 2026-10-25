package kotlin.text;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u001a\b\u0086\u0081\u0002\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001cB\u0011\bB\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001b¨\u0006\u001d"}, d2 = {"Lkotlin/text/CharDirectionality;", _UrlKt.FRAGMENT_ENCODE_SET, "value", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "UNDEFINED", "LEFT_TO_RIGHT", "RIGHT_TO_LEFT", "RIGHT_TO_LEFT_ARABIC", "EUROPEAN_NUMBER", "EUROPEAN_NUMBER_SEPARATOR", "EUROPEAN_NUMBER_TERMINATOR", "ARABIC_NUMBER", "COMMON_NUMBER_SEPARATOR", "NONSPACING_MARK", "BOUNDARY_NEUTRAL", "PARAGRAPH_SEPARATOR", "SEGMENT_SEPARATOR", "WHITESPACE", "OTHER_NEUTRALS", "LEFT_TO_RIGHT_EMBEDDING", "LEFT_TO_RIGHT_OVERRIDE", "RIGHT_TO_LEFT_EMBEDDING", "RIGHT_TO_LEFT_OVERRIDE", "POP_DIRECTIONAL_FORMAT", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCharDirectionality.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CharDirectionality.kt\nkotlin/text/CharDirectionality\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,124:1\n1220#2,2:125\n1249#2,4:127\n*S KotlinDebug\n*F\n+ 1 CharDirectionality.kt\nkotlin/text/CharDirectionality\n*L\n118#1:125,2\n118#1:127,4\n*E\n"})
/* loaded from: classes5.dex */
public enum CharDirectionality extends Enum<CharDirectionality> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ CharDirectionality[] $VALUES = null;
    public static final CharDirectionality ARABIC_NUMBER = null;
    public static final CharDirectionality BOUNDARY_NEUTRAL = null;
    public static final CharDirectionality COMMON_NUMBER_SEPARATOR = null;
    public static final Companion Companion = null;
    public static final CharDirectionality EUROPEAN_NUMBER = null;
    public static final CharDirectionality EUROPEAN_NUMBER_SEPARATOR = null;
    public static final CharDirectionality EUROPEAN_NUMBER_TERMINATOR = null;
    public static final CharDirectionality LEFT_TO_RIGHT = null;
    public static final CharDirectionality LEFT_TO_RIGHT_EMBEDDING = null;
    public static final CharDirectionality LEFT_TO_RIGHT_OVERRIDE = null;
    public static final CharDirectionality NONSPACING_MARK = null;
    public static final CharDirectionality OTHER_NEUTRALS = null;
    public static final CharDirectionality PARAGRAPH_SEPARATOR = null;
    public static final CharDirectionality POP_DIRECTIONAL_FORMAT = null;
    public static final CharDirectionality RIGHT_TO_LEFT = null;
    public static final CharDirectionality RIGHT_TO_LEFT_ARABIC = null;
    public static final CharDirectionality RIGHT_TO_LEFT_EMBEDDING = null;
    public static final CharDirectionality RIGHT_TO_LEFT_OVERRIDE = null;
    public static final CharDirectionality SEGMENT_SEPARATOR = null;
    public static final CharDirectionality UNDEFINED = null;
    public static final CharDirectionality WHITESPACE = null;
    private static final Lazy<Map<Integer, CharDirectionality>> directionalityMap$delegate = null;
    private final int value;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0006H\u0086\u0080\u0004R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058BX\u0082\u0084\n¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lkotlin/text/CharDirectionality$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "directionalityMap", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/text/CharDirectionality;", "getDirectionalityMap", "()Ljava/util/Map;", "directionalityMap$delegate", "Lkotlin/Lazy;", "valueOf", "directionality", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private final Map<Integer, CharDirectionality> getDirectionalityMap() {
            return (Map) CharDirectionality.access$getDirectionalityMap$delegate$cp().getValue();
        }

        public final CharDirectionality valueOf(int r2) {
            CharDirectionality r1 = getDirectionalityMap().get(Integer.valueOf(r2));
            if (r1 == null) goto L5;
            return r1;
        L5:
            CharsKt__CharKt$$ExternalSyntheticBUOutline0.m("Directionality #", r2, " is not defined.");
            return null;
        }
    }

    public static Map $r8$lambda$v4DGHaUIucf67ksqHLHlHE3vuBU() {
        EnumEntries<CharDirectionality> r0 = getEntries();
        LinkedHashMap r2 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(r0, 10)), 16));
        Iterator<CharDirectionality> r02 = r0.iterator();
    L4:
        if (r02.hasNext() == false) goto L6;
        CharDirectionality r1 = r02.next();
        r2.put(Integer.valueOf(r1.value), r1);
        goto L4
    L6:
        return r2;
    }

    private static final /* synthetic */ CharDirectionality[] $values() {
        return new CharDirectionality[]{UNDEFINED, LEFT_TO_RIGHT, RIGHT_TO_LEFT, RIGHT_TO_LEFT_ARABIC, EUROPEAN_NUMBER, EUROPEAN_NUMBER_SEPARATOR, EUROPEAN_NUMBER_TERMINATOR, ARABIC_NUMBER, COMMON_NUMBER_SEPARATOR, NONSPACING_MARK, BOUNDARY_NEUTRAL, PARAGRAPH_SEPARATOR, SEGMENT_SEPARATOR, WHITESPACE, OTHER_NEUTRALS, LEFT_TO_RIGHT_EMBEDDING, LEFT_TO_RIGHT_OVERRIDE, RIGHT_TO_LEFT_EMBEDDING, RIGHT_TO_LEFT_OVERRIDE, POP_DIRECTIONAL_FORMAT};
    }

    static {
        UNDEFINED = new CharDirectionality("UNDEFINED", 0, -1);
        LEFT_TO_RIGHT = new CharDirectionality("LEFT_TO_RIGHT", 1, 0);
        RIGHT_TO_LEFT = new CharDirectionality("RIGHT_TO_LEFT", 2, 1);
        RIGHT_TO_LEFT_ARABIC = new CharDirectionality("RIGHT_TO_LEFT_ARABIC", 3, 2);
        EUROPEAN_NUMBER = new CharDirectionality("EUROPEAN_NUMBER", 4, 3);
        EUROPEAN_NUMBER_SEPARATOR = new CharDirectionality("EUROPEAN_NUMBER_SEPARATOR", 5, 4);
        EUROPEAN_NUMBER_TERMINATOR = new CharDirectionality("EUROPEAN_NUMBER_TERMINATOR", 6, 5);
        ARABIC_NUMBER = new CharDirectionality("ARABIC_NUMBER", 7, 6);
        COMMON_NUMBER_SEPARATOR = new CharDirectionality("COMMON_NUMBER_SEPARATOR", 8, 7);
        NONSPACING_MARK = new CharDirectionality("NONSPACING_MARK", 9, 8);
        BOUNDARY_NEUTRAL = new CharDirectionality("BOUNDARY_NEUTRAL", 10, 9);
        PARAGRAPH_SEPARATOR = new CharDirectionality("PARAGRAPH_SEPARATOR", 11, 10);
        SEGMENT_SEPARATOR = new CharDirectionality("SEGMENT_SEPARATOR", 12, 11);
        WHITESPACE = new CharDirectionality("WHITESPACE", 13, 12);
        OTHER_NEUTRALS = new CharDirectionality("OTHER_NEUTRALS", 14, 13);
        LEFT_TO_RIGHT_EMBEDDING = new CharDirectionality("LEFT_TO_RIGHT_EMBEDDING", 15, 14);
        LEFT_TO_RIGHT_OVERRIDE = new CharDirectionality("LEFT_TO_RIGHT_OVERRIDE", 16, 15);
        RIGHT_TO_LEFT_EMBEDDING = new CharDirectionality("RIGHT_TO_LEFT_EMBEDDING", 17, 16);
        RIGHT_TO_LEFT_OVERRIDE = new CharDirectionality("RIGHT_TO_LEFT_OVERRIDE", 18, 17);
        POP_DIRECTIONAL_FORMAT = new CharDirectionality("POP_DIRECTIONAL_FORMAT", 19, 18);
        CharDirectionality[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
        Companion = new Companion(null);
        directionalityMap$delegate = LazyKt.lazy(new CharDirectionality$$ExternalSyntheticLambda0());
    }

    CharDirectionality(String r1, int r2, int r3) {
        this.value = r3;
    }

    public static final /* synthetic */ Lazy access$getDirectionalityMap$delegate$cp() {
        return directionalityMap$delegate;
    }

    public static EnumEntries<CharDirectionality> getEntries() {
        return $ENTRIES;
    }

    public static CharDirectionality valueOf(String r1) {
        return (CharDirectionality) Enum.valueOf(CharDirectionality.class, r1);
    }

    public static CharDirectionality[] values() {
        return (CharDirectionality[]) $VALUES.clone();
    }

    public final int getValue() {
        return this.value;
    }
}
