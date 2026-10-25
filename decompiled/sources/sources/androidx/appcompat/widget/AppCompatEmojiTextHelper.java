package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import androidx.emoji2.viewsintegration.EmojiTextViewHelper;

/* loaded from: classes3.dex */
class AppCompatEmojiTextHelper {
    private final EmojiTextViewHelper mEmojiTextViewHelper;
    private final TextView mView;

    public AppCompatEmojiTextHelper(TextView r3) {
        this.mView = r3;
        this.mEmojiTextViewHelper = new EmojiTextViewHelper(r3, false);
    }

    public InputFilter[] getFilters(InputFilter[] r1) {
        return this.mEmojiTextViewHelper.getFilters(r1);
    }

    public boolean isEnabled() {
        return this.mEmojiTextViewHelper.isEnabled();
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

    public void setAllCaps(boolean r1) {
        this.mEmojiTextViewHelper.setAllCaps(r1);
    }

    public void setEnabled(boolean r1) {
        this.mEmojiTextViewHelper.setEnabled(r1);
    }

    public TransformationMethod wrapTransformationMethod(TransformationMethod r1) {
        return this.mEmojiTextViewHelper.wrapTransformationMethod(r1);
    }
}
