package androidx.media3.extractor.text.webvtt;

import android.text.TextUtils;
import com.google.common.base.Ascii;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.tgnet.TLObject;

/* loaded from: classes4.dex */
public final class WebvttCssStyle {
    private int backgroundColor;
    private int bold;
    private boolean combineUpright;
    private int fontColor;
    private String fontFamily;
    private float fontSize;
    private int fontSizeUnit;
    private boolean hasBackgroundColor;
    private boolean hasFontColor;
    private int italic;
    private int linethrough;
    private int rubyPosition;
    private Set<String> targetClasses;
    private String targetId;
    private String targetTag;
    private String targetVoice;
    private int underline;

    public WebvttCssStyle() {
        this.targetId = _UrlKt.FRAGMENT_ENCODE_SET;
        this.targetTag = _UrlKt.FRAGMENT_ENCODE_SET;
        this.targetClasses = Collections.EMPTY_SET;
        this.targetVoice = _UrlKt.FRAGMENT_ENCODE_SET;
        this.fontFamily = null;
        this.hasFontColor = false;
        this.hasBackgroundColor = false;
        this.linethrough = -1;
        this.underline = -1;
        this.bold = -1;
        this.italic = -1;
        this.fontSizeUnit = -1;
        this.rubyPosition = -1;
        this.combineUpright = false;
    }

    private static int updateScoreForMatch(int r1, String r2, String r3, int r4) {
        if (r2.isEmpty() == false) goto L5;
    L12:
        return r1;
    L5:
        if (r1 == (-1)) goto L12;
        if (r2.equals(r3) == true) goto L10;
        return -1;
    L10:
        return r1 + r4;
    }

    public int getBackgroundColor() {
        if (this.hasBackgroundColor == true) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Background color not defined.");
        return 0;
    L5:
        return this.backgroundColor;
    }

    public boolean getCombineUpright() {
        return this.combineUpright;
    }

    public int getFontColor() {
        if (this.hasFontColor == true) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Font color not defined");
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

    public int getRubyPosition() {
        return this.rubyPosition;
    }

    public int getSpecificityScore(String r4, String r5, Set<String> r6, String r7) {
        if (this.targetId.isEmpty() == true) goto L5;
    L12:
        int r42 = updateScoreForMatch(updateScoreForMatch(updateScoreForMatch(0, this.targetId, r4, TLObject.FLAG_30), this.targetTag, r5, 2), this.targetVoice, r7, 4);
        if (r42 != (-1)) goto L15;
    L19:
        return 0;
    L15:
        if (r6.containsAll(this.targetClasses) == false) goto L19;
        return r42 + (this.targetClasses.size() * 4);
    L5:
        if (this.targetTag.isEmpty() == false) goto L12;
        if (this.targetClasses.isEmpty() == false) goto L12;
        if (this.targetVoice.isEmpty() == false) goto L12;
        return TextUtils.isEmpty(r5) ? 1 : 0;
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

    public WebvttCssStyle setBackgroundColor(int r1) {
        this.backgroundColor = r1;
        this.hasBackgroundColor = true;
        return this;
    }

    public WebvttCssStyle setBold(boolean r1) {
        this.bold = r1 ? 1 : 0;
        return this;
    }

    public WebvttCssStyle setCombineUpright(boolean r1) {
        this.combineUpright = r1;
        return this;
    }

    public WebvttCssStyle setFontColor(int r1) {
        this.fontColor = r1;
        this.hasFontColor = true;
        return this;
    }

    public WebvttCssStyle setFontFamily(String r1) {
        if (r1 != null) goto L4;
        String r12 = null;
    L5:
        this.fontFamily = r12;
        return this;
    L4:
        r12 = Ascii.toLowerCase(r1);
        goto L5
    }

    public WebvttCssStyle setFontSize(float r1) {
        this.fontSize = r1;
        return this;
    }

    public WebvttCssStyle setFontSizeUnit(int r1) {
        this.fontSizeUnit = r1;
        return this;
    }

    public WebvttCssStyle setItalic(boolean r1) {
        this.italic = r1 ? 1 : 0;
        return this;
    }

    public WebvttCssStyle setRubyPosition(int r1) {
        this.rubyPosition = r1;
        return this;
    }

    public void setTargetClasses(String[] r2) {
        this.targetClasses = new HashSet(Arrays.asList(r2));
    }

    public void setTargetId(String r1) {
        this.targetId = r1;
    }

    public void setTargetTagName(String r1) {
        this.targetTag = r1;
    }

    public void setTargetVoice(String r1) {
        this.targetVoice = r1;
    }

    public WebvttCssStyle setUnderline(boolean r1) {
        this.underline = r1 ? 1 : 0;
        return this;
    }
}
