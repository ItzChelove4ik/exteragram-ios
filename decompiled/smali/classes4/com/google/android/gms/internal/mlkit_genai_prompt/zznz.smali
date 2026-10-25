.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zznz;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field static final zza:Lj$/time/Duration;

.field public static final zzb:Lj$/time/Duration;


# direct methods
.method static constructor <clinit>()V
    .locals 6

    const-wide/high16 v0, -0x8000000000000000L

    .line 1
    invoke-static {v0, v1}, Lj$/time/Duration;->ofSeconds(J)Lj$/time/Duration;

    move-result-object v2

    sput-object v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznz;->zza:Lj$/time/Duration;

    const-wide/32 v2, 0x3b9ac9ff

    const-wide v4, 0x7fffffffffffffffL

    .line 2
    invoke-static {v4, v5, v2, v3}, Lj$/time/Duration;->ofSeconds(JJ)Lj$/time/Duration;

    move-result-object v2

    sput-object v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznz;->zzb:Lj$/time/Duration;

    .line 3
    invoke-static {v4, v5}, Lj$/time/Duration;->ofMillis(J)Lj$/time/Duration;

    .line 4
    invoke-static {v0, v1}, Lj$/time/Duration;->ofMillis(J)Lj$/time/Duration;

    .line 5
    sget-object v2, Lj$/time/temporal/ChronoUnit;->MICROS:Lj$/time/temporal/ChronoUnit;

    invoke-static {v4, v5, v2}, Lj$/time/Duration;->of(JLj$/time/temporal/TemporalUnit;)Lj$/time/Duration;

    .line 6
    invoke-static {v0, v1, v2}, Lj$/time/Duration;->of(JLj$/time/temporal/TemporalUnit;)Lj$/time/Duration;

    .line 7
    invoke-static {v4, v5}, Lj$/time/Duration;->ofNanos(J)Lj$/time/Duration;

    .line 8
    invoke-static {v0, v1}, Lj$/time/Duration;->ofNanos(J)Lj$/time/Duration;

    const-wide/high16 v0, 0x43e0000000000000L    # 9.223372036854776E18

    .line 9
    invoke-static {v0, v1}, Ljava/math/BigDecimal;->valueOf(D)Ljava/math/BigDecimal;

    const-wide/high16 v0, -0x3c20000000000000L    # -9.223372036854776E18

    .line 10
    invoke-static {v0, v1}, Ljava/math/BigDecimal;->valueOf(D)Ljava/math/BigDecimal;

    .line 11
    invoke-static {v4, v5}, Lj$/time/Duration;->ofMillis(J)Lj$/time/Duration;

    move-result-object v0

    const-wide/32 v1, 0xf423f

    invoke-virtual {v0, v1, v2}, Lj$/time/Duration;->plusNanos(J)Lj$/time/Duration;

    return-void
.end method

.method public static zza(D)Lj$/time/Duration;
    .locals 5

    const-wide/high16 v0, 0x43e0000000000000L    # 9.223372036854776E18

    cmpl-double v0, p0, v0

    if-ltz v0, :cond_0

    .line 1
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznz;->zzb:Lj$/time/Duration;

    return-object p0

    :cond_0
    const-wide/high16 v0, -0x3c20000000000000L    # -9.223372036854776E18

    cmpg-double v0, p0, v0

    if-gtz v0, :cond_1

    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznz;->zza:Lj$/time/Duration;

    return-object p0

    :cond_1
    sget-object v0, Ljava/math/RoundingMode;->FLOOR:Ljava/math/RoundingMode;

    invoke-static {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznx;->zza(DLjava/math/RoundingMode;)J

    move-result-wide v1

    long-to-double v3, v1

    sub-double/2addr p0, v3

    const-wide v3, 0x41cdcd6500000000L    # 1.0E9

    mul-double/2addr p0, v3

    .line 2
    invoke-static {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zznx;->zza(DLjava/math/RoundingMode;)J

    move-result-wide p0

    .line 3
    invoke-static {v1, v2, p0, p1}, Lj$/time/Duration;->ofSeconds(JJ)Lj$/time/Duration;

    move-result-object p0

    return-object p0
.end method
