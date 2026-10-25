package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
class PredefMacroInfo extends MacroInfo {
    private int id;

    public PredefMacroInfo(int r1, int r2) {
        super(r2);
        this.id = r1;
    }

    public PredefMacroInfo(int r1, int r2, int r3) {
        super(r2, r3);
        this.id = r1;
    }

    private static final Object invokeID(int r4, TeXParser r5, String[] r6) {
        switch(r4) {
            case 0: goto L464;
            case 1: goto L462;
            case 2: goto L460;
            case 3: goto L458;
            case 4: goto L458;
            case 5: goto L456;
            case 6: goto L456;
            case 7: goto L456;
            case 8: goto L454;
            case 9: goto L454;
            case 10: goto L454;
            case 11: goto L452;
            case 12: goto L450;
            case 13: goto L448;
            case 14: goto L446;
            case 15: goto L444;
            case 16: goto L442;
            case 17: goto L440;
            case 18: goto L438;
            case 19: goto L436;
            case 20: goto L434;
            case 21: goto L432;
            case 22: goto L430;
            case 23: goto L428;
            case 24: goto L426;
            case 25: goto L424;
            case 26: goto L422;
            case 27: goto L420;
            case 28: goto L418;
            case 29: goto L416;
            case 30: goto L414;
            case 31: goto L412;
            case 32: goto L410;
            case 33: goto L408;
            case 34: goto L406;
            case 35: goto L404;
            case 36: goto L402;
            case 37: goto L400;
            case 38: goto L398;
            case 39: goto L396;
            case 40: goto L394;
            case 41: goto L394;
            case 42: goto L394;
            case 43: goto L394;
            case 44: goto L394;
            case 45: goto L394;
            case 46: goto L392;
            case 47: goto L392;
            case 48: goto L392;
            case 49: goto L392;
            case 50: goto L392;
            case 51: goto L392;
            case 52: goto L392;
            case 53: goto L392;
            case 54: goto L392;
            case 55: goto L392;
            case 56: goto L392;
            case 57: goto L392;
            case 58: goto L390;
            case 59: goto L388;
            case 60: goto L386;
            case 61: goto L384;
            case 62: goto L382;
            case 63: goto L382;
            case 64: goto L382;
            case 65: goto L382;
            case 66: goto L382;
            case 67: goto L382;
            case 68: goto L382;
            case 69: goto L382;
            case 70: goto L382;
            case 71: goto L382;
            case 72: goto L382;
            case 73: goto L382;
            case 74: goto L382;
            case 75: goto L382;
            case 76: goto L380;
            case 77: goto L378;
            case 78: goto L376;
            case 79: goto L374;
            case 80: goto L372;
            case 81: goto L370;
            case 82: goto L368;
            case 83: goto L366;
            case 84: goto L364;
            case 85: goto L362;
            case 86: goto L360;
            case 87: goto L358;
            case 88: goto L356;
            case 89: goto L354;
            case 90: goto L352;
            case 91: goto L350;
            case 92: goto L348;
            case 93: goto L346;
            case 94: goto L346;
            case 95: goto L344;
            case 96: goto L342;
            case 97: goto L340;
            case 98: goto L338;
            case 99: goto L336;
            case 100: goto L334;
            case 101: goto L332;
            case 102: goto L330;
            case 103: goto L328;
            case 104: goto L326;
            case 105: goto L324;
            case 106: goto L322;
            case 107: goto L320;
            case 108: goto L318;
            case 109: goto L316;
            case 110: goto L314;
            case 111: goto L312;
            case 112: goto L310;
            case 113: goto L308;
            case 114: goto L306;
            case 115: goto L304;
            case 116: goto L302;
            case 117: goto L300;
            case 118: goto L298;
            case 119: goto L296;
            case 120: goto L294;
            case 121: goto L292;
            case 122: goto L290;
            case 123: goto L288;
            case 124: goto L286;
            case 125: goto L284;
            case 126: goto L282;
            case 127: goto L280;
            case 128: goto L278;
            case 129: goto L276;
            case 130: goto L274;
            case 131: goto L272;
            case 132: goto L270;
            case 133: goto L268;
            case 134: goto L266;
            case 135: goto L264;
            case 136: goto L262;
            case 137: goto L262;
            case 138: goto L260;
            case 139: goto L258;
            case 140: goto L256;
            case 141: goto L254;
            case 142: goto L252;
            case 143: goto L250;
            case 144: goto L248;
            case 145: goto L246;
            case 146: goto L244;
            case 147: goto L242;
            case 148: goto L240;
            case 149: goto L238;
            case 150: goto L236;
            case 151: goto L234;
            case 152: goto L232;
            case 153: goto L230;
            case 154: goto L228;
            case 155: goto L226;
            case 156: goto L224;
            case 157: goto L222;
            case 158: goto L220;
            case 159: goto L218;
            case 160: goto L216;
            case 161: goto L214;
            case 162: goto L212;
            case 163: goto L210;
            case 164: goto L208;
            case 165: goto L206;
            case 166: goto L204;
            case 167: goto L202;
            case 168: goto L200;
            case 169: goto L198;
            case 170: goto L196;
            case 171: goto L194;
            case 172: goto L192;
            case 173: goto L190;
            case 174: goto L188;
            case 175: goto L186;
            case 176: goto L184;
            case 177: goto L182;
            case 178: goto L180;
            case 179: goto L178;
            case 180: goto L176;
            case 181: goto L174;
            case 182: goto L172;
            case 183: goto L170;
            case 184: goto L168;
            case 185: goto L166;
            case 186: goto L164;
            case 187: goto L162;
            case 188: goto L160;
            case 189: goto L158;
            case 190: goto L156;
            case 191: goto L154;
            case 192: goto L152;
            case 193: goto L150;
            case 194: goto L148;
            case 195: goto L146;
            case 196: goto L144;
            case 197: goto L142;
            case 198: goto L140;
            case 199: goto L138;
            case 200: goto L136;
            case 201: goto L134;
            case 202: goto L132;
            case 203: goto L130;
            case 204: goto L128;
            case 205: goto L126;
            case 206: goto L124;
            case 207: goto L122;
            case 208: goto L122;
            case 209: goto L122;
            case 210: goto L122;
            case 211: goto L122;
            case 212: goto L122;
            case 213: goto L122;
            case 214: goto L122;
            case 215: goto L122;
            case 216: goto L122;
            case 217: goto L120;
            case 218: goto L118;
            case 219: goto L116;
            case 220: goto L114;
            case 221: goto L112;
            case 222: goto L110;
            case 223: goto L108;
            case 224: goto L106;
            case 225: goto L104;
            case 226: goto L102;
            case 227: goto L100;
            case 228: goto L98;
            case 229: goto L96;
            case 230: goto L94;
            case 231: goto L92;
            case 232: goto L90;
            case 233: goto L88;
            case 234: goto L86;
            case 235: goto L84;
            case 236: goto L82;
            case 237: goto L80;
            case 238: goto L78;
            case 239: goto L76;
            case 240: goto L74;
            case 241: goto L72;
            case 242: goto L70;
            case 243: goto L68;
            case 244: goto L66;
            case 245: goto L64;
            case 246: goto L62;
            case 247: goto L62;
            case 248: goto L60;
            case 249: goto L58;
            case 250: goto L56;
            case 251: goto L54;
            case 252: goto L54;
            case 253: goto L54;
            case 254: goto L54;
            case 255: goto L54;
            case 256: goto L54;
            case 257: goto L54;
            case 258: goto L54;
            case 259: goto L54;
            case 260: goto L54;
            case 261: goto L52;
            case 262: goto L50;
            case 263: goto L48;
            case 264: goto L46;
            case 265: goto L44;
            case 266: goto L42;
            case 267: goto L40;
            case 268: goto L38;
            case 269: goto L36;
            case 270: goto L34;
            case 271: goto L32;
            case 272: goto L30;
            case 273: goto L28;
            case 274: goto L26;
            case 275: goto L24;
            case 276: goto L22;
            case 277: goto L20;
            case 278: goto L18;
            case 279: goto L16;
            case 280: goto L14;
            case 281: goto L12;
            case 282: goto L10;
            case 283: goto L6;
            default: goto L3;
        };
    L3:
        return null;
    L6:
        return PredefMacros.brack_macro(r5, r6);
    L10:
        return PredefMacros.brace_macro(r5, r6);
    L12:
        return PredefMacros.bangle_macro(r5, r6);
    L14:
        return PredefMacros.questeq_macro(r5, r6);
    L16:
        return PredefMacros.longdiv_macro(r5, r6);
    L18:
        return PredefMacros.qquad_macro(r5, r6);
    L20:
        return PredefMacros.textstyle_macros(r5, r6);
    L22:
        return PredefMacros.fcscore_macro(r5, r6);
    L24:
        return PredefMacros.st_macro(r5, r6);
    L26:
        return PredefMacros.abovewithdelims_macro(r5, r6);
    L28:
        return PredefMacros.above_macro(r5, r6);
    L30:
        return PredefMacros.jlmXML_macro(r5, r6);
    L32:
        return PredefMacros.insertBreakMark_macro(r5, r6);
    L34:
        return PredefMacros.rmoustache_macro(r5, r6);
    L36:
        return PredefMacros.lmoustache_macro(r5, r6);
    L38:
        return PredefMacros.oint_macro(r5, r6);
    L40:
        return PredefMacros.int_macro(r5, r6);
    L42:
        return PredefMacros.idotsint_macro(r5, r6);
    L44:
        return PredefMacros.iiiint_macro(r5, r6);
    L46:
        return PredefMacros.iiint_macro(r5, r6);
    L48:
        return PredefMacros.iint_macro(r5, r6);
    L50:
        return PredefMacros.surd_macro(r5, r6);
    L52:
        return PredefMacros.quad_macro(r5, r6);
    L54:
        return PredefMacros.muskip_macros(r5, r6);
    L56:
        return PredefMacros.sc_macro(r5, r6);
    L58:
        return PredefMacros.textsc_macro(r5, r6);
    L60:
        return PredefMacros.textcircled_macro(r5, r6);
    L62:
        return PredefMacros.romannumeral_macro(r5, r6);
    L64:
        return PredefMacros.char_macro(r5, r6);
    L66:
        return PredefMacros.kern_macro(r5, r6);
    L68:
        return PredefMacros.coloncolonapprox_macro(r5, r6);
    L70:
        return PredefMacros.colonapprox_macro(r5, r6);
    L72:
        return PredefMacros.coloncolonsim_macro(r5, r6);
    L74:
        return PredefMacros.colonsim_macro(r5, r6);
    L76:
        return PredefMacros.coloncolonequals_macro(r5, r6);
    L78:
        return PredefMacros.colonequals_macro(r5, r6);
    L80:
        return PredefMacros.coloncolonminus_macro(r5, r6);
    L82:
        return PredefMacros.colonminus_macro(r5, r6);
    L84:
        return PredefMacros.equalscoloncolon_macro(r5, r6);
    L86:
        return PredefMacros.equalscolon_macro(r5, r6);
    L88:
        return PredefMacros.coloncolon_macro(r5, r6);
    L90:
        return PredefMacros.approxcoloncolon_macro(r5, r6);
    L92:
        return PredefMacros.approxcolon_macro(r5, r6);
    L94:
        return PredefMacros.simcoloncolon_macro(r5, r6);
    L96:
        return PredefMacros.simcolon_macro(r5, r6);
    L98:
        return PredefMacros.minuscoloncolon_macro(r5, r6);
    L100:
        return PredefMacros.minuscolon_macro(r5, r6);
    L102:
        return PredefMacros.geoprop_macro(r5, r6);
    L104:
        return PredefMacros.smallfrowneq_macro(r5, r6);
    L106:
        return PredefMacros.ratio_macro(r5, r6);
    L108:
        return PredefMacros.dotminus_macro(r5, r6);
    L110:
        return PredefMacros.Dstrok_macro(r5, r6);
    L112:
        return PredefMacros.dstrok_macro(r5, r6);
    L114:
        return PredefMacros.Hstrok_macro(r5, r6);
    L116:
        return PredefMacros.hstrok_macro(r5, r6);
    L118:
        return PredefMacros.jlatexmathcumsub_macro(r5, r6);
    L120:
        return PredefMacros.jlatexmathcumsup_macro(r5, r6);
    L122:
        return PredefMacros.size_macros(r5, r6);
    L124:
        return PredefMacros.hline_macro(r5, r6);
    L126:
        return PredefMacros.magnification_macro(r5, r6);
    L128:
        return PredefMacros.DeclareMathSizes_macro(r5, r6);
    L130:
        return PredefMacros.jlmTextitbf_macro(r5, r6);
    L132:
        return PredefMacros.jlmTextbf_macro(r5, r6);
    L134:
        return PredefMacros.jlmTextit_macro(r5, r6);
    L136:
        return PredefMacros.jlmText_macro(r5, r6);
    L138:
        return PredefMacros.jlmExternalFont_macro(r5, r6);
    L140:
        return PredefMacros.jlmDynamic_macro(r5, r6);
    L142:
        return PredefMacros.doteq_macro(r5, r6);
    L144:
        return PredefMacros.cong_macro(r5, r6);
    L146:
        return PredefMacros.ogonek_macro(r5, r6);
    L148:
        return PredefMacros.LCaron_macro(r5, r6);
    L150:
        return PredefMacros.tcaron_macro(r5, r6);
    L152:
        return PredefMacros.LCaron_macro(r5, r6);
    L154:
        return PredefMacros.TStroke_macro(r5, r6);
    L156:
        return PredefMacros.TStroke_macro(r5, r6);
    L158:
        return PredefMacros.IJ_macro(r5, r6);
    L160:
        return PredefMacros.IJ_macro(r5, r6);
    L162:
        return PredefMacros.cedilla_macro(r5, r6);
    L164:
        return PredefMacros.fcolorbox_macro(r5, r6);
    L166:
        return PredefMacros.colorbox_macro(r5, r6);
    L168:
        return PredefMacros.bgcolor_macro(r5, r6);
    L170:
        return PredefMacros.fgcolor_macro(r5, r6);
    L172:
        return PredefMacros.textcolor_macro(r5, r6);
    L174:
        return PredefMacros.definecolor_macro(r5, r6);
    L176:
        return PredefMacros.spAThat_macro(r5, r6);
    L178:
        return PredefMacros.spATbreve_macro(r5, r6);
    L180:
        return PredefMacros.vphantom_macro(r5, r6);
    L182:
        return PredefMacros.hphantom_macro(r5, r6);
    L184:
        return PredefMacros.phantom_macro(r5, r6);
    L186:
        return PredefMacros.doublebox_macro(r5, r6);
    L188:
        return PredefMacros.ovalbox_macro(r5, r6);
    L190:
        return PredefMacros.shadowbox_macro(r5, r6);
    L192:
        return PredefMacros.raisebox_macro(r5, r6);
    L194:
        return PredefMacros.resizebox_macro(r5, r6);
    L196:
        return PredefMacros.scalebox_macro(r5, r6);
    L198:
        return PredefMacros.reflectbox_macro(r5, r6);
    L200:
        return PredefMacros.rotatebox_macro(r5, r6);
    L202:
        return PredefMacros.prescript_macro(r5, r6);
    L204:
        return PredefMacros.sideset_macro(r5, r6);
    L206:
        return PredefMacros.scriptscriptstyle_macro(r5, r6);
    L208:
        return PredefMacros.scriptstyle_macro(r5, r6);
    L210:
        return PredefMacros.textstyle_macro(r5, r6);
    L212:
        return PredefMacros.displaystyle_macro(r5, r6);
    L214:
        return PredefMacros.Biggr_macro(r5, r6);
    L216:
        return PredefMacros.biggr_macro(r5, r6);
    L218:
        return PredefMacros.Bigr_macro(r5, r6);
    L220:
        return PredefMacros.bigr_macro(r5, r6);
    L222:
        return PredefMacros.Biggl_macro(r5, r6);
    L224:
        return PredefMacros.biggl_macro(r5, r6);
    L226:
        return PredefMacros.Bigl_macro(r5, r6);
    L228:
        return PredefMacros.bigl_macro(r5, r6);
    L230:
        return PredefMacros.Bigg_macro(r5, r6);
    L232:
        return PredefMacros.bigg_macro(r5, r6);
    L234:
        return PredefMacros.Big_macro(r5, r6);
    L236:
        return PredefMacros.big_macro(r5, r6);
    L238:
        return PredefMacros.GeoGebra_macro(r5, r6);
    L240:
        return PredefMacros.LaTeX_macro(r5, r6);
    L242:
        return PredefMacros.boldsymbol_macro(r5, r6);
    L244:
        return PredefMacros.underset_macro(r5, r6);
    L246:
        return PredefMacros.Set_macro(r5, r6);
    L248:
        return PredefMacros.Braket_macro(r5, r6);
    L250:
        return PredefMacros.overset_macro(r5, r6);
    L252:
        return PredefMacros.undertilde_macro(r5, r6);
    L254:
        return PredefMacros.underaccent_macro(r5, r6);
    L256:
        return PredefMacros.accentset_macro(r5, r6);
    L258:
        return PredefMacros.stackbin_macro(r5, r6);
    L260:
        return PredefMacros.stackrel_macro(r5, r6);
    L262:
        return PredefMacros.fbox_macro(r5, r6);
    L264:
        return PredefMacros.makeatother_macro(r5, r6);
    L266:
        return PredefMacros.makeatletter_macro(r5, r6);
    L268:
        return PredefMacros.renewenvironment_macro(r5, r6);
    L270:
        return PredefMacros.newenvironment_macro(r5, r6);
    L272:
        return PredefMacros.backslashcr_macro(r5, r6);
    L274:
        return PredefMacros.shoveleft_macro(r5, r6);
    L276:
        return PredefMacros.shoveright_macro(r5, r6);
    L278:
        return PredefMacros.gatheredATATenv_macro(r5, r6);
    L280:
        return PredefMacros.gatherATATenv_macro(r5, r6);
    L282:
        return PredefMacros.multlineATATenv_macro(r5, r6);
    L284:
        return PredefMacros.alignedatATATenv_macro(r5, r6);
    L286:
        return PredefMacros.alignatATATenv_macro(r5, r6);
    L288:
        return PredefMacros.flalignATATenv_macro(r5, r6);
    L290:
        return PredefMacros.alignedATATenv_macro(r5, r6);
    L292:
        return PredefMacros.alignATATenv_macro(r5, r6);
    L294:
        return PredefMacros.arrayATATenv_macro(r5, r6);
    L296:
        return PredefMacros.hdotsfor_macro(r5, r6);
    L298:
        return PredefMacros.multicolumn_macro(r5, r6);
    L300:
        return PredefMacros.cr_macro(r5, r6);
    L302:
        return PredefMacros.middle_macro(r5, r6);
    L304:
        return PredefMacros.left_macro(r5, r6);
    L306:
        return PredefMacros.leftbracket_macro(r5, r6);
    L308:
        return PredefMacros.leftparenthesis_macro(r5, r6);
    L310:
        return PredefMacros.normal_macro(r5, r6);
    L312:
        return PredefMacros.limits_macro(r5, r6);
    L314:
        return PredefMacros.nolimits_macro(r5, r6);
    L316:
        return PredefMacros.iddots_macro(r5, r6);
    L318:
        return PredefMacros.ddots_macro(r5, r6);
    L320:
        return PredefMacros.vdots_macro(r5, r6);
    L322:
        return PredefMacros.smash_macro(r5, r6);
    L324:
        return PredefMacros.joinrel_macro(r5, r6);
    L326:
        return PredefMacros.mathclose_macro(r5, r6);
    L328:
        return PredefMacros.mathopen_macro(r5, r6);
    L330:
        return PredefMacros.mathbin_macro(r5, r6);
    L332:
        return PredefMacros.mathinner_macro(r5, r6);
    L334:
        return PredefMacros.mathrel_macro(r5, r6);
    L336:
        return PredefMacros.mathord_macro(r5, r6);
    L338:
        return PredefMacros.mathpunct_macro(r5, r6);
    L340:
        return PredefMacros.mathop_macro(r5, r6);
    L342:
        return PredefMacros.underline_macro(r5, r6);
    L344:
        return PredefMacros.overline_macro(r5, r6);
    L346:
        return PredefMacros.sqrt_macro(r5, r6);
    L348:
        return PredefMacros.overparen_macro(r5, r6);
    L350:
        return PredefMacros.underparen_macro(r5, r6);
    L352:
        return PredefMacros.overbrack_macro(r5, r6);
    L354:
        return PredefMacros.underbrack_macro(r5, r6);
    L356:
        return PredefMacros.overbrace_macro(r5, r6);
    L358:
        return PredefMacros.underbrace_macro(r5, r6);
    L360:
        return PredefMacros.xrightarrow_macro(r5, r6);
    L362:
        return PredefMacros.xleftarrow_macro(r5, r6);
    L364:
        return PredefMacros.underleftrightarrow_macro(r5, r6);
    L366:
        return PredefMacros.underleftarrow_macro(r5, r6);
    L368:
        return PredefMacros.underrightarrow_macro(r5, r6);
    L370:
        return PredefMacros.overleftrightarrow_macro(r5, r6);
    L372:
        return PredefMacros.overleftarrow_macro(r5, r6);
    L374:
        return PredefMacros.overrightarrow_macro(r5, r6);
    L376:
        return PredefMacros.matrixATATenv_macro(r5, r6);
    L378:
        return PredefMacros.smallmatrixATATenv_macro(r5, r6);
    L380:
        return PredefMacros.nbsp_macro(r5, r6);
    L382:
        return PredefMacros.accent_macros(r5, r6);
    L384:
        return PredefMacros.grkaccent_macro(r5, r6);
    L386:
        return PredefMacros.accent_macro(r5, r6);
    L388:
        return PredefMacros.accentbis_macros(r5, r6);
    L390:
        return PredefMacros.T_macro(r5, r6);
    L392:
        return PredefMacros.accentbis_macros(r5, r6);
    L394:
        return PredefMacros.textstyle_macros(r5, r6);
    L396:
        return PredefMacros.tt_macro(r5, r6);
    L398:
        return PredefMacros.mathtt_macro(r5, r6);
    L400:
        return PredefMacros.sf_macro(r5, r6);
    L402:
        return PredefMacros.mathsf_macro(r5, r6);
    L404:
        return PredefMacros.textstyle_macros(r5, r6);
    L406:
        return PredefMacros.rm_macro(r5, r6);
    L408:
        return PredefMacros.mathrm_macro(r5, r6);
    L410:
        return PredefMacros.it_macro(r5, r6);
    L412:
        return PredefMacros.mathit_macro(r5, r6);
    L414:
        return PredefMacros.textstyle_macros(r5, r6);
    L416:
        return PredefMacros.textstyle_macros(r5, r6);
    L418:
        return PredefMacros.textstyle_macros(r5, r6);
    L420:
        return PredefMacros.bf_macro(r5, r6);
    L422:
        return PredefMacros.mathbf_macro(r5, r6);
    L424:
        return PredefMacros.binom_macro(r5, r6);
    L426:
        return PredefMacros.intertext_macro(r5, r6);
    L428:
        return PredefMacros.text_macro(r5, r6);
    L430:
        return PredefMacros.mbox_macro(r5, r6);
    L432:
        return PredefMacros.underscore_macro(r5, r6);
    L434:
        return PredefMacros.choose_macro(r5, r6);
    L436:
        return PredefMacros.atopwithdelims_macro(r5, r6);
    L438:
        return PredefMacros.atop_macro(r5, r6);
    L440:
        return PredefMacros.overwithdelims_macro(r5, r6);
    L442:
        return PredefMacros.over_macro(r5, r6);
    L444:
        return PredefMacros.genfrac_macro(r5, r6);
    L446:
        return PredefMacros.sfrac_macro(r5, r6);
    L448:
        return PredefMacros.frac_macro(r5, r6);
    L450:
        return PredefMacros.cfrac_macro(r5, r6);
    L452:
        return PredefMacros.includegraphics_macro(r5, r6);
    L454:
        return PredefMacros.mathclrlap_macro(r5, r6);
    L456:
        return PredefMacros.clrlap_macro(r5, r6);
    L458:
        return PredefMacros.hvspace_macro(r5, r6);
    L460:
        return PredefMacros.rule_macro(r5, r6);
    L462:
        return PredefMacros.renewcommand_macro(r5, r6);
    L464:
        return PredefMacros.newcommand_macro(r5, r6);
    L7:
        e = move-exception;
        throw new ParseException("Problem with command " + r6[0] + " at position " + r5.getLine() + ":" + r5.getCol() + "\n" + e.getMessage());
    }

    @Override // org.scilab.forge.jlatexmath.MacroInfo
    public Object invoke(TeXParser r1, String[] r2) {
        return invokeID(this.id, r1, r2);
    }
}
