package androidx.emoji2.text;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.Editable;
import android.text.SpanWatcher;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import androidx.core.util.Preconditions;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes3.dex */
public final class SpannableBuilder extends SpannableStringBuilder {
    private final Class<?> mWatcherClass;
    private final List<WatcherWrapper> mWatchers;

    public static class WatcherWrapper implements TextWatcher, SpanWatcher {
        private final AtomicInteger mBlockCalls;
        final Object mObject;

        public WatcherWrapper(Object r3) {
            this.mBlockCalls = new AtomicInteger(0);
            this.mObject = r3;
        }

        private boolean isEmojiSpan(Object r1) {
            return r1 instanceof EmojiSpan;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
            ((TextWatcher) this.mObject).afterTextChanged(r1);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            ((TextWatcher) this.mObject).beforeTextChanged(r1, r2, r3, r4);
        }

        public final void blockCalls() {
            this.mBlockCalls.incrementAndGet();
        }

        @Override // android.text.SpanWatcher
        public void onSpanAdded(Spannable r2, Object r3, int r4, int r5) {
            if (this.mBlockCalls.get() > 0) goto L5;
        L7:
            ((SpanWatcher) this.mObject).onSpanAdded(r2, r3, r4, r5);
            return;
        L5:
            if (isEmojiSpan(r3) == false) goto L7;
        }

        @Override // android.text.SpanWatcher
        public void onSpanChanged(Spannable r9, Object r10, int r11, int r12, int r13, int r14) {
            if (this.mBlockCalls.get() <= 0) goto L8;
            if (isEmojiSpan(r10) == false) goto L8;
            return;
        L8:
            if (Build.VERSION.SDK_INT < 28) goto L10;
        L14:
            int r4 = r11;
            int r6 = r13;
        L15:
            ((SpanWatcher) this.mObject).onSpanChanged(r9, r10, r4, r12, r6, r14);
            return;
        L10:
            if (r11 <= r12) goto L12;
            r11 = 0;
        L12:
            if (r13 <= r14) goto L14;
            r4 = r11;
            r6 = 0;
            goto L15
        }

        @Override // android.text.SpanWatcher
        public void onSpanRemoved(Spannable r2, Object r3, int r4, int r5) {
            if (this.mBlockCalls.get() > 0) goto L5;
        L7:
            ((SpanWatcher) this.mObject).onSpanRemoved(r2, r3, r4, r5);
            return;
        L5:
            if (isEmojiSpan(r3) == false) goto L7;
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            ((TextWatcher) this.mObject).onTextChanged(r1, r2, r3, r4);
        }

        public final void unblockCalls() {
            this.mBlockCalls.decrementAndGet();
        }
    }

    public SpannableBuilder(Class<?> r1, CharSequence r2) {
        super(r2);
        this.mWatchers = new ArrayList();
        Preconditions.checkNotNull(r1, "watcherClass cannot be null");
        this.mWatcherClass = r1;
    }

    public SpannableBuilder(Class<?> r1, CharSequence r2, int r3, int r4) {
        super(r2, r3, r4);
        this.mWatchers = new ArrayList();
        Preconditions.checkNotNull(r1, "watcherClass cannot be null");
        this.mWatcherClass = r1;
    }

    private void blockWatchers() {
        int r0 = 0;
    L4:
        if (r0 >= this.mWatchers.size()) goto L6;
        this.mWatchers.get(r0).blockCalls();
        r0 = r0 + 1;
        goto L4
    }

    public static SpannableBuilder create(Class<?> r1, CharSequence r2) {
        return new SpannableBuilder(r1, r2);
    }

    private void fireWatchers() {
        int r1 = 0;
    L4:
        if (r1 >= this.mWatchers.size()) goto L6;
        this.mWatchers.get(r1).onTextChanged(this, 0, length(), length());
        r1 = r1 + 1;
        goto L4
    }

    private WatcherWrapper getWatcherFor(Object r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.mWatchers.size()) goto L9;
        WatcherWrapper r1 = this.mWatchers.get(r0);
        if (r1.mObject == r4) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r1;
    L9:
        return null;
    }

    private boolean isWatcher(Class<?> r1) {
        if (this.mWatcherClass != r1) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isWatcher(Object r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (isWatcher(r1.getClass()) == false) goto L9;
        return true;
    L9:
        return false;
    }

    private void unblockwatchers() {
        int r0 = 0;
    L4:
        if (r0 >= this.mWatchers.size()) goto L6;
        this.mWatchers.get(r0).unblockCalls();
        r0 = r0 + 1;
        goto L4
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public /* bridge */ /* synthetic */ Editable append(char r1) {
        return append(r1);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public /* bridge */ /* synthetic */ Editable append(@SuppressLint({"UnknownNullness"}) CharSequence r1) {
        return append(r1);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public /* bridge */ /* synthetic */ Editable append(@SuppressLint({"UnknownNullness"}) CharSequence r1, int r2, int r3) {
        return append(r1, r2, r3);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public SpannableStringBuilder append(char r1) {
        super.append(r1);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public SpannableStringBuilder append(@SuppressLint({"UnknownNullness"}) CharSequence r1) {
        super.append(r1);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public SpannableStringBuilder append(@SuppressLint({"UnknownNullness"}) CharSequence r1, int r2, int r3) {
        super.append(r1, r2, r3);
        return this;
    }

    @Override // android.text.SpannableStringBuilder
    @SuppressLint({"UnknownNullness"})
    public SpannableStringBuilder append(CharSequence r1, Object r2, int r3) {
        super.append(r1, r2, r3);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public /* bridge */ /* synthetic */ Appendable append(char r1) {
        return append(r1);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public /* bridge */ /* synthetic */ Appendable append(@SuppressLint({"UnknownNullness"}) CharSequence r1) {
        return append(r1);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable, java.lang.Appendable
    public /* bridge */ /* synthetic */ Appendable append(@SuppressLint({"UnknownNullness"}) CharSequence r1, int r2, int r3) {
        return append(r1, r2, r3);
    }

    public void beginBatchEdit() {
        blockWatchers();
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    @SuppressLint({"UnknownNullness"})
    public /* bridge */ /* synthetic */ Editable delete(int r1, int r2) {
        return delete(r1, r2);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    @SuppressLint({"UnknownNullness"})
    public SpannableStringBuilder delete(int r1, int r2) {
        super.delete(r1, r2);
        return this;
    }

    public void endBatchEdit() {
        unblockwatchers();
        fireWatchers();
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public int getSpanEnd(Object r2) {
        if (isWatcher(r2) == false) goto L8;
        WatcherWrapper r0 = getWatcherFor(r2);
        if (r0 == null) goto L8;
        r2 = r0;
    L8:
        return super.getSpanEnd(r2);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public int getSpanFlags(Object r2) {
        if (isWatcher(r2) == false) goto L8;
        WatcherWrapper r0 = getWatcherFor(r2);
        if (r0 == null) goto L8;
        r2 = r0;
    L8:
        return super.getSpanFlags(r2);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public int getSpanStart(Object r2) {
        if (isWatcher(r2) == false) goto L8;
        WatcherWrapper r0 = getWatcherFor(r2);
        if (r0 == null) goto L8;
        r2 = r0;
    L8:
        return super.getSpanStart(r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    @SuppressLint({"UnknownNullness"})
    public <T> T[] getSpans(int r2, int r3, Class<T> r4) {
        if (isWatcher(r4) == false) goto L10;
        WatcherWrapper[] r1 = (WatcherWrapper[]) super.getSpans(r2, r3, WatcherWrapper.class);
        T[] r22 = (T[]) ((Object[]) Array.newInstance(r4, r1.length));
        int r32 = 0;
    L6:
        if (r32 >= r1.length) goto L8;
        r22[r32] = r1[r32].mObject;
        r32 = r32 + 1;
        goto L6
    L8:
        return r22;
    L10:
        return (T[]) super.getSpans(r2, r3, r4);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    @SuppressLint({"UnknownNullness"})
    public /* bridge */ /* synthetic */ Editable insert(int r1, CharSequence r2) {
        return insert(r1, r2);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    @SuppressLint({"UnknownNullness"})
    public /* bridge */ /* synthetic */ Editable insert(int r1, CharSequence r2, int r3, int r4) {
        return insert(r1, r2, r3, r4);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    @SuppressLint({"UnknownNullness"})
    public SpannableStringBuilder insert(int r1, CharSequence r2) {
        super.insert(r1, r2);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    @SuppressLint({"UnknownNullness"})
    public SpannableStringBuilder insert(int r1, CharSequence r2, int r3, int r4) {
        super.insert(r1, r2, r3, r4);
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spanned
    public int nextSpanTransition(int r2, int r3, Class r4) {
        if (r4 != null) goto L4;
    L5:
        r4 = WatcherWrapper.class;
    L7:
        return super.nextSpanTransition(r2, r3, r4);
    L4:
        if (isWatcher(r4) == false) goto L7;
        goto L5
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spannable
    public void removeSpan(Object r2) {
        if (isWatcher(r2) == false) goto L7;
        WatcherWrapper r0 = getWatcherFor(r2);
        if (r0 == null) goto L8;
        r2 = r0;
    L8:
        super.removeSpan(r2);
        if (r0 == null) goto L12;
        this.mWatchers.remove(r0);
        return;
    L12:
        return;
    L7:
        r0 = null;
        goto L8
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    @SuppressLint({"UnknownNullness"})
    public /* bridge */ /* synthetic */ Editable replace(int r1, int r2, CharSequence r3) {
        return replace(r1, r2, r3);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    @SuppressLint({"UnknownNullness"})
    public /* bridge */ /* synthetic */ Editable replace(int r1, int r2, CharSequence r3, int r4, int r5) {
        return replace(r1, r2, r3, r4, r5);
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    @SuppressLint({"UnknownNullness"})
    public SpannableStringBuilder replace(int r1, int r2, CharSequence r3) {
        blockWatchers();
        super.replace(r1, r2, r3);
        unblockwatchers();
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Editable
    @SuppressLint({"UnknownNullness"})
    public SpannableStringBuilder replace(int r1, int r2, CharSequence r3, int r4, int r5) {
        blockWatchers();
        super.replace(r1, r2, r3, r4, r5);
        unblockwatchers();
        return this;
    }

    @Override // android.text.SpannableStringBuilder, android.text.Spannable
    public void setSpan(Object r2, int r3, int r4, int r5) {
        if (isWatcher(r2) == false) goto L5;
        WatcherWrapper r0 = new WatcherWrapper(r2);
        this.mWatchers.add(r0);
        r2 = r0;
    L5:
        super.setSpan(r2, r3, r4, r5);
    }

    @Override // android.text.SpannableStringBuilder, java.lang.CharSequence
    @SuppressLint({"UnknownNullness"})
    public CharSequence subSequence(int r3, int r4) {
        return new SpannableBuilder(this.mWatcherClass, this, r3, r4);
    }
}
