package org.vosk;

/* loaded from: classes6.dex */
public enum LogLevel extends Enum<LogLevel> {
    private static final /* synthetic */ LogLevel[] $VALUES = null;
    public static final LogLevel DEBUG = null;
    public static final LogLevel INFO = null;
    public static final LogLevel WARNINGS = null;
    private final int value;

    private static /* synthetic */ LogLevel[] $values() {
        return new LogLevel[]{WARNINGS, INFO, DEBUG};
    }

    static {
        WARNINGS = new LogLevel("WARNINGS", 0, -1);
        INFO = new LogLevel("INFO", 1, 0);
        DEBUG = new LogLevel("DEBUG", 2, 1);
        $VALUES = $values();
    }

    LogLevel(String r1, int r2, int r3) {
        this.value = r3;
    }

    public static LogLevel valueOf(String r1) {
        return (LogLevel) Enum.valueOf(LogLevel.class, r1);
    }

    public static LogLevel[] values() {
        return (LogLevel[]) $VALUES.clone();
    }

    public int getValue() {
        return this.value;
    }
}
