package org.telegram.ui.Charts.data;

import android.graphics.Color;
import android.text.TextUtils;
import androidx.core.graphics.ColorUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.time.DurationKt;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.messenger.SegmentTree;
import org.telegram.ui.ActionBar.ThemeColors;

/* loaded from: classes6.dex */
public class ChartData {
    public String[] daysLookup;
    public ArrayList<Line> lines;
    public long maxValue;
    public long minValue;
    public float oneDayPercentage;
    protected long timeStep;
    public long[] x;
    public float[] xPercentage;
    public int xTickFormatter;
    public int xTooltipFormatter;
    public float yRate;
    public int yTickFormatter;
    public int yTooltipFormatter;

    public class Line {
        public int color;
        public int colorDark;
        public int colorKey;
        public String id;
        public long maxValue;
        public long minValue;
        public String name;
        public SegmentTree segmentTree;
        final /* synthetic */ ChartData this$0;
        public long[] y;

        public Line(ChartData r3) {
            this.this$0 = r3;
            this.maxValue = 0;
            this.minValue = LongCompanionObject.MAX_VALUE;
            this.color = -16777216;
            this.colorDark = -1;
        }
    }

    public ChartData() {
        this.lines = new ArrayList();
        this.maxValue = 0;
        this.minValue = LongCompanionObject.MAX_VALUE;
        this.oneDayPercentage = 0.0f;
        this.xTickFormatter = 0;
        this.xTooltipFormatter = 0;
        this.yRate = 0.0f;
        this.yTickFormatter = 0;
        this.yTooltipFormatter = 0;
    }

    public ChartData(JSONObject r13) {
        this.lines = new ArrayList();
        this.maxValue = 0;
        this.minValue = LongCompanionObject.MAX_VALUE;
        this.oneDayPercentage = 0.0f;
        int r1 = 0;
        this.xTickFormatter = 0;
        this.xTooltipFormatter = 0;
        this.yRate = 0.0f;
        this.yTickFormatter = 0;
        this.yTooltipFormatter = 0;
        JSONArray r0 = r13.getJSONArray("columns");
        r0.length();
        int r2 = 0;
    L4:
        if (r2 >= r0.length()) goto L24;
        JSONArray r3 = r0.getJSONArray(r2);
        if (r3.getString(0).equals("x") == false) goto L10;
        int r5 = r3.length() - 1;
        this.x = new long[r5];
        int r6 = 0;
    L8:
        if (r6 >= r5) goto L19;
        int r8 = r6 + 1;
        this.x[r6] = r3.getLong(r8);
        r6 = r8;
    L19:
        long[] r32 = this.x;
        if (r32.length <= 1) goto L22;
        this.timeStep = r32[1] - r32[0];
    L23:
        measure();
        r2 = r2 + 1;
        goto L4
    L22:
        this.timeStep = DurationKt.MILLIS_IN_DAY;
        goto L23
    L10:
        Line r52 = new Line(this);
        this.lines.add(r52);
        int r62 = r3.length() - 1;
        r52.id = r3.getString(0);
        r52.y = new long[r62];
        int r7 = 0;
    L11:
        if (r7 >= r62) goto L19;
        int r9 = r7 + 1;
        r52.y[r7] = r3.getLong(r9);
        long r72 = r52.y[r7];
        if (r72 <= r52.maxValue) goto L16;
        r52.maxValue = r72;
    L16:
        if (r72 >= r52.minValue) goto L18;
        r52.minValue = r72;
    L18:
        r7 = r9;
        goto L11
    L24:
        JSONObject r02 = r13.optJSONObject("colors");
        JSONObject r22 = r13.optJSONObject("names");
        this.xTickFormatter = getFormatter(r13.getString("xTickFormatter"));     // Catch: Exception -> L41
        this.yTickFormatter = getFormatter(r13.getString("yTickFormatter"));     // Catch: Exception -> L41
        this.xTooltipFormatter = getFormatter(r13.getString("xTooltipFormatter"));     // Catch: Exception -> L41
        this.yTooltipFormatter = getFormatter(r13.getString("yTooltipFormatter"));     // Catch: Exception -> L41
    L26:
        Pattern r132 = Pattern.compile("(.*)(#.*)");
    L28:
        if (r1 >= this.lines.size()) goto L40;
        Line r33 = this.lines.get(r1);
        if (r02 == null) goto L37;
        Matcher r53 = r132.matcher(r02.getString(r33.id));
        if (r53.matches() == false) goto L37;
        if (TextUtils.isEmpty(r53.group(1)) == true) goto L36;
        r33.colorKey = ThemeColors.stringKeyToInt("statisticChartLine_" + r53.group(1).toLowerCase());
    L36:
        int r54 = Color.parseColor(r53.group(2));
        r33.color = r54;
        r33.colorDark = ColorUtils.blendARGB(-1, r54, 0.85f);
    L37:
        if (r22 == null) goto L39;
        r33.name = r22.getString(r33.id);
    L39:
        r1 = r1 + 1;
        goto L28
    }

    public int findEndIndex(int r7, float r8) {
        int r0 = this.xPercentage.length;
        if (r8 == 1.0f) goto L5;
        int r02 = r0 - 1;
        int r1 = r02;
    L7:
        if (r7 > r1) goto L23;
        int r2 = (r1 + r7) >> 1;
        float[] r3 = this.xPercentage;
        float r4 = r3[r2];
        if (r8 <= r4) goto L15;
        if (r2 == r02) goto L16;
        if (r8 >= r3[r2 + 1]) goto L15;
    L16:
        return r2;
    L15:
        if (r8 == r4) goto L16;
        if (r8 < r4) goto L19;
        if (r8 <= r4) goto L7;
        r7 = r2 + 1;
        goto L7
    L19:
        r1 = r2 - 1;
        goto L7
    L23:
        return r1;
    L5:
        return r0 - 1;
    }

    public int findIndex(int r6, int r7, float r8) {
        float[] r0 = this.xPercentage;
        int r1 = r0.length;
        if (r8 > r0[r6]) goto L6;
        return r6;
    L6:
        if (r8 < r0[r7]) goto L8;
        return r7;
    L8:
        if (r6 > r7) goto L25;
        int r02 = (r7 + r6) >> 1;
        float[] r2 = this.xPercentage;
        float r3 = r2[r02];
        if (r8 <= r3) goto L17;
        if (r02 == (r1 - 1)) goto L18;
        if (r8 >= r2[r02 + 1]) goto L17;
    L18:
        return r02;
    L17:
        if (r8 == r3) goto L18;
        if (r8 < r3) goto L21;
        if (r8 <= r3) goto L8;
        r6 = r02 + 1;
        goto L8
    L21:
        r7 = r02 - 1;
        goto L8
    L25:
        return r7;
    }

    public int findStartIndex(float r7) {
        int r1 = 0;
        if (r7 != 0.0f) goto L5;
        return 0;
    L5:
        int r0 = this.xPercentage.length;
        if (r0 >= 2) goto L8;
        return 0;
    L8:
        int r02 = r0 - 1;
    L9:
        if (r1 > r02) goto L25;
        int r2 = (r02 + r1) >> 1;
        float[] r3 = this.xPercentage;
        float r4 = r3[r2];
        if (r7 >= r4) goto L17;
        if (r2 == 0) goto L18;
        if (r7 <= r3[r2 - 1]) goto L17;
    L18:
        return r2;
    L17:
        if (r7 == r4) goto L18;
        if (r7 < r4) goto L21;
        if (r7 <= r4) goto L9;
        r1 = r2 + 1;
        goto L9
    L21:
        r02 = r2 - 1;
        goto L9
    L25:
        return r1;
    }

    public String getDayString(int r7) {
        String[] r0 = this.daysLookup;
        long[] r1 = this.x;
        return r0[(int) ((r1[r7] - r1[0]) / this.timeStep)];
    }

    public int getFormatter(String r2) {
        if (TextUtils.isEmpty(r2) == false) goto L6;
        return 0;
    L6:
        if (r2.contains("TON") == false) goto L10;
        return 1;
    L10:
        if (r2.contains("XTR") == false) goto L13;
        return 2;
    L13:
        return 0;
    }

    public void measure() {
        long[] r0 = this.x;
        int r1 = r0.length;
        if (r1 != 0) goto L5;
        return;
    L5:
        long r3 = r0[0];
        long r5 = r0[r1 - 1];
        float[] r02 = new float[r1];
        this.xPercentage = r02;
        if (r1 != 1) goto L8;
        r02[0] = 1.0f;
    L11:
        int r03 = 0;
    L13:
        if (r03 >= this.lines.size()) goto L21;
        if (this.lines.get(r03).maxValue <= this.maxValue) goto L18;
        this.maxValue = this.lines.get(r03).maxValue;
    L18:
        if (this.lines.get(r03).minValue >= this.minValue) goto L20;
        this.minValue = this.lines.get(r03).minValue;
    L20:
        this.lines.get(r03).segmentTree = new SegmentTree(this.lines.get(r03).y);
        r03 = r03 + 1;
        goto L13
    L21:
        long r04 = this.timeStep;
        this.daysLookup = new String[((int) ((r5 - r3) / r04)) + 10];
        if (r04 != 1) goto L25;
        SimpleDateFormat r05 = null;
    L28:
        int r12 = 0;
    L29:
        String[] r8 = this.daysLookup;
        int r9 = r8.length;
        long r10 = this.timeStep;
        if (r12 >= r9) goto L36;
        if (r10 != 1) goto L34;
        r8[r12] = String.format(Locale.ENGLISH, "%02d:00", new Object[]{Integer.valueOf(r12)});
    L35:
        r12 = r12 + 1;
        goto L29
    L34:
        r8[r12] = r05.format(new Date((r12 * this.timeStep) + r3));
        goto L35
    L36:
        long[] r13 = this.x;
        this.oneDayPercentage = r10 / (r13[r13.length - 1] - r13[0]);
        return;
    L25:
        if (r04 >= DurationKt.MILLIS_IN_DAY) goto L27;
        r05 = new SimpleDateFormat("HH:mm");
        goto L28
    L27:
        r05 = new SimpleDateFormat("MMM d");
        goto L28
    L8:
        int r06 = 0;
    L9:
        if (r06 >= r1) goto L11;
        this.xPercentage[r06] = (this.x[r06] - r3) / (r5 - r3);
        r06 = r06 + 1;
        goto L9
    }
}
