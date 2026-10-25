package androidx.media3.extractor.text.ttml;

import android.text.Layout;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class TtmlStyle {
    private int backgroundColor;
    private int bold;
    private String extent;
    private int fontColor;
    private String fontFamily;
    private float fontSize;
    private int fontSizeUnit;
    private boolean hasBackgroundColor;
    private boolean hasFontColor;
    private String id;
    private int italic;
    private int linethrough;
    private Layout.Alignment multiRowAlign;
    private String origin;
    private int rubyPosition;
    private int rubyType;
    private float shearPercentage;
    private Layout.Alignment textAlign;
    private int textCombine;
    private TextEmphasis textEmphasis;
    private int underline;

    public TtmlStyle() {
        this.linethrough = -1;
        this.underline = -1;
        this.bold = -1;
        this.italic = -1;
        this.fontSizeUnit = -1;
        this.rubyType = -1;
        this.rubyPosition = -1;
        this.textCombine = -1;
        this.shearPercentage = Float.MAX_VALUE;
    }

    private TtmlStyle inherit(TtmlStyle r4, boolean r5) {
        if (r4 != null) goto L4;
    L68:
        return this;
    L4:
        if (this.hasFontColor == true) goto L9;
        if (r4.hasFontColor == false) goto L9;
        setFontColor(r4.fontColor);
    L9:
        if (this.bold != (-1)) goto L12;
        this.bold = r4.bold;
    L12:
        if (this.italic != (-1)) goto L15;
        this.italic = r4.italic;
    L15:
        if (this.fontFamily != null) goto L20;
        String r0 = r4.fontFamily;
        if (r0 == null) goto L20;
        this.fontFamily = r0;
    L20:
        if (this.linethrough != (-1)) goto L23;
        this.linethrough = r4.linethrough;
    L23:
        if (this.underline != (-1)) goto L26;
        this.underline = r4.underline;
    L26:
        if (this.rubyPosition != (-1)) goto L29;
        this.rubyPosition = r4.rubyPosition;
    L29:
        if (this.textAlign != null) goto L34;
        Layout.Alignment r02 = r4.textAlign;
        if (r02 == null) goto L34;
        this.textAlign = r02;
    L34:
        if (this.multiRowAlign != null) goto L39;
        Layout.Alignment r03 = r4.multiRowAlign;
        if (r03 == null) goto L39;
        this.multiRowAlign = r03;
    L39:
        if (this.textCombine != (-1)) goto L42;
        this.textCombine = r4.textCombine;
    L42:
        if (this.fontSizeUnit != (-1)) goto L45;
        this.fontSizeUnit = r4.fontSizeUnit;
        this.fontSize = r4.fontSize;
    L45:
        if (this.textEmphasis != null) goto L48;
        this.textEmphasis = r4.textEmphasis;
    L48:
        if (this.shearPercentage != Float.MAX_VALUE) goto L51;
        this.shearPercentage = r4.shearPercentage;
    L51:
        if (this.origin != null) goto L54;
        this.origin = r4.origin;
    L54:
        if (this.extent != null) goto L56;
        this.extent = r4.extent;
    L56:
        if (r5 == true) goto L58;
    L62:
        if (r5 == false) goto L68;
        if (this.rubyType != (-1)) goto L68;
        int r42 = r4.rubyType;
        if (r42 == (-1)) goto L68;
        this.rubyType = r42;
        goto L68
    L58:
        if (this.hasBackgroundColor == true) goto L62;
        if (r4.hasBackgroundColor == false) goto L62;
        setBackgroundColor(r4.backgroundColor);
        goto L62
    }

    public TtmlStyle chain(TtmlStyle r2) {
        return inherit(r2, true);
    }

    public int getBackgroundColor() {
        if (this.hasBackgroundColor == true) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Background color has not been defined.");
        return 0;
    L5:
        return this.backgroundColor;
    }

    public String getExtent() {
        return this.extent;
    }

    public int getFontColor() {
        if (this.hasFontColor == true) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Font color has not been defined.");
        return 0;
    L5:
        return this.fontColor;
    }

    public String getFontFamily() {
        return this.fontFamily;
    }

    public float getFontSize() {
        return this.fontSize;
    }

    public int getFontSizeUnit() {
        return this.fontSizeUnit;
    }

    public String getId() {
        return this.id;
    }

    public Layout.Alignment getMultiRowAlign() {
        return this.multiRowAlign;
    }

    public String getOrigin() {
        return this.origin;
    }

    public int getRubyPosition() {
        return this.rubyPosition;
    }

    public int getRubyType() {
        return this.rubyType;
    }

    public float getShearPercentage() {
        return this.shearPercentage;
    }

    public int getStyle() {
        int r0 = this.bold;
        if (r0 == (-1)) goto L5;
    L7:
        int r1 = 0;
        if (r0 != 1) goto L10;
        int r02 = 1;
    L12:
        if (this.italic != 1) goto L15;
        r1 = 2;
    L15:
        return r02 | r1;
    L10:
        r02 = 0;
        goto L12
    L5:
        if (this.italic != (-1)) goto L7;
        return -1;
    }

    public Layout.Alignment getTextAlign() {
        return this.textAlign;
    }

    public boolean getTextCombine() {
        if (this.textCombine != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public TextEmphasis getTextEmphasis() {
        return this.textEmphasis;
    }

    public boolean hasBackgroundColor() {
        return this.hasBackgroundColor;
    }

    public boolean hasFontColor() {
        return this.hasFontColor;
    }

    public boolean isLinethrough() {
        if (this.linethrough != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public boolean isUnderline() {
        if (this.underline != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public TtmlStyle setBackgroundColor(int r1) {
        this.backgroundColor = r1;
        this.hasBackgroundColor = true;
        return this;
    }

    public TtmlStyle setBold(boolean r1) {
        this.bold = r1 ? 1 : 0;
        return this;
    }

    public TtmlStyle setExtent(String r1) {
        this.extent = r1;
        return this;
    }

    public TtmlStyle setFontColor(int r1) {
        this.fontColor = r1;
        this.hasFontColor = true;
        return this;
    }

    public TtmlStyle setFontFamily(String r1) {
        this.fontFamily = r1;
        return this;
    }

    public TtmlStyle setFontSize(float r1) {
        this.fontSize = r1;
        return this;
    }

    public TtmlStyle setFontSizeUnit(int r1) {
        this.fontSizeUnit = r1;
        return this;
    }

    public TtmlStyle setId(String r1) {
        this.id = r1;
        return this;
    }

    public TtmlStyle setItalic(boolean r1) {
        this.italic = r1 ? 1 : 0;
        return this;
    }

    public TtmlStyle setLinethrough(boolean r1) {
        this.linethrough = r1 ? 1 : 0;
        return this;
    }

    public TtmlStyle setMultiRowAlign(Layout.Alignment r1) {
        this.multiRowAlign = r1;
        return this;
    }

    public TtmlStyle setOrigin(String r1) {
        this.origin = r1;
        return this;
    }

    public TtmlStyle setRubyPosition(int r1) {
        this.rubyPosition = r1;
        return this;
    }

    public TtmlStyle setRubyType(int r1) {
        this.rubyType = r1;
        return this;
    }

    public TtmlStyle setShearPercentage(float r1) {
        this.shearPercentage = r1;
        return this;
    }

    public TtmlStyle setTextAlign(Layout.Alignment r1) {
        this.textAlign = r1;
        return this;
    }

    public TtmlStyle setTextCombine(boolean r1) {
        this.textCombine = r1 ? 1 : 0;
        return this;
    }

    public TtmlStyle setTextEmphasis(TextEmphasis r1) {
        this.textEmphasis = r1;
        return this;
    }

    public TtmlStyle setUnderline(boolean r1) {
        this.underline = r1 ? 1 : 0;
        return this;
    }
}
