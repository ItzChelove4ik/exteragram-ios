package com.google.android.gms.cast;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.accessibility.CaptioningManager;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.JsonUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public final class TextTrackStyle extends AbstractSafeParcelable {
    public static final int COLOR_UNSPECIFIED = 0;
    public static final Parcelable.Creator<TextTrackStyle> CREATOR = null;
    public static final float DEFAULT_FONT_SCALE = 1.0f;
    public static final int EDGE_TYPE_DEPRESSED = 4;
    public static final int EDGE_TYPE_DROP_SHADOW = 2;
    public static final int EDGE_TYPE_NONE = 0;
    public static final int EDGE_TYPE_OUTLINE = 1;
    public static final int EDGE_TYPE_RAISED = 3;
    public static final int EDGE_TYPE_UNSPECIFIED = -1;
    public static final int FONT_FAMILY_CASUAL = 4;
    public static final int FONT_FAMILY_CURSIVE = 5;
    public static final int FONT_FAMILY_MONOSPACED_SANS_SERIF = 1;
    public static final int FONT_FAMILY_MONOSPACED_SERIF = 3;
    public static final int FONT_FAMILY_SANS_SERIF = 0;
    public static final int FONT_FAMILY_SERIF = 2;
    public static final int FONT_FAMILY_SMALL_CAPITALS = 6;
    public static final int FONT_FAMILY_UNSPECIFIED = -1;
    public static final int FONT_STYLE_BOLD = 1;
    public static final int FONT_STYLE_BOLD_ITALIC = 3;
    public static final int FONT_STYLE_ITALIC = 2;
    public static final int FONT_STYLE_NORMAL = 0;
    public static final int FONT_STYLE_UNSPECIFIED = -1;
    public static final int WINDOW_TYPE_NONE = 0;
    public static final int WINDOW_TYPE_NORMAL = 1;
    public static final int WINDOW_TYPE_ROUNDED = 2;
    public static final int WINDOW_TYPE_UNSPECIFIED = -1;
    String zza;
    private float zzb;
    private int zzc;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private String zzj;
    private int zzk;
    private int zzl;
    private JSONObject zzm;

    static {
        CREATOR = new zzdf();
    }

    public TextTrackStyle() {
        this(1.0f, 0, 0, -1, 0, -1, 0, 0, null, -1, -1, null);
    }

    public TextTrackStyle(float r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, String r9, int r10, int r11, String r12) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r4;
        this.zzf = r5;
        this.zzg = r6;
        this.zzh = r7;
        this.zzi = r8;
        this.zzj = r9;
        this.zzk = r10;
        this.zzl = r11;
        this.zza = r12;
        if (r12 != null) goto L10;
        this.zzm = null;
        return;
    L10:
        this.zzm = new JSONObject(this.zza);     // Catch: JSONException -> L6
        return;
    L6:
        this.zzm = null;
        this.zza = null;
    }

    public static TextTrackStyle fromSystemSettings(Context r5) {
        TextTrackStyle r0 = new TextTrackStyle();
        CaptioningManager r52 = (CaptioningManager) r5.getSystemService("captioning");
        if (r52 == null) goto L35;
        r0.setFontScale(r52.getFontScale());
        CaptioningManager.CaptionStyle r53 = r52.getUserStyle();
        r0.setBackgroundColor(r53.backgroundColor);
        r0.setForegroundColor(r53.foregroundColor);
        int r1 = r53.edgeType;
        if (r1 == 1) goto L10;
        if (r1 == 2) goto L9;
        r0.setEdgeType(0);
    L11:
        r0.setEdgeColor(r53.edgeColor);
        Typeface r54 = r53.getTypeface();
        if (r54 == null) goto L35;
        if (Typeface.MONOSPACE.equals(r54) == false) goto L17;
        r0.setFontGenericFamily(1);
    L23:
        boolean r12 = r54.isBold();
        boolean r55 = r54.isItalic();
        if (r12 == false) goto L28;
        if (r55 == false) goto L28;
        r0.setFontStyle(3);
        return r0;
    L28:
        if (r12 == false) goto L31;
        r0.setFontStyle(1);
        return r0;
    L31:
        if (r55 == false) goto L34;
        r0.setFontStyle(2);
        return r0;
    L34:
        r0.setFontStyle(0);
        goto L35
    L17:
        if (Typeface.SANS_SERIF.equals(r54) == false) goto L20;
        r0.setFontGenericFamily(0);
        goto L23
    L20:
        if (Typeface.SERIF.equals(r54) == false) goto L22;
        r0.setFontGenericFamily(2);
        goto L23
    L22:
        r0.setFontGenericFamily(0);
        goto L23
    L9:
        r0.setEdgeType(2);
        goto L11
    L10:
        r0.setEdgeType(1);
    L35:
        return r0;
    }

    private static final int zzb(String r7) {
        if (r7 != null) goto L5;
    L11:
        return 0;
    L5:
        if (r7.length() != 9) goto L11;
        if (r7.charAt(0) != '#') goto L11;
        int r1 = Integer.parseInt(r7.substring(1, 3), 16);     // Catch: NumberFormatException -> L12
        int r3 = Integer.parseInt(r7.substring(3, 5), 16);     // Catch: NumberFormatException -> L12
        int r5 = Integer.parseInt(r7.substring(5, 7), 16);     // Catch: NumberFormatException -> L12
        return Color.argb(Integer.parseInt(r7.substring(7, 9), 16), r1, r3, r5);
    }

    private static final String zzc(int r3) {
        return String.format("#%02X%02X%02X%02X", new Object[]{Integer.valueOf(Color.red(r3)), Integer.valueOf(Color.green(r3)), Integer.valueOf(Color.blue(r3)), Integer.valueOf(Color.alpha(r3))});
    }

    public boolean equals(Object r7) {
        if (this != r7) goto L6;
        return true;
    L6:
        if ((r7 instanceof TextTrackStyle) == true) goto L8;
        return false;
    L8:
        TextTrackStyle r72 = (TextTrackStyle) r7;
        JSONObject r1 = this.zzm;
        if (r1 == null) goto L11;
        boolean r3 = false;
    L12:
        JSONObject r4 = r72.zzm;
        if (r4 == null) goto L15;
        boolean r5 = false;
    L16:
        if (r3 == r5) goto L18;
        return false;
    L18:
        if (r1 == null) goto L24;
        if (r4 == null) goto L24;
        if (JsonUtils.areJsonValuesEquivalent(r1, r4) == true) goto L24;
        return false;
    L24:
        if (this.zzb == r72.zzb) goto L26;
    L46:
        return false;
    L26:
        if (this.zzc != r72.zzc) goto L46;
        if (this.zzd != r72.zzd) goto L46;
        if (this.zze != r72.zze) goto L46;
        if (this.zzf != r72.zzf) goto L46;
        if (this.zzg != r72.zzg) goto L46;
        if (this.zzh != r72.zzh) goto L46;
        if (this.zzi != r72.zzi) goto L46;
        if (CastUtils.zza(this.zzj, r72.zzj) == false) goto L46;
        if (this.zzk != r72.zzk) goto L46;
        if (this.zzl != r72.zzl) goto L46;
        return true;
    L15:
        r5 = true;
        goto L16
    L11:
        r3 = true;
        goto L12
    }

    public void fromJson(JSONObject r10) {
        this.zzb = (float) r10.optDouble("fontScale", 1.0d);
        this.zzc = zzb(r10.optString("foregroundColor"));
        this.zzd = zzb(r10.optString("backgroundColor"));
        if (r10.has("edgeType") == false) goto L19;
        String r0 = r10.getString("edgeType");
        if ("NONE".equals(r0) == false) goto L8;
        this.zze = 0;
        goto L19
    L8:
        if ("OUTLINE".equals(r0) == false) goto L11;
        this.zze = 1;
        goto L19
    L11:
        if ("DROP_SHADOW".equals(r0) == false) goto L14;
        this.zze = 2;
        goto L19
    L14:
        if ("RAISED".equals(r0) == false) goto L17;
        this.zze = 3;
        goto L19
    L17:
        if ("DEPRESSED".equals(r0) == false) goto L19;
        this.zze = 4;
    L19:
        this.zzf = zzb(r10.optString("edgeColor"));
        if (r10.has("windowType") == false) goto L30;
        String r02 = r10.getString("windowType");
        if ("NONE".equals(r02) == false) goto L25;
        this.zzg = 0;
        goto L30
    L25:
        if ("NORMAL".equals(r02) == false) goto L28;
        this.zzg = 1;
        goto L30
    L28:
        if ("ROUNDED_CORNERS".equals(r02) == false) goto L30;
        this.zzg = 2;
    L30:
        this.zzh = zzb(r10.optString("windowColor"));
        if (this.zzg != 2) goto L33;
        this.zzi = r10.optInt("windowRoundedCornerRadius", 0);
    L33:
        this.zzj = CastUtils.optStringOrNull(r10, "fontFamily");
        if (r10.has("fontGenericFamily") == false) goto L58;
        String r03 = r10.getString("fontGenericFamily");
        if ("SANS_SERIF".equals(r03) == false) goto L39;
        this.zzk = 0;
        goto L58
    L39:
        if ("MONOSPACED_SANS_SERIF".equals(r03) == false) goto L42;
        this.zzk = 1;
        goto L58
    L42:
        if ("SERIF".equals(r03) == false) goto L45;
        this.zzk = 2;
        goto L58
    L45:
        if ("MONOSPACED_SERIF".equals(r03) == false) goto L48;
        this.zzk = 3;
        goto L58
    L48:
        if ("CASUAL".equals(r03) == false) goto L51;
        this.zzk = 4;
        goto L58
    L51:
        if ("CURSIVE".equals(r03) == false) goto L55;
        int r04 = 5;
    L53:
        this.zzk = r04;
        goto L58
    L55:
        if ("SMALL_CAPITALS".equals(r03) == false) goto L58;
        r04 = 6;
    L58:
        if (r10.has("fontStyle") == false) goto L71;
        String r05 = r10.getString("fontStyle");
        if ("NORMAL".equals(r05) == false) goto L63;
        this.zzl = 0;
        goto L71
    L63:
        if ("BOLD".equals(r05) == false) goto L66;
        this.zzl = 1;
        goto L71
    L66:
        if ("ITALIC".equals(r05) == false) goto L69;
        this.zzl = 2;
        goto L71
    L69:
        if ("BOLD_ITALIC".equals(r05) == false) goto L71;
        this.zzl = 3;
    L71:
        this.zzm = r10.optJSONObject("customData");
    }

    public int getBackgroundColor() {
        return this.zzd;
    }

    public JSONObject getCustomData() {
        return this.zzm;
    }

    public int getEdgeColor() {
        return this.zzf;
    }

    public int getEdgeType() {
        return this.zze;
    }

    public String getFontFamily() {
        return this.zzj;
    }

    public int getFontGenericFamily() {
        return this.zzk;
    }

    public float getFontScale() {
        return this.zzb;
    }

    public int getFontStyle() {
        return this.zzl;
    }

    public int getForegroundColor() {
        return this.zzc;
    }

    public int getWindowColor() {
        return this.zzh;
    }

    public int getWindowCornerRadius() {
        return this.zzi;
    }

    public int getWindowType() {
        return this.zzg;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Float.valueOf(this.zzb), Integer.valueOf(this.zzc), Integer.valueOf(this.zzd), Integer.valueOf(this.zze), Integer.valueOf(this.zzf), Integer.valueOf(this.zzg), Integer.valueOf(this.zzh), Integer.valueOf(this.zzi), this.zzj, Integer.valueOf(this.zzk), Integer.valueOf(this.zzl), String.valueOf(this.zzm)});
    }

    public void setBackgroundColor(int r1) {
        this.zzd = r1;
    }

    public void setCustomData(JSONObject r1) {
        this.zzm = r1;
    }

    public void setEdgeColor(int r1) {
        this.zzf = r1;
    }

    public void setEdgeType(int r2) {
        if (r2 >= 0) goto L4;
    L7:
        f$$ExternalSyntheticBUOutline1.m("invalid edgeType");
        return;
    L4:
        if (r2 > 4) goto L7;
        this.zze = r2;
    }

    public void setFontFamily(String r1) {
        this.zzj = r1;
    }

    public void setFontGenericFamily(int r2) {
        if (r2 >= 0) goto L4;
    L7:
        f$$ExternalSyntheticBUOutline1.m("invalid fontGenericFamily");
        return;
    L4:
        if (r2 > 6) goto L7;
        this.zzk = r2;
    }

    public void setFontScale(float r1) {
        this.zzb = r1;
    }

    public void setFontStyle(int r2) {
        if (r2 >= 0) goto L4;
    L7:
        f$$ExternalSyntheticBUOutline1.m("invalid fontStyle");
        return;
    L4:
        if (r2 > 3) goto L7;
        this.zzl = r2;
    }

    public void setForegroundColor(int r1) {
        this.zzc = r1;
    }

    public void setWindowColor(int r1) {
        this.zzh = r1;
    }

    public void setWindowCornerRadius(int r1) {
        if (r1 < 0) goto L5;
        this.zzi = r1;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("invalid windowCornerRadius");
    }

    public void setWindowType(int r2) {
        if (r2 >= 0) goto L4;
    L7:
        f$$ExternalSyntheticBUOutline1.m("invalid windowType");
        return;
    L4:
        if (r2 > 2) goto L7;
        this.zzg = r2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        JSONObject r52 = this.zzm;
        if (r52 != null) goto L5;
        String r53 = null;
    L6:
        this.zza = r53;
        int r54 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeFloat(r4, 2, getFontScale());
        SafeParcelWriter.writeInt(r4, 3, getForegroundColor());
        SafeParcelWriter.writeInt(r4, 4, getBackgroundColor());
        SafeParcelWriter.writeInt(r4, 5, getEdgeType());
        SafeParcelWriter.writeInt(r4, 6, getEdgeColor());
        SafeParcelWriter.writeInt(r4, 7, getWindowType());
        SafeParcelWriter.writeInt(r4, 8, getWindowColor());
        SafeParcelWriter.writeInt(r4, 9, getWindowCornerRadius());
        SafeParcelWriter.writeString(r4, 10, getFontFamily(), false);
        SafeParcelWriter.writeInt(r4, 11, getFontGenericFamily());
        SafeParcelWriter.writeInt(r4, 12, getFontStyle());
        SafeParcelWriter.writeString(r4, 13, this.zza, false);
        SafeParcelWriter.finishObjectHeader(r4, r54);
        return;
    L5:
        r53 = r52.toString();
        goto L6
    }

    public final JSONObject zza() {
        JSONObject r0 = new JSONObject();
        r0.put("fontScale", this.zzb);     // Catch: JSONException -> L70
        int r1 = this.zzc;     // Catch: JSONException -> L70
        if (r1 == 0) goto L6;
        r0.put("foregroundColor", zzc(r1));     // Catch: JSONException -> L70
    L6:
        int r12 = this.zzd;     // Catch: JSONException -> L70
        if (r12 == 0) goto L9;
        r0.put("backgroundColor", zzc(r12));     // Catch: JSONException -> L70
    L9:
        int r13 = this.zze;     // Catch: JSONException -> L70
        if (r13 == 0) goto L22;
        if (r13 == 1) goto L21;
        if (r13 == 2) goto L20;
        if (r13 != 3) goto L16;
        r0.put("edgeType", "RAISED");     // Catch: JSONException -> L70
    L23:
        int r14 = this.zzf;     // Catch: JSONException -> L70
        if (r14 == 0) goto L26;
        r0.put("edgeColor", zzc(r14));     // Catch: JSONException -> L70
    L26:
        int r15 = this.zzg;     // Catch: JSONException -> L70
        if (r15 == 0) goto L34;
        if (r15 == 1) goto L33;
        if (r15 != 2) goto L35;
        r0.put("windowType", "ROUNDED_CORNERS");     // Catch: JSONException -> L70
    L35:
        int r16 = this.zzh;     // Catch: JSONException -> L70
        if (r16 == 0) goto L39;
        r0.put("windowColor", zzc(r16));     // Catch: JSONException -> L70
    L39:
        if (this.zzg != 2) goto L41;
        r0.put("windowRoundedCornerRadius", this.zzi);     // Catch: JSONException -> L70
    L41:
        String r17 = this.zzj;     // Catch: JSONException -> L70
        if (r17 == null) goto L46;
        r0.put("fontFamily", r17);     // Catch: JSONException -> L70
    L46:
        switch(this.zzk) {
            case 0: goto L54;
            case 1: goto L53;
            case 2: goto L52;
            case 3: goto L51;
            case 4: goto L50;
            case 5: goto L49;
            case 6: goto L48;
            default: goto L55;
        };
    L48:
        r0.put("fontGenericFamily", "SMALL_CAPITALS");     // Catch: JSONException -> L70
        goto L55
    L49:
        r0.put("fontGenericFamily", "CURSIVE");     // Catch: JSONException -> L70
        goto L55
    L50:
        r0.put("fontGenericFamily", "CASUAL");     // Catch: JSONException -> L70
        goto L55
    L51:
        r0.put("fontGenericFamily", "MONOSPACED_SERIF");     // Catch: JSONException -> L70
        goto L55
    L52:
        r0.put("fontGenericFamily", "SERIF");     // Catch: JSONException -> L70
        goto L55
    L53:
        r0.put("fontGenericFamily", "MONOSPACED_SANS_SERIF");     // Catch: JSONException -> L70
        goto L55
    L54:
        r0.put("fontGenericFamily", "SANS_SERIF");     // Catch: JSONException -> L70
    L55:
        int r18 = this.zzl;     // Catch: JSONException -> L70
        if (r18 == 0) goto L65;
        if (r18 == 1) goto L64;
        if (r18 == 2) goto L63;
        if (r18 != 3) goto L66;
        r0.put("fontStyle", "BOLD_ITALIC");     // Catch: JSONException -> L70
    L66:
        JSONObject r8 = this.zzm;     // Catch: JSONException -> L70
        if (r8 == null) goto L69;
        r0.put("customData", r8);     // Catch: JSONException -> L70
        goto L69
    L63:
        r0.put("fontStyle", "ITALIC");     // Catch: JSONException -> L70
        goto L66
    L64:
        r0.put("fontStyle", "BOLD");     // Catch: JSONException -> L70
        goto L66
    L65:
        r0.put("fontStyle", "NORMAL");     // Catch: JSONException -> L70
        goto L66
    L33:
        r0.put("windowType", "NORMAL");     // Catch: JSONException -> L70
        goto L35
    L34:
        r0.put("windowType", "NONE");     // Catch: JSONException -> L70
        goto L35
    L16:
        if (r13 != 4) goto L23;
        r0.put("edgeType", "DEPRESSED");     // Catch: JSONException -> L70
        goto L23
    L20:
        r0.put("edgeType", "DROP_SHADOW");     // Catch: JSONException -> L70
        goto L23
    L21:
        r0.put("edgeType", "OUTLINE");     // Catch: JSONException -> L70
        goto L23
    L22:
        r0.put("edgeType", "NONE");     // Catch: JSONException -> L70
    L69:
        return r0;
    }
}
