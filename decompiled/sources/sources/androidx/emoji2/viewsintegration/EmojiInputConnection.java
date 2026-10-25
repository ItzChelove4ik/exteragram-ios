package androidx.emoji2.viewsintegration;

import android.text.Editable;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.TextView;
import androidx.emoji2.text.EmojiCompat;

/* loaded from: classes3.dex */
final class EmojiInputConnection extends InputConnectionWrapper {
    private final EmojiCompatDeleteHelper mEmojiCompatDeleteHelper;
    private final TextView mTextView;

    public static class EmojiCompatDeleteHelper {
        public EmojiCompatDeleteHelper() {
        }

        public boolean handleDeleteSurroundingText(InputConnection r1, Editable r2, int r3, int r4, boolean r5) {
            return EmojiCompat.handleDeleteSurroundingText(r1, r2, r3, r4, r5);
        }

        public void updateEditorInfoAttrs(EditorInfo r1) {
            if (EmojiCompat.isConfigured() == false) goto L6;
            EmojiCompat.get().updateEditorInfo(r1);
            return;
        }
    }

    public EmojiInputConnection(TextView r2, InputConnection r3, EditorInfo r4) {
        this(r2, r3, r4, new EmojiCompatDeleteHelper());
    }

    public EmojiInputConnection(TextView r2, InputConnection r3, EditorInfo r4, EmojiCompatDeleteHelper r5) {
        super(r3, false);
        this.mTextView = r2;
        this.mEmojiCompatDeleteHelper = r5;
        r5.updateEditorInfoAttrs(r4);
    }

    private Editable getEditable() {
        return this.mTextView.getEditableText();
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean deleteSurroundingText(int r7, int r8) {
        if (this.mEmojiCompatDeleteHelper.handleDeleteSurroundingText(this, getEditable(), r7, r8, false) == false) goto L5;
        return true;
    L5:
        if (super.deleteSurroundingText(r7, r8) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean deleteSurroundingTextInCodePoints(int r7, int r8) {
        if (this.mEmojiCompatDeleteHelper.handleDeleteSurroundingText(this, getEditable(), r7, r8, true) == false) goto L5;
        return true;
    L5:
        if (super.deleteSurroundingTextInCodePoints(r7, r8) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
