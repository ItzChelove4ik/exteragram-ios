package org.telegram.ui.Components;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import org.mvel2.asm.signature.SignatureVisitor;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class TermsOfServiceView extends FrameLayout {
    private int currentAccount;
    private TLRPC.TL_help_termsOfService currentTos;
    private TermsOfServiceViewDelegate delegate;
    private ScrollView scrollView;
    private TextView textView;
    private TextView titleTextView;

    public interface TermsOfServiceViewDelegate {
        void onAcceptTerms(int r1);
    }

    public static /* synthetic */ void $r8$lambda$0ceETEcLxiCcxdHHGAN6f2ptKlU(TermsOfServiceView r0, AlertDialog r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$new$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$ArtBy24_nE4ZqReVldQQWa6p0AM(TermsOfServiceView r0, View r1) {
        r0.lambda$new$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$IHsW4oZSM2ZmUpUnTAZ9WWlqjvA(TermsOfServiceView r0, AlertDialog r1, int r2) {
        r0.lambda$new$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Q5jR4CwkF0yQ7i6obseNrYk41DM(TLObject r0, TLRPC.TL_error r1) {
    }

    public static /* synthetic */ void $r8$lambda$YW71V4DktXxnDs0Cvz2rFyD7DqQ(TermsOfServiceView r0, View r1) {
        r0.lambda$new$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$i7OhHPJ5ApPLCn3kD6PGzCuVmJk(TermsOfServiceView r0, AlertDialog r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$new$0(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$iZaUmCs5qeJFk9lLzRkIl2lcRsA(TermsOfServiceView r0, AlertDialog r1, int r2) {
        r0.lambda$new$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$o4uXWhM85_IE0MwBwsV6kGz9MGA(TermsOfServiceView r0, AlertDialog r1, int r2) {
        r0.lambda$new$5(r1, r2);
    }

    public TermsOfServiceView(Context r21) {
        super(r21);
        setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        int r2 = AndroidUtilities.statusBarHeight;
        if (r2 <= 0) goto L5;
        View r4 = new View(r21);
        r4.setBackgroundColor(-16777216);
        addView(r4, new FrameLayout.LayoutParams(-1, r2));
    L5:
        LinearLayout r42 = new LinearLayout(r21);
        r42.setOrientation(1);
        ImageView r6 = new ImageView(r21);
        r6.setImageResource(R.drawable.logo_middle);
        r42.addView(r6, LayoutHelper.createLinear(-2, -2, 3, 0, 28, 0, 0));
        TextView r62 = new TextView(r21);
        this.titleTextView = r62;
        int r7 = Theme.key_windowBackgroundWhiteBlackText;
        r62.setTextColor(Theme.getColor(r7));
        this.titleTextView.setTextSize(1, 17.0f);
        this.titleTextView.setTypeface(AndroidUtilities.bold());
        this.titleTextView.setText(LocaleController.getString(R.string.PrivacyPolicyAndTerms));
        r42.addView(this.titleTextView, LayoutHelper.createLinear(-2, -2, 3, 0, 20, 0, 0));
        TextView r63 = new TextView(r21);
        this.textView = r63;
        r63.setTextColor(Theme.getColor(r7));
        this.textView.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText));
        this.textView.setTextSize(1, 15.0f);
        this.textView.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        this.textView.setGravity(51);
        this.textView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        r42.addView(this.textView, LayoutHelper.createLinear(-1, -2, 3, 0, 15, 0, 15));
        ScrollView r64 = new ScrollView(r21);
        this.scrollView = r64;
        r64.setVerticalScrollBarEnabled(false);
        this.scrollView.setOverScrollMode(2);
        this.scrollView.setPadding(AndroidUtilities.dp(24.0f), r2, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(75.0f));
        this.scrollView.addView(r42, new FrameLayout.LayoutParams(-1, -2));
        addView(this.scrollView, LayoutHelper.createLinear(-1, -2));
        TextView r22 = new TextView(r21);
        r22.setText(LocaleController.getString(R.string.Decline).toUpperCase());
        r22.setGravity(17);
        r22.setTypeface(AndroidUtilities.bold());
        int r65 = Theme.key_windowBackgroundWhiteGrayText;
        r22.setTextColor(Theme.getColor(r65));
        r22.setTextSize(1, 14.0f);
        r22.setBackground(Theme.getRoundRectSelectorDrawable(Theme.getColor(r65)));
        r22.setPadding(AndroidUtilities.dp(20.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(10.0f));
        addView(r22, LayoutHelper.createFrame(-2, -2.0f, 83, 16.0f, 0.0f, 16.0f, 16.0f));
        r22.setOnClickListener(new TermsOfServiceView$$ExternalSyntheticLambda0(this));
        TextView r23 = new TextView(r21);
        r23.setText(LocaleController.getString(R.string.Accept));
        r23.setGravity(17);
        r23.setTypeface(AndroidUtilities.bold());
        r23.setTextColor(-1);
        r23.setTextSize(1, 14.0f);
        r23.setBackgroundDrawable(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(4.0f), -11491093, -12346402));
        r23.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        addView(r23, LayoutHelper.createFrame(-2, 42.0f, 85, 16.0f, 0.0f, 16.0f, 16.0f));
        r23.setOnClickListener(new TermsOfServiceView$$ExternalSyntheticLambda1(this));
        View r24 = new View(r21);
        r24.setBackgroundColor(Theme.getColor(Theme.key_divider));
        FrameLayout.LayoutParams r1 = new FrameLayout.LayoutParams(-1, 1);
        r1.bottomMargin = AndroidUtilities.dp(75.0f);
        r1.gravity = 80;
        addView(r24, r1);
    }

    private void accept() {
        this.delegate.onAcceptTerms(this.currentAccount);
        TLRPC.TL_help_acceptTermsOfService r0 = new TLRPC.TL_help_acceptTermsOfService();
        r0.id = this.currentTos.id;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new TermsOfServiceView$$ExternalSyntheticLambda4());
    }

    private static void addBulletsToText(SpannableStringBuilder r7, char r8, int r9, int r10, int r11) {
        int r0 = r7.length() - 2;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L12;
        if (r7.charAt(r1) != '\n') goto L11;
        int r2 = r1 + 1;
        if (r7.charAt(r2) != r8) goto L11;
        int r3 = r1 + 2;
        if (r7.charAt(r3) != ' ') goto L11;
        BulletSpan r4 = new BulletSpan(r9, r10, r11);
        r7.replace(r2, r1 + 3, "\u0000\u0000");
        r7.setSpan(r4, r2, r3, 33);
    L11:
        r1 = r1 + 1;
        goto L3
    }

    private /* synthetic */ void lambda$new$0(AlertDialog r1, TLObject r2, TLRPC.TL_error r3) {
        r1.dismiss();     // Catch: Exception -> L4
    L7:
        if ((r2 instanceof TLRPC.TL_boolTrue) == false) goto L9;
        MessagesController.getInstance(this.currentAccount).performLogout(0);
        return;
    L9:
        if (r3 != null) goto L11;
    L12:
        String r12 = LocaleController.getString(R.string.ErrorOccurred);
        if (r3 == null) goto L15;
        r12 = r12 + "\n" + r3.text;
    L15:
        AlertDialog.Builder r22 = new AlertDialog.Builder(getContext());
        r22.setTitle(LocaleController.getString(R.string.AppName));
        r22.setMessage(r12);
        r22.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r22.show();
        return;
    L11:
        if (r3.code != (-1000)) goto L12;
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    private /* synthetic */ void lambda$new$1(AlertDialog r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new TermsOfServiceView$$ExternalSyntheticLambda7(this, r2, r3, r4));
    }

    private /* synthetic */ void lambda$new$2(AlertDialog r3, int r4) {
        AlertDialog r32 = new AlertDialog(getContext(), 3);
        r32.setCanCancel(false);
        TL_account.deleteAccount r42 = new TL_account.deleteAccount();
        r42.reason = "Decline ToS update";
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r42, new TermsOfServiceView$$ExternalSyntheticLambda6(this, r32));
        r32.show();
    }

    private /* synthetic */ void lambda$new$3(AlertDialog r2, int r3) {
        AlertDialog.Builder r22 = new AlertDialog.Builder(getContext());
        r22.setMessage(LocaleController.getString(R.string.TosDeclineDeleteAccount));
        r22.setTitle(LocaleController.getString(R.string.AppName));
        r22.setPositiveButton(LocaleController.getString(R.string.Deactivate), new TermsOfServiceView$$ExternalSyntheticLambda5(this));
        r22.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r22.show();
    }

    private /* synthetic */ void lambda$new$4(View r3) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(r3.getContext());
        r0.setTitle(LocaleController.getString(R.string.TermsOfService));
        r0.setPositiveButton(LocaleController.getString(R.string.DeclineDeactivate), new TermsOfServiceView$$ExternalSyntheticLambda3(this));
        r0.setNegativeButton(LocaleController.getString(R.string.Back), null);
        r0.setMessage(LocaleController.getString(R.string.TosUpdateDecline));
        r0.show();
    }

    private /* synthetic */ void lambda$new$5(AlertDialog r1, int r2) {
        accept();
    }

    private /* synthetic */ void lambda$new$6(View r4) {
        if (this.currentTos.min_age_confirm == 0) goto L6;
        AlertDialog.Builder r0 = new AlertDialog.Builder(r4.getContext());
        r0.setTitle(LocaleController.getString(R.string.TosAgeTitle));
        r0.setPositiveButton(LocaleController.getString(R.string.Agree), new TermsOfServiceView$$ExternalSyntheticLambda2(this));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r0.setMessage(LocaleController.formatString("TosAgeText", R.string.TosAgeText, new Object[]{LocaleController.formatPluralString("Years", this.currentTos.min_age_confirm, new Object[0])}));
        r0.show();
        return;
    L6:
        accept();
    }

    public void setDelegate(TermsOfServiceViewDelegate r1) {
        this.delegate = r1;
    }

    public void show(int r8, TLRPC.TL_help_termsOfService r9) {
        if (getVisibility() == 0) goto L5;
        setVisibility(0);
    L5:
        SpannableStringBuilder r1 = new SpannableStringBuilder(r9.text);
        MessageObject.addEntitiesToText(r1, r9.entities, false, false, false, false);
        addBulletsToText(r1, SignatureVisitor.SUPER, AndroidUtilities.dp(10.0f), -11491093, AndroidUtilities.dp(4.0f));
        this.textView.setText(r1);
        this.currentTos = r9;
        this.currentAccount = r8;
    }
}
