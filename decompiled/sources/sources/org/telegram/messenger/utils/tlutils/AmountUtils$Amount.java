package org.telegram.messenger.utils.tlutils;

import com.exteragram.messenger.math.MathFormat$$ExternalSyntheticBackportWithForwarding0;
import java.math.BigDecimal;
import java.math.MathContext;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes6.dex */
public class AmountUtils$Amount {
    public final AmountUtils$Currency currency;
    private final long nanos;

    private AmountUtils$Amount(AmountUtils$Currency r1, long r2) {
        this.currency = r1;
        this.nanos = r2;
    }

    public static boolean equals(AmountUtils$Amount r4, AmountUtils$Amount r5) {
        if (r4 != r5) goto L6;
        return true;
    L6:
        if (r4 == null) goto L14;
        if (r5 == null) goto L14;
        if (r4.currency != r5.currency) goto L14;
        if (r4.nanos != r5.nanos) goto L14;
        return true;
    L14:
        return false;
    }

    public static boolean equals(TL_stars.StarsAmount r0, TL_stars.StarsAmount r1) {
        return equals(of(r0), of(r1));
    }

    public static AmountUtils$Amount fromDecimal(double r3, AmountUtils$Currency r5) {
        if (r5 != null) goto L6;
        return null;
    L6:
        return new AmountUtils$Amount(r5, (long) (r3 * getDecimals(r5)));
    }

    public static AmountUtils$Amount fromDecimal(long r3, AmountUtils$Currency r5) {
        if (r5 != null) goto L6;
        return null;
    L6:
        return new AmountUtils$Amount(r5, r3 * getDecimals(r5));
    }

    public static AmountUtils$Amount fromDecimal(String r4, AmountUtils$Currency r5) {
        BigDecimal r42 = new BigDecimal(r4).multiply(BigDecimal.valueOf(getDecimals(r5)));     // Catch: NumberFormatException -> L8
        if (r42.compareTo(BigDecimal.valueOf(LongCompanionObject.MAX_VALUE)) <= 0) goto L6;
        return null;
    L6:
        return fromNano(r42.longValue(), r5);
    L8:
        return null;
    }

    public static AmountUtils$Amount fromNano(long r1, AmountUtils$Currency r3) {
        if (r3 != null) goto L6;
        return null;
    L6:
        return new AmountUtils$Amount(r3, r1);
    }

    public static AmountUtils$Amount fromUsd(double r3, AmountUtils$Currency r5) {
        AmountUtils$Currency r0 = AmountUtils$Currency.TON;
        if (r5 == r0) goto L5;
        AmountUtils$Currency r02 = AmountUtils$Currency.STARS;
        if (r5 != r02) goto L11;
        return fromDecimal((r3 * 100000.0d) / MessagesController.getInstance(UserConfig.selectedAccount).starsUsdSellRate1000, r02).round(0);
    L11:
        return fromDecimal(0, r5);
    L5:
        return fromDecimal(r3 / MessagesController.getInstance(UserConfig.selectedAccount).config.tonUsdRate.get(), r0).round(2);
    }

    private static long getDecimals(AmountUtils$Currency r2) {
        return 1000000000;
    }

    private static int getTenPow(AmountUtils$Currency r0) {
        return 9;
    }

    public static AmountUtils$Amount of(TL_stars.StarsAmount r5) {
        if ((r5 instanceof TL_stars.TL_starsAmount) == false) goto L7;
        long r0 = r5.amount;
        AmountUtils$Currency r2 = AmountUtils$Currency.STARS;
        return fromNano((r0 * getDecimals(r2)) + r5.nanos, r2);
    L7:
        if ((r5 instanceof TL_stars.TL_starsTonAmount) == true) goto L9;
        return null;
    L9:
        return fromNano(r5.amount, AmountUtils$Currency.TON);
    }

    public static AmountUtils$Amount ofSafe(TL_stars.StarsAmount r2) {
        AmountUtils$Amount r22 = of(r2);
        if (r22 == null) goto L6;
        return r22;
    L6:
        return fromNano(0, AmountUtils$Currency.STARS);
    }

    public AmountUtils$Amount applyPerMille(int r5) {
        return fromNano((this.nanos * r5) / 1000, this.currency);
    }

    public long asDecimal() {
        return this.nanos / getDecimals(this.currency);
    }

    public String asDecimalString() {
        return MathFormat$$ExternalSyntheticBackportWithForwarding0.m(new BigDecimal(asNano()).divide(BigDecimal.valueOf(getDecimals(this.currency)), MathContext.UNLIMITED)).toPlainString();
    }

    public double asDouble() {
        return this.nanos / getDecimals(this.currency);
    }

    public String asFormatString() {
        return asFormatString(',');
    }

    public String asFormatString(char r6) {
        StringBuilder r0 = new StringBuilder(LocaleController.formatNumber(asDecimal(), r6));
        long r1 = this.nanos % getDecimals(this.currency);
        if (r1 == 0) goto L5;
        r0.append('.');
        String r62 = Long.toString(r1);
        int r5 = getTenPow(this.currency) - r62.length();
        int r2 = 0;
    L8:
        if (r2 >= r5) goto L10;
        r0.append('0');
        r2 = r2 + 1;
        goto L8
    L10:
        int r52 = r62.length();
    L11:
        if (r52 <= 0) goto L15;
        if (r62.charAt(r52 - 1) != '0') goto L15;
        r52 = r52 - 1;
    L15:
        r0.append(r62, 0, r52);
        return r0.toString();
    L5:
        return r0.toString();
    }

    public long asNano() {
        return this.nanos;
    }

    public AmountUtils$Amount convertTo(AmountUtils$Currency r3) {
        if (this.currency != r3) goto L6;
        return this;
    L6:
        return fromUsd(convertToUsd(), r3);
    }

    public double convertToUsd() {
        AmountUtils$Currency r0 = this.currency;
        if (r0 != AmountUtils$Currency.STARS) goto L7;
        return ((asDouble() / 1000.0d) * MessagesController.getInstance(UserConfig.selectedAccount).starsUsdSellRate1000) / 100.0d;
    L7:
        if (r0 == AmountUtils$Currency.TON) goto L9;
        return 0.0d;
    L9:
        return asDouble() * MessagesController.getInstance(UserConfig.selectedAccount).config.tonUsdRate.get();
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof AmountUtils$Amount) == true) goto L8;
        return false;
    L8:
        return equals(this, (AmountUtils$Amount) r2);
    }

    public String formatAsDecimalSpaced() {
        if (isRound() == false) goto L12;
        int r0 = AmountUtils$1.$SwitchMap$org$telegram$messenger$utils$tlutils$AmountUtils$Currency[this.currency.ordinal()];
        if (r0 == 1) goto L11;
        if (r0 == 2) goto L9;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        return LocaleController.formatPluralStringSpaced("TonCount", (int) asDecimal());
    L11:
        return LocaleController.formatPluralStringSpaced("StarsCount", (int) asDecimal());
    L12:
        int r02 = AmountUtils$1.$SwitchMap$org$telegram$messenger$utils$tlutils$AmountUtils$Currency[this.currency.ordinal()];
        if (r02 == 1) goto L20;
        if (r02 == 2) goto L18;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L18:
        return LocaleController.formatString(R.string.TonCountX, new Object[]{asDecimalString()});
    L20:
        return LocaleController.formatString(R.string.StarsCountX, new Object[]{asDecimalString()});
    }

    public boolean isRound() {
        if ((this.nanos % getDecimals(this.currency)) != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isZero() {
        if (this.nanos != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public AmountUtils$Amount round(int r9) {
        long r0 = asNano();
        long r2 = getTenPow(this.currency) - r9;
        if (r2 > 0) goto L5;
        return this;
    L5:
        long r4 = 1;
        int r92 = 0;
    L7:
        if (r92 >= r2) goto L10;
        r4 = r4 * 10;
        r92 = r92 + 1;
        goto L7
    L10:
        return fromNano((r0 / r4) * r4, this.currency);
    }

    public TL_stars.StarsAmount toTl() {
        AmountUtils$Currency r0 = this.currency;
        if (r0 != AmountUtils$Currency.STARS) goto L7;
        TL_stars.TL_starsAmount r02 = new TL_stars.TL_starsAmount();
        long r1 = getDecimals(this.currency);
        long r3 = this.nanos;
        r02.amount = r3 / r1;
        r02.nanos = (int) (r3 % r1);
        return r02;
    L7:
        if (r0 != AmountUtils$Currency.TON) goto L10;
        TL_stars.TL_starsTonAmount r03 = new TL_stars.TL_starsTonAmount();
        r03.amount = this.nanos;
        return r03;
    L10:
        return null;
    }
}
