package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessageSuggestionParams;
import org.telegram.messenger.R;
import org.telegram.messenger.utils.tlutils.AmountUtils$Amount;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class SuggestionOffer {
    public int height;
    private final Theme.ResourcesProvider resourcesProvider;
    public ArrayList<Row> rows;
    private int rowsInfoX;
    private int rowsTitleX;
    public StaticLayout title;
    private int titleX;
    public int width;

    public static class Row {
        public final Text info;
        public final Text title;

        public Row(Text r1, Text r2) {
            this.title = r1;
            this.info = r2;
        }

        public int getHeight() {
            return (int) this.title.getHeight();
        }
    }

    public SuggestionOffer(int r1, View r2, Theme.ResourcesProvider r3) {
        this.rows = new ArrayList(2);
        this.resourcesProvider = r3;
    }

    private void updateBuildTitleStep(StringBuilder r1, int r2, boolean r3) {
        if (r1.length() <= 0) goto L7;
        if (r3 == false) goto L6;
        r1.append(' ');
        r1.append(LocaleController.getString(R.string.SuggestionOfferInfoTitleEditedAnd));
        r1.append(' ');
        goto L7
    L6:
        r1.append(", ");
    L7:
        r1.append(LocaleController.getString(r2));
    }

    public void draw(Canvas r7, int r8, float r9, float r10, float r11, float r12, boolean r13) {
        int r82 = (r8 - this.width) / 2;
        RectF r0 = AndroidUtilities.rectTmp;
        r0.set(r82, 0.0f, r13 + r82, this.height);
        r7.save();
        r7.translate(r9 / 2.0f, r10);
        Paint r92 = Theme.getThemePaint("paintChatActionBackground", this.resourcesProvider);
        int r102 = r92.getAlpha();
        r92.setAlpha((int) ((r102 * r12) * r11));
        r7.drawRoundRect(r0, AndroidUtilities.dp(15.0f), AndroidUtilities.dp(15.0f), r92);
        r92.setAlpha(r102);
        Theme.ResourcesProvider r93 = this.resourcesProvider;
        if (r93 != null) goto L8;
        if (Theme.hasGradientService() == true) goto L9;
    L10:
        int r94 = AndroidUtilities.dp(14.0f);
        if (this.title == null) goto L13;
        r7.save();
        r7.translate(this.titleX + r82, r94);
        this.title.draw(r7);
        r7.restore();
        r94 = r94 + (this.title.getHeight() + AndroidUtilities.dp(12.0f));
    L13:
        ArrayList<Row> r103 = this.rows;
        int r112 = r103.size();
        int r122 = 0;
    L14:
        if (r122 >= r112) goto L16;
        Row r02 = r103.get(r122);
        r122 = r122 + 1;
        Row r03 = r02;
        float r3 = r94;
        r03.title.draw(r7, this.rowsTitleX + r82, (r03.getHeight() / 2.0f) + r3, 0.85f);
        r03.info.draw(r7, this.rowsInfoX + r82, r3 + (r03.getHeight() / 2.0f));
        r94 = r94 + (r03.getHeight() + AndroidUtilities.dp(7.0f));
        goto L14
    L16:
        r7.restore();
        return;
    L9:
        Paint r95 = Theme.getThemePaint("paintChatActionBackgroundDarken", this.resourcesProvider);
        int r104 = r95.getAlpha();
        r95.setAlpha((int) ((r104 * r12) * r11));
        r7.drawRect(r0, r95);
        r95.setAlpha(r104);
        goto L10
    L8:
        if (r93.hasGradientService() == false) goto L10;
        goto L9
    }

    public int getHeight() {
        return this.height;
    }

    public Paint getThemedPaint(String r1) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L5;
        Paint r02 = r0.getPaint(r1);
    L6:
        if (r02 == null) goto L9;
        return r02;
    L9:
        return Theme.getThemePaint(r1);
    L5:
        r02 = null;
        goto L6
    }

    public int getWidth() {
        return this.width;
    }

    public void update(MessageObject r19) {
        if (r19 == null) goto L7;
        TLRPC.Message r2 = r19.messageOwner;
        if (r2 == null) goto L7;
        TLRPC.SuggestedPost r22 = r2.suggested_post;
    L8:
        if (r22 != null) goto L10;
        return;
    L10:
        MessageSuggestionParams r3 = MessageSuggestionParams.of(r22);
        TextPaint r7 = (TextPaint) getThemedPaint("paintChatActionText3");
        this.height = AndroidUtilities.dp(14.0f) * 2;
        this.rows.clear();
        AmountUtils$Amount r4 = r3.amount;
        if (r4 != null) goto L13;
    L15:
        boolean r42 = true;
        if (r22.schedule_date <= 0) goto L18;
        this.rows.add(new Row(new Text(LocaleController.getString(R.string.SuggestionOfferInfoTime), r7), new Text(LocaleController.bold(LocaleController.formatDateTime(r22.schedule_date, true)), r7)));
    L18:
        ArrayList<Row> r23 = this.rows;
        int r32 = r23.size();
        float r5 = 0.0f;
        int r13 = 0;
        float r14 = 0.0f;
        int r6 = 0;
    L19:
        if (r6 >= r32) goto L21;
        Row r8 = r23.get(r6);
        r6 = r6 + 1;
        Row r82 = r8;
        r14 = Math.max(r14, r82.title.getWidth());
        r5 = Math.max(r5, r82.info.getWidth());
        int r9 = this.height + r82.getHeight();
        this.height = r9;
        this.height = r9 + AndroidUtilities.dp(7.0f);
        goto L19
    L21:
        int r33 = (int) ((r5 + r14) + AndroidUtilities.dp(11.0f));
        int r83 = Math.max(r33, AndroidUtilities.dp(160.0f));
        String r52 = DialogObject.getName(r19.getFromChatId());
        int r62 = r19.getEditedSuggestionFlags();
        SpannableStringBuilder r92 = new SpannableStringBuilder();
        if (r62 == 0) goto L24;
        MessageObject r10 = r19.replyMessageObject;
        if (r10 == null) goto L31;
        DialogObject.getName(r10.getFromChatId());
    L31:
        StringBuilder r102 = new StringBuilder();
        int r11 = r62 & 4;
        if (r11 == 0) goto L34;
        int r12 = 1;
    L35:
        int r15 = r62 & 2;
        if (r15 == 0) goto L38;
        int r16 = 1;
    L39:
        int r122 = r12 + r16;
        int r162 = r62 & 8;
        if (r162 == 0) goto L42;
        int r17 = 1;
    L43:
        int r123 = r122 + r17;
        int r63 = r62 & 1;
        if (r63 == 0) goto L46;
        int r172 = 1;
    L47:
        int r124 = r123 + r172;
        if (r63 == 0) goto L54;
        int r64 = R.string.SuggestionOfferInfoTitleEditedPrice;
        float r173 = 11.0f;
        if (r124 != 1) goto L52;
        boolean r24 = true;
    L53:
        updateBuildTitleStep(r102, r64, r24);
        int r25 = 1;
    L55:
        if (r15 == 0) goto L61;
        int r65 = R.string.SuggestionOfferInfoTitleEditedTime;
        r25 = r25 + 1;
        if (r124 != r25) goto L59;
        boolean r152 = true;
    L60:
        updateBuildTitleStep(r102, r65, r152);
        goto L61
    L59:
        r152 = false;
    L61:
        if (r11 == 0) goto L67;
        int r66 = R.string.SuggestionOfferInfoTitleEditedText;
        r25 = r25 + 1;
        if (r124 != r25) goto L65;
        boolean r112 = true;
    L66:
        updateBuildTitleStep(r102, r66, r112);
        goto L67
    L65:
        r112 = false;
    L67:
        if (r162 == 0) goto L74;
        int r67 = R.string.SuggestionOfferInfoTitleEditedMedia;
        if (r124 == (r25 + 1)) goto L72;
        r42 = false;
    L72:
        updateBuildTitleStep(r102, r67, r42);
    L74:
        if (r19.isOutOwner() == false) goto L76;
        r92.append(LocaleController.formatString(R.string.SuggestionOfferInfoTitleEditedFromYou, new Object[]{r102}));
    L77:
        this.title = new StaticLayout(AndroidUtilities.replaceTags(r92), r7, r83, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        int r1 = 0;
    L79:
        if (r13 >= this.title.getLineCount()) goto L81;
        r1 = (int) Math.max(r1, this.title.getLineWidth(r13));
        r13 = r13 + 1;
        goto L79
    L81:
        int r26 = this.height + this.title.getHeight();
        this.height = r26;
        this.height = r26 + AndroidUtilities.dp(5.0f);
        int r18 = Math.max(r33, r1) + (AndroidUtilities.dp(24.0f) * 2);
        this.width = r18;
        this.titleX = (r18 - r83) / 2;
        this.rowsTitleX = (r18 - r33) / 2;
        this.rowsInfoX = (int) ((r1 + AndroidUtilities.dp(r173)) + r14);
        return;
    L76:
        r92.append(LocaleController.formatString(R.string.SuggestionOfferInfoTitleEditedFromX, new Object[]{r52, r102}));
        goto L77
    L52:
        r24 = false;
        goto L53
    L54:
        r173 = 11.0f;
        r25 = 0;
        goto L55
    L46:
        r172 = 0;
        goto L47
    L42:
        r17 = 0;
        goto L43
    L38:
        r16 = 0;
        goto L39
    L34:
        r12 = 0;
        goto L35
    L24:
        if (r19.isOutOwner() == false) goto L27;
        r92.append(LocaleController.getString(R.string.SuggestionOfferInfoTitleYou));
    L26:
        r173 = 11.0f;
        goto L77
    L27:
        r92.append(LocaleController.formatString(R.string.SuggestionOfferInfoTitle, new Object[]{r52}));
        goto L26
    L13:
        if (r4.isZero() == true) goto L15;
        this.rows.add(new Row(new Text(LocaleController.getString(R.string.SuggestionOfferInfoPrice), r7), new Text(LocaleController.bold(r3.amount.formatAsDecimalSpaced()), r7)));
    L7:
        r22 = null;
        goto L8
    }
}
