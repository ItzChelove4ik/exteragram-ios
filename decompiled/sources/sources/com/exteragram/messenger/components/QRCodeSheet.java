package com.exteragram.messenger.components;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSuggestion;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.exteragram.messenger.utils.system.SystemUtils;
import com.exteragram.messenger.utils.text.LocaleUtils;
import com.google.android.gms.cast.MediaError;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LinkifyPort;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.TelegramQRCodeWriter;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.StickerImageView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes4.dex */
public class QRCodeSheet extends BottomSheet {
    private final int TEXT_TYPE_AUTH_TOKEN;
    private final int TEXT_TYPE_LINK;
    private final int TEXT_TYPE_PHONE;
    private final int TEXT_TYPE_TEXT;
    private final int TEXT_TYPE_WIFI;
    private final BaseFragment fragment;
    private String password;
    private String ssid;
    private String wifiAuthType;

    /* renamed from: com.exteragram.messenger.components.QRCodeSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends View {
        final /* synthetic */ QRCodeSheet this$0;

        public AnonymousClass1(QRCodeSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r7) {
            super.onDraw(r7);
            int r0 = getMeasuredWidth();
            RectF r1 = new RectF();
            r1.set((getMeasuredWidth() - r0) / 2.0f, 0.0f, (getMeasuredWidth() + r0) / 2.0f, AndroidUtilities.dp(4.0f));
            Theme.dialogs_onlineCirclePaint.setColor(QRCodeSheet.access$000(this.this$0, Theme.key_sheet_scrollUp));
            r7.drawRoundRect(r1, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), Theme.dialogs_onlineCirclePaint);
        }
    }

    /* renamed from: $r8$lambda$-0lZX-bxG1wRE-10HOyuUIy8mVI, reason: not valid java name */
    public static /* synthetic */ void m1211$r8$lambda$0lZXbxG1wRE10HOyuUIy8mVI(QRCodeSheet r0, int r1, String r2, BaseFragment r3, View r4) {
        r0.lambda$new$6(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$0ZmsSGcq21YmY3MVv0zggDt1EL8(QRCodeSheet r0, Bitmap r1, Activity r2, View r3) {
        r0.lambda$new$0(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$33o0_blAcXVlqmnT_GtrungUPfY(QRCodeSheet r0, String r1, View r2) {
        r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$9Pqkc7nOqaUvVlzP8V9elIcmB0c(QRCodeSheet r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$new$3(r1, r2);
    }

    /* renamed from: $r8$lambda$G16iu-b4U56tddThc8wkV1_QwRM, reason: not valid java name */
    public static /* synthetic */ void m1212$r8$lambda$G16iub4U56tddThc8wkV1_QwRM(QRCodeSheet r0, int r1, BaseFragment r2, String r3, View r4) {
        r0.lambda$new$2(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$K01s3iuavcEmQtuBOHRS8Rzix0E(QRCodeSheet r0) {
        r0.connectToWifi();
    }

    /* renamed from: $r8$lambda$PYj7O-x65AsduMp2kYCLbd85UBo, reason: not valid java name */
    public static /* synthetic */ void m1213$r8$lambda$PYj7Ox65AsduMp2kYCLbd85UBo(BaseFragment r2) {
        AlertsCreator.showSimpleAlert(r2, LocaleController.getString(R.string.AuthAnotherClient), LocaleController.getString(R.string.ErrorOccurred));
    }

    public static /* synthetic */ void $r8$lambda$TEh0JQBnGYSJ6mpVEeHEBq0i788(QRCodeSheet r0, String r1) {
        r0.lambda$showErrorBulletin$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$abc7RozZkIfrf5ggoSHifyOepUE(QRCodeSheet r0, String r1, BaseFragment r2) {
        r0.lambda$new$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$jWw6Mz5sq0VtlM5PUYHQz4qoZww(QRCodeSheet r0) {
        r0.lambda$copyQR$7();
    }

    /* renamed from: $r8$lambda$ya-M6Lvo5WAwm-UkDGOievE79Lc, reason: not valid java name */
    public static /* synthetic */ void m1214$r8$lambda$yaM6Lvo5WAwmUkDGOievE79Lc(QRCodeSheet r0, boolean r1) {
        r0.lambda$showCopyBulletin$9(r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v1, types: [android.view.View, android.view.ViewGroup, android.widget.FrameLayout] */
    /* JADX WARN: Type inference failed for: r12v3, types: [android.view.View, android.view.ViewGroup, android.widget.LinearLayout] */
    /* JADX WARN: Type inference failed for: r1v2, types: [android.view.View, android.widget.ScrollView] */
    /* JADX WARN: Type inference failed for: r27v0, types: [com.exteragram.messenger.components.QRCodeSheet, org.telegram.ui.ActionBar.BottomSheet] */
    /* JADX WARN: Type inference failed for: r6v13, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v6, types: [android.text.Spanned] */
    /* JADX WARN: Type inference failed for: r6v9, types: [java.lang.String] */
    public QRCodeSheet(BaseFragment r28, String r29) {
        String r2 = r29;
        super(r28.getParentActivity(), false, r28.getResourceProvider());
        this.TEXT_TYPE_LINK = 0;
        this.TEXT_TYPE_TEXT = 1;
        this.TEXT_TYPE_AUTH_TOKEN = 2;
        int r6 = 3;
        this.TEXT_TYPE_PHONE = 3;
        this.TEXT_TYPE_WIFI = 4;
        this.wifiAuthType = "WPA";
        this.fragment = r28;
        if (r2.startsWith("tg://login?token=") == false) goto L6;
        ?? r62 = LocaleController.getString(R.string.Cancel);
        CharSequence r9 = LocaleController.getString(R.string.Allow);
        SpannableStringBuilder r8 = r62;
        r6 = 2;
    L19:
        Activity r10 = r28.getParentActivity();
        fixNavigationBar();
        ?? r11 = new FrameLayout(r10);
        ?? r12 = new LinearLayout(r10);
        r12.setOrientation(1);
        r11.addView(r12);
        r12.addView(new AnonymousClass1(this, r28.getParentActivity()), LayoutHelper.createLinear(36, 4, 1, 18, 2, 18, 0));
        if (r6 != 2) goto L22;
        StickerImageView r13 = new StickerImageView(r10, this.currentAccount);
        r13.setStickerPackName(AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME);
        r13.setStickerNum(6);
        r13.getImageReceiver().setAutoRepeat(1);
        r13.getImageReceiver().setAutoRepeatCount(1);
        r12.addView(r13, LayoutHelper.createLinear(144, 144, 1, 0, 20, 0, 10));
    L23:
        TextView r132 = new TextView(r10);
        ScaleStateListAnimator.apply(r132, 0.02f, 1.5f);
        r132.setGravity(1);
        r132.setTextSize(1, 14.0f);
        r132.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(4.0f));
        int r3 = Theme.key_windowBackgroundWhiteGrayText;
        r132.setTextColor(getThemedColor(r3));
        if (r6 == 4) goto L26;
    L32:
        if (r6 != 2) goto L34;
        String r5 = LocaleController.getString(R.string.AreYouSureToLogin);
    L35:
        r132.setText(r5);
    L36:
        if (r6 != 0) goto L38;
        r2 = LocaleUtils.ensureUrlHasHttps(r2);
    L38:
        if (r6 == 2) goto L44;
        int r32 = getThemedColor(r3);
        if (Theme.isCurrentThemeDark() == false) goto L42;
        float r52 = 0.2f;
    L43:
        r132.setBackground(Theme.createSelectorDrawable(Theme.multAlpha(r32, r52), 7, AndroidUtilities.dp(8.0f)));
        r132.setOnClickListener(new QRCodeSheet$$ExternalSyntheticLambda2(this, r2));
        goto L44
    L42:
        r52 = 0.15f;
    L44:
        r12.addView(r132, LayoutHelper.createLinear(-2, -2, 1, 21, 2, 21, 8));
        LinearLayout r33 = new LinearLayout(r10);
        r33.setOrientation(0);
        r12.addView(r33, LayoutHelper.createLinear(-1, 48, 16.0f, 15.0f, 16.0f, 4.0f));
        ButtonWithCounterView r53 = new ButtonWithCounterView(r10, r28.getResourceProvider());
        r53.setRound();
        r53.setText(r8, false);
        r53.setOnClickListener(new QRCodeSheet$$ExternalSyntheticLambda3(this, r6, r28, r2));
        if (r6 != 2) goto L47;
        r53.setNeutral();
    L47:
        r33.addView(r53, LayoutHelper.createLinear(0, -1, 1.0f));
        r33.addView(new View(r10), LayoutHelper.createLinear(0, -1, 0.06f));
        ButtonWithCounterView r54 = new ButtonWithCounterView(r10, r28.getResourceProvider());
        r54.setRound();
        r54.setText(r9, false);
        r54.setFilled(true);
        r54.setOnClickListener(new QRCodeSheet$$ExternalSyntheticLambda4(this, r6, r2, r28));
        r33.addView(r54, LayoutHelper.createLinear(0, -1, 1.0f));
        ?? r1 = new ScrollView(r10);
        r1.addView(r11);
        setCustomView(r1);
        return;
    L34:
        r5 = r2;
        goto L35
    L26:
        if (TextUtils.isEmpty(this.ssid) == true) goto L32;
        String r55 = this.ssid;
        if (TextUtils.isEmpty(this.password) == true) goto L30;
        String r7 = ", Password: " + this.password;
    L31:
        r132.setText(MessageFormat.format("SSID: {0}{1}", new Object[]{r55, r7}));
        goto L36
    L30:
        r7 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L31
    L22:
        ImageView r133 = new ImageView(r10);
        ScaleStateListAnimator.apply(r133, 0.03f, 1.2f);
        r133.setScaleType(ImageView.ScaleType.FIT_XY);
        r133.setOutlineProvider(ViewOutlineProviderImpl.boundsWithRoundRect(AndroidUtilities.dp(12.0f)));
        r133.setClipToOutline(true);
        Bitmap r14 = createQR(r2);
        r133.setImageBitmap(r14);
        r133.setOnClickListener(new QRCodeSheet$$ExternalSyntheticLambda1(this, r14, r10));
        r12.addView(r133, LayoutHelper.createLinear(200, 200, 1, 18, 20, 18, 10));
        goto L23
    L6:
        if (LinkifyPort.WEB_URL.matcher(r2).matches() == true) goto L15;
        if (r2.startsWith("tel:") == true) goto L15;
        if (r2.startsWith("WIFI:") == false) goto L13;
        parseWifiInfo(r2);
        ?? r63 = LocaleController.getString(R.string.WifiConnect);
        r9 = getTextWithIcon("share");
        r8 = r63;
        r6 = 4;
        goto L19
    L13:
        ?? r64 = getTextWithIcon("copy");
        r9 = getTextWithIcon("share");
        r8 = r64;
        r6 = 1;
    L15:
        if (r2.startsWith("tel:") == true) goto L18;
        r6 = 0;
    L18:
        r8 = new SpannableStringBuilder(LocaleController.getString(R.string.Open)).append(".");
        int r92 = r8.length();
        r8.setSpan(new ColoredImageSpan(ContextCompat.getDrawable(r28.getParentActivity(), R.drawable.msg_mini_topicarrow)), r92 - 1, r92, 0);
        r9 = getTextWithIcon("share");
        goto L19
    }

    public static /* synthetic */ int access$000(QRCodeSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    private void connectToWifi() {
        if (TextUtils.isEmpty(this.ssid) == false) goto L6;
        showErrorBulletin(LocaleController.getString(R.string.WifiFailed));
        return;
    L6:
        WifiManager r0 = (WifiManager) ApplicationLoader.applicationContext.getSystemService("wifi");
        if (r0 != null) goto L11;
        showErrorBulletin(LocaleController.getString(R.string.WifiFailed));
        return;
    L11:
        if (r0.isWifiEnabled() == true) goto L19;
        if (Build.VERSION.SDK_INT < 29) goto L16;
        showErrorBulletin(LocaleController.getString(R.string.WifiDisabled));
        Intent r02 = new Intent("android.settings.panel.action.WIFI");
        this.fragment.startActivityForResult(r02, 501);
        return;
    L16:
        showErrorBulletin(LocaleController.getString(R.string.WifiDisabled));
        return;
    L19:
        if (Build.VERSION.SDK_INT < 29) goto L22;
        connectWifiModern(r0);
        return;
    L22:
        connectWifiLegacy(r0);
    }

    private void connectWifiLegacy(WifiManager r7) {
        WifiConfiguration r0 = new WifiConfiguration();
        r0.SSID = String.format("\"%s\"", new Object[]{this.ssid});
        if (TextUtils.isEmpty(this.wifiAuthType) == false) goto L5;
    L8:
        boolean r1 = true;
    L10:
        if (TextUtils.isEmpty(this.password) == true) goto L18;
        if (r1 == true) goto L18;
        if ("WPA".equalsIgnoreCase(this.wifiAuthType) == false) goto L16;
        r0.preSharedKey = String.format("\"%s\"", new Object[]{this.password});
    L19:
        int r02 = r7.addNetwork(r0);
        if (r02 != (-1)) goto L22;
        showErrorBulletin(LocaleController.getString(R.string.WifiFailed));
        return;
    L22:
        if (r7.enableNetwork(r02, true) == false) goto L25;
        r7.reconnect();
        BulletinFactory.of(this.fragment).createSimpleBulletin(R.raw.contact_check, LocaleController.getString(R.string.WifiSuccess)).show();
        return;
    L25:
        showErrorBulletin(LocaleController.getString(R.string.WifiFailed));
        return;
    L16:
        if ("WEP".equalsIgnoreCase(this.wifiAuthType) == false) goto L19;
        r0.wepKeys[0] = String.format("\"%s\"", new Object[]{this.password});
        r0.wepTxKeyIndex = 0;
        r0.allowedKeyManagement.set(0);
        r0.allowedGroupCiphers.set(0);
    L18:
        r0.allowedKeyManagement.set(0);
        goto L19
    L5:
        if ("nopass".equalsIgnoreCase(this.wifiAuthType) == true) goto L8;
        r1 = false;
        goto L10
    }

    private void connectWifiModern(WifiManager r4) {
        WifiNetworkSuggestion.Builder r0 = QRCodeSheet$$ExternalSyntheticApiModelOutline0.m().setSsid(this.ssid).setIsAppInteractionRequired(true);
        if (TextUtils.isEmpty(this.password) == true) goto L13;
        if ("WPA".equalsIgnoreCase(this.wifiAuthType) == false) goto L8;
        r0.setWpa2Passphrase(this.password);
        goto L13
    L8:
        if (Build.VERSION.SDK_INT < 30) goto L13;
        if ("SAE".equalsIgnoreCase(this.wifiAuthType) == false) goto L13;
        r0.setWpa3Passphrase(this.password);
    L13:
        if (r4.addNetworkSuggestions(Collections.singletonList(r0.build())) != 0) goto L16;
        Intent r42 = new Intent("android.settings.WIFI_SETTINGS");
        this.fragment.getParentActivity().startActivity(r42);
        return;
    L16:
        showErrorBulletin(LocaleController.getString(R.string.WifiFailed));
    }

    @SuppressLint({"SetWorldReadable"})
    private void copyQR(Bitmap r4, Activity r5) {
        File r0 = new File(r5.getExternalFilesDir(null), "qr_code.jpg");     // Catch: IOException -> L7
        if (r0.exists() == false) goto L5;
        r0.delete();     // Catch: IOException -> L7
    L5:
        r0.createNewFile();     // Catch: IOException -> L7
        FileOutputStream r52 = new FileOutputStream(r0);     // Catch: IOException -> L7
        r4.compress(Bitmap.CompressFormat.PNG, 100, r52);     // Catch: IOException -> L7
        r52.flush();     // Catch: IOException -> L7
        r52.close();     // Catch: IOException -> L7
        r0.setReadable(true, false);     // Catch: IOException -> L7
        SystemUtils.addFileToClipboard(r0, new QRCodeSheet$$ExternalSyntheticLambda5(this));     // Catch: IOException -> L7
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
    }

    private Bitmap createQR(String r11) {
        HashMap r4 = new HashMap();     // Catch: Exception -> L4
        r4.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);     // Catch: Exception -> L4
        r4.put(EncodeHintType.MARGIN, 0);     // Catch: Exception -> L4
        return new TelegramQRCodeWriter().encode(r11, 768, 768, r4, null, 1.0f, -1, -16777216, false);
    L4:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private Spanned getTextWithIcon(String r6) {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        SpannableStringBuilder r1 = r0.append("..");
        Activity r5 = this.fragment.getParentActivity();
        if (r6.equals("copy") == false) goto L5;
        int r4 = R.drawable.msg_copy_filled;
    L6:
        r1.setSpan(new ColoredImageSpan(ContextCompat.getDrawable(r5, r4)), 0, 1, 0);
        r0.setSpan(new DialogCell.FixedWidthSpan(AndroidUtilities.dp(4.0f)), 1, 2, 0);
        if (r6.equals("copy") == false) goto L10;
        int r52 = R.string.LinkActionCopy;
    L11:
        r0.append(LocaleController.getString(r52));
        return r0;
    L10:
        r52 = R.string.LinkActionShare;
        goto L11
    L5:
        r4 = R.drawable.msg_share_filled;
        goto L6
    }

    private /* synthetic */ void lambda$copyQR$7() {
        BulletinFactory.of(getContainer(), null).createCopyBulletin(LocaleController.getString(R.string.PhotoCopied), this.resourcesProvider).show();
    }

    private /* synthetic */ void lambda$new$0(Bitmap r1, Activity r2, View r3) {
        if (r1 == null) goto L5;
        copyQR(r1, r2);
        return;
    }

    private /* synthetic */ void lambda$new$1(String r1, View r2) {
        if (AndroidUtilities.addToClipboard(r1) == false) goto L6;
        showCopyBulletin(true);
        return;
    }

    private /* synthetic */ void lambda$new$2(int r1, BaseFragment r2, String r3, View r4) {
        if (r1 != 2) goto L6;
        dismiss();
        return;
    L6:
        if (r1 != 0) goto L8;
    L18:
        Browser.openUrl(r2.getParentActivity(), Uri.parse(r3));
    L19:
        dismiss();
        return;
    L8:
        if (r1 == 1) goto L16;
        if (r1 == 3) goto L18;
        if (r1 != 4) goto L19;
        AndroidUtilities.runOnUIThread(new QRCodeSheet$$ExternalSyntheticLambda7(this), 750);
        goto L19
    L16:
        if (AndroidUtilities.addToClipboard(r3) == false) goto L19;
        showCopyBulletin(false);
        goto L19
    }

    private /* synthetic */ void lambda$new$3(TLObject r1, TLRPC.TL_error r2) {
        dismiss();
    }

    private /* synthetic */ void lambda$new$5(String r3, BaseFragment r4) {
        byte[] r32 = Base64.decode(r3.substring(17).replaceAll("/", "_").replaceAll("\\+", "-"), 8);     // Catch: Exception -> L5
        TLRPC.TL_auth_acceptLoginToken r0 = new TLRPC.TL_auth_acceptLoginToken();     // Catch: Exception -> L5
        r0.token = r32;     // Catch: Exception -> L5
        ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(r0, new QRCodeSheet$$ExternalSyntheticLambda10(this));     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e("Failed to pass qr code auth", e);
        AndroidUtilities.runOnUIThread(new QRCodeSheet$$ExternalSyntheticLambda11(r4));
    }

    private /* synthetic */ void lambda$new$6(int r1, String r2, BaseFragment r3, View r4) {
        if (r1 != 2) goto L11;
        AndroidUtilities.runOnUIThread(new QRCodeSheet$$ExternalSyntheticLambda6(this, r2, r3), 750);
    L9:
        dismiss();
        return;
    L11:
        Intent r12 = new Intent("android.intent.action.SEND");     // Catch: Exception -> L7
        r12.setType("text/plain");     // Catch: Exception -> L7
        r12.putExtra("android.intent.extra.TEXT", r2);     // Catch: Exception -> L7
        r3.startActivityForResult(Intent.createChooser(r12, LocaleController.getString(R.string.QrCode)), MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L7
    L7:
        e = move-exception;
        FileLog.e(e);
        goto L9
    }

    private /* synthetic */ void lambda$showCopyBulletin$9(boolean r3) {
        if (r3 == false) goto L4;
        BulletinFactory r2 = BulletinFactory.of(getContainer(), null);
    L5:
        r2.createCopyBulletin(LocaleController.formatString("TextCopied", R.string.TextCopied, new Object[0])).show();
        return;
    L4:
        r2 = BulletinFactory.of(this.fragment);
        goto L5
    }

    private /* synthetic */ void lambda$showErrorBulletin$8(String r1) {
        BulletinFactory.of(this.fragment).createErrorBulletin(r1).show();
    }

    private void parseWifiInfo(String r6) {
        String[] r62 = r6.substring(r6.indexOf(":") + 1).split("(?<!\\\\);");
        int r0 = r62.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L14;
        String r2 = r62[r1];
        if (r2.startsWith("S:") == false) goto L8;
        this.ssid = unescapeWifiString(r2.substring(2));
    L13:
        r1 = r1 + 1;
        goto L3
    L8:
        if (r2.startsWith("P:") == false) goto L11;
        this.password = unescapeWifiString(r2.substring(2));
        goto L13
    L11:
        if (r2.startsWith("T:") == false) goto L13;
        this.wifiAuthType = r2.substring(2);
        goto L13
    }

    private void showCopyBulletin(boolean r2) {
        AndroidUtilities.runOnUIThread(new QRCodeSheet$$ExternalSyntheticLambda8(this, r2));
    }

    private void showErrorBulletin(String r2) {
        AndroidUtilities.runOnUIThread(new QRCodeSheet$$ExternalSyntheticLambda9(this, r2));
    }

    private String unescapeWifiString(String r2) {
        return r2.replace("\\\\", "\\").replace("\\;", ";").replace("\\:", ":").replace("\\,", ",").replace("\\\"", "\"");
    }
}
