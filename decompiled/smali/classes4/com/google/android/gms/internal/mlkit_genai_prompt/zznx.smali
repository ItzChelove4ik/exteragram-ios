.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zznx;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method static constructor <clinit>()V
    .locals 2

    const-wide/high16 v0, 0x4000000000000000L    # 2.0

    .line 1
    invoke-static {v0, v1}, Ljava/lang/Math;->log(D)D

    return-void
.end method

.method public static zza(DLjava/math/RoundingMode;)J
    .locals 8

    .line 1
    invoke-static {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzny;->zza(D)Z

    move-result v0

    if-eqz v0, :cond_a

    .line 3
    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznw;->zza:[I

    invoke-virtual {p2}, Ljava/lang/Enum;->ordinal()I

    move-result v1

    aget v0, v0, v1

    const/4 v1, 0x1

    const-wide/16 v2, 0x0

    const-wide/high16 v4, 0x3fe0000000000000L    # 0.5

    packed-switch v0, :pswitch_data_0

    .line 16
    invoke-static {}, Lokio/AsyncTimeout$$ExternalSyntheticBUOutline0;->m()V

    const-wide/16 p0, 0x0

    return-wide p0

    .line 4
    :pswitch_0
    invoke-static {p0, p1}, Ljava/lang/Math;->rint(D)D

    move-result-wide v2

    sub-double v6, p0, v2

    .line 5
    invoke-static {v6, v7}, Ljava/lang/Math;->abs(D)D

    move-result-wide v6

    cmpl-double v0, v6, v4

    if-nez v0, :cond_5

    goto :goto_2

    .line 6
    :pswitch_1
    invoke-static {p0, p1}, Ljava/lang/Math;->rint(D)D

    move-result-wide v2

    sub-double v6, p0, v2

    .line 7
    invoke-static {v6, v7}, Ljava/lang/Math;->abs(D)D

    move-result-wide v6

    cmpl-double v0, v6, v4

    if-nez v0, :cond_5

    .line 8
    invoke-static {v4, v5, p0, p1}, Ljava/lang/Math;->copySign(DD)D

    move-result-wide v2

    add-double/2addr v2, p0

    goto :goto_3

    .line 9
    :pswitch_2
    invoke-static {p0, p1}, Ljava/lang/Math;->rint(D)D

    move-result-wide v2

    goto :goto_3

    .line 10
    :pswitch_3
    invoke-static {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznx;->zzb(D)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_2

    :cond_0
    double-to-long v4, p0

    cmpl-double v0, p0, v2

    if-lez v0, :cond_1

    move v0, v1

    goto :goto_0

    :cond_1
    const/4 v0, -0x1

    :goto_0
    int-to-long v2, v0

    add-long/2addr v4, v2

    long-to-double v2, v4

    goto :goto_3

    :pswitch_4
    cmpl-double v0, p0, v2

    if-lez v0, :cond_4

    .line 11
    invoke-static {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznx;->zzb(D)Z

    move-result v0

    if-eqz v0, :cond_2

    goto :goto_2

    :cond_2
    double-to-long v2, p0

    const-wide/16 v4, 0x1

    :goto_1
    add-long/2addr v2, v4

    long-to-double v2, v2

    goto :goto_3

    :pswitch_5
    cmpl-double v0, p0, v2

    if-gez v0, :cond_4

    .line 12
    invoke-static {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznx;->zzb(D)Z

    move-result v0

    if-eqz v0, :cond_3

    goto :goto_2

    :cond_3
    double-to-long v2, p0

    const-wide/16 v4, -0x1

    goto :goto_1

    .line 13
    :pswitch_6
    invoke-static {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznx;->zzb(D)Z

    move-result v0

    if-eqz v0, :cond_9

    :cond_4
    :goto_2
    :pswitch_7
    move-wide v2, p0

    :cond_5
    :goto_3
    const-wide/high16 v4, -0x3c20000000000000L    # -9.223372036854776E18

    sub-double/2addr v4, v2

    const-wide/high16 v6, 0x3ff0000000000000L    # 1.0

    cmpg-double v0, v4, v6

    const/4 v4, 0x0

    if-gez v0, :cond_6

    move v0, v1

    goto :goto_4

    :cond_6
    move v0, v4

    :goto_4
    const-wide/high16 v5, 0x43e0000000000000L    # 9.223372036854776E18

    cmpg-double v5, v2, v5

    if-gez v5, :cond_7

    goto :goto_5

    :cond_7
    move v1, v4

    :goto_5
    and-int/2addr v0, v1

    if-eqz v0, :cond_8

    double-to-long p0, v2

    return-wide p0

    .line 5
    :cond_8
    new-instance v0, Ljava/lang/ArithmeticException;

    .line 14
    invoke-static {p2}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    invoke-static {p0, p1}, Ljava/lang/String;->valueOf(D)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v1

    add-int/lit8 v1, v1, 0x3b

    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result v2

    add-int/2addr v1, v2

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v1, "rounded value is out of range for input "

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p0, p1}, Ljava/lang/StringBuilder;->append(D)Ljava/lang/StringBuilder;

    const-string p0, " and rounding mode "

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/lang/ArithmeticException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 13
    :cond_9
    new-instance p0, Ljava/lang/ArithmeticException;

    const-string p1, "mode was UNNECESSARY, but rounding was necessary"

    .line 15
    invoke-direct {p0, p1}, Ljava/lang/ArithmeticException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 1
    :cond_a
    new-instance p0, Ljava/lang/ArithmeticException;

    const-string p1, "input is infinite or NaN"

    .line 2
    invoke-direct {p0, p1}, Ljava/lang/ArithmeticException;-><init>(Ljava/lang/String;)V

    throw p0

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_7
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public static zzb(D)Z
    .locals 7

    .line 1
    invoke-static {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzny;->zza(D)Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_2

    const-wide/16 v2, 0x0

    cmpl-double v0, p0, v2

    const/4 v2, 0x1

    if-eqz v0, :cond_1

    .line 2
    invoke-static {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzny;->zza(D)Z

    move-result v0

    const-string v3, "not a normal value"

    invoke-static {v0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzla;->zza(ZLjava/lang/Object;)V

    .line 3
    invoke-static {p0, p1}, Ljava/lang/Math;->getExponent(D)I

    move-result v0

    .line 4
    invoke-static {p0, p1}, Ljava/lang/Double;->doubleToRawLongBits(D)J

    move-result-wide v3

    const-wide v5, 0xfffffffffffffL

    and-long/2addr v3, v5

    const/16 v5, -0x3ff

    if-ne v0, v5, :cond_0

    add-long/2addr v3, v3

    goto :goto_0

    :cond_0
    const-wide/high16 v5, 0x10000000000000L

    or-long/2addr v3, v5

    .line 5
    :goto_0
    invoke-static {v3, v4}, Ljava/lang/Long;->numberOfTrailingZeros(J)I

    move-result v0

    rsub-int/lit8 v0, v0, 0x34

    invoke-static {p0, p1}, Ljava/lang/Math;->getExponent(D)I

    move-result p0

    if-le v0, p0, :cond_1

    return v1

    :cond_1
    return v2

    :cond_2
    return v1
.end method
