package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.DragEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;
import androidx.appcompat.R$attr;
import androidx.core.view.ContentInfoCompat;
import androidx.core.view.OnReceiveContentViewBehavior;
import androidx.core.view.ViewCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.core.view.inputmethod.InputConnectionCompat;
import androidx.core.widget.TextViewCompat;
import androidx.core.widget.TextViewOnReceiveContentListener;

/* loaded from: classes3.dex */
public class AppCompatEditText extends EditText implements OnReceiveContentViewBehavior {
    private final AppCompatEmojiEditTextHelper mAppCompatEmojiEditTextHelper;
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final TextViewOnReceiveContentListener mDefaultOnReceiveContentListener;
    private SuperCaller mSuperCaller;
    private final AppCompatTextClassifierHelper mTextClassifierHelper;
    private final AppCompatTextHelper mTextHelper;

    public class SuperCaller {
        final /* synthetic */ AppCompatEditText this$0;

        public SuperCaller(AppCompatEditText r1) {
            this.this$0 = r1;
        }

        public TextClassifier getTextClassifier() {
            return AppCompatEditText.access$001(this.this$0);
        }

        public void setTextClassifier(TextClassifier r1) {
            AppCompatEditText.access$101(this.this$0, r1);
        }
    }

    public AppCompatEditText(Context r2, AttributeSet r3) {
        this(r2, r3, R$attr.editTextStyle);
    }

    public AppCompatEditText(Context r1, AttributeSet r2, int r3) {
        super(TintContextWrapper.wrap(r1), r2, r3);
        ThemeUtils.checkAppCompatTheme(this, getContext());
        AppCompatBackgroundHelper r12 = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = r12;
        r12.loadFromAttributes(r2, r3);
        AppCompatTextHelper r13 = new AppCompatTextHelper(this);
        this.mTextHelper = r13;
        r13.loadFromAttributes(r2, r3);
        r13.applyCompoundDrawablesTints();
        this.mTextClassifierHelper = new AppCompatTextClassifierHelper(this);
        this.mDefaultOnReceiveContentListener = new TextViewOnReceiveContentListener();
        AppCompatEmojiEditTextHelper r14 = new AppCompatEmojiEditTextHelper(this);
        this.mAppCompatEmojiEditTextHelper = r14;
        r14.loadFromAttributes(r2, r3);
        initEmojiKeyListener(r14);
    }

    public static /* synthetic */ TextClassifier access$001(AppCompatEditText r0) {
        return super.getTextClassifier();
    }

    public static /* synthetic */ void access$101(AppCompatEditText r0, TextClassifier r1) {
        super.setTextClassifier(r1);
    }

    private SuperCaller getSuperCaller() {
        if (this.mSuperCaller != null) goto L6;
        this.mSuperCaller = new SuperCaller(this);
    L6:
        return this.mSuperCaller;
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 == null) goto L5;
        r0.applySupportBackgroundTint();
    L5:
        AppCompatTextHelper r1 = this.mTextHelper;
        if (r1 == null) goto L9;
        r1.applyCompoundDrawablesTints();
        return;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return TextViewCompat.unwrapCustomSelectionActionModeCallback(super.getCustomSelectionActionModeCallback());
    }

    public ColorStateList getSupportBackgroundTintList() {
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getSupportBackgroundTintList();
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getSupportBackgroundTintMode();
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.mTextHelper.getCompoundDrawableTintList();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.mTextHelper.getCompoundDrawableTintMode();
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

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        if (Build.VERSION.SDK_INT >= 28) goto L10;
        AppCompatTextClassifierHelper r0 = this.mTextClassifierHelper;
        if (r0 == null) goto L10;
        return r0.getTextClassifier();
    L10:
        return getSuperCaller().getTextClassifier();
    }

    public void initEmojiKeyListener(AppCompatEmojiEditTextHelper r6) {
        KeyListener r0 = getKeyListener();
        if (r6.isEmojiCapableKeyListener(r0) == false) goto L9;
        boolean r1 = super.isFocusable();
        boolean r2 = super.isClickable();
        boolean r3 = super.isLongClickable();
        int r4 = super.getInputType();
        KeyListener r62 = r6.getKeyListener(r0);
        if (r62 == r0) goto L10;
        super.setKeyListener(r62);
        super.setRawInputType(r4);
        super.setFocusable(r1);
        super.setClickable(r2);
        super.setLongClickable(r3);
        return;
    L10:
        return;
    }

    public boolean isEmojiCompatEnabled() {
        return this.mAppCompatEmojiEditTextHelper.isEnabled();
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo r4) {
        InputConnection r0 = super.onCreateInputConnection(r4);
        this.mTextHelper.populateSurroundingTextIfNeeded(this, r0, r4);
        InputConnection r02 = AppCompatHintHelper.onCreateInputConnection(r0, r4, this);
        if (r02 == null) goto L10;
        if (Build.VERSION.SDK_INT > 30) goto L10;
        String[] r1 = ViewCompat.getOnReceiveContentMimeTypes(this);
        if (r1 == null) goto L10;
        EditorInfoCompat.setContentMimeTypes(r4, r1);
        r02 = InputConnectionCompat.createWrapper(this, r02, r4);
    L10:
        return this.mAppCompatEmojiEditTextHelper.onCreateInputConnection(r02, r4);
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        int r0 = Build.VERSION.SDK_INT;
        if (r0 >= 30) goto L5;
        return;
    L5:
        if (r0 >= 33) goto L9;
        ((InputMethodManager) getContext().getSystemService("input_method")).isActive(this);
        return;
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onDragEvent(DragEvent r2) {
        if (AppCompatReceiveContentHelper.maybeHandleDragEventViaPerformReceiveContent(this, r2) == false) goto L7;
        return true;
    L7:
        return super.onDragEvent(r2);
    }

    @Override // androidx.core.view.OnReceiveContentViewBehavior
    public ContentInfoCompat onReceiveContent(ContentInfoCompat r2) {
        return this.mDefaultOnReceiveContentListener.onReceiveContent(this, r2);
    }

    @Override // android.widget.EditText, android.widget.TextView
    public boolean onTextContextMenuItem(int r2) {
        if (AppCompatReceiveContentHelper.maybeHandleMenuActionViaPerformReceiveContent(this, r2) == false) goto L7;
        return true;
    L7:
        return super.onTextContextMenuItem(r2);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable r1) {
        super.setBackgroundDrawable(r1);
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 == null) goto L6;
        r0.onSetBackgroundDrawable(r1);
        return;
    }

    @Override // android.view.View
    public void setBackgroundResource(int r1) {
        super.setBackgroundResource(r1);
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 == null) goto L6;
        r0.onSetBackgroundResource(r1);
        return;
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable r1, Drawable r2, Drawable r3, Drawable r4) {
        super.setCompoundDrawables(r1, r2, r3, r4);
        AppCompatTextHelper r0 = this.mTextHelper;
        if (r0 == null) goto L6;
        r0.onSetCompoundDrawables();
        return;
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable r1, Drawable r2, Drawable r3, Drawable r4) {
        super.setCompoundDrawablesRelative(r1, r2, r3, r4);
        AppCompatTextHelper r0 = this.mTextHelper;
        if (r0 == null) goto L6;
        r0.onSetCompoundDrawables();
        return;
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback r1) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, r1));
    }

    public void setEmojiCompatEnabled(boolean r1) {
        this.mAppCompatEmojiEditTextHelper.setEnabled(r1);
    }

    @Override // android.widget.TextView
    public void setKeyListener(KeyListener r2) {
        super.setKeyListener(this.mAppCompatEmojiEditTextHelper.getKeyListener(r2));
    }

    public void setSupportBackgroundTintList(ColorStateList r1) {
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 == null) goto L6;
        r0.setSupportBackgroundTintList(r1);
        return;
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode r1) {
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 == null) goto L6;
        r0.setSupportBackgroundTintMode(r1);
        return;
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList r2) {
        this.mTextHelper.setCompoundDrawableTintList(r2);
        this.mTextHelper.applyCompoundDrawablesTints();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode r2) {
        this.mTextHelper.setCompoundDrawableTintMode(r2);
        this.mTextHelper.applyCompoundDrawablesTints();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context r1, int r2) {
        super.setTextAppearance(r1, r2);
        AppCompatTextHelper r0 = this.mTextHelper;
        if (r0 == null) goto L6;
        r0.onSetTextAppearance(r1, r2);
        return;
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier r3) {
        if (Build.VERSION.SDK_INT >= 28) goto L9;
        AppCompatTextClassifierHelper r0 = this.mTextClassifierHelper;
        if (r0 == null) goto L9;
        r0.setTextClassifier(r3);
        return;
    L9:
        getSuperCaller().setTextClassifier(r3);
    }
}
