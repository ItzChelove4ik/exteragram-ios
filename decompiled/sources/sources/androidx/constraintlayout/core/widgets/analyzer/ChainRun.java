package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class ChainRun extends WidgetRun {
    private int mChainStyle;
    ArrayList<WidgetRun> mWidgets;

    public ChainRun(ConstraintWidget r1, int r2) {
        super(r1);
        this.mWidgets = new ArrayList();
        this.orientation = r2;
        build();
    }

    private void build() {
        ConstraintWidget r0 = this.mWidget;
        ConstraintWidget r1 = r0.getPreviousChainMember(this.orientation);
    L3:
        ConstraintWidget r6 = r1;
        ConstraintWidget r12 = r0;
        r0 = r6;
        if (r0 == null) goto L6;
        r1 = r0.getPreviousChainMember(this.orientation);
        goto L3
    L6:
        this.mWidget = r12;
        this.mWidgets.add(r12.getRun(this.orientation));
        ConstraintWidget r02 = r12.getNextChainMember(this.orientation);
    L7:
        ArrayList<WidgetRun> r13 = this.mWidgets;
        if (r02 == null) goto L10;
        r13.add(r02.getRun(this.orientation));
        r02 = r02.getNextChainMember(this.orientation);
        goto L7
    L10:
        int r03 = r13.size();
        int r2 = 0;
    L12:
        if (r2 >= r03) goto L19;
        WidgetRun r4 = r13.get(r2);
        r2 = r2 + 1;
        WidgetRun r42 = r4;
        int r5 = this.orientation;
        if (r5 == 0) goto L15;
        if (r5 != 1) goto L12;
        r42.mWidget.verticalChainRun = this;
        goto L12
    L15:
        r42.mWidget.horizontalChainRun = this;
        goto L12
    L19:
        if (this.orientation == 0) goto L21;
    L25:
        int r04 = this.orientation;
        ConstraintWidget r14 = this.mWidget;
        if (r04 != 0) goto L28;
        int r05 = r14.getHorizontalChainStyle();
    L29:
        this.mChainStyle = r05;
        return;
    L28:
        r05 = r14.getVerticalChainStyle();
        goto L29
    L21:
        if (((ConstraintWidgetContainer) this.mWidget.getParent()).isRtl() == false) goto L25;
        if (this.mWidgets.size() <= 1) goto L25;
        ArrayList<WidgetRun> r06 = this.mWidgets;
        this.mWidget = r06.get(r06.size() - 1).mWidget;
        goto L25
    }

    private ConstraintWidget getFirstVisibleWidget() {
        int r0 = 0;
    L4:
        if (r0 >= this.mWidgets.size()) goto L10;
        WidgetRun r1 = this.mWidgets.get(r0);
        if (r1.mWidget.getVisibility() != 8) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return r1.mWidget;
    L10:
        return null;
    }

    private ConstraintWidget getLastVisibleWidget() {
        int r0 = this.mWidgets.size() - 1;
    L3:
        if (r0 < 0) goto L9;
        WidgetRun r1 = this.mWidgets.get(r0);
        if (r1.mWidget.getVisibility() != 8) goto L7;
        r0 = r0 - 1;
        goto L3
    L7:
        return r1.mWidget;
    L9:
        return null;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void apply() {
        ArrayList<WidgetRun> r0 = this.mWidgets;
        int r1 = r0.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L5;
        WidgetRun r4 = r0.get(r3);
        r3 = r3 + 1;
        r4.apply();
        goto L3
    L5:
        int r02 = this.mWidgets.size();
        if (r02 >= 1) goto L8;
        return;
    L8:
        ConstraintWidget r32 = this.mWidgets.get(0).mWidget;
        ConstraintWidget r03 = this.mWidgets.get(r02 - 1).mWidget;
        if (this.orientation != 0) goto L20;
        ConstraintAnchor r12 = r32.mLeft;
        ConstraintAnchor r04 = r03.mRight;
        DependencyNode r33 = getTarget(r12, 0);
        int r13 = r12.getMargin();
        ConstraintWidget r42 = getFirstVisibleWidget();
        if (r42 == null) goto L13;
        r13 = r42.mLeft.getMargin();
    L13:
        if (r33 == null) goto L15;
        addTarget(this.start, r33, r13);
    L15:
        DependencyNode r14 = getTarget(r04, 0);
        int r05 = r04.getMargin();
        ConstraintWidget r2 = getLastVisibleWidget();
        if (r2 == null) goto L18;
        r05 = r2.mRight.getMargin();
    L18:
        if (r14 == null) goto L30;
        addTarget(this.end, r14, -r05);
    L30:
        this.start.updateDelegate = this;
        this.end.updateDelegate = this;
        return;
    L20:
        ConstraintAnchor r22 = r32.mTop;
        ConstraintAnchor r06 = r03.mBottom;
        DependencyNode r34 = getTarget(r22, 1);
        int r23 = r22.getMargin();
        ConstraintWidget r43 = getFirstVisibleWidget();
        if (r43 == null) goto L23;
        r23 = r43.mTop.getMargin();
    L23:
        if (r34 == null) goto L25;
        addTarget(this.start, r34, r23);
    L25:
        DependencyNode r15 = getTarget(r06, 1);
        int r07 = r06.getMargin();
        ConstraintWidget r24 = getLastVisibleWidget();
        if (r24 == null) goto L28;
        r07 = r24.mBottom.getMargin();
    L28:
        if (r15 == null) goto L30;
        addTarget(this.end, r15, -r07);
        goto L30
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        int r0 = 0;
    L4:
        if (r0 >= this.mWidgets.size()) goto L6;
        this.mWidgets.get(r0).applyToWidget();
        r0 = r0 + 1;
        goto L4
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void clear() {
        this.mRunGroup = null;
        ArrayList<WidgetRun> r3 = this.mWidgets;
        int r0 = r3.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        WidgetRun r2 = r3.get(r1);
        r1 = r1 + 1;
        r2.clear();
        goto L3
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public long getWrapDimension() {
        int r0 = this.mWidgets.size();
        long r1 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L5;
        r1 = ((r1 + r4.start.mMargin) + this.mWidgets.get(r3).getWrapDimension()) + r4.end.mMargin;
        r3 = r3 + 1;
        goto L3
    L5:
        return r1;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public boolean supportsWrapComputation() {
        int r0 = this.mWidgets.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        if (this.mWidgets.get(r2).supportsWrapComputation() == false) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return false;
    L8:
        return true;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("ChainRun ");
        if (this.orientation != 0) goto L5;
        String r1 = "horizontal : ";
    L6:
        r0.append(r1);
        ArrayList<WidgetRun> r5 = this.mWidgets;
        int r12 = r5.size();
        int r2 = 0;
    L7:
        if (r2 >= r12) goto L10;
        WidgetRun r3 = r5.get(r2);
        r2 = r2 + 1;
        r0.append("<");
        r0.append(r3);
        r0.append("> ");
        goto L7
    L10:
        return r0.toString();
    L5:
        r1 = "vertical : ";
        goto L6
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency r27) {
        if (this.start.resolved == true) goto L5;
        return;
    L5:
        if (this.end.resolved == false) goto L330;
        ConstraintWidget r1 = this.mWidget.getParent();
        if ((r1 instanceof ConstraintWidgetContainer) == false) goto L10;
        boolean r12 = ((ConstraintWidgetContainer) r1).isRtl();
    L11:
        int r2 = this.end.value - this.start.value;
        int r4 = this.mWidgets.size();
        int r5 = 0;
    L12:
        int r6 = -1;
        int r7 = 8;
        if (r5 >= r4) goto L17;
        if (this.mWidgets.get(r5).mWidget.getVisibility() != 8) goto L18;
        r5 = r5 + 1;
    L18:
        int r8 = r4 - 1;
        int r9 = r8;
    L19:
        if (r9 < 0) goto L24;
        if (this.mWidgets.get(r9).mWidget.getVisibility() != 8) goto L23;
        r9 = r9 - 1;
        goto L19
    L23:
        r6 = r9;
    L24:
        int r92 = 0;
    L26:
        if (r92 >= 2) goto L75;
        int r13 = 0;
        int r14 = 0;
        int r15 = 0;
        int r16 = 0;
        float r17 = 0.0f;
    L28:
        if (r13 >= r4) goto L69;
        WidgetRun r10 = this.mWidgets.get(r13);
        if (r10.mWidget.getVisibility() != r7) goto L32;
        boolean r20 = r12;
    L68:
        r13 = r13 + 1;
        r12 = r20;
        r7 = 8;
        goto L28
    L32:
        r16 = r16 + 1;
        if (r13 <= 0) goto L36;
        if (r13 < r5) goto L36;
        r14 = r14 + r10.start.mMargin;
    L36:
        DimensionDependency r3 = r10.mDimension;
        int r11 = r3.value;
        if (r10.mDimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L39;
        boolean r72 = true;
    L40:
        if (r72 == false) goto L52;
        int r32 = this.orientation;
        if (r32 != 0) goto L47;
        if (r10.mWidget.mHorizontalRun.mDimension.resolved == true) goto L47;
        return;
    L47:
        if (r32 == 1) goto L49;
    L51:
        r20 = r12;
    L60:
        if (r72 == true) goto L64;
        r15 = r15 + 1;
        float r18 = r10.mWidget.mWeight[this.orientation];
        if (r18 < 0.0f) goto L65;
        r17 = r17 + r18;
    L65:
        if (r13 >= r8) goto L68;
        if (r13 >= r6) goto L68;
        r14 = r14 + (-r10.end.mMargin);
        goto L68
    L64:
        r14 = r14 + r11;
        goto L65
    L49:
        if (r10.mWidget.mVerticalRun.mDimension.resolved == true) goto L51;
        return;
    L52:
        r20 = r12;
        if (r10.matchConstraintsType != 1) goto L58;
        if (r92 != 0) goto L58;
        r11 = r3.wrapValue;
        r15 = r15 + 1;
    L56:
        r72 = true;
    L58:
        if (r3.resolved == false) goto L60;
    L39:
        r72 = false;
        goto L40
    L69:
        boolean r202 = r12;
        float r272 = 0.0f;
        if (r14 < r2) goto L74;
        if (r15 == 0) goto L74;
        r92 = r92 + 1;
        r12 = r202;
        r7 = 8;
    L74:
        int r19 = r16;
    L76:
        int r33 = this.start.value;
        if (r202 == false) goto L79;
        r33 = this.end.value;
    L79:
        float r73 = 0.5f;
        if (r14 > r2) goto L82;
    L85:
        if (r15 <= 0) goto L138;
        float r93 = r2 - r14;
        int r102 = (int) ((r93 / r15) + 0.5f);
        int r112 = 0;
        int r122 = 0;
    L87:
        if (r112 >= r4) goto L118;
        WidgetRun r132 = this.mWidgets.get(r112);
        float r162 = r73;
        int r21 = r33;
        if (r132.mWidget.getVisibility() != 8) goto L92;
    L90:
        float r22 = r93;
        int r23 = r102;
        int r24 = r112;
        r122 = r122;
    L117:
        r112 = r24 + 1;
        r73 = r162;
        r33 = r21;
        r93 = r22;
        r102 = r23;
        goto L87
    L92:
        if (r132.mDimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L90;
        DimensionDependency r34 = r132.mDimension;
        if (r34.resolved == true) goto L90;
        if (r17 <= r272) goto L99;
        int r74 = (int) (((r132.mWidget.mWeight[this.orientation] * r93) / r17) + r162);
    L98:
        r22 = r93;
        int r94 = this.orientation;
        ConstraintWidget r95 = r132.mWidget;
        if (r94 != 0) goto L104;
        r23 = r102;
        int r103 = r95.mMatchConstraintMaxWidth;
        int r96 = r95.mMatchConstraintMinWidth;
    L103:
        r24 = r112;
        int r25 = r122;
        if (r132.matchConstraintsType != 1) goto L108;
        int r35 = Math.min(r74, r34.wrapValue);
    L109:
        int r36 = Math.max(r96, r35);
        if (r103 <= 0) goto L112;
        r36 = Math.min(r103, r36);
    L112:
        if (r36 == r74) goto L114;
        r122 = r25 + 1;
        r74 = r36;
    L115:
        r132.mDimension.resolve(r74);
        goto L117
    L114:
        r122 = r25;
        goto L115
    L108:
        r35 = r74;
        goto L109
    L104:
        r23 = r102;
        r103 = r95.mMatchConstraintMaxHeight;
        r96 = r95.mMatchConstraintMinHeight;
        goto L103
    L99:
        r74 = r102;
        goto L98
    L118:
        int r212 = r33;
        float r163 = r73;
        int r252 = r122;
        if (r252 <= 0) goto L133;
        r15 = r15 - r252;
        int r37 = 0;
        r14 = 0;
    L121:
        if (r37 >= r4) goto L133;
        WidgetRun r75 = this.mWidgets.get(r37);
        if (r75.mWidget.getVisibility() == 8) goto L132;
        if (r37 <= 0) goto L128;
        if (r37 < r5) goto L128;
        r14 = r14 + r75.start.mMargin;
    L128:
        r14 = r14 + r75.mDimension.value;
        if (r37 >= r8) goto L132;
        if (r37 >= r6) goto L132;
        r14 = r14 + (-r75.end.mMargin);
    L132:
        r37 = r37 + 1;
    L133:
        int r76 = 2;
        if (this.mChainStyle != 2) goto L137;
        if (r252 != 0) goto L137;
        int r38 = 0;
        this.mChainStyle = 0;
    L139:
        if (r14 <= r2) goto L141;
        this.mChainStyle = r76;
    L141:
        if (r19 <= 0) goto L145;
        if (r15 != 0) goto L145;
        if (r5 != r6) goto L145;
        this.mChainStyle = r76;
    L145:
        int r77 = this.mChainStyle;
        if (r77 != 1) goto L195;
        if (r19 <= 1) goto L149;
        int r26 = (r2 - r14) / (r19 - 1);
    L152:
        if (r15 <= 0) goto L154;
        r26 = r38;
    L154:
        int r110 = r212;
    L155:
        if (r38 >= r4) goto L333;
        if (r202 == false) goto L158;
        int r78 = r4 - (r38 + 1);
    L159:
        WidgetRun r79 = this.mWidgets.get(r78);
        if (r79.mWidget.getVisibility() != 8) goto L162;
        r79.start.resolve(r110);
        r79.end.resolve(r110);
    L194:
        r38 = r38 + 1;
        goto L155
    L162:
        if (r38 <= 0) goto L166;
        if (r202 == false) goto L165;
        r110 = r110 - r26;
        goto L166
    L165:
        r110 = r110 + r26;
    L166:
        if (r38 <= 0) goto L172;
        if (r38 < r5) goto L172;
        DependencyNode r97 = r79.start;
        if (r202 == false) goto L171;
        r110 = r110 - r97.mMargin;
        goto L172
    L171:
        r110 = r110 + r97.mMargin;
    L172:
        if (r202 == false) goto L174;
        r79.end.resolve(r110);
    L175:
        DimensionDependency r98 = r79.mDimension;
        int r104 = r98.value;
        if (r79.mDimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L178;
    L180:
        if (r202 == false) goto L182;
        r110 = r110 - r104;
    L183:
        if (r202 == false) goto L186;
        r79.start.resolve(r110);
    L187:
        r79.mResolved = true;
        if (r38 >= r8) goto L194;
        if (r38 >= r6) goto L194;
        DependencyNode r710 = r79.end;
        if (r202 == false) goto L193;
        r110 = r110 - (-r710.mMargin);
        goto L194
    L193:
        r110 = r110 + (-r710.mMargin);
        goto L194
    L186:
        r79.end.resolve(r110);
        goto L187
    L182:
        r110 = r110 + r104;
        goto L183
    L178:
        if (r79.matchConstraintsType != 1) goto L180;
        r104 = r98.wrapValue;
        goto L180
    L174:
        r79.start.resolve(r110);
        goto L175
    L158:
        r78 = r38;
        goto L159
    L333:
        return;
    L149:
        if (r19 != 1) goto L151;
        r26 = (r2 - r14) / 2;
        goto L152
    L151:
        r26 = r38;
        goto L152
    L195:
        if (r77 != 0) goto L238;
        int r28 = (r2 - r14) / (r19 + 1);
        if (r15 <= 0) goto L199;
        r28 = r38;
    L199:
        int r111 = r212;
    L200:
        if (r38 >= r4) goto L334;
        if (r202 == false) goto L203;
        int r711 = r4 - (r38 + 1);
    L204:
        WidgetRun r712 = this.mWidgets.get(r711);
        if (r712.mWidget.getVisibility() != 8) goto L207;
        r712.start.resolve(r111);
        r712.end.resolve(r111);
    L236:
        r38 = r38 + 1;
        goto L200
    L207:
        if (r202 == false) goto L209;
        int r113 = r111 - r28;
    L210:
        if (r38 <= 0) goto L216;
        if (r38 < r5) goto L216;
        DependencyNode r99 = r712.start;
        if (r202 == false) goto L215;
        r113 = r113 - r99.mMargin;
        goto L216
    L215:
        r113 = r113 + r99.mMargin;
    L216:
        if (r202 == false) goto L218;
        r712.end.resolve(r113);
    L219:
        DimensionDependency r910 = r712.mDimension;
        int r105 = r910.value;
        if (r712.mDimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L222;
    L224:
        if (r202 == false) goto L226;
        r111 = r113 - r105;
    L227:
        if (r202 == false) goto L229;
        r712.start.resolve(r111);
    L230:
        if (r38 >= r8) goto L236;
        if (r38 >= r6) goto L236;
        DependencyNode r713 = r712.end;
        if (r202 == false) goto L235;
        r111 = r111 - (-r713.mMargin);
        goto L236
    L235:
        r111 = r111 + (-r713.mMargin);
        goto L236
    L229:
        r712.end.resolve(r111);
        goto L230
    L226:
        r111 = r113 + r105;
        goto L227
    L222:
        if (r712.matchConstraintsType != 1) goto L224;
        r105 = Math.min(r105, r910.wrapValue);
        goto L224
    L218:
        r712.start.resolve(r113);
        goto L219
    L209:
        r113 = r111 + r28;
        goto L210
    L203:
        r711 = r38;
        goto L204
    L334:
        return;
    L238:
        if (r77 != 2) goto L329;
        int r114 = this.orientation;
        ConstraintWidget r714 = this.mWidget;
        if (r114 != 0) goto L242;
        float r115 = r714.getHorizontalBiasPercent();
    L243:
        if (r202 == false) goto L245;
        r115 = 1.0f - r115;
    L245:
        int r116 = (int) (((r2 - r14) * r115) + r163);
        if (r116 < 0) goto L248;
        if (r15 > 0) goto L248;
    L249:
        if (r202 == false) goto L251;
        int r117 = r212 - r116;
    L252:
        if (r38 >= r4) goto L335;
        if (r202 == false) goto L255;
        int r29 = r4 - (r38 + 1);
    L256:
        WidgetRun r210 = this.mWidgets.get(r29);
        if (r210.mWidget.getVisibility() != 8) goto L259;
        r210.start.resolve(r117);
        r210.end.resolve(r117);
    L286:
        r38 = r38 + 1;
        goto L252
    L259:
        if (r38 <= 0) goto L265;
        if (r38 < r5) goto L265;
        DependencyNode r715 = r210.start;
        if (r202 == false) goto L264;
        r117 = r117 - r715.mMargin;
        goto L265
    L264:
        r117 = r117 + r715.mMargin;
    L265:
        if (r202 == false) goto L267;
        r210.end.resolve(r117);
    L268:
        DimensionDependency r716 = r210.mDimension;
        int r911 = r716.value;
        if (r210.mDimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L274;
        if (r210.matchConstraintsType != 1) goto L274;
        r911 = r716.wrapValue;
    L274:
        if (r202 == false) goto L276;
        r117 = r117 - r911;
    L277:
        if (r202 == false) goto L279;
        r210.start.resolve(r117);
    L280:
        if (r38 >= r8) goto L286;
        if (r38 >= r6) goto L286;
        DependencyNode r211 = r210.end;
        if (r202 == false) goto L285;
        r117 = r117 - (-r211.mMargin);
        goto L286
    L285:
        r117 = r117 + (-r211.mMargin);
        goto L286
    L279:
        r210.end.resolve(r117);
        goto L280
    L276:
        r117 = r117 + r911;
        goto L277
    L267:
        r210.start.resolve(r117);
        goto L268
    L255:
        r29 = r38;
        goto L256
    L335:
        return;
    L251:
        r117 = r212 + r116;
    L248:
        r116 = r38;
        goto L249
    L242:
        r115 = r714.getVerticalBiasPercent();
        goto L243
    L329:
        return;
    L137:
        r38 = 0;
        goto L139
    L138:
        r212 = r33;
        r163 = 0.5f;
        r38 = 0;
        r76 = 2;
        goto L139
    L82:
        if (r202 == false) goto L84;
        r33 = r33 + ((int) (((r14 - r2) / 2.0f) + 0.5f));
        goto L85
    L84:
        r33 = r33 - ((int) (((r14 - r2) / 2.0f) + 0.5f));
        goto L85
    L75:
        r202 = r12;
        r272 = 0.0f;
        r17 = 0.0f;
        r19 = 0;
        r14 = 0;
        r15 = 0;
        goto L76
    L17:
        r5 = -1;
        goto L18
    L10:
        r12 = false;
        goto L11
    }
}
