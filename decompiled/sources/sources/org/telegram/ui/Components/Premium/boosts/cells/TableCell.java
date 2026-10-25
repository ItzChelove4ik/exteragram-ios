package org.telegram.ui.Components.Premium.boosts.cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import java.util.Date;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class TableCell extends FrameLayout {
    private final TextView dateNameTextView;
    private final TextView dateTextView;
    private FrameLayout fromFrameLayout;
    private final BackupImageView fromImageView;
    private final TextView fromNameTextView;
    private final TextView fromTextView;
    private TLRPC.TL_payments_checkedGiftCode giftCode;
    private final TextView giftNameTextView;
    private final TextView giftTextView;
    private final Paint linePaint;
    private final TextView reasonNameTextView;
    private final TextView reasonTextView;
    private final Theme.ResourcesProvider resourcesProvider;
    private final Path roundPath;
    private final RectF roundRect;
    private TableRow tableRow4;
    private FrameLayout toFrameLayout;
    private final BackupImageView toImageView;
    private final TextView toNameTextView;
    private final TextView toTextView;

    /* renamed from: org.telegram.ui.Components.Premium.boosts.cells.TableCell$1, reason: invalid class name */
    public class AnonymousClass1 extends TableLayout {
        final /* synthetic */ TableCell this$0;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass1(TableCell r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            this.val$resourcesProvider = r3;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r17) {
            TableCell.m12170$$Nest$fgetroundRect(this.this$0).set(0.0f, 0.0f, getWidth(), getHeight());
            TableCell.m12169$$Nest$fgetroundPath(this.this$0).rewind();
            TableCell.m12169$$Nest$fgetroundPath(this.this$0).addRoundRect(TableCell.m12170$$Nest$fgetroundRect(this.this$0), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), Path.Direction.CW);
            super.dispatchDraw(r17);
            TableCell.m12168$$Nest$fgetlinePaint(this.this$0).setColor(ColorUtils.blendARGB(Theme.getColor(Theme.key_divider, this.val$resourcesProvider), -1, 0.1f));
            TableCell.m12168$$Nest$fgetlinePaint(this.this$0).setStrokeWidth(AndroidUtilities.dp(1.0f));
            float r1 = getHeight();
            if (TableCell.m12171$$Nest$fgettableRow4(this.this$0).getVisibility() != 0) goto L5;
            float r2 = 5.0f;
        L6:
            float r12 = r1 / r2;
            int r22 = 1;
        L8:
            if (r22 > 4) goto L10;
            float r6 = r12 * r22;
            r17.drawLine(0.0f, r6, getWidth(), r6, TableCell.m12168$$Nest$fgetlinePaint(this.this$0));
            r22 = r22 + 1;
            goto L8
        L10:
            boolean r13 = LocaleController.isRTL;
            TableCell r23 = this.this$0;
            if (r13 == false) goto L14;
            int r14 = TableCell.m12167$$Nest$fgetdateTextView(r23).getRight();
        L13:
            float r15 = r14;
            r17.drawLine(r15, 0.0f, r15, getHeight(), TableCell.m12168$$Nest$fgetlinePaint(this.this$0));
            TableCell.m12168$$Nest$fgetlinePaint(this.this$0).setStrokeWidth(AndroidUtilities.dp(2.0f));
            r17.drawPath(TableCell.m12169$$Nest$fgetroundPath(this.this$0), TableCell.m12168$$Nest$fgetlinePaint(this.this$0));
            return;
        L14:
            r14 = TableCell.m12167$$Nest$fgetdateTextView(r23).getLeft();
            goto L13
        L5:
            r2 = 4.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.cells.TableCell$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewOutlineProvider {
        final /* synthetic */ TableCell this$0;

        public AnonymousClass2(TableCell r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            r8.setRoundRect(0, 0, r7.getMeasuredWidth(), r7.getMeasuredHeight(), AndroidUtilities.dp(6.0f));
        }
    }

    /* renamed from: $r8$lambda$CpRPlXKT_fEQ514l--77FhsNQLY, reason: not valid java name */
    public static /* synthetic */ void m12164$r8$lambda$CpRPlXKT_fEQ514l77FhsNQLY(Utilities.Callback r0, TLRPC.TL_payments_checkedGiftCode r1) {
        r0.run(r1);
    }

    public static /* synthetic */ void $r8$lambda$EITu6bVmGYWk9SR8Ememutw_2TY(Utilities.Callback r0, TLRPC.Chat r1) {
        r0.run(r1);
    }

    public static /* synthetic */ void $r8$lambda$N4Arapk_g1jMSVOvHWUFGhMhj3U(Utilities.Callback r0, TLRPC.TL_payments_checkedGiftCode r1, View r2) {
        r0.run(r1);
    }

    /* renamed from: $r8$lambda$Xw36Xi0oyPoL7n82OdetOsJeQ-Q, reason: not valid java name */
    public static /* synthetic */ void m12165$r8$lambda$Xw36Xi0oyPoL7n82OdetOsJeQQ(Utilities.Callback r0, TLRPC.TL_payments_checkedGiftCode r1) {
        r0.run(r1);
    }

    /* renamed from: $r8$lambda$cPnBG3bcyTQg0i0ADJUOn9JPp-A, reason: not valid java name */
    public static /* synthetic */ void m12166$r8$lambda$cPnBG3bcyTQg0i0ADJUOn9JPpA(Utilities.Callback r0, TLRPC.User r1, View r2) {
        r0.run(r1);
    }

    public static /* synthetic */ void $r8$lambda$oCWiLvmQuQWhwE0cSyFbyB9IPoQ(Utilities.Callback r0, TLRPC.User r1, View r2) {
        r0.run(r1);
    }

    public static /* synthetic */ void $r8$lambda$qxcb9iDctZWrVpIID4RVkT_OI94(Utilities.Callback r0, TLRPC.Chat r1, View r2) {
        r0.run(r1);
    }

    public static /* synthetic */ void $r8$lambda$rIJK3wDuCa8I963h360SaKH1kc0(Utilities.Callback r0, TLRPC.User r1) {
        r0.run(r1);
    }

    /* renamed from: -$$Nest$fgetdateTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m12167$$Nest$fgetdateTextView(TableCell r0) {
        return r0.dateTextView;
    }

    /* renamed from: -$$Nest$fgetlinePaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m12168$$Nest$fgetlinePaint(TableCell r0) {
        return r0.linePaint;
    }

    /* renamed from: -$$Nest$fgetroundPath, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Path m12169$$Nest$fgetroundPath(TableCell r0) {
        return r0.roundPath;
    }

    /* renamed from: -$$Nest$fgetroundRect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m12170$$Nest$fgetroundRect(TableCell r0) {
        return r0.roundRect;
    }

    /* renamed from: -$$Nest$fgettableRow4, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TableRow m12171$$Nest$fgettableRow4(TableCell r0) {
        return r0.tableRow4;
    }

    public TableCell(Context r36, Theme.ResourcesProvider r37) {
        super(r36);
        Paint r3 = new Paint();
        this.linePaint = r3;
        this.roundPath = new Path();
        this.roundRect = new RectF();
        r3.setStyle(Paint.Style.STROKE);
        this.resourcesProvider = r37;
        TextView r32 = createTextView(LocaleController.getString(R.string.BoostingFrom), false);
        this.fromNameTextView = r32;
        TextView r5 = createTextView(LocaleController.getString(R.string.BoostingTo), false);
        this.toNameTextView = r5;
        TextView r6 = createTextView(LocaleController.getString(R.string.BoostingGift), false);
        this.giftNameTextView = r6;
        TextView r7 = createTextView(LocaleController.getString(R.string.BoostingReason), false);
        this.reasonNameTextView = r7;
        TextView r8 = createTextView(LocaleController.getString(R.string.BoostingDate), false);
        this.dateNameTextView = r8;
        TextView r10 = createTextView(true);
        this.fromTextView = r10;
        TextView r11 = createTextView(true);
        this.toTextView = r11;
        TextView r12 = createTextView(false);
        this.giftTextView = r12;
        TextView r13 = createTextView(true);
        this.reasonTextView = r13;
        TextView r14 = createTextView(false);
        this.dateTextView = r14;
        BackupImageView r15 = new BackupImageView(r36);
        this.fromImageView = r15;
        r15.setRoundRadius(AndroidUtilities.dp(12.0f));
        BackupImageView r9 = new BackupImageView(r36);
        this.toImageView = r9;
        r9.setRoundRadius(AndroidUtilities.dp(12.0f));
        TableRow r4 = new TableRow(r36);
        FrameLayout r2 = new FrameLayout(r36);
        this.fromFrameLayout = r2;
        boolean r17 = LocaleController.isRTL;
        int r18 = 3;
        if (r17 == false) goto L5;
        int r22 = 5;
    L7:
        if (r17 == false) goto L9;
        float r23 = 0.0f;
    L10:
        if (r17 == false) goto L12;
        float r25 = 12.0f;
    L13:
        r2.addView(r15, LayoutHelper.createFrame(24, 24.0f, r22, r23, 0.0f, r25, 0.0f));
        FrameLayout r24 = this.fromFrameLayout;
        boolean r82 = LocaleController.isRTL;
        if (r82 == false) goto L17;
        int r152 = 5;
    L18:
        int r30 = r152 | 16;
        if (r82 == false) goto L21;
        float r31 = 0.0f;
    L22:
        if (r82 == false) goto L24;
        float r33 = 29.0f;
    L25:
        r24.addView(r10, LayoutHelper.createFrame(-2, -2.0f, r30, r31, 0.0f, r33, 0.0f));
        if (LocaleController.isRTL == false) goto L28;
        float r102 = 1.0f;
    L29:
        TableRow.LayoutParams r26 = new TableRow.LayoutParams(-2, -2, r102);
        r26.gravity = 16;
        if (LocaleController.isRTL == false) goto L32;
        r4.addView(this.fromFrameLayout, r26);
        r4.addView(r32, new TableRow.LayoutParams(-2, -2));
    L33:
        this.fromFrameLayout.setPadding(0, AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(6.0f));
        TableRow r27 = new TableRow(r36);
        FrameLayout r34 = new FrameLayout(r36);
        this.toFrameLayout = r34;
        boolean r83 = LocaleController.isRTL;
        if (r83 == false) goto L36;
        int r302 = 5;
    L37:
        if (r83 == false) goto L39;
        float r312 = 0.0f;
    L40:
        if (r83 == false) goto L42;
        float r332 = 12.0f;
    L43:
        r34.addView(r9, LayoutHelper.createFrame(24, 24.0f, r302, r312, 0.0f, r332, 0.0f));
        FrameLayout r35 = this.toFrameLayout;
        boolean r84 = LocaleController.isRTL;
        if (r84 == false) goto L46;
        r18 = 5;
    L46:
        int r303 = r18 | 16;
        if (r84 == false) goto L49;
        float r313 = 0.0f;
    L50:
        if (r84 == false) goto L52;
        float r333 = 29.0f;
    L53:
        r35.addView(r11, LayoutHelper.createFrame(-2, -2.0f, r303, r313, 0.0f, r333, 0.0f));
        if (LocaleController.isRTL == false) goto L56;
        float r85 = 1.0f;
    L57:
        TableRow.LayoutParams r38 = new TableRow.LayoutParams(-2, -2, r85);
        r38.gravity = 16;
        if (LocaleController.isRTL == false) goto L60;
        r27.addView(this.toFrameLayout, r38);
        r27.addView(r5, new TableRow.LayoutParams(-2, -2));
    L61:
        this.toFrameLayout.setPadding(0, AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(6.0f));
        TableRow r39 = new TableRow(r36);
        if (LocaleController.isRTL == false) goto L64;
        r39.addView(r12, new TableRow.LayoutParams(-2, -2, 1.0f));
        r39.addView(r6, new TableRow.LayoutParams(-2, -2));
    L65:
        TableRow r52 = new TableRow(r36);
        this.tableRow4 = r52;
        if (LocaleController.isRTL == false) goto L68;
        r52.addView(r13, new TableRow.LayoutParams(-2, -2, 1.0f));
        this.tableRow4.addView(r7, new TableRow.LayoutParams(-2, -2));
    L69:
        TableRow r53 = new TableRow(r36);
        if (LocaleController.isRTL == false) goto L72;
        r53.addView(r14, new TableRow.LayoutParams(-2, -2, 1.0f));
        r53.addView(r8, new TableRow.LayoutParams(-2, -2));
    L73:
        AnonymousClass1 r62 = new AnonymousClass1(this, r36, r37);
        r62.addView(r4);
        r62.addView(r27);
        r62.addView(r39);
        r62.addView(this.tableRow4);
        r62.addView(r53);
        if (LocaleController.isRTL == false) goto L76;
        boolean r1 = true;
        r62.setColumnShrinkable(0, true);
    L77:
        addView(r62, LayoutHelper.createFrame(-1, -2.0f));
        r62.setOutlineProvider(new AnonymousClass2(this));
        r62.setClipToOutline(r1);
        setPaddingRelative(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(18.0f), AndroidUtilities.dp(14.0f), 0);
        return;
    L76:
        r1 = true;
        r62.setColumnShrinkable(1, true);
        goto L77
    L72:
        r53.addView(r8, new TableRow.LayoutParams(-2, -2));
        r53.addView(r14, new TableRow.LayoutParams(-2, -2));
        goto L73
    L68:
        r52.addView(r7, new TableRow.LayoutParams(-2, -2));
        this.tableRow4.addView(r13, new TableRow.LayoutParams(-2, -2));
        goto L69
    L64:
        r39.addView(r6, new TableRow.LayoutParams(-2, -2));
        r39.addView(r12, new TableRow.LayoutParams(-2, -2));
        goto L65
    L60:
        r27.addView(r5, new TableRow.LayoutParams(-2, -2));
        r27.addView(this.toFrameLayout, r38);
        goto L61
    L56:
        r85 = 0.0f;
        goto L57
    L52:
        r333 = 0.0f;
        goto L53
    L49:
        r313 = 29.0f;
        goto L50
    L42:
        r332 = 0.0f;
        goto L43
    L39:
        r312 = 12.0f;
        goto L40
    L36:
        r302 = 3;
        goto L37
    L32:
        r4.addView(r32, new TableRow.LayoutParams(-2, -2));
        r4.addView(this.fromFrameLayout, r26);
        goto L33
    L28:
        r102 = 0.0f;
        goto L29
    L24:
        r33 = 0.0f;
        goto L25
    L21:
        r31 = 29.0f;
        goto L22
    L17:
        r152 = 3;
        goto L18
    L12:
        r25 = 0.0f;
        goto L13
    L9:
        r23 = 12.0f;
        goto L10
    L5:
        r22 = 3;
        goto L7
    }

    private TextView createTextView(String r5, boolean r6) {
        if (r6 == false) goto L4;
        TextView r0 = new LinkSpanDrawable.LinksTextView(getContext(), this.resourcesProvider);
        r0.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText, this.resourcesProvider));
    L5:
        if (r6 == false) goto L7;
        int r1 = Theme.key_dialogTextBlue;
    L8:
        r0.setTextColor(Theme.getColor(r1, this.resourcesProvider));
        r0.setTextSize(1, 14.0f);
        if (r6 == false) goto L11;
    L15:
        if (r5 == null) goto L26;
        r0.setTypeface(AndroidUtilities.bold());
        r0.setText(r5);
        r0.setBackgroundColor(Theme.getColor(Theme.key_graySection, this.resourcesProvider));
        float r52 = 12.0f;
        if (LocaleController.isRTL == false) goto L19;
        float r4 = 32.0f;
    L20:
        int r42 = AndroidUtilities.dp(r4);
        int r2 = AndroidUtilities.dp(11.0f);
        if (LocaleController.isRTL == true) goto L24;
        r52 = 32.0f;
    L24:
        r0.setPadding(r42, r2, AndroidUtilities.dp(r52), AndroidUtilities.dp(11.0f));
        return r0;
    L19:
        r4 = 12.0f;
        goto L20
    L26:
        r0.setSingleLine(true);
        r0.setEllipsize(TextUtils.TruncateAt.END);
        r0.setPadding(AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(14.0f), 0);
        return r0;
    L11:
        if (LocaleController.isRTL == false) goto L13;
        int r62 = 5;
    L14:
        r0.setGravity(r62);
        goto L15
    L13:
        r62 = 3;
        goto L14
    L7:
        r1 = Theme.key_dialogTextBlack;
        goto L8
    L4:
        r0 = new TextView(getContext());
        goto L5
    }

    private TextView createTextView(boolean r2) {
        return createTextView(null, r2);
    }

    public void setData(TLRPC.TL_payments_checkedGiftCode r8, Utilities.Callback<TLObject> r9) {
        this.giftCode = r8;
        Date r0 = new Date(r8.date * 1000);
        this.dateTextView.setText(LocaleController.formatString("formatDateAtTime", R.string.formatDateAtTime, new Object[]{LocaleController.getInstance().getFormatterYear().format(r0), LocaleController.getInstance().getFormatterDay().format(r0)}));
        TextView r02 = this.reasonTextView;
        if (r8.via_giveaway == false) goto L5;
        int r1 = Theme.key_dialogTextBlue;
    L6:
        r02.setTextColor(Theme.getColor(r1, this.resourcesProvider));
        TLRPC.Chat r03 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(-DialogObject.getPeerDialogId(r8.from_id)));
        boolean r12 = ChatObject.isChannelAndNotMegaGroup(r03);
        if (r8.via_giveaway == false) goto L9;
        SpannableStringBuilder r13 = new SpannableStringBuilder();
        r13.append("**");
        r13.append(LocaleController.getString(R.string.BoostingGiveaway));
        r13.append("**");
        this.reasonTextView.setText(AndroidUtilities.replaceSingleTag(r13.toString(), Theme.key_chat_messageLinkIn, 0, new TableCell$$ExternalSyntheticLambda0(r9, r8), this.resourcesProvider));
        this.reasonTextView.setOnClickListener(new TableCell$$ExternalSyntheticLambda1(r9, r8));
    L14:
        int r14 = r8.months;
        if (r14 != 12) goto L17;
        String r15 = LocaleController.formatPluralString("Years", 1, new Object[0]);
    L18:
        this.giftTextView.setText(LocaleController.formatString("BoostingTelegramPremiumFor", R.string.BoostingTelegramPremiumFor, new Object[]{r15}));
        if (r03 == null) goto L21;
        SpannableStringBuilder r16 = new SpannableStringBuilder();
        r16.append("**");
        r16.append(r03.title);
        r16.append("**");
        SpannableStringBuilder r17 = AndroidUtilities.replaceSingleTag(r16.toString(), Theme.key_chat_messageLinkIn, 0, new TableCell$$ExternalSyntheticLambda2(r9, r03), this.resourcesProvider);
        TextView r2 = this.fromTextView;
        r2.setText(Emoji.replaceEmoji(r17, r2.getPaint().getFontMetricsInt(), false));
        this.fromImageView.setForUserOrChat(r03, new AvatarDrawable(r03));
        this.fromFrameLayout.setOnClickListener(new TableCell$$ExternalSyntheticLambda3(r9, r03));
    L23:
        if (r8.to_id == (-1)) goto L25;
    L27:
        TLRPC.User r04 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Long.valueOf(r8.to_id));
        if (r04 == null) goto L31;
        SpannableStringBuilder r22 = new SpannableStringBuilder();
        r22.append("**");
        r22.append(UserObject.getFirstName(r04));
        r22.append("**");
        SpannableStringBuilder r23 = AndroidUtilities.replaceSingleTag(r22.toString(), Theme.key_chat_messageLinkIn, 0, new TableCell$$ExternalSyntheticLambda6(r9, r04), this.resourcesProvider);
        TextView r3 = this.toTextView;
        r3.setText(Emoji.replaceEmoji(r23, r3.getPaint().getFontMetricsInt(), false));
        this.toImageView.setForUserOrChat(r04, new AvatarDrawable(r04));
        this.toFrameLayout.setOnClickListener(new TableCell$$ExternalSyntheticLambda7(r9, r04));
    L31:
        if (r8.boost == null) goto L34;
        this.tableRow4.setVisibility(8);
        return;
    L34:
        return;
    L25:
        if (r8.via_giveaway == false) goto L27;
        SpannableStringBuilder r05 = new SpannableStringBuilder();
        r05.append("**");
        r05.append(LocaleController.getString(R.string.BoostingIncompleteGiveaway));
        r05.append("**");
        this.reasonTextView.setText(AndroidUtilities.replaceSingleTag(r05.toString(), Theme.key_chat_messageLinkIn, 0, new TableCell$$ExternalSyntheticLambda5(r9, r8), this.resourcesProvider));
        this.toTextView.setText(LocaleController.getString(R.string.BoostingNoRecipient));
        this.toTextView.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, this.resourcesProvider));
        ((ViewGroup.MarginLayoutParams) this.toTextView.getLayoutParams()).leftMargin = 0;
        ((ViewGroup.MarginLayoutParams) this.toTextView.getLayoutParams()).rightMargin = 0;
        this.toImageView.setVisibility(8);
        goto L31
    L21:
        TLRPC.User r06 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Long.valueOf(r8.from_id.user_id));
        this.fromTextView.setText(Emoji.replaceEmoji(UserObject.getFirstName(r06), this.fromTextView.getPaint().getFontMetricsInt(), false));
        this.fromImageView.setForUserOrChat(r06, new AvatarDrawable(r06));
        this.fromFrameLayout.setOnClickListener(new TableCell$$ExternalSyntheticLambda4(r9, r06));
        goto L23
    L17:
        r15 = LocaleController.formatPluralString("Months", r14, new Object[0]);
        goto L18
    L9:
        TextView r24 = this.reasonTextView;
        if (r12 == false) goto L12;
        int r18 = R.string.BoostingYouWereSelected;
    L13:
        r24.setText(LocaleController.getString(r18));
        this.reasonTextView.setOnClickListener(null);
        goto L14
    L12:
        r18 = R.string.BoostingYouWereSelectedGroup;
        goto L13
    L5:
        r1 = Theme.key_dialogTextBlack;
        goto L6
    }
}
