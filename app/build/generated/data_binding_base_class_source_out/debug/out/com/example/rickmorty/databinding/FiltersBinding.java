// Generated by data binding compiler. Do not edit!
package com.example.rickmorty.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.rickmorty.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FiltersBinding extends ViewDataBinding {
  @NonNull
  public final Chip chipAliveMain;

  @NonNull
  public final LinearLayout chipContainer;

  @NonNull
  public final Chip chipDeadMain;

  @NonNull
  public final Chip chipGenderFemaleMain;

  @NonNull
  public final Chip chipGenderMaleMain;

  @NonNull
  public final Chip chipGenderUnknowMain;

  @NonNull
  public final ChipGroup chipGroupGenderMain;

  @NonNull
  public final ChipGroup chipGroupStatusMain;

  @NonNull
  public final Chip chipUnknowMain;

  protected FiltersBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Chip chipAliveMain, LinearLayout chipContainer, Chip chipDeadMain, Chip chipGenderFemaleMain,
      Chip chipGenderMaleMain, Chip chipGenderUnknowMain, ChipGroup chipGroupGenderMain,
      ChipGroup chipGroupStatusMain, Chip chipUnknowMain) {
    super(_bindingComponent, _root, _localFieldCount);
    this.chipAliveMain = chipAliveMain;
    this.chipContainer = chipContainer;
    this.chipDeadMain = chipDeadMain;
    this.chipGenderFemaleMain = chipGenderFemaleMain;
    this.chipGenderMaleMain = chipGenderMaleMain;
    this.chipGenderUnknowMain = chipGenderUnknowMain;
    this.chipGroupGenderMain = chipGroupGenderMain;
    this.chipGroupStatusMain = chipGroupStatusMain;
    this.chipUnknowMain = chipUnknowMain;
  }

  @NonNull
  public static FiltersBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.filters, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FiltersBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FiltersBinding>inflateInternal(inflater, R.layout.filters, root, attachToRoot, component);
  }

  @NonNull
  public static FiltersBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.filters, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FiltersBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FiltersBinding>inflateInternal(inflater, R.layout.filters, null, false, component);
  }

  public static FiltersBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static FiltersBinding bind(@NonNull View view, @Nullable Object component) {
    return (FiltersBinding)bind(component, view, R.layout.filters);
  }
}