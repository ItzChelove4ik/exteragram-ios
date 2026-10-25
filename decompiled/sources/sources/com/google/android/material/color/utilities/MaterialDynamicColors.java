package com.google.android.material.color.utilities;

/* loaded from: classes5.dex */
public final class MaterialDynamicColors {
    private final boolean isExtendedFidelity;

    /* renamed from: $r8$lambda$-1ajoGTyPZ9TzeW9mzWnvl68kYE, reason: not valid java name */
    public static /* synthetic */ ToneDeltaPair m1828$r8$lambda$1ajoGTyPZ9TzeW9mzWnvl68kYE(MaterialDynamicColors r7, DynamicScheme r8) {
        return new ToneDeltaPair(r7.primaryFixed(), r7.primaryFixedDim(), 10.0d, TonePolarity.LIGHTER, true);
    }

    /* renamed from: $r8$lambda$-XVlkyCVdKj1jLX-ENc39tr4sHI, reason: not valid java name */
    public static /* synthetic */ Double m1829$r8$lambda$XVlkyCVdKj1jLXENc39tr4sHI(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 20.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 100.0d;
        goto L7
    }

    /* renamed from: $r8$lambda$-gx3RKvwt6RZxgcTgoyrTOi-h2w, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1830$r8$lambda$gx3RKvwt6RZxgcTgoyrTOih2w(DynamicScheme r0) {
        return r0.primaryPalette;
    }

    /* renamed from: $r8$lambda$-hfSniu70ma68rv89WagCAc9idY, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1831$r8$lambda$hfSniu70ma68rv89WagCAc9idY(DynamicScheme r0) {
        return r0.neutralPalette;
    }

    /* renamed from: $r8$lambda$-oS6hJqUe85TRNmiNDeOe7DTeVk, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1832$r8$lambda$oS6hJqUe85TRNmiNDeOe7DTeVk(DynamicScheme r0) {
        return r0.neutralVariantPalette;
    }

    /* renamed from: $r8$lambda$-q1dCMb2SlEyzZwqMrtKptEsoAg, reason: not valid java name */
    public static /* synthetic */ ToneDeltaPair m1833$r8$lambda$q1dCMb2SlEyzZwqMrtKptEsoAg(MaterialDynamicColors r7, DynamicScheme r8) {
        return new ToneDeltaPair(r7.primaryFixed(), r7.primaryFixedDim(), 10.0d, TonePolarity.LIGHTER, true);
    }

    /* renamed from: $r8$lambda$-shu7z98MF_ZaJbeX2NJvWovmTA, reason: not valid java name */
    public static /* synthetic */ Double m1834$r8$lambda$shu7z98MF_ZaJbeX2NJvWovmTA(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 30.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 80.0d;
        goto L7
    }

    public static /* synthetic */ DynamicColor $r8$lambda$0EAqSR4IENSvD3DdcI1uCVBekTw(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.tertiary();
    }

    public static /* synthetic */ TonalPalette $r8$lambda$1RpurNoFmNru9c7WRW8mseiuyb4(DynamicScheme r0) {
        return r0.secondaryPalette;
    }

    public static /* synthetic */ Double $r8$lambda$1bchGPUnKbUncNl3I6Xbs8s6dzg(DynamicScheme r2) {
        if (isMonochrome(r2) == false) goto L5;
        double r0 = 100.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 10.0d;
        goto L7
    }

    public static /* synthetic */ TonalPalette $r8$lambda$2QjxNeaRpokjRVA9OWFC_3xJaiE(DynamicScheme r0) {
        return r0.secondaryPalette;
    }

    /* renamed from: $r8$lambda$32MvzKssB1DUr6_nCxdc0kzj-0Y, reason: not valid java name */
    public static /* synthetic */ Double m1835$r8$lambda$32MvzKssB1DUr6_nCxdc0kzj0Y(MaterialDynamicColors r3, DynamicScheme r4) {
        r3.getClass();
        double r1 = 90.0d;
        if (isMonochrome(r4) == false) goto L11;
        if (r4.isDark == true) goto L9;
        r1 = 10.0d;
    L9:
        return Double.valueOf(r1);
    L11:
        if (r3.isFidelity(r4) == true) goto L19;
        if (r4.isDark == true) goto L17;
        r1 = 30.0d;
    L17:
        return Double.valueOf(r1);
    L19:
        return Double.valueOf(DynamicColor.foregroundTone(r3.secondaryContainer().tone.apply(r4).doubleValue(), 4.5d));
    }

    public static /* synthetic */ Double $r8$lambda$3NyEgwJ7w4h4R4bflHRTshyJKc8(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 80.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 40.0d;
        goto L7
    }

    public static /* synthetic */ DynamicColor $r8$lambda$3rkDQydPRFKQANjM8AURJKF545Q(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.tertiaryFixed();
    }

    /* renamed from: $r8$lambda$53no_2VMDH8w3XkUWXd1r-1K93o, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1836$r8$lambda$53no_2VMDH8w3XkUWXd1r1K93o(DynamicScheme r0) {
        return r0.primaryPalette;
    }

    public static /* synthetic */ Double $r8$lambda$62cUxaPZkVk4xiQfC0iRBdYdusc(DynamicScheme r3) {
        double r1 = 90.0d;
        if (isMonochrome(r3) == false) goto L11;
        if (r3.isDark == true) goto L9;
        r1 = 10.0d;
    L9:
        return Double.valueOf(r1);
    L11:
        if (r3.isDark == true) goto L15;
        r1 = 30.0d;
    L15:
        return Double.valueOf(r1);
    }

    /* renamed from: $r8$lambda$691m-OuziJ6g2fkt3vsZIEFI264, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1837$r8$lambda$691mOuziJ6g2fkt3vsZIEFI264(DynamicScheme r0) {
        return r0.tertiaryPalette;
    }

    public static /* synthetic */ Double $r8$lambda$753fYGJCQoiKZSLq4_ojNhadFKQ(DynamicScheme r10) {
        if (r10.isDark == false) goto L5;
        double r0 = new ContrastCurve(4.0d, 4.0d, 2.0d, 0.0d).get(r10.contrastLevel);
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 100.0d;
        goto L7
    }

    public static /* synthetic */ Double $r8$lambda$7xH019cRObx7qv97igFapMRW9Pk(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 6.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 98.0d;
        goto L7
    }

    public static /* synthetic */ ToneDeltaPair $r8$lambda$7xuiLGVfeXhAeM3UkKNdYnFnJuQ(MaterialDynamicColors r7, DynamicScheme r8) {
        return new ToneDeltaPair(r7.primaryContainer(), r7.primary(), 10.0d, TonePolarity.NEARER, false);
    }

    public static /* synthetic */ TonalPalette $r8$lambda$8N9YjzkV7js6OHy3jDiWzcX53L4(DynamicScheme r0) {
        return r0.neutralPalette;
    }

    /* renamed from: $r8$lambda$8sR8EjxJwLUuIpRIlTxPT63cM-o, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1838$r8$lambda$8sR8EjxJwLUuIpRIlTxPT63cMo(DynamicScheme r0) {
        return r0.neutralVariantPalette;
    }

    public static /* synthetic */ DynamicColor $r8$lambda$93mBLgNceWmv0aE_3L1FYIZ_uFU(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.inverseSurface();
    }

    public static /* synthetic */ DynamicColor $r8$lambda$9KgPwJs9Jl6GTHmbH5efNelnk1Q(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.secondaryFixed();
    }

    public static /* synthetic */ TonalPalette $r8$lambda$A3DBdzdQzx7JQYDdDVwbAgSxEsQ(DynamicScheme r0) {
        return r0.neutralVariantPalette;
    }

    /* renamed from: $r8$lambda$AgT_PHvw2e6aEJ-ja4Fn8QjNRlU, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1839$r8$lambda$AgT_PHvw2e6aEJja4Fn8QjNRlU(DynamicScheme r0) {
        return r0.primaryPalette;
    }

    public static /* synthetic */ DynamicColor $r8$lambda$Al9FPas3etVKkukhLqlRdW4ncgA(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.errorContainer();
    }

    public static /* synthetic */ ToneDeltaPair $r8$lambda$BFmGIXcWi4f4p2BcAJRzW9LQ2ls(MaterialDynamicColors r7, DynamicScheme r8) {
        return new ToneDeltaPair(r7.secondaryContainer(), r7.secondary(), 10.0d, TonePolarity.NEARER, false);
    }

    /* renamed from: $r8$lambda$BMz8OkgDrHqeL-M-IMbUq9S1v4U, reason: not valid java name */
    public static /* synthetic */ ToneDeltaPair m1840$r8$lambda$BMz8OkgDrHqeLMIMbUq9S1v4U(MaterialDynamicColors r7, DynamicScheme r8) {
        return new ToneDeltaPair(r7.errorContainer(), r7.error(), 10.0d, TonePolarity.NEARER, false);
    }

    /* renamed from: $r8$lambda$BP08d14uw5Tb0y_ZBi-n10CGqfs, reason: not valid java name */
    public static /* synthetic */ Double m1841$r8$lambda$BP08d14uw5Tb0y_ZBin10CGqfs(DynamicScheme r2) {
        return Double.valueOf(r2.secondaryPalette.getKeyColor().getTone());
    }

    /* renamed from: $r8$lambda$B_D-gTGcm4cAQoYwC3mea_UZ1LM, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1842$r8$lambda$B_DgTGcm4cAQoYwC3mea_UZ1LM(DynamicScheme r0) {
        return r0.primaryPalette;
    }

    /* renamed from: $r8$lambda$Br5qILwB4351p-JYbccEUs5szUE, reason: not valid java name */
    public static /* synthetic */ Double m1843$r8$lambda$Br5qILwB4351pJYbccEUs5szUE(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 60.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 50.0d;
        goto L7
    }

    public static /* synthetic */ Double $r8$lambda$BudreMWMW90tFtQmj9Tje4mFw2Q(DynamicScheme r11) {
        if (r11.isDark == false) goto L5;
        double r0 = new ContrastCurve(12.0d, 12.0d, 16.0d, 20.0d).get(r11.contrastLevel);
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = new ContrastCurve(94.0d, 94.0d, 92.0d, 90.0d).get(r11.contrastLevel);
        goto L7
    }

    /* renamed from: $r8$lambda$CSMr91JQ1SQN1D5GeEYf-3x4XDQ, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1844$r8$lambda$CSMr91JQ1SQN1D5GeEYf3x4XDQ(DynamicScheme r0) {
        return r0.neutralPalette;
    }

    public static /* synthetic */ DynamicColor $r8$lambda$D9ge5fmI89PoOdo6jN71oeV18zM(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.primaryFixedDim();
    }

    public static /* synthetic */ Double $r8$lambda$DFmyTBFs8_axzZKQcgR_i0qWZOs(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 90.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 10.0d;
        goto L7
    }

    public static /* synthetic */ Double $r8$lambda$DwU9BENCiZB8PlYhnEYDmpZAhgQ(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 10.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 90.0d;
        goto L7
    }

    public static /* synthetic */ Double $r8$lambda$EDhObFLVkvtGXX6UZ8WS3YGDOn4(DynamicScheme r10) {
        if (r10.isDark == false) goto L5;
        double r0 = new ContrastCurve(24.0d, 24.0d, 29.0d, 34.0d).get(r10.contrastLevel);
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 98.0d;
        goto L7
    }

    /* renamed from: $r8$lambda$EHvXVIuPCC6-2HjTJZCQ14VkpZw, reason: not valid java name */
    public static /* synthetic */ Double m1845$r8$lambda$EHvXVIuPCC62HjTJZCQ14VkpZw(DynamicScheme r3) {
        double r1 = 100.0d;
        if (isMonochrome(r3) == false) goto L10;
        if (r3.isDark == false) goto L8;
        r1 = 10.0d;
    L8:
        return Double.valueOf(r1);
    L10:
        if (r3.isDark == false) goto L13;
        r1 = 20.0d;
    L13:
        return Double.valueOf(r1);
    }

    public static /* synthetic */ Double $r8$lambda$EIoggH4XRew41dsf6WKTx_nXSXw(DynamicScheme r2) {
        return Double.valueOf(r2.primaryPalette.getKeyColor().getTone());
    }

    public static /* synthetic */ Double $r8$lambda$EWE6QjaYXyaeU4onfesJhncPIj8(DynamicScheme r2) {
        return Double.valueOf(0.0d);
    }

    /* renamed from: $r8$lambda$Ee_wYoFmOEbzU_KVMdr8Adm-eAc, reason: not valid java name */
    public static /* synthetic */ DynamicColor m1846$r8$lambda$Ee_wYoFmOEbzU_KVMdr8AdmeAc(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.tertiaryFixed();
    }

    public static /* synthetic */ Double $r8$lambda$EhNHL0AfADKDonWgcSUuaLYCoeU(MaterialDynamicColors r2, DynamicScheme r3) {
        r2.getClass();
        if (isMonochrome(r3) == false) goto L11;
        if (r3.isDark == false) goto L7;
        double r22 = 60.0d;
    L9:
        return Double.valueOf(r22);
    L7:
        r22 = 49.0d;
        goto L9
    L11:
        if (r2.isFidelity(r3) == true) goto L19;
        if (r3.isDark == false) goto L15;
        double r23 = 30.0d;
    L17:
        return Double.valueOf(r23);
    L15:
        r23 = 90.0d;
        goto L17
    L19:
        return Double.valueOf(DislikeAnalyzer.fixIfDisliked(r3.tertiaryPalette.getHct(r3.sourceColorHct.getTone())).getTone());
    }

    public static /* synthetic */ TonalPalette $r8$lambda$FnrflFDuCOIvk_1ThPwJju9CyCY(DynamicScheme r0) {
        return r0.neutralVariantPalette;
    }

    public static /* synthetic */ DynamicColor $r8$lambda$G13AdpWORTQGMs5ArtyWfRsYLIk(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.tertiaryFixedDim();
    }

    public static /* synthetic */ TonalPalette $r8$lambda$G1U2DwQKIkQ4YVgSyqSbAaI82dA(DynamicScheme r0) {
        return r0.secondaryPalette;
    }

    public static /* synthetic */ Double $r8$lambda$G6b21CiQOJTMnDSSDKDHuiZeDWY(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 40.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 80.0d;
        goto L7
    }

    public static /* synthetic */ TonalPalette $r8$lambda$Gm82oI5ddtfzwMalHXOMfzVwgGQ(DynamicScheme r0) {
        return r0.tertiaryPalette;
    }

    /* renamed from: $r8$lambda$H-1i7GPC40INAGTYtXpu0ngHMoU, reason: not valid java name */
    public static /* synthetic */ Double m1847$r8$lambda$H1i7GPC40INAGTYtXpu0ngHMoU(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 80.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 30.0d;
        goto L7
    }

    /* renamed from: $r8$lambda$HAH-STEuX22IL2ey_pp6sRoXIPI, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1848$r8$lambda$HAHSTEuX22IL2ey_pp6sRoXIPI(DynamicScheme r0) {
        return r0.neutralVariantPalette;
    }

    public static /* synthetic */ TonalPalette $r8$lambda$HdtGC_9fg1Pe6_4H7_lQbumkKw8(DynamicScheme r0) {
        return r0.neutralPalette;
    }

    public static /* synthetic */ Double $r8$lambda$HmUtLkO7lcM6gH6VhpeARm5diaI(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 100.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 0.0d;
        goto L7
    }

    /* renamed from: $r8$lambda$IzbKJSnfUCQljqgkag8UIDTn-r0, reason: not valid java name */
    public static /* synthetic */ Double m1849$r8$lambda$IzbKJSnfUCQljqgkag8UIDTnr0(MaterialDynamicColors r12, DynamicScheme r13) {
        r12.getClass();
        double r1 = 30.0d;
        if (r13.isDark == false) goto L5;
        double r9 = 30.0d;
    L7:
        if (isMonochrome(r13) == false) goto L15;
        if (r13.isDark == true) goto L13;
        r1 = 85.0d;
    L13:
        return Double.valueOf(r1);
    L15:
        if (r12.isFidelity(r13) == true) goto L19;
        return Double.valueOf(r9);
    L19:
        return Double.valueOf(findDesiredChromaByTone(r13.secondaryPalette.getHue(), r13.secondaryPalette.getChroma(), r9, !r13.isDark));
    L5:
        r9 = 90.0d;
        goto L7
    }

    public static /* synthetic */ Double $r8$lambda$JBGnBxBZTn0aszq9lftyBe99gkM(DynamicScheme r11) {
        if (r11.isDark == false) goto L5;
        double r0 = new ContrastCurve(17.0d, 17.0d, 21.0d, 25.0d).get(r11.contrastLevel);
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = new ContrastCurve(92.0d, 92.0d, 88.0d, 85.0d).get(r11.contrastLevel);
        goto L7
    }

    /* renamed from: $r8$lambda$JDcw6RJkkwdPhup_6Meo6Ome-JY, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1850$r8$lambda$JDcw6RJkkwdPhup_6Meo6OmeJY(DynamicScheme r0) {
        return r0.neutralPalette;
    }

    /* renamed from: $r8$lambda$JKKZo-rQKhDKImOz6FPNC36hpBc, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1851$r8$lambda$JKKZorQKhDKImOz6FPNC36hpBc(DynamicScheme r0) {
        return r0.primaryPalette;
    }

    /* renamed from: $r8$lambda$JiIKre9Uej6UnWFHZZbq-mzMVvg, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1852$r8$lambda$JiIKre9Uej6UnWFHZZbqmzMVvg(DynamicScheme r0) {
        return r0.primaryPalette;
    }

    public static /* synthetic */ TonalPalette $r8$lambda$KP_K21WIdP5EFFRH6qGGJ_XLhV4(DynamicScheme r0) {
        return r0.primaryPalette;
    }

    public static /* synthetic */ DynamicColor $r8$lambda$KjnRYATxbo_1EOPn_ZOC7HKW7x0(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.primary();
    }

    public static /* synthetic */ TonalPalette $r8$lambda$LLPa16n5lyuPbyhpn1WW8my6RbA(DynamicScheme r0) {
        return r0.neutralPalette;
    }

    /* renamed from: $r8$lambda$LmWZj_AMmB-Mqu_jq6rULpAb_F4, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1853$r8$lambda$LmWZj_AMmBMqu_jq6rULpAb_F4(DynamicScheme r0) {
        return r0.primaryPalette;
    }

    /* renamed from: $r8$lambda$LzePG_k2Ck11fgQS_-fWdpLzOwk, reason: not valid java name */
    public static /* synthetic */ DynamicColor m1854$r8$lambda$LzePG_k2Ck11fgQS_fWdpLzOwk(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.tertiaryContainer();
    }

    /* renamed from: $r8$lambda$MF91EOTB8qNRHvQE-KN0aMZrRQ0, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1855$r8$lambda$MF91EOTB8qNRHvQEKN0aMZrRQ0(DynamicScheme r0) {
        return r0.tertiaryPalette;
    }

    public static /* synthetic */ Double $r8$lambda$MgBD7oJBcx6upN6eRuMJj5xwXv8(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 30.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 90.0d;
        goto L7
    }

    public static /* synthetic */ TonalPalette $r8$lambda$MyPp93FDMjmChZbGciitu9bX3ZM(DynamicScheme r0) {
        return r0.neutralPalette;
    }

    /* renamed from: $r8$lambda$N0MqKgI37q-hoL-3eM5Ia-8Wdtw, reason: not valid java name */
    public static /* synthetic */ Double m1856$r8$lambda$N0MqKgI37qhoL3eM5Ia8Wdtw(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 0.2d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 0.12d;
        goto L7
    }

    public static /* synthetic */ TonalPalette $r8$lambda$NJ7YNOWyINHOGhQpWKrdSsDudr8(DynamicScheme r0) {
        return r0.neutralPalette;
    }

    public static /* synthetic */ ToneDeltaPair $r8$lambda$Nojx5v6iMlKxtendojdYTexdCnI(MaterialDynamicColors r7, DynamicScheme r8) {
        return new ToneDeltaPair(r7.errorContainer(), r7.error(), 10.0d, TonePolarity.NEARER, false);
    }

    public static /* synthetic */ Double $r8$lambda$O3hstdB3WD55z2I58Zy8tRAhh1A(DynamicScheme r2) {
        if (isMonochrome(r2) == false) goto L5;
        double r0 = 40.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 90.0d;
        goto L7
    }

    public static /* synthetic */ TonalPalette $r8$lambda$ORKsYNf3nr_5MHoI1hsfapRRNyM(DynamicScheme r0) {
        return r0.tertiaryPalette;
    }

    public static /* synthetic */ TonalPalette $r8$lambda$OYH30EXmKCm4so78oXkluo0SeKY(DynamicScheme r0) {
        return r0.secondaryPalette;
    }

    public static /* synthetic */ ToneDeltaPair $r8$lambda$OvZEJThb90sQ3uStky3z7MNZ8Ws(MaterialDynamicColors r7, DynamicScheme r8) {
        return new ToneDeltaPair(r7.secondaryFixed(), r7.secondaryFixedDim(), 10.0d, TonePolarity.LIGHTER, true);
    }

    public static /* synthetic */ Double $r8$lambda$P5LSXWAdZnvRnUoGfniHZpBbgwo(DynamicScheme r2) {
        return Double.valueOf(r2.neutralVariantPalette.getKeyColor().getTone());
    }

    public static /* synthetic */ TonalPalette $r8$lambda$R5OdoBaKbfuNzjiNfFaoMJFJY7U(DynamicScheme r0) {
        return r0.primaryPalette;
    }

    public static /* synthetic */ TonalPalette $r8$lambda$R86LhlJvKMXZNhTvKDPBQf305z0(DynamicScheme r0) {
        return r0.neutralPalette;
    }

    /* renamed from: $r8$lambda$S2IvI5mz57Vr0X9iK-ZsujxhqK4, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1857$r8$lambda$S2IvI5mz57Vr0X9iKZsujxhqK4(DynamicScheme r0) {
        return r0.tertiaryPalette;
    }

    public static /* synthetic */ Double $r8$lambda$TWR8VJwymIOFvq55fmJ9HrDDSvc(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 6.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 98.0d;
        goto L7
    }

    public static /* synthetic */ ToneDeltaPair $r8$lambda$TeOBmH0oPwAYssMA1OvxLrCA4l4(MaterialDynamicColors r7, DynamicScheme r8) {
        return new ToneDeltaPair(r7.tertiaryContainer(), r7.tertiary(), 10.0d, TonePolarity.NEARER, false);
    }

    public static /* synthetic */ Double $r8$lambda$U3VuEYWsqMWheBs1r_cUItLqV_I(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 80.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 40.0d;
        goto L7
    }

    /* renamed from: $r8$lambda$UTcjeNt-4r0e7oCvNpx26eOkXmI, reason: not valid java name */
    public static /* synthetic */ Double m1858$r8$lambda$UTcjeNt4r0e7oCvNpx26eOkXmI(MaterialDynamicColors r2, DynamicScheme r3) {
        if (r2.isFidelity(r3) == false) goto L7;
        return Double.valueOf(DynamicColor.foregroundTone(r2.primaryContainer().tone.apply(r3).doubleValue(), 4.5d));
    L7:
        if (isMonochrome(r3) == false) goto L15;
        if (r3.isDark == false) goto L11;
        double r22 = 0.0d;
    L13:
        return Double.valueOf(r22);
    L11:
        r22 = 100.0d;
        goto L13
    L15:
        if (r3.isDark == false) goto L17;
        double r23 = 90.0d;
    L19:
        return Double.valueOf(r23);
    L17:
        r23 = 30.0d;
        goto L19
    }

    public static /* synthetic */ DynamicColor $r8$lambda$UTss0PV7E14afDqx5cnr_q6P1cc(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.primaryFixedDim();
    }

    public static /* synthetic */ Double $r8$lambda$UYTtgMKDlM81iLvDgzjUYqkWlBo(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 20.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 95.0d;
        goto L7
    }

    public static /* synthetic */ Double $r8$lambda$Uu3agoMb2nGAAyBlRGBmyz9oN0c(DynamicScheme r11) {
        if (r11.isDark == false) goto L5;
        double r0 = 6.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = new ContrastCurve(87.0d, 87.0d, 80.0d, 75.0d).get(r11.contrastLevel);
        goto L7
    }

    public static /* synthetic */ Double $r8$lambda$Uy3plK61bCh8aOVEzmpt9bX88g4(DynamicScheme r2) {
        return Double.valueOf(10.0d);
    }

    /* renamed from: $r8$lambda$VF9f-0UlH5trx4Bic4keO7uV0po, reason: not valid java name */
    public static /* synthetic */ Double m1859$r8$lambda$VF9f0UlH5trx4Bic4keO7uV0po(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 80.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 40.0d;
        goto L7
    }

    public static /* synthetic */ TonalPalette $r8$lambda$V_OKZc4XZGYuDRX9R7SzPwu2vec(DynamicScheme r0) {
        return r0.tertiaryPalette;
    }

    /* renamed from: $r8$lambda$WDm-R4NTIXrUQ5SITis2T8PvgjA, reason: not valid java name */
    public static /* synthetic */ DynamicColor m1860$r8$lambda$WDmR4NTIXrUQ5SITis2T8PvgjA(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.secondaryFixedDim();
    }

    public static /* synthetic */ Double $r8$lambda$WKuM40qTBNXHU7wm0V72um7qmSk(DynamicScheme r2) {
        if (isMonochrome(r2) == false) goto L11;
        if (r2.isDark == false) goto L7;
        double r0 = 100.0d;
    L9:
        return Double.valueOf(r0);
    L7:
        r0 = 0.0d;
        goto L9
    L11:
        if (r2.isDark == false) goto L13;
        double r02 = 80.0d;
    L15:
        return Double.valueOf(r02);
    L13:
        r02 = 40.0d;
        goto L15
    }

    public static /* synthetic */ TonalPalette $r8$lambda$WM4QFraRCYJq6wL5AfI7fwfZE10(DynamicScheme r0) {
        return r0.secondaryPalette;
    }

    /* renamed from: $r8$lambda$WOlE5kxkT79msWjD-gzf1fx-044, reason: not valid java name */
    public static /* synthetic */ Double m1861$r8$lambda$WOlE5kxkT79msWjDgzf1fx044(DynamicScheme r2) {
        if (isMonochrome(r2) == false) goto L11;
        if (r2.isDark == false) goto L7;
        double r0 = 10.0d;
    L9:
        return Double.valueOf(r0);
    L7:
        r0 = 90.0d;
        goto L9
    L11:
        if (r2.isDark == false) goto L13;
        double r02 = 20.0d;
    L15:
        return Double.valueOf(r02);
    L13:
        r02 = 100.0d;
        goto L15
    }

    public static /* synthetic */ TonalPalette $r8$lambda$X0whBdiYao_CTmOvJXJdFM9Lerw(DynamicScheme r0) {
        return r0.primaryPalette;
    }

    public static /* synthetic */ TonalPalette $r8$lambda$X7i9vz8wgFZZ7hqRy8ZXVMaE5ps(DynamicScheme r0) {
        return r0.tertiaryPalette;
    }

    public static /* synthetic */ TonalPalette $r8$lambda$XUZoYFkBBnGnsOY6NPTkDvc3yTU(DynamicScheme r0) {
        return r0.neutralPalette;
    }

    /* renamed from: $r8$lambda$XsSkJqmaRHXWUEA8u0xI8M1e6-A, reason: not valid java name */
    public static /* synthetic */ Double m1862$r8$lambda$XsSkJqmaRHXWUEA8u0xI8M1e6A(DynamicScheme r2) {
        if (isMonochrome(r2) == false) goto L5;
        double r0 = 90.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 30.0d;
        goto L7
    }

    public static /* synthetic */ TonalPalette $r8$lambda$Yp684_AKUZhBRMmERxAH0Xc9xIU(DynamicScheme r0) {
        return r0.neutralPalette;
    }

    public static /* synthetic */ Double $r8$lambda$Zly7fa0uOal4E3fLslmkRD_9L5E(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 90.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 20.0d;
        goto L7
    }

    /* renamed from: $r8$lambda$_42detWe0fsyTkbR_-PTZRTdlVA, reason: not valid java name */
    public static /* synthetic */ Double m1863$r8$lambda$_42detWe0fsyTkbR_PTZRTdlVA(DynamicScheme r11) {
        if (r11.isDark == false) goto L5;
        double r0 = new ContrastCurve(22.0d, 22.0d, 26.0d, 30.0d).get(r11.contrastLevel);
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = new ContrastCurve(90.0d, 90.0d, 84.0d, 80.0d).get(r11.contrastLevel);
        goto L7
    }

    public static /* synthetic */ TonalPalette $r8$lambda$_wOXBzziQ6bwlInTYpyhAumgrkE(DynamicScheme r0) {
        return r0.secondaryPalette;
    }

    public static /* synthetic */ Double $r8$lambda$aURlTVvRU5xtvkLLjh162HkEbbE(DynamicScheme r2) {
        return Double.valueOf(0.0d);
    }

    public static /* synthetic */ DynamicColor $r8$lambda$aZB8Qcfho4FhqnPzH9zSZK57Hng(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.primaryFixed();
    }

    public static /* synthetic */ Double $r8$lambda$atzvKZUZCMAXZ2oa2LhQgYF8SwU(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 10.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 90.0d;
        goto L7
    }

    public static /* synthetic */ TonalPalette $r8$lambda$aziQRULtm31tcMTzT2PPBEePvOQ(DynamicScheme r0) {
        return r0.primaryPalette;
    }

    public static /* synthetic */ Double $r8$lambda$bU8MUUzIZ31yw8OQXf5S3wPmKjI(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 10.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 90.0d;
        goto L7
    }

    /* renamed from: $r8$lambda$bYy_j_7FTDwy-NksCrdNvtza9MU, reason: not valid java name */
    public static /* synthetic */ ToneDeltaPair m1864$r8$lambda$bYy_j_7FTDwyNksCrdNvtza9MU(MaterialDynamicColors r7, DynamicScheme r8) {
        return new ToneDeltaPair(r7.secondaryContainer(), r7.secondary(), 10.0d, TonePolarity.NEARER, false);
    }

    public static /* synthetic */ Double $r8$lambda$bttoD6hlzyPMkpIfWsX921JE5es(DynamicScheme r2) {
        if (isMonochrome(r2) == false) goto L11;
        if (r2.isDark == false) goto L7;
        double r0 = 10.0d;
    L9:
        return Double.valueOf(r0);
    L7:
        r0 = 90.0d;
        goto L9
    L11:
        if (r2.isDark == false) goto L13;
        double r02 = 20.0d;
    L15:
        return Double.valueOf(r02);
    L13:
        r02 = 100.0d;
        goto L15
    }

    public static /* synthetic */ Double $r8$lambda$bvjppDZkmqDl8k6e1NeBZFR9pCo(DynamicScheme r2) {
        if (isMonochrome(r2) == false) goto L5;
        double r0 = 90.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 30.0d;
        goto L7
    }

    /* renamed from: $r8$lambda$c3Zx-IcziLmf9xdFxfbEK1BJnOw, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1865$r8$lambda$c3ZxIcziLmf9xdFxfbEK1BJnOw(DynamicScheme r0) {
        return r0.neutralPalette;
    }

    public static /* synthetic */ Double $r8$lambda$czEyzXYmhR1TgMNUxS3C1bLOX5Y(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 90.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 10.0d;
        goto L7
    }

    /* renamed from: $r8$lambda$d-T2vd0ye9I8ZLQX-29tlccvvjI, reason: not valid java name */
    public static /* synthetic */ ToneDeltaPair m1866$r8$lambda$dT2vd0ye9I8ZLQX29tlccvvjI(MaterialDynamicColors r7, DynamicScheme r8) {
        return new ToneDeltaPair(r7.secondaryFixed(), r7.secondaryFixedDim(), 10.0d, TonePolarity.LIGHTER, true);
    }

    /* renamed from: $r8$lambda$d93-9kBrwV6vzgtr_qMcRO75U0U, reason: not valid java name */
    public static /* synthetic */ DynamicColor m1867$r8$lambda$d939kBrwV6vzgtr_qMcRO75U0U(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.primaryContainer();
    }

    public static /* synthetic */ Double $r8$lambda$dd8XlA1UaZulOTDQRl8DNvtKl4k(MaterialDynamicColors r2, DynamicScheme r3) {
        r2.getClass();
        if (isMonochrome(r3) == false) goto L11;
        if (r3.isDark == false) goto L7;
        double r22 = 0.0d;
    L9:
        return Double.valueOf(r22);
    L7:
        r22 = 100.0d;
        goto L9
    L11:
        if (r2.isFidelity(r3) == true) goto L19;
        if (r3.isDark == false) goto L15;
        double r23 = 90.0d;
    L17:
        return Double.valueOf(r23);
    L15:
        r23 = 30.0d;
        goto L17
    L19:
        return Double.valueOf(DynamicColor.foregroundTone(r2.tertiaryContainer().tone.apply(r3).doubleValue(), 4.5d));
    }

    public static /* synthetic */ TonalPalette $r8$lambda$dz9IPP7ntcu3auPNCf7pu9j4bZ4(DynamicScheme r0) {
        return r0.errorPalette;
    }

    public static /* synthetic */ Double $r8$lambda$eLf7xrTGoPWlb5ZlhDVNG9g2q8U(DynamicScheme r2) {
        if (isMonochrome(r2) == false) goto L5;
        double r0 = 40.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 90.0d;
        goto L7
    }

    public static /* synthetic */ TonalPalette $r8$lambda$et6DsGluOAw8S_D3be4S0UzPkbA(DynamicScheme r0) {
        return r0.neutralVariantPalette;
    }

    /* renamed from: $r8$lambda$fXALqQyxIJAWW43-8SINRLVlDM8, reason: not valid java name */
    public static /* synthetic */ Double m1868$r8$lambda$fXALqQyxIJAWW438SINRLVlDM8(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 30.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 90.0d;
        goto L7
    }

    public static /* synthetic */ ToneDeltaPair $r8$lambda$flOmK91DuQNU6ZQSz7iP36OcNL0(MaterialDynamicColors r7, DynamicScheme r8) {
        return new ToneDeltaPair(r7.primaryContainer(), r7.primary(), 10.0d, TonePolarity.NEARER, false);
    }

    public static /* synthetic */ TonalPalette $r8$lambda$fxxzhtW6wXsSCvBu_aQwOsh39T0(DynamicScheme r0) {
        return r0.neutralPalette;
    }

    /* renamed from: $r8$lambda$gBOhzteSxkcYq-OGiWbj3J9cZzM, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1869$r8$lambda$gBOhzteSxkcYqOGiWbj3J9cZzM(DynamicScheme r0) {
        return r0.errorPalette;
    }

    public static /* synthetic */ Double $r8$lambda$gZRwj4LSDf34phGhaGnTEhwKPN0(DynamicScheme r2) {
        if (isMonochrome(r2) == false) goto L5;
        double r0 = 80.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 90.0d;
        goto L7
    }

    public static /* synthetic */ DynamicColor $r8$lambda$g_fWczuHTQRCYeuTXa_z4Uo8ENE(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.primaryFixed();
    }

    /* renamed from: $r8$lambda$goUEiwYnJwC_oKwjGO7DRli65-I, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1870$r8$lambda$goUEiwYnJwC_oKwjGO7DRli65I(DynamicScheme r0) {
        return r0.primaryPalette;
    }

    public static /* synthetic */ TonalPalette $r8$lambda$gsla5tb_QmsmiBzcCtVKTnXy67E(DynamicScheme r0) {
        return r0.tertiaryPalette;
    }

    public static /* synthetic */ Double $r8$lambda$hdTtXSJsFPVdocGbI2Ua1TAAs5U(DynamicScheme r2) {
        if (isMonochrome(r2) == false) goto L5;
        double r0 = 100.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 10.0d;
        goto L7
    }

    public static /* synthetic */ TonalPalette $r8$lambda$i0JwaRK1pCjknPe2N74wPm0FuzM(DynamicScheme r0) {
        return r0.neutralPalette;
    }

    /* renamed from: $r8$lambda$i4lCydE-p-ft7pCwJfQsh2EHMSw, reason: not valid java name */
    public static /* synthetic */ ToneDeltaPair m1871$r8$lambda$i4lCydEpft7pCwJfQsh2EHMSw(MaterialDynamicColors r7, DynamicScheme r8) {
        return new ToneDeltaPair(r7.tertiaryFixed(), r7.tertiaryFixedDim(), 10.0d, TonePolarity.LIGHTER, true);
    }

    public static /* synthetic */ Double $r8$lambda$i7yFulMZ_crcj_rLlYbSXafN8xU(DynamicScheme r2) {
        if (isMonochrome(r2) == false) goto L5;
        double r0 = 70.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 80.0d;
        goto L7
    }

    public static /* synthetic */ TonalPalette $r8$lambda$iI8TGVaVp779Csfd1EQBRGyBCw4(DynamicScheme r0) {
        return r0.neutralPalette;
    }

    public static /* synthetic */ TonalPalette $r8$lambda$iWpzYLsJ4U9GRIqAPJg_ZbKuYcg(DynamicScheme r0) {
        return r0.neutralPalette;
    }

    public static /* synthetic */ ToneDeltaPair $r8$lambda$iktiPh2eC0EW4rhZMdfP8gj5o4A(MaterialDynamicColors r7, DynamicScheme r8) {
        return new ToneDeltaPair(r7.tertiaryContainer(), r7.tertiary(), 10.0d, TonePolarity.NEARER, false);
    }

    /* renamed from: $r8$lambda$lA_vAWEgvVlLx5Ds-nDcDakHMok, reason: not valid java name */
    public static /* synthetic */ DynamicColor m1872$r8$lambda$lA_vAWEgvVlLx5DsnDcDakHMok(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.secondaryFixedDim();
    }

    public static /* synthetic */ TonalPalette $r8$lambda$lBpDOerJlb942naSSfxvHCwivhE(DynamicScheme r0) {
        return r0.neutralPalette;
    }

    /* renamed from: $r8$lambda$lf6QqT-V0VT-UvRf04inOXEZUJQ, reason: not valid java name */
    public static /* synthetic */ Double m1873$r8$lambda$lf6QqTV0VTUvRf04inOXEZUJQ(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 30.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 90.0d;
        goto L7
    }

    public static /* synthetic */ Double $r8$lambda$nYm0QxMry84fY5ECHJfCeAbryh0(DynamicScheme r2) {
        if (isMonochrome(r2) == false) goto L11;
        if (r2.isDark == false) goto L7;
        double r0 = 90.0d;
    L9:
        return Double.valueOf(r0);
    L7:
        r0 = 25.0d;
        goto L9
    L11:
        if (r2.isDark == false) goto L13;
        double r02 = 80.0d;
    L15:
        return Double.valueOf(r02);
    L13:
        r02 = 40.0d;
        goto L15
    }

    public static /* synthetic */ DynamicColor $r8$lambda$nYwKmCAjbkQt9e4Hc2TrXIcF_yE(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.tertiaryFixedDim();
    }

    public static /* synthetic */ TonalPalette $r8$lambda$oSe8Hw2Hrt0eEFR46EbkzSaN2SA(DynamicScheme r0) {
        return r0.neutralVariantPalette;
    }

    public static /* synthetic */ Double $r8$lambda$oWKrh1xMeI11GzXIIxhxMNyEFeA(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 10.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 90.0d;
        goto L7
    }

    public static /* synthetic */ Double $r8$lambda$oud39NBBQwPf4miQLXaVom4Ngk8(MaterialDynamicColors r0, DynamicScheme r1) {
        if (r0.isFidelity(r1) == false) goto L7;
        return Double.valueOf(r1.sourceColorHct.getTone());
    L7:
        if (isMonochrome(r1) == false) goto L15;
        if (r1.isDark == false) goto L11;
        double r02 = 85.0d;
    L13:
        return Double.valueOf(r02);
    L11:
        r02 = 25.0d;
        goto L13
    L15:
        if (r1.isDark == false) goto L17;
        double r03 = 30.0d;
    L19:
        return Double.valueOf(r03);
    L17:
        r03 = 90.0d;
        goto L19
    }

    public static /* synthetic */ Double $r8$lambda$ox8YgcersNQGkj7RqfgNgKZqYB4(DynamicScheme r2) {
        if (isMonochrome(r2) == false) goto L5;
        double r0 = 30.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 80.0d;
        goto L7
    }

    public static /* synthetic */ Double $r8$lambda$p0ZyPwwgGi9y2_b70meVKSuhL_s(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 80.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 30.0d;
        goto L7
    }

    /* renamed from: $r8$lambda$pHFdsv-TJfMLXg7p8ZviqMWTGvc, reason: not valid java name */
    public static /* synthetic */ DynamicColor m1874$r8$lambda$pHFdsvTJfMLXg7p8ZviqMWTGvc(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.error();
    }

    public static /* synthetic */ DynamicColor $r8$lambda$qYUGZG1G44eNpdpVTV1jfnEU_Uo(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.secondaryContainer();
    }

    public static /* synthetic */ DynamicColor $r8$lambda$qlfe8IBCombGEVnusQzXus69xis(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.inverseSurface();
    }

    public static /* synthetic */ Double $r8$lambda$rBhGBWuHAfj9lJ3VpNsyZltEQ4U(DynamicScheme r2) {
        if (r2.isDark == false) goto L5;
        double r0 = 30.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 80.0d;
        goto L7
    }

    public static /* synthetic */ TonalPalette $r8$lambda$rNtvx41bLEZPK4R3a7toy3gtNd0(DynamicScheme r0) {
        return r0.secondaryPalette;
    }

    public static /* synthetic */ TonalPalette $r8$lambda$r_xs8P9u8eQiwAFSUeg78FqZrgo(DynamicScheme r0) {
        return r0.secondaryPalette;
    }

    public static /* synthetic */ TonalPalette $r8$lambda$rbJqeMelTsvtQF6pdGncRrWVLuQ(DynamicScheme r0) {
        return r0.tertiaryPalette;
    }

    /* renamed from: $r8$lambda$rk_GkmD-GcrNFM5lcwjoU7ZMqPM, reason: not valid java name */
    public static /* synthetic */ DynamicColor m1875$r8$lambda$rk_GkmDGcrNFM5lcwjoU7ZMqPM(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.secondaryFixed();
    }

    /* renamed from: $r8$lambda$tKELCMc_78I3d-Sdl--8J4qr0GA, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1876$r8$lambda$tKELCMc_78I3dSdl8J4qr0GA(DynamicScheme r0) {
        return r0.errorPalette;
    }

    /* renamed from: $r8$lambda$tTMBfEoew5nfvj7ofvxVmJVM-LM, reason: not valid java name */
    public static /* synthetic */ DynamicColor m1877$r8$lambda$tTMBfEoew5nfvj7ofvxVmJVMLM(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.background();
    }

    /* renamed from: $r8$lambda$uW4HBIifAUZXF-Euk1B4yNI2ZQs, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1878$r8$lambda$uW4HBIifAUZXFEuk1B4yNI2ZQs(DynamicScheme r0) {
        return r0.errorPalette;
    }

    public static /* synthetic */ Double $r8$lambda$v2Ghw74VdLASLWYLzckC4RqoDDs(DynamicScheme r2) {
        if (isMonochrome(r2) == false) goto L5;
        double r0 = 25.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 30.0d;
        goto L7
    }

    /* renamed from: $r8$lambda$vGOtmB9yMwyd-lbhkXlWWchfDGI, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1879$r8$lambda$vGOtmB9yMwydlbhkXlWWchfDGI(DynamicScheme r0) {
        return r0.neutralPalette;
    }

    public static /* synthetic */ Double $r8$lambda$vaAZIi5q1TOCHx4eliio5Y8fE9g(DynamicScheme r2) {
        if (isMonochrome(r2) == false) goto L5;
        double r0 = 30.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 80.0d;
        goto L7
    }

    /* renamed from: $r8$lambda$vgY7zb80SyF4-nYx5nOlJkDcHvQ, reason: not valid java name */
    public static /* synthetic */ ToneDeltaPair m1880$r8$lambda$vgY7zb80SyF4nYx5nOlJkDcHvQ(MaterialDynamicColors r7, DynamicScheme r8) {
        return new ToneDeltaPair(r7.tertiaryFixed(), r7.tertiaryFixedDim(), 10.0d, TonePolarity.LIGHTER, true);
    }

    public static /* synthetic */ TonalPalette $r8$lambda$x3LnDgb3xNC46Ib6PFdrv_gYe6k(DynamicScheme r0) {
        return r0.neutralPalette;
    }

    public static /* synthetic */ DynamicColor $r8$lambda$xKLg9cmjS0F86RydDnK4JdhM010(MaterialDynamicColors r0, DynamicScheme r1) {
        return r0.secondary();
    }

    public static /* synthetic */ Double $r8$lambda$xofFIbgAwgp0fhx8G5zJoUqxZ0U(DynamicScheme r2) {
        return Double.valueOf(r2.neutralPalette.getKeyColor().getTone());
    }

    public static /* synthetic */ TonalPalette $r8$lambda$xsYIQTlu6U77Wv6zuCHLvgqGlPQ(DynamicScheme r0) {
        return r0.secondaryPalette;
    }

    public static /* synthetic */ Double $r8$lambda$ykEzX81RMA0xj2V1ffLKAi414P0(DynamicScheme r2) {
        return Double.valueOf(r2.tertiaryPalette.getKeyColor().getTone());
    }

    public static /* synthetic */ TonalPalette $r8$lambda$zfZo0nqGqcNunt1yyudQBId57bs(DynamicScheme r0) {
        return r0.neutralPalette;
    }

    public static /* synthetic */ TonalPalette $r8$lambda$zqsZEHInv4HHb6xJ70LUV5jdokY(DynamicScheme r0) {
        return r0.neutralPalette;
    }

    /* renamed from: $r8$lambda$zqyuLwUAMpA4ob-jlLAhcLHX_M8, reason: not valid java name */
    public static /* synthetic */ Double m1881$r8$lambda$zqyuLwUAMpA4objlLAhcLHX_M8(DynamicScheme r11) {
        if (r11.isDark == false) goto L5;
        double r0 = new ContrastCurve(10.0d, 10.0d, 11.0d, 12.0d).get(r11.contrastLevel);
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = new ContrastCurve(96.0d, 96.0d, 96.0d, 95.0d).get(r11.contrastLevel);
        goto L7
    }

    public MaterialDynamicColors() {
        this.isExtendedFidelity = false;
    }

    public MaterialDynamicColors(boolean r1) {
        this.isExtendedFidelity = r1;
    }

    public static double findDesiredChromaByTone(double r8, double r10, double r12, boolean r14) {
        Hct r0 = Hct.from(r8, r10, r12);
        if (r0.getChroma() >= r10) goto L20;
        double r1 = r0.getChroma();
    L6:
        if (r0.getChroma() >= r10) goto L20;
        if (r14 == false) goto L9;
        double r3 = -1.0d;
    L10:
        r12 = r12 + r3;
        Hct r32 = Hct.from(r8, r10, r12);
        if (r1 > r32.getChroma()) goto L15;
        if (Math.abs(r32.getChroma() - r10) < 0.4d) goto L15;
        if (Math.abs(r32.getChroma() - r10) >= Math.abs(r0.getChroma() - r10)) goto L19;
        r0 = r32;
    L19:
        r1 = Math.max(r1, r32.getChroma());
    L15:
        return r12;
    L9:
        r3 = 1.0d;
    L20:
        return r12;
    }

    private boolean isFidelity(DynamicScheme r3) {
        if (this.isExtendedFidelity == false) goto L9;
        Variant r2 = r3.variant;
        if (r2 == Variant.MONOCHROME) goto L9;
        if (r2 == Variant.NEUTRAL) goto L9;
        return true;
    L9:
        Variant r22 = r3.variant;
        if (r22 != Variant.FIDELITY) goto L12;
    L16:
        return true;
    L12:
        if (r22 == Variant.CONTENT) goto L16;
        return false;
    }

    private static boolean isMonochrome(DynamicScheme r1) {
        if (r1.variant != Variant.MONOCHROME) goto L6;
        return true;
    L6:
        return false;
    }

    public DynamicColor background() {
        return new DynamicColor("background", new MaterialDynamicColors$$ExternalSyntheticLambda43(), new MaterialDynamicColors$$ExternalSyntheticLambda44(), true, null, null, null, null);
    }

    public DynamicColor controlActivated() {
        return DynamicColor.fromPalette("control_activated", new MaterialDynamicColors$$ExternalSyntheticLambda16(), new MaterialDynamicColors$$ExternalSyntheticLambda17());
    }

    public DynamicColor controlHighlight() {
        return new DynamicColor("control_highlight", new MaterialDynamicColors$$ExternalSyntheticLambda37(), new MaterialDynamicColors$$ExternalSyntheticLambda38(), false, null, null, null, null, new MaterialDynamicColors$$ExternalSyntheticLambda39());
    }

    public DynamicColor controlNormal() {
        return DynamicColor.fromPalette("control_normal", new MaterialDynamicColors$$ExternalSyntheticLambda14(), new MaterialDynamicColors$$ExternalSyntheticLambda15());
    }

    public DynamicColor error() {
        return new DynamicColor("error", new MaterialDynamicColors$$ExternalSyntheticLambda129(), new MaterialDynamicColors$$ExternalSyntheticLambda130(), true, new MaterialDynamicColors$$ExternalSyntheticLambda9(this), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 7.0d), new MaterialDynamicColors$$ExternalSyntheticLambda131(this));
    }

    public DynamicColor errorContainer() {
        return new DynamicColor("error_container", new MaterialDynamicColors$$ExternalSyntheticLambda145(), new MaterialDynamicColors$$ExternalSyntheticLambda146(), true, new MaterialDynamicColors$$ExternalSyntheticLambda9(this), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), new MaterialDynamicColors$$ExternalSyntheticLambda147(this));
    }

    public DynamicColor highestSurface(DynamicScheme r1) {
        if (r1.isDark == false) goto L7;
        return surfaceBright();
    L7:
        return surfaceDim();
    }

    public DynamicColor inverseOnSurface() {
        return new DynamicColor("inverse_on_surface", new MaterialDynamicColors$$ExternalSyntheticLambda116(), new MaterialDynamicColors$$ExternalSyntheticLambda117(), false, new MaterialDynamicColors$$ExternalSyntheticLambda118(this), null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null);
    }

    public DynamicColor inversePrimary() {
        return new DynamicColor("inverse_primary", new MaterialDynamicColors$$ExternalSyntheticLambda57(), new MaterialDynamicColors$$ExternalSyntheticLambda58(), false, new MaterialDynamicColors$$ExternalSyntheticLambda59(this), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 7.0d), null);
    }

    public DynamicColor inverseSurface() {
        return new DynamicColor("inverse_surface", new MaterialDynamicColors$$ExternalSyntheticLambda138(), new MaterialDynamicColors$$ExternalSyntheticLambda139(), false, null, null, null, null);
    }

    public DynamicColor neutralPaletteKeyColor() {
        return DynamicColor.fromPalette("neutral_palette_key_color", new MaterialDynamicColors$$ExternalSyntheticLambda10(), new MaterialDynamicColors$$ExternalSyntheticLambda11());
    }

    public DynamicColor neutralVariantPaletteKeyColor() {
        return DynamicColor.fromPalette("neutral_variant_palette_key_color", new MaterialDynamicColors$$ExternalSyntheticLambda83(), new MaterialDynamicColors$$ExternalSyntheticLambda84());
    }

    public DynamicColor onBackground() {
        return new DynamicColor("on_background", new MaterialDynamicColors$$ExternalSyntheticLambda47(), new MaterialDynamicColors$$ExternalSyntheticLambda48(), false, new MaterialDynamicColors$$ExternalSyntheticLambda49(this), null, new ContrastCurve(3.0d, 3.0d, 4.5d, 7.0d), null);
    }

    public DynamicColor onError() {
        return new DynamicColor("on_error", new MaterialDynamicColors$$ExternalSyntheticLambda70(), new MaterialDynamicColors$$ExternalSyntheticLambda71(), false, new MaterialDynamicColors$$ExternalSyntheticLambda72(this), null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null);
    }

    public DynamicColor onErrorContainer() {
        return new DynamicColor("on_error_container", new MaterialDynamicColors$$ExternalSyntheticLambda140(), new MaterialDynamicColors$$ExternalSyntheticLambda141(), false, new MaterialDynamicColors$$ExternalSyntheticLambda142(this), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), null);
    }

    public DynamicColor onPrimary() {
        return new DynamicColor("on_primary", new MaterialDynamicColors$$ExternalSyntheticLambda54(), new MaterialDynamicColors$$ExternalSyntheticLambda55(), false, new MaterialDynamicColors$$ExternalSyntheticLambda56(this), null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null);
    }

    public DynamicColor onPrimaryContainer() {
        return new DynamicColor("on_primary_container", new MaterialDynamicColors$$ExternalSyntheticLambda75(), new MaterialDynamicColors$$ExternalSyntheticLambda76(this), false, new MaterialDynamicColors$$ExternalSyntheticLambda77(this), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), null);
    }

    public DynamicColor onPrimaryFixed() {
        return new DynamicColor("on_primary_fixed", new MaterialDynamicColors$$ExternalSyntheticLambda125(), new MaterialDynamicColors$$ExternalSyntheticLambda126(), false, new MaterialDynamicColors$$ExternalSyntheticLambda127(this), new MaterialDynamicColors$$ExternalSyntheticLambda128(this), new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null);
    }

    public DynamicColor onPrimaryFixedVariant() {
        return new DynamicColor("on_primary_fixed_variant", new MaterialDynamicColors$$ExternalSyntheticLambda63(), new MaterialDynamicColors$$ExternalSyntheticLambda64(), false, new MaterialDynamicColors$$ExternalSyntheticLambda65(this), new MaterialDynamicColors$$ExternalSyntheticLambda66(this), new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), null);
    }

    public DynamicColor onSecondary() {
        return new DynamicColor("on_secondary", new MaterialDynamicColors$$ExternalSyntheticLambda100(), new MaterialDynamicColors$$ExternalSyntheticLambda101(), false, new MaterialDynamicColors$$ExternalSyntheticLambda102(this), null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null);
    }

    public DynamicColor onSecondaryContainer() {
        return new DynamicColor("on_secondary_container", new MaterialDynamicColors$$ExternalSyntheticLambda122(), new MaterialDynamicColors$$ExternalSyntheticLambda123(this), false, new MaterialDynamicColors$$ExternalSyntheticLambda124(this), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), null);
    }

    public DynamicColor onSecondaryFixed() {
        return new DynamicColor("on_secondary_fixed", new MaterialDynamicColors$$ExternalSyntheticLambda112(), new MaterialDynamicColors$$ExternalSyntheticLambda113(), false, new MaterialDynamicColors$$ExternalSyntheticLambda114(this), new MaterialDynamicColors$$ExternalSyntheticLambda115(this), new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null);
    }

    public DynamicColor onSecondaryFixedVariant() {
        return new DynamicColor("on_secondary_fixed_variant", new MaterialDynamicColors$$ExternalSyntheticLambda156(), new MaterialDynamicColors$$ExternalSyntheticLambda157(), false, new MaterialDynamicColors$$ExternalSyntheticLambda158(this), new MaterialDynamicColors$$ExternalSyntheticLambda159(this), new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), null);
    }

    public DynamicColor onSurface() {
        return new DynamicColor("on_surface", new MaterialDynamicColors$$ExternalSyntheticLambda7(), new MaterialDynamicColors$$ExternalSyntheticLambda8(), false, new MaterialDynamicColors$$ExternalSyntheticLambda9(this), null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null);
    }

    public DynamicColor onSurfaceVariant() {
        return new DynamicColor("on_surface_variant", new MaterialDynamicColors$$ExternalSyntheticLambda136(), new MaterialDynamicColors$$ExternalSyntheticLambda137(), false, new MaterialDynamicColors$$ExternalSyntheticLambda9(this), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), null);
    }

    public DynamicColor onTertiary() {
        return new DynamicColor("on_tertiary", new MaterialDynamicColors$$ExternalSyntheticLambda4(), new MaterialDynamicColors$$ExternalSyntheticLambda5(), false, new MaterialDynamicColors$$ExternalSyntheticLambda6(this), null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null);
    }

    public DynamicColor onTertiaryContainer() {
        return new DynamicColor("on_tertiary_container", new MaterialDynamicColors$$ExternalSyntheticLambda119(), new MaterialDynamicColors$$ExternalSyntheticLambda120(this), false, new MaterialDynamicColors$$ExternalSyntheticLambda121(this), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), null);
    }

    public DynamicColor onTertiaryFixed() {
        return new DynamicColor("on_tertiary_fixed", new MaterialDynamicColors$$ExternalSyntheticLambda31(), new MaterialDynamicColors$$ExternalSyntheticLambda32(), false, new MaterialDynamicColors$$ExternalSyntheticLambda33(this), new MaterialDynamicColors$$ExternalSyntheticLambda34(this), new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null);
    }

    public DynamicColor onTertiaryFixedVariant() {
        return new DynamicColor("on_tertiary_fixed_variant", new MaterialDynamicColors$$ExternalSyntheticLambda20(), new MaterialDynamicColors$$ExternalSyntheticLambda21(), false, new MaterialDynamicColors$$ExternalSyntheticLambda22(this), new MaterialDynamicColors$$ExternalSyntheticLambda23(this), new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), null);
    }

    public DynamicColor outline() {
        return new DynamicColor("outline", new MaterialDynamicColors$$ExternalSyntheticLambda108(), new MaterialDynamicColors$$ExternalSyntheticLambda109(), false, new MaterialDynamicColors$$ExternalSyntheticLambda9(this), null, new ContrastCurve(1.5d, 3.0d, 4.5d, 7.0d), null);
    }

    public DynamicColor outlineVariant() {
        return new DynamicColor("outline_variant", new MaterialDynamicColors$$ExternalSyntheticLambda50(), new MaterialDynamicColors$$ExternalSyntheticLambda51(), false, new MaterialDynamicColors$$ExternalSyntheticLambda9(this), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), null);
    }

    public DynamicColor primary() {
        return new DynamicColor("primary", new MaterialDynamicColors$$ExternalSyntheticLambda151(), new MaterialDynamicColors$$ExternalSyntheticLambda152(), true, new MaterialDynamicColors$$ExternalSyntheticLambda9(this), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 7.0d), new MaterialDynamicColors$$ExternalSyntheticLambda153(this));
    }

    public DynamicColor primaryContainer() {
        return new DynamicColor("primary_container", new MaterialDynamicColors$$ExternalSyntheticLambda40(), new MaterialDynamicColors$$ExternalSyntheticLambda41(this), true, new MaterialDynamicColors$$ExternalSyntheticLambda9(this), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), new MaterialDynamicColors$$ExternalSyntheticLambda42(this));
    }

    public DynamicColor primaryFixed() {
        return new DynamicColor("primary_fixed", new MaterialDynamicColors$$ExternalSyntheticLambda91(), new MaterialDynamicColors$$ExternalSyntheticLambda92(), true, new MaterialDynamicColors$$ExternalSyntheticLambda9(this), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), new MaterialDynamicColors$$ExternalSyntheticLambda93(this));
    }

    public DynamicColor primaryFixedDim() {
        return new DynamicColor("primary_fixed_dim", new MaterialDynamicColors$$ExternalSyntheticLambda94(), new MaterialDynamicColors$$ExternalSyntheticLambda95(), true, new MaterialDynamicColors$$ExternalSyntheticLambda9(this), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), new MaterialDynamicColors$$ExternalSyntheticLambda96(this));
    }

    public DynamicColor primaryPaletteKeyColor() {
        return DynamicColor.fromPalette("primary_palette_key_color", new MaterialDynamicColors$$ExternalSyntheticLambda18(), new MaterialDynamicColors$$ExternalSyntheticLambda19());
    }

    public DynamicColor scrim() {
        return new DynamicColor("scrim", new MaterialDynamicColors$$ExternalSyntheticLambda154(), new MaterialDynamicColors$$ExternalSyntheticLambda155(), false, null, null, null, null);
    }

    public DynamicColor secondary() {
        return new DynamicColor("secondary", new MaterialDynamicColors$$ExternalSyntheticLambda103(), new MaterialDynamicColors$$ExternalSyntheticLambda104(), true, new MaterialDynamicColors$$ExternalSyntheticLambda9(this), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 7.0d), new MaterialDynamicColors$$ExternalSyntheticLambda105(this));
    }

    public DynamicColor secondaryContainer() {
        return new DynamicColor("secondary_container", new MaterialDynamicColors$$ExternalSyntheticLambda28(), new MaterialDynamicColors$$ExternalSyntheticLambda29(this), true, new MaterialDynamicColors$$ExternalSyntheticLambda9(this), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), new MaterialDynamicColors$$ExternalSyntheticLambda30(this));
    }

    public DynamicColor secondaryFixed() {
        return new DynamicColor("secondary_fixed", new MaterialDynamicColors$$ExternalSyntheticLambda60(), new MaterialDynamicColors$$ExternalSyntheticLambda61(), true, new MaterialDynamicColors$$ExternalSyntheticLambda9(this), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), new MaterialDynamicColors$$ExternalSyntheticLambda62(this));
    }

    public DynamicColor secondaryFixedDim() {
        return new DynamicColor("secondary_fixed_dim", new MaterialDynamicColors$$ExternalSyntheticLambda78(), new MaterialDynamicColors$$ExternalSyntheticLambda79(), true, new MaterialDynamicColors$$ExternalSyntheticLambda9(this), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), new MaterialDynamicColors$$ExternalSyntheticLambda80(this));
    }

    public DynamicColor secondaryPaletteKeyColor() {
        return DynamicColor.fromPalette("secondary_palette_key_color", new MaterialDynamicColors$$ExternalSyntheticLambda24(), new MaterialDynamicColors$$ExternalSyntheticLambda25());
    }

    public DynamicColor shadow() {
        return new DynamicColor("shadow", new MaterialDynamicColors$$ExternalSyntheticLambda87(), new MaterialDynamicColors$$ExternalSyntheticLambda88(), false, null, null, null, null);
    }

    public DynamicColor surface() {
        return new DynamicColor("surface", new MaterialDynamicColors$$ExternalSyntheticLambda0(), new MaterialDynamicColors$$ExternalSyntheticLambda1(), true, null, null, null, null);
    }

    public DynamicColor surfaceBright() {
        return new DynamicColor("surface_bright", new MaterialDynamicColors$$ExternalSyntheticLambda52(), new MaterialDynamicColors$$ExternalSyntheticLambda53(), true, null, null, null, null);
    }

    public DynamicColor surfaceContainer() {
        return new DynamicColor("surface_container", new MaterialDynamicColors$$ExternalSyntheticLambda134(), new MaterialDynamicColors$$ExternalSyntheticLambda135(), true, null, null, null, null);
    }

    public DynamicColor surfaceContainerHigh() {
        return new DynamicColor("surface_container_high", new MaterialDynamicColors$$ExternalSyntheticLambda45(), new MaterialDynamicColors$$ExternalSyntheticLambda46(), true, null, null, null, null);
    }

    public DynamicColor surfaceContainerHighest() {
        return new DynamicColor("surface_container_highest", new MaterialDynamicColors$$ExternalSyntheticLambda89(), new MaterialDynamicColors$$ExternalSyntheticLambda90(), true, null, null, null, null);
    }

    public DynamicColor surfaceContainerLow() {
        return new DynamicColor("surface_container_low", new MaterialDynamicColors$$ExternalSyntheticLambda26(), new MaterialDynamicColors$$ExternalSyntheticLambda27(), true, null, null, null, null);
    }

    public DynamicColor surfaceContainerLowest() {
        return new DynamicColor("surface_container_lowest", new MaterialDynamicColors$$ExternalSyntheticLambda106(), new MaterialDynamicColors$$ExternalSyntheticLambda107(), true, null, null, null, null);
    }

    public DynamicColor surfaceDim() {
        return new DynamicColor("surface_dim", new MaterialDynamicColors$$ExternalSyntheticLambda12(), new MaterialDynamicColors$$ExternalSyntheticLambda13(), true, null, null, null, null);
    }

    public DynamicColor surfaceTint() {
        return new DynamicColor("surface_tint", new MaterialDynamicColors$$ExternalSyntheticLambda110(), new MaterialDynamicColors$$ExternalSyntheticLambda111(), true, null, null, null, null);
    }

    public DynamicColor surfaceVariant() {
        return new DynamicColor("surface_variant", new MaterialDynamicColors$$ExternalSyntheticLambda81(), new MaterialDynamicColors$$ExternalSyntheticLambda82(), true, null, null, null, null);
    }

    public DynamicColor tertiary() {
        return new DynamicColor("tertiary", new MaterialDynamicColors$$ExternalSyntheticLambda160(), new MaterialDynamicColors$$ExternalSyntheticLambda161(), true, new MaterialDynamicColors$$ExternalSyntheticLambda9(this), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 7.0d), new MaterialDynamicColors$$ExternalSyntheticLambda162(this));
    }

    public DynamicColor tertiaryContainer() {
        return new DynamicColor("tertiary_container", new MaterialDynamicColors$$ExternalSyntheticLambda97(), new MaterialDynamicColors$$ExternalSyntheticLambda98(this), true, new MaterialDynamicColors$$ExternalSyntheticLambda9(this), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), new MaterialDynamicColors$$ExternalSyntheticLambda99(this));
    }

    public DynamicColor tertiaryFixed() {
        return new DynamicColor("tertiary_fixed", new MaterialDynamicColors$$ExternalSyntheticLambda148(), new MaterialDynamicColors$$ExternalSyntheticLambda149(), true, new MaterialDynamicColors$$ExternalSyntheticLambda9(this), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), new MaterialDynamicColors$$ExternalSyntheticLambda150(this));
    }

    public DynamicColor tertiaryFixedDim() {
        return new DynamicColor("tertiary_fixed_dim", new MaterialDynamicColors$$ExternalSyntheticLambda67(), new MaterialDynamicColors$$ExternalSyntheticLambda68(), true, new MaterialDynamicColors$$ExternalSyntheticLambda9(this), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), new MaterialDynamicColors$$ExternalSyntheticLambda69(this));
    }

    public DynamicColor tertiaryPaletteKeyColor() {
        return DynamicColor.fromPalette("tertiary_palette_key_color", new MaterialDynamicColors$$ExternalSyntheticLambda35(), new MaterialDynamicColors$$ExternalSyntheticLambda36());
    }

    public DynamicColor textHintInverse() {
        return DynamicColor.fromPalette("text_hint_inverse", new MaterialDynamicColors$$ExternalSyntheticLambda85(), new MaterialDynamicColors$$ExternalSyntheticLambda86());
    }

    public DynamicColor textPrimaryInverse() {
        return DynamicColor.fromPalette("text_primary_inverse", new MaterialDynamicColors$$ExternalSyntheticLambda132(), new MaterialDynamicColors$$ExternalSyntheticLambda133());
    }

    public DynamicColor textPrimaryInverseDisableOnly() {
        return DynamicColor.fromPalette("text_primary_inverse_disable_only", new MaterialDynamicColors$$ExternalSyntheticLambda73(), new MaterialDynamicColors$$ExternalSyntheticLambda74());
    }

    public DynamicColor textSecondaryAndTertiaryInverse() {
        return DynamicColor.fromPalette("text_secondary_and_tertiary_inverse", new MaterialDynamicColors$$ExternalSyntheticLambda143(), new MaterialDynamicColors$$ExternalSyntheticLambda144());
    }

    public DynamicColor textSecondaryAndTertiaryInverseDisabled() {
        return DynamicColor.fromPalette("text_secondary_and_tertiary_inverse_disabled", new MaterialDynamicColors$$ExternalSyntheticLambda2(), new MaterialDynamicColors$$ExternalSyntheticLambda3());
    }
}
