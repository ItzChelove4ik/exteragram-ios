.class public final Lcom/google/android/recaptcha/internal/zzos;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private zza:Z

.field private zzb:J

.field private zzc:J


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    sget p0, Lcom/google/android/recaptcha/internal/zzov;->$r8$clinit:I

    return-void
.end method

.method public static zza()Lcom/google/android/recaptcha/internal/zzos;
    .locals 4

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzos;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzos;-><init>()V

    iget-boolean v1, v0, Lcom/google/android/recaptcha/internal/zzos;->zza:Z

    const/4 v2, 0x1

    xor-int/2addr v1, v2

    const-string v3, "This stopwatch is already running."

    .line 2
    invoke-static {v1, v3}, Lcom/google/android/recaptcha/internal/zzoq;->zzc(ZLjava/lang/Object;)V

    iput-boolean v2, v0, Lcom/google/android/recaptcha/internal/zzos;->zza:Z

    .line 3
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v1

    iput-wide v1, v0, Lcom/google/android/recaptcha/internal/zzos;->zzc:J

    return-object v0
.end method

.method private final zzd()J
    .locals 4

    .line 1
    iget-boolean v0, p0, Lcom/google/android/recaptcha/internal/zzos;->zza:Z

    if-eqz v0, :cond_0

    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v0

    iget-wide v2, p0, Lcom/google/android/recaptcha/internal/zzos;->zzc:J

    sub-long/2addr v0, v2

    iget-wide v2, p0, Lcom/google/android/recaptcha/internal/zzos;->zzb:J

    add-long/2addr v0, v2

    return-wide v0

    :cond_0
    iget-wide v0, p0, Lcom/google/android/recaptcha/internal/zzos;->zzb:J

    return-wide v0
.end method


# virtual methods
.method public final toString()Ljava/lang/String;
    .locals 8

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzos;->zzd()J

    move-result-wide v0

    const-wide v2, 0x4e94914f0000L

    .line 2
    div-long v2, v0, v2

    const-wide/16 v4, 0x0

    cmp-long p0, v2, v4

    sget-object v2, Ljava/util/concurrent/TimeUnit;->NANOSECONDS:Ljava/util/concurrent/TimeUnit;

    if-lez p0, :cond_0

    sget-object p0, Ljava/util/concurrent/TimeUnit;->DAYS:Ljava/util/concurrent/TimeUnit;

    goto :goto_0

    :cond_0
    const-wide v6, 0x34630b8a000L

    .line 3
    div-long v6, v0, v6

    cmp-long p0, v6, v4

    if-lez p0, :cond_1

    sget-object p0, Ljava/util/concurrent/TimeUnit;->HOURS:Ljava/util/concurrent/TimeUnit;

    goto :goto_0

    :cond_1
    const-wide v6, 0xdf8475800L

    .line 4
    div-long v6, v0, v6

    cmp-long p0, v6, v4

    if-lez p0, :cond_2

    sget-object p0, Ljava/util/concurrent/TimeUnit;->MINUTES:Ljava/util/concurrent/TimeUnit;

    goto :goto_0

    :cond_2
    const-wide/32 v6, 0x3b9aca00

    .line 5
    div-long v6, v0, v6

    cmp-long p0, v6, v4

    if-lez p0, :cond_3

    sget-object p0, Ljava/util/concurrent/TimeUnit;->SECONDS:Ljava/util/concurrent/TimeUnit;

    goto :goto_0

    :cond_3
    const-wide/32 v6, 0xf4240

    .line 6
    div-long v6, v0, v6

    cmp-long p0, v6, v4

    if-lez p0, :cond_4

    sget-object p0, Ljava/util/concurrent/TimeUnit;->MILLISECONDS:Ljava/util/concurrent/TimeUnit;

    goto :goto_0

    :cond_4
    const-wide/16 v6, 0x3e8

    .line 7
    div-long v6, v0, v6

    cmp-long p0, v6, v4

    if-lez p0, :cond_5

    sget-object p0, Ljava/util/concurrent/TimeUnit;->MICROSECONDS:Ljava/util/concurrent/TimeUnit;

    goto :goto_0

    :cond_5
    move-object p0, v2

    :goto_0
    long-to-double v0, v0

    const-wide/16 v3, 0x1

    .line 8
    invoke-virtual {v2, v3, v4, p0}, Ljava/util/concurrent/TimeUnit;->convert(JLjava/util/concurrent/TimeUnit;)J

    move-result-wide v2

    long-to-double v2, v2

    sget-object v4, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    div-double/2addr v0, v2

    .line 9
    invoke-static {v0, v1}, Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;

    move-result-object v0

    filled-new-array {v0}, [Ljava/lang/Object;

    move-result-object v0

    const-string v1, "%.4g"

    invoke-static {v4, v1, v0}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    .line 10
    sget-object v1, Lcom/google/android/recaptcha/internal/zzor;->zza:[I

    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    move-result p0

    aget p0, v1, p0

    packed-switch p0, :pswitch_data_0

    .line 12
    invoke-static {}, Lokio/AsyncTimeout$$ExternalSyntheticBUOutline0;->m()V

    const/4 p0, 0x0

    return-object p0

    .line 11
    :pswitch_0
    const-string p0, "d"

    goto :goto_1

    :pswitch_1
    const-string p0, "h"

    goto :goto_1

    :pswitch_2
    const-string p0, "min"

    goto :goto_1

    :pswitch_3
    const-string p0, "s"

    goto :goto_1

    :pswitch_4
    const-string p0, "ms"

    goto :goto_1

    :pswitch_5
    const-string/jumbo p0, "\u03bcs"

    goto :goto_1

    .line 10
    :pswitch_6
    const-string p0, "ns"

    .line 11
    :goto_1
    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v1

    add-int/lit8 v1, v1, 0x1

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v3

    add-int/2addr v1, v3

    invoke-direct {v2, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, " "

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public final zzb()Lcom/google/android/recaptcha/internal/zzos;
    .locals 6

    .line 1
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v0

    iget-boolean v2, p0, Lcom/google/android/recaptcha/internal/zzos;->zza:Z

    const-string v3, "This stopwatch is already stopped."

    .line 2
    invoke-static {v2, v3}, Lcom/google/android/recaptcha/internal/zzoq;->zzc(ZLjava/lang/Object;)V

    const/4 v2, 0x0

    iput-boolean v2, p0, Lcom/google/android/recaptcha/internal/zzos;->zza:Z

    iget-wide v2, p0, Lcom/google/android/recaptcha/internal/zzos;->zzb:J

    iget-wide v4, p0, Lcom/google/android/recaptcha/internal/zzos;->zzc:J

    sub-long/2addr v0, v4

    add-long/2addr v2, v0

    iput-wide v2, p0, Lcom/google/android/recaptcha/internal/zzos;->zzb:J

    return-object p0
.end method

.method public final zzc(Ljava/util/concurrent/TimeUnit;)J
    .locals 2

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzos;->zzd()J

    move-result-wide v0

    sget-object p0, Ljava/util/concurrent/TimeUnit;->NANOSECONDS:Ljava/util/concurrent/TimeUnit;

    invoke-virtual {p1, v0, v1, p0}, Ljava/util/concurrent/TimeUnit;->convert(JLjava/util/concurrent/TimeUnit;)J

    move-result-wide p0

    return-wide p0
.end method
