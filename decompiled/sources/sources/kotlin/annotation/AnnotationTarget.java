package kotlin.annotation;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0012\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lkotlin/annotation/AnnotationTarget;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "CLASS", "ANNOTATION_CLASS", "TYPE_PARAMETER", "PROPERTY", "FIELD", "LOCAL_VARIABLE", "VALUE_PARAMETER", "CONSTRUCTOR", "FUNCTION", "PROPERTY_GETTER", "PROPERTY_SETTER", "TYPE", "EXPRESSION", "FILE", "TYPEALIAS", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public enum AnnotationTarget extends Enum<AnnotationTarget> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ AnnotationTarget[] $VALUES = null;
    public static final AnnotationTarget ANNOTATION_CLASS = null;
    public static final AnnotationTarget CLASS = null;
    public static final AnnotationTarget CONSTRUCTOR = null;
    public static final AnnotationTarget EXPRESSION = null;
    public static final AnnotationTarget FIELD = null;
    public static final AnnotationTarget FILE = null;
    public static final AnnotationTarget FUNCTION = null;
    public static final AnnotationTarget LOCAL_VARIABLE = null;
    public static final AnnotationTarget PROPERTY = null;
    public static final AnnotationTarget PROPERTY_GETTER = null;
    public static final AnnotationTarget PROPERTY_SETTER = null;
    public static final AnnotationTarget TYPE = null;

    @SinceKotlin(version = "1.1")
    public static final AnnotationTarget TYPEALIAS = null;
    public static final AnnotationTarget TYPE_PARAMETER = null;
    public static final AnnotationTarget VALUE_PARAMETER = null;

    private static final /* synthetic */ AnnotationTarget[] $values() {
        return new AnnotationTarget[]{CLASS, ANNOTATION_CLASS, TYPE_PARAMETER, PROPERTY, FIELD, LOCAL_VARIABLE, VALUE_PARAMETER, CONSTRUCTOR, FUNCTION, PROPERTY_GETTER, PROPERTY_SETTER, TYPE, EXPRESSION, FILE, TYPEALIAS};
    }

    static {
        CLASS = new AnnotationTarget("CLASS", 0);
        ANNOTATION_CLASS = new AnnotationTarget("ANNOTATION_CLASS", 1);
        TYPE_PARAMETER = new AnnotationTarget("TYPE_PARAMETER", 2);
        PROPERTY = new AnnotationTarget("PROPERTY", 3);
        FIELD = new AnnotationTarget("FIELD", 4);
        LOCAL_VARIABLE = new AnnotationTarget("LOCAL_VARIABLE", 5);
        VALUE_PARAMETER = new AnnotationTarget("VALUE_PARAMETER", 6);
        CONSTRUCTOR = new AnnotationTarget("CONSTRUCTOR", 7);
        FUNCTION = new AnnotationTarget("FUNCTION", 8);
        PROPERTY_GETTER = new AnnotationTarget("PROPERTY_GETTER", 9);
        PROPERTY_SETTER = new AnnotationTarget("PROPERTY_SETTER", 10);
        TYPE = new AnnotationTarget("TYPE", 11);
        EXPRESSION = new AnnotationTarget("EXPRESSION", 12);
        FILE = new AnnotationTarget("FILE", 13);
        TYPEALIAS = new AnnotationTarget("TYPEALIAS", 14);
        AnnotationTarget[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    AnnotationTarget(String r1, int r2) {
    }

    public static EnumEntries<AnnotationTarget> getEntries() {
        return $ENTRIES;
    }

    public static AnnotationTarget valueOf(String r1) {
        return (AnnotationTarget) Enum.valueOf(AnnotationTarget.class, r1);
    }

    public static AnnotationTarget[] values() {
        return (AnnotationTarget[]) $VALUES.clone();
    }
}
