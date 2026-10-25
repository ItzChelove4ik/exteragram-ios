package androidx.emoji2.viewsintegration;

import android.annotation.SuppressLint;
import android.text.Editable;
import androidx.emoji2.text.SpannableBuilder;

/* loaded from: classes3.dex */
final class EmojiEditableFactory extends Editable.Factory {
    private static final Object INSTANCE_LOCK = null;
    private static volatile Editable.Factory sInstance;
    private static Class<?> sWatcherClass;

    static {
        INSTANCE_LOCK = new Object();
    }

    @SuppressLint({"PrivateApi"})
    private EmojiEditableFactory() {
        sWatcherClass = Class.forName("android.text.DynamicLayout$ChangeWatcher", false, EmojiEditableFactory.class.getClassLoader());     // Catch: Throwable -> L5
        return;
    }

    public static Editable.Factory getInstance() {
        if (sInstance != null) goto L16;
        Object r0 = INSTANCE_LOCK;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L7:
        if (sInstance != null) goto L11;
        sInstance = new EmojiEditableFactory();     // Catch: Throwable -> L9
    L11:
        monitor-exit(r0);     // Catch: Throwable -> L9
    L16:
        return sInstance;
    }

    @Override // android.text.Editable.Factory
    public Editable newEditable(CharSequence r2) {
        Class<?> r0 = sWatcherClass;
        if (r0 == null) goto L7;
        return SpannableBuilder.create(r0, r2);
    L7:
        return super.newEditable(r2);
    }
}
