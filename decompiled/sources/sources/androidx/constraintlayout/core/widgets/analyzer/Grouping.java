package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Flow;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes3.dex */
public abstract class Grouping {
    public static WidgetGroup findDependents(ConstraintWidget r6, int r7, ArrayList<WidgetGroup> r8, WidgetGroup r9) {
        if (r7 != 0) goto L4;
        int r0 = r6.horizontalGroup;
    L5:
        int r1 = 0;
        if (r0 == (-1)) goto L19;
        if (r9 != null) goto L9;
    L10:
        int r3 = 0;
    L12:
        if (r3 >= r8.size()) goto L21;
        WidgetGroup r4 = r8.get(r3);
        if (r4.getId() == r0) goto L15;
        r3 = r3 + 1;
        goto L12
    L15:
        if (r9 == null) goto L17;
        r9.moveTo(r7, r4);
        r8.remove(r9);
    L17:
        r9 = r4;
    L21:
        if (r9 != null) goto L37;
        if ((r6 instanceof HelperWidget) == false) goto L33;
        int r02 = ((HelperWidget) r6).findGroupInDependents(r7);
        if (r02 == (-1)) goto L33;
        int r2 = 0;
    L28:
        if (r2 >= r8.size()) goto L33;
        WidgetGroup r32 = r8.get(r2);
        if (r32.getId() == r02) goto L31;
        r2 = r2 + 1;
        goto L28
    L31:
        r9 = r32;
    L33:
        if (r9 != null) goto L35;
        r9 = new WidgetGroup(r7);
    L35:
        r8.add(r9);
    L37:
        if (r9.add(r6) == true) goto L39;
    L48:
        return r9;
    L39:
        if ((r6 instanceof Guideline) == false) goto L44;
        Guideline r03 = (Guideline) r6;
        ConstraintAnchor r22 = r03.getAnchor();
        if (r03.getOrientation() != 0) goto L43;
        r1 = 1;
    L43:
        r22.findDependents(r1, r8, r9);
    L44:
        if (r7 != 0) goto L46;
        r6.horizontalGroup = r9.getId();
        r6.mLeft.findDependents(r7, r8, r9);
        r6.mRight.findDependents(r7, r8, r9);
    L47:
        r6.mCenter.findDependents(r7, r8, r9);
        goto L48
    L46:
        r6.verticalGroup = r9.getId();
        r6.mTop.findDependents(r7, r8, r9);
        r6.mBaseline.findDependents(r7, r8, r9);
        r6.mBottom.findDependents(r7, r8, r9);
        goto L47
    L9:
        if (r0 != r9.getId()) goto L10;
    L19:
        if (r0 == (-1)) goto L21;
        return r9;
    L4:
        r0 = r6.verticalGroup;
        goto L5
    }

    private static WidgetGroup findGroup(ArrayList<WidgetGroup> r4, int r5) {
        int r0 = r4.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        WidgetGroup r2 = r4.get(r1);
        if (r5 == r2.getId()) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r2;
    L8:
        return null;
    }

    public static boolean simpleSolvingPass(ConstraintWidgetContainer r17, BasicMeasure.Measurer r18) {
        ArrayList<ConstraintWidget> r1 = r17.getChildren();
        int r2 = r1.size();
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L11;
        ConstraintWidget r5 = r1.get(r4);
        if (validInGroup(r17.getHorizontalDimensionBehaviour(), r17.getVerticalDimensionBehaviour(), r5.getHorizontalDimensionBehaviour(), r5.getVerticalDimensionBehaviour()) == false) goto L6;
        if ((r5 instanceof Flow) == true) goto L9;
        r4 = r4 + 1;
        goto L3
    L9:
        return false;
    L6:
        return false;
    L11:
        int r52 = 0;
        ArrayList r6 = null;
        ArrayList r7 = null;
        ArrayList r8 = null;
        ArrayList r9 = null;
        ArrayList r10 = null;
        ArrayList r11 = null;
    L12:
        if (r52 >= r2) goto L73;
        ConstraintWidget r13 = r1.get(r52);
        if (validInGroup(r17.getHorizontalDimensionBehaviour(), r17.getVerticalDimensionBehaviour(), r13.getHorizontalDimensionBehaviour(), r13.getVerticalDimensionBehaviour()) == true) goto L17;
        ConstraintWidgetContainer.measure(r3, r13, r18, r17.mMeasure, BasicMeasure.Measure.SELF_DIMENSIONS);
    L17:
        boolean r42 = r13 instanceof Guideline;
        if (r42 == false) goto L30;
        Guideline r12 = (Guideline) r13;
        if (r12.getOrientation() != 0) goto L25;
        if (r8 != null) goto L23;
        r8 = new ArrayList();
    L23:
        r8.add(r12);
    L25:
        if (r12.getOrientation() != 1) goto L30;
        if (r6 != null) goto L28;
        r6 = new ArrayList();
    L28:
        r6.add(r12);
    L30:
        if ((r13 instanceof HelperWidget) == false) goto L51;
        if ((r13 instanceof Barrier) == false) goto L43;
        Barrier r32 = (Barrier) r13;
        if (r32.getOrientation() != 0) goto L39;
        if (r7 != null) goto L37;
        r7 = new ArrayList();
    L37:
        r7.add(r32);
    L39:
        if (r32.getOrientation() != 1) goto L51;
        if (r9 != null) goto L42;
        r9 = new ArrayList();
    L42:
        r9.add(r32);
        goto L51
    L43:
        HelperWidget r33 = (HelperWidget) r13;
        if (r7 != null) goto L46;
        r7 = new ArrayList();
    L46:
        r7.add(r33);
        if (r9 != null) goto L49;
        r9 = new ArrayList();
    L49:
        r9.add(r33);
    L51:
        if (r13.mLeft.mTarget != null) goto L61;
        if (r13.mRight.mTarget != null) goto L61;
        if (r42 == true) goto L61;
        if ((r13 instanceof Barrier) == true) goto L61;
        if (r10 != null) goto L59;
        r10 = new ArrayList();
    L59:
        r10.add(r13);
    L61:
        if (r13.mTop.mTarget != null) goto L72;
        if (r13.mBottom.mTarget != null) goto L72;
        if (r13.mBaseline.mTarget != null) goto L72;
        if (r42 == true) goto L72;
        if ((r13 instanceof Barrier) == true) goto L72;
        if (r11 != null) goto L71;
        r11 = new ArrayList();
    L71:
        r11.add(r13);
    L72:
        r52 = r52 + 1;
        r3 = 0;
        goto L12
    L73:
        ArrayList<WidgetGroup> r34 = new ArrayList();
        if (r6 == null) goto L78;
        int r43 = r6.size();
        int r53 = 0;
    L76:
        if (r53 >= r43) goto L78;
        Object r122 = r6.get(r53);
        r53 = r53 + 1;
        findDependents((Guideline) r122, 0, r34, null);
    L78:
        if (r7 == null) goto L82;
        int r44 = r7.size();
        int r54 = 0;
    L80:
        if (r54 >= r44) goto L82;
        Object r62 = r7.get(r54);
        r54 = r54 + 1;
        HelperWidget r63 = (HelperWidget) r62;
        WidgetGroup r123 = findDependents(r63, 0, r34, null);
        r63.addDependents(r34, 0, r123);
        r123.cleanup(r34);
    L82:
        ConstraintAnchor r45 = r17.getAnchor(ConstraintAnchor.Type.LEFT);
        if (r45.getDependents() == null) goto L88;
        Iterator<ConstraintAnchor> r46 = r45.getDependents().iterator();
    L86:
        if (r46.hasNext() == false) goto L88;
        findDependents(r46.next().mOwner, 0, r34, null);
    L88:
        ConstraintAnchor r47 = r17.getAnchor(ConstraintAnchor.Type.RIGHT);
        if (r47.getDependents() == null) goto L94;
        Iterator<ConstraintAnchor> r48 = r47.getDependents().iterator();
    L92:
        if (r48.hasNext() == false) goto L94;
        findDependents(r48.next().mOwner, 0, r34, null);
    L94:
        ConstraintAnchor r49 = r17.getAnchor(ConstraintAnchor.Type.CENTER);
        if (r49.getDependents() == null) goto L100;
        Iterator<ConstraintAnchor> r410 = r49.getDependents().iterator();
    L98:
        if (r410.hasNext() == false) goto L100;
        findDependents(r410.next().mOwner, 0, r34, null);
    L100:
        if (r10 == null) goto L104;
        int r411 = r10.size();
        int r55 = 0;
    L102:
        if (r55 >= r411) goto L104;
        Object r64 = r10.get(r55);
        r55 = r55 + 1;
        findDependents((ConstraintWidget) r64, 0, r34, null);
    L104:
        if (r8 == null) goto L108;
        int r412 = r8.size();
        int r56 = 0;
    L106:
        if (r56 >= r412) goto L108;
        Object r65 = r8.get(r56);
        r56 = r56 + 1;
        findDependents((Guideline) r65, 1, r34, null);
    L108:
        if (r9 == null) goto L112;
        int r413 = r9.size();
        int r57 = 0;
    L110:
        if (r57 >= r413) goto L112;
        Object r66 = r9.get(r57);
        r57 = r57 + 1;
        HelperWidget r67 = (HelperWidget) r66;
        WidgetGroup r72 = findDependents(r67, 1, r34, null);
        r67.addDependents(r34, 1, r72);
        r72.cleanup(r34);
    L112:
        ConstraintAnchor r414 = r17.getAnchor(ConstraintAnchor.Type.TOP);
        if (r414.getDependents() == null) goto L118;
        Iterator<ConstraintAnchor> r415 = r414.getDependents().iterator();
    L116:
        if (r415.hasNext() == false) goto L118;
        findDependents(r415.next().mOwner, 1, r34, null);
    L118:
        ConstraintAnchor r416 = r17.getAnchor(ConstraintAnchor.Type.BASELINE);
        if (r416.getDependents() == null) goto L124;
        Iterator<ConstraintAnchor> r417 = r416.getDependents().iterator();
    L122:
        if (r417.hasNext() == false) goto L124;
        findDependents(r417.next().mOwner, 1, r34, null);
    L124:
        ConstraintAnchor r418 = r17.getAnchor(ConstraintAnchor.Type.BOTTOM);
        if (r418.getDependents() == null) goto L130;
        Iterator<ConstraintAnchor> r419 = r418.getDependents().iterator();
    L128:
        if (r419.hasNext() == false) goto L130;
        findDependents(r419.next().mOwner, 1, r34, null);
    L130:
        ConstraintAnchor r420 = r17.getAnchor(ConstraintAnchor.Type.CENTER);
        if (r420.getDependents() == null) goto L136;
        Iterator<ConstraintAnchor> r421 = r420.getDependents().iterator();
    L134:
        if (r421.hasNext() == false) goto L136;
        findDependents(r421.next().mOwner, 1, r34, null);
    L136:
        if (r11 == null) goto L140;
        int r422 = r11.size();
        int r58 = 0;
    L138:
        if (r58 >= r422) goto L140;
        Object r68 = r11.get(r58);
        r58 = r58 + 1;
        findDependents((ConstraintWidget) r68, 1, r34, null);
    L140:
        int r423 = 0;
    L141:
        if (r423 >= r2) goto L150;
        ConstraintWidget r59 = r1.get(r423);
        if (r59.oppositeDimensionsTied() == false) goto L148;
        WidgetGroup r69 = findGroup(r34, r59.horizontalGroup);
        WidgetGroup r510 = findGroup(r34, r59.verticalGroup);
        if (r69 == null) goto L148;
        if (r510 == null) goto L148;
        r69.moveTo(0, r510);
        r510.setOrientation(2);
        r34.remove(r69);
    L148:
        r423 = r423 + 1;
        goto L141
    L150:
        if (r34.size() > 1) goto L154;
        return false;
    L154:
        if (r17.getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) goto L165;
        int r14 = r34.size();
        WidgetGroup r22 = null;
        int r424 = 0;
        int r511 = 0;
    L156:
        if (r511 >= r14) goto L163;
        WidgetGroup r610 = r34.get(r511);
        r511 = r511 + 1;
        WidgetGroup r611 = r610;
        if (r611.getOrientation() == 1) goto L156;
        r611.setAuthoritative(false);
        int r73 = r611.measureWrap(r17.getSystem(), 0);
        if (r73 <= r424) goto L156;
        r22 = r611;
        r424 = r73;
        goto L156
    L163:
        if (r22 == null) goto L165;
        r17.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
        r17.setWidth(r424);
        r22.setAuthoritative(true);
    L167:
        if (r17.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) goto L179;
        int r15 = r34.size();
        WidgetGroup r425 = null;
        int r512 = 0;
        int r142 = 0;
    L169:
        if (r512 >= r15) goto L176;
        WidgetGroup r612 = r34.get(r512);
        r512 = r512 + 1;
        WidgetGroup r613 = r612;
        if (r613.getOrientation() == 0) goto L169;
        r613.setAuthoritative(false);
        int r74 = r613.measureWrap(r17.getSystem(), 1);
        if (r74 <= r142) goto L169;
        r425 = r613;
        r142 = r74;
        goto L169
    L176:
        boolean r152 = true;
        if (r425 == null) goto L180;
        r17.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
        r17.setHeight(r142);
        r425.setAuthoritative(true);
    L181:
        if (r22 != null) goto L186;
        if (r425 != null) goto L186;
        return false;
    L186:
        return r152;
    L180:
        r425 = null;
        goto L181
    L179:
        r152 = true;
    L165:
        r22 = null;
        goto L167
    }

    public static boolean validInGroup(ConstraintWidget.DimensionBehaviour r5, ConstraintWidget.DimensionBehaviour r6, ConstraintWidget.DimensionBehaviour r7, ConstraintWidget.DimensionBehaviour r8) {
        ConstraintWidget.DimensionBehaviour r0 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (r7 == r0) goto L11;
        ConstraintWidget.DimensionBehaviour r3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r7 == r3) goto L11;
        if (r7 != ConstraintWidget.DimensionBehaviour.MATCH_PARENT) goto L10;
        if (r5 != r3) goto L11;
    L10:
        boolean r52 = false;
    L12:
        if (r8 == r0) goto L20;
        ConstraintWidget.DimensionBehaviour r72 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r8 == r72) goto L20;
        if (r8 != ConstraintWidget.DimensionBehaviour.MATCH_PARENT) goto L19;
        if (r6 != r72) goto L20;
    L19:
        boolean r62 = false;
    L21:
        if (r52 == true) goto L25;
        if (r62 == true) goto L25;
        return false;
    L25:
        return true;
    L20:
        r62 = true;
    L11:
        r52 = true;
        goto L12
    }
}
