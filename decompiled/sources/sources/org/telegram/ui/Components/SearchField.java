package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;

/* loaded from: classes7.dex */
public abstract class SearchField extends FrameLayout {
    private ImageView clearSearchImageView;
    private CloseProgressDrawable2 progressDrawable;
    private final Theme.ResourcesProvider resourcesProvider;
    private View searchBackground;
    private EditTextBoldCursor searchEditText;
    private ImageView searchIconImageView;

    /* renamed from: org.telegram.ui.Components.SearchField$1, reason: invalid class name */
    public class AnonymousClass1 extends CloseProgressDrawable2 {
        final /* synthetic */ SearchField this$0;

        public AnonymousClass1(SearchField r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.CloseProgressDrawable2
        public int getCurrentColor() {
            return SearchField.m12564$$Nest$mgetThemedColor(this.this$0, Theme.key_dialogSearchIcon);
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchField$2, reason: invalid class name */
    public class AnonymousClass2 extends EditTextBoldCursor {
        final /* synthetic */ SearchField this$0;

        public AnonymousClass2(SearchField r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.EditTextEffects, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r2) {
            this.this$0.processTouchEvent(r2);
            return super.dispatchTouchEvent(r2);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public boolean onTouchEvent(MotionEvent r3) {
            if (isEnabled() == true) goto L7;
            return false;
        L7:
            if (r3.getAction() != 1) goto L10;
            this.this$0.onFieldTouchUp(this);
        L10:
            return super.onTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchField$3, reason: invalid class name */
    public class AnonymousClass3 implements TextWatcher {
        final /* synthetic */ SearchField this$0;

        public AnonymousClass3(SearchField r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r5) {
            boolean r0 = false;
            if (SearchField.m12563$$Nest$fgetsearchEditText(this.this$0).length() <= 0) goto L5;
            boolean r52 = true;
        L6:
            float r3 = 0.0f;
            if (SearchField.m12562$$Nest$fgetclearSearchImageView(this.this$0).getAlpha() == 0.0f) goto L9;
            r0 = true;
        L9:
            if (r52 == r0) goto L22;
            ViewPropertyAnimator r02 = SearchField.m12562$$Nest$fgetclearSearchImageView(this.this$0).animate();
            float r1 = 1.0f;
            if (r52 == false) goto L13;
            r3 = 1.0f;
        L13:
            ViewPropertyAnimator r03 = r02.alpha(r3).setDuration(150);
            if (r52 == false) goto L16;
            float r32 = 1.0f;
        L17:
            ViewPropertyAnimator r04 = r03.scaleX(r32);
            if (r52 == true) goto L21;
            r1 = 0.1f;
        L21:
            r04.scaleY(r1).start();
            goto L22
        L16:
            r32 = 0.1f;
        L22:
            SearchField r4 = this.this$0;
            r4.onTextChange(SearchField.m12563$$Nest$fgetsearchEditText(r4).getText().toString());
            return;
        L5:
            r52 = false;
            goto L6
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    public static /* synthetic */ void $r8$lambda$5QA1C72FlxymCRFlzKkBlbZeoSw(SearchField r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$N3GA0aHS1xvhgmfdn48A7imz_hs(SearchField r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$new$1(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetclearSearchImageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m12562$$Nest$fgetclearSearchImageView(SearchField r0) {
        return r0.clearSearchImageView;
    }

    /* renamed from: -$$Nest$fgetsearchEditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor m12563$$Nest$fgetsearchEditText(SearchField r0) {
        return r0.searchEditText;
    }

    /* renamed from: -$$Nest$mgetThemedColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12564$$Nest$mgetThemedColor(SearchField r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public SearchField(Context r12, boolean r13, float r14, Theme.ResourcesProvider r15) {
        super(r12);
        this.resourcesProvider = r15;
        View r152 = new View(r12);
        this.searchBackground = r152;
        r152.setBackgroundDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(18.0f), getThemedColor(Theme.key_dialogSearchBackground)));
        if (r13 == false) goto L5;
        FrameLayout.LayoutParams r142 = LayoutHelper.createFrameRelatively(-1.0f, 36.0f, 8388659, r14, 11.0f, r14, 0.0f);
        float r3 = r14;
    L6:
        addView(this.searchBackground, r142);
        ImageView r143 = new ImageView(r12);
        this.searchIconImageView = r143;
        ImageView.ScaleType r153 = ImageView.ScaleType.CENTER;
        r143.setScaleType(r153);
        this.searchIconImageView.setImageResource(R.drawable.smiles_inputsearch);
        this.searchIconImageView.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_dialogSearchIcon), PorterDuff.Mode.MULTIPLY));
        if (r13 == false) goto L9;
        FrameLayout.LayoutParams r0 = LayoutHelper.createFrameRelatively(36.0f, 36.0f, 8388659, r3 + 2.0f, 11.0f, 0.0f, 0.0f);
    L10:
        addView(this.searchIconImageView, r0);
        ImageView r02 = new ImageView(r12);
        this.clearSearchImageView = r02;
        r02.setScaleType(r153);
        ImageView r154 = this.clearSearchImageView;
        AnonymousClass1 r03 = new AnonymousClass1(this);
        this.progressDrawable = r03;
        r154.setImageDrawable(r03);
        this.progressDrawable.setSide(AndroidUtilities.dp(7.0f));
        this.clearSearchImageView.setScaleX(0.1f);
        this.clearSearchImageView.setScaleY(0.1f);
        this.clearSearchImageView.setAlpha(0.0f);
        if (r13 == false) goto L13;
        FrameLayout.LayoutParams r155 = LayoutHelper.createFrameRelatively(36.0f, 36.0f, 8388661, r3, 11.0f, r3, 0.0f);
    L14:
        addView(this.clearSearchImageView, r155);
        this.clearSearchImageView.setOnClickListener(new SearchField$$ExternalSyntheticLambda0(this));
        AnonymousClass2 r156 = new AnonymousClass2(this, r12);
        this.searchEditText = r156;
        r156.setTextSize(1, 16.0f);
        this.searchEditText.setTypeface(AndroidUtilities.regular());
        this.searchEditText.setHintTextColor(getThemedColor(Theme.key_dialogSearchHint));
        this.searchEditText.setTextColor(getThemedColor(Theme.key_dialogSearchText));
        this.searchEditText.setBackgroundDrawable(null);
        this.searchEditText.setPadding(0, 0, 0, 0);
        this.searchEditText.setMaxLines(1);
        this.searchEditText.setLines(1);
        this.searchEditText.setSingleLine(true);
        EditTextBoldCursor r122 = this.searchEditText;
        if (r13 == false) goto L17;
        int r157 = LayoutHelper.getAbsoluteGravityStart();
    L18:
        r122.setGravity(r157 | 16);
        this.searchEditText.setImeOptions(268435459);
        this.searchEditText.setCursorColor(getThemedColor(Theme.key_featuredStickers_addedIcon));
        this.searchEditText.setCursorSize(AndroidUtilities.dp(20.0f));
        this.searchEditText.setCursorWidth(1.5f);
        if (r13 == false) goto L21;
        float r144 = 2.0f + r3;
        FrameLayout.LayoutParams r123 = LayoutHelper.createFrameRelatively(-1.0f, 40.0f, 8388659, r144 + 38.0f, 9.0f, r144 + 30.0f, 0.0f);
    L22:
        addView(this.searchEditText, r123);
        this.searchEditText.addTextChangedListener(new AnonymousClass3(this));
        this.searchEditText.setOnEditorActionListener(new SearchField$$ExternalSyntheticLambda1(this));
        return;
    L21:
        float r145 = 2.0f + r3;
        r123 = LayoutHelper.createFrame(-1, 40.0f, 51, r145 + 38.0f, 9.0f, r145 + 30.0f, 0.0f);
        goto L22
    L17:
        r157 = 3;
        goto L18
    L13:
        r155 = LayoutHelper.createFrame(36, 36.0f, 53, r3, 11.0f, r3, 0.0f);
        goto L14
    L9:
        r0 = LayoutHelper.createFrame(36, 36.0f, 51, r3 + 2.0f, 11.0f, 0.0f, 0.0f);
        goto L10
    L5:
        r3 = r14;
        r142 = LayoutHelper.createFrame(-1, 36.0f, 51, r3, 11.0f, r3, 0.0f);
        goto L6
    }

    public SearchField(Context r2, boolean r3, Theme.ResourcesProvider r4) {
        this(r2, r3, 14.0f, r4);
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$new$0(View r2) {
        this.searchEditText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        AndroidUtilities.showKeyboard(this.searchEditText);
    }

    private /* synthetic */ boolean lambda$new$1(TextView r1, int r2, KeyEvent r3) {
        if (r3 != null) goto L4;
        return false;
    L4:
        if (r3.getAction() != 1) goto L8;
        if (r3.getKeyCode() != 84) goto L8;
    L11:
        this.searchEditText.hideActionMode();
        AndroidUtilities.hideKeyboard(this.searchEditText);
        return false;
    L8:
        if (r3.getAction() == 0) goto L10;
        return false;
    L10:
        if (r3.getKeyCode() == 66) goto L11;
        return false;
    }

    public CloseProgressDrawable2 getProgressDrawable() {
        return this.progressDrawable;
    }

    public View getSearchBackground() {
        return this.searchBackground;
    }

    public EditTextBoldCursor getSearchEditText() {
        return this.searchEditText;
    }

    public void getThemeDescriptions(List<ThemeDescription> r18) {
        r18.add(new ThemeDescription(this.searchBackground, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_dialogSearchBackground));
        ImageView r4 = this.searchIconImageView;
        int r5 = ThemeDescription.FLAG_IMAGECOLOR;
        int r10 = Theme.key_dialogSearchIcon;
        r18.add(new ThemeDescription(r4, r5, null, null, null, null, r10));
        r18.add(new ThemeDescription(this.clearSearchImageView, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, r10));
        r18.add(new ThemeDescription(this.searchEditText, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_dialogSearchText));
        r18.add(new ThemeDescription(this.searchEditText, ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, Theme.key_dialogSearchHint));
        r18.add(new ThemeDescription(this.searchEditText, ThemeDescription.FLAG_CURSORCOLOR, null, null, null, null, Theme.key_featuredStickers_addedIcon));
    }

    public void onFieldTouchUp(EditTextBoldCursor r1) {
    }

    public abstract void onTextChange(String r1);

    public void processTouchEvent(MotionEvent r1) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean r1) {
        super.requestDisallowInterceptTouchEvent(r1);
    }

    public void setHint(String r1) {
        this.searchEditText.setHint(r1);
    }
}
