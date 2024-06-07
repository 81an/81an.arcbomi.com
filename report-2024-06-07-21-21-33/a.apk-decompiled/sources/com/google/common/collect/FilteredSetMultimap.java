package com.google.common.collect;

import com.android.tools.r8.annotations.SynthesizedClass;

@ElementTypesAreNonnullByDefault
/* loaded from: classes.dex */
interface FilteredSetMultimap<K, V> extends FilteredMultimap<K, V>, SetMultimap<K, V> {
    @Override // com.google.common.collect.FilteredMultimap
    SetMultimap<K, V> unfiltered();

    @SynthesizedClass(kind = "$-CC")
    /* renamed from: com.google.common.collect.FilteredSetMultimap$-CC, reason: invalid class name */
    /* loaded from: classes.dex */
    public final /* synthetic */ class CC<K, V> {
    }
}
