package androidx.profileinstaller;

/* loaded from: classes4.dex */
enum FileSectionType extends Enum<FileSectionType> {
    private static final /* synthetic */ FileSectionType[] $VALUES = null;
    public static final FileSectionType AGGREGATION_COUNT = null;
    public static final FileSectionType CLASSES = null;
    public static final FileSectionType DEX_FILES = null;
    public static final FileSectionType EXTRA_DESCRIPTORS = null;
    public static final FileSectionType METHODS = null;
    private final long mValue;

    private static /* synthetic */ FileSectionType[] $values() {
        return new FileSectionType[]{DEX_FILES, EXTRA_DESCRIPTORS, CLASSES, METHODS, AGGREGATION_COUNT};
    }

    static {
        DEX_FILES = new FileSectionType("DEX_FILES", 0, 0);
        EXTRA_DESCRIPTORS = new FileSectionType("EXTRA_DESCRIPTORS", 1, 1);
        CLASSES = new FileSectionType("CLASSES", 2, 2);
        METHODS = new FileSectionType("METHODS", 3, 3);
        AGGREGATION_COUNT = new FileSectionType("AGGREGATION_COUNT", 4, 4);
        $VALUES = $values();
    }

    FileSectionType(String r1, int r2, long r3) {
        this.mValue = r3;
    }

    public static FileSectionType valueOf(String r1) {
        return (FileSectionType) Enum.valueOf(FileSectionType.class, r1);
    }

    public static FileSectionType[] values() {
        return (FileSectionType[]) $VALUES.clone();
    }

    public long getValue() {
        return this.mValue;
    }
}
