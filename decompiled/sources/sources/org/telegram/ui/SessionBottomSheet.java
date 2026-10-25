package org.telegram.ui;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.exteragram.messenger.utils.ui.UIUtil;
import com.google.android.gms.cast.CredentialsData;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.SessionCell;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.Switch;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes6.dex */
public class SessionBottomSheet extends BottomSheet {
    RLottieImageView imageView;
    BaseFragment parentFragment;
    TLRPC.TL_authorization session;

    /* renamed from: org.telegram.ui.SessionBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ SessionBottomSheet this$0;

        public AnonymousClass1(SessionBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r2) {
            if (this.this$0.imageView.isPlaying() == false) goto L5;
            return;
        L5:
            if (this.this$0.imageView.getAnimatedDrawable() == null) goto L9;
            this.this$0.imageView.getAnimatedDrawable().setCurrentFrame(40);
            this.this$0.imageView.playAnimation();
            return;
        }
    }

    /* renamed from: org.telegram.ui.SessionBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ SessionBottomSheet this$0;
        final /* synthetic */ TLRPC.TL_authorization val$session;

        public AnonymousClass2(SessionBottomSheet r1, TLRPC.TL_authorization r2) {
            this.this$0 = r1;
            this.val$session = r2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r1) {
            SessionBottomSheet.m18780$$Nest$mcopyText(this.this$0, this.val$session.country);
        }
    }

    /* renamed from: org.telegram.ui.SessionBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 implements View.OnLongClickListener {
        final /* synthetic */ SessionBottomSheet this$0;
        final /* synthetic */ TLRPC.TL_authorization val$session;

        public AnonymousClass3(SessionBottomSheet r1, TLRPC.TL_authorization r2) {
            this.this$0 = r1;
            this.val$session = r2;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View r1) {
            SessionBottomSheet.m18780$$Nest$mcopyText(this.this$0, this.val$session.country);
            return true;
        }
    }

    /* renamed from: org.telegram.ui.SessionBottomSheet$4, reason: invalid class name */
    public class AnonymousClass4 implements View.OnClickListener {
        final /* synthetic */ SessionBottomSheet this$0;
        final /* synthetic */ TLRPC.TL_authorization val$session;

        public AnonymousClass4(SessionBottomSheet r1, TLRPC.TL_authorization r2) {
            this.this$0 = r1;
            this.val$session = r2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r1) {
            SessionBottomSheet.m18780$$Nest$mcopyText(this.this$0, this.val$session.ip);
        }
    }

    /* renamed from: org.telegram.ui.SessionBottomSheet$5, reason: invalid class name */
    public class AnonymousClass5 implements View.OnLongClickListener {
        final /* synthetic */ SessionBottomSheet this$0;
        final /* synthetic */ TLRPC.TL_authorization val$session;

        public AnonymousClass5(SessionBottomSheet r1, TLRPC.TL_authorization r2) {
            this.this$0 = r1;
            this.val$session = r2;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View r1) {
            SessionBottomSheet.m18780$$Nest$mcopyText(this.this$0, this.val$session.country);
            return true;
        }
    }

    /* renamed from: org.telegram.ui.SessionBottomSheet$6, reason: invalid class name */
    public class AnonymousClass6 implements View.OnClickListener {
        final /* synthetic */ SessionBottomSheet this$0;
        final /* synthetic */ ItemView val$acceptSecretChats;
        final /* synthetic */ TLRPC.TL_authorization val$session;

        public AnonymousClass6(SessionBottomSheet r1, ItemView r2, TLRPC.TL_authorization r3) {
            this.this$0 = r1;
            this.val$acceptSecretChats = r2;
            this.val$session = r3;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r3) {
            this.val$acceptSecretChats.switchView.setChecked(!r3.isChecked(), true);
            this.val$session.encrypted_requests_disabled = !this.val$acceptSecretChats.switchView.isChecked();
            SessionBottomSheet.m18781$$Nest$muploadSessionSettings(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.SessionBottomSheet$7, reason: invalid class name */
    public class AnonymousClass7 implements View.OnClickListener {
        final /* synthetic */ SessionBottomSheet this$0;
        final /* synthetic */ ItemView val$acceptCalls;
        final /* synthetic */ TLRPC.TL_authorization val$session;

        public AnonymousClass7(SessionBottomSheet r1, ItemView r2, TLRPC.TL_authorization r3) {
            this.this$0 = r1;
            this.val$acceptCalls = r2;
            this.val$session = r3;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r3) {
            this.val$acceptCalls.switchView.setChecked(!r3.isChecked(), true);
            this.val$session.call_requests_disabled = !this.val$acceptCalls.switchView.isChecked();
            SessionBottomSheet.m18781$$Nest$muploadSessionSettings(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.SessionBottomSheet$8, reason: invalid class name */
    public class AnonymousClass8 implements View.OnClickListener {
        final /* synthetic */ SessionBottomSheet this$0;
        final /* synthetic */ Callback val$callback;
        final /* synthetic */ BaseFragment val$fragment;
        final /* synthetic */ TLRPC.TL_authorization val$session;

        public static /* synthetic */ void $r8$lambda$nEa_QGrQN0IxNu6MfEnLyBzA3jw(AnonymousClass8 r0, Callback r1, TLRPC.TL_authorization r2, AlertDialog r3, int r4) {
            r0.lambda$onClick$0(r1, r2, r3, r4);
        }

        public AnonymousClass8(SessionBottomSheet r1, Callback r2, TLRPC.TL_authorization r3, BaseFragment r4) {
            this.this$0 = r1;
            this.val$callback = r2;
            this.val$session = r3;
            this.val$fragment = r4;
        }

        private /* synthetic */ void lambda$onClick$0(Callback r1, TLRPC.TL_authorization r2, AlertDialog r3, int r4) {
            r1.onSessionTerminated(r2);
            this.this$0.dismiss();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r5) {
            AlertDialog.Builder r52 = new AlertDialog.Builder(this.this$0.parentFragment.getParentActivity());
            r52.setMessage(LocaleController.getString(R.string.TerminateSessionText));
            r52.setTitle(LocaleController.getString(R.string.AreYouSureSessionTitle));
            r52.setPositiveButton(LocaleController.getString(R.string.Terminate), new SessionBottomSheet$8$$ExternalSyntheticLambda0(this, this.val$callback, this.val$session));
            r52.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
            AlertDialog r53 = r52.create();
            this.val$fragment.showDialog(r53);
            TextView r4 = (TextView) r53.getButton(-1);
            if (r4 == null) goto L6;
            r4.setTextColor(Theme.getColor(Theme.key_text_RedBold));
            return;
        }
    }

    public interface Callback {
        void onSessionTerminated(TLRPC.TL_authorization r1);
    }

    public static class ItemView extends FrameLayout {
        TextView descriptionText;
        ImageView iconView;
        boolean needDivider;
        Switch switchView;
        TextView valueText;

        public ItemView(Context r17, boolean r18) {
            super(r17);
            this.needDivider = false;
            ImageView r3 = new ImageView(r17);
            this.iconView = r3;
            r3.setScaleType(ImageView.ScaleType.CENTER);
            addView(this.iconView, LayoutHelper.createFrame(32, 32.0f, 0, 12.0f, 4.0f, 0.0f, 0.0f));
            LinearLayout r32 = new LinearLayout(r17);
            r32.setOrientation(1);
            addView(r32, LayoutHelper.createFrame(-1, -2.0f, 0, 64.0f, 4.0f, 0.0f, 4.0f));
            TextView r5 = new TextView(r17);
            this.valueText = r5;
            r5.setTextSize(2, 16.0f);
            this.valueText.setTypeface(AndroidUtilities.regular());
            this.valueText.setGravity(3);
            this.valueText.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            TextView r52 = this.valueText;
            if (r18 == false) goto L5;
            int r14 = 64;
        L6:
            r32.addView(r52, LayoutHelper.createLinear(-1, -2, 0, 0, 0, r14, 0));
            TextView r53 = new TextView(r17);
            this.descriptionText = r53;
            r53.setTextSize(2, 13.0f);
            this.descriptionText.setTypeface(AndroidUtilities.regular());
            this.descriptionText.setGravity(3);
            this.descriptionText.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
            TextView r54 = this.descriptionText;
            if (r18 == false) goto L9;
            int r142 = 64;
        L10:
            r32.addView(r54, LayoutHelper.createLinear(-1, -2, 0, 0, 4, r142, 0));
            setPadding(0, AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f));
            if (r18 == false) goto L13;
            Switch r33 = new Switch(r17);
            this.switchView = r33;
            r33.setDrawIconType(1);
            addView(this.switchView, LayoutHelper.createFrame(37, 40.0f, 21, 21.0f, 0.0f, 21.0f, 0.0f));
        L13:
            setClipChildren(false);
            return;
        L9:
            r142 = 0;
            goto L10
        L5:
            r14 = 0;
            goto L6
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            super.dispatchDraw(r8);
            if (this.needDivider == false) goto L6;
            r8.drawRect(AndroidUtilities.dp(64.0f), getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight(), Theme.dividerPaint);
            return;
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r4) {
            super.onInitializeAccessibilityNodeInfo(r4);
            if (this.switchView == null) goto L11;
            r4.setClassName("android.widget.Switch");
            r4.setCheckable(true);
            r4.setChecked(this.switchView.isChecked());
            StringBuilder r0 = new StringBuilder();
            r0.append(this.valueText.getText());
            r0.append("\n");
            r0.append(this.descriptionText.getText());
            r0.append("\n");
            if (this.switchView.isChecked() == false) goto L8;
            int r3 = R.string.NotificationsOn;
        L9:
            r0.append(LocaleController.getString(r3));
            r4.setText(r0.toString());
            return;
        L8:
            r3 = R.string.NotificationsOff;
            goto L9
        }
    }

    public static /* synthetic */ void $r8$lambda$COOKj4QkZIyqIndRnRelWdkQ7Aw(SessionBottomSheet r0, String r1, DialogInterface r2, int r3) {
        r0.lambda$copyText$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$vKDcJ0X1ZP1ewonnzJv9wWdCnF4(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: -$$Nest$mcopyText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18780$$Nest$mcopyText(SessionBottomSheet r0, String r1) {
        r0.copyText(r1);
    }

    /* renamed from: -$$Nest$muploadSessionSettings, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18781$$Nest$muploadSessionSettings(SessionBottomSheet r0) {
        r0.uploadSessionSettings();
    }

    public SessionBottomSheet(BaseFragment r20, TLRPC.TL_authorization r21, boolean r22, Callback r23) {
        super(r20.getParentActivity(), false);
        setOpenNoDelay(true);
        Activity r5 = r20.getParentActivity();
        this.session = r21;
        this.parentFragment = r20;
        fixNavigationBar();
        LinearLayout r6 = new LinearLayout(r5);
        r6.setOrientation(1);
        r6.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        RLottieImageView r7 = new RLottieImageView(r5);
        this.imageView = r7;
        r7.setOnClickListener(new AnonymousClass1(this));
        this.imageView.setScaleType(ImageView.ScaleType.CENTER);
        r6.addView(this.imageView, LayoutHelper.createLinear(70, 70, 1, 0, 16, 0, 0));
        TextView r72 = new TextView(r5);
        r72.setTextSize(2, 20.0f);
        r72.setTypeface(AndroidUtilities.bold());
        r72.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        r72.setGravity(17);
        r6.addView(r72, LayoutHelper.createLinear(-1, -2, 1, 21, 12, 21, 0));
        TextView r10 = new TextView(r5);
        r10.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
        r10.setTextSize(2, 13.0f);
        r10.setTypeface(AndroidUtilities.regular());
        r10.setGravity(17);
        r6.addView(r10, LayoutHelper.createLinear(-1, -2, 1, 21, 4, 21, 21));
        if ((r21.flags & 1) == 0) goto L5;
        String r8 = LocaleController.getString(R.string.Online);
    L6:
        r10.setText(r8);
        StringBuilder r82 = new StringBuilder();
        if (r21.device_model.length() == 0) goto L10;
        r82.append(r21.device_model);
    L10:
        if (r82.length() == 0) goto L12;
    L20:
        r72.setText(r82);
        setAnimation(r21, this.imageView);
        ItemView r73 = new ItemView(r5, false);
        StringBuilder r83 = new StringBuilder();
        r83.append(r21.app_name);
        r83.append(" ");
        r83.append(r21.app_version);
        r73.valueText.setText(r83);
        Drawable r84 = ContextCompat.getDrawable(r5, R.drawable.menu_devices).mutate();
        int r11 = Theme.key_windowBackgroundWhiteGrayIcon;
        int r12 = Theme.getColor(r11);
        PorterDuff.Mode r13 = PorterDuff.Mode.SRC_IN;
        r84.setColorFilter(new PorterDuffColorFilter(r12, r13));
        r73.iconView.setImageDrawable(r84);
        r73.descriptionText.setText(LocaleController.getString(R.string.Application));
        r6.addView(r73);
        if (r21.country.length() == 0) goto L24;
        ItemView r85 = new ItemView(r5, false);
        r85.valueText.setText(r21.country);
        Drawable r102 = ContextCompat.getDrawable(r5, R.drawable.msg_location).mutate();
        r102.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r11), r13));
        r85.iconView.setImageDrawable(r102);
        r85.descriptionText.setText(LocaleController.getString(R.string.Location));
        r85.setOnClickListener(new AnonymousClass2(this, r21));
        r85.setOnLongClickListener(new AnonymousClass3(this, r21));
        r85.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector), 2));
        r6.addView(r85);
        r73.needDivider = true;
        r73 = r85;
    L24:
        if (r21.ip.length() == 0) goto L27;
        ItemView r86 = new ItemView(r5, false);
        r86.valueText.setText(r21.ip);
        Drawable r103 = ContextCompat.getDrawable(r5, R.drawable.msg_language).mutate();
        r103.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r11), r13));
        r86.iconView.setImageDrawable(r103);
        r86.descriptionText.setText(LocaleController.getString(R.string.IpAddress));
        r86.setOnClickListener(new AnonymousClass4(this, r21));
        r86.setOnLongClickListener(new AnonymousClass5(this, r21));
        r86.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector), 2));
        r6.addView(r86);
        r73.needDivider = true;
        r73 = r86;
    L27:
        if (secretChatsEnabled(r21) == false) goto L30;
        ItemView r87 = new ItemView(r5, true);
        r87.valueText.setText(LocaleController.getString(R.string.AcceptSecretChats));
        Drawable r104 = ContextCompat.getDrawable(r5, R.drawable.msg_secret).mutate();
        r104.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r11), r13));
        r87.iconView.setImageDrawable(r104);
        r87.switchView.setChecked(!r21.encrypted_requests_disabled, false);
        r87.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector), 7));
        r87.setOnClickListener(new AnonymousClass6(this, r87, r21));
        r73.needDivider = true;
        r87.descriptionText.setText(LocaleController.getString(R.string.AcceptSecretChatsDescription));
        r6.addView(r87);
        r73 = r87;
    L30:
        if (acceptCallsEnabled(r21) == false) goto L32;
        ItemView r88 = new ItemView(r5, true);
        r88.valueText.setText(LocaleController.getString(R.string.AcceptCalls));
        Drawable r105 = ContextCompat.getDrawable(r5, R.drawable.msg_calls).mutate();
        r105.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r11), r13));
        r88.iconView.setImageDrawable(r105);
        r88.switchView.setChecked(!r21.call_requests_disabled, false);
        r88.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector), 7));
        r88.setOnClickListener(new AnonymousClass7(this, r88, r21));
        r73.needDivider = true;
        r88.descriptionText.setText(LocaleController.getString(R.string.AcceptCallsChatsDescription));
        r6.addView(r88);
    L32:
        if (r22 == true) goto L34;
        ButtonWithCounterView r3 = new ButtonWithCounterView(r5, null);
        r3.setText(LocaleController.getString(R.string.TerminateSession), false);
        r6.addView(r3, LayoutHelper.createFrame(-1, 48.0f, 0, 16.0f, 15.0f, 16.0f, 16.0f));
        r3.setOnClickListener(new AnonymousClass8(this, r23, r21, r20));
    L34:
        ScrollView r1 = new ScrollView(r5);
        r1.addView(r6);
        setCustomView(r1);
        return;
    L12:
        if (r21.platform.length() == 0) goto L15;
        r82.append(r21.platform);
    L15:
        if (r21.system_version.length() == 0) goto L20;
        if (r21.platform.length() == 0) goto L19;
        r82.append(" ");
    L19:
        r82.append(r21.system_version);
        goto L20
    L5:
        r8 = LocaleController.formatDateTime(r21.date_active, true);
        goto L6
    }

    private boolean acceptCallsEnabled(TLRPC.TL_authorization r1) {
        if (r1.api_id == 22) goto L6;
        return true;
    L6:
        return false;
    }

    private void copyText(String r5) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(getContext());
        r0.setItems(new CharSequence[]{LocaleController.getString(R.string.Copy)}, new SessionBottomSheet$$ExternalSyntheticLambda0(this, r5));
        r0.show();
    }

    private /* synthetic */ void lambda$copyText$1(String r1, DialogInterface r2, int r3) {
        ((ClipboardManager) ApplicationLoader.applicationContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", r1));
        BulletinFactory.of(getContainer(), null).createCopyBulletin(LocaleController.getString(R.string.TextCopied)).show();
    }

    private boolean secretChatsEnabled(TLRPC.TL_authorization r1) {
        int r0 = r1.api_id;
        if (r0 != 2040) goto L5;
        return false;
    L5:
        if (r0 == 2496) goto L10;
        return true;
    L10:
        return false;
    }

    private void setAnimation(TLRPC.TL_authorization r9, RLottieImageView r10) {
        String r0 = r9.platform.toLowerCase();
        if (r0.isEmpty() == false) goto L5;
        r0 = r9.system_version.toLowerCase();
    L5:
        String r1 = r9.device_model.toLowerCase();
        boolean r4 = true;
        if (r1.contains("safari") == false) goto L9;
        int r02 = R.raw.safari_30;
        int r12 = Theme.key_avatar_backgroundPink;
        int r2 = Theme.key_avatar_background2Pink;
    L58:
        boolean r92 = r9.app_name.toLowerCase().contains("exteragram");
        if (r92 == false) goto L61;
        r10.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(42.0f), ContextCompat.getColor(ApplicationLoader.applicationContext, R.color.ic_background)));
    L62:
        if (r4 == false) goto L66;
        if (r92 == true) goto L66;
        r10.setAnimation(r02, 50, 50, new int[]{0, Theme.getColor(r12)});
        return;
    L66:
        Context r8 = getContext();
        if (r92 == false) goto L69;
        r02 = R.drawable.ic_foreground;
    L69:
        Drawable r82 = ContextCompat.getDrawable(r8, r02).mutate();
        if (r92 == false) goto L72;
        r82 = new BitmapDrawable(ApplicationLoader.applicationContext.getResources(), UIUtil.drawableToBitmap(r82, AndroidUtilities.dp(70.0f), AndroidUtilities.dp(70.0f)));
    L72:
        r10.setImageDrawable(r82);
        return;
    L61:
        r10.setBackground(new SessionCell.CircleGradientDrawable(AndroidUtilities.dp(42.0f), Theme.getColor(r12), Theme.getColor(r2)));
        goto L62
    L9:
        if (r1.contains("edge") == false) goto L12;
        r02 = R.raw.edge_30;
        r12 = Theme.key_avatar_backgroundPink;
        r2 = Theme.key_avatar_background2Pink;
        goto L58
    L12:
        if (r1.contains("chrome") == false) goto L15;
        r02 = R.raw.chrome_30;
        r12 = Theme.key_avatar_backgroundPink;
        r2 = Theme.key_avatar_background2Pink;
        goto L58
    L15:
        if (r1.contains("firefox") == false) goto L18;
        r02 = R.raw.firefox_30;
        r12 = Theme.key_avatar_backgroundRed;
        r2 = Theme.key_avatar_background2Red;
        goto L58
    L18:
        if (r1.contains("opera") == true) goto L51;
        if (r1.contains("firefox") == true) goto L51;
        if (r1.contains("vivaldi") == true) goto L51;
        if (r0.contains("ubuntu") == false) goto L28;
        r02 = R.raw.ubuntu_30;
        r12 = Theme.key_avatar_backgroundBlue;
        r2 = Theme.key_avatar_background2Blue;
        goto L58
    L28:
        if (r0.contains("linux") == false) goto L31;
        r02 = R.raw.linux_30;
        r12 = Theme.key_avatar_backgroundBlue;
        r2 = Theme.key_avatar_background2Blue;
        goto L58
    L31:
        if (r0.contains(CredentialsData.CREDENTIALS_TYPE_IOS) == false) goto L38;
        if (r1.contains("ipad") == false) goto L35;
        r02 = R.raw.ipad_30;
    L36:
        r12 = Theme.key_avatar_backgroundBlue;
        r2 = Theme.key_avatar_background2Blue;
        goto L58
    L35:
        r02 = R.raw.iphone_30;
        goto L36
    L38:
        if (r0.contains("windows") == false) goto L41;
        r02 = R.raw.windows_30;
        r12 = Theme.key_avatar_backgroundCyan;
        r2 = Theme.key_avatar_background2Cyan;
        goto L58
    L41:
        if (r0.contains("macos") == false) goto L44;
        r02 = R.raw.mac_30;
        r12 = Theme.key_avatar_backgroundCyan;
        r2 = Theme.key_avatar_background2Cyan;
        goto L58
    L44:
        if (r0.contains("android") == false) goto L47;
        r02 = R.raw.android_30;
        r12 = Theme.key_avatar_backgroundGreen;
        r2 = Theme.key_avatar_background2Green;
        goto L58
    L47:
        if (r9.app_name.toLowerCase().contains("desktop") == false) goto L49;
        r02 = R.raw.windows_30;
        r12 = Theme.key_avatar_backgroundCyan;
        r2 = Theme.key_avatar_background2Cyan;
        goto L58
    L49:
        r02 = R.raw.chrome_30;
        r12 = Theme.key_avatar_backgroundPink;
        r2 = Theme.key_avatar_background2Pink;
    L51:
        if (r1.contains("opera") == false) goto L54;
        r02 = R.drawable.device_web_opera;
    L57:
        r12 = Theme.key_avatar_backgroundPink;
        r2 = Theme.key_avatar_background2Pink;
        r4 = false;
        goto L58
    L54:
        if (r1.contains("firefox") == false) goto L56;
        r02 = R.drawable.device_web_firefox;
        goto L57
    L56:
        r02 = R.drawable.device_web_other;
        goto L57
    }

    private void uploadSessionSettings() {
        TL_account.changeAuthorizationSettings r0 = new TL_account.changeAuthorizationSettings();
        TLRPC.TL_authorization r1 = this.session;
        r0.encrypted_requests_disabled = r1.encrypted_requests_disabled;
        r0.call_requests_disabled = r1.call_requests_disabled;
        r0.flags = 3;
        r0.hash = r1.hash;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new SessionBottomSheet$$ExternalSyntheticLambda1());
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        this.imageView.playAnimation();
    }
}
