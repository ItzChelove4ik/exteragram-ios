package com.caverock.androidsvg;

import android.graphics.Matrix;
import android.util.Log;
import android.util.Xml;
import com.caverock.androidsvg.CSSParser;
import com.caverock.androidsvg.PreserveAspectRatio;
import com.caverock.androidsvg.SVG;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaStatus;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Opcodes;
import org.mvel2.asm.signature.SignatureVisitor;
import org.telegram.messenger.ImageLoader;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.DefaultHandler2;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes4.dex */
class SVGParser {
    private SVG.SvgContainer currentElement;
    private int ignoreDepth;
    private boolean ignoring;
    private boolean inMetadataElement;
    private boolean inStyleElement;
    private StringBuilder metadataElementContents;
    private SVGElem metadataTag;
    private StringBuilder styleElementContents;
    private SVG svgDocument;

    /* renamed from: com.caverock.androidsvg.SVGParser$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr = null;
        static final /* synthetic */ int[] $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem = null;

        static {
            int[] r0 = new int[SVGAttr.values().length];
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr = r0;
            r0[SVGAttr.x.ordinal()] = 1;     // Catch: NoSuchFieldError -> L146
        L286:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.y.ordinal()] = 2;     // Catch: NoSuchFieldError -> L147
        L302:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.width.ordinal()] = 3;     // Catch: NoSuchFieldError -> L148
        L312:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.height.ordinal()] = 4;     // Catch: NoSuchFieldError -> L149
        L316:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.version.ordinal()] = 5;     // Catch: NoSuchFieldError -> L150
        L336:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.href.ordinal()] = 6;     // Catch: NoSuchFieldError -> L151
        L340:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.preserveAspectRatio.ordinal()] = 7;     // Catch: NoSuchFieldError -> L152
        L362:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.d.ordinal()] = 8;     // Catch: NoSuchFieldError -> L153
        L366:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.pathLength.ordinal()] = 9;     // Catch: NoSuchFieldError -> L154
        L384:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.rx.ordinal()] = 10;     // Catch: NoSuchFieldError -> L155
        L390:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.ry.ordinal()] = 11;     // Catch: NoSuchFieldError -> L156
        L406:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.cx.ordinal()] = 12;     // Catch: NoSuchFieldError -> L157
        L410:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.cy.ordinal()] = 13;     // Catch: NoSuchFieldError -> L158
        L428:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.r.ordinal()] = 14;     // Catch: NoSuchFieldError -> L159
        L432:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.x1.ordinal()] = 15;     // Catch: NoSuchFieldError -> L160
        L446:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.y1.ordinal()] = 16;     // Catch: NoSuchFieldError -> L161
        L452:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.x2.ordinal()] = 17;     // Catch: NoSuchFieldError -> L162
        L470:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.y2.ordinal()] = 18;     // Catch: NoSuchFieldError -> L163
        L474:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.dx.ordinal()] = 19;     // Catch: NoSuchFieldError -> L164
        L488:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.dy.ordinal()] = 20;     // Catch: NoSuchFieldError -> L165
        L494:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.requiredFeatures.ordinal()] = 21;     // Catch: NoSuchFieldError -> L166
        L270:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.requiredExtensions.ordinal()] = 22;     // Catch: NoSuchFieldError -> L167
        L274:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.systemLanguage.ordinal()] = 23;     // Catch: NoSuchFieldError -> L168
        L294:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.requiredFormats.ordinal()] = 24;     // Catch: NoSuchFieldError -> L169
        L298:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.requiredFonts.ordinal()] = 25;     // Catch: NoSuchFieldError -> L170
        L320:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.refX.ordinal()] = 26;     // Catch: NoSuchFieldError -> L171
        L322:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.refY.ordinal()] = 27;     // Catch: NoSuchFieldError -> L172
        L342:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.markerWidth.ordinal()] = 28;     // Catch: NoSuchFieldError -> L173
        L346:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.markerHeight.ordinal()] = 29;     // Catch: NoSuchFieldError -> L174
        L368:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.markerUnits.ordinal()] = 30;     // Catch: NoSuchFieldError -> L175
        L370:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.orient.ordinal()] = 31;     // Catch: NoSuchFieldError -> L176
        L394:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.gradientUnits.ordinal()] = 32;     // Catch: NoSuchFieldError -> L177
        L396:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.gradientTransform.ordinal()] = 33;     // Catch: NoSuchFieldError -> L178
        L412:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.spreadMethod.ordinal()] = 34;     // Catch: NoSuchFieldError -> L179
        L416:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fx.ordinal()] = 35;     // Catch: NoSuchFieldError -> L180
        L436:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fy.ordinal()] = 36;     // Catch: NoSuchFieldError -> L181
        L438:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.offset.ordinal()] = 37;     // Catch: NoSuchFieldError -> L182
        L454:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.clipPathUnits.ordinal()] = 38;     // Catch: NoSuchFieldError -> L183
        L458:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.startOffset.ordinal()] = 39;     // Catch: NoSuchFieldError -> L184
        L476:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.patternUnits.ordinal()] = 40;     // Catch: NoSuchFieldError -> L185
        L478:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.patternContentUnits.ordinal()] = 41;     // Catch: NoSuchFieldError -> L186
        L498:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.patternTransform.ordinal()] = 42;     // Catch: NoSuchFieldError -> L187
        L500:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.maskUnits.ordinal()] = 43;     // Catch: NoSuchFieldError -> L188
        L276:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.maskContentUnits.ordinal()] = 44;     // Catch: NoSuchFieldError -> L189
        L282:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.style.ordinal()] = 45;     // Catch: NoSuchFieldError -> L190
        L300:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.CLASS.ordinal()] = 46;     // Catch: NoSuchFieldError -> L191
        L306:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fill.ordinal()] = 47;     // Catch: NoSuchFieldError -> L192
        L324:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fill_rule.ordinal()] = 48;     // Catch: NoSuchFieldError -> L193
        L328:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fill_opacity.ordinal()] = 49;     // Catch: NoSuchFieldError -> L194
        L350:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.stroke.ordinal()] = 50;     // Catch: NoSuchFieldError -> L195
        L354:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.stroke_opacity.ordinal()] = 51;     // Catch: NoSuchFieldError -> L196
        L372:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.stroke_width.ordinal()] = 52;     // Catch: NoSuchFieldError -> L197
        L378:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.stroke_linecap.ordinal()] = 53;     // Catch: NoSuchFieldError -> L198
        L400:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.stroke_linejoin.ordinal()] = 54;     // Catch: NoSuchFieldError -> L199
        L402:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.stroke_miterlimit.ordinal()] = 55;     // Catch: NoSuchFieldError -> L200
        L418:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.stroke_dasharray.ordinal()] = 56;     // Catch: NoSuchFieldError -> L201
        L422:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.stroke_dashoffset.ordinal()] = 57;     // Catch: NoSuchFieldError -> L202
        L440:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.opacity.ordinal()] = 58;     // Catch: NoSuchFieldError -> L203
        L442:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.color.ordinal()] = 59;     // Catch: NoSuchFieldError -> L204
        L462:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.font.ordinal()] = 60;     // Catch: NoSuchFieldError -> L205
        L464:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.font_family.ordinal()] = 61;     // Catch: NoSuchFieldError -> L206
        L480:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.font_size.ordinal()] = 62;     // Catch: NoSuchFieldError -> L207
        L484:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.font_weight.ordinal()] = 63;     // Catch: NoSuchFieldError -> L208
        L504:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.font_style.ordinal()] = 64;     // Catch: NoSuchFieldError -> L209
        L266:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.text_decoration.ordinal()] = 65;     // Catch: NoSuchFieldError -> L210
        L288:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.direction.ordinal()] = 66;     // Catch: NoSuchFieldError -> L211
        L290:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.text_anchor.ordinal()] = 67;     // Catch: NoSuchFieldError -> L212
        L310:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.overflow.ordinal()] = 68;     // Catch: NoSuchFieldError -> L213
        L314:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.marker.ordinal()] = 69;     // Catch: NoSuchFieldError -> L214
        L332:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.marker_start.ordinal()] = 70;     // Catch: NoSuchFieldError -> L215
        L338:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.marker_mid.ordinal()] = 71;     // Catch: NoSuchFieldError -> L216
        L360:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.marker_end.ordinal()] = 72;     // Catch: NoSuchFieldError -> L217
        L364:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.display.ordinal()] = 73;     // Catch: NoSuchFieldError -> L218
        L382:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.visibility.ordinal()] = 74;     // Catch: NoSuchFieldError -> L219
        L388:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.stop_color.ordinal()] = 75;     // Catch: NoSuchFieldError -> L220
        L404:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.stop_opacity.ordinal()] = 76;     // Catch: NoSuchFieldError -> L221
        L408:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.clip.ordinal()] = 77;     // Catch: NoSuchFieldError -> L222
        L426:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.clip_path.ordinal()] = 78;     // Catch: NoSuchFieldError -> L223
        L430:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.clip_rule.ordinal()] = 79;     // Catch: NoSuchFieldError -> L224
        L444:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.mask.ordinal()] = 80;     // Catch: NoSuchFieldError -> L225
        L450:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.solid_color.ordinal()] = 81;     // Catch: NoSuchFieldError -> L226
        L468:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.solid_opacity.ordinal()] = 82;     // Catch: NoSuchFieldError -> L227
        L472:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.viewport_fill.ordinal()] = 83;     // Catch: NoSuchFieldError -> L228
        L486:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.viewport_fill_opacity.ordinal()] = 84;     // Catch: NoSuchFieldError -> L229
        L492:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.vector_effect.ordinal()] = 85;     // Catch: NoSuchFieldError -> L230
        L268:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.image_rendering.ordinal()] = 86;     // Catch: NoSuchFieldError -> L231
        L272:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.viewBox.ordinal()] = 87;     // Catch: NoSuchFieldError -> L232
        L292:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.type.ordinal()] = 88;     // Catch: NoSuchFieldError -> L233
        L296:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.media.ordinal()] = 89;     // Catch: NoSuchFieldError -> L234
        L113:
            int[] r02 = new int[SVGElem.values().length];
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem = r02;
            r02[SVGElem.svg.ordinal()] = 1;     // Catch: NoSuchFieldError -> L235
        L334:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.g.ordinal()] = 2;     // Catch: NoSuchFieldError -> L236
        L344:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.f35a.ordinal()] = 3;     // Catch: NoSuchFieldError -> L237
        L348:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.defs.ordinal()] = 4;     // Catch: NoSuchFieldError -> L238
        L358:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.use.ordinal()] = 5;     // Catch: NoSuchFieldError -> L239
        L376:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.path.ordinal()] = 6;     // Catch: NoSuchFieldError -> L240
        L386:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.rect.ordinal()] = 7;     // Catch: NoSuchFieldError -> L241
        L392:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.circle.ordinal()] = 8;     // Catch: NoSuchFieldError -> L242
        L398:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.ellipse.ordinal()] = 9;     // Catch: NoSuchFieldError -> L243
        L414:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.line.ordinal()] = 10;     // Catch: NoSuchFieldError -> L244
        L420:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.polyline.ordinal()] = 11;     // Catch: NoSuchFieldError -> L245
        L424:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.polygon.ordinal()] = 12;     // Catch: NoSuchFieldError -> L246
        L434:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.text.ordinal()] = 13;     // Catch: NoSuchFieldError -> L247
        L448:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.tspan.ordinal()] = 14;     // Catch: NoSuchFieldError -> L248
        L456:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.tref.ordinal()] = 15;     // Catch: NoSuchFieldError -> L249
        L460:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.SWITCH.ordinal()] = 16;     // Catch: NoSuchFieldError -> L250
        L466:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.symbol.ordinal()] = 17;     // Catch: NoSuchFieldError -> L251
        L482:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.marker.ordinal()] = 18;     // Catch: NoSuchFieldError -> L252
        L490:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.linearGradient.ordinal()] = 19;     // Catch: NoSuchFieldError -> L253
        L496:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.radialGradient.ordinal()] = 20;     // Catch: NoSuchFieldError -> L254
        L502:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.stop.ordinal()] = 21;     // Catch: NoSuchFieldError -> L255
        L278:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.title.ordinal()] = 22;     // Catch: NoSuchFieldError -> L256
        L284:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.desc.ordinal()] = 23;     // Catch: NoSuchFieldError -> L257
        L304:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.clipPath.ordinal()] = 24;     // Catch: NoSuchFieldError -> L258
        L308:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.textPath.ordinal()] = 25;     // Catch: NoSuchFieldError -> L259
        L326:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.pattern.ordinal()] = 26;     // Catch: NoSuchFieldError -> L260
        L330:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.image.ordinal()] = 27;     // Catch: NoSuchFieldError -> L261
        L352:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.view.ordinal()] = 28;     // Catch: NoSuchFieldError -> L262
        L356:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.mask.ordinal()] = 29;     // Catch: NoSuchFieldError -> L263
        L374:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.style.ordinal()] = 30;     // Catch: NoSuchFieldError -> L264
        L380:
            $SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.solidColor.ordinal()] = 31;     // Catch: NoSuchFieldError -> L265
            return;
        }
    }

    public static class AspectRatioKeywords {
        private static final Map<String, PreserveAspectRatio.Alignment> aspectRatioKeywords = null;

        static {
            HashMap r0 = new HashMap(10);
            aspectRatioKeywords = r0;
            r0.put("none", PreserveAspectRatio.Alignment.none);
            r0.put("xMinYMin", PreserveAspectRatio.Alignment.xMinYMin);
            r0.put("xMidYMin", PreserveAspectRatio.Alignment.xMidYMin);
            r0.put("xMaxYMin", PreserveAspectRatio.Alignment.xMaxYMin);
            r0.put("xMinYMid", PreserveAspectRatio.Alignment.xMinYMid);
            r0.put("xMidYMid", PreserveAspectRatio.Alignment.xMidYMid);
            r0.put("xMaxYMid", PreserveAspectRatio.Alignment.xMaxYMid);
            r0.put("xMinYMax", PreserveAspectRatio.Alignment.xMinYMax);
            r0.put("xMidYMax", PreserveAspectRatio.Alignment.xMidYMax);
            r0.put("xMaxYMax", PreserveAspectRatio.Alignment.xMaxYMax);
        }

        public static PreserveAspectRatio.Alignment get(String r1) {
            return aspectRatioKeywords.get(r1);
        }
    }

    public static class ColourKeywords {
        private static final Map<String, Integer> colourKeywords = null;

        static {
            HashMap r0 = new HashMap(47);
            colourKeywords = r0;
            r0.put("aliceblue", -984833);
            r0.put("antiquewhite", -332841);
            r0.put("aqua", -16711681);
            r0.put("aquamarine", -8388652);
            r0.put("azure", -983041);
            r0.put("beige", -657956);
            r0.put("bisque", -6972);
            r0.put("black", -16777216);
            r0.put("blanchedalmond", -5171);
            r0.put("blue", -16776961);
            r0.put("blueviolet", -7722014);
            r0.put("brown", -5952982);
            r0.put("burlywood", -2180985);
            r0.put("cadetblue", -10510688);
            r0.put("chartreuse", -8388864);
            r0.put("chocolate", -2987746);
            r0.put("coral", -32944);
            r0.put("cornflowerblue", -10185235);
            r0.put("cornsilk", -1828);
            r0.put("crimson", -2354116);
            r0.put("cyan", -16711681);
            r0.put("darkblue", -16777077);
            r0.put("darkcyan", -16741493);
            r0.put("darkgoldenrod", -4684277);
            r0.put("darkgray", -5658199);
            r0.put("darkgreen", -16751616);
            r0.put("darkgrey", -5658199);
            r0.put("darkkhaki", -4343957);
            r0.put("darkmagenta", -7667573);
            r0.put("darkolivegreen", -11179217);
            r0.put("darkorange", -29696);
            r0.put("darkorchid", -6737204);
            r0.put("darkred", -7667712);
            r0.put("darksalmon", -1468806);
            r0.put("darkseagreen", -7357297);
            r0.put("darkslateblue", -12042869);
            r0.put("darkslategray", -13676721);
            r0.put("darkslategrey", -13676721);
            r0.put("darkturquoise", -16724271);
            r0.put("darkviolet", -7077677);
            r0.put("deeppink", -60269);
            r0.put("deepskyblue", -16728065);
            r0.put("dimgray", -9868951);
            r0.put("dimgrey", -9868951);
            r0.put("dodgerblue", -14774017);
            r0.put("firebrick", -5103070);
            r0.put("floralwhite", -1296);
            r0.put("forestgreen", -14513374);
            r0.put("fuchsia", -65281);
            r0.put("gainsboro", -2302756);
            r0.put("ghostwhite", -460545);
            r0.put("gold", -10496);
            r0.put("goldenrod", -2448096);
            r0.put("gray", -8355712);
            r0.put("green", -16744448);
            r0.put("greenyellow", -5374161);
            r0.put("grey", -8355712);
            r0.put("honeydew", -983056);
            r0.put("hotpink", -38476);
            r0.put("indianred", -3318692);
            r0.put("indigo", -11861886);
            r0.put("ivory", -16);
            r0.put("khaki", -989556);
            r0.put("lavender", -1644806);
            r0.put("lavenderblush", -3851);
            r0.put("lawngreen", -8586240);
            r0.put("lemonchiffon", -1331);
            r0.put("lightblue", -5383962);
            r0.put("lightcoral", -1015680);
            r0.put("lightcyan", -2031617);
            r0.put("lightgoldenrodyellow", -329006);
            r0.put("lightgray", -2894893);
            r0.put("lightgreen", -7278960);
            r0.put("lightgrey", -2894893);
            r0.put("lightpink", -18751);
            r0.put("lightsalmon", -24454);
            r0.put("lightseagreen", -14634326);
            r0.put("lightskyblue", -7876870);
            r0.put("lightslategray", -8943463);
            r0.put("lightslategrey", -8943463);
            r0.put("lightsteelblue", -5192482);
            r0.put("lightyellow", -32);
            r0.put("lime", -16711936);
            r0.put("limegreen", -13447886);
            r0.put("linen", -331546);
            r0.put("magenta", -65281);
            r0.put("maroon", -8388608);
            r0.put("mediumaquamarine", -10039894);
            r0.put("mediumblue", -16777011);
            r0.put("mediumorchid", -4565549);
            r0.put("mediumpurple", -7114533);
            r0.put("mediumseagreen", -12799119);
            r0.put("mediumslateblue", -8689426);
            r0.put("mediumspringgreen", -16713062);
            r0.put("mediumturquoise", -12004916);
            r0.put("mediumvioletred", -3730043);
            r0.put("midnightblue", -15132304);
            r0.put("mintcream", -655366);
            r0.put("mistyrose", -6943);
            r0.put("moccasin", -6987);
            r0.put("navajowhite", -8531);
            r0.put("navy", -16777088);
            r0.put("oldlace", -133658);
            r0.put("olive", -8355840);
            r0.put("olivedrab", -9728477);
            r0.put("orange", -23296);
            r0.put("orangered", -47872);
            r0.put("orchid", -2461482);
            r0.put("palegoldenrod", -1120086);
            r0.put("palegreen", -6751336);
            r0.put("paleturquoise", -5247250);
            r0.put("palevioletred", -2396013);
            r0.put("papayawhip", -4139);
            r0.put("peachpuff", -9543);
            r0.put("peru", -3308225);
            r0.put("pink", -16181);
            r0.put("plum", -2252579);
            r0.put("powderblue", -5185306);
            r0.put("purple", -8388480);
            r0.put("rebeccapurple", -10079335);
            r0.put(PluginsConstants.Settings.RED, Integer.valueOf(Opcodes.V_PREVIEW));
            r0.put("rosybrown", -4419697);
            r0.put("royalblue", -12490271);
            r0.put("saddlebrown", -7650029);
            r0.put("salmon", -360334);
            r0.put("sandybrown", -744352);
            r0.put("seagreen", -13726889);
            r0.put("seashell", -2578);
            r0.put("sienna", -6270419);
            r0.put("silver", -4144960);
            r0.put("skyblue", -7876885);
            r0.put("slateblue", -9807155);
            r0.put("slategray", -9404272);
            r0.put("slategrey", -9404272);
            r0.put("snow", -1286);
            r0.put("springgreen", -16711809);
            r0.put("steelblue", -12156236);
            r0.put("tan", -2968436);
            r0.put("teal", -16744320);
            r0.put("thistle", -2572328);
            r0.put("tomato", -40121);
            r0.put("turquoise", -12525360);
            r0.put("violet", -1146130);
            r0.put("wheat", -663885);
            r0.put("white", -1);
            r0.put("whitesmoke", -657931);
            r0.put("yellow", -256);
            r0.put("yellowgreen", -6632142);
            r0.put("transparent", 0);
        }

        public static Integer get(String r1) {
            return colourKeywords.get(r1);
        }
    }

    public static class FontSizeKeywords {
        private static final Map<String, SVG.Length> fontSizeKeywords = null;

        static {
            HashMap r0 = new HashMap(9);
            fontSizeKeywords = r0;
            SVG.Unit r2 = SVG.Unit.pt;
            r0.put("xx-small", new SVG.Length(0.694f, r2));
            r0.put("x-small", new SVG.Length(0.833f, r2));
            r0.put("small", new SVG.Length(10.0f, r2));
            r0.put("medium", new SVG.Length(12.0f, r2));
            r0.put("large", new SVG.Length(14.4f, r2));
            r0.put("x-large", new SVG.Length(17.3f, r2));
            r0.put("xx-large", new SVG.Length(20.7f, r2));
            SVG.Unit r22 = SVG.Unit.percent;
            r0.put("smaller", new SVG.Length(83.33f, r22));
            r0.put("larger", new SVG.Length(120.0f, r22));
        }

        public static SVG.Length get(String r1) {
            return fontSizeKeywords.get(r1);
        }
    }

    public static class FontWeightKeywords {
        private static final Map<String, Integer> fontWeightKeywords = null;

        static {
            HashMap r0 = new HashMap(13);
            fontWeightKeywords = r0;
            Integer r1 = Integer.valueOf(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN);
            r0.put("normal", r1);
            r0.put("bold", 700);
            r0.put("bolder", 1);
            r0.put("lighter", -1);
            r0.put("100", 100);
            r0.put("200", 200);
            r0.put("300", 300);
            r0.put("400", r1);
            r0.put("500", Integer.valueOf(MediaError.DetailedErrorCode.SEGMENT_UNKNOWN));
            r0.put("600", Integer.valueOf(MediaError.DetailedErrorCode.TEXT_UNKNOWN));
            r0.put("700", 700);
            r0.put("800", 800);
            r0.put("900", 900);
        }

        public static Integer get(String r1) {
            return fontWeightKeywords.get(r1);
        }
    }

    public class SAXHandler extends DefaultHandler2 {
        final /* synthetic */ SVGParser this$0;

        private SAXHandler(SVGParser r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ SAXHandler(SVGParser r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void characters(char[] r2, int r3, int r4) {
            SVGParser.access$300(this.this$0, new String(r2, r3, r4));
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endDocument() {
            SVGParser.access$500(this.this$0);
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String r1, String r2, String r3) {
            SVGParser.access$400(this.this$0, r1, r2, r3);
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void processingInstruction(String r2, String r3) {
            TextScanner r0 = new TextScanner(r3);
            Map r32 = SVGParser.access$600(this.this$0, r0);
            SVGParser.access$700(this.this$0, r2, r32);
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startDocument() {
            SVGParser.access$100(this.this$0);
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String r1, String r2, String r3, Attributes r4) {
            SVGParser.access$200(this.this$0, r1, r2, r3, r4);
        }
    }

    public enum SVGAttr extends Enum<SVGAttr> {
        private static final /* synthetic */ SVGAttr[] $VALUES = null;
        public static final SVGAttr CLASS = null;
        public static final SVGAttr UNSUPPORTED = null;
        private static final Map<String, SVGAttr> cache = null;
        public static final SVGAttr clip = null;
        public static final SVGAttr clipPathUnits = null;
        public static final SVGAttr clip_path = null;
        public static final SVGAttr clip_rule = null;
        public static final SVGAttr color = null;
        public static final SVGAttr cx = null;
        public static final SVGAttr cy = null;
        public static final SVGAttr d = null;
        public static final SVGAttr direction = null;
        public static final SVGAttr display = null;
        public static final SVGAttr dx = null;
        public static final SVGAttr dy = null;
        public static final SVGAttr fill = null;
        public static final SVGAttr fill_opacity = null;
        public static final SVGAttr fill_rule = null;
        public static final SVGAttr font = null;
        public static final SVGAttr font_family = null;
        public static final SVGAttr font_size = null;
        public static final SVGAttr font_style = null;
        public static final SVGAttr font_weight = null;
        public static final SVGAttr fx = null;
        public static final SVGAttr fy = null;
        public static final SVGAttr gradientTransform = null;
        public static final SVGAttr gradientUnits = null;
        public static final SVGAttr height = null;
        public static final SVGAttr href = null;
        public static final SVGAttr image_rendering = null;
        public static final SVGAttr marker = null;
        public static final SVGAttr markerHeight = null;
        public static final SVGAttr markerUnits = null;
        public static final SVGAttr markerWidth = null;
        public static final SVGAttr marker_end = null;
        public static final SVGAttr marker_mid = null;
        public static final SVGAttr marker_start = null;
        public static final SVGAttr mask = null;
        public static final SVGAttr maskContentUnits = null;
        public static final SVGAttr maskUnits = null;
        public static final SVGAttr media = null;
        public static final SVGAttr offset = null;
        public static final SVGAttr opacity = null;
        public static final SVGAttr orient = null;
        public static final SVGAttr overflow = null;
        public static final SVGAttr pathLength = null;
        public static final SVGAttr patternContentUnits = null;
        public static final SVGAttr patternTransform = null;
        public static final SVGAttr patternUnits = null;
        public static final SVGAttr points = null;
        public static final SVGAttr preserveAspectRatio = null;
        public static final SVGAttr r = null;
        public static final SVGAttr refX = null;
        public static final SVGAttr refY = null;
        public static final SVGAttr requiredExtensions = null;
        public static final SVGAttr requiredFeatures = null;
        public static final SVGAttr requiredFonts = null;
        public static final SVGAttr requiredFormats = null;
        public static final SVGAttr rx = null;
        public static final SVGAttr ry = null;
        public static final SVGAttr solid_color = null;
        public static final SVGAttr solid_opacity = null;
        public static final SVGAttr spreadMethod = null;
        public static final SVGAttr startOffset = null;
        public static final SVGAttr stop_color = null;
        public static final SVGAttr stop_opacity = null;
        public static final SVGAttr stroke = null;
        public static final SVGAttr stroke_dasharray = null;
        public static final SVGAttr stroke_dashoffset = null;
        public static final SVGAttr stroke_linecap = null;
        public static final SVGAttr stroke_linejoin = null;
        public static final SVGAttr stroke_miterlimit = null;
        public static final SVGAttr stroke_opacity = null;
        public static final SVGAttr stroke_width = null;
        public static final SVGAttr style = null;
        public static final SVGAttr systemLanguage = null;
        public static final SVGAttr text_anchor = null;
        public static final SVGAttr text_decoration = null;
        public static final SVGAttr transform = null;
        public static final SVGAttr type = null;
        public static final SVGAttr vector_effect = null;
        public static final SVGAttr version = null;
        public static final SVGAttr viewBox = null;
        public static final SVGAttr viewport_fill = null;
        public static final SVGAttr viewport_fill_opacity = null;
        public static final SVGAttr visibility = null;
        public static final SVGAttr width = null;
        public static final SVGAttr x = null;
        public static final SVGAttr x1 = null;
        public static final SVGAttr x2 = null;
        public static final SVGAttr y = null;
        public static final SVGAttr y1 = null;
        public static final SVGAttr y2 = null;

        static {
            SVGAttr r1 = new SVGAttr("CLASS", 0);
            CLASS = r1;
            SVGAttr r2 = new SVGAttr("clip", 1);
            clip = r2;
            SVGAttr r3 = new SVGAttr("clip_path", 2);
            clip_path = r3;
            SVGAttr r4 = new SVGAttr("clipPathUnits", 3);
            clipPathUnits = r4;
            SVGAttr r5 = new SVGAttr("clip_rule", 4);
            clip_rule = r5;
            SVGAttr r6 = new SVGAttr("color", 5);
            color = r6;
            SVGAttr r7 = new SVGAttr("cx", 6);
            cx = r7;
            SVGAttr r8 = new SVGAttr("cy", 7);
            cy = r8;
            SVGAttr r9 = new SVGAttr("direction", 8);
            direction = r9;
            SVGAttr r10 = new SVGAttr("dx", 9);
            dx = r10;
            SVGAttr r11 = new SVGAttr("dy", 10);
            dy = r11;
            SVGAttr r12 = new SVGAttr("fx", 11);
            fx = r12;
            SVGAttr r13 = new SVGAttr("fy", 12);
            fy = r13;
            SVGAttr r14 = new SVGAttr("d", 13);
            d = r14;
            SVGAttr r15 = new SVGAttr("display", 14);
            display = r15;
            SVGAttr r0 = new SVGAttr("fill", 15);
            fill = r0;
            SVGAttr r16 = new SVGAttr("fill_rule", 16);
            fill_rule = r16;
            SVGAttr r02 = new SVGAttr("fill_opacity", 17);
            fill_opacity = r02;
            SVGAttr r17 = new SVGAttr("font", 18);
            font = r17;
            SVGAttr r03 = new SVGAttr("font_family", 19);
            font_family = r03;
            SVGAttr r18 = new SVGAttr("font_size", 20);
            font_size = r18;
            SVGAttr r04 = new SVGAttr("font_weight", 21);
            font_weight = r04;
            SVGAttr r19 = new SVGAttr("font_style", 22);
            font_style = r19;
            SVGAttr r05 = new SVGAttr("gradientTransform", 23);
            gradientTransform = r05;
            SVGAttr r110 = new SVGAttr("gradientUnits", 24);
            gradientUnits = r110;
            SVGAttr r06 = new SVGAttr("height", 25);
            height = r06;
            SVGAttr r111 = new SVGAttr("href", 26);
            href = r111;
            SVGAttr r07 = new SVGAttr("image_rendering", 27);
            image_rendering = r07;
            SVGAttr r112 = new SVGAttr("marker", 28);
            marker = r112;
            SVGAttr r08 = new SVGAttr("marker_start", 29);
            marker_start = r08;
            SVGAttr r113 = new SVGAttr("marker_mid", 30);
            marker_mid = r113;
            SVGAttr r09 = new SVGAttr("marker_end", 31);
            marker_end = r09;
            SVGAttr r114 = new SVGAttr("markerHeight", 32);
            markerHeight = r114;
            SVGAttr r010 = new SVGAttr("markerUnits", 33);
            markerUnits = r010;
            SVGAttr r115 = new SVGAttr("markerWidth", 34);
            markerWidth = r115;
            SVGAttr r011 = new SVGAttr(PluginsConstants.Settings.MASK, 35);
            mask = r011;
            SVGAttr r116 = new SVGAttr("maskContentUnits", 36);
            maskContentUnits = r116;
            SVGAttr r012 = new SVGAttr("maskUnits", 37);
            maskUnits = r012;
            SVGAttr r117 = new SVGAttr("media", 38);
            media = r117;
            SVGAttr r013 = new SVGAttr("offset", 39);
            offset = r013;
            SVGAttr r118 = new SVGAttr("opacity", 40);
            opacity = r118;
            SVGAttr r014 = new SVGAttr("orient", 41);
            orient = r014;
            SVGAttr r119 = new SVGAttr("overflow", 42);
            overflow = r119;
            SVGAttr r015 = new SVGAttr("pathLength", 43);
            pathLength = r015;
            SVGAttr r120 = new SVGAttr("patternContentUnits", 44);
            patternContentUnits = r120;
            SVGAttr r016 = new SVGAttr("patternTransform", 45);
            patternTransform = r016;
            SVGAttr r22 = new SVGAttr("patternUnits", 46);
            patternUnits = r22;
            SVGAttr r017 = new SVGAttr("points", 47);
            points = r017;
            SVGAttr r121 = new SVGAttr("preserveAspectRatio", 48);
            preserveAspectRatio = r121;
            SVGAttr r018 = new SVGAttr("r", 49);
            r = r018;
            SVGAttr r122 = new SVGAttr("refX", 50);
            refX = r122;
            SVGAttr r019 = new SVGAttr("refY", 51);
            refY = r019;
            SVGAttr r123 = new SVGAttr("requiredFeatures", 52);
            requiredFeatures = r123;
            SVGAttr r020 = new SVGAttr("requiredExtensions", 53);
            requiredExtensions = r020;
            SVGAttr r124 = new SVGAttr("requiredFormats", 54);
            requiredFormats = r124;
            SVGAttr r021 = new SVGAttr("requiredFonts", 55);
            requiredFonts = r021;
            SVGAttr r125 = new SVGAttr("rx", 56);
            rx = r125;
            SVGAttr r022 = new SVGAttr("ry", 57);
            ry = r022;
            SVGAttr r126 = new SVGAttr("solid_color", 58);
            solid_color = r126;
            SVGAttr r023 = new SVGAttr("solid_opacity", 59);
            solid_opacity = r023;
            SVGAttr r127 = new SVGAttr("spreadMethod", 60);
            spreadMethod = r127;
            SVGAttr r024 = new SVGAttr("startOffset", 61);
            startOffset = r024;
            SVGAttr r128 = new SVGAttr("stop_color", 62);
            stop_color = r128;
            SVGAttr r025 = new SVGAttr("stop_opacity", 63);
            stop_opacity = r025;
            SVGAttr r129 = new SVGAttr("stroke", 64);
            stroke = r129;
            SVGAttr r026 = new SVGAttr("stroke_dasharray", 65);
            stroke_dasharray = r026;
            SVGAttr r130 = new SVGAttr("stroke_dashoffset", 66);
            stroke_dashoffset = r130;
            SVGAttr r027 = new SVGAttr("stroke_linecap", 67);
            stroke_linecap = r027;
            SVGAttr r131 = new SVGAttr("stroke_linejoin", 68);
            stroke_linejoin = r131;
            SVGAttr r028 = new SVGAttr("stroke_miterlimit", 69);
            stroke_miterlimit = r028;
            SVGAttr r132 = new SVGAttr("stroke_opacity", 70);
            stroke_opacity = r132;
            SVGAttr r029 = new SVGAttr("stroke_width", 71);
            stroke_width = r029;
            SVGAttr r133 = new SVGAttr("style", 72);
            style = r133;
            SVGAttr r030 = new SVGAttr("systemLanguage", 73);
            systemLanguage = r030;
            SVGAttr r134 = new SVGAttr("text_anchor", 74);
            text_anchor = r134;
            SVGAttr r031 = new SVGAttr("text_decoration", 75);
            text_decoration = r031;
            SVGAttr r135 = new SVGAttr("transform", 76);
            transform = r135;
            SVGAttr r032 = new SVGAttr("type", 77);
            type = r032;
            SVGAttr r136 = new SVGAttr("vector_effect", 78);
            vector_effect = r136;
            SVGAttr r033 = new SVGAttr("version", 79);
            version = r033;
            SVGAttr r137 = new SVGAttr("viewBox", 80);
            viewBox = r137;
            SVGAttr r034 = new SVGAttr("width", 81);
            width = r034;
            SVGAttr r138 = new SVGAttr("x", 82);
            x = r138;
            SVGAttr r035 = new SVGAttr("y", 83);
            y = r035;
            SVGAttr r139 = new SVGAttr("x1", 84);
            x1 = r139;
            SVGAttr r036 = new SVGAttr("y1", 85);
            y1 = r036;
            SVGAttr r140 = new SVGAttr("x2", 86);
            x2 = r140;
            SVGAttr r037 = new SVGAttr("y2", 87);
            y2 = r037;
            SVGAttr r141 = new SVGAttr("viewport_fill", 88);
            viewport_fill = r141;
            SVGAttr r038 = new SVGAttr("viewport_fill_opacity", 89);
            viewport_fill_opacity = r038;
            SVGAttr r142 = new SVGAttr("visibility", 90);
            visibility = r142;
            SVGAttr r039 = new SVGAttr("UNSUPPORTED", 91);
            UNSUPPORTED = r039;
            $VALUES = new SVGAttr[]{r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r0, r16, r02, r17, r03, r18, r04, r19, r05, r110, r06, r111, r07, r112, r08, r113, r09, r114, r010, r115, r011, r116, r012, r117, r013, r118, r014, r119, r015, r120, r016, r22, r017, r121, r018, r122, r019, r123, r020, r124, r021, r125, r022, r126, r023, r127, r024, r128, r025, r129, r026, r130, r027, r131, r028, r132, r029, r133, r030, r134, r031, r135, r032, r136, r033, r137, r034, r138, r035, r139, r036, r140, r037, r141, r038, r142, r039};
            cache = new HashMap();
            SVGAttr[] r143 = values();
            int r23 = r143.length;
            int r32 = 0;
        L3:
            if (r32 >= r23) goto L11;
            SVGAttr r42 = r143[r32];
            if (r42 != CLASS) goto L8;
            cache.put("class", r42);
        L10:
            r32 = r32 + 1;
            goto L3
        L8:
            if (r42 == UNSUPPORTED) goto L10;
            String r52 = r42.name().replace('_', SignatureVisitor.SUPER);
            cache.put(r52, r42);
            goto L10
        }

        SVGAttr(String r1, int r2) {
        }

        public static SVGAttr fromString(String r1) {
            SVGAttr r12 = cache.get(r1);
            if (r12 == null) goto L6;
            return r12;
        L6:
            return UNSUPPORTED;
        }

        public static SVGAttr valueOf(String r1) {
            return (SVGAttr) Enum.valueOf(SVGAttr.class, r1);
        }

        public static SVGAttr[] values() {
            return (SVGAttr[]) $VALUES.clone();
        }
    }

    public enum SVGElem extends Enum<SVGElem> {
        private static final /* synthetic */ SVGElem[] $VALUES = null;
        public static final SVGElem SWITCH = null;
        public static final SVGElem UNSUPPORTED = null;

        /* renamed from: a, reason: collision with root package name */
        public static final SVGElem f35a = null;
        private static final Map<String, SVGElem> cache = null;
        public static final SVGElem circle = null;
        public static final SVGElem clipPath = null;
        public static final SVGElem defs = null;
        public static final SVGElem desc = null;
        public static final SVGElem ellipse = null;
        public static final SVGElem g = null;
        public static final SVGElem image = null;
        public static final SVGElem line = null;
        public static final SVGElem linearGradient = null;
        public static final SVGElem marker = null;
        public static final SVGElem mask = null;
        public static final SVGElem path = null;
        public static final SVGElem pattern = null;
        public static final SVGElem polygon = null;
        public static final SVGElem polyline = null;
        public static final SVGElem radialGradient = null;
        public static final SVGElem rect = null;
        public static final SVGElem solidColor = null;
        public static final SVGElem stop = null;
        public static final SVGElem style = null;
        public static final SVGElem svg = null;
        public static final SVGElem symbol = null;
        public static final SVGElem text = null;
        public static final SVGElem textPath = null;
        public static final SVGElem title = null;
        public static final SVGElem tref = null;
        public static final SVGElem tspan = null;
        public static final SVGElem use = null;
        public static final SVGElem view = null;

        static {
            SVGElem r1 = new SVGElem("svg", 0);
            svg = r1;
            SVGElem r2 = new SVGElem("a", 1);
            f35a = r2;
            SVGElem r3 = new SVGElem("circle", 2);
            circle = r3;
            SVGElem r4 = new SVGElem("clipPath", 3);
            clipPath = r4;
            SVGElem r5 = new SVGElem("defs", 4);
            defs = r5;
            SVGElem r6 = new SVGElem("desc", 5);
            desc = r6;
            SVGElem r7 = new SVGElem("ellipse", 6);
            ellipse = r7;
            SVGElem r8 = new SVGElem(ImageLoader.AUTOPLAY_FILTER, 7);
            g = r8;
            SVGElem r9 = new SVGElem("image", 8);
            image = r9;
            SVGElem r10 = new SVGElem("line", 9);
            line = r10;
            SVGElem r11 = new SVGElem("linearGradient", 10);
            linearGradient = r11;
            SVGElem r12 = new SVGElem("marker", 11);
            marker = r12;
            SVGElem r13 = new SVGElem(PluginsConstants.Settings.MASK, 12);
            mask = r13;
            SVGElem r14 = new SVGElem("path", 13);
            path = r14;
            SVGElem r15 = new SVGElem("pattern", 14);
            pattern = r15;
            SVGElem r0 = new SVGElem("polygon", 15);
            polygon = r0;
            SVGElem r16 = new SVGElem("polyline", 16);
            polyline = r16;
            SVGElem r02 = new SVGElem("radialGradient", 17);
            radialGradient = r02;
            SVGElem r17 = new SVGElem("rect", 18);
            rect = r17;
            SVGElem r03 = new SVGElem("solidColor", 19);
            solidColor = r03;
            SVGElem r18 = new SVGElem("stop", 20);
            stop = r18;
            SVGElem r04 = new SVGElem("style", 21);
            style = r04;
            SVGElem r19 = new SVGElem("SWITCH", 22);
            SWITCH = r19;
            SVGElem r05 = new SVGElem("symbol", 23);
            symbol = r05;
            SVGElem r110 = new SVGElem("text", 24);
            text = r110;
            SVGElem r06 = new SVGElem("textPath", 25);
            textPath = r06;
            SVGElem r111 = new SVGElem("title", 26);
            title = r111;
            SVGElem r07 = new SVGElem("tref", 27);
            tref = r07;
            SVGElem r112 = new SVGElem("tspan", 28);
            tspan = r112;
            SVGElem r08 = new SVGElem("use", 29);
            use = r08;
            SVGElem r113 = new SVGElem(PluginsConstants.Settings.VIEW, 30);
            view = r113;
            SVGElem r09 = new SVGElem("UNSUPPORTED", 31);
            UNSUPPORTED = r09;
            int r010 = 0;
            $VALUES = new SVGElem[]{r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r0, r16, r02, r17, r03, r18, r04, r19, r05, r110, r06, r111, r07, r112, r08, r113, r09};
            cache = new HashMap();
            SVGElem[] r114 = values();
            int r22 = r114.length;
        L3:
            if (r010 >= r22) goto L11;
            SVGElem r32 = r114[r010];
            if (r32 != SWITCH) goto L8;
            cache.put(PluginsConstants.Settings.TYPE_SWITCH, r32);
        L10:
            r010 = r010 + 1;
            goto L3
        L8:
            if (r32 == UNSUPPORTED) goto L10;
            String r42 = r32.name();
            cache.put(r42, r32);
            goto L10
        }

        SVGElem(String r1, int r2) {
        }

        public static SVGElem fromString(String r1) {
            SVGElem r12 = cache.get(r1);
            if (r12 == null) goto L6;
            return r12;
        L6:
            return UNSUPPORTED;
        }

        public static SVGElem valueOf(String r1) {
            return (SVGElem) Enum.valueOf(SVGElem.class, r1);
        }

        public static SVGElem[] values() {
            return (SVGElem[]) $VALUES.clone();
        }
    }

    public static class TextScanner {
        String input;
        int inputLength;
        private NumberParser numberParser;
        int position;

        public TextScanner(String r2) {
            this.position = 0;
            this.inputLength = 0;
            this.numberParser = new NumberParser();
            String r22 = r2.trim();
            this.input = r22;
            this.inputLength = r22.length();
        }

        public int advanceChar() {
            int r0 = this.position;
            int r1 = this.inputLength;
            if (r0 != r1) goto L5;
            return -1;
        L5:
            int r02 = r0 + 1;
            this.position = r02;
            if (r02 < r1) goto L8;
            return -1;
        L8:
            return this.input.charAt(r02);
        }

        public String ahead() {
            int r0 = this.position;
        L4:
            if (empty() == true) goto L8;
            if (isWhitespace(this.input.charAt(this.position)) == true) goto L8;
            this.position++;
        L8:
            String r1 = this.input.substring(r0, this.position);
            this.position = r0;
            return r1;
        }

        public Boolean checkedNextFlag(Object r1) {
            if (r1 != null) goto L5;
            return null;
        L5:
            skipCommaWhitespace();
            return nextFlag();
        }

        public float checkedNextFloat(float r1) {
            if (Float.isNaN(r1) == false) goto L6;
            return Float.NaN;
        L6:
            skipCommaWhitespace();
            return nextFloat();
        }

        public float checkedNextFloat(Boolean r1) {
            if (r1 != null) goto L5;
            return Float.NaN;
        L5:
            skipCommaWhitespace();
            return nextFloat();
        }

        public boolean consume(char r4) {
            int r0 = this.position;
            if (r0 < this.inputLength) goto L5;
        L7:
            boolean r42 = false;
        L8:
            if (r42 == false) goto L10;
            this.position++;
        L10:
            return r42;
        L5:
            if (this.input.charAt(r0) != r4) goto L7;
            r42 = true;
            goto L8
        }

        public boolean consume(String r5) {
            int r0 = r5.length();
            int r1 = this.position;
            if (r1 <= (this.inputLength - r0)) goto L5;
        L7:
            boolean r52 = false;
        L8:
            if (r52 == false) goto L10;
            this.position += r0;
        L10:
            return r52;
        L5:
            if (this.input.substring(r1, r1 + r0).equals(r5) == false) goto L7;
            r52 = true;
            goto L8
        }

        public boolean empty() {
            if (this.position != this.inputLength) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean hasLetter() {
            int r0 = this.position;
            if (r0 != this.inputLength) goto L5;
            return false;
        L5:
            char r3 = this.input.charAt(r0);
            if (r3 < 'a') goto L10;
            if (r3 > 'z') goto L10;
            return true;
        L10:
            if (r3 >= 'A') goto L12;
        L15:
            return false;
        L12:
            if (r3 > 'Z') goto L15;
            return true;
        }

        public boolean isEOL(int r1) {
            if (r1 != 10) goto L5;
            return true;
        L5:
            if (r1 == 13) goto L11;
            return false;
        L11:
            return true;
        }

        public boolean isWhitespace(int r1) {
            if (r1 != 32) goto L5;
            return true;
        L5:
            if (r1 != 10) goto L7;
            return true;
        L7:
            if (r1 != 13) goto L9;
            return true;
        L9:
            if (r1 == 9) goto L17;
            return false;
        L17:
            return true;
        }

        public Integer nextChar() {
            int r0 = this.position;
            if (r0 != this.inputLength) goto L6;
            return null;
        L6:
            String r1 = this.input;
            this.position = r0 + 1;
            return Integer.valueOf(r1.charAt(r0));
        }

        public Boolean nextFlag() {
            int r0 = this.position;
            if (r0 != this.inputLength) goto L5;
            return null;
        L5:
            char r02 = this.input.charAt(r0);
            if (r02 == '0') goto L10;
            if (r02 == '1') goto L10;
            return null;
        L10:
            boolean r2 = true;
            this.position++;
            if (r02 == '1') goto L15;
            r2 = false;
        L15:
            return Boolean.valueOf(r2);
        }

        public float nextFloat() {
            float r0 = this.numberParser.parseNumber(this.input, this.position, this.inputLength);
            if (Float.isNaN(r0) == true) goto L5;
            this.position = this.numberParser.getEndPos();
        L5:
            return r0;
        }

        public String nextFunction() {
            if (empty() == false) goto L5;
            return null;
        L5:
            int r0 = this.position;
            int r2 = this.input.charAt(r0);
        L7:
            if (r2 < 97) goto L11;
            if (r2 > 122) goto L11;
        L14:
            r2 = advanceChar();
        L11:
            if (r2 < 65) goto L15;
            if (r2 <= 90) goto L14;
        L15:
            int r3 = this.position;
        L17:
            if (isWhitespace(r2) == false) goto L20;
            r2 = advanceChar();
            goto L17
        L20:
            if (r2 != 40) goto L23;
            this.position++;
            return this.input.substring(r0, r3);
        L23:
            this.position = r0;
            return null;
        }

        public SVG.Length nextLength() {
            float r0 = nextFloat();
            if (Float.isNaN(r0) == false) goto L6;
            return null;
        L6:
            SVG.Unit r2 = nextUnit();
            if (r2 != null) goto L11;
            return new SVG.Length(r0, SVG.Unit.px);
        L11:
            return new SVG.Length(r0, r2);
        }

        public String nextQuotedString() {
            if (empty() == false) goto L5;
            return null;
        L5:
            int r0 = this.position;
            char r2 = this.input.charAt(r0);
            if (r2 != '\'') goto L8;
        L10:
            int r3 = advanceChar();
        L12:
            if (r3 == (-1)) goto L15;
            if (r3 == r2) goto L15;
            r3 = advanceChar();
        L15:
            if (r3 != (-1)) goto L18;
            this.position = r0;
            return null;
        L18:
            int r1 = this.position;
            this.position = r1 + 1;
            return this.input.substring(r0 + 1, r1);
        L8:
            if (r2 == '\"') goto L10;
            return null;
        }

        public String nextToken() {
            return nextToken(' ', false);
        }

        public String nextToken(char r2) {
            return nextToken(r2, false);
        }

        public String nextToken(char r4, boolean r5) {
            if (empty() == false) goto L5;
            return null;
        L5:
            char r0 = this.input.charAt(this.position);
            if (r5 == false) goto L8;
        L9:
            if (r0 != r4) goto L11;
        L10:
            return null;
        L11:
            int r02 = this.position;
            int r1 = advanceChar();
        L13:
            if (r1 == (-1)) goto L22;
            if (r1 == r4) goto L22;
            if (r5 == true) goto L20;
            if (isWhitespace(r1) == true) goto L22;
        L20:
            r1 = advanceChar();
        L22:
            return this.input.substring(r02, this.position);
        L8:
            if (isWhitespace(r0) == true) goto L10;
            goto L9
        }

        public String nextTokenWithWhitespace(char r2) {
            return nextToken(r2, true);
        }

        public SVG.Unit nextUnit() {
            if (empty() == false) goto L5;
            return null;
        L5:
            char r0 = this.input.charAt(this.position);
            int r2 = this.position;
            if (r0 != '%') goto L10;
            this.position = r2 + 1;
            return SVG.Unit.percent;
        L10:
            if (r2 <= (this.inputLength - 2)) goto L15;
            return null;
        L15:
            SVG.Unit r02 = SVG.Unit.valueOf(this.input.substring(r2, r2 + 2).toLowerCase(Locale.US));     // Catch: IllegalArgumentException -> L14
            this.position += 2;
            return r02;
        L14:
            return null;
        }

        public String nextWord() {
            if (empty() == false) goto L5;
            return null;
        L5:
            int r0 = this.position;
            char r2 = this.input.charAt(r0);
            if (r2 < 'A') goto L8;
            if (r2 > 'Z') goto L8;
        L10:
            int r1 = advanceChar();
        L11:
            if (r1 < 65) goto L13;
            if (r1 > 90) goto L13;
        L15:
            r1 = advanceChar();
        L13:
            if (r1 < 97) goto L17;
            if (r1 <= 122) goto L15;
        L17:
            return this.input.substring(r0, this.position);
        L8:
            if (r2 < 'a') goto L18;
            if (r2 <= 'z') goto L10;
        L18:
            this.position = r0;
            return null;
        }

        public float possibleNextFloat() {
            skipCommaWhitespace();
            float r0 = this.numberParser.parseNumber(this.input, this.position, this.inputLength);
            if (Float.isNaN(r0) == true) goto L5;
            this.position = this.numberParser.getEndPos();
        L5:
            return r0;
        }

        public String restOfText() {
            if (empty() == false) goto L6;
            return null;
        L6:
            int r0 = this.position;
            this.position = this.inputLength;
            return this.input.substring(r0);
        }

        public boolean skipCommaWhitespace() {
            skipWhitespace();
            int r0 = this.position;
            if (r0 != this.inputLength) goto L6;
            return false;
        L6:
            if (this.input.charAt(r0) == ',') goto L8;
            return false;
        L8:
            this.position++;
            skipWhitespace();
            return true;
        }

        public void skipWhitespace() {
        L2:
            int r0 = this.position;
            if (r0 >= this.inputLength) goto L8;
            if (isWhitespace(this.input.charAt(r0)) == false) goto L11;
            this.position++;
            goto L2
        L11:
            return;
        }
    }

    public class XPPAttributesWrapper implements Attributes {
        private XmlPullParser parser;
        final /* synthetic */ SVGParser this$0;

        public XPPAttributesWrapper(SVGParser r1, XmlPullParser r2) {
            this.this$0 = r1;
            this.parser = r2;
        }

        @Override // org.xml.sax.Attributes
        public int getIndex(String r1) {
            return -1;
        }

        @Override // org.xml.sax.Attributes
        public int getIndex(String r1, String r2) {
            return -1;
        }

        @Override // org.xml.sax.Attributes
        public int getLength() {
            return this.parser.getAttributeCount();
        }

        @Override // org.xml.sax.Attributes
        public String getLocalName(int r1) {
            return this.parser.getAttributeName(r1);
        }

        @Override // org.xml.sax.Attributes
        public String getQName(int r3) {
            String r0 = this.parser.getAttributeName(r3);
            if (this.parser.getAttributePrefix(r3) != null) goto L5;
            return r0;
        L5:
            return this.parser.getAttributePrefix(r3) + ':' + r0;
        }

        @Override // org.xml.sax.Attributes
        public String getType(int r1) {
            return null;
        }

        @Override // org.xml.sax.Attributes
        public String getType(String r1) {
            return null;
        }

        @Override // org.xml.sax.Attributes
        public String getType(String r1, String r2) {
            return null;
        }

        @Override // org.xml.sax.Attributes
        public String getURI(int r1) {
            return this.parser.getAttributeNamespace(r1);
        }

        @Override // org.xml.sax.Attributes
        public String getValue(int r1) {
            return this.parser.getAttributeValue(r1);
        }

        @Override // org.xml.sax.Attributes
        public String getValue(String r1) {
            return null;
        }

        @Override // org.xml.sax.Attributes
        public String getValue(String r1, String r2) {
            return null;
        }
    }

    public SVGParser() {
        this.svgDocument = null;
        this.currentElement = null;
        this.ignoring = false;
        this.inMetadataElement = false;
        this.metadataTag = null;
        this.metadataElementContents = null;
        this.inStyleElement = false;
        this.styleElementContents = null;
    }

    public static /* synthetic */ void access$100(SVGParser r0) {
        r0.startDocument();
    }

    public static /* synthetic */ void access$200(SVGParser r0, String r1, String r2, String r3, Attributes r4) {
        r0.startElement(r1, r2, r3, r4);
    }

    public static /* synthetic */ void access$300(SVGParser r0, String r1) {
        r0.text(r1);
    }

    public static /* synthetic */ void access$400(SVGParser r0, String r1, String r2, String r3) {
        r0.endElement(r1, r2, r3);
    }

    public static /* synthetic */ void access$500(SVGParser r0) {
        r0.endDocument();
    }

    public static /* synthetic */ Map access$600(SVGParser r0, TextScanner r1) {
        return r0.parseProcessingInstructionAttributes(r1);
    }

    public static /* synthetic */ void access$700(SVGParser r0, String r1, Map r2) {
        r0.handleProcessingInstruction(r1, r2);
    }

    private void appendToTextContainer(String r3) {
        SVG.SvgConditionalContainer r0 = (SVG.SvgConditionalContainer) this.currentElement;
        int r1 = r0.children.size();
        if (r1 != 0) goto L5;
        SVG.SvgObject r02 = null;
    L7:
        if ((r02 instanceof SVG.TextSequence) == false) goto L10;
        StringBuilder r2 = new StringBuilder();
        SVG.TextSequence r03 = (SVG.TextSequence) r02;
        r2.append(r03.text);
        r2.append(r3);
        r03.text = r2.toString();
        return;
    L10:
        this.currentElement.addChild(new SVG.TextSequence(r3));
        return;
    L5:
        r02 = r0.children.get(r1 - 1);
        goto L7
    }

    private void circle(Attributes r3) {
        debug("<circle>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.Circle r0 = new SVG.Circle();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesTransform(r0, r3);
        parseAttributesConditional(r0, r3);
        parseAttributesCircle(r0, r3);
        this.currentElement.addChild(r0);
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private static int clamp255(float r1) {
        if (r1 >= 0.0f) goto L7;
        return 0;
    L7:
        if (r1 <= 255.0f) goto L11;
        return 255;
    L11:
        return Math.round(r1);
    }

    private void clipPath(Attributes r3) {
        debug("<clipPath>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.ClipPath r0 = new SVG.ClipPath();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesTransform(r0, r3);
        parseAttributesConditional(r0, r3);
        parseAttributesClipPath(r0, r3);
        this.currentElement.addChild(r0);
        this.currentElement = r0;
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private void debug(String r1, Object... r2) {
    }

    private void defs(Attributes r3) {
        debug("<defs>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.Defs r0 = new SVG.Defs();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesTransform(r0, r3);
        this.currentElement.addChild(r0);
        this.currentElement = r0;
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private void ellipse(Attributes r3) {
        debug("<ellipse>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.Ellipse r0 = new SVG.Ellipse();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesTransform(r0, r3);
        parseAttributesConditional(r0, r3);
        parseAttributesEllipse(r0, r3);
        this.currentElement.addChild(r0);
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private void endDocument() {
    }

    private void endElement(String r4, String r5, String r6) {
        if (this.ignoring == false) goto L9;
        int r0 = this.ignoreDepth - 1;
        this.ignoreDepth = r0;
        if (r0 != 0) goto L9;
        this.ignoring = false;
        return;
    L9:
        if ("http://www.w3.org/2000/svg".equals(r4) == true) goto L14;
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r4) == true) goto L14;
        return;
    L14:
        if (r5.length() > 0) goto L17;
        r5 = r6;
    L17:
        int r42 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[SVGElem.fromString(r5).ordinal()];
        if (r42 != 1) goto L20;
    L45:
        this.currentElement = ((SVG.SvgObject) this.currentElement).parent;
        return;
    L20:
        if (r42 == 2) goto L45;
        if (r42 == 4) goto L45;
        if (r42 == 5) goto L45;
        if (r42 == 13) goto L45;
        if (r42 == 14) goto L45;
        switch(r42) {
            case 16: goto L45;
            case 17: goto L45;
            case 18: goto L45;
            case 19: goto L45;
            case 20: goto L45;
            case 21: goto L45;
            case 22: goto L35;
            case 23: goto L35;
            case 24: goto L45;
            case 25: goto L45;
            case 26: goto L45;
            case 27: goto L45;
            case 28: goto L45;
            case 29: goto L45;
            case 30: goto L31;
            case 31: goto L45;
            default: goto L50;
        };
    L31:
        StringBuilder r43 = this.styleElementContents;
        if (r43 == null) goto L47;
        this.inStyleElement = false;
        parseCSSStyleSheet(r43.toString());
        this.styleElementContents.setLength(0);
        return;
    L47:
        return;
    L35:
        this.inMetadataElement = false;
        StringBuilder r44 = this.metadataElementContents;
        if (r44 == null) goto L48;
        SVGElem r52 = this.metadataTag;
        if (r52 != SVGElem.title) goto L41;
        this.svgDocument.setTitle(r44.toString());
    L43:
        this.metadataElementContents.setLength(0);
        return;
    L41:
        if (r52 != SVGElem.desc) goto L43;
        this.svgDocument.setDesc(r44.toString());
        goto L43
    L48:
        return;
    }

    private void g(Attributes r3) {
        debug("<g>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.Group r0 = new SVG.Group();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesTransform(r0, r3);
        parseAttributesConditional(r0, r3);
        this.currentElement.addChild(r0);
        this.currentElement = r0;
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private void handleProcessingInstruction(String r1, Map<String, String> r2) {
        if (r1.equals("xml-stylesheet") == false) goto L6;
        SVG.getFileResolver();
        return;
    }

    private static int hslToRgb(float r3, float r4, float r5) {
        float r0 = 0.0f;
        float r32 = r3 % 360.0f;
        if (r3 >= 0.0f) goto L6;
        r32 = r32 + 360.0f;
    L6:
        float r33 = r32 / 60.0f;
        float r42 = r4 / 100.0f;
        float r52 = r5 / 100.0f;
        if (r42 >= 0.0f) goto L10;
        r42 = 0.0f;
    L13:
        if (r52 < 0.0f) goto L20;
        if (r52 <= 1.0f) goto L18;
        r0 = 1.0f;
        goto L20
    L18:
        r0 = r52;
    L20:
        if (r0 > 0.5f) goto L22;
        float r43 = (r42 + 1.0f) * r0;
    L23:
        float r02 = (r0 * 2.0f) - r43;
        float r1 = hueToRgb(r02, r43, r33 + 2.0f);
        float r2 = hueToRgb(r02, r43, r33);
        float r34 = hueToRgb(r02, r43, r33 - 2.0f);
        return clamp255(r34 * 256.0f) | ((clamp255(r1 * 256.0f) << 16) | (clamp255(r2 * 256.0f) << 8));
    L22:
        r43 = (r0 + r42) - (r42 * r0);
        goto L23
    L10:
        if (r42 <= 1.0f) goto L13;
        r42 = 1.0f;
        goto L13
    }

    private static float hueToRgb(float r2, float r3, float r4) {
        if (r4 >= 0.0f) goto L6;
        r4 = r4 + 6.0f;
    L6:
        if (r4 < 6.0f) goto L9;
        r4 = r4 - 6.0f;
    L9:
        if (r4 >= 1.0f) goto L14;
        float r32 = (r3 - r2) * r4;
    L12:
        return r32 + r2;
    L14:
        if (r4 >= 3.0f) goto L17;
        return r3;
    L17:
        if (r4 >= 4.0f) goto L19;
        r32 = (r3 - r2) * (4.0f - r4);
        goto L12
    L19:
        return r2;
    }

    private void image(Attributes r3) {
        debug("<image>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.Image r0 = new SVG.Image();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesTransform(r0, r3);
        parseAttributesConditional(r0, r3);
        parseAttributesImage(r0, r3);
        this.currentElement.addChild(r0);
        this.currentElement = r0;
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private void line(Attributes r3) {
        debug("<line>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.Line r0 = new SVG.Line();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesTransform(r0, r3);
        parseAttributesConditional(r0, r3);
        parseAttributesLine(r0, r3);
        this.currentElement.addChild(r0);
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private void linearGradient(Attributes r3) {
        debug("<linearGradient>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.SvgLinearGradient r0 = new SVG.SvgLinearGradient();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesGradient(r0, r3);
        parseAttributesLinearGradient(r0, r3);
        this.currentElement.addChild(r0);
        this.currentElement = r0;
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private void marker(Attributes r3) {
        debug("<marker>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.Marker r0 = new SVG.Marker();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesConditional(r0, r3);
        parseAttributesViewBox(r0, r3);
        parseAttributesMarker(r0, r3);
        this.currentElement.addChild(r0);
        this.currentElement = r0;
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private void mask(Attributes r3) {
        debug("<mask>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.Mask r0 = new SVG.Mask();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesConditional(r0, r3);
        parseAttributesMask(r0, r3);
        this.currentElement.addChild(r0);
        this.currentElement = r0;
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private void parseAttributesCircle(SVG.Circle r4, Attributes r5) {
        int r3 = 0;
    L4:
        if (r3 >= r5.getLength()) goto L16;
        String r0 = r5.getValue(r3).trim();
        switch(AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r5.getLocalName(r3)).ordinal()]) {
            case 12: goto L14;
            case 13: goto L13;
            case 14: goto L8;
            default: goto L15;
        };
    L8:
        SVG.Length r02 = parseLength(r0);
        r4.r = r02;
        if (r02.isNegative() == false) goto L15;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid <circle> element. r cannot be negative");
        return;
    L13:
        r4.cy = parseLength(r0);
        goto L15
    L14:
        r4.cx = parseLength(r0);
    L15:
        r3 = r3 + 1;
        goto L4
    }

    private void parseAttributesClipPath(SVG.ClipPath r4, Attributes r5) {
        int r3 = 0;
    L4:
        if (r3 >= r5.getLength()) goto L22;
        String r0 = r5.getValue(r3).trim();
        if (AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r5.getLocalName(r3)).ordinal()] != 38) goto L14;
        if ("objectBoundingBox".equals(r0) == false) goto L12;
        r4.clipPathUnitsAreUser = Boolean.FALSE;
        goto L14
    L12:
        if ("userSpaceOnUse".equals(r0) == false) goto L15;
        r4.clipPathUnitsAreUser = Boolean.TRUE;
        goto L14
    L15:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid value for attribute clipPathUnits");
        return;
    L14:
        r3 = r3 + 1;
        goto L4
    }

    private void parseAttributesConditional(SVG.SvgConditional r5, Attributes r6) {
        int r0 = 0;
    L4:
        if (r0 >= r6.getLength()) goto L18;
        String r1 = r6.getValue(r0).trim();
        switch(AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r6.getLocalName(r0)).ordinal()]) {
            case 21: goto L16;
            case 22: goto L15;
            case 23: goto L14;
            case 24: goto L13;
            case 25: goto L8;
            default: goto L17;
        };
    L8:
        List<String> r12 = parseFontFamily(r1);
        if (r12 == null) goto L11;
        HashSet r2 = new HashSet(r12);
    L12:
        r5.setRequiredFonts(r2);
        goto L17
    L11:
        r2 = new HashSet(0);
        goto L12
    L13:
        r5.setRequiredFormats(parseRequiredFormats(r1));
        goto L17
    L14:
        r5.setSystemLanguage(parseSystemLanguage(r1));
        goto L17
    L15:
        r5.setRequiredExtensions(r1);
        goto L17
    L16:
        r5.setRequiredFeatures(parseRequiredFeatures(r1));
    L17:
        r0 = r0 + 1;
        goto L4
    }

    private void parseAttributesCore(SVG.SvgElementBase r3, Attributes r4) {
        int r2 = 0;
    L4:
        if (r2 >= r4.getLength()) goto L29;
        String r0 = r4.getQName(r2);
        if (r0.equals("id") == true) goto L23;
        if (r0.equals("xml:id") == true) goto L23;
        if (r0.equals("xml:space") == true) goto L12;
        r2 = r2 + 1;
        goto L4
    L12:
        String r22 = r4.getValue(r2).trim();
        if (PluginsConstants.Settings.DEFAULT.equals(r22) == false) goto L17;
        r3.spacePreserve = Boolean.FALSE;
        return;
    L17:
        if ("preserve".equals(r22) == false) goto L20;
        r3.spacePreserve = Boolean.TRUE;
        return;
    L20:
        SVGParser$$ExternalSyntheticBUOutline0.m("Invalid value for \"xml:space\" attribute: ", r22);
        return;
    L23:
        r3.id = r4.getValue(r2).trim();
        return;
    }

    private void parseAttributesEllipse(SVG.Ellipse r4, Attributes r5) {
        int r3 = 0;
    L4:
        if (r3 >= r5.getLength()) goto L28;
        String r0 = r5.getValue(r3).trim();
        switch(AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r5.getLocalName(r3)).ordinal()]) {
            case 10: goto L15;
            case 11: goto L10;
            case 12: goto L9;
            case 13: goto L8;
            default: goto L17;
        };
    L8:
        r4.cy = parseLength(r0);
        goto L17
    L9:
        r4.cx = parseLength(r0);
        goto L17
    L10:
        SVG.Length r02 = parseLength(r0);
        r4.ry = r02;
        if (r02.isNegative() == false) goto L17;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid <ellipse> element. ry cannot be negative");
        return;
    L15:
        SVG.Length r03 = parseLength(r0);
        r4.rx = r03;
        if (r03.isNegative() == false) goto L17;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid <ellipse> element. rx cannot be negative");
        return;
    L17:
        r3 = r3 + 1;
        goto L4
    }

    private void parseAttributesGradient(SVG.GradientElement r5, Attributes r6) {
        int r0 = 0;
    L4:
        if (r0 >= r6.getLength()) goto L28;
        String r1 = r6.getValue(r0).trim();
        int r2 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r6.getLocalName(r0)).ordinal()];
        if (r2 == 6) goto L23;
        switch(r2) {
            case 32: goto L15;
            case 33: goto L13;
            case 34: goto L29;
            default: goto L27;
        };
    L13:
        r5.gradientTransform = parseTransformList(r1);
        goto L27
    L29:
        r5.spreadMethod = SVG.GradientSpread.valueOf(r1);     // Catch: IllegalArgumentException -> L11
    L11:
        SVGParser$$ExternalSyntheticBUOutline2.m("Invalid spreadMethod attribute. \"", r1, "\" is not a valid value.");
        return;
    L15:
        if ("objectBoundingBox".equals(r1) == false) goto L18;
        r5.gradientUnitsAreUser = Boolean.FALSE;
        goto L27
    L18:
        if ("userSpaceOnUse".equals(r1) == false) goto L20;
        r5.gradientUnitsAreUser = Boolean.TRUE;
        goto L27
    L20:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid value for attribute gradientUnits");
        return;
    L27:
        r0 = r0 + 1;
        goto L4
    L23:
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r6.getURI(r0)) == false) goto L25;
    L26:
        r5.href = r1;
        goto L27
    L25:
        if ("http://www.w3.org/1999/xlink".equals(r6.getURI(r0)) == false) goto L27;
    }

    private void parseAttributesImage(SVG.Image r4, Attributes r5) {
        int r3 = 0;
    L4:
        if (r3 >= r5.getLength()) goto L37;
        String r0 = r5.getValue(r3).trim();
        int r1 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r5.getLocalName(r3)).ordinal()];
        if (r1 != 1) goto L8;
        r4.x = parseLength(r0);
    L36:
        r3 = r3 + 1;
        goto L4
    L8:
        if (r1 != 2) goto L10;
        r4.y = parseLength(r0);
        goto L36
    L10:
        if (r1 != 3) goto L12;
        SVG.Length r02 = parseLength(r0);
        r4.width = r02;
        if (r02.isNegative() == false) goto L36;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid <use> element. width cannot be negative");
        return;
    L12:
        if (r1 != 4) goto L14;
        SVG.Length r03 = parseLength(r0);
        r4.height = r03;
        if (r03.isNegative() == false) goto L36;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid <use> element. height cannot be negative");
        return;
    L14:
        if (r1 == 6) goto L20;
        if (r1 != 7) goto L36;
        parsePreserveAspectRatio(r4, r0);
        goto L36
    L20:
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r5.getURI(r3)) == false) goto L22;
    L23:
        r4.href = r0;
        goto L36
    L22:
        if ("http://www.w3.org/1999/xlink".equals(r5.getURI(r3)) == false) goto L36;
    }

    private void parseAttributesLine(SVG.Line r4, Attributes r5) {
        int r3 = 0;
    L4:
        if (r3 >= r5.getLength()) goto L13;
        String r0 = r5.getValue(r3).trim();
        switch(AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r5.getLocalName(r3)).ordinal()]) {
            case 15: goto L11;
            case 16: goto L10;
            case 17: goto L9;
            case 18: goto L8;
            default: goto L12;
        };
    L8:
        r4.y2 = parseLength(r0);
        goto L12
    L9:
        r4.x2 = parseLength(r0);
        goto L12
    L10:
        r4.y1 = parseLength(r0);
        goto L12
    L11:
        r4.x1 = parseLength(r0);
    L12:
        r3 = r3 + 1;
        goto L4
    }

    private void parseAttributesLinearGradient(SVG.SvgLinearGradient r4, Attributes r5) {
        int r3 = 0;
    L4:
        if (r3 >= r5.getLength()) goto L13;
        String r0 = r5.getValue(r3).trim();
        switch(AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r5.getLocalName(r3)).ordinal()]) {
            case 15: goto L11;
            case 16: goto L10;
            case 17: goto L9;
            case 18: goto L8;
            default: goto L12;
        };
    L8:
        r4.y2 = parseLength(r0);
        goto L12
    L9:
        r4.x2 = parseLength(r0);
        goto L12
    L10:
        r4.y1 = parseLength(r0);
        goto L12
    L11:
        r4.x1 = parseLength(r0);
    L12:
        r3 = r3 + 1;
        goto L4
    }

    private void parseAttributesMarker(SVG.Marker r5, Attributes r6) {
        int r0 = 0;
    L4:
        if (r0 >= r6.getLength()) goto L33;
        String r1 = r6.getValue(r0).trim();
        switch(AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r6.getLocalName(r0)).ordinal()]) {
            case 26: goto L31;
            case 27: goto L30;
            case 28: goto L25;
            case 29: goto L20;
            case 30: goto L13;
            case 31: goto L9;
            default: goto L32;
        };
    L20:
        SVG.Length r12 = parseLength(r1);
        r5.markerHeight = r12;
        if (r12.isNegative() == false) goto L32;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid <marker> element. markerHeight cannot be negative");
        return;
    L25:
        SVG.Length r13 = parseLength(r1);
        r5.markerWidth = r13;
        if (r13.isNegative() == false) goto L32;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid <marker> element. markerWidth cannot be negative");
        return;
    L30:
        r5.refY = parseLength(r1);
        goto L32
    L31:
        r5.refX = parseLength(r1);
        goto L32
    L9:
        if ("auto".equals(r1) == false) goto L11;
        r5.orient = Float.valueOf(Float.NaN);
        goto L32
    L11:
        r5.orient = Float.valueOf(parseFloat(r1));
        goto L32
    L13:
        if ("strokeWidth".equals(r1) == false) goto L16;
        r5.markerUnitsAreUser = false;
        goto L32
    L16:
        if ("userSpaceOnUse".equals(r1) == false) goto L18;
        r5.markerUnitsAreUser = true;
        goto L32
    L18:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid value for attribute markerUnits");
        return;
    L32:
        r0 = r0 + 1;
        goto L4
    }

    private void parseAttributesMask(SVG.Mask r6, Attributes r7) {
        int r5 = 0;
    L4:
        if (r5 >= r7.getLength()) goto L47;
        String r0 = r7.getValue(r5).trim();
        int r1 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r7.getLocalName(r5)).ordinal()];
        if (r1 != 1) goto L8;
        r6.x = parseLength(r0);
    L46:
        r5 = r5 + 1;
        goto L4
    L8:
        if (r1 != 2) goto L10;
        r6.y = parseLength(r0);
        goto L46
    L10:
        if (r1 != 3) goto L12;
        SVG.Length r02 = parseLength(r0);
        r6.width = r02;
        if (r02.isNegative() == false) goto L46;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid <mask> element. width cannot be negative");
        return;
    L12:
        if (r1 != 4) goto L14;
        SVG.Length r03 = parseLength(r0);
        r6.height = r03;
        if (r03.isNegative() == false) goto L46;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid <mask> element. height cannot be negative");
        return;
    L14:
        if (r1 == 43) goto L27;
        if (r1 != 44) goto L46;
        if ("objectBoundingBox".equals(r0) == false) goto L22;
        r6.maskContentUnitsAreUser = Boolean.FALSE;
        goto L46
    L22:
        if ("userSpaceOnUse".equals(r0) == false) goto L24;
        r6.maskContentUnitsAreUser = Boolean.TRUE;
        goto L46
    L24:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid value for attribute maskContentUnits");
        return;
    L27:
        if ("objectBoundingBox".equals(r0) == false) goto L30;
        r6.maskUnitsAreUser = Boolean.FALSE;
        goto L46
    L30:
        if ("userSpaceOnUse".equals(r0) == false) goto L32;
        r6.maskUnitsAreUser = Boolean.TRUE;
        goto L46
    L32:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid value for attribute maskUnits");
        return;
    }

    private void parseAttributesPath(SVG.Path r4, Attributes r5) {
        int r3 = 0;
    L4:
        if (r3 >= r5.getLength()) goto L17;
        String r0 = r5.getValue(r3).trim();
        int r1 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r5.getLocalName(r3)).ordinal()];
        if (r1 != 8) goto L8;
        r4.d = parsePath(r0);
    L16:
        r3 = r3 + 1;
        goto L4
    L8:
        if (r1 != 9) goto L16;
        Float r02 = Float.valueOf(parseFloat(r0));
        r4.pathLength = r02;
        if (r02.floatValue() >= 0.0f) goto L16;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid <path> element. pathLength cannot be negative");
        return;
    }

    private void parseAttributesPattern(SVG.Pattern r6, Attributes r7) {
        int r0 = 0;
    L4:
        if (r0 >= r7.getLength()) goto L53;
        String r1 = r7.getValue(r0).trim();
        int r2 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r7.getLocalName(r0)).ordinal()];
        if (r2 != 1) goto L8;
        r6.x = parseLength(r1);
    L52:
        r0 = r0 + 1;
        goto L4
    L8:
        if (r2 != 2) goto L10;
        r6.y = parseLength(r1);
        goto L52
    L10:
        if (r2 != 3) goto L12;
        SVG.Length r12 = parseLength(r1);
        r6.width = r12;
        if (r12.isNegative() == false) goto L52;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid <pattern> element. width cannot be negative");
        return;
    L12:
        if (r2 != 4) goto L14;
        SVG.Length r13 = parseLength(r1);
        r6.height = r13;
        if (r13.isNegative() == false) goto L52;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid <pattern> element. height cannot be negative");
        return;
    L14:
        if (r2 == 6) goto L36;
        switch(r2) {
            case 40: goto L28;
            case 41: goto L20;
            case 42: goto L18;
            default: goto L52;
        };
    L18:
        r6.patternTransform = parseTransformList(r1);
        goto L52
    L20:
        if ("objectBoundingBox".equals(r1) == false) goto L23;
        r6.patternContentUnitsAreUser = Boolean.FALSE;
        goto L52
    L23:
        if ("userSpaceOnUse".equals(r1) == false) goto L25;
        r6.patternContentUnitsAreUser = Boolean.TRUE;
        goto L52
    L25:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid value for attribute patternContentUnits");
        return;
    L28:
        if ("objectBoundingBox".equals(r1) == false) goto L31;
        r6.patternUnitsAreUser = Boolean.FALSE;
        goto L52
    L31:
        if ("userSpaceOnUse".equals(r1) == false) goto L33;
        r6.patternUnitsAreUser = Boolean.TRUE;
        goto L52
    L33:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid value for attribute patternUnits");
        return;
    L36:
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r7.getURI(r0)) == false) goto L38;
    L39:
        r6.href = r1;
        goto L52
    L38:
        if ("http://www.w3.org/1999/xlink".equals(r7.getURI(r0)) == false) goto L52;
    }

    private void parseAttributesPolyLine(SVG.PolyLine r9, Attributes r10, String r11) {
        int r0 = 0;
    L4:
        if (r0 >= r10.getLength()) goto L23;
        if (SVGAttr.fromString(r10.getLocalName(r0)) != SVGAttr.points) goto L22;
        TextScanner r1 = new TextScanner(r10.getValue(r0));
        ArrayList r2 = new ArrayList();
        r1.skipWhitespace();
    L9:
        if (r1.empty() == true) goto L19;
        float r3 = r1.nextFloat();
        if (Float.isNaN(r3) == true) goto L17;
        r1.skipCommaWhitespace();
        float r4 = r1.nextFloat();
        if (Float.isNaN(r4) == true) goto L15;
        r1.skipCommaWhitespace();
        r2.add(Float.valueOf(r3));
        r2.add(Float.valueOf(r4));
        goto L9
    L15:
        SVGParser$$ExternalSyntheticBUOutline2.m("Invalid <", r11, "> points attribute. There should be an even number of coordinates.");
        return;
    L17:
        SVGParser$$ExternalSyntheticBUOutline2.m("Invalid <", r11, "> points attribute. Non-coordinate content found in list.");
        return;
    L19:
        r9.points = new float[r2.size()];
        int r12 = r2.size();
        int r32 = 0;
        int r42 = 0;
    L20:
        if (r42 >= r12) goto L22;
        Object r5 = r2.get(r42);
        r42 = r42 + 1;
        r9.points[r32] = ((Float) r5).floatValue();
        r32 = r32 + 1;
    L22:
        r0 = r0 + 1;
        goto L4
    }

    private void parseAttributesRadialGradient(SVG.SvgRadialGradient r4, Attributes r5) {
        int r3 = 0;
    L4:
        if (r3 >= r5.getLength()) goto L21;
        String r0 = r5.getValue(r3).trim();
        int r1 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r5.getLocalName(r3)).ordinal()];
        if (r1 != 35) goto L8;
        r4.fx = parseLength(r0);
    L20:
        r3 = r3 + 1;
        goto L4
    L8:
        if (r1 == 36) goto L18;
        switch(r1) {
            case 12: goto L17;
            case 13: goto L16;
            case 14: goto L11;
            default: goto L20;
        };
    L11:
        SVG.Length r02 = parseLength(r0);
        r4.r = r02;
        if (r02.isNegative() == false) goto L20;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid <radialGradient> element. r cannot be negative");
        return;
    L16:
        r4.cy = parseLength(r0);
        goto L20
    L17:
        r4.cx = parseLength(r0);
        goto L20
    L18:
        r4.fy = parseLength(r0);
        goto L20
    }

    private void parseAttributesRect(SVG.Rect r4, Attributes r5) {
        int r3 = 0;
    L4:
        if (r3 >= r5.getLength()) goto L41;
        String r0 = r5.getValue(r3).trim();
        int r1 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r5.getLocalName(r3)).ordinal()];
        if (r1 != 1) goto L8;
        r4.x = parseLength(r0);
    L40:
        r3 = r3 + 1;
        goto L4
    L8:
        if (r1 != 2) goto L10;
        r4.y = parseLength(r0);
        goto L40
    L10:
        if (r1 != 3) goto L12;
        SVG.Length r02 = parseLength(r0);
        r4.width = r02;
        if (r02.isNegative() == false) goto L40;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid <rect> element. width cannot be negative");
        return;
    L12:
        if (r1 != 4) goto L14;
        SVG.Length r03 = parseLength(r0);
        r4.height = r03;
        if (r03.isNegative() == false) goto L40;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid <rect> element. height cannot be negative");
        return;
    L14:
        if (r1 != 10) goto L16;
        SVG.Length r04 = parseLength(r0);
        r4.rx = r04;
        if (r04.isNegative() == false) goto L40;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid <rect> element. rx cannot be negative");
        return;
    L16:
        if (r1 != 11) goto L40;
        SVG.Length r05 = parseLength(r0);
        r4.ry = r05;
        if (r05.isNegative() == false) goto L40;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid <rect> element. ry cannot be negative");
        return;
    }

    private void parseAttributesSVG(SVG.Svg r4, Attributes r5) {
        int r3 = 0;
    L4:
        if (r3 >= r5.getLength()) goto L30;
        String r0 = r5.getValue(r3).trim();
        int r1 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r5.getLocalName(r3)).ordinal()];
        if (r1 != 1) goto L8;
        r4.x = parseLength(r0);
    L29:
        r3 = r3 + 1;
        goto L4
    L8:
        if (r1 != 2) goto L10;
        r4.y = parseLength(r0);
        goto L29
    L10:
        if (r1 != 3) goto L12;
        SVG.Length r02 = parseLength(r0);
        r4.width = r02;
        if (r02.isNegative() == false) goto L29;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid <svg> element. width cannot be negative");
        return;
    L12:
        if (r1 != 4) goto L14;
        SVG.Length r03 = parseLength(r0);
        r4.height = r03;
        if (r03.isNegative() == false) goto L29;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid <svg> element. height cannot be negative");
        return;
    L14:
        if (r1 != 5) goto L29;
        r4.version = r0;
        goto L29
    }

    private void parseAttributesStop(SVG.Stop r5, Attributes r6) {
        int r0 = 0;
    L4:
        if (r0 >= r6.getLength()) goto L10;
        String r1 = r6.getValue(r0).trim();
        if (AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r6.getLocalName(r0)).ordinal()] != 37) goto L9;
        r5.offset = parseGradientOffset(r1);
    L9:
        r0 = r0 + 1;
        goto L4
    }

    private void parseAttributesStyle(SVG.SvgElementBase r4, Attributes r5) {
        int r3 = 0;
    L4:
        if (r3 >= r5.getLength()) goto L19;
        String r0 = r5.getValue(r3).trim();
        if (r0.length() == 0) goto L18;
        int r1 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r5.getLocalName(r3)).ordinal()];
        if (r1 != 45) goto L11;
        parseStyle(r4, r0);
        goto L18
    L11:
        if (r1 != 46) goto L13;
        r4.classNames = CSSParser.parseClassAttribute(r0);
        goto L18
    L13:
        if (r4.baseStyle != null) goto L15;
        r4.baseStyle = new SVG.Style();
    L15:
        processStyleProperty(r4.baseStyle, r5.getLocalName(r3), r5.getValue(r3).trim());
    L18:
        r3 = r3 + 1;
        goto L4
    }

    private void parseAttributesTRef(SVG.TRef r4, Attributes r5) {
        int r3 = 0;
    L4:
        if (r3 >= r5.getLength()) goto L14;
        String r0 = r5.getValue(r3).trim();
        if (AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r5.getLocalName(r3)).ordinal()] != 6) goto L13;
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r5.getURI(r3)) == false) goto L11;
    L12:
        r4.href = r0;
        goto L13
    L11:
        if ("http://www.w3.org/1999/xlink".equals(r5.getURI(r3)) == true) goto L12;
    L13:
        r3 = r3 + 1;
        goto L4
    }

    private void parseAttributesTextPath(SVG.TextPath r4, Attributes r5) {
        int r3 = 0;
    L4:
        if (r3 >= r5.getLength()) goto L17;
        String r0 = r5.getValue(r3).trim();
        int r1 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r5.getLocalName(r3)).ordinal()];
        if (r1 == 6) goto L12;
        if (r1 != 39) goto L16;
        r4.startOffset = parseLength(r0);
    L16:
        r3 = r3 + 1;
        goto L4
    L12:
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r5.getURI(r3)) == false) goto L14;
    L15:
        r4.href = r0;
        goto L16
    L14:
        if ("http://www.w3.org/1999/xlink".equals(r5.getURI(r3)) == false) goto L16;
    }

    private void parseAttributesTextPosition(SVG.TextPositionedContainer r4, Attributes r5) {
        int r3 = 0;
    L4:
        if (r3 >= r5.getLength()) goto L19;
        String r0 = r5.getValue(r3).trim();
        int r1 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r5.getLocalName(r3)).ordinal()];
        if (r1 != 1) goto L8;
        r4.x = parseLengthList(r0);
    L18:
        r3 = r3 + 1;
        goto L4
    L8:
        if (r1 != 2) goto L10;
        r4.y = parseLengthList(r0);
        goto L18
    L10:
        if (r1 != 19) goto L12;
        r4.dx = parseLengthList(r0);
        goto L18
    L12:
        if (r1 != 20) goto L18;
        r4.dy = parseLengthList(r0);
        goto L18
    }

    private void parseAttributesTransform(SVG.HasTransform r4, Attributes r5) {
        int r0 = 0;
    L4:
        if (r0 >= r5.getLength()) goto L9;
        if (SVGAttr.fromString(r5.getLocalName(r0)) != SVGAttr.transform) goto L8;
        r4.setTransform(parseTransformList(r5.getValue(r0)));
    L8:
        r0 = r0 + 1;
        goto L4
    }

    private void parseAttributesUse(SVG.Use r4, Attributes r5) {
        int r3 = 0;
    L4:
        if (r3 >= r5.getLength()) goto L34;
        String r0 = r5.getValue(r3).trim();
        int r1 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r5.getLocalName(r3)).ordinal()];
        if (r1 != 1) goto L8;
        r4.x = parseLength(r0);
    L33:
        r3 = r3 + 1;
        goto L4
    L8:
        if (r1 != 2) goto L10;
        r4.y = parseLength(r0);
        goto L33
    L10:
        if (r1 != 3) goto L12;
        SVG.Length r02 = parseLength(r0);
        r4.width = r02;
        if (r02.isNegative() == false) goto L33;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid <use> element. width cannot be negative");
        return;
    L12:
        if (r1 != 4) goto L14;
        SVG.Length r03 = parseLength(r0);
        r4.height = r03;
        if (r03.isNegative() == false) goto L33;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid <use> element. height cannot be negative");
        return;
    L14:
        if (r1 != 6) goto L33;
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r5.getURI(r3)) == false) goto L19;
    L20:
        r4.href = r0;
        goto L33
    L19:
        if ("http://www.w3.org/1999/xlink".equals(r5.getURI(r3)) == false) goto L33;
    }

    private void parseAttributesViewBox(SVG.SvgViewBoxContainer r4, Attributes r5) {
        int r3 = 0;
    L4:
        if (r3 >= r5.getLength()) goto L13;
        String r0 = r5.getValue(r3).trim();
        int r1 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r5.getLocalName(r3)).ordinal()];
        if (r1 != 7) goto L8;
        parsePreserveAspectRatio(r4, r0);
    L12:
        r3 = r3 + 1;
        goto L4
    L8:
        if (r1 != 87) goto L12;
        r4.viewBox = parseViewBox(r0);
        goto L12
    }

    private void parseCSSStyleSheet(String r4) {
        CSSParser r0 = new CSSParser(CSSParser.MediaType.screen, CSSParser.Source.Document);
        this.svgDocument.addCSSRules(r0.parse(r4));
    }

    private static SVG.CSSClipRect parseClip(String r6) {
        if ("auto".equals(r6) == false) goto L6;
        return null;
    L6:
        if (r6.startsWith("rect(") == true) goto L8;
        return null;
    L8:
        TextScanner r0 = new TextScanner(r6.substring(5));
        r0.skipWhitespace();
        SVG.Length r62 = parseLengthOrAuto(r0);
        r0.skipCommaWhitespace();
        SVG.Length r2 = parseLengthOrAuto(r0);
        r0.skipCommaWhitespace();
        SVG.Length r3 = parseLengthOrAuto(r0);
        r0.skipCommaWhitespace();
        SVG.Length r4 = parseLengthOrAuto(r0);
        r0.skipWhitespace();
        if (r0.consume(')') == true) goto L14;
        if (r0.empty() == true) goto L14;
        return null;
    L14:
        return new SVG.CSSClipRect(r62, r2, r3, r4);
    }

    private static SVG.Colour parseColour(String r11) {
        int r2 = 5;
        if (r11.charAt(0) != '#') goto L25;
        IntegerParser r0 = IntegerParser.parseHex(r11, 1, r11.length());
        if (r0 == null) goto L24;
        int r5 = r0.getEndPos();
        if (r5 == 4) goto L21;
        if (r5 != 5) goto L10;
        int r112 = r0.value();
        int r02 = 61440 & r112;
        int r1 = r112 & 3840;
        int r22 = r112 & 240;
        int r113 = r112 & 15;
        return new SVG.Colour((((((((r113 << 24) | (r113 << 28)) | (r02 << 8)) | (r02 << 4)) | (r1 << 4)) | r1) | r22) | (r22 >> 4));
    L10:
        if (r5 == 7) goto L18;
        if (r5 != 9) goto L16;
        return new SVG.Colour((r0.value() >>> 8) | (r0.value() << 24));
    L16:
        throw new SVGParseException("Bad hex colour value: ".concat(r11));
    L18:
        return new SVG.Colour(r0.value() | (-16777216));
    L21:
        int r114 = r0.value();
        int r03 = r114 & 3840;
        int r12 = r114 & 240;
        int r115 = r114 & 15;
        return new SVG.Colour(r115 | (((((r03 << 8) | ((-16777216) | (r03 << 12))) | (r12 << 8)) | (r12 << 4)) | (r115 << 4)));
    L24:
        throw new SVGParseException("Bad hex colour value: ".concat(r11));
    L25:
        String r04 = r11.toLowerCase(Locale.US);
        boolean r13 = r04.startsWith("rgba(");
        if (r13 == true) goto L65;
        if (r04.startsWith("rgb(") == true) goto L65;
        boolean r14 = r04.startsWith("hsla(");
        if (r14 == true) goto L38;
        if (r04.startsWith("hsl(") == true) goto L38;
        return parseColourKeyword(r04);
    L38:
        if (r14 == true) goto L41;
        r2 = 4;
    L41:
        TextScanner r05 = new TextScanner(r11.substring(r2));
        r05.skipWhitespace();
        float r23 = r05.nextFloat();
        float r4 = r05.checkedNextFloat(r23);
        if (Float.isNaN(r4) == true) goto L44;
        r05.consume('%');
    L44:
        float r8 = r05.checkedNextFloat(r4);
        if (Float.isNaN(r8) == true) goto L47;
        r05.consume('%');
    L47:
        if (r14 == false) goto L56;
        float r15 = r05.checkedNextFloat(r8);
        r05.skipWhitespace();
        if (Float.isNaN(r15) == true) goto L55;
        if (r05.consume(')') == false) goto L55;
        return new SVG.Colour((clamp255(r15 * 256.0f) << 24) | hslToRgb(r23, r4, r8));
    L55:
        throw new SVGParseException("Bad hsla() colour value: ".concat(r11));
    L56:
        r05.skipWhitespace();
        if (Float.isNaN(r8) == true) goto L63;
        if (r05.consume(')') == false) goto L63;
        return new SVG.Colour(hslToRgb(r23, r4, r8) | (-16777216));
    L63:
        throw new SVGParseException("Bad hsl() colour value: ".concat(r11));
    L65:
        if (r13 == true) goto L68;
        r2 = 4;
    L68:
        TextScanner r06 = new TextScanner(r11.substring(r2));
        r06.skipWhitespace();
        float r24 = r06.nextFloat();
        if (Float.isNaN(r24) == false) goto L71;
    L73:
        float r42 = r06.checkedNextFloat(r24);
        if (Float.isNaN(r42) == false) goto L76;
    L78:
        float r9 = r06.checkedNextFloat(r42);
        if (Float.isNaN(r9) == false) goto L81;
    L83:
        if (r13 == false) goto L92;
        float r16 = r06.checkedNextFloat(r9);
        r06.skipWhitespace();
        if (Float.isNaN(r16) == true) goto L91;
        if (r06.consume(')') == false) goto L91;
        return new SVG.Colour((((clamp255(r16 * 256.0f) << 24) | (clamp255(r24) << 16)) | (clamp255(r42) << 8)) | clamp255(r9));
    L91:
        throw new SVGParseException("Bad rgba() colour value: ".concat(r11));
    L92:
        r06.skipWhitespace();
        if (Float.isNaN(r9) == true) goto L99;
        if (r06.consume(')') == false) goto L99;
        return new SVG.Colour((((clamp255(r24) << 16) | (-16777216)) | (clamp255(r42) << 8)) | clamp255(r9));
    L99:
        throw new SVGParseException("Bad rgb() colour value: ".concat(r11));
    L81:
        if (r06.consume('%') == false) goto L83;
        r9 = (r9 * 256.0f) / 100.0f;
        goto L83
    L76:
        if (r06.consume('%') == false) goto L78;
        r42 = (r42 * 256.0f) / 100.0f;
        goto L78
    L71:
        if (r06.consume('%') == false) goto L73;
        r24 = (r24 * 256.0f) / 100.0f;
        goto L73
    }

    private static SVG.Colour parseColourKeyword(String r1) {
        Integer r0 = ColourKeywords.get(r1);
        if (r0 != null) goto L5;
        SVGParser$$ExternalSyntheticBUOutline0.m("Invalid colour keyword: ", r1);
        return null;
    L5:
        return new SVG.Colour(r0.intValue());
    }

    private static SVG.SvgPaint parseColourSpecifer(String r1) {
        r1.getClass();
        if (r1.equals("none") == true) goto L13;
        if (r1.equals("currentColor") == true) goto L11;
        return parseColour(r1);
    L8:
        return null;
    L11:
        return SVG.CurrentColor.getInstance();
    L13:
        return SVG.Colour.TRANSPARENT;
    }

    private static SVG.Style.FillRule parseFillRule(String r1) {
        if ("nonzero".equals(r1) == false) goto L7;
        return SVG.Style.FillRule.NonZero;
    L7:
        if ("evenodd".equals(r1) == true) goto L9;
        return null;
    L9:
        return SVG.Style.FillRule.EvenOdd;
    }

    private static float parseFloat(String r2) {
        int r0 = r2.length();
        if (r0 != 0) goto L5;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid float value (empty string)");
        return 0.0f;
    L5:
        return parseFloat(r2, 0, r0);
    }

    private static float parseFloat(String r1, int r2, int r3) {
        float r22 = new NumberParser().parseNumber(r1, r2, r3);
        if (Float.isNaN(r22) == true) goto L5;
        return r22;
    L5:
        SVGParser$$ExternalSyntheticBUOutline0.m("Invalid float value: ", r1);
        return 0.0f;
    }

    private static void parseFont(SVG.Style r6, String r7) {
        if ("|caption|icon|menu|message-box|small-caption|status-bar|".contains("|" + r7 + '|') == false) goto L8;
        TextScanner r0 = new TextScanner(r7);
        Integer r72 = null;
        SVG.Style.FontStyle r1 = null;
        String r2 = null;
    L6:
        String r4 = r0.nextToken('/');
        r0.skipWhitespace();
        if (r4 == null) goto L60;
        if (r72 == null) goto L13;
        if (r1 == null) goto L13;
    L27:
        SVG.Length r22 = parseFontSize(r4);
        if (r0.consume('/') == false) goto L35;
        r0.skipWhitespace();
        String r3 = r0.nextToken();
        if (r3 != null) goto L44;
    L34:
        r0.skipWhitespace();
        goto L35
    L44:
        parseLength(r3);     // Catch: SVGParseException -> L33
    L33:
        return;
    L35:
        r6.fontFamily = parseFontFamily(r0.restOfText());
        r6.fontSize = r22;
        if (r72 != null) goto L38;
        int r73 = MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
    L39:
        r6.fontWeight = Integer.valueOf(r73);
        if (r1 != null) goto L42;
        r1 = SVG.Style.FontStyle.Normal;
    L42:
        r6.fontStyle = r1;
        r6.specifiedFlags |= 122880;
        return;
    L38:
        r73 = r72.intValue();
    L13:
        if (r4.equals("normal") == true) goto L6;
        if (r72 != null) goto L19;
        r72 = FontWeightKeywords.get(r4);
        if (r72 != null) goto L6;
    L19:
        if (r1 != null) goto L23;
        r1 = parseFontStyle(r4);
        if (r1 != null) goto L6;
    L23:
        if (r2 != null) goto L27;
        if (r4.equals("small-caps") == false) goto L27;
        r2 = r4;
        goto L6
    L60:
        return;
    }

    private static List<String> parseFontFamily(String r2) {
        TextScanner r0 = new TextScanner(r2);
        ArrayList r22 = null;
    L3:
        String r1 = r0.nextQuotedString();
        if (r1 != null) goto L6;
        r1 = r0.nextTokenWithWhitespace(',');
    L6:
        if (r1 == null) goto L7;
        if (r22 != null) goto L10;
        r22 = new ArrayList();
    L10:
        r22.add(r1);
        r0.skipCommaWhitespace();
        if (r0.empty() == false) goto L3;
        return r22;
    L7:
        return r22;
    }

    private static SVG.Length parseFontSize(String r1) {
        SVG.Length r0 = FontSizeKeywords.get(r1);     // Catch: SVGParseException -> L7
        if (r0 != null) goto L6;
        return parseLength(r1);
    L6:
        return r0;
    L7:
        return null;
    }

    private static SVG.Style.FontStyle parseFontStyle(String r2) {
        r2.getClass();
        char r1 = 65535;
        switch(r2.hashCode()) {
            case -1657669071: goto L14;
            case -1178781136: goto L10;
            case -1039745817: goto L6;
            default: goto L17;
        };
    L17:
        switch(r1) {
            case 0: goto L25;
            case 1: goto L23;
            case 2: goto L21;
            default: goto L18;
        };
    L18:
        return null;
    L21:
        return SVG.Style.FontStyle.Normal;
    L23:
        return SVG.Style.FontStyle.Italic;
    L25:
        return SVG.Style.FontStyle.Oblique;
    L6:
        if (r2.equals("normal") == false) goto L17;
        r1 = 2;
        goto L17
    L10:
        if (r2.equals("italic") == false) goto L17;
        r1 = 1;
        goto L17
    L14:
        if (r2.equals("oblique") == false) goto L17;
        r1 = 0;
        goto L17
    }

    private static Integer parseFontWeight(String r0) {
        return FontWeightKeywords.get(r0);
    }

    private static String parseFunctionalIRI(String r1, String r2) {
        if (r1.equals("none") == false) goto L6;
        return null;
    L6:
        if (r1.startsWith("url(") == true) goto L9;
        return null;
    L9:
        if (r1.endsWith(")") == false) goto L13;
        return r1.substring(4, r1.length() - 1).trim();
    L13:
        return r1.substring(4).trim();
    }

    private Float parseGradientOffset(String r5) {
        if (r5.length() == 0) goto L23;
        int r4 = r5.length();
        boolean r1 = true;
        if (r5.charAt(r5.length() - 1) != '%') goto L7;
        r4 = r4 - 1;
    L25:
        float r42 = parseFloat(r5, 0, r4);     // Catch: NumberFormatException -> L20
        float r0 = 100.0f;
        if (r1 == false) goto L12;
        r42 = r42 / 100.0f;     // Catch: NumberFormatException -> L20
    L12:
        if (r42 >= 0.0f) goto L15;
        r0 = 0.0f;
    L18:
        return Float.valueOf(r0);
    L15:
        if (r42 > 100.0f) goto L18;
        r0 = r42;
    L20:
        e = move-exception;
        throw new SVGParseException("Invalid offset value in <stop>: ".concat(r5), e);
    L7:
        r1 = false;
        goto L25
    L23:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid offset value in <stop> (empty string)");
        return null;
    }

    public static SVG.Length parseLength(String r4) {
        if (r4.length() == 0) goto L24;
        int r0 = r4.length();
        SVG.Unit r1 = SVG.Unit.px;
        char r2 = r4.charAt(r0 - 1);
        if (r2 != '%') goto L8;
        r0 = r0 - 1;
        r1 = SVG.Unit.percent;
    L26:
        return new SVG.Length(parseFloat(r4, 0, r0), r1);
    L21:
        e = move-exception;
        throw new SVGParseException("Invalid length value: ".concat(r4), e);
    L8:
        if (r0 <= 2) goto L26;
        if (Character.isLetter(r2) == false) goto L26;
        if (Character.isLetter(r4.charAt(r0 - 2)) == false) goto L26;
        r0 = r0 - 2;
        r1 = SVG.Unit.valueOf(r4.substring(r0).toLowerCase(Locale.US));     // Catch: IllegalArgumentException -> L16
    L17:
        throw new SVGParseException("Invalid length unit specifier: ".concat(r4));
    L24:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid length value (empty string)");
        return null;
    }

    private static List<SVG.Length> parseLengthList(String r4) {
        if (r4.length() == 0) goto L16;
        ArrayList r0 = new ArrayList(1);
        TextScanner r1 = new TextScanner(r4);
        r1.skipWhitespace();
    L6:
        if (r1.empty() == true) goto L15;
        float r42 = r1.nextFloat();
        if (Float.isNaN(r42) == true) goto L14;
        SVG.Unit r2 = r1.nextUnit();
        if (r2 != null) goto L12;
        r2 = SVG.Unit.px;
    L12:
        r0.add(new SVG.Length(r42, r2));
        r1.skipCommaWhitespace();
        goto L6
    L14:
        throw new SVGParseException("Invalid length list value: " + r1.ahead());
    L15:
        return r0;
    L16:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid length list (empty string)");
        return null;
    }

    private static SVG.Length parseLengthOrAuto(TextScanner r1) {
        if (r1.consume("auto") == false) goto L7;
        return new SVG.Length(0.0f);
    L7:
        return r1.nextLength();
    }

    private static Float parseOpacity(String r2) {
        float r22 = parseFloat(r2);     // Catch: SVGParseException -> L10
        float r0 = 0.0f;
        if (r22 >= 0.0f) goto L5;
    L4:
        r22 = r0;
    L8:
        return Float.valueOf(r22);
    L5:
        r0 = 1.0f;
        if (r22 <= 1.0f) goto L8;
    L10:
        return null;
    }

    private static Boolean parseOverflow(String r2) {
        r2.getClass();
        char r1 = 65535;
        switch(r2.hashCode()) {
            case -1217487446: goto L18;
            case -907680051: goto L14;
            case 3005871: goto L10;
            case 466743410: goto L6;
            default: goto L21;
        };
    L21:
        switch(r1) {
            case 0: goto L27;
            case 1: goto L27;
            case 2: goto L25;
            case 3: goto L25;
            default: goto L22;
        };
    L22:
        return null;
    L25:
        return Boolean.TRUE;
    L27:
        return Boolean.FALSE;
    L6:
        if (r2.equals("visible") == false) goto L21;
        r1 = 3;
        goto L21
    L10:
        if (r2.equals("auto") == false) goto L21;
        r1 = 2;
        goto L21
    L14:
        if (r2.equals("scroll") == false) goto L21;
        r1 = 1;
        goto L21
    L18:
        if (r2.equals("hidden") == false) goto L21;
        r1 = 0;
        goto L21
    }

    private static SVG.SvgPaint parsePaintSpecifier(String r4) {
        if (r4.startsWith("url(") == false) goto L14;
        int r0 = r4.indexOf(")");
        SVG.SvgPaint r2 = null;
        if (r0 == (-1)) goto L12;
        String r1 = r4.substring(4, r0).trim();
        String r42 = r4.substring(r0 + 1).trim();
        if (r42.length() <= 0) goto L10;
        r2 = parseColourSpecifer(r42);
    L10:
        return new SVG.PaintReference(r1, r2);
    L12:
        return new SVG.PaintReference(r4.substring(4).trim(), null);
    L14:
        return parseColourSpecifer(r4);
    }

    private static SVG.PathDefinition parsePath(String r19) {
        TextScanner r0 = new TextScanner(r19);
        SVG.PathDefinition r1 = new SVG.PathDefinition();
        if (r0.empty() == true) goto L99;
        int r2 = r0.nextChar().intValue();
        int r9 = 109;
        if (r2 == 77) goto L9;
        if (r2 != 109) goto L99;
    L9:
        int r11 = r2;
        float r22 = 0.0f;
        float r3 = 0.0f;
        float r4 = 0.0f;
        float r5 = 0.0f;
        float r12 = 0.0f;
        float r13 = 0.0f;
    L10:
        r0.skipWhitespace();
        int r6 = 108;
        switch(r11) {
            case 65: goto L86;
            case 67: goto L78;
            case 72: goto L70;
            case 76: goto L62;
            case 77: goto L49;
            case 81: goto L41;
            case 83: goto L32;
            case 84: goto L24;
            case 86: goto L16;
            case 90: goto L13;
            case 97: goto L86;
            case 99: goto L78;
            case 104: goto L70;
            case 108: goto L62;
            case 109: goto L49;
            case 113: goto L41;
            case 115: goto L32;
            case 116: goto L24;
            case 118: goto L16;
            case 122: goto L13;
            default: goto L99;
        };
    L13:
        r1.close();
        r22 = r12;
        r3 = r22;
        r4 = r13;
    L14:
        r5 = r4;
    L97:
        r0.skipCommaWhitespace();
        if (r0.empty() == true) goto L99;
        if (r0.hasLetter() == false) goto L103;
        r11 = r0.nextChar().intValue();
    L103:
        r9 = 109;
        goto L10
    L16:
        float r52 = r0.nextFloat();
        if (Float.isNaN(r52) == true) goto L18;
        if (r11 != 118) goto L23;
        r52 = r52 + r4;
    L23:
        r4 = r52;
        r1.lineTo(r22, r4);
        goto L14
    L18:
        Log.e("SVGParser", "Bad path coords for " + ((char) r11) + " path segment");
        return r1;
    L24:
        r3 = (r22 * 2.0f) - r3;
        r5 = (2.0f * r4) - r5;
        float r62 = r0.nextFloat();
        float r7 = r0.checkedNextFloat(r62);
        if (Float.isNaN(r7) == true) goto L26;
        if (r11 != 116) goto L31;
        r62 = r62 + r22;
        r7 = r7 + r4;
    L31:
        r22 = r62;
        r4 = r7;
        r1.quadTo(r3, r5, r22, r4);
        goto L97
    L26:
        Log.e("SVGParser", "Bad path coords for " + ((char) r11) + " path segment");
        return r1;
    L32:
        float r63 = (r22 * 2.0f) - r3;
        float r32 = (2.0f * r4) - r5;
        float r53 = r0.nextFloat();
        float r72 = r0.checkedNextFloat(r53);
        float r10 = r0.checkedNextFloat(r72);
        float r16 = r0.checkedNextFloat(r10);
        if (Float.isNaN(r16) == true) goto L34;
        if (r11 != 115) goto L39;
        r10 = r10 + r22;
        r16 = r16 + r4;
        r53 = r53 + r22;
        r72 = r72 + r4;
    L39:
        float r42 = r53;
        r5 = r72;
        float r64 = r10;
        float r73 = r16;
        r1.cubicTo(r63, r32, r42, r5, r64, r73);
    L40:
        r3 = r42;
        r22 = r64;
        r4 = r73;
        goto L97
    L34:
        Log.e("SVGParser", "Bad path coords for " + ((char) r11) + " path segment");
        return r1;
    L41:
        r3 = r0.nextFloat();
        r5 = r0.checkedNextFloat(r3);
        float r65 = r0.checkedNextFloat(r5);
        float r74 = r0.checkedNextFloat(r65);
        if (Float.isNaN(r74) == true) goto L43;
        if (r11 != 113) goto L48;
        r65 = r65 + r22;
        r74 = r74 + r4;
        r3 = r3 + r22;
        r5 = r5 + r4;
    L48:
        r22 = r65;
        r4 = r74;
        r1.quadTo(r3, r5, r22, r4);
        goto L97
    L43:
        Log.e("SVGParser", "Bad path coords for " + ((char) r11) + " path segment");
        return r1;
    L49:
        float r33 = r0.nextFloat();
        float r54 = r0.checkedNextFloat(r33);
        if (Float.isNaN(r54) == true) goto L51;
        if (r11 == r9) goto L55;
    L57:
        r22 = r33;
        r4 = r54;
        r1.moveTo(r22, r4);
        if (r11 == r9) goto L61;
        r6 = 76;
    L61:
        r3 = r22;
        r12 = r3;
        r5 = r4;
        r13 = r5;
        r11 = r6;
        goto L97
    L55:
        if (r1.isEmpty() == true) goto L57;
        r33 = r33 + r22;
        r54 = r54 + r4;
        goto L57
    L51:
        Log.e("SVGParser", "Bad path coords for " + ((char) r11) + " path segment");
        return r1;
    L62:
        float r34 = r0.nextFloat();
        float r55 = r0.checkedNextFloat(r34);
        if (Float.isNaN(r55) == true) goto L64;
        if (r11 != 108) goto L68;
        r34 = r34 + r22;
        r55 = r55 + r4;
    L68:
        r22 = r34;
        r4 = r55;
        r1.lineTo(r22, r4);
        r3 = r22;
    L69:
        r5 = r4;
        goto L97
    L64:
        Log.e("SVGParser", "Bad path coords for " + ((char) r11) + " path segment");
        return r1;
    L70:
        float r35 = r0.nextFloat();
        if (Float.isNaN(r35) == true) goto L72;
        if (r11 != 104) goto L77;
        r35 = r35 + r22;
    L77:
        r22 = r35;
        r1.lineTo(r22, r4);
        r3 = r22;
        goto L97
    L72:
        Log.e("SVGParser", "Bad path coords for " + ((char) r11) + " path segment");
        return r1;
    L78:
        float r36 = r0.nextFloat();
        float r56 = r0.checkedNextFloat(r36);
        float r66 = r0.checkedNextFloat(r56);
        float r75 = r0.checkedNextFloat(r66);
        float r102 = r0.checkedNextFloat(r75);
        float r162 = r0.checkedNextFloat(r102);
        if (Float.isNaN(r162) == true) goto L80;
        if (r11 != 99) goto L85;
        r102 = r102 + r22;
        r162 = r162 + r4;
        r36 = r36 + r22;
        r56 = r56 + r4;
        r66 = r66 + r22;
        r75 = r75 + r4;
    L85:
        float r23 = r36;
        float r37 = r56;
        r42 = r66;
        r5 = r75;
        r64 = r102;
        r73 = r162;
        r1.cubicTo(r23, r37, r42, r5, r64, r73);
        goto L40
    L80:
        Log.e("SVGParser", "Bad path coords for " + ((char) r11) + " path segment");
        return r1;
    L86:
        float r103 = r22;
        float r24 = r0.nextFloat();
        float r38 = r0.checkedNextFloat(r24);
        float r57 = r4;
        float r43 = r0.checkedNextFloat(r38);
        Boolean r67 = r0.checkedNextFlag(Float.valueOf(r43));
        Boolean r76 = r0.checkedNextFlag(r67);
        float r92 = r0.checkedNextFloat(r76);
        float r17 = r0.checkedNextFloat(r92);
        if (Float.isNaN(r17) == true) goto L104;
        if (r24 < 0.0f) goto L104;
        if (r38 < 0.0f) goto L104;
        if (r11 != 97) goto L96;
        r92 = r92 + r103;
        r17 = r17 + r57;
    L96:
        float r8 = r17;
        boolean r58 = r67.booleanValue();
        boolean r68 = r76.booleanValue();
        float r77 = r92;
        r1.arcTo(r24, r38, r43, r58, r68, r77, r8);
        r22 = r77;
        r3 = r22;
        r4 = r8;
    L104:
        Log.e("SVGParser", "Bad path coords for " + ((char) r11) + " path segment");
        return r1;
    L99:
        return r1;
    }

    public static PreserveAspectRatio parsePreserveAspectRatio(String r4) {
        TextScanner r0 = new TextScanner(r4);
        r0.skipWhitespace();
        String r1 = r0.nextToken();
        if ("defer".equals(r1) == false) goto L5;
        r0.skipWhitespace();
        r1 = r0.nextToken();
    L5:
        PreserveAspectRatio.Alignment r12 = AspectRatioKeywords.get(r1);
        r0.skipWhitespace();
        PreserveAspectRatio.Scale r3 = null;
        if (r0.empty() == true) goto L16;
        String r02 = r0.nextToken();
        r02.getClass();
        if (r02.equals("meet") == false) goto L10;
        r3 = PreserveAspectRatio.Scale.meet;
        goto L16
    L10:
        if (r02.equals("slice") == true) goto L13;
        SVGParser$$ExternalSyntheticBUOutline0.m("Invalid preserveAspectRatio definition: ", r4);
        return null;
    L13:
        r3 = PreserveAspectRatio.Scale.slice;
    L16:
        return new PreserveAspectRatio(r12, r3);
    }

    private static void parsePreserveAspectRatio(SVG.SvgPreserveAspectRatioContainer r0, String r1) {
        r0.preserveAspectRatio = parsePreserveAspectRatio(r1);
    }

    private Map<String, String> parseProcessingInstructionAttributes(TextScanner r4) {
        HashMap r3 = new HashMap();
        r4.skipWhitespace();
        String r1 = r4.nextToken(SignatureVisitor.INSTANCEOF);
    L3:
        if (r1 == null) goto L5;
        r4.consume(SignatureVisitor.INSTANCEOF);
        r3.put(r1, r4.nextQuotedString());
        r4.skipWhitespace();
        r1 = r4.nextToken(SignatureVisitor.INSTANCEOF);
        goto L3
    L5:
        return r3;
    }

    private static SVG.Style.RenderQuality parseRenderQuality(String r2) {
        r2.getClass();
        char r1 = 65535;
        switch(r2.hashCode()) {
            case -933002398: goto L14;
            case 3005871: goto L10;
            case 362741610: goto L6;
            default: goto L17;
        };
    L17:
        switch(r1) {
            case 0: goto L25;
            case 1: goto L23;
            case 2: goto L21;
            default: goto L18;
        };
    L18:
        return null;
    L21:
        return SVG.Style.RenderQuality.optimizeSpeed;
    L23:
        return SVG.Style.RenderQuality.auto;
    L25:
        return SVG.Style.RenderQuality.optimizeQuality;
    L6:
        if (r2.equals("optimizeSpeed") == false) goto L17;
        r1 = 2;
        goto L17
    L10:
        if (r2.equals("auto") == false) goto L17;
        r1 = 1;
        goto L17
    L14:
        if (r2.equals("optimizeQuality") == false) goto L17;
        r1 = 0;
        goto L17
    }

    private static Set<String> parseRequiredFeatures(String r3) {
        TextScanner r0 = new TextScanner(r3);
        HashSet r32 = new HashSet();
    L4:
        if (r0.empty() == true) goto L10;
        String r1 = r0.nextToken();
        if (r1.startsWith("http://www.w3.org/TR/SVG11/feature#") == false) goto L8;
        r32.add(r1.substring(35));
    L9:
        r0.skipWhitespace();
        goto L4
    L8:
        r32.add("UNSUPPORTED");
        goto L9
    L10:
        return r32;
    }

    private static Set<String> parseRequiredFormats(String r2) {
        TextScanner r0 = new TextScanner(r2);
        HashSet r22 = new HashSet();
    L4:
        if (r0.empty() == true) goto L6;
        r22.add(r0.nextToken());
        r0.skipWhitespace();
        goto L4
    L6:
        return r22;
    }

    private static SVG.Length[] parseStrokeDashArray(String r5) {
        TextScanner r0 = new TextScanner(r5);
        r0.skipWhitespace();
        if (r0.empty() == false) goto L5;
        return null;
    L5:
        SVG.Length r52 = r0.nextLength();
        if (r52 != null) goto L9;
        return null;
    L9:
        if (r52.isNegative() == false) goto L11;
        return null;
    L11:
        float r2 = r52.floatValue();
        ArrayList r3 = new ArrayList();
        r3.add(r52);
    L13:
        if (r0.empty() == true) goto L22;
        r0.skipCommaWhitespace();
        SVG.Length r53 = r0.nextLength();
        if (r53 == null) goto L16;
        if (r53.isNegative() == true) goto L19;
        r3.add(r53);
        r2 = r2 + r53.floatValue();
        goto L13
    L19:
        return null;
    L16:
        return null;
    L22:
        if (r2 != 0.0f) goto L25;
        return null;
    L25:
        return (SVG.Length[]) r3.toArray(new SVG.Length[r3.size()]);
    }

    private static SVG.Style.LineCap parseStrokeLineCap(String r1) {
        if ("butt".equals(r1) == false) goto L7;
        return SVG.Style.LineCap.Butt;
    L7:
        if ("round".equals(r1) == false) goto L11;
        return SVG.Style.LineCap.Round;
    L11:
        if ("square".equals(r1) == true) goto L13;
        return null;
    L13:
        return SVG.Style.LineCap.Square;
    }

    private static SVG.Style.LineJoin parseStrokeLineJoin(String r1) {
        if ("miter".equals(r1) == false) goto L7;
        return SVG.Style.LineJoin.Miter;
    L7:
        if ("round".equals(r1) == false) goto L11;
        return SVG.Style.LineJoin.Round;
    L11:
        if ("bevel".equals(r1) == true) goto L13;
        return null;
    L13:
        return SVG.Style.LineJoin.Bevel;
    }

    private static void parseStyle(SVG.SvgElementBase r4, String r5) {
        TextScanner r0 = new TextScanner(r5.replaceAll("/\\*.*?\\*/", _UrlKt.FRAGMENT_ENCODE_SET));
    L3:
        String r1 = r0.nextToken(':');
        r0.skipWhitespace();
        if (r0.consume(':') == false) goto L8;
        r0.skipWhitespace();
        String r2 = r0.nextTokenWithWhitespace(';');
        if (r2 == null) goto L24;
        r0.skipWhitespace();
        if (r0.empty() == true) goto L14;
        if (r0.consume(';') == false) goto L3;
    L14:
        if (r4.style != null) goto L16;
        r4.style = new SVG.Style();
    L16:
        processStyleProperty(r4.style, r1, r2);
        r0.skipWhitespace();
        goto L3
    L24:
        return;
    }

    private static Set<String> parseSystemLanguage(String r4) {
        TextScanner r0 = new TextScanner(r4);
        HashSet r42 = new HashSet();
    L4:
        if (r0.empty() == true) goto L9;
        String r1 = r0.nextToken();
        int r2 = r1.indexOf(45);
        if (r2 == (-1)) goto L8;
        r1 = r1.substring(0, r2);
    L8:
        r42.add(new Locale(r1, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET).getLanguage());
        r0.skipWhitespace();
        goto L4
    L9:
        return r42;
    }

    private static SVG.Style.TextAnchor parseTextAnchor(String r2) {
        r2.getClass();
        char r1 = 65535;
        switch(r2.hashCode()) {
            case -1074341483: goto L14;
            case 100571: goto L10;
            case 109757538: goto L6;
            default: goto L17;
        };
    L17:
        switch(r1) {
            case 0: goto L25;
            case 1: goto L23;
            case 2: goto L21;
            default: goto L18;
        };
    L18:
        return null;
    L21:
        return SVG.Style.TextAnchor.Start;
    L23:
        return SVG.Style.TextAnchor.End;
    L25:
        return SVG.Style.TextAnchor.Middle;
    L6:
        if (r2.equals("start") == false) goto L17;
        r1 = 2;
        goto L17
    L10:
        if (r2.equals("end") == false) goto L17;
        r1 = 1;
        goto L17
    L14:
        if (r2.equals("middle") == false) goto L17;
        r1 = 0;
        goto L17
    }

    private static SVG.Style.TextDecoration parseTextDecoration(String r2) {
        r2.getClass();
        char r1 = 65535;
        switch(r2.hashCode()) {
            case -1171789332: goto L22;
            case -1026963764: goto L18;
            case 3387192: goto L14;
            case 93826908: goto L10;
            case 529818312: goto L6;
            default: goto L25;
        };
    L25:
        switch(r1) {
            case 0: goto L37;
            case 1: goto L35;
            case 2: goto L33;
            case 3: goto L31;
            case 4: goto L29;
            default: goto L26;
        };
    L26:
        return null;
    L29:
        return SVG.Style.TextDecoration.Overline;
    L31:
        return SVG.Style.TextDecoration.Blink;
    L33:
        return SVG.Style.TextDecoration.None;
    L35:
        return SVG.Style.TextDecoration.Underline;
    L37:
        return SVG.Style.TextDecoration.LineThrough;
    L6:
        if (r2.equals("overline") == false) goto L25;
        r1 = 4;
        goto L25
    L10:
        if (r2.equals("blink") == false) goto L25;
        r1 = 3;
        goto L25
    L14:
        if (r2.equals("none") == false) goto L25;
        r1 = 2;
        goto L25
    L18:
        if (r2.equals("underline") == false) goto L25;
        r1 = 1;
        goto L25
    L22:
        if (r2.equals("line-through") == false) goto L25;
        r1 = 0;
        goto L25
    }

    private static SVG.Style.TextDirection parseTextDirection(String r1) {
        r1.getClass();
        if (r1.equals("ltr") == true) goto L11;
        if (r1.equals("rtl") == true) goto L9;
        return null;
    L9:
        return SVG.Style.TextDirection.RTL;
    L11:
        return SVG.Style.TextDirection.LTR;
    }

    private Matrix parseTransformList(String r21) {
        Matrix r1 = new Matrix();
        TextScanner r2 = new TextScanner(r21);
        r2.skipWhitespace();
    L4:
        if (r2.empty() == true) goto L99;
        String r3 = r2.nextFunction();
        if (r3 == null) goto L97;
        char r12 = 65535;
        switch(r3.hashCode()) {
            case -1081239615: goto L31;
            case -925180581: goto L27;
            case 109250890: goto L23;
            case 109493390: goto L19;
            case 109493391: goto L15;
            case 1052832078: goto L11;
            default: goto L35;
        };
    L11:
        if (r3.equals("translate") == false) goto L35;
        r12 = 5;
        goto L35
    L15:
        if (r3.equals("skewY") == false) goto L35;
        r12 = 4;
        goto L35
    L19:
        if (r3.equals("skewX") == false) goto L35;
        r12 = 3;
        goto L35
    L23:
        if (r3.equals("scale") == false) goto L35;
        r12 = 2;
        goto L35
    L27:
        if (r3.equals("rotate") == false) goto L35;
        r12 = 1;
        goto L35
    L31:
        if (r3.equals("matrix") == false) goto L35;
        r12 = 0;
    L35:
        switch(r12) {
            case 0: goto L86;
            case 1: goto L72;
            case 2: goto L62;
            case 3: goto L55;
            case 4: goto L48;
            case 5: goto L38;
            default: goto L36;
        };
    L38:
        r2.skipWhitespace();
        float r32 = r2.nextFloat();
        float r6 = r2.possibleNextFloat();
        r2.skipWhitespace();
        if (Float.isNaN(r32) == true) goto L46;
        if (r2.consume(')') == false) goto L46;
        if (Float.isNaN(r6) == false) goto L45;
        r1.preTranslate(r32, 0.0f);
    L92:
        if (r2.empty() == true) goto L99;
        r2.skipCommaWhitespace();
        goto L4
    L45:
        r1.preTranslate(r32, r6);
    L46:
        SVGParser$$ExternalSyntheticBUOutline0.m("Invalid transform list: ", r21);
        return null;
    L48:
        r2.skipWhitespace();
        float r33 = r2.nextFloat();
        r2.skipWhitespace();
        if (Float.isNaN(r33) == true) goto L53;
        if (r2.consume(')') == false) goto L53;
        r1.preSkew(0.0f, (float) Math.tan(Math.toRadians(r33)));
    L53:
        SVGParser$$ExternalSyntheticBUOutline0.m("Invalid transform list: ", r21);
        return null;
    L55:
        r2.skipWhitespace();
        float r34 = r2.nextFloat();
        r2.skipWhitespace();
        if (Float.isNaN(r34) == true) goto L60;
        if (r2.consume(')') == false) goto L60;
        r1.preSkew((float) Math.tan(Math.toRadians(r34)), 0.0f);
    L60:
        SVGParser$$ExternalSyntheticBUOutline0.m("Invalid transform list: ", r21);
        return null;
    L62:
        r2.skipWhitespace();
        float r35 = r2.nextFloat();
        float r5 = r2.possibleNextFloat();
        r2.skipWhitespace();
        if (Float.isNaN(r35) == true) goto L70;
        if (r2.consume(')') == false) goto L70;
        if (Float.isNaN(r5) == false) goto L69;
        r1.preScale(r35, r35);
        goto L92
    L69:
        r1.preScale(r35, r5);
    L70:
        SVGParser$$ExternalSyntheticBUOutline0.m("Invalid transform list: ", r21);
        return null;
    L72:
        r2.skipWhitespace();
        float r36 = r2.nextFloat();
        float r52 = r2.possibleNextFloat();
        float r62 = r2.possibleNextFloat();
        r2.skipWhitespace();
        if (Float.isNaN(r36) == true) goto L84;
        if (r2.consume(')') == false) goto L84;
        if (Float.isNaN(r52) == false) goto L80;
        r1.preRotate(r36);
        goto L92
    L80:
        if (Float.isNaN(r62) == true) goto L82;
        r1.preRotate(r36, r52, r62);
        goto L92
    L82:
        SVGParser$$ExternalSyntheticBUOutline0.m("Invalid transform list: ", r21);
        return null;
    L84:
        SVGParser$$ExternalSyntheticBUOutline0.m("Invalid transform list: ", r21);
        return null;
    L86:
        r2.skipWhitespace();
        float r37 = r2.nextFloat();
        r2.skipCommaWhitespace();
        float r122 = r2.nextFloat();
        r2.skipCommaWhitespace();
        float r15 = r2.nextFloat();
        r2.skipCommaWhitespace();
        float r16 = r2.nextFloat();
        r2.skipCommaWhitespace();
        float r17 = r2.nextFloat();
        r2.skipCommaWhitespace();
        float r18 = r2.nextFloat();
        r2.skipWhitespace();
        if (Float.isNaN(r18) == true) goto L95;
        if (r2.consume(')') == false) goto L95;
        Matrix r4 = new Matrix();
        r4.setValues(new float[]{r37, r15, r17, r122, r16, r18, 0.0f, 0.0f, 1.0f});
        r1.preConcat(r4);
    L95:
        SVGParser$$ExternalSyntheticBUOutline0.m("Invalid transform list: ", r21);
        return null;
    L36:
        SVGParser$$ExternalSyntheticBUOutline2.m("Invalid transform list fn: ", r3, ")");
        return null;
    L97:
        SVGParser$$ExternalSyntheticBUOutline0.m("Bad transform function encountered in transform list: ", r21);
        return null;
    L99:
        return r1;
    }

    private void parseUsingSAX(InputStream r4) {
        Log.d("SVGParser", "Falling back to SAX parser");
        SAXParserFactory r0 = SAXParserFactory.newInstance();     // Catch: IOException -> L5 SAXException -> L8 ParserConfigurationException -> L11
        r0.setFeature("http://xml.org/sax/features/external-general-entities", false);     // Catch: IOException -> L5 SAXException -> L8 ParserConfigurationException -> L11
        r0.setFeature("http://xml.org/sax/features/external-parameter-entities", false);     // Catch: IOException -> L5 SAXException -> L8 ParserConfigurationException -> L11
        XMLReader r02 = r0.newSAXParser().getXMLReader();     // Catch: IOException -> L5 SAXException -> L8 ParserConfigurationException -> L11
        SAXHandler r1 = new SAXHandler(this, null);     // Catch: IOException -> L5 SAXException -> L8 ParserConfigurationException -> L11
        r02.setContentHandler(r1);     // Catch: IOException -> L5 SAXException -> L8 ParserConfigurationException -> L11
        r02.setProperty("http://xml.org/sax/properties/lexical-handler", r1);     // Catch: IOException -> L5 SAXException -> L8 ParserConfigurationException -> L11
        r02.parse(new InputSource(r4));     // Catch: IOException -> L5 SAXException -> L8 ParserConfigurationException -> L11
        return;
    L5:
        e = move-exception;
        throw new SVGParseException("Stream error", e);
    L11:
        e = move-exception;
        throw new SVGParseException("XML parser problem", e);
    L8:
        e = move-exception;
        throw new SVGParseException("SVG parse error", e);
    }

    private void parseUsingXmlPullParser(InputStream r9, boolean r10) {
        XmlPullParser r0 = Xml.newPullParser();     // Catch: IOException -> L42 XmlPullParserException -> L45
        XPPAttributesWrapper r1 = new XPPAttributesWrapper(this, r0);     // Catch: IOException -> L42 XmlPullParserException -> L45
        r0.setFeature("http://xmlpull.org/v1/doc/features.html#process-docdecl", false);     // Catch: IOException -> L42 XmlPullParserException -> L45
        r0.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);     // Catch: IOException -> L42 XmlPullParserException -> L45
        r0.setInput(r9, null);     // Catch: IOException -> L42 XmlPullParserException -> L45
        int r2 = r0.getEventType();     // Catch: IOException -> L42 XmlPullParserException -> L45
    L3:
        if (r2 == 1) goto L40;
        if (r2 != 0) goto L6;
        startDocument();     // Catch: IOException -> L42 XmlPullParserException -> L45
    L39:
        r2 = r0.nextToken();     // Catch: IOException -> L42 XmlPullParserException -> L45
        goto L3
    L6:
        if (r2 != 8) goto L8;
        Log.d("SVGParser", "PROC INSTR: " + r0.getText());     // Catch: IOException -> L42 XmlPullParserException -> L45
        TextScanner r22 = new TextScanner(r0.getText());     // Catch: IOException -> L42 XmlPullParserException -> L45
        handleProcessingInstruction(r22.nextToken(), parseProcessingInstructionAttributes(r22));     // Catch: IOException -> L42 XmlPullParserException -> L45
        goto L39
    L8:
        if (r2 != 10) goto L10;
        if (r10 == false) goto L39;
        if (this.svgDocument.getRootElement() != null) goto L39;
        if (r0.getText().contains("<!ENTITY ") == false) goto L39;
        Log.d("SVGParser", "Switching to SAX parser to process entities");     // Catch: IOException -> L35 XmlPullParserException -> L45
        r9.reset();     // Catch: IOException -> L35 XmlPullParserException -> L45
        parseUsingSAX(r9);     // Catch: IOException -> L35 XmlPullParserException -> L45
        return;
    L35:
        Log.w("SVGParser", "Detected internal entity definitions, but could not parse them.");     // Catch: IOException -> L42 XmlPullParserException -> L45
        return;
    L10:
        if (r2 != 2) goto L12;
        String r23 = r0.getName();     // Catch: IOException -> L42 XmlPullParserException -> L45
        if (r0.getPrefix() == null) goto L27;
        r23 = r0.getPrefix() + ':' + r23;     // Catch: IOException -> L42 XmlPullParserException -> L45
    L27:
        startElement(r0.getNamespace(), r0.getName(), r23, r1);     // Catch: IOException -> L42 XmlPullParserException -> L45
        goto L39
    L12:
        if (r2 != 3) goto L14;
        String r24 = r0.getName();     // Catch: IOException -> L42 XmlPullParserException -> L45
        if (r0.getPrefix() == null) goto L23;
        r24 = r0.getPrefix() + ':' + r24;     // Catch: IOException -> L42 XmlPullParserException -> L45
    L23:
        endElement(r0.getNamespace(), r0.getName(), r24);     // Catch: IOException -> L42 XmlPullParserException -> L45
        goto L39
    L14:
        if (r2 != 4) goto L16;
        int[] r25 = new int[2];     // Catch: IOException -> L42 XmlPullParserException -> L45
        text(r0.getTextCharacters(r25), r25[0], r25[1]);     // Catch: IOException -> L42 XmlPullParserException -> L45
        goto L39
    L16:
        if (r2 != 5) goto L39;
        text(r0.getText());     // Catch: IOException -> L42 XmlPullParserException -> L45
        goto L39
    L40:
        endDocument();     // Catch: IOException -> L42 XmlPullParserException -> L45
        return;
    L42:
        e = move-exception;
        throw new SVGParseException("Stream error", e);
    L45:
        e = move-exception;
        throw new SVGParseException("XML parser problem", e);
    }

    private static SVG.Style.VectorEffect parseVectorEffect(String r1) {
        r1.getClass();
        if (r1.equals("none") == true) goto L11;
        if (r1.equals("non-scaling-stroke") == true) goto L9;
        return null;
    L9:
        return SVG.Style.VectorEffect.NonScalingStroke;
    L11:
        return SVG.Style.VectorEffect.None;
    }

    private static SVG.Box parseViewBox(String r5) {
        TextScanner r0 = new TextScanner(r5);
        r0.skipWhitespace();
        float r52 = r0.nextFloat();
        r0.skipCommaWhitespace();
        float r1 = r0.nextFloat();
        r0.skipCommaWhitespace();
        float r2 = r0.nextFloat();
        r0.skipCommaWhitespace();
        float r02 = r0.nextFloat();
        if (Float.isNaN(r52) == false) goto L5;
    L20:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid viewBox definition - should have four numbers");
        return null;
    L5:
        if (Float.isNaN(r1) == true) goto L20;
        if (Float.isNaN(r2) == true) goto L20;
        if (Float.isNaN(r02) == true) goto L20;
        if (r2 >= 0.0f) goto L13;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid viewBox. width cannot be negative");
        return null;
    L13:
        if (r02 >= 0.0f) goto L15;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid viewBox. height cannot be negative");
        return null;
    L15:
        return new SVG.Box(r52, r1, r2, r02);
    }

    private void path(Attributes r3) {
        debug("<path>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.Path r0 = new SVG.Path();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesTransform(r0, r3);
        parseAttributesConditional(r0, r3);
        parseAttributesPath(r0, r3);
        this.currentElement.addChild(r0);
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private void pattern(Attributes r3) {
        debug("<pattern>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.Pattern r0 = new SVG.Pattern();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesConditional(r0, r3);
        parseAttributesViewBox(r0, r3);
        parseAttributesPattern(r0, r3);
        this.currentElement.addChild(r0);
        this.currentElement = r0;
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private void polygon(Attributes r3) {
        debug("<polygon>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.PolyLine r0 = new SVG.Polygon();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesTransform(r0, r3);
        parseAttributesConditional(r0, r3);
        parseAttributesPolyLine(r0, r3, "polygon");
        this.currentElement.addChild(r0);
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private void polyline(Attributes r3) {
        debug("<polyline>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.PolyLine r0 = new SVG.PolyLine();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesTransform(r0, r3);
        parseAttributesConditional(r0, r3);
        parseAttributesPolyLine(r0, r3, "polyline");
        this.currentElement.addChild(r0);
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    public static void processStyleProperty(SVG.Style r6, String r7, String r8) {
        if (r8.length() != 0) goto L6;
        return;
    L6:
        if (r8.equals("inherit") == false) goto L162;
        return;
    L162:
        switch(AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r7).ordinal()]) {
            case 47: goto L157;
            case 48: goto L153;
            case 49: goto L149;
            case 50: goto L145;
            case 51: goto L141;
            case 52: goto L139;
            case 53: goto L135;
            case 54: goto L131;
            case 55: goto L129;
            case 56: goto L122;
            case 57: goto L119;
            case 58: goto L117;
            case 59: goto L115;
            case 60: goto L113;
            case 61: goto L109;
            case 62: goto L105;
            case 63: goto L101;
            case 64: goto L97;
            case 65: goto L93;
            case 66: goto L89;
            case 67: goto L85;
            case 68: goto L81;
            case 69: goto L79;
            case 70: goto L77;
            case 71: goto L75;
            case 72: goto L73;
            case 73: goto L67;
            case 74: goto L60;
            case 75: goto L52;
            case 76: goto L49;
            case 77: goto L45;
            case 78: goto L43;
            case 79: goto L41;
            case 80: goto L39;
            case 81: goto L32;
            case 82: goto L29;
            case 83: goto L22;
            case 84: goto L19;
            case 85: goto L15;
            case 86: goto L11;
            default: goto L195;
        };
    L11:
        SVG.Style.RenderQuality r72 = parseRenderQuality(r8);
        r6.imageRendering = r72;
        if (r72 == null) goto L169;
        r6.specifiedFlags |= 137438953472L;
        return;
    L169:
        return;
    L15:
        SVG.Style.VectorEffect r73 = parseVectorEffect(r8);
        r6.vectorEffect = r73;
        if (r73 == null) goto L170;
        r6.specifiedFlags |= 34359738368L;
        return;
    L170:
        return;
    L19:
        r6.viewportFillOpacity = parseOpacity(r8);
        r6.specifiedFlags |= 17179869184L;
        return;
    L29:
        r6.solidOpacity = parseOpacity(r8);
        r6.specifiedFlags |= 4294967296L;
        return;
    L39:
        r6.mask = parseFunctionalIRI(r8, r7);
        r6.specifiedFlags |= 1073741824;
        return;
    L41:
        r6.clipRule = parseFillRule(r8);
        r6.specifiedFlags |= 536870912;
        return;
    L43:
        r6.clipPath = parseFunctionalIRI(r8, r7);
        r6.specifiedFlags |= 268435456;
        return;
    L45:
        SVG.CSSClipRect r74 = parseClip(r8);
        r6.clip = r74;
        if (r74 == null) goto L173;
        r6.specifiedFlags |= 1048576;
        return;
    L173:
        return;
    L49:
        r6.stopOpacity = parseOpacity(r8);
        r6.specifiedFlags |= 134217728;
        return;
    L73:
        r6.markerEnd = parseFunctionalIRI(r8, r7);
        r6.specifiedFlags |= 8388608;
        return;
    L75:
        r6.markerMid = parseFunctionalIRI(r8, r7);
        r6.specifiedFlags |= 4194304;
        return;
    L77:
        r6.markerStart = parseFunctionalIRI(r8, r7);
        r6.specifiedFlags |= 2097152;
        return;
    L79:
        String r75 = parseFunctionalIRI(r8, r7);
        r6.markerStart = r75;
        r6.markerMid = r75;
        r6.markerEnd = r75;
        r6.specifiedFlags |= 14680064;
        return;
    L81:
        Boolean r76 = parseOverflow(r8);
        r6.overflow = r76;
        if (r76 == null) goto L177;
        r6.specifiedFlags |= 524288;
        return;
    L177:
        return;
    L85:
        SVG.Style.TextAnchor r77 = parseTextAnchor(r8);
        r6.textAnchor = r77;
        if (r77 == null) goto L178;
        r6.specifiedFlags |= MediaStatus.COMMAND_STREAM_TRANSFER;
        return;
    L178:
        return;
    L89:
        SVG.Style.TextDirection r78 = parseTextDirection(r8);
        r6.direction = r78;
        if (r78 == null) goto L179;
        r6.specifiedFlags |= 68719476736L;
        return;
    L179:
        return;
    L93:
        SVG.Style.TextDecoration r79 = parseTextDecoration(r8);
        r6.textDecoration = r79;
        if (r79 == null) goto L180;
        r6.specifiedFlags |= MediaStatus.COMMAND_UNFOLLOW;
        return;
    L180:
        return;
    L97:
        SVG.Style.FontStyle r710 = parseFontStyle(r8);
        r6.fontStyle = r710;
        if (r710 == null) goto L181;
        r6.specifiedFlags |= MediaStatus.COMMAND_FOLLOW;
        return;
    L181:
        return;
    L101:
        Integer r711 = parseFontWeight(r8);
        r6.fontWeight = r711;
        if (r711 == null) goto L182;
        r6.specifiedFlags |= MediaStatus.COMMAND_DISLIKE;
        return;
    L182:
        return;
    L105:
        SVG.Length r712 = parseFontSize(r8);
        r6.fontSize = r712;
        if (r712 == null) goto L183;
        r6.specifiedFlags |= 16384;
        return;
    L183:
        return;
    L109:
        List<String> r713 = parseFontFamily(r8);
        r6.fontFamily = r713;
        if (r713 == null) goto L184;
        r6.specifiedFlags |= MediaStatus.COMMAND_PLAYBACK_RATE;
        return;
    L184:
        return;
    L113:
        parseFont(r6, r8);
        return;
    L115:
        r6.color = parseColour(r8);     // Catch: SVGParseException -> L161
        r6.specifiedFlags |= MediaStatus.COMMAND_EDIT_TRACKS;
        return;
    L117:
        r6.opacity = parseOpacity(r8);
        r6.specifiedFlags |= MediaStatus.COMMAND_QUEUE_REPEAT_ONE;
        return;
    L119:
        r6.strokeDashOffset = parseLength(r8);     // Catch: SVGParseException -> L161
        r6.specifiedFlags |= 1024;
        return;
    L129:
        r6.strokeMiterLimit = Float.valueOf(parseFloat(r8));     // Catch: SVGParseException -> L161
        r6.specifiedFlags |= 256;
        return;
    L131:
        SVG.Style.LineJoin r714 = parseStrokeLineJoin(r8);
        r6.strokeLineJoin = r714;
        if (r714 == null) goto L186;
        r6.specifiedFlags |= 128;
        return;
    L186:
        return;
    L135:
        SVG.Style.LineCap r715 = parseStrokeLineCap(r8);
        r6.strokeLineCap = r715;
        if (r715 == null) goto L187;
        r6.specifiedFlags |= 64;
        return;
    L187:
        return;
    L139:
        r6.strokeWidth = parseLength(r8);     // Catch: SVGParseException -> L161
        r6.specifiedFlags |= 32;
        return;
    L141:
        Float r716 = parseOpacity(r8);
        r6.strokeOpacity = r716;
        if (r716 == null) goto L188;
        r6.specifiedFlags |= 16;
        return;
    L188:
        return;
    L145:
        SVG.SvgPaint r717 = parsePaintSpecifier(r8);
        r6.stroke = r717;
        if (r717 == null) goto L189;
        r6.specifiedFlags |= 8;
        return;
    L189:
        return;
    L149:
        Float r718 = parseOpacity(r8);
        r6.fillOpacity = r718;
        if (r718 == null) goto L190;
        r6.specifiedFlags |= 4;
        return;
    L190:
        return;
    L153:
        SVG.Style.FillRule r719 = parseFillRule(r8);
        r6.fillRule = r719;
        if (r719 == null) goto L191;
        r6.specifiedFlags |= 2;
        return;
    L191:
        return;
    L157:
        SVG.SvgPaint r720 = parsePaintSpecifier(r8);
        r6.fill = r720;
        if (r720 == null) goto L192;
        r6.specifiedFlags |= 1;
        return;
    L192:
        return;
    L195:
        return;
    L22:
        if (r8.equals("currentColor") == false) goto L167;
        r6.viewportFill = SVG.CurrentColor.getInstance();
    L25:
        r6.specifiedFlags |= 8589934592L;
        return;
    L167:
        r6.viewportFill = parseColour(r8);     // Catch: SVGParseException -> L27
    L27:
        e = move-exception;
        Log.w("SVGParser", e.getMessage());
        return;
    L32:
        if (r8.equals("currentColor") == false) goto L163;
        r6.solidColor = SVG.CurrentColor.getInstance();
    L35:
        r6.specifiedFlags |= 2147483648L;
        return;
    L163:
        r6.solidColor = parseColour(r8);     // Catch: SVGParseException -> L37
    L37:
        e = move-exception;
        Log.w("SVGParser", e.getMessage());
        return;
    L52:
        if (r8.equals("currentColor") == false) goto L165;
        r6.stopColor = SVG.CurrentColor.getInstance();
    L55:
        r6.specifiedFlags |= 67108864;
        return;
    L165:
        r6.stopColor = parseColour(r8);     // Catch: SVGParseException -> L57
    L57:
        e = move-exception;
        Log.w("SVGParser", e.getMessage());
        return;
    L60:
        if (r8.indexOf(124) < 0) goto L62;
        return;
    L62:
        if ("|visible|hidden|collapse|".contains("|" + r8 + '|') == false) goto L196;
        r6.visibility = Boolean.valueOf(r8.equals("visible"));
        r6.specifiedFlags |= 33554432;
        return;
    L196:
        return;
    L67:
        if (r8.indexOf(124) < 0) goto L69;
        return;
    L69:
        if ("|inline|block|list-item|run-in|compact|marker|table|inline-table|table-row-group|table-header-group|table-footer-group|table-row|table-column-group|table-column|table-cell|table-caption|none|".contains("|" + r8 + '|') == false) goto L197;
        r6.display = Boolean.valueOf(!r8.equals("none"));
        r6.specifiedFlags |= 16777216;
        return;
    L197:
        return;
    L122:
        if ("none".equals(r8) == false) goto L125;
        r6.strokeDashArray = null;
        r6.specifiedFlags |= 512;
        return;
    L125:
        SVG.Length[] r721 = parseStrokeDashArray(r8);
        r6.strokeDashArray = r721;
        if (r721 == null) goto L185;
        r6.specifiedFlags |= 512;
        return;
    L185:
        return;
    }

    private void radialGradient(Attributes r3) {
        debug("<radialGradient>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.SvgRadialGradient r0 = new SVG.SvgRadialGradient();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesGradient(r0, r3);
        parseAttributesRadialGradient(r0, r3);
        this.currentElement.addChild(r0);
        this.currentElement = r0;
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private void rect(Attributes r3) {
        debug("<rect>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.Rect r0 = new SVG.Rect();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesTransform(r0, r3);
        parseAttributesConditional(r0, r3);
        parseAttributesRect(r0, r3);
        this.currentElement.addChild(r0);
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private void solidColor(Attributes r3) {
        debug("<solidColor>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.SolidColor r0 = new SVG.SolidColor();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        this.currentElement.addChild(r0);
        this.currentElement = r0;
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private void startDocument() {
        this.svgDocument = new SVG();
    }

    private void startElement(String r3, String r4, String r5, Attributes r6) {
        if (this.ignoring == false) goto L7;
        this.ignoreDepth++;
        return;
    L7:
        if ("http://www.w3.org/2000/svg".equals(r3) == true) goto L12;
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r3) == true) goto L12;
        return;
    L12:
        if (r4.length() > 0) goto L15;
        r4 = r5;
    L15:
        SVGElem r32 = SVGElem.fromString(r4);
        switch(AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGElem[r32.ordinal()]) {
            case 1: goto L75;
            case 2: goto L73;
            case 3: goto L73;
            case 4: goto L71;
            case 5: goto L69;
            case 6: goto L67;
            case 7: goto L65;
            case 8: goto L63;
            case 9: goto L61;
            case 10: goto L59;
            case 11: goto L57;
            case 12: goto L55;
            case 13: goto L53;
            case 14: goto L51;
            case 15: goto L49;
            case 16: goto L47;
            case 17: goto L45;
            case 18: goto L43;
            case 19: goto L41;
            case 20: goto L39;
            case 21: goto L37;
            case 22: goto L35;
            case 23: goto L35;
            case 24: goto L33;
            case 25: goto L31;
            case 26: goto L29;
            case 27: goto L27;
            case 28: goto L25;
            case 29: goto L23;
            case 30: goto L21;
            case 31: goto L19;
            default: goto L17;
        };
    L17:
        this.ignoring = true;
        this.ignoreDepth = 1;
        return;
    L19:
        solidColor(r6);
        return;
    L21:
        style(r6);
        return;
    L23:
        mask(r6);
        return;
    L25:
        view(r6);
        return;
    L27:
        image(r6);
        return;
    L29:
        pattern(r6);
        return;
    L31:
        textPath(r6);
        return;
    L33:
        clipPath(r6);
        return;
    L35:
        this.inMetadataElement = true;
        this.metadataTag = r32;
        return;
    L37:
        stop(r6);
        return;
    L39:
        radialGradient(r6);
        return;
    L41:
        linearGradient(r6);
        return;
    L43:
        marker(r6);
        return;
    L45:
        symbol(r6);
        return;
    L47:
        zwitch(r6);
        return;
    L49:
        tref(r6);
        return;
    L51:
        tspan(r6);
        return;
    L53:
        text(r6);
        return;
    L55:
        polygon(r6);
        return;
    L57:
        polyline(r6);
        return;
    L59:
        line(r6);
        return;
    L61:
        ellipse(r6);
        return;
    L63:
        circle(r6);
        return;
    L65:
        rect(r6);
        return;
    L67:
        path(r6);
        return;
    L69:
        use(r6);
        return;
    L71:
        defs(r6);
        return;
    L73:
        g(r6);
        return;
    L75:
        svg(r6);
    }

    private void stop(Attributes r3) {
        debug("<stop>", new Object[0]);
        SVG.SvgContainer r0 = this.currentElement;
        if (r0 != null) goto L5;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
        return;
    L5:
        if ((r0 instanceof SVG.GradientElement) == false) goto L8;
        SVG.Stop r02 = new SVG.Stop();
        r02.document = this.svgDocument;
        r02.parent = this.currentElement;
        parseAttributesCore(r02, r3);
        parseAttributesStyle(r02, r3);
        parseAttributesStop(r02, r3);
        this.currentElement.addChild(r02);
        this.currentElement = r02;
        return;
    L8:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. <stop> elements are only valid inside <linearGradient> or <radialGradient> elements.");
    }

    private void style(Attributes r8) {
        int r0 = 0;
        debug("<style>", new Object[0]);
        if (this.currentElement == null) goto L22;
        String r2 = "all";
        boolean r3 = true;
    L6:
        if (r0 >= r8.getLength()) goto L15;
        String r4 = r8.getValue(r0).trim();
        int r5 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVGParser$SVGAttr[SVGAttr.fromString(r8.getLocalName(r0)).ordinal()];
        if (r5 != 88) goto L10;
        r3 = r4.equals("text/css");
    L14:
        r0 = r0 + 1;
        goto L6
    L10:
        if (r5 != 89) goto L14;
        r2 = r4;
        goto L14
    L15:
        if (r3 == true) goto L17;
    L20:
        this.ignoring = true;
        this.ignoreDepth = 1;
        return;
    L17:
        if (CSSParser.mediaMatches(r2, CSSParser.MediaType.screen) == false) goto L20;
        this.inStyleElement = true;
        return;
    L22:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private void svg(Attributes r3) {
        debug("<svg>", new Object[0]);
        SVG.Svg r0 = new SVG.Svg();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesConditional(r0, r3);
        parseAttributesViewBox(r0, r3);
        parseAttributesSVG(r0, r3);
        SVG.SvgContainer r32 = this.currentElement;
        if (r32 != null) goto L5;
        this.svgDocument.setRootElement(r0);
    L6:
        this.currentElement = r0;
        return;
    L5:
        r32.addChild(r0);
        goto L6
    }

    private void symbol(Attributes r3) {
        debug("<symbol>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.SvgViewBoxContainer r0 = new SVG.Symbol();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesConditional(r0, r3);
        parseAttributesViewBox(r0, r3);
        this.currentElement.addChild(r0);
        this.currentElement = r0;
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private void text(String r3) {
        if (this.ignoring == false) goto L6;
        return;
    L6:
        if (this.inMetadataElement == false) goto L13;
        if (this.metadataElementContents != null) goto L10;
        this.metadataElementContents = new StringBuilder(r3.length());
    L10:
        this.metadataElementContents.append(r3);
        return;
    L13:
        if (this.inStyleElement == false) goto L20;
        if (this.styleElementContents != null) goto L17;
        this.styleElementContents = new StringBuilder(r3.length());
    L17:
        this.styleElementContents.append(r3);
        return;
    L20:
        if ((this.currentElement instanceof SVG.TextContainer) == false) goto L23;
        appendToTextContainer(r3);
        return;
    }

    private void text(Attributes r3) {
        debug("<text>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.Text r0 = new SVG.Text();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesTransform(r0, r3);
        parseAttributesConditional(r0, r3);
        parseAttributesTextPosition(r0, r3);
        this.currentElement.addChild(r0);
        this.currentElement = r0;
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private void text(char[] r2, int r3, int r4) {
        if (this.ignoring == false) goto L6;
        return;
    L6:
        if (this.inMetadataElement == false) goto L13;
        if (this.metadataElementContents != null) goto L10;
        this.metadataElementContents = new StringBuilder(r4);
    L10:
        this.metadataElementContents.append(r2, r3, r4);
        return;
    L13:
        if (this.inStyleElement == false) goto L20;
        if (this.styleElementContents != null) goto L17;
        this.styleElementContents = new StringBuilder(r4);
    L17:
        this.styleElementContents.append(r2, r3, r4);
        return;
    L20:
        if ((this.currentElement instanceof SVG.TextContainer) == false) goto L23;
        appendToTextContainer(new String(r2, r3, r4));
        return;
    }

    private void textPath(Attributes r3) {
        debug("<textPath>", new Object[0]);
        if (this.currentElement == null) goto L10;
        SVG.TextPath r0 = new SVG.TextPath();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesConditional(r0, r3);
        parseAttributesTextPath(r0, r3);
        this.currentElement.addChild(r0);
        this.currentElement = r0;
        SVG.SvgContainer r2 = r0.parent;
        if ((r2 instanceof SVG.TextRoot) == false) goto L8;
        r0.setTextRoot((SVG.TextRoot) r2);
        return;
    L8:
        r0.setTextRoot(((SVG.TextChild) r2).getTextRoot());
        return;
    L10:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private void tref(Attributes r3) {
        debug("<tref>", new Object[0]);
        SVG.SvgContainer r0 = this.currentElement;
        if (r0 != null) goto L5;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
        return;
    L5:
        if ((r0 instanceof SVG.TextContainer) == false) goto L12;
        SVG.TRef r02 = new SVG.TRef();
        r02.document = this.svgDocument;
        r02.parent = this.currentElement;
        parseAttributesCore(r02, r3);
        parseAttributesStyle(r02, r3);
        parseAttributesConditional(r02, r3);
        parseAttributesTRef(r02, r3);
        this.currentElement.addChild(r02);
        SVG.SvgContainer r2 = r02.parent;
        if ((r2 instanceof SVG.TextRoot) == false) goto L10;
        r02.setTextRoot((SVG.TextRoot) r2);
        return;
    L10:
        r02.setTextRoot(((SVG.TextChild) r2).getTextRoot());
        return;
    L12:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. <tref> elements are only valid inside <text> or <tspan> elements.");
    }

    private void tspan(Attributes r3) {
        debug("<tspan>", new Object[0]);
        SVG.SvgContainer r0 = this.currentElement;
        if (r0 != null) goto L5;
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
        return;
    L5:
        if ((r0 instanceof SVG.TextContainer) == false) goto L12;
        SVG.TSpan r02 = new SVG.TSpan();
        r02.document = this.svgDocument;
        r02.parent = this.currentElement;
        parseAttributesCore(r02, r3);
        parseAttributesStyle(r02, r3);
        parseAttributesConditional(r02, r3);
        parseAttributesTextPosition(r02, r3);
        this.currentElement.addChild(r02);
        this.currentElement = r02;
        SVG.SvgContainer r2 = r02.parent;
        if ((r2 instanceof SVG.TextRoot) == false) goto L10;
        r02.setTextRoot((SVG.TextRoot) r2);
        return;
    L10:
        r02.setTextRoot(((SVG.TextChild) r2).getTextRoot());
        return;
    L12:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. <tspan> elements are only valid inside <text> or other <tspan> elements.");
    }

    private void use(Attributes r3) {
        debug("<use>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.Use r0 = new SVG.Use();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesTransform(r0, r3);
        parseAttributesConditional(r0, r3);
        parseAttributesUse(r0, r3);
        this.currentElement.addChild(r0);
        this.currentElement = r0;
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private void view(Attributes r3) {
        debug("<view>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.SvgViewBoxContainer r0 = new SVG.View();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesConditional(r0, r3);
        parseAttributesViewBox(r0, r3);
        this.currentElement.addChild(r0);
        this.currentElement = r0;
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    private void zwitch(Attributes r3) {
        debug("<switch>", new Object[0]);
        if (this.currentElement == null) goto L6;
        SVG.Switch r0 = new SVG.Switch();
        r0.document = this.svgDocument;
        r0.parent = this.currentElement;
        parseAttributesCore(r0, r3);
        parseAttributesStyle(r0, r3);
        parseAttributesTransform(r0, r3);
        parseAttributesConditional(r0, r3);
        this.currentElement.addChild(r0);
        this.currentElement = r0;
        return;
    L6:
        SVGParser$$ExternalSyntheticBUOutline1.m("Invalid document. Root element must be <svg>");
    }

    public SVG parse(InputStream r5, boolean r6) {
        if (r5.markSupported() == true) goto L27;
        r5 = new BufferedInputStream(r5);
    L27:
        r5.mark(3);     // Catch: IOException -> L22
        int r2 = r5.read() + (r5.read() << 8);     // Catch: IOException -> L22
        r5.reset();     // Catch: IOException -> L22
        if (r2 != 35615) goto L25;
        r5 = new BufferedInputStream(new GZIPInputStream(r5));     // Catch: IOException -> L22
    L25:
        r5.mark(4096);     // Catch: Throwable -> L17
        parseUsingXmlPullParser(r5, r6);     // Catch: Throwable -> L17
        r5.close();     // Catch: IOException -> L14
    L16:
        return this.svgDocument;
    L14:
        Log.e("SVGParser", "Exception thrown closing input stream");
    L17:
        th = move-exception;
        r5.close();     // Catch: IOException -> L20
    L21:
        throw th;
    L20:
        Log.e("SVGParser", "Exception thrown closing input stream");
        goto L21
    }
}
