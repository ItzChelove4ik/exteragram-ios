package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.ColorSpanUnderline;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;

/* loaded from: classes6.dex */
public class StickerSetNameCell extends FrameLayout {
    private final ImageView buttonView;
    private final TextView editView;
    private boolean empty;
    private boolean isEmoji;
    public int position;
    private final Theme.ResourcesProvider resourcesProvider;
    private CharSequence stickerSetName;
    private int stickerSetNameSearchIndex;
    private int stickerSetNameSearchLength;
    private final TextView textView;
    private CharSequence url;
    private int urlSearchLength;
    private final TextView urlTextView;

    public StickerSetNameCell(Context r7, boolean r8, Theme.ResourcesProvider r9, boolean r10) {
        this(r7, r8, false, r9, r10);
    }

    public StickerSetNameCell(Context r22, boolean r23, boolean r24, Theme.ResourcesProvider r25, boolean r26) {
        super(r22);
        this.resourcesProvider = r25;
        this.isEmoji = r23;
        LinearLayout r3 = new LinearLayout(r22);
        r3.setOrientation(0);
        r3.setGravity(17);
        TextView r6 = new TextView(r22);
        this.textView = r6;
        if (r26 == false) goto L5;
        int r8 = getGlassIconColor(0.6f);
    L6:
        r6.setTextColor(r8);
        r6.setTextSize(1, 15.0f);
        r6.setTypeface(AndroidUtilities.bold());
        TextUtils.TruncateAt r10 = TextUtils.TruncateAt.END;
        r6.setEllipsize(r10);
        r6.setSingleLine(true);
        if (r23 == false) goto L10;
        r6.setGravity(17);
        r6.setTextAlignment(4);
    L10:
        if (r24 == false) goto L18;
        if (r23 == false) goto L13;
        float r17 = 5.0f;
    L14:
        if (r23 == false) goto L16;
        float r19 = 15.0f;
    L17:
        ViewGroup.LayoutParams r2 = LayoutHelper.createFrameRelatively(-2.0f, -2.0f, 8388659, r17, 5.0f, r19, 0.0f);
    L25:
        addView(r3, r2);
        r3.addView(r6, LayoutHelper.createLinear(-2, -2, 1.0f, 16));
        TextView r27 = new TextView(r22);
        this.editView = r27;
        if (r26 == false) goto L28;
        int r62 = getGlassIconColor(0.6f);
    L29:
        r27.setTextColor(r62);
        r27.setTextSize(1, 11.0f);
        r27.setTypeface(AndroidUtilities.bold());
        r27.setEllipsize(r10);
        r27.setPadding(AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(6.33f), 0);
        int r4 = AndroidUtilities.dp(9.0f);
        if (r26 == false) goto L32;
        int r63 = getGlassIconColor(0.05f);
    L33:
        if (r26 == false) goto L35;
        int r9 = getGlassIconColor(0.08f);
    L36:
        r27.setBackground(Theme.createSimpleSelectorRoundRectDrawable(r4, r63, r9));
        r27.setGravity(17);
        r27.setSingleLine(true);
        ScaleStateListAnimator.apply(r27);
        r3.addView(r27, LayoutHelper.createLinear(-2, -2, 0.0f, 16, 5, 1, 0, 0));
        r27.setVisibility(8);
        TextView r28 = new TextView(r22);
        this.urlTextView = r28;
        if (r26 == false) goto L39;
        int r32 = getGlassIconColor(0.6f);
    L40:
        r28.setTextColor(r32);
        r28.setTextSize(1, 12.0f);
        r28.setEllipsize(r10);
        r28.setSingleLine(true);
        r28.setVisibility(4);
        if (r24 == false) goto L43;
        ViewGroup.LayoutParams r33 = LayoutHelper.createFrameRelatively(-2.0f, -2.0f, 8388661, 12.0f, 6.0f, 17.0f, 0.0f);
    L44:
        addView(r28, r33);
        ImageView r29 = new ImageView(r22);
        this.buttonView = r29;
        r29.setScaleType(ImageView.ScaleType.CENTER);
        if (r26 == false) goto L47;
        int r34 = getGlassIconColor(0.6f);
    L48:
        r29.setColorFilter(new PorterDuffColorFilter(r34, PorterDuff.Mode.MULTIPLY));
        r29.setBackground(Theme.createSelectorDrawable(getThemedColor(Theme.key_listSelector), 3));
        boolean r1 = this.isEmoji;
        if (r24 == false) goto L54;
        if (r1 == false) goto L52;
        float r102 = 0.0f;
    L53:
        ViewGroup.LayoutParams r12 = LayoutHelper.createFrameRelatively(24.0f, 24.0f, 8388661, 0.0f, 0.0f, r102, 0.0f);
    L58:
        r29.setTranslationY(AndroidUtilities.dp(4.0f));
        addView(r29, r12);
        return;
    L52:
        r102 = 10.0f;
        goto L53
    L54:
        if (r1 == false) goto L56;
        float r82 = 0.0f;
    L57:
        r12 = LayoutHelper.createFrame(24, 24.0f, 53, 0.0f, 0.0f, r82, 0.0f);
        goto L58
    L56:
        r82 = 10.0f;
        goto L57
    L47:
        r34 = getThemedColor(Theme.key_chat_emojiPanelStickerSetNameIcon);
        goto L48
    L43:
        r33 = LayoutHelper.createFrame(-2, -2.0f, 53, 12.0f, 6.0f, 17.0f, 0.0f);
        goto L44
    L39:
        r32 = getThemedColor(Theme.key_chat_emojiPanelStickerSetName);
        goto L40
    L35:
        r9 = Theme.multAlpha(getThemedColor(Theme.key_chat_emojiPanelStickerSetName), 0.24f);
        goto L36
    L32:
        r63 = Theme.multAlpha(getThemedColor(Theme.key_chat_emojiPanelStickerSetName), 0.1f);
        goto L33
    L28:
        r62 = getThemedColor(Theme.key_chat_emojiPanelStickerSetName);
        goto L29
    L16:
        r19 = 25.0f;
        goto L17
    L13:
        r17 = 15.0f;
        goto L14
    L18:
        if (r23 == false) goto L20;
        float r15 = 5.0f;
    L21:
        if (r23 == false) goto L23;
        float r172 = 15.0f;
    L24:
        r2 = LayoutHelper.createFrame(-2, -2.0f, 51, r15, 5.0f, r172, 0.0f);
        goto L25
    L23:
        r172 = 25.0f;
        goto L24
    L20:
        r15 = 15.0f;
        goto L21
    L5:
        r8 = getThemedColor(Theme.key_chat_emojiPanelStickerSetName);
        goto L6
    }

    public static void createThemeDescriptions(List<ThemeDescription> r28, RecyclerListView r29, ThemeDescription.ThemeDescriptionDelegate r30) {
        int r18 = Theme.key_chat_emojiPanelStickerSetName;
        r28.add(new ThemeDescription(r29, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{StickerSetNameCell.class}, new String[]{"textView"}, null, null, null, r18));
        r28.add(new ThemeDescription(r29, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{StickerSetNameCell.class}, new String[]{"urlTextView"}, null, null, null, r18));
        r28.add(new ThemeDescription(r29, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{StickerSetNameCell.class}, new String[]{"buttonView"}, null, null, null, Theme.key_chat_emojiPanelStickerSetNameIcon));
        r28.add(new ThemeDescription(null, 0, null, null, null, r30, Theme.key_chat_emojiPanelStickerSetNameHighlight));
        r28.add(new ThemeDescription(null, 0, null, null, null, r30, r18));
    }

    private int getGlassIconColor(float r2) {
        return ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_glass_defaultIcon, this.resourcesProvider), (int) (r2 * 255.0f));
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private void updateTextSearchSpan() {
        if (this.stickerSetName != null) goto L5;
        return;
    L5:
        if (this.stickerSetNameSearchLength <= 0) goto L14;
        SpannableStringBuilder r0 = new SpannableStringBuilder(this.stickerSetName);
        ForegroundColorSpan r1 = new ForegroundColorSpan(getThemedColor(Theme.key_chat_emojiPanelStickerSetNameHighlight));     // Catch: Exception -> L10
        int r2 = this.stickerSetNameSearchIndex;     // Catch: Exception -> L10
        r0.setSpan(r1, r2, this.stickerSetNameSearchLength + r2, 33);     // Catch: Exception -> L10
    L8:
        TextView r5 = this.textView;
        r5.setText(Emoji.replaceEmoji(r0, r5.getPaint().getFontMetricsInt(), false));
        return;
    }

    private void updateUrlSearchSpan() {
        if (this.url == null) goto L11;
        SpannableStringBuilder r0 = new SpannableStringBuilder(this.url);
        r0.setSpan(new ColorSpanUnderline(getThemedColor(Theme.key_chat_emojiPanelStickerSetNameHighlight)), 0, this.urlSearchLength, 33);     // Catch: Exception -> L8
        r0.setSpan(new ColorSpanUnderline(getThemedColor(Theme.key_chat_emojiPanelStickerSetName)), this.urlSearchLength, this.url.length(), 33);     // Catch: Exception -> L8
    L6:
        this.urlTextView.setText(r0);
        return;
    }

    public TextView getTextView() {
        return this.textView;
    }

    @Override // android.view.View
    public void invalidate() {
        this.textView.invalidate();
        super.invalidate();
    }

    @Override // android.view.ViewGroup
    public void measureChildWithMargins(View r7, int r8, int r9, int r10, int r11) {
        if (r7 != this.urlTextView) goto L5;
        r9 = r9 + (this.textView.getMeasuredWidth() + AndroidUtilities.dp(16.0f));
    L5:
        super.measureChildWithMargins(r7, r8, r9, r10, r11);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        if (this.empty == false) goto L6;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(1, TLObject.FLAG_30));
        return;
    L6:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(27.0f), TLObject.FLAG_30));
    }

    public void setEdit(View.OnClickListener r3) {
        this.editView.setVisibility(0);
        this.editView.setText(LocaleController.getString(R.string.EditPack));
        this.editView.setOnClickListener(r3);
    }

    public void setHeaderOnClick(View.OnClickListener r1) {
        this.textView.setOnClickListener(r1);
    }

    public void setOnIconClickListener(View.OnClickListener r1) {
        this.buttonView.setOnClickListener(r1);
    }

    public void setText(CharSequence r7, int r8) {
        setText(r7, r8, null, 0, 0);
    }

    public void setText(CharSequence r7, int r8, int r9, int r10) {
        setText(r7, r8, null, r9, r10);
    }

    public void setText(CharSequence r7, int r8, CharSequence r9) {
        setText(r7, r8, r9, 0, 0);
    }

    public void setText(CharSequence r3, int r4, CharSequence r5, int r6, int r7) {
        this.stickerSetName = r3;
        this.stickerSetNameSearchIndex = r6;
        this.stickerSetNameSearchLength = r7;
        if (r3 != null) goto L5;
        this.empty = true;
        this.textView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.buttonView.setVisibility(4);
    L13:
        this.editView.setVisibility(8);
        return;
    L5:
        this.empty = false;
        if (r7 == 0) goto L8;
        updateTextSearchSpan();
    L9:
        ImageView r32 = this.buttonView;
        if (r4 == 0) goto L12;
        r32.setImageResource(r4);
        this.buttonView.setContentDescription(r5);
        this.buttonView.setVisibility(0);
        goto L13
    L12:
        r32.setVisibility(4);
        goto L13
    L8:
        TextView r72 = this.textView;
        r72.setText(Emoji.replaceEmoji(r3, r72.getPaint().getFontMetricsInt(), false));
        goto L9
    }

    public void setTitleColor(int r1) {
        this.textView.setTextColor(r1);
    }

    public void setUrl(CharSequence r1, int r2) {
        this.url = r1;
        this.urlSearchLength = r2;
        TextView r22 = this.urlTextView;
        if (r1 == null) goto L5;
        int r12 = 0;
    L6:
        r22.setVisibility(r12);
        updateUrlSearchSpan();
        return;
    L5:
        r12 = 8;
        goto L6
    }

    public void updateColors() {
        updateTextSearchSpan();
        updateUrlSearchSpan();
    }
}
