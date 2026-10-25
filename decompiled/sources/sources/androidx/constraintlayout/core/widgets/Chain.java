package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.ArrayRow;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public abstract class Chain {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v2, types: [androidx.constraintlayout.core.widgets.ConstraintWidget] */
    /* JADX WARN: Type inference failed for: r14v24 */
    /* JADX WARN: Type inference failed for: r14v25 */
    /* JADX WARN: Type inference failed for: r14v26 */
    /* JADX WARN: Type inference failed for: r4v44, types: [androidx.constraintlayout.core.widgets.ConstraintWidget] */
    public static void applyChainConstraints(ConstraintWidgetContainer r37, LinearSystem r38, int r39, int r40, ChainHead r41) {
        int r9 = r39;
        ConstraintWidget r10 = r41.mFirst;
        ConstraintWidget r11 = r41.mLast;
        ConstraintWidget r12 = r41.mFirstVisibleWidget;
        ConstraintWidget r13 = r41.mLastVisibleWidget;
        ConstraintWidget r3 = r41.mHead;
        float r4 = r41.mTotalWeight;
        if (r37.mListDimensionBehaviors[r9] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) goto L5;
        boolean r5 = true;
    L7:
        if (r9 != 0) goto L19;
        int r8 = r3.mHorizontalChainStyle;
        if (r8 != 0) goto L11;
        boolean r15 = true;
    L12:
        if (r8 != 1) goto L14;
        boolean r16 = true;
    L15:
        if (r8 != 2) goto L17;
    L16:
        boolean r6 = true;
    L18:
        ?? r14 = r10;
        boolean r82 = false;
    L28:
        float r19 = r4;
        SolverVariable r22 = null;
        if (r82 == true) goto L87;
        ConstraintAnchor r42 = r14.mListAnchors[r40];
        if (r6 == false) goto L33;
        int r20 = 1;
    L34:
        int r23 = r42.getMargin();
        ConstraintWidget.DimensionBehaviour r7 = r14.mListDimensionBehaviors[r9];
        boolean r24 = r5;
        ConstraintWidget.DimensionBehaviour r52 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r7 == r52) goto L37;
    L40:
        boolean r72 = false;
    L39:
        boolean r25 = r6;
        ConstraintAnchor r62 = r42.mTarget;
        if (r62 == null) goto L45;
        if (r14 == r10) goto L45;
        r23 = r23 + r62.getMargin();
    L45:
        int r63 = r23;
        if (r25 == false) goto L50;
        if (r14 == r10) goto L50;
        if (r14 == r12) goto L50;
        r20 = 8;
    L50:
        boolean r232 = r72;
        ConstraintAnchor r73 = r42.mTarget;
        boolean r26 = r82;
        if (r73 == null) goto L66;
        SolverVariable r83 = r42.mSolverVariable;
        if (r14 != r12) goto L55;
        boolean r27 = r15;
        r38.addGreaterThan(r83, r73.mSolverVariable, r63, 6);
    L56:
        if (r232 == false) goto L59;
        if (r25 == true) goto L59;
        r20 = 5;
    L59:
        if (r14 != r12) goto L64;
        if (r25 == false) goto L64;
        if (r14.isInBarrier(r9) == false) goto L64;
        int r74 = 5;
    L65:
        r38.addEquality(r42.mSolverVariable, r42.mTarget.mSolverVariable, r63, r74);
    L67:
        if (r24 == true) goto L69;
    L75:
        ConstraintAnchor r43 = r14.mListAnchors[r40 + 1].mTarget;
        if (r43 == null) goto L83;
        ?? r44 = r43.mOwner;
        ConstraintAnchor r53 = r44.mListAnchors[r40].mTarget;
        if (r53 == null) goto L83;
        if (r53.mOwner != r14) goto L83;
        r22 = r44;
    L83:
        if (r22 == null) goto L85;
        r14 = r22;
        r82 = r26;
    L86:
        r4 = r19;
        r5 = r24;
        r6 = r25;
        r15 = r27;
        r14 = r14;
        goto L28
    L85:
        r82 = true;
        goto L86
    L69:
        if (r14.getVisibility() != 8) goto L71;
    L73:
        int r75 = 0;
    L74:
        r38.addGreaterThan(r14.mListAnchors[r40].mSolverVariable, r37.mListAnchors[r40].mSolverVariable, r75, 8);
        goto L75
    L71:
        if (r14.mListDimensionBehaviors[r9] != r52) goto L73;
        ConstraintAnchor[] r45 = r14.mListAnchors;
        r75 = 0;
        r38.addGreaterThan(r45[r40 + 1].mSolverVariable, r45[r40].mSolverVariable, 0, 5);
    L64:
        r74 = r20;
        goto L65
    L55:
        r27 = r15;
        r38.addGreaterThan(r83, r73.mSolverVariable, r63, 8);
        goto L56
    L66:
        r27 = r15;
        goto L67
    L37:
        if (r14.mResolvedMatchConstraintDefault[r9] != 0) goto L40;
        r72 = true;
        goto L39
    L33:
        r20 = 4;
        goto L34
    L87:
        boolean r242 = r5;
        boolean r252 = r6;
        boolean r272 = r15;
        if (r13 == null) goto L106;
        int r54 = r40 + 1;
        if (r11.mListAnchors[r54].mTarget == null) goto L106;
        ConstraintAnchor r46 = r13.mListAnchors[r54];
        if (r13.mListDimensionBehaviors[r9] != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L100;
        if (r13.mResolvedMatchConstraintDefault[r9] != 0) goto L100;
        if (r252 == true) goto L100;
        ConstraintAnchor r64 = r46.mTarget;
        if (r64.mOwner != r37) goto L100;
        r38.addEquality(r46.mSolverVariable, r64.mSolverVariable, -r46.getMargin(), 5);
    L104:
        r38.addLowerThan(r46.mSolverVariable, r11.mListAnchors[r54].mTarget.mSolverVariable, -r46.getMargin(), 6);
    L100:
        if (r252 == false) goto L104;
        ConstraintAnchor r65 = r46.mTarget;
        if (r65.mOwner != r37) goto L104;
        r38.addEquality(r46.mSolverVariable, r65.mSolverVariable, -r46.getMargin(), 4);
    L106:
        if (r242 == false) goto L108;
        int r47 = r40 + 1;
        SolverVariable r0 = r37.mListAnchors[r47].mSolverVariable;
        ConstraintAnchor r48 = r11.mListAnchors[r47];
        r38.addGreaterThan(r0, r48.mSolverVariable, r48.getMargin(), 8);
    L108:
        ArrayList<ConstraintWidget> r02 = r41.mWeightedMatchConstraintsWidgets;
        if (r02 == null) goto L138;
        int r49 = r02.size();
        if (r49 <= 1) goto L138;
        if (r41.mHasUndefinedWeights == true) goto L115;
    L117:
        float r30 = r19;
    L118:
        float r55 = 0.0f;
        float r29 = 0.0f;
        ConstraintWidget r66 = null;
        int r76 = 0;
    L119:
        if (r76 >= r49) goto L138;
        ConstraintWidget r84 = r02.get(r76);
        float r142 = r84.mWeight[r9];
        if (r142 < r55) goto L123;
        float r372 = r55;
    L126:
        float r31 = r142;
        if (r31 != r372) goto L133;
        ConstraintAnchor[] r85 = r84.mListAnchors;
        r38.addEquality(r85[r40 + 1].mSolverVariable, r85[r40].mSolverVariable, 0, 8);
    L131:
        ArrayList<ConstraintWidget> r192 = r02;
    L137:
        r76 = r76 + 1;
        r55 = r372;
        r02 = r192;
        goto L119
    L133:
        if (r66 == null) goto L135;
        ConstraintAnchor[] r67 = r66.mListAnchors;
        SolverVariable r143 = r67[r40].mSolverVariable;
        int r152 = r40 + 1;
        SolverVariable r68 = r67[r152].mSolverVariable;
        ConstraintAnchor[] r56 = r84.mListAnchors;
        r192 = r02;
        SolverVariable r03 = r56[r40].mSolverVariable;
        SolverVariable r57 = r56[r152].mSolverVariable;
        ArrayRow r28 = r38.createRow();
        r28.createRowEqualMatchDimensions(r29, r30, r31, r143, r68, r03, r57);
        r38.addConstraint(r28);
    L136:
        r66 = r84;
        r29 = r31;
        goto L137
    L135:
        r192 = r02;
        goto L136
    L123:
        if (r41.mHasComplexMatchWeights == false) goto L125;
        ConstraintAnchor[] r86 = r84.mListAnchors;
        r372 = r55;
        r38.addEquality(r86[r40 + 1].mSolverVariable, r86[r40].mSolverVariable, 0, 4);
        goto L131
    L125:
        r372 = r55;
        r142 = 1.0f;
        goto L126
    L115:
        if (r41.mHasComplexMatchWeights == true) goto L117;
        r30 = r41.mWidgetsMatchCount;
    L138:
        if (r12 == null) goto L160;
        if (r12 == r13) goto L141;
        if (r252 == false) goto L160;
    L141:
        ConstraintAnchor r04 = r10.mListAnchors[r40];
        int r410 = r40 + 1;
        ConstraintAnchor r2 = r11.mListAnchors[r410];
        ConstraintAnchor r05 = r04.mTarget;
        if (r05 == null) goto L144;
        SolverVariable r06 = r05.mSolverVariable;
    L145:
        ConstraintAnchor r58 = r2.mTarget;
        if (r58 == null) goto L148;
        SolverVariable r59 = r58.mSolverVariable;
    L149:
        ConstraintAnchor r69 = r12.mListAnchors[r40];
        if (r13 == null) goto L152;
        r2 = r13.mListAnchors[r410];
    L152:
        if (r06 == null) goto L159;
        if (r59 == null) goto L159;
        if (r9 != 0) goto L157;
        float r32 = r3.mHorizontalBiasPercent;
    L156:
        float r411 = r32;
        int r33 = r69.getMargin();
        int r77 = r2.getMargin();
        r38.addCentering(r69.mSolverVariable, r06, r33, r411, r59, r2.mSolverVariable, r77, 7);
        goto L159
    L157:
        r32 = r3.mVerticalBiasPercent;
    L159:
        LinearSystem r07 = r38;
    L284:
        if (r272 == true) goto L286;
        if (r16 == true) goto L286;
        return;
    L286:
        if (r12 == null) goto L327;
        if (r12 == r13) goto L328;
        ConstraintAnchor[] r1 = r12.mListAnchors;
        ConstraintAnchor r210 = r1[r40];
        if (r13 != null) goto L291;
        r13 = r12;
    L291:
        int r412 = r40 + 1;
        ConstraintAnchor r34 = r13.mListAnchors[r412];
        ConstraintAnchor r510 = r210.mTarget;
        if (r510 == null) goto L294;
        SolverVariable r511 = r510.mSolverVariable;
    L295:
        ConstraintAnchor r610 = r34.mTarget;
        if (r610 == null) goto L298;
        SolverVariable r611 = r610.mSolverVariable;
    L299:
        if (r11 == r13) goto L304;
        ConstraintAnchor r612 = r11.mListAnchors[r412].mTarget;
        if (r612 == null) goto L303;
        r22 = r612.mSolverVariable;
    L303:
        r611 = r22;
    L304:
        if (r12 != r13) goto L306;
        r34 = r1[r412];
    L306:
        if (r511 == null) goto L329;
        if (r611 == null) goto L330;
        SolverVariable r211 = r511;
        SolverVariable r512 = r611;
        r07.addCentering(r210.mSolverVariable, r211, r210.getMargin(), 0.5f, r512, r34.mSolverVariable, r13.mListAnchors[r412].getMargin(), 5);
        return;
    L330:
        return;
    L329:
        return;
    L298:
        r611 = null;
        goto L299
    L294:
        r511 = null;
        goto L295
    L328:
        return;
    L327:
        return;
    L148:
        r59 = null;
        goto L149
    L144:
        r06 = null;
    L160:
        if (r272 == false) goto L222;
        if (r12 == null) goto L222;
        int r08 = r41.mWidgetsMatchCount;
        if (r08 > 0) goto L165;
    L167:
        boolean r18 = false;
    L168:
        ConstraintWidget r144 = r12;
        ConstraintWidget r153 = r144;
    L169:
        if (r144 == null) goto L159;
        ConstraintWidget r09 = r144.mNextChainWidget[r9];
    L171:
        if (r09 == null) goto L175;
        int r613 = 8;
        if (r09.getVisibility() != 8) goto L176;
        r09 = r09.mNextChainWidget[r9];
    L176:
        if (r09 != null) goto L181;
        if (r144 == r13) goto L181;
        ConstraintWidget r193 = r09;
        int r92 = r613;
    L218:
        if (r144.getVisibility() == r92) goto L220;
        r153 = r144;
    L220:
        r9 = r39;
        r144 = r193;
    L181:
        ConstraintAnchor r17 = r144.mListAnchors[r40];
        SolverVariable r212 = r17.mSolverVariable;
        ConstraintAnchor r35 = r17.mTarget;
        if (r35 == null) goto L184;
        SolverVariable r36 = r35.mSolverVariable;
    L185:
        if (r153 == r144) goto L187;
        r36 = r153.mListAnchors[r40 + 1].mSolverVariable;
    L192:
        int r110 = r17.getMargin();
        int r513 = r40 + 1;
        int r413 = r144.mListAnchors[r513].getMargin();
        if (r09 == null) goto L195;
        ConstraintAnchor r78 = r09.mListAnchors[r40];
        SolverVariable r87 = r78.mSolverVariable;
    L199:
        SolverVariable r614 = r144.mListAnchors[r513].mSolverVariable;
        if (r78 == null) goto L202;
        r413 = r413 + r78.getMargin();
    L202:
        int r111 = r110 + r153.mListAnchors[r513].getMargin();
        if (r212 == null) goto L216;
        if (r36 == null) goto L216;
        if (r87 == null) goto L216;
        if (r614 == null) goto L216;
        if (r144 != r12) goto L209;
        r111 = r12.mListAnchors[r40].getMargin();
    L209:
        if (r144 != r13) goto L211;
        r413 = r13.mListAnchors[r513].getMargin();
    L211:
        int r79 = r413;
        SolverVariable r514 = r87;
        if (r18 == false) goto L214;
        int r88 = 8;
    L215:
        r193 = r09;
        r92 = 8;
        r38.addCentering(r212, r36, r111, 0.5f, r514, r614, r79, r88);
        goto L218
    L214:
        r88 = 5;
    L216:
        r193 = r09;
        r92 = 8;
        goto L218
    L195:
        r78 = r11.mListAnchors[r513].mTarget;
        if (r78 == null) goto L198;
        r87 = r78.mSolverVariable;
        goto L199
    L198:
        r87 = null;
        goto L199
    L187:
        if (r144 != r12) goto L192;
        ConstraintAnchor r310 = r10.mListAnchors[r40].mTarget;
        if (r310 == null) goto L191;
        r36 = r310.mSolverVariable;
        goto L192
    L191:
        r36 = null;
        goto L192
    L184:
        r36 = null;
        goto L185
    L175:
        r613 = 8;
        goto L176
    L165:
        if (r41.mWidgetsCount != r08) goto L167;
        r18 = true;
    L222:
        if (r16 == false) goto L159;
        if (r12 == null) goto L159;
        int r010 = r41.mWidgetsMatchCount;
        if (r010 > 0) goto L227;
    L229:
        boolean r182 = false;
    L230:
        ConstraintWidget r145 = r12;
        ConstraintWidget r154 = r145;
    L231:
        if (r145 == null) goto L274;
        ConstraintWidget r011 = r145.mNextChainWidget[r39];
    L233:
        if (r011 == null) goto L237;
        if (r011.getVisibility() != 8) goto L237;
        r011 = r011.mNextChainWidget[r39];
    L237:
        if (r145 == r12) goto L271;
        if (r145 == r13) goto L271;
        if (r011 == null) goto L271;
        if (r011 != r13) goto L242;
        r011 = null;
    L242:
        ConstraintAnchor r112 = r145.mListAnchors[r40];
        SolverVariable r213 = r112.mSolverVariable;
        ConstraintAnchor r311 = r112.mTarget;
        if (r311 == null) goto L245;
        SolverVariable r312 = r311.mSolverVariable;
    L245:
        int r414 = r40 + 1;
        SolverVariable r313 = r154.mListAnchors[r414].mSolverVariable;
        int r113 = r112.getMargin();
        int r515 = r145.mListAnchors[r414].getMargin();
        if (r011 == null) goto L251;
        ConstraintAnchor r615 = r011.mListAnchors[r40];
        SolverVariable r710 = r615.mSolverVariable;
        ConstraintAnchor r89 = r615.mTarget;
        if (r89 == null) goto L250;
        SolverVariable r810 = r89.mSolverVariable;
    L256:
        if (r615 == null) goto L258;
        r515 = r515 + r615.getMargin();
    L258:
        int r114 = r113 + r154.mListAnchors[r414].getMargin();
        if (r182 == false) goto L261;
        int r415 = 8;
    L262:
        if (r213 == null) goto L267;
        if (r313 == null) goto L267;
        if (r710 == null) goto L267;
        if (r810 == null) goto L267;
        ConstraintWidget r194 = r011;
        r38.addCentering(r213, r313, r114, 0.5f, r710, r810, r515, r415);
    L268:
        r011 = r194;
    L267:
        r194 = r011;
        goto L268
    L261:
        r415 = 4;
        goto L262
    L250:
        r810 = null;
        goto L256
    L251:
        r615 = r13.mListAnchors[r40];
        if (r615 == null) goto L254;
        r710 = r615.mSolverVariable;
    L255:
        r810 = r145.mListAnchors[r414].mSolverVariable;
        goto L256
    L254:
        r710 = null;
    L271:
        if (r145.getVisibility() == 8) goto L273;
        r154 = r145;
    L273:
        r145 = r011;
        goto L231
    L274:
        ConstraintAnchor r012 = r12.mListAnchors[r40];
        ConstraintAnchor r214 = r10.mListAnchors[r40].mTarget;
        int r416 = r40 + 1;
        ConstraintAnchor r93 = r13.mListAnchors[r416];
        ConstraintAnchor r102 = r11.mListAnchors[r416].mTarget;
        if (r214 == null) goto L278;
        if (r12 == r13) goto L279;
        r38.addEquality(r012.mSolverVariable, r214.mSolverVariable, r012.getMargin(), 5);
        goto L278
    L279:
        if (r102 == null) goto L278;
        r07 = r38;
        r07.addCentering(r012.mSolverVariable, r214.mSolverVariable, r012.getMargin(), 0.5f, r93.mSolverVariable, r102.mSolverVariable, r93.getMargin(), 5);
    L281:
        if (r102 == null) goto L284;
        if (r12 == r13) goto L284;
        r07.addEquality(r93.mSolverVariable, r102.mSolverVariable, -r93.getMargin(), 5);
    L278:
        r07 = r38;
        goto L281
    L227:
        if (r41.mWidgetsCount != r010) goto L229;
        r182 = true;
    L17:
        r6 = false;
        goto L18
    L14:
        r16 = false;
        goto L15
    L11:
        r15 = false;
        goto L12
    L19:
        int r811 = r3.mVerticalChainStyle;
        if (r811 != 0) goto L22;
        r15 = true;
    L23:
        if (r811 != 1) goto L25;
        r16 = true;
    L26:
        if (r811 != 2) goto L17;
    L25:
        r16 = false;
        goto L26
    L22:
        r15 = false;
        goto L23
    L5:
        r5 = false;
        goto L7
    }

    public static void applyChainConstraints(ConstraintWidgetContainer r6, LinearSystem r7, ArrayList<ConstraintWidget> r8, int r9) {
        int r0 = 0;
        if (r9 != 0) goto L5;
        int r1 = r6.mHorizontalChainsSize;
        ChainHead[] r2 = r6.mHorizontalChainsArray;
        int r3 = 0;
    L6:
        if (r0 >= r1) goto L13;
        ChainHead r4 = r2[r0];
        r4.define();
        if (r8 != null) goto L10;
    L11:
        applyChainConstraints(r6, r7, r9, r3, r4);
    L12:
        r0 = r0 + 1;
        goto L6
    L10:
        if (r8.contains(r4.mFirst) == false) goto L12;
    L13:
        return;
    L5:
        r1 = r6.mVerticalChainsSize;
        r2 = r6.mVerticalChainsArray;
        r3 = 2;
        goto L6
    }
}
