package org.telegram.ui.Components.Paint;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Components.Paint.Brush;

/* loaded from: classes7.dex */
public class ShapeDetector {
    private static final double diagonal = 0.0d;
    private static final double halfDiagonal = 0.0d;
    private static DispatchQueue queue;
    private final int MIN_POINTS;
    private final long TIMEOUT;
    private AtomicBoolean busy;
    Context context;
    private Runnable detect;
    private boolean isLearning;
    private Utilities.Callback<Shape> onShapeDetected;
    private ArrayList<Point> points;
    SharedPreferences preferences;
    private AtomicBoolean scheduled;
    private boolean shapeDetected;
    private ArrayList<Template> templates;
    private int templatesUsageScore;
    private ArrayList<Point> toSave;

    public static class Point {
        public double x;
        public double y;

        public Point(double r1, double r3) {
            set(r1, r3);
        }

        public double distance(double r5, double r7) {
            return Math.sqrt(Math.pow(r5 - this.x, 2.0d) + Math.pow(r7 - this.y, 2.0d));
        }

        public double distance(Point r5) {
            return distance(r5.x, r5.y);
        }

        public void set(double r1, double r3) {
            this.x = r1;
            this.y = r3;
        }
    }

    public static class RectD {
        public double bottom;
        public double left;
        public double right;
        public double top;

        public RectD(double r1, double r3, double r5, double r7) {
            this.left = r1;
            this.top = r3;
            this.right = r5;
            this.bottom = r7;
        }

        public String toString() {
            return "RectD{left=" + this.left + ", top=" + this.top + ", right=" + this.right + ", bottom=" + this.bottom + '}';
        }

        public void union(double r3, double r5) {
            if (this.left < r3) goto L6;
            this.left = r3;
        L6:
            if (this.top < r5) goto L9;
            this.top = r5;
        L9:
            if (this.right > r3) goto L12;
            this.right = r3;
        L12:
            if (this.bottom > r5) goto L15;
            this.bottom = r5;
            return;
        }
    }

    public static class Template {
        public ArrayList<Point> points;
        public int score;
        public int shapeType;

        private Template() {
            this.points = new ArrayList();
        }

        public /* synthetic */ Template(ShapeDetectorIA r1) {
            this();
        }
    }

    /* renamed from: $r8$lambda$6bCdpnRGzYg5kgece_KXQOu-_Cg, reason: not valid java name */
    public static /* synthetic */ void m11407$r8$lambda$6bCdpnRGzYg5kgece_KXQOu_Cg(ShapeDetector r0) {
        r0.lambda$parseTemplates$0();
    }

    /* renamed from: $r8$lambda$GhGemyrUO-2oYWyrUur64DGhkRw, reason: not valid java name */
    public static /* synthetic */ void m11408$r8$lambda$GhGemyrUO2oYWyrUur64DGhkRw(ShapeDetector r0, ArrayList r1, DialogInterface r2, int r3) {
        r0.lambda$showSaveLearnDialog$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$GxA29YSQVZtnM7v_Ihhg7B9tQx0(ShapeDetector r0, Shape r1, int r2, ArrayList r3) {
        r0.lambda$new$1(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$NR3gy7T2KbmGppf_AoujZxiF-KM, reason: not valid java name */
    public static /* synthetic */ void m11409$r8$lambda$NR3gy7T2KbmGppf_AoujZxiFKM(ShapeDetector r0) {
        r0.lambda$new$2();
    }

    static {
        queue = new DispatchQueue("ShapeDetector");
        double r0 = Math.sqrt(125000.0d);
        diagonal = r0;
        halfDiagonal = r0 / 2.0d;
    }

    public ShapeDetector(Context r3, Utilities.Callback<Shape> r4) {
        this.MIN_POINTS = 8;
        this.TIMEOUT = 150;
        this.points = new ArrayList();
        this.templates = new ArrayList();
        this.toSave = null;
        this.busy = new AtomicBoolean(false);
        this.scheduled = new AtomicBoolean(false);
        this.detect = new ShapeDetector$$ExternalSyntheticLambda0(this);
        this.context = r3;
        this.onShapeDetected = r4;
        SharedPreferences r32 = r3.getSharedPreferences("shapedetector_conf", 0);
        this.preferences = r32;
        this.isLearning = r32.getBoolean("learning", false);
        this.templatesUsageScore = this.preferences.getInt("scoreall", 0);
        parseTemplates();
    }

    private RectD boundingBox(ArrayList<Point> r11) {
        if (r11.size() > 0) goto L6;
        return null;
    L6:
        double r2 = r11.get(0).x;
        double r4 = r11.get(0).y;
        RectD r1 = new RectD(r2, r4, r2, r4);
        int r10 = 1;
    L8:
        if (r10 >= r11.size()) goto L10;
        Point r0 = r11.get(r10);
        r1.union(r0.x, r0.y);
        r10 = r10 + 1;
        goto L8
    L10:
        return r1;
    }

    private Point centroid(ArrayList<Point> r7) {
        Point r6 = new Point(0.0d, 0.0d);
        int r0 = 0;
    L4:
        if (r0 >= r7.size()) goto L6;
        Point r1 = r7.get(r0);
        r6.x += r1.x;
        r6.y += r1.y;
        r0 = r0 + 1;
        goto L4
    L6:
        r6.x /= r7.size();
        r6.y /= r7.size();
        return r6;
    }

    private Shape constructShape(int r11, ArrayList<Point> r12) {
        if (r11 >= 0) goto L5;
    L25:
        return null;
    L5:
        if (r11 >= Brush.Shape.SHAPES_LIST.size()) goto L25;
        if (r12.size() < 1) goto L25;
        Shape r1 = new Shape(Brush.Shape.make(r11));
        if (r11 != 4) goto L19;
        int r10 = findAnglePoint(r12);
        if (r10 > 0) goto L14;
        return null;
    L14:
        if (r10 <= 10) goto L16;
        r10 = r10 - 2;
    L16:
        Point r112 = r12.get(r10);
        Point r102 = r12.get(r10 / 2);
        Point r122 = r12.get(0);
        r1.centerX = (float) r112.x;
        r1.centerY = (float) r112.y;
        r1.middleX = (float) r102.x;
        r1.middleY = (float) r102.y;
        r1.radiusX = (float) r122.x;
        r1.radiusY = (float) r122.y;
        r1.arrowTriangleLength = 16.0f;
        return r1;
    L19:
        Point r0 = centroid(r12);
        r1.centerX = (float) r0.x;
        r1.centerY = (float) r0.y;
        RectD r02 = boundingBox(r12);
        r1.radiusX = ((float) (r02.right - r02.left)) / 2.0f;
        r1.radiusY = ((float) (r02.bottom - r02.top)) / 2.0f;
        if (r11 != 2) goto L24;
        int r103 = findAnglePoint(r12, 1);
        if (r103 <= 0) goto L24;
        Point r104 = r12.get(r103);
        r1.rotation = (float) Math.atan2(r104.y - r1.centerY, r104.x - r1.centerX);
    L24:
        return r1;
    }

    private double distanceAtAngle(ArrayList<Point> r24, Point r25, ArrayList<Point> r26, double r27) {
        Point r0 = r25;
        double r1 = Math.cos(r27);
        double r3 = Math.sin(r27);
        int r5 = Math.min(r24.size(), r26.size());
        double r6 = 0.0d;
        int r8 = 0;
    L3:
        if (r8 >= r5) goto L6;
        Point r10 = r24.get(r8);
        Point r12 = r26.get(r8);
        double r13 = r10.x;
        double r15 = r1;
        double r14 = r0.x;
        double r17 = (r13 - r14) * r15;
        double r16 = r10.y;
        double r18 = r0.y;
        r6 = r6 + r12.distance((r17 - ((r16 - r18) * r3)) + r14, (((r13 - r14) * r3) + ((r16 - r18) * r15)) + r18);
        r8 = r8 + 1;
        r0 = r25;
        r1 = r15;
        goto L3
    L6:
        return r6 / r24.size();
    }

    private double distanceAtBestAngle(ArrayList<Point> r21, Point r22, ArrayList<Point> r23, double r24, double r26, double r28) {
        double r0 = (Math.sqrt(5.0d) - 1.0d) * 0.5d;
        double r4 = 1.0d - r0;
        double r12 = (r0 * r24) + (r4 * r26);
        double r10 = (r4 * r24) + (r0 * r26);
        double r6 = distanceAtAngle(r21, r22, r23, r12);
        double r16 = r10;
        double r8 = distanceAtAngle(r21, r22, r23, r10);
        double r2 = r24;
        double r14 = r26;
    L4:
        if (Math.abs(r14 - r2) <= r28) goto L10;
        if (r6 < r8) goto L7;
        double r102 = (r4 * r12) + (r0 * r14);
        r6 = r8;
        r2 = r12;
        r12 = r16;
        r8 = distanceAtAngle(r21, r22, r23, r102);
        r16 = r102;
        goto L4
    L7:
        double r103 = (r4 * r16) + (r0 * r2);
        r8 = r6;
        r14 = r16;
        r6 = distanceAtAngle(r21, r22, r23, r103);
        r16 = r12;
        r12 = r103;
        goto L4
    L10:
        return Math.min(r6, r8);
    }

    private int findAnglePoint(ArrayList<Point> r2) {
        return findAnglePoint(r2, 0);
    }

    private int findAnglePoint(ArrayList<Point> r14, int r15) {
        int r13 = Math.max(1, r14.size() / 4);
    L4:
        if (r13 >= (r14.size() - 1)) goto L11;
        Point r1 = r14.get(r13 - 1);
        Point r2 = r14.get(r13);
        int r3 = r13 + 1;
        Point r4 = r14.get(r3);
        double r5 = r1.distance(r2);
        double r7 = r1.distance(r4);
        double r12 = r2.distance(r4);
        if (((Math.acos((((r5 * r5) + (r7 * r7)) - (r12 * r12)) / ((r5 * 2.0d) * r7)) / 3.141592653589793d) * 180.0d) <= 18.0d) goto L10;
        if (r15 <= 0) goto L9;
        r15 = r15 - 1;
        goto L10
    L9:
        return r13;
    L10:
        r13 = r3;
        goto L4
    L11:
        return -1;
    }

    private ArrayList<Point> fullClone(ArrayList<Point> r8) {
        ArrayList<Point> r7 = new ArrayList();
        int r0 = 0;
    L4:
        if (r0 >= r8.size()) goto L6;
        Point r1 = r8.get(r0);
        r7.add(new Point(r1.x, r1.y));
        r0 = r0 + 1;
        goto L4
    L6:
        return r7;
    }

    private double indicativeAngle(ArrayList<Point> r6) {
        Point r5 = centroid(r6);
        return Math.atan2(r5.y - r6.get(0).y, r5.x - r6.get(0).x);
    }

    public static boolean isLearning(Context r2) {
        return r2.getSharedPreferences("shapedetector_conf", 0).getBoolean("learning", false);
    }

    private /* synthetic */ void lambda$new$1(Shape r3, int r4, ArrayList r5) {
        if (r3 == null) goto L5;
        boolean r1 = true;
    L6:
        this.shapeDetected = r1;
        if (r1 == false) goto L12;
        if (r4 < 0) goto L12;
        if (r4 >= this.templates.size()) goto L12;
        this.templatesUsageScore++;
        this.templates.get(r4).score++;
        this.preferences.edit().putInt("score" + r4, this.templates.get(r4).score).putInt("scoreall", this.templatesUsageScore).apply();
        this.toSave = null;
    L13:
        this.onShapeDetected.run(r3);
        return;
    L12:
        this.toSave = r5;
        goto L13
    L5:
        r1 = false;
        goto L6
    }

    private /* synthetic */ void lambda$new$2() {
        if (this.busy.get() == false) goto L5;
        return;
    L5:
        this.scheduled.set(false);
        this.busy.set(true);
        long r12 = System.currentTimeMillis();
        monitor-enter(this);
    L12:
        th = move-exception;
        throw th;
    L8:
        if (this.points.size() >= 8) goto L14;
        this.busy.set(false);     // Catch: Throwable -> L12
        monitor-exit(this);     // Catch: Throwable -> L12
        return;
    L14:
        ArrayList<Point> r0 = fullClone(this.points);     // Catch: Throwable -> L12
        monitor-exit(this);     // Catch: Throwable -> L12
        ArrayList<Point> r02 = resample(r0, 48);
        ArrayList<Point> r2 = fullClone(r02);
        rotate(r2, indicativeAngle(r2));
        Point r3 = centroid(r2);
        translate(r2, -r3.x, -r3.y);
        scale(r2, 250.0d);
        Point r32 = centroid(r2);
        double r15 = Double.MAX_VALUE;
        int r4 = 0;
        int r5 = -1;
        int r17 = -1;
    L18:
        if (r4 >= this.templates.size()) goto L24;
        int r19 = r4;
        int r18 = r5;
        double r42 = distanceAtBestAngle(r2, r32, this.templates.get(r4).points, -1.5707963267948966d, 1.5707963267948966d, 0.06981317007977318d);
        if (r42 >= r15) goto L22;
        r15 = r42;
        r17 = this.templates.get(r19).shapeType;
        r5 = r19;
    L23:
        r4 = r19 + 1;
        goto L18
    L22:
        r5 = r18;
        goto L23
    L24:
        int r11 = r5;
        if ((1.0d - (r15 / halfDiagonal)) >= 0.8d) goto L27;
        int r14 = -1;
    L28:
        Shape r03 = constructShape(r14, r02);
        if (BuildVars.LOGS_ENABLED == false) goto L39;
        StringBuilder r43 = new StringBuilder("took ");
        r43.append(System.currentTimeMillis() - r12);
        r43.append("ms to ");
        if (r03 == null) goto L33;
        String r52 = _UrlKt.FRAGMENT_ENCODE_SET;
    L34:
        r43.append(r52);
        r43.append("detect a shape");
        if (r03 == null) goto L37;
        String r53 = " (template#" + r11 + " shape#" + r14 + ")";
    L38:
        r43.append(r53);
        Log.i("shapedetector", r43.toString());
        goto L39
    L37:
        r53 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L38
    L33:
        r52 = "not ";
    L39:
        AndroidUtilities.runOnUIThread(new ShapeDetector$$ExternalSyntheticLambda3(this, r03, r11, r2));
        this.busy.set(false);
        return;
    L27:
        r14 = r17;
        goto L28
    }

    private /* synthetic */ void lambda$parseTemplates$0() {
        InputStream r1 = ApplicationLoader.applicationContext.getAssets().open("shapes.dat");     // Catch: Exception -> L31
    L5:
        ShapeDetectorIA r5 = null;
        char r6 = 0;
        if (r1.available() <= 5) goto L14;
        Template r2 = new Template(null);     // Catch: Exception -> L31
        r2.shapeType = r1.read();     // Catch: Exception -> L31
        int r3 = r1.read();     // Catch: Exception -> L31
        int r7 = r1.read() - 64;
        int r8 = r1.read() - 64;
        if (r1.available() < (r3 * 2)) goto L14;
        int r52 = 0;
    L10:
        if (r52 >= r3) goto L12;
        r2.points.add(new Point((r1.read() - r7) - 127, (r1.read() - r8) - 127));     // Catch: Exception -> L31
        r52 = r52 + 1;     // Catch: Exception -> L31
        goto L10
    L12:
        r2.score = this.preferences.getInt("score" + this.templates.size(), 0);     // Catch: Exception -> L31
        this.templates.add(r2);     // Catch: Exception -> L31
    L14:
        if (this.isLearning == false) goto L29;
        String r22 = this.preferences.getString("moretemplates", null);     // Catch: Exception -> L31
        if (r22 == null) goto L29;
        String[] r23 = r22.split("\\|");     // Catch: Exception -> L31
        int r32 = this.templates.size();     // Catch: Exception -> L31
        int r72 = 0;
    L19:
        if (r72 >= r23.length) goto L29;
        Template r82 = new Template(r5);     // Catch: Exception -> L31
        String[] r9 = r23[r72].split(",");     // Catch: Exception -> L31
        int r11 = 1;
        if (r9.length > 1) goto L23;
        char r92 = r6;
    L28:
        r72 = r72 + 1;     // Catch: Exception -> L31
        r6 = r92;
        r5 = null;
        goto L19
    L23:
        r82.shapeType = Integer.parseInt(r9[r6]);     // Catch: Exception -> L31
    L25:
        if (r11 >= r9.length) goto L27;
        r82.points.add(new Point(Double.parseDouble(r9[r11]), Double.parseDouble(r9[r11 + 1])));     // Catch: Exception -> L31
        r11 = r11 + 2;     // Catch: Exception -> L31
        goto L25
    L27:
        r92 = 0;
        r82.score = this.preferences.getInt("score" + (r32 + r72), 0);     // Catch: Exception -> L31
        this.templates.add(r82);     // Catch: Exception -> L31
    L29:
        r1.close();     // Catch: Exception -> L31
        return;
    L31:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$showSaveLearnDialog$3(ArrayList r8, DialogInterface r9, int r10) {
        int r92 = 0;
        if (r10 != 0) goto L19;
        StringBuilder r82 = new StringBuilder("[");
        int r1 = 0;
    L6:
        if (r1 >= this.templates.size()) goto L17;
        Template r2 = this.templates.get(r1);
        if (r1 <= 0) goto L10;
        r82.append(",\n");
    L10:
        r82.append("\t{\n\t\t\"shape\": ");
        r82.append(r2.shapeType);
        r82.append(",\n\t\t\"points\": [");
        int r3 = 0;
    L12:
        if (r3 >= r2.points.size()) goto L16;
        if (r3 <= 0) goto L15;
        r82.append(",");
    L15:
        Point r4 = r2.points.get(r3);
        r82.append("[");
        r82.append(Math.round(r4.x));
        r82.append(",");
        r82.append(Math.round(r4.y));
        r82.append("]");
        r3 = r3 + 1;
        goto L12
    L16:
        r82.append("],\n\t\t\"freq\": ");
        r82.append(Math.round(((r2.score / this.templatesUsageScore) * 100.0f) * 100.0f) / 100.0f);
        r82.append("\n\t}");
        r1 = r1 + 1;
        goto L6
    L17:
        r82.append("\n]");
        Log.i("shapedetector", r82.toString());
        return;
    L19:
        Template r12 = new Template(null);
        r12.shapeType = r10 - 1;
        r12.points = r8;
        this.templates.add(r12);
        String r102 = this.preferences.getString("moretemplates", null);
        if (r102 != null) goto L22;
        String r103 = _UrlKt.FRAGMENT_ENCODE_SET + r12.shapeType;
    L24:
        if (r92 >= r8.size()) goto L26;
        r103 = r103 + "," + Math.round(((Point) r8.get(r92)).x) + "," + Math.round(((Point) r8.get(r92)).y);
        r92 = r92 + 1;
        goto L24
    L26:
        this.preferences.edit().putString("moretemplates", r103).apply();
        return;
    L22:
        r103 = r102 + "|" + r12.shapeType;
        goto L24
    }

    private void parseTemplates() {
        queue.postRunnable(new ShapeDetector$$ExternalSyntheticLambda2(this));
    }

    private double pathLength(ArrayList<Point> r5) {
        double r0 = 0.0d;
        int r4 = 1;
    L4:
        if (r4 >= r5.size()) goto L6;
        r0 = r0 + r5.get(r4 - 1).distance(r5.get(r4));
        r4 = r4 + 1;
        goto L4
    L6:
        return r0;
    }

    private ArrayList<Point> resample(ArrayList<Point> r20, int r21) {
        ArrayList<Point> r1 = new ArrayList();
        r1.add(r20.get(0));
        boolean r4 = true;
        int r5 = r21 - 1;
        double r2 = pathLength(r20) / r5;
        int r8 = 1;
        double r9 = 0.0d;
    L4:
        if (r8 >= r20.size()) goto L11;
        int r11 = r8 - 1;
        double r12 = r20.get(r11).distance(r20.get(r8));
        double r14 = r9 + r12;
        if (r14 < r2) goto L8;
        double r92 = (r2 - r9) / r12;
        boolean r19 = r4;
        int r212 = r5;
        double r17 = r2;
        Point r142 = new Point(r20.get(r11).x + ((r20.get(r8).x - r20.get(r11).x) * r92), r20.get(r11).y + (r92 * (r20.get(r8).y - r20.get(r11).y)));
        r1.add(r142);
        r20.add(r8, r142);
        r9 = 0.0d;
    L9:
        r8 = r8 + 1;
        r4 = r19;
        r5 = r212;
        r2 = r17;
        goto L4
    L8:
        r17 = r2;
        r19 = r4;
        r212 = r5;
        r9 = r14;
        goto L9
    L11:
        if (r1.size() != r5) goto L13;
        r1.add(r20.get(r20.size() - 1));
    L13:
        return r1;
    }

    private void rotate(ArrayList<Point> r2, double r3) {
        rotate(r2, r3, centroid(r2));
    }

    private void rotate(ArrayList<Point> r21, double r22, Point r24) {
        double r1 = Math.cos(r22);
        double r3 = Math.sin(r22);
        int r5 = 0;
    L4:
        if (r5 >= r21.size()) goto L6;
        Point r7 = r21.get(r5);
        double r8 = r7.x;
        double r10 = r24.x;
        double r12 = (r8 - r10) * r1;
        double r14 = r7.y;
        double r16 = r1;
        double r13 = r24.y;
        double r122 = (r12 - ((r14 - r13) * r3)) + r10;
        r7.y = (((r8 - r10) * r3) + ((r14 - r13) * r16)) + r13;
        r7.x = r122;
        r5 = r5 + 1;
        r1 = r16;
        goto L4
    }

    private void scale(ArrayList<Point> r10, double r11) {
        RectD r9 = boundingBox(r10);
        double r0 = r9.right - r9.left;
        double r2 = r9.bottom - r9.top;
        int r92 = 0;
    L4:
        if (r92 >= r10.size()) goto L6;
        Point r4 = r10.get(r92);
        r4.x *= r11 / r0;
        r4.y *= r11 / r2;
        r92 = r92 + 1;
        goto L4
    }

    public static void setLearning(Context r2, boolean r3) {
        SharedPreferences.Editor r22 = r2.getSharedPreferences("shapedetector_conf", 0).edit();
        if (r3 == true) goto L5;
        r22.clear();
    L6:
        r22.apply();
        return;
    L5:
        r22.putBoolean("learning", true);
        goto L6
    }

    private void showSaveLearnDialog() {
        ArrayList<Point> r0 = this.toSave;
        new AlertDialog.Builder(this.context).setTitle("Shape?").setItems(new String[]{"Log all", "Circle", "Rectangle", "Star", "Bubble", "Arrow", "None"}, new ShapeDetector$$ExternalSyntheticLambda1(this, r0)).show();
        this.toSave = null;
    }

    private void translate(ArrayList<Point> r4, double r5, double r7) {
        int r3 = 0;
    L4:
        if (r3 >= r4.size()) goto L6;
        Point r0 = r4.get(r3);
        r0.x += r5;
        r0.y += r7;
        r3 = r3 + 1;
        goto L4
    }

    public void append(double r3, double r5, boolean r7) {
        monitor-enter(this);
        this.points.add(new Point(r3, r5));     // Catch: Throwable -> L11
        if (this.points.size() < 8) goto L6;
        boolean r32 = true;
    L7:
        monitor-exit(this);     // Catch: Throwable -> L11
        if (r32 == false) goto L16;
        scheduleDetect(r7);
        return;
    L16:
        return;
    L6:
        r32 = false;
    L11:
        th = move-exception;
        throw th;
    }

    public void clear() {
        monitor-enter(this);
        this.points.clear();     // Catch: Throwable -> L11
        monitor-exit(this);     // Catch: Throwable -> L11
        queue.cancelRunnable(this.detect);
        this.scheduled.set(false);
        this.shapeDetected = false;
        if (this.isLearning == true) goto L8;
        return;
    L8:
        if (this.toSave == null) goto L17;
        showSaveLearnDialog();
        return;
    L17:
        return;
    L11:
        th = move-exception;
        throw th;
    }

    public void scheduleDetect(boolean r4) {
        if (this.busy.get() == false) goto L5;
        return;
    L5:
        if (this.scheduled.get() == false) goto L11;
        if (this.shapeDetected == true) goto L11;
        if (r4 == false) goto L11;
        queue.cancelRunnable(this.detect);
        queue.postRunnable(this.detect, 150);
    L11:
        if (this.scheduled.get() == true) goto L15;
        this.scheduled.set(true);
        queue.postRunnable(this.detect, 150);
        return;
    }
}
