package androidx.emoji2.text;

import android.annotation.SuppressLint;
import android.os.Build;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
abstract class EmojiExclusions {

    public static class EmojiExclusions_Api34 {
        public static Set<int[]> getExclusions() {
            return EmojiExclusions_Reflections.getExclusions();
        }
    }

    public static class EmojiExclusions_Reflections {
        @SuppressLint({"BanUncheckedReflection"})
        public static Set<int[]> getExclusions() {
            Object r0 = Class.forName("android.text.EmojiConsistency").getMethod("getEmojiConsistencySet", null).invoke(null, null);     // Catch: Throwable -> L13
            if (r0 == null) goto L5;
            Set<int[]> r02 = (Set) r0;     // Catch: Throwable -> L13
            Iterator<int[]> r1 = r02.iterator();     // Catch: Throwable -> L13
        L8:
            if (r1.hasNext() == false) goto L19;
            if ((r1.next() instanceof int[]) == true) goto L8;
            return Collections.EMPTY_SET;
        L19:
            return r02;
        L5:
            return Collections.EMPTY_SET;
        L14:
            return Collections.EMPTY_SET;
        }
    }

    public static Set<int[]> getEmojiExclusions() {
        if (Build.VERSION.SDK_INT < 34) goto L7;
        return EmojiExclusions_Api34.getExclusions();
    L7:
        return EmojiExclusions_Reflections.getExclusions();
    }
}
