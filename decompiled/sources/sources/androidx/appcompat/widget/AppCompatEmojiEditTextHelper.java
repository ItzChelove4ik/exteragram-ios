package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import androidx.appcompat.R$styleable;
import androidx.emoji2.viewsintegration.EmojiEditTextHelper;

/* loaded from: classes3.dex */
class AppCompatEmojiEditTextHelper {
    private final EmojiEditTextHelper mEmojiEditTextHelper;
    private final EditText mView;

    public AppCompatEmojiEditTextHelper(EditText r3) {
        this.mView = r3;
        this.mEmojiEditTextHelper = new EmojiEditTextHelper(r3, false);
    }

    public KeyListener getKeyListener(KeyListener r2) {
        if (isEmojiCapableKeyListener(r2) == true) goto L5;
        return r2;
    L5:
        return this.mEmojiEditTextHelper.getKeyListener(r2);
    }

    public boolean isEmojiCapableKeyListener(KeyListener r1) {
        return !(r1 instanceof NumberKeyListener);
    }

    public boolean isEnabled() {
        return this.mEmojiEditTextHelper.isEnabled();
    }

    public void loadFromAttributes(AttributeSet r4, int r5) {
        TypedArray r42 = this.mView.getContext().obtainStyledAttributes(r4, R$styleable.AppCompatTextView, r5, 0);
        boolean r0 = true;
        if (r42.hasValue(R$styleable.AppCompatTextView_emojiCompatEnabled) == false) goto L9;
        r0 = r42.getBoolean(R$styleable.AppCompatTextView_emojiCompatEnabled, true);     // Catch: Throwable -> L7
    L9:
        r42.recycle();
        setEnabled(r0);
        return;
    L7:
        th = move-exception;
        r42.recycle();
        throw th;
    }

    public InputConnection onCreateInputConnection(InputConnection r1, EditorInfo r2) {
        return this.mEmojiEditTextHelper.onCreateInputConnection(r1, r2);
    }

    public void setEnabled(boolean r1) {
        this.mEmojiEditTextHelper.setEnabled(r1);
    }
}
