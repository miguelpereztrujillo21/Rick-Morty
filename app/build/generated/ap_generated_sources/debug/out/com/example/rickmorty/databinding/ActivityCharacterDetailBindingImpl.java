package com.example.rickmorty.databinding;
import com.example.rickmorty.R;
import com.example.rickmorty.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityCharacterDetailBindingImpl extends ActivityCharacterDetailBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.image_character_detail, 1);
        sViewsWithIds.put(R.id.text_name_character_detail, 2);
        sViewsWithIds.put(R.id.image_status_character_detail, 3);
        sViewsWithIds.put(R.id.image_gender_character_detail, 4);
        sViewsWithIds.put(R.id.text_species_character_detail, 5);
        sViewsWithIds.put(R.id.text_origin_character_detail, 6);
        sViewsWithIds.put(R.id.text_location_character_detail, 7);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityCharacterDetailBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }
    private ActivityCharacterDetailBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[1]
            , (android.widget.ImageView) bindings[4]
            , (android.widget.ImageView) bindings[3]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[5]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
        if (BR.activityCharacterDetail == variableId) {
            setActivityCharacterDetail((com.example.rickmorty.modules.modules.character_detail.ActivityCharacterDetail) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setActivityCharacterDetail(@Nullable com.example.rickmorty.modules.modules.character_detail.ActivityCharacterDetail ActivityCharacterDetail) {
        this.mActivityCharacterDetail = ActivityCharacterDetail;
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
        flag 0 (0x1L): activityCharacterDetail
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}