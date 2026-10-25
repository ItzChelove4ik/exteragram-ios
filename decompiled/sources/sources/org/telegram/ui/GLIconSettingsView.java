package org.telegram.ui;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ColorPicker;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.GLIcon.GLIconRenderer;
import org.telegram.ui.Components.Premium.GLIcon.Icon3D;
import org.telegram.ui.Components.SeekBarView;

/* loaded from: classes6.dex */
public class GLIconSettingsView extends LinearLayout {
    public static float smallStarsSize = 1.0f;

    /* renamed from: org.telegram.ui.GLIconSettingsView$1, reason: invalid class name */
    public class AnonymousClass1 implements SeekBarView.SeekBarViewDelegate {
        final /* synthetic */ GLIconSettingsView this$0;
        final /* synthetic */ GLIconRenderer val$mRenderer;

        public AnonymousClass1(GLIconSettingsView r1, GLIconRenderer r2) {
            this.this$0 = r1;
            this.val$mRenderer = r2;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarDrag(boolean r1, float r2) {
            Icon3D r0 = this.val$mRenderer.model;
            if (r0 == null) goto L6;
            r0.spec1 = r2 * 2.0f;
            return;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarPressed(boolean r1) {
        }
    }

    /* renamed from: org.telegram.ui.GLIconSettingsView$2, reason: invalid class name */
    public class AnonymousClass2 implements SeekBarView.SeekBarViewDelegate {
        final /* synthetic */ GLIconSettingsView this$0;
        final /* synthetic */ GLIconRenderer val$mRenderer;

        public AnonymousClass2(GLIconSettingsView r1, GLIconRenderer r2) {
            this.this$0 = r1;
            this.val$mRenderer = r2;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarDrag(boolean r1, float r2) {
            Icon3D r0 = this.val$mRenderer.model;
            if (r0 == null) goto L6;
            r0.spec2 = r2 * 2.0f;
            return;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarPressed(boolean r1) {
        }
    }

    /* renamed from: org.telegram.ui.GLIconSettingsView$3, reason: invalid class name */
    public class AnonymousClass3 implements View.OnClickListener {
        final /* synthetic */ GLIconSettingsView this$0;
        final /* synthetic */ Context val$context;
        final /* synthetic */ GLIconRenderer val$mRenderer;

        /* renamed from: org.telegram.ui.GLIconSettingsView$3$1, reason: invalid class name */
        public class AnonymousClass1 implements ColorPicker.ColorPickerDelegate {
            final /* synthetic */ AnonymousClass3 this$1;

            public AnonymousClass1(AnonymousClass3 r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.Components.ColorPicker.ColorPickerDelegate
            public void setColor(int r1, int r2, boolean r3) {
                Icon3D r0 = this.this$1.val$mRenderer.model;
                if (r0 == null) goto L6;
                r0.specColor = r1;
                return;
            }
        }

        /* renamed from: org.telegram.ui.GLIconSettingsView$3$2, reason: invalid class name */
        public class AnonymousClass2 extends ColorPicker {
            final /* synthetic */ AnonymousClass3 this$1;

            public AnonymousClass2(AnonymousClass3 r1, Context r2, boolean r3, ColorPicker.ColorPickerDelegate r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(300.0f), TLObject.FLAG_30));
            }
        }

        public AnonymousClass3(GLIconSettingsView r1, Context r2, GLIconRenderer r3) {
            this.this$0 = r1;
            this.val$context = r2;
            this.val$mRenderer = r3;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r10) {
            AnonymousClass2 r0 = new AnonymousClass2(this, this.val$context, false, new AnonymousClass1(this));
            Icon3D r102 = this.val$mRenderer.model;
            if (r102 == null) goto L5;
            int r103 = r102.specColor;
        L6:
            r0.setColor(r103, 0);
            r0.setType(-1, true, 1, 1, false, 0, false);
            BottomSheet r104 = new BottomSheet(this.val$context, false);
            r104.setCustomView(r0);
            r104.setDimBehind(false);
            r104.show();
            return;
        L5:
            r103 = 0;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.GLIconSettingsView$4, reason: invalid class name */
    public class AnonymousClass4 implements SeekBarView.SeekBarViewDelegate {
        final /* synthetic */ GLIconSettingsView this$0;
        final /* synthetic */ GLIconRenderer val$mRenderer;

        public AnonymousClass4(GLIconSettingsView r1, GLIconRenderer r2) {
            this.this$0 = r1;
            this.val$mRenderer = r2;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarDrag(boolean r1, float r2) {
            Icon3D r0 = this.val$mRenderer.model;
            if (r0 == null) goto L6;
            r0.diffuse = r2;
            return;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarPressed(boolean r1) {
        }
    }

    /* renamed from: org.telegram.ui.GLIconSettingsView$5, reason: invalid class name */
    public class AnonymousClass5 implements SeekBarView.SeekBarViewDelegate {
        final /* synthetic */ GLIconSettingsView this$0;
        final /* synthetic */ GLIconRenderer val$mRenderer;

        public AnonymousClass5(GLIconSettingsView r1, GLIconRenderer r2) {
            this.this$0 = r1;
            this.val$mRenderer = r2;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarDrag(boolean r1, float r2) {
            Icon3D r0 = this.val$mRenderer.model;
            if (r0 == null) goto L6;
            r0.normalSpec = r2 * 2.0f;
            return;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarPressed(boolean r1) {
        }
    }

    /* renamed from: org.telegram.ui.GLIconSettingsView$6, reason: invalid class name */
    public class AnonymousClass6 implements View.OnClickListener {
        final /* synthetic */ GLIconSettingsView this$0;
        final /* synthetic */ Context val$context;
        final /* synthetic */ GLIconRenderer val$mRenderer;

        /* renamed from: org.telegram.ui.GLIconSettingsView$6$1, reason: invalid class name */
        public class AnonymousClass1 implements ColorPicker.ColorPickerDelegate {
            final /* synthetic */ AnonymousClass6 this$1;

            public AnonymousClass1(AnonymousClass6 r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.Components.ColorPicker.ColorPickerDelegate
            public void setColor(int r1, int r2, boolean r3) {
                if (r2 != 0) goto L7;
                Icon3D r0 = this.this$1.val$mRenderer.model;
                if (r0 == null) goto L8;
                r0.normalSpecColor = r1;
                return;
            L8:
                return;
            }
        }

        /* renamed from: org.telegram.ui.GLIconSettingsView$6$2, reason: invalid class name */
        public class AnonymousClass2 extends ColorPicker {
            final /* synthetic */ AnonymousClass6 this$1;

            public AnonymousClass2(AnonymousClass6 r1, Context r2, boolean r3, ColorPicker.ColorPickerDelegate r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(300.0f), TLObject.FLAG_30));
            }
        }

        public AnonymousClass6(GLIconSettingsView r1, Context r2, GLIconRenderer r3) {
            this.this$0 = r1;
            this.val$context = r2;
            this.val$mRenderer = r3;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r10) {
            AnonymousClass2 r0 = new AnonymousClass2(this, this.val$context, false, new AnonymousClass1(this));
            Icon3D r102 = this.val$mRenderer.model;
            if (r102 != null) goto L5;
            int r103 = 0;
        L6:
            r0.setColor(r103, 0);
            r0.setType(-1, true, 1, 1, false, 0, false);
            BottomSheet r104 = new BottomSheet(this.val$context, false);
            r104.setCustomView(r0);
            r104.setDimBehind(false);
            r104.show();
            return;
        L5:
            r103 = r102.normalSpecColor;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.GLIconSettingsView$7, reason: invalid class name */
    public class AnonymousClass7 implements SeekBarView.SeekBarViewDelegate {
        final /* synthetic */ GLIconSettingsView this$0;

        public AnonymousClass7(GLIconSettingsView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarDrag(boolean r1, float r2) {
            GLIconSettingsView.smallStarsSize = r2 * 2.0f;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarPressed(boolean r1) {
        }
    }

    static {
    }

    public GLIconSettingsView(Context r26, GLIconRenderer r27) {
        super(r26);
        setOrientation(1);
        TextView r4 = new TextView(r26);
        r4.setText("Spectral top ");
        int r5 = Theme.key_dialogTextBlue2;
        r4.setTextColor(Theme.getColor(r5));
        r4.setTextSize(1, 16.0f);
        r4.setLines(1);
        r4.setMaxLines(1);
        r4.setSingleLine(true);
        int r8 = 5;
        if (LocaleController.isRTL == false) goto L5;
        int r7 = 3;
    L6:
        r4.setGravity(r7 | 48);
        if (LocaleController.isRTL == false) goto L9;
        int r72 = 3;
    L10:
        addView(r4, LayoutHelper.createFrame(-2, -1.0f, r72 | 48, 21.0f, 13.0f, 21.0f, 0.0f));
        SeekBarView r42 = new SeekBarView(r26);
        r42.setDelegate(new AnonymousClass1(this, r27));
        Icon3D r73 = r27.model;
        float r11 = 0.0f;
        if (r73 != null) goto L13;
        float r74 = 0.0f;
    L14:
        r42.setProgress(r74);
        r42.setReportChanges(true);
        addView(r42, LayoutHelper.createFrame(-1, 38.0f, 0, 5.0f, 4.0f, 5.0f, 0.0f));
        TextView r43 = new TextView(r26);
        r43.setText("Spectral bottom ");
        r43.setTextColor(Theme.getColor(r5));
        r43.setTextSize(1, 16.0f);
        r43.setLines(1);
        r43.setMaxLines(1);
        r43.setSingleLine(true);
        if (LocaleController.isRTL == false) goto L17;
        int r75 = 3;
    L18:
        r43.setGravity(r75 | 48);
        if (LocaleController.isRTL == false) goto L21;
        int r76 = 3;
    L22:
        addView(r43, LayoutHelper.createFrame(-2, -1.0f, r76 | 48, 21.0f, 13.0f, 21.0f, 0.0f));
        SeekBarView r44 = new SeekBarView(r26);
        r44.setDelegate(new AnonymousClass2(this, r27));
        Icon3D r77 = r27.model;
        if (r77 != null) goto L25;
        float r78 = 0.0f;
    L26:
        r44.setProgress(r78);
        r44.setReportChanges(true);
        addView(r44, LayoutHelper.createFrame(-1, 38.0f, 0, 5.0f, 4.0f, 5.0f, 0.0f));
        TextView r45 = new TextView(r26);
        r45.setText("Setup spec color");
        r45.setTextSize(1, 16.0f);
        r45.setLines(1);
        r45.setGravity(17);
        r45.setMaxLines(1);
        r45.setSingleLine(true);
        int r12 = Theme.key_featuredStickers_buttonText;
        r45.setTextColor(Theme.getColor(r12));
        int r13 = Theme.key_featuredStickers_addButton;
        r45.setBackground(Theme.AdaptiveRipple.filledRect(Theme.getColor(r13), new float[]{4.0f}));
        r45.setOnClickListener(new AnonymousClass3(this, r26, r27));
        addView(r45, LayoutHelper.createFrame(-1, 48.0f, 16, 16.0f, 0.0f, 16.0f, 0.0f));
        TextView r46 = new TextView(r26);
        r46.setText("Diffuse ");
        r46.setTextColor(Theme.getColor(r5));
        r46.setTextSize(1, 16.0f);
        r46.setLines(1);
        r46.setMaxLines(1);
        r46.setSingleLine(true);
        if (LocaleController.isRTL == false) goto L29;
        int r14 = 3;
    L30:
        r46.setGravity(r14 | 48);
        if (LocaleController.isRTL == false) goto L33;
        int r142 = 3;
    L34:
        addView(r46, LayoutHelper.createFrame(-2, -1.0f, r142 | 48, 21.0f, 13.0f, 21.0f, 0.0f));
        SeekBarView r47 = new SeekBarView(r26);
        r47.setDelegate(new AnonymousClass4(this, r27));
        Icon3D r143 = r27.model;
        if (r143 != null) goto L37;
        float r144 = 0.0f;
    L38:
        r47.setProgress(r144);
        r47.setReportChanges(true);
        addView(r47, LayoutHelper.createFrame(-1, 38.0f, 0, 5.0f, 4.0f, 5.0f, 0.0f));
        TextView r48 = new TextView(r26);
        r48.setText("Normal map spectral");
        r48.setTextColor(Theme.getColor(r5));
        r48.setTextSize(1, 16.0f);
        r48.setLines(1);
        r48.setMaxLines(1);
        r48.setSingleLine(true);
        if (LocaleController.isRTL == false) goto L41;
        int r145 = 3;
    L42:
        r48.setGravity(r145 | 48);
        if (LocaleController.isRTL == false) goto L45;
        int r146 = 3;
    L46:
        addView(r48, LayoutHelper.createFrame(-2, -1.0f, r146 | 48, 21.0f, 13.0f, 21.0f, 0.0f));
        SeekBarView r49 = new SeekBarView(r26);
        r49.setDelegate(new AnonymousClass5(this, r27));
        Icon3D r147 = r27.model;
        if (r147 == null) goto L50;
        r11 = r147.normalSpec / 2.0f;
    L50:
        r49.setProgress(r11);
        r49.setReportChanges(true);
        addView(r49, LayoutHelper.createFrame(-1, 38.0f, 0, 5.0f, 4.0f, 5.0f, 0.0f));
        TextView r410 = new TextView(r26);
        r410.setText("Setup normal spec color");
        r410.setTextSize(1, 16.0f);
        r410.setLines(1);
        r410.setGravity(17);
        r410.setMaxLines(1);
        r410.setSingleLine(true);
        r410.setTextColor(Theme.getColor(r12));
        r410.setBackground(Theme.AdaptiveRipple.filledRect(Theme.getColor(r13), new float[]{4.0f}));
        r410.setOnClickListener(new AnonymousClass6(this, r26, r27));
        addView(r410, LayoutHelper.createFrame(-1, 48.0f, 16, 16.0f, 0.0f, 16.0f, 0.0f));
        TextView r2 = new TextView(r26);
        r2.setText("Small starts size");
        r2.setTextColor(Theme.getColor(r5));
        r2.setTextSize(1, 16.0f);
        r2.setLines(1);
        r2.setMaxLines(1);
        r2.setSingleLine(true);
        if (LocaleController.isRTL == false) goto L53;
        int r411 = 3;
    L54:
        r2.setGravity(r411 | 48);
        if (LocaleController.isRTL == false) goto L57;
        r8 = 3;
    L57:
        addView(r2, LayoutHelper.createFrame(-2, -1.0f, r8 | 48, 21.0f, 13.0f, 21.0f, 0.0f));
        SeekBarView r22 = new SeekBarView(r26);
        r22.setDelegate(new AnonymousClass7(this));
        r22.setProgress(smallStarsSize / 2.0f);
        r22.setReportChanges(true);
        addView(r22, LayoutHelper.createFrame(-1, 38.0f, 0, 5.0f, 4.0f, 5.0f, 0.0f));
        return;
    L53:
        r411 = 5;
        goto L54
    L45:
        r146 = 5;
        goto L46
    L41:
        r145 = 5;
        goto L42
    L37:
        r144 = r143.diffuse;
        goto L38
    L33:
        r142 = 5;
        goto L34
    L29:
        r14 = 5;
        goto L30
    L25:
        r78 = r77.spec2 / 2.0f;
        goto L26
    L21:
        r76 = 5;
        goto L22
    L17:
        r75 = 5;
        goto L18
    L13:
        r74 = r73.spec1 / 2.0f;
        goto L14
    L9:
        r72 = 5;
        goto L10
    L5:
        r7 = 5;
        goto L6
    }
}
