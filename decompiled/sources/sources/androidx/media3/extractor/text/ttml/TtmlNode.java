package androidx.media3.extractor.text.ttml;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableStringBuilder;
import android.util.Base64;
import android.util.Pair;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
final class TtmlNode {
    private List<TtmlNode> children;
    public final long endTimeUs;
    public final String imageId;
    public final boolean isTextNode;
    private final HashMap<String, Integer> nodeEndsByRegion;
    private final HashMap<String, Integer> nodeStartsByRegion;
    public final TtmlNode parent;
    public final String regionId;
    public final long startTimeUs;
    public final TtmlStyle style;
    private final String[] styleIds;
    public final String tag;
    public final String text;

    private TtmlNode(String r1, String r2, long r3, long r5, TtmlStyle r7, String[] r8, String r9, String r10, TtmlNode r11) {
        this.tag = r1;
        this.text = r2;
        this.imageId = r10;
        this.style = r7;
        this.styleIds = r8;
        if (r2 == null) goto L5;
        boolean r12 = true;
    L6:
        this.isTextNode = r12;
        this.startTimeUs = r3;
        this.endTimeUs = r5;
        this.regionId = (String) Assertions.checkNotNull(r9);
        this.parent = r11;
        this.nodeStartsByRegion = new HashMap();
        this.nodeEndsByRegion = new HashMap();
        return;
    L5:
        r12 = false;
        goto L6
    }

    private void applyStyleToOutput(Map<String, TtmlStyle> r10, Cue.Builder r11, int r12, int r13, int r14) {
        TtmlStyle r5 = TtmlRenderUtil.resolveStyle(this.style, this.styleIds, r10);
        SpannableStringBuilder r0 = (SpannableStringBuilder) r11.getText();
        if (r0 != null) goto L5;
        r0 = new SpannableStringBuilder();
        r11.setText(r0);
    L5:
        SpannableStringBuilder r2 = r0;
        if (r5 == null) goto L19;
        TtmlRenderUtil.applyStylesToSpan(r2, r12, r13, r5, this.parent, r10, r14);
        if ("p".equals(this.tag) == true) goto L10;
        return;
    L10:
        if (r5.getShearPercentage() == Float.MAX_VALUE) goto L13;
        r11.setShearDegrees((r5.getShearPercentage() * (-90.0f)) / 100.0f);
    L13:
        if (r5.getTextAlign() == null) goto L16;
        r11.setTextAlignment(r5.getTextAlign());
    L16:
        if (r5.getMultiRowAlign() == null) goto L21;
        r11.setMultiRowAlignment(r5.getMultiRowAlign());
        return;
    L21:
        return;
    }

    public static TtmlNode buildNode(String r12, long r13, long r15, TtmlStyle r17, String[] r18, String r19, String r20, TtmlNode r21) {
        return new TtmlNode(r12, null, r13, r15, r17, r18, r19, r20, r21);
    }

    public static TtmlNode buildTextNode(String r12) {
        return new TtmlNode(null, TtmlRenderUtil.applyTextElementSpacePolicy(r12), -9223372036854775807L, -9223372036854775807L, null, null, _UrlKt.FRAGMENT_ENCODE_SET, null, null);
    }

    private static void cleanUpText(SpannableStringBuilder r7) {
        int r2 = 0;
        DeleteTextSpan[] r0 = (DeleteTextSpan[]) r7.getSpans(0, r7.length(), DeleteTextSpan.class);
        int r1 = r0.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L5;
        DeleteTextSpan r4 = r0[r3];
        r7.replace(r7.getSpanStart(r4), r7.getSpanEnd(r4), _UrlKt.FRAGMENT_ENCODE_SET);
        r3 = r3 + 1;
        goto L3
    L5:
        int r02 = 0;
    L7:
        if (r02 >= r7.length()) goto L21;
        if (r7.charAt(r02) != ' ') goto L19;
        int r12 = r02 + 1;
        int r42 = r12;
    L12:
        if (r42 >= r7.length()) goto L16;
        if (r7.charAt(r42) != ' ') goto L16;
        r42 = r42 + 1;
    L16:
        int r43 = r42 - r12;
        if (r43 <= 0) goto L19;
        r7.delete(r02, r43 + r02);
    L19:
        r02 = r02 + 1;
        goto L7
    L21:
        if (r7.length() > 0) goto L23;
    L25:
        int r03 = 0;
    L27:
        if (r03 >= (r7.length() - 1)) goto L35;
        if (r7.charAt(r03) != '\n') goto L33;
        int r44 = r03 + 1;
        if (r7.charAt(r44) != ' ') goto L33;
        r7.delete(r44, r03 + 2);
    L33:
        r03 = r03 + 1;
        goto L27
    L35:
        if (r7.length() <= 0) goto L40;
        if (r7.charAt(r7.length() - 1) != ' ') goto L40;
        r7.delete(r7.length() - 1, r7.length());
    L40:
        if (r2 >= (r7.length() - 1)) goto L48;
        if (r7.charAt(r2) != ' ') goto L46;
        int r04 = r2 + 1;
        if (r7.charAt(r04) != '\n') goto L46;
        r7.delete(r2, r04);
    L46:
        r2 = r2 + 1;
        goto L40
    L48:
        if (r7.length() > 0) goto L50;
        return;
    L50:
        if (r7.charAt(r7.length() - 1) != '\n') goto L70;
        r7.delete(r7.length() - 1, r7.length());
        return;
    L70:
        return;
    L23:
        if (r7.charAt(0) != ' ') goto L25;
        r7.delete(0, 1);
        goto L25
    }

    private void getEventTimes(TreeSet<Long> r7, boolean r8) {
        boolean r0 = "p".equals(this.tag);
        boolean r1 = "div".equals(this.tag);
        if (r8 == true) goto L8;
        if (r0 == true) goto L8;
        if (r1 == false) goto L15;
        if (this.imageId != null) goto L8;
    L15:
        if (this.children == null) goto L27;
        int r2 = 0;
    L19:
        if (r2 >= this.children.size()) goto L31;
        TtmlNode r3 = this.children.get(r2);
        if (r8 == true) goto L25;
        if (r0 == true) goto L25;
        boolean r4 = false;
    L26:
        r3.getEventTimes(r7, r4);
        r2 = r2 + 1;
    L25:
        r4 = true;
        goto L26
    L31:
        return;
    L27:
        return;
    L8:
        long r12 = this.startTimeUs;
        if (r12 == (-9223372036854775807L)) goto L11;
        r7.add(Long.valueOf(r12));
    L11:
        long r13 = this.endTimeUs;
        if (r13 == (-9223372036854775807L)) goto L15;
        r7.add(Long.valueOf(r13));
        goto L15
    }

    private static SpannableStringBuilder getRegionOutputText(String r2, Map<String, Cue.Builder> r3) {
        if (r3.containsKey(r2) == true) goto L6;
        Cue.Builder r0 = new Cue.Builder();
        r0.setText(new SpannableStringBuilder());
        r3.put(r2, r0);
    L6:
        return (SpannableStringBuilder) Assertions.checkNotNull(r3.get(r2).getText());
    }

    private void traverseForImage(long r3, String r5, List<Pair<String, String>> r6) {
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(this.regionId) == true) goto L7;
        r5 = this.regionId;
    L7:
        if (isActive(r3) == true) goto L9;
    L14:
        int r0 = 0;
    L16:
        if (r0 >= getChildCount()) goto L18;
        getChild(r0).traverseForImage(r3, r5, r6);
        r0 = r0 + 1;
        goto L16
    L18:
        return;
    L9:
        if ("div".equals(this.tag) == false) goto L14;
        if (this.imageId == null) goto L14;
        r6.add(new Pair(r5, this.imageId));
    }

    private void traverseForStyle(long r10, Map<String, TtmlStyle> r12, Map<String, TtmlRegion> r13, String r14, Map<String, Cue.Builder> r15) {
        if (isActive(r10) == true) goto L6;
        return;
    L6:
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(this.regionId) == false) goto L8;
        String r7 = r14;
    L9:
        Iterator<Map.Entry<String, Integer>> r6 = this.nodeEndsByRegion.entrySet().iterator();
    L10:
        int r2 = 0;
        if (r6.hasNext() == false) goto L18;
        Map.Entry<String, Integer> r1 = r6.next();
        String r3 = r1.getKey();
        if (this.nodeStartsByRegion.containsKey(r3) == false) goto L15;
        r2 = this.nodeStartsByRegion.get(r3).intValue();
    L15:
        int r4 = r1.getValue().intValue();
        if (r2 == r4) goto L10;
        applyStyleToOutput(r12, (Cue.Builder) Assertions.checkNotNull(r15.get(r3)), r2, r4, ((TtmlRegion) Assertions.checkNotNull(r13.get(r7))).verticalType);
        goto L10
    L18:
        int r16 = 0;
    L20:
        if (r16 >= getChildCount()) goto L29;
        getChild(r16).traverseForStyle(r10, r12, r13, r7, r15);
        r16 = r16 + 1;
        goto L20
    L29:
        return;
    L8:
        r7 = this.regionId;
        goto L9
    }

    private void traverseForText(long r9, boolean r11, String r12, Map<String, Cue.Builder> r13) {
        this.nodeStartsByRegion.clear();
        this.nodeEndsByRegion.clear();
        if ("metadata".equals(this.tag) == false) goto L6;
        return;
    L6:
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(this.regionId) == false) goto L8;
    L7:
        String r4 = r12;
        if (this.isTextNode == false) goto L15;
        if (r11 == false) goto L15;
        getRegionOutputText(r4, r13).append((CharSequence) Assertions.checkNotNull(this.text));
        return;
    L15:
        if ("br".equals(this.tag) == false) goto L20;
        if (r11 == false) goto L20;
        getRegionOutputText(r4, r13).append('\n');
        return;
    L20:
        if (isActive(r9) == false) goto L42;
        Iterator<Map.Entry<String, Cue.Builder>> r122 = r13.entrySet().iterator();
    L23:
        if (r122.hasNext() == false) goto L25;
        Map.Entry<String, Cue.Builder> r0 = r122.next();
        this.nodeStartsByRegion.put(r0.getKey(), Integer.valueOf(((CharSequence) Assertions.checkNotNull(r0.getValue().getText())).length()));
        goto L23
    L25:
        boolean r123 = "p".equals(this.tag);
        int r7 = 0;
    L27:
        if (r7 >= getChildCount()) goto L35;
        TtmlNode r02 = getChild(r7);
        if (r11 == true) goto L33;
        if (r123 == true) goto L33;
        boolean r3 = false;
        long r1 = r9;
        Map<String, Cue.Builder> r5 = r13;
    L34:
        r02.traverseForText(r1, r3, r4, r5);
        r7 = r7 + 1;
        r9 = r1;
        r13 = r5;
    L33:
        r3 = true;
        r5 = r13;
        r1 = r9;
        goto L34
    L35:
        Map<String, Cue.Builder> r52 = r13;
        if (r123 == false) goto L38;
        TtmlRenderUtil.endParagraph(getRegionOutputText(r4, r52));
    L38:
        Iterator<Map.Entry<String, Cue.Builder>> r92 = r52.entrySet().iterator();
    L40:
        if (r92.hasNext() == false) goto L49;
        Map.Entry<String, Cue.Builder> r10 = r92.next();
        this.nodeEndsByRegion.put(r10.getKey(), Integer.valueOf(((CharSequence) Assertions.checkNotNull(r10.getValue().getText())).length()));
        goto L40
    L49:
        return;
    L42:
        return;
    L8:
        r12 = this.regionId;
        goto L7
    }

    public void addChild(TtmlNode r2) {
        if (this.children != null) goto L5;
        this.children = new ArrayList();
    L5:
        this.children.add(r2);
    }

    public TtmlNode getChild(int r1) {
        List<TtmlNode> r0 = this.children;
        if (r0 == null) goto L7;
        return r0.get(r1);
    L7:
        throw new IndexOutOfBoundsException();
    }

    public int getChildCount() {
        List<TtmlNode> r0 = this.children;
        if (r0 != null) goto L7;
        return 0;
    L7:
        return r0.size();
    }

    public List<Cue> getCues(long r10, Map<String, TtmlStyle> r12, Map<String, TtmlRegion> r13, Map<String, String> r14) {
        ArrayList r0 = new ArrayList();
        traverseForImage(r10, this.regionId, r0);
        TreeMap r7 = new TreeMap();
        traverseForText(r10, false, this.regionId, r7);
        traverseForStyle(r10, r12, r13, this.regionId, r7);
        ArrayList r9 = new ArrayList();
        int r102 = r0.size();
        int r122 = 0;
    L3:
        if (r122 >= r102) goto L8;
        Object r132 = r0.get(r122);
        r122 = r122 + 1;
        Pair r133 = (Pair) r132;
        String r1 = r14.get(r133.second);
        if (r1 == null) goto L3;
        byte[] r15 = Base64.decode(r1, 0);
        Bitmap r16 = BitmapFactory.decodeByteArray(r15, 0, r15.length);
        TtmlRegion r134 = (TtmlRegion) Assertions.checkNotNull(r13.get(r133.first));
        r9.add(new Cue.Builder().setBitmap(r16).setPosition(r134.position).setPositionAnchor(0).setLine(r134.line, 0).setLineAnchor(r134.lineAnchor).setSize(r134.width).setBitmapHeight(r134.height).setVerticalType(r134.verticalType).build());
        goto L3
    L8:
        Iterator r103 = r7.entrySet().iterator();
    L10:
        if (r103.hasNext() == false) goto L12;
        Map.Entry r11 = (Map.Entry) r103.next();
        TtmlRegion r123 = (TtmlRegion) Assertions.checkNotNull(r13.get(r11.getKey()));
        Cue.Builder r112 = (Cue.Builder) r11.getValue();
        cleanUpText((SpannableStringBuilder) Assertions.checkNotNull(r112.getText()));
        r112.setLine(r123.line, r123.lineType);
        r112.setLineAnchor(r123.lineAnchor);
        r112.setPosition(r123.position);
        r112.setSize(r123.width);
        r112.setTextSize(r123.textSize, r123.textSizeType);
        r112.setVerticalType(r123.verticalType);
        r9.add(r112.build());
        goto L10
    L12:
        return r9;
    }

    public long[] getEventTimesUs() {
        TreeSet<Long> r0 = new TreeSet();
        int r1 = 0;
        getEventTimes(r0, false);
        long[] r5 = new long[r0.size()];
        Iterator<Long> r02 = r0.iterator();
    L4:
        if (r02.hasNext() == false) goto L6;
        r5[r1] = r02.next().longValue();
        r1 = r1 + 1;
        goto L4
    L6:
        return r5;
    }

    public String[] getStyleIds() {
        return this.styleIds;
    }

    public boolean isActive(long r7) {
        long r0 = this.startTimeUs;
        if (r0 != (-9223372036854775807L)) goto L7;
        if (this.endTimeUs != (-9223372036854775807L)) goto L7;
        return true;
    L7:
        if (r0 > r7) goto L11;
        if (this.endTimeUs != (-9223372036854775807L)) goto L11;
        return true;
    L11:
        if (r0 != (-9223372036854775807L)) goto L15;
        if (r7 >= this.endTimeUs) goto L15;
        return true;
    L15:
        if (r0 <= r7) goto L17;
        return false;
    L17:
        if (r7 >= this.endTimeUs) goto L25;
        return true;
    L25:
        return false;
    }
}
