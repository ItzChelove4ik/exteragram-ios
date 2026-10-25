package androidx.constraintlayout.core.widgets.analyzer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class DependencyNode implements Dependency {
    public boolean delegateToWidgetRun;
    List<Dependency> mDependencies;
    int mMargin;
    DimensionDependency mMarginDependency;
    int mMarginFactor;
    WidgetRun mRun;
    List<DependencyNode> mTargets;
    Type mType;
    public boolean readyToSolve;
    public boolean resolved;
    public Dependency updateDelegate;
    public int value;

    public enum Type extends Enum<Type> {
        private static final /* synthetic */ Type[] $VALUES = null;
        public static final Type BASELINE = null;
        public static final Type BOTTOM = null;
        public static final Type HORIZONTAL_DIMENSION = null;
        public static final Type LEFT = null;
        public static final Type RIGHT = null;
        public static final Type TOP = null;
        public static final Type UNKNOWN = null;
        public static final Type VERTICAL_DIMENSION = null;

        private static /* synthetic */ Type[] $values() {
            return new Type[]{UNKNOWN, HORIZONTAL_DIMENSION, VERTICAL_DIMENSION, LEFT, RIGHT, TOP, BOTTOM, BASELINE};
        }

        static {
            UNKNOWN = new Type("UNKNOWN", 0);
            HORIZONTAL_DIMENSION = new Type("HORIZONTAL_DIMENSION", 1);
            VERTICAL_DIMENSION = new Type("VERTICAL_DIMENSION", 2);
            LEFT = new Type("LEFT", 3);
            RIGHT = new Type("RIGHT", 4);
            TOP = new Type("TOP", 5);
            BOTTOM = new Type("BOTTOM", 6);
            BASELINE = new Type("BASELINE", 7);
            $VALUES = $values();
        }

        Type(String r1, int r2) {
        }

        public static Type valueOf(String r1) {
            return (Type) Enum.valueOf(Type.class, r1);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }
    }

    public DependencyNode(WidgetRun r4) {
        this.updateDelegate = null;
        this.delegateToWidgetRun = false;
        this.readyToSolve = false;
        this.mType = Type.UNKNOWN;
        this.mMarginFactor = 1;
        this.mMarginDependency = null;
        this.resolved = false;
        this.mDependencies = new ArrayList();
        this.mTargets = new ArrayList();
        this.mRun = r4;
    }

    public void addDependency(Dependency r2) {
        this.mDependencies.add(r2);
        if (this.resolved == false) goto L6;
        r2.update(r2);
        return;
    }

    public void clear() {
        this.mTargets.clear();
        this.mDependencies.clear();
        this.resolved = false;
        this.value = 0;
        this.readyToSolve = false;
        this.delegateToWidgetRun = false;
    }

    public void resolve(int r2) {
        if (this.resolved == true) goto L9;
        this.resolved = true;
        this.value = r2;
        Iterator<Dependency> r1 = this.mDependencies.iterator();
    L7:
        if (r1.hasNext() == false) goto L11;
        Dependency r22 = r1.next();
        r22.update(r22);
        goto L7
    L11:
        return;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        r0.append(this.mRun.mWidget.getDebugName());
        r0.append(":");
        r0.append(this.mType);
        r0.append("(");
        if (this.resolved == false) goto L5;
        Object r1 = Integer.valueOf(this.value);
    L6:
        r0.append(r1);
        r0.append(") <t=");
        r0.append(this.mTargets.size());
        r0.append(":d=");
        r0.append(this.mDependencies.size());
        r0.append(">");
        return r0.toString();
    L5:
        r1 = "unresolved";
        goto L6
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency r6) {
        Iterator<DependencyNode> r62 = this.mTargets.iterator();
    L4:
        if (r62.hasNext() == false) goto L8;
        if (r62.next().resolved == true) goto L4;
        return;
    L8:
        this.readyToSolve = true;
        Dependency r0 = this.updateDelegate;
        if (r0 == null) goto L12;
        r0.update(this);
    L12:
        if (this.delegateToWidgetRun == false) goto L15;
        this.mRun.update(this);
        return;
    L15:
        Iterator<DependencyNode> r02 = this.mTargets.iterator();
        DependencyNode r1 = null;
        int r2 = 0;
    L17:
        if (r02.hasNext() == false) goto L22;
        DependencyNode r3 = r02.next();
        if ((r3 instanceof DimensionDependency) == true) goto L17;
        r2 = r2 + 1;
        r1 = r3;
        goto L17
    L22:
        if (r1 == null) goto L32;
        if (r2 != 1) goto L32;
        if (r1.resolved == false) goto L32;
        DimensionDependency r63 = this.mMarginDependency;
        if (r63 != null) goto L29;
    L31:
        resolve(r1.value + this.mMargin);
        goto L32
    L29:
        if (r63.resolved == false) goto L44;
        this.mMargin = this.mMarginFactor * r63.value;
        goto L31
    L44:
        return;
    L32:
        Dependency r64 = this.updateDelegate;
        if (r64 == null) goto L45;
        r64.update(this);
        return;
    }
}
