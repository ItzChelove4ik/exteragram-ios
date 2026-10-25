package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.utils.system.VibratorUtils;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BatteryDrawable;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.IntSeekBarAccessibilityDelegate;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SeekBarAccessibilityDelegate;
import org.telegram.ui.Components.SeekBarView;
import org.telegram.ui.Components.Switch;
import org.telegram.ui.Components.ThanosEffect;

/* loaded from: classes6.dex */
public class LiteModeSettingsActivity extends BaseFragment {
    private int FLAGS_CHAT;
    Adapter adapter;
    FrameLayout contentView;
    private boolean[] expanded;
    private ArrayList<Item> items;
    LinearLayoutManager layoutManager;
    RecyclerListView listView;
    private ArrayList<Item> oldItems;
    private Utilities.Callback<Boolean> onPowerAppliedChange;
    Bulletin restrictBulletin;

    /* renamed from: org.telegram.ui.LiteModeSettingsActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ LiteModeSettingsActivity this$0;

        public AnonymousClass1(LiteModeSettingsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    public class Adapter extends AdapterWithDiffUtils {
        final /* synthetic */ LiteModeSettingsActivity this$0;

        /* renamed from: org.telegram.ui.LiteModeSettingsActivity$Adapter$1, reason: invalid class name */
        public class AnonymousClass1 extends TextInfoPrivacyCell {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass1(Adapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Cells.TextInfoPrivacyCell, android.view.View
            public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
                super.onInitializeAccessibilityNodeInfo(r1);
                r1.setEnabled(true);
            }

            @Override // android.view.View
            public void onPopulateAccessibilityEvent(AccessibilityEvent r2) {
                super.onPopulateAccessibilityEvent(r2);
                r2.setContentDescription(getTextView().getText());
                setContentDescription(getTextView().getText());
            }
        }

        private Adapter(LiteModeSettingsActivity r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Adapter(LiteModeSettingsActivity r1, LiteModeSettingsActivityIA r2) {
            this(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return LiteModeSettingsActivity.m15694$$Nest$fgetitems(this.this$0).size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 >= 0) goto L4;
            return 2;
        L4:
            if (r2 < LiteModeSettingsActivity.m15694$$Nest$fgetitems(this.this$0).size()) goto L7;
            return 2;
        L7:
            return ((Item) LiteModeSettingsActivity.m15694$$Nest$fgetitems(this.this$0).get(r2)).viewType;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r2) {
            if (r2.getItemViewType() != 4) goto L5;
            return true;
        L5:
            if (r2.getItemViewType() != 3) goto L7;
            return true;
        L7:
            if (r2.getItemViewType() == 5) goto L14;
            return false;
        L14:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r7, int r8) {
            if (r8 >= 0) goto L4;
            return;
        L4:
            if (r8 >= LiteModeSettingsActivity.m15694$$Nest$fgetitems(this.this$0).size()) goto L47;
            Item r0 = (Item) LiteModeSettingsActivity.m15694$$Nest$fgetitems(this.this$0).get(r8);
            int r1 = r7.getItemViewType();
            if (r1 != 0) goto L10;
            ((HeaderCell) r7.itemView).setText(r0.text);
            return;
        L10:
            boolean r2 = true;
            if (r1 != 1) goto L15;
            ((PowerSaverSlider) r7.itemView).update();
            return;
        L15:
            if (r1 != 2) goto L23;
            TextInfoPrivacyCell r6 = (TextInfoPrivacyCell) r7.itemView;
            if (TextUtils.isEmpty(r0.text) == false) goto L19;
            r6.setFixedSize(12);
        L20:
            r6.setText(r0.text);
            r6.setContentDescription(r0.text);
            r6.setBackground(null);
            return;
        L19:
            r6.setFixedSize(0);
            goto L20
        L23:
            if (r1 != 3) goto L25;
        L36:
            int r82 = r8 + 1;
            if (r82 < LiteModeSettingsActivity.m15694$$Nest$fgetitems(this.this$0).size()) goto L39;
        L41:
            r2 = false;
        L42:
            ((SwitchCell) r7.itemView).set(r0, r2);
            return;
        L39:
            if (((Item) LiteModeSettingsActivity.m15694$$Nest$fgetitems(this.this$0).get(r82)).viewType == 2) goto L41;
        L25:
            if (r1 == 4) goto L36;
            if (r1 != 5) goto L45;
            TextCell r62 = (TextCell) r7.itemView;
            int r72 = r0.type;
            if (r72 != 1) goto L33;
            r62.setTextAndCheck(r0.text, MessagesController.getGlobalMainSettings().getBoolean("view_animations", true), false);
            return;
        L33:
            if (r72 != 2) goto L46;
            r62.setTextAndCheck(r0.text, LiteMode.isPowerSaverFollowSystem(), false);
            return;
        L46:
            return;
        L45:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
            Context r1 = r7.getContext();
            if (r8 != 0) goto L6;
            FrameLayout r6 = new HeaderCell(r1);
        L23:
            return new RecyclerListView.Holder(r6);
        L6:
            if (r8 != 1) goto L10;
            FrameLayout r72 = new PowerSaverSlider(this.this$0, r1);
        L8:
            r6 = r72;
            goto L23
        L10:
            if (r8 != 2) goto L13;
            r72 = new AnonymousClass1(this, r1);
            goto L8
        L13:
            if (r8 != 3) goto L15;
        L21:
            r72 = new SwitchCell(this.this$0, r1);
            goto L8
        L15:
            if (r8 == 4) goto L21;
            if (r8 != 5) goto L20;
            r6 = new TextCell(r1, 23, false, true, null);
            goto L23
        L20:
            r6 = null;
            goto L23
        }
    }

    public static class Item extends AdapterWithDiffUtils.Item {
        public int flags;
        public int iconResId;
        public CharSequence text;
        public int type;

        private Item(int r2, CharSequence r3, int r4, int r5, int r6) {
            super(r2, false);
            this.text = r3;
            this.iconResId = r4;
            this.flags = r5;
            this.type = r6;
        }

        public static Item asCheckbox(CharSequence r6, int r7) {
            return new Item(4, r6, 0, r7, 0);
        }

        public static Item asHeader(CharSequence r6) {
            return new Item(0, r6, 0, 0, 0);
        }

        public static Item asInfo(CharSequence r6) {
            return new Item(2, r6, 0, 0, 0);
        }

        public static Item asSlider() {
            return new Item(1, null, 0, 0, 0);
        }

        public static Item asSwitch(int r6, CharSequence r7, int r8) {
            return new Item(3, r7, r6, r8, 0);
        }

        public static Item asSwitch(CharSequence r6, int r7) {
            return new Item(5, r6, 0, 0, r7);
        }

        public boolean equals(Object r9) {
            if (this != r9) goto L6;
            return true;
        L6:
            if ((r9 instanceof Item) == true) goto L8;
            return false;
        L8:
            Item r92 = (Item) r9;
            int r1 = r92.viewType;
            int r3 = this.viewType;
            if (r1 == r3) goto L12;
            return false;
        L12:
            if (r3 != 3) goto L17;
            if (r92.iconResId == this.iconResId) goto L17;
            return false;
        L17:
            if (r3 != 5) goto L22;
            if (r92.type == this.type) goto L22;
            return false;
        L22:
            if (r3 == 3) goto L25;
            if (r3 == 4) goto L25;
        L27:
            if (r3 == 0) goto L36;
            if (r3 == 2) goto L36;
            if (r3 == 3) goto L36;
            if (r3 == 4) goto L36;
            if (r3 == 5) goto L36;
            return true;
        L36:
            return TextUtils.equals(r92.text, this.text);
        L25:
            if (r92.flags == this.flags) goto L27;
            return false;
        }

        public int getFlagsCount() {
            return Integer.bitCount(this.flags);
        }
    }

    public class PowerSaverSlider extends FrameLayout {
        BatteryDrawable batteryIcon;
        SpannableStringBuilder batteryText;
        LinearLayout headerLayout;
        AnimatedTextView headerOnView;
        private boolean headerOnVisible;
        TextView headerTextView;
        TextView leftTextView;
        AnimatedTextView middleTextView;
        private ValueAnimator offActiveAnimator;
        private float offActiveT;
        private ValueAnimator onActiveAnimator;
        private float onActiveT;
        TextView rightTextView;
        private SeekBarAccessibilityDelegate seekBarAccessibilityDelegate;
        SeekBarView seekBarView;
        final /* synthetic */ LiteModeSettingsActivity this$0;
        FrameLayout valuesView;

        /* renamed from: org.telegram.ui.LiteModeSettingsActivity$PowerSaverSlider$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatedTextView {
            Drawable backgroundDrawable;
            final /* synthetic */ PowerSaverSlider this$1;
            final /* synthetic */ LiteModeSettingsActivity val$this$0;

            public AnonymousClass1(PowerSaverSlider r1, Context r2, boolean r3, boolean r4, boolean r5, LiteModeSettingsActivity r6) {
                this.this$1 = r1;
                this.val$this$0 = r6;
                super(r2, r3, r4, r5);
                this.backgroundDrawable = Theme.createRoundRectDrawable(AndroidUtilities.dp(4.0f), Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader), 0.15f));
            }

            @Override // org.telegram.ui.Components.AnimatedTextView, android.view.View
            public void onDraw(Canvas r5) {
                this.backgroundDrawable.setBounds(0, 0, (int) ((getPaddingLeft() + getDrawable().getCurrentWidth()) + getPaddingRight()), getMeasuredHeight());
                this.backgroundDrawable.draw(r5);
                super.onDraw(r5);
            }
        }

        /* renamed from: org.telegram.ui.LiteModeSettingsActivity$PowerSaverSlider$2, reason: invalid class name */
        public class AnonymousClass2 implements SeekBarView.SeekBarViewDelegate {
            final /* synthetic */ PowerSaverSlider this$1;
            final /* synthetic */ LiteModeSettingsActivity val$this$0;

            public AnonymousClass2(PowerSaverSlider r1, LiteModeSettingsActivity r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
            public CharSequence getContentDescription() {
                return " ";
            }

            @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
            public void onSeekBarDrag(boolean r1, float r2) {
                int r12 = Math.round(r2 * 100.0f);
                if (r12 == LiteMode.getPowerSaverLevel()) goto L13;
                LiteMode.setPowerSaverLevel(r12);
                LiteModeSettingsActivity.m15697$$Nest$mupdateValues(this.this$1.this$0);
                LiteModeSettingsActivity.m15696$$Nest$mupdateInfo(this.this$1.this$0);
                if (r12 > 0) goto L7;
            L11:
                this.this$1.performHapticFeedback(VibratorUtils.getType(3), 1);     // Catch: Exception -> L10
                return;
            L15:
                return;
            L7:
                if (r12 >= 100) goto L11;
                return;
            }

            @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
            public void onSeekBarPressed(boolean r1) {
            }
        }

        /* renamed from: org.telegram.ui.LiteModeSettingsActivity$PowerSaverSlider$3, reason: invalid class name */
        public class AnonymousClass3 extends AnimatedTextView {
            final /* synthetic */ PowerSaverSlider this$1;
            final /* synthetic */ LiteModeSettingsActivity val$this$0;

            public AnonymousClass3(PowerSaverSlider r1, Context r2, boolean r3, boolean r4, boolean r5, LiteModeSettingsActivity r6) {
                this.this$1 = r1;
                this.val$this$0 = r6;
                super(r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.AnimatedTextView, android.view.View
            public void onMeasure(int r3, int r4) {
                int r32 = View.MeasureSpec.getSize(r3);
                if (r32 > 0) goto L5;
                r32 = AndroidUtilities.displaySize.x - AndroidUtilities.dp(20.0f);
            L5:
                super.onMeasure(View.MeasureSpec.makeMeasureSpec((int) ((r32 - this.this$1.leftTextView.getPaint().measureText(this.this$1.leftTextView.getText().toString())) - this.this$1.rightTextView.getPaint().measureText(this.this$1.rightTextView.getText().toString())), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(24.0f), TLObject.FLAG_30));
            }
        }

        /* renamed from: org.telegram.ui.LiteModeSettingsActivity$PowerSaverSlider$4, reason: invalid class name */
        public class AnonymousClass4 extends IntSeekBarAccessibilityDelegate {
            final /* synthetic */ PowerSaverSlider this$1;
            final /* synthetic */ LiteModeSettingsActivity val$this$0;

            public AnonymousClass4(PowerSaverSlider r1, LiteModeSettingsActivity r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // org.telegram.ui.Components.IntSeekBarAccessibilityDelegate
            public int getDelta() {
                return 5;
            }

            @Override // org.telegram.ui.Components.IntSeekBarAccessibilityDelegate
            public int getMaxValue() {
                return 100;
            }

            @Override // org.telegram.ui.Components.IntSeekBarAccessibilityDelegate
            public int getProgress() {
                return LiteMode.getPowerSaverLevel();
            }

            @Override // org.telegram.ui.Components.SeekBarAccessibilityDelegate
            public void onInitializeAccessibilityNodeInfoInternal(View r1, AccessibilityNodeInfo r2) {
                super.onInitializeAccessibilityNodeInfoInternal(r1, r2);
                r2.setEnabled(true);
            }

            @Override // android.view.View.AccessibilityDelegate
            public void onPopulateAccessibilityEvent(View r3, AccessibilityEvent r4) {
                super.onPopulateAccessibilityEvent(r3, r4);
                StringBuilder r32 = new StringBuilder(LocaleController.getString(R.string.LiteBatteryTitle));
                r32.append(", ");
                if (LiteMode.isPowerSaverFollowSystem() == false) goto L5;
                r32.append(LocaleController.getString(R.string.LiteBatteryFollowSystemInfo));
            L12:
                r4.setContentDescription(r32);
                this.this$1.setContentDescription(r32);
                return;
            L5:
                int r0 = LiteMode.getPowerSaverLevel();
                if (r0 > 0) goto L9;
                r32.append(LocaleController.getString(R.string.LiteBatteryAlwaysDisabled));
                goto L12
            L9:
                if (r0 < 100) goto L11;
                r32.append(LocaleController.getString(R.string.LiteBatteryAlwaysEnabled));
                goto L12
            L11:
                r32.append(LocaleController.formatString(R.string.AccDescrLiteBatteryWhenBelow, new Object[]{Integer.valueOf(Math.round(r0))}));
                goto L12
            }

            @Override // org.telegram.ui.Components.IntSeekBarAccessibilityDelegate
            public void setProgress(int r3) {
                float r32 = r3 / 100.0f;
                this.this$1.seekBarView.delegate.onSeekBarDrag(true, r32);
                this.this$1.seekBarView.setProgress(r32);
            }
        }

        /* renamed from: org.telegram.ui.LiteModeSettingsActivity$PowerSaverSlider$5, reason: invalid class name */
        public class AnonymousClass5 extends AnimatorListenerAdapter {
            final /* synthetic */ PowerSaverSlider this$1;
            final /* synthetic */ float val$activeT;

            public AnonymousClass5(PowerSaverSlider r1, float r2) {
                this.this$1 = r1;
                this.val$activeT = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r4) {
                TextView r42 = this.this$1.rightTextView;
                int r0 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText);
                int r1 = Theme.getColor(Theme.key_windowBackgroundWhiteBlueText);
                PowerSaverSlider r2 = this.this$1;
                float r3 = this.val$activeT;
                PowerSaverSlider.m15700$$Nest$fputonActiveT(r2, r3);
                r42.setTextColor(ColorUtils.blendARGB(r0, r1, r3));
            }
        }

        /* renamed from: org.telegram.ui.LiteModeSettingsActivity$PowerSaverSlider$6, reason: invalid class name */
        public class AnonymousClass6 extends AnimatorListenerAdapter {
            final /* synthetic */ PowerSaverSlider this$1;
            final /* synthetic */ float val$activeT;

            public AnonymousClass6(PowerSaverSlider r1, float r2) {
                this.this$1 = r1;
                this.val$activeT = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r4) {
                TextView r42 = this.this$1.leftTextView;
                int r0 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText);
                int r1 = Theme.getColor(Theme.key_windowBackgroundWhiteBlueText);
                PowerSaverSlider r2 = this.this$1;
                float r3 = this.val$activeT;
                PowerSaverSlider.m15699$$Nest$fputoffActiveT(r2, r3);
                r42.setTextColor(ColorUtils.blendARGB(r0, r1, r3));
            }
        }

        public static /* synthetic */ void $r8$lambda$2PVjpA80_J5XbwzaQtcE02H2S38(PowerSaverSlider r0, ValueAnimator r1) {
            r0.lambda$updateOffActive$1(r1);
        }

        /* renamed from: $r8$lambda$h0kQ-owmGOPhMfgHtZM27Mot5t0, reason: not valid java name */
        public static /* synthetic */ void m15698$r8$lambda$h0kQowmGOPhMfgHtZM27Mot5t0(PowerSaverSlider r0, ValueAnimator r1) {
            r0.lambda$updateOnActive$0(r1);
        }

        /* renamed from: -$$Nest$fputoffActiveT, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15699$$Nest$fputoffActiveT(PowerSaverSlider r0, float r1) {
            r0.offActiveT = r1;
        }

        /* renamed from: -$$Nest$fputonActiveT, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15700$$Nest$fputonActiveT(PowerSaverSlider r0, float r1) {
            r0.onActiveT = r1;
        }

        public PowerSaverSlider(LiteModeSettingsActivity r22, Context r23) {
            this.this$0 = r22;
            super(r23);
            setWillNotDraw(false);
            LinearLayout r0 = new LinearLayout(r23);
            this.headerLayout = r0;
            if (LocaleController.isRTL == false) goto L5;
            int r3 = 5;
        L6:
            r0.setGravity(r3);
            this.headerLayout.setImportantForAccessibility(4);
            TextView r02 = new TextView(r23);
            this.headerTextView = r02;
            r02.setTextSize(1, 15.0f);
            this.headerTextView.setTypeface(AndroidUtilities.bold());
            TextView r03 = this.headerTextView;
            int r12 = Theme.key_windowBackgroundWhiteBlueHeader;
            r03.setTextColor(Theme.getColor(r12));
            TextView r04 = this.headerTextView;
            if (LocaleController.isRTL == false) goto L9;
            int r32 = 5;
        L10:
            r04.setGravity(r32);
            this.headerTextView.setText(LocaleController.getString("LiteBatteryTitle"));
            this.headerLayout.addView(this.headerTextView, LayoutHelper.createLinear(-2, -2, 16));
            AnonymousClass1 r05 = new AnonymousClass1(this, r23, true, false, false, r22);
            this.headerOnView = r05;
            r05.setTypeface(AndroidUtilities.bold());
            this.headerOnView.setPadding(AndroidUtilities.dp(5.33f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(5.33f), AndroidUtilities.dp(2.0f));
            this.headerOnView.setTextSize(AndroidUtilities.dp(12.0f));
            this.headerOnView.setTextColor(Theme.getColor(r12));
            this.headerLayout.addView(this.headerOnView, LayoutHelper.createLinear(-2, 17, 16, 6, 1, 0, 0));
            addView(this.headerLayout, LayoutHelper.createFrame(-1, -2.0f, 55, 21.0f, 17.0f, 21.0f, 0.0f));
            SeekBarView r06 = new SeekBarView(r23, true, null);
            this.seekBarView = r06;
            r06.setReportChanges(true);
            this.seekBarView.setDelegate(new AnonymousClass2(this, r22));
            this.seekBarView.setProgress(LiteMode.getPowerSaverLevel() / 100.0f);
            this.seekBarView.setImportantForAccessibility(2);
            addView(this.seekBarView, LayoutHelper.createFrame(-1, 44.0f, 48, 6.0f, 68.0f, 6.0f, 0.0f));
            FrameLayout r07 = new FrameLayout(r23);
            this.valuesView = r07;
            r07.setImportantForAccessibility(4);
            TextView r08 = new TextView(r23);
            this.leftTextView = r08;
            r08.setTextSize(1, 13.0f);
            TextView r09 = this.leftTextView;
            int r122 = Theme.key_windowBackgroundWhiteGrayText;
            r09.setTextColor(Theme.getColor(r122));
            this.leftTextView.setGravity(3);
            this.leftTextView.setText(LocaleController.getString(R.string.LiteBatteryDisabled));
            this.valuesView.addView(this.leftTextView, LayoutHelper.createFrame(-2, -2, 19));
            AnonymousClass3 r14 = new AnonymousClass3(this, r23, false, true, true, r22);
            this.middleTextView = r14;
            r14.setAnimationProperties(0.45f, 0, 240, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.middleTextView.setGravity(1);
            this.middleTextView.setTextSize(AndroidUtilities.dp(13.0f));
            this.middleTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText));
            this.valuesView.addView(this.middleTextView, LayoutHelper.createFrame(-2, -2, 17));
            this.batteryText = new SpannableStringBuilder("b");
            BatteryDrawable r010 = new BatteryDrawable();
            this.batteryIcon = r010;
            r010.colorFromPaint(this.middleTextView.getPaint());
            this.batteryIcon.setTranslationY(AndroidUtilities.dp(1.5f));
            this.batteryIcon.setBounds(AndroidUtilities.dp(3.0f), AndroidUtilities.dp(-20.0f), AndroidUtilities.dp(23.0f), 0);
            this.batteryText.setSpan(new ImageSpan(this.batteryIcon, 0), 0, this.batteryText.length(), 33);
            TextView r011 = new TextView(r23);
            this.rightTextView = r011;
            r011.setTextSize(1, 13.0f);
            this.rightTextView.setTextColor(Theme.getColor(r122));
            this.rightTextView.setGravity(5);
            this.rightTextView.setText(LocaleController.getString(R.string.LiteBatteryEnabled));
            this.valuesView.addView(this.rightTextView, LayoutHelper.createFrame(-2, -2, 21));
            addView(this.valuesView, LayoutHelper.createFrame(-1, -2.0f, 55, 21.0f, 52.0f, 21.0f, 0.0f));
            this.seekBarAccessibilityDelegate = new AnonymousClass4(this, r22);
            update();
            return;
        L9:
            r32 = 3;
            goto L10
        L5:
            r3 = 3;
            goto L6
        }

        private /* synthetic */ void lambda$updateOffActive$1(ValueAnimator r4) {
            TextView r0 = this.leftTextView;
            int r1 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText);
            int r2 = Theme.getColor(Theme.key_windowBackgroundWhiteBlueText);
            float r42 = ((Float) r4.getAnimatedValue()).floatValue();
            this.offActiveT = r42;
            r0.setTextColor(ColorUtils.blendARGB(r1, r2, r42));
        }

        private /* synthetic */ void lambda$updateOnActive$0(ValueAnimator r4) {
            TextView r0 = this.rightTextView;
            int r1 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText);
            int r2 = Theme.getColor(Theme.key_windowBackgroundWhiteBlueText);
            float r42 = ((Float) r4.getAnimatedValue()).floatValue();
            this.onActiveT = r42;
            r0.setTextColor(ColorUtils.blendARGB(r1, r2, r42));
        }

        private void updateHeaderOnVisibility(boolean r3) {
            if (r3 == this.headerOnVisible) goto L10;
            this.headerOnVisible = r3;
            this.headerOnView.clearAnimation();
            ViewPropertyAnimator r2 = this.headerOnView.animate();
            if (r3 == false) goto L7;
            float r32 = 1.0f;
        L8:
            r2.alpha(r32).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(220).start();
            return;
        L7:
            r32 = 0.0f;
            goto L8
        }

        private void updateOffActive(boolean r4) {
            if (r4 == false) goto L4;
            float r42 = 1.0f;
        L6:
            if (this.offActiveT == r42) goto L12;
            this.offActiveT = r42;
            ValueAnimator r0 = this.offActiveAnimator;
            if (r0 == null) goto L10;
            r0.cancel();
            this.offActiveAnimator = null;
        L10:
            ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{this.offActiveT, r42});
            this.offActiveAnimator = r02;
            r02.addUpdateListener(new LiteModeSettingsActivity$PowerSaverSlider$$ExternalSyntheticLambda1(this));
            this.offActiveAnimator.addListener(new AnonymousClass6(this, r42));
            this.offActiveAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.offActiveAnimator.setDuration(320);
            this.offActiveAnimator.start();
            return;
        L12:
            return;
        L4:
            r42 = 0.0f;
            goto L6
        }

        private void updateOnActive(boolean r4) {
            if (r4 == false) goto L4;
            float r42 = 1.0f;
        L6:
            if (this.onActiveT == r42) goto L12;
            this.onActiveT = r42;
            ValueAnimator r0 = this.onActiveAnimator;
            if (r0 == null) goto L10;
            r0.cancel();
            this.onActiveAnimator = null;
        L10:
            ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{this.onActiveT, r42});
            this.onActiveAnimator = r02;
            r02.addUpdateListener(new LiteModeSettingsActivity$PowerSaverSlider$$ExternalSyntheticLambda0(this));
            this.onActiveAnimator.addListener(new AnonymousClass5(this, r42));
            this.onActiveAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.onActiveAnimator.setDuration(320);
            this.onActiveAnimator.start();
            return;
        L12:
            return;
        L4:
            r42 = 0.0f;
            goto L6
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            super.onDraw(r8);
            r8.drawLine(0.0f, getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, Theme.dividerPaint);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
            super.onInitializeAccessibilityNodeInfo(r2);
            this.seekBarAccessibilityDelegate.onInitializeAccessibilityNodeInfo(this, r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(112.0f), TLObject.FLAG_30));
        }

        @Override // android.view.View
        public void onPopulateAccessibilityEvent(AccessibilityEvent r2) {
            super.onPopulateAccessibilityEvent(r2);
            this.seekBarAccessibilityDelegate.onPopulateAccessibilityEvent(this, r2);
        }

        @Override // android.view.View
        public boolean performAccessibilityAction(int r2, Bundle r3) {
            return this.seekBarAccessibilityDelegate.performAccessibilityAction(this, r2, r3);
        }

        public void update() {
            int r0 = LiteMode.getPowerSaverLevel();
            boolean r1 = LiteMode.isPowerSaverFollowSystem();
            boolean r2 = false;
            if (r1 == false) goto L5;
            this.middleTextView.setText(LocaleController.getString(R.string.LiteBatteryFollowSystemState), !LocaleController.isRTL);
        L10:
            AnimatedTextView r5 = this.headerOnView;
            if (LiteMode.isPowerSaverApplied() == false) goto L14;
            int r6 = R.string.LiteBatteryEnabled;
        L15:
            r5.setText(LocaleController.getString(r6).toUpperCase());
            if (r1 == true) goto L21;
            if (r0 <= 0) goto L20;
            if (r0 < 100) goto L21;
        L20:
            boolean r52 = false;
        L22:
            updateHeaderOnVisibility(r52);
            if (r1 == true) goto L26;
            if (r0 < 100) goto L26;
            boolean r3 = true;
        L27:
            updateOnActive(r3);
            if (r1 == true) goto L31;
            if (r0 > 0) goto L31;
            r2 = true;
        L31:
            updateOffActive(r2);
            this.seekBarView.setEnabled(!r1);
            SeekBarView r10 = this.seekBarView;
            if (r1 == false) goto L34;
            float r02 = 0.5f;
        L35:
            r10.setAlpha(r02);
            return;
        L34:
            r02 = 1.0f;
        L26:
            r3 = false;
        L21:
            r52 = true;
            goto L22
        L14:
            r6 = R.string.LiteBatteryDisabled;
            goto L15
        L5:
            if (r0 > 0) goto L7;
            this.middleTextView.setText(LocaleController.getString(R.string.LiteBatteryAlwaysDisabled), !LocaleController.isRTL);
            goto L10
        L7:
            if (r0 < 100) goto L9;
            this.middleTextView.setText(LocaleController.getString(R.string.LiteBatteryAlwaysEnabled), !LocaleController.isRTL);
            goto L10
        L9:
            float r62 = r0;
            this.batteryIcon.setFillValue(r62 / 100.0f, true);
            this.middleTextView.setText(AndroidUtilities.replaceCharSequence("%s", LocaleController.getString(R.string.LiteBatteryWhenBelow), TextUtils.concat(new CharSequence[]{String.format("%d%% ", new Object[]{Integer.valueOf(Math.round(r62))}), this.batteryText})), !LocaleController.isRTL);
            goto L10
        }
    }

    public class SwitchCell extends FrameLayout {
        private int all;
        private ImageView arrowView;
        private CheckBox2 checkBoxView;
        private boolean containing;
        private AnimatedTextView countTextView;
        private boolean disabled;
        private int enabled;
        private ImageView imageView;
        private boolean needDivider;
        private boolean needLine;
        private float switchAlpha;
        private ValueAnimator switchAlphaAnimator;
        private Switch switchView;
        private TextView textView;
        private LinearLayout textViewLayout;
        final /* synthetic */ LiteModeSettingsActivity this$0;

        /* renamed from: org.telegram.ui.LiteModeSettingsActivity$SwitchCell$1, reason: invalid class name */
        public class AnonymousClass1 extends TextView {
            final /* synthetic */ SwitchCell this$1;
            final /* synthetic */ LiteModeSettingsActivity val$this$0;

            public AnonymousClass1(SwitchCell r1, Context r2, LiteModeSettingsActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.widget.TextView, android.view.View
            public void onMeasure(int r3, int r4) {
                if (View.MeasureSpec.getMode(r3) != Integer.MIN_VALUE) goto L5;
                r3 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3) - AndroidUtilities.dp(52.0f), Integer.MIN_VALUE);
            L5:
                super.onMeasure(r3, r4);
            }
        }

        /* renamed from: org.telegram.ui.LiteModeSettingsActivity$SwitchCell$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ SwitchCell this$1;
            final /* synthetic */ float val$alpha;

            public AnonymousClass2(SwitchCell r1, float r2) {
                this.this$1 = r1;
                this.val$alpha = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                SwitchCell.m15702$$Nest$fputswitchAlpha(this.this$1, this.val$alpha);
                SwitchCell.m15701$$Nest$fgetswitchView(this.this$1).setAlpha(this.val$alpha);
                SwitchCell.m15703$$Nest$fputswitchAlphaAnimator(this.this$1, null);
            }
        }

        public static /* synthetic */ void $r8$lambda$qtEFl98k9yJKH3ee4piPXG0AtGQ(SwitchCell r0, ValueAnimator r1) {
            r0.lambda$setSwitchAlpha$0(r1);
        }

        /* renamed from: -$$Nest$fgetswitchView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Switch m15701$$Nest$fgetswitchView(SwitchCell r0) {
            return r0.switchView;
        }

        /* renamed from: -$$Nest$fputswitchAlpha, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15702$$Nest$fputswitchAlpha(SwitchCell r0, float r1) {
            r0.switchAlpha = r1;
        }

        /* renamed from: -$$Nest$fputswitchAlphaAnimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15703$$Nest$fputswitchAlphaAnimator(SwitchCell r0, ValueAnimator r1) {
            r0.switchAlphaAnimator = r1;
        }

        public SwitchCell(LiteModeSettingsActivity r22, Context r23) {
            this.this$0 = r22;
            super(r23);
            this.switchAlpha = 1.0f;
            setImportantForAccessibility(1);
            ImageView r5 = new ImageView(r23);
            this.imageView = r5;
            int r7 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon);
            PorterDuff.Mode r8 = PorterDuff.Mode.MULTIPLY;
            r5.setColorFilter(new PorterDuffColorFilter(r7, r8));
            this.imageView.setVisibility(8);
            ImageView r52 = this.imageView;
            int r9 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r72 = 5;
        L6:
            addView(r52, LayoutHelper.createFrame(24, 24.0f, r72 | 16, 20.0f, 0.0f, 20.0f, 0.0f));
            AnonymousClass1 r53 = new AnonymousClass1(this, r23, r22);
            this.textView = r53;
            r53.setLines(1);
            this.textView.setSingleLine(true);
            this.textView.setEllipsize(TextUtils.TruncateAt.END);
            this.textView.setTextSize(1, 16.0f);
            TextView r1 = this.textView;
            int r54 = Theme.key_windowBackgroundWhiteBlackText;
            r1.setTextColor(Theme.getColor(r54));
            TextView r12 = this.textView;
            if (LocaleController.isRTL == false) goto L9;
            int r73 = 5;
        L10:
            r12.setGravity(r73);
            this.textView.setImportantForAccessibility(2);
            AnimatedTextView r122 = new AnimatedTextView(r23, false, true, true);
            this.countTextView = r122;
            r122.setAnimationProperties(0.35f, 0, 200, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.countTextView.setTypeface(AndroidUtilities.bold());
            this.countTextView.setTextSize(AndroidUtilities.dp(14.0f));
            this.countTextView.setTextColor(Theme.getColor(r54));
            this.countTextView.setImportantForAccessibility(2);
            ImageView r123 = new ImageView(r23);
            this.arrowView = r123;
            r123.setVisibility(8);
            this.arrowView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r54), r8));
            this.arrowView.setImageResource(R.drawable.arrow_more);
            LinearLayout r55 = new LinearLayout(r23);
            this.textViewLayout = r55;
            r55.setOrientation(0);
            LinearLayout r56 = this.textViewLayout;
            if (LocaleController.isRTL == false) goto L13;
            int r82 = 5;
        L14:
            r56.setGravity(r82);
            boolean r57 = LocaleController.isRTL;
            LinearLayout r83 = this.textViewLayout;
            if (r57 == false) goto L17;
            r83.addView(this.arrowView, LayoutHelper.createLinear(16, 16, 0.0f, 16, 0, 0, 6, 0));
            this.textViewLayout.addView(this.countTextView, LayoutHelper.createLinear(-2, -2, 0.0f, 16, 0, 0, 6, 0));
            this.textViewLayout.addView(this.textView, LayoutHelper.createLinear(-2, -2, 1.0f, 16));
        L18:
            LinearLayout r3 = this.textViewLayout;
            if (LocaleController.isRTL == false) goto L21;
            int r58 = 5;
        L22:
            addView(r3, LayoutHelper.createFrame(-1, -2.0f, r58 | 16, 64.0f, 0.0f, 8.0f, 0.0f));
            Switch r32 = new Switch(r23);
            this.switchView = r32;
            r32.setVisibility(8);
            Switch r33 = this.switchView;
            int r59 = Theme.key_switchTrack;
            int r84 = Theme.key_switchTrackChecked;
            int r124 = Theme.key_windowBackgroundWhite;
            r33.setColors(r59, r84, r124, r124);
            this.switchView.setImportantForAccessibility(2);
            Switch r34 = this.switchView;
            if (LocaleController.isRTL == false) goto L25;
            int r510 = 3;
        L26:
            addView(r34, LayoutHelper.createFrame(37, 50.0f, r510 | 16, 19.0f, 0.0f, 19.0f, 0.0f));
            CheckBox2 r35 = new CheckBox2(r23, 21);
            this.checkBoxView = r35;
            r35.setColor(Theme.key_radioBackgroundChecked, Theme.key_checkboxDisabled, Theme.key_checkboxCheck);
            this.checkBoxView.setDrawUnchecked(true);
            this.checkBoxView.setChecked(true, false);
            this.checkBoxView.setDrawBackgroundAsArc(10);
            this.checkBoxView.setVisibility(8);
            this.checkBoxView.setImportantForAccessibility(2);
            CheckBox2 r2 = this.checkBoxView;
            boolean r36 = LocaleController.isRTL;
            if (r36 == false) goto L29;
            r9 = 5;
        L29:
            int r14 = r9 | 16;
            if (r36 == false) goto L32;
            float r15 = 0.0f;
        L33:
            if (r36 == false) goto L35;
            float r17 = 64.0f;
        L36:
            addView(r2, LayoutHelper.createFrame(21, 21.0f, r14, r15, 0.0f, r17, 0.0f));
            setFocusable(true);
            setClipChildren(false);
            return;
        L35:
            r17 = 0.0f;
            goto L36
        L32:
            r15 = 64.0f;
            goto L33
        L25:
            r510 = 5;
            goto L26
        L21:
            r58 = 3;
            goto L22
        L17:
            r83.addView(this.textView, LayoutHelper.createLinear(-2, -2, 1.0f, 16));
            this.textViewLayout.addView(this.countTextView, LayoutHelper.createLinear(-2, -2, 0.0f, 16, 6, 0, 0, 0));
            this.textViewLayout.addView(this.arrowView, LayoutHelper.createLinear(16, 16, 0.0f, 16, 2, 0, 0, 0));
            goto L18
        L13:
            r82 = 3;
            goto L14
        L9:
            r73 = 3;
            goto L10
        L5:
            r72 = 3;
            goto L6
        }

        private /* synthetic */ void lambda$setSwitchAlpha$0(ValueAnimator r1) {
            float r12 = ((Float) r1.getAnimatedValue()).floatValue();
            this.switchAlpha = r12;
            this.switchView.setAlpha(r12);
        }

        private int preprocessFlagsCount(int r4) {
            boolean r3 = this.this$0.getUserConfig().isPremium();
            int r0 = Integer.bitCount(r4);
            if (r3 == false) goto L14;
            if ((r4 & 4096) <= 0) goto L8;
            r0 = r0 - 1;
        L8:
            if ((r4 & 8192) <= 0) goto L11;
            r0 = r0 - 1;
        L11:
            if ((r4 & 16384) <= 0) goto L23;
        L12:
            r0 = r0 - 1;
        L23:
            if (SharedConfig.getDevicePerformanceClass() >= 1) goto L28;
            if ((r4 & 256) <= 0) goto L28;
            r0 = r0 - 1;
        L28:
            if (Build.VERSION.SDK_INT < 33) goto L34;
            if (SharedConfig.getDevicePerformanceClass() >= 1) goto L37;
            if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L34;
        L37:
            if (ThanosEffect.supports() == false) goto L39;
            return r0;
        L39:
            if ((65536 & r4) > 0) goto L41;
            return r0;
        L41:
            return r0 - 1;
        L34:
            if ((262144 & r4) <= 0) goto L37;
            r0 = r0 - 1;
            goto L37
        L14:
            if ((r4 & 16) <= 0) goto L17;
            r0 = r0 - 1;
        L17:
            if ((r4 & 8) <= 0) goto L20;
            r0 = r0 - 1;
        L20:
            if ((r4 & 4) <= 0) goto L23;
            goto L12
        }

        private void setSwitchAlpha(float r3, boolean r4) {
            ValueAnimator r0 = this.switchAlphaAnimator;
            if (r0 == null) goto L5;
            r0.cancel();
            this.switchAlphaAnimator = null;
        L5:
            this.switchView.clearAnimation();
            if (r4 == false) goto L9;
            ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{this.switchAlpha, r3});
            this.switchAlphaAnimator = r42;
            r42.addUpdateListener(new LiteModeSettingsActivity$SwitchCell$$ExternalSyntheticLambda0(this));
            this.switchAlphaAnimator.addListener(new AnonymousClass2(this, r3));
            this.switchAlphaAnimator.setDuration(220);
            this.switchAlphaAnimator.start();
            return;
        L9:
            this.switchAlpha = r3;
            this.switchView.setAlpha(r3);
        }

        private void updateCount(Item r4, boolean r5) {
            boolean r0 = true;
            this.enabled = preprocessFlagsCount(LiteMode.getValue(true) & r4.flags);
            this.all = preprocessFlagsCount(r4.flags);
            AnimatedTextView r42 = this.countTextView;
            String r3 = String.format("%d/%d", new Object[]{Integer.valueOf(this.enabled), Integer.valueOf(this.all)});
            if (r5 == true) goto L5;
        L7:
            r0 = false;
        L8:
            r42.setText(r3, r0);
            return;
        L5:
            if (LocaleController.isRTL == true) goto L7;
            goto L7
        }

        @Override // android.view.View
        public void onDraw(Canvas r21) {
            super.onDraw(r21);
            boolean r1 = LocaleController.isRTL;
            boolean r2 = this.needLine;
            if (r1 == false) goto L14;
            if (r2 == false) goto L7;
            float r11 = AndroidUtilities.dp(75.0f);
            r21.drawRect(r11 - AndroidUtilities.dp(0.66f), (getMeasuredHeight() - AndroidUtilities.dp(20.0f)) / 2.0f, r11, (getMeasuredHeight() + AndroidUtilities.dp(20.0f)) / 2.0f, Theme.dividerPaint);
        L7:
            if (this.needDivider == false) goto L20;
            int r12 = getMeasuredWidth() - AndroidUtilities.dp(64.0f);
            if (this.textView.getTranslationX() >= 0.0f) goto L11;
            int r22 = AndroidUtilities.dp(-32.0f);
        L12:
            r21.drawLine(r12 + r22, getMeasuredHeight() - 1, 0.0f, getMeasuredHeight() - 1, Theme.dividerPaint);
            return;
        L11:
            r22 = 0;
            goto L12
        L20:
            return;
        L14:
            if (r2 == false) goto L17;
            float r13 = getMeasuredWidth() - AndroidUtilities.dp(75.0f);
            r21.drawRect(r13 - AndroidUtilities.dp(0.66f), (getMeasuredHeight() - AndroidUtilities.dp(20.0f)) / 2.0f, r13, (getMeasuredHeight() + AndroidUtilities.dp(20.0f)) / 2.0f, Theme.dividerPaint);
        L17:
            if (this.needDivider == false) goto L21;
            r21.drawLine(AndroidUtilities.dp(64.0f) + this.textView.getTranslationX(), getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, Theme.dividerPaint);
            return;
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r4) {
            super.onInitializeAccessibilityNodeInfo(r4);
            if (this.checkBoxView.getVisibility() != 0) goto L5;
            CharSequence r0 = "android.widget.CheckBox";
        L6:
            r4.setClassName(r0);
            r4.setCheckable(true);
            r4.setEnabled(true);
            if (this.checkBoxView.getVisibility() != 0) goto L9;
            r4.setChecked(this.checkBoxView.isChecked());
        L10:
            StringBuilder r02 = new StringBuilder();
            r02.append(this.textView.getText());
            if (this.containing == false) goto L13;
            r02.append('\n');
            r02.append(LocaleController.formatString("Of", R.string.Of, new Object[]{Integer.valueOf(this.enabled), Integer.valueOf(this.all)}));
        L13:
            r4.setContentDescription(r02);
            return;
        L9:
            r4.setChecked(this.switchView.isChecked());
            goto L10
        L5:
            r0 = "android.widget.Switch";
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30));
        }

        public void set(Item r7, boolean r8) {
            int r0 = r7.viewType;
            CheckBox2 r1 = this.checkBoxView;
            boolean r2 = true;
            if (r0 != 3) goto L17;
            r1.setVisibility(8);
            this.imageView.setVisibility(0);
            this.imageView.setImageResource(r7.iconResId);
            this.textView.setText(r7.text);
            if (r7.getFlagsCount() <= 1) goto L7;
            boolean r02 = true;
        L8:
            this.containing = r02;
            if (r02 == false) goto L11;
            updateCount(r7, false);
            this.countTextView.setVisibility(0);
            this.arrowView.setVisibility(0);
        L12:
            this.textView.setTranslationX(0.0f);
            this.switchView.setVisibility(0);
            this.switchView.setChecked(LiteMode.isEnabled(r7.flags), false);
            if (r7.getFlagsCount() <= 1) goto L15;
            boolean r03 = true;
        L16:
            this.needLine = r03;
        L22:
            ViewGroup.MarginLayoutParams r04 = (ViewGroup.MarginLayoutParams) this.textViewLayout.getLayoutParams();
            if (r7.viewType == 3) goto L25;
            float r72 = 8.0f;
        L30:
            r04.rightMargin = AndroidUtilities.dp(r72);
            this.needDivider = r8;
            if (r8 == false) goto L33;
        L35:
            r2 = false;
        L36:
            setWillNotDraw(r2);
            setDisabled(LiteMode.isPowerSaverApplied(), false);
            return;
        L33:
            if (this.needLine == true) goto L35;
        L25:
            if (LocaleController.isRTL == false) goto L27;
            int r73 = 64;
        L28:
            r72 = r73 + 4;
            goto L30
        L27:
            r73 = 75;
            goto L28
        L15:
            r03 = false;
            goto L16
        L11:
            this.countTextView.setVisibility(8);
            this.arrowView.setVisibility(8);
            goto L12
        L7:
            r02 = false;
            goto L8
        L17:
            r1.setVisibility(0);
            this.checkBoxView.setChecked(LiteMode.isEnabled(r7.flags), false);
            this.imageView.setVisibility(8);
            this.switchView.setVisibility(8);
            this.countTextView.setVisibility(8);
            this.arrowView.setVisibility(8);
            this.textView.setText(r7.text);
            TextView r05 = this.textView;
            float r12 = AndroidUtilities.dp(41.0f);
            if (LocaleController.isRTL == false) goto L20;
            float r3 = -2.2f;
        L21:
            r05.setTranslationX(r12 * r3);
            this.containing = false;
            this.needLine = false;
            goto L22
        L20:
            r3 = 1.0f;
            goto L21
        }

        public void setDisabled(boolean r6, boolean r7) {
            if (this.disabled == r6) goto L14;
            this.disabled = r6;
            if (r6 == false) goto L7;
            float r0 = 0.5f;
        L8:
            ImageView r1 = this.imageView;
            if (r7 == false) goto L11;
            r1.animate().alpha(r0).setDuration(220).start();
            this.textViewLayout.animate().alpha(r0).setDuration(220).start();
            setSwitchAlpha(r0, true);
            this.checkBoxView.animate().alpha(r0).setDuration(220).start();
        L12:
            setEnabled(!r6);
            return;
        L11:
            r1.setAlpha(r0);
            this.textViewLayout.setAlpha(r0);
            setSwitchAlpha(r0, false);
            this.checkBoxView.setAlpha(r0);
            goto L12
        L7:
            r0 = 1.0f;
            goto L8
        }

        public void update(Item r6) {
            if (r6.viewType == 3) goto L5;
            this.checkBoxView.setChecked(LiteMode.isEnabled(r6.flags), true);
        L19:
            setDisabled(LiteMode.isPowerSaverApplied(), true);
            return;
        L5:
            if (r6.getFlagsCount() <= 1) goto L7;
            boolean r0 = true;
        L8:
            this.containing = r0;
            if (r0 == false) goto L17;
            updateCount(r6, true);
            int r02 = LiteModeSettingsActivity.m15695$$Nest$mgetExpandedIndex(this.this$0, r6.flags);
            this.arrowView.clearAnimation();
            ViewPropertyAnimator r1 = this.arrowView.animate();
            if (r02 >= 0) goto L13;
        L15:
            float r03 = 0.0f;
        L16:
            r1.rotation(r03).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(240).start();
            goto L17
        L13:
            if (LiteModeSettingsActivity.m15693$$Nest$fgetexpanded(this.this$0)[r02] == false) goto L15;
            r03 = 180.0f;
        L17:
            this.switchView.setChecked(LiteMode.isEnabled(r6.flags), true);
            goto L19
        L7:
            r0 = false;
            goto L8
        }
    }

    public static /* synthetic */ void $r8$lambda$3jQ9UWJyha9qInDmCWiVDS5iOCw(LiteModeSettingsActivity r0, Boolean r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$obk5RgM4OOmZtvLVmGOby0kWjOQ(LiteModeSettingsActivity r0, View r1, int r2, float r3, float r4) {
        r0.lambda$createView$0(r1, r2, r3, r4);
    }

    public static /* synthetic */ int $r8$lambda$vqpET2vyGgkoQJmeYsMUmdLbsk8(LiteModeSettingsActivity r0, int r1) {
        return r0.lambda$highlightRow$2(r1);
    }

    /* renamed from: -$$Nest$fgetexpanded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean[] m15693$$Nest$fgetexpanded(LiteModeSettingsActivity r0) {
        return r0.expanded;
    }

    /* renamed from: -$$Nest$fgetitems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m15694$$Nest$fgetitems(LiteModeSettingsActivity r0) {
        return r0.items;
    }

    /* renamed from: -$$Nest$mgetExpandedIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15695$$Nest$mgetExpandedIndex(LiteModeSettingsActivity r0, int r1) {
        return r0.getExpandedIndex(r1);
    }

    /* renamed from: -$$Nest$mupdateInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15696$$Nest$mupdateInfo(LiteModeSettingsActivity r0) {
        r0.updateInfo();
    }

    /* renamed from: -$$Nest$mupdateValues, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15697$$Nest$mupdateValues(LiteModeSettingsActivity r0) {
        r0.updateValues();
    }

    public LiteModeSettingsActivity() {
        this.onPowerAppliedChange = new LiteModeSettingsActivity$$ExternalSyntheticLambda0(this);
        this.expanded = new boolean[3];
        this.oldItems = new ArrayList();
        this.items = new ArrayList();
    }

    private int getExpandedIndex(int r2) {
        if (r2 != 3) goto L7;
        return 0;
    L7:
        if (r2 != 28700) goto L11;
        return 1;
    L11:
        if (r2 != this.FLAGS_CHAT) goto L14;
        return 2;
    L14:
        return -1;
    }

    private CharSequence getPowerSaverInfoText() {
        if (LiteMode.isPowerSaverFollowSystem() == false) goto L7;
        return LocaleController.getString(R.string.LiteBatteryFollowSystemInfo);
    L7:
        if (LiteMode.getPowerSaverLevel() > 0) goto L11;
        return LocaleController.getString(R.string.LiteBatteryInfoDisabled);
    L11:
        if (LiteMode.getPowerSaverLevel() < 100) goto L15;
        return LocaleController.getString(R.string.LiteBatteryInfoEnabled);
    L15:
        return LocaleController.formatString(R.string.LiteBatteryInfoBelow, new Object[]{String.format("%d%%", new Object[]{Integer.valueOf(LiteMode.getPowerSaverLevel())})});
    }

    private void highlightRow(int r2) {
        LiteModeSettingsActivity$$ExternalSyntheticLambda2 r0 = new LiteModeSettingsActivity$$ExternalSyntheticLambda2(this, r2);
        this.listView.highlightRow(r0);
    }

    private /* synthetic */ void lambda$createView$0(View r4, int r5, float r6, float r7) {
        if (r4 == null) goto L43;
        if (r5 >= 0) goto L5;
        return;
    L5:
        if (r5 >= this.items.size()) goto L47;
        Item r52 = this.items.get(r5);
        int r72 = r52.viewType;
        if (r72 == 3) goto L23;
        if (r72 == 4) goto L23;
        if (r72 != 5) goto L45;
        int r53 = r52.type;
        if (r53 != 1) goto L19;
        SharedPreferences r3 = MessagesController.getGlobalMainSettings();
        boolean r62 = r3.getBoolean("view_animations", true);
        SharedPreferences.Editor r32 = r3.edit();
        r32.putBoolean("view_animations", !r62);
        SharedConfig.setAnimationsEnabled(!r62);
        r32.apply();
        ((TextCell) r4).setChecked(!r62);
        return;
    L19:
        if (r53 != 2) goto L46;
        LiteMode.setPowerSaverFollowSystem(!LiteMode.isPowerSaverFollowSystem());
        updateValues();
        updateInfo();
        ((TextCell) r4).setChecked(LiteMode.isPowerSaverFollowSystem());
        return;
    L46:
        return;
    L45:
        return;
    L23:
        if (LiteMode.isPowerSaverApplied() == false) goto L27;
        this.restrictBulletin = BulletinFactory.of(this).createSimpleBulletin(new BatteryDrawable(0.1f, -1, Theme.getColor(Theme.key_dialogSwipeRemove), 1.3f), LocaleController.getString(R.string.LiteBatteryRestricted)).show();
        return;
    L27:
        if (r52.viewType == 3) goto L29;
    L41:
        LiteMode.toggleFlag(r52.flags, !LiteMode.isEnabledSetting(r52.flags));
        updateValues();
        return;
    L29:
        if (r52.getFlagsCount() <= 1) goto L41;
        if (LocaleController.isRTL == false) goto L36;
        if (r6 <= AndroidUtilities.dp(75.0f)) goto L41;
    L37:
        int r42 = getExpandedIndex(r52.flags);
        if (r42 == (-1)) goto L41;
        this.expanded[r42] = !r5[r42];
        updateValues();
        updateItems();
        return;
    L36:
        if (r6 >= (r4.getMeasuredWidth() - AndroidUtilities.dp(75.0f))) goto L41;
    L47:
        return;
    }

    private /* synthetic */ int lambda$highlightRow$2(int r2) {
        this.layoutManager.scrollToPositionWithOffset(r2, AndroidUtilities.dp(60.0f));
        return r2;
    }

    private /* synthetic */ void lambda$new$1(Boolean r1) {
        updateValues();
    }

    private void updateInfo() {
        if (this.items.isEmpty() == false) goto L7;
        updateItems();
        return;
    L7:
        if (this.items.size() < 3) goto L10;
        this.items.set(2, Item.asInfo(getPowerSaverInfoText()));
        this.adapter.notifyItemChanged(2);
        return;
    }

    private void updateItems() {
        this.oldItems.clear();
        this.oldItems.addAll(this.items);
        this.items.clear();
        this.items.add(Item.asSlider());
        this.items.add(Item.asSwitch(LocaleController.getString(R.string.LiteBatteryFollowSystem), 2));
        this.items.add(Item.asInfo(getPowerSaverInfoText()));
        this.items.add(Item.asHeader(LocaleController.getString(R.string.LiteOptionsTitle)));
        this.items.add(Item.asSwitch(R.drawable.msg2_sticker, LocaleController.getString(R.string.LiteOptionsStickers), 3));
        if (this.expanded[0] == false) goto L5;
        this.items.add(Item.asCheckbox(LocaleController.getString(R.string.LiteOptionsAutoplayKeyboard), 1));
        this.items.add(Item.asCheckbox(LocaleController.getString(R.string.LiteOptionsAutoplayChat), 2));
    L5:
        this.items.add(Item.asSwitch(R.drawable.msg2_smile_status, LocaleController.getString(R.string.LiteOptionsEmoji), LiteMode.FLAGS_ANIMATED_EMOJI));
        if (this.expanded[1] == false) goto L8;
        this.items.add(Item.asCheckbox(LocaleController.getString(R.string.LiteOptionsAutoplayKeyboard), LiteMode.FLAG_ANIMATED_EMOJI_KEYBOARD));
        this.items.add(Item.asCheckbox(LocaleController.getString(R.string.LiteOptionsAutoplayReactions), LiteMode.FLAG_ANIMATED_EMOJI_REACTIONS));
        this.items.add(Item.asCheckbox(LocaleController.getString(R.string.LiteOptionsAutoplayChat), LiteMode.FLAG_ANIMATED_EMOJI_CHAT));
    L8:
        this.items.add(Item.asSwitch(R.drawable.msg2_ask_question, LocaleController.getString(R.string.LiteOptionsChat), this.FLAGS_CHAT));
        if (this.expanded[2] == false) goto L28;
        this.items.add(Item.asCheckbox(LocaleController.getString("LiteOptionsBackground"), 32));
        if (AndroidUtilities.isTablet() == true) goto L13;
        this.items.add(Item.asCheckbox(LocaleController.getString("LiteOptionsTopics"), 64));
    L13:
        this.items.add(Item.asCheckbox(LocaleController.getString("LiteOptionsSpoiler"), 128));
        if (SharedConfig.getDevicePerformanceClass() < 1) goto L16;
    L17:
        this.items.add(Item.asCheckbox(LocaleController.getString("LiteOptionsBlur2"), 256));
    L19:
        if (Build.VERSION.SDK_INT >= 33) goto L21;
    L25:
        this.items.add(Item.asCheckbox(LocaleController.getString("LiteOptionsScale"), 32768));
        if (ThanosEffect.supports() == false) goto L28;
        this.items.add(Item.asCheckbox(LocaleController.getString("LiteOptionsThanos"), 65536));
        goto L28
    L21:
        if (SharedConfig.getDevicePerformanceClass() < 1) goto L23;
    L24:
        this.items.add(Item.asCheckbox(LocaleController.getString("LiteOptionsLiquidGlass"), 262144));
        goto L25
    L23:
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L25;
    L16:
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L19;
    L28:
        this.items.add(Item.asSwitch(R.drawable.msg2_call_earpiece, LocaleController.getString(R.string.LiteOptionsCalls), 512));
        this.items.add(Item.asSwitch(R.drawable.msg2_videocall, LocaleController.getString(R.string.LiteOptionsAutoplayVideo), 1024));
        this.items.add(Item.asSwitch(R.drawable.msg2_gif, LocaleController.getString(R.string.LiteOptionsAutoplayGifs), 2048));
        this.items.add(Item.asSwitch(R.drawable.photo_star, LocaleController.getString(R.string.LiteOptionsParticles), 131072));
        this.items.add(Item.asInfo(_UrlKt.FRAGMENT_ENCODE_SET));
        this.items.add(Item.asSwitch(LocaleController.getString(R.string.LiteSmoothTransitions), 1));
        this.items.add(Item.asInfo(LocaleController.getString("LiteSmoothTransitionsInfo")));
        this.adapter.setItems(this.oldItems, this.items);
    }

    private void updateValues() {
        if (this.listView == null) goto L52;
        int r0 = 0;
    L7:
        if (r0 >= this.listView.getChildCount()) goto L35;
        View r1 = this.listView.getChildAt(r0);
        if (r1 == null) goto L33;
        int r2 = this.listView.getChildAdapterPosition(r1);
        if (r2 < 0) goto L33;
        if (r2 >= this.items.size()) goto L33;
        Item r22 = this.items.get(r2);
        int r3 = r22.viewType;
        if (r3 != 3) goto L19;
    L32:
        ((SwitchCell) r1).update(r22);
        goto L33
    L19:
        if (r3 == 4) goto L32;
        if (r3 != 1) goto L25;
        ((PowerSaverSlider) r1).update();
        goto L33
    L25:
        if (r3 != 5) goto L33;
        int r23 = r22.type;
        if (r23 != 1) goto L30;
        ((TextCell) r1).setChecked(MessagesController.getGlobalMainSettings().getBoolean("view_animations", true));
        goto L33
    L30:
        if (r23 != 2) goto L33;
        ((TextCell) r1).setChecked(LiteMode.isPowerSaverFollowSystem());
    L33:
        r0 = r0 + 1;
        goto L7
    L35:
        if (this.restrictBulletin != null) goto L37;
        return;
    L37:
        if (LiteMode.isPowerSaverApplied() == true) goto L51;
        this.restrictBulletin.hide();
        this.restrictBulletin = null;
        return;
    L51:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r4) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.PowerUsage));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        INavigationLayout r0 = this.parentLayout;
        if (r0 != null) goto L5;
    L7:
        FrameLayout r02 = new FrameLayout(r4);
        this.contentView = r02;
        r02.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        RecyclerListView r03 = new RecyclerListView(r4);
        this.listView = r03;
        r03.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        RecyclerListView r04 = this.listView;
        LinearLayoutManager r1 = new LinearLayoutManager(r4);
        this.layoutManager = r1;
        r04.setLayoutManager(r1);
        RecyclerListView r42 = this.listView;
        Adapter r05 = new Adapter(this, null);
        this.adapter = r05;
        r42.setAdapter(r05);
        DefaultItemAnimator r43 = new DefaultItemAnimator();
        r43.setDurations(350);
        r43.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r43.setDelayAnimations(false);
        r43.setSupportsChangeAnimations(false);
        this.listView.setItemAnimator(r43);
        this.contentView.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setOnItemClickListener(new LiteModeSettingsActivity$$ExternalSyntheticLambda1(this));
        this.fragmentView = this.contentView;
        if (AndroidUtilities.isTablet() == false) goto L10;
        int r44 = 360864;
    L11:
        this.FLAGS_CHAT = r44;
        updateItems();
        return this.fragmentView;
    L10:
        r44 = LiteMode.FLAGS_CHAT;
        goto L11
    L5:
        if (r0.isRightLayout() == false) goto L7;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_close);
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBecomeFullyHidden() {
        super.onBecomeFullyHidden();
        LiteMode.removeOnPowerSaverAppliedListener(this.onPowerAppliedChange);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBecomeFullyVisible() {
        super.onBecomeFullyVisible();
        LiteMode.addOnPowerSaverAppliedListener(this.onPowerAppliedChange);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        LiteMode.savePreference();
        AnimatedEmojiDrawable.updateAll();
        Theme.reloadWallpaper(true);
        LiteMode.removeOnPowerSaverAppliedListener(this.onPowerAppliedChange);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }

    public void scrollToFlags(int r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.items.size()) goto L10;
        if (this.items.get(r0).flags == r3) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        highlightRow(r0);
        return;
    }

    public void scrollToType(int r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.items.size()) goto L10;
        if (this.items.get(r0).type == r3) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        highlightRow(r0);
        return;
    }

    public void setExpanded(int r2, boolean r3) {
        int r22 = getExpandedIndex(r2);
        if (r22 != (-1)) goto L5;
        return;
    L5:
        this.expanded[r22] = r3;
        updateValues();
        updateItems();
    }
}
