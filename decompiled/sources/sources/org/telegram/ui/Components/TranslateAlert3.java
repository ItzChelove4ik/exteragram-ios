package org.telegram.ui.Components;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LanguageDetector;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.TranslateController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.XiaomiUtilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.spoilers.SpoilersTextView;
import org.telegram.ui.GradientClip;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class TranslateAlert3 extends BottomSheetWithRecyclerListView {
    private UniversalAdapter adapter;
    private ButtonWithCounterView button;
    private FrameLayout buttonContainer;
    private ImageView closeView;
    private boolean collapsed;
    private long dialogId;
    private String from_lang;
    private int messageId;
    private boolean noforwards;
    private Utilities.CallbackReturn<URLSpan, Boolean> onLinkPress;
    private Utilities.Callback<CharSequence> onUseListener;
    private int requestId;
    private boolean summarized;
    private CharSequence text;
    private String to_lang;
    private int tone;
    private String[] tones;
    private String[] tonesText;
    private CharSequence translated;
    private boolean translatedLoading;

    public static class Header extends FrameLayout implements Theme.Colorable {
        public final LinearLayout anotherExample;
        public final ImageView anotherExampleIcon;
        public final TextView anotherExampleText;
        public final CheckBox2 emojifyCheckbox;
        public final LinearLayout emojifyContainer;
        public final TextView emojifyTextView;
        public final ImageView imageView;
        public final LinearLayout layout1;
        public final LinearLayout layout2;
        private final Theme.ResourcesProvider resourcesProvider;
        public final TextView text1View;
        public final TextView text2View;
        public final TextView text3View;

        public static class Factory extends UItem.UItemFactory<Header> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem of(int r8, CharSequence r9, CharSequence r10, CharSequence r11, View.OnClickListener r12) {
                return of(r8, r9, r10, r11, r12, false, null, null);
            }

            public static UItem of(int r1, CharSequence r2, CharSequence r3, CharSequence r4, View.OnClickListener r5, boolean r6, View.OnClickListener r7, View.OnClickListener r8) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.id = r1;
                r0.text = r2;
                r0.subtext = r3;
                r0.textValue = r4;
                r0.clickCallback = r5;
                r0.checked = r6;
                r0.clickCallback2 = r7;
                r0.object = r8;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r9, UItem r10, boolean r11, UniversalAdapter r12, UniversalRecyclerView r13) {
                Header r0 = (Header) r9;
                CharSequence r1 = r10.text;
                CharSequence r2 = r10.subtext;
                CharSequence r3 = r10.textValue;
                View.OnClickListener r4 = r10.clickCallback;
                boolean r5 = r10.checked;
                View.OnClickListener r6 = r10.clickCallback2;
                Object r8 = r10.object;
                if ((r8 instanceof View.OnClickListener) == false) goto L6;
                View.OnClickListener r82 = (View.OnClickListener) r8;
            L7:
                r0.set(r1, r2, r3, r4, r5, r6, r82);
                return;
            L6:
                r82 = null;
                goto L7
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean contentsEquals(UItem r2, UItem r3) {
                if (TextUtils.equals(r2.text, r3.text) == true) goto L5;
                return false;
            L5:
                if (TextUtils.equals(r2.subtext, r3.subtext) == true) goto L7;
                return false;
            L7:
                if (TextUtils.equals(r2.textValue, r3.textValue) == true) goto L9;
                return false;
            L9:
                if (r2.clickCallback2 != r3.clickCallback2) goto L16;
                return true;
            L16:
                return false;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public Header createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new Header(r1, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean equals(UItem r1, UItem r2) {
                if (r1.id != r2.id) goto L6;
                return true;
            L6:
                return false;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean isClickable() {
                return false;
            }
        }

        public static /* synthetic */ void $r8$lambda$MsC9pS0A9MmBRrGcvwev9SiQLxA(Header r0, View.OnClickListener r1, View r2) {
            r0.lambda$set$0(r1, r2);
        }

        public Header(Context r24, Theme.ResourcesProvider r25) {
            super(r24);
            this.resourcesProvider = r25;
            setClipToPadding(false);
            setPadding(AndroidUtilities.dp(20.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(6.0f));
            LinearLayout r4 = new LinearLayout(r24);
            this.layout1 = r4;
            r4.setOrientation(0);
            addView(r4, LayoutHelper.createFrame(-2, -2, 19));
            TextView r5 = new TextView(r24);
            this.text1View = r5;
            r5.setTextSize(1, 14.0f);
            r5.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_EXTRA_BOLD));
            r4.addView(r5, LayoutHelper.createLinear(-2, -2, 19, 0, 0, 0, 0));
            LinearLayout r52 = new LinearLayout(r24);
            this.layout2 = r52;
            r52.setOrientation(0);
            r52.setPadding(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(1.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(1.0f));
            ScaleStateListAnimator.apply(r52);
            r4.addView(r52, LayoutHelper.createFrame(-2, -2.0f, 19, -6.0f, 0.0f, 0.0f, 0.0f));
            TextView r11 = new TextView(r24);
            this.text2View = r11;
            r11.setTextSize(1, 14.0f);
            r11.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_EXTRA_BOLD));
            r52.addView(r11, LayoutHelper.createLinear(-2, -2, 19, 0, 0, 0, 0));
            ImageView r112 = new ImageView(r24);
            this.imageView = r112;
            r112.setImageResource(R.drawable.arrows_select);
            r52.addView(r112, LayoutHelper.createLinear(16, 16, 19, 1, 0, 0, 0));
            r112.setTranslationY(AndroidUtilities.dp(1.0f));
            TextView r53 = new TextView(r24);
            this.text3View = r53;
            r53.setTextSize(1, 14.0f);
            r53.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_EXTRA_BOLD));
            r4.addView(r53, LayoutHelper.createLinear(-2, -2, 19, -6, 0, 0, 0));
            LinearLayout r42 = new LinearLayout(r24);
            this.emojifyContainer = r42;
            r42.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(3.0f));
            r42.setClipToPadding(false);
            r42.setOrientation(0);
            CheckBox2 r54 = new CheckBox2(r24, 20, r25);
            this.emojifyCheckbox = r54;
            r54.setColor(Theme.key_radioBackgroundChecked, Theme.key_checkboxDisabled, Theme.key_checkboxCheck);
            r54.setDrawUnchecked(true);
            r54.setChecked(false, false);
            r54.setDrawBackgroundAsArc(10);
            r42.addView(r54, LayoutHelper.createLinear(22, 22, 16, 0, 0, 0, 0));
            TextView r55 = new TextView(r24);
            this.emojifyTextView = r55;
            r55.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r25));
            r55.setTextSize(1, 14.0f);
            r55.setTypeface(AndroidUtilities.bold());
            r55.setText(LocaleController.getString(R.string.AIEditorEmojify));
            r42.addView(r55, LayoutHelper.createLinear(-2, -2, 16, 3, -1, 2, 0));
            addView(r42, LayoutHelper.createFrame(-2, -2.0f, 21, 0.0f, -3.0f, -6.0f, -3.0f));
            ScaleStateListAnimator.apply(r42, 0.025f, 1.5f);
            LinearLayout r43 = new LinearLayout(r24);
            this.anotherExample = r43;
            r43.setPadding(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(3.0f));
            r43.setOrientation(0);
            r43.setVisibility(8);
            addView(r43, LayoutHelper.createFrame(-2, -2.0f, 53, 0.0f, 0.0f, -6.0f, 0.0f));
            ScaleStateListAnimator.apply(r43, 0.025f, 1.5f);
            ImageView r2 = new ImageView(r24);
            this.anotherExampleIcon = r2;
            r2.setImageResource(R.drawable.mini_replace2);
            r43.addView(r2, LayoutHelper.createLinear(-2, -2, 16, 0, 0, 4, 0));
            TextView r22 = new TextView(r24);
            this.anotherExampleText = r22;
            r22.setTextSize(1, 14.0f);
            r22.setTypeface(AndroidUtilities.bold());
            r22.setText(LocaleController.getString(R.string.AIEditorAnotherExample));
            r43.addView(r22, LayoutHelper.createLinear(-2, -2, 16));
            updateColors();
        }

        private /* synthetic */ void lambda$set$0(View.OnClickListener r3, View r4) {
            this.anotherExampleIcon.animate().rotation(this.anotherExampleIcon.getRotation() + 180.0f).setDuration(380).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
            if (r3 == null) goto L6;
            r3.onClick(r4);
            return;
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public /* bridge */ /* synthetic */ int[] getColorKeys() {
            return super.getColorKeys();
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }

        public void set(CharSequence r2, CharSequence r3, CharSequence r4, View.OnClickListener r5, boolean r6, View.OnClickListener r7, View.OnClickListener r8) {
            this.text1View.setText(r2);
            this.text2View.setText(r3);
            this.text3View.setText(r4);
            ImageView r22 = this.imageView;
            int r32 = 8;
            if (r5 == null) goto L5;
            int r0 = 0;
        L6:
            r22.setVisibility(r0);
            this.layout2.setOnClickListener(r5);
            LinearLayout r23 = this.layout2;
            if (r5 == null) goto L9;
            boolean r52 = true;
        L10:
            r23.setClickable(r52);
            this.emojifyCheckbox.setChecked(r6, false);
            LinearLayout r24 = this.emojifyContainer;
            if (r7 == null) goto L13;
            int r53 = 0;
        L14:
            r24.setVisibility(r53);
            this.emojifyContainer.setOnClickListener(r7);
            LinearLayout r25 = this.anotherExample;
            if (r8 == null) goto L17;
            r32 = 0;
        L17:
            r25.setVisibility(r32);
            this.anotherExample.setOnClickListener(new TranslateAlert3$Header$$ExternalSyntheticLambda0(this, r8));
            updateColors();
            return;
        L13:
            r53 = 8;
            goto L14
        L9:
            r52 = false;
            goto L10
        L5:
            r0 = 8;
            goto L6
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public void updateColors() {
            TextView r0 = this.text1View;
            int r1 = Theme.key_windowBackgroundWhiteBlackText;
            r0.setTextColor(Theme.getColor(r1, this.resourcesProvider));
            TextView r02 = this.text2View;
            int r2 = Theme.key_windowBackgroundWhiteBlueHeader;
            r02.setTextColor(Theme.getColor(r2, this.resourcesProvider));
            this.text3View.setTextColor(Theme.getColor(r1, this.resourcesProvider));
            this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r2, this.resourcesProvider), PorterDuff.Mode.MULTIPLY));
            LinearLayout r03 = this.layout2;
            if (r03.isClickable() == false) goto L5;
            Drawable r12 = Theme.createRadSelectorDrawable(Theme.multAlpha(Theme.getColor(r2), 0.1f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f));
        L6:
            r03.setBackground(r12);
            boolean r04 = this.layout2.isClickable();
            LinearLayout r13 = this.layout2;
            if (r04 == false) goto L9;
            ScaleStateListAnimator.apply(r13);
        L10:
            this.emojifyContainer.setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector, this.resourcesProvider), 24, 24));
            ImageView r05 = this.anotherExampleIcon;
            int r22 = Theme.key_featuredStickers_addButton;
            r05.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r22, this.resourcesProvider), PorterDuff.Mode.SRC_IN));
            this.anotherExampleText.setTextColor(Theme.getColor(r22, this.resourcesProvider));
            this.anotherExample.setBackground(Theme.createRadSelectorDrawable(Theme.multAlpha(Theme.getColor(r22, this.resourcesProvider), 0.1f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f)));
            return;
        L9:
            ScaleStateListAnimator.reset(r13);
            goto L10
        L5:
            r12 = null;
            goto L6
        }
    }

    public static class Text extends FrameLayout implements Theme.Colorable {
        private final AnimatedFloat animatedClipHeight;
        private int clipHeight;
        public boolean collapsed;
        public final ImageView copyButton;
        public TextView moreView;
        public boolean needDivider;
        private final Theme.ResourcesProvider resourcesProvider;
        public SpoilersTextView shortTextView;
        public SpoilersTextView textView;
        private FrameLayout.LayoutParams textViewLayoutParams;

        /* renamed from: org.telegram.ui.Components.TranslateAlert3$Text$1, reason: invalid class name */
        public class AnonymousClass1 extends SpoilersTextView {
            private GradientClip clip;
            final /* synthetic */ Text this$0;

            public AnonymousClass1(Text r1, Context r2) {
                this.this$0 = r1;
                super(r2);
                this.clip = new GradientClip();
            }

            @Override // org.telegram.ui.Components.spoilers.SpoilersTextView, android.widget.TextView, android.view.View
            public void onDraw(Canvas r10) {
                int r0 = this.this$0.moreView.getWidth() + AndroidUtilities.dp(8.0f);
                r10.saveLayerAlpha(getScrollX(), 0.0f, (getScrollX() + getWidth()) - r0, getHeight(), 255, 31);
                super.onDraw(r10);
                r10.save();
                r10.translate(getPaddingLeft(), getPaddingTop());
                SquigglyLinesSpan.drawOnText(r10, getLayout());
                r10.restore();
                RectF r102 = AndroidUtilities.rectTmp;
                r102.set((getWidth() - r0) - AndroidUtilities.dp(24.0f), 0.0f, getWidth() - r0, getHeight());
                this.clip.draw(r10, r102, 2, 1.0f);
                r10.restore();
            }
        }

        /* renamed from: org.telegram.ui.Components.TranslateAlert3$Text$2, reason: invalid class name */
        public class AnonymousClass2 extends SpoilersTextView {
            final /* synthetic */ Text this$0;

            public AnonymousClass2(Text r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Components.spoilers.SpoilersTextView, android.widget.TextView, android.view.View
            public void onDraw(Canvas r3) {
                super.onDraw(r3);
                r3.save();
                r3.translate(getPaddingLeft(), getPaddingTop());
                SquigglyLinesSpan.drawOnText(r3, getLayout());
                r3.restore();
            }
        }

        public static class Factory extends UItem.UItemFactory<Text> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem of(int r1, CharSequence r2, boolean r3, boolean r4, View.OnClickListener r5, LinkSpanDrawable.LinksTextView.OnLinkPress r6, View.OnClickListener r7) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.id = r1;
                r0.text = r2;
                r0.collapsed = r3;
                r0.locked = r4;
                r0.clickCallback = r5;
                r0.object = r6;
                r0.clickCallback2 = r7;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r9, UItem r10, boolean r11, UniversalAdapter r12, UniversalRecyclerView r13) {
                Text r0 = (Text) r9;
                CharSequence r1 = r10.text;
                boolean r2 = r10.collapsed;
                View.OnClickListener r3 = r10.clickCallback;
                Object r8 = r10.object;
                if (r8 == null) goto L6;
                LinkSpanDrawable.LinksTextView.OnLinkPress r82 = (LinkSpanDrawable.LinksTextView.OnLinkPress) r8;
            L5:
                LinkSpanDrawable.LinksTextView.OnLinkPress r4 = r82;
                r0.set(r1, r2, r3, r4, r10.locked, r10.clickCallback2, r11);
                return;
            L6:
                r82 = null;
                goto L5
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean contentsEquals(UItem r2, UItem r3) {
                if (TextUtils.equals(r2.text, r3.text) == true) goto L5;
                return false;
            L5:
                if (r2.collapsed != r3.collapsed) goto L10;
                return true;
            L10:
                return false;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public Text createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new Text(r1, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean equals(UItem r1, UItem r2) {
                if (r1.id != r2.id) goto L6;
                return true;
            L6:
                return false;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean isClickable() {
                return false;
            }
        }

        /* renamed from: $r8$lambda$UGX_qJCNxwgX-n-2KaIG9_iRAoI, reason: not valid java name */
        public static /* synthetic */ void m13424$r8$lambda$UGX_qJCNxwgXn2KaIG9_iRAoI(Text r0) {
            r0.lambda$set$0();
        }

        public Text(Context r13, Theme.ResourcesProvider r14) {
            super(r13);
            this.clipHeight = -1;
            this.animatedClipHeight = new AnimatedFloat(this, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.resourcesProvider = r14;
            setClipToPadding(false);
            setPadding(AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(16.0f));
            AnonymousClass1 r142 = new AnonymousClass1(this, r13);
            this.shortTextView = r142;
            NotificationCenter.listenEmojiLoading(r142);
            this.shortTextView.setTextSize(1, 16.0f);
            this.shortTextView.setMaxLines(1);
            this.shortTextView.setSingleLine();
            this.shortTextView.setEllipsize(TextUtils.TruncateAt.END);
            addView(this.shortTextView, LayoutHelper.createFrame(-1, -2.0f));
            TextView r143 = new TextView(r13);
            this.moreView = r143;
            r143.setText(LocaleController.getString(R.string.DescriptionMore));
            this.moreView.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
            this.moreView.setGravity(17);
            ScaleStateListAnimator.apply(this.moreView);
            addView(this.moreView, LayoutHelper.createFrame(-2, 18.0f, 53, 0.0f, 1.0f, 0.0f, 0.0f));
            AnonymousClass2 r12 = new AnonymousClass2(this, r13);
            this.textView = r12;
            NotificationCenter.listenEmojiLoading(r12);
            this.textView.setTextSize(1, 16.0f);
            this.textView.setTextIsSelectable(true);
            View r122 = this.textView;
            FrameLayout.LayoutParams r144 = LayoutHelper.createFrame(-1, -2.0f);
            this.textViewLayoutParams = r144;
            addView(r122, r144);
            ImageView r123 = new ImageView(r13);
            this.copyButton = r123;
            r123.setImageResource(R.drawable.msg_copy);
            r123.setScaleType(ImageView.ScaleType.CENTER);
            r123.setContentDescription(LocaleController.getString(R.string.Copy));
            ScaleStateListAnimator.apply(r123);
            addView(r123, LayoutHelper.createFrame(38, 38.0f, 85, 0.0f, 0.0f, -16.0f, -12.0f));
            r123.setVisibility(8);
            updateColors();
        }

        private /* synthetic */ void lambda$set$0() {
            this.shortTextView.setVisibility(8);
        }

        private boolean needsBottomMargin() {
            if (this.copyButton.getVisibility() == 0) goto L5;
            return false;
        L5:
            Layout r4 = this.textView.getLayout();
            if (r4.getLineCount() > 0) goto L9;
            return false;
        L9:
            if (r4.getLineRight(r4.getLineCount() - 1) <= (r4.getWidth() - AndroidUtilities.dp(42.0f))) goto L11;
            return true;
        L11:
            return false;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r4) {
            r4.save();
            r4.clipRect(0.0f, 0.0f, getWidth(), this.animatedClipHeight.set(this.clipHeight));
            super.dispatchDraw(r4);
            r4.restore();
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public /* bridge */ /* synthetic */ int[] getColorKeys() {
            return super.getColorKeys();
        }

        @Override // android.view.View
        public void onDraw(Canvas r9) {
            super.onDraw(r9);
            if (this.needDivider == true) goto L5;
            return;
        L5:
            if (this.collapsed == false) goto L7;
            SpoilersTextView r0 = this.shortTextView;
        L8:
            Paint r1 = Theme.getThemePaint("paintDivider", this.resourcesProvider);
            if (r1 != null) goto L11;
            r1 = Theme.dividerPaint;
        L11:
            Paint r7 = r1;
            if (LocaleController.isRTL == false) goto L15;
            r9.drawRect(0.0f, getMeasuredHeight() - 1, r0.getRight(), getMeasuredHeight(), r7);
            return;
        L15:
            r9.drawRect(r0.getLeft(), getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight(), r7);
            return;
        L7:
            r0 = this.textView;
            goto L8
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            this.clipHeight = getMeasuredHeight();
            int r32 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30);
            this.textViewLayoutParams.bottomMargin = 0;
            super.onMeasure(r32, r4);
            if (needsBottomMargin() == false) goto L6;
            this.textViewLayoutParams.bottomMargin = AndroidUtilities.dp(26.0f);
            super.onMeasure(r32, r4);
        L6:
            if (getMeasuredHeight() > this.clipHeight) goto L8;
        L11:
            AnimatedFloat r33 = this.animatedClipHeight;
            int r42 = getMeasuredHeight();
            this.clipHeight = r42;
            r33.force(r42);
            return;
        L8:
            if (this.collapsed == true) goto L11;
            this.clipHeight = getMeasuredHeight();
            invalidate();
        }

        public void set(CharSequence r12, boolean r13, View.OnClickListener r14, LinkSpanDrawable.LinksTextView.OnLinkPress r15, boolean r16, View.OnClickListener r17, boolean r18) {
            if (r12 != null) goto L5;
            CharSequence r122 = _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            SpannableStringBuilder r2 = new SpannableStringBuilder(r122);
            int r4 = 0;
            LoadingSpan[] r123 = (LoadingSpan[]) r2.getSpans(0, r2.length(), LoadingSpan.class);
            if (r123 == null) goto L13;
            int r3 = 0;
        L10:
            if (r3 >= r123.length) goto L13;
            int r5 = r2.getSpanStart(r123[r3]);
            int r6 = r2.getSpanEnd(r123[r3]);
            r2.removeSpan(r123[r3]);
            SpoilersTextView r8 = this.textView;
            LoadingSpan r9 = r123[r3];
            r2.setSpan(new LoadingSpan(r8, r9.size, r9.yOffset).setHeight(r123[r3].height).setAlpha(r123[r3].alpha).setFullWidth(r123[r3].fullWidth), r5, r6, 33);
            r3 = r3 + 1;
        L13:
            if (this.collapsed == false) goto L16;
            if (r13 == true) goto L16;
            this.shortTextView.setVisibility(0);
            this.textView.setVisibility(0);
            ViewPropertyAnimator r32 = this.shortTextView.animate().alpha(0.0f).withEndAction(new TranslateAlert3$Text$$ExternalSyntheticLambda0(this));
            CubicBezierInterpolator r62 = CubicBezierInterpolator.EASE_OUT_QUINT;
            r32.setInterpolator(r62).setDuration(320).start();
            this.textView.animate().alpha(1.0f).setInterpolator(r62).setDuration(320).start();
        L25:
            this.collapsed = r13;
            TextView r33 = this.moreView;
            if (r13 == false) goto L28;
            int r63 = 0;
        L29:
            r33.setVisibility(r63);
            this.moreView.setOnClickListener(r14);
            setClipChildren(r13);
            this.shortTextView.setText(r2);
            this.textView.setText(r2);
            SpoilersTextView r132 = this.textView;
            if (r16 == true) goto L35;
            if (r123 != null) goto L33;
        L34:
            boolean r124 = true;
        L36:
            r132.setTextIsSelectable(r124);
            this.textView.setOnLinkPressListener(r15);
            ImageView r125 = this.copyButton;
            if (r17 != null) goto L40;
            r4 = 8;
        L40:
            r125.setVisibility(r4);
            this.copyButton.setOnClickListener(r17);
            this.needDivider = r18;
            setWillNotDraw(!r18);
            return;
        L33:
            if (r123.length == 0) goto L34;
        L35:
            r124 = false;
            goto L36
        L28:
            r63 = 8;
        L16:
            SpoilersTextView r34 = this.shortTextView;
            if (r13 == false) goto L19;
            int r64 = 0;
        L20:
            r34.setVisibility(r64);
            SpoilersTextView r35 = this.textView;
            if (r13 == true) goto L23;
            int r65 = 0;
        L24:
            r35.setVisibility(r65);
            goto L25
        L23:
            r65 = 8;
            goto L24
        L19:
            r64 = 8;
            goto L20
        L5:
            r122 = AnimatedEmojiSpan.cloneSpans(r12);
            goto L6
        }

        public void setHandlesColor(int r4) {
            if (Build.VERSION.SDK_INT >= 29) goto L5;
            return;
        L5:
            if (XiaomiUtilities.isMIUI() == true) goto L13;
            Drawable r0 = this.textView.getTextSelectHandleLeft();     // Catch: Exception -> L9
            PorterDuff.Mode r1 = PorterDuff.Mode.SRC_IN;     // Catch: Exception -> L9
            r0.setColorFilter(r4, r1);     // Catch: Exception -> L9
            this.textView.setTextSelectHandleLeft(r0);     // Catch: Exception -> L9
            Drawable r02 = this.textView.getTextSelectHandle();     // Catch: Exception -> L9
            r02.setColorFilter(r4, r1);     // Catch: Exception -> L9
            this.textView.setTextSelectHandle(r02);     // Catch: Exception -> L9
            Drawable r03 = this.textView.getTextSelectHandleRight();     // Catch: Exception -> L9
            r03.setColorFilter(r4, r1);     // Catch: Exception -> L9
            this.textView.setTextSelectHandleRight(r03);     // Catch: Exception -> L9
            return;
        L14:
            return;
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public void updateColors() {
            SpoilersTextView r0 = this.shortTextView;
            int r1 = Theme.key_windowBackgroundWhiteBlackText;
            r0.setTextColor(Theme.getColor(r1, this.resourcesProvider));
            TextView r02 = this.moreView;
            int r2 = Theme.key_windowBackgroundWhiteBlueHeader;
            r02.setTextColor(Theme.getColor(r2, this.resourcesProvider));
            this.moreView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(9.0f), Theme.multAlpha(Theme.getColor(r2, this.resourcesProvider), 0.1f)));
            this.textView.setTextColor(Theme.getColor(r1, this.resourcesProvider));
            this.textView.setLinkTextColor(Theme.getColor(r2, this.resourcesProvider));
            this.textView.setHighlightColor(Theme.getColor(Theme.key_chat_inTextSelectionHighlight, this.resourcesProvider));
            setHandlesColor(Theme.getColor(Theme.key_chat_TextSelectionCursor, this.resourcesProvider));
            ImageView r03 = this.copyButton;
            int r22 = Theme.key_featuredStickers_addButton;
            r03.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r22, this.resourcesProvider), PorterDuff.Mode.SRC_IN));
            this.copyButton.setBackground(Theme.createSelectorDrawable(Theme.multAlpha(Theme.getColor(r22, this.resourcesProvider), 0.1f)));
        }
    }

    public static /* synthetic */ void $r8$lambda$4rEhjt_MbneRaO1WDCXlDXgENAI(TranslateAlert3 r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$6DEoHRmHdsLPWHFYt7te6dECXzw(TranslateAlert3 r0, TLRPC.TL_textWithEntities r1, TLRPC.TL_error r2) {
        r0.lambda$requestTranslate$11(r1, r2);
    }

    /* renamed from: $r8$lambda$Hlwbe04V5P-oD1mmjnW24I1394g, reason: not valid java name */
    public static /* synthetic */ void m13418$r8$lambda$Hlwbe04V5PoD1mmjnW24I1394g(TranslateAlert3 r0, Theme.ResourcesProvider r1, View r2, int r3) {
        r0.lambda$new$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$KKhR6eRxuTzksRK8jZBYrLcCXZg(TranslateAlert3 r0, View r1) {
        r0.onToLangMenu(r1);
    }

    public static /* synthetic */ void $r8$lambda$La_Sgekkm03rUzm5xd4yfMsS9cY(TranslateAlert3 r0, int r1) {
        r0.lambda$onToLangMenu$4(r1);
    }

    /* renamed from: $r8$lambda$Tu74fLed9VlCe-hQxqLxD6XCJO4, reason: not valid java name */
    public static /* synthetic */ void m13419$r8$lambda$Tu74fLed9VlCehQxqLxD6XCJO4(TranslateAlert3 r0, View r1) {
        r0.lambda$requestTranslate$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$WQ7s9AyKKOwwqT5RazCMagzoG7o(Exception r0) {
        FileLog.e(r0);
    }

    public static /* synthetic */ void $r8$lambda$WlSvybLsZ7hpCq2QXw4aLfEqSSE(ItemOptions r0, boolean r1, Runnable r2, View r3) {
        r0.dismiss();
        if (r1 == true) goto L7;
        if (r2 == null) goto L8;
        r2.run();
        return;
    L8:
        return;
    }

    public static /* synthetic */ void $r8$lambda$bs82YpQjgpclwZNkkp2BLqiRBdI(TranslateAlert3 r0, View r1) {
        r0.lambda$show$9(r1);
    }

    /* renamed from: $r8$lambda$jY-1y1hnphBLQSKkm-UQHShZsjU, reason: not valid java name */
    public static /* synthetic */ void m13420$r8$lambda$jY1y1hnphBLQSKkmUQHShZsjU(TranslateAlert3 r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$mRCri-LtTIX7cAmVKo_ybU5yGAM, reason: not valid java name */
    public static /* synthetic */ void m13421$r8$lambda$mRCriLtTIX7cAmVKo_ybU5yGAM(TranslateAlert3 r0, TranslateController.Language r1) {
        r0.lambda$onToLangMenu$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$pHa6XABYMTzi1sAXErxfgeQ0JPQ(TranslateAlert3 r0, UniversalAdapter r1, View r2) {
        r0.lambda$fillItems$8(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$qbf1qmQ7PA2RMq1VhJtFpU5KDfE(TranslateAlert3 r0, TranslateController.Language r1) {
        r0.lambda$onToLangMenu$5(r1);
    }

    /* renamed from: $r8$lambda$sFKP102Ash0ilzKf-bDtLPiID8g, reason: not valid java name */
    public static /* synthetic */ void m13422$r8$lambda$sFKP102Ash0ilzKfbDtLPiID8g(TranslateAlert3 r0, ClickableSpan r1) {
        r0.onLinkPressed(r1);
    }

    public static /* synthetic */ void $r8$lambda$sIfaKWO6YFaUqQgfkztbB5Fnlpc(TranslateAlert3 r0, TLRPC.TL_messages_translateResult r1, TLRPC.TL_error r2) {
        r0.lambda$requestTranslate$14(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$w4dQuwJBnp1l24Ts0yhH5FQTJFs(TranslateAlert3 r0, String r1) {
        r0.lambda$setText$2(r1);
    }

    /* renamed from: $r8$lambda$wjc7WuRupP-HCFB-DawDVGHkchw, reason: not valid java name */
    public static /* synthetic */ void m13423$r8$lambda$wjc7WuRupPHCFBDawDVGHkchw(TranslateAlert3 r0, View r1) {
        r0.lambda$requestTranslate$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$zLITipFpm_8WXAkCSEpKA5ZCza4(TranslateAlert3 r0, View r1) {
        r0.lambda$requestTranslate$13(r1);
    }

    public TranslateAlert3(Context r17, Theme.ResourcesProvider r18) {
        super(r17, null, false, false, false, false, BottomSheetWithRecyclerListView.ActionBarType.SLIDING, r18);
        this.tone = 1;
        this.tones = new String[]{"formal", "neutral", "casual"};
        this.tonesText = new String[]{"Formal", "Neutral", "Casual"};
        this.collapsed = true;
        this.requestId = -1;
        ImageView r3 = new ImageView(r17);
        this.closeView = r3;
        r3.setScaleType(ImageView.ScaleType.CENTER);
        this.closeView.setImageResource(R.drawable.ic_close_white);
        ImageView r32 = this.closeView;
        int r4 = Theme.key_windowBackgroundWhiteBlackText;
        r32.setColorFilter(getThemedColor(r4));
        this.closeView.setBackground(Theme.createSelectorDrawable(Theme.multAlpha(getThemedColor(r4), 0.1f)));
        this.actionBar.addView(this.closeView, LayoutHelper.createFrame(54, 54.0f, 85, 0.0f, 0.0f, 8.0f, 0.0f));
        ScaleStateListAnimator.apply(this.closeView, 0.1f, 1.5f);
        this.closeView.setOnClickListener(new TranslateAlert3$$ExternalSyntheticLambda3(this));
        String r33 = TranslateAlert2.getToLanguage();
        this.to_lang = r33;
        if (r33 != null) goto L5;
        this.to_lang = TranslateController.currentLanguage();
    L5:
        this.ignoreTouchActionBar = false;
        this.headerMoveTop = AndroidUtilities.dp(12.0f);
        int r42 = Theme.key_windowBackgroundGray;
        setBackgroundColor(getThemedColor(r42));
        FrameLayout r5 = new FrameLayout(r17);
        this.buttonContainer = r5;
        r5.setBackground(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{Theme.multAlpha(getThemedColor(r42), 0.0f), getThemedColor(r42), getThemedColor(r42)}));
        ButtonWithCounterView r1 = new ButtonWithCounterView(r17, r18).setRound();
        this.button = r1;
        r1.setText(LocaleController.getString(R.string.OK));
        FrameLayout.LayoutParams r12 = LayoutHelper.createFrame(-1, 48.0f, 119, 12.0f, 6.0f, 12.0f, 12.0f);
        int r43 = r12.leftMargin;
        int r52 = this.backgroundPaddingLeft;
        r12.leftMargin = r43 + r52;
        r12.rightMargin += r52;
        this.buttonContainer.addView(this.button, r12);
        this.containerView.addView(this.buttonContainer, LayoutHelper.createFrame(-1, -2, 80));
        RecyclerListView r13 = this.recyclerListView;
        int r2 = this.backgroundPaddingLeft;
        r13.setPadding(r2, 0, r2, AndroidUtilities.dp(66.0f));
        this.recyclerListView.setClipToPadding(false);
        this.recyclerListView.setSections();
        this.recyclerListView.setOnItemClickListener(new TranslateAlert3$$ExternalSyntheticLambda4(this, r18));
        DefaultItemAnimator r14 = new DefaultItemAnimator();
        r14.setSupportsChangeAnimations(false);
        r14.setDelayAnimations(false);
        r14.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r14.setDurations(350);
        this.recyclerListView.setItemAnimator(r14);
        this.adapter.update(false);
    }

    private void addChecked(ItemOptions r9, LinearLayout r10, boolean r11, CharSequence r12, Runnable r13) {
        int r0 = Theme.key_actionBarDefaultSubmenuItem;
        int r1 = Theme.key_actionBarDefaultSubmenuItemIcon;
        ActionBarMenuSubItem r2 = new ActionBarMenuSubItem(getContext(), true, false, false, this.resourcesProvider);
        r2.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
        r2.setText(r12);
        r2.setChecked(r11);
        r2.setColors(Theme.getColor(r0, this.resourcesProvider), Theme.getColor(r1, this.resourcesProvider));
        r2.setSelectorColor(Theme.multAlpha(Theme.getColor(r0, this.resourcesProvider), 0.12f));
        r2.setOnClickListener(new TranslateAlert3$$ExternalSyntheticLambda17(r9, r11, r13));
        r10.addView(r2, LayoutHelper.createLinear(-1, -2));
    }

    private void cancelRequest() {
        if (this.requestId < 0) goto L6;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.requestId, true);
        this.requestId = -1;
        return;
    }

    private void fillItems(ArrayList<UItem> r13, UniversalAdapter r14) {
        r13.add(UItem.asShadow(null));
        r14.itemsOffset = 1;
        r14.whiteSectionStart();
        String r2 = this.from_lang;
        if (r2 == null) goto L5;
        String r22 = TranslateAlert2.capitalFirst(TranslateAlert2.languageName(r2));
    L6:
        r13.add(Header.Factory.of(3, _UrlKt.FRAGMENT_ENCODE_SET, r22, null, null));
        r13.add(Text.Factory.of(4, this.text, this.collapsed, this.noforwards, new TranslateAlert3$$ExternalSyntheticLambda6(this, r14), new TranslateAlert3$$ExternalSyntheticLambda7(this), null));
        StringBuilder r23 = new StringBuilder();
        r23.append(TranslateAlert2.languageName(this.to_lang));
        if (this.tone != 1) goto L9;
    L11:
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        r23.append(r3);
        r13.add(Header.Factory.of(5, _UrlKt.FRAGMENT_ENCODE_SET, TranslateAlert2.capitalFirst(r23.toString()), null, new TranslateAlert3$$ExternalSyntheticLambda8(this)));
        r13.add(Text.Factory.of(6, this.translated, false, this.noforwards, null, new TranslateAlert3$$ExternalSyntheticLambda7(this), null));
        r14.whiteSectionEnd();
        r13.add(UItem.asShadow(null));
        r14.whiteSectionStart();
        if (this.noforwards == true) goto L16;
        r13.add(UItem.asButton(1, R.drawable.msg_copy, LocaleController.getString(R.string.TranslateCopy)));
    L16:
        if (this.dialogId != 0) goto L18;
    L20:
        r14.whiteSectionEnd();
        return;
    L18:
        if (MessagesController.getInstance(this.currentAccount).getTranslateController().isTranslatingDialog(this.dialogId) == true) goto L20;
        r13.add(UItem.asButton(2, R.drawable.msg_translate, LocaleController.getString(R.string.TranslateEntireChat)));
        goto L20
    L9:
        if (this.tonesText == null) goto L11;
        r3 = " (" + this.tonesText[this.tone] + ")";
        goto L12
    L5:
        r22 = LocaleController.getString(R.string.AIEditorOriginalText);
        goto L6
    }

    private /* synthetic */ void lambda$fillItems$8(UniversalAdapter r1, View r2) {
        this.collapsed = false;
        saveScrollPosition();
        r1.update(true);
        applyScrolledPosition(true);
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$new$1(Theme.ResourcesProvider r2, View r3, int r4) {
        UItem r32 = this.adapter.getItem(r4 - 1);
        if (r32 == null) goto L27;
        int r33 = r32.id;
        if (r33 != 1) goto L14;
        CharSequence r22 = this.translated;
        if (r22 != null) goto L10;
        return;
    L10:
        if (this.translatedLoading == true) goto L25;
        AndroidUtilities.addToClipboard(r22);
        return;
    L25:
        return;
    L14:
        if (r33 == 2) goto L16;
        return;
    L16:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L18;
        MessagesController.getInstance(this.currentAccount).getTranslateController().toggleTranslatingDialog(this.dialogId);
        dismiss();
        return;
    L18:
        if (LaunchActivity.getSafeLastFragment() == null) goto L28;
        new PremiumFeatureBottomSheet(getContext(), 13, true, r2).show();
        return;
    L28:
        return;
    }

    private /* synthetic */ void lambda$onToLangMenu$4(int r1) {
        cancelRequest();
        this.tone = r1;
        TranslateAlert2.setToLanguage(this.to_lang);
        requestTranslate();
    }

    private /* synthetic */ void lambda$onToLangMenu$5(TranslateController.Language r1) {
        cancelRequest();
        String r12 = r1.code;
        this.to_lang = r12;
        TranslateAlert2.setToLanguage(r12);
        requestTranslate();
    }

    private /* synthetic */ void lambda$onToLangMenu$6(TranslateController.Language r1) {
        cancelRequest();
        String r12 = r1.code;
        this.to_lang = r12;
        TranslateAlert2.setToLanguage(r12);
        requestTranslate();
    }

    private /* synthetic */ void lambda$requestTranslate$10(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$requestTranslate$11(TLRPC.TL_textWithEntities r3, TLRPC.TL_error r4) {
        this.requestId = -1;
        this.button.setLoading(false);
        if (r4 == null) goto L6;
        BulletinFactory.of(this.topBulletinContainer, this.resourcesProvider).showForError(r4);
        this.button.setText(LocaleController.getString(R.string.OK));
        this.button.setOnClickListener(new TranslateAlert3$$ExternalSyntheticLambda16(this));
        return;
    L6:
        this.translated = MessageObject.formatTextWithEntities(r3);
        this.translatedLoading = false;
        this.adapter.update(true);
    }

    private /* synthetic */ void lambda$requestTranslate$12(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$requestTranslate$13(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$requestTranslate$14(TLRPC.TL_messages_translateResult r3, TLRPC.TL_error r4) {
        this.requestId = -1;
        this.button.setLoading(false);
        if (r4 == null) goto L6;
        BulletinFactory.of(this.topBulletinContainer, this.resourcesProvider).showForError(r4);
        this.button.setText(LocaleController.getString(R.string.OK));
        this.button.setOnClickListener(new TranslateAlert3$$ExternalSyntheticLambda14(this));
        return;
    L6:
        if (r3 != null) goto L8;
    L12:
        this.button.setText(LocaleController.getString(R.string.OK));
        this.button.setOnClickListener(new TranslateAlert3$$ExternalSyntheticLambda15(this));
        return;
    L8:
        if (r3.result.isEmpty() == true) goto L12;
        this.translated = MessageObject.formatTextWithEntities(r3.result.get(0));
        this.translatedLoading = false;
        this.adapter.update(true);
    }

    private /* synthetic */ void lambda$setText$2(String r1) {
        this.from_lang = r1;
        this.adapter.update(true);
    }

    private /* synthetic */ void lambda$show$9(View r2) {
        CharSequence r22 = this.translated;
        if (r22 == null) goto L5;
        this.onUseListener.run(r22);
    L5:
        dismiss();
    }

    private void onLinkPressed(ClickableSpan r3) {
        if (r3 == null) goto L13;
        Utilities.CallbackReturn<URLSpan, Boolean> r0 = this.onLinkPress;
        if (r0 != null) goto L7;
    L11:
        r3.onClick(this.containerView);
        return;
    L7:
        if ((r3 instanceof URLSpan) == false) goto L11;
        if (r0.run((URLSpan) r3).booleanValue() == false) goto L11;
        return;
    }

    private void onToLangMenu(View r13) {
        ItemOptions r3 = ItemOptions.makeOptions(this.container, this.resourcesProvider, r13);
        r3.setMaxHeight(AndroidUtilities.dp(450.0f));
        int r132 = 0;
        r3.setDrawScrim(false);
        r3.setOnTopOfScrim();
        ScrollView r0 = new ScrollView(getContext());
        LinearLayout r4 = new LinearLayout(getContext());
        r4.setOrientation(1);
        r0.addView(r4);
        r3.addView(r0);
        int r02 = 0;
    L4:
        if (r02 >= this.tones.length) goto L10;
        if (this.tone != r02) goto L8;
        boolean r5 = true;
    L9:
        addChecked(r3, r4, r5, this.tonesText[r02], new TranslateAlert3$$ExternalSyntheticLambda11(this, r02));
        r02 = r02 + 1;
        goto L4
    L8:
        r5 = false;
        goto L9
    L10:
        View r12 = new ActionBarPopupWindow.GapView(getContext(), this.resourcesProvider);
        r12.setTag(R.id.fit_width_tag, 1);
        r4.addView(r12, LayoutHelper.createLinear(-1, 8));
        ArrayList<TranslateController.Language> r122 = TranslateController.getSuggestedLanguages(null);
        ArrayList<TranslateController.Language> r9 = TranslateController.getLanguages();
        if (TextUtils.isEmpty(this.to_lang) == true) goto L13;
        addChecked(r3, r4, true, TranslateAlert2.capitalFirst(TranslateAlert2.languageName(this.to_lang)), null);
    L13:
        int r10 = r122.size();
        int r52 = 0;
    L14:
        if (r52 >= r10) goto L19;
        int r11 = r52 + 1;
        TranslateController.Language r6 = r122.get(r52);
        if (TextUtils.equals(r6.code, this.to_lang) == true) goto L18;
        addChecked(r3, r4, false, r6.displayName, new TranslateAlert3$$ExternalSyntheticLambda12(this, r6));
    L18:
        r52 = r11;
        goto L14
    L19:
        View r123 = new ActionBarPopupWindow.GapView(getContext(), this.resourcesProvider);
        r123.setTag(R.id.fit_width_tag, 1);
        r4.addView(r123, LayoutHelper.createLinear(-1, 8));
        int r124 = r9.size();
    L20:
        if (r132 >= r124) goto L22;
        TranslateController.Language r03 = r9.get(r132);
        r132 = r132 + 1;
        TranslateController.Language r04 = r03;
        addChecked(r3, r4, TextUtils.equals(r04.code, this.to_lang), r04.displayName, new TranslateAlert3$$ExternalSyntheticLambda13(this, r04));
        goto L20
    L22:
        r3.show();
    }

    private void requestTranslate() {
        TLRPC.TL_textWithEntities r0 = new TLRPC.TL_textWithEntities();
        CharSequence[] r3 = {this.text};
        r0.entities = MediaDataController.getInstance(this.currentAccount).getEntities(r3, true);
        CharSequence r1 = r3[0];
        if (r1 != null) goto L5;
        String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        r0.text = r12;
        if (this.onUseListener == null) goto L9;
        this.button.setLoading(true);
    L9:
        SpannableStringBuilder r13 = new SpannableStringBuilder();
        r13.append(LocaleController.getString(R.string.Loading));
        r13.setSpan(new LoadingSpan(null, AndroidUtilities.dp(120.0f), 0), 0, r13.length(), 33);
        this.translated = r13;
        this.translatedLoading = true;
        if (this.summarized == true) goto L12;
    L19:
        TLRPC.TL_messages_translateText r14 = new TLRPC.TL_messages_translateText();
        r14.to_lang = this.to_lang;
        if (this.dialogId != 0) goto L22;
    L24:
        r14.flags |= 2;
        r14.text.add(r0);
    L25:
        int r02 = this.tone;
        if (r02 == 1) goto L28;
        r14.flags |= 4;
        r14.tone = this.tones[r02];
    L28:
        this.requestId = ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r14, new AiTonesController$$ExternalSyntheticLambda0(), new TranslateAlert3$$ExternalSyntheticLambda10(this));
    L29:
        this.adapter.update(true);
        return;
    L22:
        if (this.messageId == 0) goto L24;
        r14.flags |= 1;
        r14.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        r14.id.add(Integer.valueOf(this.messageId));
        goto L25
    L12:
        if (this.dialogId == 0) goto L19;
        if (this.messageId == 0) goto L19;
        TLRPC.TL_messages_summarizeText r03 = new TLRPC.TL_messages_summarizeText();
        r03.flags |= 1;
        r03.to_lang = this.to_lang;
        r03.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        r03.id = this.messageId;
        int r15 = this.tone;
        if (r15 == 1) goto L18;
        r03.flags |= 4;
        r03.tone = this.tones[r15];
    L18:
        this.requestId = ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r03, new AiTonesController$$ExternalSyntheticLambda0(), new TranslateAlert3$$ExternalSyntheticLambda9(this));
        goto L29
    L5:
        r12 = r1.toString();
        goto L6
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(r9, getContext(), this.currentAccount, 0, true, new TranslateAlert3$$ExternalSyntheticLambda2(this), this.resourcesProvider);
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        if (this.dialogId != 0) goto L5;
        return "Translate";
    L5:
        if (this.messageId != 0) goto L7;
        return "Translate";
    L7:
        if (this.summarized == false) goto L13;
        return "Summarize & Translate";
    L13:
        return "Translate";
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public void onActionBarAlpha(float r5) {
        float r52 = 1.0f - r5;
        this.closeView.setAlpha(r52);
        this.closeView.setScaleX(AndroidUtilities.lerp(0.6f, 1.0f, r52));
        this.closeView.setScaleY(AndroidUtilities.lerp(0.6f, 1.0f, r52));
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView, org.telegram.ui.ActionBar.BottomSheet
    public void onContainerViewTranslation() {
        super.onContainerViewTranslation();
        ValueAnimator r0 = this.keyboardContentAnimator;
        FrameLayout r1 = this.buttonContainer;
        if (r0 == null) goto L6;
        r1.setTranslationY(-((Float) r0.getAnimatedValue()).floatValue());
        return;
    L6:
        r1.setTranslationY(0.0f);
    }

    public TranslateAlert3 setOnUse(Utilities.Callback<CharSequence> r1) {
        this.onUseListener = r1;
        return this;
    }

    public TranslateAlert3 setText(CharSequence r3) {
        this.text = r3;
        if (LanguageDetector.hasSupport() == false) goto L5;
        LanguageDetector.detectLanguage(r3.toString(), new TranslateAlert3$$ExternalSyntheticLambda0(this), new TranslateAlert3$$ExternalSyntheticLambda1());
    L5:
        return this;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        ActionBar r0 = this.actionBar;
        if (r0 == null) goto L5;
        r0.setTitle(getTitle());
    L5:
        this.adapter.update(false);
        requestTranslate();
        if (this.onUseListener == null) goto L9;
        this.button.setText("Use This Translation");
        this.button.setOnClickListener(new TranslateAlert3$$ExternalSyntheticLambda5(this));
        return;
    }
}
