.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzd;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zza;
.source "SourceFile"


# static fields
.field static final zza:Lsun/misc/Unsafe;

.field static final zzb:J

.field static final zzc:J

.field static final zzd:J

.field static final zze:J

.field static final zzf:J


# direct methods
.method static constructor <clinit>()V
    .locals 5

    .line 1
    const-class v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;

    :try_start_0
    invoke-static {}, Lsun/misc/Unsafe;->getUnsafe()Lsun/misc/Unsafe;

    move-result-object v1
    :try_end_0
    .catch Ljava/lang/SecurityException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 12
    :catch_0
    :try_start_1
    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoe;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoe;
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_3

    :try_start_2
    const-string v2, "java.security.AccessController"

    .line 2
    invoke-static {v2}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v2

    const-string v3, "doPrivileged"

    const-class v4, Ljava/security/PrivilegedExceptionAction;

    filled-new-array {v4}, [Ljava/lang/Class;

    move-result-object v4

    .line 3
    invoke-virtual {v2, v3, v4}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v2

    filled-new-array {v1}, [Ljava/lang/Object;

    move-result-object v1

    const/4 v3, 0x0

    .line 4
    invoke-virtual {v2, v3, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lsun/misc/Unsafe;
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_1

    goto :goto_0

    .line 5
    :catch_1
    :try_start_3
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzd;->zzi()Lsun/misc/Unsafe;

    move-result-object v1

    .line 6
    move-object v2, v1

    check-cast v2, Lsun/misc/Unsafe;
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_3

    .line 1
    :goto_0
    :try_start_4
    const-class v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;

    const-string v3, "waitersField"

    .line 8
    invoke-virtual {v2, v3}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v3

    invoke-virtual {v1, v3}, Lsun/misc/Unsafe;->objectFieldOffset(Ljava/lang/reflect/Field;)J

    move-result-wide v3

    sput-wide v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzd;->zzc:J

    const-string v3, "listenersField"

    .line 9
    invoke-virtual {v2, v3}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v3

    invoke-virtual {v1, v3}, Lsun/misc/Unsafe;->objectFieldOffset(Ljava/lang/reflect/Field;)J

    move-result-wide v3

    sput-wide v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzd;->zzb:J

    const-string v3, "valueField"

    .line 10
    invoke-virtual {v2, v3}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v2

    invoke-virtual {v1, v2}, Lsun/misc/Unsafe;->objectFieldOffset(Ljava/lang/reflect/Field;)J

    move-result-wide v2

    sput-wide v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzd;->zzd:J

    const-string v2, "thread"

    .line 11
    invoke-virtual {v0, v2}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v2

    invoke-virtual {v1, v2}, Lsun/misc/Unsafe;->objectFieldOffset(Ljava/lang/reflect/Field;)J

    move-result-wide v2

    sput-wide v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzd;->zze:J

    const-string v2, "next"

    .line 12
    invoke-virtual {v0, v2}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v0

    invoke-virtual {v1, v0}, Lsun/misc/Unsafe;->objectFieldOffset(Ljava/lang/reflect/Field;)J

    move-result-wide v2

    sput-wide v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzd;->zzf:J

    sput-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzd;->zza:Lsun/misc/Unsafe;
    :try_end_4
    .catch Ljava/lang/NoSuchFieldException; {:try_start_4 .. :try_end_4} :catch_2

    return-void

    :catch_2
    move-exception v0

    .line 13
    invoke-static {v0}, Lokhttp3/HttpUrl$$ExternalSyntheticBUOutline0;->m(Ljava/lang/Throwable;)V

    return-void

    :catch_3
    move-exception v0

    const-string v1, "Could not initialize intrinsics"

    .line 7
    invoke-static {v1, v0}, Lorg/mvel2/util/Make$Map$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method

.method public synthetic constructor <init>([B)V
    .locals 0

    const/4 p1, 0x0

    invoke-direct {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zza;-><init>([B)V

    return-void
.end method

.method public static synthetic zzh()Lsun/misc/Unsafe;
    .locals 1

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzd;->zzi()Lsun/misc/Unsafe;

    move-result-object v0

    return-object v0
.end method

.method private static synthetic zzi()Lsun/misc/Unsafe;
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

    const-string v1, "the Unsafe"

    .line 6
    invoke-direct {v0, v1}, Ljava/lang/NoSuchFieldError;-><init>(Ljava/lang/String;)V

    throw v0
.end method


# virtual methods
.method public final zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;Ljava/lang/Thread;)V
    .locals 2

    .line 1
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzd;->zza:Lsun/misc/Unsafe;

    sget-wide v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzd;->zze:J

    invoke-virtual {p0, p1, v0, v1, p2}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    return-void
.end method

.method public final zzb(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;)V
    .locals 2

    .line 1
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzd;->zza:Lsun/misc/Unsafe;

    sget-wide v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzd;->zzf:J

    invoke-virtual {p0, p1, v0, v1, p2}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    return-void
.end method

.method public final zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;)Z
    .locals 6

    .line 1
    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzd;->zza:Lsun/misc/Unsafe;

    sget-wide v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzd;->zzc:J

    move-object v1, p1

    move-object v4, p2

    move-object v5, p3

    invoke-static/range {v0 .. v5}, Lcom/google/android/gms/internal/cast/zzwb$zzd$$ExternalSyntheticBackportWithForwarding0;->m(Lsun/misc/Unsafe;Ljava/lang/Object;JLjava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public final zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc$zzd;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc$zzd;)Z
    .locals 6

    .line 1
    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzd;->zza:Lsun/misc/Unsafe;

    sget-wide v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzd;->zzb:J

    move-object v1, p1

    move-object v4, p2

    move-object v5, p3

    invoke-static/range {v0 .. v5}, Lcom/google/android/gms/internal/cast/zzwb$zzd$$ExternalSyntheticBackportWithForwarding0;->m(Lsun/misc/Unsafe;Ljava/lang/Object;JLjava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public final zze(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;
    .locals 2

    .line 1
    :cond_0
    iget-object v0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;->waitersField:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;

    if-ne p2, v0, :cond_1

    goto :goto_0

    :cond_1
    invoke-virtual {p0, p1, v0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzd;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zze;)Z

    move-result v1

    if-eqz v1, :cond_0

    :goto_0
    return-object v0
.end method

.method public final zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc$zzd;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc$zzd;
    .locals 2

    .line 1
    :cond_0
    iget-object v0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;->listenersField:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc$zzd;

    if-ne p2, v0, :cond_1

    goto :goto_0

    :cond_1
    invoke-virtual {p0, p1, v0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzd;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc$zzd;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzoc$zzd;)Z

    move-result v1

    if-eqz v1, :cond_0

    :goto_0
    return-object v0
.end method

.method public final zzg(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod;Ljava/lang/Object;Ljava/lang/Object;)Z
    .locals 6

    .line 1
    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzd;->zza:Lsun/misc/Unsafe;

    sget-wide v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzod$zzd;->zzd:J

    move-object v1, p1

    move-object v4, p2

    move-object v5, p3

    invoke-static/range {v0 .. v5}, Lcom/google/android/gms/internal/cast/zzwb$zzd$$ExternalSyntheticBackportWithForwarding0;->m(Lsun/misc/Unsafe;Ljava/lang/Object;JLjava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method
