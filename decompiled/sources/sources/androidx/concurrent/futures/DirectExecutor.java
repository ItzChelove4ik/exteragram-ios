package androidx.concurrent.futures;

import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public enum DirectExecutor extends Enum<DirectExecutor> implements Executor {
    private static final /* synthetic */ DirectExecutor[] $VALUES = null;
    public static final DirectExecutor INSTANCE = null;

    static {
        DirectExecutor r0 = new DirectExecutor("INSTANCE", 0);
        INSTANCE = r0;
        $VALUES = new DirectExecutor[]{r0};
    }

    DirectExecutor(String r1, int r2) {
    }

    public static DirectExecutor valueOf(String r1) {
        return (DirectExecutor) Enum.valueOf(DirectExecutor.class, r1);
    }

    public static DirectExecutor[] values() {
        return (DirectExecutor[]) $VALUES.clone();
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable r1) {
        r1.run();
    }

    @Override // java.lang.Enum
    public String toString() {
        return "DirectExecutor";
    }
}
