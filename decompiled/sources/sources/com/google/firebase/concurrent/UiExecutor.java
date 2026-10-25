package com.google.firebase.concurrent;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public enum UiExecutor extends Enum<UiExecutor> implements Executor {
    private static final /* synthetic */ UiExecutor[] $VALUES = null;

    @SuppressLint({"ThreadPoolCreation"})
    private static final Handler HANDLER = null;
    public static final UiExecutor INSTANCE = null;

    private static /* synthetic */ UiExecutor[] $values() {
        return new UiExecutor[]{INSTANCE};
    }

    static {
        INSTANCE = new UiExecutor("INSTANCE", 0);
        $VALUES = $values();
        HANDLER = new Handler(Looper.getMainLooper());
    }

    UiExecutor(String r1, int r2) {
    }

    public static UiExecutor valueOf(String r1) {
        return (UiExecutor) Enum.valueOf(UiExecutor.class, r1);
    }

    public static UiExecutor[] values() {
        return (UiExecutor[]) $VALUES.clone();
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable r1) {
        HANDLER.post(r1);
    }
}
