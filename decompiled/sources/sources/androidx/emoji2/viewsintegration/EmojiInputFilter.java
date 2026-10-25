package androidx.emoji2.viewsintegration;

import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.text.Spanned;
import android.widget.TextView;
import androidx.emoji2.text.EmojiCompat;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
final class EmojiInputFilter implements InputFilter {
    private EmojiCompat.InitCallback mInitCallback;
    private final TextView mTextView;

    public static class InitCallbackImpl extends EmojiCompat.InitCallback {
        private final Reference<EmojiInputFilter> mEmojiInputFilterReference;
        private final Reference<TextView> mViewRef;

        public InitCallbackImpl(TextView r2, EmojiInputFilter r3) {
            this.mViewRef = new WeakReference(r2);
            this.mEmojiInputFilterReference = new WeakReference(r3);
        }

        private boolean isInputFilterCurrentlyRegisteredOnTextView(TextView r3, InputFilter r4) {
            if (r4 == null) goto L17;
            if (r3 == null) goto L17;
            InputFilter[] r32 = r3.getFilters();
            if (r32 != null) goto L9;
            return false;
        L9:
            int r0 = 0;
        L11:
            if (r0 >= r32.length) goto L17;
            if (r32[r0] == r4) goto L14;
            r0 = r0 + 1;
            goto L11
        L14:
            return true;
        L17:
            return false;
        }

        @Override // androidx.emoji2.text.EmojiCompat.InitCallback
        public void onInitialized() {
            super.onInitialized();
            TextView r0 = this.mViewRef.get();
            if (isInputFilterCurrentlyRegisteredOnTextView(r0, this.mEmojiInputFilterReference.get()) == true) goto L6;
            return;
        L6:
            if (r0.isAttachedToWindow() == false) goto L14;
            CharSequence r3 = r0.getText();
            CharSequence r1 = EmojiCompat.get().process(r3);
            if (r3 == r1) goto L17;
            int r32 = Selection.getSelectionStart(r1);
            int r2 = Selection.getSelectionEnd(r1);
            r0.setText(r1);
            if ((r1 instanceof Spannable) == false) goto L15;
            EmojiInputFilter.updateSelection((Spannable) r1, r32, r2);
            return;
        L15:
            return;
        L17:
            return;
        }
    }

    public EmojiInputFilter(TextView r1) {
        this.mTextView = r1;
    }

    private EmojiCompat.InitCallback getInitCallback() {
        if (this.mInitCallback != null) goto L6;
        this.mInitCallback = new InitCallbackImpl(this.mTextView, this);
    L6:
        return this.mInitCallback;
    }

    public static void updateSelection(Spannable r0, int r1, int r2) {
        if (r1 < 0) goto L6;
        if (r2 < 0) goto L6;
        Selection.setSelection(r0, r1, r2);
        return;
    L6:
        if (r1 < 0) goto L9;
        Selection.setSelection(r0, r1);
        return;
    L9:
        if (r2 < 0) goto L12;
        Selection.setSelection(r0, r2);
        return;
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence r3, int r4, int r5, Spanned r6, int r7, int r8) {
        if (this.mTextView.isInEditMode() == true) goto L27;
        int r0 = EmojiCompat.get().getLoadState();
        if (r0 != 0) goto L8;
    L28:
        EmojiCompat.get().registerInitCallback(getInitCallback());
        return r3;
    L8:
        if (r0 != 1) goto L10;
        if (r8 != 0) goto L19;
        if (r7 != 0) goto L19;
        if (r6.length() != 0) goto L19;
        if (r3 != this.mTextView.getText()) goto L19;
        return r3;
    L19:
        if (r3 == null) goto L27;
        if (r4 == 0) goto L22;
    L24:
        r3 = r3.subSequence(r4, r5);
    L26:
        return EmojiCompat.get().process(r3, 0, r3.length());
    L22:
        if (r5 != r3.length()) goto L24;
    L10:
        if (r0 == 3) goto L28;
    L27:
        return r3;
    }
}
