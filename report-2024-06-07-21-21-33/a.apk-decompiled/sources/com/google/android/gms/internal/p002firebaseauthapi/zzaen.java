package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.core.text.HtmlCompat;
import androidx.core.view.MotionEventCompat;
import com.google.android.gms.fido.u2f.api.common.RegisterRequest;
import com.google.api.Service;
import com.google.firebase.firestore.index.FirestoreIndexValueWriter;
import com.google.protobuf.DescriptorProtos;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import sun.misc.Unsafe;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzaen<T> implements zzaew<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzafx.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzaek zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final int[] zzk;
    private final int zzl;
    private final int zzm;
    private final zzady zzn;
    private final zzafn zzo;
    private final zzact zzp;
    private final zzaep zzq;
    private final zzaef zzr;

    private zzaen(int[] iArr, Object[] objArr, int i, int i2, zzaek zzaekVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzaep zzaepVar, zzady zzadyVar, zzafn zzafnVar, zzact zzactVar, zzaef zzaefVar, byte[] bArr) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = zzaekVar instanceof zzadf;
        this.zzj = z;
        boolean z3 = false;
        if (zzactVar != null && zzactVar.zzh(zzaekVar)) {
            z3 = true;
        }
        this.zzh = z3;
        this.zzk = iArr2;
        this.zzl = i3;
        this.zzm = i4;
        this.zzq = zzaepVar;
        this.zzn = zzadyVar;
        this.zzo = zzafnVar;
        this.zzp = zzactVar;
        this.zzg = zzaekVar;
        this.zzr = zzaefVar;
    }

    private final int zzA(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    private static int zzB(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzC(int i) {
        return this.zzc[i + 1];
    }

    private static long zzD(Object obj, long j) {
        return ((Long) zzafx.zzf(obj, j)).longValue();
    }

    private final zzadj zzE(int i) {
        int i2 = i / 3;
        return (zzadj) this.zzd[i2 + i2 + 1];
    }

    private final zzaew zzF(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzaew zzaewVar = (zzaew) this.zzd[i3];
        if (zzaewVar != null) {
            return zzaewVar;
        }
        zzaew zzb2 = zzaes.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzG(Object obj, int i, Object obj2, zzafn zzafnVar, Object obj3) {
        int i2 = this.zzc[i];
        Object zzf = zzafx.zzf(obj, zzC(i) & 1048575);
        if (zzf == null || zzE(i) == null) {
            return obj2;
        }
        throw null;
    }

    private final Object zzH(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private final Object zzI(Object obj, int i) {
        zzaew zzF = zzF(i);
        long zzC = zzC(i) & 1048575;
        if (!zzV(obj, i)) {
            return zzF.zze();
        }
        Object object = zzb.getObject(obj, zzC);
        if (zzY(object)) {
            return object;
        }
        Object zze = zzF.zze();
        if (object != null) {
            zzF.zzg(zze, object);
        }
        return zze;
    }

    private final Object zzJ(Object obj, int i, int i2) {
        zzaew zzF = zzF(i2);
        if (!zzZ(obj, i, i2)) {
            return zzF.zze();
        }
        Object object = zzb.getObject(obj, zzC(i2) & 1048575);
        if (zzY(object)) {
            return object;
        }
        Object zze = zzF.zze();
        if (object != null) {
            zzF.zzg(zze, object);
        }
        return zze;
    }

    private static Field zzK(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private static void zzL(Object obj) {
        if (!zzY(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
        }
    }

    private final void zzM(Object obj, Object obj2, int i) {
        if (zzV(obj2, i)) {
            long zzC = zzC(i) & 1048575;
            Unsafe unsafe = zzb;
            Object object = unsafe.getObject(obj2, zzC);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzaew zzF = zzF(i);
            if (!zzV(obj, i)) {
                if (!zzY(object)) {
                    unsafe.putObject(obj, zzC, object);
                } else {
                    Object zze = zzF.zze();
                    zzF.zzg(zze, object);
                    unsafe.putObject(obj, zzC, zze);
                }
                zzP(obj, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, zzC);
            if (!zzY(object2)) {
                Object zze2 = zzF.zze();
                zzF.zzg(zze2, object2);
                unsafe.putObject(obj, zzC, zze2);
                object2 = zze2;
            }
            zzF.zzg(object2, object);
        }
    }

    private final void zzN(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzZ(obj2, i2, i)) {
            long zzC = zzC(i) & 1048575;
            Unsafe unsafe = zzb;
            Object object = unsafe.getObject(obj2, zzC);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzaew zzF = zzF(i);
            if (!zzZ(obj, i2, i)) {
                if (!zzY(object)) {
                    unsafe.putObject(obj, zzC, object);
                } else {
                    Object zze = zzF.zze();
                    zzF.zzg(zze, object);
                    unsafe.putObject(obj, zzC, zze);
                }
                zzQ(obj, i2, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, zzC);
            if (!zzY(object2)) {
                Object zze2 = zzF.zze();
                zzF.zzg(zze2, object2);
                unsafe.putObject(obj, zzC, zze2);
                object2 = zze2;
            }
            zzF.zzg(object2, object);
        }
    }

    private final void zzO(Object obj, int i, zzaev zzaevVar) throws IOException {
        if (zzU(i)) {
            zzafx.zzs(obj, i & 1048575, zzaevVar.zzs());
        } else if (!this.zzi) {
            zzafx.zzs(obj, i & 1048575, zzaevVar.zzp());
        } else {
            zzafx.zzs(obj, i & 1048575, zzaevVar.zzr());
        }
    }

    private final void zzP(Object obj, int i) {
        int zzz = zzz(i);
        long j = 1048575 & zzz;
        if (j == 1048575) {
            return;
        }
        zzafx.zzq(obj, j, (1 << (zzz >>> 20)) | zzafx.zzc(obj, j));
    }

    private final void zzQ(Object obj, int i, int i2) {
        zzafx.zzq(obj, zzz(i2) & 1048575, i);
    }

    private final void zzR(Object obj, int i, Object obj2) {
        zzb.putObject(obj, zzC(i) & 1048575, obj2);
        zzP(obj, i);
    }

    private final void zzS(Object obj, int i, int i2, Object obj2) {
        zzb.putObject(obj, zzC(i2) & 1048575, obj2);
        zzQ(obj, i, i2);
    }

    private final boolean zzT(Object obj, Object obj2, int i) {
        return zzV(obj, i) == zzV(obj2, i);
    }

    private static boolean zzU(int i) {
        return (i & 536870912) != 0;
    }

    private final boolean zzV(Object obj, int i) {
        int zzz = zzz(i);
        long j = zzz & 1048575;
        if (j != 1048575) {
            return (zzafx.zzc(obj, j) & (1 << (zzz >>> 20))) != 0;
        }
        int zzC = zzC(i);
        long j2 = zzC & 1048575;
        switch (zzB(zzC)) {
            case 0:
                return Double.doubleToRawLongBits(zzafx.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzafx.zzb(obj, j2)) != 0;
            case 2:
                return zzafx.zzd(obj, j2) != 0;
            case 3:
                return zzafx.zzd(obj, j2) != 0;
            case 4:
                return zzafx.zzc(obj, j2) != 0;
            case 5:
                return zzafx.zzd(obj, j2) != 0;
            case 6:
                return zzafx.zzc(obj, j2) != 0;
            case 7:
                return zzafx.zzw(obj, j2);
            case 8:
                Object zzf = zzafx.zzf(obj, j2);
                if (zzf instanceof String) {
                    return !((String) zzf).isEmpty();
                }
                if (zzf instanceof zzacc) {
                    return !zzacc.zzb.equals(zzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzafx.zzf(obj, j2) != null;
            case 10:
                return !zzacc.zzb.equals(zzafx.zzf(obj, j2));
            case 11:
                return zzafx.zzc(obj, j2) != 0;
            case 12:
                return zzafx.zzc(obj, j2) != 0;
            case 13:
                return zzafx.zzc(obj, j2) != 0;
            case 14:
                return zzafx.zzd(obj, j2) != 0;
            case 15:
                return zzafx.zzc(obj, j2) != 0;
            case 16:
                return zzafx.zzd(obj, j2) != 0;
            case 17:
                return zzafx.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzW(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzV(obj, i);
        }
        return (i3 & i4) != 0;
    }

    private static boolean zzX(Object obj, int i, zzaew zzaewVar) {
        return zzaewVar.zzk(zzafx.zzf(obj, i & 1048575));
    }

    private static boolean zzY(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzadf) {
            return ((zzadf) obj).zzK();
        }
        return true;
    }

    private final boolean zzZ(Object obj, int i, int i2) {
        return zzafx.zzc(obj, (long) (zzz(i2) & 1048575)) == i;
    }

    private static boolean zzaa(Object obj, long j) {
        return ((Boolean) zzafx.zzf(obj, j)).booleanValue();
    }

    private final void zzab(Object obj, zzaco zzacoVar) throws IOException {
        int i;
        if (this.zzh) {
            this.zzp.zza(obj);
            throw null;
        }
        int length = this.zzc.length;
        Unsafe unsafe = zzb;
        int i2 = 1048575;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1048575;
        while (i3 < length) {
            int zzC = zzC(i3);
            int[] iArr = this.zzc;
            int i6 = iArr[i3];
            int zzB = zzB(zzC);
            if (zzB <= 17) {
                int i7 = iArr[i3 + 2];
                int i8 = i7 & i2;
                if (i8 != i5) {
                    i4 = unsafe.getInt(obj, i8);
                    i5 = i8;
                }
                i = 1 << (i7 >>> 20);
            } else {
                i = 0;
            }
            long j = zzC & i2;
            switch (zzB) {
                case 0:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        zzacoVar.zzf(i6, zzafx.zza(obj, j));
                        continue;
                    }
                case 1:
                    if ((i4 & i) != 0) {
                        zzacoVar.zzo(i6, zzafx.zzb(obj, j));
                        break;
                    } else {
                        continue;
                    }
                case 2:
                    if ((i4 & i) != 0) {
                        zzacoVar.zzt(i6, unsafe.getLong(obj, j));
                        break;
                    } else {
                        continue;
                    }
                case 3:
                    if ((i4 & i) != 0) {
                        zzacoVar.zzJ(i6, unsafe.getLong(obj, j));
                        break;
                    } else {
                        continue;
                    }
                case 4:
                    if ((i4 & i) != 0) {
                        zzacoVar.zzr(i6, unsafe.getInt(obj, j));
                        break;
                    } else {
                        continue;
                    }
                case 5:
                    if ((i4 & i) != 0) {
                        zzacoVar.zzm(i6, unsafe.getLong(obj, j));
                        break;
                    } else {
                        continue;
                    }
                case 6:
                    if ((i4 & i) != 0) {
                        zzacoVar.zzk(i6, unsafe.getInt(obj, j));
                        break;
                    } else {
                        continue;
                    }
                case 7:
                    if ((i4 & i) != 0) {
                        zzacoVar.zzb(i6, zzafx.zzw(obj, j));
                        break;
                    } else {
                        continue;
                    }
                case 8:
                    if ((i4 & i) != 0) {
                        zzad(i6, unsafe.getObject(obj, j), zzacoVar);
                        break;
                    } else {
                        continue;
                    }
                case 9:
                    if ((i4 & i) != 0) {
                        zzacoVar.zzv(i6, unsafe.getObject(obj, j), zzF(i3));
                        break;
                    } else {
                        continue;
                    }
                case 10:
                    if ((i4 & i) != 0) {
                        zzacoVar.zzd(i6, (zzacc) unsafe.getObject(obj, j));
                        break;
                    } else {
                        continue;
                    }
                case 11:
                    if ((i4 & i) != 0) {
                        zzacoVar.zzH(i6, unsafe.getInt(obj, j));
                        break;
                    } else {
                        continue;
                    }
                case 12:
                    if ((i4 & i) != 0) {
                        zzacoVar.zzi(i6, unsafe.getInt(obj, j));
                        break;
                    } else {
                        continue;
                    }
                case 13:
                    if ((i4 & i) != 0) {
                        zzacoVar.zzw(i6, unsafe.getInt(obj, j));
                        break;
                    } else {
                        continue;
                    }
                case 14:
                    if ((i4 & i) != 0) {
                        zzacoVar.zzy(i6, unsafe.getLong(obj, j));
                        break;
                    } else {
                        continue;
                    }
                case 15:
                    if ((i4 & i) != 0) {
                        zzacoVar.zzA(i6, unsafe.getInt(obj, j));
                        break;
                    } else {
                        continue;
                    }
                case 16:
                    if ((i4 & i) != 0) {
                        zzacoVar.zzC(i6, unsafe.getLong(obj, j));
                        break;
                    } else {
                        continue;
                    }
                case 17:
                    if ((i4 & i) != 0) {
                        zzacoVar.zzq(i6, unsafe.getObject(obj, j), zzF(i3));
                        break;
                    } else {
                        continue;
                    }
                case 18:
                    zzaey.zzL(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    continue;
                case 19:
                    zzaey.zzP(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    continue;
                case 20:
                    zzaey.zzS(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    continue;
                case 21:
                    zzaey.zzaa(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    continue;
                case 22:
                    zzaey.zzR(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    continue;
                case 23:
                    zzaey.zzO(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    continue;
                case 24:
                    zzaey.zzN(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    continue;
                case 25:
                    zzaey.zzJ(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    continue;
                case 26:
                    zzaey.zzY(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar);
                    break;
                case 27:
                    zzaey.zzT(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, zzF(i3));
                    break;
                case 28:
                    zzaey.zzK(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar);
                    break;
                case Service.SYSTEM_PARAMETERS_FIELD_NUMBER /* 29 */:
                    zzaey.zzZ(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    break;
                case 30:
                    zzaey.zzM(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    break;
                case DescriptorProtos.FileOptions.CC_ENABLE_ARENAS_FIELD_NUMBER /* 31 */:
                    zzaey.zzU(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    break;
                case 32:
                    zzaey.zzV(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    break;
                case 33:
                    zzaey.zzW(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    break;
                case 34:
                    zzaey.zzX(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    break;
                case MotionEventCompat.AXIS_GENERIC_4 /* 35 */:
                    zzaey.zzL(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 36:
                    zzaey.zzP(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 37:
                    zzaey.zzS(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
                    zzaey.zzaa(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 39:
                    zzaey.zzR(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 40:
                    zzaey.zzO(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 41:
                    zzaey.zzN(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 42:
                    zzaey.zzJ(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case MotionEventCompat.AXIS_GENERIC_12 /* 43 */:
                    zzaey.zzZ(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 44:
                    zzaey.zzM(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 45:
                    zzaey.zzU(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case MotionEventCompat.AXIS_GENERIC_15 /* 46 */:
                    zzaey.zzV(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
                    zzaey.zzW(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 48:
                    zzaey.zzX(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 49:
                    zzaey.zzQ(this.zzc[i3], (List) unsafe.getObject(obj, j), zzacoVar, zzF(i3));
                    break;
                case FirestoreIndexValueWriter.INDEX_TYPE_ARRAY /* 50 */:
                    zzac(zzacoVar, i6, unsafe.getObject(obj, j), i3);
                    break;
                case 51:
                    if (zzZ(obj, i6, i3)) {
                        zzacoVar.zzf(i6, zzo(obj, j));
                        break;
                    }
                    break;
                case 52:
                    if (zzZ(obj, i6, i3)) {
                        zzacoVar.zzo(i6, zzp(obj, j));
                        break;
                    }
                    break;
                case 53:
                    if (zzZ(obj, i6, i3)) {
                        zzacoVar.zzt(i6, zzD(obj, j));
                        break;
                    }
                    break;
                case 54:
                    if (zzZ(obj, i6, i3)) {
                        zzacoVar.zzJ(i6, zzD(obj, j));
                        break;
                    }
                    break;
                case FirestoreIndexValueWriter.INDEX_TYPE_MAP /* 55 */:
                    if (zzZ(obj, i6, i3)) {
                        zzacoVar.zzr(i6, zzs(obj, j));
                        break;
                    }
                    break;
                case 56:
                    if (zzZ(obj, i6, i3)) {
                        zzacoVar.zzm(i6, zzD(obj, j));
                        break;
                    }
                    break;
                case 57:
                    if (zzZ(obj, i6, i3)) {
                        zzacoVar.zzk(i6, zzs(obj, j));
                        break;
                    }
                    break;
                case 58:
                    if (zzZ(obj, i6, i3)) {
                        zzacoVar.zzb(i6, zzaa(obj, j));
                        break;
                    }
                    break;
                case 59:
                    if (zzZ(obj, i6, i3)) {
                        zzad(i6, unsafe.getObject(obj, j), zzacoVar);
                        break;
                    }
                    break;
                case 60:
                    if (zzZ(obj, i6, i3)) {
                        zzacoVar.zzv(i6, unsafe.getObject(obj, j), zzF(i3));
                        break;
                    }
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzZ(obj, i6, i3)) {
                        zzacoVar.zzd(i6, (zzacc) unsafe.getObject(obj, j));
                        break;
                    }
                    break;
                case 62:
                    if (zzZ(obj, i6, i3)) {
                        zzacoVar.zzH(i6, zzs(obj, j));
                        break;
                    }
                    break;
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (zzZ(obj, i6, i3)) {
                        zzacoVar.zzi(i6, zzs(obj, j));
                        break;
                    }
                    break;
                case 64:
                    if (zzZ(obj, i6, i3)) {
                        zzacoVar.zzw(i6, zzs(obj, j));
                        break;
                    }
                    break;
                case RegisterRequest.U2F_V1_CHALLENGE_BYTE_LENGTH /* 65 */:
                    if (zzZ(obj, i6, i3)) {
                        zzacoVar.zzy(i6, zzD(obj, j));
                        break;
                    }
                    break;
                case 66:
                    if (zzZ(obj, i6, i3)) {
                        zzacoVar.zzA(i6, zzs(obj, j));
                        break;
                    }
                    break;
                case 67:
                    if (zzZ(obj, i6, i3)) {
                        zzacoVar.zzC(i6, zzD(obj, j));
                        break;
                    }
                    break;
                case 68:
                    if (zzZ(obj, i6, i3)) {
                        zzacoVar.zzq(i6, unsafe.getObject(obj, j), zzF(i3));
                        break;
                    }
                    break;
            }
            i3 += 3;
            i2 = 1048575;
        }
        zzafn zzafnVar = this.zzo;
        zzafnVar.zzr(zzafnVar.zzd(obj), zzacoVar);
    }

    private final void zzac(zzaco zzacoVar, int i, Object obj, int i2) throws IOException {
        if (obj == null) {
            return;
        }
        throw null;
    }

    private static final void zzad(int i, Object obj, zzaco zzacoVar) throws IOException {
        if (obj instanceof String) {
            zzacoVar.zzF(i, (String) obj);
        } else {
            zzacoVar.zzd(i, (zzacc) obj);
        }
    }

    static zzafo zzd(Object obj) {
        zzadf zzadfVar = (zzadf) obj;
        zzafo zzafoVar = zzadfVar.zzc;
        if (zzafoVar != zzafo.zzc()) {
            return zzafoVar;
        }
        zzafo zzf = zzafo.zzf();
        zzadfVar.zzc = zzf;
        return zzf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzaen zzl(Class cls, zzaeh zzaehVar, zzaep zzaepVar, zzady zzadyVar, zzafn zzafnVar, zzact zzactVar, zzaef zzaefVar) {
        if (zzaehVar instanceof zzaeu) {
            return zzm((zzaeu) zzaehVar, zzaepVar, zzadyVar, zzafnVar, zzactVar, zzaefVar);
        }
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0385  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0260  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.google.android.gms.internal.p002firebaseauthapi.zzaen zzm(com.google.android.gms.internal.p002firebaseauthapi.zzaeu r34, com.google.android.gms.internal.p002firebaseauthapi.zzaep r35, com.google.android.gms.internal.p002firebaseauthapi.zzady r36, com.google.android.gms.internal.p002firebaseauthapi.zzafn r37, com.google.android.gms.internal.p002firebaseauthapi.zzact r38, com.google.android.gms.internal.p002firebaseauthapi.zzaef r39) {
        /*
            Method dump skipped, instructions count: 1016
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzaen.zzm(com.google.android.gms.internal.firebase-auth-api.zzaeu, com.google.android.gms.internal.firebase-auth-api.zzaep, com.google.android.gms.internal.firebase-auth-api.zzady, com.google.android.gms.internal.firebase-auth-api.zzafn, com.google.android.gms.internal.firebase-auth-api.zzact, com.google.android.gms.internal.firebase-auth-api.zzaef):com.google.android.gms.internal.firebase-auth-api.zzaen");
    }

    private static double zzo(Object obj, long j) {
        return ((Double) zzafx.zzf(obj, j)).doubleValue();
    }

    private static float zzp(Object obj, long j) {
        return ((Float) zzafx.zzf(obj, j)).floatValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x003d. Please report as an issue. */
    private final int zzq(Object obj) {
        int i;
        int zzE;
        int zzE2;
        int zzE3;
        int zzF;
        int zzE4;
        int zzy;
        int zzE5;
        int zzE6;
        int zzd;
        int zzE7;
        int i2;
        int zzu;
        int zzi;
        int zzD;
        int zzE8;
        int i3;
        int zzE9;
        int zzE10;
        int zzE11;
        int zzF2;
        int zzE12;
        int zzd2;
        int zzE13;
        int i4;
        Unsafe unsafe = zzb;
        int i5 = 1048575;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 1048575;
        while (i6 < this.zzc.length) {
            int zzC = zzC(i6);
            int[] iArr = this.zzc;
            int i10 = iArr[i6];
            int zzB = zzB(zzC);
            if (zzB <= 17) {
                int i11 = iArr[i6 + 2];
                int i12 = i11 & i5;
                i = 1 << (i11 >>> 20);
                if (i12 != i9) {
                    i8 = unsafe.getInt(obj, i12);
                    i9 = i12;
                }
            } else {
                i = 0;
            }
            long j = zzC & i5;
            switch (zzB) {
                case 0:
                    if ((i8 & i) == 0) {
                        break;
                    } else {
                        zzE = zzacn.zzE(i10 << 3);
                        zzE5 = zzE + 8;
                        i7 += zzE5;
                        break;
                    }
                case 1:
                    if ((i8 & i) == 0) {
                        break;
                    } else {
                        zzE2 = zzacn.zzE(i10 << 3);
                        zzE5 = zzE2 + 4;
                        i7 += zzE5;
                        break;
                    }
                case 2:
                    if ((i8 & i) == 0) {
                        break;
                    } else {
                        long j2 = unsafe.getLong(obj, j);
                        zzE3 = zzacn.zzE(i10 << 3);
                        zzF = zzacn.zzF(j2);
                        i7 += zzE3 + zzF;
                        break;
                    }
                case 3:
                    if ((i8 & i) == 0) {
                        break;
                    } else {
                        long j3 = unsafe.getLong(obj, j);
                        zzE3 = zzacn.zzE(i10 << 3);
                        zzF = zzacn.zzF(j3);
                        i7 += zzE3 + zzF;
                        break;
                    }
                case 4:
                    if ((i8 & i) == 0) {
                        break;
                    } else {
                        int i13 = unsafe.getInt(obj, j);
                        zzE4 = zzacn.zzE(i10 << 3);
                        zzy = zzacn.zzy(i13);
                        i2 = zzE4 + zzy;
                        i7 += i2;
                        break;
                    }
                case 5:
                    if ((i8 & i) == 0) {
                        break;
                    } else {
                        zzE = zzacn.zzE(i10 << 3);
                        zzE5 = zzE + 8;
                        i7 += zzE5;
                        break;
                    }
                case 6:
                    if ((i8 & i) == 0) {
                        break;
                    } else {
                        zzE2 = zzacn.zzE(i10 << 3);
                        zzE5 = zzE2 + 4;
                        i7 += zzE5;
                        break;
                    }
                case 7:
                    if ((i8 & i) == 0) {
                        break;
                    } else {
                        zzE5 = zzacn.zzE(i10 << 3) + 1;
                        i7 += zzE5;
                        break;
                    }
                case 8:
                    if ((i8 & i) == 0) {
                        break;
                    } else {
                        Object object = unsafe.getObject(obj, j);
                        if (object instanceof zzacc) {
                            zzE6 = zzacn.zzE(i10 << 3);
                            zzd = ((zzacc) object).zzd();
                            zzE7 = zzacn.zzE(zzd);
                            i2 = zzE6 + zzE7 + zzd;
                            i7 += i2;
                            break;
                        } else {
                            zzE4 = zzacn.zzE(i10 << 3);
                            zzy = zzacn.zzC((String) object);
                            i2 = zzE4 + zzy;
                            i7 += i2;
                        }
                    }
                case 9:
                    if ((i8 & i) == 0) {
                        break;
                    } else {
                        zzE5 = zzaey.zzo(i10, unsafe.getObject(obj, j), zzF(i6));
                        i7 += zzE5;
                        break;
                    }
                case 10:
                    if ((i8 & i) == 0) {
                        break;
                    } else {
                        zzacc zzaccVar = (zzacc) unsafe.getObject(obj, j);
                        zzE6 = zzacn.zzE(i10 << 3);
                        zzd = zzaccVar.zzd();
                        zzE7 = zzacn.zzE(zzd);
                        i2 = zzE6 + zzE7 + zzd;
                        i7 += i2;
                        break;
                    }
                case 11:
                    if ((i8 & i) == 0) {
                        break;
                    } else {
                        int i14 = unsafe.getInt(obj, j);
                        zzE4 = zzacn.zzE(i10 << 3);
                        zzy = zzacn.zzE(i14);
                        i2 = zzE4 + zzy;
                        i7 += i2;
                        break;
                    }
                case 12:
                    if ((i8 & i) == 0) {
                        break;
                    } else {
                        int i15 = unsafe.getInt(obj, j);
                        zzE4 = zzacn.zzE(i10 << 3);
                        zzy = zzacn.zzy(i15);
                        i2 = zzE4 + zzy;
                        i7 += i2;
                        break;
                    }
                case 13:
                    if ((i8 & i) == 0) {
                        break;
                    } else {
                        zzE2 = zzacn.zzE(i10 << 3);
                        zzE5 = zzE2 + 4;
                        i7 += zzE5;
                        break;
                    }
                case 14:
                    if ((i8 & i) == 0) {
                        break;
                    } else {
                        zzE = zzacn.zzE(i10 << 3);
                        zzE5 = zzE + 8;
                        i7 += zzE5;
                        break;
                    }
                case 15:
                    if ((i8 & i) == 0) {
                        break;
                    } else {
                        int i16 = unsafe.getInt(obj, j);
                        zzE4 = zzacn.zzE(i10 << 3);
                        zzy = zzacn.zzE((i16 >> 31) ^ (i16 + i16));
                        i2 = zzE4 + zzy;
                        i7 += i2;
                        break;
                    }
                case 16:
                    if ((i & i8) == 0) {
                        break;
                    } else {
                        long j4 = unsafe.getLong(obj, j);
                        i7 += zzacn.zzE(i10 << 3) + zzacn.zzF((j4 >> 63) ^ (j4 + j4));
                        break;
                    }
                case 17:
                    if ((i8 & i) == 0) {
                        break;
                    } else {
                        zzE5 = zzacn.zzx(i10, (zzaek) unsafe.getObject(obj, j), zzF(i6));
                        i7 += zzE5;
                        break;
                    }
                case 18:
                    zzE5 = zzaey.zzh(i10, (List) unsafe.getObject(obj, j), false);
                    i7 += zzE5;
                    break;
                case 19:
                    zzE5 = zzaey.zzf(i10, (List) unsafe.getObject(obj, j), false);
                    i7 += zzE5;
                    break;
                case 20:
                    zzE5 = zzaey.zzm(i10, (List) unsafe.getObject(obj, j), false);
                    i7 += zzE5;
                    break;
                case 21:
                    zzE5 = zzaey.zzx(i10, (List) unsafe.getObject(obj, j), false);
                    i7 += zzE5;
                    break;
                case 22:
                    zzE5 = zzaey.zzk(i10, (List) unsafe.getObject(obj, j), false);
                    i7 += zzE5;
                    break;
                case 23:
                    zzE5 = zzaey.zzh(i10, (List) unsafe.getObject(obj, j), false);
                    i7 += zzE5;
                    break;
                case 24:
                    zzE5 = zzaey.zzf(i10, (List) unsafe.getObject(obj, j), false);
                    i7 += zzE5;
                    break;
                case 25:
                    zzE5 = zzaey.zza(i10, (List) unsafe.getObject(obj, j), false);
                    i7 += zzE5;
                    break;
                case 26:
                    zzu = zzaey.zzu(i10, (List) unsafe.getObject(obj, j));
                    i7 += zzu;
                    break;
                case 27:
                    zzu = zzaey.zzp(i10, (List) unsafe.getObject(obj, j), zzF(i6));
                    i7 += zzu;
                    break;
                case 28:
                    zzu = zzaey.zzc(i10, (List) unsafe.getObject(obj, j));
                    i7 += zzu;
                    break;
                case Service.SYSTEM_PARAMETERS_FIELD_NUMBER /* 29 */:
                    zzu = zzaey.zzv(i10, (List) unsafe.getObject(obj, j), false);
                    i7 += zzu;
                    break;
                case 30:
                    zzu = zzaey.zzd(i10, (List) unsafe.getObject(obj, j), false);
                    i7 += zzu;
                    break;
                case DescriptorProtos.FileOptions.CC_ENABLE_ARENAS_FIELD_NUMBER /* 31 */:
                    zzu = zzaey.zzf(i10, (List) unsafe.getObject(obj, j), false);
                    i7 += zzu;
                    break;
                case 32:
                    zzu = zzaey.zzh(i10, (List) unsafe.getObject(obj, j), false);
                    i7 += zzu;
                    break;
                case 33:
                    zzu = zzaey.zzq(i10, (List) unsafe.getObject(obj, j), false);
                    i7 += zzu;
                    break;
                case 34:
                    zzu = zzaey.zzs(i10, (List) unsafe.getObject(obj, j), false);
                    i7 += zzu;
                    break;
                case MotionEventCompat.AXIS_GENERIC_4 /* 35 */:
                    zzi = zzaey.zzi((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i7 += i4;
                    }
                    break;
                case 36:
                    zzi = zzaey.zzg((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i7 += i4;
                    }
                    break;
                case 37:
                    zzi = zzaey.zzn((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i7 += i4;
                    }
                    break;
                case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
                    zzi = zzaey.zzy((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i7 += i4;
                    }
                    break;
                case 39:
                    zzi = zzaey.zzl((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i7 += i4;
                    }
                    break;
                case 40:
                    zzi = zzaey.zzi((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i7 += i4;
                    }
                    break;
                case 41:
                    zzi = zzaey.zzg((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i7 += i4;
                    }
                    break;
                case 42:
                    zzi = zzaey.zzb((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i7 += i4;
                    }
                    break;
                case MotionEventCompat.AXIS_GENERIC_12 /* 43 */:
                    zzi = zzaey.zzw((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i7 += i4;
                    }
                    break;
                case 44:
                    zzi = zzaey.zze((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i7 += i4;
                    }
                    break;
                case 45:
                    zzi = zzaey.zzg((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i7 += i4;
                    }
                    break;
                case MotionEventCompat.AXIS_GENERIC_15 /* 46 */:
                    zzi = zzaey.zzi((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i7 += i4;
                    }
                    break;
                case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
                    zzi = zzaey.zzr((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i7 += i4;
                    }
                    break;
                case 48:
                    zzi = zzaey.zzt((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i7 += i4;
                    }
                    break;
                case 49:
                    zzu = zzaey.zzj(i10, (List) unsafe.getObject(obj, j), zzF(i6));
                    i7 += zzu;
                    break;
                case FirestoreIndexValueWriter.INDEX_TYPE_ARRAY /* 50 */:
                    zzaef.zza(i10, unsafe.getObject(obj, j), zzH(i6));
                    break;
                case 51:
                    if (zzZ(obj, i10, i6)) {
                        zzE9 = zzacn.zzE(i10 << 3);
                        zzu = zzE9 + 8;
                        i7 += zzu;
                    }
                    break;
                case 52:
                    if (zzZ(obj, i10, i6)) {
                        zzE10 = zzacn.zzE(i10 << 3);
                        zzu = zzE10 + 4;
                        i7 += zzu;
                    }
                    break;
                case 53:
                    if (zzZ(obj, i10, i6)) {
                        long zzD2 = zzD(obj, j);
                        zzE11 = zzacn.zzE(i10 << 3);
                        zzF2 = zzacn.zzF(zzD2);
                        i7 += zzE11 + zzF2;
                    }
                    break;
                case 54:
                    if (zzZ(obj, i10, i6)) {
                        long zzD3 = zzD(obj, j);
                        zzE11 = zzacn.zzE(i10 << 3);
                        zzF2 = zzacn.zzF(zzD3);
                        i7 += zzE11 + zzF2;
                    }
                    break;
                case FirestoreIndexValueWriter.INDEX_TYPE_MAP /* 55 */:
                    if (zzZ(obj, i10, i6)) {
                        int zzs = zzs(obj, j);
                        i3 = zzacn.zzE(i10 << 3);
                        zzi = zzacn.zzy(zzs);
                        i4 = i3 + zzi;
                        i7 += i4;
                    }
                    break;
                case 56:
                    if (zzZ(obj, i10, i6)) {
                        zzE9 = zzacn.zzE(i10 << 3);
                        zzu = zzE9 + 8;
                        i7 += zzu;
                    }
                    break;
                case 57:
                    if (zzZ(obj, i10, i6)) {
                        zzE10 = zzacn.zzE(i10 << 3);
                        zzu = zzE10 + 4;
                        i7 += zzu;
                    }
                    break;
                case 58:
                    if (zzZ(obj, i10, i6)) {
                        zzu = zzacn.zzE(i10 << 3) + 1;
                        i7 += zzu;
                    }
                    break;
                case 59:
                    if (zzZ(obj, i10, i6)) {
                        Object object2 = unsafe.getObject(obj, j);
                        if (object2 instanceof zzacc) {
                            zzE12 = zzacn.zzE(i10 << 3);
                            zzd2 = ((zzacc) object2).zzd();
                            zzE13 = zzacn.zzE(zzd2);
                            i4 = zzE12 + zzE13 + zzd2;
                            i7 += i4;
                        } else {
                            i3 = zzacn.zzE(i10 << 3);
                            zzi = zzacn.zzC((String) object2);
                            i4 = i3 + zzi;
                            i7 += i4;
                        }
                    }
                    break;
                case 60:
                    if (zzZ(obj, i10, i6)) {
                        zzu = zzaey.zzo(i10, unsafe.getObject(obj, j), zzF(i6));
                        i7 += zzu;
                    }
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzZ(obj, i10, i6)) {
                        zzacc zzaccVar2 = (zzacc) unsafe.getObject(obj, j);
                        zzE12 = zzacn.zzE(i10 << 3);
                        zzd2 = zzaccVar2.zzd();
                        zzE13 = zzacn.zzE(zzd2);
                        i4 = zzE12 + zzE13 + zzd2;
                        i7 += i4;
                    }
                    break;
                case 62:
                    if (zzZ(obj, i10, i6)) {
                        int zzs2 = zzs(obj, j);
                        i3 = zzacn.zzE(i10 << 3);
                        zzi = zzacn.zzE(zzs2);
                        i4 = i3 + zzi;
                        i7 += i4;
                    }
                    break;
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (zzZ(obj, i10, i6)) {
                        int zzs3 = zzs(obj, j);
                        i3 = zzacn.zzE(i10 << 3);
                        zzi = zzacn.zzy(zzs3);
                        i4 = i3 + zzi;
                        i7 += i4;
                    }
                    break;
                case 64:
                    if (zzZ(obj, i10, i6)) {
                        zzE10 = zzacn.zzE(i10 << 3);
                        zzu = zzE10 + 4;
                        i7 += zzu;
                    }
                    break;
                case RegisterRequest.U2F_V1_CHALLENGE_BYTE_LENGTH /* 65 */:
                    if (zzZ(obj, i10, i6)) {
                        zzE9 = zzacn.zzE(i10 << 3);
                        zzu = zzE9 + 8;
                        i7 += zzu;
                    }
                    break;
                case 66:
                    if (zzZ(obj, i10, i6)) {
                        int zzs4 = zzs(obj, j);
                        i3 = zzacn.zzE(i10 << 3);
                        zzi = zzacn.zzE((zzs4 >> 31) ^ (zzs4 + zzs4));
                        i4 = i3 + zzi;
                        i7 += i4;
                    }
                    break;
                case 67:
                    if (zzZ(obj, i10, i6)) {
                        long zzD4 = zzD(obj, j);
                        i7 += zzacn.zzE(i10 << 3) + zzacn.zzF((zzD4 >> 63) ^ (zzD4 + zzD4));
                    }
                    break;
                case 68:
                    if (zzZ(obj, i10, i6)) {
                        zzu = zzacn.zzx(i10, (zzaek) unsafe.getObject(obj, j), zzF(i6));
                        i7 += zzu;
                    }
                    break;
            }
            i6 += 3;
            i5 = 1048575;
        }
        zzafn zzafnVar = this.zzo;
        int zza2 = i7 + zzafnVar.zza(zzafnVar.zzd(obj));
        if (!this.zzh) {
            return zza2;
        }
        this.zzp.zza(obj);
        throw null;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x0033. Please report as an issue. */
    private final int zzr(Object obj) {
        int zzE;
        int zzE2;
        int zzE3;
        int zzF;
        int zzE4;
        int zzy;
        int zzE5;
        int zzE6;
        int zzd;
        int zzE7;
        int zzo;
        int zzD;
        int zzE8;
        int i;
        Unsafe unsafe = zzb;
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzc.length; i3 += 3) {
            int zzC = zzC(i3);
            int zzB = zzB(zzC);
            int i4 = this.zzc[i3];
            long j = zzC & 1048575;
            if (zzB >= zzacy.DOUBLE_LIST_PACKED.zza() && zzB <= zzacy.SINT64_LIST_PACKED.zza()) {
                int i5 = this.zzc[i3 + 2];
            }
            switch (zzB) {
                case 0:
                    if (zzV(obj, i3)) {
                        zzE = zzacn.zzE(i4 << 3);
                        zzo = zzE + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzV(obj, i3)) {
                        zzE2 = zzacn.zzE(i4 << 3);
                        zzo = zzE2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzV(obj, i3)) {
                        long zzd2 = zzafx.zzd(obj, j);
                        zzE3 = zzacn.zzE(i4 << 3);
                        zzF = zzacn.zzF(zzd2);
                        i2 += zzE3 + zzF;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzV(obj, i3)) {
                        long zzd3 = zzafx.zzd(obj, j);
                        zzE3 = zzacn.zzE(i4 << 3);
                        zzF = zzacn.zzF(zzd3);
                        i2 += zzE3 + zzF;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzV(obj, i3)) {
                        int zzc = zzafx.zzc(obj, j);
                        zzE4 = zzacn.zzE(i4 << 3);
                        zzy = zzacn.zzy(zzc);
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzV(obj, i3)) {
                        zzE = zzacn.zzE(i4 << 3);
                        zzo = zzE + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzV(obj, i3)) {
                        zzE2 = zzacn.zzE(i4 << 3);
                        zzo = zzE2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzV(obj, i3)) {
                        zzE5 = zzacn.zzE(i4 << 3);
                        zzo = zzE5 + 1;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (!zzV(obj, i3)) {
                        break;
                    } else {
                        Object zzf = zzafx.zzf(obj, j);
                        if (zzf instanceof zzacc) {
                            zzE6 = zzacn.zzE(i4 << 3);
                            zzd = ((zzacc) zzf).zzd();
                            zzE7 = zzacn.zzE(zzd);
                            i = zzE6 + zzE7 + zzd;
                            i2 += i;
                            break;
                        } else {
                            zzE4 = zzacn.zzE(i4 << 3);
                            zzy = zzacn.zzC((String) zzf);
                            i = zzE4 + zzy;
                            i2 += i;
                        }
                    }
                case 9:
                    if (zzV(obj, i3)) {
                        zzo = zzaey.zzo(i4, zzafx.zzf(obj, j), zzF(i3));
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (zzV(obj, i3)) {
                        zzacc zzaccVar = (zzacc) zzafx.zzf(obj, j);
                        zzE6 = zzacn.zzE(i4 << 3);
                        zzd = zzaccVar.zzd();
                        zzE7 = zzacn.zzE(zzd);
                        i = zzE6 + zzE7 + zzd;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzV(obj, i3)) {
                        int zzc2 = zzafx.zzc(obj, j);
                        zzE4 = zzacn.zzE(i4 << 3);
                        zzy = zzacn.zzE(zzc2);
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzV(obj, i3)) {
                        int zzc3 = zzafx.zzc(obj, j);
                        zzE4 = zzacn.zzE(i4 << 3);
                        zzy = zzacn.zzy(zzc3);
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzV(obj, i3)) {
                        zzE2 = zzacn.zzE(i4 << 3);
                        zzo = zzE2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzV(obj, i3)) {
                        zzE = zzacn.zzE(i4 << 3);
                        zzo = zzE + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzV(obj, i3)) {
                        int zzc4 = zzafx.zzc(obj, j);
                        zzE4 = zzacn.zzE(i4 << 3);
                        zzy = zzacn.zzE((zzc4 >> 31) ^ (zzc4 + zzc4));
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzV(obj, i3)) {
                        long zzd4 = zzafx.zzd(obj, j);
                        zzE4 = zzacn.zzE(i4 << 3);
                        zzy = zzacn.zzF((zzd4 >> 63) ^ (zzd4 + zzd4));
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (zzV(obj, i3)) {
                        zzo = zzacn.zzx(i4, (zzaek) zzafx.zzf(obj, j), zzF(i3));
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 18:
                    zzo = zzaey.zzh(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 19:
                    zzo = zzaey.zzf(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 20:
                    zzo = zzaey.zzm(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 21:
                    zzo = zzaey.zzx(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 22:
                    zzo = zzaey.zzk(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 23:
                    zzo = zzaey.zzh(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 24:
                    zzo = zzaey.zzf(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 25:
                    zzo = zzaey.zza(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 26:
                    zzo = zzaey.zzu(i4, (List) zzafx.zzf(obj, j));
                    i2 += zzo;
                    break;
                case 27:
                    zzo = zzaey.zzp(i4, (List) zzafx.zzf(obj, j), zzF(i3));
                    i2 += zzo;
                    break;
                case 28:
                    zzo = zzaey.zzc(i4, (List) zzafx.zzf(obj, j));
                    i2 += zzo;
                    break;
                case Service.SYSTEM_PARAMETERS_FIELD_NUMBER /* 29 */:
                    zzo = zzaey.zzv(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 30:
                    zzo = zzaey.zzd(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case DescriptorProtos.FileOptions.CC_ENABLE_ARENAS_FIELD_NUMBER /* 31 */:
                    zzo = zzaey.zzf(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 32:
                    zzo = zzaey.zzh(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 33:
                    zzo = zzaey.zzq(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 34:
                    zzo = zzaey.zzs(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case MotionEventCompat.AXIS_GENERIC_4 /* 35 */:
                    zzy = zzaey.zzi((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    zzy = zzaey.zzg((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    zzy = zzaey.zzn((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
                    zzy = zzaey.zzy((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    zzy = zzaey.zzl((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    zzy = zzaey.zzi((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    zzy = zzaey.zzg((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    zzy = zzaey.zzb((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case MotionEventCompat.AXIS_GENERIC_12 /* 43 */:
                    zzy = zzaey.zzw((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    zzy = zzaey.zze((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    zzy = zzaey.zzg((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case MotionEventCompat.AXIS_GENERIC_15 /* 46 */:
                    zzy = zzaey.zzi((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
                    zzy = zzaey.zzr((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 48:
                    zzy = zzaey.zzt((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    zzo = zzaey.zzj(i4, (List) zzafx.zzf(obj, j), zzF(i3));
                    i2 += zzo;
                    break;
                case FirestoreIndexValueWriter.INDEX_TYPE_ARRAY /* 50 */:
                    zzaef.zza(i4, zzafx.zzf(obj, j), zzH(i3));
                    break;
                case 51:
                    if (zzZ(obj, i4, i3)) {
                        zzE = zzacn.zzE(i4 << 3);
                        zzo = zzE + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzZ(obj, i4, i3)) {
                        zzE2 = zzacn.zzE(i4 << 3);
                        zzo = zzE2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzZ(obj, i4, i3)) {
                        long zzD2 = zzD(obj, j);
                        zzE3 = zzacn.zzE(i4 << 3);
                        zzF = zzacn.zzF(zzD2);
                        i2 += zzE3 + zzF;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzZ(obj, i4, i3)) {
                        long zzD3 = zzD(obj, j);
                        zzE3 = zzacn.zzE(i4 << 3);
                        zzF = zzacn.zzF(zzD3);
                        i2 += zzE3 + zzF;
                        break;
                    } else {
                        break;
                    }
                case FirestoreIndexValueWriter.INDEX_TYPE_MAP /* 55 */:
                    if (zzZ(obj, i4, i3)) {
                        int zzs = zzs(obj, j);
                        zzE4 = zzacn.zzE(i4 << 3);
                        zzy = zzacn.zzy(zzs);
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzZ(obj, i4, i3)) {
                        zzE = zzacn.zzE(i4 << 3);
                        zzo = zzE + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzZ(obj, i4, i3)) {
                        zzE2 = zzacn.zzE(i4 << 3);
                        zzo = zzE2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzZ(obj, i4, i3)) {
                        zzE5 = zzacn.zzE(i4 << 3);
                        zzo = zzE5 + 1;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (!zzZ(obj, i4, i3)) {
                        break;
                    } else {
                        Object zzf2 = zzafx.zzf(obj, j);
                        if (zzf2 instanceof zzacc) {
                            zzE6 = zzacn.zzE(i4 << 3);
                            zzd = ((zzacc) zzf2).zzd();
                            zzE7 = zzacn.zzE(zzd);
                            i = zzE6 + zzE7 + zzd;
                            i2 += i;
                            break;
                        } else {
                            zzE4 = zzacn.zzE(i4 << 3);
                            zzy = zzacn.zzC((String) zzf2);
                            i = zzE4 + zzy;
                            i2 += i;
                        }
                    }
                case 60:
                    if (zzZ(obj, i4, i3)) {
                        zzo = zzaey.zzo(i4, zzafx.zzf(obj, j), zzF(i3));
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzZ(obj, i4, i3)) {
                        zzacc zzaccVar2 = (zzacc) zzafx.zzf(obj, j);
                        zzE6 = zzacn.zzE(i4 << 3);
                        zzd = zzaccVar2.zzd();
                        zzE7 = zzacn.zzE(zzd);
                        i = zzE6 + zzE7 + zzd;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzZ(obj, i4, i3)) {
                        int zzs2 = zzs(obj, j);
                        zzE4 = zzacn.zzE(i4 << 3);
                        zzy = zzacn.zzE(zzs2);
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (zzZ(obj, i4, i3)) {
                        int zzs3 = zzs(obj, j);
                        zzE4 = zzacn.zzE(i4 << 3);
                        zzy = zzacn.zzy(zzs3);
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzZ(obj, i4, i3)) {
                        zzE2 = zzacn.zzE(i4 << 3);
                        zzo = zzE2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case RegisterRequest.U2F_V1_CHALLENGE_BYTE_LENGTH /* 65 */:
                    if (zzZ(obj, i4, i3)) {
                        zzE = zzacn.zzE(i4 << 3);
                        zzo = zzE + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzZ(obj, i4, i3)) {
                        int zzs4 = zzs(obj, j);
                        zzE4 = zzacn.zzE(i4 << 3);
                        zzy = zzacn.zzE((zzs4 >> 31) ^ (zzs4 + zzs4));
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzZ(obj, i4, i3)) {
                        long zzD4 = zzD(obj, j);
                        zzE4 = zzacn.zzE(i4 << 3);
                        zzy = zzacn.zzF((zzD4 >> 63) ^ (zzD4 + zzD4));
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzZ(obj, i4, i3)) {
                        zzo = zzacn.zzx(i4, (zzaek) zzafx.zzf(obj, j), zzF(i3));
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
            }
        }
        zzafn zzafnVar = this.zzo;
        return i2 + zzafnVar.zza(zzafnVar.zzd(obj));
    }

    private static int zzs(Object obj, long j) {
        return ((Integer) zzafx.zzf(obj, j)).intValue();
    }

    private final int zzt(Object obj, byte[] bArr, int i, int i2, int i3, long j, zzabp zzabpVar) throws IOException {
        Unsafe unsafe = zzb;
        Object zzH = zzH(i3);
        Object object = unsafe.getObject(obj, j);
        if (zzaef.zzb(object)) {
            zzaee zzb2 = zzaee.zza().zzb();
            zzaef.zzc(zzb2, object);
            unsafe.putObject(obj, j, zzb2);
        }
        throw null;
    }

    private final int zzu(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzabp zzabpVar) throws IOException {
        Unsafe unsafe = zzb;
        long j2 = this.zzc[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Double.valueOf(Double.longBitsToDouble(zzabq.zzp(bArr, i))));
                    unsafe.putInt(obj, j2, i4);
                    return i + 8;
                }
                break;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Float.valueOf(Float.intBitsToFloat(zzabq.zzb(bArr, i))));
                    unsafe.putInt(obj, j2, i4);
                    return i + 4;
                }
                break;
            case 53:
            case 54:
                if (i5 == 0) {
                    int zzm = zzabq.zzm(bArr, i, zzabpVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzabpVar.zzb));
                    unsafe.putInt(obj, j2, i4);
                    return zzm;
                }
                break;
            case FirestoreIndexValueWriter.INDEX_TYPE_MAP /* 55 */:
            case 62:
                if (i5 == 0) {
                    int zzj = zzabq.zzj(bArr, i, zzabpVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzabpVar.zza));
                    unsafe.putInt(obj, j2, i4);
                    return zzj;
                }
                break;
            case 56:
            case RegisterRequest.U2F_V1_CHALLENGE_BYTE_LENGTH /* 65 */:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Long.valueOf(zzabq.zzp(bArr, i)));
                    unsafe.putInt(obj, j2, i4);
                    return i + 8;
                }
                break;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Integer.valueOf(zzabq.zzb(bArr, i)));
                    unsafe.putInt(obj, j2, i4);
                    return i + 4;
                }
                break;
            case 58:
                if (i5 == 0) {
                    int zzm2 = zzabq.zzm(bArr, i, zzabpVar);
                    unsafe.putObject(obj, j, Boolean.valueOf(zzabpVar.zzb != 0));
                    unsafe.putInt(obj, j2, i4);
                    return zzm2;
                }
                break;
            case 59:
                if (i5 == 2) {
                    int zzj2 = zzabq.zzj(bArr, i, zzabpVar);
                    int i9 = zzabpVar.zza;
                    if (i9 == 0) {
                        unsafe.putObject(obj, j, "");
                    } else if ((i6 & 536870912) == 0 || zzagc.zzf(bArr, zzj2, zzj2 + i9)) {
                        unsafe.putObject(obj, j, new String(bArr, zzj2, i9, zzadl.zzb));
                        zzj2 += i9;
                    } else {
                        throw zzadn.zzd();
                    }
                    unsafe.putInt(obj, j2, i4);
                    return zzj2;
                }
                break;
            case 60:
                if (i5 == 2) {
                    Object zzJ = zzJ(obj, i4, i8);
                    int zzo = zzabq.zzo(zzJ, zzF(i8), bArr, i, i2, zzabpVar);
                    zzS(obj, i4, i8, zzJ);
                    return zzo;
                }
                break;
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                if (i5 == 2) {
                    int zza2 = zzabq.zza(bArr, i, zzabpVar);
                    unsafe.putObject(obj, j, zzabpVar.zzc);
                    unsafe.putInt(obj, j2, i4);
                    return zza2;
                }
                break;
            case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                if (i5 == 0) {
                    int zzj3 = zzabq.zzj(bArr, i, zzabpVar);
                    int i10 = zzabpVar.zza;
                    zzadj zzE = zzE(i8);
                    if (zzE == null || zzE.zza()) {
                        unsafe.putObject(obj, j, Integer.valueOf(i10));
                        unsafe.putInt(obj, j2, i4);
                    } else {
                        zzd(obj).zzj(i3, Long.valueOf(i10));
                    }
                    return zzj3;
                }
                break;
            case 66:
                if (i5 == 0) {
                    int zzj4 = zzabq.zzj(bArr, i, zzabpVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzacg.zzs(zzabpVar.zza)));
                    unsafe.putInt(obj, j2, i4);
                    return zzj4;
                }
                break;
            case 67:
                if (i5 == 0) {
                    int zzm3 = zzabq.zzm(bArr, i, zzabpVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzacg.zzt(zzabpVar.zzb)));
                    unsafe.putInt(obj, j2, i4);
                    return zzm3;
                }
                break;
            case 68:
                if (i5 == 3) {
                    Object zzJ2 = zzJ(obj, i4, i8);
                    int zzn = zzabq.zzn(zzJ2, zzF(i8), bArr, i, i2, (i3 & (-8)) | 4, zzabpVar);
                    zzS(obj, i4, i8, zzJ2);
                    return zzn;
                }
                break;
        }
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x02ec, code lost:
    
        if (r0 != r24) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x02ee, code lost:
    
        r14 = r31;
        r12 = r32;
        r13 = r34;
        r11 = r35;
        r2 = r15;
        r1 = r23;
        r6 = r25;
        r7 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x02ff, code lost:
    
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x032b, code lost:
    
        if (r0 != r14) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x034e, code lost:
    
        if (r0 != r14) goto L102;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:62:0x0094. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int zzv(java.lang.Object r31, byte[] r32, int r33, int r34, com.google.android.gms.internal.p002firebaseauthapi.zzabp r35) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 954
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzaen.zzv(java.lang.Object, byte[], int, int, com.google.android.gms.internal.firebase-auth-api.zzabp):int");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0037. Please report as an issue. */
    private final int zzw(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzabp zzabpVar) throws IOException {
        int i8;
        int i9;
        int i10;
        int i11;
        int zzj;
        int i12 = i;
        Unsafe unsafe = zzb;
        zzadk zzadkVar = (zzadk) unsafe.getObject(obj, j2);
        if (!zzadkVar.zzc()) {
            int size = zzadkVar.size();
            zzadkVar = zzadkVar.zzd(size == 0 ? 10 : size + size);
            unsafe.putObject(obj, j2, zzadkVar);
        }
        switch (i7) {
            case 18:
            case MotionEventCompat.AXIS_GENERIC_4 /* 35 */:
                if (i5 == 2) {
                    zzacp zzacpVar = (zzacp) zzadkVar;
                    int zzj2 = zzabq.zzj(bArr, i12, zzabpVar);
                    int i13 = zzabpVar.zza + zzj2;
                    while (zzj2 < i13) {
                        zzacpVar.zze(Double.longBitsToDouble(zzabq.zzp(bArr, zzj2)));
                        zzj2 += 8;
                    }
                    if (zzj2 == i13) {
                        return zzj2;
                    }
                    throw zzadn.zzi();
                }
                if (i5 == 1) {
                    zzacp zzacpVar2 = (zzacp) zzadkVar;
                    zzacpVar2.zze(Double.longBitsToDouble(zzabq.zzp(bArr, i)));
                    while (true) {
                        i8 = i12 + 8;
                        if (i8 < i2) {
                            i12 = zzabq.zzj(bArr, i8, zzabpVar);
                            if (i3 == zzabpVar.zza) {
                                zzacpVar2.zze(Double.longBitsToDouble(zzabq.zzp(bArr, i12)));
                            }
                        }
                    }
                    return i8;
                }
                return i12;
            case 19:
            case 36:
                if (i5 == 2) {
                    zzacz zzaczVar = (zzacz) zzadkVar;
                    int zzj3 = zzabq.zzj(bArr, i12, zzabpVar);
                    int i14 = zzabpVar.zza + zzj3;
                    while (zzj3 < i14) {
                        zzaczVar.zze(Float.intBitsToFloat(zzabq.zzb(bArr, zzj3)));
                        zzj3 += 4;
                    }
                    if (zzj3 == i14) {
                        return zzj3;
                    }
                    throw zzadn.zzi();
                }
                if (i5 == 5) {
                    zzacz zzaczVar2 = (zzacz) zzadkVar;
                    zzaczVar2.zze(Float.intBitsToFloat(zzabq.zzb(bArr, i)));
                    while (true) {
                        i9 = i12 + 4;
                        if (i9 < i2) {
                            i12 = zzabq.zzj(bArr, i9, zzabpVar);
                            if (i3 == zzabpVar.zza) {
                                zzaczVar2.zze(Float.intBitsToFloat(zzabq.zzb(bArr, i12)));
                            }
                        }
                    }
                    return i9;
                }
                return i12;
            case 20:
            case 21:
            case 37:
            case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
                if (i5 == 2) {
                    zzadz zzadzVar = (zzadz) zzadkVar;
                    int zzj4 = zzabq.zzj(bArr, i12, zzabpVar);
                    int i15 = zzabpVar.zza + zzj4;
                    while (zzj4 < i15) {
                        zzj4 = zzabq.zzm(bArr, zzj4, zzabpVar);
                        zzadzVar.zzf(zzabpVar.zzb);
                    }
                    if (zzj4 == i15) {
                        return zzj4;
                    }
                    throw zzadn.zzi();
                }
                if (i5 == 0) {
                    zzadz zzadzVar2 = (zzadz) zzadkVar;
                    int zzm = zzabq.zzm(bArr, i12, zzabpVar);
                    zzadzVar2.zzf(zzabpVar.zzb);
                    while (zzm < i2) {
                        int zzj5 = zzabq.zzj(bArr, zzm, zzabpVar);
                        if (i3 != zzabpVar.zza) {
                            return zzm;
                        }
                        zzm = zzabq.zzm(bArr, zzj5, zzabpVar);
                        zzadzVar2.zzf(zzabpVar.zzb);
                    }
                    return zzm;
                }
                return i12;
            case 22:
            case Service.SYSTEM_PARAMETERS_FIELD_NUMBER /* 29 */:
            case 39:
            case MotionEventCompat.AXIS_GENERIC_12 /* 43 */:
                if (i5 == 2) {
                    return zzabq.zzf(bArr, i12, zzadkVar, zzabpVar);
                }
                if (i5 == 0) {
                    return zzabq.zzl(i3, bArr, i, i2, zzadkVar, zzabpVar);
                }
                return i12;
            case 23:
            case 32:
            case 40:
            case MotionEventCompat.AXIS_GENERIC_15 /* 46 */:
                if (i5 == 2) {
                    zzadz zzadzVar3 = (zzadz) zzadkVar;
                    int zzj6 = zzabq.zzj(bArr, i12, zzabpVar);
                    int i16 = zzabpVar.zza + zzj6;
                    while (zzj6 < i16) {
                        zzadzVar3.zzf(zzabq.zzp(bArr, zzj6));
                        zzj6 += 8;
                    }
                    if (zzj6 == i16) {
                        return zzj6;
                    }
                    throw zzadn.zzi();
                }
                if (i5 == 1) {
                    zzadz zzadzVar4 = (zzadz) zzadkVar;
                    zzadzVar4.zzf(zzabq.zzp(bArr, i));
                    while (true) {
                        i10 = i12 + 8;
                        if (i10 < i2) {
                            i12 = zzabq.zzj(bArr, i10, zzabpVar);
                            if (i3 == zzabpVar.zza) {
                                zzadzVar4.zzf(zzabq.zzp(bArr, i12));
                            }
                        }
                    }
                    return i10;
                }
                return i12;
            case 24:
            case DescriptorProtos.FileOptions.CC_ENABLE_ARENAS_FIELD_NUMBER /* 31 */:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzadg zzadgVar = (zzadg) zzadkVar;
                    int zzj7 = zzabq.zzj(bArr, i12, zzabpVar);
                    int i17 = zzabpVar.zza + zzj7;
                    while (zzj7 < i17) {
                        zzadgVar.zzf(zzabq.zzb(bArr, zzj7));
                        zzj7 += 4;
                    }
                    if (zzj7 == i17) {
                        return zzj7;
                    }
                    throw zzadn.zzi();
                }
                if (i5 == 5) {
                    zzadg zzadgVar2 = (zzadg) zzadkVar;
                    zzadgVar2.zzf(zzabq.zzb(bArr, i));
                    while (true) {
                        i11 = i12 + 4;
                        if (i11 < i2) {
                            i12 = zzabq.zzj(bArr, i11, zzabpVar);
                            if (i3 == zzabpVar.zza) {
                                zzadgVar2.zzf(zzabq.zzb(bArr, i12));
                            }
                        }
                    }
                    return i11;
                }
                return i12;
            case 25:
            case 42:
                if (i5 == 2) {
                    zzabr zzabrVar = (zzabr) zzadkVar;
                    zzj = zzabq.zzj(bArr, i12, zzabpVar);
                    int i18 = zzabpVar.zza + zzj;
                    while (zzj < i18) {
                        zzj = zzabq.zzm(bArr, zzj, zzabpVar);
                        zzabrVar.zze(zzabpVar.zzb != 0);
                    }
                    if (zzj != i18) {
                        throw zzadn.zzi();
                    }
                    return zzj;
                }
                if (i5 == 0) {
                    zzabr zzabrVar2 = (zzabr) zzadkVar;
                    int zzm2 = zzabq.zzm(bArr, i12, zzabpVar);
                    zzabrVar2.zze(zzabpVar.zzb != 0);
                    while (zzm2 < i2) {
                        int zzj8 = zzabq.zzj(bArr, zzm2, zzabpVar);
                        if (i3 != zzabpVar.zza) {
                            return zzm2;
                        }
                        zzm2 = zzabq.zzm(bArr, zzj8, zzabpVar);
                        zzabrVar2.zze(zzabpVar.zzb != 0);
                    }
                    return zzm2;
                }
                return i12;
            case 26:
                if (i5 == 2) {
                    if ((j & 536870912) != 0) {
                        i12 = zzabq.zzj(bArr, i12, zzabpVar);
                        int i19 = zzabpVar.zza;
                        if (i19 < 0) {
                            throw zzadn.zzf();
                        }
                        if (i19 == 0) {
                            zzadkVar.add("");
                        } else {
                            int i20 = i12 + i19;
                            if (!zzagc.zzf(bArr, i12, i20)) {
                                throw zzadn.zzd();
                            }
                            zzadkVar.add(new String(bArr, i12, i19, zzadl.zzb));
                            i12 = i20;
                        }
                        while (i12 < i2) {
                            int zzj9 = zzabq.zzj(bArr, i12, zzabpVar);
                            if (i3 == zzabpVar.zza) {
                                i12 = zzabq.zzj(bArr, zzj9, zzabpVar);
                                int i21 = zzabpVar.zza;
                                if (i21 < 0) {
                                    throw zzadn.zzf();
                                }
                                if (i21 == 0) {
                                    zzadkVar.add("");
                                } else {
                                    int i22 = i12 + i21;
                                    if (zzagc.zzf(bArr, i12, i22)) {
                                        zzadkVar.add(new String(bArr, i12, i21, zzadl.zzb));
                                        i12 = i22;
                                    } else {
                                        throw zzadn.zzd();
                                    }
                                }
                            }
                        }
                    } else {
                        i12 = zzabq.zzj(bArr, i12, zzabpVar);
                        int i23 = zzabpVar.zza;
                        if (i23 < 0) {
                            throw zzadn.zzf();
                        }
                        if (i23 == 0) {
                            zzadkVar.add("");
                        } else {
                            zzadkVar.add(new String(bArr, i12, i23, zzadl.zzb));
                            i12 += i23;
                        }
                        while (i12 < i2) {
                            int zzj10 = zzabq.zzj(bArr, i12, zzabpVar);
                            if (i3 == zzabpVar.zza) {
                                i12 = zzabq.zzj(bArr, zzj10, zzabpVar);
                                int i24 = zzabpVar.zza;
                                if (i24 < 0) {
                                    throw zzadn.zzf();
                                }
                                if (i24 == 0) {
                                    zzadkVar.add("");
                                } else {
                                    zzadkVar.add(new String(bArr, i12, i24, zzadl.zzb));
                                    i12 += i24;
                                }
                            }
                        }
                    }
                }
                return i12;
            case 27:
                if (i5 == 2) {
                    return zzabq.zze(zzF(i6), i3, bArr, i, i2, zzadkVar, zzabpVar);
                }
                return i12;
            case 28:
                if (i5 == 2) {
                    int zzj11 = zzabq.zzj(bArr, i12, zzabpVar);
                    int i25 = zzabpVar.zza;
                    if (i25 < 0) {
                        throw zzadn.zzf();
                    }
                    if (i25 > bArr.length - zzj11) {
                        throw zzadn.zzi();
                    }
                    if (i25 == 0) {
                        zzadkVar.add(zzacc.zzb);
                    } else {
                        zzadkVar.add(zzacc.zzo(bArr, zzj11, i25));
                        zzj11 += i25;
                    }
                    while (zzj11 < i2) {
                        int zzj12 = zzabq.zzj(bArr, zzj11, zzabpVar);
                        if (i3 != zzabpVar.zza) {
                            return zzj11;
                        }
                        zzj11 = zzabq.zzj(bArr, zzj12, zzabpVar);
                        int i26 = zzabpVar.zza;
                        if (i26 >= 0) {
                            if (i26 > bArr.length - zzj11) {
                                throw zzadn.zzi();
                            }
                            if (i26 == 0) {
                                zzadkVar.add(zzacc.zzb);
                            } else {
                                zzadkVar.add(zzacc.zzo(bArr, zzj11, i26));
                                zzj11 += i26;
                            }
                        } else {
                            throw zzadn.zzf();
                        }
                    }
                    return zzj11;
                }
                return i12;
            case 30:
            case 44:
                if (i5 != 2) {
                    if (i5 == 0) {
                        zzj = zzabq.zzl(i3, bArr, i, i2, zzadkVar, zzabpVar);
                    }
                    return i12;
                }
                zzj = zzabq.zzf(bArr, i12, zzadkVar, zzabpVar);
                zzaey.zzC(obj, i4, zzadkVar, zzE(i6), null, this.zzo);
                return zzj;
            case 33:
            case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
                if (i5 == 2) {
                    zzadg zzadgVar3 = (zzadg) zzadkVar;
                    int zzj13 = zzabq.zzj(bArr, i12, zzabpVar);
                    int i27 = zzabpVar.zza + zzj13;
                    while (zzj13 < i27) {
                        zzj13 = zzabq.zzj(bArr, zzj13, zzabpVar);
                        zzadgVar3.zzf(zzacg.zzs(zzabpVar.zza));
                    }
                    if (zzj13 == i27) {
                        return zzj13;
                    }
                    throw zzadn.zzi();
                }
                if (i5 == 0) {
                    zzadg zzadgVar4 = (zzadg) zzadkVar;
                    int zzj14 = zzabq.zzj(bArr, i12, zzabpVar);
                    zzadgVar4.zzf(zzacg.zzs(zzabpVar.zza));
                    while (zzj14 < i2) {
                        int zzj15 = zzabq.zzj(bArr, zzj14, zzabpVar);
                        if (i3 != zzabpVar.zza) {
                            return zzj14;
                        }
                        zzj14 = zzabq.zzj(bArr, zzj15, zzabpVar);
                        zzadgVar4.zzf(zzacg.zzs(zzabpVar.zza));
                    }
                    return zzj14;
                }
                return i12;
            case 34:
            case 48:
                if (i5 == 2) {
                    zzadz zzadzVar5 = (zzadz) zzadkVar;
                    int zzj16 = zzabq.zzj(bArr, i12, zzabpVar);
                    int i28 = zzabpVar.zza + zzj16;
                    while (zzj16 < i28) {
                        zzj16 = zzabq.zzm(bArr, zzj16, zzabpVar);
                        zzadzVar5.zzf(zzacg.zzt(zzabpVar.zzb));
                    }
                    if (zzj16 == i28) {
                        return zzj16;
                    }
                    throw zzadn.zzi();
                }
                if (i5 == 0) {
                    zzadz zzadzVar6 = (zzadz) zzadkVar;
                    int zzm3 = zzabq.zzm(bArr, i12, zzabpVar);
                    zzadzVar6.zzf(zzacg.zzt(zzabpVar.zzb));
                    while (zzm3 < i2) {
                        int zzj17 = zzabq.zzj(bArr, zzm3, zzabpVar);
                        if (i3 != zzabpVar.zza) {
                            return zzm3;
                        }
                        zzm3 = zzabq.zzm(bArr, zzj17, zzabpVar);
                        zzadzVar6.zzf(zzacg.zzt(zzabpVar.zzb));
                    }
                    return zzm3;
                }
                return i12;
            default:
                if (i5 == 3) {
                    zzaew zzF = zzF(i6);
                    int i29 = (i3 & (-8)) | 4;
                    int zzc = zzabq.zzc(zzF, bArr, i, i2, i29, zzabpVar);
                    zzadkVar.add(zzabpVar.zzc);
                    while (zzc < i2) {
                        int zzj18 = zzabq.zzj(bArr, zzc, zzabpVar);
                        if (i3 != zzabpVar.zza) {
                            return zzc;
                        }
                        zzc = zzabq.zzc(zzF, bArr, zzj18, i2, i29, zzabpVar);
                        zzadkVar.add(zzabpVar.zzc);
                    }
                    return zzc;
                }
                return i12;
        }
    }

    private final int zzx(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzA(i, 0);
    }

    private final int zzy(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzA(i, i2);
    }

    private final int zzz(int i) {
        return this.zzc[i + 2];
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaew
    public final int zza(Object obj) {
        return this.zzj ? zzr(obj) : zzq(obj);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x001a. Please report as an issue. */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaew
    public final int zzb(Object obj) {
        int i;
        int zzc;
        int length = this.zzc.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int zzC = zzC(i3);
            int i4 = this.zzc[i3];
            long j = 1048575 & zzC;
            int i5 = 37;
            switch (zzB(zzC)) {
                case 0:
                    i = i2 * 53;
                    zzc = zzadl.zzc(Double.doubleToLongBits(zzafx.zza(obj, j)));
                    i2 = i + zzc;
                    break;
                case 1:
                    i = i2 * 53;
                    zzc = Float.floatToIntBits(zzafx.zzb(obj, j));
                    i2 = i + zzc;
                    break;
                case 2:
                    i = i2 * 53;
                    zzc = zzadl.zzc(zzafx.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 3:
                    i = i2 * 53;
                    zzc = zzadl.zzc(zzafx.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 4:
                    i = i2 * 53;
                    zzc = zzafx.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 5:
                    i = i2 * 53;
                    zzc = zzadl.zzc(zzafx.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 6:
                    i = i2 * 53;
                    zzc = zzafx.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 7:
                    i = i2 * 53;
                    zzc = zzadl.zza(zzafx.zzw(obj, j));
                    i2 = i + zzc;
                    break;
                case 8:
                    i = i2 * 53;
                    zzc = ((String) zzafx.zzf(obj, j)).hashCode();
                    i2 = i + zzc;
                    break;
                case 9:
                    Object zzf = zzafx.zzf(obj, j);
                    if (zzf != null) {
                        i5 = zzf.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 10:
                    i = i2 * 53;
                    zzc = zzafx.zzf(obj, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 11:
                    i = i2 * 53;
                    zzc = zzafx.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 12:
                    i = i2 * 53;
                    zzc = zzafx.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 13:
                    i = i2 * 53;
                    zzc = zzafx.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 14:
                    i = i2 * 53;
                    zzc = zzadl.zzc(zzafx.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 15:
                    i = i2 * 53;
                    zzc = zzafx.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 16:
                    i = i2 * 53;
                    zzc = zzadl.zzc(zzafx.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 17:
                    Object zzf2 = zzafx.zzf(obj, j);
                    if (zzf2 != null) {
                        i5 = zzf2.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case Service.SYSTEM_PARAMETERS_FIELD_NUMBER /* 29 */:
                case 30:
                case DescriptorProtos.FileOptions.CC_ENABLE_ARENAS_FIELD_NUMBER /* 31 */:
                case 32:
                case 33:
                case 34:
                case MotionEventCompat.AXIS_GENERIC_4 /* 35 */:
                case 36:
                case 37:
                case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
                case 39:
                case 40:
                case 41:
                case 42:
                case MotionEventCompat.AXIS_GENERIC_12 /* 43 */:
                case 44:
                case 45:
                case MotionEventCompat.AXIS_GENERIC_15 /* 46 */:
                case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
                case 48:
                case 49:
                    i = i2 * 53;
                    zzc = zzafx.zzf(obj, j).hashCode();
                    i2 = i + zzc;
                    break;
                case FirestoreIndexValueWriter.INDEX_TYPE_ARRAY /* 50 */:
                    i = i2 * 53;
                    zzc = zzafx.zzf(obj, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 51:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzadl.zzc(Double.doubleToLongBits(zzo(obj, j)));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = Float.floatToIntBits(zzp(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzadl.zzc(zzD(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzadl.zzc(zzD(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case FirestoreIndexValueWriter.INDEX_TYPE_MAP /* 55 */:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzs(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzadl.zzc(zzD(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzs(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzadl.zza(zzaa(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = ((String) zzafx.zzf(obj, j)).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzafx.zzf(obj, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzafx.zzf(obj, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzs(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzs(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzs(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case RegisterRequest.U2F_V1_CHALLENGE_BYTE_LENGTH /* 65 */:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzadl.zzc(zzD(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzs(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzadl.zzc(zzD(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzafx.zzf(obj, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i2 * 53) + this.zzo.zzd(obj).hashCode();
        if (!this.zzh) {
            return hashCode;
        }
        this.zzp.zza(obj);
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0425, code lost:
    
        if (r0 == r1) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0427, code lost:
    
        r27.putInt(r12, r0, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x042d, code lost:
    
        r10 = r9.zzl;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0432, code lost:
    
        if (r10 >= r9.zzm) goto L211;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0434, code lost:
    
        zzG(r29, r9.zzk[r10], null, r9.zzo, r29);
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0447, code lost:
    
        if (r7 != 0) goto L157;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x044b, code lost:
    
        if (r6 != r32) goto L155;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0452, code lost:
    
        throw com.google.android.gms.internal.p002firebaseauthapi.zzadn.zzg();
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0459, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0455, code lost:
    
        if (r6 > r32) goto L161;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0457, code lost:
    
        if (r8 != r7) goto L161;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x045e, code lost:
    
        throw com.google.android.gms.internal.p002firebaseauthapi.zzadn.zzg();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:105:0x0093. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int zzc(java.lang.Object r29, byte[] r30, int r31, int r32, int r33, com.google.android.gms.internal.p002firebaseauthapi.zzabp r34) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1158
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzaen.zzc(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.firebase-auth-api.zzabp):int");
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaew
    public final Object zze() {
        return ((zzadf) this.zzg).zzw();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaew
    public final void zzf(Object obj) {
        if (zzY(obj)) {
            if (obj instanceof zzadf) {
                zzadf zzadfVar = (zzadf) obj;
                zzadfVar.zzH(Integer.MAX_VALUE);
                zzadfVar.zza = 0;
                zzadfVar.zzF();
            }
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzC = zzC(i);
                long j = 1048575 & zzC;
                int zzB = zzB(zzC);
                if (zzB != 9) {
                    switch (zzB) {
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                        case Service.SYSTEM_PARAMETERS_FIELD_NUMBER /* 29 */:
                        case 30:
                        case DescriptorProtos.FileOptions.CC_ENABLE_ARENAS_FIELD_NUMBER /* 31 */:
                        case 32:
                        case 33:
                        case 34:
                        case MotionEventCompat.AXIS_GENERIC_4 /* 35 */:
                        case 36:
                        case 37:
                        case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
                        case 39:
                        case 40:
                        case 41:
                        case 42:
                        case MotionEventCompat.AXIS_GENERIC_12 /* 43 */:
                        case 44:
                        case 45:
                        case MotionEventCompat.AXIS_GENERIC_15 /* 46 */:
                        case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
                        case 48:
                        case 49:
                            this.zzn.zzb(obj, j);
                            break;
                        case FirestoreIndexValueWriter.INDEX_TYPE_ARRAY /* 50 */:
                            Unsafe unsafe = zzb;
                            Object object = unsafe.getObject(obj, j);
                            if (object != null) {
                                ((zzaee) object).zzc();
                                unsafe.putObject(obj, j, object);
                                break;
                            } else {
                                break;
                            }
                    }
                }
                if (zzV(obj, i)) {
                    zzF(i).zzf(zzb.getObject(obj, j));
                }
            }
            this.zzo.zzm(obj);
            if (this.zzh) {
                this.zzp.zze(obj);
            }
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaew
    public final void zzg(Object obj, Object obj2) {
        zzL(obj);
        Objects.requireNonNull(obj2);
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzC = zzC(i);
            long j = 1048575 & zzC;
            int i2 = this.zzc[i];
            switch (zzB(zzC)) {
                case 0:
                    if (zzV(obj2, i)) {
                        zzafx.zzo(obj, j, zzafx.zza(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzV(obj2, i)) {
                        zzafx.zzp(obj, j, zzafx.zzb(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzV(obj2, i)) {
                        zzafx.zzr(obj, j, zzafx.zzd(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzV(obj2, i)) {
                        zzafx.zzr(obj, j, zzafx.zzd(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzV(obj2, i)) {
                        zzafx.zzq(obj, j, zzafx.zzc(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzV(obj2, i)) {
                        zzafx.zzr(obj, j, zzafx.zzd(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzV(obj2, i)) {
                        zzafx.zzq(obj, j, zzafx.zzc(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzV(obj2, i)) {
                        zzafx.zzm(obj, j, zzafx.zzw(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzV(obj2, i)) {
                        zzafx.zzs(obj, j, zzafx.zzf(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zzM(obj, obj2, i);
                    break;
                case 10:
                    if (zzV(obj2, i)) {
                        zzafx.zzs(obj, j, zzafx.zzf(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzV(obj2, i)) {
                        zzafx.zzq(obj, j, zzafx.zzc(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzV(obj2, i)) {
                        zzafx.zzq(obj, j, zzafx.zzc(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzV(obj2, i)) {
                        zzafx.zzq(obj, j, zzafx.zzc(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzV(obj2, i)) {
                        zzafx.zzr(obj, j, zzafx.zzd(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzV(obj2, i)) {
                        zzafx.zzq(obj, j, zzafx.zzc(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzV(obj2, i)) {
                        zzafx.zzr(obj, j, zzafx.zzd(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zzM(obj, obj2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case Service.SYSTEM_PARAMETERS_FIELD_NUMBER /* 29 */:
                case 30:
                case DescriptorProtos.FileOptions.CC_ENABLE_ARENAS_FIELD_NUMBER /* 31 */:
                case 32:
                case 33:
                case 34:
                case MotionEventCompat.AXIS_GENERIC_4 /* 35 */:
                case 36:
                case 37:
                case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
                case 39:
                case 40:
                case 41:
                case 42:
                case MotionEventCompat.AXIS_GENERIC_12 /* 43 */:
                case 44:
                case 45:
                case MotionEventCompat.AXIS_GENERIC_15 /* 46 */:
                case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
                case 48:
                case 49:
                    this.zzn.zzc(obj, obj2, j);
                    break;
                case FirestoreIndexValueWriter.INDEX_TYPE_ARRAY /* 50 */:
                    zzaey.zzI(this.zzr, obj, obj2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case FirestoreIndexValueWriter.INDEX_TYPE_MAP /* 55 */:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zzZ(obj2, i2, i)) {
                        zzafx.zzs(obj, j, zzafx.zzf(obj2, j));
                        zzQ(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzN(obj, obj2, i);
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                case 62:
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                case 64:
                case RegisterRequest.U2F_V1_CHALLENGE_BYTE_LENGTH /* 65 */:
                case 66:
                case 67:
                    if (zzZ(obj2, i2, i)) {
                        zzafx.zzs(obj, j, zzafx.zzf(obj2, j));
                        zzQ(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzN(obj, obj2, i);
                    break;
            }
        }
        zzaey.zzF(this.zzo, obj, obj2);
        if (this.zzh) {
            zzaey.zzE(this.zzp, obj, obj2);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:12:0x00c1. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0655 A[LOOP:2: B:36:0x0651->B:38:0x0655, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0669  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x061d A[Catch: all -> 0x0612, TryCatch #0 {all -> 0x0612, blocks: (B:17:0x05e8, B:44:0x0618, B:46:0x061d, B:47:0x0622), top: B:16:0x05e8 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0628 A[SYNTHETIC] */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaew
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzh(java.lang.Object r18, com.google.android.gms.internal.p002firebaseauthapi.zzaev r19, com.google.android.gms.internal.p002firebaseauthapi.zzacs r20) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1788
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzaen.zzh(java.lang.Object, com.google.android.gms.internal.firebase-auth-api.zzaev, com.google.android.gms.internal.firebase-auth-api.zzacs):void");
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaew
    public final void zzi(Object obj, byte[] bArr, int i, int i2, zzabp zzabpVar) throws IOException {
        if (this.zzj) {
            zzv(obj, bArr, i, i2, zzabpVar);
        } else {
            zzc(obj, bArr, i, i2, 0, zzabpVar);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0015. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:17:0x01c2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x01c3 A[SYNTHETIC] */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaew
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean zzj(java.lang.Object r9, java.lang.Object r10) {
        /*
            Method dump skipped, instructions count: 634
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzaen.zzj(java.lang.Object, java.lang.Object):boolean");
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaew
    public final boolean zzk(Object obj) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.zzl) {
            int i6 = this.zzk[i5];
            int i7 = this.zzc[i6];
            int zzC = zzC(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                if (i9 != 1048575) {
                    i4 = zzb.getInt(obj, i9);
                }
                i2 = i4;
                i = i9;
            } else {
                i = i3;
                i2 = i4;
            }
            if ((268435456 & zzC) != 0 && !zzW(obj, i6, i, i2, i10)) {
                return false;
            }
            int zzB = zzB(zzC);
            if (zzB != 9 && zzB != 17) {
                if (zzB != 27) {
                    if (zzB == 60 || zzB == 68) {
                        if (zzZ(obj, i7, i6) && !zzX(obj, zzC, zzF(i6))) {
                            return false;
                        }
                    } else if (zzB != 49) {
                        if (zzB == 50 && !((zzaee) zzafx.zzf(obj, zzC & 1048575)).isEmpty()) {
                            throw null;
                        }
                    }
                }
                List list = (List) zzafx.zzf(obj, zzC & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    zzaew zzF = zzF(i6);
                    for (int i11 = 0; i11 < list.size(); i11++) {
                        if (!zzF.zzk(list.get(i11))) {
                            return false;
                        }
                    }
                }
            } else if (zzW(obj, i6, i, i2, i10) && !zzX(obj, zzC, zzF(i6))) {
                return false;
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzp.zza(obj);
        throw null;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaew
    public final void zzn(Object obj, zzaco zzacoVar) throws IOException {
        if (!this.zzj) {
            zzab(obj, zzacoVar);
            return;
        }
        if (!this.zzh) {
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzC = zzC(i);
                int i2 = this.zzc[i];
                switch (zzB(zzC)) {
                    case 0:
                        if (zzV(obj, i)) {
                            zzacoVar.zzf(i2, zzafx.zza(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zzV(obj, i)) {
                            zzacoVar.zzo(i2, zzafx.zzb(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zzV(obj, i)) {
                            zzacoVar.zzt(i2, zzafx.zzd(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zzV(obj, i)) {
                            zzacoVar.zzJ(i2, zzafx.zzd(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zzV(obj, i)) {
                            zzacoVar.zzr(i2, zzafx.zzc(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zzV(obj, i)) {
                            zzacoVar.zzm(i2, zzafx.zzd(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zzV(obj, i)) {
                            zzacoVar.zzk(i2, zzafx.zzc(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zzV(obj, i)) {
                            zzacoVar.zzb(i2, zzafx.zzw(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zzV(obj, i)) {
                            zzad(i2, zzafx.zzf(obj, zzC & 1048575), zzacoVar);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        if (zzV(obj, i)) {
                            zzacoVar.zzv(i2, zzafx.zzf(obj, zzC & 1048575), zzF(i));
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zzV(obj, i)) {
                            zzacoVar.zzd(i2, (zzacc) zzafx.zzf(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zzV(obj, i)) {
                            zzacoVar.zzH(i2, zzafx.zzc(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zzV(obj, i)) {
                            zzacoVar.zzi(i2, zzafx.zzc(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zzV(obj, i)) {
                            zzacoVar.zzw(i2, zzafx.zzc(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zzV(obj, i)) {
                            zzacoVar.zzy(i2, zzafx.zzd(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zzV(obj, i)) {
                            zzacoVar.zzA(i2, zzafx.zzc(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zzV(obj, i)) {
                            zzacoVar.zzC(i2, zzafx.zzd(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zzV(obj, i)) {
                            zzacoVar.zzq(i2, zzafx.zzf(obj, zzC & 1048575), zzF(i));
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        zzaey.zzL(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 19:
                        zzaey.zzP(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 20:
                        zzaey.zzS(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 21:
                        zzaey.zzaa(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 22:
                        zzaey.zzR(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 23:
                        zzaey.zzO(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 24:
                        zzaey.zzN(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 25:
                        zzaey.zzJ(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 26:
                        zzaey.zzY(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar);
                        break;
                    case 27:
                        zzaey.zzT(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, zzF(i));
                        break;
                    case 28:
                        zzaey.zzK(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar);
                        break;
                    case Service.SYSTEM_PARAMETERS_FIELD_NUMBER /* 29 */:
                        zzaey.zzZ(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 30:
                        zzaey.zzM(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case DescriptorProtos.FileOptions.CC_ENABLE_ARENAS_FIELD_NUMBER /* 31 */:
                        zzaey.zzU(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 32:
                        zzaey.zzV(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 33:
                        zzaey.zzW(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 34:
                        zzaey.zzX(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case MotionEventCompat.AXIS_GENERIC_4 /* 35 */:
                        zzaey.zzL(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 36:
                        zzaey.zzP(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 37:
                        zzaey.zzS(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
                        zzaey.zzaa(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 39:
                        zzaey.zzR(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 40:
                        zzaey.zzO(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 41:
                        zzaey.zzN(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 42:
                        zzaey.zzJ(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case MotionEventCompat.AXIS_GENERIC_12 /* 43 */:
                        zzaey.zzZ(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 44:
                        zzaey.zzM(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 45:
                        zzaey.zzU(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case MotionEventCompat.AXIS_GENERIC_15 /* 46 */:
                        zzaey.zzV(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
                        zzaey.zzW(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 48:
                        zzaey.zzX(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 49:
                        zzaey.zzQ(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, zzF(i));
                        break;
                    case FirestoreIndexValueWriter.INDEX_TYPE_ARRAY /* 50 */:
                        zzac(zzacoVar, i2, zzafx.zzf(obj, zzC & 1048575), i);
                        break;
                    case 51:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzf(i2, zzo(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzo(i2, zzp(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzt(i2, zzD(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzJ(i2, zzD(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case FirestoreIndexValueWriter.INDEX_TYPE_MAP /* 55 */:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzr(i2, zzs(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzm(i2, zzD(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzk(i2, zzs(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzb(i2, zzaa(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zzZ(obj, i2, i)) {
                            zzad(i2, zzafx.zzf(obj, zzC & 1048575), zzacoVar);
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzv(i2, zzafx.zzf(obj, zzC & 1048575), zzF(i));
                            break;
                        } else {
                            break;
                        }
                    case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzd(i2, (zzacc) zzafx.zzf(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzH(i2, zzs(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzi(i2, zzs(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzw(i2, zzs(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case RegisterRequest.U2F_V1_CHALLENGE_BYTE_LENGTH /* 65 */:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzy(i2, zzD(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzA(i2, zzs(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzC(i2, zzD(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzq(i2, zzafx.zzf(obj, zzC & 1048575), zzF(i));
                            break;
                        } else {
                            break;
                        }
                }
            }
            zzafn zzafnVar = this.zzo;
            zzafnVar.zzr(zzafnVar.zzd(obj), zzacoVar);
            return;
        }
        this.zzp.zza(obj);
        throw null;
    }
}
