.class final enum Lcom/google/android/recaptcha/internal/zzrm;
.super Ljava/lang/Enum;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzrk;


# static fields
.field public static final enum zza:Lcom/google/android/recaptcha/internal/zzrm;

.field public static final enum zzb:Lcom/google/android/recaptcha/internal/zzrm;

.field private static final zzc:Lsun/misc/Unsafe;

.field private static final zzd:I

.field private static final synthetic zze:[Lcom/google/android/recaptcha/internal/zzrm;


# direct methods
.method static constructor <clinit>()V
    .locals 5

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzrm;

    const-string v1, "UNSAFE_LITTLE_ENDIAN"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzrm;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzrm;->zza:Lcom/google/android/recaptcha/internal/zzrm;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzrm;

    .line 2
    const-string v2, "UNSAFE_BIG_ENDIAN"

    const/4 v3, 0x1

    invoke-direct {v1, v2, v3}, Lcom/google/android/recaptcha/internal/zzrm;-><init>(Ljava/lang/String;I)V

    sput-object v1, Lcom/google/android/recaptcha/internal/zzrm;->zzb:Lcom/google/android/recaptcha/internal/zzrm;

    filled-new-array {v0, v1}, [Lcom/google/android/recaptcha/internal/zzrm;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzrm;->zze:[Lcom/google/android/recaptcha/internal/zzrm;

    .line 3
    :try_start_0
    invoke-static {}, Lsun/misc/Unsafe;->getUnsafe()Lsun/misc/Unsafe;

    move-result-object v0
    :try_end_0
    .catch Ljava/lang/SecurityException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 4
    :catch_0
    :try_start_1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzrl;->zza:Lcom/google/android/recaptcha/internal/zzrl;
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_2

    :try_start_2
    const-string v1, "java.security.AccessController"

    .line 5
    invoke-static {v1}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v1

    const-string v2, "doPrivileged"

    const-class v4, Ljava/security/PrivilegedExceptionAction;

    filled-new-array {v4}, [Ljava/lang/Class;

    move-result-object v4

    .line 6
    invoke-virtual {v1, v2, v4}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v1

    filled-new-array {v0}, [Ljava/lang/Object;

    move-result-object v0

    const/4 v2, 0x0

    .line 7
    invoke-virtual {v1, v2, v0}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lsun/misc/Unsafe;
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_1

    goto :goto_0

    .line 8
    :catch_1
    :try_start_3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzrm;->zzc()Lsun/misc/Unsafe;

    move-result-object v0

    .line 9
    move-object v1, v0

    check-cast v1, Lsun/misc/Unsafe;
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_2

    .line 3
    :goto_0
    sput-object v0, Lcom/google/android/recaptcha/internal/zzrm;->zzc:Lsun/misc/Unsafe;

    .line 11
    const-class v1, [B

    invoke-virtual {v0, v1}, Lsun/misc/Unsafe;->arrayBaseOffset(Ljava/lang/Class;)I

    move-result v2

    sput v2, Lcom/google/android/recaptcha/internal/zzrm;->zzd:I

    .line 12
    invoke-virtual {v0, v1}, Lsun/misc/Unsafe;->arrayIndexScale(Ljava/lang/Class;)I

    move-result v0

    if-ne v0, v3, :cond_0

    return-void

    .line 13
    :cond_0
    invoke-static {}, Lokio/AsyncTimeout$$ExternalSyntheticBUOutline0;->m()V

    return-void

    :catch_2
    move-exception v0

    .line 7
    const-string v1, "Could not initialize intrinsics"

    .line 10
    invoke-static {v1, v0}, Lorg/mvel2/util/Make$Map$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0

    .line 1
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static values()[Lcom/google/android/recaptcha/internal/zzrm;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzrm;->zze:[Lcom/google/android/recaptcha/internal/zzrm;

    invoke-virtual {v0}, [Lcom/google/android/recaptcha/internal/zzrm;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/google/android/recaptcha/internal/zzrm;

    return-object v0
.end method

.method public static synthetic zzb()Lsun/misc/Unsafe;
    .locals 1

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzrm;->zzc()Lsun/misc/Unsafe;

    move-result-object v0

    return-object v0
.end method

.method private static synthetic zzc()Lsun/misc/Unsafe;
    .locals 6

    .line 1
    const-class v0, Lsun/misc/Unsafe;

    invoke-virtual {v0}, Ljava/lang/Class;->getDeclaredFields()[Ljava/lang/reflect/Field;

    move-result-object v1

    array-length v2, v1

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v2, :cond_1

    aget-object v4, v1, v3

    const/4 v5, 0x1

    .line 2
    invoke-virtual {v4, v5}, Ljava/lang/reflect/AccessibleObject;->setAccessible(Z)V

    const/4 v5, 0x0

    .line 3
    invoke-virtual {v4, v5}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    .line 4
    invoke-virtual {v0, v4}, Ljava/lang/Class;->isInstance(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_0

    .line 5
    invoke-virtual {v0, v4}, Ljava/lang/Class;->cast(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lsun/misc/Unsafe;

    return-object v0

    :cond_0
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_1
    new-instance v0, Ljava/lang/NoSuchFieldError;

    const-string/jumbo v1, "the Unsafe"

    .line 6
    invoke-direct {v0, v1}, Ljava/lang/NoSuchFieldError;-><init>(Ljava/lang/String;)V

    throw v0
.end method


# virtual methods
.method public final synthetic zza([BI)J
    .locals 4

    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    move-result p0

    if-eqz p0, :cond_1

    const/4 v0, 0x1

    if-ne p0, v0, :cond_0

    int-to-long v0, p2

    .line 1
    sget-object p0, Lcom/google/android/recaptcha/internal/zzrm;->zzc:Lsun/misc/Unsafe;

    sget p2, Lcom/google/android/recaptcha/internal/zzrm;->zzd:I

    int-to-long v2, p2

    add-long/2addr v0, v2

    invoke-virtual {p0, p1, v0, v1}, Lsun/misc/Unsafe;->getLong(Ljava/lang/Object;J)J

    move-result-wide p0

    .line 2
    invoke-static {p0, p1}, Ljava/lang/Long;->reverseBytes(J)J

    move-result-wide p0

    return-wide p0

    :cond_0
    const/4 p0, 0x0

    .line 0
    throw p0

    :cond_1
    int-to-long v0, p2

    .line 2
    sget-object p0, Lcom/google/android/recaptcha/internal/zzrm;->zzc:Lsun/misc/Unsafe;

    sget p2, Lcom/google/android/recaptcha/internal/zzrm;->zzd:I

    int-to-long v2, p2

    add-long/2addr v0, v2

    .line 3
    invoke-virtual {p0, p1, v0, v1}, Lsun/misc/Unsafe;->getLong(Ljava/lang/Object;J)J

    move-result-wide p0

    return-wide p0
.end method
