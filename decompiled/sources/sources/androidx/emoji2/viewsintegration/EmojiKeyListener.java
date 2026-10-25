package androidx.emoji2.viewsintegration;

import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import androidx.emoji2.text.EmojiCompat;

/* loaded from: classes3.dex */
final class EmojiKeyListener implements KeyListener {
    private final EmojiCompatHandleKeyDownHelper mEmojiCompatHandleKeyDownHelper;
    private final KeyListener mKeyListener;

    public static class EmojiCompatHandleKeyDownHelper {
        public EmojiCompatHandleKeyDownHelper() {
        }

        public boolean handleKeyDown(Editable r1, int r2, KeyEvent r3) {
            return EmojiCompat.handleOnKeyDown(r1, r2, r3);
        }
    }

    public EmojiKeyListener(KeyListener r2) {
        this(r2, new EmojiCompatHandleKeyDownHelper());
    }

    public EmojiKeyListener(KeyListener r1, EmojiCompatHandleKeyDownHelper r2) {
        this.mKeyListener = r1;
        this.mEmojiCompatHandleKeyDownHelper = r2;
    }

    @Override // android.text.method.KeyListener
    public void clearMetaKeyState(View r1, Editable r2, int r3) {
        this.mKeyListener.clearMetaKeyState(r1, r2, r3);
    }

    @Override // android.text.method.KeyListener
    public int getInputType() {
        return this.mKeyListener.getInputType();
    }

    @Override // android.text.method.KeyListener
    public boolean onKeyDown(View r2, Editable r3, int r4, KeyEvent r5) {
        if (this.mEmojiCompatHandleKeyDownHelper.handleKeyDown(r3, r4, r5) == false) goto L5;
        return true;
    L5:
        if (this.mKeyListener.onKeyDown(r2, r3, r4, r5) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.text.method.KeyListener
    public boolean onKeyOther(View r1, Editable r2, KeyEvent r3) {
        return this.mKeyListener.onKeyOther(r1, r2, r3);
    }

    @Override // android.text.method.KeyListener
    public boolean onKeyUp(View r1, Editable r2, int r3, KeyEvent r4) {
        return this.mKeyListener.onKeyUp(r1, r2, r3, r4);
    }
}
