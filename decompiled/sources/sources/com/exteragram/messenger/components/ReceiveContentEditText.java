package com.exteragram.messenger.components;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Rect;
import android.os.Build;
import android.text.Editable;
import android.text.Selection;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.TextView;
import androidx.core.view.ContentInfoCompat;
import androidx.core.view.OnReceiveContentViewBehavior;
import androidx.core.view.ViewCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.core.view.inputmethod.InputConnectionCompat;
import androidx.core.widget.TextViewOnReceiveContentListener;
import com.exteragram.messenger.math.inline.InlineMathController;

@SuppressLint({"RestrictedApi", "AppCompatCustomView"})
/* loaded from: classes.dex */
public abstract class ReceiveContentEditText extends EditText implements OnReceiveContentViewBehavior {
    private final TextViewOnReceiveContentListener defaultOnReceiveContentListener;
    private InlineMathController inlineMath;

    /* loaded from: classes4.dex */
    public static final class OnDropApi24Impl {
        public static boolean onDropForTextView(DragEvent r1, ReceiveContentEditText r2, Activity r3) {
            r3.requestDragAndDropPermissions(r1);
            int r32 = r2.getOffsetForPosition(r1.getX(), r1.getY());
            r2.beginBatchEdit();
            Selection.setSelection(r2.getText(), r32);     // Catch: Throwable -> L6
            ViewCompat.performReceiveContent(r2, new ContentInfoCompat.Builder(r1.getClipData(), 3).build());     // Catch: Throwable -> L6
            r2.endBatchEdit();
            return true;
        L6:
            th = move-exception;
            r2.endBatchEdit();
            throw th;
        }
    }

    public ReceiveContentEditText(Context r1, AttributeSet r2, int r3, int r4) {
        super(r1, r2, r3, r4);
        this.defaultOnReceiveContentListener = new TextViewOnReceiveContentListener();
    }

    private Activity findActivity() {
        Context r1 = getContext();
    L4:
        if ((r1 instanceof ContextWrapper) == false) goto L10;
        if ((r1 instanceof Activity) == true) goto L8;
        r1 = ((ContextWrapper) r1).getBaseContext();
        goto L4
    L8:
        return (Activity) r1;
    L10:
        return null;
    }

    private boolean handleDragEventViaReceiveContent(DragEvent r5) {
        if (Build.VERSION.SDK_INT < 31) goto L5;
    L19:
        return false;
    L5:
        if (r5.getLocalState() != null) goto L19;
        if (ViewCompat.getOnReceiveContentMimeTypes(this) == null) goto L19;
        Activity r0 = findActivity();
        if (r0 != null) goto L13;
        return false;
    L13:
        if (r5.getAction() != 1) goto L16;
        return false;
    L16:
        if (r5.getAction() != 3) goto L19;
        return OnDropApi24Impl.onDropForTextView(r5, this, r0);
    }

    private boolean handleMenuActionViaReceiveContent(int r6) {
        int r1 = 0;
        if (ViewCompat.getOnReceiveContentMimeTypes(this) != null) goto L5;
    L23:
        return false;
    L5:
        if (r6 != 16908322) goto L7;
    L9:
        ClipboardManager r2 = (ClipboardManager) getContext().getSystemService("clipboard");
        if (r2 != null) goto L12;
        ClipData r22 = null;
    L14:
        if (r22 != null) goto L16;
    L22:
        return true;
    L16:
        if (r22.getItemCount() <= 0) goto L22;
        ContentInfoCompat.Builder r4 = new ContentInfoCompat.Builder(r22, 1);
        if (r6 == 16908322) goto L21;
        r1 = 1;
    L21:
        ViewCompat.performReceiveContent(this, r4.setFlags(r1).build());
        goto L22
    L12:
        r22 = r2.getPrimaryClip();
        goto L14
    L7:
        if (r6 == 16908337) goto L9;
        goto L9
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent r2) {
        InlineMathController r0 = this.inlineMath;
        if (r0 == null) goto L9;
        if (r0.onKeyEvent(r2) == false) goto L9;
        return true;
    L9:
        return super.dispatchKeyEvent(r2);
    }

    @Override // android.widget.TextView
    public int getCompoundPaddingBottom() {
        int r0 = super.getCompoundPaddingBottom();
        InlineMathController r1 = this.inlineMath;
        if (r1 == null) goto L5;
        int r12 = r1.getExtraBottom();
    L7:
        return r0 + r12;
    L5:
        r12 = 0;
        goto L7
    }

    public InlineMathController getInlineMath() {
        return this.inlineMath;
    }

    @Override // android.widget.EditText, android.widget.TextView
    public Editable getText() {
        if (Build.VERSION.SDK_INT < 28) goto L7;
        return super.getText();
    L7:
        return super.getEditableText();
    }

    @Override // android.widget.EditText, android.widget.TextView
    public /* bridge */ /* synthetic */ CharSequence getText() {
        return getText();
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo r4) {
        InputConnection r0 = super.onCreateInputConnection(r4);
        if (r0 != null) goto L5;
    L9:
        if (r0 == null) goto L14;
        InlineMathController r3 = this.inlineMath;
        if (r3 == null) goto L14;
        return r3.wrap(r0);
    L14:
        return r0;
    L5:
        if (Build.VERSION.SDK_INT > 30) goto L9;
        String[] r1 = ViewCompat.getOnReceiveContentMimeTypes(this);
        if (r1 == null) goto L9;
        EditorInfoCompat.setContentMimeTypes(r4, r1);
        r0 = InputConnectionCompat.createWrapper(this, r0, r4);
        goto L9
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        InlineMathController r0 = this.inlineMath;
        if (r0 == null) goto L6;
        r0.cancel();
        return;
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onDragEvent(DragEvent r2) {
        if (handleDragEventViaReceiveContent(r2) == false) goto L7;
        return true;
    L7:
        return super.onDragEvent(r2);
    }

    @Override // android.widget.TextView, android.view.View
    public void onFocusChanged(boolean r1, int r2, Rect r3) {
        super.onFocusChanged(r1, r2, r3);
        InlineMathController r0 = this.inlineMath;
        if (r0 == null) goto L6;
        r0.onFocusChanged(r1);
        return;
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(r2, r3);
        InlineMathController r0 = this.inlineMath;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.updateOnMeasure() == false) goto L9;
        super.onMeasure(r2, r3);
        return;
    }

    @Override // androidx.core.view.OnReceiveContentViewBehavior
    public ContentInfoCompat onReceiveContent(ContentInfoCompat r2) {
        return this.defaultOnReceiveContentListener.onReceiveContent(this, r2);
    }

    @Override // android.widget.TextView
    public void onSelectionChanged(int r1, int r2) {
        super.onSelectionChanged(r1, r2);
        InlineMathController r0 = this.inlineMath;
        if (r0 == null) goto L6;
        r0.invalidateState();
        return;
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        super.onTextChanged(r1, r2, r3, r4);
        InlineMathController r0 = this.inlineMath;
        if (r0 == null) goto L6;
        r0.onTextChanged();
        return;
    }

    @Override // android.widget.EditText, android.widget.TextView
    public boolean onTextContextMenuItem(int r2) {
        if (handleMenuActionViaReceiveContent(r2) == false) goto L7;
        return true;
    L7:
        return super.onTextContextMenuItem(r2);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent r2) {
        if (this.inlineMath == null) goto L8;
        if (r2.getAction() != 0) goto L8;
        this.inlineMath.onTouchDown();
    L8:
        return super.onTouchEvent(r2);
    }

    public void setInlineMath(InlineMathController r1) {
        this.inlineMath = r1;
        requestLayout();
    }

    @Override // android.widget.EditText, android.widget.TextView
    public void setText(CharSequence r1, TextView.BufferType r2) {
        super.setText(r1, r2);
        InlineMathController r0 = this.inlineMath;
        if (r0 == null) goto L6;
        r0.cancel();
        return;
    }
}
