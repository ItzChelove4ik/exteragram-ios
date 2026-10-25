package org.telegram.messenger;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.SparseArray;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import javax.xml.parsers.SAXParserFactory;
import kotlin.UByte;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Opcodes;
import org.mvel2.asm.signature.SignatureVisitor;
import org.telegram.messenger.wallpaper.WallpaperGiftPatternPosition;
import org.telegram.ui.ActionBar.Theme;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/* loaded from: classes.dex */
public class SvgHelper {
    private static final Pattern SPLIT_BOUNDARY = null;
    private static final double[] pow10 = null;

    /* loaded from: classes5.dex */
    public static class Circle {
        float rad;
        float x1;
        float y1;

        public Circle(float r1, float r2, float r3) {
            this.x1 = r1;
            this.y1 = r2;
            this.rad = r3;
        }
    }

    /* loaded from: classes5.dex */
    public static class Line {
        float x1;
        float x2;
        float y1;
        float y2;

        public Line(float r1, float r2, float r3, float r4) {
            this.x1 = r1;
            this.y1 = r2;
            this.x2 = r3;
            this.y2 = r4;
        }
    }

    /* loaded from: classes5.dex */
    public static class NumberParse {
        private int nextCmd;
        private ArrayList<Float> numbers;

        /* renamed from: -$$Nest$fgetnumbers, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m4989$$Nest$fgetnumbers(NumberParse r0) {
            return r0.numbers;
        }

        public NumberParse(ArrayList<Float> r1, int r2) {
            this.numbers = r1;
            this.nextCmd = r2;
        }

        public int getNextCmd() {
            return this.nextCmd;
        }

        public float getNumber(int r1) {
            return this.numbers.get(r1).floatValue();
        }
    }

    /* loaded from: classes5.dex */
    public static class Oval {
        RectF rect;

        public Oval(RectF r1) {
            this.rect = r1;
        }
    }

    public static class ParserHelper {
        private char current;
        private int n;
        public int pos;
        private CharSequence s;

        public ParserHelper(CharSequence r2, int r3) {
            this.s = r2;
            this.pos = r3;
            this.n = r2.length();
            this.current = r2.charAt(r3);
        }

        private char read() {
            int r0 = this.pos;
            int r1 = this.n;
            if (r0 >= r1) goto L5;
            this.pos = r0 + 1;
        L5:
            int r02 = this.pos;
            if (r02 != r1) goto L10;
            return 0;
        L10:
            return this.s.charAt(r02);
        }

        private void reportUnexpectedCharacterError(char r3) {
            throw new RuntimeException("Unexpected char '" + r3 + "'.");
        }

        public void advance() {
            this.current = read();
        }

        public float buildFloat(int r4, int r5) {
            if (r5 < (-125)) goto L25;
            if (r4 != 0) goto L7;
            return 0.0f;
        L7:
            if (r5 < 128) goto L13;
            if (r4 <= 0) goto L11;
            return Float.POSITIVE_INFINITY;
        L11:
            return Float.NEGATIVE_INFINITY;
        L13:
            if (r5 != 0) goto L17;
            return r4;
        L17:
            if (r4 < 67108864) goto L19;
            r4 = r4 + 1;
        L19:
            double r3 = r4;
            double[] r0 = SvgHelper.m4983$$Nest$sfgetpow10();
            if (r5 <= 0) goto L22;
            double r32 = r3 * r0[r5];
        L24:
            return (float) r32;
        L22:
            r32 = r3 / r0[-r5];
            goto L24
        L25:
            return 0.0f;
        }

        public float nextFloat() {
            skipWhitespace();
            float r0 = parseFloat();
            skipNumberSeparator();
            return r0;
        }

        public float parseFloat() {
            char r0 = this.current;
            int r2 = 0;
            boolean r3 = true;
            if (r0 == '+') goto L7;
            if (r0 == '-') goto L6;
            boolean r02 = true;
        L10:
            switch(this.current) {
                case 46: goto L27;
                case 47: goto L11;
                case 48: goto L13;
                case 49: goto L19;
                case 50: goto L19;
                case 51: goto L19;
                case 52: goto L19;
                case 53: goto L19;
                case 54: goto L19;
                case 55: goto L19;
                case 56: goto L19;
                case 57: goto L19;
                default: goto L11;
            };
        L11:
            return Float.NaN;
        L13:
            char r5 = read();
            this.current = r5;
            if (r5 == '.') goto L26;
            if (r5 == 'E') goto L26;
            if (r5 == 'e') goto L26;
            switch(r5) {
                case 48: goto L13;
                case 49: goto L19;
                case 50: goto L19;
                case 51: goto L19;
                case 52: goto L19;
                case 53: goto L19;
                case 54: goto L19;
                case 55: goto L19;
                case 56: goto L19;
                case 57: goto L19;
                default: goto L18;
            };
        L18:
            return 0.0f;
        L26:
            int r52 = 0;
            int r11 = 0;
            int r12 = 0;
        L25:
            boolean r13 = true;
        L29:
            if (this.current != '.') goto L44;
            char r9 = read();
            this.current = r9;
            switch(r9) {
                case 48: goto L35;
                case 49: goto L40;
                case 50: goto L40;
                case 51: goto L40;
                case 52: goto L40;
                case 53: goto L40;
                case 54: goto L40;
                case 55: goto L40;
                case 56: goto L40;
                case 57: goto L40;
                default: goto L32;
            };
        L32:
            if (r13 == true) goto L44;
            reportUnexpectedCharacterError(r9);
            return 0.0f;
        L35:
            if (r52 != 0) goto L40;
        L36:
            char r92 = read();
            this.current = r92;
            r11 = r11 - 1;
            switch(r92) {
                case 48: goto L36;
                case 49: goto L40;
                case 50: goto L40;
                case 51: goto L40;
                case 52: goto L40;
                case 53: goto L40;
                case 54: goto L40;
                case 55: goto L40;
                case 56: goto L40;
                case 57: goto L40;
                default: goto L38;
            };
        L38:
            if (r13 == true) goto L44;
            return 0.0f;
        L40:
            if (r52 >= 9) goto L42;
            r52 = r52 + 1;
            r12 = (r12 * 10) + (this.current - '0');
            r11 = r11 - 1;
        L42:
            char r93 = read();
            this.current = r93;
            switch(r93) {
                case 48: goto L40;
                case 49: goto L40;
                case 50: goto L40;
                case 51: goto L40;
                case 52: goto L40;
                case 53: goto L40;
                case 54: goto L40;
                case 55: goto L40;
                case 56: goto L40;
                case 57: goto L40;
                default: goto L44;
            };
        L44:
            char r53 = this.current;
            if (r53 == 'E') goto L48;
            if (r53 == 'e') goto L48;
        L72:
            if (r3 == true) goto L74;
            r2 = -r2;
        L74:
            int r22 = r2 + r11;
            if (r02 == true) goto L78;
            r12 = -r12;
        L78:
            return buildFloat(r12, r22);
        L48:
            char r54 = read();
            this.current = r54;
            if (r54 == '+') goto L55;
            if (r54 == '-') goto L54;
            switch(r54) {
                case 48: goto L60;
                case 49: goto L60;
                case 50: goto L60;
                case 51: goto L60;
                case 52: goto L60;
                case 53: goto L60;
                case 54: goto L60;
                case 55: goto L60;
                case 56: goto L60;
                case 57: goto L60;
                default: goto L52;
            };
        L52:
            reportUnexpectedCharacterError(r54);
            return 0.0f;
        L60:
            switch(this.current) {
                case 48: goto L62;
                case 49: goto L65;
                case 50: goto L65;
                case 51: goto L65;
                case 52: goto L65;
                case 53: goto L65;
                case 54: goto L65;
                case 55: goto L65;
                case 56: goto L65;
                case 57: goto L65;
                default: goto L72;
            };
        L62:
            char r1 = read();
            this.current = r1;
            switch(r1) {
                case 48: goto L62;
                case 49: goto L65;
                case 50: goto L65;
                case 51: goto L65;
                case 52: goto L65;
                case 53: goto L65;
                case 54: goto L65;
                case 55: goto L65;
                case 56: goto L65;
                case 57: goto L65;
                default: goto L72;
            };
        L65:
            int r14 = 0;
        L67:
            if (r2 >= 3) goto L69;
            r2 = r2 + 1;
            r14 = (r14 * 10) + (this.current - '0');
        L69:
            char r4 = read();
            this.current = r4;
            switch(r4) {
                case 48: goto L67;
                case 49: goto L67;
                case 50: goto L67;
                case 51: goto L67;
                case 52: goto L67;
                case 53: goto L67;
                case 54: goto L67;
                case 55: goto L67;
                case 56: goto L67;
                case 57: goto L67;
                default: goto L71;
            };
        L71:
            r2 = r14;
            goto L72
        L54:
            r3 = false;
        L55:
            char r15 = read();
            this.current = r15;
            switch(r15) {
                case 48: goto L60;
                case 49: goto L60;
                case 50: goto L60;
                case 51: goto L60;
                case 52: goto L60;
                case 53: goto L60;
                case 54: goto L60;
                case 55: goto L60;
                case 56: goto L60;
                case 57: goto L60;
                default: goto L57;
            };
        L57:
            reportUnexpectedCharacterError(r15);
            return 0.0f;
        L19:
            r52 = 0;
            r11 = 0;
            r12 = 0;
        L20:
            if (r52 >= 9) goto L22;
            r52 = r52 + 1;
            r12 = (r12 * 10) + (this.current - '0');
        L23:
            char r132 = read();
            this.current = r132;
            switch(r132) {
                case 48: goto L20;
                case 49: goto L20;
                case 50: goto L20;
                case 51: goto L20;
                case 52: goto L20;
                case 53: goto L20;
                case 54: goto L20;
                case 55: goto L20;
                case 56: goto L20;
                case 57: goto L20;
                default: goto L25;
            };
        L22:
            r11 = r11 + 1;
            goto L23
        L27:
            r52 = 0;
            r11 = 0;
            r12 = 0;
            r13 = false;
            goto L29
        L6:
            r02 = false;
        L8:
            this.current = read();
            goto L10
        L7:
            r02 = true;
            goto L8
        }

        public void skipNumberSeparator() {
        L2:
            int r0 = this.pos;
            if (r0 >= this.n) goto L14;
            char r02 = this.s.charAt(r0);
            if (r02 == '\t') goto L13;
            if (r02 == '\n') goto L13;
            if (r02 == ' ') goto L13;
            if (r02 == ',') goto L13;
            return;
        L13:
            advance();
            goto L2
        }

        public void skipWhitespace() {
        L2:
            int r0 = this.pos;
            if (r0 >= this.n) goto L7;
            if (Character.isWhitespace(this.s.charAt(r0)) == false) goto L10;
            advance();
            goto L2
        L10:
            return;
        }
    }

    /* loaded from: classes5.dex */
    public static class Properties {
        Attributes atts;
        ArrayList<StyleSet> styles;

        private Properties(Attributes r4, HashMap<String, StyleSet> r5) {
            this.atts = r4;
            String r0 = SvgHelper.m4988$$Nest$smgetStringAttr("style", r4);
            if (r0 == null) goto L6;
            ArrayList<StyleSet> r42 = new ArrayList();
            this.styles = r42;
            r42.add(new StyleSet(r0, null));
            return;
        L6:
            String r43 = SvgHelper.m4988$$Nest$smgetStringAttr("class", r4);
            if (r43 == null) goto L15;
            this.styles = new ArrayList();
            String[] r44 = r43.split(" ");
            int r02 = 0;
        L10:
            if (r02 >= r44.length) goto L19;
            StyleSet r1 = r5.get(r44[r02].trim());
            if (r1 == null) goto L14;
            this.styles.add(r1);
        L14:
            r02 = r02 + 1;
            goto L10
        L19:
            return;
        }

        public /* synthetic */ Properties(Attributes r1, HashMap r2, SvgHelperIA r3) {
            this(r1, r2);
        }

        public String getAttr(String r4) {
            ArrayList<StyleSet> r0 = this.styles;
            String r1 = null;
            if (r0 != null) goto L5;
        L12:
            if (r1 == null) goto L14;
            return r1;
        L14:
            return SvgHelper.m4988$$Nest$smgetStringAttr(r4, this.atts);
        L5:
            if (r0.isEmpty() == true) goto L12;
            int r02 = this.styles.size();
            int r2 = 0;
        L7:
            if (r2 >= r02) goto L12;
            r1 = this.styles.get(r2).getStyle(r4);
            if (r1 != null) goto L12;
            r2 = r2 + 1;
            goto L7
        }

        public Float getFloat(String r1) {
            String r0 = getAttr(r1);
            if (r0 != null) goto L8;
            return null;
        L8:
            return Float.valueOf(Float.parseFloat(r0));
        L7:
            return null;
        }

        public Float getFloat(String r1, float r2) {
            Float r0 = getFloat(r1);
            if (r0 == null) goto L5;
            return r0;
        L5:
            return Float.valueOf(r2);
        }

        public Integer getHex(String r2) {
            String r1 = getAttr(r2);
            if (r1 != null) goto L11;
            return null;
        L11:
            return Integer.valueOf(Integer.parseInt(r1.substring(1), 16));
        L10:
            return SvgHelper.m4984$$Nest$smgetColorByName(r1);
        }

        public String getString(String r1) {
            return getAttr(r1);
        }
    }

    /* loaded from: classes5.dex */
    public static class RoundRect {
        RectF rect;
        float rx;

        public RoundRect(RectF r1, float r2) {
            this.rect = r1;
            this.rx = r2;
        }
    }

    /* loaded from: classes5.dex */
    public static class SVGHandler extends DefaultHandler implements SvgResult {
        private boolean alphaOnly;
        private Bitmap bitmap;
        private boolean boundsMode;
        private Canvas canvas;
        private int desiredHeight;
        private int desiredWidth;
        private SvgDrawable drawable;
        private float globalScale;
        private HashMap<String, StyleSet> globalStyles;
        private boolean insideGiftRect;
        private int insideGiftRectDepth;
        private List<WallpaperGiftPatternPosition> insideGiftRectPositions;
        private Paint paint;
        private Integer paintColor;
        boolean pushed;
        private RectF rect;
        private RectF rectTmp;
        private float scale;
        private ScaleMode scaleMode;
        private StringBuilder styles;

        /* renamed from: -$$Nest$fputalphaOnly, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4990$$Nest$fputalphaOnly(SVGHandler r0, boolean r1) {
            r0.alphaOnly = r1;
        }

        private SVGHandler(int r8, int r9, Integer r10, boolean r11, float r12) {
            this(r8, r9, r10, r11, r12, ScaleMode.Default);
        }

        private SVGHandler(int r4, int r5, Integer r6, boolean r7, float r8, ScaleMode r9) {
            this.scale = 1.0f;
            this.paint = new Paint(1);
            this.rect = new RectF();
            this.rectTmp = new RectF();
            this.globalScale = 1.0f;
            this.pushed = false;
            this.globalStyles = new HashMap();
            this.insideGiftRect = false;
            this.insideGiftRectDepth = 0;
            this.globalScale = r8;
            this.desiredWidth = r4;
            this.desiredHeight = r5;
            this.paintColor = r6;
            this.scaleMode = r9;
            if (r7 == false) goto L6;
            this.drawable = new SvgDrawable();
            return;
        }

        public /* synthetic */ SVGHandler(int r1, int r2, Integer r3, boolean r4, float r5, ScaleMode r6, SvgHelperIA r7) {
            this(r1, r2, r3, r4, r5, r6);
        }

        public /* synthetic */ SVGHandler(int r1, int r2, Integer r3, boolean r4, float r5, SvgHelperIA r6) {
            this(r1, r2, r3, r4, r5);
        }

        private void doColor(Properties r2, Integer r3, boolean r4) {
            Integer r0 = this.paintColor;
            if (r0 == null) goto L5;
            this.paint.setColor(r0.intValue());
        L6:
            Float r32 = r2.getFloat("opacity");
            if (r32 != null) goto L12;
            if (r4 == false) goto L10;
            String r33 = "fill-opacity";
        L11:
            r32 = r2.getFloat(r33);
            goto L12
        L10:
            r33 = "stroke-opacity";
        L12:
            Paint r1 = this.paint;
            if (r32 != null) goto L16;
            r1.setAlpha(255);
            return;
        L16:
            r1.setAlpha((int) (r32.floatValue() * 255.0f));
            return;
        L5:
            int r34 = (r3.intValue() & 16777215) | (-16777216);
            this.paint.setColor(r34);
            goto L6
        }

        private boolean doFill(Properties r6) {
            if ("none".equals(r6.getString("display")) == false) goto L5;
            return false;
        L5:
            String r2 = r6.getString("fill");
            if (r2 != null) goto L8;
        L11:
            Integer r22 = r6.getHex("fill");
            if (r22 == null) goto L16;
            doColor(r6, r22, true);
            this.paint.setStyle(Paint.Style.FILL);
            return true;
        L16:
            if (r6.getString("fill") == null) goto L18;
        L24:
            return false;
        L18:
            if (r6.getString("stroke") != null) goto L24;
            this.paint.setStyle(Paint.Style.FILL);
            Integer r62 = this.paintColor;
            Paint r5 = this.paint;
            if (r62 == null) goto L22;
            r5.setColor(r62.intValue());
        L23:
            return true;
        L22:
            r5.setColor(-16777216);
            goto L23
        L8:
            if (r2.startsWith("url(#") == false) goto L11;
            r2.substring(5, r2.length() - 1);
            return false;
        }

        private boolean doStroke(Properties r4) {
            if ("none".equals(r4.getString("display")) == false) goto L5;
            return false;
        L5:
            Integer r0 = r4.getHex("stroke");
            if (r0 == null) goto L30;
            doColor(r4, r0, false);
            Float r02 = r4.getFloat("stroke-width");
            if (r02 == null) goto L10;
            this.paint.setStrokeWidth(r02.floatValue());
        L10:
            String r03 = r4.getString("stroke-linecap");
            if ("round".equals(r03) == false) goto L14;
            this.paint.setStrokeCap(Paint.Cap.ROUND);
        L19:
            String r42 = r4.getString("stroke-linejoin");
            if ("miter".equals(r42) == false) goto L23;
            this.paint.setStrokeJoin(Paint.Join.MITER);
        L28:
            this.paint.setStyle(Paint.Style.STROKE);
            return true;
        L23:
            if ("round".equals(r42) == false) goto L26;
            this.paint.setStrokeJoin(Paint.Join.ROUND);
            goto L28
        L26:
            if ("bevel".equals(r42) == false) goto L28;
            this.paint.setStrokeJoin(Paint.Join.BEVEL);
            goto L28
        L14:
            if ("square".equals(r03) == false) goto L17;
            this.paint.setStrokeCap(Paint.Cap.SQUARE);
            goto L19
        L17:
            if ("butt".equals(r03) == false) goto L19;
            this.paint.setStrokeCap(Paint.Cap.BUTT);
            goto L19
        L30:
            return false;
        }

        private void popTransform() {
            if (this.pushed == false) goto L10;
            SvgDrawable r0 = this.drawable;
            if (r0 == null) goto L8;
            SvgDrawable.m4991$$Nest$maddCommand(r0, null);
            return;
        L8:
            this.canvas.restore();
            return;
        }

        private void pushTransform(Attributes r2) {
            String r22 = SvgHelper.m4988$$Nest$smgetStringAttr("transform", r2);
            if (r22 == null) goto L5;
            boolean r0 = true;
        L6:
            this.pushed = r0;
            if (r0 == false) goto L14;
            Matrix r23 = SvgHelper.parseTransform(r22);
            SvgDrawable r02 = this.drawable;
            if (r02 == null) goto L12;
            SvgDrawable.m4991$$Nest$maddCommand(r02, r23);
            return;
        L12:
            this.canvas.save();
            this.canvas.concat(r23);
            return;
        L14:
            return;
        L5:
            r0 = false;
            goto L6
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void characters(char[] r1, int r2, int r3) {
            StringBuilder r0 = this.styles;
            if (r0 == null) goto L6;
            r0.append(r1, r2, r3);
            return;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endDocument() {
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String r7, String r8, String r9) {
            if (this.insideGiftRect == false) goto L8;
            int r72 = this.insideGiftRectDepth - 1;
            this.insideGiftRectDepth = r72;
            if (r72 != 0) goto L53;
            this.insideGiftRect = false;
            return;
        L53:
            return;
        L8:
            r8.getClass();
            char r1 = 65535;
            switch(r8.hashCode()) {
                case 103: goto L24;
                case 3079438: goto L20;
                case 109780401: goto L16;
                case 917656469: goto L12;
                default: goto L27;
            };
        L27:
            switch(r1) {
                case 0: goto L46;
                case 1: goto L46;
                case 2: goto L29;
                case 3: goto L46;
                default: goto L55;
            };
        L29:
            StringBuilder r73 = this.styles;
            if (r73 == null) goto L54;
            String[] r74 = r73.toString().split("\\}");
            int r82 = 0;
        L33:
            if (r82 >= r74.length) goto L44;
            String r12 = r74[r82].trim().replace("\t", _UrlKt.FRAGMENT_ENCODE_SET).replace("\n", _UrlKt.FRAGMENT_ENCODE_SET);
            r74[r82] = r12;
            if (r12.length() == 0) goto L43;
            if (r74[r82].charAt(0) != '.') goto L43;
            int r13 = r74[r82].indexOf(123);
            if (r13 < 0) goto L43;
            this.globalStyles.put(r74[r82].substring(1, r13).trim(), new StyleSet(r74[r82].substring(r13 + 1), null));
        L43:
            r82 = r82 + 1;
            goto L33
        L44:
            this.styles = null;
            return;
        L54:
            return;
        L46:
            this.boundsMode = false;
            return;
        L55:
            return;
        L12:
            if (r8.equals("clipPath") == false) goto L27;
            r1 = 3;
            goto L27
        L16:
            if (r8.equals("style") == false) goto L27;
            r1 = 2;
            goto L27
        L20:
            if (r8.equals("defs") == false) goto L27;
            r1 = 1;
            goto L27
        L24:
            if (r8.equals(ImageLoader.AUTOPLAY_FILTER) == false) goto L27;
            r1 = 0;
            goto L27
        }

        @Override // org.telegram.messenger.SvgHelper.SvgResult
        public Bitmap getBitmap() {
            return this.bitmap;
        }

        @Override // org.telegram.messenger.SvgHelper.SvgResult
        public SvgDrawable getDrawable() {
            return this.drawable;
        }

        @Override // org.telegram.messenger.SvgHelper.SvgResult
        public List<WallpaperGiftPatternPosition> getGiftPatternPositions() {
            return this.insideGiftRectPositions;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startDocument() {
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String r20, String r21, String r22, Attributes r23) {
            Float r5 = Float.valueOf(0.0f);
            if (ImageLoader.AUTOPLAY_FILTER.equals(r22) == false) goto L11;
            if (this.insideGiftRect == true) goto L11;
            if ("GiftPatterns".equals(r23.getValue("id")) == false) goto L22;
            this.insideGiftRect = true;
            this.insideGiftRectDepth = 1;
            return;
        L22:
            if (this.boundsMode == true) goto L24;
        L26:
            r21.getClass();
            int r14 = 2;
            char r15 = 65535;
            switch(r21.hashCode()) {
                case -1656480802: goto L74;
                case -1360216880: goto L70;
                case -397519558: goto L66;
                case 103: goto L62;
                case 114276: goto L58;
                case 3079438: goto L54;
                case 3321844: goto L50;
                case 3433509: goto L46;
                case 3496420: goto L42;
                case 109780401: goto L38;
                case 561938880: goto L34;
                case 917656469: goto L30;
                default: goto L78;
            };
        L30:
            if (r21.equals("clipPath") == false) goto L78;
            r15 = 11;
            goto L78
        L34:
            if (r21.equals("polyline") == false) goto L78;
            r15 = '\n';
            goto L78
        L38:
            if (r21.equals("style") == false) goto L78;
            r15 = '\t';
            goto L78
        L42:
            if (r21.equals("rect") == false) goto L78;
            r15 = '\b';
            goto L78
        L46:
            if (r21.equals("path") == false) goto L78;
            r15 = 7;
            goto L78
        L50:
            if (r21.equals("line") == false) goto L78;
            r15 = 6;
            goto L78
        L54:
            if (r21.equals("defs") == false) goto L78;
            r15 = 5;
            goto L78
        L58:
            if (r21.equals("svg") == false) goto L78;
            r15 = 4;
            goto L78
        L62:
            if (r21.equals(ImageLoader.AUTOPLAY_FILTER) == false) goto L78;
            r15 = 3;
            goto L78
        L66:
            if (r21.equals("polygon") == false) goto L78;
            r15 = 2;
            goto L78
        L70:
            if (r21.equals("circle") == false) goto L78;
            r15 = 1;
            goto L78
        L74:
            if (r21.equals("ellipse") == false) goto L78;
            r15 = 0;
        L78:
            switch(r15) {
                case 0: goto L217;
                case 1: goto L199;
                case 2: goto L174;
                case 3: goto L171;
                case 4: goto L135;
                case 5: goto L133;
                case 6: goto L125;
                case 7: goto L111;
                case 8: goto L82;
                case 9: goto L80;
                case 10: goto L174;
                case 11: goto L133;
                default: goto L252;
            };
        L80:
            this.styles = new StringBuilder();
            return;
        L82:
            Float r1 = SvgHelper.m4985$$Nest$smgetFloatAttr("x", r23);
            if (r1 != null) goto L85;
            r1 = r5;
        L85:
            Float r2 = SvgHelper.m4985$$Nest$smgetFloatAttr("y", r23);
            if (r2 == null) goto L89;
            r5 = r2;
        L89:
            Float r24 = SvgHelper.m4985$$Nest$smgetFloatAttr("width", r23);
            Float r4 = SvgHelper.m4985$$Nest$smgetFloatAttr("height", r23);
            Float r6 = SvgHelper.m4986$$Nest$smgetFloatAttr("rx", r23, null);
            pushTransform(r23);
            Properties r7 = new Properties(r23, this.globalStyles, null);
            if (doFill(r7) == false) goto L100;
            SvgDrawable r3 = this.drawable;
            if (r3 == null) goto L96;
            if (r6 == null) goto L95;
            SvgDrawable.m4992$$Nest$maddCommand(r3, new RoundRect(new RectF(r1.floatValue(), r5.floatValue(), r1.floatValue() + r24.floatValue(), r5.floatValue() + r4.floatValue()), r6.floatValue()), this.paint);
            goto L100
        L95:
            SvgDrawable.m4992$$Nest$maddCommand(r3, new RectF(r1.floatValue(), r5.floatValue(), r1.floatValue() + r24.floatValue(), r5.floatValue() + r4.floatValue()), this.paint);
            goto L100
        L96:
            if (r6 == null) goto L98;
            this.rectTmp.set(r1.floatValue(), r5.floatValue(), r1.floatValue() + r24.floatValue(), r5.floatValue() + r4.floatValue());
            this.canvas.drawRoundRect(this.rectTmp, r6.floatValue(), r6.floatValue(), this.paint);
            goto L100
        L98:
            this.canvas.drawRect(r1.floatValue(), r5.floatValue(), r1.floatValue() + r24.floatValue(), r5.floatValue() + r4.floatValue(), this.paint);
        L100:
            if (doStroke(r7) == false) goto L109;
            SvgDrawable r32 = this.drawable;
            if (r32 == null) goto L106;
            if (r6 == null) goto L105;
            SvgDrawable.m4992$$Nest$maddCommand(r32, new RoundRect(new RectF(r1.floatValue(), r5.floatValue(), r1.floatValue() + r24.floatValue(), r5.floatValue() + r4.floatValue()), r6.floatValue()), this.paint);
            goto L109
        L105:
            SvgDrawable.m4992$$Nest$maddCommand(r32, new RectF(r1.floatValue(), r5.floatValue(), r1.floatValue() + r24.floatValue(), r5.floatValue() + r4.floatValue()), this.paint);
            goto L109
        L106:
            if (r6 == null) goto L108;
            this.rectTmp.set(r1.floatValue(), r5.floatValue(), r1.floatValue() + r24.floatValue(), r5.floatValue() + r4.floatValue());
            this.canvas.drawRoundRect(this.rectTmp, r6.floatValue(), r6.floatValue(), this.paint);
            goto L109
        L108:
            this.canvas.drawRect(r1.floatValue(), r5.floatValue(), r1.floatValue() + r24.floatValue(), r5.floatValue() + r4.floatValue(), this.paint);
        L109:
            popTransform();
            return;
        L111:
            Path r12 = SvgHelper.doPath(SvgHelper.m4988$$Nest$smgetStringAttr("d", r23));
            pushTransform(r23);
            Properties r25 = new Properties(r23, this.globalStyles, null);
            if (doFill(r25) == false) goto L118;
            SvgDrawable r33 = this.drawable;
            if (r33 == null) goto L116;
            SvgDrawable.m4992$$Nest$maddCommand(r33, r12, this.paint);
            goto L118
        L116:
            this.canvas.drawPath(r12, this.paint);
        L118:
            if (doStroke(r25) == false) goto L123;
            SvgDrawable r26 = this.drawable;
            if (r26 == null) goto L122;
            SvgDrawable.m4992$$Nest$maddCommand(r26, r12, this.paint);
            goto L123
        L122:
            this.canvas.drawPath(r12, this.paint);
        L123:
            popTransform();
            return;
        L125:
            Float r13 = SvgHelper.m4985$$Nest$smgetFloatAttr("x1", r23);
            Float r27 = SvgHelper.m4985$$Nest$smgetFloatAttr("x2", r23);
            Float r42 = SvgHelper.m4985$$Nest$smgetFloatAttr("y1", r23);
            Float r52 = SvgHelper.m4985$$Nest$smgetFloatAttr("y2", r23);
            if (doStroke(new Properties(r23, this.globalStyles, null)) == false) goto L239;
            pushTransform(r23);
            SvgDrawable r34 = this.drawable;
            if (r34 == null) goto L130;
            SvgDrawable.m4992$$Nest$maddCommand(r34, new Line(r13.floatValue(), r42.floatValue(), r27.floatValue(), r52.floatValue()), this.paint);
        L131:
            popTransform();
            return;
        L130:
            this.canvas.drawLine(r13.floatValue(), r42.floatValue(), r27.floatValue(), r52.floatValue(), this.paint);
            goto L131
        L239:
            return;
        L133:
            this.boundsMode = true;
            return;
        L135:
            Float r16 = SvgHelper.m4985$$Nest$smgetFloatAttr("width", r23);
            Float r28 = SvgHelper.m4985$$Nest$smgetFloatAttr("height", r23);
            if (r16 == null) goto L138;
            if (r28 == null) goto L138;
        L141:
            if (r16 == null) goto L143;
            if (r28 == null) goto L143;
        L144:
            int r17 = (int) Math.ceil(r16.floatValue());
            int r29 = (int) Math.ceil(r28.floatValue());
            if (r17 == 0) goto L157;
            if (r29 == 0) goto L157;
            int r35 = this.desiredWidth;
            if (r35 == 0) goto L158;
            int r43 = this.desiredHeight;
            if (r43 == 0) goto L158;
            if (this.scaleMode != ScaleMode.ByWidth) goto L155;
            this.scale = r35 / r17;
        L156:
            float r36 = this.scale;
            r17 = (int) (r17 * r36);
            r29 = (int) (r29 * r36);
            goto L158
        L155:
            this.scale = Math.min(r35 / r17, r43 / r29);
        L158:
            SvgDrawable r37 = this.drawable;
            if (r37 == null) goto L161;
            r37.width = r17;
            r37.height = r29;
            return;
        L161:
            if (this.alphaOnly == false) goto L163;
            Bitmap.Config r38 = Bitmap.Config.ALPHA_8;
        L164:
            Bitmap r18 = Bitmap.createBitmap(r17, r29, r38);
            this.bitmap = r18;
            r18.eraseColor(0);
            Canvas r19 = new Canvas(this.bitmap);
            this.canvas = r19;
            float r210 = this.scale;
            if (r210 == 0.0f) goto L240;
            float r0 = this.globalScale;
            r19.scale(r0 * r210, r0 * r210);
            return;
        L240:
            return;
        L163:
            r38 = Bitmap.Config.ARGB_8888;
        L157:
            r17 = this.desiredWidth;
            r29 = this.desiredHeight;
        L143:
            r16 = Float.valueOf(this.desiredWidth);
            r28 = Float.valueOf(this.desiredHeight);
        L138:
            String r39 = SvgHelper.m4988$$Nest$smgetStringAttr("viewBox", r23);
            if (r39 == null) goto L141;
            String[] r110 = r39.split(" ");
            Float r211 = Float.valueOf(Float.parseFloat(r110[2]));
            r28 = Float.valueOf(Float.parseFloat(r110[3]));
            r16 = r211;
            goto L141
        L174:
            NumberParse r212 = SvgHelper.m4987$$Nest$smgetNumberParseAttr("points", r23);
            if (r212 == null) goto L242;
            Path r44 = new Path();
            ArrayList r213 = NumberParse.m4989$$Nest$fgetnumbers(r212);
            if (r213.size() <= 1) goto L243;
            pushTransform(r23);
            Properties r53 = new Properties(r23, this.globalStyles, null);
            r44.moveTo(((Float) r213.get(0)).floatValue(), ((Float) r213.get(1)).floatValue());
        L180:
            if (r14 >= r213.size()) goto L183;
            r44.lineTo(((Float) r213.get(r14)).floatValue(), ((Float) r213.get(r14 + 1)).floatValue());
            r14 = r14 + 2;
            goto L180
        L183:
            if (r21.equals("polygon") == false) goto L186;
            r44.close();
        L186:
            if (doFill(r53) == false) goto L192;
            SvgDrawable r111 = this.drawable;
            if (r111 == null) goto L190;
            SvgDrawable.m4992$$Nest$maddCommand(r111, r44, this.paint);
            goto L192
        L190:
            this.canvas.drawPath(r44, this.paint);
        L192:
            if (doStroke(r53) == false) goto L197;
            SvgDrawable r112 = this.drawable;
            if (r112 == null) goto L196;
            SvgDrawable.m4992$$Nest$maddCommand(r112, r44, this.paint);
            goto L197
        L196:
            this.canvas.drawPath(r44, this.paint);
        L197:
            popTransform();
            return;
        L243:
            return;
        L242:
            return;
        L199:
            Float r113 = SvgHelper.m4985$$Nest$smgetFloatAttr("cx", r23);
            Float r214 = SvgHelper.m4985$$Nest$smgetFloatAttr("cy", r23);
            Float r45 = SvgHelper.m4985$$Nest$smgetFloatAttr("r", r23);
            if (r113 == null) goto L244;
            if (r214 == null) goto L245;
            if (r45 == null) goto L246;
            pushTransform(r23);
            Properties r54 = new Properties(r23, this.globalStyles, null);
            if (doFill(r54) == false) goto L210;
            SvgDrawable r310 = this.drawable;
            if (r310 == null) goto L208;
            SvgDrawable.m4992$$Nest$maddCommand(r310, new Circle(r113.floatValue(), r214.floatValue(), r45.floatValue()), this.paint);
            goto L210
        L208:
            this.canvas.drawCircle(r113.floatValue(), r214.floatValue(), r45.floatValue(), this.paint);
        L210:
            if (doStroke(r54) == false) goto L215;
            SvgDrawable r311 = this.drawable;
            if (r311 == null) goto L214;
            SvgDrawable.m4992$$Nest$maddCommand(r311, new Circle(r113.floatValue(), r214.floatValue(), r45.floatValue()), this.paint);
            goto L215
        L214:
            this.canvas.drawCircle(r113.floatValue(), r214.floatValue(), r45.floatValue(), this.paint);
        L215:
            popTransform();
            return;
        L246:
            return;
        L245:
            return;
        L244:
            return;
        L217:
            Float r114 = SvgHelper.m4985$$Nest$smgetFloatAttr("cx", r23);
            Float r215 = SvgHelper.m4985$$Nest$smgetFloatAttr("cy", r23);
            Float r46 = SvgHelper.m4985$$Nest$smgetFloatAttr("rx", r23);
            Float r55 = SvgHelper.m4985$$Nest$smgetFloatAttr("ry", r23);
            if (r114 == null) goto L247;
            if (r215 == null) goto L248;
            if (r46 == null) goto L249;
            if (r55 == null) goto L250;
            pushTransform(r23);
            Properties r62 = new Properties(r23, this.globalStyles, null);
            this.rect.set(r114.floatValue() - r46.floatValue(), r215.floatValue() - r55.floatValue(), r114.floatValue() + r46.floatValue(), r215.floatValue() + r55.floatValue());
            if (doFill(r62) == false) goto L229;
            SvgDrawable r115 = this.drawable;
            if (r115 == null) goto L227;
            SvgDrawable.m4992$$Nest$maddCommand(r115, new Oval(this.rect), this.paint);
            goto L229
        L227:
            this.canvas.drawOval(this.rect, this.paint);
        L229:
            if (doStroke(r62) == false) goto L234;
            SvgDrawable r116 = this.drawable;
            if (r116 == null) goto L233;
            SvgDrawable.m4992$$Nest$maddCommand(r116, new Oval(this.rect), this.paint);
            goto L234
        L233:
            this.canvas.drawOval(this.rect, this.paint);
        L234:
            popTransform();
            return;
        L250:
            return;
        L249:
            return;
        L248:
            return;
        L247:
            return;
        L252:
            return;
        L171:
            if ("bounds".equalsIgnoreCase(SvgHelper.m4988$$Nest$smgetStringAttr("id", r23)) == false) goto L241;
            this.boundsMode = true;
            return;
        L241:
            return;
        L24:
            if (r21.equals("style") == true) goto L26;
            return;
        L11:
            if (this.insideGiftRect == false) goto L22;
            this.insideGiftRectDepth++;
            if ("rect".equals(r22) == false) goto L237;
            WallpaperGiftPatternPosition r117 = WallpaperGiftPatternPosition.create(r23, this.scale);
            if (r117 != null) goto L17;
            return;
        L17:
            if (this.insideGiftRectPositions != null) goto L19;
            this.insideGiftRectPositions = new ArrayList();
        L19:
            this.insideGiftRectPositions.add(r117);
            return;
        }
    }

    /* loaded from: classes5.dex */
    public enum ScaleMode extends Enum<ScaleMode> {
        private static final /* synthetic */ ScaleMode[] $VALUES = null;
        public static final ScaleMode ByWidth = null;
        public static final ScaleMode Default = null;

        private static /* synthetic */ ScaleMode[] $values() {
            return new ScaleMode[]{Default, ByWidth};
        }

        static {
            Default = new ScaleMode("Default", 0);
            ByWidth = new ScaleMode("ByWidth", 1);
            $VALUES = $values();
        }

        ScaleMode(String r1, int r2) {
        }

        public static ScaleMode valueOf(String r1) {
            return (ScaleMode) Enum.valueOf(ScaleMode.class, r1);
        }

        public static ScaleMode[] values() {
            return (ScaleMode[]) $VALUES.clone();
        }
    }

    /* loaded from: classes5.dex */
    public static class StyleSet {
        HashMap<String, String> styleMap;

        private StyleSet(String r8) {
            this.styleMap = new HashMap();
            String[] r82 = r8.split(";");
            int r0 = r82.length;
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L8;
            String[] r3 = r82[r2].split(":");
            if (r3.length != 2) goto L7;
            this.styleMap.put(r3[0].trim(), r3[1].trim());
        L7:
            r2 = r2 + 1;
            goto L3
        }

        public /* synthetic */ StyleSet(String r1, SvgHelperIA r2) {
            this(r1);
        }

        private StyleSet(StyleSet r2) {
            HashMap<String, String> r0 = new HashMap();
            this.styleMap = r0;
            r0.putAll(r2.styleMap);
        }

        public String getStyle(String r1) {
            return this.styleMap.get(r1);
        }
    }

    public static class SvgDrawable extends Drawable {
        private static float gradientWidth;
        private static long lastUpdateTime;
        private static boolean lite;
        private static final int[] parentPosition = null;
        private static WeakReference<Drawable> shiftDrawable;
        private static Runnable shiftRunnable;
        private static float totalTranslation;
        private boolean aspectCenter;
        private boolean aspectFill;
        private final Bitmap[] backgroundBitmap;
        private final Canvas[] backgroundCanvas;
        private Paint backgroundPaint;
        private float colorAlpha;
        protected ArrayList<Object> commands;
        private float crossfadeAlpha;
        private final int[] currentColor;
        private int currentColorKey;
        private Theme.ResourcesProvider currentResourcesProvider;
        protected int height;
        private Integer overrideColor;
        private Paint overridePaint;
        SparseArray<Paint> overridePaintByPosition;
        protected HashMap<Object, Paint> paints;
        private ImageReceiver parentImageReceiver;
        private final LinearGradient[] placeholderGradient;
        private final Matrix[] placeholderMatrix;
        protected int width;

        public static /* synthetic */ void $r8$lambda$ENqkX9olOH39O3HcX1qVldguLSQ() {
            shiftRunnable = null;
        }

        /* renamed from: -$$Nest$maddCommand, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4991$$Nest$maddCommand(SvgDrawable r0, Object r1) {
            r0.addCommand(r1);
        }

        /* renamed from: -$$Nest$maddCommand, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4992$$Nest$maddCommand(SvgDrawable r0, Object r1, Paint r2) {
            r0.addCommand(r1, r2);
        }

        static {
            parentPosition = new int[2];
            lite = LiteMode.isEnabled(32);
        }

        public SvgDrawable() {
            this.commands = new ArrayList();
            this.paints = new HashMap();
            this.backgroundBitmap = new Bitmap[3];
            this.backgroundCanvas = new Canvas[3];
            this.placeholderGradient = new LinearGradient[3];
            this.placeholderMatrix = new Matrix[3];
            this.currentColor = new int[2];
            this.crossfadeAlpha = 1.0f;
            this.overridePaintByPosition = new SparseArray();
            this.aspectFill = true;
            this.aspectCenter = false;
        }

        private void addCommand(Object r1) {
            this.commands.add(r1);
        }

        private void addCommand(Object r2, Paint r3) {
            this.commands.add(r2);
            this.paints.put(r2, new Paint(r3));
        }

        public static void updateLiteValues() {
            lite = LiteMode.isEnabled(32);
        }

        public /* bridge */ /* synthetic */ Object clone() {
            return m4993clone();
        }

        /* renamed from: clone, reason: collision with other method in class */
        public SvgDrawable m4993clone() {
            SvgDrawable r0 = new SvgDrawable();
            int r1 = 0;
        L4:
            if (r1 >= this.commands.size()) goto L9;
            r0.commands.add(this.commands.get(r1));
            Paint r2 = this.paints.get(this.commands.get(r1));
            if (r2 == null) goto L8;
            Paint r3 = new Paint();
            r3.setColor(r2.getColor());
            r3.setStrokeCap(r2.getStrokeCap());
            r3.setStrokeJoin(r2.getStrokeJoin());
            r3.setStrokeWidth(r2.getStrokeWidth());
            r3.setStyle(r2.getStyle());
            r0.paints.put(this.commands.get(r1), r3);
        L8:
            r1 = r1 + 1;
            goto L4
        L9:
            r0.width = this.width;
            r0.height = this.height;
            return r0;
        }

        public void copyCommandFromPosition(int r1) {
            ArrayList<Object> r0 = this.commands;
            r0.add(r0.get(r1));
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r11) {
            drawInternal(r11, false, 0, System.currentTimeMillis(), getBounds().left, getBounds().top, getBounds().width(), getBounds().height());
        }

        public void drawInternal(Canvas r20, boolean r21, int r22, long r23, float r25, float r26, float r27, float r28) {
            int r6 = this.currentColorKey;
            if (r6 < 0) goto L5;
            setupGradient(r6, this.currentResourcesProvider, this.colorAlpha, r21);
        L5:
            float r62 = getScale((int) r27, (int) r28);
            int r8 = 0;
            if (this.placeholderGradient[r22] == null) goto L61;
            float r7 = gradientWidth;
            if (r7 <= 0.0f) goto L61;
            if (lite == false) goto L61;
            long r12 = 0;
            long r14 = 64;
            if (r21 == false) goto L24;
            long r16 = r23 - lastUpdateTime;
            if (r16 > 64) goto L18;
            r14 = r16;
        L18:
            if (r14 <= 0) goto L43;
            lastUpdateTime = r23;
            totalTranslation += (r14 * r7) / 1800.0f;
        L20:
            float r72 = totalTranslation;
            float r11 = gradientWidth;
            if (r72 < (r11 * 2.0f)) goto L43;
            totalTranslation = r72 - (r11 * 2.0f);
        L43:
            ImageReceiver r73 = this.parentImageReceiver;
            if (r73 == null) goto L47;
            if (r21 == true) goto L47;
            int[] r112 = parentPosition;
            r73.getParentPosition(r112);
            int r74 = r112[0];
        L48:
            if (r21 == false) goto L50;
            int r113 = r22 + 1;
        L51:
            Matrix r122 = this.placeholderMatrix[r113];
            if (r122 == null) goto L61;
            r122.reset();
            Matrix[] r123 = this.placeholderMatrix;
            if (r21 == false) goto L56;
            r123[r113].postTranslate(((-r74) + totalTranslation) - r25, 0.0f);
        L57:
            float r10 = 1.0f / r62;
            this.placeholderMatrix[r113].postScale(r10, r10);
            this.placeholderGradient[r113].setLocalMatrix(this.placeholderMatrix[r113]);
            ImageReceiver r75 = this.parentImageReceiver;
            if (r75 == null) goto L61;
            if (r21 == true) goto L61;
            r75.invalidate();
            goto L61
        L56:
            r123[r113].postTranslate(((-r74) + totalTranslation) - r25, 0.0f);
            goto L57
        L50:
            r113 = 0;
        L47:
            r74 = 0;
            goto L48
        L24:
            if (shiftRunnable != null) goto L26;
        L27:
            long r162 = r23 - lastUpdateTime;
            if (r162 > 64) goto L32;
            r14 = r162;
        L32:
            if (r14 < 0) goto L35;
            r12 = r14;
        L35:
            lastUpdateTime = r23;
            totalTranslation += (r12 * gradientWidth) / 1800.0f;
        L36:
            float r76 = totalTranslation;
            float r114 = gradientWidth;
            if (r76 < (r114 / 2.0f)) goto L39;
            totalTranslation = r76 - r114;
            goto L36
        L39:
            shiftDrawable = new WeakReference(this);
            Runnable r77 = shiftRunnable;
            if (r77 == null) goto L42;
            AndroidUtilities.cancelRunOnUIThread(r77);
        L42:
            SvgHelper$SvgDrawable$$ExternalSyntheticLambda0 r78 = new SvgHelper$SvgDrawable$$ExternalSyntheticLambda0();
            shiftRunnable = r78;
            AndroidUtilities.runOnUIThread(r78, ((int) (1000.0f / AndroidUtilities.screenRefreshRate)) - 1);
            goto L43
        L26:
            if (shiftDrawable.get() != this) goto L43;
        L61:
            r20.save();
            r20.translate(r25, r26);
            if (this.aspectFill == true) goto L64;
        L65:
            r20.translate((r27 - (this.width * r62)) / 2.0f, (r28 - (this.height * r62)) / 2.0f);
        L66:
            r20.scale(r62, r62);
            int r3 = this.commands.size();
        L67:
            if (r8 >= r3) goto L104;
            Object r4 = this.commands.get(r8);
            if ((r4 instanceof Matrix) == false) goto L71;
            r20.save();
            r20.concat((Matrix) r4);
        L103:
            r8 = r8 + 1;
            goto L67
        L71:
            if (r4 != null) goto L73;
            r20.restore();
            goto L103
        L73:
            Paint r5 = this.overridePaintByPosition.get(r8);
            if (r5 != null) goto L76;
            r5 = this.overridePaint;
        L76:
            if (r21 == false) goto L78;
            r5 = this.backgroundPaint;
        L81:
            int r63 = r5.getAlpha();
            r5.setAlpha((int) (this.crossfadeAlpha * r63));
            if ((r4 instanceof Path) == false) goto L85;
            r20.drawPath((Path) r4, r5);
        L102:
            r5.setAlpha(r63);
            goto L103
        L85:
            if ((r4 instanceof Rect) == false) goto L88;
            r20.drawRect((Rect) r4, r5);
            goto L102
        L88:
            if ((r4 instanceof RectF) == false) goto L91;
            r20.drawRect((RectF) r4, r5);
            goto L102
        L91:
            if ((r4 instanceof Line) == false) goto L94;
            Line r42 = (Line) r4;
            r20.drawLine(r42.x1, r42.y1, r42.x2, r42.y2, r5);
            goto L102
        L94:
            if ((r4 instanceof Circle) == false) goto L97;
            Circle r43 = (Circle) r4;
            r20.drawCircle(r43.x1, r43.y1, r43.rad, r5);
            goto L102
        L97:
            if ((r4 instanceof Oval) == false) goto L100;
            r20.drawOval(((Oval) r4).rect, r5);
            goto L102
        L100:
            if ((r4 instanceof RoundRect) == false) goto L102;
            RoundRect r44 = (RoundRect) r4;
            RectF r79 = r44.rect;
            float r45 = r44.rx;
            r20.drawRoundRect(r79, r45, r45, r5);
            goto L102
        L78:
            if (r5 != null) goto L81;
            r5 = this.paints.get(r4);
            goto L81
        L104:
            r20.restore();
            return;
        L64:
            if (this.aspectCenter == false) goto L66;
            goto L65
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return this.width;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return this.height;
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        public float getScale(int r2, int r3) {
            float r22 = r2 / this.width;
            float r32 = r3 / this.height;
            if (this.aspectFill == false) goto L7;
            return Math.max(r22, r32);
        L7:
            return Math.min(r22, r32);
        }

        public void overrideWidthAndHeight(int r1, int r2) {
            this.width = r1;
            this.height = r2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r2) {
            this.crossfadeAlpha = r2 / 255.0f;
        }

        public void setAspectCenter(boolean r1) {
            this.aspectCenter = r1;
        }

        public void setAspectFill(boolean r1) {
            this.aspectFill = r1;
        }

        public void setColor(int r1) {
            this.overrideColor = Integer.valueOf(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }

        public void setColorKey(int r1) {
            this.currentColorKey = r1;
        }

        public void setColorKey(int r1, Theme.ResourcesProvider r2) {
            this.currentColorKey = r1;
            this.currentResourcesProvider = r2;
        }

        public void setPaint(Paint r1) {
            this.overridePaint = r1;
        }

        public void setPaint(Paint r1, int r2) {
            this.overridePaintByPosition.put(r2, r1);
        }

        public void setParent(ImageReceiver r1) {
            this.parentImageReceiver = r1;
        }

        public void setupGradient(int r2, float r3, boolean r4) {
            setupGradient(r2, null, r3, r4);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r16v1, types: [android.graphics.LinearGradient] */
        /* JADX WARN: Type inference failed for: r28v1 */
        /* JADX WARN: Type inference failed for: r28v10 */
        /* JADX WARN: Type inference failed for: r28v11 */
        /* JADX WARN: Type inference failed for: r28v12 */
        /* JADX WARN: Type inference failed for: r28v13 */
        /* JADX WARN: Type inference failed for: r28v14 */
        /* JADX WARN: Type inference failed for: r28v2 */
        /* JADX WARN: Type inference failed for: r28v3 */
        /* JADX WARN: Type inference failed for: r28v4 */
        /* JADX WARN: Type inference failed for: r28v5 */
        /* JADX WARN: Type inference failed for: r28v6 */
        /* JADX WARN: Type inference failed for: r28v7 */
        /* JADX WARN: Type inference failed for: r28v8 */
        /* JADX WARN: Type inference failed for: r28v9 */
        public void setupGradient(int r25, Theme.ResourcesProvider r26, float r27, boolean r28) {
            Integer r1 = this.overrideColor;
            if (r1 != null) goto L6;
            int r12 = Theme.getColor(r25, r26);
        L7:
            this.currentResourcesProvider = r26;
            int[] r2 = this.currentColor;
            if (r2[r28 ? 1 : 0] == r12) goto L40;
            this.colorAlpha = r27;
            this.currentColorKey = r25;
            r2[r28 ? 1 : 0] = r12;
            gradientWidth = AndroidUtilities.displaySize.x * 2;
            if (lite == true) goto L22;
            int r13 = ColorUtils.setAlphaComponent(r12, 70);
            if (r28 == true) goto L14;
            Iterator<Paint> r0 = this.paints.values().iterator();
        L20:
            if (r0.hasNext() == false) goto L43;
            Paint r3 = r0.next();
            r3.setShader(null);
            r3.setColor(r13);
            goto L20
        L43:
            return;
        L14:
            if (this.backgroundPaint != null) goto L16;
            this.backgroundPaint = new Paint(1);
        L16:
            this.backgroundPaint.setShader(null);
            this.backgroundPaint.setColor(r13);
            return;
        L22:
            float r22 = AndroidUtilities.dp(180.0f) / gradientWidth;
            int r14 = Color.argb((int) ((Color.alpha(r12) / 2) * this.colorAlpha), Color.red(r12), Color.green(r12), Color.blue(r12));
            float r6 = (1.0f - r22) / 2.0f;
            LinearGradient[] r8 = this.placeholderGradient;
            float r23 = r22 / 2.0f;
            Shader.TileMode r16 = Shader.TileMode.REPEAT;
            r8[r28 ? 1 : 0] = new LinearGradient(0.0f, 0.0f, gradientWidth, 0.0f, new int[]{0, 0, r14, 0, 0}, new float[]{0.0f, r6 - r23, r6, r23 + r6, 1.0f}, r16);
            if (Build.VERSION.SDK_INT < 28) goto L25;
            BitmapShader r15 = new LinearGradient(0.0f, 0.0f, gradientWidth, 0.0f, new int[]{r14, r14}, null, r16);
        L29:
            this.placeholderMatrix[r28 ? 1 : 0] = new Matrix();
            this.placeholderGradient[r28 ? 1 : 0].setLocalMatrix(this.placeholderMatrix[r28 ? 1 : 0]);
            if (r28 == true) goto L32;
            Iterator<Paint> r24 = this.paints.values().iterator();
        L38:
            if (r24.hasNext() == false) goto L44;
            r24.next().setShader(new ComposeShader(this.placeholderGradient[r28 ? 1 : 0], r15, PorterDuff.Mode.ADD));
            goto L38
        L44:
            return;
        L32:
            if (this.backgroundPaint != null) goto L34;
            this.backgroundPaint = new Paint(1);
        L34:
            this.backgroundPaint.setShader(new ComposeShader(this.placeholderGradient[r28 ? 1 : 0], r15, PorterDuff.Mode.ADD));
            return;
        L25:
            Bitmap[] r32 = this.backgroundBitmap;
            if (r32[r28 ? 1 : 0] != null) goto L28;
            r32[r28 ? 1 : 0] = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
            this.backgroundCanvas[r28 ? 1 : 0] = new Canvas(this.backgroundBitmap[r28 ? 1 : 0]);
        L28:
            this.backgroundCanvas[r28 ? 1 : 0].drawColor(r14);
            r15 = new BitmapShader(this.backgroundBitmap[r28 ? 1 : 0], r16, r16);
            goto L29
        L40:
            return;
        L6:
            r12 = r1.intValue();
            goto L7
        }
    }

    /* loaded from: classes5.dex */
    public interface SvgResult {
        Bitmap getBitmap();

        SvgDrawable getDrawable();

        List<WallpaperGiftPatternPosition> getGiftPatternPositions();
    }

    /* renamed from: -$$Nest$sfgetpow10, reason: not valid java name */
    public static /* bridge */ /* synthetic */ double[] m4983$$Nest$sfgetpow10() {
        return pow10;
    }

    /* renamed from: -$$Nest$smgetColorByName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Integer m4984$$Nest$smgetColorByName(String r0) {
        return getColorByName(r0);
    }

    /* renamed from: -$$Nest$smgetFloatAttr, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Float m4985$$Nest$smgetFloatAttr(String r0, Attributes r1) {
        return getFloatAttr(r0, r1);
    }

    /* renamed from: -$$Nest$smgetFloatAttr, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Float m4986$$Nest$smgetFloatAttr(String r0, Attributes r1, Float r2) {
        return getFloatAttr(r0, r1, r2);
    }

    /* renamed from: -$$Nest$smgetNumberParseAttr, reason: not valid java name */
    public static /* bridge */ /* synthetic */ NumberParse m4987$$Nest$smgetNumberParseAttr(String r0, Attributes r1) {
        return getNumberParseAttr(r0, r1);
    }

    /* renamed from: -$$Nest$smgetStringAttr, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m4988$$Nest$smgetStringAttr(String r0, Attributes r1) {
        return getStringAttr(r0, r1);
    }

    static {
        pow10 = new double[128];
        int r0 = 0;
    L3:
        double[] r1 = pow10;
        if (r0 >= r1.length) goto L6;
        r1[r0] = Math.pow(10.0d, r0);
        r0 = r0 + 1;
        goto L3
    L6:
        SPLIT_BOUNDARY = Pattern.compile("(?<=\\))\\s*(?=[A-Za-z])");
    }

    public SvgHelper() {
    }

    private static float[] arcToBeziers(double r18, double r20) {
        int r0 = (int) Math.ceil((Math.abs(r20) * 2.0d) / 3.141592653589793d);
        double r4 = r20 / r0;
        double r1 = r4 / 2.0d;
        double r6 = (Math.sin(r1) * 1.3333333333333333d) / (Math.cos(r1) + 1.0d);
        float[] r12 = new float[r0 * 6];
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r2 >= r0) goto L6;
        double r8 = r18 + (r2 * r4);
        double r10 = Math.cos(r8);
        double r122 = Math.sin(r8);
        float[] r202 = r12;
        r202[r3] = (float) (r10 - (r6 * r122));
        r202[r3 + 1] = (float) (r122 + (r10 * r6));
        double r82 = r8 + r4;
        double r102 = Math.cos(r82);
        double r83 = Math.sin(r82);
        r202[r3 + 2] = (float) ((r6 * r83) + r102);
        r202[r3 + 3] = (float) (r83 - (r6 * r102));
        int r13 = r3 + 5;
        r202[r3 + 4] = (float) r102;
        r3 = r3 + 6;
        r202[r13] = (float) r83;
        r2 = r2 + 1;
        r12 = r202;
        r0 = r0;
        goto L3
    L6:
        return r12;
    }

    private static double checkedArcCos(double r2) {
        if (r2 >= (-1.0d)) goto L7;
        return 3.141592653589793d;
    L7:
        if (r2 <= 1.0d) goto L11;
        return 0.0d;
    L11:
        return Math.acos(r2);
    }

    public static String decompress(byte[] r5) {
        StringBuilder r0 = new StringBuilder(r5.length * 2);     // Catch: Exception -> L18
        r0.append('M');     // Catch: Exception -> L18
        int r1 = 0;
    L4:
        if (r1 >= r5.length) goto L16;
        byte r2 = r5[r1];     // Catch: Exception -> L18
        int r3 = r2 & UByte.MAX_VALUE;     // Catch: Exception -> L18
        if (r3 < 192) goto L9;
        r0.append("AACAAAAHAAALMAAAQASTAVAAAZaacaaaahaaalmaaaqastava.az0123456789-,".charAt(r3 - 192));     // Catch: Exception -> L18
    L15:
        r1 = r1 + 1;     // Catch: Exception -> L18
        goto L4
    L9:
        if (r3 < 128) goto L12;
        r0.append(',');     // Catch: Exception -> L18
    L14:
        r0.append(r2 & 63);     // Catch: Exception -> L18
        goto L15
    L12:
        if (r3 < 64) goto L14;
        r0.append(SignatureVisitor.SUPER);     // Catch: Exception -> L18
        goto L14
    L16:
        r0.append('z');     // Catch: Exception -> L18
        return r0.toString();
    L18:
        e = move-exception;
        FileLog.e(e);
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static Path doPath(String r22) {
        String r0 = r22;
        if (ApplicationLoader.isAndroidTestEnvironment() == true) goto L5;
        int r1 = r0.length();
        ParserHelper r2 = new ParserHelper(r0, 0);
        r2.skipWhitespace();
        Path r4 = new Path();
        char r5 = 0;
        float r6 = 0.0f;
        float r7 = 0.0f;
        float r11 = 0.0f;
        float r12 = 0.0f;
        float r15 = 0.0f;
        float r16 = 0.0f;
    L7:
        int r8 = r2.pos;
        if (r8 >= r1) goto L104;
        char r82 = r0.charAt(r8);
        switch(r82) {
            case 43: goto L12;
            case 44: goto L45;
            case 45: goto L12;
            case 46: goto L12;
            case 47: goto L45;
            case 48: goto L12;
            case 49: goto L12;
            case 50: goto L12;
            case 51: goto L12;
            case 52: goto L12;
            case 53: goto L12;
            case 54: goto L12;
            case 55: goto L12;
            case 56: goto L12;
            case 57: goto L12;
            default: goto L45;
        };
    L12:
        if (r5 != 'm') goto L14;
    L47:
        char r21 = r5;
        r5 = (char) (r5 - 1);
        char r3 = r21;
    L48:
        boolean r13 = true;
        switch(r5) {
            case 65: goto L87;
            case 67: goto L83;
            case 72: goto L79;
            case 76: goto L75;
            case 77: goto L70;
            case 81: goto L66;
            case 83: goto L61;
            case 84: goto L56;
            case 86: goto L52;
            case 90: goto L51;
            case 97: goto L87;
            case 99: goto L83;
            case 104: goto L79;
            case 108: goto L75;
            case 109: goto L70;
            case 113: goto L66;
            case 115: goto L61;
            case 116: goto L56;
            case 118: goto L52;
            case 122: goto L51;
            default: goto L50;
        };
    L50:
        r13 = false;
    L101:
        if (r13 == true) goto L103;
        r15 = r6;
        r16 = r7;
    L103:
        r2.skipWhitespace();
        r0 = r22;
        r5 = r3;
        goto L7
    L51:
        r4.close();
        r4.moveTo(r11, r12);
        r6 = r11;
        r15 = r6;
        r7 = r12;
        r16 = r7;
        goto L101
    L52:
        float r83 = r2.nextFloat();
        if (r5 != 'v') goto L55;
        r4.rLineTo(0.0f, r83);
        r7 = r7 + r83;
        goto L50
    L55:
        r4.lineTo(r6, r83);
        r7 = r83;
        goto L50
    L56:
        float r9 = r2.nextFloat();
        float r10 = r2.nextFloat();
        if (r5 != 't') goto L59;
        r9 = r9 + r6;
        r10 = r10 + r7;
    L59:
        r15 = (r6 * 2.0f) - r15;
        float r52 = (r7 * 2.0f) - r16;
        r4.quadTo(r15, r52, r9, r10);
        r16 = r52;
    L60:
        r6 = r9;
        r7 = r10;
        goto L101
    L61:
        float r102 = r2.nextFloat();
        float r14 = r2.nextFloat();
        float r18 = r2.nextFloat();
        float r19 = r2.nextFloat();
        if (r5 != 's') goto L64;
        r102 = r102 + r6;
        r18 = r18 + r6;
        r14 = r14 + r7;
        r19 = r19 + r7;
    L64:
        r9 = r18;
        float r53 = (r6 * 2.0f) - r15;
        float r62 = (r7 * 2.0f) - r16;
        float r72 = r102;
        float r84 = r14;
        r10 = r19;
        r4.cubicTo(r53, r62, r72, r84, r9, r10);
    L65:
        r15 = r72;
        r16 = r84;
        goto L60
    L66:
        float r85 = r2.nextFloat();
        float r92 = r2.nextFloat();
        float r103 = r2.nextFloat();
        float r142 = r2.nextFloat();
        if (r5 != 'q') goto L69;
        r85 = r85 + r6;
        r92 = r92 + r7;
        r103 = r103 + r6;
        r142 = r142 + r7;
    L69:
        r15 = r85;
        r6 = r103;
        r7 = r142;
        r4.quadTo(r15, r92, r6, r7);
        r16 = r92;
        goto L101
    L70:
        float r86 = r2.nextFloat();
        float r93 = r2.nextFloat();
        if (r5 != 'm') goto L74;
        r11 = r11 + r86;
        r12 = r12 + r93;
        r4.rMoveTo(r86, r93);
    L73:
        r6 = r6 + r86;
        r7 = r7 + r93;
        goto L50
    L74:
        r4.moveTo(r86, r93);
        r6 = r86;
        r11 = r6;
        r7 = r93;
        r12 = r7;
        goto L50
    L75:
        r86 = r2.nextFloat();
        r93 = r2.nextFloat();
        if (r5 != 'l') goto L78;
        r4.rLineTo(r86, r93);
        goto L73
    L78:
        r4.lineTo(r86, r93);
        r6 = r86;
        r7 = r93;
        goto L50
    L79:
        float r87 = r2.nextFloat();
        if (r5 != 'h') goto L82;
        r4.rLineTo(r87, 0.0f);
        r6 = r6 + r87;
        goto L50
    L82:
        r4.lineTo(r87, r7);
        r6 = r87;
        goto L50
    L83:
        float r88 = r2.nextFloat();
        float r94 = r2.nextFloat();
        float r104 = r2.nextFloat();
        float r152 = r2.nextFloat();
        float r162 = r2.nextFloat();
        float r17 = r2.nextFloat();
        if (r5 != 'c') goto L86;
        r88 = r88 + r6;
        r104 = r104 + r6;
        r162 = r162 + r6;
        r94 = r94 + r7;
        r152 = r152 + r7;
        r17 = r17 + r7;
    L86:
        float r54 = r88;
        float r63 = r94;
        r72 = r104;
        r84 = r152;
        r9 = r162;
        r10 = r17;
        r4.cubicTo(r54, r63, r72, r84, r9, r10);
        goto L65
    L87:
        float r95 = r2.nextFloat();
        float r105 = r2.nextFloat();
        float r143 = r11;
        float r112 = r2.nextFloat();
        if (((int) r2.nextFloat()) != 1) goto L90;
        float r89 = r12;
        boolean r122 = true;
    L92:
        if (((int) r2.nextFloat()) == 1) goto L95;
        r13 = false;
    L95:
        float r02 = r2.nextFloat();
        float r172 = r2.nextFloat();
        if (r5 != 'a') goto L99;
        float r03 = r02 + r6;
        r172 = r172 + r7;
        float r55 = r6;
        float r64 = r7;
        float r73 = r03;
    L98:
        float r04 = r89;
        float r810 = r172;
        drawArc(r4, r55, r64, r73, r810, r95, r105, r112, r122, r13);
        r6 = r73;
        r7 = r810;
        r12 = r04;
        r11 = r143;
        goto L50
    L99:
        r55 = r6;
        r64 = r7;
        r73 = r02;
        goto L98
    L90:
        r89 = r12;
        r122 = false;
        goto L92
    L14:
        if (r5 == 'M') goto L47;
        if (r5 != 'c') goto L18;
    L46:
        r3 = r5;
        goto L48
    L18:
        if (r5 == 'C') goto L46;
        if (r5 == 'l') goto L46;
        if (r5 == 'L') goto L46;
        if (r5 == 's') goto L46;
        if (r5 == 'S') goto L46;
        if (r5 == 'h') goto L46;
        if (r5 == 'H') goto L46;
        if (r5 == 'v') goto L46;
        if (r5 == 'V') goto L46;
        if (r5 == 'q') goto L46;
        if (r5 == 'Q') goto L46;
        if (r5 == 'a') goto L46;
        if (r5 == 'A') goto L46;
        if (r5 == 't') goto L46;
        if (r5 == 'T') goto L46;
    L45:
        r2.advance();
        r3 = r82;
        r5 = r3;
        goto L48
    L104:
        return r4;
    L5:
        return new Path();
    }

    private static void drawArc(Path r39, float r40, float r41, float r42, float r43, float r44, float r45, float r46, boolean r47, boolean r48) {
        float r1 = r42;
        if (r40 != r1) goto L8;
        if (r41 != r43) goto L8;
        return;
    L8:
        if (r44 != 0.0f) goto L10;
        r1 = r42;
    L48:
        r39.lineTo(r1, r43);
        return;
    L10:
        if (r45 == 0.0f) goto L48;
        float r5 = Math.abs(r44);
        float r6 = Math.abs(r45);
        double r7 = Math.toRadians(r46 % 360.0d);
        double r9 = Math.cos(r7);
        double r72 = Math.sin(r7);
        double r11 = (r40 - r1) / 2.0d;
        double r13 = (r41 - r43) / 2.0d;
        double r92 = (r9 * r11) + (r72 * r13);
        double r112 = ((-r72) * r11) + (r13 * r9);
        double r132 = r5 * r5;
        double r73 = r6 * r6;
        double r21 = r92 * r92;
        double r23 = r112 * r112;
        double r25 = (r21 / r132) + (r23 / r73);
        if (r25 <= 0.99999d) goto L15;
        double r74 = Math.sqrt(r25) * 1.00001d;
        r5 = (float) (r5 * r74);
        r6 = (float) (r74 * r6);
        r132 = r5 * r5;
        r73 = r6 * r6;
    L15:
        double r252 = 1.0d;
        if (r47 != r48) goto L18;
        double r29 = -1.0d;
    L19:
        double r31 = r132 * r73;
        double r133 = r132 * r23;
        double r75 = r73 * r21;
        double r312 = ((r31 - r133) - r75) / (r133 + r75);
        if (r312 >= 0.0d) goto L22;
        r312 = 0.0d;
    L22:
        double r292 = r29 * Math.sqrt(r312);
        double r134 = r5;
        double r76 = r6;
        double r212 = ((r134 * r112) / r76) * r292;
        double r293 = r292 * (-((r76 * r92) / r134));
        double r77 = ((r40 + r1) / 2.0d) + ((r9 * r212) - (r72 * r293));
        double r135 = ((r41 + r43) / 2.0d) + ((r72 * r212) + (r9 * r293));
        double r15 = (r92 - r212) / r134;
        double r19 = (r112 - r293) / r76;
        double r93 = ((-r92) - r212) / r134;
        double r113 = ((-r112) - r293) / r76;
        double r17 = (r15 * r15) + (r19 * r19);
        double r213 = Math.sqrt(r17);
        if (r19 >= 0.0d) goto L25;
        double r294 = -1.0d;
    L26:
        double r295 = r294 * Math.acos(r15 / r213);
        double r172 = Math.sqrt(r17 * ((r93 * r93) + (r113 * r113)));
        double r214 = (r15 * r93) + (r19 * r113);
        if (((r15 * r113) - (r19 * r93)) >= 0.0d) goto L29;
        r252 = -1.0d;
    L29:
        double r253 = r252 * checkedArcCos(r214 / r172);
        if (r253 != 0.0d) goto L34;
        r39.lineTo(r1, r43);
        return;
    L34:
        if (r48 == true) goto L38;
        if (r253 <= 0.0d) goto L38;
        r253 = r253 - 6.283185307179586d;
    L42:
        float[] r0 = arcToBeziers(r295 % 6.283185307179586d, r253 % 6.283185307179586d);
        Matrix r12 = new Matrix();
        r12.postScale(r5, r6);
        r12.postRotate(r46);
        r12.postTranslate((float) r77, (float) r135);
        r12.mapPoints(r0);
        r0[r0.length - 2] = r42;
        r0[r0.length - 1] = r43;
        int r14 = 0;
    L44:
        if (r14 >= r0.length) goto L51;
        r39.cubicTo(r0[r14], r0[r14 + 1], r0[r14 + 2], r0[r14 + 3], r0[r14 + 4], r0[r14 + 5]);
        r14 = r14 + 6;
        goto L44
    L51:
        return;
    L38:
        if (r48 == false) goto L42;
        if (r253 >= 0.0d) goto L42;
        r253 = r253 + 6.283185307179586d;
        goto L42
    L25:
        r294 = 1.0d;
        goto L26
    L18:
        r29 = 1.0d;
        goto L19
    }

    public static Bitmap getBitmap(int r1, int r2, int r3, int r4) {
        return getBitmap(r1, r2, r3, r4, 1.0f);
    }

    public static Bitmap getBitmap(int r6, int r7, int r8, int r9, float r10) {
        return getBitmap(r6, r7, r8, r9, r10, ScaleMode.Default);
    }

    public static Bitmap getBitmap(int r9, int r10, int r11, int r12, float r13, ScaleMode r14) {
        InputStream r92 = ApplicationLoader.applicationContext.getResources().openRawResource(r9);     // Catch: Exception -> L16
        XMLReader r0 = SAXParserFactory.newInstance().newSAXParser().getXMLReader();     // Catch: Throwable -> L7
        SVGHandler r1 = new SVGHandler(r10, r11, Integer.valueOf(r12), false, r13, r14, null);     // Catch: Throwable -> L7
        r0.setContentHandler(r1);     // Catch: Throwable -> L7
        r0.parse(new InputSource(r92));     // Catch: Throwable -> L7
        Bitmap r102 = r1.getBitmap();     // Catch: Throwable -> L7
        if (r92 == null) goto L6;
        r92.close();     // Catch: Exception -> L16
    L6:
        return r102;
    L7:
        th = move-exception;
        if (r92 == null) goto L24;
        r92.close();     // Catch: Throwable -> L12
    L25:
        throw th;     // Catch: Exception -> L16
    L12:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L16
        throw th;     // Catch: Exception -> L16
    L24:
        throw th;     // Catch: Exception -> L16
    L16:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static Bitmap getBitmap(File r1, int r2, int r3, boolean r4) {
        return getBitmap(r1, r2, r3, r4, ScaleMode.Default);
    }

    public static Bitmap getBitmap(File r11, int r12, int r13, boolean r14, ScaleMode r15) {
        FileInputStream r2 = new FileInputStream(r11);     // Catch: Exception -> L16
        XMLReader r112 = SAXParserFactory.newInstance().newSAXParser().getXMLReader();     // Catch: Throwable -> L7
        if (r14 == false) goto L9;
        Integer r6 = -1;
    L10:
        SVGHandler r3 = new SVGHandler(r12, r13, r6, false, 1.0f, r15, null);     // Catch: Throwable -> L7
        if (r14 == true) goto L13;
        SVGHandler.m4990$$Nest$fputalphaOnly(r3, true);     // Catch: Throwable -> L7
    L13:
        r112.setContentHandler(r3);     // Catch: Throwable -> L7
        r112.parse(new InputSource(r2));     // Catch: Throwable -> L7
        Bitmap r113 = r3.getBitmap();     // Catch: Throwable -> L7
        r2.close();     // Catch: Exception -> L16
        return r113;
    L9:
        r6 = null;
    L7:
        th = move-exception;
        r2.close();     // Catch: Throwable -> L20
    L31:
        throw th;     // Catch: Exception -> L16
    L20:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L16
        throw th;     // Catch: Exception -> L16
    L16:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static Bitmap getBitmap(InputStream r9, int r10, int r11, Integer r12, float r13) {
        if (r9 != null) goto L28;
        return null;
    L28:
        XMLReader r0 = SAXParserFactory.newInstance().newSAXParser().getXMLReader();     // Catch: Throwable -> L10 Exception -> L12
        SVGHandler r2 = new SVGHandler(r10, r11, r12, false, r13, null);     // Catch: Throwable -> L10 Exception -> L12
        r0.setContentHandler(r2);     // Catch: Throwable -> L10 Exception -> L12
        r0.parse(new InputSource(r9));     // Catch: Throwable -> L10 Exception -> L12
        Bitmap r102 = r2.getBitmap();     // Catch: Throwable -> L10 Exception -> L12
        r9.close();     // Catch: Exception -> L8
        return r102;
    L12:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L10
        r9.close();     // Catch: Exception -> L8
        return null;
    L10:
        th = move-exception;
        r9.close();     // Catch: Throwable -> L19
    L30:
        throw th;     // Catch: Exception -> L8
    L19:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L8
        throw th;     // Catch: Exception -> L8
    L8:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static Bitmap getBitmap(InputStream r9, int r10, int r11, boolean r12) {
        XMLReader r0 = SAXParserFactory.newInstance().newSAXParser().getXMLReader();     // Catch: Exception -> L6
        if (r12 == false) goto L8;
        Integer r5 = -1;
    L9:
        SVGHandler r2 = new SVGHandler(r10, r11, r5, false, 1.0f, null);     // Catch: Exception -> L6
        r0.setContentHandler(r2);     // Catch: Exception -> L6
        r0.parse(new InputSource(r9));     // Catch: Exception -> L6
        return r2.getBitmap();
    L8:
        r5 = null;
    L6:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static Bitmap getBitmap(String r9, int r10, int r11, boolean r12) {
        XMLReader r0 = SAXParserFactory.newInstance().newSAXParser().getXMLReader();     // Catch: Exception -> L6
        if (r12 == false) goto L8;
        Integer r5 = -1;
    L9:
        SVGHandler r2 = new SVGHandler(r10, r11, r5, false, 1.0f, null);     // Catch: Exception -> L6
        r0.setContentHandler(r2);     // Catch: Exception -> L6
        r0.parse(new InputSource(new StringReader(r9)));     // Catch: Exception -> L6
        return r2.getBitmap();
    L8:
        r5 = null;
    L6:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static Bitmap getBitmapByPathOnly(String r2, int r3, int r4, int r5, int r6) {
        Path r22 = doPath(r2);     // Catch: Exception -> L4
        Bitmap r0 = Bitmap.createBitmap(r5, r6, Bitmap.Config.ARGB_8888);     // Catch: Exception -> L4
        Canvas r1 = new Canvas(r0);     // Catch: Exception -> L4
        r1.scale(r5 / r3, r6 / r4);     // Catch: Exception -> L4
        Paint r32 = new Paint();     // Catch: Exception -> L4
        r32.setColor(-1);     // Catch: Exception -> L4
        r1.drawPath(r22, r32);     // Catch: Exception -> L4
        return r0;
    L4:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private static Integer getColorByName(String r2) {
        String r22 = r2.toLowerCase();
        r22.getClass();
        switch(r22.hashCode()) {
            case -734239628: goto L38;
            case 112785: goto L34;
            case 3027034: goto L30;
            case 3068707: goto L26;
            case 3181155: goto L22;
            case 93818879: goto L18;
            case 98619139: goto L14;
            case 113101865: goto L10;
            case 828922025: goto L6;
            default: goto L4;
        };
    L4:
        char r23 = 65535;
    L41:
        switch(r23) {
            case 0: goto L61;
            case 1: goto L59;
            case 2: goto L57;
            case 3: goto L55;
            case 4: goto L53;
            case 5: goto L51;
            case 6: goto L49;
            case 7: goto L47;
            case 8: goto L45;
            default: goto L42;
        };
    L42:
        return null;
    L45:
        return -65281;
    L47:
        return -1;
    L49:
        return -16711936;
    L51:
        return -16777216;
    L53:
        return -7829368;
    L55:
        return -16711681;
    L57:
        return -16776961;
    L59:
        return Integer.valueOf(Opcodes.V_PREVIEW);
    L61:
        return -256;
    L6:
        if (r22.equals("magenta") == false) goto L4;
        r23 = '\b';
        goto L41
    L10:
        if (r22.equals("white") == false) goto L4;
        r23 = 7;
        goto L41
    L14:
        if (r22.equals("green") == false) goto L4;
        r23 = 6;
        goto L41
    L18:
        if (r22.equals("black") == false) goto L4;
        r23 = 5;
        goto L41
    L22:
        if (r22.equals("gray") == false) goto L4;
        r23 = 4;
        goto L41
    L26:
        if (r22.equals("cyan") == false) goto L4;
        r23 = 3;
        goto L41
    L30:
        if (r22.equals("blue") == false) goto L4;
        r23 = 2;
        goto L41
    L34:
        if (r22.equals(PluginsConstants.Settings.RED) == false) goto L4;
        r23 = 1;
        goto L41
    L38:
        if (r22.equals("yellow") == false) goto L4;
        r23 = 0;
        goto L41
    }

    public static SvgDrawable getDrawable(int r8, Integer r9) {
        XMLReader r0 = SAXParserFactory.newInstance().newSAXParser().getXMLReader();     // Catch: Exception -> L4
        SVGHandler r1 = new SVGHandler(0, 0, r9, true, 1.0f, null);     // Catch: Exception -> L4
        r0.setContentHandler(r1);     // Catch: Exception -> L4
        r0.parse(new InputSource(ApplicationLoader.applicationContext.getResources().openRawResource(r8)));     // Catch: Exception -> L4
        return r1.getDrawable();
    L4:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static SvgDrawable getDrawable(String r8) {
        XMLReader r0 = SAXParserFactory.newInstance().newSAXParser().getXMLReader();     // Catch: Exception -> L4
        SVGHandler r1 = new SVGHandler(0, 0, null, true, 1.0f, null);     // Catch: Exception -> L4
        r0.setContentHandler(r1);     // Catch: Exception -> L4
        r0.parse(new InputSource(new StringReader(r8)));     // Catch: Exception -> L4
        return r1.getDrawable();
    L4:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static SvgDrawable getDrawableByPath(Path r4, int r5, int r6) {
        SvgDrawable r0 = new SvgDrawable();     // Catch: Exception -> L4
        r0.commands.add(r4);     // Catch: Exception -> L4
        r0.paints.put(r4, new Paint(1));     // Catch: Exception -> L4
        r0.width = r5;     // Catch: Exception -> L4
        r0.height = r6;     // Catch: Exception -> L4
        return r0;
    L4:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static SvgDrawable getDrawableByPath(String r4, int r5, int r6) {
        Path r42 = doPath(r4);     // Catch: Exception -> L4
        SvgDrawable r0 = new SvgDrawable();     // Catch: Exception -> L4
        r0.commands.add(r42);     // Catch: Exception -> L4
        r0.paints.put(r42, new Paint(1));     // Catch: Exception -> L4
        r0.width = r5;     // Catch: Exception -> L4
        r0.height = r6;     // Catch: Exception -> L4
        return r0;
    L4:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private static Float getFloatAttr(String r1, Attributes r2) {
        return getFloatAttr(r1, r2, null);
    }

    private static Float getFloatAttr(String r0, Attributes r1, Float r2) {
        String r02 = getStringAttr(r0, r1);
        if (r02 != null) goto L6;
        return r2;
    L6:
        if (r02.endsWith("px") == false) goto L9;
        r02 = r02.substring(0, r02.length() - 2);
    L13:
        return Float.valueOf(Float.parseFloat(r02));
    L9:
        if (r02.endsWith("mm") == false) goto L13;
        return null;
    }

    private static Integer getHexAttr(String r1, Attributes r2) {
        String r12 = getStringAttr(r1, r2);
        if (r12 != null) goto L11;
        return null;
    L11:
        return Integer.valueOf(Integer.parseInt(r12.substring(1), 16));
    L10:
        return getColorByName(r12);
    }

    private static NumberParse getNumberParseAttr(String r3, Attributes r4) {
        int r0 = r4.getLength();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        if (r4.getLocalName(r1).equals(r3) == true) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        return parseNumbers(r4.getValue(r1));
    L9:
        return null;
    }

    private static String getStringAttr(String r3, Attributes r4) {
        int r0 = r4.getLength();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        if (r4.getLocalName(r1).equals(r3) == true) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        return r4.getValue(r1);
    L9:
        return null;
    }

    public static SvgResult getSvgBitmap(File r10, int r11, int r12, boolean r13) {
        FileInputStream r2 = new FileInputStream(r10);     // Catch: Exception -> L16
        XMLReader r102 = SAXParserFactory.newInstance().newSAXParser().getXMLReader();     // Catch: Throwable -> L7
        if (r13 == false) goto L9;
        Integer r6 = -1;
    L10:
        SVGHandler r3 = new SVGHandler(r11, r12, r6, false, 1.0f, null);     // Catch: Throwable -> L7
        if (r13 == true) goto L13;
        SVGHandler.m4990$$Nest$fputalphaOnly(r3, true);     // Catch: Throwable -> L7
    L13:
        r102.setContentHandler(r3);     // Catch: Throwable -> L7
        r102.parse(new InputSource(r2));     // Catch: Throwable -> L7
        r2.close();     // Catch: Exception -> L16
        return r3;
    L9:
        r6 = null;
    L7:
        th = move-exception;
        r2.close();     // Catch: Throwable -> L20
    L31:
        throw th;     // Catch: Exception -> L16
    L20:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L16
        throw th;     // Catch: Exception -> L16
    L16:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private static NumberParse parseNumbers(String r11) {
        int r0 = r11.length();
        ArrayList r1 = new ArrayList();
        int r5 = 0;
        boolean r6 = false;
        int r4 = 1;
    L3:
        if (r4 >= r0) goto L27;
        if (r6 == false) goto L6;
        r6 = false;
    L26:
        r4 = r4 + 1;
        goto L3
    L6:
        char r7 = r11.charAt(r4);
        switch(r7) {
            case 9: goto L15;
            case 10: goto L15;
            case 32: goto L15;
            case 41: goto L9;
            case 44: goto L15;
            case 45: goto L15;
            case 65: goto L9;
            case 67: goto L9;
            case 72: goto L9;
            case 76: goto L9;
            case 77: goto L9;
            case 81: goto L9;
            case 83: goto L9;
            case 84: goto L9;
            case 86: goto L9;
            case 90: goto L9;
            case 97: goto L9;
            case 99: goto L9;
            case 104: goto L9;
            case 108: goto L9;
            case 109: goto L9;
            case 113: goto L9;
            case 115: goto L9;
            case 116: goto L9;
            case 118: goto L9;
            case 122: goto L9;
            default: goto L26;
        };
    L15:
        if (r7 == '-') goto L17;
    L19:
        String r9 = r11.substring(r5, r4);
        if (r9.trim().length() <= 0) goto L25;
        r1.add(Float.valueOf(Float.parseFloat(r9)));
        if (r7 != '-') goto L24;
        r5 = r4;
        goto L26
    L24:
        r5 = r4 + 1;
        r6 = true;
        goto L26
    L25:
        r5 = r5 + 1;
        goto L26
    L17:
        if (r11.charAt(r4 - 1) != 'e') goto L19;
    L9:
        String r112 = r11.substring(r5, r4);
        if (r112.trim().length() <= 0) goto L13;
        r1.add(Float.valueOf(Float.parseFloat(r112)));
    L13:
        return new NumberParse(r1, r4);
    L27:
        String r02 = r11.substring(r5);
        if (r02.length() <= 0) goto L32;
        r1.add(Float.valueOf(Float.parseFloat(r02)));     // Catch: NumberFormatException -> L33
    L30:
        r5 = r11.length();
    L32:
        return new NumberParse(r1, r5);
    }

    public static Matrix parseTransform(String r2) {
        Matrix r0 = new Matrix();
        Iterator<String> r22 = splitSvgTransforms(r2).iterator();
    L4:
        if (r22.hasNext() == false) goto L8;
        Matrix r1 = parseTransformCommand(r22.next());
        if (r1 == null) goto L4;
        r0.preConcat(r1);
        goto L4
    L8:
        return r0;
    }

    private static Matrix parseTransformCommand(String r16) {
        float r4 = 0.0f;
        if (r16.startsWith("matrix(") == false) goto L9;
        NumberParse r0 = parseNumbers(r16.substring(7));
        if (NumberParse.m4989$$Nest$fgetnumbers(r0).size() != 6) goto L48;
        Matrix r1 = new Matrix();
        r1.setValues(new float[]{((Float) NumberParse.m4989$$Nest$fgetnumbers(r0).get(0)).floatValue(), ((Float) NumberParse.m4989$$Nest$fgetnumbers(r0).get(2)).floatValue(), ((Float) NumberParse.m4989$$Nest$fgetnumbers(r0).get(4)).floatValue(), ((Float) NumberParse.m4989$$Nest$fgetnumbers(r0).get(1)).floatValue(), ((Float) NumberParse.m4989$$Nest$fgetnumbers(r0).get(3)).floatValue(), ((Float) NumberParse.m4989$$Nest$fgetnumbers(r0).get(5)).floatValue(), 0.0f, 0.0f, 1.0f});
        return r1;
    L48:
        return null;
    L9:
        if (r16.startsWith("translate(") == false) goto L18;
        NumberParse r02 = parseNumbers(r16.substring(10));
        if (NumberParse.m4989$$Nest$fgetnumbers(r02).size() <= 0) goto L50;
        float r12 = ((Float) NumberParse.m4989$$Nest$fgetnumbers(r02).get(0)).floatValue();
        if (NumberParse.m4989$$Nest$fgetnumbers(r02).size() <= 1) goto L15;
        r4 = ((Float) NumberParse.m4989$$Nest$fgetnumbers(r02).get(1)).floatValue();
    L15:
        Matrix r03 = new Matrix();
        r03.postTranslate(r12, r4);
        return r03;
    L50:
        return null;
    L18:
        if (r16.startsWith("scale(") == false) goto L27;
        NumberParse r04 = parseNumbers(r16.substring(6));
        if (NumberParse.m4989$$Nest$fgetnumbers(r04).size() <= 0) goto L51;
        float r13 = ((Float) NumberParse.m4989$$Nest$fgetnumbers(r04).get(0)).floatValue();
        if (NumberParse.m4989$$Nest$fgetnumbers(r04).size() <= 1) goto L24;
        r4 = ((Float) NumberParse.m4989$$Nest$fgetnumbers(r04).get(1)).floatValue();
    L24:
        Matrix r05 = new Matrix();
        r05.postScale(r13, r4);
        return r05;
    L51:
        return null;
    L27:
        if (r16.startsWith("skewX(") == false) goto L33;
        NumberParse r06 = parseNumbers(r16.substring(6));
        if (NumberParse.m4989$$Nest$fgetnumbers(r06).size() <= 0) goto L52;
        float r07 = ((Float) NumberParse.m4989$$Nest$fgetnumbers(r06).get(0)).floatValue();
        Matrix r14 = new Matrix();
        r14.postSkew((float) Math.tan(r07), 0.0f);
        return r14;
    L52:
        return null;
    L33:
        if (r16.startsWith("skewY(") == false) goto L39;
        NumberParse r08 = parseNumbers(r16.substring(6));
        if (NumberParse.m4989$$Nest$fgetnumbers(r08).size() <= 0) goto L53;
        float r09 = ((Float) NumberParse.m4989$$Nest$fgetnumbers(r08).get(0)).floatValue();
        Matrix r15 = new Matrix();
        r15.postSkew(0.0f, (float) Math.tan(r09));
        return r15;
    L53:
        return null;
    L39:
        if (r16.startsWith("rotate(") == false) goto L54;
        NumberParse r010 = parseNumbers(r16.substring(7));
        if (NumberParse.m4989$$Nest$fgetnumbers(r010).size() <= 0) goto L55;
        Matrix r17 = new Matrix();
        float r2 = ((Float) NumberParse.m4989$$Nest$fgetnumbers(r010).get(0)).floatValue();
        if (NumberParse.m4989$$Nest$fgetnumbers(r010).size() <= 2) goto L46;
        r17.postRotate(r2, ((Float) NumberParse.m4989$$Nest$fgetnumbers(r010).get(1)).floatValue(), ((Float) NumberParse.m4989$$Nest$fgetnumbers(r010).get(2)).floatValue());
        return r17;
    L46:
        r17.postRotate(r2);
        return r17;
    L55:
        return null;
    L54:
        return null;
    }

    private static List<String> splitSvgTransforms(String r5) {
        if (r5 == null) goto L4;
        String r52 = r5.trim();
        if (r52.isEmpty() == true) goto L8;
        String[] r53 = SPLIT_BOUNDARY.split(r52);
        ArrayList r0 = new ArrayList(r53.length);
        int r1 = r53.length;
        int r2 = 0;
    L10:
        if (r2 >= r1) goto L15;
        String r3 = r53[r2].trim();
        if (r3.isEmpty() == true) goto L14;
        r0.add(r3);
    L14:
        r2 = r2 + 1;
        goto L10
    L15:
        return r0;
    L8:
        return Collections.EMPTY_LIST;
    L4:
        return Collections.EMPTY_LIST;
    }
}
