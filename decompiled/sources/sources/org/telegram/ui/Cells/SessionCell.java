package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.google.android.gms.cast.CredentialsData;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.DotDividerSpan;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class SessionCell extends FrameLayout {
    private AvatarDrawable avatarDrawable;
    private int currentAccount;
    private int currentType;
    private TextView detailExTextView;
    private TextView detailTextView;
    FlickerLoadingView globalGradient;
    private BackupImageView imageView;
    LinearLayout linearLayout;
    private TextView nameTextView;
    private boolean needDivider;
    private TextView onlineTextView;
    private BackupImageView placeholderImageView;
    private boolean showStub;
    private AnimatedFloat showStubValue;

    public static class CircleGradientDrawable extends Drawable {
        private int colorBottom;
        private int colorTop;
        private Paint paint;
        private int size;

        public CircleGradientDrawable(int r11, int r12, int r13) {
            this.size = r11;
            this.colorTop = r12;
            this.colorBottom = r13;
            Paint r0 = new Paint(1);
            this.paint = r0;
            r0.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, r11, new int[]{r12, r13}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r5) {
            r5.drawCircle(getBounds().centerX(), getBounds().centerY(), Math.min(getBounds().width(), getBounds().height()) / 2.0f, this.paint);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return this.size;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return this.size;
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.paint.setAlpha(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    public SessionCell(Context r25, int r26) {
        super(r25);
        this.showStubValue = new AnimatedFloat(this);
        this.currentAccount = UserConfig.selectedAccount;
        LinearLayout r3 = new LinearLayout(r25);
        this.linearLayout = r3;
        int r4 = 0;
        r3.setOrientation(0);
        this.linearLayout.setWeightSum(1.0f);
        this.currentType = r26;
        int r32 = 72;
        int r6 = 15;
        int r7 = 21;
        int r8 = 3;
        if (r26 != 1) goto L27;
        LinearLayout r11 = this.linearLayout;
        boolean r12 = LocaleController.isRTL;
        if (r12 == false) goto L7;
        int r13 = 5;
    L8:
        int r16 = r13 | 48;
        if (r12 == false) goto L11;
        int r14 = 15;
    L12:
        float r142 = r14;
        if (r12 == false) goto L15;
        r6 = 49;
    L15:
        addView(r11, LayoutHelper.createFrame(-1, 30.0f, r16, r142, 11.0f, r6, 0.0f));
        AvatarDrawable r62 = new AvatarDrawable();
        this.avatarDrawable = r62;
        r62.setTextSize(AndroidUtilities.dp(10.0f));
        BackupImageView r63 = new BackupImageView(r25);
        this.imageView = r63;
        r63.setRoundRadius(AndroidUtilities.dp(10.0f));
        BackupImageView r5 = this.imageView;
        boolean r64 = LocaleController.isRTL;
        if (r64 == false) goto L18;
        int r112 = 5;
    L19:
        int r143 = r112 | 48;
        if (r64 == false) goto L22;
        int r113 = 0;
    L23:
        float r15 = r113;
        if (r64 == false) goto L26;
        r4 = 21;
    L26:
        addView(r5, LayoutHelper.createFrame(20, 20.0f, r143, r15, 13.0f, r4, 0.0f));
    L62:
        TextView r42 = new TextView(r25);
        this.nameTextView = r42;
        int r52 = Theme.key_windowBackgroundWhiteBlackText;
        r42.setTextColor(Theme.getColor(r52));
        TextView r43 = this.nameTextView;
        if (r26 != 0) goto L65;
        float r65 = 15.0f;
    L66:
        r43.setTextSize(1, r65);
        this.nameTextView.setLines(1);
        this.nameTextView.setTypeface(AndroidUtilities.bold());
        this.nameTextView.setMaxLines(1);
        this.nameTextView.setSingleLine(true);
        TextView r44 = this.nameTextView;
        TextUtils.TruncateAt r66 = TextUtils.TruncateAt.END;
        r44.setEllipsize(r66);
        TextView r45 = this.nameTextView;
        if (LocaleController.isRTL == false) goto L69;
        int r114 = 5;
    L70:
        r45.setGravity(r114 | 48);
        TextView r46 = new TextView(r25);
        this.onlineTextView = r46;
        float r115 = 13.0f;
        if (r26 != 0) goto L73;
        float r122 = 12.0f;
    L74:
        r46.setTextSize(1, r122);
        this.onlineTextView.setTypeface(AndroidUtilities.regular());
        TextView r47 = this.onlineTextView;
        if (LocaleController.isRTL == false) goto L77;
        int r123 = 3;
    L78:
        r47.setGravity(r123 | 48);
        boolean r48 = LocaleController.isRTL;
        LinearLayout r124 = this.linearLayout;
        if (r48 == false) goto L81;
        r124.addView(this.onlineTextView, LayoutHelper.createLinear(-2, -1, 51, 0, 2, 0, 0));
        this.linearLayout.addView(this.nameTextView, LayoutHelper.createLinear(0, -1, 1.0f, 53, 10, 0, 0, 0));
    L83:
        if (LocaleController.isRTL == false) goto L87;
        if (r26 == 0) goto L91;
        r32 = 21;
    L91:
        TextView r49 = new TextView(r25);
        this.detailTextView = r49;
        r49.setTextColor(Theme.getColor(r52));
        TextView r410 = this.detailTextView;
        if (r26 != 0) goto L94;
        float r125 = 13.0f;
    L95:
        r410.setTextSize(1, r125);
        this.detailTextView.setTypeface(AndroidUtilities.regular());
        this.detailTextView.setLines(1);
        this.detailTextView.setMaxLines(1);
        this.detailTextView.setSingleLine(true);
        this.detailTextView.setEllipsize(r66);
        TextView r411 = this.detailTextView;
        if (LocaleController.isRTL == false) goto L98;
        int r126 = 5;
    L99:
        r411.setGravity(r126 | 48);
        TextView r412 = this.detailTextView;
        if (LocaleController.isRTL == false) goto L102;
        int r127 = 5;
    L103:
        int r152 = r127 | 48;
        float r72 = r7;
        if (r26 != 0) goto L107;
        float r128 = 28.0f;
    L108:
        float r33 = r32;
        addView(r412, LayoutHelper.createFrame(-1, -2.0f, r152, r72, r128, r33, 0.0f));
        TextView r34 = new TextView(r25);
        this.detailExTextView = r34;
        r34.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3));
        TextView r1 = this.detailExTextView;
        if (r26 == 0) goto L112;
        r115 = 14.0f;
    L112:
        r1.setTextSize(1, r115);
        this.detailExTextView.setTypeface(AndroidUtilities.regular());
        this.detailExTextView.setLines(1);
        this.detailExTextView.setMaxLines(1);
        this.detailExTextView.setSingleLine(true);
        this.detailExTextView.setEllipsize(r66);
        TextView r17 = this.detailExTextView;
        if (LocaleController.isRTL == false) goto L115;
        int r35 = 5;
    L116:
        r17.setGravity(r35 | 48);
        TextView r18 = this.detailExTextView;
        if (LocaleController.isRTL == false) goto L119;
        r8 = 5;
    L119:
        int r36 = r8 | 48;
        if (r26 != 0) goto L123;
        float r2 = 46.0f;
    L124:
        addView(r18, LayoutHelper.createFrame(-1, -2.0f, r36, r72, r2, r33, 0.0f));
        return;
    L123:
        r2 = 59.0f;
        goto L124
    L115:
        r35 = 3;
        goto L116
    L107:
        r128 = 36.0f;
        goto L108
    L102:
        r127 = 3;
        goto L103
    L98:
        r126 = 3;
        goto L99
    L94:
        r125 = 14.0f;
        goto L95
    L87:
        if (r26 == 0) goto L90;
        r32 = 21;
    L90:
        r7 = r32;
        r32 = 21;
        goto L91
    L81:
        r124.addView(this.nameTextView, LayoutHelper.createLinear(0, -1, 1.0f, 51, 0, 0, 10, 0));
        this.linearLayout.addView(this.onlineTextView, LayoutHelper.createLinear(-2, -1, 53, 0, 2, 0, 0));
        goto L83
    L77:
        r123 = 5;
        goto L78
    L73:
        r122 = 13.0f;
        goto L74
    L69:
        r114 = 3;
        goto L70
    L65:
        r65 = 16.0f;
        goto L66
    L22:
        r113 = 21;
        goto L23
    L18:
        r112 = 3;
        goto L19
    L11:
        r14 = 49;
        goto L12
    L7:
        r13 = 3;
        goto L8
    L27:
        BackupImageView r116 = new BackupImageView(r25);
        this.placeholderImageView = r116;
        r116.setRoundRadius(AndroidUtilities.dp(10.0f));
        BackupImageView r117 = this.placeholderImageView;
        boolean r129 = LocaleController.isRTL;
        if (r129 == false) goto L30;
        int r132 = 5;
    L31:
        int r162 = r132 | 48;
        if (r129 == false) goto L34;
        int r144 = 0;
    L35:
        float r145 = r144;
        if (r129 == false) goto L38;
        int r1210 = 16;
    L39:
        addView(r117, LayoutHelper.createFrame(42, 42.0f, r162, r145, 9.0f, r1210, 0.0f));
        this.avatarDrawable = new AvatarDrawable();
        BackupImageView r118 = new BackupImageView(r25);
        this.imageView = r118;
        r118.setRoundRadius(AndroidUtilities.dp(10.0f));
        BackupImageView r53 = this.imageView;
        boolean r119 = LocaleController.isRTL;
        if (r119 == false) goto L42;
        int r1211 = 5;
    L43:
        int r163 = r1211 | 48;
        if (r119 == false) goto L46;
        int r1212 = 0;
    L47:
        float r1213 = r1212;
        if (r119 == false) goto L50;
        r4 = 16;
    L50:
        addView(r53, LayoutHelper.createFrame(42, 42.0f, r163, r1213, 9.0f, r4, 0.0f));
        LinearLayout r413 = this.linearLayout;
        boolean r54 = LocaleController.isRTL;
        if (r54 == false) goto L53;
        int r1110 = 5;
    L54:
        int r146 = r1110 | 48;
        if (r54 == false) goto L57;
        int r1111 = 15;
    L58:
        float r153 = r1111;
        if (r54 == false) goto L61;
        r6 = 72;
    L61:
        addView(r413, LayoutHelper.createFrame(-1, 30.0f, r146, r153, 6.333f, r6, 0.0f));
        goto L62
    L57:
        r1111 = 72;
        goto L58
    L53:
        r1110 = 3;
        goto L54
    L46:
        r1212 = 16;
        goto L47
    L42:
        r1211 = 3;
        goto L43
    L38:
        r1210 = 0;
        goto L39
    L34:
        r144 = 16;
        goto L35
    L30:
        r132 = 3;
        goto L31
    }

    public static CombinedDrawable createDrawable(int r1, String r2) {
        TLRPC.TL_authorization r0 = new TLRPC.TL_authorization();
        r0.device_model = r2;
        r0.platform = r2;
        r0.app_name = r2;
        return createDrawable(r1, r0);
    }

    public static CombinedDrawable createDrawable(int r10, TLRPC.TL_authorization r11) {
        String r0 = r11.platform.toLowerCase();
        if (r0.isEmpty() == false) goto L5;
        r0 = r11.system_version.toLowerCase();
    L5:
        String r1 = r11.device_model.toLowerCase();
        if (r1.contains("safari") == false) goto L9;
        int r12 = R.drawable.device_web_safari;
        int r2 = Theme.key_avatar_backgroundPink;
        int r5 = Theme.key_avatar_background2Pink;
    L69:
        boolean r112 = r11.app_name.toLowerCase().contains("exteragram");
        Context r6 = ApplicationLoader.applicationContext;
        if (r112 == false) goto L72;
        r12 = R.drawable.ic_foreground;
    L72:
        Drawable r13 = ContextCompat.getDrawable(r6, r12).mutate();
        if (r112 == true) goto L75;
        r13.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_avatar_text), PorterDuff.Mode.SRC_IN));
    L75:
        float r102 = r10;
        int r7 = AndroidUtilities.dp(r102);
        int r8 = -16777216;
        if (r112 == false) goto L78;
        int r22 = ContextCompat.getColor(ApplicationLoader.applicationContext, R.color.ic_background);
    L81:
        if (r112 == false) goto L83;
        r8 = ContextCompat.getColor(ApplicationLoader.applicationContext, R.color.ic_background);
    L86:
        CombinedDrawable r113 = new CombinedDrawable(new CircleGradientDrawable(r7, r22, r8), r13);
        if (r0.contains("fragment") == false) goto L90;
        r113.setIconSize((int) ((r13.getIntrinsicWidth() / 44.0f) * r102), (int) ((r13.getIntrinsicHeight() / 44.0f) * r102));
        return r113;
    L90:
        r113.setIconSize(AndroidUtilities.dp(42.0f), AndroidUtilities.dp(42.0f));
        return r113;
    L83:
        if (r5 == (-1)) goto L86;
        r8 = Theme.getColor(r5);
        goto L86
    L78:
        if (r2 != (-1)) goto L80;
        r22 = -16777216;
        goto L81
    L80:
        r22 = Theme.getColor(r2);
        goto L81
    L9:
        if (r1.contains("edge") == false) goto L12;
        r12 = R.drawable.device_web_edge;
        r2 = Theme.key_avatar_backgroundPink;
        r5 = Theme.key_avatar_background2Pink;
        goto L69
    L12:
        if (r1.contains("chrome") == false) goto L15;
        r12 = R.drawable.device_web_chrome;
        r2 = Theme.key_avatar_backgroundPink;
        r5 = Theme.key_avatar_background2Pink;
        goto L69
    L15:
        if (r1.contains("opera") == false) goto L18;
        r12 = R.drawable.device_web_opera;
        r2 = Theme.key_avatar_backgroundPink;
        r5 = Theme.key_avatar_background2Pink;
        goto L69
    L18:
        if (r1.contains("firefox") == false) goto L21;
        r12 = R.drawable.device_web_firefox;
        r2 = Theme.key_avatar_backgroundPink;
        r5 = Theme.key_avatar_background2Pink;
        goto L69
    L21:
        if (r1.contains("vivaldi") == false) goto L24;
        r12 = R.drawable.device_web_other;
        r2 = Theme.key_avatar_backgroundPink;
        r5 = Theme.key_avatar_background2Pink;
        goto L69
    L24:
        if (r0.contains(CredentialsData.CREDENTIALS_TYPE_IOS) == false) goto L31;
        if (r1.contains("ipad") == false) goto L28;
        r12 = R.drawable.device_tablet_ios;
    L29:
        r2 = Theme.key_avatar_backgroundBlue;
        r5 = Theme.key_avatar_background2Blue;
        goto L69
    L28:
        r12 = R.drawable.device_phone_ios;
        goto L29
    L31:
        if (r0.contains("windows") == false) goto L34;
        r12 = R.drawable.device_desktop_win;
        r2 = Theme.key_avatar_backgroundCyan;
        r5 = Theme.key_avatar_background2Cyan;
        goto L69
    L34:
        if (r0.contains("macos") == false) goto L37;
        r12 = R.drawable.device_desktop_osx;
        r2 = Theme.key_avatar_backgroundCyan;
        r5 = Theme.key_avatar_background2Cyan;
        goto L69
    L37:
        if (r0.contains("android") == false) goto L44;
        if (r1.contains("tab") == false) goto L41;
        r12 = R.drawable.device_tablet_android;
    L42:
        r2 = Theme.key_avatar_backgroundGreen;
        r5 = Theme.key_avatar_background2Green;
        goto L69
    L41:
        r12 = R.drawable.device_phone_android;
        goto L42
    L44:
        if (r0.contains("fragment") == false) goto L48;
        r12 = R.drawable.fragment;
    L46:
        r2 = -1;
        r5 = -1;
        goto L69
    L48:
        if (r0.equalsIgnoreCase("search") == false) goto L51;
        r12 = R.drawable.msg_search;
        r2 = Theme.key_avatar_backgroundBlue;
        r5 = Theme.key_avatar_background2Blue;
        goto L69
    L51:
        if (r0.contains("anonymous") == false) goto L54;
        r12 = R.drawable.large_hidden;
        r2 = Theme.key_avatar_backgroundBlue;
        r5 = Theme.key_avatar_background2Blue;
        goto L69
    L54:
        if (r0.contains("premiumbot") == false) goto L57;
        r12 = R.drawable.filled_star_plus;
        r2 = Theme.key_color_yellow;
        r5 = Theme.key_color_orange;
        goto L69
    L57:
        if (r0.contains("ads") == false) goto L60;
        r12 = R.drawable.msg_channel;
        r2 = Theme.key_avatar_backgroundPink;
        r5 = Theme.key_avatar_background2Pink;
        goto L69
    L60:
        if (r0.contains("api") == false) goto L63;
        r12 = R.drawable.filled_paid_broadcast;
        r2 = Theme.key_avatar_backgroundGreen;
        r5 = Theme.key_avatar_background2Green;
        goto L69
    L63:
        if (r0.equals("?") == false) goto L66;
        r12 = R.drawable.msg_emoji_question;
        goto L46
    L66:
        if (r11.app_name.toLowerCase().contains("desktop") == false) goto L68;
        r12 = R.drawable.device_desktop_other;
        r2 = Theme.key_avatar_backgroundCyan;
        r5 = Theme.key_avatar_background2Cyan;
        goto L69
    L68:
        r12 = R.drawable.device_web_other;
        r2 = Theme.key_avatar_backgroundPink;
        r5 = Theme.key_avatar_background2Pink;
        goto L69
    }

    private void setContentAlpha(float r3) {
        TextView r0 = this.detailExTextView;
        if (r0 == null) goto L5;
        r0.setAlpha(r3);
    L5:
        TextView r02 = this.detailTextView;
        if (r02 == null) goto L8;
        r02.setAlpha(r3);
    L8:
        TextView r03 = this.nameTextView;
        if (r03 == null) goto L11;
        r03.setAlpha(r3);
    L11:
        TextView r04 = this.onlineTextView;
        if (r04 == null) goto L14;
        r04.setAlpha(r3);
    L14:
        BackupImageView r05 = this.imageView;
        if (r05 == null) goto L17;
        r05.setAlpha(r3);
    L17:
        BackupImageView r06 = this.placeholderImageView;
        if (r06 == null) goto L20;
        r06.setAlpha(1.0f - r3);
    L20:
        LinearLayout r2 = this.linearLayout;
        if (r2 == null) goto L24;
        r2.setAlpha(r3);
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r11) {
        AnimatedFloat r0 = this.showStubValue;
        float r2 = 0.0f;
        if (this.showStub == false) goto L5;
        float r1 = 1.0f;
    L6:
        float r02 = r0.set(r1);
        setContentAlpha(1.0f - r02);
        if (r02 <= 0.0f) goto L20;
        if (this.globalGradient == null) goto L20;
        if (r02 >= 1.0f) goto L13;
        RectF r4 = AndroidUtilities.rectTmp;
        r4.set(0.0f, 0.0f, getWidth(), getHeight());
        r11.saveLayerAlpha(r4, (int) (r02 * 255.0f), 31);
    L13:
        this.globalGradient.updateColors();
        this.globalGradient.updateGradient();
        if (getParent() == null) goto L16;
        View r03 = (View) getParent();
        this.globalGradient.setParentSize(r03.getMeasuredWidth(), r03.getMeasuredHeight(), -getX());
    L16:
        float r04 = (this.linearLayout.getTop() + this.nameTextView.getTop()) + AndroidUtilities.dp(12.0f);
        float r42 = this.linearLayout.getX();
        RectF r5 = AndroidUtilities.rectTmp;
        r5.set(r42, r04 - AndroidUtilities.dp(4.0f), (getMeasuredWidth() * 0.2f) + r42, r04 + AndroidUtilities.dp(4.0f));
        r11.drawRoundRect(r5, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.globalGradient.getPaint());
        float r05 = (this.linearLayout.getTop() + this.detailTextView.getTop()) - AndroidUtilities.dp(1.0f);
        float r43 = this.linearLayout.getX();
        r5.set(r43, r05 - AndroidUtilities.dp(4.0f), (getMeasuredWidth() * 0.4f) + r43, r05 + AndroidUtilities.dp(4.0f));
        r11.drawRoundRect(r5, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.globalGradient.getPaint());
        float r06 = (this.linearLayout.getTop() + this.detailExTextView.getTop()) - AndroidUtilities.dp(1.0f);
        float r3 = this.linearLayout.getX();
        r5.set(r3, r06 - AndroidUtilities.dp(4.0f), (getMeasuredWidth() * 0.3f) + r3, r06 + AndroidUtilities.dp(4.0f));
        r11.drawRoundRect(r5, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.globalGradient.getPaint());
        invalidate();
        if (r02 >= 1.0f) goto L20;
        r11.restore();
    L20:
        if (this.needDivider == true) goto L22;
        return;
    L22:
        if (this.currentType != 1) goto L24;
        int r07 = 49;
    L26:
        if (LocaleController.isRTL == false) goto L28;
    L27:
        float r44 = r2;
        float r52 = getMeasuredHeight() - 1;
        int r22 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L32;
        int r08 = AndroidUtilities.dp(r07);
    L33:
        r11.drawLine(r44, r52, r22 - r08, getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L32:
        r08 = 0;
        goto L33
    L28:
        r2 = AndroidUtilities.dp(r07);
        goto L27
    L24:
        r07 = 72;
        goto L26
    L5:
        r1 = 0.0f;
        goto L6
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        int r32 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30);
        if (this.currentType != 0) goto L5;
        float r0 = 70.0f;
    L6:
        super.onMeasure(r32, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r0) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
        return;
    L5:
        r0 = 90.0f;
        goto L6
    }

    public void setSession(TLObject r8, boolean r9) {
        this.needDivider = r9;
        this.imageView.setRoundRadius(AndroidUtilities.dp(10.0f));
        if ((r8 instanceof TL_account.TL_connectedBot) == false) goto L8;
        TL_account.TL_connectedBot r82 = (TL_account.TL_connectedBot) r8;
        TLRPC.User r92 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r82.bot_id));
        this.avatarDrawable.setInfo(r92);
        this.imageView.setRoundRadius(AndroidUtilities.dp(21.0f));
        this.imageView.setForUserOrChat(r92, this.avatarDrawable);
        this.nameTextView.setText(UserObject.getUserName(r92));
        this.detailTextView.setText(LocaleController.getString(R.string.SessionBot));
        if (TLObject.hasFlag(r82.flags, 2) == false) goto L66;
        this.detailExTextView.setText(LocaleController.formatString(R.string.SessionBotConnectedOn, new Object[]{LocaleController.formatDateTime(r82.date, false)}));
    L66:
        if (this.showStub == false) goto L69;
        this.showStub = false;
        invalidate();
        return;
    L69:
        return;
    L8:
        if ((r8 instanceof TLRPC.TL_authorization) == false) goto L35;
        TLRPC.TL_authorization r83 = (TLRPC.TL_authorization) r8;
        this.imageView.setImageDrawable(createDrawable(42, r83));
        StringBuilder r93 = new StringBuilder();
        if (r83.device_model.length() == 0) goto L13;
        r93.append(r83.device_model);
    L13:
        if (r93.length() == 0) goto L15;
    L23:
        this.nameTextView.setText(r93);
        if ((r83.flags & 1) == 0) goto L26;
        setTag(Integer.valueOf(Theme.key_windowBackgroundWhiteValueText));
        String r94 = LocaleController.getString(R.string.Online);
    L27:
        SpannableStringBuilder r3 = new SpannableStringBuilder();
        if (r83.country.length() == 0) goto L31;
        r3.append(r83.country);
    L31:
        if (r3.length() == 0) goto L33;
        DotDividerSpan r4 = new DotDividerSpan();
        r4.setTopPadding(AndroidUtilities.dp(1.5f));
        r3.append(" . ").setSpan(r4, r3.length() - 2, r3.length() - 1, 0);
    L33:
        r3.append(r94);
        this.detailExTextView.setText(r3);
        StringBuilder r95 = new StringBuilder();
        r95.append(r83.app_name);
        r95.append(" ");
        r95.append(r83.app_version);
        this.detailTextView.setText(r95);
        goto L66
    L26:
        setTag(Integer.valueOf(Theme.key_windowBackgroundWhiteGrayText3));
        r94 = LocaleController.stringForMessageListDate(r83.date_active);
        goto L27
    L15:
        if (r83.platform.length() == 0) goto L18;
        r93.append(r83.platform);
    L18:
        if (r83.system_version.length() == 0) goto L23;
        if (r83.platform.length() == 0) goto L22;
        r93.append(" ");
    L22:
        r93.append(r83.system_version);
        goto L23
    L35:
        if ((r8 instanceof TLRPC.TL_webAuthorization) == false) goto L66;
        TLRPC.TL_webAuthorization r84 = (TLRPC.TL_webAuthorization) r8;
        TLRPC.User r96 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r84.bot_id));
        this.nameTextView.setText(r84.domain);
        if (r96 == null) goto L39;
        this.avatarDrawable.setInfo(this.currentAccount, r96);
        String r0 = UserObject.getFirstName(r96);
        this.imageView.setForUserOrChat(r96, this.avatarDrawable);
    L40:
        int r97 = Theme.key_windowBackgroundWhiteGrayText3;
        setTag(Integer.valueOf(r97));
        this.onlineTextView.setText(LocaleController.stringForMessageListDate(r84.date_active));
        this.onlineTextView.setTextColor(Theme.getColor(r97));
        StringBuilder r98 = new StringBuilder();
        if (r84.ip.length() == 0) goto L44;
        r98.append(r84.ip);
    L44:
        if (r84.region.length() != 0) goto L46;
    L49:
        this.detailExTextView.setText(r98);
        StringBuilder r99 = new StringBuilder();
        if (TextUtils.isEmpty(r0) == true) goto L53;
        r99.append(r0);
    L53:
        if (r84.browser.length() == 0) goto L59;
        if (r99.length() == 0) goto L57;
        r99.append(", ");
    L57:
        r99.append(r84.browser);
    L59:
        if (r84.platform.length() != 0) goto L61;
    L64:
        this.detailTextView.setText(r99);
        goto L66
    L61:
        if (r99.length() == 0) goto L63;
        r99.append(", ");
    L63:
        r99.append(r84.platform);
        goto L64
    L46:
        if (r98.length() == 0) goto L48;
        r98.append(" ");
    L48:
        r98.append("— ");
        r98.append(r84.region);
        goto L49
    L39:
        r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L40
    }

    public void showStub(FlickerLoadingView r4) {
        this.globalGradient = r4;
        this.showStub = true;
        Context r42 = ApplicationLoader.applicationContext;
        if (AndroidUtilities.isTablet() == false) goto L5;
        int r0 = R.drawable.device_tablet_android;
    L6:
        Drawable r43 = ContextCompat.getDrawable(r42, r0).mutate();
        r43.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_avatar_text), PorterDuff.Mode.SRC_IN));
        CombinedDrawable r02 = new CombinedDrawable(Theme.createCircleDrawable(AndroidUtilities.dp(42.0f), Theme.getColor(Theme.key_avatar_backgroundGreen)), r43);
        BackupImageView r44 = this.placeholderImageView;
        if (r44 == null) goto L9;
        r44.setImageDrawable(r02);
    L10:
        invalidate();
        return;
    L9:
        this.imageView.setImageDrawable(r02);
        goto L10
    L5:
        r0 = R.drawable.device_phone_android;
        goto L6
    }
}
