package org.telegram.ui.Components;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class GuardBotReplaceSheet extends BottomSheetWithRecyclerListView {
    private UniversalAdapter adapter;
    private final LinearLayout contentLayout;
    private final ButtonWithCounterView keepCurrentButton;
    private final ButtonWithCounterView useNewButton;

    public static /* synthetic */ void $r8$lambda$0D2LatTXwwuP8dxO5RoOMFlV57A(GuardBotReplaceSheet r0, Runnable r1, View r2) {
        r0.lambda$new$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$UcgqB1R_aAKz6p_JpQkZyD0dXHE(GuardBotReplaceSheet r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$t_5dv4SY2Zfii3US4uH7BUftY7k(GuardBotReplaceSheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    private GuardBotReplaceSheet(Context r23, Theme.ResourcesProvider r24, int r25, TLObject r26, TLObject r27, Runnable r28) {
        super(r23, null, false, false, false, false, BottomSheetWithRecyclerListView.ActionBarType.FADING, r24);
        fixNavigationBar();
        LinearLayout r2 = new LinearLayout(r23);
        this.contentLayout = r2;
        r2.setOrientation(1);
        r2.setClipChildren(false);
        r2.setClipToPadding(false);
        FrameLayout r5 = new FrameLayout(r23);
        View r7 = buildAvatar(r23, r26, AndroidUtilities.dp(60.0f));
        View r6 = buildAvatar(r23, r27, AndroidUtilities.dp(60.0f));
        ImageView r11 = new ImageView(r23);
        r11.setImageResource(R.drawable.msg_arrow_avatar);
        r11.setColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r24));
        r11.setScaleType(ImageView.ScaleType.CENTER);
        LinearLayout r12 = new LinearLayout(r23);
        r12.setOrientation(0);
        r12.setGravity(16);
        r12.setClipChildren(false);
        r12.addView(r7, LayoutHelper.createLinear(60, 60));
        r12.addView(r11, LayoutHelper.createLinear(24, 24, 16, 7, 0, 7, 0));
        r12.addView(r6, LayoutHelper.createLinear(60, 60));
        r5.addView(r12, LayoutHelper.createFrame(-2, -2, 17));
        r2.addView(r5, LayoutHelper.createLinear(-1, -2, 1, 0, 23, 0, 19));
        TextView r52 = new TextView(r23);
        r52.setTypeface(AndroidUtilities.bold());
        r52.setGravity(17);
        r52.setText(LocaleController.getString(R.string.GuardBotReplaceTitle));
        r52.setTextSize(1, 20.0f);
        int r62 = Theme.key_windowBackgroundWhiteBlackText;
        r52.setTextColor(Theme.getColor(r62, r24));
        r2.addView(r52, LayoutHelper.createLinear(-1, -2, 17, 20, 0, 20, 6));
        String r53 = DialogObject.getShortName(r26);
        String r9 = DialogObject.getShortName(r27);
        TextView r10 = new TextView(r23);
        r10.setGravity(17);
        r10.setText(buildSubtitle(r53, r9));
        r10.setTextSize(1, 14.0f);
        r10.setTextColor(Theme.getColor(r62, r24));
        r10.setLineSpacing(AndroidUtilities.dp(2.66f), 1.0f);
        r2.addView(r10, LayoutHelper.createLinear(-1, -2, 17, 24, 0, 24, 29));
        ButtonWithCounterView r3 = new ButtonWithCounterView(r23, r24);
        this.useNewButton = r3;
        r3.setRound();
        r3.setText(LocaleController.formatString(R.string.GuardBotReplaceUseNew, new Object[]{r9}), false);
        r3.setOnClickListener(new GuardBotReplaceSheet$$ExternalSyntheticLambda0(this, r28));
        r2.addView(r3, LayoutHelper.createLinear(-1, 48, 14.0f, 0.0f, 14.0f, 10.0f));
        ButtonWithCounterView r32 = new ButtonWithCounterView(r23, r24);
        this.keepCurrentButton = r32;
        r32.setRound();
        r32.setNeutral();
        r32.setText(LocaleController.formatString(R.string.GuardBotReplaceKeepCurrent, new Object[]{r53}), false);
        r32.setOnClickListener(new GuardBotReplaceSheet$$ExternalSyntheticLambda1(this));
        r2.addView(r32, LayoutHelper.createLinear(-1, 48, 14.0f, 0.0f, 14.0f, 14.0f));
        RecyclerListView r1 = this.recyclerListView;
        int r22 = this.backgroundPaddingLeft;
        r1.setPadding(r22, 0, r22, 0);
        this.adapter.update(false);
    }

    private View buildAvatar(Context r1, TLObject r2, int r3) {
        ProfileActivity.AvatarImageView r0 = new ProfileActivity.AvatarImageView(r1);
        r0.setRoundRadius(r3 / 2);
        AvatarDrawable r12 = new AvatarDrawable();
        r12.setInfo(r2);
        r0.setImageDrawable(r12);
        r0.setLayoutParams(new FrameLayout.LayoutParams(r3, r3));
        return r0;
    }

    private static CharSequence buildSubtitle(String r1, String r2) {
        return AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GuardBotReplaceMessage, new Object[]{r1, r2}));
    }

    private void fillItems(ArrayList<UItem> r1, UniversalAdapter r2) {
        r1.add(UItem.asCustom(-1, this.contentLayout));
    }

    private /* synthetic */ void lambda$new$0(Runnable r1, View r2) {
        if (r1 == null) goto L4;
        r1.run();
    L4:
        dismiss();
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        dismiss();
    }

    public static void show(Context r7, Theme.ResourcesProvider r8, int r9, TLObject r10, TLObject r11, Runnable r12) {
        new GuardBotReplaceSheet(r7, r8, r9, r10, r11, r12).show();
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(this.recyclerListView, getContext(), this.currentAccount, 0, true, new GuardBotReplaceSheet$$ExternalSyntheticLambda2(this), this.resourcesProvider);
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }
}
