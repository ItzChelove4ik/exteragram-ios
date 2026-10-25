package org.telegram.ui.bots;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.ui.ActionBar.MenuDrawable;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.StaticLayoutEx;

/* loaded from: classes7.dex */
public class BotCommandsMenuView extends View {
    final MenuDrawable backDrawable;
    Drawable backgroundDrawable;
    boolean drawBackgroundDrawable;
    float expandProgress;
    public boolean expanded;
    boolean isOpened;
    public boolean isWebView;
    boolean isWebViewOpened;
    int lastSize;
    private String menuText;
    StaticLayout menuTextLayout;
    private float menuTextWidth;
    final Paint paint;
    final RectF rectTmp;
    final TextPaint textPaint;
    RLottieDrawable webViewAnimation;

    /* renamed from: org.telegram.ui.bots.BotCommandsMenuView$1, reason: invalid class name */
    public class AnonymousClass1 extends MenuDrawable {
        final /* synthetic */ BotCommandsMenuView this$0;

        public AnonymousClass1(BotCommandsMenuView r1) {
            this.this$0 = r1;
        }

        @Override // android.graphics.drawable.Drawable
        public void invalidateSelf() {
            super.invalidateSelf();
            this.this$0.invalidate();
        }
    }

    public static class BotCommandView extends LinearLayout {
        TextView command;
        String commandStr;
        TextView description;

        /* renamed from: org.telegram.ui.bots.BotCommandsMenuView$BotCommandView$1, reason: invalid class name */
        public class AnonymousClass1 extends TextView {
            final /* synthetic */ BotCommandView this$0;

            public AnonymousClass1(BotCommandView r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // android.widget.TextView
            public void setText(CharSequence r3, TextView.BufferType r4) {
                super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false), r4);
            }
        }

        public BotCommandView(Context r13) {
            super(r13);
            setOrientation(0);
            setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(8.0f));
            AnonymousClass1 r1 = new AnonymousClass1(this, r13);
            this.description = r1;
            NotificationCenter.listenEmojiLoading(r1);
            this.description.setTextSize(1, 16.0f);
            TextView r0 = this.description;
            int r12 = Theme.key_windowBackgroundWhiteBlackText;
            r0.setTextColor(Theme.getColor(r12));
            this.description.setTag(Integer.valueOf(r12));
            this.description.setMaxLines(2);
            this.description.setEllipsize(TextUtils.TruncateAt.END);
            addView(this.description, LayoutHelper.createLinear(-1, -2, 1.0f, 16, 0, 0, AndroidUtilities.dp(8.0f), 0));
            TextView r02 = new TextView(r13);
            this.command = r02;
            r02.setTextSize(1, 14.0f);
            TextView r132 = this.command;
            int r03 = Theme.key_windowBackgroundWhiteGrayText;
            r132.setTextColor(Theme.getColor(r03));
            this.command.setTag(Integer.valueOf(r03));
            addView(this.command, LayoutHelper.createLinear(-2, -2, 0.0f, 16));
        }

        public String getCommand() {
            return this.commandStr;
        }
    }

    public static class BotCommandsAdapter extends RecyclerListView.SelectionAdapter {
        ArrayList<String> newResult;
        ArrayList<Boolean> newResultEphemeral;
        ArrayList<String> newResultHelp;

        public BotCommandsAdapter() {
            this.newResult = new ArrayList();
            this.newResultHelp = new ArrayList();
            this.newResultEphemeral = new ArrayList();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.newResult.size();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r7, int r8) {
            BotCommandView r72 = (BotCommandView) r7.itemView;
            String r0 = this.newResult.get(r8);
            if (this.newResultEphemeral.get(r8).booleanValue() == false) goto L5;
            ColoredImageSpan r1 = new ColoredImageSpan(R.drawable.mini_ephemeral_hidden_14);
            r1.setColorKey(Theme.key_windowBackgroundWhiteGrayText3);
            r1.setTopOffset(1);
            SpannableStringBuilder r3 = new SpannableStringBuilder(r0);
            r3.append(" *");
            r3.setSpan(r1, r3.length() - 1, r3.length(), 33);
            r72.command.setText(r3);
        L6:
            r72.description.setText(this.newResultHelp.get(r8));
            r72.commandStr = r0;
            return;
        L5:
            r72.command.setText(r0);
            goto L6
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            BotCommandView r1 = new BotCommandView(r2.getContext());
            r1.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r1);
        }

        public void setBotInfo(LongSparseArray<TL_bots.BotInfo> r9) {
            this.newResult.clear();
            this.newResultHelp.clear();
            this.newResultEphemeral.clear();
            int r1 = 0;
        L4:
            if (r1 >= r9.size()) goto L15;
            TL_bots.BotInfo r2 = r9.valueAt(r1);
            int r3 = 0;
        L7:
            if (r3 >= r2.commands.size()) goto L14;
            TLRPC.BotCommand r4 = r2.commands.get(r3);
            if (r4 == null) goto L13;
            if (r4.command == null) goto L13;
            this.newResult.add("/" + r4.command);
            this.newResultHelp.add(r4.description);
            this.newResultEphemeral.add(Boolean.valueOf(r4.ephemeral));
        L13:
            r3 = r3 + 1;
            goto L7
        L14:
            r1 = r1 + 1;
            goto L4
        L15:
            notifyDataSetChanged();
        }
    }

    public BotCommandsMenuView(Context r7) {
        super(r7);
        this.rectTmp = new RectF();
        this.paint = new Paint(1);
        TextPaint r72 = new TextPaint(1);
        this.textPaint = r72;
        AnonymousClass1 r1 = new AnonymousClass1(this);
        this.backDrawable = r1;
        this.webViewAnimation = new RLottieDrawable(R.raw.bot_webview_sheet_to_cross, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f));
        this.menuText = LocaleController.getString(R.string.BotsMenuTitle);
        this.drawBackgroundDrawable = true;
        updateColors();
        r1.setMiniIcon(true);
        r1.setRotateToBack(false);
        r1.setRotation(0.0f, false);
        r1.setCallback(this);
        r72.setTypeface(AndroidUtilities.bold());
        r1.setRoundCap();
        Drawable r73 = Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(16.0f), 0, Theme.getColor(Theme.key_featuredStickers_addButtonPressed));
        this.backgroundDrawable = r73;
        r73.setCallback(this);
        this.webViewAnimation.setCallback(this);
        this.webViewAnimation.setMasterParent(this);
        setContentDescription(LocaleController.getString("AccDescrBotMenu", R.string.AccDescrBotMenu));
    }

    private void updateColors() {
        this.paint.setColor(Theme.getColor(Theme.key_chat_messagePanelVoiceBackground));
        int r0 = Theme.getColor(Theme.key_chat_messagePanelVoiceDuration);
        this.backDrawable.setBackColor(r0);
        this.backDrawable.setIconColor(r0);
        RLottieDrawable r1 = this.webViewAnimation;
        if (r1 == null) goto L5;
        r1.setColorFilter(new PorterDuffColorFilter(r0, PorterDuff.Mode.SRC_IN));
    L5:
        this.textPaint.setColor(r0);
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r11) {
        if (this.menuTextLayout == null) goto L39;
        boolean r0 = this.expanded;
        boolean r1 = true;
        if (r0 == false) goto L12;
        float r5 = this.expandProgress;
        if (r5 == 1.0f) goto L12;
        float r52 = r5 + 0.10666667f;
        this.expandProgress = r52;
        if (r52 <= 1.0f) goto L11;
        this.expandProgress = 1.0f;
    L20:
        float r02 = CubicBezierInterpolator.DEFAULT.getInterpolation(this.expandProgress);
        if (r1 == false) goto L26;
        if (r02 <= 0.0f) goto L26;
        this.textPaint.setAlpha((int) (255.0f * r02));
    L26:
        if (this.drawBackgroundDrawable == false) goto L29;
        this.rectTmp.set(0.0f, 0.0f, AndroidUtilities.dp(40.0f) + ((this.menuTextWidth + AndroidUtilities.dp(4.0f)) * r02), getMeasuredHeight());
        r11.drawRoundRect(this.rectTmp, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), this.paint);
        Drawable r2 = this.backgroundDrawable;
        RectF r6 = this.rectTmp;
        r2.setBounds((int) r6.left, (int) r6.top, (int) r6.right, (int) r6.bottom);
        this.backgroundDrawable.draw(r11);
    L29:
        if (this.isWebView == false) goto L33;
        r11.save();
        r11.translate(AndroidUtilities.dp(9.5f), AndroidUtilities.dp(6.0f));
        RLottieDrawable r22 = this.webViewAnimation;
        r22.setBounds(0, 0, r22.getMinimumWidth(), r22.getMinimumHeight());
        r22.draw(r11);
        r11.restore();
        if (r22.isRunning() == false) goto L35;
        invalidate();
    L35:
        if (r02 <= 0.0f) goto L37;
        r11.save();
        r11.translate(AndroidUtilities.dp(34.0f), (getMeasuredHeight() - this.menuTextLayout.getHeight()) / 2.0f);
        this.menuTextLayout.draw(r11);
        r11.restore();
    L37:
        if (r1 == false) goto L39;
        onTranslationChanged((this.menuTextWidth + AndroidUtilities.dp(4.0f)) * r02);
        goto L39
    L33:
        r11.save();
        r11.translate(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(4.0f));
        this.backDrawable.draw(r11);
        r11.restore();
        goto L35
    L11:
        invalidate();
    L12:
        if (r0 == true) goto L19;
        float r03 = this.expandProgress;
        if (r03 == 0.0f) goto L19;
        float r04 = r03 - 0.10666667f;
        this.expandProgress = r04;
        if (r04 >= 0.0f) goto L18;
        this.expandProgress = 0.0f;
        goto L20
    L18:
        invalidate();
    L19:
        r1 = false;
    L39:
        super.dispatchDraw(r11);
    }

    @Override // android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.backgroundDrawable.setState(getDrawableState());
    }

    public boolean isOpened() {
        return this.isOpened;
    }

    @Override // android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.backgroundDrawable.jumpToCurrentState();
    }

    @Override // android.view.View
    public void onMeasure(int r14, int r15) {
        int r142 = (View.MeasureSpec.getSize(r14) + View.MeasureSpec.getSize(r15)) << 16;
        if (this.lastSize == r142) goto L5;
    L6:
        this.backDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        this.textPaint.setTextSize(AndroidUtilities.dp(15.0f));
        this.lastSize = r142;
        int r5 = (int) (AndroidUtilities.displaySize.x * 0.6f);
        StaticLayout r143 = StaticLayoutEx.createStaticLayout(Emoji.replaceEmoji(this.menuText, this.textPaint.getFontMetricsInt(), false), this.textPaint, r5, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false, TextUtils.TruncateAt.END, r5, 1);
        this.menuTextLayout = r143;
        if (r143.getLineCount() <= 0) goto L9;
        float r144 = this.menuTextLayout.getLineWidth(0);
    L10:
        this.menuTextWidth = r144;
    L11:
        onTranslationChanged((this.menuTextWidth + AndroidUtilities.dp(4.0f)) * this.expandProgress);
        int r145 = AndroidUtilities.dp(40.0f);
        if (this.expanded == false) goto L14;
        r145 = r145 + (((int) this.menuTextWidth) + AndroidUtilities.dp(4.0f));
    L14:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(r145, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32.0f), TLObject.FLAG_30));
        return;
    L9:
        r144 = 0.0f;
        goto L10
    L5:
        if (this.menuTextLayout != null) goto L11;
        goto L6
    }

    public void onTranslationChanged(float r1) {
    }

    public void setDrawBackgroundDrawable(boolean r1) {
        this.drawBackgroundDrawable = r1;
        invalidate();
    }

    public void setExpanded(boolean r2, boolean r3) {
        if (this.expanded == r2) goto L12;
        this.expanded = r2;
        if (r3 == true) goto L10;
        if (r2 == false) goto L8;
        float r22 = 1.0f;
    L9:
        this.expandProgress = r22;
        goto L10
    L8:
        r22 = 0.0f;
    L10:
        requestLayout();
        invalidate();
        return;
    }

    public boolean setMenuText(String r2) {
        if (r2 != null) goto L4;
        r2 = LocaleController.getString(R.string.BotsMenuTitle);
    L4:
        String r0 = this.menuText;
        if (r0 != null) goto L7;
    L10:
        boolean r02 = true;
    L11:
        this.menuText = r2;
        this.menuTextLayout = null;
        requestLayout();
        return r02;
    L7:
        if (r0.equals(r2) == false) goto L10;
        r02 = false;
        goto L11
    }

    public void setOpened(boolean r3) {
        if (this.isOpened == r3) goto L5;
        this.isOpened = r3;
    L5:
        int r1 = 1;
        if (this.isWebView == true) goto L8;
        MenuDrawable r2 = this.backDrawable;
        if (r3 == false) goto L17;
        float r32 = 1.0f;
    L18:
        r2.setRotation(r32, true);
        return;
    L17:
        r32 = 0.0f;
        goto L18
    L8:
        if (this.isWebViewOpened == r3) goto L20;
        RLottieDrawable r0 = this.webViewAnimation;
        r0.stop();
        r0.setPlayInDirectionOfCustomEndFrame(true);
        if (r3 == false) goto L12;
        r1 = r0.getFramesCount();
    L12:
        r0.setCustomEndFrame(r1);
        r0.start();
        this.isWebViewOpened = r3;
        return;
    }

    public void setWebView(boolean r1) {
        this.isWebView = r1;
        invalidate();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (super.verifyDrawable(r2) == false) goto L5;
        return true;
    L5:
        if (this.backgroundDrawable == r2) goto L11;
        return false;
    L11:
        return true;
    }
}
