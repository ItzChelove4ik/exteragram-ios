package androidx.media3.common.util;

import android.graphics.Color;
import android.text.TextUtils;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.common.base.Ascii;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.Opcodes;

/* loaded from: classes3.dex */
public abstract class ColorParser {
    private static final Map<String, Integer> COLOR_MAP = null;
    private static final Pattern RGBA_PATTERN_FLOAT_ALPHA = null;
    private static final Pattern RGBA_PATTERN_INT_ALPHA = null;
    private static final Pattern RGB_PATTERN = null;

    static {
        RGB_PATTERN = Pattern.compile("^rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");
        RGBA_PATTERN_INT_ALPHA = Pattern.compile("^rgba\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");
        RGBA_PATTERN_FLOAT_ALPHA = Pattern.compile("^rgba\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3}),(\\d*\\.?\\d*?)\\)$");
        HashMap r0 = new HashMap();
        COLOR_MAP = r0;
        r0.put("aliceblue", -984833);
        r0.put("antiquewhite", -332841);
        r0.put("aqua", -16711681);
        r0.put("aquamarine", -8388652);
        r0.put("azure", -983041);
        r0.put("beige", -657956);
        r0.put("bisque", -6972);
        r0.put("black", -16777216);
        r0.put("blanchedalmond", -5171);
        r0.put("blue", -16776961);
        r0.put("blueviolet", -7722014);
        r0.put("brown", -5952982);
        r0.put("burlywood", -2180985);
        r0.put("cadetblue", -10510688);
        r0.put("chartreuse", -8388864);
        r0.put("chocolate", -2987746);
        r0.put("coral", -32944);
        r0.put("cornflowerblue", -10185235);
        r0.put("cornsilk", -1828);
        r0.put("crimson", -2354116);
        r0.put("cyan", -16711681);
        r0.put("darkblue", -16777077);
        r0.put("darkcyan", -16741493);
        r0.put("darkgoldenrod", -4684277);
        r0.put("darkgray", -5658199);
        r0.put("darkgreen", -16751616);
        r0.put("darkgrey", -5658199);
        r0.put("darkkhaki", -4343957);
        r0.put("darkmagenta", -7667573);
        r0.put("darkolivegreen", -11179217);
        r0.put("darkorange", -29696);
        r0.put("darkorchid", -6737204);
        r0.put("darkred", -7667712);
        r0.put("darksalmon", -1468806);
        r0.put("darkseagreen", -7357297);
        r0.put("darkslateblue", -12042869);
        r0.put("darkslategray", -13676721);
        r0.put("darkslategrey", -13676721);
        r0.put("darkturquoise", -16724271);
        r0.put("darkviolet", -7077677);
        r0.put("deeppink", -60269);
        r0.put("deepskyblue", -16728065);
        r0.put("dimgray", -9868951);
        r0.put("dimgrey", -9868951);
        r0.put("dodgerblue", -14774017);
        r0.put("firebrick", -5103070);
        r0.put("floralwhite", -1296);
        r0.put("forestgreen", -14513374);
        r0.put("fuchsia", -65281);
        r0.put("gainsboro", -2302756);
        r0.put("ghostwhite", -460545);
        r0.put("gold", -10496);
        r0.put("goldenrod", -2448096);
        r0.put("gray", -8355712);
        r0.put("green", -16744448);
        r0.put("greenyellow", -5374161);
        r0.put("grey", -8355712);
        r0.put("honeydew", -983056);
        r0.put("hotpink", -38476);
        r0.put("indianred", -3318692);
        r0.put("indigo", -11861886);
        r0.put("ivory", -16);
        r0.put("khaki", -989556);
        r0.put("lavender", -1644806);
        r0.put("lavenderblush", -3851);
        r0.put("lawngreen", -8586240);
        r0.put("lemonchiffon", -1331);
        r0.put("lightblue", -5383962);
        r0.put("lightcoral", -1015680);
        r0.put("lightcyan", -2031617);
        r0.put("lightgoldenrodyellow", -329006);
        r0.put("lightgray", -2894893);
        r0.put("lightgreen", -7278960);
        r0.put("lightgrey", -2894893);
        r0.put("lightpink", -18751);
        r0.put("lightsalmon", -24454);
        r0.put("lightseagreen", -14634326);
        r0.put("lightskyblue", -7876870);
        r0.put("lightslategray", -8943463);
        r0.put("lightslategrey", -8943463);
        r0.put("lightsteelblue", -5192482);
        r0.put("lightyellow", -32);
        r0.put("lime", -16711936);
        r0.put("limegreen", -13447886);
        r0.put("linen", -331546);
        r0.put("magenta", -65281);
        r0.put("maroon", -8388608);
        r0.put("mediumaquamarine", -10039894);
        r0.put("mediumblue", -16777011);
        r0.put("mediumorchid", -4565549);
        r0.put("mediumpurple", -7114533);
        r0.put("mediumseagreen", -12799119);
        r0.put("mediumslateblue", -8689426);
        r0.put("mediumspringgreen", -16713062);
        r0.put("mediumturquoise", -12004916);
        r0.put("mediumvioletred", -3730043);
        r0.put("midnightblue", -15132304);
        r0.put("mintcream", -655366);
        r0.put("mistyrose", -6943);
        r0.put("moccasin", -6987);
        r0.put("navajowhite", -8531);
        r0.put("navy", -16777088);
        r0.put("oldlace", -133658);
        r0.put("olive", -8355840);
        r0.put("olivedrab", -9728477);
        r0.put("orange", -23296);
        r0.put("orangered", -47872);
        r0.put("orchid", -2461482);
        r0.put("palegoldenrod", -1120086);
        r0.put("palegreen", -6751336);
        r0.put("paleturquoise", -5247250);
        r0.put("palevioletred", -2396013);
        r0.put("papayawhip", -4139);
        r0.put("peachpuff", -9543);
        r0.put("peru", -3308225);
        r0.put("pink", -16181);
        r0.put("plum", -2252579);
        r0.put("powderblue", -5185306);
        r0.put("purple", -8388480);
        r0.put("rebeccapurple", -10079335);
        r0.put(PluginsConstants.Settings.RED, Integer.valueOf(Opcodes.V_PREVIEW));
        r0.put("rosybrown", -4419697);
        r0.put("royalblue", -12490271);
        r0.put("saddlebrown", -7650029);
        r0.put("salmon", -360334);
        r0.put("sandybrown", -744352);
        r0.put("seagreen", -13726889);
        r0.put("seashell", -2578);
        r0.put("sienna", -6270419);
        r0.put("silver", -4144960);
        r0.put("skyblue", -7876885);
        r0.put("slateblue", -9807155);
        r0.put("slategray", -9404272);
        r0.put("slategrey", -9404272);
        r0.put("snow", -1286);
        r0.put("springgreen", -16711809);
        r0.put("steelblue", -12156236);
        r0.put("tan", -2968436);
        r0.put("teal", -16744320);
        r0.put("thistle", -2572328);
        r0.put("tomato", -40121);
        r0.put("transparent", 0);
        r0.put("turquoise", -12525360);
        r0.put("violet", -1146130);
        r0.put("wheat", -663885);
        r0.put("white", -1);
        r0.put("whitesmoke", -657931);
        r0.put("yellow", -256);
        r0.put("yellowgreen", -6632142);
    }

    private static int parseColorInternal(String r6, boolean r7) {
        Assertions.checkArgument(!TextUtils.isEmpty(r6));
        String r62 = r6.replace(" ", _UrlKt.FRAGMENT_ENCODE_SET);
        if (r62.charAt(0) != '#') goto L15;
        int r72 = (int) Long.parseLong(r62.substring(1), 16);
        if (r62.length() != 7) goto L9;
        return (-16777216) | r72;
    L9:
        if (r62.length() == 9) goto L11;
        Segment$$ExternalSyntheticBUOutline0.m();
        return 0;
    L11:
        return ((r72 & 255) << 24) | (r72 >>> 8);
    L15:
        if (r62.startsWith("rgba") == false) goto L28;
        if (r7 == false) goto L18;
        Pattern r2 = RGBA_PATTERN_FLOAT_ALPHA;
    L19:
        Matcher r63 = r2.matcher(r62);
        if (r63.matches() == true) goto L22;
    L37:
        Segment$$ExternalSyntheticBUOutline0.m();
        return 0;
    L22:
        if (r7 == false) goto L24;
        int r73 = (int) (Float.parseFloat((String) Assertions.checkNotNull(r63.group(4))) * 255.0f);
    L26:
        return Color.argb(r73, Integer.parseInt((String) Assertions.checkNotNull(r63.group(1)), 10), Integer.parseInt((String) Assertions.checkNotNull(r63.group(2)), 10), Integer.parseInt((String) Assertions.checkNotNull(r63.group(3)), 10));
    L24:
        r73 = Integer.parseInt((String) Assertions.checkNotNull(r63.group(4)), 10);
        goto L26
    L18:
        r2 = RGBA_PATTERN_INT_ALPHA;
        goto L19
    L28:
        if (r62.startsWith("rgb") == false) goto L33;
        Matcher r64 = RGB_PATTERN.matcher(r62);
        if (r64.matches() == false) goto L37;
        return Color.rgb(Integer.parseInt((String) Assertions.checkNotNull(r64.group(1)), 10), Integer.parseInt((String) Assertions.checkNotNull(r64.group(2)), 10), Integer.parseInt((String) Assertions.checkNotNull(r64.group(3)), 10));
    L33:
        Integer r65 = COLOR_MAP.get(Ascii.toLowerCase(r62));
        if (r65 == null) goto L37;
        return r65.intValue();
    }

    public static int parseCssColor(String r1) {
        return parseColorInternal(r1, true);
    }

    public static int parseTtmlColor(String r1) {
        return parseColorInternal(r1, false);
    }
}
