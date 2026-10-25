package org.telegram.ui;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.exteragram.messenger.utils.system.VibratorUtils;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.EditTextCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.SectionsScrollView;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.SlideChooseView;
import org.telegram.ui.Stories.recorder.KeyboardNotifier;

/* loaded from: classes6.dex */
public class LinkEditActivity extends BaseFragment {
    private TextCheckCell approveCell;
    private TextInfoPrivacyCell approveHintCell;
    private FrameLayout buttonLayout;
    private Callback callback;
    private final long chatId;
    private TextView createTextView;
    int currentInviteDate;
    private final int[] defaultDates;
    private final int[] defaultUses;
    private ArrayList<Integer> dispalyedDates;
    private ArrayList<Integer> dispalyedUses;
    private TextInfoPrivacyCell divider;
    private TextInfoPrivacyCell dividerName;
    private TextInfoPrivacyCell dividerUses;
    private boolean finished;
    private boolean firstLayout;
    private boolean ignoreSet;
    TLRPC.TL_chatInviteExported inviteToEdit;
    boolean loading;
    private EditText nameEditText;
    AlertDialog progressDialog;
    private TextSettingsCell revokeLink;
    boolean scrollToEnd;
    boolean scrollToStart;
    private SectionsScrollView scrollView;
    private int shakeDp;
    private TextCheckCell subCell;
    private EditTextCell subEditPriceCell;
    private TextInfoPrivacyCell subInfoCell;
    private TextView subPriceView;
    private SlideChooseView timeChooseView;
    private TextView timeEditText;
    private HeaderCell timeHeaderCell;
    private int type;
    private SlideChooseView usesChooseView;
    private EditText usesEditText;
    private HeaderCell usesHeaderCell;

    /* renamed from: org.telegram.ui.LinkEditActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ LinkEditActivity this$0;

        public AnonymousClass1(LinkEditActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            AndroidUtilities.hideKeyboard(LinkEditActivity.m15681$$Nest$fgetusesEditText(this.this$0));
            return;
        }
    }

    /* renamed from: org.telegram.ui.LinkEditActivity$10, reason: invalid class name */
    public class AnonymousClass10 implements TextWatcher {
        final /* synthetic */ LinkEditActivity this$0;

        public AnonymousClass10(LinkEditActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r2) {
            Emoji.replaceEmoji(r2, LinkEditActivity.m15678$$Nest$fgetnameEditText(this.this$0).getPaint().getFontMetricsInt(), false);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.LinkEditActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends Drawable {
        final Paint p;
        final /* synthetic */ LinkEditActivity this$0;

        public AnonymousClass2(LinkEditActivity r2) {
            this.this$0 = r2;
            this.p = new Paint(1);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r10) {
            this.p.setColor(Theme.getColor(Theme.key_telegram_color));
            r10.drawRoundRect(getBounds().left, getBounds().exactCenterY() - AndroidUtilities.dp(14.0f), getBounds().right, AndroidUtilities.dp(14.0f) + getBounds().exactCenterY(), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), this.p);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    /* renamed from: org.telegram.ui.LinkEditActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends SectionsScrollView.SectionsLinearLayout {
        final /* synthetic */ LinkEditActivity this$0;

        public AnonymousClass3(LinkEditActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r1) {
            super.dispatchDraw(r1);
            LinkEditActivity.m15682$$Nest$fputfirstLayout(this.this$0, false);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
        }
    }

    /* renamed from: org.telegram.ui.LinkEditActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends SizeNotifierFrameLayout {
        final /* synthetic */ LinkEditActivity this$0;

        public AnonymousClass4(LinkEditActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r3) {
            super.dispatchDraw(r3);
            LinkEditActivity r32 = this.this$0;
            if (r32.scrollToEnd == false) goto L7;
            r32.scrollToEnd = false;
            LinkEditActivity.m15679$$Nest$fgetscrollView(r32).smoothScrollTo(0, Math.max(0, LinkEditActivity.m15679$$Nest$fgetscrollView(this.this$0).getChildAt(0).getMeasuredHeight() - LinkEditActivity.m15679$$Nest$fgetscrollView(this.this$0).getMeasuredHeight()));
            return;
        L7:
            if (r32.scrollToStart == false) goto L10;
            r32.scrollToStart = false;
            LinkEditActivity.m15679$$Nest$fgetscrollView(r32).smoothScrollTo(0, 0);
            return;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
            int r0 = LinkEditActivity.m15679$$Nest$fgetscrollView(this.this$0).getScrollY();
            super.onLayout(r2, r3, r4, r5, r6);
            if (r0 == LinkEditActivity.m15679$$Nest$fgetscrollView(this.this$0).getScrollY()) goto L8;
            LinkEditActivity r22 = this.this$0;
            if (r22.scrollToEnd == true) goto L9;
            LinkEditActivity.m15679$$Nest$fgetscrollView(r22).setTranslationY(LinkEditActivity.m15679$$Nest$fgetscrollView(this.this$0).getScrollY() - r0);
            LinkEditActivity.m15679$$Nest$fgetscrollView(this.this$0).animate().cancel();
            LinkEditActivity.m15679$$Nest$fgetscrollView(this.this$0).animate().translationY(0.0f).setDuration(250).setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator).start();
            return;
        L9:
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, r3);
            measureKeyboardHeight();
            int r22 = this.keyboardHeight;
            if (r22 != 0) goto L5;
        L7:
            FrameLayout r23 = LinkEditActivity.m15676$$Nest$fgetbuttonLayout(this.this$0);
            if (this.keyboardHeight <= AndroidUtilities.dp(20.0f)) goto L10;
            int r1 = 8;
        L11:
            r23.setVisibility(r1);
            return;
        L10:
            r1 = 0;
            goto L11
        L5:
            if (r22 >= AndroidUtilities.dp(20.0f)) goto L7;
            LinkEditActivity.m15681$$Nest$fgetusesEditText(this.this$0).clearFocus();
            LinkEditActivity.m15678$$Nest$fgetnameEditText(this.this$0).clearFocus();
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.LinkEditActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends EditText {
        final /* synthetic */ LinkEditActivity this$0;

        public AnonymousClass5(LinkEditActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.TextView, android.view.View
        public boolean onTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 1) goto L6;
            setCursorVisible(true);
        L6:
            return super.onTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.LinkEditActivity$6, reason: invalid class name */
    public class AnonymousClass6 implements TextWatcher {
        final /* synthetic */ LinkEditActivity this$0;

        public AnonymousClass6(LinkEditActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r3) {
            if (LinkEditActivity.m15677$$Nest$fgetignoreSet(this.this$0) == false) goto L6;
            return;
        L6:
            if (r3.toString().equals(MVEL.VERSION_SUB) == false) goto L18;
            LinkEditActivity.m15681$$Nest$fgetusesEditText(this.this$0).setText(_UrlKt.FRAGMENT_ENCODE_SET);
            return;
        L18:
            int r32 = Integer.parseInt(r3.toString());     // Catch: NumberFormatException -> L16
            LinkEditActivity r2 = this.this$0;
            if (r32 <= 100000) goto L14;
            LinkEditActivity.m15684$$Nest$mresetUses(r2);
            return;
        L14:
            LinkEditActivity.m15683$$Nest$mchooseUses(r2, r32);
            return;
        L16:
            LinkEditActivity.m15684$$Nest$mresetUses(this.this$0);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.LinkEditActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends TextCheckCell {
        final /* synthetic */ LinkEditActivity this$0;

        public AnonymousClass7(LinkEditActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Cells.TextCheckCell, android.view.View
        public void onDraw(Canvas r4) {
            r4.save();
            r4.clipRect(0, 0, getWidth(), getHeight());
            super.onDraw(r4);
            r4.restore();
        }
    }

    /* renamed from: org.telegram.ui.LinkEditActivity$8, reason: invalid class name */
    public class AnonymousClass8 extends EditTextCell {
        private boolean ignoreTextChanged;
        final /* synthetic */ LinkEditActivity this$0;

        public AnonymousClass8(LinkEditActivity r1, Context r2, String r3, boolean r4, boolean r5, int r6, Theme.ResourcesProvider r7) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7);
        }

        @Override // org.telegram.ui.Cells.EditTextCell
        public void onTextChanged(CharSequence r7) {
            super.onTextChanged(r7);
            if (this.ignoreTextChanged == false) goto L6;
            return;
        L6:
            if (TextUtils.isEmpty(r7) == false) goto L21;
            LinkEditActivity.m15680$$Nest$fgetsubPriceView(this.this$0).setText(_UrlKt.FRAGMENT_ENCODE_SET);
            return;
        L21:
            long r0 = Long.parseLong(r7.toString());     // Catch: Exception -> L18
            if (r0 <= this.this$0.getMessagesController().starsSubscriptionAmountMax) goto L12;
            this.ignoreTextChanged = true;     // Catch: Exception -> L18
            r0 = this.this$0.getMessagesController().starsSubscriptionAmountMax;     // Catch: Exception -> L18
            setText(Long.toString(r0));     // Catch: Exception -> L18
            this.ignoreTextChanged = false;     // Catch: Exception -> L18
        L12:
            TextView r72 = LinkEditActivity.m15680$$Nest$fgetsubPriceView(this.this$0);     // Catch: Exception -> L18
            if (this.this$0.getConnectionsManager().isTestBackend() == false) goto L15;
            int r2 = R.string.RequireMonthlyFeePriceTest5Minutes;     // Catch: Exception -> L18
        L16:
            r72.setText(LocaleController.formatString(r2, new Object[]{BillingController.getInstance().formatCurrency((long) ((r0 / 1000.0d) * MessagesController.getInstance(LinkEditActivity.access$000(this.this$0)).starsUsdWithdrawRate1000), "USD")}));     // Catch: Exception -> L18
            return;
        L15:
            r2 = R.string.RequireMonthlyFeePrice;     // Catch: Exception -> L18
        L18:
            e = move-exception;
            FileLog.e(e);
        }
    }

    /* renamed from: org.telegram.ui.LinkEditActivity$9, reason: invalid class name */
    public class AnonymousClass9 extends EditText {
        final /* synthetic */ LinkEditActivity this$0;

        public AnonymousClass9(LinkEditActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.TextView, android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 1) goto L6;
            setCursorVisible(true);
        L6:
            return super.onTouchEvent(r3);
        }
    }

    public interface Callback {
        void onLinkCreated(TLObject r1);

        void onLinkEdited(TLRPC.TL_chatInviteExported r1, TLObject r2);

        void onLinkRemoved(TLRPC.TL_chatInviteExported r1);

        void revokeLink(TLRPC.TL_chatInviteExported r1);
    }

    /* renamed from: $r8$lambda$-Wxozuw3YDXmzMaNrHu8FZ7D5i0, reason: not valid java name */
    public static /* synthetic */ void m15670$r8$lambda$Wxozuw3YDXmzMaNrHu8FZ7D5i0(LinkEditActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$onCreateClicked$14(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$2bfPdTVCSJjws7kGLUNsA25_CNM(LinkEditActivity r0, boolean r1, View r2) {
        r0.lambda$createView$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$EpMrRg807wV2F9AkfxiGPhrrr04(LinkEditActivity r0, View r1) {
        r0.lambda$createView$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$LWY1Vj4eApVLCyjjBuiNQ4wXD2Q(LinkEditActivity r0, int r1) {
        r0.lambda$createView$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$N8v3ZrtsNDwtaVbJCE7m8_6V3o4(LinkEditActivity r0) {
        r0.lambda$createView$8();
    }

    public static /* synthetic */ void $r8$lambda$PtMwoxo3HJKRJNJVDYxszG_lC40(Integer r0) {
    }

    /* renamed from: $r8$lambda$RH-0cV9V6689N7iOM8BEfWYrJAI, reason: not valid java name */
    public static /* synthetic */ void m15671$r8$lambda$RH0cV9V6689N7iOM8BEfWYrJAI(LinkEditActivity r0) {
        r0.lambda$createView$6();
    }

    /* renamed from: $r8$lambda$RvGn01ezxlULzSogZ8QXtQo9-L0, reason: not valid java name */
    public static /* synthetic */ void m15672$r8$lambda$RvGn01ezxlULzSogZ8QXtQo9L0(LinkEditActivity r0, Runnable[] r1, View r2) {
        r0.lambda$createView$7(r1, r2);
    }

    /* renamed from: $r8$lambda$Vdhk6mNHRpQyMAMf3r-75YrT35E, reason: not valid java name */
    public static /* synthetic */ void m15673$r8$lambda$Vdhk6mNHRpQyMAMf3r75YrT35E(LinkEditActivity r0) {
        r0.lambda$getThemeDescriptions$16();
    }

    public static /* synthetic */ void $r8$lambda$VkFsMCELWiSKO0QY89F0mnezD2I(LinkEditActivity r0, Context r1, View r2) {
        r0.lambda$createView$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$hq1ymIohu2YUBmvujhyeaCumSp4(LinkEditActivity r0, View r1) {
        r0.onCreateClicked(r1);
    }

    public static /* synthetic */ void $r8$lambda$kRKVdBWoqZv9PqbKpSXCtO_rW7s(LinkEditActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$onCreateClicked$12(r1, r2);
    }

    /* renamed from: $r8$lambda$njsI3Z27Ul1bgi7vckrY1z-8c7A, reason: not valid java name */
    public static /* synthetic */ void m15674$r8$lambda$njsI3Z27Ul1bgi7vckrY1z8c7A(LinkEditActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$onCreateClicked$15(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$oYKslG3Hc8sH6Ej4Dq_mKaAUHfI(LinkEditActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$onCreateClicked$13(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$rQrxRM_SXq896GHiTanAhFNqveA(LinkEditActivity r0, int r1) {
        r0.lambda$createView$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$sql_ZsdxsPUhXNRXvkIsolG3hRI(LinkEditActivity r0, boolean r1, int r2, int r3) {
        r0.lambda$createView$0(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$uKWg_70ZEDh0ljexy85BjGSYH-I, reason: not valid java name */
    public static /* synthetic */ void m15675$r8$lambda$uKWg_70ZEDh0ljexy85BjGSYHI(LinkEditActivity r0) {
        r0.lambda$createView$5();
    }

    public static /* synthetic */ void $r8$lambda$y_7bBiiiWtlZLggvGiQCzuC3FRI(LinkEditActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$9(r1, r2);
    }

    /* renamed from: -$$Nest$fgetbuttonLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m15676$$Nest$fgetbuttonLayout(LinkEditActivity r0) {
        return r0.buttonLayout;
    }

    /* renamed from: -$$Nest$fgetignoreSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15677$$Nest$fgetignoreSet(LinkEditActivity r0) {
        return r0.ignoreSet;
    }

    /* renamed from: -$$Nest$fgetnameEditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditText m15678$$Nest$fgetnameEditText(LinkEditActivity r0) {
        return r0.nameEditText;
    }

    /* renamed from: -$$Nest$fgetscrollView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SectionsScrollView m15679$$Nest$fgetscrollView(LinkEditActivity r0) {
        return r0.scrollView;
    }

    /* renamed from: -$$Nest$fgetsubPriceView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m15680$$Nest$fgetsubPriceView(LinkEditActivity r0) {
        return r0.subPriceView;
    }

    /* renamed from: -$$Nest$fgetusesEditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditText m15681$$Nest$fgetusesEditText(LinkEditActivity r0) {
        return r0.usesEditText;
    }

    /* renamed from: -$$Nest$fputfirstLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15682$$Nest$fputfirstLayout(LinkEditActivity r0, boolean r1) {
        r0.firstLayout = r1;
    }

    /* renamed from: -$$Nest$mchooseUses, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15683$$Nest$mchooseUses(LinkEditActivity r0, int r1) {
        r0.chooseUses(r1);
    }

    /* renamed from: -$$Nest$mresetUses, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15684$$Nest$mresetUses(LinkEditActivity r0) {
        r0.resetUses();
    }

    public LinkEditActivity(int r5, long r6) {
        this.shakeDp = -3;
        this.firstLayout = true;
        this.dispalyedDates = new ArrayList();
        this.defaultDates = new int[]{3600, 86400, 604800};
        this.dispalyedUses = new ArrayList();
        this.defaultUses = new int[]{1, 10, 100};
        this.type = r5;
        this.chatId = r6;
    }

    public static /* synthetic */ int access$000(LinkEditActivity r0) {
        return r0.currentAccount;
    }

    private void chooseDate(int r14) {
        long r1 = r14;
        this.timeEditText.setText(LocaleController.formatDateAudio(r1, false));
        int r142 = r14 - getConnectionsManager().getCurrentTime();
        this.dispalyedDates.clear();
        int r0 = 0;
        boolean r4 = false;
        int r5 = 0;
    L3:
        int[] r6 = this.defaultDates;
        if (r0 >= r6.length) goto L10;
        if (r4 == true) goto L9;
        if (r142 >= r6[r0]) goto L9;
        this.dispalyedDates.add(Integer.valueOf(r142));
        r5 = r0;
        r4 = true;
    L9:
        this.dispalyedDates.add(Integer.valueOf(this.defaultDates[r0]));
        r0 = r0 + 1;
        goto L3
    L10:
        if (r4 == true) goto L12;
        this.dispalyedDates.add(Integer.valueOf(r142));
        r5 = this.defaultDates.length;
    L12:
        int r02 = this.dispalyedDates.size();
        int r42 = r02 + 1;
        String[] r62 = new String[r42];
        int r7 = 0;
    L13:
        if (r7 >= r42) goto L33;
        if (r7 != r02) goto L17;
        r62[r7] = LocaleController.getString(R.string.NoLimit);
    L32:
        r7 = r7 + 1;
        goto L13
    L17:
        if (this.dispalyedDates.get(r7).intValue() != this.defaultDates[0]) goto L20;
        r62[r7] = LocaleController.formatPluralString("Hours", 1, new Object[0]);
        goto L32
    L20:
        if (this.dispalyedDates.get(r7).intValue() != this.defaultDates[1]) goto L23;
        r62[r7] = LocaleController.formatPluralString("Days", 1, new Object[0]);
        goto L32
    L23:
        if (this.dispalyedDates.get(r7).intValue() != this.defaultDates[2]) goto L25;
        r62[r7] = LocaleController.formatPluralString("Weeks", 1, new Object[0]);
        goto L32
    L25:
        long r9 = r142;
        if (r9 >= 86400) goto L29;
        r62[r7] = LocaleController.getString(R.string.MessageScheduleToday);
        goto L32
    L29:
        if (r9 >= 31449600) goto L31;
        r62[r7] = LocaleController.getInstance().getFormatterScheduleDay().format(1000 * r1);
        goto L32
    L31:
        r62[r7] = LocaleController.getInstance().getFormatterYear().format(1000 * r1);
        goto L32
    L33:
        this.timeChooseView.setOptions(r5, r62);
    }

    private void chooseUses(int r7) {
        this.dispalyedUses.clear();
        int r0 = 0;
        int r1 = 0;
        boolean r2 = false;
        int r3 = 0;
    L3:
        int[] r4 = this.defaultUses;
        if (r1 >= r4.length) goto L12;
        if (r2 == true) goto L11;
        int r42 = r4[r1];
        if (r7 > r42) goto L11;
        if (r7 == r42) goto L10;
        this.dispalyedUses.add(Integer.valueOf(r7));
    L10:
        r2 = true;
        r3 = r1;
    L11:
        this.dispalyedUses.add(Integer.valueOf(this.defaultUses[r1]));
        r1 = r1 + 1;
        goto L3
    L12:
        if (r2 == true) goto L14;
        this.dispalyedUses.add(Integer.valueOf(r7));
        r3 = this.defaultUses.length;
    L14:
        int r72 = this.dispalyedUses.size();
        int r12 = r72 + 1;
        String[] r22 = new String[r12];
    L15:
        if (r0 >= r12) goto L20;
        if (r0 != r72) goto L18;
        r22[r0] = LocaleController.getString(R.string.NoLimit);
    L19:
        r0 = r0 + 1;
        goto L15
    L18:
        r22[r0] = this.dispalyedUses.get(r0).toString();
        goto L19
    L20:
        this.usesChooseView.setOptions(r3, r22);
    }

    private /* synthetic */ void lambda$createView$0(boolean r1, int r2, int r3) {
        chooseDate(r2);
    }

    private /* synthetic */ void lambda$createView$1(Context r7, View r8) {
        AlertsCreator.createDatePickerDialog(r7, LocaleController.getString(R.string.ExpireAfter), LocaleController.getString(R.string.SetTimeLimit), -1, new LinkEditActivity$$ExternalSyntheticLambda12(this));
    }

    private /* synthetic */ void lambda$createView$10(View r3) {
        AlertDialog.Builder r32 = new AlertDialog.Builder(getParentActivity());
        r32.setMessage(LocaleController.getString(R.string.RevokeAlert));
        r32.setTitle(LocaleController.getString(R.string.RevokeLink));
        r32.setPositiveButton(LocaleController.getString(R.string.RevokeButton), new LinkEditActivity$$ExternalSyntheticLambda13(this));
        r32.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r32.create());
    }

    private /* synthetic */ void lambda$createView$2(int r3) {
        if (r3 >= this.dispalyedDates.size()) goto L6;
        this.timeEditText.setText(LocaleController.formatDateAudio(this.dispalyedDates.get(r3).intValue() + getConnectionsManager().getCurrentTime(), false));
        return;
    L6:
        this.timeEditText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    private /* synthetic */ void lambda$createView$3(int r3) {
        this.usesEditText.clearFocus();
        this.ignoreSet = true;
        int r0 = this.dispalyedUses.size();
        EditText r1 = this.usesEditText;
        if (r3 >= r0) goto L5;
        r1.setText(this.dispalyedUses.get(r3).toString());
    L6:
        this.ignoreSet = false;
        return;
    L5:
        r1.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L6
    }

    private /* synthetic */ void lambda$createView$4(boolean r2, View r3) {
        if (r2 == true) goto L22;
        TextCheckCell r22 = this.subCell;
        if (r22 != null) goto L7;
    L10:
        TextCheckCell r32 = (TextCheckCell) r3;
        boolean r23 = r32.isChecked();
        r32.setChecked(!r23);
        setUsesVisible(r23);
        this.firstLayout = true;
        if (this.subCell != null) goto L13;
        return;
    L13:
        if (r32.isChecked() == false) goto L17;
        this.subCell.setChecked(false);
        this.subCell.setCheckBoxIcon(R.drawable.permission_locked);
        this.subEditPriceCell.setVisibility(8);
        return;
    L17:
        if (this.inviteToEdit != null) goto L21;
        this.subCell.setCheckBoxIcon(0);
        return;
    L21:
        return;
    L7:
        if (r22.isChecked() == false) goto L10;
        TextCheckCell r24 = this.subCell;
        int r33 = -this.shakeDp;
        this.shakeDp = r33;
        AndroidUtilities.shakeViewSpring(r24, r33);
        return;
    }

    private /* synthetic */ void lambda$createView$5() {
        this.subEditPriceCell.editText.requestFocus();
        AndroidUtilities.showKeyboard(this.subEditPriceCell.editText);
    }

    private /* synthetic */ void lambda$createView$6() {
        this.subEditPriceCell.editText.clearFocus();
        AndroidUtilities.hideKeyboard(this.subEditPriceCell.editText);
    }

    private /* synthetic */ void lambda$createView$7(Runnable[] r4, View r5) {
        if (this.inviteToEdit == null) goto L6;
        return;
    L6:
        if (this.approveCell.isChecked() == false) goto L9;
        TextCheckCell r42 = this.approveCell;
        int r52 = -this.shakeDp;
        this.shakeDp = r52;
        AndroidUtilities.shakeViewSpring(r42, r52);
        return;
    L9:
        TextCheckCell r53 = (TextCheckCell) r5;
        r53.setChecked(!r53.isChecked());
        EditTextCell r0 = this.subEditPriceCell;
        if (r53.isChecked() == false) goto L12;
        int r1 = 0;
    L13:
        r0.setVisibility(r1);
        AndroidUtilities.cancelRunOnUIThread(r4[0]);
        boolean r54 = r53.isChecked();
        TextCheckCell r02 = this.approveCell;
        if (r54 == false) goto L17;
        r02.setChecked(false);
        this.approveCell.setCheckBoxIcon(R.drawable.permission_locked);
        this.approveHintCell.setText(LocaleController.getString(R.string.ApproveNewMembersDescriptionFrozen));
        LinkEditActivity$$ExternalSyntheticLambda10 r55 = new LinkEditActivity$$ExternalSyntheticLambda10(this);
        r4[0] = r55;
        AndroidUtilities.runOnUIThread(r55, 60);
        return;
    L17:
        r02.setCheckBoxIcon(0);
        this.approveHintCell.setText(LocaleController.getString(R.string.ApproveNewMembersDescription2));
        LinkEditActivity$$ExternalSyntheticLambda11 r56 = new LinkEditActivity$$ExternalSyntheticLambda11(this);
        r4[0] = r56;
        AndroidUtilities.runOnUIThread(r56);
        return;
    L12:
        r1 = 8;
        goto L13
    }

    private /* synthetic */ void lambda$createView$8() {
        Browser.openUrl(getContext(), LocaleController.getString(R.string.RequireMonthlyFeeInfoLink));
    }

    private /* synthetic */ void lambda$createView$9(AlertDialog r1, int r2) {
        this.callback.revokeLink(this.inviteToEdit);
        finishFragment();
    }

    private /* synthetic */ void lambda$getThemeDescriptions$16() {
        TextInfoPrivacyCell r0 = this.dividerUses;
        if (r0 == null) goto L9;
        r0.getContext();
        EditText r02 = this.usesEditText;
        int r1 = Theme.key_windowBackgroundWhiteBlackText;
        r02.setTextColor(Theme.getColor(r1));
        EditText r03 = this.usesEditText;
        int r2 = Theme.key_windowBackgroundWhiteGrayText;
        r03.setHintTextColor(Theme.getColor(r2));
        this.timeEditText.setTextColor(Theme.getColor(r1));
        this.timeEditText.setHintTextColor(Theme.getColor(r2));
        TextSettingsCell r04 = this.revokeLink;
        if (r04 == null) goto L7;
        r04.setTextColor(Theme.getColor(Theme.key_text_RedRegular));
    L7:
        this.createTextView.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        this.nameEditText.setTextColor(Theme.getColor(r1));
        this.nameEditText.setHintTextColor(Theme.getColor(r2));
        return;
    }

    private /* synthetic */ void lambda$onCreateClicked$12(TLRPC.TL_error r2, TLObject r3) {
        this.loading = false;
        AlertDialog r0 = this.progressDialog;
        if (r0 == null) goto L5;
        r0.dismiss();
    L5:
        if (r2 != null) goto L11;
        Callback r22 = this.callback;
        if (r22 == null) goto L9;
        r22.onLinkCreated(r3);
    L9:
        finishFragment();
        return;
    L11:
        AlertsCreator.showSimpleAlert(this, r2.text);
    }

    private /* synthetic */ void lambda$onCreateClicked$13(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new LinkEditActivity$$ExternalSyntheticLambda16(this, r3, r2));
    }

    private /* synthetic */ void lambda$onCreateClicked$14(TLRPC.TL_error r2, TLObject r3) {
        this.loading = false;
        AlertDialog r0 = this.progressDialog;
        if (r0 == null) goto L5;
        r0.dismiss();
    L5:
        if (r2 == null) goto L7;
        AlertsCreator.showSimpleAlert(this, r2.text);
        return;
    L7:
        if ((r3 instanceof TLRPC.TL_messages_exportedChatInvite) == false) goto L9;
        this.inviteToEdit = (TLRPC.TL_chatInviteExported) ((TLRPC.TL_messages_exportedChatInvite) r3).invite;
    L9:
        Callback r22 = this.callback;
        if (r22 == null) goto L12;
        r22.onLinkEdited(this.inviteToEdit, r3);
    L12:
        finishFragment();
    }

    private /* synthetic */ void lambda$onCreateClicked$15(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new LinkEditActivity$$ExternalSyntheticLambda17(this, r3, r2));
    }

    private void onCreateClicked(View r11) {
        if (this.loading == true) goto L104;
        int r112 = this.timeChooseView.getSelectedIndex();
        if (r112 < this.dispalyedDates.size()) goto L8;
    L11:
        TextCheckCell r113 = this.subCell;
        if (r113 != null) goto L14;
    L19:
        long r2 = 0;
    L20:
        int r114 = this.type;
        boolean r8 = true;
        if (r114 != 0) goto L53;
        AlertDialog r115 = this.progressDialog;
        if (r115 == null) goto L25;
        r115.dismiss();
    L25:
        this.loading = true;
        AlertDialog r116 = new AlertDialog(getParentActivity(), 3);
        this.progressDialog = r116;
        r116.showDelayed(500);
        TLRPC.TL_messages_exportChatInvite r117 = new TLRPC.TL_messages_exportChatInvite();
        r117.peer = getMessagesController().getInputPeer(-this.chatId);
        r117.legacy_revoke_permanent = false;
        int r4 = this.timeChooseView.getSelectedIndex();
        r117.flags |= 1;
        if (r4 >= this.dispalyedDates.size()) goto L28;
        r117.expire_date = this.dispalyedDates.get(r4).intValue() + getConnectionsManager().getCurrentTime();
    L29:
        int r42 = this.usesChooseView.getSelectedIndex();
        r117.flags |= 2;
        if (r42 >= this.dispalyedUses.size()) goto L32;
        r117.usage_limit = this.dispalyedUses.get(r42).intValue();
    L33:
        TextCheckCell r43 = this.approveCell;
        if (r43 != null) goto L36;
    L38:
        r8 = false;
    L39:
        r117.request_needed = r8;
        if (r8 == false) goto L42;
        r117.usage_limit = 0;
    L42:
        String r44 = this.nameEditText.getText().toString();
        r117.title = r44;
        if (TextUtils.isEmpty(r44) == true) goto L46;
        r117.flags |= 16;
    L46:
        if (r2 <= 0) goto L52;
        r117.flags |= 32;
        TL_stars.TL_starsSubscriptionPricing r0 = new TL_stars.TL_starsSubscriptionPricing();
        r117.subscription_pricing = r0;
        if (getConnectionsManager().isTestBackend() == false) goto L50;
        int r1 = 300;
    L51:
        r0.period = r1;
        r117.subscription_pricing.amount = r2;
        goto L52
    L50:
        r1 = 2592000;
    L52:
        getConnectionsManager().sendRequest(r117, new LinkEditActivity$$ExternalSyntheticLambda14(this));
        return;
    L36:
        if (r43.isChecked() == false) goto L38;
    L32:
        r117.usage_limit = 0;
        goto L33
    L28:
        r117.expire_date = 0;
        goto L29
    L53:
        if (r114 != 1) goto L102;
        AlertDialog r118 = this.progressDialog;
        if (r118 == null) goto L57;
        r118.dismiss();
    L57:
        TLRPC.TL_messages_editExportedChatInvite r119 = new TLRPC.TL_messages_editExportedChatInvite();
        r119.link = this.inviteToEdit.link;
        r119.revoked = false;
        r119.peer = getMessagesController().getInputPeer(-this.chatId);
        int r02 = this.timeChooseView.getSelectedIndex();
        int r12 = this.dispalyedDates.size();
        int r22 = this.currentInviteDate;
        if (r02 < r12) goto L60;
        if (r22 == 0) goto L65;
        r119.flags |= 1;
        r119.expire_date = 0;
    L62:
        boolean r03 = true;
    L66:
        int r13 = this.usesChooseView.getSelectedIndex();
        if (r13 >= this.dispalyedUses.size()) goto L72;
        int r14 = this.dispalyedUses.get(r13).intValue();
        if (this.inviteToEdit.usage_limit == r14) goto L75;
        r119.flags |= 2;
        r119.usage_limit = r14;
    L74:
        r03 = true;
    L75:
        boolean r15 = this.inviteToEdit.request_needed;
        TextCheckCell r23 = this.approveCell;
        if (r23 != null) goto L78;
    L80:
        boolean r24 = false;
    L81:
        if (r15 == r24) goto L92;
        r119.flags |= 8;
        TextCheckCell r04 = this.approveCell;
        if (r04 != null) goto L85;
    L87:
        boolean r05 = false;
    L88:
        r119.request_needed = r05;
        if (r05 == false) goto L91;
        r119.flags |= 2;
        r119.usage_limit = 0;
    L91:
        r03 = true;
        goto L92
    L85:
        if (r04.isChecked() == false) goto L87;
        r05 = true;
    L92:
        String r16 = this.nameEditText.getText().toString();
        if (TextUtils.equals(this.inviteToEdit.title, r16) == true) goto L95;
        r119.title = r16;
        r119.flags |= 16;
        r03 = true;
    L95:
        if (r03 == false) goto L97;
        this.loading = true;
        AlertDialog r06 = new AlertDialog(getParentActivity(), 3);
        this.progressDialog = r06;
        r06.showDelayed(500);
        getConnectionsManager().sendRequest(r119, new LinkEditActivity$$ExternalSyntheticLambda15(this));
        return;
    L97:
        finishFragment();
        return;
    L78:
        if (r23.isChecked() == false) goto L80;
        r24 = true;
        goto L81
    L72:
        if (this.inviteToEdit.usage_limit == 0) goto L75;
        r119.flags |= 2;
        r119.usage_limit = 0;
    L65:
        r03 = false;
        goto L66
    L60:
        if (r22 == this.dispalyedDates.get(r02).intValue()) goto L65;
        r119.flags |= 1;
        r119.expire_date = this.dispalyedDates.get(r02).intValue() + getConnectionsManager().getCurrentTime();
        goto L62
    L102:
        return;
    L14:
        if (r113.isChecked() == false) goto L19;
        r2 = Long.parseLong(this.subEditPriceCell.editText.getText().toString());     // Catch: Exception -> L17
    L17:
        e = move-exception;
        FileLog.e(e);
        goto L19
    L8:
        if (this.dispalyedDates.get(r112).intValue() >= 0) goto L11;
        AndroidUtilities.shakeView(this.timeEditText);
        VibratorUtils.vibrate();
        return;
    }

    private void resetDates() {
        this.dispalyedDates.clear();
        int r1 = 0;
    L3:
        int[] r2 = this.defaultDates;
        if (r1 >= r2.length) goto L6;
        this.dispalyedDates.add(Integer.valueOf(r2[r1]));
        r1 = r1 + 1;
        goto L3
    L6:
        this.timeChooseView.setOptions(3, new String[]{LocaleController.formatPluralString("Hours", 1, new Object[0]), LocaleController.formatPluralString("Days", 1, new Object[0]), LocaleController.formatPluralString("Weeks", 1, new Object[0]), LocaleController.getString(R.string.NoLimit)});
    }

    private void resetUses() {
        this.dispalyedUses.clear();
        int r0 = 0;
    L3:
        int[] r1 = this.defaultUses;
        if (r0 >= r1.length) goto L6;
        this.dispalyedUses.add(Integer.valueOf(r1[r0]));
        r0 = r0 + 1;
        goto L3
    L6:
        String[] r02 = {"1", "10", "100", LocaleController.getString(R.string.NoLimit)};
        this.usesChooseView.setOptions(3, r02);
    }

    private void setUsesVisible(boolean r5) {
        HeaderCell r0 = this.usesHeaderCell;
        int r1 = 8;
        if (r5 == false) goto L5;
        int r3 = 0;
    L6:
        r0.setVisibility(r3);
        SlideChooseView r02 = this.usesChooseView;
        if (r5 == false) goto L9;
        int r32 = 0;
    L10:
        r02.setVisibility(r32);
        EditText r03 = this.usesEditText;
        if (r5 == false) goto L13;
        int r33 = 0;
    L14:
        r03.setVisibility(r33);
        TextInfoPrivacyCell r4 = this.dividerUses;
        if (r5 == false) goto L17;
        r1 = 0;
    L17:
        r4.setVisibility(r1);
        return;
    L13:
        r33 = 8;
        goto L14
    L9:
        r32 = 8;
        goto L10
    L5:
        r3 = 8;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r29) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        int r0 = this.type;
        if (r0 != 0) goto L5;
        this.actionBar.setTitle(LocaleController.getString(R.string.NewLink));
    L7:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        TextView r02 = new TextView(r29);
        this.createTextView = r02;
        r02.setBackground(new AnonymousClass2(this));
        this.createTextView.setEllipsize(TextUtils.TruncateAt.END);
        this.createTextView.setGravity(17);
        this.createTextView.setOnClickListener(new LinkEditActivity$$ExternalSyntheticLambda1(this));
        this.createTextView.setSingleLine();
        int r03 = this.type;
        if (r03 != 0) goto L10;
        this.createTextView.setText(LocaleController.getString(R.string.CreateLinkHeaderNoCaps));
    L12:
        this.createTextView.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        this.createTextView.setTextSize(1, 14.0f);
        this.createTextView.setTypeface(AndroidUtilities.bold());
        this.createTextView.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
        ScaleStateListAnimator.apply(this.createTextView);
        this.actionBar.addView(this.createTextView, LayoutHelper.createFrame(-2, ActionBar.getCurrentActionBarHeight() / AndroidUtilities.density, 8388693, 0.0f, 0.0f, 12.0f, 0.0f));
        AnonymousClass3 r10 = new AnonymousClass3(this, r29);
        SectionsScrollView r04 = new SectionsScrollView(r29, r10, this.resourceProvider);
        this.scrollView = r04;
        this.actionBar.setAdaptiveBackground(r04);
        AnonymousClass4 r11 = new AnonymousClass4(this, r29);
        this.fragmentView = r11;
        LayoutTransition r05 = new LayoutTransition();
        r05.setDuration(420);
        CubicBezierInterpolator r4 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r05.setInterpolator(2, r4);
        r05.setInterpolator(0, r4);
        r05.setInterpolator(4, r4);
        r05.setInterpolator(1, r4);
        r05.setInterpolator(3, r4);
        r10.setLayoutTransition(r05);
        r10.setOrientation(1);
        r10.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(91.0f));
        this.scrollView.addView(r10);
        HeaderCell r06 = new HeaderCell(r29);
        this.timeHeaderCell = r06;
        r06.setText(LocaleController.getString(R.string.LimitByPeriod));
        r10.addView(this.timeHeaderCell);
        SlideChooseView r07 = new SlideChooseView(r29);
        this.timeChooseView = r07;
        r10.addView(r07);
        TextView r08 = new TextView(r29);
        this.timeEditText = r08;
        r08.setPadding(AndroidUtilities.dp(22.0f), 0, AndroidUtilities.dp(22.0f), 0);
        this.timeEditText.setGravity(16);
        this.timeEditText.setTextSize(1, 16.0f);
        this.timeEditText.setHint(LocaleController.getString(R.string.TimeLimitHint));
        this.timeEditText.setOnClickListener(new LinkEditActivity$$ExternalSyntheticLambda2(this, r29));
        this.timeChooseView.setCallback(new LinkEditActivity$$ExternalSyntheticLambda3(this));
        resetDates();
        r10.addView(this.timeEditText, LayoutHelper.createLinear(-1, 50));
        TextInfoPrivacyCell r09 = new TextInfoPrivacyCell(r29, 12, this.resourceProvider);
        this.divider = r09;
        r09.setText(LocaleController.getString(R.string.TimeLimitHelp));
        r10.addView(this.divider);
        HeaderCell r010 = new HeaderCell(r29);
        this.usesHeaderCell = r010;
        r010.setText(LocaleController.getString(R.string.LimitNumberOfUses));
        r10.addView(this.usesHeaderCell);
        SlideChooseView r011 = new SlideChooseView(r29);
        this.usesChooseView = r011;
        r011.setCallback(new LinkEditActivity$$ExternalSyntheticLambda4(this));
        resetUses();
        r10.addView(this.usesChooseView);
        AnonymousClass5 r012 = new AnonymousClass5(this, r29);
        this.usesEditText = r012;
        r012.setPadding(AndroidUtilities.dp(22.0f), 0, AndroidUtilities.dp(22.0f), 0);
        this.usesEditText.setGravity(16);
        this.usesEditText.setTextSize(1, 16.0f);
        this.usesEditText.setHint(LocaleController.getString(R.string.UsesLimitHint));
        this.usesEditText.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
        this.usesEditText.setInputType(2);
        this.usesEditText.addTextChangedListener(new AnonymousClass6(this));
        r10.addView(this.usesEditText, LayoutHelper.createLinear(-1, 50));
        TextInfoPrivacyCell r013 = new TextInfoPrivacyCell(r29, 12, this.resourceProvider);
        this.dividerUses = r013;
        r013.setText(LocaleController.getString(R.string.UsesLimitHelp));
        r10.addView(this.dividerUses);
        TLRPC.Chat r014 = getMessagesController().getChat(Long.valueOf(this.chatId));
        if (ChatObject.isPublic(r014) == true) goto L15;
    L19:
        boolean r42 = false;
    L20:
        AnonymousClass7 r5 = new AnonymousClass7(this, r29);
        this.approveCell = r5;
        int r13 = Theme.key_windowBackgroundWhite;
        r5.setBackgroundColor(Theme.getColor(r13));
        this.approveCell.setTag(Integer.valueOf(r13));
        this.approveCell.setTextAndCheck(LocaleController.getString(R.string.ApproveNewMembers2), false, false);
        this.approveCell.setOnClickListener(new LinkEditActivity$$ExternalSyntheticLambda5(this, r42));
        r10.addView(this.approveCell, LayoutHelper.createLinear(-1, 56));
        TextInfoPrivacyCell r52 = new TextInfoPrivacyCell(r29, 12, this.resourceProvider);
        this.approveHintCell = r52;
        if (r42 == false) goto L23;
        this.approveCell.setCheckBoxIcon(R.drawable.permission_locked);
        this.approveHintCell.setText(LocaleController.getString(R.string.ApproveNewMembersUnavailablePublicGroup));
    L24:
        r10.addView(this.approveHintCell);
        if (r014 != null) goto L27;
    L31:
        TLRPC.ChatFull r015 = MessagesController.getInstance(this.currentAccount).getChatFull(this.chatId);
        if (this.inviteToEdit == null) goto L34;
    L38:
        TLRPC.TL_chatInviteExported r016 = this.inviteToEdit;
        if (r016 != null) goto L41;
    L54:
        int r9 = -1;
        int r8 = 12;
    L30:
        int r15 = -2;
        float r17 = -1.0f;
    L55:
        AnonymousClass9 r017 = new AnonymousClass9(this, r29);
        this.nameEditText = r017;
        r017.addTextChangedListener(new AnonymousClass10(this));
        this.nameEditText.setCursorVisible(false);
        this.nameEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(32)});
        this.nameEditText.setGravity(16);
        this.nameEditText.setHint(LocaleController.getString(R.string.LinkNameHint));
        EditText r018 = this.nameEditText;
        int r3 = Theme.key_windowBackgroundWhiteGrayText;
        r018.setHintTextColor(Theme.getColor(r3));
        this.nameEditText.setLines(1);
        this.nameEditText.setPadding(AndroidUtilities.dp(22.0f), 0, AndroidUtilities.dp(22.0f), 0);
        this.nameEditText.setSingleLine();
        EditText r019 = this.nameEditText;
        int r43 = Theme.key_windowBackgroundWhiteBlackText;
        r019.setTextColor(Theme.getColor(r43));
        this.nameEditText.setTextSize(1, 16.0f);
        r10.addView(this.nameEditText, LayoutHelper.createLinear(r9, 50));
        TextInfoPrivacyCell r020 = new TextInfoPrivacyCell(r29, r8, this.resourceProvider);
        this.dividerName = r020;
        r020.setText(LocaleController.getString(R.string.LinkNameHelp));
        r10.addView(this.dividerName);
        if (this.type != 1) goto L58;
        TextSettingsCell r021 = new TextSettingsCell(r29);
        this.revokeLink = r021;
        r021.setBackgroundColor(Theme.getColor(r13));
        this.revokeLink.setText(LocaleController.getString(R.string.RevokeLink), false);
        this.revokeLink.setTextColor(Theme.getColor(Theme.key_text_RedRegular));
        this.revokeLink.setOnClickListener(new LinkEditActivity$$ExternalSyntheticLambda8(this));
        r10.addView(this.revokeLink);
    L58:
        r11.addView(this.scrollView, LayoutHelper.createFrame(r9, r17));
        FrameLayout r022 = new FrameLayout(r29);
        this.buttonLayout = r022;
        int r2 = Theme.key_windowBackgroundGray;
        r022.setBackgroundColor(getThemedColor(r2));
        new KeyboardNotifier(r11, new LinkEditActivity$$ExternalSyntheticLambda9());
        r11.addView(this.buttonLayout, LayoutHelper.createFrame(r9, r15, 80));
        this.timeHeaderCell.setBackgroundColor(Theme.getColor(r13));
        this.timeChooseView.setBackgroundColor(Theme.getColor(r13));
        this.timeEditText.setBackgroundColor(Theme.getColor(r13));
        this.usesHeaderCell.setBackgroundColor(Theme.getColor(r13));
        this.usesChooseView.setBackgroundColor(Theme.getColor(r13));
        this.usesEditText.setBackgroundColor(Theme.getColor(r13));
        this.nameEditText.setBackgroundColor(Theme.getColor(r13));
        r11.setBackgroundColor(Theme.getColor(r2));
        this.usesEditText.setTextColor(Theme.getColor(r43));
        this.usesEditText.setHintTextColor(Theme.getColor(r3));
        this.timeEditText.setTextColor(Theme.getColor(r43));
        this.timeEditText.setHintTextColor(Theme.getColor(r3));
        this.usesEditText.setCursorVisible(false);
        setInviteToEdit(this.inviteToEdit);
        r11.setClipChildren(false);
        this.scrollView.setClipChildren(false);
        r10.setClipChildren(false);
        return r11;
    L41:
        if (r016.subscription_pricing == null) goto L54;
    L42:
        TextCheckCell r023 = new TextCheckCell(r29);
        this.subCell = r023;
        r023.setBackgroundColor(Theme.getColor(r13));
        this.subCell.setDrawCheckRipple(true);
        this.subCell.setTextAndCheck(LocaleController.getString(R.string.RequireMonthlyFee), false, true);
        if (this.inviteToEdit == null) goto L45;
        this.subCell.setCheckBoxIcon(R.drawable.permission_locked);
        this.subCell.setEnabled(false);
    L45:
        this.subCell.setOnClickListener(new LinkEditActivity$$ExternalSyntheticLambda6(this, new Runnable[1]));
        r10.addView(this.subCell, LayoutHelper.createLinear(-1, 48));
        TextView r024 = new TextView(r29);
        this.subPriceView = r024;
        r024.setTextSize(1, 16.0f);
        this.subPriceView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3));
        if (getConnectionsManager().isTestBackend() == false) goto L48;
        int r6 = R.string.RequireMonthlyFeePriceHintTest5Minutes;
    L49:
        r9 = -1;
        r8 = 12;
        r15 = -2;
        r17 = -1.0f;
        AnonymousClass8 r025 = new AnonymousClass8(this, r29, LocaleController.getString(r6), false, false, -1, this.resourceProvider);
        this.subEditPriceCell = r025;
        r025.editText.setInputType(2);
        this.subEditPriceCell.editText.setRawInputType(2);
        this.subEditPriceCell.setBackgroundColor(getThemedColor(r13));
        this.subEditPriceCell.hideKeyboardOnEnter();
        this.subEditPriceCell.addView(this.subPriceView, LayoutHelper.createFrame(-2, -2.0f, 21, 0.0f, 0.0f, 19.0f, 0.0f));
        ImageView r026 = this.subEditPriceCell.setLeftDrawable(getContext().getResources().getDrawable(R.drawable.star_small_inner).mutate());
        r026.setScaleX(0.83f);
        r026.setScaleY(0.83f);
        r026.setTranslationY(AndroidUtilities.dp(-1.0f));
        r026.setTranslationX(AndroidUtilities.dp(1.0f));
        r10.addView(this.subEditPriceCell, LayoutHelper.createLinear(-1, 48));
        this.subEditPriceCell.setVisibility(8);
        TextInfoPrivacyCell r027 = new TextInfoPrivacyCell(r29, 12, this.resourceProvider);
        this.subInfoCell = r027;
        if (this.inviteToEdit == null) goto L52;
        r027.setText(LocaleController.getString(R.string.RequireMonthlyFeeInfoFrozen));
    L53:
        r10.addView(this.subInfoCell, LayoutHelper.createLinear(-1, -2));
        goto L55
    L52:
        r027.setText(AndroidUtilities.withLearnMore(LocaleController.getString(R.string.RequireMonthlyFeeInfo), new LinkEditActivity$$ExternalSyntheticLambda7(this)));
        goto L53
    L48:
        r6 = R.string.RequireMonthlyFeePriceHint;
        goto L49
    L34:
        if (ChatObject.isChannelAndNotMegaGroup(MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.chatId))) == false) goto L38;
        if (r015 == null) goto L38;
        if (r015.paid_media_allowed == true) goto L42;
    L27:
        if (r014.username == null) goto L31;
        r9 = -1;
        r8 = 12;
        goto L30
    L23:
        r52.setText(LocaleController.getString(R.string.ApproveNewMembersDescription2));
        goto L24
    L15:
        if (r014.join_request == true) goto L19;
        if (r014.join_to_send == true) goto L19;
        r42 = true;
        goto L20
    L10:
        if (r03 != 1) goto L12;
        this.createTextView.setText(LocaleController.getString(R.string.SaveLinkHeaderNoCaps));
        goto L12
    L5:
        if (r0 != 1) goto L7;
        this.actionBar.setTitle(LocaleController.getString(R.string.EditLink));
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void finishFragment() {
        ViewGroup.LayoutParams r0 = this.scrollView.getLayoutParams();
        r0.height = this.scrollView.getHeight();
        this.finished = true;
        super.finishFragment();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        LinkEditActivity$$ExternalSyntheticLambda0 r6 = new LinkEditActivity$$ExternalSyntheticLambda0(this);
        ArrayList<ThemeDescription> r8 = new ArrayList();
        int r17 = Theme.key_windowBackgroundWhiteBlueHeader;
        r8.add(new ThemeDescription(this.timeHeaderCell, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, r17));
        r8.add(new ThemeDescription(this.usesHeaderCell, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, r17));
        HeaderCell r1 = this.timeHeaderCell;
        int r172 = ThemeDescription.FLAG_BACKGROUND;
        int r25 = Theme.key_windowBackgroundWhite;
        r8.add(new ThemeDescription(r1, r172, null, null, null, null, r25));
        r8.add(new ThemeDescription(this.usesHeaderCell, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r25));
        r8.add(new ThemeDescription(this.timeChooseView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r25));
        r8.add(new ThemeDescription(this.usesChooseView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r25));
        r8.add(new ThemeDescription(this.timeEditText, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r25));
        r8.add(new ThemeDescription(this.usesEditText, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r25));
        r8.add(new ThemeDescription(this.revokeLink, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r25));
        int r22 = Theme.key_windowBackgroundWhiteGrayText4;
        r8.add(new ThemeDescription(this.divider, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, r22));
        r8.add(new ThemeDescription(this.dividerUses, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, r22));
        r8.add(new ThemeDescription(this.dividerName, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, r22));
        r8.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r8.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_actionBarDefault));
        r8.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r8.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r8.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r8.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_featuredStickers_addButton));
        r8.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_featuredStickers_addButtonPressed));
        r8.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_windowBackgroundWhiteBlackText));
        r8.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_windowBackgroundWhiteGrayText));
        r8.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_featuredStickers_buttonText));
        r8.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_text_RedRegular));
        return r8;
    }

    public void setCallback(Callback r1) {
        this.callback = r1;
    }

    public void setInviteToEdit(TLRPC.TL_chatInviteExported r5) {
        this.inviteToEdit = r5;
        if (this.fragmentView == null) goto L41;
        if (r5 == null) goto L42;
        int r0 = r5.expire_date;
        if (r0 <= 0) goto L8;
        chooseDate(r0);
        this.currentInviteDate = this.dispalyedDates.get(this.timeChooseView.getSelectedIndex()).intValue();
    L9:
        int r02 = r5.usage_limit;
        if (r02 <= 0) goto L12;
        chooseUses(r02);
        this.usesEditText.setText(Integer.toString(r5.usage_limit));
    L12:
        TextCheckCell r03 = this.approveCell;
        if (r03 == null) goto L15;
        r03.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.approveCell.setChecked(r5.request_needed);
    L15:
        boolean r2 = true;
        setUsesVisible(!r5.request_needed);
        if (TextUtils.isEmpty(r5.title) == true) goto L18;
        SpannableStringBuilder r04 = new SpannableStringBuilder(r5.title);
        Emoji.replaceEmoji(r04, this.nameEditText.getPaint().getFontMetricsInt(), false);
        this.nameEditText.setText(r04);
    L18:
        TextCheckCell r05 = this.subCell;
        if (r05 == null) goto L26;
        if (r5.subscription_pricing != null) goto L24;
        r2 = false;
    L24:
        r05.setChecked(r2);
    L26:
        if (r5.subscription_pricing == null) goto L33;
        TextCheckCell r06 = this.approveCell;
        if (r06 == null) goto L30;
        r06.setChecked(false);
        this.approveCell.setCheckBoxIcon(R.drawable.permission_locked);
    L30:
        TextInfoPrivacyCell r07 = this.approveHintCell;
        if (r07 == null) goto L33;
        r07.setText(LocaleController.getString(R.string.ApproveNewMembersDescriptionFrozen));
    L33:
        EditTextCell r08 = this.subEditPriceCell;
        if (r08 != null) goto L36;
        return;
    L36:
        if (r5.subscription_pricing == null) goto L38;
        int r22 = 0;
    L39:
        r08.setVisibility(r22);
        this.subEditPriceCell.setText(Long.toString(r5.subscription_pricing.amount));
        this.subEditPriceCell.editText.setClickable(false);
        this.subEditPriceCell.editText.setFocusable(false);
        this.subEditPriceCell.editText.setFocusableInTouchMode(false);
        this.subEditPriceCell.editText.setLongClickable(false);
        return;
    L38:
        r22 = 8;
        goto L39
    L8:
        this.currentInviteDate = 0;
        goto L9
    L42:
        return;
    }
}
