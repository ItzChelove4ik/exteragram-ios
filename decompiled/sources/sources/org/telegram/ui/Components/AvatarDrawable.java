package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class AvatarDrawable extends Drawable {
    public static final int[][] advancedGradients = null;
    private GradientTools advancedGradient;
    private int alpha;
    private float archivedAvatarProgress;
    private int avatarType;
    private int color;
    private int color2;
    private Drawable customIconDrawable;
    private boolean drawAvatarBackground;
    private boolean drawDeleted;
    private LinearGradient gradient;
    private int gradientBottom;
    private int gradientColor1;
    private int gradientColor2;
    private boolean hasAdvancedGradient;
    private boolean hasGradient;
    private int iconTx;
    private int iconTy;
    private boolean invalidateTextLayout;
    private boolean isProfile;
    private TextPaint namePaint;
    private boolean needApplyColorAccent;
    private Theme.ResourcesProvider resourcesProvider;
    private boolean rotate45Background;
    private int roundRadius;
    private float scaleSize;
    private StringBuilder stringBuilder;
    private float textHeight;
    private StaticLayout textLayout;
    private float textLeft;
    private float textWidth;

    static {
        advancedGradients = new int[][]{new int[]{-636796, -1090751, -612560, -35006}, new int[]{-693938, -690388, -11246, -22717}, new int[]{-8160001, -5217281, -36183, -1938945}, new int[]{-16133536, -10560448, -4070106, -8331477}, new int[]{-10569989, -14692629, -12191817, -14683687}, new int[]{-11694593, -13910017, -14622003, -15801871}, new int[]{-439392, -304000, -19910, -98718}};
    }

    public AvatarDrawable() {
        this(null);
    }

    public AvatarDrawable(TLRPC.Chat r2) {
        this(r2, false);
    }

    public AvatarDrawable(TLRPC.Chat r1, boolean r2) {
        this();
        this.isProfile = r2;
        setInfo(r1);
    }

    public AvatarDrawable(TLRPC.User r2) {
        this(r2, false);
    }

    public AvatarDrawable(TLRPC.User r7, boolean r8) {
        this();
        this.isProfile = r8;
        if (r7 == null) goto L6;
        setInfo(r7.id, r7.first_name, r7.last_name, null);
        this.drawDeleted = UserObject.isDeleted(r7);
        return;
    }

    public AvatarDrawable(Theme.ResourcesProvider r3) {
        this.scaleSize = 1.0f;
        this.stringBuilder = new StringBuilder(5);
        this.roundRadius = -1;
        this.drawAvatarBackground = true;
        this.rotate45Background = false;
        this.alpha = 255;
        this.resourcesProvider = r3;
        TextPaint r32 = new TextPaint(1);
        this.namePaint = r32;
        r32.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_NUNITO_EXTRABOLD));
        this.namePaint.setTextSize(AndroidUtilities.dp(18.0f));
    }

    public static String colorName(int r7) {
        return LocaleController.getString(new int[]{R.string.ColorRed, R.string.ColorOrange, R.string.ColorViolet, R.string.ColorGreen, R.string.ColorCyan, R.string.ColorBlue, R.string.ColorPink}[r7 % 7]);
    }

    public static void getAvatarSymbols(String r2, String r3, String r4, StringBuilder r5) {
        r5.setLength(0);
        if (r4 == null) goto L6;
        r5.append(r4);
        return;
    L6:
        if (r2 == null) goto L11;
        if (r2.length() <= 0) goto L11;
        r5.append(takeFirstCharacter(r2));
    L11:
        if (r3 != null) goto L13;
    L19:
        if (r2 != null) goto L21;
        return;
    L21:
        if (r2.length() <= 0) goto L39;
        int r32 = r2.length() - 1;
    L23:
        if (r32 < 0) goto L40;
        if (r2.charAt(r32) != ' ') goto L32;
        if (r32 == (r2.length() - 1)) goto L32;
        if (r2.charAt(r32 + 1) == ' ') goto L32;
        int r33 = r5.length();
        r5.append("\u200c");
        r5.append(takeFirstCharacter(r2.substring(r33)));
        return;
    L32:
        r32 = r32 - 1;
        goto L23
    L40:
        return;
    L39:
        return;
    L13:
        if (r3.length() <= 0) goto L19;
        int r22 = r3.lastIndexOf(32);
        if (r22 < 0) goto L17;
        r3 = r3.substring(r22 + 1);
    L17:
        r5.append("\u200c");
        r5.append(takeFirstCharacter(r3));
    }

    public static int getColorForId(long r1) {
        return Theme.getColor(Theme.keys_avatar_background[getColorIndex(r1)]);
    }

    public static int getColorIndex(long r2) {
        return (int) Math.abs(r2 % Theme.keys_avatar_background.length);
    }

    public static int getPeerColorIndex(int r3) {
        float[] r1 = Theme.getTempHsv(5);
        Color.colorToHSV(r3, r1);
        int r12 = (int) r1[0];
        if (r12 < 345) goto L5;
        return 0;
    L5:
        if (r12 >= 29) goto L8;
        return 0;
    L8:
        if (r12 >= 67) goto L12;
        return 1;
    L12:
        if (r12 >= 140) goto L16;
        return 3;
    L16:
        if (r12 >= 199) goto L20;
        return 4;
    L20:
        if (r12 >= 234) goto L23;
        return 5;
    L23:
        if (r12 >= 301) goto L26;
        return 2;
    L26:
        return 6;
    }

    public static int getProfileBackColorForId(long r0, Theme.ResourcesProvider r2) {
        return Theme.getColor(Theme.key_windowBackgroundGray, r2);
    }

    public static int getProfileColorForId(long r1, Theme.ResourcesProvider r3) {
        return Theme.getColor(Theme.keys_avatar_background[getColorIndex(r1)], r3);
    }

    public static int getProfileTextColorForId(long r0, Theme.ResourcesProvider r2) {
        return Theme.getColor(Theme.key_avatar_subtitleInProfileBlue, r2);
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private static String takeFirstCharacter(String r3) {
        ArrayList<Emoji.EmojiSpanRange> r0 = Emoji.parseEmojis(r3);
        if (r0 == null) goto L11;
        if (r0.isEmpty() == true) goto L11;
        if (r0.get(0).start != 0) goto L11;
        return r3.substring(0, r0.get(0).end);
    L11:
        return r3.substring(0, r3.offsetByCodePoints(0, Math.min(r3.codePointCount(0, r3.length()), 1)));
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r21) {
        Canvas r2 = r21;
        Rect r0 = getBounds();
        if (r0 != null) goto L5;
        return;
    L5:
        int r9 = r0.width();
        TextPaint r3 = this.namePaint;
        int r4 = Theme.key_avatar_text;
        r3.setColor(ColorUtils.setAlphaComponent(getThemedColor(r4), this.alpha));
        Paint r32 = Theme.avatar_backgroundPaint;
        if (this.hasAdvancedGradient == false) goto L12;
        GradientTools r5 = this.advancedGradient;
        if (r5 == null) goto L12;
        r5.setBounds(r0.left, r0.top, r3 + r9, r8 + r9);
        r32 = this.advancedGradient.paint;
    L10:
        Paint r8 = r32;
        r2.save();
        r2.translate(r0.left, r0.top);
        if (this.drawAvatarBackground == true) goto L27;
    L42:
        int r02 = this.avatarType;
        if (r02 == 2) goto L45;
        if (r02 == 0) goto L59;
    L93:
        Drawable r33 = this.customIconDrawable;
        if (r33 != null) goto L164;
        if (r02 != 1) goto L99;
        r33 = Theme.avatarDrawables[0];
        goto L164
    L99:
        if (r02 != 4) goto L102;
        r33 = Theme.avatarDrawables[2];
        goto L164
    L102:
        if (r02 != 5) goto L105;
        r33 = Theme.avatarDrawables[3];
        goto L164
    L105:
        if (r02 != 6) goto L108;
        r33 = Theme.avatarDrawables[4];
        goto L164
    L108:
        if (r02 != 7) goto L111;
        r33 = Theme.avatarDrawables[5];
        goto L164
    L111:
        if (r02 != 8) goto L114;
        r33 = Theme.avatarDrawables[6];
        goto L164
    L114:
        if (r02 != 9) goto L117;
        r33 = Theme.avatarDrawables[7];
        goto L164
    L117:
        if (r02 != 10) goto L119;
        r33 = Theme.avatarDrawables[8];
        goto L164
    L119:
        if (r02 != 3) goto L122;
        r33 = Theme.avatarDrawables[10];
        goto L164
    L122:
        if (r02 != 12) goto L125;
        r33 = Theme.avatarDrawables[11];
        goto L164
    L125:
        if (r02 != 14) goto L128;
        r33 = Theme.avatarDrawables[12];
        goto L164
    L128:
        if (r02 != 15) goto L131;
        r33 = Theme.avatarDrawables[13];
        goto L164
    L131:
        if (r02 != 16) goto L134;
        r33 = Theme.avatarDrawables[14];
        goto L164
    L134:
        if (r02 != 19) goto L137;
        r33 = Theme.avatarDrawables[15];
        goto L164
    L137:
        if (r02 != 18) goto L140;
        r33 = Theme.avatarDrawables[16];
        goto L164
    L140:
        if (r02 != 20) goto L143;
        r33 = Theme.avatarDrawables[17];
        goto L164
    L143:
        if (r02 != 21) goto L146;
        r33 = Theme.avatarDrawables[18];
        goto L164
    L146:
        if (r02 != 22) goto L149;
        r33 = Theme.avatarDrawables[19];
        goto L164
    L149:
        if (r02 != 23) goto L152;
        r33 = Theme.avatarDrawables[21];
        goto L164
    L152:
        if (r02 != 24) goto L155;
        r33 = Theme.avatarDrawables[20];
        goto L164
    L155:
        if (r02 != 25) goto L158;
        r33 = Theme.avatarDrawables[22];
        goto L164
    L158:
        if (r02 != 26) goto L161;
        r33 = Theme.avatarDrawables[23];
        goto L164
    L161:
        if (r02 != 27) goto L163;
        r33 = Theme.avatarDrawables[24];
        goto L164
    L163:
        r33 = Theme.avatarDrawables[9];
    L164:
        if (r33 == null) goto L169;
        int r03 = (int) (r33.getIntrinsicWidth() * this.scaleSize);
        int r52 = (int) (r33.getIntrinsicHeight() * this.scaleSize);
        int r6 = ((r9 - r03) / 2) + this.iconTx;
        int r92 = ((r9 - r52) / 2) + this.iconTy;
        r33.setBounds(r6, r92, r03 + r6, r52 + r92);
        if (this.alpha == 255) goto L168;
        int r62 = ColorUtils.setAlphaComponent(getThemedColor(r4), this.alpha);
        PorterDuff.Mode r7 = PorterDuff.Mode.SRC_IN;
        r33.setColorFilter(new PorterDuffColorFilter(r62, r7));
        r33.setAlpha(this.alpha);
        r33.draw(r2);
        r33.setColorFilter(new PorterDuffColorFilter(ColorUtils.setAlphaComponent(getThemedColor(r4), 255), r7));
        r33.setAlpha(255);
        goto L169
    L168:
        r33.draw(r2);
    L169:
        r2.restore();
        return;
    L59:
        if (this.customIconDrawable != null) goto L93;
        if (this.drawDeleted == false) goto L76;
        Drawable r04 = Theme.avatarDrawables[1];
        if (r04 == null) goto L76;
        int r05 = r04.getIntrinsicWidth();
        int r42 = Theme.avatarDrawables[1].getIntrinsicHeight();
        if (this.isProfile == false) goto L70;
        float r06 = r05;
        float r1 = this.scaleSize;
    L68:
        r05 = (int) (r06 * r1);
        r42 = (int) (r42 * r1);
    L74:
        int r12 = (r9 - r05) / 2;
        int r93 = (r9 - r42) / 2;
        Theme.avatarDrawables[1].setBounds(r12, r93, r05 + r12, r42 + r93);
        Theme.avatarDrawables[1].draw(r2);
        goto L169
    L70:
        if (r05 <= (r9 - AndroidUtilities.dp(6.0f))) goto L72;
    L73:
        r1 = r9 / AndroidUtilities.dp(50.0f);
        r06 = r05;
        goto L68
    L72:
        if (r42 <= (r9 - AndroidUtilities.dp(6.0f))) goto L74;
    L76:
        if (this.invalidateTextLayout == false) goto L91;
        this.invalidateTextLayout = false;
        if (this.stringBuilder.length() <= 0) goto L89;
        CharSequence r13 = Emoji.replaceEmoji(this.stringBuilder.toString().toUpperCase(), this.namePaint.getFontMetricsInt(), true);
        StaticLayout r07 = this.textLayout;
        if (r07 != null) goto L82;
    L171:
        StaticLayout r122 = new StaticLayout(r13, this.namePaint, AndroidUtilities.dp(100.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);     // Catch: Exception -> L87
        this.textLayout = r122;     // Catch: Exception -> L87
        if (r122.getLineCount() <= 0) goto L91;
        this.textLeft = this.textLayout.getLineLeft(0);     // Catch: Exception -> L87
        this.textWidth = this.textLayout.getLineWidth(0);     // Catch: Exception -> L87
        this.textHeight = this.textLayout.getLineBottom(0);     // Catch: Exception -> L87
    L87:
        e = move-exception;
        FileLog.e(e);
        goto L91
    L82:
        if (TextUtils.equals(r13, r07.getText()) == true) goto L91;
    L89:
        this.textLayout = null;
    L91:
        if (this.textLayout == null) goto L169;
        float r08 = r9;
        float r34 = r08 / AndroidUtilities.dp(50.0f);
        float r43 = r08 / 2.0f;
        r2.scale(r34, r34, r43, r43);
        r2.translate(((r08 - this.textWidth) / 2.0f) - this.textLeft, ((r08 - this.textHeight) / 2.0f) + AndroidUtilities.dpf2(0.5f));
        this.textLayout.draw(r2);
        goto L169
    L45:
        if (this.archivedAvatarProgress == 0.0f) goto L54;
        int r09 = Theme.key_avatar_backgroundArchived;
        r8.setColor(ColorUtils.setAlphaComponent(getThemedColor(r09), this.alpha));
        int r35 = this.roundRadius;
        if (r35 < 0) goto L49;
    L48:
        float r36 = r35;
        float r37 = r9;
        float r14 = this.archivedAvatarProgress;
        r21.drawRoundRect(0.0f, 0.0f, r37 * r14, r37 * r14, r36, r36, r8);
        r2 = r21;
        if (Theme.dialogs_archiveAvatarDrawableRecolored == false) goto L56;
        Theme.dialogs_archiveAvatarDrawable.beginApplyLayerColors();
        Theme.dialogs_archiveAvatarDrawable.setLayerColor("Arrow1", Theme.getNonAnimatedColor(r09));
        Theme.dialogs_archiveAvatarDrawable.setLayerColor("Arrow2", Theme.getNonAnimatedColor(r09));
        Theme.dialogs_archiveAvatarDrawable.commitApplyLayerColors();
        Theme.dialogs_archiveAvatarDrawableRecolored = false;
    L56:
        int r010 = Theme.dialogs_archiveAvatarDrawable.getIntrinsicWidth();
        int r15 = Theme.dialogs_archiveAvatarDrawable.getIntrinsicHeight();
        int r38 = (r9 - r010) / 2;
        int r94 = (r9 - r15) / 2;
        r2.save();
        Theme.dialogs_archiveAvatarDrawable.setBounds(r38, r94, r010 + r38, r15 + r94);
        Theme.dialogs_archiveAvatarDrawable.draw(r2);
        r2.restore();
        goto L169
    L49:
        r35 = ExteraConfig.getAvatarCorners(r9, true, false);
        goto L48
    L54:
        if (Theme.dialogs_archiveAvatarDrawableRecolored == true) goto L56;
        Theme.dialogs_archiveAvatarDrawable.beginApplyLayerColors();
        Theme.dialogs_archiveAvatarDrawable.setLayerColor("Arrow1", this.color);
        Theme.dialogs_archiveAvatarDrawable.setLayerColor("Arrow2", this.color);
        Theme.dialogs_archiveAvatarDrawable.commitApplyLayerColors();
        Theme.dialogs_archiveAvatarDrawableRecolored = true;
        goto L56
    L27:
        if (this.avatarType != 2) goto L31;
        if (this.archivedAvatarProgress >= 1.0f) goto L42;
    L31:
        if (this.rotate45Background == false) goto L33;
        r2.save();
        float r011 = r9 / 2.0f;
        r2.rotate(-45.0f, r011, r011);
    L33:
        RectF r012 = AndroidUtilities.rectTmp;
        float r72 = r9;
        r012.set(0.0f, 0.0f, r72, r72);
        int r11 = this.roundRadius;
        if (r11 != 0) goto L36;
        r2.drawRect(r012, r8);
    L40:
        if (this.rotate45Background == false) goto L42;
        r2.restore();
        goto L42
    L36:
        if (r11 <= 0) goto L38;
        r2.drawRoundRect(r012, r11, r11, r8);
        goto L40
    L38:
        float r73 = r72 / 2.0f;
        r2.drawCircle(r73, r73, r73, r8);
    L12:
        if (this.hasGradient == false) goto L23;
        int r152 = ColorUtils.setAlphaComponent(getColor(), this.alpha);
        int r53 = ColorUtils.setAlphaComponent(getColor2(), this.alpha);
        if (this.gradient != null) goto L16;
    L21:
        int r74 = r0.height();
        this.gradientBottom = r74;
        this.gradientColor1 = r152;
        this.gradientColor2 = r53;
        this.gradient = new LinearGradient(0.0f, 0.0f, 0.0f, r74, r152, r53, Shader.TileMode.CLAMP);
    L22:
        r32.setShader(this.gradient);
        r32.setAlpha(this.alpha);
        goto L10
    L16:
        if (this.gradientBottom != r0.height()) goto L21;
        if (this.gradientColor1 != r152) goto L21;
        if (this.gradientColor2 == r53) goto L22;
    L23:
        r32.setShader(null);
        r32.setColor(ColorUtils.setAlphaComponent(getColor(), this.alpha));
        goto L10
    }

    public int getAvatarType() {
        return this.avatarType;
    }

    public int getColor() {
        boolean r0 = this.needApplyColorAccent;
        int r1 = this.color;
        if (r0 == true) goto L5;
        return r1;
    L5:
        return Theme.changeColorAccent(r1);
    }

    public int getColor2() {
        boolean r0 = this.needApplyColorAccent;
        int r1 = this.color2;
        if (r0 == true) goto L5;
        return r1;
    L5:
        return Theme.changeColorAccent(r1);
    }

    public Drawable getCustomIcon() {
        return this.customIconDrawable;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.alpha = r1;
    }

    public void setArchivedAvatarHiddenProgress(float r1) {
        this.archivedAvatarProgress = r1;
    }

    public void setAvatarType(int r18) {
        this.avatarType = r18;
        boolean r2 = false;
        this.rotate45Background = false;
        this.hasAdvancedGradient = false;
        this.hasGradient = false;
        if (r18 != 13) goto L5;
        int r1 = Theme.getColor(Theme.key_chats_actionBackground);
        this.color2 = r1;
        this.color = r1;
    L66:
        int r12 = this.avatarType;
        if (r12 == 2) goto L75;
        if (r12 == 1) goto L75;
        if (r12 == 20) goto L75;
        if (r12 == 21) goto L75;
        if (r12 == 27) goto L75;
        if (r12 == 12) goto L75;
        if (r12 == 14) goto L75;
        r2 = true;
    L75:
        this.needApplyColorAccent = r2;
        return;
    L5:
        if (r18 != 2) goto L7;
        int r13 = getThemedColor(Theme.key_avatar_backgroundArchivedHidden);
        this.color2 = r13;
        this.color = r13;
        goto L66
    L7:
        if (r18 == 27) goto L65;
        if (r18 == 12) goto L65;
        if (r18 == 1) goto L65;
        if (r18 == 14) goto L65;
        if (r18 != 20) goto L15;
        this.rotate45Background = true;
        this.hasGradient = true;
        this.color = getThemedColor(Theme.key_stories_circle1);
        this.color2 = getThemedColor(Theme.key_stories_circle2);
        goto L66
    L15:
        if (r18 != 3) goto L18;
        this.hasGradient = true;
        this.color = getThemedColor(Theme.keys_avatar_background[getColorIndex(5)]);
        this.color2 = getThemedColor(Theme.keys_avatar_background2[getColorIndex(5)]);
        goto L66
    L18:
        if (r18 != 25) goto L21;
        this.hasGradient = true;
        this.color = getThemedColor(Theme.keys_avatar_background[getColorIndex(2)]);
        this.color2 = getThemedColor(Theme.keys_avatar_background2[getColorIndex(2)]);
        goto L66
    L21:
        if (r18 != 26) goto L24;
        this.hasGradient = true;
        this.color = getThemedColor(Theme.keys_avatar_background[getColorIndex(1)]);
        this.color2 = getThemedColor(Theme.keys_avatar_background2[getColorIndex(1)]);
        goto L66
    L24:
        if (r18 != 4) goto L27;
        this.hasGradient = true;
        this.color = getThemedColor(Theme.keys_avatar_background[getColorIndex(5)]);
        this.color2 = getThemedColor(Theme.keys_avatar_background2[getColorIndex(5)]);
        goto L66
    L27:
        if (r18 != 5) goto L30;
        this.hasGradient = true;
        this.color = getThemedColor(Theme.keys_avatar_background[getColorIndex(4)]);
        this.color2 = getThemedColor(Theme.keys_avatar_background2[getColorIndex(4)]);
        goto L66
    L30:
        if (r18 != 6) goto L32;
    L64:
        this.hasGradient = true;
        this.color = getThemedColor(Theme.keys_avatar_background[getColorIndex(3)]);
        this.color2 = getThemedColor(Theme.keys_avatar_background2[getColorIndex(3)]);
        goto L66
    L32:
        if (r18 == 23) goto L64;
        if (r18 != 7) goto L37;
    L63:
        this.hasGradient = true;
        this.color = getThemedColor(Theme.keys_avatar_background[getColorIndex(1)]);
        this.color2 = getThemedColor(Theme.keys_avatar_background2[getColorIndex(1)]);
        goto L66
    L37:
        if (r18 == 24) goto L63;
        if (r18 != 8) goto L43;
        this.hasGradient = true;
        this.color = getThemedColor(Theme.keys_avatar_background[getColorIndex(0)]);
        this.color2 = getThemedColor(Theme.keys_avatar_background2[getColorIndex(0)]);
        goto L66
    L43:
        if (r18 != 9) goto L46;
        this.hasGradient = true;
        this.color = getThemedColor(Theme.keys_avatar_background[getColorIndex(6)]);
        this.color2 = getThemedColor(Theme.keys_avatar_background2[getColorIndex(6)]);
        goto L66
    L46:
        if (r18 != 10) goto L49;
        this.hasGradient = true;
        this.color = getThemedColor(Theme.keys_avatar_background[getColorIndex(5)]);
        this.color2 = getThemedColor(Theme.keys_avatar_background2[getColorIndex(5)]);
        goto L66
    L49:
        if (r18 != 17) goto L51;
        this.hasGradient = true;
        this.color = getThemedColor(Theme.keys_avatar_background[getColorIndex(5)]);
        this.color2 = getThemedColor(Theme.keys_avatar_background2[getColorIndex(5)]);
        goto L66
    L51:
        if (r18 != 21) goto L57;
        this.hasAdvancedGradient = true;
        if (this.advancedGradient != null) goto L55;
        this.advancedGradient = new GradientTools();
    L55:
        this.advancedGradient.setColors(-8160001, -5217281, -36183, -1938945);
        goto L66
    L57:
        if (r18 != 22) goto L62;
        this.hasAdvancedGradient = true;
        if (this.advancedGradient != null) goto L61;
        this.advancedGradient = new GradientTools();
    L61:
        this.advancedGradient.setColors(-11694593, -13910017, -14622003, -15801871);
        goto L66
    L62:
        this.hasGradient = true;
        this.color = getThemedColor(Theme.keys_avatar_background[getColorIndex(4)]);
        this.color2 = getThemedColor(Theme.keys_avatar_background2[getColorIndex(4)]);
    L65:
        this.hasGradient = true;
        this.color = getThemedColor(Theme.key_avatar_backgroundSaved);
        this.color2 = getThemedColor(Theme.key_avatar_background2Saved);
        goto L66
    }

    public void setColor(int r2) {
        this.hasGradient = false;
        this.hasAdvancedGradient = false;
        this.color2 = r2;
        this.color = r2;
        this.needApplyColorAccent = false;
    }

    public void setColor(int r2, int r3) {
        this.hasGradient = true;
        this.hasAdvancedGradient = false;
        this.color = r2;
        this.color2 = r3;
        this.needApplyColorAccent = false;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setCustomIcon(Drawable r1) {
        this.customIconDrawable = r1;
    }

    public void setDrawAvatarBackground(boolean r1) {
        this.drawAvatarBackground = r1;
    }

    public void setInfo(int r2, TLObject r3) {
        if ((r3 instanceof TLRPC.User) == false) goto L7;
        setInfo(r2, (TLRPC.User) r3);
        return;
    L7:
        if ((r3 instanceof TLRPC.Chat) == false) goto L11;
        setInfo(r2, (TLRPC.Chat) r3);
        return;
    L11:
        if ((r3 instanceof TLRPC.ChatInvite) == false) goto L14;
        setInfo(r2, (TLRPC.ChatInvite) r3);
        return;
    }

    public void setInfo(int r9, TLRPC.Chat r10) {
        if (r10 == null) goto L10;
        long r1 = r10.id;
        String r3 = r10.title;
        if (r10.color == null) goto L7;
        Integer r0 = Integer.valueOf(ChatObject.getColorId(r10));
    L6:
        Integer r6 = r0;
        setInfo(r1, r3, null, null, r6, ChatObject.getPeerColorForAvatar(r9, r10));
        return;
    L7:
        r0 = null;
        goto L6
    }

    public void setInfo(int r9, TLRPC.ChatInvite r10) {
        if (r10 == null) goto L12;
        String r3 = r10.title;
        TLRPC.Chat r0 = r10.chat;
        if (r0 != null) goto L6;
    L9:
        Integer r02 = null;
    L8:
        Integer r6 = r02;
        setInfo(0, r3, null, null, r6, ChatObject.getPeerColorForAvatar(r9, r10.chat));
        return;
    L6:
        if (r0.color == null) goto L9;
        r02 = Integer.valueOf(ChatObject.getColorId(r0));
        goto L8
    }

    public void setInfo(int r9, TLRPC.User r10) {
        if (r10 == null) goto L10;
        long r1 = r10.id;
        String r3 = r10.first_name;
        String r4 = r10.last_name;
        if (r10.color == null) goto L7;
        Integer r0 = Integer.valueOf(UserObject.getColorId(r10));
    L6:
        Integer r6 = r0;
        setInfo(r1, r3, r4, null, r6, UserObject.getPeerColorForAvatar(r9, r10));
        this.drawDeleted = UserObject.isDeleted(r10);
        return;
    L7:
        r0 = null;
        goto L6
    }

    public void setInfo(long r4) {
        this.invalidateTextLayout = true;
        this.hasGradient = true;
        this.hasAdvancedGradient = false;
        this.color = getThemedColor(Theme.keys_avatar_background[getColorIndex(r4)]);
        this.color2 = getThemedColor(Theme.keys_avatar_background2[getColorIndex(r4)]);
        this.avatarType = 0;
        this.drawDeleted = false;
        getAvatarSymbols(_UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, this.stringBuilder);
    }

    public void setInfo(long r9, String r11, String r12) {
        setInfo(r9, r11, r12, null, null, null);
    }

    public void setInfo(long r9, String r11, String r12, String r13) {
        setInfo(r9, r11, r12, r13, null, null);
    }

    public void setInfo(long r10, String r12, String r13, String r14, Integer r15, MessagesController.PeerColor r16) {
        setInfo(r10, r12, r13, r14, r15, r16, false);
    }

    public void setInfo(long r6, String r8, String r9, String r10, Integer r11, MessagesController.PeerColor r12, boolean r13) {
        boolean r0 = true;
        this.invalidateTextLayout = true;
        if (r13 == false) goto L7;
        this.hasGradient = false;
        this.hasAdvancedGradient = true;
        if (this.advancedGradient != null) goto L9;
        this.advancedGradient = new GradientTools();
    L9:
        if (r12 == null) goto L13;
        if (r13 == false) goto L12;
        int[] r112 = advancedGradients[getPeerColorIndex(r12.getAvatarColor1())];
        this.advancedGradient.setColors(r112[0], r112[1], r112[2], r112[3]);
    L19:
        if (r6 == 5) goto L22;
        r0 = false;
    L22:
        this.needApplyColorAccent = r0;
        this.avatarType = 0;
        this.drawDeleted = false;
        if (r8 != null) goto L25;
    L26:
        r8 = r9;
        r9 = null;
    L27:
        getAvatarSymbols(r8, r9, r10, this.stringBuilder);
        return;
    L25:
        if (r8.length() != 0) goto L27;
    L12:
        this.color = r12.getAvatarColor1();
        this.color2 = r12.getAvatarColor2();
        goto L19
    L13:
        if (r11 == null) goto L15;
        setPeerColor(r11.intValue());
        goto L19
    L15:
        if (r13 == false) goto L17;
        int[] r113 = advancedGradients[getColorIndex(r6)];
        this.advancedGradient.setColors(r113[0], r113[1], r113[2], r113[3]);
        goto L19
    L17:
        this.color = getThemedColor(Theme.keys_avatar_background[getColorIndex(r6)]);
        this.color2 = getThemedColor(Theme.keys_avatar_background2[getColorIndex(r6)]);
        goto L19
    L7:
        this.hasGradient = true;
        this.hasAdvancedGradient = false;
        goto L9
    }

    public void setInfo(TLObject r2) {
        if ((r2 instanceof TLRPC.User) == false) goto L7;
        setInfo((TLRPC.User) r2);
        return;
    L7:
        if ((r2 instanceof TLRPC.Chat) == false) goto L11;
        setInfo((TLRPC.Chat) r2);
        return;
    L11:
        if ((r2 instanceof TLRPC.ChatInvite) == false) goto L14;
        setInfo((TLRPC.ChatInvite) r2);
        return;
    }

    public void setInfo(TLRPC.Chat r2) {
        setInfo(UserConfig.selectedAccount, r2);
    }

    public void setInfo(TLRPC.ChatInvite r2) {
        setInfo(UserConfig.selectedAccount, r2);
    }

    public void setInfo(TLRPC.User r2) {
        setInfo(UserConfig.selectedAccount, r2);
    }

    public void setPeerColor(int r9) {
        GradientTools r0 = this.advancedGradient;
        if (r0 == null) goto L5;
        this.hasGradient = false;
        this.hasAdvancedGradient = true;
    L7:
        if (r9 < 14) goto L26;
        MessagesController r02 = MessagesController.getInstance(UserConfig.selectedAccount);
        if (r02 == null) goto L21;
        MessagesController.PeerColors r3 = r02.peerColors;
        if (r3 == null) goto L21;
        if (r3.getColor(r9) == null) goto L21;
        int r92 = r02.peerColors.getColor(r9).getColor1();
        if (this.advancedGradient == null) goto L18;
        int[] r93 = advancedGradients[getPeerColorIndex(r92)];
        this.advancedGradient.setColors(r93[0], r93[1], r93[2], r93[3]);
        return;
    L18:
        this.color = getThemedColor(Theme.keys_avatar_background[getPeerColorIndex(r92)]);
        this.color2 = getThemedColor(Theme.keys_avatar_background2[getPeerColorIndex(r92)]);
        return;
    L21:
        if (this.advancedGradient == null) goto L24;
        int[] r94 = advancedGradients[getColorIndex(r9)];
        this.advancedGradient.setColors(r94[0], r94[1], r94[2], r94[3]);
        return;
    L24:
        long r1 = r9;
        this.color = getThemedColor(Theme.keys_avatar_background[getColorIndex(r1)]);
        this.color2 = getThemedColor(Theme.keys_avatar_background2[getColorIndex(r1)]);
        return;
    L26:
        if (r0 == null) goto L29;
        int[] r95 = advancedGradients[getColorIndex(r9)];
        this.advancedGradient.setColors(r95[0], r95[1], r95[2], r95[3]);
        return;
    L29:
        long r12 = r9;
        this.color = getThemedColor(Theme.keys_avatar_background[getColorIndex(r12)]);
        this.color2 = getThemedColor(Theme.keys_avatar_background2[getColorIndex(r12)]);
        return;
    L5:
        this.hasGradient = true;
        this.hasAdvancedGradient = false;
        goto L7
    }

    public void setProfile(boolean r1) {
        this.isProfile = r1;
    }

    public void setRoundRadius(int r1) {
        this.roundRadius = r1;
    }

    public void setScaleSize(float r1) {
        this.scaleSize = r1;
    }

    public void setText(String r2) {
        this.invalidateTextLayout = true;
        this.avatarType = 0;
        this.drawDeleted = false;
        getAvatarSymbols(r2, null, null, this.stringBuilder);
    }

    public void setTextSize(int r1) {
        this.namePaint.setTextSize(r1);
    }
}
