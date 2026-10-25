.class public abstract Lcom/google/android/recaptcha/internal/zzaiq;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final synthetic $r8$clinit:I

.field private static final zzb:Ljava/lang/ThreadLocal;


# direct methods
.method static constructor <clinit>()V
    .locals 4

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzahx;->zzc()Lcom/google/android/recaptcha/internal/zzahw;

    move-result-object v0

    const-wide v1, -0xe7791f700L

    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzahw;->zza(J)Lcom/google/android/recaptcha/internal/zzahw;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzahw;->zzb(I)Lcom/google/android/recaptcha/internal/zzahw;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzahx;

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzahx;->zzc()Lcom/google/android/recaptcha/internal/zzahw;

    move-result-object v0

    const-wide v2, 0x3afff4417fL

    invoke-virtual {v0, v2, v3}, Lcom/google/android/recaptcha/internal/zzahw;->zza(J)Lcom/google/android/recaptcha/internal/zzahw;

    const v2, 0x3b9ac9ff

    invoke-virtual {v0, v2}, Lcom/google/android/recaptcha/internal/zzahw;->zzb(I)Lcom/google/android/recaptcha/internal/zzahw;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzahx;

    .line 3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzahx;->zzc()Lcom/google/android/recaptcha/internal/zzahw;

    move-result-object v0

    const-wide/16 v2, 0x0

    invoke-virtual {v0, v2, v3}, Lcom/google/android/recaptcha/internal/zzahw;->zza(J)Lcom/google/android/recaptcha/internal/zzahw;

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzahw;->zzb(I)Lcom/google/android/recaptcha/internal/zzahw;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzahx;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzaip;

    .line 4
    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzaip;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaiq;->zzb:Ljava/lang/ThreadLocal;

    const-string v0, "now"

    .line 5
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzaiq;->zzf(Ljava/lang/String;)Ljava/lang/reflect/Method;

    const-string v0, "getEpochSecond"

    .line 6
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzaiq;->zzf(Ljava/lang/String;)Ljava/lang/reflect/Method;

    const-string v0, "getNano"

    .line 7
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzaiq;->zzf(Ljava/lang/String;)Ljava/lang/reflect/Method;

    return-void
.end method

.method public static zza(Lcom/google/android/recaptcha/internal/zzahx;)Lcom/google/android/recaptcha/internal/zzahx;
    .locals 5

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzahx;->zza()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/google/android/recaptcha/internal/zzaiq;->zze(J)Z

    move-result v2

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzahx;->zzb()I

    move-result v3

    if-eqz v2, :cond_0

    if-ltz v3, :cond_0

    const v2, 0x3b9aca00

    if-ge v3, v2, :cond_0

    return-object p0

    :cond_0
    new-instance p0, Ljava/lang/IllegalArgumentException;

    .line 3
    invoke-static {v0, v1}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v2

    invoke-static {v3}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v4

    add-int/lit16 v2, v2, 0x87

    invoke-virtual {v4}, Ljava/lang/String;->length()I

    move-result v4

    add-int/2addr v2, v4

    add-int/lit8 v2, v2, 0x25

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4, v2}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v2, "Timestamp is not valid. See proto definition for valid values. Seconds ("

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, ") must be in range [-62,135,596,800, +253,402,300,799]. Nanos ("

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, ") must be in range [0, +999,999,999]."

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method public static zzb(Lcom/google/android/recaptcha/internal/zzahx;)Ljava/lang/String;
    .locals 5

    .line 1
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaiq;->zza(Lcom/google/android/recaptcha/internal/zzahx;)Lcom/google/android/recaptcha/internal/zzahx;

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzahx;->zza()J

    move-result-wide v0

    .line 3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzahx;->zzb()I

    move-result p0

    new-instance v2, Ljava/lang/StringBuilder;

    .line 4
    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-wide/16 v3, 0x3e8

    mul-long/2addr v0, v3

    new-instance v3, Ljava/util/Date;

    .line 5
    invoke-direct {v3, v0, v1}, Ljava/util/Date;-><init>(J)V

    sget-object v0, Lcom/google/android/recaptcha/internal/zzaiq;->zzb:Ljava/lang/ThreadLocal;

    .line 6
    invoke-virtual {v0}, Ljava/lang/ThreadLocal;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/text/SimpleDateFormat;

    invoke-virtual {v0, v3}, Ljava/text/DateFormat;->format(Ljava/util/Date;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    if-eqz p0, :cond_2

    const-string v0, "."

    .line 7
    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const v0, 0xf4240

    rem-int v1, p0, v0

    if-nez v1, :cond_0

    sget-object v1, Ljava/util/Locale;->ENGLISH:Ljava/util/Locale;

    div-int/2addr p0, v0

    .line 8
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    const-string v0, "%1$03d"

    invoke-static {v1, v0, p0}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    goto :goto_0

    .line 12
    :cond_0
    rem-int/lit16 v0, p0, 0x3e8

    if-nez v0, :cond_1

    sget-object v0, Ljava/util/Locale;->ENGLISH:Ljava/util/Locale;

    div-int/lit16 p0, p0, 0x3e8

    .line 9
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    const-string v1, "%1$06d"

    invoke-static {v0, v1, p0}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    goto :goto_0

    :cond_1
    sget-object v0, Ljava/util/Locale;->ENGLISH:Ljava/util/Locale;

    .line 10
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    const-string v1, "%1$09d"

    invoke-static {v0, v1, p0}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    .line 11
    :goto_0
    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_2
    const-string p0, "Z"

    .line 12
    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static zzc(J)Lcom/google/android/recaptcha/internal/zzahx;
    .locals 6

    const-wide/16 v0, 0x3e8

    .line 1
    rem-long v2, p0, v0

    const-wide/32 v4, 0xf4240

    mul-long/2addr v2, v4

    div-long/2addr p0, v0

    long-to-int v0, v2

    invoke-static {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzaiq;->zzd(JI)Lcom/google/android/recaptcha/internal/zzahx;

    move-result-object p0

    return-object p0
.end method

.method public static zzd(JI)Lcom/google/android/recaptcha/internal/zzahx;
    .locals 4

    .line 1
    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzaiq;->zze(J)Z

    move-result v0

    if-eqz v0, :cond_3

    const v0, -0x3b9aca00

    const v1, 0x3b9aca00

    if-le p2, v0, :cond_0

    if-lt p2, v1, :cond_1

    :cond_0
    div-int v0, p2, v1

    int-to-long v2, v0

    .line 2
    invoke-static {p0, p1, v2, v3}, Ljava/lang/Math;->addExact(JJ)J

    move-result-wide p0

    rem-int/2addr p2, v1

    :cond_1
    if-gez p2, :cond_2

    add-int/2addr p2, v1

    const-wide/16 v0, 0x1

    .line 3
    invoke-static {p0, p1, v0, v1}, Ljava/lang/Math;->subtractExact(JJ)J

    move-result-wide p0

    .line 4
    :cond_2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzahx;->zzc()Lcom/google/android/recaptcha/internal/zzahw;

    move-result-object v0

    invoke-virtual {v0, p0, p1}, Lcom/google/android/recaptcha/internal/zzahw;->zza(J)Lcom/google/android/recaptcha/internal/zzahw;

    invoke-virtual {v0, p2}, Lcom/google/android/recaptcha/internal/zzahw;->zzb(I)Lcom/google/android/recaptcha/internal/zzahw;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzahx;

    .line 5
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaiq;->zza(Lcom/google/android/recaptcha/internal/zzahx;)Lcom/google/android/recaptcha/internal/zzahx;

    return-object p0

    .line 1
    :cond_3
    new-instance p2, Ljava/lang/IllegalArgumentException;

    invoke-static {p0, p1}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    add-int/lit8 v0, v0, 0x75

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1, v0}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v0, "Timestamp is not valid. Input seconds is too large. Seconds ("

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0, p1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p0, ") must be in range [-62,135,596,800, +253,402,300,799]. "

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {p2, p0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p2
.end method

.method private static zze(J)Z
    .locals 2

    const-wide v0, -0xe7791f700L

    cmp-long v0, p0, v0

    if-ltz v0, :cond_0

    const-wide v0, 0x3afff4417fL

    cmp-long p0, p0, v0

    if-gtz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private static zzf(Ljava/lang/String;)Ljava/lang/reflect/Method;
    .locals 2

    const/4 v0, 0x0

    .line 1
    :try_start_0
    const-string v1, "java.time.Instant"

    invoke-static {v1}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {v1, p0, v0}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    return-object v0
.end method
