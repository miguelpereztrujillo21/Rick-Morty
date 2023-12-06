package com.example.rickmorty.databinding;
import com.example.rickmorty.R;
import com.example.rickmorty.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FiltersBindingImpl extends FiltersBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.chip_group_status_main, 1);
        sViewsWithIds.put(R.id.chip_dead_main, 2);
        sViewsWithIds.put(R.id.chip_alive_main, 3);
        sViewsWithIds.put(R.id.chip_unknow_main, 4);
        sViewsWithIds.put(R.id.chip_group_gender_main, 5);
        sViewsWithIds.put(R.id.chip_gender_male_main, 6);
        sViewsWithIds.put(R.id.chip_gender_female_main, 7);
        sViewsWithIds.put(R.id.chip_gender_unknow_main, 8);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FiltersBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }
    private FiltersBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.chip.Chip) bindings[3]
            , (com.google.android.material.chip.Chip) bindings[2]
            , (com.google.android.material.chip.Chip) bindings[7]
            , (com.google.android.material.chip.Chip) bindings[6]
            , (com.google.android.material.chip.Chip) bindings[8]
            , (com.google.android.material.chip.ChipGroup) bindings[5]
            , (com.google.android.material.chip.ChipGroup) bindings[1]
            , (com.google.android.material.chip.Chip) bindings[4]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}