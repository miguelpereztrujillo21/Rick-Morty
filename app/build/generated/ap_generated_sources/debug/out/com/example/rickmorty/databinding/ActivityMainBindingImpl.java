package com.example.rickmorty.databinding;
import com.example.rickmorty.R;
import com.example.rickmorty.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityMainBindingImpl extends ActivityMainBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.chip_group_status_main, 2);
        sViewsWithIds.put(R.id.chip_dead_main, 3);
        sViewsWithIds.put(R.id.chip_alive_main, 4);
        sViewsWithIds.put(R.id.chip_unknow_main, 5);
        sViewsWithIds.put(R.id.chip_group_gender, 6);
        sViewsWithIds.put(R.id.chip_gender_male_main, 7);
        sViewsWithIds.put(R.id.chip_gender_female_main, 8);
        sViewsWithIds.put(R.id.chip_gender_unknow_main, 9);
        sViewsWithIds.put(R.id.recycler_main, 10);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener searchBarMainandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of mainViewModel.filterText.getValue()
            //         is mainViewModel.filterText.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(searchBarMain);
            // localize variables for thread safety
            // mainViewModel.filterText != null
            boolean mainViewModelFilterTextJavaLangObjectNull = false;
            // mainViewModel
            com.example.rickmorty.modules.modules.main.MainViewModel mainViewModel = mMainViewModel;
            // mainViewModel != null
            boolean mainViewModelJavaLangObjectNull = false;
            // mainViewModel.filterText
            androidx.lifecycle.MutableLiveData<java.lang.String> mainViewModelFilterText = null;
            // mainViewModel.filterText.getValue()
            java.lang.String mainViewModelFilterTextGetValue = null;



            mainViewModelJavaLangObjectNull = (mainViewModel) != (null);
            if (mainViewModelJavaLangObjectNull) {


                mainViewModelFilterText = mainViewModel.getFilterText();

                mainViewModelFilterTextJavaLangObjectNull = (mainViewModelFilterText) != (null);
                if (mainViewModelFilterTextJavaLangObjectNull) {




                    mainViewModelFilterText.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public ActivityMainBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }
    private ActivityMainBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (com.google.android.material.chip.Chip) bindings[4]
            , (com.google.android.material.chip.Chip) bindings[3]
            , (com.google.android.material.chip.Chip) bindings[8]
            , (com.google.android.material.chip.Chip) bindings[7]
            , (com.google.android.material.chip.Chip) bindings[9]
            , (com.google.android.material.chip.ChipGroup) bindings[6]
            , (com.google.android.material.chip.ChipGroup) bindings[2]
            , (com.google.android.material.chip.Chip) bindings[5]
            , (androidx.recyclerview.widget.RecyclerView) bindings[10]
            , (com.google.android.material.textfield.TextInputEditText) bindings[1]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.searchBarMain.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
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
        if (BR.mainViewModel == variableId) {
            setMainViewModel((com.example.rickmorty.modules.modules.main.MainViewModel) variable);
        }
        else if (BR.mainActivity == variableId) {
            setMainActivity((com.example.rickmorty.modules.modules.main.MainActivity) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setMainViewModel(@Nullable com.example.rickmorty.modules.modules.main.MainViewModel MainViewModel) {
        this.mMainViewModel = MainViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.mainViewModel);
        super.requestRebind();
    }
    public void setMainActivity(@Nullable com.example.rickmorty.modules.modules.main.MainActivity MainActivity) {
        this.mMainActivity = MainActivity;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeMainViewModelFilterText((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeMainViewModelFilterText(androidx.lifecycle.MutableLiveData<java.lang.String> MainViewModelFilterText, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
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
        com.example.rickmorty.modules.modules.main.MainViewModel mainViewModel = mMainViewModel;
        androidx.lifecycle.MutableLiveData<java.lang.String> mainViewModelFilterText = null;
        java.lang.String mainViewModelFilterTextGetValue = null;

        if ((dirtyFlags & 0xbL) != 0) {



                if (mainViewModel != null) {
                    // read mainViewModel.filterText
                    mainViewModelFilterText = mainViewModel.getFilterText();
                }
                updateLiveDataRegistration(0, mainViewModelFilterText);


                if (mainViewModelFilterText != null) {
                    // read mainViewModel.filterText.getValue()
                    mainViewModelFilterTextGetValue = mainViewModelFilterText.getValue();
                }
        }
        // batch finished
        if ((dirtyFlags & 0xbL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.searchBarMain, mainViewModelFilterTextGetValue);
        }
        if ((dirtyFlags & 0x8L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.searchBarMain, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, searchBarMainandroidTextAttrChanged);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): mainViewModel.filterText
        flag 1 (0x2L): mainViewModel
        flag 2 (0x3L): mainActivity
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}