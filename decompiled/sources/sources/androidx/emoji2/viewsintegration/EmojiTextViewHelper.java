package androidx.emoji2.viewsintegration;

import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.SparseArray;
import android.widget.TextView;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.EmojiCompat;

/* loaded from: classes3.dex */
public final class EmojiTextViewHelper {
    private final HelperInternal mHelper;

    public static class HelperInternal {
        public HelperInternal() {
        }

        public abstract InputFilter[] getFilters(InputFilter[] r1);

        public abstract boolean isEnabled();

        public abstract void setAllCaps(boolean r1);

        public abstract void setEnabled(boolean r1);

        public abstract TransformationMethod wrapTransformationMethod(TransformationMethod r1);
    }

    public static class HelperInternal19 extends HelperInternal {
        private final EmojiInputFilter mEmojiInputFilter;
        private boolean mEnabled;
        private final TextView mTextView;

        public HelperInternal19(TextView r2) {
            this.mTextView = r2;
            this.mEnabled = true;
            this.mEmojiInputFilter = new EmojiInputFilter(r2);
        }

        private InputFilter[] addEmojiInputFilterIfMissing(InputFilter[] r6) {
            int r0 = r6.length;
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L8;
            if (r6[r2] == this.mEmojiInputFilter) goto L6;
            r2 = r2 + 1;
            goto L3
        L6:
            return r6;
        L8:
            InputFilter[] r22 = new InputFilter[r6.length + 1];
            System.arraycopy(r6, 0, r22, 0, r0);
            r22[r0] = this.mEmojiInputFilter;
            return r22;
        }

        private SparseArray<InputFilter> getEmojiInputFilterPositionArray(InputFilter[] r4) {
            SparseArray<InputFilter> r3 = new SparseArray(1);
            int r0 = 0;
        L4:
            if (r0 >= r4.length) goto L9;
            InputFilter r1 = r4[r0];
            if ((r1 instanceof EmojiInputFilter) == false) goto L8;
            r3.put(r0, r1);
        L8:
            r0 = r0 + 1;
            goto L4
        L9:
            return r3;
        }

        private InputFilter[] removeEmojiInputFilterIfPresent(InputFilter[] r6) {
            SparseArray<InputFilter> r5 = getEmojiInputFilterPositionArray(r6);
            if (r5.size() != 0) goto L5;
            return r6;
        L5:
            int r0 = r6.length;
            InputFilter[] r1 = new InputFilter[r6.length - r5.size()];
            int r2 = 0;
            int r3 = 0;
        L6:
            if (r2 >= r0) goto L11;
            if (r5.indexOfKey(r2) >= 0) goto L10;
            r1[r3] = r6[r2];
            r3 = r3 + 1;
        L10:
            r2 = r2 + 1;
            goto L6
        L11:
            return r1;
        }

        private TransformationMethod unwrapForDisabled(TransformationMethod r1) {
            if ((r1 instanceof EmojiTransformationMethod) == true) goto L5;
            return r1;
        L5:
            return ((EmojiTransformationMethod) r1).getOriginalTransformationMethod();
        }

        private void updateFilters() {
            InputFilter[] r0 = this.mTextView.getFilters();
            this.mTextView.setFilters(getFilters(r0));
        }

        private TransformationMethod wrapForEnabled(TransformationMethod r1) {
            if ((r1 instanceof EmojiTransformationMethod) == false) goto L6;
            return r1;
        L6:
            if ((r1 instanceof PasswordTransformationMethod) == false) goto L9;
            return r1;
        L9:
            return new EmojiTransformationMethod(r1);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.HelperInternal
        public InputFilter[] getFilters(InputFilter[] r2) {
            if (this.mEnabled == true) goto L7;
            return removeEmojiInputFilterIfPresent(r2);
        L7:
            return addEmojiInputFilterIfMissing(r2);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.HelperInternal
        public boolean isEnabled() {
            return this.mEnabled;
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.HelperInternal
        public void setAllCaps(boolean r1) {
            if (r1 == false) goto L5;
            updateTransformationMethod();
            return;
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.HelperInternal
        public void setEnabled(boolean r1) {
            this.mEnabled = r1;
            updateTransformationMethod();
            updateFilters();
        }

        public void setEnabledUnsafe(boolean r1) {
            this.mEnabled = r1;
        }

        public void updateTransformationMethod() {
            TransformationMethod r0 = wrapTransformationMethod(this.mTextView.getTransformationMethod());
            this.mTextView.setTransformationMethod(r0);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.HelperInternal
        public TransformationMethod wrapTransformationMethod(TransformationMethod r2) {
            if (this.mEnabled == false) goto L7;
            return wrapForEnabled(r2);
        L7:
            return unwrapForDisabled(r2);
        }
    }

    public static class SkippingHelper19 extends HelperInternal {
        private final HelperInternal19 mHelperDelegate;

        public SkippingHelper19(TextView r2) {
            this.mHelperDelegate = new HelperInternal19(r2);
        }

        private boolean skipBecauseEmojiCompatNotInitialized() {
            return !EmojiCompat.isConfigured();
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.HelperInternal
        public InputFilter[] getFilters(InputFilter[] r2) {
            if (skipBecauseEmojiCompatNotInitialized() == false) goto L6;
            return r2;
        L6:
            return this.mHelperDelegate.getFilters(r2);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.HelperInternal
        public boolean isEnabled() {
            return this.mHelperDelegate.isEnabled();
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.HelperInternal
        public void setAllCaps(boolean r2) {
            if (skipBecauseEmojiCompatNotInitialized() == false) goto L5;
            return;
        L5:
            this.mHelperDelegate.setAllCaps(r2);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.HelperInternal
        public void setEnabled(boolean r2) {
            boolean r0 = skipBecauseEmojiCompatNotInitialized();
            HelperInternal19 r1 = this.mHelperDelegate;
            if (r0 == false) goto L6;
            r1.setEnabledUnsafe(r2);
            return;
        L6:
            r1.setEnabled(r2);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.HelperInternal
        public TransformationMethod wrapTransformationMethod(TransformationMethod r2) {
            if (skipBecauseEmojiCompatNotInitialized() == false) goto L6;
            return r2;
        L6:
            return this.mHelperDelegate.wrapTransformationMethod(r2);
        }
    }

    public EmojiTextViewHelper(TextView r2, boolean r3) {
        Preconditions.checkNotNull(r2, "textView cannot be null");
        if (r3 == true) goto L6;
        this.mHelper = new SkippingHelper19(r2);
        return;
    L6:
        this.mHelper = new HelperInternal19(r2);
    }

    public InputFilter[] getFilters(InputFilter[] r1) {
        return this.mHelper.getFilters(r1);
    }

    public boolean isEnabled() {
        return this.mHelper.isEnabled();
    }

    public void setAllCaps(boolean r1) {
        this.mHelper.setAllCaps(r1);
    }

    public void setEnabled(boolean r1) {
        this.mHelper.setEnabled(r1);
    }

    public TransformationMethod wrapTransformationMethod(TransformationMethod r1) {
        return this.mHelper.wrapTransformationMethod(r1);
    }
}
