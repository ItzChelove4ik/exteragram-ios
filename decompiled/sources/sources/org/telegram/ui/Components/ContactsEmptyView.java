package org.telegram.ui.Components;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class ContactsEmptyView extends LinearLayout {
    private final ButtonWithCounterView button;
    private final int currentAccount;
    private final LoadingStickerDrawable drawable;
    private final BackupImageView stickerView;
    private final TextView subtitleTextView;
    private final TextView titleTextView;

    public ContactsEmptyView(Context r13) {
        super(r13);
        this.currentAccount = UserConfig.selectedAccount;
        setOrientation(1);
        BackupImageView r1 = new BackupImageView(r13);
        this.stickerView = r1;
        LoadingStickerDrawable r2 = new LoadingStickerDrawable(r1, "m418 282.6c13.4-21.1 20.2-44.9 20.2-70.8 0-88.3-79.8-175.3-178.9-175.3-100.1 0-178.9 88-178.9 175.3 0 46.6 16.9 73.1 29.1 86.1-19.3 23.4-30.9 52.3-34.6 86.1-2.5 22.7 3.2 41.4 17.4 57.3 14.3 16 51.7 35 148.1 35 41.2 0 119.9-5.3 156.7-18.3 49.5-17.4 59.2-41.1 59.2-76.2 0-41.5-12.9-74.8-38.3-99.2z", AndroidUtilities.dp(110.0f), AndroidUtilities.dp(110.0f));
        this.drawable = r2;
        r1.setImageDrawable(r2);
        if (AndroidUtilities.isTablet() == true) goto L5;
        addView(r1, LayoutHelper.createLinear(110, 110, 49));
    L5:
        TextView r12 = new TextView(r13);
        this.titleTextView = r12;
        r12.setTextSize(1, 20.0f);
        r12.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        r12.setGravity(1);
        r12.setText(LocaleController.getString(R.string.NoContactsYet3));
        r12.setTypeface(AndroidUtilities.bold());
        addView(r12, LayoutHelper.createLinear(-2, -2, 49, 0, 15, 0, 7));
        TextView r14 = new TextView(r13);
        this.subtitleTextView = r14;
        r14.setTextSize(1, 14.0f);
        r14.setTextColor(Theme.getColor(Theme.key_emptyListPlaceholder));
        r14.setGravity(1);
        r14.setText(LocaleController.getString(R.string.NoContactsYet3Sub));
        r14.setMaxWidth(AndroidUtilities.dp(260.0f));
        r14.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        addView(r14, LayoutHelper.createLinear(-2, -2, 49, 0, 0, 0, 19));
        ButtonWithCounterView r15 = new ButtonWithCounterView(r13, null);
        this.button = r15;
        r15.setUseWrapContent(true);
        r15.setRound();
        r15.setPadding(AndroidUtilities.dp(28.0f), 0, AndroidUtilities.dp(28.0f), 0);
        SpannableStringBuilder r132 = new SpannableStringBuilder("c");
        r132.setSpan(new ColoredImageSpan(R.drawable.filled_new_contact_24), 0, 1, 33);
        r132.append("  ").append(LocaleController.getString(R.string.NewContact));
        r15.setText(r132, false);
        addView(r15, LayoutHelper.createLinear(-2, 44, 49));
    }

    private void setSticker() {
        this.stickerView.setImageDrawable(new RLottieDrawable(R.raw.utyan_empty, AndroidUtilities.dp(110.0f), AndroidUtilities.dp(110.0f)));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setSticker();
    }
}
