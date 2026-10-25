package androidx.emoji2.viewsintegration;

import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import androidx.core.util.Preconditions;

/* loaded from: classes3.dex */
public final class EmojiEditTextHelper {
    private int mEmojiReplaceStrategy;
    private final HelperInternal mHelper;
    private int mMaxEmojiCount;

    public static class HelperInternal {
        public HelperInternal() {
        }

        public abstract KeyListener getKeyListener(KeyListener r1);

        public abstract boolean isEnabled();

        public abstract InputConnection onCreateInputConnection(InputConnection r1, EditorInfo r2);

        public abstract void setEnabled(boolean r1);
    }

    public static class HelperInternal19 extends HelperInternal {
        private final EditText mEditText;
        private final EmojiTextWatcher mTextWatcher;

        public HelperInternal19(EditText r2, boolean r3) {
            this.mEditText = r2;
            EmojiTextWatcher r0 = new EmojiTextWatcher(r2, r3);
            this.mTextWatcher = r0;
            r2.addTextChangedListener(r0);
            r2.setEditableFactory(EmojiEditableFactory.getInstance());
        }

        @Override // androidx.emoji2.viewsintegration.EmojiEditTextHelper.HelperInternal
        public KeyListener getKeyListener(KeyListener r1) {
            if ((r1 instanceof EmojiKeyListener) == false) goto L5;
            return r1;
        L5:
            if (r1 != null) goto L9;
            return null;
        L9:
            if ((r1 instanceof NumberKeyListener) == false) goto L12;
            return r1;
        L12:
            return new EmojiKeyListener(r1);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiEditTextHelper.HelperInternal
        public boolean isEnabled() {
            return this.mTextWatcher.isEnabled();
        }

        @Override // androidx.emoji2.viewsintegration.EmojiEditTextHelper.HelperInternal
        public InputConnection onCreateInputConnection(InputConnection r2, EditorInfo r3) {
            if ((r2 instanceof EmojiInputConnection) == false) goto L6;
            return r2;
        L6:
            return new EmojiInputConnection(this.mEditText, r2, r3);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiEditTextHelper.HelperInternal
        public void setEnabled(boolean r1) {
            this.mTextWatcher.setEnabled(r1);
        }
    }

    public EmojiEditTextHelper(EditText r2, boolean r3) {
        this.mMaxEmojiCount = Integer.MAX_VALUE;
        this.mEmojiReplaceStrategy = 0;
        Preconditions.checkNotNull(r2, "editText cannot be null");
        this.mHelper = new HelperInternal19(r2, r3);
    }

    public KeyListener getKeyListener(KeyListener r1) {
        return this.mHelper.getKeyListener(r1);
    }

    public boolean isEnabled() {
        return this.mHelper.isEnabled();
    }

    public InputConnection onCreateInputConnection(InputConnection r1, EditorInfo r2) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return this.mHelper.onCreateInputConnection(r1, r2);
    }

    public void setEnabled(boolean r1) {
        this.mHelper.setEnabled(r1);
    }
}
