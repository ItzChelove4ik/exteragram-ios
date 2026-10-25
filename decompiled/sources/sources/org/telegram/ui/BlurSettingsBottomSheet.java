package org.telegram.ui;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SeekBarView;
import org.telegram.ui.Components.SizeNotifierFrameLayout;

/* loaded from: classes.dex */
public class BlurSettingsBottomSheet extends BottomSheet {
    public static float blurAlpha = 0.0f;
    public static float blurRadius = 1.0f;
    public static float saturation = 1.0f;
    SizeNotifierFrameLayout contentView;
    BaseFragment fragment;

    /* renamed from: org.telegram.ui.BlurSettingsBottomSheet$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 implements SeekBarView.SeekBarViewDelegate {
        final /* synthetic */ BlurSettingsBottomSheet this$0;
        final /* synthetic */ TextView val$saturationTextView;

        public AnonymousClass1(BlurSettingsBottomSheet r1, TextView r2) {
            this.this$0 = r1;
            this.val$saturationTextView = r2;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarDrag(boolean r3, float r4) {
            BlurSettingsBottomSheet.saturation = r4;
            this.val$saturationTextView.setText("Saturation " + (r4 * 5.0f));
            this.this$0.contentView.invalidateBlurredViews();
            this.this$0.contentView.invalidateBlur();
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarPressed(boolean r1) {
        }
    }

    /* renamed from: org.telegram.ui.BlurSettingsBottomSheet$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 implements SeekBarView.SeekBarViewDelegate {
        final /* synthetic */ BlurSettingsBottomSheet this$0;
        final /* synthetic */ TextView val$alphaTextView;

        public AnonymousClass2(BlurSettingsBottomSheet r1, TextView r2) {
            this.this$0 = r1;
            this.val$alphaTextView = r2;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarDrag(boolean r3, float r4) {
            this.val$alphaTextView.setText("Alpha " + BlurSettingsBottomSheet.blurAlpha);
            BlurSettingsBottomSheet.blurAlpha = r4;
            this.this$0.contentView.invalidateBlur();
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarPressed(boolean r1) {
        }
    }

    /* renamed from: org.telegram.ui.BlurSettingsBottomSheet$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 implements SeekBarView.SeekBarViewDelegate {
        final /* synthetic */ BlurSettingsBottomSheet this$0;

        public AnonymousClass3(BlurSettingsBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarDrag(boolean r1, float r2) {
            BlurSettingsBottomSheet.blurRadius = r2;
            this.this$0.contentView.invalidateBlur();
            this.this$0.contentView.invalidateBlurredViews();
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarPressed(boolean r1) {
            this.this$0.contentView.invalidateBlurredViews();
        }
    }

    /* renamed from: org.telegram.ui.BlurSettingsBottomSheet$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 implements View.OnLayoutChangeListener {
        final /* synthetic */ BlurSettingsBottomSheet this$0;
        final /* synthetic */ SeekBarView val$seekBar;
        final /* synthetic */ SeekBarView val$seekBar2;
        final /* synthetic */ SeekBarView val$seekBar3;

        public AnonymousClass4(BlurSettingsBottomSheet r1, SeekBarView r2, SeekBarView r3, SeekBarView r4) {
            this.this$0 = r1;
            this.val$seekBar = r2;
            this.val$seekBar2 = r3;
            this.val$seekBar3 = r4;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
            this.val$seekBar.setProgress(BlurSettingsBottomSheet.saturation);
            this.val$seekBar2.setProgress(BlurSettingsBottomSheet.blurRadius);
            this.val$seekBar3.setProgress(BlurSettingsBottomSheet.blurAlpha);
        }
    }

    static {
        blurAlpha = 1.0f - (Color.alpha(Theme.getColor(Theme.key_chat_BlurAlpha)) / 255.0f);
    }

    private BlurSettingsBottomSheet(BaseFragment r20) {
        super(r20.getParentActivity(), false);
        this.fragment = r20;
        if ((r20.getFragmentView() instanceof SizeNotifierFrameLayout) == false) goto L5;
        this.contentView = (SizeNotifierFrameLayout) r20.getFragmentView();
    L5:
        Activity r1 = r20.getParentActivity();
        LinearLayout r2 = new LinearLayout(r1);
        r2.setOrientation(1);
        TextView r4 = new TextView(r1);
        r4.setText("Saturation " + (saturation * 5.0f));
        int r5 = Theme.key_dialogTextBlue2;
        r4.setTextColor(Theme.getColor(r5));
        r4.setTextSize(1, 16.0f);
        r4.setLines(1);
        r4.setMaxLines(1);
        r4.setSingleLine(true);
        int r8 = 5;
        if (LocaleController.isRTL == false) goto L8;
        int r7 = 3;
    L9:
        r4.setGravity(r7 | 48);
        if (LocaleController.isRTL == false) goto L12;
        int r72 = 3;
    L13:
        r2.addView(r4, LayoutHelper.createFrame(-2, -1.0f, r72 | 48, 21.0f, 13.0f, 21.0f, 0.0f));
        SeekBarView r73 = new SeekBarView(r1);
        r73.setDelegate(new AnonymousClass1(this, r4));
        r73.setReportChanges(true);
        r2.addView(r73, LayoutHelper.createFrame(-1, 38.0f, 0, 5.0f, 4.0f, 5.0f, 0.0f));
        TextView r42 = new TextView(r1);
        r42.setText("Alpha " + blurAlpha);
        r42.setTextColor(Theme.getColor(r5));
        r42.setTextSize(1, 16.0f);
        r42.setLines(1);
        r42.setMaxLines(1);
        r42.setSingleLine(true);
        if (LocaleController.isRTL == false) goto L16;
        int r10 = 3;
    L17:
        r42.setGravity(r10 | 48);
        if (LocaleController.isRTL == false) goto L20;
        int r102 = 3;
    L21:
        r2.addView(r42, LayoutHelper.createFrame(-2, -1.0f, r102 | 48, 21.0f, 13.0f, 21.0f, 0.0f));
        SeekBarView r103 = new SeekBarView(r1);
        r103.setDelegate(new AnonymousClass2(this, r42));
        r103.setReportChanges(true);
        r2.addView(r103, LayoutHelper.createFrame(-1, 38.0f, 0, 5.0f, 4.0f, 5.0f, 0.0f));
        TextView r43 = new TextView(r1);
        r43.setText("Blur Radius");
        r43.setTextColor(Theme.getColor(r5));
        r43.setTextSize(1, 16.0f);
        r43.setLines(1);
        r43.setMaxLines(1);
        r43.setSingleLine(true);
        if (LocaleController.isRTL == false) goto L24;
        int r52 = 3;
    L25:
        r43.setGravity(r52 | 48);
        if (LocaleController.isRTL == false) goto L28;
        r8 = 3;
    L28:
        r2.addView(r43, LayoutHelper.createFrame(-2, -1.0f, r8 | 48, 21.0f, 13.0f, 21.0f, 0.0f));
        SeekBarView r44 = new SeekBarView(r1);
        r44.setDelegate(new AnonymousClass3(this));
        r44.setReportChanges(true);
        r2.addView(r44, LayoutHelper.createFrame(-1, 38.0f, 0, 5.0f, 4.0f, 5.0f, 0.0f));
        r2.addOnLayoutChangeListener(new AnonymousClass4(this, r73, r44, r103));
        ScrollView r3 = new ScrollView(r1);
        r3.addView(r2);
        setCustomView(r3);
        return;
    L24:
        r52 = 5;
        goto L25
    L20:
        r102 = 5;
        goto L21
    L16:
        r10 = 5;
        goto L17
    L12:
        r72 = 5;
        goto L13
    L8:
        r7 = 5;
        goto L9
    }

    public static void onThemeApplyed() {
        blurAlpha = 1.0f - (Color.alpha(Theme.getColor(Theme.key_chat_BlurAlpha, null, true)) / 255.0f);
    }

    public static void show(BaseFragment r1) {
        new BlurSettingsBottomSheet(r1).show();
    }
}
